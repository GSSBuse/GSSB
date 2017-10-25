/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.entity.domainname;

import java.util.List;

import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;

/**
 * 页面域名Entity
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class PageDomainEntity extends DyDomainname {

	private static final long serialVersionUID = 4225415371735938039L;

	private Long currAmount;              // 域名当前价
	private boolean attentioned;          // 域名是否被关注
	private String attentionText;         // 域名是否被关注文本
	private boolean hasNews;              // 域名有无新消息
	private Long newBidCount;             //新消息条数
	private Long bidCount;                // 会员出价总数
	private List<BidClient> bidList;      // 会员出价列表
	private Long attentionCount;          // 关注会员总数
	private List<DyClient> attentionList; // 关注会员列表
	private DyClient client;              // 域名所有者
	private boolean endFlag;              // 结拍标识
	private String currentSysUserId;		  //当前系统登录者ID
	private Long proxyAmount = 0L;	// 代理竞价金额
	private Long proxyIncrement = 0L;	// 代理竞价加价幅度
	private String topBidClientId;	// 最高出价会员ID
	/**
	 * @return the currAmount
	 */
	public Long getCurrAmount() {
		return currAmount;
	}
	public Long getNewBidCount() {
		return newBidCount;
	}
	public void setNewBidCount(Long newBidCount) {
		this.newBidCount = newBidCount;
	}
	/**
	 * @param currAmount the currAmount to set
	 */
	public void setCurrAmount(Long currAmount) {
		this.currAmount = currAmount;
	}
	/**
	 * @return the isAttentioned
	 */
	public boolean isAttentioned() {
		return attentioned;
	}
	/**
	 * @param isAttentioned the isAttentioned to set
	 */
	public void setAttentioned(boolean isAttentioned) {
		this.attentioned = isAttentioned;
	}
	/**
	 * @return the attentionText
	 */
	public String getAttentionText() {
		return attentionText;
	}
	/**
	 * @param attentionText the attentionText to set
	 */
	public void setAttentionText(String attentionText) {
		this.attentionText = attentionText;
	}
	/**
	 * @return the hasNews
	 */
	public boolean isHasNews() {
		return hasNews;
	}
	/**
	 * @param hasNews the hasNews to set
	 */
	public void setHasNews(boolean hasNews) {
		this.hasNews = hasNews;
	}
	/**
	 * @return the bidCount
	 */
	public Long getBidCount() {
		return bidCount;
	}
	/**
	 * @param bidCount the bidCount to set
	 */
	public void setBidCount(Long bidCount) {
		this.bidCount = bidCount;
	}
	/**
	 * @return the bidList
	 */
	public List<BidClient> getBidList() {
		return bidList;
	}
	/**
	 * @param bidList the bidList to set
	 */
	public void setBidList(List<BidClient> bidList) {
		this.bidList = bidList;
	}
	/**
	 * @return the attentionCount
	 */
	public Long getAttentionCount() {
		return attentionCount;
	}
	/**
	 * @param attentionCount the attentionCount to set
	 */
	public void setAttentionCount(Long attentionCount) {
		this.attentionCount = attentionCount;
	}
	/**
	 * @return the attentionList
	 */
	public List<DyClient> getAttentionList() {
		return attentionList;
	}
	/**
	 * @param attentionList the attentionList to set
	 */
	public void setAttentionList(List<DyClient> attentionList) {
		this.attentionList = attentionList;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the client
	 */
	public DyClient getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(DyClient client) {
		this.client = client;
	}
	/**
	 * @return the endFlag
	 */
	public boolean isEndFlag() {
		return endFlag;
	}
	/**
	 * @param endFlag the endFlag to set
	 */
	public void setEndFlag(boolean endFlag) {
		this.endFlag = endFlag;
	}
	public String getCurrentSysUserId() {
		return currentSysUserId;
	}
	public void setCurrentSysUserId(String currentSysUserId) {
		this.currentSysUserId = currentSysUserId;
	}
	/**
	 * @return the proxyAmount
	 */
	public Long getProxyAmount() {
		return proxyAmount;
	}
	/**
	 * @param proxyAmount the proxyAmount to set
	 */
	public void setProxyAmount(Long proxyAmount) {
		this.proxyAmount = proxyAmount;
	}
	/**
	 * @return the proxyIncrement
	 */
	public Long getProxyIncrement() {
		return proxyIncrement;
	}
	/**
	 * @param proxyIncrement the proxyIncrement to set
	 */
	public void setProxyIncrement(Long proxyIncrement) {
		this.proxyIncrement = proxyIncrement;
	}
	/**
	 * @return the topBidClientId
	 */
	public String getTopBidClientId() {
		return topBidClientId;
	}
	/**
	 * @param topBidClientId the topBidClientId to set
	 */
	public void setTopBidClientId(String topBidClientId) {
		this.topBidClientId = topBidClientId;
	}

}