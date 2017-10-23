package com.thinkgem.jeesite.modules.wx.entity.domainname;

import com.thinkgem.jeesite.common.persistence.DataEntity;


/**
 * 出价-会员信息
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class BidClient extends DataEntity<BidClient>{

	private static final long serialVersionUID = 1L;
	private String bidhistoryId;	//出价历史ID
	private String domainId;	// 域名ID
	private String domainName;	// 域名名称
	private String domainStatus; //域名状态
	private String clientId;	// 会员ID
	private Long bidAmount;		// 出价金额
	private Long proxyAmount;	//代理竞价金额
	private Long proxyIncrement;	//代理竞价加价幅度
	private String dyid;		// 米友号
	private String name;		// 姓名
	private String nickname;	// 微信昵称
	private String photo;		// 微信头像
	private String bidTime;		// 出价时间
	private long bidCount;		// 出价总记录数
	private String type;		// 出价类型
	private String endTime;		// 截拍时间
	
	private String shareOpenid;
	
	public String getBidhistoryId() {
		return bidhistoryId;
	}
	public void setBidhistoryId(String bidhistoryId) {
		this.bidhistoryId = bidhistoryId;
	}
	/**
	 * @return the domainId
	 */
	public String getDomainId() {
		return domainId;
	}
	/**
	 * @param domainId the domainId to set
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}
	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public String getDomainStatus() {
		return domainStatus;
	}
	public void setDomainStatus(String domainStatus) {
		this.domainStatus = domainStatus;
	}
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the bidAmount
	 */
	public Long getBidAmount() {
		return bidAmount;
	}
	/**
	 * @param bidAmount the bidAmount to set
	 */
	public void setBidAmount(Long bidAmount) {
		this.bidAmount = bidAmount;
	}
	/**
	 * @return the dyid
	 */
	public String getDyid() {
		return dyid;
	}
	/**
	 * @param dyid the dyid to set
	 */
	public void setDyid(String dyid) {
		this.dyid = dyid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * @return the bidTime
	 */
	public String getBidTime() {
		return bidTime;
	}
	/**
	 * @param bidTime the bidTime to set
	 */
	public void setBidTime(String bidTime) {
		this.bidTime = bidTime;
	}
	/**
	 * @return the bidCount
	 */
	public long getBidCount() {
		return bidCount;
	}
	/**
	 * @param bidCount the bidCount to set
	 */
	public void setBidCount(long bidCount) {
		this.bidCount = bidCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getProxyAmount() {
		return proxyAmount;
	}
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
	
	public String toString(){
		String paramsStr = "";
		paramsStr += "域名名称：" + domainName;
		paramsStr += "  会员米友号：" + dyid;
		paramsStr += "  会员昵称：" + nickname;
		paramsStr += " 	出价金额：" + bidAmount;
		paramsStr += "  代理竞价金额：" + proxyAmount;
		paramsStr += "出价类型：" + type;
		paramsStr += "  出价时间：" + bidTime;
		paramsStr += "  截拍时间：" + endTime;
		return paramsStr;
	}
	/**
	 * shareOpenid的取得
	 * @return shareOpenid
	 */
	public String getShareOpenid() {
	    return shareOpenid;
	}
	/**
	 * shareOpenid的设定
	 * @param shareOpenid shareOpenid
	 */
	public void setShareOpenid(String shareOpenid) {
	    this.shareOpenid = shareOpenid;
	}
}