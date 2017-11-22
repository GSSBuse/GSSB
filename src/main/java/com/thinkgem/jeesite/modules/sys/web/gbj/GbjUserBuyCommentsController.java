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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyComments;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserBuyCommentsService;

/**
 * 买标信息评论Controller
 * @author snnu
 * @version 2017-11-20
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjUserBuyComments")
public class GbjUserBuyCommentsController extends BaseController {

	@Autowired
	private GbjUserBuyCommentsService gbjUserBuyCommentsService;
	
	@ModelAttribute
	public GbjUserBuyComments get(@RequestParam(required=false) String id) {
		GbjUserBuyComments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjUserBuyCommentsService.get(id);
		}
		if (entity == null){
			entity = new GbjUserBuyComments();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjUserBuyComments:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjUserBuyComments gbjUserBuyComments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjUserBuyComments> page = gbjUserBuyCommentsService.findPage(new Page<GbjUserBuyComments>(request, response), gbjUserBuyComments); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjUserBuyCommentsList";
	}

	@RequiresPermissions("sys:gbj:gbjUserBuyComments:view")
	@RequestMapping(value = "form")
	public String form(GbjUserBuyComments gbjUserBuyComments, Model model) {
		model.addAttribute("gbjUserBuyComments", gbjUserBuyComments);
		return "modules/sys/gbj/gbjUserBuyCommentsForm";
	}

	@RequiresPermissions("sys:gbj:gbjUserBuyComments:edit")
	@RequestMapping(value = "save")
	public String save(GbjUserBuyComments gbjUserBuyComments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjUserBuyComments)){
			return form(gbjUserBuyComments, model);
		}
		gbjUserBuyCommentsService.save(gbjUserBuyComments);
		addMessage(redirectAttributes, "保存买标信息评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserBuyComments/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjUserBuyComments:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjUserBuyComments gbjUserBuyComments, RedirectAttributes redirectAttributes) {
		gbjUserBuyCommentsService.delete(gbjUserBuyComments);
		addMessage(redirectAttributes, "删除买标信息评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserBuyComments/?repage";
	}

}