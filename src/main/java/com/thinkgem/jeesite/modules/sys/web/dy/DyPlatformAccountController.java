/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.dy;

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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformAccount;
import com.thinkgem.jeesite.modules.sys.service.dy.DyPlatformAccountService;

/**
 * 平台总账户管理Controller
 * @author quanyf.fnst
 * @version 2015-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyPlatformAccount")
public class DyPlatformAccountController extends BaseController {

	@Autowired
	private DyPlatformAccountService dyPlatformAccountService;
	
	@ModelAttribute
	public DyPlatformAccount get(@RequestParam(required=false) String id) {
		DyPlatformAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyPlatformAccountService.get(id);
		}
		if (entity == null){
			entity = new DyPlatformAccount();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyPlatformAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyPlatformAccount dyPlatformAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyPlatformAccount> page = dyPlatformAccountService.findPage(new Page<DyPlatformAccount>(request, response), dyPlatformAccount); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyPlatformAccountList";
	}

	@RequiresPermissions("sys:dy:dyPlatformAccount:view")
	@RequestMapping(value = "form")
	public String form(DyPlatformAccount dyPlatformAccount, Model model) {
		model.addAttribute("dyPlatformAccount", dyPlatformAccount);
		return "modules/sys/dy/dyPlatformAccountForm";
	}

	@RequiresPermissions("sys:dy:dyPlatformAccount:edit")
	@RequestMapping(value = "save")
	public String save(DyPlatformAccount dyPlatformAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyPlatformAccount)){
			return form(dyPlatformAccount, model);
		}
		dyPlatformAccountService.save(dyPlatformAccount);
		addMessage(redirectAttributes, "保存平台总账户管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyPlatformAccount/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyPlatformAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(DyPlatformAccount dyPlatformAccount, RedirectAttributes redirectAttributes) {
		dyPlatformAccountService.delete(dyPlatformAccount);
		addMessage(redirectAttributes, "删除平台总账户管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyPlatformAccount/?repage";
	}

}