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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldCommentsReply;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserSoldCommentsReplyService;

/**
 * 卖标信息评论回复管理Controller
 * @author snnu
 * @version 2018-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjUserSoldCommentsReply")
public class GbjUserSoldCommentsReplyController extends BaseController {

	@Autowired
	private GbjUserSoldCommentsReplyService gbjUserSoldCommentsReplyService;
	
	@ModelAttribute
	public GbjUserSoldCommentsReply get(@RequestParam(required=false) String id) {
		GbjUserSoldCommentsReply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjUserSoldCommentsReplyService.get(id);
		}
		if (entity == null){
			entity = new GbjUserSoldCommentsReply();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjUserSoldCommentsReply:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjUserSoldCommentsReply gbjUserSoldCommentsReply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjUserSoldCommentsReply> page = gbjUserSoldCommentsReplyService.findPage(new Page<GbjUserSoldCommentsReply>(request, response), gbjUserSoldCommentsReply); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjUserSoldCommentsReplyList";
	}

	@RequiresPermissions("sys:gbj:gbjUserSoldCommentsReply:view")
	@RequestMapping(value = "form")
	public String form(GbjUserSoldCommentsReply gbjUserSoldCommentsReply, Model model) {
		model.addAttribute("gbjUserSoldCommentsReply", gbjUserSoldCommentsReply);
		return "modules/sys/gbj/gbjUserSoldCommentsReplyForm";
	}

	@RequiresPermissions("sys:gbj:gbjUserSoldCommentsReply:edit")
	@RequestMapping(value = "save")
	public String save(GbjUserSoldCommentsReply gbjUserSoldCommentsReply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjUserSoldCommentsReply)){
			return form(gbjUserSoldCommentsReply, model);
		}
		gbjUserSoldCommentsReplyService.save(gbjUserSoldCommentsReply);
		addMessage(redirectAttributes, "保存卖标信息评论回复管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserSoldCommentsReply/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjUserSoldCommentsReply:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjUserSoldCommentsReply gbjUserSoldCommentsReply, RedirectAttributes redirectAttributes) {
		gbjUserSoldCommentsReplyService.delete(gbjUserSoldCommentsReply);
		addMessage(redirectAttributes, "删除卖标信息评论回复管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserSoldCommentsReply/?repage";
	}

}