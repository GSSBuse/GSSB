package com.thinkgem.jeesite.modules.wx.entity.domainname;

import com.thinkgem.jeesite.common.persistence.DataEntity;
/**
 * 红包佣金和会员关联信息Entity
 * @author quanyf.fnst
 * @version 2015-12-02
 */
public class BonusClient extends DataEntity<BonusClient>{
	private String domainnameId;		// 域名id
	private String clientId;		// 会员id
	private Long money;		// 金额
	private String type;		// 收支类型
	private String clientPhoto;		//	会员头像
	private String clientNickname;		//会员昵称
	public String getDomainnameId() {
		return domainnameId;
	}
	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
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
	public String getClientPhoto() {
		return clientPhoto;
	}
	public void setClientPhoto(String clientPhoto) {
		this.clientPhoto = clientPhoto;
	}
	public String getClientNickname() {
		return clientNickname;
	}
	public void setClientNickname(String clientNickname) {
		this.clientNickname = clientNickname;
	}
	
}
