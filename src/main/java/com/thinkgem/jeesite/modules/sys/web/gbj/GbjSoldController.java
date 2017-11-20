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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjSold;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjSoldService;

/**
 * 卖标信息管理Controller
 * @author snnu
 * @version 2017-11-19
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjSold")
public class GbjSoldController extends BaseController {

	@Autowired
	private GbjSoldService gbjSoldService;
	
	@ModelAttribute
	public GbjSold get(@RequestParam(required=false) String id) {
		GbjSold entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjSoldService.get(id);
		}
		if (entity == null){
			entity = new GbjSold();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjSold:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjSold gbjSold, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjSold> page = gbjSoldService.findPage(new Page<GbjSold>(request, response), gbjSold); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjSoldList";
	}

	@RequiresPermissions("sys:gbj:gbjSold:view")
	@RequestMapping(value = "form")
	public String form(GbjSold gbjSold, Model model) {
		model.addAttribute("gbjSold", gbjSold);
		return "modules/sys/gbj/gbjSoldForm";
	}

	@RequiresPermissions("sys:gbj:gbjSold:edit")
	@RequestMapping(value = "save")
	public String save(GbjSold gbjSold, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjSold)){
			return form(gbjSold, model);
		}
		gbjSoldService.save(gbjSold);
		addMessage(redirectAttributes, "保存卖标信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjSold/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjSold:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjSold gbjSold, RedirectAttributes redirectAttributes) {
		gbjSoldService.delete(gbjSold);
		addMessage(redirectAttributes, "删除卖标信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjSold/?repage";
	}

}