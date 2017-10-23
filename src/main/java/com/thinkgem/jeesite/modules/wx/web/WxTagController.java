/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.entity.WxTag;
import com.thinkgem.jeesite.modules.wx.service.WxTagService;

/**
 * 企业微信标签管理Controller
 * @author shenzb.fnst
 * @version 2015-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/wx/wxTag")
public class WxTagController extends BaseController {

	@Autowired
	private WxTagService wxTagService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private OfficeService officeService;

	@ModelAttribute
	public WxTag get(@RequestParam(required=false) String id) {
		WxTag entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wxTagService.get(id);
		}
		if (entity == null){
			entity = new WxTag();
		}
		return entity;
	}
	
	@RequiresPermissions("wx:wxTag:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxTag wxTag, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WxTag> page = wxTagService.findPage(new Page<WxTag>(request, response), wxTag); 
		model.addAttribute("page", page);
		return "modules/wx/wxTagList";
	}

	@RequiresPermissions("wx:wxTag:view")
	@RequestMapping(value = "form")
	public String form(WxTag wxTag, Model model) {
		model.addAttribute("wxTag", wxTag);
		return "modules/wx/wxTagForm";
	}

	@RequiresPermissions("wx:wxTag:edit")
	@RequestMapping(value = "save")
	public String save(WxTag wxTag, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wxTag)){
			return form(wxTag, model);
		}
		wxTagService.save(wxTag);
		addMessage(redirectAttributes, "保存企业微信标签成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxTag/?repage";
	}
	
	@RequiresPermissions("wx:wxTag:edit")
	@RequestMapping(value = "delete")
	public String delete(WxTag wxTag, RedirectAttributes redirectAttributes) {
		wxTagService.delete(wxTag);
		addMessage(redirectAttributes, "删除企业微信标签成功");
		return "redirect:"+Global.getAdminPath()+"/wx/wxTag/?repage";
	}

	/**
	 * 添加成员页面
	 * @param wxTag
	 * @param model
	 * @return
	 */
	@RequiresPermissions("wx:wxTag:edit")
	@RequestMapping(value = "assign")
	public String assign(WxTag wxTag, Model model) {
		List<User> userList = wxTagService.findUser(wxTag);
		model.addAttribute("userList", userList);
		return "modules/wx/wxTagAssign";
	}
	
	/**
	 * 标签分配 -- 打开标签分配对话框
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("wx:wxTag:view")
	@RequestMapping(value = "usertotag")
	public String selectUserToRole(WxTag wxTag, Model model) {
		List<User> userList = wxTagService.findUser(wxTag);
		model.addAttribute("wxTag", wxTag);
		model.addAttribute("userList", userList);
		model.addAttribute("selectIds", Collections3.extractToString(userList, "name", ","));
		model.addAttribute("officeList", officeService.findAll());
		return "modules/sys/selectUserToTag";
	}

	/**
	 * 标签分配
	 * @param wxTag
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("wx:wxTag:edit")
	@RequestMapping(value = "assigntag")
	public String assignTag(WxTag wxTag, String[] idsArr, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/wx/wxTag/assign?id="+wxTag.getId();
		}
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			User user = wxTagService.assignUserToTag(wxTag, systemService.getUser(idsArr[i]));
			if (null != user) {
				msg.append("<br/>新增用户【" + user.getName() + "】到标签【" + wxTag.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功添加 "+newNum+" 个用户"+msg);
		return "redirect:" + adminPath + "/wx/wxTag/assign?id="+wxTag.getId();
	}

	/**
	 * 标签分配 -- 从标签中移除用户
	 * @param userId
	 * @param wxTagId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("wx:wxTag:edit")
	@RequestMapping(value = "outtag")
	public String outrole(String userId, String wxTagId, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/wx/wxTag/assign?id="+wxTagId;
		}
		WxTag wxTag = wxTagService.get(wxTagId);
		User user = systemService.getUser(userId);
		if (UserUtils.getUser().getId().equals(userId)) {
			addMessage(redirectAttributes, "无法从标签【" + wxTag.getName() + "】中移除用户【" + user.getName() + "】自己！");
		}else {
			Boolean flag = wxTagService.outUserInTag(wxTag, user);
			if (!flag) {
				addMessage(redirectAttributes, "用户【" + user.getName() + "】从标签【" + wxTag.getName() + "】中移除失败！");
			} else {
				addMessage(redirectAttributes, "用户【" + user.getName() + "】从标签【" + wxTag.getName() + "】中移除成功！");
			}
		}
		return "redirect:" + adminPath + "/wx/wxTag/assign?id="+wxTag.getId();
	}

	/**
	 * 标签分配 -- 根据部门编号获取用户列表
	 * @param officeId
	 * @param response
	 * @return
	 */
	@RequiresPermissions("sys:role:view")
	@ResponseBody
	@RequestMapping(value = "users")
	public List<Map<String, Object>> users(String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		User user = new User();
		user.setOffice(new Office(officeId));
		Page<User> page = systemService.findUser(new Page<User>(1, -1), user);
		for (User e : page.getList()) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", 0);
			map.put("name", e.getName());
			mapList.add(map);			
		}
		return mapList;
	}
}