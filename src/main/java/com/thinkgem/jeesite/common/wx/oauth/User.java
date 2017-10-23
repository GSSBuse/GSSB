/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2014  , MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.thinkgem.jeesite.common.wx.oauth;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.nutz.mapl.Mapl;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.bean.UserInfo;
import com.thinkgem.jeesite.common.wx.util.HttpKit;


/**
 * 用户操作接口
 * @author 
 */
public class User {
	private static Logger log=Logger.getLogger(User.class);
	private static final String USER_INFO_URI = "https://api.weixin.qq.com/cgi-bin/user/info";
	private static final String SNS_USER_INFO_URI = "https://api.weixin.qq.com/sns/userinfo";
	private static final String USER_GET_URI = "https://api.weixin.qq.com/cgi-bin/user/get";

	/**
	 * 拉取用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static UserInfo getUserInfoSns(String accessToken, String openid) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openid);
		params.put("lang", "zh_CN");
		String  jsonStr = HttpKit.get(SNS_USER_INFO_URI, params);
		if(StringUtils.isNotEmpty(jsonStr)){
			Object obj = Json.fromJson(jsonStr);
			if(Mapl.cell(obj,"errcode") != null){
				System.out.println("openid=" + openid);
				
				throw new Exception((String)Mapl.cell(obj,"errmsg"));

				//return new UserInfo();
			} else {
				System.out.println(jsonStr);
			}
			UserInfo user = Mapl.maplistToT(obj, UserInfo.class);
			return user;
		}
		return new UserInfo();
	}
	
	/**
	 * 拉取用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static UserInfo getUserInfo(String accessToken, String openid) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("openid", openid);
		String  jsonStr = HttpKit.get(USER_INFO_URI, params);
		if(StringUtils.isNotEmpty(jsonStr)){
			Object obj = Json.fromJson(jsonStr);
			if(Mapl.cell(obj,"errcode") != null){
				System.out.println("openid=" + openid);
				
				throw new Exception((String)Mapl.cell(obj,"errmsg"));

				//return new UserInfo();
			} else {
				System.out.println(jsonStr);
			}
			UserInfo user = Mapl.maplistToT(obj, UserInfo.class);
			return user;
		}
		return new UserInfo();
	}
	
	/**
	 * 获取帐号的关注者列表
	 * @param accessToken
	 * @param next_openid
	 * @return
	 */
	public static Object getFollwersList(String accessToken, String next_openid) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("next_openid", next_openid);
		String  jsonStr = HttpKit.get(USER_GET_URI, params);
		log.debug("获取关注者列表:\n"+jsonStr);
		if(StringUtils.isNotEmpty(jsonStr)){
			Map<String, Object> obj = Json.fromJson(Map.class, jsonStr);
			if(obj.get("errcode") != null){
				throw new Exception(obj.get("errmsg").toString());
			}
			return obj;
		}
		return null;
	}

    public static void main(String args[]) throws Exception{
    	WeChat wechat = new WeChat();
    	User user = new User();
 		String accessToken = wechat.getAccessToken();
 		System.out.println(accessToken);
    	UserInfo userinfo = user.getUserInfo(wechat.getAccessToken(), "o6cyhjmz-Wcvq_f3O4iYfpJPOGnc");
    	System.out.println(userinfo.getNickname());
    	System.out.println(userinfo.getHeadimgurl());
    }
}