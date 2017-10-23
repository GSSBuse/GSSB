/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013  , MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.thinkgem.jeesite.common.wx.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.nutz.json.Json;

import com.thinkgem.jeesite.common.utils.Reflections;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.wx.bean.UnifiedOrder;
import com.thinkgem.jeesite.common.wx.bean.WxRedpack;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.common.wx.util.HttpKit;

import java.lang.reflect.Field;
/**
 * 支付相关方法
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 *
 */
public class Pay {

    // 发货通知接口
    private static final String DELIVERNOTIFY_URL = "https://api.weixin.qq.com/pay/delivernotify?access_token=";

    /**
     * 参与 paySign 签名的字段包括：appid、timestamp、noncestr、package 以及 appkey。
     * 这里 signType 并不参与签名微信的Package参数
     * @param params
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getPackage(Map<String, String> params) throws UnsupportedEncodingException {
        String partnerKey = ConfKit.get("partnerKey");
        String partnerId = ConfKit.get("partnerId");
        String notifyUrl = ConfKit.get("notify_url");
        // 公共参数
        params.put("bank_type", "WX");
        params.put("attach", "yongle");
        params.put("partner", partnerId);
        params.put("notify_url", notifyUrl);
        params.put("input_charset", "UTF-8");
        return packageSign(params, partnerKey);
    }

    /**
     * 构造签名
     * @param params
     * @param encode
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }

    /**dengyu
	 * 该方法计算统一下单签名，不要sign参数，sign可传入null
	 * @param unifiedOrderParameters参数bean，
	 * @param key API密钥 :key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
	 * @return 统一下单的签名
	 */
	public static String createUnifiedOrderSign(UnifiedOrder unifiedOrderParameters, String key) throws Exception{
		String sign = null;
		try {
			List<String> list = new ArrayList<String>();
			for (Field field : UnifiedOrder.class.getDeclaredFields()) {
				// sign参数不参与签名的生成
				if (!field.getName().equals("sign")) {
					// total_fee是int型，默认值是-1
					if (!field.getName().equals("total_fee")) {
						// 取属性值，并做判断
						if (Reflections.getFieldValue(unifiedOrderParameters,
								field.getName()) != null) {
							list.add(field.getName());
						}
					} else {
						list.add(field.getName());
					}
				}
			}
			Object[] params = list.toArray();
			Arrays.sort(params);
			StringBuffer stringA = new StringBuffer();
			boolean first = true;
			for (Object param : params) {
				if (first) {
					first = false;
				} else {
					stringA.append("&");
				}
				stringA.append(param).append("=");
				String value = Reflections.getFieldValue(
						unifiedOrderParameters, param.toString()).toString();
				stringA.append(value);
			}
			stringA.append("&key=").append(key);
			sign = DigestUtils.md5Hex(stringA.toString()).toUpperCase();
		} catch (Exception e) {
			throw e;
		}
		return sign;
	}
	
