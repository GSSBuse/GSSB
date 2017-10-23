package com.thinkgem.jeesite.modules.wx.entity.domainname;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class ShareOrSecondBonus extends DataEntity<ShareOrSecondBonus> {
	
	Long money = null;
	String type = null;
	Date createDate = null;
	String domainname = null;
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDomainname() {
		return domainname;
	}
	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}
	

}
