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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBusinessNumber;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjBusinessNumberService;

/**
 * 历史交易量Controller
 * @author snnu
 * @version 2018-01-11
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjBusinessNumber")
public class GbjBusinessNumberController extends BaseController {

	@Autowired
	private GbjBusinessNumberService gbjBusinessNumberService;
	
	@ModelAttribute
	public GbjBusinessNumber get(@RequestParam(required=false) String id) {
		GbjBusinessNumber entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjBusinessNumberService.get(id);
		}
		if (entity == null){
			entity = new GbjBusinessNumber();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjBusinessNumber:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjBusinessNumber gbjBusinessNumber, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjBusinessNumber> page = gbjBusinessNumberService.findPage(new Page<GbjBusinessNumber>(request, response), gbjBusinessNumber); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjBusinessNumberList";
	}

	@RequiresPermissions("sys:gbj:gbjBusinessNumber:view")
	@RequestMapping(value = "form")
	public String form(GbjBusinessNumber gbjBusinessNumber, Model model) {
		model.addAttribute("gbjBusinessNumber", gbjBusinessNumber);
		return "modules/sys/gbj/gbjBusinessNumberForm";
	}

	@RequiresPermissions("sys:gbj:gbjBusinessNumber:edit")
	@RequestMapping(value = "save")
	public String save(GbjBusinessNumber gbjBusinessNumber, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjBusinessNumber)){
			return form(gbjBusinessNumber, model);
		}
		gbjBusinessNumberService.save(gbjBusinessNumber);
		addMessage(redirectAttributes, "保存历史交易量成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjBusinessNumber/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjBusinessNumber:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjBusinessNumber gbjBusinessNumber, RedirectAttributes redirectAttributes) {
		gbjBusinessNumberService.delete(gbjBusinessNumber);
		addMessage(redirectAttributes, "删除历史交易量成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjBusinessNumber/?repage";
	}

}