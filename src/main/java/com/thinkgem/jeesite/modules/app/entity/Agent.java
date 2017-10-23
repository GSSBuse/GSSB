/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */

package com.thinkgem.jeesite.modules.app.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.wx.entity.WxInterfaceInfo;

/**
 * 企业应用Entity
 * @author shenzb.fnst
 * @version 2015-07-29
 */
public class Agent extends DataEntity<WxInterfaceInfo> {

	private static final long serialVersionUID = 1L;
	/** 应用id */
	private String agentid;
	/** 应用名称 */
	private String name;
	/** 方形头像url */
	private String square_logo_url;
	/** 圆形头像url */
	private String round_logo_url;
	/**
	 * @return the agentid
	 */
	public String getAgentid() {
		return agentid;
	}
	/**
	 * @param agentid the agentid to set
	 */
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the square_logo_url
	 */
	public String getSquare_logo_url() {
		return square_logo_url;
	}
	/**
	 * @param square_logo_url the square_logo_url to set
	 */
	public void setSquare_logo_url(String square_logo_url) {
		this.square_logo_url = square_logo_url;
	}
	/**
	 * @return the round_logo_url
	 */
	public String getRound_logo_url() {
		return round_logo_url;
	}
	/**
	 * @param round_logo_url the round_logo_url to set
	 */
	public void setRound_logo_url(String round_logo_url) {
		this.round_logo_url = round_logo_url;
	}
}
