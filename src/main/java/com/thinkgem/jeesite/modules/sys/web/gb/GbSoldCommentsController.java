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
import com.thinkgem.jeesite.modules.sys.entity.gb.GbSoldComments;
import com.thinkgem.jeesite.modules.sys.service.gb.GbSoldCommentsService;

/**
 * 卖标评论管理Controller
 * @author 管理员
 * @version 2017-11-05
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gb/gbSoldComments")
public class GbSoldCommentsController extends BaseController {

	@Autowired
	private GbSoldCommentsService gbSoldCommentsService;
	
	@ModelAttribute
	public GbSoldComments get(@RequestParam(required=false) String id) {
		GbSoldComments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbSoldCommentsService.get(id);
		}
		if (entity == null){
			entity = new GbSoldComments();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gb:gbSoldComments:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbSoldComments gbSoldComments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbSoldComments> page = gbSoldCommentsService.findPage(new Page<GbSoldComments>(request, response), gbSoldComments); 
		model.addAttribute("page", page);
		return "modules/sys/gb/gbSoldCommentsList";
	}

	@RequiresPermissions("sys:gb:gbSoldComments:view")
	@RequestMapping(value = "form")
	public String form(GbSoldComments gbSoldComments, Model model) {
		model.addAttribute("gbSoldComments", gbSoldComments);
		return "modules/sys/gb/gbSoldCommentsForm";
	}

	@RequiresPermissions("sys:gb:gbSoldComments:edit")
	@RequestMapping(value = "save")
	public String save(GbSoldComments gbSoldComments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbSoldComments)){
			return form(gbSoldComments, model);
		}
		gbSoldCommentsService.save(gbSoldComments);
		addMessage(redirectAttributes, "保存管理卖标评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbSoldComments/?repage";
	}
	
	@RequiresPermissions("sys:gb:gbSoldComments:edit")
	@RequestMapping(value = "delete")
	public String delete(GbSoldComments gbSoldComments, RedirectAttributes redirectAttributes) {
		gbSoldCommentsService.delete(gbSoldComments);
		addMessage(redirectAttributes, "删除管理卖标评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gb/gbSoldComments/?repage";
	}

}