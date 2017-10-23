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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.nutz.mapl.Mapl;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.wx.bean.Article;
import com.thinkgem.jeesite.common.wx.bean.ArticlesNews;
import com.thinkgem.jeesite.common.wx.bean.WxTemplate;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.common.wx.util.HttpKit;

import eu.bitwalker.useragentutils.ApplicationType;

/**
 * 客服消息接口
 *
 * @author L.cm
 * @date 2013-11-5 下午3:32:30
 * @description 当用户主动发消息给公众号的时候
 * （包括发送信息、点击自定义菜单、订阅事件、扫描二维码事件、支付成功事件、用户维权），
 * 微信将会把消息数据推送给开发者，开发者在一段时间内（目前修改为48小时）可以调用客服消息接口，
 * 通过POST一个JSON数据包来发送消息给普通用户，在48小时内不限制发送次数。
 * 此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务。
 */
public class Message {

    public static final String MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    public static final String UPLOADNEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=";
    public static final String MASS_SENDALL_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=";
    public static final String MASS_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
    public static final String MASS_DELETE_URL = "https://api.weixin.qq.com//cgi-bin/message/mass/delete?access_token=";
    public static final String SEND_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    
    private static final Logger LOGGER = Logger.getLogger(Message.class);
    
    /**
     * 发送客服消息
     * @param accessToken
     * @param message
     * @return
     * @throws Exception
     */
    private String sendMsg(String accessToken, Map<String, Object> message) throws Exception{
		String reslut = HttpKit.post(MESSAGE_URL.concat(accessToken), Json.toJson(message));
		return reslut;
	}
    
    /**
     * 发送文本客服消息
     * @param openId
     * @param text
     * @throws Exception 
     */
    public String sendText(String accessToken,String openId, String text) throws Exception {
        Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("content", text);
        json.put("touser", openId);
        json.put("msgtype", "text");
        json.put("text", textObj);
        String reslut = "";
        try {
        	reslut = sendMsg(accessToken, json);
        } catch (Exception e) {
        	LOGGER.error("文本客服消息发送异常", e);
        }
        return reslut;
    }
    
    /**
     * 发送图片消息
     * @param accessToken
     * @param openId
     * @param media_id
     * @return
     * @throws Exception
     */
    public String SendImage(String accessToken,String openId, String media_id) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("media_id", media_id);
        json.put("touser", openId);
        json.put("msgtype", "image");
        json.put("image", textObj);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送语言回复
     * @param accessToken
     * @param openId
     * @param media_id
     * @return
     * @throws Exception
     */
    public String SendVoice(String accessToken,String openId, String media_id) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("media_id", media_id);
        json.put("touser", openId);
        json.put("msgtype", "voice");
        json.put("voice", textObj);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送视频回复
     * @param accessToken
     * @param openId
     * @param media_id
     * @param title
     * @param description
     * @return
     * @throws Exception
     */
    public String SendVideo(String accessToken,String openId, String media_id,String title,String description) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("media_id", media_id);
        textObj.put("title", title);
        textObj.put("description", description);
        
        json.put("touser", openId);
        json.put("msgtype", "video");
        json.put("video", textObj);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送音乐回复
     * @param accessToken
     * @param openId
     * @param musicurl
     * @param hqmusicurl
     * @param thumb_media_id
     * @param title
     * @param description
     * @return
     * @throws Exception
     */
    public String SendMusic(String accessToken,String openId, String musicurl,String hqmusicurl,String thumb_media_id,String title,String description) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("musicurl", musicurl);
        textObj.put("hqmusicurl", hqmusicurl);
        textObj.put("thumb_media_id", thumb_media_id);
        textObj.put("title", title);
        textObj.put("description", description);
        
