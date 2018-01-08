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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTag;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjTagService;

/**
 * 标签类型Controller
 * @author snnu
 * @version 2018-01-08
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjTag")
public class GbjTagController extends BaseController {

	@Autowired
	private GbjTagService gbjTagService;
	
	@ModelAttribute
	public GbjTag get(@RequestParam(required=false) String id) {
		GbjTag entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjTagService.get(id);
		}
		if (entity == null){
			entity = new GbjTag();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjTag:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjTag gbjTag, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjTag> page = gbjTagService.findPage(new Page<GbjTag>(request, response), gbjTag); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjTagList";
	}

	@RequiresPermissions("sys:gbj:gbjTag:view")
	@RequestMapping(value = "form")
	public String form(GbjTag gbjTag, Model model) {
		model.addAttribute("gbjTag", gbjTag);
		return "modules/sys/gbj/gbjTagForm";
	}

	@RequiresPermissions("sys:gbj:gbjTag:edit")
	@RequestMapping(value = "save")
	public String save(GbjTag gbjTag, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjTag)){
			return form(gbjTag, model);
		}
		gbjTagService.save(gbjTag);
		addMessage(redirectAttributes, "保存标签类型列表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjTag/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjTag:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjTag gbjTag, RedirectAttributes redirectAttributes) {
		gbjTagService.delete(gbjTag);
		addMessage(redirectAttributes, "删除标签类型列表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjTag/?repage";
	}

}