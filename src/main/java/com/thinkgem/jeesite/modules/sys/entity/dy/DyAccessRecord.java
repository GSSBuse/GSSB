/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 访问记录管理Entity
 * @author quanyf.fnst
 * @version 2015-11-04
 */
public class DyAccessRecord extends DataEntity<DyAccessRecord> {
	
	private static final long serialVersionUID = 1L;
	private String domainnameId;		// 域名Id
	private String clientId;		// 会员Id
	private String shareClientid;		// 分享人Id
	private Date accessTime;		// 访问时间
	
	public DyAccessRecord() {
		super();
	}

	public DyAccessRecord(String id){
		super(id);
	}

	@Length(min=1, max=64, message="域名Id长度必须介于 1 和 64 之间")
	public String getDomainnameId() {
		return domainnameId;
	}

	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	
	@Length(min=1, max=64, message="会员Id长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@Length(min=1, max=64, message="分享人Id长度必须介于 1 和 64 之间")
	public String getShareClientid() {
		return shareClientid;
	}

	public void setShareClientid(String shareClientid) {
		this.shareClientid = shareClientid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}
	
}