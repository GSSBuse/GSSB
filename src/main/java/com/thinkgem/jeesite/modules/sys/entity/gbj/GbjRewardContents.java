/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 悬赏信息评论Entity
 * @author snnu
 * @version 2017-11-20
 */
public class GbjRewardContents extends DataEntity<GbjRewardContents> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户ID
	private String rewardId;		// 悬赏信息ID
	private String contents;		// 评论内容
	private Long upCounts;		// 点赞个数
	private String toUserId;		// 评论人id
	
	public GbjRewardContents() {
		super();
	}

	public GbjRewardContents(String id){
		super(id);
	}

	@Length(min=0, max=200, message="用户ID长度必须介于 0 和 200 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=200, message="悬赏信息ID长度必须介于 0 和 200 之间")
	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}
	
	@Length(min=0, max=500, message="评论内容长度必须介于 0 和 500 之间")
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public Long getUpCounts() {
		return upCounts;
	}

	public void setUpCounts(Long upCounts) {
		this.upCounts = upCounts;
	}
	
	@Length(min=0, max=200, message="评论人id长度必须介于 0 和 200 之间")
	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	
}