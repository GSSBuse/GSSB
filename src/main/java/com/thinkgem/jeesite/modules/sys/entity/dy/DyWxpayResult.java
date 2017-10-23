/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信支付结果管理Entity
 * @author quanyf.fnst
 * @version 2015-11-05
 */
public class DyWxpayResult extends DataEntity<DyWxpayResult> {
	
	private static final long serialVersionUID = 1L;
	private String openid;		// 会员openid
	private String sign;		// 签名
	private String outTradeNo;		// 商户订单号
	private Long totalFee;		// 交易总额
	private String transactionId;		// 微信支付订单号
	private Date timeEnd;		// 支付完成时间
	private String resultCode;		// 支付结果
	private String status;		// 处理状态
	
	public DyWxpayResult() {
		super();
	}

	public DyWxpayResult(String id){
		super(id);
	}

	@Length(min=1, max=200, message="会员openid长度必须介于 1 和 200 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=1, max=32, message="签名长度必须介于 1 和 32 之间")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@Length(min=1, max=32, message="商户订单号长度必须介于 1 和 32 之间")
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	@NotNull(message="交易总额不能为空")
	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}
	
	@Length(min=0, max=32, message="微信支付订单号长度必须介于 0 和 32 之间")
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	@Length(min=0, max=10, message="支付结果长度必须介于 0 和 10 之间")
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	@Length(min=1, max=1, message="处理状态长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}