/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 悬赏信息管理Entity
 * @author snnu
 * @version 2017-11-20
 */
public class GbjReward extends DataEntity<GbjReward> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户ID
	private String title;		// 标题
	private String titleNeed;		// 起名需求
	private Long rewardMoney;		// 打赏金额
	private String isPayReward;		// 是否支付
	private String isFinished;		// 是否完成
	private Long upCount;		// 点赞个数
	
	public GbjReward() {
		super();
	}

	public GbjReward(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=500, message="标题长度必须介于 0 和 500 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=500, message="起名需求长度必须介于 0 和 500 之间")
	public String getTitleNeed() {
		return titleNeed;
	}

	public void setTitleNeed(String titleNeed) {
		this.titleNeed = titleNeed;
	}
	
	public Long getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(Long rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	
	@Length(min=0, max=1, message="是否支付长度必须介于 0 和 1 之间")
	public String getIsPayReward() {
		return isPayReward;
	}

	public void setIsPayReward(String isPayReward) {
		this.isPayReward = isPayReward;
	}
	
	@Length(min=0, max=1, message="是否完成长度必须介于 0 和 1 之间")
	public String getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(String isFinished) {
		this.isFinished = isFinished;
	}
	
	public Long getUpCount() {
		return upCount;
	}

	public void setUpCount(Long upCount) {
		this.upCount = upCount;
	}
	
}