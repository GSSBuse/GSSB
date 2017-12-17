/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 游客查询管理Entity
 * @author snnu
 * @version 2017-12-16
 */
public class GbjTouristRequire extends DataEntity<GbjTouristRequire> {
	
	private static final long serialVersionUID = 1L;
	private String searchContents;		// 查询内容
	private String typeId;		// 商标类型
	private String name;		// 姓名
	private String mobile;		// 电话
	private String email;		// 邮箱
	private String qq;		// QQ
	private String isVisit;		// 是否回访
	private String visitWay;		// 回访方式
	private String visitRemarks;		// 回访备注
	
	public GbjTouristRequire() {
		super();
	}

	public GbjTouristRequire(String id){
		super(id);
	}

	@Length(min=1, max=500, message="查询内容长度必须介于 1 和 500 之间")
	public String getSearchContents() {
		return searchContents;
	}

	public void setSearchContents(String searchContents) {
		this.searchContents = searchContents;
	}
	
	@Length(min=0, max=64, message="商标类型长度必须介于 0 和 64 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=1, max=200, message="姓名长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=200, message="电话长度必须介于 1 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=1, max=200, message="邮箱长度必须介于 1 和 200 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=1, max=200, message="QQ长度必须介于 1 和 200 之间")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Length(min=0, max=1, message="是否回访长度必须介于 0 和 1 之间")
	public String getIsVisit() {
		return isVisit;
	}

	public void setIsVisit(String isVisit) {
		this.isVisit = isVisit;
	}
	
	@Length(min=0, max=200, message="回访方式长度必须介于 0 和 200 之间")
	public String getVisitWay() {
		return visitWay;
	}

	public void setVisitWay(String visitWay) {
		this.visitWay = visitWay;
	}
	
	@Length(min=0, max=500, message="回访备注长度必须介于 0 和 500 之间")
	public String getVisitRemarks() {
		return visitRemarks;
	}

	public void setVisitRemarks(String visitRemarks) {
		this.visitRemarks = visitRemarks;
	}
	
}