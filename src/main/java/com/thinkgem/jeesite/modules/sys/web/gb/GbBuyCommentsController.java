/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.gb;

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
import com.thinkgem.jeesite.modules.sys.entity.gb.GbBuyComments;
import com.thinkgem.jeesite.modules.sys.service.gb.GbBuyCommentsService;

/**
 * 买标评论Controller
 * @author 管理员
 * @version 2017-11-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gb/gbBuyComments")
public class GbBuyCommentsController extends BaseController {

	@Autowired
	private GbBuyCommentsService gbBuyCommentsService;
	
	@ModelAttribute
	public GbBuyComments get(@RequestParam(required=false) String id) {
		GbBuyComments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbBuyCommentsService.get(id);
		}
		if (entity == null){
			entity = new GbBuyComments();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gb:gbBuyComments:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbBuyComments gbBuyComments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbBuyComments> page = gbBuyCommentsService.findPage(new Page<GbBuyComments>(request, response), gbBuyComments); 
		model.addAttribute("page", page);
		return "modules/sys/gb/gbBuyCommentsList";
	}

	@RequiresPermissions("sys:gb:gbBuyComments:view")
	@RequestMapping(value = "form")
	public String form(GbBuyComments gbBuyComments, Model model) {
		model.addAttribute("gbBuyComments", gbBuyComments);
		return "modules/sys/gb/gbBuyCommentsForm";
	}

	@RequiresPermissions("sys:gb:gbBuyComments:edit")
	@RequestMapping(value = "save")
	public String save(GbBuyComments gbBuyComments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbBuyComments)){
			return form(gbBuyComments, model);
		}
		gbBuyCommentsService.save(gbBuyComments);
		addMessage(redirectAttributes, "保存管理买标评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbBuyComments/?repage";
	}
	
	@RequiresPermissions("sys:gb:gbBuyComments:edit")
	@RequestMapping(value = "delete")
	public String delete(GbBuyComments gbBuyComments, RedirectAttributes redirectAttributes) {
		gbBuyCommentsService.delete(gbBuyComments);
		addMessage(redirectAttributes, "删除管理买标评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbBuyComments/?repage";
	}

}