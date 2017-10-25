/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 域名信息管理Entity
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class DyDomainname extends DataEntity<DyDomainname> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 域名名称
	private String clientId;		// 会员编号
	private Date beginTime;		// 拍卖开始时间
	private Date endTime;		// 拍卖结束时间
	private Long deposit;		// 保证金
	private String description;		// 域名描述
	private String type;		// 域名类别
	private Long reservePrice;		// 保留价
	private Long appraisePrice;		// 估价
	private Long increment;		// 加价幅度
	private String status;		// 状态
	private Date waitTime;		// 操作有效期
	private Long bonusShareTotal;		// 转发送红包总额
	private Long bonusShareNumber;		// 转发送红包份数
	private Long bonusSecond;		// 次高价红包
	private String image1;		// 图片1
	private String image2;		// 图片2
	private String image3;		// 图片3
	private DyClient dyClient;  //会员表
	
	private String searchStartTime;
	private String searchEndTime;
	
	public DyDomainname() {
		super();
	}

	public DyDomainname(String id){
		super(id);
	}

	/**
	 * name的取得
	 * @return name
	 */
	@Length(min=1, max=100, message="域名名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	/**
	 * name的设定
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * clientId的取得
	 * @return clientId
	 */
	@Length(min=1, max=64, message="会员编号长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	/**
	 * clientId的设定
	 * @param clientId clientId
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * beginTime的取得
	 * @return beginTime
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * beginTime的设定
	 * @param beginTime beginTime
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * endTime的取得
	 * @return endTime
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="拍卖结束时间不能为空")
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * endTime的设定
	 * @param endTime endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * deposit的取得
	 * @return deposit
	 */
	public Long getDeposit() {
		return deposit;
	}

	/**
	 * deposit的设定
	 * @param deposit deposit
	 */
	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}

	/**
	 * description的取得
	 * @return description
	 */
	@Length(min=0, max=1024, message="域名描述长度必须介于 0 和 1024 之间")
	public String getDescription() {
		return description;
	}

	/**
	 * description的设定
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * type的取得
	 * @return type
	 */
	@Length(min=0, max=100, message="域名类别长度必须介于 0 和 100 之间")
	public String getType() {
		return type;
	}

	/**
	 * type的设定
	 * @param type type
	 */
	public void setType(String type) {
		this.type = type;
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

	/**
	 * appraisePrice的取得
	 * @return appraisePrice
	 */
	public Long getAppraisePrice() {
		return appraisePrice;
	}

	/**
	 * appraisePrice的设定
	 * @param appraisePrice appraisePrice
	 */
	public void setAppraisePrice(Long appraisePrice) {
		this.appraisePrice = appraisePrice;
	}

	/**
	 * increment的取得
	 * @return increment
	 */
	public Long getIncrement() {
		return increment;
	}

	/**
	 * increment的设定
	 * @param increment increment
	 */
	public void setIncrement(Long increment) {
		this.increment = increment;
	}

	/**
	 * status的取得
	 * @return status
	 */
	@Length(min=1, max=2, message="状态长度必须介于 1 和 2 之间")
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
	 * waitTime的取得
	 * @return waitTime
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWaitTime() {
		return waitTime;
	}

	/**
	 * waitTime的设定
	 * @param waitTime waitTime
	 */
	public void setWaitTime(Date waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * bonusShareTotal的取得
	 * @return bonusShareTotal
	 */
	public Long getBonusShareTotal() {
		return bonusShareTotal;
	}

	/**
	 * bonusShareTotal的设定
	 * @param bonusShareTotal bonusShareTotal
	 */
	public void setBonusShareTotal(Long bonusShareTotal) {
		this.bonusShareTotal = bonusShareTotal;
	}

	/**
	 * bonusShareNumber的取得
	 * @return bonusShareNumber
	 */
	public Long getBonusShareNumber() {
		return bonusShareNumber;
	}

	/**
	 * bonusShareNumber的设定
	 * @param bonusShareNumber bonusShareNumber
	 */
	public void setBonusShareNumber(Long bonusShareNumber) {
		this.bonusShareNumber = bonusShareNumber;
	}

	/**
	 * bonusSecond的取得
	 * @return bonusSecond
	 */
	public Long getBonusSecond() {
		return bonusSecond;
	}

	/**
	 * bonusSecond的设定
	 * @param bonusSecond bonusSecond
	 */
	public void setBonusSecond(Long bonusSecond) {
		this.bonusSecond = bonusSecond;
	}

	/**
	 * image1的取得
	 * @return image1
	 */
	@Length(min=0, max=500, message="图片1 地址长度必须介于 0 和 500 之间")
	public String getImage1() {
		return image1;
	}

	/**
	 * image1的设定
	 * @param image1 image1
	 */
	public void setImage1(String image1) {
		this.image1 = image1;
	}

	/**
	 * image2的取得
	 * @return image2
	 */
	@Length(min=0, max=500, message="图片2 地址长度必须介于 0 和 500 之间")
	public String getImage2() {
		return image2;
	}

	/**
	 * image2的设定
	 * @param image2 image2
	 */
	public void setImage2(String image2) {
		this.image2 = image2;
	}

	/**
	 * image3的取得
	 * @return image3
	 */
	@Length(min=0, max=500, message="图片3 地址长度必须介于 0 和 500 之间")
	public String getImage3() {
		return image3;
	}

	/**
	 * image3的设定
	 * @param image3 image3
	 */
	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String toString(){
		String paramsStr = "";
		paramsStr += "域名名称：" + name;
		paramsStr += "  拍卖结束时间：" + endTime;
		paramsStr += "  域名描述：" + description;
		paramsStr += "  保留价：" + reservePrice;
		paramsStr += "  状态：" + DySysUtils.transformDomainStatus(status);
		paramsStr += "  转发红包总额：" + bonusShareTotal;
		paramsStr += "  转发红包份数：" +  bonusShareNumber;
		paramsStr += "  次高价红包：" + bonusSecond;
		paramsStr += "  图片1：" + image1;
		paramsStr += "  图片2：" + image2;
		paramsStr += "  图片3：" + image3;
		paramsStr += "  备注：" + remarks;
		return paramsStr;
	}

	/**
	 * dyClient的取得
	 * @return dyClient
	 */
	public DyClient getDyClient() {
		return dyClient;
	}

	/**
	 * dyClient的设定
	 * @param dyClient dyClient
	 */
	public void setDyClient(DyClient dyClient) {
		this.dyClient = dyClient;
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