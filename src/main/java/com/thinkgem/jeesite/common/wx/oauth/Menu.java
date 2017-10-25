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
import java.util.Map;

import org.apache.log4j.Logger;
import org.nutz.json.Json;

import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.bean.Button;
import com.thinkgem.jeesite.common.wx.bean.ClickCommonButton;
import com.thinkgem.jeesite.common.wx.bean.ComplexButton;
import com.thinkgem.jeesite.common.wx.bean.MenuBean;
import com.thinkgem.jeesite.common.wx.bean.ViewCommonButton;
import com.thinkgem.jeesite.common.wx.inf.ErrorCode;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.common.wx.util.HttpKit;

/**
 * 菜单,可以将accessToken
 * 存储在session或者memcache中
 * @author L.cm
 * @date 2013-11-5 下午3:17:33
 */
@SuppressWarnings("unchecked")
public class Menu {
	private static Logger log=Logger.getLogger(Menu.class);
    /**
     * 创建菜单
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
	public boolean createMenu(String accessToken,String params,String resultMsg) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken, params);
        Map<String, Object> map = Json.fromJson(Map.class,jsonStr);
        log.debug("创建菜单："+ErrorCode.get(map));
        resultMsg=ErrorCode.get(map);
        System.out.println(resultMsg);
        return "0".equals(map.get("errcode").toString());
    }
    
    /**
     * 查询菜单
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public Map<String, Object> getMenuInfo(String accessToken) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
        Map<String, Object> map = Json.fromJson(Map.class,jsonStr);
        return map;
    }
    
    /**
     * 删除自定义菜单
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public boolean deleteMenu(String accessToken) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);
        Map<String, Object> map = Json.fromJson(Map.class,jsonStr);
        log.debug("删除菜单："+ErrorCode.get(map));
        return "0".equals(map.get("errcode").toString());
    }
    

	/**
	 * 创建菜单样例
	 */
	public boolean createMenuDemo(){
		System.out.println(ConfKit.get("root_uri"));
		ViewCommonButton btn1 = new ViewCommonButton();
		btn1.setName("我要买");
		btn1.setType("view");
		String url1 = ConfKit.get("root_uri") + "/domainname/ibuy.html";
		btn1.setUrl(url1);
		
		ViewCommonButton btn2 = new ViewCommonButton();
		btn2.setName("我要卖");
		btn2.setType("view");
		String url2 = ConfKit.get("root_uri") + "/domainname/isell.html";
		btn2.setUrl(url2);

		ViewCommonButton btn31 = new ViewCommonButton();
		btn31.setName("个人中心");
		btn31.setType("view");
		String url31 = ConfKit.get("root_uri") + "/domainname/icenter.html";
		btn31.setUrl(url31);
		
		ViewCommonButton btn32 = new ViewCommonButton();
		btn32.setName("历史交易");
		btn32.setType("view");
		String url32 = ConfKit.get("root_uri") + "/domainname/getHistory.html";
		btn32.setUrl(url32);
		
		ClickCommonButton btn33 = new ClickCommonButton();
		btn33.setName("联系经纪人");
		btn33.setType("click");
		btn33.setKey("contact");
		
		ViewCommonButton btn34 = new ViewCommonButton();
		btn34.setName("使用指南");
		btn34.setType("view");
		String url34 = ConfKit.get("root_uri") + "/domainname/viewArticle?articleId=2";
		btn34.setUrl(url34);
		
		ComplexButton btn3 = new ComplexButton();  
		btn3.setName("其它");  
		btn3.setSub_button(new Button[] { btn31, btn32 ,btn33,btn34});
		//我的，联系经纪人、查看交易记录

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		MenuBean menu = new MenuBean();
		menu.setButton(new Button[] { btn1, btn2, btn3 });
		String jsonParams = Json.toJson(menu);
		String resultMsg = "";
		try {
			return createMenu(WeChat.getAccessToken(),jsonParams, resultMsg);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
