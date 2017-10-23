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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyTemplateMessage;
import com.thinkgem.jeesite.modules.sys.service.dy.DyTemplateMessageService;

/**
 * 通知消息设置Controller
 * @author songshuqing
 * @version 2015-11-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyTemplateMessage")
public class DyTemplateMessageController extends BaseController {

	@Autowired
	private DyTemplateMessageService dyTemplateMessageService;
	
	@ModelAttribute
	public DyTemplateMessage get(@RequestParam(required=false) String id) {
		DyTemplateMessage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyTemplateMessageService.get(id);
		}
		if (entity == null){
			entity = new DyTemplateMessage();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyTemplateMessage:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyTemplateMessage dyTemplateMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyTemplateMessage> page = dyTemplateMessageService.findPage(new Page<DyTemplateMessage>(request, response), dyTemplateMessage); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyTemplateMessageList";
	}

	@RequiresPermissions("sys:dy:dyTemplateMessage:view")
	@RequestMapping(value = "form")
	public String form(DyTemplateMessage dyTemplateMessage, Model model) {
		model.addAttribute("dyTemplateMessage", dyTemplateMessage);
		return "modules/sys/dy/dyTemplateMessageForm";
	}

	@RequiresPermissions("sys:dy:dyTemplateMessage:edit")
	@RequestMapping(value = "save")
	public String save(DyTemplateMessage dyTemplateMessage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyTemplateMessage)){
			return form(dyTemplateMessage, model);
		}
		dyTemplateMessageService.save(dyTemplateMessage);
		addMessage(redirectAttributes, "保存通知消息设置成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyTemplateMessage/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyTemplateMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(DyTemplateMessage dyTemplateMessage, RedirectAttributes redirectAttributes) {
		dyTemplateMessageService.delete(dyTemplateMessage);
		addMessage(redirectAttributes, "删除通知消息设置成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyTemplateMessage/?repage";
	}

}