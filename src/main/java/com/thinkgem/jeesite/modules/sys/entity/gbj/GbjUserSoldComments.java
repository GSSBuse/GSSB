/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 卖标信息评论Entity
 * 
 * @author 管理员
 * @version 2017-12-21
 */
public class GbjUserSoldComments extends DataEntity<GbjUserSoldComments> {

	private static final long serialVersionUID = 1L;
	private GbjSold sold;
	private String soldId; // 卖标信息ID
	private String parentId; // 父ID
	private String childId; // 子ID
	private String comment; // 评论内容
	private Date commentTime; // 评论时间
	private GbjUser user; // 用户ID

	public GbjUser getUser() {
		return user;
	}

	public void getUser(GbjUser user) {
		this.user = user;
	}

	public void setUser(GbjUser user) {
		this.user = user;
	}

	public GbjUserSoldComments(GbjUser userId) {
		this.user = userId;
	}

	public GbjUserSoldComments() {
		super();
	}

	public GbjUserSoldComments(String id) {
		super(id);
	}

	public GbjSold getSold() {
		return sold;
	}

	public void getSold(GbjSold sold) {
		this.sold = sold;
	}

	public void setSold(GbjSold sold) {
		this.sold = sold;
	}

	public GbjUserSoldComments(GbjSold soldId) {
		this.sold = soldId;
	}

	public String getSoldId() {
		return soldId;
	}

	public void setSoldId(String soldId) {
		this.soldId = soldId;
	}

	/*
	 * @Length(min=0, max=64, message="卖标信息ID长度必须介于 0 和 64 之间") public String
	 * getSold() { return sold; }
	 * 
	 * public void setSold(String sold) { this.sold = sold; }
	 */

	@Length(min = 0, max = 64, message = "父ID长度必须介于 0 和 64 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Length(min = 0, max = 64, message = "子ID长度必须介于 0 和 64 之间")
	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	@Length(min = 1, max = 200, message = "评论内容长度必须介于 1 和 200 之间")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "评论时间不能为空")
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

}