/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.dy;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.TransactionInformation;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBidhistoryService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.sys.utils.NewsUpdateFlagUtil;
import com.thinkgem.jeesite.modules.sys.utils.ShowDomainCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidCashInfo;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 域名信息管理Controller
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyDomainname")
public class DyDomainnameController extends BaseController {

	@Autowired
	private DyDomainnameService dyDomainnameService;
	@Autowired
	private DyClientService dyClientService;
	@Autowired
	private SystemService systemservice;
	@Autowired
	private DyBidhistoryService dyBidhistoryService;
	@Autowired
	private DyFinanceService dyFinanceService;
	
	@ModelAttribute
	public DyDomainname get(@RequestParam(required=false) String id) {
		DyDomainname entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyDomainnameService.get(id);
		}
		if (entity == null){
			entity = new DyDomainname();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyDomainname:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyDomainname dyDomainname, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Page<DyDomainname> page;
		if(StringUtils.equals("0", dyDomainname.getStatus())){	//设置域名审核状态
			dyDomainname.setStatus(null);
		}
		String allList = request.getParameter("allList");  //是否查看全部
		if(StringUtils.isBlank(allList)){
			if(!UserUtils.isAdmin() && UserUtils.isSecurity()){	//判断当前登录者是不是经纪人
				allList = "0";
			}else{
				allList = "1";
			}
		}
		if(StringUtils.equals("0", allList)){
			 String userId = UserUtils.getUser().getId();
			 //查看自己
			 page = dyDomainnameService.findPageByUserId(new Page<DyDomainname>(request, response), dyDomainname, userId); 
		}else{
			page = dyDomainnameService.findPageByUserId(new Page<DyDomainname>(request, response), dyDomainname, null); 
		}
		model.addAttribute("allList",allList);
		model.addAttribute("page", page);
		return "modules/sys/dy/dyDomainnameList";
	}
	/**
	 * 域名成交管理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:dy:dyDomainname:view")
	@RequestMapping(value = "deal")
	public String deal(BidCashInfo bidCashInfo,HttpServletRequest request, HttpServletResponse response, Model model){
		//分页查询
		Page<BidCashInfo> page = new Page<BidCashInfo>(request, response);
		String allList = request.getParameter("allList");		//是查看所有经纪人下的域名
		if(StringUtils.isBlank(allList)){
			if(!UserUtils.isAdmin() && UserUtils.isSecurity()){	//判断当前登录者是不是经纪人
				allList = "0";
			}else{
				allList = "1";
			}
		}
		if(StringUtils.equals("0", allList)){
			bidCashInfo.setQueryBrokerId(UserUtils.getUser().getId());
		}
		dyDomainnameService.findConfirmList(page, bidCashInfo);
		model.addAttribute("page", page);
		model.addAttribute("allList", allList);
		return "modules/sys/dy/dyDomainDealList";
	}
	@RequiresPermissions("sys:dy:dyDomainname:view")
	@RequestMapping(value = "form")
	public String form(DyDomainname dyDomainname, Model model) {
		if (StringUtils.isNotBlank(dyDomainname.getClientId())) {
			dyDomainname.setDyClient(dyClientService.get(dyDomainname.getClientId()));
		}
		/*检查是否有红包功能*/
		model.addAttribute("isShareBonus", DySysUtils.SHARE_BONUS_ENABLE);
		/*选择审核还是修改*/
		if(StringUtils.equals(dyDomainname.getStatus(), Constant.DOMAIN_STATUS_01) && StringUtils.isNotBlank(dyDomainname.getId())){
			dyDomainnameService.downloadImagesSync(dyDomainname);
			model.addAttribute("dyDomainname", dyDomainname);
			return "modules/sys/dy/dyDomainnameFormConfirm";
		}else{
			model.addAttribute("dyDomainname", dyDomainname);
			return "modules/sys/dy/dyDomainnameForm";
		}
	}
	@RequiresPermissions("sys:dy:dyDomainname:view")
	@RequestMapping(value = "bidCashInfoForm")
	public String bidCashInfoForm(BidCashInfo bidCashInfo, Model model){
		BidCashInfo newBidCashInfo = dyDomainnameService.finddealByDomainId(bidCashInfo.getDomainId());
		String showSave = "1";
		if(StringUtils.equals(Constant.DOMAIN_STATUS_14,newBidCashInfo.getStatus())){
			if(!UserUtils.isAdmin() && !StringUtils.equals(newBidCashInfo.getBuyBrokerId(), UserUtils.getUser().getId())){
				showSave = "0";
			}
		}
		model.addAttribute("bidCashInfo", newBidCashInfo);
		model.addAttribute("showSave", showSave);
		return "modules/sys/dy/dyDomainDealForm";
	}
	/**
	 * 更新域名交易信息(只能更新交易时间和交易状态)，并进行相关财务操作
	 */
	@RequiresPermissions("sys:dy:dyDomainname:edit")
	@RequestMapping(value = "dealSave")
	public String dealSave(BidCashInfo bidCashInfo,HttpServletRequest request ,Model model, RedirectAttributes redirectAttributes) throws Exception {
		String oldStatus = request.getParameter("oldStatus");
		/*判断域名信息是否已经过时*/
		DyDomainname dyDomainname = dyDomainnameService.get(bidCashInfo.getDomainId());
		if(!StringUtils.equals(dyDomainname.getStatus(),oldStatus)){
			addMessage(redirectAttributes, "域名信息已过时，请刷新页面");
			return bidCashInfoForm(bidCashInfo, model);
		}
		try{
			//设置交易信息(只能更新交易时间和交易状态)
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
			dyDomainname.setStatus(bidCashInfo.getStatus());
			dyDomainname.setWaitTime(sdf.parse(bidCashInfo.getWaitTime()));
			/*更新操作*/
			if(StringUtils.equals(oldStatus, bidCashInfo.getStatus())){		//如果只修改了交易结束时间
				dyDomainnameService.save(dyDomainname);
			}else{
				/*修改域名信息状态，并进行相关财务操作*/
				if(StringUtils.equals(Constant.DOMAIN_STATUS_12, dyDomainname.getStatus())){
					//待卖家转移域名
					dyFinanceService.pay(dyClientService.get(bidCashInfo.getBuyClientId()), String.valueOf(bidCashInfo.getBidAmount()), bidCashInfo.getDomainId());
				}else if(StringUtils.equals(Constant.DOMAIN_STATUS_13, dyDomainname.getStatus())){
					//设置操作限制时间
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					cal.add(Calendar.DAY_OF_MONTH, DySysUtils.OPERATE_LIMIT_TIME_RECEIVE);
					dyDomainname.setWaitTime(cal.getTime());
					//待买家确认
					dyDomainnameService.save(dyDomainname);
					//给买家发送微信消息
					Message message = new Message();
					String title = DySysUtils.TEMPLATE_TITLE_0012;
					String content = DySysUtils.TEMPLATE_MESSAGE_0012;
					content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
					content = content.replace("{{seller.DATA}}", bidCashInfo.getSellNickname());
					content = content.replace("{{waittime.DATA}}", bidCashInfo.getWaitTime());
					message.SendNews(WeChat.getAccessToken(), bidCashInfo.getBuyOpenId(), title, content, dyDomainname.getId());
				}else if(StringUtils.equals(Constant.DOMAIN_STATUS_14, dyDomainname.getStatus())){
					//设置操作限制时间
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					cal.add(Calendar.DAY_OF_MONTH, DySysUtils.OPERATE_LIMIT_TIME_CONFIRM);
					dyDomainname.setWaitTime(cal.getTime());
					//待经纪人确认交易完成
					dyDomainnameService.save(dyDomainname);
					/*给买家发送微信消息*/
					Message message1 = new Message();
					String title1 = DySysUtils.TEMPLATE_TITLE_0015;
					String content1 = DySysUtils.TEMPLATE_MESSAGE_0015;
					content1 = content1.replace("{{domainname.DATA}}", dyDomainname.getName());
					content1 = content1.replace("{{seller.DATA}}", bidCashInfo.getSellNickname());
					content1 = content1.replace("{{price.DATA}}", Long.toString(bidCashInfo.getBidAmount()));
					message1.SendNews(WeChat.getAccessToken(), bidCashInfo.getBuyOpenId(), title1, content1, dyDomainname.getId());
					/*给卖家发送微信消息*/
					Message message = new Message();
					String title = DySysUtils.TEMPLATE_TITLE_0016;
					String content = DySysUtils.TEMPLATE_MESSAGE_0016;
					content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
					content = content.replace("{{buyer.DATA}}", bidCashInfo.getBuyNickname());
					content = content.replace("{{price.DATA}}", Long.toString(bidCashInfo.getBidAmount()));
					message.SendNews(WeChat.getAccessToken(), bidCashInfo.getSellOpenId(), title, content, dyDomainname.getId());
				}else if(StringUtils.equals(Constant.DOMAIN_STATUS_15, dyDomainname.getStatus())){
					//交易结束
					dyDomainnameService.updateFinance(bidCashInfo ,dyDomainname);
				}else if(StringUtils.equals(Constant.DOMAIN_STATUS_21, dyDomainname.getStatus())){
					if(StringUtils.equals(Constant.DOMAIN_STATUS_11, oldStatus)){
						//从“待买家付款”状态，变为“买家违约”
						TransactionInformation ti = new TransactionInformation();
						ti.setSellerId(bidCashInfo.getSellClientId());
						ti.setBuyerId(bidCashInfo.getBuyClientId());
						ti.setBidAmount(bidCashInfo.getBidAmount());
						ti.setDomainnameId(bidCashInfo.getDomainId());
						dyBidhistoryService.buyerBreachHandle(ti);
					}else{
						//买家违约(买家已付款)
						dyBidhistoryService.buyerLoser(bidCashInfo,dyDomainname);
					}
				}else if(StringUtils.equals(Constant.DOMAIN_STATUS_22, dyDomainname.getStatus())){
					//卖家违约
					TransactionInformation ti = new TransactionInformation();
					ti.setSellerId(bidCashInfo.getSellClientId());
					ti.setBuyerId(bidCashInfo.getBuyClientId());
					ti.setBidAmount(bidCashInfo.getBidAmount());
					ti.setDomainnameId(bidCashInfo.getDomainId());
					dyBidhistoryService.sellerBreachHandle(ti);
				}
			}
			/*保存日志*/
			LogUtils.saveSpecialLog(request, null);
			addMessage(redirectAttributes, "保存域名信息成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "更新域名交易信息失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyDomainname/deal/?repage";
	}
	@RequiresPermissions("sys:dy:dyDomainname:edit")
	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,DyDomainname dyDomainname, Model model, RedirectAttributes redirectAttributes) throws Exception {
		try{
			DyClient dyClient = dyClientService.getByDyid(dyDomainname.getDyClient().getDyid());
			if(dyClient == null){
				addMessage(model, "保存失败，卖家不存在，请检查米友号");
				return form(dyDomainname, model);
			}
			//根据米友号获取会员ＩＤ
			dyDomainname.setClientId(dyClient.getId());
			if (!beanValidator(model, dyDomainname)){
				return form(dyDomainname, model);
			}
			if(StringUtils.isBlank(dyDomainname.getId())){
				//域名提交，保存
				//dyDomainname.setStatus(Constant.DOMAIN_STATUS_01);
				dyDomainnameService.submitDomain(dyDomainname, dyClient);
			}else{
				//修改域名，保存
				dyDomainnameService.domainSave(dyDomainname,dyClient);
			}
			/*清除域名缓存*/
			ShowDomainCacheUtil.clearCache();
			/*保存日志*/
			LogUtils.saveSpecialLog(request, null);
			addMessage(redirectAttributes, "保存域名信息成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "编辑域名信息失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyDomainname/?repage";
	}
	/**
	 * 域名审核
	 */
	@RequiresPermissions("sys:dy:dyDomainname:edit")
	@RequestMapping(value = "savePass")
	public String savePass(DyDomainname dyDomainname,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws Exception {	
		/*判断域名信息是否已经过时*/
		DyDomainname newDomainname = dyDomainnameService.get(dyDomainname.getId());
		if(!StringUtils.equals(newDomainname.getStatus(),Constant.DOMAIN_STATUS_01)){
			addMessage(redirectAttributes, "域名信息过时，请刷新页面");
			return "redirect:"+Global.getAdminPath()+"/sys/dy/dyDomainname/?repage";
		}
		if (!beanValidator(model, dyDomainname)){
			return "redirect:"+Global.getAdminPath()+"/sys/dy/dyDomainname/?repage";
		}
		try{
			/*检查交易时间和账户*/
			if(StringUtils.equals(Constant.DOMAIN_STATUS_03,dyDomainname.getStatus())){
				//审核通过的检查
				int resultType = dyDomainnameService.checkDealNums(dyDomainname);//计算交易结束时间
				if(resultType != 3){
					if(resultType == 0){
						addMessage(model, "当天交易的域名已满,审核失败,请重新设定交易时间");
					}else if(resultType == 1){
						addMessage(model, "异常，保证金冻结记录未找到");
					}else if(resultType == 4){
						addMessage(model, "异常，红包资金扣除记录未找到");
					}else if(resultType == 2){
						addMessage(model, "交易结束时间已过时,审核失败,请重新设定交易时间");
					}
					dyDomainname.setStatus(Constant.DOMAIN_STATUS_01);
					return form(dyDomainname, model);
				}
			}
			/*更新域名信息，和相关财务操作*/
			if(StringUtils.equals(Constant.DOMAIN_STATUS_03,dyDomainname.getStatus())){
				//审核通过的操作
				dyDomainnameService.updateDomainStatus(dyDomainname);
				/*清除域名缓存*/
				ShowDomainCacheUtil.clearCache();
				NewsUpdateFlagUtil.setUpdateTimestamp();
			}else{
				//审核失败的操作，需退回用户的保证金、红包
				dyDomainnameService.verifyToFailure(dyDomainname);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "域名审核失败【"+e.getMessage()+"】");
			return "redirect:"+Global.getAdminPath()+"/sys/dy/dyDomainname/?status=01&repage";
		}
		/*保存日志*/
		LogUtils.saveSpecialLog(request, null);
		
		// 发送通知消息
		if(StringUtils.equals(Constant.DOMAIN_STATUS_03,dyDomainname.getStatus())){
			Message message = new Message();
			String title = DySysUtils.TEMPLATE_TITLE_0004;
			String content = DySysUtils.TEMPLATE_MESSAGE_0004;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			content = content.replace("{{endtime.DATA}}", df.format(dyDomainname.getEndTime()));
			message.SendNews(WeChat.getAccessToken(), dyClientService.get(dyDomainname.getClientId()).getOpenid(), title, content, dyDomainname.getId());
		}else{
			Message message = new Message();
			String title = DySysUtils.TEMPLATE_TITLE_0003;
			String content = DySysUtils.TEMPLATE_MESSAGE_0003;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			message.SendNews(WeChat.getAccessToken(), dyClientService.get(dyDomainname.getClientId()).getOpenid(), title, content, "审核失败");
		}	
		addMessage(redirectAttributes, "域名信息修改成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyDomainname/?status=01&repage";
	}
	/**流拍、停止拍卖
	 * @throws Exception */
	@RequiresPermissions("sys:dy:dyDomainname:edit")
	@ResponseBody
	@RequestMapping(value = "stopSell")
	public String stopSell(DyDomainname dyDomainname, HttpServletRequest request, Model model) throws Exception{
		/*判断域名信息是否已经过时*/
		DyDomainname newDomainname = dyDomainnameService.get(dyDomainname.getId());
		if(!StringUtils.equals(newDomainname.getStatus(),Constant.DOMAIN_STATUS_03)){
			return "域名信息过时，请刷新页面";
		}
		if(StringUtils.isNotBlank(dyDomainname.getId())){
			try{
				dyDomainnameService.stopSell(dyDomainname.getId());
				/*清除域名缓存*/
				ShowDomainCacheUtil.clearCache();
				/*保存日志*/
				LogUtils.saveSpecialLog(request,dyDomainnameService.get(dyDomainname.getId()).toString());
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());
				return e.getMessage();
			}
		}
		return "success";
	}
	/**
	 * 确认支付保证金
	 * @throws Exception */
	@RequiresPermissions("sys:dy:dyDomainname:edit")
	@ResponseBody
	@RequestMapping(value = "confirmPayDeposit")
	public String confirmPayDeposit(DyDomainname dyDomainname, HttpServletRequest request, Model model) throws Exception{
		/*判断域名信息是否已经过时*/
		DyDomainname newDomainname = dyDomainnameService.get(dyDomainname.getId());
		if(!StringUtils.equals(newDomainname.getStatus(),Constant.DOMAIN_STATUS_00)){
			return "域名信息过时，请刷新页面";
		}
		if(StringUtils.isNotBlank(dyDomainname.getId())){
			try{
				DyClient client = dyClientService.get(dyDomainname.getClientId());
				boolean isMoneyEnough = dyDomainnameService.submitDomain(dyDomainname,client);
				if(isMoneyEnough){
					/*保存日志*/
					LogUtils.saveSpecialLog(request,dyDomainnameService.get(dyDomainname.getId()).toString());
					return "支付保证金成功";
				}else{
					/*保存日志*/
					LogUtils.saveSpecialLog(request,dyDomainnameService.get(dyDomainname.getId()).toString());
					return "失败，提醒会员充值";
				}
			}catch(Exception e){
				logger.error(e.getMessage());
				return e.getMessage();
			}
		}
		return "无此域名，请刷新页面";
	}
	
	@RequiresPermissions("sys:dy:dyDomainname:edit")
	@RequestMapping(value = "delete")
	public String delete(DyDomainname dyDomainname, RedirectAttributes redirectAttributes) {
		dyDomainnameService.delete(dyDomainname);
		addMessage(redirectAttributes, "删除域名信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyDomainname/?repage";
	}
	@RequiresPermissions("sys:dy:dyDomainname:view")
	@ResponseBody
	@RequestMapping(value = "selectList")
	public List<Map<String , Object>> selectList(HttpServletResponse response){
		List<Map<String , Object>> mapList = Lists.newArrayList();
		List<DyDomainname> list = dyDomainnameService.findList(new DyDomainname());
		for(DyDomainname d : list){
			Map<String , Object> map = Maps.newHashMap();
			map.put("id", d.getId());
			map.put("name", StringUtils.replace(d.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
	/**
	 * 获取未审核的域名数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "unconfirmCount")
	public String unconfirmCount(){
		if(UserUtils.isAdmin()){
			//如果是管理员，查看全部
			return String.valueOf(dyDomainnameService.unconfirmCount());
		}else{
			//如果是经济人，仅统计自己的交易数量
			String userId = UserUtils.getUser().getId();
			return String.valueOf(dyDomainnameService.unconfirmCountBroker(userId));
		}
	}
	/**
	 * 获取等待经济人确认交易结束的域名数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "unfinishCount")
	public String unfinishCount(){
		if(UserUtils.isAdmin()){
			//如果是管理员，查看全部
			return String.valueOf(dyDomainnameService.unfinishCount());
		}else{
			//如果是经济人，仅统计自己的交易数量
			String userId = UserUtils.getUser().getId();
			return String.valueOf(dyDomainnameService.unfinishCountBroker(userId));
		}
	}

	/**
	 * 压缩图片界面
	 */
	@RequiresPermissions("sys:dy:dyDomainname:view")
	@RequestMapping(value = "setImageSize")
	public String setImageSize(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){
		String imagePath = request.getParameter("image");
		if(StringUtils.isBlank(imagePath)){
			addMessage(redirectAttributes, "请先上传图片");  
		} 
		try {
			String path = Global.getUserfilesBaseDir() + imagePath.substring(imagePath.indexOf(Global.USERFILES_BASE_URL));
			File imageFile = new File(path);
			Image image = ImageIO.read(imageFile);
			int imageHeight = image.getHeight(null);
			int imageWitdh = image.getWidth(null);
			model.addAttribute("imageHeight", imageHeight);
			model.addAttribute("imageWitdh", imageWitdh);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("image",request.getParameter("image"));
		return "modules/sys/dy/setImageSize";
	}
	
	/**
	 * 压缩图片
	 */
	@RequiresPermissions("sys:dy:dyDomainname:view")
	@RequestMapping(value = "updateImageSize")
	public String updateImageSize(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){
		String imagePath = request.getParameter("imagePath");
		DySysUtils.formatPicture(imagePath);
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyDomainname/setImageSize?image="+imagePath;
	}
}