/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.gbj;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 历史交易量Entity
 * @author snnu
 * @version 2018-01-11
 */
public class GbjBusinessNumber extends DataEntity<GbjBusinessNumber> {
	
	private static final long serialVersionUID = 1L;
	private Long shangbiaoNumber;		// 商标交易量
	private Long businessNumber;		// 交易数量
	private Long banquanNumber;		// 版权交易量
	private Long zhuanliNumber;		// 专利交易量
	
	public GbjBusinessNumber() {
		super();
	}

	public GbjBusinessNumber(String id){
		super(id);
	}

	public Long getShangbiaoNumber() {
		return shangbiaoNumber;
	}

	public void setShangbiaoNumber(Long shangbiaoNumber) {
		this.shangbiaoNumber = shangbiaoNumber;
	}
	
	public Long getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(Long businessNumber) {
		this.businessNumber = businessNumber;
	}
	
	public Long getBanquanNumber() {
		return banquanNumber;
	}

	public void setBanquanNumber(Long banquanNumber) {
		this.banquanNumber = banquanNumber;
	}
	
	public Long getZhuanliNumber() {
		return zhuanliNumber;
	}

	public void setZhuanliNumber(Long zhuanliNumber) {
		this.zhuanliNumber = zhuanliNumber;
	}
	
}