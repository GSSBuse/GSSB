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
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessage;
import com.thinkgem.jeesite.modules.sys.service.dy.DyMessageService;

/**
 * 推送消息管理Controller
 * @author quanyf.fnst
 * @version 2015-10-20
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyMessage")
public class DyMessageController extends BaseController {

	@Autowired
	private DyMessageService dyMessageService;
	
	@ModelAttribute
	public DyMessage get(@RequestParam(required=false) String id) {
		DyMessage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyMessageService.get(id);
		}
		if (entity == null){
			entity = new DyMessage();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyMessage:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyMessage dyMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyMessage> page = dyMessageService.findPage(new Page<DyMessage>(request, response), dyMessage); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyMessageList";
	}

	@RequiresPermissions("sys:dy:dyMessage:view")
	@RequestMapping(value = "form")
	public String form(DyMessage dyMessage, Model model) {
		model.addAttribute("dyMessage", dyMessage);
		return "modules/sys/dy/dyMessageForm";
	}

	@RequiresPermissions("sys:dy:dyMessage:edit")
	@RequestMapping(value = "save")
	public String save(DyMessage dyMessage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyMessage)){
			return form(dyMessage, model);
		}
		try{
			dyMessageService.save(dyMessage);
			addMessage(redirectAttributes, "保存推送消息成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "保存推送消息失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyMessage/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(DyMessage dyMessage, RedirectAttributes redirectAttributes) {
		dyMessageService.delete(dyMessage);
		addMessage(redirectAttributes, "删除推送消息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyMessage/?repage";
	}

}