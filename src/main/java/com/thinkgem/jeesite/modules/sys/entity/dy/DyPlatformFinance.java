/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 平台总账户管理Entity
 * @author quanyf.fnst
 * @version 2015-12-15
 */
public class DyPlatformFinance extends DataEntity<DyPlatformFinance> {
	
	private static final long serialVersionUID = 1L;
	private String clientId;		// 会员id
	private String operate;		// 操作
	private Long operateAmount;		// 操作金额
	private Long totalAmount;		// 账户总额
	
	private DyClient dyClient;
	
	public DyPlatformFinance() {
		super();
	}

	public DyPlatformFinance(String id){
		super(id);
	}

	@Length(min=1, max=64, message="会员id长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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
	
	@NotNull(message="账户总额不能为空")
	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public DyClient getDyClient() {
		return dyClient;
	}

	public void setDyClient(DyClient dyClient) {
		this.dyClient = dyClient;
	}
}