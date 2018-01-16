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
 * 买标信息评论回复管理Entity
 * 
 * @author snnu
 * @version 2018-01-15
 */
public class GbjUserBuyCommentsReply extends DataEntity<GbjUserBuyCommentsReply> {

	private static final long serialVersionUID = 1L;
	private String buyId; // 买标信息ID
	private String toId; // 对应评论信息ID
	private String userId; // 评论用户id
	private String replyComments; // 回复信息
	private Date commentTime; // 评论时间
	private GbjBuy buy;
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

	public GbjUserBuyCommentsReply(GbjUser userId) {
		this.user = userId;
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

	public GbjUserBuyCommentsReply(GbjBuy buyId) {
		this.buy = buyId;
	}

	public GbjUserBuyCommentsReply() {
		super();
	}

	public GbjUserBuyCommentsReply(String id) {
		super(id);
	}

	@Length(min = 1, max = 64, message = "买标信息ID长度必须介于 1 和 64 之间")
	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	@Length(min = 1, max = 64, message = "对应评论信息ID长度必须介于 1 和 64 之间")
	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	@NotNull(message = "评论用户id不能为空")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min = 1, max = 500, message = "回复信息长度必须介于 1 和 500 之间")
	public String getReplyComments() {
		return replyComments;
	}

	public void setReplyComments(String replyComments) {
		this.replyComments = replyComments;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

}