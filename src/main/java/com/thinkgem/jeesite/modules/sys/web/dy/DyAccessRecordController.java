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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAccessRecord;
import com.thinkgem.jeesite.modules.sys.service.dy.DyAccessRecordService;

/**
 * 访问记录管理Controller
 * @author quanyf.fnst
 * @version 2015-11-04
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyAccessRecord")
public class DyAccessRecordController extends BaseController {

	@Autowired
	private DyAccessRecordService dyAccessRecordService;
	
	@ModelAttribute
	public DyAccessRecord get(@RequestParam(required=false) String id) {
		DyAccessRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyAccessRecordService.get(id);
		}
		if (entity == null){
			entity = new DyAccessRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyAccessRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyAccessRecord dyAccessRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyAccessRecord> page = dyAccessRecordService.findPage(new Page<DyAccessRecord>(request, response), dyAccessRecord); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyAccessRecordList";
	}

	@RequiresPermissions("sys:dy:dyAccessRecord:view")
	@RequestMapping(value = "form")
	public String form(DyAccessRecord dyAccessRecord, Model model) {
		model.addAttribute("dyAccessRecord", dyAccessRecord);
		return "modules/sys/dy/dyAccessRecordForm";
	}

	@RequiresPermissions("sys:dy:dyAccessRecord:edit")
	@RequestMapping(value = "save")
	public String save(DyAccessRecord dyAccessRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyAccessRecord)){
			return form(dyAccessRecord, model);
		}
		dyAccessRecordService.save(dyAccessRecord);
		addMessage(redirectAttributes, "保存访问记录管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyAccessRecord/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyAccessRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(DyAccessRecord dyAccessRecord, RedirectAttributes redirectAttributes) {
		dyAccessRecordService.delete(dyAccessRecord);
		addMessage(redirectAttributes, "删除访问记录管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyAccessRecord/?repage";
	}

}