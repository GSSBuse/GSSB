/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资讯文章管理Entity
 * @author quanyf.fnst
 * @version 2015-10-19
 */
public class DyArticle extends DataEntity<DyArticle> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 文章标题
	private String description;		// 关联域名id
	private String hit;		// 点击数
	private String content;		// 内容
	private Date sendTime;		// 发送时间
	
	private String domainnameName;		// 关联域名名称(不是数据库字段)
	public DyArticle() {
		super();
	}

	public DyArticle(String id){
		super(id);
	}

	@Length(min=1, max=255, message="文章标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="描述摘要长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=10, message="点击数长度必须介于 0 和 10 之间")
	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getDomainnameName() {
		return domainnameName;
	}

	public void setDomainnameName(String domainnameName) {
		this.domainnameName = domainnameName;
	}
	
}