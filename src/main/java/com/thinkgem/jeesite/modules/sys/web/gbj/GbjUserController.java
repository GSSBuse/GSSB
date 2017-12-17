/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.gbj;

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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUser;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserService;

/**
 * 用户信息表Controller
 * @author snnu
 * @version 2017-12-17
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjUser")
public class GbjUserController extends BaseController {

	@Autowired
	private GbjUserService gbjUserService;
	
	@ModelAttribute
	public GbjUser get(@RequestParam(required=false) String id) {
		GbjUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjUserService.get(id);
		}
		if (entity == null){
			entity = new GbjUser();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjUser gbjUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjUser> page = gbjUserService.findPage(new Page<GbjUser>(request, response), gbjUser); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjUserList";
	}

	@RequiresPermissions("sys:gbj:gbjUser:view")
	@RequestMapping(value = "form")
	public String form(GbjUser gbjUser, Model model) {
		model.addAttribute("gbjUser", gbjUser);
		return "modules/sys/gbj/gbjUserForm";
	}

	@RequiresPermissions("sys:gbj:gbjUser:edit")
	@RequestMapping(value = "save")
	public String save(GbjUser gbjUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjUser)){
			return form(gbjUser, model);
		}
		gbjUserService.save(gbjUser);
		addMessage(redirectAttributes, "保存用户信息增删改查成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUser/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjUser:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjUser gbjUser, RedirectAttributes redirectAttributes) {
		gbjUserService.delete(gbjUser);
		addMessage(redirectAttributes, "删除用户信息增删改查成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUser/?repage";
	}

}