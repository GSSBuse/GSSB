/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.gb;

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
import com.thinkgem.jeesite.modules.sys.entity.gb.GbUser;
import com.thinkgem.jeesite.modules.sys.service.gb.GbUserService;

/**
 * 用户信息管理Controller
 * @author 管理员
 * @version 2017-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gb/gbUser")
public class GbUserController extends BaseController {

	@Autowired
	private GbUserService gbUserService;
	
	@ModelAttribute
	public GbUser get(@RequestParam(required=false) String id) {
		GbUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbUserService.get(id);
		}
		if (entity == null){
			entity = new GbUser();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gb:gbUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbUser gbUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbUser> page = gbUserService.findPage(new Page<GbUser>(request, response), gbUser); 
		model.addAttribute("page", page);
		return "modules/sys/gb/gbUserList";
	}

	@RequiresPermissions("sys:gb:gbUser:view")
	@RequestMapping(value = "form")
	public String form(GbUser gbUser, Model model) {
		model.addAttribute("gbUser", gbUser);
		return "modules/sys/gb/gbUserForm";
	}

	@RequiresPermissions("sys:gb:gbUser:edit")
	@RequestMapping(value = "save")
	public String save(GbUser gbUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbUser)){
			return form(gbUser, model);
		}
		gbUserService.save(gbUser);
		addMessage(redirectAttributes, "保存管理用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbUser/?repage";
	}
	
	@RequiresPermissions("sys:gb:gbUser:edit")
	@RequestMapping(value = "delete")
	public String delete(GbUser gbUser, RedirectAttributes redirectAttributes) {
		gbUserService.delete(gbUser);
		addMessage(redirectAttributes, "删除管理用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbUser/?repage";
	}

}