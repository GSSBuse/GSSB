package com.thinkgem.jeesite.common.wx.bean;

/**
 * @author wufl.fnst
 *发送红包，微信返回信息的实体
 */
public class WxReturnWxRedpackResult {
	
	String return_code = null;//返回状态码  是	SUCCESS	String(16)	SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	String return_msg = null;//返回信息	否	签名失败	String(128)	返回信息，如非空，为错误原因	签名失败	参数格式校验错误
	
	//以下字段在return_code为SUCCESS的时候有返回
	String sign = null;//签名		是	C380BEC2BFD727A4B6845133519F3AD6	String(32)	生成签名方式详见签名生成算法
	String result_code = null;//业务结果		是	SUCCESS	String(16)	SUCCESS/FAIL
	String err_code = null;//错误代码		否	SYSTEMERROR	String(32)	错误码信息 
	String err_code_des = null;//错误代码描述	err_code_des	否	系统错误	String(128)	结果信息描述
	
	//以下字段在return_code和result_code都为SUCCESS的时候有返回
	String mch_billno = null;//商户订单号		是	10000098201411111234567890	String(28)	商户订单号（每个订单号必须唯一）	组成：mch_id+yyyymmdd+10位一天内不能重复的数字
	String mch_id = null;//商户号		是	10000098	String(32)	微信支付分配的商户号
	String wxappid = null;//公众账号appid		是	wx8888888888888888	String(32)	商户appid
	String re_openid = null;//用户openid		是	oxTWIuGaIt6gTKsQRLau2M0yL16E	String(32)	接受收红包的用户	用户在wxappid下的openid
	String total_amount = null;//付款金额		是	1000	int	付款金额，单位分
	String send_time = null;//发放成功时间		是	20150520102602	int	红包发送时间
	String send_listid = null;//微信单号		是	100000000020150520314766074200	String(32)	红包订单的微信单号
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
	public String getMch_billno() {
		return mch_billno;
	}
	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getWxappid() {
		return wxappid;
	}
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public String getSend_listid() {
		return send_listid;
	}
	public void setSend_listid(String send_listid) {
		this.send_listid = send_listid;
	}
	
	

}
