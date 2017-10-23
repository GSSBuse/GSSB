/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 群发消息列表管理Entity
 * @author quanyf.fnst
 * @version 2015-10-19
 */
public class DyMessagegroup extends DataEntity<DyMessagegroup> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 图文消息标题
	private String type;		// 定时发送
	private Date sendTime;		// 发送时间
	private String status;		// 是否已发送
	private String sendToAll;		// 是否群发
	private String messageList;		// 消息列表
	
	private List<DyMessage> dyMessageList;  //消息列表实体
	
	public DyMessagegroup() {
		super();
	}

	public DyMessagegroup(String id){
		super(id);
	}

	@Length(min=0, max=255, message="图文消息标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1, message="定时发送长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@Length(min=0, max=1, message="是否已发送长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="是否群发长度必须介于 0 和 1 之间")
	public String getSendToAll() {
		return sendToAll;
	}

	public void setSendToAll(String sendToAll) {
		this.sendToAll = sendToAll;
	}
	
	@Length(min=0, max=700, message="消息列表长度必须介于 0 和 700 之间")
	public String getMessageList() {
		return messageList;
	}

	public void setMessageList(String messageList) {
		this.messageList = messageList;
	}

	public List<DyMessage> getDyMessageList() {
		return dyMessageList;
	}

	public void setDyMessageList(List<DyMessage> dyMessageList) {
		this.dyMessageList = dyMessageList;
	}
	
}