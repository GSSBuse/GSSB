/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 买标信息评论Entity
 * 
 * @author snnu
 * @version 2017-12-18
 */
public class GbjUserBuyComments extends DataEntity<GbjUserBuyComments> {

	private static final long serialVersionUID = 1L;
	private GbjBuy buy;
	private String buyId; // 买标信息ID
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

	public GbjUserBuyComments(GbjUser userId) {
		this.user = userId;
	}

	public GbjUserBuyComments() {
		super();
	}

	public GbjUserBuyComments(String id) {
		super(id);
	}

	public GbjBuy getBuy() {
		return buy;
	}

	public void getBuy(GbjBuy buy) {
		this.buy = buy;
	}

	public void setBuy(GbjBuy buy) {
		this.buy = buy;
	}

	public GbjUserBuyComments(GbjBuy buyId) {
		this.buy = buyId;
	}

	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	/*
	 * @Length(min=0, max=64, message="买标信息ID长度必须介于 0 和 64 之间") public String
	 * getBuy() { return buy; }
	 * 
	 * public void setBuy(String buy) { this.buy = buy; }
	 */

	@Length(min = 0, max = 200, message = "父ID长度必须介于 0 和 200 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Length(min = 0, max = 200, message = "子ID长度必须介于 0 和 200 之间")
	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	@Length(min = 1, max = 500, message = "评论内容长度必须介于 1 和 500 之间")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

}