        json.put("touser", openId);
        json.put("msgtype", "music");
        json.put("music", textObj);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送图文回复
     * @param accessToken
     * @param openId
     * @param articles
     * @return
     * @throws Exception
     */
    public String SendNews(String accessToken,String openId, List<ArticlesNews> articles) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        json.put("touser", openId);
        json.put("msgtype", "news");
        Mapl.put(json, "news.articles", articles);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送一条图文回复
     * @param accessToken
     * @param openId
     * @param content
     * @return
     * @throws Exception
     */
    public String SendNews(String accessToken,String openId, String title, String description, String domainId) throws Exception{
    	
    	ArticlesNews articles = new ArticlesNews();
		articles.setTitle(title);
		articles.setDescription(description);
		if (StringUtils.isNotBlank(domainId)) {
			articles.setUrl(ConfKit.get("root_uri") + "/domainname/singleDomainname?singleDomainId=" + domainId);
		}
		if ("审核失败".equals(domainId)) {
			//审核失败的跳转
			articles.setUrl(ConfKit.get("root_uri") + "/domainname/isell");
		}
    	
		List<ArticlesNews> artList = new ArrayList<ArticlesNews>();
		artList.add(articles);
		String result = "";
		try {
			result = SendNews(accessToken, openId, artList);
			if (result != null) {
				if (!result.contains("ok")) {
					LOGGER.error("客服图文消息发送异常:" + result);
				}
			}
		} catch (Exception e) {
			LOGGER.error("客服图文消息发送异常", e);
		}
        return result;
    }
    
    /**
     * 上传图文消息素材
     * @param accessToken
     * @param articles
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public Object uploadnews(String accessToken,List<Article> articles) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
    	Map<String,Object> json = new HashMap<String,Object>();
    	json.put("articles", articles);
    	String reslut = HttpKit.post(UPLOADNEWS_URL.concat(accessToken), Json.toJson(json));
    	if (StringUtils.isNotEmpty(reslut)) {
			return Json.fromJson(reslut);
		}
		return null;
    }
    
    /**
     * 根据分组进行群发
     * @param accessToken
     * @param groupId
     * @param mpNewsMediaId
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public Object massSendall(String accessToken,String groupId,String mpNewsMediaId) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
    	Map<String,Object> json = new HashMap<String,Object>();
    	Map<String,Object> filter = new HashMap<String,Object>();
    	Map<String,Object> mpnews = new HashMap<String,Object>();
    	if(StringUtils.isBlank(groupId)){
    		filter.put("is_to_all", true);
    	}else{
    		filter.put("is_to_all", false);
    		filter.put("group_id", groupId);
    	}

    	mpnews.put("media_id", mpNewsMediaId);
    	
    	json.put("mpnews", mpnews);
    	json.put("filter", filter);
    	json.put("msgtype", "mpnews");
    	String reslut = HttpKit.post(MASS_SENDALL_URL.concat(accessToken), Json.toJson(json));
    	if (StringUtils.isNotEmpty(reslut)) {
			return Json.fromJson(reslut);
		}
		return null;
    }
    
    /**
     * 根据OpenID列表群发
     * @param accessToken
     * @param openids
     * @param mpNewsMediaId
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public Object massSend(String accessToken,String[] openids,String mpNewsMediaId) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
    	Map<String,Object> json = new HashMap<String,Object>();
    	Map<String,Object> mpnews = new HashMap<String,Object>();
    	mpnews.put("media_id", mpNewsMediaId);
    	json.put("touser", openids);
    	json.put("mpnews", mpnews);
    	json.put("msgtype", "mpnews");
    	String reslut = HttpKit.post(MASS_SEND_URL.concat(accessToken), Json.toJson(json));
    	if (StringUtils.isNotEmpty(reslut)) {
			return Json.fromJson(reslut);
		}
		return null;
    }
    
    /**
     * 删除群发
     * @param accessToken
     * @param msgid
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public Object massSend(String accessToken,String msgid) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException{
    	Map<String,Object> json = new HashMap<String,Object>();
    	json.put("msgid", msgid);
    	String reslut = HttpKit.post(MASS_DELETE_URL.concat(accessToken), Json.toJson(json));
    	if (StringUtils.isNotEmpty(reslut)) {
			return Json.fromJson(reslut);
		}
		return null;
    }
    
    public static Object sendTemplate(String accessToken, WxTemplate t) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {  
    	
    	String reslut = HttpKit.post(SEND_TEMPLATE.concat(accessToken), Json.toJson(t));
    	if (StringUtils.isNotEmpty(reslut)) {
			return Json.fromJson(reslut);
		}
		return null; 
    }
    
    /**
     * 异步微信消息发送
     * 
     * @param type 客户端类型
     * @param t 模版
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public static void sendTemplateAsync(ApplicationType type, WxTemplate t) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {  
//    	WeiXinSenderThreadPlugin.addWeiXinTask(type, t);
    }
}
