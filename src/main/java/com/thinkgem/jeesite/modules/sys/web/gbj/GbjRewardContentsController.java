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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjRewardContents;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjRewardContentsService;

/**
 * 悬赏信息评论Controller
 * @author snnu
 * @version 2017-11-20
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjRewardContents")
public class GbjRewardContentsController extends BaseController {

	@Autowired
	private GbjRewardContentsService gbjRewardContentsService;
	
	@ModelAttribute
	public GbjRewardContents get(@RequestParam(required=false) String id) {
		GbjRewardContents entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjRewardContentsService.get(id);
		}
		if (entity == null){
			entity = new GbjRewardContents();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjRewardContents:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjRewardContents gbjRewardContents, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjRewardContents> page = gbjRewardContentsService.findPage(new Page<GbjRewardContents>(request, response), gbjRewardContents); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjRewardContentsList";
	}

	@RequiresPermissions("sys:gbj:gbjRewardContents:view")
	@RequestMapping(value = "form")
	public String form(GbjRewardContents gbjRewardContents, Model model) {
		model.addAttribute("gbjRewardContents", gbjRewardContents);
		return "modules/sys/gbj/gbjRewardContentsForm";
	}

	@RequiresPermissions("sys:gbj:gbjRewardContents:edit")
	@RequestMapping(value = "save")
	public String save(GbjRewardContents gbjRewardContents, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjRewardContents)){
			return form(gbjRewardContents, model);
		}
		gbjRewardContentsService.save(gbjRewardContents);
		addMessage(redirectAttributes, "保存悬赏信息评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjRewardContents/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjRewardContents:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjRewardContents gbjRewardContents, RedirectAttributes redirectAttributes) {
		gbjRewardContentsService.delete(gbjRewardContents);
		addMessage(redirectAttributes, "删除悬赏信息评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjRewardContents/?repage";
	}

}