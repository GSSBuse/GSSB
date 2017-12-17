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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTouristRequire;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjTouristRequireService;

/**
 * 游客查询管理Controller
 * @author snnu
 * @version 2017-12-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjTouristRequire")
public class GbjTouristRequireController extends BaseController {

	@Autowired
	private GbjTouristRequireService gbjTouristRequireService;
	
	@ModelAttribute
	public GbjTouristRequire get(@RequestParam(required=false) String id) {
		GbjTouristRequire entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjTouristRequireService.get(id);
		}
		if (entity == null){
			entity = new GbjTouristRequire();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjTouristRequire:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjTouristRequire gbjTouristRequire, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjTouristRequire> page = gbjTouristRequireService.findPage(new Page<GbjTouristRequire>(request, response), gbjTouristRequire); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjTouristRequireList";
	}

	@RequiresPermissions("sys:gbj:gbjTouristRequire:view")
	@RequestMapping(value = "form")
	public String form(GbjTouristRequire gbjTouristRequire, Model model) {
		model.addAttribute("gbjTouristRequire", gbjTouristRequire);
		return "modules/sys/gbj/gbjTouristRequireForm";
	}

	@RequiresPermissions("sys:gbj:gbjTouristRequire:edit")
	@RequestMapping(value = "save")
	public String save(GbjTouristRequire gbjTouristRequire, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjTouristRequire)){
			return form(gbjTouristRequire, model);
		}
		gbjTouristRequireService.save(gbjTouristRequire);
		addMessage(redirectAttributes, "保存游客查询管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjTouristRequire/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjTouristRequire:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjTouristRequire gbjTouristRequire, RedirectAttributes redirectAttributes) {
		gbjTouristRequireService.delete(gbjTouristRequire);
		addMessage(redirectAttributes, "删除游客查询管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjTouristRequire/?repage";
	}

}