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
import com.thinkgem.jeesite.modules.sys.entity.gb.GbSold;
import com.thinkgem.jeesite.modules.sys.service.gb.GbSoldService;

/**
 * 卖标信息管理Controller
 * @author 管理员
 * @version 2017-12-17
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gb/gbSold")
public class GbSoldController extends BaseController {

	@Autowired
	private GbSoldService gbSoldService;
	
	@ModelAttribute
	public GbSold get(@RequestParam(required=false) String id) {
		GbSold entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbSoldService.get(id);
		}
		if (entity == null){
			entity = new GbSold();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gb:gbSold:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbSold gbSold, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbSold> page = gbSoldService.findPage(new Page<GbSold>(request, response), gbSold); 
		model.addAttribute("page", page);
		return "modules/sys/gb/gbSoldList";
	}

	@RequiresPermissions("sys:gb:gbSold:view")
	@RequestMapping(value = "form")
	public String form(GbSold gbSold, Model model) {
		model.addAttribute("gbSold", gbSold);
		return "modules/sys/gb/gbSoldForm";
	}

	@RequiresPermissions("sys:gb:gbSold:edit")
	@RequestMapping(value = "save")
	public String save(GbSold gbSold, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbSold)){
			return form(gbSold, model);
		}
		gbSoldService.save(gbSold);
		addMessage(redirectAttributes, "保存卖标信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbSold/?repage";
	}
	
	@RequiresPermissions("sys:gb:gbSold:edit")
	@RequestMapping(value = "delete")
	public String delete(GbSold gbSold, RedirectAttributes redirectAttributes) {
		gbSoldService.delete(gbSold);
		addMessage(redirectAttributes, "删除卖标信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbSold/?repage";
	}

}