package com.thinkgem.jeesite.modules.sys.entity.dy;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author wufl.fnst
 *
 */
public class TransactionInformation extends DataEntity<TransactionInformation> {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = -5629298777762262562L;
    
	String domainnameId = null;//交易域名id
	String sellerId = null;//买家id
	String buyerId = null;//卖家id
	Long bidAmount =null;//成交价
	Long proxyAmount = null;//代理竞价，不为null和0说明是代理竞价
	Long reservePrice = null;
	
	/**
	 * domainnameId的取得
	 * @return domainnameId
	 */
	public String getDomainnameId() {
		return domainnameId;
	}
	/**
	 * domainnameId的设定
	 * @param domainnameId domainnameId
	 */
	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	/**
	 * sellerId的取得
	 * @return sellerId
	 */
	public String getSellerId() {
		return sellerId;
	}
	/**
	 * sellerId的设定
	 * @param sellerId sellerId
	 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	/**
	 * buyerId的取得
	 * @return buyerId
	 */
	public String getBuyerId() {
		return buyerId;
	}
	/**
	 * buyerId的设定
	 * @param buyerId buyerId
	 */
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	/**
	 * bidAmount的取得
	 * @return bidAmount
	 */
	public Long getBidAmount() {
		return bidAmount;
	}
	/**
	 * bidAmount的设定
	 * @param bidAmount bidAmount
	 */
	public void setBidAmount(Long bidAmount) {
		this.bidAmount = bidAmount;
	}
	/**
	 * proxyAmount的取得
	 * @return proxyAmount
	 */
	public Long getProxyAmount() {
		return proxyAmount;
	}
	/**
	 * proxyAmount的设定
	 * @param proxyAmount proxyAmount
	 */
	public void setProxyAmount(Long proxyAmount) {
		this.proxyAmount = proxyAmount;
	}
	/**
	 * reservePrice的取得
	 * @return reservePrice
	 */
	public Long getReservePrice() {
	    return reservePrice;
	}
	/**
	 * reservePrice的设定
	 * @param reservePrice reservePrice
	 */
	public void setReservePrice(Long reservePrice) {
	    this.reservePrice = reservePrice;
	}
	
	

}
