package com.thinkgem.jeesite.common.wx.bean;

/**
 * @author wufl.fnst
 *发送红包时的参数实体
 */
public class WxRedpack {
	String nonce_str = null;//随机字符串 5K8264ILTKCH16CQ2502SI8ZNMTM67VS	String(32)	随机字符串，不长于32位
	String sign = null;//签名 必须字段 C380BEC2BFD727A4B6845133519F3AD6	String(32)	详见签名生成算法
	String mch_billno = null;//商户订单号	 必须字段	10000098201411111234567890	String(28)	 商户订单号（每个订单号必须唯一）	 组成：mch_id+yyyymmdd+10位一天内不能重复的数字。	Strin 接口根据商户订单号支持重入，如出现超时可再调用。
	String mch_id = null;//商户号  必须字段	10000098	String(32)	微信支付分配的商户号
	String wxappid = null;//公众账号appid	  必须字段	wx8888888888888888	String(32)	微信分配的公众账号ID（企业号corpid即为此appId）
	String send_name = null;//商户名称 必须字段	天虹百货	String(32)	红包发送者名称
	String re_openid = null;//用户openid 	必须字段	oxTWIuGaIt6gTKsQRLau2M0yL16E	String(32)	 接受红包的用户	用户在wxappid下的openid
	int total_amount = 0;//付款金额	必须字段	1000	int	付款金额，单位分
	int total_num = 1;//红包发放总人数	必须字段	1	int	红包发放总人数
	String wishing = null;//红包祝福语 必须字段	感谢您参加猜灯谜活动，祝您元宵节快乐！	String(128)	红包祝福语
	String client_ip = null;//Ip地址	 必须字段	192.168.0.1	String(15)	调用接口的机器Ip地址
	String act_name = null;//活动名称	 必须字段	猜灯谜抢红包活动	String(32)	活动名称
	String remark = null;//备注	必须字段	猜越多得越多，快来抢！	String(256)	备注信息
	
	
	
	public WxRedpack(String nonce_str, String sign, String mch_billno,
			String mch_id, String wxappid, String send_name, String re_openid,
			int total_amount, int total_num, String wishing, String client_ip,
			String act_name, String remark) {
		super();
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.mch_billno = mch_billno;
		this.mch_id = mch_id;
		this.wxappid = wxappid;
		this.send_name = send_name;
		this.re_openid = re_openid;
		this.total_amount = total_amount;
		this.total_num = total_num;
		this.wishing = wishing;
		this.client_ip = client_ip;
		this.act_name = act_name;
		this.remark = remark;
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
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public int getTotal_num() {
		return total_num;
	}
	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}
	public String getWishing() {
		return wishing;
	}
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
