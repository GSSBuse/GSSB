/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gb;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 卖标评论管理Entity
 * @author 管理员
 * @version 2017-11-05
 */
public class GbSoldComments extends DataEntity<GbSoldComments> {
	
	private static final long serialVersionUID = 1L;
	private String gbclientId;		// gbclient_id
	private String buyId;		// buy_id
	private String comments;		// comments
	private Date createTime;		// create_time
	
	public GbSoldComments() {
		super();
	}

	public GbSoldComments(String id){
		super(id);
	}

	@Length(min=1, max=64, message="gbclient_id长度必须介于 1 和 64 之间")
	public String getGbclientId() {
		return gbclientId;
	}

	public void setGbclientId(String gbclientId) {
		this.gbclientId = gbclientId;
	}
	
	@Length(min=1, max=64, message="buy_id长度必须介于 1 和 64 之间")
	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}
	
	@Length(min=0, max=200, message="comments长度必须介于 0 和 200 之间")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}