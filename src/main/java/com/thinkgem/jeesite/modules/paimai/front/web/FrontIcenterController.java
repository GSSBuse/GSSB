package com.thinkgem.jeesite.modules.paimai.front.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.JsMaker;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;
import com.thinkgem.jeesite.modules.wx.web.domainname.IcenterController;


@Controller
@RequestMapping(value= {"${frontPath}/icenter"})
public class FrontIcenterController extends BaseController implements Constant{
	
	@Autowired
	IcenterController icenterController;
	
	@Autowired
	private DyClientService dyClientService; 
	
	@RequestMapping(value= {""})
	public String ibuyDetal(Model model) {
		DyClient currentDyClient = dyClientService.get(DySysUtils.getCurrentDyClient().getId());
		User broker = UserUtils.get(currentDyClient.getBrokerId());
		currentDyClient.setBroker(broker);
		UserUtils.getSession().setAttribute("current_dy_client", currentDyClient);
		
		DyClient editClient = new DyClient();
		editClient.setName(currentDyClient.getName());
		editClient.setMobile(currentDyClient.getMobile());
		editClient.setEmail(currentDyClient.getEmail());
		editClient.setWx(currentDyClient.getWx());
		editClient.setQq(currentDyClient.getQq());
		editClient.setAuthenticationMark(currentDyClient.getAuthenticationMark());
		editClient.setAuthenticationNegativeImageUrl(currentDyClient.getAuthenticationNegativeImageUrl());
		editClient.setAuthenticationPositiveImageUrl(currentDyClient.getAuthenticationPositiveImageUrl());
		editClient.setIDcardNumber(currentDyClient.getIDcardNumber());
		editClient.setDefaultIncomeExpense(currentDyClient.getDefaultIncomeExpense());
		editClient.setPayPassword(currentDyClient.getPayPassword());
		
		model.addAttribute("initData", JsonMapper.toJsonString(editClient));
		return "modules/paimai/front/icenter";
	}
	
