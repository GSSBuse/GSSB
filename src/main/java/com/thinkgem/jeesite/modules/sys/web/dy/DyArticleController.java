/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.dy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyArticle;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.service.dy.DyArticleService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;

/**
 * 资讯文章管理Controller
 * @author quanyf.fnst
 * @version 2015-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyArticle")
public class DyArticleController extends BaseController {

	@Autowired
	private DyArticleService dyArticleService;
	@Autowired
	private DyDomainnameService dyDomainnameService;
	
	@ModelAttribute
	public DyArticle get(@RequestParam(required=false) String id) {
		DyArticle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyArticleService.get(id);
		}
		if (entity == null){
			entity = new DyArticle();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyArticle:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyArticle dyArticle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyArticle> page = dyArticleService.findPage(new Page<DyArticle>(request, response), dyArticle); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyArticleList";
	}
	@RequiresPermissions("sys:dy:dyArticle:view")
	@ResponseBody
	@RequestMapping(value = "selectList")
	public List<Map<String , Object>> selectList(HttpServletResponse response){
		List<Map<String , Object>> mapList = Lists.newArrayList();
		List<DyArticle> list = dyArticleService.findList(new DyArticle());
		for(DyArticle d : list){
			Map<String , Object> map = Maps.newHashMap();
			map.put("id", d.getId());
			map.put("name", StringUtils.replace(d.getTitle(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
	@RequiresPermissions("sys:dy:dyArticle:view")
	@RequestMapping(value = "form")
	public String form(DyArticle dyArticle, Model model) {
		if(StringUtils.isNotBlank(dyArticle.getDescription())){
			DyDomainname dyDomainname = dyDomainnameService.get(dyArticle.getDescription());
			if(dyDomainname != null){
				dyArticle.setDomainnameName(dyDomainname.getName());
			}
		}
		model.addAttribute("dyArticle", dyArticle);
		return "modules/sys/dy/dyArticleForm";
	}

	@RequiresPermissions("sys:dy:dyArticle:edit")
	@RequestMapping(value = "save")
	public String save(DyArticle dyArticle, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyArticle)){
			return form(dyArticle, model);
		}
		//html标签转义
		String newContent = StringEscapeUtils.unescapeHtml4(dyArticle.getContent());
		dyArticle.setContent(newContent);
		dyArticleService.save(dyArticle);
		addMessage(redirectAttributes, "保存资讯文章成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyArticle/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyArticle:edit")
	@RequestMapping(value = "delete")
	public String delete(DyArticle dyArticle, RedirectAttributes redirectAttributes) {
		dyArticleService.delete(dyArticle);
		addMessage(redirectAttributes, "删除资讯文章成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyArticle/?repage";
	}

}