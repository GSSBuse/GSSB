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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjReward;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjRewardService;

/**
 * 悬赏信息管理Controller
 * @author snnu
 * @version 2017-11-20
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/gbj/gbjReward")
public class GbjRewardController extends BaseController {

	@Autowired
	private GbjRewardService gbjRewardService;
	
	@ModelAttribute
	public GbjReward get(@RequestParam(required=false) String id) {
		GbjReward entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = gbjRewardService.get(id);
		}
		if (entity == null){
			entity = new GbjReward();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:gbj:gbjReward:view")
	@RequestMapping(value = {"list", ""})
	public String list(GbjReward gbjReward, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GbjReward> page = gbjRewardService.findPage(new Page<GbjReward>(request, response), gbjReward); 
		model.addAttribute("page", page);
		return "modules/sys/gbj/gbjRewardList";
	}

	@RequiresPermissions("sys:gbj:gbjReward:view")
	@RequestMapping(value = "form")
	public String form(GbjReward gbjReward, Model model) {
		model.addAttribute("gbjReward", gbjReward);
		return "modules/sys/gbj/gbjRewardForm";
	}

	@RequiresPermissions("sys:gbj:gbjReward:edit")
	@RequestMapping(value = "save")
	public String save(GbjReward gbjReward, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gbjReward)){
			return form(gbjReward, model);
		}
		gbjRewardService.save(gbjReward);
		addMessage(redirectAttributes, "保存悬赏信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjReward/?repage";
	}
	
	@RequiresPermissions("sys:gbj:gbjReward:edit")
	@RequestMapping(value = "delete")
	public String delete(GbjReward gbjReward, RedirectAttributes redirectAttributes) {
		gbjRewardService.delete(gbjReward);
		addMessage(redirectAttributes, "删除悬赏信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/sys/gbj/gbjReward/?repage";
	}

}