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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyShareRecord;
import com.thinkgem.jeesite.modules.sys.service.dy.DyShareRecordService;

/**
 * 分享记录管理Controller
 * @author quanyf.fnst
 * @version 2015-11-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyShareRecord")
public class DyShareRecordController extends BaseController {

	@Autowired
	private DyShareRecordService dyShareRecordService;
	
	@ModelAttribute
	public DyShareRecord get(@RequestParam(required=false) String id) {
		DyShareRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyShareRecordService.get(id);
		}
		if (entity == null){
			entity = new DyShareRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyShareRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyShareRecord dyShareRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyShareRecord> page = dyShareRecordService.findPage(new Page<DyShareRecord>(request, response), dyShareRecord); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyShareRecordList";
	}

	@RequiresPermissions("sys:dy:dyShareRecord:view")
	@RequestMapping(value = "form")
	public String form(DyShareRecord dyShareRecord, Model model) {
		model.addAttribute("dyShareRecord", dyShareRecord);
		return "modules/sys/dy/dyShareRecordForm";
	}

	@RequiresPermissions("sys:dy:dyShareRecord:edit")
	@RequestMapping(value = "save")
	public String save(DyShareRecord dyShareRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyShareRecord)){
			return form(dyShareRecord, model);
		}
		dyShareRecordService.save(dyShareRecord);
		addMessage(redirectAttributes, "保存分享记录管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyShareRecord/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyShareRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(DyShareRecord dyShareRecord, RedirectAttributes redirectAttributes) {
		dyShareRecordService.delete(dyShareRecord);
		addMessage(redirectAttributes, "删除分享记录管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyShareRecord/?repage";
	}

}