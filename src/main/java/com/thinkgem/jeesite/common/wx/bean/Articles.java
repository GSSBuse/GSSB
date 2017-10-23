/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013  , MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.thinkgem.jeesite.common.wx.bean;

import java.io.Serializable;

/**
 * 多图文消息
 * @author 
 *
 */
public class Articles implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
//	public static Articles from(WeixinClickArticleModel model) {
//		Articles ar = new Articles();
//		ar.setTitle(model.getStr("title"));
//		ar.setDescription(model.getStr("description"));
//		ar.setPicUrl(model.getStr("pic_url"));
//		ar.setUrl(model.getStr("url"));
//		
//		return ar;
//	}
//	
//	public static List<Articles> from(List<WeixinClickArticleModel> models) {
//		List<Articles> ars = new ArrayList<Articles>();
//		for (WeixinClickArticleModel m : models) {
//			ars.add(from(m));
//		}
//		
//		return ars;
//	}
}
