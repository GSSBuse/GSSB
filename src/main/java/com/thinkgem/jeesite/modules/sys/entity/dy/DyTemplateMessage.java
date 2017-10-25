/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 通知消息设置Entity
 * @author songshuqing
 * @version 2015-11-15
 */
public class DyTemplateMessage extends DataEntity<DyTemplateMessage> {
	
	private static final long serialVersionUID = 1L;
	private String templateId;		// 消息ID
	private String title;		// 消息标题
	private String content;		// 消息内容
	
	public DyTemplateMessage() {
		super();
	}

	public DyTemplateMessage(String id){
		super(id);
	}
	
	public DyTemplateMessage(int nouse,String templateId){
		super();
		setTemplateId(templateId);
	}

	@Length(min=0, max=64, message="消息ID长度必须介于 0 和 64 之间")
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
	@Length(min=0, max=255, message="消息标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1024, message="消息内容长度必须介于 0 和 1024 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}