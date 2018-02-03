/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 提现记录表Entity
 * 
 * @author snnu
 * @version 2018-02-03
 */
public class GbjWithdraw extends DataEntity<GbjWithdraw> {

	private static final long serialVersionUID = 1L;
	private String userId; // 用户ID
	private String username; // 用户名
	private String money; // 提现金额
	private String payway; // 支付宝账号
	private String wechat; // 微信
	private Date payTime; // 提现时间
	private String mobile; // 提现通知电话
	private String isSuccess; // 提现是否成功

	public GbjWithdraw() {
		super();
	}

	public GbjWithdraw(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "用户ID长度必须介于 0 和 64 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min = 0, max = 64, message = "用户名长度必须介于 0 和 64 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Length(min = 0, max = 200, message = "支付宝账号长度必须介于 0 和 200 之间")
	public String getPayway() {
		return payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	@Length(min = 0, max = 200, message = "微信长度必须介于 0 和 200 之间")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Length(min = 0, max = 200, message = "提现通知电话长度必须介于 0 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Length(min = 0, max = 1, message = "提现是否成功长度必须介于 0 和 1 之间")
	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

}