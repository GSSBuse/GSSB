/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.wx.oauth.QyContact;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.service.SyncInfoService;
import com.thinkgem.jeesite.modules.wx.service.WxInterfaceInfoService;

/**
 * 微信同步Controller
 * @author shenzb.fnst
 * @version 2015-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/wx/syncInfo")
public class SyncInfoController extends BaseController {

	@Autowired
	private WxInterfaceInfoService wxInterfaceInfoService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private SyncInfoService syncInfoService;

	/**
	 * 上传本地用户到微信企业号
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "localUser2wx")
	public String localUser2wx(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			List<User> userList = UserUtils.getUserListByUserType("3");
			String token = QyContact.getToken();
			QyContact.uploadUser2Weixin(userList, token);
			addMessage(redirectAttributes, "同步到服务器成功。");
		} catch (Exception e) {
			addMessage(redirectAttributes, "同步到服务器失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 从微信企业号同步用户到本地
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "wxUser2local")
	public String wxUser2local(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String token = QyContact.getToken();
			List<User> userList = QyContact.getUserList(token);
			systemService.deleteNormalUser();
			for (User user : userList) {
				systemService.saveUser(user);
			}
			addMessage(redirectAttributes, "从服务器下载成功。");
		} catch (Exception e) {
			addMessage(redirectAttributes, "从服务器下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 上传本地部门信息到微信企业号
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "localDept2wx")
	public String localDept2wx(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			List<Office> officeList = syncInfoService.getOfficeListByCompanyId(UserUtils.getUser().getCompany().getId());
			String token = QyContact.getToken();
			QyContact.uploadDept2Weixin(officeList, token);
			addMessage(redirectAttributes, "同步到服务器成功。");
		} catch (Exception e) {
			addMessage(redirectAttributes, "同步到服务器失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 从微信企业号同步部门信息到本地
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "wxDept2local")
	public String wxDept2local(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String token = QyContact.getToken();
			List<User> userList = QyContact.getUserList(token);
			systemService.deleteNormalUser();
			for (User user : userList) {
				systemService.saveUser(user);
			}
			addMessage(redirectAttributes, "从服务器下载成功。");
		} catch (Exception e) {
			addMessage(redirectAttributes, "从服务器下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 上传本地标签到微信企业号
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "localTag2wx")
	public String localTag2wx(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			List<User> userList = UserUtils.getUserListByUserType("3");
			String token = QyContact.getToken();
			QyContact.uploadUser2Weixin(userList, token);
			addMessage(redirectAttributes, "同步到服务器成功。");
		} catch (Exception e) {
			addMessage(redirectAttributes, "同步到服务器失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 从微信企业号同步标签到本地
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "wxTag2local")
	public String wxTag2local(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String token = QyContact.getToken();
			List<User> userList = QyContact.getUserList(token);
			systemService.deleteNormalUser();
			for (User user : userList) {
				systemService.saveUser(user);
			}
			addMessage(redirectAttributes, "从服务器下载成功。");
		} catch (Exception e) {
			addMessage(redirectAttributes, "从服务器下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
}