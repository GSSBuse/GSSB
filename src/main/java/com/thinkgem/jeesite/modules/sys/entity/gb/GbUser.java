/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gb;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户信息管理Entity
 * @author 管理员
 * @version 2017-11-06
 */
public class GbUser extends DataEntity<GbUser> {
	
	private static final long serialVersionUID = 1L;
	private String username;		// 用户名
	private String password;		// 密码
	private String name;		// 姓名
	private String mobile;		// 手机号
	private String email;		// 邮箱
	private String wechat;		// 微信
	private String qq;		// qq
	private String zfb;		// 支付宝帐号
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String isDelete;		// 是否删除
	
	public GbUser() {
		super();
	}

	public GbUser(String id){
		super(id);
	}

	@Length(min=1, max=100, message="用户名长度必须介于 1 和 100 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=200, message="密码长度必须介于 1 和 200 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=1, max=200, message="姓名长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="手机号长度必须介于 1 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=100, message="邮箱长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=100, message="微信长度必须介于 0 和 100 之间")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	@Length(min=0, max=20, message="qq长度必须介于 0 和 20 之间")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Length(min=0, max=100, message="支付宝帐号长度必须介于 0 和 100 之间")
	public String getZfb() {
		return zfb;
	}

	public void setZfb(String zfb) {
		this.zfb = zfb;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=1, max=2, message="是否删除长度必须介于 1 和 2 之间")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}