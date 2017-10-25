package com.thinkgem.jeesite.modules.wx.entity.domainname;

import com.thinkgem.jeesite.common.persistence.DataEntity;
/**
 * 域名付款信息
 * @author quanyf.fnst
 *@version 2015-10-28
 */
public class BidCashInfo extends DataEntity<BidCashInfo>{
	private static final long serialVersionUID = 1L;
	private String domainId;		    //域名Id
	private String domainName;		    //域名名称
	private String endTime;			    //拍卖结束时间
	private String waitTime;			//交易有效时间
	private long bonusShareTotal;		//转发红包总额
	private long bonusSecond;		    //次高价红包
	private long bidAmount;			    //最高出价
	private String sellClientId;		//卖家Id
	private String sellDyId;				//卖家米友号
	private String sellNickname;		//卖家昵称
	private String sellName;			//卖家姓名
	private String sellOpenId;			//卖家openid
	private String sellBrokerId;		//卖家经纪人Id
	private String sellBrokerName;		//卖家经纪人姓名
	private String buyClientId;			//买家Id 
	private String buyDyId;				//买家米友号
	private String buyNickname;			//买家昵称
	private String buyName;				//买家姓名
	private String buyOpenId;			//卖家openid
	private String buyBrokerId;			//卖家经纪人Id
	private String buyBrokerName;		//买家经纪人姓名
	private String status;				//交易状态
	private long rebate;			    //平台佣金
	private long shareRebate;			    //分享佣金
	private String secondClientId;		//次高价会员Id
	private String secondClientName;		//次高价会员姓名
	private String secondClientNickname;		//次高价会员昵称
	private String shareClientId;		//分享会员Id
	private String shareClientName;		//分享会员姓名
	private String shareClientNickname;		//分享会员昵称
	private String queryBrokerId;		//经纪人ID（没有意义，作为查询条件用）
	private String reservePrice;
	
