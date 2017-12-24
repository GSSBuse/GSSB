package com.thinkgem.jeesite.modules.paimai.front.bean;

import java.io.Serializable;

/**
 * 前台登录用户信息
 * 
 * @author chen
 * @date 2017年12月23日
 */
public class FrontLoginUser implements Serializable {

	private static final long serialVersionUID = -3117367988004477240L;

	private String id;

	private String username;

	private String name;

	private String mobile;

	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}