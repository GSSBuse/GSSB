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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAttention;
import com.thinkgem.jeesite.modules.sys.service.dy.DyAttentionService;

/**
 * 会员关注管理Controller
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyAttention")
public class DyAttentionController extends BaseController {

	@Autowired
	private DyAttentionService dyAttentionService;
	
	@ModelAttribute
	public DyAttention get(@RequestParam(required=false) String id) {
		DyAttention entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyAttentionService.get(id);
		}
		if (entity == null){
			entity = new DyAttention();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyAttention:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyAttention dyAttention, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyAttention> page = dyAttentionService.findPage(new Page<DyAttention>(request, response), dyAttention); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyAttentionList";
	}

	@RequiresPermissions("sys:dy:dyAttention:view")
	@RequestMapping(value = "form")
	public String form(DyAttention dyAttention, Model model) {
		model.addAttribute("dyAttention", dyAttention);
		return "modules/sys/dy/dyAttentionForm";
	}

	@RequiresPermissions("sys:dy:dyAttention:edit")
	@RequestMapping(value = "save")
	public String save(DyAttention dyAttention, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyAttention)){
			return form(dyAttention, model);
		}
		dyAttentionService.save(dyAttention);
		addMessage(redirectAttributes, "保存会员关注成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyAttention/?repage";
	}
	
	@RequiresPermissions("sys:dy:dyAttention:edit")
	@RequestMapping(value = "delete")
	public String delete(DyAttention dyAttention, RedirectAttributes redirectAttributes) {
		dyAttentionService.delete(dyAttention);
		addMessage(redirectAttributes, "删除会员关注成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyAttention/?repage";
	}

}