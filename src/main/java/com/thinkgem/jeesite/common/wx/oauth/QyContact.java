/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.wx.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.json.Json;

import com.alibaba.druid.support.json.JSONUtils;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.wx.util.HttpKit;
import com.thinkgem.jeesite.modules.app.entity.Agent;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.dao.WxInterfaceInfoDao;
import com.thinkgem.jeesite.modules.wx.entity.WxInterfaceInfo;

/**
 * 微信企业号通讯录管理接口
 * @author shenzb.fnst
 * @version 2015-07-27
 */
public class QyContact {

	private static final String TOKEN_KEY = "access_token";
	private static final String USER_LIST_KEY = "userlist";
	private static final String USE_ID_LIST_KEY = "useridlist";
	private static final String AGENT_LIST_KEY = "agentlist";
	private static final String RET_CODE_KEY = "errcode";
	private static final String RET_CODE_0 = "0";

	private static final String GET_USER_LIST_URI = "https://qyapi.weixin.qq.com/cgi-bin/user/list";
	private static final String DELETE_USER_LIST_URI = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete";
	private static final String CREATE_USER_URI = "https://qyapi.weixin.qq.com/cgi-bin/user/create";

	private static final String GET_AGENT_LIST_URI = "https://qyapi.weixin.qq.com/cgi-bin/agent/list";

	private static final String CREATE_MENU_URI = "https://qyapi.weixin.qq.com/cgi-bin/menu/create";
	private static final String DELETE_MENU_URI = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete";
	private static final String GET_MENU_URI = "https://qyapi.weixin.qq.com/cgi-bin/menu/get";

	private static final String CREATE_DEPARTMENT_URI = "https://qyapi.weixin.qq.com/cgi-bin/department/create";
	private static final String UPDATE_DEPARTMENT_URI = "https://qyapi.weixin.qq.com/cgi-bin/department/update";
	private static final String DELETE_DEPARTMENT_URI = "https://qyapi.weixin.qq.com/cgi-bin/department/delete";
	private static final String GET_DEPARTMENT_URI = "https://qyapi.weixin.qq.com/cgi-bin/department/list";

	private static final String CREATE_TAG_URI = "https://qyapi.weixin.qq.com/cgi-bin/tag/create";
	private static final String UPDATE_TAG_URI = "https://qyapi.weixin.qq.com/cgi-bin/tag/update";
	private static final String DELETE_TAG_URI = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete";
	private static final String GET_TAG_URI = "https://qyapi.weixin.qq.com/cgi-bin/tag/list";
	private static final String GET_TAG_USER_URI = "https://qyapi.weixin.qq.com/cgi-bin/tag/get";
	private static final String ADD_TAG_USER_URI = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers";
	private static final String DEL_TAG_USER_URI = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers";
	
	private static WxInterfaceInfoDao wxInterfaceInfoDao = SpringContextHolder.getBean(WxInterfaceInfoDao.class);

	/**
	 * 通过公司ID获取微信接口TOKEN
	 * @param id
	 * @return 取不到返回null
	 * @throws Exception 
	 */
	public static String getToken() throws Exception{
		String companyId = UserUtils.getUser().getCompany().getId();
		
		WxInterfaceInfo wxInterfaceInfo = wxInterfaceInfoDao.getWxInterfaceInfoByCompanyId(companyId);
		if (wxInterfaceInfo == null) {
			return null;
		}
		
		Map<String, String> tokenMap = Json.fromJsonAsMap(
				String.class,
				new Oauth(wxInterfaceInfo.getCorpid(),
						wxInterfaceInfo.getProviderSecret()).getToken());

		return tokenMap.get(TOKEN_KEY);
	}

