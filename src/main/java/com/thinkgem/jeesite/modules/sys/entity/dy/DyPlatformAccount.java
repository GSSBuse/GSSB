/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 平台总账户管理Entity
 * @author quanyf.fnst
 * @version 2015-12-15
 */
public class DyPlatformAccount extends DataEntity<DyPlatformAccount> {
	
	private static final long serialVersionUID = 1L;
	private Long totalFinance;		// 平台（账户/收支）总额
	
	public DyPlatformAccount() {
		super();
	}

	public DyPlatformAccount(String id){
		super(id);
	}

	@NotNull(message="平台（账户/收支）总额不能为空")
	public Long getTotalFinance() {
		return totalFinance;
	}

	public void setTotalFinance(Long totalFinance) {
		this.totalFinance = totalFinance;
	}
	
}