package com.thinkgem.jeesite.common.wx.bean;

public class WxReturnUnifiedOrder {
	
	//返回结果
	String return_code = null;//返回状态码 String(16)	SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	String return_msg = null;//	返回信息	String(128):如非空，为错误原因	如签名失败、参数格式校验错误
	
	//以下字段在return_code为SUCCESS的时候有返回
	String appid =null;//公众账号ID String(32) 调用接口提交的公众账号ID
	String mch_id = null;//商户号String(32)	调用接口提交的商户号
	String device_info = null;//设备号 String(32)	调用接口提交的终端设备号，
	String nonce_str = null;//随机字符串	String(32)	微信返回的随机字符串
	String sign = null;//微信返回的签名
	String result_code = null;//业务结果 String(16)	SUCCESS/FAIL
	String err_code = null;//错误代码	 String(32)	SYSTEMERROR	详细参见第6节错误列表（微信支付开发文档）
	String err_code_des = null;//错误代码描述	String(128):错误返回的信息描述
	
	//以下字段在return_code 和result_code都为SUCCESS的时候有返回
	String trade_type = null;//交易类型 String(16):调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
	String prepay_id = null;//预支付交易会话标识 String(64)	 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	String code_url = null;//二维码链接	String(64):	URl：weixin：//wxpay/s/An4baqw trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getCode_url() {
		return code_url;
	}
	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
}
