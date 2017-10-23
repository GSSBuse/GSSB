/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 最新消息管理Entity
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class DyNews extends DataEntity<DyNews> {
	
	private static final long serialVersionUID = 1L;
	private String domainnameId;		// 被关注域名id
	private String clientId;		// 关注者id
	private String newBidCount;		// 最新出价计数
	
	public DyNews() {
		super();
	}

	public DyNews(String id){
		super(id);
	}

	@Length(min=1, max=64, message="被关注域名id长度必须介于 1 和 64 之间")
	public String getDomainnameId() {
		return domainnameId;
	}

	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	
	@Length(min=1, max=64, message="关注者id长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@Length(min=1, max=11, message="最新出价计数长度必须介于 1 和 11 之间")
	public String getNewBidCount() {
		return newBidCount;
	}

	public void setNewBidCount(String newBidCount) {
		this.newBidCount = newBidCount;
	}
	
}