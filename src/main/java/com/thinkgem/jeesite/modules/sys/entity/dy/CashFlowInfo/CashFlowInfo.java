package com.thinkgem.jeesite.modules.sys.entity.dy.CashFlowInfo;

import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;

public class CashFlowInfo extends DyCashFlow {
	private String brokerId;  //经纪人ID
	private String brokerName;  //经纪人姓名
	private String name ; //会员姓名
	private String dyid ;	//会员米友号
	private String nickname;	//会员微信昵称
	private String mobile; 	//会员手机
	private long accountBalance;	//当前账户总额
	private long freezeBalance;	//冻结金额
	private String startTime ;	//开始时间
	private String endTime ;	//截止时间
	
	
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDyid() {
		return dyid;
	}
	public void setDyid(String dyid) {
		this.dyid = dyid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}
	public long getFreezeBalance() {
		return freezeBalance;
	}
	public void setFreezeBalance(long freezeBalance) {
		this.freezeBalance = freezeBalance;
	}
	
}
