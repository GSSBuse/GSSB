/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013  , MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.thinkgem.jeesite.common.wx.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件
 * 
 * @author L.cm email: 596392912@qq.com site: http://www.dreamlu.net
 *
 */
public class ConfKit {
	private static Properties props = new Properties();
	static {
		try {
//			Boolean devMode = PropertiesContent.getToBool("jfinal.devmode", false);
//			if (devMode != null && devMode.booleanValue() == true) {
//				props.load(ConfKit.class.getResourceAsStream("/wechat_dev.properties"));
//			} else {
				props.load(ConfKit.class.getResourceAsStream("/wechat.properties"));
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

	public static void setProps(Properties p) {
		props = p;
	}
}
