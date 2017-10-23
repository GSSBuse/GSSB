/**
 * 微信企业号开发API
 * (c) 2012-2014  , MIT Licensed
 * http://qydev.weixin.qq.com/wiki/index.php
 */
package com.thinkgem.jeesite.common.wx.oauth;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.support.json.JSONUtils;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.wx.util.HttpKit;

/**
 * 微信企业号应用管理接口
 * @author shenzb.fnst
 * @version 2015-07-27
 */
public class QyAgent {
	/** 获取应用套件令牌的URI */
	private static final String SUITE_TOKEN_URI = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";

	/** 获取预授权码的URI */
	private static final String PRE_AUTH_CODE_URI = "https://qyapi.weixin.qq.com/cgi-bin/service/get_pre_auth_code";

	/** 获取永久授权码的URI */
	private static final String PERMANENT_CODE_URI = "https://qyapi.weixin.qq.com/cgi-bin/service/get_permanent_code";

	/** 获取企业号的授权信息的URI */
	private static final String AUTH_INFO_URI = "https://qyapi.weixin.qq.com/cgi-bin/service/get_auth_info";

	/** 获取企业号应用的授权信息的URI */
	private static final String GET_AGENT_URI = "https://qyapi.weixin.qq.com/cgi-bin/service/get_agent";

	/** 设置企业号应用的授权信息的URI */
	private static final String SET_AGENT_URI = "https://qyapi.weixin.qq.com/cgi-bin/service/set_agent";

	/** 获取企业号access_token的URI */
	private static final String GET_CORP_TOKEN_URI = "https://qyapi.weixin.qq.com/cgi-bin/service/get_corp_token";

	/** Map Key*/
	private static final String SUITE_ID           = "suite_id";           // 应用套件id
	private static final String SUITE_SECRET       = "suite_secret";       // 应用套件secret
	private static final String SUITE_TICKET       = "suite_ticket";       // 微信后台推送的ticket
	private static final String SUITE_ACCESS_TOKEN = "suite_access_token"; // 应用套件access_token
	private static final String APPID              = "appid";              // 应用id
	private static final String PRE_AUTH_CODE      = "pre_auth_code";      // 预授权码
	private static final String PERMANENT_CODE     = "permanent_code";     // 预授权码
	private static final String SESSION_INFO       = "session_info";       // 授权过程中需要用到的会话信息
	private static final String ERRCODE            = "errcode";            // 返回码
	private static final String AUTH_CORPID        = "auth_corpid";        // 授权方corpid
	private static final String AGENT              = "agent";              // 授权的应用信息
	private static final String ACCESS_TOKEN       = "access_token";       // 授权方（企业）access_token
	private static final String AGENTID            = "agentid";            // 授权方应用id
	private static final String AUTH_CODE          = "auth_code";          // 授权码

	/**
	 * 获取微信服务器推送suite_ticket
	 * @return suite_ticket
	 * @throws Exception
	 */
	public static String getSuiteTicket() throws Exception {
		Object suiteTicket = CacheUtils.get(SUITE_TICKET);
		return null == suiteTicket ? null : suiteTicket.toString();
	}

