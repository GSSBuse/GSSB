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
	private String sellNickname;		//卖家昵称
	private String sellName;			//卖家姓名
	private String sellOpenId;			//卖家openid
	private String sellBrokerId;		//卖家经纪人Id
	private String sellBrokerName;		//卖家经纪人姓名
	private String buyClientId;			//买家Id 
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
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(String waitTime) {
		this.waitTime = waitTime;
	}
	public long getBonusShareTotal() {
		return bonusShareTotal;
	}
	public void setBonusShareTotal(long bonusShareTotal) {
		this.bonusShareTotal = bonusShareTotal;
	}
	public long getBonusSecond() {
		return bonusSecond;
	}
	public void setBonusSecond(long bonusSecond) {
		this.bonusSecond = bonusSecond;
	}
	public long getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(long bidAmount) {
		this.bidAmount = bidAmount;
	}
	public long getRebate() {
		return rebate;
	}
	public void setRebate(long rebate) {
		this.rebate = rebate;
	}
	public String getSellClientId() {
		return sellClientId;
	}
	public void setSellClientId(String sellClientId) {
		this.sellClientId = sellClientId;
	}
	public String getSellNickname() {
		return sellNickname;
	}
	public void setSellNickname(String sellNickname) {
		this.sellNickname = sellNickname;
	}
	public String getSellName() {
		return sellName;
	}
	public void setSellName(String sellName) {
		this.sellName = sellName;
	}
	public String getBuyClientId() {
		return buyClientId;
	}
	public void setBuyClientId(String buyClientId) {
		this.buyClientId = buyClientId;
	}
	public String getBuyNickname() {
		return buyNickname;
	}
	public void setBuyNickname(String buyNickname) {
		this.buyNickname = buyNickname;
	}
	public String getBuyName() {
		return buyName;
	}
	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}
	
	public String getSellBrokerId() {
		return sellBrokerId;
	}
	public void setSellBrokerId(String sellBrokerId) {
		this.sellBrokerId = sellBrokerId;
	}
	public String getBuyBrokerId() {
		return buyBrokerId;
	}
	public void setBuyBrokerId(String buyBrokerId) {
		this.buyBrokerId = buyBrokerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSellBrokerName() {
		return sellBrokerName;
	}
	public void setSellBrokerName(String sellBrokerName) {
		this.sellBrokerName = sellBrokerName;
	}
	public String getBuyBrokerName() {
		return buyBrokerName;
	}
	public void setBuyBrokerName(String buyBrokerName) {
		this.buyBrokerName = buyBrokerName;
	}
	public String getQueryBrokerId() {
		return queryBrokerId;
	}
	public void setQueryBrokerId(String queryBrokerId) {
		this.queryBrokerId = queryBrokerId;
	}
	public String getSellOpenId() {
		return sellOpenId;
	}
	public void setSellOpenId(String sellOpenId) {
		this.sellOpenId = sellOpenId;
	}
	public String getBuyOpenId() {
		return buyOpenId;
	}
	public void setBuyOpenId(String buyOpenId) {
		this.buyOpenId = buyOpenId;
	}
	public String getSecondClientId() {
		return secondClientId;
	}
	public void setSecondClientId(String secondClientId) {
		this.secondClientId = secondClientId;
	}
	public String getSecondClientName() {
		return secondClientName;
	}
	public void setSecondClientName(String secondClientName) {
		this.secondClientName = secondClientName;
	}
	
	public String getSecondClientNickname() {
		return secondClientNickname;
	}
	public void setSecondClientNickname(String secondClientNickname) {
		this.secondClientNickname = secondClientNickname;
	}
	public String getShareClientId() {
		return shareClientId;
	}
	public void setShareClientId(String shareClientId) {
		this.shareClientId = shareClientId;
	}
	public String getShareClientName() {
		return shareClientName;
	}
	public void setShareClientName(String shareClientName) {
		this.shareClientName = shareClientName;
	}
	public String getShareClientNickname() {
		return shareClientNickname;
	}
	public void setShareClientNickname(String shareClientNickname) {
		this.shareClientNickname = shareClientNickname;
	}
	public long getShareRebate() {
		return shareRebate;
	}
	public void setShareRebate(long shareRebate) {
		this.shareRebate = shareRebate;
	}
	
	
}
