/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 标签类型Entity
 * @author snnu
 * @version 2018-01-08
 */
public class GbjTag extends DataEntity<GbjTag> {
	
	private static final long serialVersionUID = 1L;
	private String tagName;		// 标签名称
	
	public GbjTag() {
		super();
	}

	public GbjTag(String id){
		super(id);
	}

	@Length(min=0, max=200, message="标签名称长度必须介于 0 和 200 之间")
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
}