	/**
	 * 获取应用套件 access_token
	 * 
	 * @param suiteId 应用套件id
	 * @param suiteSeret 应用套件secret
	 * @return 应用套件 access_token
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@SuppressWarnings("unchecked")
	public static String getSuiteToken(String suiteId, String suiteSeret) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put(SUITE_ID, suiteId);
		params.put(SUITE_SECRET, suiteSeret);
		params.put(SUITE_TICKET, getSuiteTicket());
		String httpResults = HttpKit.post(SUITE_TOKEN_URI, JsonMapper.toJsonString(params));

		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpResults);
		
		return jsonMap.get(SUITE_ACCESS_TOKEN).toString();
	}

	/**
	 * 获取应用套件预授权码
	 * 
	 * @param suiteId 应用套件id
	 * @param appIdList 应用套件ID列表
	 * @param suiteToken 应用套件 access_token
	 * @return 预授权码pre_auth_code
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@SuppressWarnings("unchecked")
	public static String getSuitePreAuthCode(String suiteId, List<String> appIdList, String suiteToken) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SUITE_ID, suiteId);
		params.put(APPID, appIdList);

		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(SUITE_ACCESS_TOKEN, suiteToken);

		String httpResults = HttpKit.post(
				HttpKit.initParams(PRE_AUTH_CODE_URI, urlMap),
				JsonMapper.toJsonString(params)
				);

		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpResults);
		return jsonMap.get(PRE_AUTH_CODE).toString();
	}

	/**
	 * 设置授权配置
	 * 
	 * @param preAuthCode 预授权码
	 * @param appIdList 应用套件ID列表
	 * @param suiteToken 应用套件 access_token
	 * @return 返回码errcode
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@SuppressWarnings("unchecked")
	public static String setSessionInfo(String preAuthCode, List<String> appIdList, String suiteToken) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PRE_AUTH_CODE, preAuthCode);
		Map<String, List<String>> appIdListMap = new HashMap<String, List<String>>();
		appIdListMap.put(APPID, appIdList);
		params.put(SESSION_INFO, appIdListMap);

		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(SUITE_ACCESS_TOKEN, suiteToken);

		String httpResults = HttpKit.post(
				HttpKit.initParams(PRE_AUTH_CODE_URI, urlMap),
				JsonMapper.toJsonString(params)
				);

		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpResults);
		return jsonMap.get(ERRCODE).toString();
	}

	/**
	 * 获取应用套件永久授权码
	 * 
	 * @param suiteId 应用套件id
	 * @param preAuthCode 预授权码
	 * @param suiteToken 应用套件 access_token
	 * @return 永久授权码permanentCode(JSON)
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@SuppressWarnings("unchecked")
	public static String getSuitePermanentCode(String suiteId, String preAuthCode, String suiteToken) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SUITE_ID, suiteId);
		params.put(AUTH_CODE, preAuthCode);

		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(SUITE_ACCESS_TOKEN, suiteToken);

		String httpResults = HttpKit.post(
				HttpKit.initParams(PERMANENT_CODE_URI, urlMap),
				JsonMapper.toJsonString(params)
				);

		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpResults);
		return jsonMap.get(PERMANENT_CODE).toString();
	}

	/**
	 * 获取企业号的授权信息
	 * 
	 * @param suiteId 应用套件id
	 * @param authCorpid 授权方corpid
	 * @param permanentCode 永久授权码
	 * @param suiteToken 应用套件 access_token
	 * @return 授权信息authInfo
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, ?> getAuthInfo(String suiteId, String authCorpid, String permanentCode, String suiteToken) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SUITE_ID, suiteId);
		params.put(AUTH_CORPID, authCorpid);
		params.put(PERMANENT_CODE, permanentCode);

		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(SUITE_ACCESS_TOKEN, suiteToken);

		String httpResults = HttpKit.post(
				HttpKit.initParams(AUTH_INFO_URI, urlMap),
				JsonMapper.toJsonString(params)
				);

		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpResults);
		return jsonMap;
	}

	/**
	 * 获取企业号应用
	 * 
	 * @param suiteId 应用套件id
	 * @param authCorpid 授权方corpid
	 * @param permanentCode 永久授权码
	 * @param agentId 授权方应用id
	 * @param suiteToken 应用套件 access_token
	 * @return 企业号应用信息agent
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, ?> getAgent(String suiteId, String authCorpid, String permanentCode, String agentId, String suiteToken) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SUITE_ID, suiteId);
		params.put(AUTH_CORPID, authCorpid);
		params.put(PERMANENT_CODE, permanentCode);
		params.put(AGENTID, agentId);

		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(SUITE_ACCESS_TOKEN, suiteToken);

		String httpResults = HttpKit.post(
				HttpKit.initParams(GET_AGENT_URI, urlMap),
				JsonMapper.toJsonString(params)
				);

		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpResults);
		return jsonMap;
	}
	
	/**
	 * 设置企业号应用
	 * 
	 * @param suiteId 应用套件id
	 * @param authCorpid 授权方corpid
	 * @param permanentCode 永久授权码
	 * @param agent 授权方应用设置信息
	 * @param suiteToken 应用套件 access_token
	 * @return 返回码errcode
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@SuppressWarnings("unchecked")
	public static String setAgent(String suiteId, String authCorpid, String permanentCode, Map<String, Object> agent, String suiteToken) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SUITE_ID, suiteId);
		params.put(AUTH_CORPID, authCorpid);
		params.put(PERMANENT_CODE, permanentCode);
		params.put(AGENT, agent);

		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(SUITE_ACCESS_TOKEN, suiteToken);

		String httpResults = HttpKit.post(
				HttpKit.initParams(SET_AGENT_URI, urlMap),
				JsonMapper.toJsonString(params)
				);

		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpResults);
		return jsonMap.get(ERRCODE).toString();
	}
	
	/**
	 * 获取企业号access_token
	 * 
	 * @param suiteId 应用套件id
	 * @param authCorpid 授权方corpid
	 * @param permanentCode 永久授权码
	 * @param suiteToken 应用套件 access_token
	 * @return 企业号access_token
	 * 
	 * @throws IOException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@SuppressWarnings("unchecked")
	public static String getCorpToken(String suiteId, String authCorpid, String permanentCode, String suiteToken) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(SUITE_ID, suiteId);
		params.put(AUTH_CORPID, authCorpid);
		params.put(PERMANENT_CODE, permanentCode);

		Map<String, String> urlMap = new HashMap<String, String>();
		urlMap.put(SUITE_ACCESS_TOKEN, suiteToken);

		String httpResults = HttpKit.post(
				HttpKit.initParams(GET_CORP_TOKEN_URI, urlMap),
				JsonMapper.toJsonString(params)
				);

		Map<String, ?> jsonMap = (Map<String, ?>) JSONUtils.parse(httpResults);
		return jsonMap.get(ACCESS_TOKEN).toString();
	}
}
