/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 悬赏信息评论回复表Entity
 * 
 * @author snnu
 * @version 2018-01-18
 */
public class GbjUserRewardCommentsReply extends DataEntity<GbjUserRewardCommentsReply> {

	private static final long serialVersionUID = 1L;
	private String rewardId; // 悬赏信息ID
	private String toId; // 对应评论信息ID
	private String userId; // 评论用户id
	private String replyComments; // 回复信息
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

	public GbjUserRewardCommentsReply(GbjUser userId) {
		this.user = userId;
	}

	public GbjUserRewardCommentsReply() {
		super();
	}

	public GbjUserRewardCommentsReply(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "悬赏信息ID长度必须介于 0 和 64 之间")
	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

	@Length(min = 0, max = 64, message = "对应评论信息ID长度必须介于 0 和 64 之间")
	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min = 0, max = 500, message = "回复信息长度必须介于 0 和 500 之间")
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