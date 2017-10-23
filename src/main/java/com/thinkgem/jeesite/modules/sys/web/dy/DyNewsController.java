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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyNews;
import com.thinkgem.jeesite.modules.sys.service.dy.DyNewsService;
import com.thinkgem.jeesite.modules.sys.utils.NewsUpdateFlagUtil;

/**
 * 最新消息管理Controller
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyNews")
public class DyNewsController extends BaseController {

	@Autowired
	private DyNewsService dyNewsService;
	
	@ModelAttribute
	public DyNews get(@RequestParam(required=false) String id) {
		DyNews entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyNewsService.get(id);
		}
		if (entity == null){
			entity = new DyNews();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyNews:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyNews dyNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyNews> page = dyNewsService.findPage(new Page<DyNews>(request, response), dyNews); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyNewsList";
	}

	@RequiresPermissions("sys:dy:dyNews:view")
	@RequestMapping(value = "form")
	public String form(DyNews dyNews, Model model) {
		model.addAttribute("dyNews", dyNews);
		return "modules/sys/dy/dyNewsForm";
	}

	@RequiresPermissions("sys:dy:dyNews:edit")
	@RequestMapping(value = "save")
	public String save(DyNews dyNews, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyNews)){
			return form(dyNews, model);
		}
		try{
			dyNewsService.save(dyNews);
			NewsUpdateFlagUtil.setUpdateTimestamp();
			addMessage(redirectAttributes, "保存最新消息成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "保存最新消息失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyNews/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyNews:edit")
	@RequestMapping(value = "delete")
	public String delete(DyNews dyNews, RedirectAttributes redirectAttributes) {
		dyNewsService.delete(dyNews);
		addMessage(redirectAttributes, "删除最新消息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyNews/?repage";
	}

}