/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资金流信息Entity
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class DyCashFlow extends DataEntity<DyCashFlow> {
	
	private static final long serialVersionUID = 1L;
	private String clientId;		// 会员id
	private String domainnameId;	//域名id
	private String operate;			// 操作
	private Long operateAmount;		// 操作金额
	private Date operateTime;		// 操作时间
	private String confirmResult;	// 经纪人确认结果
	private Long amountBalance;		// 操作后余额
	private DyClient dyClient;
	private DyDomainname dyDomainname;
	public DyCashFlow() {
		super();
	}
	public String toString(){
		String paramsStr = "";
		paramsStr += "ID：" + getId();
		paramsStr += "会员米友号：" + dyClient.getDyid();
		paramsStr += "  会员姓名：" + dyClient.getName();
		paramsStr += "  会员昵称：" + dyClient.getNickname();
		paramsStr += "  银行账号：" + dyClient.getDefaultIncomeExpense();
		paramsStr += "  操作：" + operate;
		paramsStr += "  操作金额：" + operateAmount;
		paramsStr += "	操作时间：" + operateTime;
		paramsStr += "  财务确认结果：" + confirmResult;
		paramsStr += "  更新时间：" + updateDate;
		paramsStr += "  备注：" + remarks;
		return paramsStr;
	}
	public DyCashFlow(String id){
		super(id);
	}

	@Length(min=1, max=64, message="会员id长度必须介于 1 和 64 之间")
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@Length(min=1, max=64, message="域名id长度必须介于 1 和 64 之间")
	public String getDomainnameId() {
		return domainnameId;
	}

	public void setDomainnameId(String domainnameId) {
		this.domainnameId = domainnameId;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="操作时间不能为空")
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	public String getConfirmResult() {
		return confirmResult;
	}

	public void setConfirmResult(String confirmResult) {
		this.confirmResult = confirmResult;
	}

	public Long getAmountBalance() {
		return amountBalance;
	}

	public void setAmountBalance(Long amountBalance) {
		this.amountBalance = amountBalance;
	}

	public DyClient getDyClient() {
		return dyClient;
	}

	public void setDyClient(DyClient dyClient) {
		this.dyClient = dyClient;
	}

	public DyDomainname getDyDomainname() {
		return dyDomainname;
	}

	public void setDyDomainname(DyDomainname dyDomainname) {
		this.dyDomainname = dyDomainname;
	}
	
}