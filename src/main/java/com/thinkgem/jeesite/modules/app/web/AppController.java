/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */

package com.thinkgem.jeesite.modules.app.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.wx.oauth.QyContact;

/**
 * 微信同步Controller
 * @author shenzb.fnst
 * @version 2015-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/app")
public class AppController extends BaseController {
	/**
	 * 测试应用
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "testApp")
	public String testApp(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/app/testApp";
	}

	@RequiresPermissions("app:mamagement:view")
	@RequestMapping(value = "appList")
	public String appManagementList(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			String token = QyContact.getToken();
			model.addAttribute("agentList", QyContact.getAgentList(token));
		} catch (Exception e) {
			addMessage(model, "获取应用列表失败！失败信息：" + e.getMessage());
		}
		return "modules/app/appList";
	}

	@RequiresPermissions("app:mamagement:edit")
	@RequestMapping(value = "appManagement/{agentid}")
	public String editAgent(@PathVariable String agentid) {
		//return "redirect:" + adminPath + "/app/appManagementList?repage";
		return "modules/app/appList";
	}

	@RequestMapping(value = "appManagement/create")
	public String createAgent(Model model) {
		return "modules/app/appCreate";
	}
}
