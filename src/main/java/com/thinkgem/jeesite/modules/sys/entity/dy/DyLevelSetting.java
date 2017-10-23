/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 加价与保证金增幅Entity
 * @author quanyf.fnst
 * @version 2015-10-29
 */
public class DyLevelSetting extends DataEntity<DyLevelSetting> {
	
	private static final long serialVersionUID = 1L;
	private Long level;		// 出价
	private Long price;		// 增幅
	private String type;		// 类型
	
	public DyLevelSetting() {
		super();
	}

	public DyLevelSetting(String id){
		super(id);
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}
	
	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}