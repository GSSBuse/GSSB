/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员出价信息Entity
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class DyBidhistory extends DataEntity<DyBidhistory> {
	
	private static final long serialVersionUID = 1L;
	private String domainnameId;		// 域名id
	private String clientId;		// 会员id
	private Long bidAmount;		// 出价金额
	private Long proxyAmount;		// 代理竞价金额
	private String type;		// 出价类型
	private Date bidTime;		// 出价时间
	private String shareOpenid;		// 出价时间
	
	public DyBidhistory() {
		super();
	}

	public DyBidhistory(String id){
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
	
	@NotNull(message="出价金额不能为空")
	public Long getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(Long bidAmount) {
		this.bidAmount = bidAmount;
	}
	
	public Long getProxyAmount() {
		return proxyAmount;
	}

	public void setProxyAmount(Long proxyAmount) {
		this.proxyAmount = proxyAmount;
	}
	
	@Length(min=1, max=100, message="出价类型长度必须介于 1 和 100 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="出价时间不能为空")
	public Date getBidTime() {
		return bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	/**
	 * @return the shareOpenid
	 */
	public String getShareOpenid() {
		return shareOpenid;
	}

	/**
	 * @param shareOpenid the shareOpenid to set
	 */
	public void setShareOpenid(String shareOpenid) {
		this.shareOpenid = shareOpenid;
	}
	
}