	/**dengyu
	 * 该方法计算发送红包签名，不要sign参数，sign可传入null
	 * @param wxRedpackParameters参数bean，
	 * @param key API密钥 :key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
	 * @return 统一下单的签名
	 */
	public static String createWxRedpackSign(WxRedpack wxRedpackParameters, String key) throws Exception{
		String sign = null;
		try {
			List<String> list = new ArrayList<String>();
			for (Field field : WxRedpack.class.getDeclaredFields()) {
				// sign参数不参与签名的生成
				if (!field.getName().equals("sign")) {
					// total_amount是int型，默认值是0,total_num是int型，默认值是1
					if (!field.getName().equals("total_amount") && !field.getName().equals("total_num")) {
						// 取属性值，并做判断
						if (Reflections.getFieldValue(wxRedpackParameters,
								field.getName()) != null) {
							list.add(field.getName());
						}
					} else {
						list.add(field.getName());
					}
				}
			}
			Object[] params = list.toArray();
			Arrays.sort(params);
			StringBuffer stringA = new StringBuffer();
			boolean first = true;
			for (Object param : params) {
				if (first) {
					first = false;
				} else {
					stringA.append("&");
				}
				stringA.append(param).append("=");
				String value = Reflections.getFieldValue(wxRedpackParameters, param.toString()).toString();
				stringA.append(value);
			}
			stringA.append("&key=").append(key);
			sign = DigestUtils.md5Hex(stringA.toString()).toUpperCase();
		} catch (Exception e) {
			throw e;
		}
		return sign;
	}
	/**dengyu
     * 支付签名 JSSDK发起的支付
     * @param timestamp
     * @param nonceStr
     * @param packages 统一支付接口返回的prepay_id参数值
     * @param appId 微信分配的公众账号ID
     * @param key API密钥：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String paySign(String appId,String timeStamp, String nonceStr,String packages,String key) throws UnsupportedEncodingException {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appId", appId);
        paras.put("timeStamp", timeStamp);
        paras.put("nonceStr", nonceStr);
        paras.put("package", packages);
        paras.put("signType", "MD5");
        //appId、timestamp、nonceStr、package 以及 signType。
        String stringA = createSign(paras, false);
        String stringSignTemp = stringA + "&key=" + key;
        String paySign =DigestUtils.md5Hex(stringSignTemp).toUpperCase();
        return paySign;
    }
    /**
     * 构造package, 这是我见到的最草蛋的加密，尼玛文档还有错
     * @param params
     * @param paternerKey
     * @return
     * @throws UnsupportedEncodingException 
     */
    private static String packageSign(Map<String, String> params,String paternerKey) throws UnsupportedEncodingException {
        String string1 = createSign(params, false);
        String stringSignTemp = string1 + "&key=" + paternerKey;
        String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase();
        String string2 = createSign(params, true);
        return string2 + "&sign=" + signValue;
    }

    /**
     * 支付签名
     * @param timestamp
     * @param noncestr
     * @param packages
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String paySign(String timestamp, String noncestr,String packages) throws UnsupportedEncodingException {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", ConfKit.get("AppId"));
        paras.put("timestamp", timestamp);
        paras.put("noncestr", noncestr);
        paras.put("package", packages);
        paras.put("appkey", ConfKit.get("paySignKey"));
        // appid、timestamp、noncestr、package 以及 appkey。
        String string1 = createSign(paras, false);
        String paySign = DigestUtils.shaHex(string1);
        return paySign;
    }
    
    /**
     * 支付回调校验签名
     * @param timestamp
     * @param noncestr
     * @param openid
     * @param issubscribe
     * @param appsignature
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static boolean verifySign(long timestamp,
            String noncestr, String openid, int issubscribe, String appsignature) throws UnsupportedEncodingException {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", ConfKit.get("AppId"));
        paras.put("appkey", ConfKit.get("paySignKey"));
        paras.put("timestamp", String.valueOf(timestamp));
        paras.put("noncestr", noncestr);
        paras.put("openid", openid);
        paras.put("issubscribe", String.valueOf(issubscribe));
        // appid、appkey、productid、timestamp、noncestr、openid、issubscribe
        String string1 = createSign(paras, false);
        String paySign = DigestUtils.shaHex(string1);
        return paySign.equalsIgnoreCase(appsignature);
    }
    
    /**
     * 发货通知
     * @param access_token
     * @param openid
     * @param transid
     * @param out_trade_no
     * @param app_signature
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public static boolean delivernotify(String access_token, String openid, String transid, String out_trade_no, String app_signature) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", ConfKit.get("AppId"));
        paras.put("openid", openid);
        paras.put("transid", transid);
        paras.put("out_trade_no", out_trade_no);
        paras.put("deliver_timestamp", (System.currentTimeMillis() / 1000) + "");
        paras.put("deliver_status", "1");
        paras.put("deliver_msg", "ok");
        paras.put("app_signature", app_signature);
        paras.put("sign_method", "sha1");
        String json = HttpKit.post(DELIVERNOTIFY_URL.concat(access_token), Json.toJson(paras));
        if (StringUtils.isNotBlank(json)) {
        	Map<String, Object> object = Json.fromJson(Map.class, json);
            if (object.containsKey("errcode")) {
                Object errcode = object.get("errcode");
                return "0".equals(errcode.toString());
            }
        }
        return false;
    }
}
