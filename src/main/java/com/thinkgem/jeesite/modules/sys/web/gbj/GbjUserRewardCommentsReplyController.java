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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserRewardCommentsReply;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserRewardCommentsReplyService;

/**
 * 悬赏信息评论回复表Controller
 * @author snnu
 * @version 2018-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjUserRewardCommentsReply")
public class GbjUserRewardCommentsReplyController extends BaseController {

	@Autowired
	private GbjUserRewardCommentsReplyService gbjUserRewardCommentsReplyService;
	
	@ModelAttribute
	public GbjUserRewardCommentsReply get(@RequestParam(required=false) String id) {
		GbjUserRewardCommentsReply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjUserRewardCommentsReplyService.get(id);
		}
		if (entity == null){
			entity = new GbjUserRewardCommentsReply();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjUserRewardCommentsReply:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjUserRewardCommentsReply gbjUserRewardCommentsReply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjUserRewardCommentsReply> page = gbjUserRewardCommentsReplyService.findPage(new Page<GbjUserRewardCommentsReply>(request, response), gbjUserRewardCommentsReply); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjUserRewardCommentsReplyList";
	}

	@RequiresPermissions("sys:gbj:gbjUserRewardCommentsReply:view")
	@RequestMapping(value = "form")
	public String form(GbjUserRewardCommentsReply gbjUserRewardCommentsReply, Model model) {
		model.addAttribute("gbjUserRewardCommentsReply", gbjUserRewardCommentsReply);
		return "modules/sys/gbj/gbjUserRewardCommentsReplyForm";
	}

	@RequiresPermissions("sys:gbj:gbjUserRewardCommentsReply:edit")
	@RequestMapping(value = "save")
	public String save(GbjUserRewardCommentsReply gbjUserRewardCommentsReply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjUserRewardCommentsReply)){
			return form(gbjUserRewardCommentsReply, model);
		}
		gbjUserRewardCommentsReplyService.save(gbjUserRewardCommentsReply);
		addMessage(redirectAttributes, "保存悬赏信息评论回复表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserRewardCommentsReply/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjUserRewardCommentsReply:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjUserRewardCommentsReply gbjUserRewardCommentsReply, RedirectAttributes redirectAttributes) {
		gbjUserRewardCommentsReplyService.delete(gbjUserRewardCommentsReply);
		addMessage(redirectAttributes, "删除悬赏信息评论回复表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserRewardCommentsReply/?repage";
	}

}