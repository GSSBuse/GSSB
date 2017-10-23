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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBonus;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBonusService;

/**
 * 红包佣金信息Controller
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyBonus")
public class DyBonusController extends BaseController {

	@Autowired
	private DyBonusService dyBonusService;
	
	@ModelAttribute
	public DyBonus get(@RequestParam(required=false) String id) {
		DyBonus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyBonusService.get(id);
		}
		if (entity == null){
			entity = new DyBonus();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyBonus:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyBonus dyBonus, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyBonus> page = dyBonusService.findPage(new Page<DyBonus>(request, response), dyBonus); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyBonusList";
	}

	@RequiresPermissions("sys:dy:dyBonus:view")
	@RequestMapping(value = "form")
	public String form(DyBonus dyBonus, Model model) {
		model.addAttribute("dyBonus", dyBonus);
		return "modules/sys/dy/dyBonusForm";
	}

	@RequiresPermissions("sys:dy:dyBonus:edit")
	@RequestMapping(value = "save")
	public String save(DyBonus dyBonus, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyBonus)){
			return form(dyBonus, model);
		}
		dyBonusService.save(dyBonus);
		addMessage(redirectAttributes, "保存红包佣金成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyBonus/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyBonus:edit")
	@RequestMapping(value = "delete")
	public String delete(DyBonus dyBonus, RedirectAttributes redirectAttributes) {
		dyBonusService.delete(dyBonus);
		addMessage(redirectAttributes, "删除红包佣金成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyBonus/?repage";
	}

}