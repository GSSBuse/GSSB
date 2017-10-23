package com.thinkgem.jeesite.modules.wx.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.bean.AjaxResult;

/**
 * 微信配置信息控制器
 * @author machao
 * @since 2015/10/20
 *
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class WxConfigController {
	private static final String APPID = "wx2d9e57560cab1021";
	private static final String APPSECRET = "b9a5ba28f13b3b722128a1d6513d2544";
	private static final String ACCESS_TOKEN_REQ = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
	
	private String timestamp	= null;
	private String nonce_str = null;
	
	//可能需要考虑刷新access_token和jsapi_ticket，因为有效时间只有2小时，可以用Timer。
	private String access_token = null;
	private String jsapi_ticket = null;
	
	/**
	 * 获取微信配置信息
	 * @param model 页面模型
	 * @return 我要卖页面视图
	 */
	@RequestMapping(value = {"isell/getWxConfig"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult GetWxConfig(Model model, HttpServletRequest requesturl) {
		if(null == access_token) {
			//获取access_token
			access_token = GetValue(ACCESS_TOKEN_REQ, "access_token");
			if(null == access_token) {
				AjaxResult ar = AjaxResult.makeError("获取access_token失败");
				return ar;
			}
		}
		
		if(null == jsapi_ticket) {
			//获取ticket
			String jsapi_ticket_req = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
			jsapi_ticket = GetValue(jsapi_ticket_req, "ticket");
			AjaxResult ar = AjaxResult.makeError("获取jsapi_ticket失败");
			return ar;
		}
		
		String url = requesturl.getRequestURL().toString();
		String sign = GetSign(jsapi_ticket, url);
		
		AjaxResult ar = AjaxResult.makeSuccess("");
		ar.getData().put("timestamp", timestamp);
		ar.getData().put("nonce_str", nonce_str);
		ar.getData().put("signature", sign);
		return ar;
	}
	
	//获取sign
	public String GetSign(String jsapi_ticket, String url) {
        nonce_str = create_nonce_str();
        timestamp = create_timestamp();
        String params;
        String signature = "";
 
        //注意这里参数名必须全部小写，且必须有序
        params = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(params);
 
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(params.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        
        return signature;
    }
	
	private String GetValue(String req, String key) {
		try {
			String resp = get(req);
			
			JSONObject json = new JSONObject(resp);
			String res = json.getString(key);
			
			if((null != res) && (res.length() != 0)) {
				return res;
			} else {
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//发送get请求
	public static String get(String path) throws Exception{
        HttpURLConnection httpConn=null;
        BufferedReader in=null;
        try {
            URL url=new URL(path);
            httpConn=(HttpURLConnection)url.openConnection();
             
            //读取响应
            if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK){
                StringBuffer content=new StringBuffer();
                String tempStr="";
                in=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                while((tempStr=in.readLine())!=null){
                    content.append(tempStr);
                }
                return content.toString();
            }else{
                throw new Exception("请求出现了问题!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            in.close();
            httpConn.disconnect();
        }
        return null;
    }
	
	private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
	
	private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
 
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
