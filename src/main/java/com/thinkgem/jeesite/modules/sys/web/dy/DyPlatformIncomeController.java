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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformIncome;
import com.thinkgem.jeesite.modules.sys.service.dy.DyPlatformIncomeService;

/**
 * 平台收入管理Controller
 * @author quanyf.fnst
 * @version 2015-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyPlatformIncome")
public class DyPlatformIncomeController extends BaseController {

	@Autowired
	private DyPlatformIncomeService dyPlatformIncomeService;
	
	@ModelAttribute
	public DyPlatformIncome get(@RequestParam(required=false) String id) {
		DyPlatformIncome entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyPlatformIncomeService.get(id);
		}
		if (entity == null){
			entity = new DyPlatformIncome();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyPlatformIncome:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyPlatformIncome dyPlatformIncome, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyPlatformIncome> page = dyPlatformIncomeService.findPage(new Page<DyPlatformIncome>(request, response), dyPlatformIncome); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyPlatformIncomeList";
	}

	@RequiresPermissions("sys:dy:dyPlatformIncome:view")
	@RequestMapping(value = "form")
	public String form(DyPlatformIncome dyPlatformIncome, Model model) {
		model.addAttribute("dyPlatformIncome", dyPlatformIncome);
		return "modules/sys/dy/dyPlatformIncomeForm";
	}

	@RequiresPermissions("sys:dy:dyPlatformIncome:edit")
	@RequestMapping(value = "save")
	public String save(DyPlatformIncome dyPlatformIncome, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyPlatformIncome)){
			return form(dyPlatformIncome, model);
		}
		try{
			dyPlatformIncomeService.save(dyPlatformIncome);
			addMessage(redirectAttributes, "保存平台收入管理成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "保存平台收入管理失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyPlatformIncome/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyPlatformIncome:edit")
	@RequestMapping(value = "delete")
	public String delete(DyPlatformIncome dyPlatformIncome, RedirectAttributes redirectAttributes) {
		dyPlatformIncomeService.delete(dyPlatformIncome);
		addMessage(redirectAttributes, "删除平台收入管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyPlatformIncome/?repage";
	}

}