/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wx.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 企业微信标签管理Entity
 * @author shenzb.fnst
 * @version 2015-08-17
 */
public class WxTag extends DataEntity<WxTag> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 所属企业
	private String name;		// 标签名称
	private String enname;		// 英文名称
	private String tagType;		// 标签类型
	private String dataScope;		// 数据范围
	private String isSys;		// 是否系统数据
	private String useable;		// 是否可用
	
	public WxTag() {
		super();
	}

	public WxTag(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=1, max=30, message="标签名称长度必须介于 1 和 30 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="英文名称长度必须介于 0 和 50 之间")
	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}
	
	@Length(min=0, max=255, message="标签类型长度必须介于 0 和 255 之间")
	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	
	@Length(min=0, max=1, message="数据范围长度必须介于 0 和 1 之间")
	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}
	
	@Length(min=0, max=64, message="是否系统数据长度必须介于 0 和 64 之间")
	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
	
	@Length(min=0, max=64, message="是否可用长度必须介于 0 和 64 之间")
	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}
	
}