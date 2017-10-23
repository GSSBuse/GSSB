/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 接口验证信息Entity
 * @author shenzb.fnst
 * @version 2015-07-27
 */
public class WxInterfaceInfo extends DataEntity<WxInterfaceInfo> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 企业编号
	private String corpid;		// corpid
	private String providerSecret;		// provider_secret
	
	public WxInterfaceInfo() {
		super();
	}

	public WxInterfaceInfo(String id){
		super(id);
	}

	@NotNull(message="企业编号不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=1, max=100, message="corpid长度必须介于 1 和 100 之间")
	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	
	@Length(min=1, max=100, message="provider_secret长度必须介于 1 和 100 之间")
	public String getProviderSecret() {
		return providerSecret;
	}

	public void setProviderSecret(String providerSecret) {
		this.providerSecret = providerSecret;
	}
	
}