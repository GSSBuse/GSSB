package com.thinkgem.jeesite.modules.sys.entity.dy;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author wufl.fnst
 *
 */
public class TransactionInformation extends DataEntity<TransactionInformation> {
	
	String domainnameId = null;//交易域名id
	String sellerId = null;//买家id
	String buyerId = null;//卖家id
	Long bidAmount =null;//成交价
	Long proxyAmount = null;//代理竞价，不为null和0说明是代理竞价
	
	public String getDomainnameId() {
		return domainnameId;
	}
	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
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
	
	

}
