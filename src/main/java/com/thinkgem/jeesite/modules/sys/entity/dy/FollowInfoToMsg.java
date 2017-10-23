package com.thinkgem.jeesite.modules.sys.entity.dy;

import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用于记录某个域名关注者的openId,结拍最后一小时发消息用
 * @author wufl.fnst
 *
 */
public class FollowInfoToMsg extends DataEntity<FollowInfoToMsg>{
	String domainname = null;//域名名称
	String domainnameId = null;//域名名称
	String openId = null;//域名关注者openId
	Date min60 = null;//距离结拍60分钟
	Date min59 = null;//距离结拍59分钟
	
	public String getDomainnameId() {
		return domainnameId;
	}
	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	public Date getMin60() {
		return min60;
	}
	public void setMin60(Date min60) {
		this.min60 = min60;
	}
	public Date getMin59() {
		return min59;
	}
	public void setMin59(Date min59) {
		this.min59 = min59;
	}
	public String getDomainname() {
		return domainname;
	}
	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
