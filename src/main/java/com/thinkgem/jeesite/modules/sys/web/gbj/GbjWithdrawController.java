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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjWithdraw;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjWithdrawService;

/**
 * 提现记录表Controller
 * @author snnu
 * @version 2018-02-03
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjWithdraw")
public class GbjWithdrawController extends BaseController {

	@Autowired
	private GbjWithdrawService gbjWithdrawService;
	
	@ModelAttribute
	public GbjWithdraw get(@RequestParam(required=false) String id) {
		GbjWithdraw entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjWithdrawService.get(id);
		}
		if (entity == null){
			entity = new GbjWithdraw();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjWithdraw:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjWithdraw gbjWithdraw, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjWithdraw> page = gbjWithdrawService.findPage(new Page<GbjWithdraw>(request, response), gbjWithdraw); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjWithdrawList";
	}

	@RequiresPermissions("sys:gbj:gbjWithdraw:view")
	@RequestMapping(value = "form")
	public String form(GbjWithdraw gbjWithdraw, Model model) {
		model.addAttribute("gbjWithdraw", gbjWithdraw);
		return "modules/sys/gbj/gbjWithdrawForm";
	}

	@RequiresPermissions("sys:gbj:gbjWithdraw:edit")
	@RequestMapping(value = "save")
	public String save(GbjWithdraw gbjWithdraw, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjWithdraw)){
			return form(gbjWithdraw, model);
		}
		gbjWithdrawService.save(gbjWithdraw);
		addMessage(redirectAttributes, "保存提现记录表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjWithdraw/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjWithdraw:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjWithdraw gbjWithdraw, RedirectAttributes redirectAttributes) {
		gbjWithdrawService.delete(gbjWithdraw);
		addMessage(redirectAttributes, "删除提现记录表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjWithdraw/?repage";
	}

}