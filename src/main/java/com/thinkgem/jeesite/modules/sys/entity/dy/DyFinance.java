/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员财务信息管理Entity
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class DyFinance extends DataEntity<DyFinance> {
	
	private static final long serialVersionUID = 1L;
	private String clientId;		// 会员id
	private Long accountBalance;		// 账户余额
	private Long freezeBalance;		// 冻结金额
	
	public DyFinance() {
		super();
	}

	public DyFinance(String id){
		super(id);
	}

	@Length(min=1, max=64, message="会员id长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@NotNull(message="账户余额不能为空")
	public Long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Long accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	@NotNull(message="冻结金额不能为空")
	public Long getFreezeBalance() {
		return freezeBalance;
	}

	public void setFreezeBalance(Long freezeBalance) {
		this.freezeBalance = freezeBalance;
	}
	
}