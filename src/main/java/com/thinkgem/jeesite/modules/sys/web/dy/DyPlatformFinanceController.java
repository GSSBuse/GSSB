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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformFinance;
import com.thinkgem.jeesite.modules.sys.service.dy.DyPlatformFinanceService;

/**
 * 平台总账户管理Controller
 * @author quanyf.fnst
 * @version 2015-12-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyPlatformFinance")
public class DyPlatformFinanceController extends BaseController {

	@Autowired
	private DyPlatformFinanceService dyPlatformFinanceService;
	
	@ModelAttribute
	public DyPlatformFinance get(@RequestParam(required=false) String id) {
		DyPlatformFinance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyPlatformFinanceService.get(id);
		}
		if (entity == null){
			entity = new DyPlatformFinance();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyPlatformFinance:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyPlatformFinance dyPlatformFinance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyPlatformFinance> page = dyPlatformFinanceService.findPage(new Page<DyPlatformFinance>(request, response), dyPlatformFinance); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyPlatformFinanceList";
	}

	@RequiresPermissions("sys:dy:dyPlatformFinance:view")
	@RequestMapping(value = "form")
	public String form(DyPlatformFinance dyPlatformFinance, Model model) {
		model.addAttribute("dyPlatformFinance", dyPlatformFinance);
		return "modules/sys/dy/dyPlatformFinanceForm";
	}

	@RequiresPermissions("sys:dy:dyPlatformFinance:edit")
	@RequestMapping(value = "save")
	public String save(DyPlatformFinance dyPlatformFinance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyPlatformFinance)){
			return form(dyPlatformFinance, model);
		}
		try{
			dyPlatformFinanceService.save(dyPlatformFinance);
			addMessage(redirectAttributes, "保存平台总账户管理成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "保存平台总账户管理失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyPlatformFinance/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyPlatformFinance:edit")
	@RequestMapping(value = "delete")
	public String delete(DyPlatformFinance dyPlatformFinance, RedirectAttributes redirectAttributes) {
		dyPlatformFinanceService.delete(dyPlatformFinance);
		addMessage(redirectAttributes, "删除平台总账户管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyPlatformFinance/?repage";
	}

}