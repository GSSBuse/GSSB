/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.dy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 会员信息管理Controller
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyClient")
public class DyClientController extends BaseController {

	@Autowired
	private DyClientService dyClientService;
	@Autowired
	private DyFinanceService dyFinanceService;
	
	@ModelAttribute
	public DyClient get(@RequestParam(required=false) String id) {
		DyClient entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyClientService.get(id);
		}
		if (entity == null){
			entity = new DyClient();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyClient:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyClient dyClient, HttpServletRequest request, HttpServletResponse response, Model model) {
		//if(dyClient.getBroker() == null){
		String allList = request.getParameter("allList");
		if(StringUtils.isBlank(allList)){
			if(!UserUtils.isAdmin() && UserUtils.isSecurity()){	//判断当前登录者是不是经纪人
				allList = "0";
			}else{
				allList = "1";
			}
		}
		if(StringUtils.equals("0", allList)){
			dyClient.setBrokerId(UserUtils.getUser().getId());		//查看自己
		}
		try{
			Page<DyClient> page = dyClientService.findPage(new Page<DyClient>(request, response), dyClient); 	
			model.addAttribute("page", page);
			model.addAttribute("allList", allList);
		}catch(Exception e){
			addMessage(model, "查询会员列表失败，系统出错，请联系系统管理员");
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "modules/sys/dy/dyClientList";
	}

	@RequiresPermissions("sys:dy:dyClient:view")
	@RequestMapping(value = "form")
	public String form(DyClient dyClient, Model model) {
		DyFinance dyFinance = new DyFinance();
		dyFinance.setClientId(dyClient.getId());
		dyClient.setDyFinance(dyFinanceService.get(dyFinance));		//账户信息
		dyClient.setBroker(UserUtils.get(dyClient.getBrokerId()));
		model.addAttribute("dyClient", dyClient);
		return "modules/sys/dy/dyClientForm";
	}

	@RequiresPermissions("sys:dy:dyClient:edit")
	@RequestMapping(value = "save")
	public String save(DyClient dyClient, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		/*米友号检查*/
		if(!StringUtils.equals(dyClient.getDyid(),request.getParameter("oldDyid"))){
			if(dyClientService.hasDyid(dyClient.getDyid()) != 0){
				dyClient.setDyid(request.getParameter("oldDyid"));
				addMessage(model, "修改失败，该米友号已经存在");
				return form(dyClient, model);
			}
		}
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(dyClient.getNewPayPassword())) {
			dyClient.setPayPassword(DigestUtils.md5Hex(dyClient.getNewPayPassword()));
		//	dyClient.setPayPassword(SystemService.entryptPassword(dyClient.getNewPayPassword()));
		}
		if (!beanValidator(model, dyClient)){
			return form(dyClient, model);
		}
		try{
			dyClientService.save(dyClient);
			/*保存日志*/
			LogUtils.saveSpecialLog(request, null);
			addMessage(redirectAttributes, "保存会员信息成功");
		}catch(Exception e){
			addMessage(redirectAttributes, "保存会员信息失败，系统出错，请联系系统管理员");
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyClient/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyClient:edit")
	@RequestMapping(value = "delete")
	public String delete(DyClient dyClient, RedirectAttributes redirectAttributes) {
		dyClientService.delete(dyClient);
		addMessage(redirectAttributes, "删除会员信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyClient/?repage";
	}
	/**
	 * 身份未认证的会员数量
	 */
	@RequestMapping(value = "unmarkCount")
	@ResponseBody
	public String unmarkCount(){
		if(UserUtils.isAdmin()){
			//如果是管理员则，查看全部
			return String.valueOf(dyClientService.unmarkCount());
		}else{
			String userId = UserUtils.getUser().getId();
			return String.valueOf(dyClientService.unmarkCountBroker(userId));
		}

	}
}