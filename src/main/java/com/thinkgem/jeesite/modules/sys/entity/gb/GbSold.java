/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gb;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 卖标信息管理Entity
 * @author 管理员
 * @version 2017-12-17
 */
public class GbSold extends DataEntity<GbSold> {
	
	private static final long serialVersionUID = 1L;
	private String gbclientId;		// gbclient_id
	private String typeId;		// type_id
	private Long price;		// price
	private Long realprice;		// realprice
	private String connacts;		// connacts
	private String mobile;		// mobile
	private String description;		// description
	private String title;		// title
	private Date createTime;		// create_time
	private Date dealTime;		// deal_time
	private String status;		// status
	
	public GbSold() {
		super();
	}

	public GbSold(String id){
		super(id);
	}

	@Length(min=0, max=64, message="gbclient_id长度必须介于 0 和 64 之间")
	public String getGbclientId() {
		return gbclientId;
	}

	public void setGbclientId(String gbclientId) {
		this.gbclientId = gbclientId;
	}
	
	@Length(min=0, max=10, message="type_id长度必须介于 0 和 10 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
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
	
	@Length(min=0, max=50, message="connacts长度必须介于 0 和 50 之间")
	public String getConnacts() {
		return connacts;
	}

	public void setConnacts(String connacts) {
		this.connacts = connacts;
	}
	
	@Length(min=0, max=20, message="mobile长度必须介于 0 和 20 之间")
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
	
	@Length(min=0, max=20, message="title长度必须介于 0 和 20 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	
	@Length(min=0, max=1, message="status长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}