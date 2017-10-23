/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 分享记录管理Entity
 * @author quanyf.fnst
 * @version 2015-11-05
 */
public class DyShareRecord extends DataEntity<DyShareRecord> {
	
	private static final long serialVersionUID = 1L;
	private String domainnameId;		// 域名id
	private String clientId;		// 会员id
	private Date shareTime;		// 分享时间
	private long redbagAmount;		//分享获得的红包金额
	
	public DyShareRecord() {
		super();
	}

	public DyShareRecord(String id){
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShareTime() {
		return shareTime;
	}

	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}

	public long getRedbagAmount() {
		return redbagAmount;
	}

	public void setRedbagAmount(long redbagAmount) {
		this.redbagAmount = redbagAmount;
	}

}