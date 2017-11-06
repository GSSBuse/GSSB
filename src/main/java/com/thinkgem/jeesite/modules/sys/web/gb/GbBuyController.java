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
import com.thinkgem.jeesite.modules.sys.entity.gb.GbBuy;
import com.thinkgem.jeesite.modules.sys.service.gb.GbBuyService;

/**
 * 买标信息管理Controller
 * @author 管理员
 * @version 2017-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gb/gbBuy")
public class GbBuyController extends BaseController {

	@Autowired
	private GbBuyService gbBuyService;
	
	@ModelAttribute
	public GbBuy get(@RequestParam(required=false) String id) {
		GbBuy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbBuyService.get(id);
		}
		if (entity == null){
			entity = new GbBuy();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gb:gbBuy:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbBuy gbBuy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbBuy> page = gbBuyService.findPage(new Page<GbBuy>(request, response), gbBuy); 
		model.addAttribute("page", page);
		return "modules/sys/gb/gbBuyList";
	}

	@RequiresPermissions("sys:gb:gbBuy:view")
	@RequestMapping(value = "form")
	public String form(GbBuy gbBuy, Model model) {
		model.addAttribute("gbBuy", gbBuy);
		return "modules/sys/gb/gbBuyForm";
	}

	@RequiresPermissions("sys:gb:gbBuy:edit")
	@RequestMapping(value = "save")
	public String save(GbBuy gbBuy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbBuy)){
			return form(gbBuy, model);
		}
		gbBuyService.save(gbBuy);
		addMessage(redirectAttributes, "保存买标信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbBuy/?repage";
	}
	
	@RequiresPermissions("sys:gb:gbBuy:edit")
	@RequestMapping(value = "delete")
	public String delete(GbBuy gbBuy, RedirectAttributes redirectAttributes) {
		gbBuyService.delete(gbBuy);
		addMessage(redirectAttributes, "删除买标信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbBuy/?repage";
	}

}