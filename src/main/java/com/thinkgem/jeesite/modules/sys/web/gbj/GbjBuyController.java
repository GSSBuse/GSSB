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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjReward;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjBuyService;

/**
 * 买标信息Controller
 * @author snnu
 * @version 2017-12-17
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjBuy")
public class GbjBuyController extends BaseController {

	@Autowired
	private GbjBuyService gbjBuyService;
	
	@ModelAttribute
	public GbjBuy get(@RequestParam(required=false) String id) {
		GbjBuy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjBuyService.get(id);
		}
		if (entity == null){
			entity = new GbjBuy();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjBuy:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjBuy gbjBuy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjBuy> page = gbjBuyService.findPage(new Page<GbjBuy>(request, response), gbjBuy); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjBuyList";
	}

	@RequiresPermissions("sys:gbj:gbjBuy:view")
	@RequestMapping(value = "form")
	public String form(GbjBuy gbjBuy, Model model) {
		model.addAttribute("gbjBuy", gbjBuy);
		return "modules/sys/gbj/gbjBuyForm";
	}
	
	
	@RequiresPermissions("sys:gbj:gbjBuy:edit")
	@RequestMapping(value = "save")
	public String save(GbjBuy gbjBuy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjBuy)){
			return form(gbjBuy, model);
		}
		gbjBuyService.save(gbjBuy);
		addMessage(redirectAttributes, "保存买标信息列表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjBuy/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjBuy:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjBuy gbjBuy, RedirectAttributes redirectAttributes) {
		gbjBuyService.delete(gbjBuy);
		addMessage(redirectAttributes, "删除买标信息列表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjBuy/?repage";
	}
	
	
	//发布买标信息
		@RequiresPermissions("sys:gbj:gbjBuy:edit")
		@RequestMapping(value = "release")
		public String release(GbjBuy gbjBuy, RedirectAttributes redirectAttributes) {
			//System.out.print("controller");
			gbjBuyService.release(gbjBuy);
			addMessage(redirectAttributes, "发布买标信息成功");
			return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjBuy/?repage";
		}
		
		
		//撤回买标信息
			@RequiresPermissions("sys:gbj:gbjBuy:edit")
			@RequestMapping(value = "withdraw")
			public String withdraw(GbjBuy gbjBuy, RedirectAttributes redirectAttributes) {
				//System.out.print("controller");
				gbjBuyService.withdraw(gbjBuy);
				addMessage(redirectAttributes, "撤回买标信息成功");
				return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjBuy/?repage";
			}
	

}