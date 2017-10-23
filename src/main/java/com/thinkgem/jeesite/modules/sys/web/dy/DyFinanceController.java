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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;

/**
 * 会员财务信息管理Controller
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyFinance")
public class DyFinanceController extends BaseController {

	@Autowired
	private DyFinanceService dyFinanceService;
	
	@ModelAttribute
	public DyFinance get(@RequestParam(required=false) String id) {
		DyFinance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyFinanceService.get(id);
		}
		if (entity == null){
			entity = new DyFinance();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyFinance:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyFinance dyFinance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyFinance> page = dyFinanceService.findPage(new Page<DyFinance>(request, response), dyFinance); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyFinanceList";
	}

	@RequiresPermissions("sys:dy:dyFinance:view")
	@RequestMapping(value = "form")
	public String form(DyFinance dyFinance, Model model) {
		model.addAttribute("dyFinance", dyFinance);
		return "modules/sys/dy/dyFinanceForm";
	}

	@RequiresPermissions("sys:dy:dyFinance:edit")
	@RequestMapping(value = "save")
	public String save(DyFinance dyFinance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyFinance)){
			return form(dyFinance, model);
		}
		try{
			dyFinanceService.save(dyFinance);
			addMessage(redirectAttributes, "保存会员财务信息成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "保存会员财务信息失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyFinance/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyFinance:edit")
	@RequestMapping(value = "delete")
	public String delete(DyFinance dyFinance, RedirectAttributes redirectAttributes) {
		dyFinanceService.delete(dyFinance);
		addMessage(redirectAttributes, "删除会员财务信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyFinance/?repage";
	}

}