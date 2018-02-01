/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 悬赏信息管理Entity
 * 
 * @author snnu
 * @version 2017-12-21
 */
public class GbjReward extends DataEntity<GbjReward> {

	private static final long serialVersionUID = 1L;
	private GbjUser user;
	private String user_id; // user_id
	private String realname; // 真实姓名
	private String typeId; // 国标类型
	private String title; // 标题
	private String description; // 起名需求
	private String transactionId; // 微信支付订单号
	private Long totalFee; // 交易金额
	private String resultCode; // 支付结果
	private Date timeEnd; // 支付完成时间
	private String mobile; // 手机号码
	private String tag; // 标签

	private String status; // 状态（未发布，已发布，已中标）
	private Long payFlowNumber; // 支付流水号
	private String successfulBidder; // 中标者
	private String frontDelFlag; // 撤回标记
	private Long upCounts; // 点赞数
	private Long lookCounts; // 查看数
	private Long commentsCounts; // 评论数

	public GbjReward() {
		super();
	}

	public GbjReward(String id) {
		super(id);
	}

	public GbjUser getUser() {
		return user;
	}

	public void getUser(GbjUser user) {
		this.user = user;
	}

	public void setUser(GbjUser user) {
		this.user = user;
	}

	public GbjReward(GbjUser userId) {
		this.user = userId;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/*
	 * @Length(min=1, max=64, message="用户名ID长度必须介于 1 和 64 之间") public String
	 * getUser() { return user; }
	 * 
	 * public void setUser(String user) { this.user = user; }
	 */

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	@Length(min = 1, max = 200, message = "真实姓名长度必须介于 1 和 200 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Length(min = 1, max = 64, message = "国标类型长度必须介于 1 和 64 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Length(min = 1, max = 500, message = "标题长度必须介于 1 和 500 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Length(min = 1, max = 200, message = "手机号码长度必须介于 1 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Length(min = 0, max = 200, message = "标签长度必须介于 0 和 200 之间")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Length(min = 0, max = 20, message = "状态（未发布，已发布，已中标）长度必须介于 0 和 20 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getPayFlowNumber() {
		return payFlowNumber;
	}

	public void setPayFlowNumber(Long payFlowNumber) {
		this.payFlowNumber = payFlowNumber;
	}

	@Length(min = 0, max = 20, message = "中标者长度必须介于 0 和 20 之间")
	public String getSuccessfulBidder() {
		return successfulBidder;
	}

	public void setSuccessfulBidder(String successfulBidder) {
		this.successfulBidder = successfulBidder;
	}

	@Length(min = 0, max = 1, message = "撤回标记长度必须介于 0 和 1 之间")
	public String getFrontDelFlag() {
		return frontDelFlag;
	}

	public void setFrontDelFlag(String frontDelFlag) {
		this.frontDelFlag = frontDelFlag;
	}

	public Long getUpCounts() {
		return upCounts;
	}

	public void setUpCounts(Long upCounts) {
		this.upCounts = upCounts;
	}

	public Long getLookCounts() {
		return lookCounts;
	}

	public void setLookCounts(Long lookCounts) {
		this.lookCounts = lookCounts;
	}

	public Long getCommentsCounts() {
		return commentsCounts;
	}

	public void setCommentsCounts(Long commentsCounts) {
		this.commentsCounts = commentsCounts;
	}

}