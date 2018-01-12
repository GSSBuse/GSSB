/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 悬赏信息评论Entity
 * 
 * @author snnu
 * @version 2017-12-21
 */
public class GbjUserRewardComments extends DataEntity<GbjUserRewardComments> {

	private static final long serialVersionUID = 1L;
	private GbjReward reward;
	private String rewardId; // 悬赏信息ID
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

	public GbjUserRewardComments(GbjUser userId) {
		this.user = userId;
	}

	public GbjUserRewardComments() {
		super();
	}

	public GbjUserRewardComments(String id) {
		super(id);
	}

	public GbjReward getReward() {
		return reward;
	}

	public void getReward(GbjReward reward) {
		this.reward = reward;
	}

	public void setReward(GbjReward reward) {
		this.reward = reward;
	}

	public GbjUserRewardComments(GbjReward rewardId) {
		this.reward = rewardId;
	}

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

	/*
	 * @Length(min=0, max=64, message="用户ID长度必须介于 0 和 64 之间") public String
	 * getReward() { return reward; }
	 * 
	 * public void setReward(String reward) { this.reward = reward; }
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
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

}