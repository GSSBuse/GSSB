package com.thinkgem.jeesite.modules.paimai.bean;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * PC端我的交易信息实体
 * @author wufl.fnst
 *
 */
public class TransactionsEntity extends DataEntity<TransactionsEntity>{
	
	private String domainname = null;		// 域名名称
	private Date endTime = null;		// 拍卖结束时间
	private String domainnameStatus = null;		// 状态
	private Date waitTime = null;		// 操作有效期
	private String domainnameId = null;// 域名id
	
	private String currentClientId = null;// 当前登录会员id,检索用	
	private String searchStatusOne = null;//检索状态1，取值对应域名的状态，用于数据库语句检索域名使用
	private String searchStatusTwo = null;//检索状态2，取值对应域名的状态，用于数据库语句检索域名使用
	private Long currentPrice = null;//当前价
	private Long dealPrice = null;//成交价
	private Long newsCount = null;//新消息计数
	private String photo = null;//域名所属人的头像地址
	
	public String getCurrentClientId() {
		return currentClientId;
	}
	public void setCurrentClientId(String currentClientId) {
		this.currentClientId = currentClientId;
	}
	
	public String getDomainname() {
		return domainname;
	}
	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDomainnameStatus() {
		return domainnameStatus;
	}
	public void setDomainnameStatus(String domainnameStatus) {
		this.domainnameStatus = domainnameStatus;
	}
	public Date getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(Date waitTime) {
		this.waitTime = waitTime;
	}
	public String getDomainnameId() {
		return domainnameId;
	}
	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	public String getSearchStatusOne() {
		return searchStatusOne;
	}
	public void setSearchStatusOne(String searchStatusOne) {
		this.searchStatusOne = searchStatusOne;
	}
	public String getSearchStatusTwo() {
		return searchStatusTwo;
	}
	public void setSearchStatusTwo(String searchStatusTwo) {
		this.searchStatusTwo = searchStatusTwo;
	}
	public Long getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Long currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Long getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(Long dealPrice) {
		this.dealPrice = dealPrice;
	}
	public Long getNewsCount() {
		return newsCount;
	}
	public void setNewsCount(Long newsCount) {
		this.newsCount = newsCount;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
