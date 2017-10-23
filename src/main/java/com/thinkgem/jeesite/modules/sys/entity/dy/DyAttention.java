/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员关注管理Entity
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class DyAttention extends DataEntity<DyAttention> {
	
	private static final long serialVersionUID = 1L;
	private String clientId;		// 会员id
	private String domainnameId;		// 被关注域名id
	
	public DyAttention() {
		super();
	}

	public DyAttention(String id){
		super(id);
	}

	@Length(min=1, max=64, message="会员id长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@Length(min=1, max=64, message="被关注域名id长度必须介于 1 和 64 之间")
	public String getDomainnameId() {
		return domainnameId;
	}

	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	
}