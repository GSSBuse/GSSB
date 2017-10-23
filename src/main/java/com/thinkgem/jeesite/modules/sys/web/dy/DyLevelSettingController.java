/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.dy;

import java.util.ArrayList;
import java.util.List;

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
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyLevelSetting;
import com.thinkgem.jeesite.modules.sys.service.dy.DyLevelSettingService;

/**
 * 加价与保证金增幅Controller
 * @author quanyf.fnst
 * @version 2015-10-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyLevelSetting")
public class DyLevelSettingController extends BaseController {

	@Autowired
	private DyLevelSettingService dyLevelSettingService;
	
	@ModelAttribute
	public DyLevelSetting get(@RequestParam(required=false) String id) {
		DyLevelSetting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyLevelSettingService.get(id);
		}
		if (entity == null){
			entity = new DyLevelSetting();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyLevelSetting:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyLevelSetting dyLevelSetting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyLevelSetting> page = dyLevelSettingService.findPage(new Page<DyLevelSetting>(request, response), dyLevelSetting); 
		model.addAttribute("page", page);
		model.addAttribute("type",dyLevelSetting.getType());
		return "modules/sys/dy/dyLevelSettingList";
	}

	@RequiresPermissions("sys:dy:dyLevelSetting:view")
	@RequestMapping(value = "form")
	public String form(DyLevelSetting dyLevelSetting, Model model) {
		model.addAttribute("dyLevelSetting", dyLevelSetting);
		return "modules/sys/dy/dyLevelSettingForm";
	}
	@RequiresPermissions("sys:dy:dyLevelSetting:edit")
	@RequestMapping(value = "subTable")
	public String subTable(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes){
		String[] levelList = request.getParameterValues("endLevel");
		String[] priceList = request.getParameterValues("price");
		String type =request.getParameter("type");
		try{
			List<DyLevelSetting> dyLevelList = new ArrayList<DyLevelSetting>();
			for(int i=0 ; i<levelList.length ; i++){
				DyLevelSetting dyLevelSetting = new DyLevelSetting();
				dyLevelSetting.setLevel(Long.parseLong(levelList[i]));
				dyLevelSetting.setPrice(Long.parseLong(priceList[i]));
				dyLevelSetting.setType(type);
				dyLevelList.add(dyLevelSetting);
			}
			dyLevelSettingService.savelList(dyLevelList);
			CacheUtils.remove(Constant.CACHE_BID_RULES_MAP);
			addMessage(redirectAttributes, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "保存失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyLevelSetting/?type="+type+"&repage";
	}
	@RequiresPermissions("sys:dy:dyLevelSetting:edit")
	@RequestMapping(value = "save")
	public String save(DyLevelSetting dyLevelSetting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyLevelSetting)){
			return form(dyLevelSetting, model);
		}
		dyLevelSettingService.save(dyLevelSetting);
		addMessage(redirectAttributes, "保存加价与保证金增幅成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyLevelSetting/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyLevelSetting:edit")
	@RequestMapping(value = "delete")
	public String delete(DyLevelSetting dyLevelSetting, RedirectAttributes redirectAttributes) {
		try{
			dyLevelSettingService.delete(dyLevelSetting);
			addMessage(redirectAttributes, "删除加价与保证金增幅成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyLevelSetting/?repage";
	}

}