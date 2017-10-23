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
	
	public DyDomainname() {
		super();
	}

	public DyDomainname(String id){
		super(id);
	}

	@Length(min=1, max=100, message="域名名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="会员编号长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="拍卖结束时间不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Long getDeposit() {
		return deposit;
	}

	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
	
	@Length(min=0, max=1024, message="域名描述长度必须介于 0 和 1024 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=100, message="域名类别长度必须介于 0 和 100 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Long getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(Long reservePrice) {
		this.reservePrice = reservePrice;
	}
	
	public Long getAppraisePrice() {
		return appraisePrice;
	}

	public void setAppraisePrice(Long appraisePrice) {
		this.appraisePrice = appraisePrice;
	}
	
	public Long getIncrement() {
		return increment;
	}

	public void setIncrement(Long increment) {
		this.increment = increment;
	}
	
	@Length(min=1, max=2, message="状态长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(Date waitTime) {
		this.waitTime = waitTime;
	}
	
	public Long getBonusShareTotal() {
		return bonusShareTotal;
	}

	public void setBonusShareTotal(Long bonusShareTotal) {
		this.bonusShareTotal = bonusShareTotal;
	}
	
	public Long getBonusShareNumber() {
		return bonusShareNumber;
	}

	public void setBonusShareNumber(Long bonusShareNumber) {
		this.bonusShareNumber = bonusShareNumber;
	}
	
	public Long getBonusSecond() {
		return bonusSecond;
	}

	public void setBonusSecond(Long bonusSecond) {
		this.bonusSecond = bonusSecond;
	}
	
	@Length(min=0, max=100, message="图片1长度必须介于 0 和 100 之间")
	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}
	
	@Length(min=0, max=100, message="图片2长度必须介于 0 和 100 之间")
	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	@Length(min=0, max=100, message="图片3长度必须介于 0 和 100 之间")
	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public DyClient getDyClient() {
		return dyClient;
	}

	public void setDyClient(DyClient dyClient) {
		this.dyClient = dyClient;
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
}