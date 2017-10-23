/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.entity.WxInterfaceInfo;
import com.thinkgem.jeesite.modules.wx.service.WxInterfaceInfoService;

/**
 * 接口验证信息Controller
 * @author shenzb.fnst
 * @version 2015-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/wx/wxInterfaceInfo")
public class WxInterfaceInfoController extends BaseController {

	@Autowired
	private WxInterfaceInfoService wxInterfaceInfoService;
	
	@ModelAttribute
	public WxInterfaceInfo get(@RequestParam(required=false) String id) {
		WxInterfaceInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wxInterfaceInfoService.get(id);
		}
		if (entity == null){
			entity = new WxInterfaceInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("wx:wxInterfaceInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxInterfaceInfo wxInterfaceInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WxInterfaceInfo> page = wxInterfaceInfoService.findPage(new Page<WxInterfaceInfo>(request, response), wxInterfaceInfo); 
		model.addAttribute("page", page);
		return "modules/wx/wxInterfaceInfoList";
	}

	@RequiresPermissions("wx:wxInterfaceInfo:view")
	@RequestMapping(value = "form")
	public String form(WxInterfaceInfo wxInterfaceInfo, Model model) {
		wxInterfaceInfo.setOffice(UserUtils.getUser().getCompany());
		model.addAttribute("wxInterfaceInfo", wxInterfaceInfo);
		return "modules/wx/wxInterfaceInfoForm";
	}

	@RequiresPermissions("wx:wxInterfaceInfo:edit")
	@RequestMapping(value = "save")
	public String save(WxInterfaceInfo wxInterfaceInfo, Model model, RedirectAttributes redirectAttributes) {
		wxInterfaceInfo.setOffice(UserUtils.getUser().getCompany());
		if (!beanValidator(model, wxInterfaceInfo)){
			return form(wxInterfaceInfo, model);
		}
		wxInterfaceInfoService.save(wxInterfaceInfo);
		addMessage(redirectAttributes, "保存接口验证信息成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxInterfaceInfo/?repage";
	}
	
	@RequiresPermissions("wx:wxInterfaceInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(WxInterfaceInfo wxInterfaceInfo, RedirectAttributes redirectAttributes) {
		wxInterfaceInfoService.delete(wxInterfaceInfo);
		addMessage(redirectAttributes, "删除接口验证信息成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxInterfaceInfo/?repage";
	}

	/**
	 * 接口信息显示
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("wx:wxInterfaceInfo:view")
	@RequestMapping(value = "info")
	public String info(WxInterfaceInfo wxInterfaceInfo, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		WxInterfaceInfo currentWxInterfaceInfo = wxInterfaceInfoService.getWxInterfaceInfoByCompanyId(currentUser.getCompany().getId());
//		if (StringUtils.isNotBlank(wxInterfaceInfo.getCorpid())){
//			if(Global.isDemoMode()){
//				model.addAttribute("message", "演示模式，不允许操作！");
//				return "modules/sys/userInfo";
//			}
//			if (currentWxInterfaceInfo == null) {
//				currentWxInterfaceInfo = new WxInterfaceInfo();
//			}
//			currentWxInterfaceInfo.setOffice(wxInterfaceInfo.getOffice());
//			currentWxInterfaceInfo.setCorpid(wxInterfaceInfo.getCorpid());
//			currentWxInterfaceInfo.setProviderSecret(wxInterfaceInfo.getProviderSecret());
//			wxInterfaceInfoService.save(currentWxInterfaceInfo);
//			model.addAttribute("message", "保存信息成功");
//			model.addAttribute("wxInterfaceInfo", currentWxInterfaceInfo);
//		} else {
//			model.addAttribute("wxInterfaceInfo", new WxInterfaceInfo());
//		}
		model.addAttribute("wxInterfaceInfo", currentWxInterfaceInfo == null ? new WxInterfaceInfo() : currentWxInterfaceInfo);
		//model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/wx/wxInterfaceInfo";
	}

}