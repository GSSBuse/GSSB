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
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.entity.WxCallbackInfo;
import com.thinkgem.jeesite.modules.wx.service.WxCallbackInfoService;

/**
 * 回调验证信息Controller
 * @author shenzb.fnst
 * @version 2015-08-04
 */
@Controller
@RequestMapping(value = "${adminPath}/wx/wxCallbackInfo")
public class WxCallbackInfoController extends BaseController {

	@Autowired
	private WxCallbackInfoService wxCallbackInfoService;
	
	@ModelAttribute
	public WxCallbackInfo get(@RequestParam(required=false) String id) {
		WxCallbackInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wxCallbackInfoService.get(id);
		}
		if (entity == null){
			entity = new WxCallbackInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("wx:wxCallbackInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxCallbackInfo wxCallbackInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WxCallbackInfo> page = wxCallbackInfoService.findPage(new Page<WxCallbackInfo>(request, response), wxCallbackInfo); 
		model.addAttribute("page", page);
		return "modules/wx/wxCallbackInfoList";
	}

	@RequiresPermissions("wx:wxCallbackInfo:view")
	@RequestMapping(value = "form")
	public String form(WxCallbackInfo wxCallbackInfo, Model model) {
		wxCallbackInfo.setOffice(UserUtils.getUser().getCompany());
		model.addAttribute("wxCallbackInfo", wxCallbackInfo);
		return "modules/wx/wxCallbackInfoForm";
	}

	@RequiresPermissions("wx:wxCallbackInfo:edit")
	@RequestMapping(value = "save")
	public String save(WxCallbackInfo wxCallbackInfo, Model model, RedirectAttributes redirectAttributes) {
		wxCallbackInfo.setOffice(UserUtils.getUser().getCompany());
		if (!beanValidator(model, wxCallbackInfo)){
			return form(wxCallbackInfo, model);
		}
		wxCallbackInfoService.save(wxCallbackInfo);
		addMessage(redirectAttributes, "保存回调验证成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxCallbackInfo/?repage";
	}
	
	@RequiresPermissions("wx:wxCallbackInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(WxCallbackInfo wxCallbackInfo, RedirectAttributes redirectAttributes) {
		wxCallbackInfoService.delete(wxCallbackInfo);
		addMessage(redirectAttributes, "删除回调验证成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxCallbackInfo/?repage";
	}

}