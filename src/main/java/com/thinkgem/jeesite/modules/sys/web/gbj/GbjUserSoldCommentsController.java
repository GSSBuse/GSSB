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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldComments;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjUserSoldCommentsService;

/**
 * 卖标信息评论Controller
 * @author 管理员
 * @version 2017-12-21
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjUserSoldComments")
public class GbjUserSoldCommentsController extends BaseController {

	@Autowired
	private GbjUserSoldCommentsService gbjUserSoldCommentsService;
	
	@ModelAttribute
	public GbjUserSoldComments get(@RequestParam(required=false) String id) {
		GbjUserSoldComments entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjUserSoldCommentsService.get(id);
		}
		if (entity == null){
			entity = new GbjUserSoldComments();
		}
		return entity;
	}
	
	
	//根据sold_Id查询买标信息评论信息
		@RequiresPermissions("sys:gbj:gbjUserSoldComments:view")
		@RequestMapping(value = {"getSoldCommentsListbyid"})
		public String getComments(String sold_Id,HttpServletRequest request, 
				HttpServletResponse response, Model model) {
			
			//System.out.print(buy_Id);                                    
			Page<GbjUserSoldComments> page = gbjUserSoldCommentsService.findPages(new Page<GbjUserSoldComments>(request, response), sold_Id);
			
			model.addAttribute("page", page);
			return "modules/sys/gbj/gbjUserSoldCommentsList";
		
			
		}
	
	@RequiresPermissions("sys:gbj:gbjUserSoldComments:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjUserSoldComments gbjUserSoldComments, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjUserSoldComments> page = gbjUserSoldCommentsService.findPage(new Page<GbjUserSoldComments>(request, response), gbjUserSoldComments); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjUserSoldCommentsList";
	}

	@RequiresPermissions("sys:gbj:gbjUserSoldComments:view")
	@RequestMapping(value = "form")
	public String form(GbjUserSoldComments gbjUserSoldComments, Model model) {
		model.addAttribute("gbjUserSoldComments", gbjUserSoldComments);
		return "modules/sys/gbj/gbjUserSoldCommentsForm";
	}

	@RequiresPermissions("sys:gbj:gbjUserSoldComments:edit")
	@RequestMapping(value = "save")
	public String save(GbjUserSoldComments gbjUserSoldComments, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjUserSoldComments)){
			return form(gbjUserSoldComments, model);
		}
		gbjUserSoldCommentsService.save(gbjUserSoldComments);
		addMessage(redirectAttributes, "保存管理卖标评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserSoldComments/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjUserSoldComments:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjUserSoldComments gbjUserSoldComments, RedirectAttributes redirectAttributes) {
		gbjUserSoldCommentsService.delete(gbjUserSoldComments);
		addMessage(redirectAttributes, "删除管理卖标评论成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjUserSoldComments/?repage";
	}

}