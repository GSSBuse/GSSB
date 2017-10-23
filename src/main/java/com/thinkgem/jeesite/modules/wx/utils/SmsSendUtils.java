package com.thinkgem.jeesite.modules.wx.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.PropertiesLoader;
import com.thinkgem.jeesite.common.wx.util.HttpKit;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyFinanceDao;


/**
 * 聚合短信发送工具类
 * 
 * @author Wu Fulin
 * @date 2015-11-02
 */
public class SmsSendUtils {
	
	Logger logger = Logger.getLogger(getClass());
	private static PropertiesLoader loader = new PropertiesLoader("sms.properties");
	//存储配置文件中的信息，避免重复读取配置文件
	private static final Map<String, String> smsConf = new HashMap<String, String>();

	//测试短信发送
	public static void main(String[] args) throws SmsSendException {
//		Map<String, String> tplValueMap = new HashMap<String, String>();
//		String code = IdGen.uuid().substring(0, 6);//6位随机验证码
//		tplValueMap.put("code", code);
//		SmsSendUtils.sendSms(Long.parseLong("15951726542"), Constant.TPL_ID_1, tplValueMap);
//		UUID uuid = UUID.randomUUID(); 
//        String guidStr = uuid.toString(); 
//        int num = (int) Math.round(Math.random()*1000000-1); 
//        System.out.println(guidStr);

	}
	 /**
		 * 发送短信
		 * @param mobile 接收短信的手机号
		 * @param tpl_id短信模板id
		 * @param  tplValueMap 存放模板参数（短信模板中的参数：#code#=1234&#name#=wufulin）的map
		 * @return
		 */
	public static void sendSms(long mobile, String tpl_id, Map<String, String> tplValueMap) throws SmsSendException {
		//从配置文件读取接口地址
		String url = null;
		if (!smsConf.containsKey("juhe_sms_send_url")) {
			smsConf.put("juhe_sms_send_url", loader.getProperty("juhe_sms_send_url"));
			url = smsConf.get("juhe_sms_send_url");
		}else{
			url = smsConf.get("juhe_sms_send_url");
		}
		//从配置文件读取应用APPKEY
		String APPKEY = null;
		if (!smsConf.containsKey("juhe_sms_send_key")) {
			smsConf.put("juhe_sms_send_key", loader.getProperty("juhe_sms_send_key"));
			APPKEY = smsConf.get("juhe_sms_send_key");
		}else{
			APPKEY = smsConf.get("juhe_sms_send_key");
		}
		//模板参数的拼接
		String tpl_value = "";
		Set<String> keys = tplValueMap.keySet();
		for (String key : keys) {
			String value = tplValueMap.get(key);
			if (!"".equals(tpl_value)) {
				tpl_value += "&";
			}
			tpl_value += "#" + key + "#=" + value;
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("key", APPKEY);
		params.put("dtype", "json");
		params.put("mobile", mobile + "");
		params.put("tpl_id", tpl_id + "");
		params.put("tpl_value", tpl_value);

		doSendSms(url, params, 3);
	}
	/**
	 * 发送短信,失败则重发，最多重发N次
	 * @param url 发送短信接口地址
	 * @param params 存储参数的map
	 * @param  reSendCount 发送失败时重发的最大的次数
	 * @return
	 */
	private static void doSendSms(String url, Map<String, String> params, int reSendCount) throws SmsSendException {
		String json = null;
		try {
			//待检ff1
			json = HttpKit.get(url, params);
		} catch (Exception e) {
			throw new SmsSendException("调用聚合短信接口出错：" + e.getMessage());
		}

		@SuppressWarnings("unchecked")
		Map<String, ?> jsonObj = (Map<String, ?>)Json.fromJson(json);

		Object error_code = jsonObj.get("error_code");
		if ("0".equals(error_code.toString()) || "".equals(error_code.toString()) || "null".equals(error_code.toString())) {
			// 短信发送成功
		} else {
			reSendCount--;
			if (reSendCount > 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				// 重试
				doSendSms(url, params, reSendCount);
			}

			Object reason = jsonObj.get("reason");
			throw new SmsSendException(reason.toString());
		}
	}

	public static class SmsSendException extends Exception {

		private static final long serialVersionUID = 8104043426891697309L;

		private String reason;

		public SmsSendException(String reason) {
			super(reason);
			this.reason = reason;
		}

		public SmsSendException(String reason, Exception e) {
			super(reason, e);
			this.reason = reason;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}
	}
}