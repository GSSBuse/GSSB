/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gb;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 买标信息管理Entity
 * @author 管理员
 * @version 2017-11-02
 */
public class GbBuy extends DataEntity<GbBuy> {
	
	private static final long serialVersionUID = 1L;
	private String gbclientId;		// 用户id
	private String typeId;		// 国标类型1:商标2:版权3:专利
	private String name;		// name
	private Long price;		// prices
	private Long realprice;		// 国标实际成交价格
	private String connacts;		// connacts
	private String mobile;		// mobile
	private String description;		// description
	private String title;		// title
	private Date createTime;		// create_time
	private String status;		// status
	private Date dealTime;		// 国标成交时间
	
	public GbBuy() {
		super();
	}

	public GbBuy(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户id长度必须介于 0 和 64 之间")
	public String getGbclientId() {
		return gbclientId;
	}

	public void setGbclientId(String gbclientId) {
		this.gbclientId = gbclientId;
	}
	
	@Length(min=0, max=1, message="国标类型1:商标2:版权3:专利长度必须介于 0 和 1 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="price不能为空")
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Long getRealprice() {
		return realprice;
	}

	public void setRealprice(Long realprice) {
		this.realprice = realprice;
	}
	
	@Length(min=1, max=50, message="connacts长度必须介于 1 和 50 之间")
	public String getConnacts() {
		return connacts;
	}

	public void setConnacts(String connacts) {
		this.connacts = connacts;
	}
	
	@Length(min=1, max=20, message="mobile长度必须介于 1 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=200, message="description长度必须介于 0 和 200 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=1, max=20, message="title长度必须介于 1 和 20 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="create_time不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=1, max=1, message="status长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	
}