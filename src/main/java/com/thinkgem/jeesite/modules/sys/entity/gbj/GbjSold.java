/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 卖标信息管理Entity
 * @author snnu
 * @version 2017-11-19
 */
public class GbjSold extends DataEntity<GbjSold> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户ID
	private String typeId;		// 国标类型
	private Long price;		// 预算价格
	private Long realprice;		// 实际成交价格
	private String linkman;		// 联系人
	private String mobile;		// 联系人手机号
	private String description;		// 国标描述
	private String title;		// 国标标题
	
	public GbjSold() {
		super();
	}

	public GbjSold(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=11, message="国标类型长度必须介于 0 和 11 之间")
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
	
	@Length(min=0, max=200, message="联系人长度必须介于 0 和 200 之间")
	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	
	@Length(min=0, max=200, message="联系人手机号长度必须介于 0 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=500, message="国标描述长度必须介于 0 和 500 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=200, message="国标标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}