package com.thinkgem.jeesite.common.wx.bean;

/**链接菜单按钮，可以作为一级菜单和二级菜单按钮
 * @author wufl.fnst
 *
 */
public class ViewCommonButton extends Button {  
   
	private String type = "view"; // 类型 固定为view
   
	private String url; //链接地址

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	} 
	
	
}
