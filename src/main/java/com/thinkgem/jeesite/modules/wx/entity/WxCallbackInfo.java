/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 回调验证信息Entity
 * @author shenzb.fnst
 * @version 2015-08-04
 */
public class WxCallbackInfo extends DataEntity<WxCallbackInfo> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 企业编号
	private String corpid;		// 微信企业ID
	private String agentId;		// 应用ID
	private String agentName;		// 应用名称
	private String token;		// Token
	private String encodingAeskey;		// EncodingAESKey
	
	public WxCallbackInfo() {
		super();
	}

	public WxCallbackInfo(String id){
		super(id);
	}

	@NotNull(message="企业编号不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=1, max=100, message="微信企业ID长度必须介于 1 和 100 之间")
	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	
	@Length(min=1, max=64, message="应用ID长度必须介于 1 和 64 之间")
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
	@Length(min=0, max=100, message="应用名称长度必须介于 0 和 100 之间")
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	@Length(min=1, max=100, message="Token长度必须介于 1 和 100 之间")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Length(min=1, max=100, message="EncodingAESKey长度必须介于 1 和 100 之间")
	public String getEncodingAeskey() {
		return encodingAeskey;
	}

	public void setEncodingAeskey(String encodingAeskey) {
		this.encodingAeskey = encodingAeskey;
	}
	
}