	private String searchStartTime;
	private String searchEndTime;
	
	
	/**
	 * domainId的取得
	 * @return domainId
	 */
	public String getDomainId() {
		return domainId;
	}
	/**
	 * domainId的设定
	 * @param domainId domainId
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	/**
	 * domainName的取得
	 * @return domainName
	 */
	public String getDomainName() {
		return domainName;
	}
	/**
	 * domainName的设定
	 * @param domainName domainName
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	/**
	 * endTime的取得
	 * @return endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * endTime的设定
	 * @param endTime endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * waitTime的取得
	 * @return waitTime
	 */
	public String getWaitTime() {
		return waitTime;
	}
	/**
	 * waitTime的设定
	 * @param waitTime waitTime
	 */
	public void setWaitTime(String waitTime) {
		this.waitTime = waitTime;
	}
	/**
	 * bonusShareTotal的取得
	 * @return bonusShareTotal
	 */
	public long getBonusShareTotal() {
		return bonusShareTotal;
	}
	/**
	 * bonusShareTotal的设定
	 * @param bonusShareTotal bonusShareTotal
	 */
	public void setBonusShareTotal(long bonusShareTotal) {
		this.bonusShareTotal = bonusShareTotal;
	}
	/**
	 * bonusSecond的取得
	 * @return bonusSecond
	 */
	public long getBonusSecond() {
		return bonusSecond;
	}
	/**
	 * bonusSecond的设定
	 * @param bonusSecond bonusSecond
	 */
	public void setBonusSecond(long bonusSecond) {
		this.bonusSecond = bonusSecond;
	}
	/**
	 * bidAmount的取得
	 * @return bidAmount
	 */
	public long getBidAmount() {
		return bidAmount;
	}
	/**
	 * bidAmount的设定
	 * @param bidAmount bidAmount
	 */
	public void setBidAmount(long bidAmount) {
		this.bidAmount = bidAmount;
	}
	/**
	 * sellClientId的取得
	 * @return sellClientId
	 */
	public String getSellClientId() {
		return sellClientId;
	}
	/**
	 * sellClientId的设定
	 * @param sellClientId sellClientId
	 */
	public void setSellClientId(String sellClientId) {
		this.sellClientId = sellClientId;
	}
	/**
	 * sellDyId的取得
	 * @return sellDyId
	 */
	public String getSellDyId() {
	    return sellDyId;
	}
	/**
	 * sellDyId的设定
	 * @param sellDyId sellDyId
	 */
	public void setSellDyId(String sellDyId) {
	    this.sellDyId = sellDyId;
	}
	/**
	 * sellNickname的取得
	 * @return sellNickname
	 */
	public String getSellNickname() {
		return sellNickname;
	}
	/**
	 * sellNickname的设定
	 * @param sellNickname sellNickname
	 */
	public void setSellNickname(String sellNickname) {
		this.sellNickname = sellNickname;
	}
	/**
	 * buyClientId的取得
	 * @return buyClientId
	 */
	public String getBuyClientId() {
		return buyClientId;
	}
	/**
	 * buyClientId的设定
	 * @param buyClientId buyClientId
	 */
	public void setBuyClientId(String buyClientId) {
		this.buyClientId = buyClientId;
	}
	/**
	 * buyDyId的取得
	 * @return buyDyId
	 */
	public String getBuyDyId() {
	    return buyDyId;
	}
	/**
	 * buyDyId的设定
	 * @param buyDyId buyDyId
	 */
	public void setBuyDyId(String buyDyId) {
	    this.buyDyId = buyDyId;
	}
	/**
	 * buyNickname的取得
	 * @return buyNickname
	 */
	public String getBuyNickname() {
		return buyNickname;
	}
	/**
	 * buyNickname的设定
	 * @param buyNickname buyNickname
	 */
	public void setBuyNickname(String buyNickname) {
		this.buyNickname = buyNickname;
	}
	/**
	 * sellBrokerId的取得
	 * @return sellBrokerId
	 */
	public String getSellBrokerId() {
		return sellBrokerId;
	}
	/**
	 * sellBrokerId的设定
	 * @param sellBrokerId sellBrokerId
	 */
	public void setSellBrokerId(String sellBrokerId) {
		this.sellBrokerId = sellBrokerId;
	}
	public String getSellBrokerName() {
		return sellBrokerName;
	}
	public void setSellBrokerName(String sellBrokerName) {
		this.sellBrokerName = sellBrokerName;
	}
	/**
	 * buyBrokerId的取得
	 * @return buyBrokerId
	 */
	public String getBuyBrokerId() {
		return buyBrokerId;
	}
	/**
	 * buyBrokerId的设定
	 * @param buyBrokerId buyBrokerId
	 */
	public void setBuyBrokerId(String buyBrokerId) {
		this.buyBrokerId = buyBrokerId;
	}
	public String getBuyBrokerName() {
		return buyBrokerName;
	}
	public void setBuyBrokerName(String buyBrokerName) {
		this.buyBrokerName = buyBrokerName;
	}
	/**
	 * sellName的取得
	 * @return sellName
	 */
	public String getSellName() {
		return sellName;
	}
	/**
	 * sellName的设定
	 * @param sellName sellName
	 */
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public String getSellOpenId() {
		return sellOpenId;
	}
	public void setSellOpenId(String sellOpenId) {
		this.sellOpenId = sellOpenId;
	}
	/**
	 * buyName的取得
	 * @return buyName
	 */
	public String getBuyName() {
		return buyName;
	}
	/**
	 * buyName的设定
	 * @param buyName buyName
	 */
	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}
	public String getBuyOpenId() {
		return buyOpenId;
	}
	public void setBuyOpenId(String buyOpenId) {
		this.buyOpenId = buyOpenId;
	}
	/**
	 * secondClientId的取得
	 * @return secondClientId
	 */
	public String getSecondClientId() {
		return secondClientId;
	}
	/**
	 * secondClientId的设定
	 * @param secondClientId secondClientId
	 */
	public void setSecondClientId(String secondClientId) {
		this.secondClientId = secondClientId;
	}
	/**
	 * secondClientName的取得
	 * @return secondClientName
	 */
	public String getSecondClientName() {
		return secondClientName;
	}
	/**
	 * secondClientName的设定
	 * @param secondClientName secondClientName
	 */
	public void setSecondClientName(String secondClientName) {
		this.secondClientName = secondClientName;
	}
	/**
	 * secondClientNickname的取得
	 * @return secondClientNickname
	 */
	public String getSecondClientNickname() {
		return secondClientNickname;
	}
	/**
	 * secondClientNickname的设定
	 * @param secondClientNickname secondClientNickname
	 */
	public void setSecondClientNickname(String secondClientNickname) {
		this.secondClientNickname = secondClientNickname;
	}
	/**
	 * shareClientId的取得
	 * @return shareClientId
	 */
	public String getShareClientId() {
		return shareClientId;
	}
	/**
	 * shareClientId的设定
	 * @param shareClientId shareClientId
	 */
	public void setShareClientId(String shareClientId) {
		this.shareClientId = shareClientId;
	}
	/**
	 * shareClientName的取得
	 * @return shareClientName
	 */
	public String getShareClientName() {
		return shareClientName;
	}
	/**
	 * shareClientName的设定
	 * @param shareClientName shareClientName
	 */
	public void setShareClientName(String shareClientName) {
		this.shareClientName = shareClientName;
	}
	/**
	 * status的取得
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * status的设定
	 * @param status status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * rebate的取得
	 * @return rebate
	 */
	public long getRebate() {
		return rebate;
	}
	/**
	 * rebate的设定
	 * @param rebate rebate
	 */
	public void setRebate(long rebate) {
		this.rebate = rebate;
	}
	public long getShareRebate() {
		return shareRebate;
	}
	public void setShareRebate(long shareRebate) {
		this.shareRebate = shareRebate;
	}
	/**
	 * shareClientNickname的取得
	 * @return shareClientNickname
	 */
	public String getShareClientNickname() {
		return shareClientNickname;
	}
	/**
	 * shareClientNickname的设定
	 * @param shareClientNickname shareClientNickname
	 */
	public void setShareClientNickname(String shareClientNickname) {
		this.shareClientNickname = shareClientNickname;
	}
	/**
	 * queryBrokerId的取得
	 * @return queryBrokerId
	 */
	public String getQueryBrokerId() {
		return queryBrokerId;
	}
	/**
	 * queryBrokerId的设定
	 * @param queryBrokerId queryBrokerId
	 */
	public void setQueryBrokerId(String queryBrokerId) {
		this.queryBrokerId = queryBrokerId;
	}
	/**
	 * reservePrice的取得
	 * @return reservePrice
	 */
	public String getReservePrice() {
	    return reservePrice;
	}
	/**
	 * reservePrice的设定
	 * @param reservePrice reservePrice
	 */
	public void setReservePrice(String reservePrice) {
	    this.reservePrice = reservePrice;
	}
	/**
	 * searchStartTime的取得
	 * @return searchStartTime
	 */
	public String getSearchStartTime() {
	    return searchStartTime;
	}
	/**
	 * searchStartTime的设定
	 * @param searchStartTime searchStartTime
	 */
	public void setSearchStartTime(String searchStartTime) {
	    this.searchStartTime = searchStartTime;
	}
	/**
	 * searchEndTime的取得
	 * @return searchEndTime
	 */
	public String getSearchEndTime() {
	    return searchEndTime;
	}
	/**
	 * searchEndTime的设定
	 * @param searchEndTime searchEndTime
	 */
	public void setSearchEndTime(String searchEndTime) {
	    this.searchEndTime = searchEndTime;
	}
	
	
}
