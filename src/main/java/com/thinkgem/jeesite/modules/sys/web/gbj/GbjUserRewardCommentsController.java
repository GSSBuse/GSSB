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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserRewardComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldComments;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserRewardCommentsService;

/**
 * 悬赏信息评论Controller
 * @author snnu
 * @version 2017-12-21
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjUserRewardComments")
public class GbjUserRewardCommentsController extends BaseController {

	@Autowired
	private GbjUserRewardCommentsService gbjUserRewardCommentsService;
	
	@ModelAttribute
	public GbjUserRewardComments get(@RequestParam(required=false) String id) {
		GbjUserRewardComments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjUserRewardCommentsService.get(id);
		}
		if (entity == null){
			entity = new GbjUserRewardComments();
		}
		return entity;
	}
	
	//根据reward_Id查询买标信息评论信息
			@RequiresPermissions("sys:gbj:gbjUserRewardComments:view")
			@RequestMapping(value = {"getRewardCommentsListbyid"})
			public String getComments(String reward_Id,HttpServletRequest request, 
					HttpServletResponse response, Model model) {
				
				//System.out.print(reward_Id);                                    
				Page<GbjUserRewardComments> page = gbjUserRewardCommentsService.findPages(new Page<GbjUserRewardComments>(request, response), reward_Id);
				
				model.addAttribute("page", page);
				return "modules/sys/gbj/gbjUserRewardCommentsList";
			
				
			}
	
	
	@RequiresPermissions("sys:gbj:gbjUserRewardComments:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjUserRewardComments gbjUserRewardComments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjUserRewardComments> page = gbjUserRewardCommentsService.findPage(new Page<GbjUserRewardComments>(request, response), gbjUserRewardComments); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjUserRewardCommentsList";
	}

	@RequiresPermissions("sys:gbj:gbjUserRewardComments:view")
	@RequestMapping(value = "form")
	public String form(GbjUserRewardComments gbjUserRewardComments, Model model) {
		model.addAttribute("gbjUserRewardComments", gbjUserRewardComments);
		return "modules/sys/gbj/gbjUserRewardCommentsForm";
	}

	@RequiresPermissions("sys:gbj:gbjUserRewardComments:edit")
	@RequestMapping(value = "save")
	public String save(GbjUserRewardComments gbjUserRewardComments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjUserRewardComments)){
			return form(gbjUserRewardComments, model);
		}
		gbjUserRewardCommentsService.save(gbjUserRewardComments);
		addMessage(redirectAttributes, "保存悬赏信息评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserRewardComments/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjUserRewardComments:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjUserRewardComments gbjUserRewardComments, RedirectAttributes redirectAttributes) {
		gbjUserRewardCommentsService.delete(gbjUserRewardComments);
		addMessage(redirectAttributes, "删除悬赏信息评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserRewardComments/?repage";
	}

}