	/**
	 * 修改并保存会员信息
	 */
	@ResponseBody
	@RequestMapping(value ={"dyInfoSave"},method = RequestMethod.POST)
	public AjaxResult dyInfoSave(Model model,
			@RequestParam(value="mobile", required=false) String mobile,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="wx", required=false) String wx,
			@RequestParam(value="qq", required=false) String qq,
			@RequestParam(value="name", required=false) String name) {
		try{
			DyClient currentDyClient = DySysUtils.getCurrentDyClient();
			currentDyClient = dyClientService.get(currentDyClient.getId());
			//currentDyClient.setMobile(mobile);
			//currentDyClient.setEmail(email);
			currentDyClient.setName(name);
			currentDyClient.setWx(wx);
			currentDyClient.setQq(qq);
			dyClientService.save(currentDyClient);
			UserUtils.getSession().setAttribute("current_dy_client", currentDyClient);
			return AjaxResult.makeSuccess("保存成功");
		} catch (Exception e) {
			return AjaxResult.makeError("保存个人信息失败:" + e.getMessage());
		}
	}
	
	/**
	 * 发送更改手机号的验证码
	 */
	@ResponseBody
	@RequestMapping(value ={"sendCaptcha"},method = RequestMethod.POST)
	public AjaxResult dyInfoSave(HttpServletRequest request, Model model,String mobile) {
		return icenterController.changeMobile(request, model, mobile);
	}
	
	/**
	 * 个人中心：验证码验证通过，更改手机号
	 */
	@RequestMapping(value = {"verificationPhone"}, method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult verificationPhone(Model model,String mobile, String verificationCode) {
		return icenterController.verificationPhone(model, mobile, verificationCode);
	}
	
	/**
	 * 个人中心：修改email,发送确认链接
	 */
	@RequestMapping(value = {"emailInput"}, method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult emailInput(Model model,String email,HttpServletRequest req, String verificationCode) {
		return icenterController.emailInput(model, email, req, verificationCode);
	}
	
	/**
	 * 提交身份认证信息页面
	 * 
	 * @param model
	 * @param email
	 * @param req
	 * @return
	 */
	@RequestMapping(value = {"idcardAuth"}, method={RequestMethod.GET})
	public String idcardAuth(Model model,HttpServletRequest req) {
		DyClient editClient = new DyClient();
		DyClient currentDyClient = dyClientService.get(DySysUtils.getCurrentDyClient().getId());
		
		if (!AUTHENTICATION_MARK_0.equals(currentDyClient.getAuthenticationMark())) {
			// 认证中和已经认证会员。不能再进入该页面
			return "redirect:" + frontPath + "/icenter.html";
		}
		
		editClient.setAuthenticationMark(currentDyClient.getAuthenticationMark());
		editClient.setAuthenticationNegativeImageUrl(currentDyClient.getAuthenticationNegativeImageUrl());
		editClient.setAuthenticationPositiveImageUrl(currentDyClient.getAuthenticationPositiveImageUrl());
		editClient.setIDcardNumber(currentDyClient.getIDcardNumber());
		
		model.addAttribute("initData", JsonMapper.toJsonString(editClient));
		
		return "modules/paimai/front/idcardAuth";
	}
	
	/**
	 * 提交身份认证信息页面
	 * 
	 * @param model
	 * @param email
	 * @param req
	 * @return
	 */
	@RequestMapping(value = {"idcardAuth"}, method={RequestMethod.POST})
	@ResponseBody
	public String idcardAuthPost(Model model, HttpServletRequest req, String idcardNumber, MultipartFile authenticationPositiveImageFile, MultipartFile authenticationNegativeImageFile) {

		JsMaker jm = JsMaker.born();
		
		try {
			DyClient currentDyClient = dyClientService.get(DySysUtils.getCurrentDyClient().getId());
			
			// 相对文件夹
			String relativePath = Global.USERFILES_BASE_URL
					+ currentDyClient.getBrokerId() + "/clientUploads/" + currentDyClient.getDyid() + "/";
			
			String realPath = Global.getUserfilesBaseDir() + relativePath;
			FileUtils.createDirectory(realPath);
			
			// 保存文件
			String posFilename = saveFile(authenticationPositiveImageFile, realPath);
			String negFilename = saveFile(authenticationNegativeImageFile, realPath);
			
			currentDyClient.setAuthenticationPositiveImageUrl(req.getContextPath() + relativePath + posFilename);
			currentDyClient.setAuthenticationNegativeImageUrl(req.getContextPath() + relativePath + negFilename);
			currentDyClient.setIDcardNumber(idcardNumber);
			currentDyClient.setAuthenticationMark(AUTHENTICATION_MARK_2);
			
			dyClientService.save(currentDyClient);
			
			// 更新联系人
			User broker = UserUtils.get(currentDyClient.getBrokerId());
			currentDyClient.setBroker(broker);
			UserUtils.getSession().setAttribute("current_dy_client", currentDyClient);
			
			// 提示消息
			jm.append("top.$.jBox.tip('提交成功','success',{opacity:0});");
			jm.append("setTimeout(function(){ \n"
					+ " top.location.href = '../icenter.html';"
					+ "}, 1200);");
			
		} catch (Exception e) {
			logger.error("文件上传失败", e);
			jm.addAlert("$.jBox.tip('文件上传失败:"+e.getLocalizedMessage()+"','error');");
			jm.append("top.$('#submitChange').attr('disabled', false);");
		}
		
		return jm.toJs();
	}
	
	private static final long maxSize = 5 * 1024 * 1024;
	
	/**
	 * 保存文件
	 * 
	 * @param obj
	 *            要上传的文件域
	 * @param file
	 * @return
	 */
	private String saveFile(MultipartFile item, String realPath) throws Exception{
		String fileName = item.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

		if (item.getSize() > maxSize) { // 检查文件大小
			throw new Exception("上传文件大小超过限制(5MB)");
		} else {
			String newFileName;
//			if ("".equals(fileName.trim())) {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
//			} else {
//				newFileName = fileName + "." + fileExt;
//			}
			// .../basePath/dirName/yyyyMMdd/yyyyMMddHHmmss_xxx.xxx
			try {
				File uploadedFile = new File(realPath, newFileName);

				FileUtils.writeByteArrayToFile(uploadedFile, item.getBytes());
				
				return newFileName;
			} catch (IOException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
