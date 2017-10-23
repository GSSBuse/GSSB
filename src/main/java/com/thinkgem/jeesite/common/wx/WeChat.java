/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013  , MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.thinkgem.jeesite.common.wx;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.nutz.mapl.Mapl;

import com.thinkgem.jeesite.common.utils.ExpireCacheDataProducer;
import com.thinkgem.jeesite.common.utils.ExpireDataUtil;
import com.thinkgem.jeesite.common.utils.Reflections;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.exception.ProduceException;
import com.thinkgem.jeesite.common.wx.aes.SHA1;
import com.thinkgem.jeesite.common.wx.bean.Articles;
import com.thinkgem.jeesite.common.wx.bean.Attachment;
import com.thinkgem.jeesite.common.wx.bean.InMessage;
import com.thinkgem.jeesite.common.wx.bean.OutMessage;
import com.thinkgem.jeesite.common.wx.bean.PayResult;
import com.thinkgem.jeesite.common.wx.bean.UnifiedOrder;
import com.thinkgem.jeesite.common.wx.bean.WxRedpack;
import com.thinkgem.jeesite.common.wx.bean.WxReturnUnifiedOrder;
import com.thinkgem.jeesite.common.wx.bean.WxReturnWxRedpackResult;
import com.thinkgem.jeesite.common.wx.inf.MessageProcessingHandler;
import com.thinkgem.jeesite.common.wx.oauth.Group;
import com.thinkgem.jeesite.common.wx.oauth.Menu;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.common.wx.oauth.Pay;
import com.thinkgem.jeesite.common.wx.oauth.Qrcod;
import com.thinkgem.jeesite.common.wx.oauth.User;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.common.wx.util.HttpKit;
import com.thinkgem.jeesite.common.wx.util.Tools;
import com.thinkgem.jeesite.common.wx.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 微信常用的API
 *
 * @author L.cm & 
 * @date 2013-11-5 下午3:01:20
 */
public class WeChat {
	private static final Logger LOGGER = Logger.getLogger(WeChat.class);
	
	private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	private static final String JSAPITICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi";
	private static final String PAYFEEDBACK_URL = "https://api.weixin.qq.com/payfeedback/update";
	private static final String DEFAULT_HANDLER = "com.gson.inf.DefaultMessageProcessingHandlerImpl";
	private static final String GET_MEDIA_URL= "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=";
	private static final String UPLOAD_MEDIA_URL= "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	private static final String KF_GET_LIST = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=";
	private static final String KF_GET_ONLINE_LIST = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=";
	private static final String KF_ADD_ACCOUNT = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=";
	private static final String KF_UPDATE_ACCOUNT = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";
	private static final String KF_UPLOAD_ACCOUNT_IMG = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=";
	private static final String KF_DEL_ACCOUNT = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=";
	private static final String QRCODE_TICKET = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
	private static final String CONVERT_TO_SHORT_URL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=";
	private static final String UNIFIEDORDER_URL= "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单接口
	private static final String SEND_REDPACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";//发送红包接口
    private static Class<?>  messageProcessingHandlerClazz = null;
    /**
     * 消息操作接口
     */
    public static final Message message = new Message();
    /**
     * 菜单操作接口
     */
    public static final Menu menu = new Menu();
    /**
     * 用户操作接口
     */
    public static final User user = new User();
    /**
     * 分组操作接口
     */
    public static final Group group = new Group();
    
    /**
     * 二维码操作接口
     */
    public static final Qrcod qrcod = new Qrcod();
    
	
    /**生成支付、红包唯一订单号
     * @param type 类型：红包为redpack,支付为pay
     * @return 订单号
     */
    