	/**
	 * 获取微信用户列表
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, String>> getWxUserList(String token) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put(TOKEN_KEY, token); // 调用接口凭证
		params.put("department_id", "1");       // 获取的部门id
		params.put("fetch_child", "1");         // 1/0：是否递归获取子部门下面的成员
		params.put("status", "0");              // 0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加
		String httpsResult = HttpKit.get(GET_USER_LIST_URI, params);
		Map<?, ?> jsonMap = (Map<?, ?>) JSONUtils.parse(httpsResult);
		return (List<Map<String, String>>) jsonMap.get(USER_LIST_KEY);
	}

	/**
	 * 获取微信用户列表
	 * @param token
	 * @return 取不到返回null
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static List<User> getUserList(String token) throws Exception{

		List<Map<String, String>> wxUserList = getWxUserList(token);

		List<User> userList = new ArrayList<User>();
		String dateTime = DateUtils.getDateTime();
		for (Map<String, String> wxUser : wxUserList) {
			User user = new User();
			user.setCompany(UserUtils.getUser().getCompany());
			user.setOffice(new Office(wxUser.get("department").replace("[", "").replace("]", "")));
			user.setNo(wxUser.get("userid"));
			user.setName(wxUser.get("name"));
			user.setLoginName(wxUser.get("userid"));
			user.setPassword(SystemService.entryptPassword(Global.getConfig("initlize.password")));
			if (wxUser.containsKey("email")) {
				user.setEmail(wxUser.get("email"));
			}
			if (wxUser.containsKey("mobile")) {
				user.setMobile(wxUser.get("mobile"));
			}
			if (wxUser.containsKey("weixinid")) {
				user.setWechat(wxUser.get("weixinid"));
			}
			user.setLoginFlag("1");
			user.setUserType("3");
			List<Role> roleList = new ArrayList<Role>();
			Role role = new Role("9646061130ef4a4384ddfbff975e2ea4");
			roleList.add(role);
			user.setRole(role);
			user.setRoleList(roleList);
			user.setRemarks(dateTime + " 导入用户");
			userList.add(user);
		}
		return userList;
	}

	/**
	 * 同步本地用户到微信企业号
	 * @param userList
	 * @param token
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void uploadUser2Weixin(List<User> userList, String token) throws Exception {
		Map<String, ?> jsonMap = null;
		String url = null;
		List<Map<String, String>> wxUserList = getWxUserList(token);
		List<String> userIdList = new ArrayList<String>();
		for (Map<String, String> wxUser : wxUserList) {
			userIdList.add(wxUser.get("userid"));
		}
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put(TOKEN_KEY, token);          // 调用接口凭证
		if (!userIdList.isEmpty()) {
			params.put(USE_ID_LIST_KEY, userIdList); // 获取的部门id
			url = DELETE_USER_LIST_URI + "?" + TOKEN_KEY + "=" + token;
			String deleteResult = HttpKit.post(url, JsonMapper.toJsonString(params));
			jsonMap = (Map<String, ?>) JSONUtils.parse(deleteResult);
			if (!RET_CODE_0.equals(jsonMap.get(RET_CODE_KEY).toString())) {
				throw new Exception("同步失败，请重试");
			}
		}

		url = CREATE_USER_URI + "?" + TOKEN_KEY + "=" + token;
		for (User user : userList) {
			params.clear();
			params.put("userid", user.getNo());
			params.put("name", user.getName());
			List<String> departmentList = new ArrayList<String>();
			departmentList.add(user.getOffice().getId());
			params.put("department", departmentList);
//			params.put("position", "");
			if (StringUtils.isBlank(user.getMobile())
					&& StringUtils.isNotBlank(user.getMobile())
					&& StringUtils.isNotBlank(user.getMobile())) {
				throw new Exception("用户" + user.getNo() + "的手机，邮件，微信号必须不能同时为空，请检查用户信息");
			}
			if (StringUtils.isNotBlank(user.getMobile())) {
				params.put("mobile", user.getMobile());
			}
			if (StringUtils.isNotBlank(user.getEmail())) {
				params.put("email", user.getEmail());
			}
			if (StringUtils.isNotBlank(user.getWechat())) {
				params.put("weixinid", user.getWechat());
			}
			String uploadResult = HttpKit.post(url, JsonMapper.toJsonString(params));
			jsonMap = (Map<String, ?>) JSONUtils.parse(uploadResult);
			if (!RET_CODE_0.equals(jsonMap.get(RET_CODE_KEY).toString())) {
				throw new Exception("用户" + user.getNo() + "同步失败，请重试");
			}
		}
	}

	/**
	 * 获取企业应用列表
	 * @param token 调用接口凭证
	 * @return 
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	@SuppressWarnings("unchecked")
	public static List<Agent> getAgentList(String token) throws Exception{
		List<Agent> agentList = new ArrayList<Agent>();

		Map<String, String> params = new HashMap<String, String>();
		params.put(TOKEN_KEY, token);
		String httpsResult = HttpKit.get(GET_AGENT_LIST_URI, params );
		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpsResult);
		List<Map<String, ?>> agentMapList = (List<Map<String, ?>>)jsonMap.get(AGENT_LIST_KEY);
		for (Map<String, ?> agentMap : agentMapList) {
			Agent agent = new Agent();
			agent.setAgentid(agentMap.get("agentid").toString());
			agent.setName(agentMap.get("name").toString());
			agent.setRound_logo_url(agentMap.get("round_logo_url").toString());
			agent.setSquare_logo_url(agentMap.get("square_logo_url").toString());
			agentList.add(agent);
		}
		return agentList;
	}

	/**
	 * 创建应用菜单
	 * @param token 调用接口凭证
	 * @param agentId 企业应用的id
	 * @param menu 菜单
	 * @return 返回码errcode
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	@SuppressWarnings("unchecked")
	public static String createAppMenu(String token, String agentId, Map<String, Object> menu) throws Exception{
		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(TOKEN_KEY, token);
		urlMap.put("agentid", agentId);

		String httpsResult = HttpKit.post(
				HttpKit.initParams(CREATE_MENU_URI, urlMap),
				JsonMapper.toJsonString(menu)
				);
		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpsResult);

		return jsonMap.get(RET_CODE_KEY).toString();
	}

	/**
	 * 删除应用菜单
	 * @param token 调用接口凭证
	 * @param agentId 企业应用的id
	 * @return 返回码errcode
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	@SuppressWarnings("unchecked")
	public static String deleteAppMenu(String token, String agentId) throws Exception{
		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(TOKEN_KEY, token);
		urlMap.put("agentid", agentId);

		String httpsResult = HttpKit.get(
				DELETE_MENU_URI,
				urlMap
				);
		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpsResult);

		return jsonMap.get(RET_CODE_KEY).toString();
	}

	/**
	 * 获取应用菜单
	 * @param token 调用接口凭证
	 * @param agentId 企业应用的id
	 * @return 菜单
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, ?> getAppMenu(String token, String agentId) throws Exception{
		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(TOKEN_KEY, token);
		urlMap.put("agentid", agentId);

		String httpsResult = HttpKit.get(
				GET_MENU_URI,
				urlMap
				);
		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpsResult);

		return jsonMap;
	}

	public static void uploadDept2Weixin(List<Office> officeList, String token) {
		// TODO Auto-generated method stub
		
	}
}
