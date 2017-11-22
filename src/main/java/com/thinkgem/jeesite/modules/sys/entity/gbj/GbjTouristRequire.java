/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 游客查询管理Entity
 * @author snnu
 * @version 2017-11-20
 */
public class GbjTouristRequire extends DataEntity<GbjTouristRequire> {
	
	private static final long serialVersionUID = 1L;
	private String searchContents;		// 查询内容
	private String name;		// 姓名
	private String mobile;		// 电话
	private String isCalled;		// 是否电话回访
	
	public GbjTouristRequire() {
		super();
	}

	public GbjTouristRequire(String id){
		super(id);
	}

	@Length(min=0, max=500, message="查询内容长度必须介于 0 和 500 之间")
	public String getSearchContents() {
		return searchContents;
	}

	public void setSearchContents(String searchContents) {
		this.searchContents = searchContents;
	}
	
	@Length(min=0, max=200, message="姓名长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="电话长度必须介于 0 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=1, message="是否电话回访长度必须介于 0 和 1 之间")
	public String getIsCalled() {
		return isCalled;
	}

	public void setIsCalled(String isCalled) {
		this.isCalled = isCalled;
	}
	
}