    public static String tradeNumber(String type){
    	String number = null;
    	Date date = new Date();
    	number = type+new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    	return number;
    }
    /**
	 *dengyu
	 * 统一下单 获取 prepay_id，用于前台的页面的支付
	 * @param unifiedOrderParameters 统一下单接口参数对象
	 * @param key APP密钥
	 * @return WxReturnUnifiedOrder 返回订单信息实体
	 */
	public static WxReturnUnifiedOrder Order(UnifiedOrder unifiedOrderParameters,String key) throws Exception{
		WxReturnUnifiedOrder wxReturnUnifiedOrder = null;		
		try {
			//生成统一下单的签名
			String sign = Pay.createUnifiedOrderSign(unifiedOrderParameters,key);
			unifiedOrderParameters.setSign(sign);
			LOGGER.debug("sign:"+sign);
//			//获取需要的参数
//			Map<String, String> map = new HashMap<String, String>();
//			for (Field field : UnifiedOrder.class.getDeclaredFields()) {
//				if (Reflections.getFieldValue(unifiedOrderParameters,field.getName()) != null) {
//					map.put(field.getName(),Reflections.getFieldValue(unifiedOrderParameters,field.getName()).toString());
//				}
//			}
			
			// 把发送参数转换为xml输出
			XStream xs = new XStream(new XppDriver(new NoNameCoder()));//XStreamFactory.init(false)会插入下划线，如a_b会变成a__b;
			xs.alias("xml", unifiedOrderParameters.getClass());
			String xml = xs.toXML(unifiedOrderParameters);
			
			//发送统一下单请求
			String returnXml = HttpKit.post(UNIFIEDORDER_URL, xml);

			// 转换微信post过来的xml内容
			XStream xs1 = XStreamFactory.init(false);
			xs1.ignoreUnknownElements();
			xs1.alias("xml", WxReturnUnifiedOrder.class);
			wxReturnUnifiedOrder = (WxReturnUnifiedOrder) xs1.fromXML(returnXml);
			
			return wxReturnUnifiedOrder;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 *dengyu
	 * 发红包
	 * @param wxRedpackParameters 发红包接口参数对象
	 * @param password 证书密码
	 * @param keyStorePath 证书存储路径
	 * @param key APP密钥
	 * @return WxReturnUnifiedOrder 返回订单信息实体
	 */
	public static WxReturnWxRedpackResult sendWxRedpack(WxRedpack wxRedpackParameters,String password, String keyStorePath,String key) throws Exception{
		WxReturnWxRedpackResult wxReturnWxRedpackResult = null;		
		try {
			//生成发送红包的的签名
			String sign = Pay.createWxRedpackSign(wxRedpackParameters, key);
			wxRedpackParameters.setSign(sign);
			
			// 把发送参数转换为xml输出
			XStream xs = new XStream(new XppDriver(new NoNameCoder()));//XStreamFactory.init(false);
			xs.alias("xml", wxRedpackParameters.getClass());
			String xml = xs.toXML(wxRedpackParameters);
			//
			//发送统一下单请求，此方法待检
			String returnXml = HttpKit.post(SEND_REDPACK_URL, password, keyStorePath, xml);
			
			// 转换微信post过来的xml内容
			XStream xs1 = XStreamFactory.init(false);
			xs1.ignoreUnknownElements();
			xs1.alias("xml", WxReturnWxRedpackResult.class);
			wxReturnWxRedpackResult = (WxReturnWxRedpackResult) xs1.fromXML(returnXml);
			
			return wxReturnWxRedpackResult;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**dengyu
	 * 微信支付通知， 获取支付结果信息
	 * @return PayResult 微信返回的结果
	 */
	public static PayResult payResult( HttpServletRequest req) throws Exception{
		PayResult payResult = null;
		try {
			// 读取微信post过来的数据
			InputStream inputStream = req.getInputStream();
			String postData = IOUtils.toString(inputStream);
			
			//将xml数据转化为实体对象
			XStream xs = XStreamFactory.init(false);
			xs.ignoreUnknownElements();
			xs.alias("xml", PayResult.class);
			payResult = (PayResult) xs.fromXML(postData);
			
			return payResult;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
    
    /**
     * 获取access_token
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception {
		String token = ExpireDataUtil.get("ATK_C", new ExpireCacheDataProducer<String>() {

			// 凭证有效时间，单位：秒
			private long expires_in = 3600;

			@Override
			public String produce(Object key) throws ProduceException {
				try {
					String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + ConfKit.get("AppId") + "&secret=" + ConfKit.get("AppSecret"));
					Object json = Json.fromJson(jsonStr);
					String access_token = Mapl.cell(json, "access_token").toString();
					// 凭证有效时间，单位：秒
					String expires_in = Mapl.cell(json, "expires_in") != null ? Mapl.cell(json, "expires_in").toString() : null;
					if (StringUtils.isNumeric(expires_in)) {
						// 有效时间减去10分钟
						this.expires_in = Long.parseLong(expires_in) - 600;
					}
					return access_token;
				} catch (Exception e) {
					throw new ProduceException(e);
				}
			}

			@Override
			public long getExpirePeriod() {
				return (long) (this.expires_in * 1000);
			}

		});
		LOGGER.info("当前的access_token：" + token);
		return token;
    }
    
    /**
     * 获取jsapi_ticket,用于生成签名，调用js接口
     * @return ticket
     * @throws Exception
     */
    public static String getJsapiTicket() throws Exception {
		String jsapiTicket = ExpireDataUtil.get("JS_API_TICKET", new ExpireCacheDataProducer<String>() {

			// 凭证有效时间，单位：秒
			private long expires_in = 3600;

			@Override
			public String produce(Object key) throws ProduceException {
				try {
					String jsonStr = HttpKit.get(JSAPITICKET_URL.concat("&access_token=") + getAccessToken());
					Object json = Json.fromJson(jsonStr);
					String access_token = Mapl.cell(json, "ticket").toString();
					// 凭证有效时间，单位：秒
					String expires_in = Mapl.cell(json, "expires_in") != null ? Mapl.cell(json, "expires_in").toString() : null;
					if (StringUtils.isNumeric(expires_in)) {
						// 有效时间减去10分钟
						this.expires_in = Long.parseLong(expires_in) - 600;
					}
					return access_token;
				} catch (Exception e) {
					throw new ProduceException(e);
				}
			}

			@Override
			public long getExpirePeriod() {
				return (long) (this.expires_in * 1000);
			}

		});
		LOGGER.info("当前的ticket：" + jsapiTicket);
		return jsapiTicket;
    }
   
    
    /**
	 * 分享：获取 签名
	 * @param nonceStr（随机字符串）
	 * @param timestamp（时间戳）
	 * @param  url（当前网页的URL，不包含#及其后面部分）
	 * @param 有效的jsapi_ticket
	 * @return
	 */
	public static String signature(String noncestr, String timestamp,String url ,String jsapi_ticket) throws Exception{
		try{
			String signature = SHA1.getDengYuSHA1(noncestr, jsapi_ticket, timestamp, url);
			return signature;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}
   /**
    * 支付反馈
    * @param openid
    * @param feedbackid
    * @return
    * @throws Exception
    */
    public static boolean payfeedback(String appid, String secret, String openid, String feedbackid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        map.put("openid", openid);
        map.put("feedbackid", feedbackid);
        String jsonStr = HttpKit.get(PAYFEEDBACK_URL, map);
        Object json = Json.fromJson(jsonStr);
        return "0".equals(Mapl.cell(json, "errcode").toString());
    }

    /**
     * 签名检查
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static Boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        return Tools.checkSignature(token, signature, timestamp, nonce);
    }

    /**
     * 根据接收到用户消息进行处理
     * @param responseInputString   微信发送过来的xml消息体
     * @return
     */
    public static String processing(String responseInputString) {
        InMessage inMessage = parsingInMessage(responseInputString);
        OutMessage oms = null;
        // 加载处理器
        if (messageProcessingHandlerClazz == null) {
            // 获取自定消息处理器，如果自定义处理器则使用默认处理器。
            String handler = ConfKit.get("MessageProcessingHandlerImpl");
            handler = handler == null ? DEFAULT_HANDLER : handler;
            try {
            	messageProcessingHandlerClazz = Thread.currentThread().getContextClassLoader().loadClass(handler);
            } catch (Exception e) {
                throw new RuntimeException("messageProcessingHandler Load Error！");
            }
        }
        String xml = "";
        try {
        	MessageProcessingHandler messageProcessingHandler = (MessageProcessingHandler) messageProcessingHandlerClazz.newInstance();
            //取得消息类型
            String type = inMessage.getMsgType();
            Method getOutMessage = messageProcessingHandler.getClass().getMethod("getOutMessage");
            Method alMt = messageProcessingHandler.getClass().getMethod("allType", InMessage.class);
            Method mt = messageProcessingHandler.getClass().getMethod(type + "TypeMsg", InMessage.class);
            
            alMt.invoke(messageProcessingHandler, inMessage);
           
            if(mt != null){
            	mt.invoke(messageProcessingHandler, inMessage);
            }
            
            Object obj = getOutMessage.invoke(messageProcessingHandler);
            if(obj != null){
            	oms = (OutMessage) obj;
            }
            //调用事后处理
            try {
            	Method aftMt =  messageProcessingHandler.getClass().getMethod("afterProcess",InMessage.class,OutMessage.class);
            	aftMt.invoke(messageProcessingHandler, inMessage, oms);
			} catch (Exception e) {}
            
            obj = getOutMessage.invoke(messageProcessingHandler);
            if(obj != null){
            	oms = (OutMessage) obj;
            	setMsgInfo(oms, inMessage);
            }
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
        if(oms != null){
            // 把发送发送对象转换为xml输出
            XStream xs = XStreamFactory.init(true);
            xs.alias("xml", oms.getClass());
            xs.alias("item", Articles.class);
            xml = xs.toXML(oms);
        }
        return xml;
    }

    /**
     * 设置发送消息体
     * @param oms
     * @param msg
     * @throws Exception
     */
    private static void setMsgInfo(OutMessage oms,InMessage msg) throws Exception {
    	if(oms != null){
    		Class<?> outMsg = oms.getClass().getSuperclass();
            Field CreateTime = outMsg.getDeclaredField("CreateTime");
            Field ToUserName = outMsg.getDeclaredField("ToUserName");
            Field FromUserName = outMsg.getDeclaredField("FromUserName");

            ToUserName.setAccessible(true);
            CreateTime.setAccessible(true);
            FromUserName.setAccessible(true);

            CreateTime.set(oms, new Date().getTime());
            ToUserName.set(oms, msg.getFromUserName());
            FromUserName.set(oms, msg.getToUserName());
    	}
    }

    /**
     *消息体转换
     * @param responseInputString
     * @return
     */
    private static InMessage parsingInMessage(String responseInputString) {
        //转换微信post过来的xml内容
        XStream xs = XStreamFactory.init(false);
        xs.ignoreUnknownElements();
        xs.alias("xml", InMessage.class);
        InMessage msg = (InMessage) xs.fromXML(responseInputString);
        msg.setRawData(responseInputString);
        return msg;
    }
    
    /**
     * 获取媒体资源
     * @param accessToken
     * @param mediaId
     * @return
     * @throws IOException
     */
    public static Attachment getMedia(String accessToken,String mediaId) throws IOException{
    	String url = GET_MEDIA_URL + accessToken + "&media_id=" + mediaId;
        return HttpKit.download(url);
    }
    
    /**
     * 上传素材文件
     * @param type
     * @param file
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> uploadMedia(String accessToken,String type,File file) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String url = UPLOAD_MEDIA_URL + accessToken +"&type="+type;
        String jsonStr = HttpKit.upload(url,file);
        return Json.fromJson(Map.class, jsonStr);
    }
    
    /**
     * 判断是否来自微信, 5.0 之后的支持微信支付
     * @param request
     * @return
     */
 	public static boolean isWeiXin(HttpServletRequest request) {
 		String userAgent = request.getHeader("User-Agent");
 		if (StringUtils.isNotBlank(userAgent)) {
 			Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
 			Matcher m = p.matcher(userAgent);
 			String version = null;
 			if(m.find()){
 				version = m.group(1);
 			}
 			return (null != version && NumberUtils.toInt(version) >= 5); 
 		}
 		
 		return false;
 	}
 	
 	/**
     * 判断是否Ajax请求
     * @param request
     * @return
     */
 	public static boolean isAjax(HttpServletRequest request) {
 		String requestType = request.getHeader("X-Requested-With"); 
 		if (StringUtils.equalsIgnoreCase(requestType, "XMLHttpRequest")) {
 			return true;
 		}
 		
 		return false;
 	}
 	
 	/**
 	 * 获取所有客服基本信息
 	 * 
 	 * @param token
 	 * @return Json对象。使用{@link Mapl}进行内容提取<br>
 	 * 返回数据示例（正确时的JSON返回结果）：<br>
 	 * <pre>
{
   "kf_list": [
   {
       "kf_account": "test1@test", 
       "kf_nick": "ntest1", 
       "kf_id": "1001"
       "kf_headimgurl": "http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0"
   }
}
    </pre>
 	 * @throws Exception 
 	 */
 	public static Object getKfList(String token) throws  Exception {
 		String json = HttpKit.get(KF_GET_LIST + token);
 		
 		return Json.fromJson(json);
 	}
 	
 	/**
 	 * 获取所有在线客服基本信息
 	 * 
 	 * @param token
 	 * @return Json对象。使用{@link Mapl}进行内容提取<br>
 	 * 返回数据示例（正确时的JSON返回结果）：<br>
 	 * <pre>
{
   "kf_online_list": [
       {
           "kf_account": "test1@test", 
           "status": 1, 
           "kf_id": "1001", 
           "auto_accept": 0, 
           "accepted_case": 1
       },
       {
           "kf_account": "test2@test", 
           "status": 1, 
           "kf_id": "1002", 
           "auto_accept": 0, 
           "accepted_case": 2
       }
   ]
}
    </pre>
 	 * @throws Exception 
 	 */
 	public static Object getKfOnlineList(String token) throws  Exception {
 		String json = HttpKit.get(KF_GET_ONLINE_LIST + token);
 		return Json.fromJson(json);
 	}
 	
 	/**
 	 * 添加客服账号
 	 * 
 	 * @param token 
 	 * @param account 完整客服账号，格式为：账号前缀@公众号微信号
 	 * @param nickname 客服昵称，最长6个汉字或12个英文字符
 	 * @param pwdMd5 客服账号登录密码，格式为密码明文的32位加密MD5值
 	 * @return Json对象。使用{@link Mapl}进行内容提取<br>
 	 * 返回数据示例（正确时的JSON返回结果）：<br>
 	 * <pre>
{
    "errcode" : 0,
    "errmsg" : "ok",
}
    </pre>
 	 * @throws Exception 
 	 */
 	public static Object addKfAccount(String token, String account, String nickname, String pwdMd5) throws Exception {
 		Map<String, String> params = new HashMap<String, String>();
 		params.put("kf_account", account);
 		params.put("nickname", nickname);
 		params.put("password", pwdMd5);
 		String data = Json.toJson(params);
 		String json = HttpKit.post(KF_ADD_ACCOUNT + token, data);
 		return Json.fromJson(json);
 	}
 	
 	/**
 	 * 更新客服账号
 	 * 
 	 * @param token 
 	 * @param account 完整客服账号，格式为：账号前缀@公众号微信号
 	 * @param nickname 客服昵称，最长6个汉字或12个英文字符
 	 * @param pwdMd5 客服账号登录密码，格式为密码明文的32位加密MD5值
 	 * @return Json对象。使用{@link Mapl}进行内容提取<br>
 	 * 返回数据示例（正确时的JSON返回结果）：<br>
 	 * <pre>
{
    "errcode" : 0,
    "errmsg" : "ok",
}
    </pre>
 	 * @throws Exception 
 	 */
 	public static Object updateKfAccount(String token, String account, String nickname, String pwdMd5) throws Exception {
 		Map<String, String> params = new HashMap<String, String>();
 		params.put("kf_account", account);
 		params.put("nickname", nickname);
 		params.put("password", pwdMd5);
 		String data = Json.toJson(params);
 		String json = HttpKit.post(KF_UPDATE_ACCOUNT + token, data);
 		return Json.fromJson(json);
 	}
 	
 	/**
 	 * 添加客服账号头像
 	 * 
 	 * @param token 
 	 * @param account 完整客服账号，格式为：账号前缀@公众号微信号
 	 * @param imgPath 头像图片文件地址(必须是jpg格式)
 	 * @return Json对象。使用{@link Mapl}进行内容提取<br>
 	 * 返回数据示例（正确时的JSON返回结果）：<br>
 	 * <pre>
{
    "errcode" : 0,
    "errmsg" : "ok",
}
    </pre>
 	 * @throws Exception 
 	 */
 	public static Object uploadKfHeadimg(String token, String account, String imgPath) throws Exception {
 		String json = HttpKit.upload(KF_UPLOAD_ACCOUNT_IMG + token + "&kf_account=" + account, new File(imgPath));
 		return Json.fromJson(json);
 	}
 	
 	/**
 	 * 删除客服账号
 	 * 
 	 * @param token
 	 * @param account 完整客服账号，格式为：账号前缀@公众号微信号
 	 * @return Json对象。使用{@link Mapl}进行内容提取<br>
 	 * 返回数据示例（正确时的JSON返回结果）：<br>
 	 * <pre>
{
    "errcode" : 0,
    "errmsg" : "ok",
}
    </pre>
 	 * @throws Exception
 	 */
 	public static Object removeKfAccount(String token, String account) throws Exception {
 		String json = HttpKit.get(KF_DEL_ACCOUNT + token + "&kf_account=" + account);
 		return Json.fromJson(json);
 	}
 	
 	/**
 	 * 
 	 * 
 	 * @param token
 	 * @param sid
 	 * @return
 	 * @throws Exception
 	 */
 	public static String getQrcodeTicket(String token, int sid, int expiredSecond) throws Exception {
 		Object data = Json.fromJson("{'action_name': 'QR_SCENE'}");
 		Mapl.put(data, "action_info.scene.scene_id", sid);
 		Mapl.put(data, "expire_seconds", expiredSecond);
 		
		String content = HttpKit.post(QRCODE_TICKET+token, Json.toJson(data));
		Object json = Json.fromJson(content);
		
		if (Mapl.cell(json, "errcode") != null && !"0".equals(Mapl.cell(json, "errcode").toString())) {
			throw new RuntimeException("微信请求错误 : " + content);
		} else {
			return (String)Mapl.cell(json, "ticket");
		}
			
 	}
 	
 	/**
 	 * 微信长链接转换为短链接
 	 * 
 	 * @param token
 	 * @param longUrl
 	 * @return
 	 * @throws Exception
 	 */
 	public static String toShortUrl(String token, String longUrl) throws Exception {
 		Object data = Json.fromJson("{'action': 'long2short'}");
 		Mapl.put(data, "long_url", longUrl);
 		
 		String content = HttpKit.post(CONVERT_TO_SHORT_URL+token, Json.toJson(data));
 		
 		Object json = Json.fromJson(content);
		
		if (Mapl.cell(json, "errcode") != null && !"0".equals(Mapl.cell(json, "errcode").toString())) {
			throw new RuntimeException("微信请求错误 : " + content);
		} else {
			return (String)Mapl.cell(json, "short_url");
		}
		
 	}
 	
 	public static void main(String args[]) throws Exception {
 		boolean h = menu.createMenuDemo();
 		if(h){
 			System.out.println("创建成功");
 		}else {
 			System.out.println("创建失败");
		}
// 		WeChat wechat = new WeChat();
// 		
// 		String accessToken = WeChat.getAccessToken();
// 		System.out.println(accessToken);
// 		
// 		String url = WeChat.toShortUrl(accessToken, "http://mp.weixin.qq.com/wiki/10/165c9b15eddcfbd8699ac12b0bd89ae6.html");
// 		
// 		System.out.println(url);
 		
// 		WxTemplate t = new WxTemplate();
//		t.setUrl("");
//		t.setTouser("o6cyhjmz-Wcvq_f3O4iYfpJPOGnc");
//		//t.setTouser(openid);
//		t.setTopcolor("#000000");
//		t.setTemplate_id("jMIN7uh7THTzq4xgS_a6LaThHdnXPs2hyG1O8pbySlw");
//
//		Map<String, TemplateData> m = new HashMap<String, TemplateData>();
//
//		TemplateData first = new TemplateData();
//		first.setColor("#000000");
//		first.setValue("您好，您的“保修工单”申请小唯已经收到，正抓紧为您解决，请耐心等待:)");
//		m.put("first", first);
//
//		TemplateData orderId = new TemplateData();
//		orderId.setColor("#000000");
//		orderId.setValue("testtest");
//		m.put("Apply_id", orderId);
//
//		TemplateData orderType = new TemplateData();
//		orderType.setColor("#000000");
//		orderType.setValue("testtest");
//		m.put("Apply_Type", orderType);
//
//		TemplateData orderContent = new TemplateData();
//		orderContent.setColor("#000000");
//		orderContent.setValue("工单已提交，待处理");
//		m.put("Apply_State", orderContent);
//
//		TemplateData date = new TemplateData();
//		date.setColor("#000000");
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
//		date.setValue(df.format(new Date()));
//		m.put("Apply_CreateTime", date);
//
//		TemplateData remark = new TemplateData();
//		remark.setColor("#0000FF");
//		remark.setValue("点击“详情”查看服务工单详细信息，如有疑问请致电400-828-1878或直接在微信留言，小唯将第一时间为您服务！");
//		m.put("remark", remark);
//
//		t.setData(m);
//
// 		System.out.println(message.sendTemplate(accessToken, t));
	
 	}
}
