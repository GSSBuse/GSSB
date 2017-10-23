/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 平台收入管理Entity
 * @author quanyf.fnst
 * @version 2015-12-15
 */
public class DyPlatformIncome extends DataEntity<DyPlatformIncome> {
	
	private static final long serialVersionUID = 1L;
	private String domainnameId;		// 域名id
	private String buyClientId;		// 买家id
	private String operate;		// 操作
	private Long operateAmount;		// 操作金额
	private Long totalAmount;		// 总收入额
	
	private DyDomainname dyDomainname;
	private DyClient buyClient;
	
	public DyPlatformIncome() {
		super();
	}

	public DyPlatformIncome(String id){
		super(id);
	}

	@Length(min=1, max=64, message="域名id长度必须介于 1 和 64 之间")
	public String getDomainnameId() {
		return domainnameId;
	}

	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
	}
	
	@Length(min=0, max=64, message="买家id长度必须介于 0 和 64 之间")
	public String getBuyClientId() {
		return buyClientId;
	}

	public void setBuyClientId(String buyClientId) {
		this.buyClientId = buyClientId;
	}
	
	@Length(min=1, max=100, message="操作长度必须介于 1 和 100 之间")
	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}
	
	@NotNull(message="操作金额不能为空")
	public Long getOperateAmount() {
		return operateAmount;
	}

	public void setOperateAmount(Long operateAmount) {
		this.operateAmount = operateAmount;
	}
	
	@NotNull(message="总收入额不能为空")
	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public DyDomainname getDyDomainname() {
		return dyDomainname;
	}

	public void setDyDomainname(DyDomainname dyDomainname) {
		this.dyDomainname = dyDomainname;
	}

	public DyClient getBuyClient() {
		return buyClient;
	}

	public void setBuyClient(DyClient buyClient) {
		this.buyClient = buyClient;
	}
	
}