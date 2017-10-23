/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 推送消息管理Entity
 * @author quanyf.fnst
 * @version 2015-10-20
 */
public class DyMessage extends DataEntity<DyMessage> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String description;  //描述
	private String picture;		// 封面
	private String showPicture;		// 是否显示封面
	private String urlType;		// 链接类型
	private String urlId;		// 文章或域名ID
	
	private String urlName;		//文章标题或域名名称（不是数据库字段）
	public DyMessage() {
		super();
	}

	public DyMessage(String id){
		super(id);
	}

	@Length(min=1, max=255, message="标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Length(min=0, max=100, message="封面长度必须介于 0 和 100 之间")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Length(min=1, max=1, message="是否显示封面长度必须介于 1 和 1 之间")
	public String getShowPicture() {
		return showPicture;
	}

	public void setShowPicture(String showPicture) {
		this.showPicture = showPicture;
	}
	
	@Length(min=1, max=1, message="链接类型长度必须介于 1 和 1 之间")
	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	
	@Length(min=1, max=64, message="文章或域名ID长度必须介于 1 和 64 之间")
	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	
}