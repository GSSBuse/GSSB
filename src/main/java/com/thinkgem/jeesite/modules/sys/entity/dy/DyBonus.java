/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 红包佣金信息Entity
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class DyBonus extends DataEntity<DyBonus> {
	
	private static final long serialVersionUID = 1L;
	private String domainnameId;		// 域名id
	private String clientId;		// 会员id
	private Long money;		// 金额
	private String type;		// 收支类型
	
	public DyBonus() {
		super();
	}

	public DyBonus(String id){
		super(id);
	}

	@Length(min=1, max=64, message="域名id长度必须介于 1 和 64 之间")
	public String getDomainnameId() {
		return domainnameId;
	}

	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	
	@Length(min=1, max=64, message="会员id长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@NotNull(message="金额不能为空")
	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}
	
	@Length(min=1, max=2, message="收支类型长度必须介于 1 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}