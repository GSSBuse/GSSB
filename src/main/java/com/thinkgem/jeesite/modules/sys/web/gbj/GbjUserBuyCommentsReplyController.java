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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyCommentsReply;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserBuyCommentsReplyService;

/**
 * 买标信息评论回复管理Controller
 * @author snnu
 * @version 2018-01-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjUserBuyCommentsReply")
public class GbjUserBuyCommentsReplyController extends BaseController {

	@Autowired
	private GbjUserBuyCommentsReplyService gbjUserBuyCommentsReplyService;
	
	@ModelAttribute
	public GbjUserBuyCommentsReply get(@RequestParam(required=false) String id) {
		GbjUserBuyCommentsReply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjUserBuyCommentsReplyService.get(id);
		}
		if (entity == null){
			entity = new GbjUserBuyCommentsReply();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjUserBuyCommentsReply:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjUserBuyCommentsReply gbjUserBuyCommentsReply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjUserBuyCommentsReply> page = gbjUserBuyCommentsReplyService.findPage(new Page<GbjUserBuyCommentsReply>(request, response), gbjUserBuyCommentsReply); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjUserBuyCommentsReplyList";
	}

	@RequiresPermissions("sys:gbj:gbjUserBuyCommentsReply:view")
	@RequestMapping(value = "form")
	public String form(GbjUserBuyCommentsReply gbjUserBuyCommentsReply, Model model) {
		model.addAttribute("gbjUserBuyCommentsReply", gbjUserBuyCommentsReply);
		return "modules/sys/gbj/gbjUserBuyCommentsReplyForm";
	}

	@RequiresPermissions("sys:gbj:gbjUserBuyCommentsReply:edit")
	@RequestMapping(value = "save")
	public String save(GbjUserBuyCommentsReply gbjUserBuyCommentsReply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjUserBuyCommentsReply)){
			return form(gbjUserBuyCommentsReply, model);
		}
		gbjUserBuyCommentsReplyService.save(gbjUserBuyCommentsReply);
		addMessage(redirectAttributes, "保存买标信息评论回复管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserBuyCommentsReply/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjUserBuyCommentsReply:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjUserBuyCommentsReply gbjUserBuyCommentsReply, RedirectAttributes redirectAttributes) {
		gbjUserBuyCommentsReplyService.delete(gbjUserBuyCommentsReply);
		addMessage(redirectAttributes, "删除买标信息评论回复管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserBuyCommentsReply/?repage";
	}

}