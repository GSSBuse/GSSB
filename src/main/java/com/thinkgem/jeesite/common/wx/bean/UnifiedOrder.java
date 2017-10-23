package com.thinkgem.jeesite.common.wx.bean;

import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.common.wx.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**统一下单时,存储传递参数的bean
 * @author wufl.fnst
 *
 */
public class UnifiedOrder {
	String appid = null;//公众账号ID,不能为空，必填项，32位：微信分配的公众账号ID（企业号corpid即为此appId
	String mch_id = null;//商户号,不能为空，必填项，最大32位	：微信支付分配的商户号
	String device_info = null;//设备号,可空，最大32位：	终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	String nonce_str = null;//随机字符串，不能为空，必填项，最大32位：随机字符串，不长于32位。推荐随机数生成算法
	String sign = null;//签名，不能为空，必填项，最大32位
	String body = null;//商品描述,不能为空，必填项，最大32位：商品或支付单简要描述
	String detail = null;//商品详情,可空，最大8192位:商品名称明细列表
	String attach = null;//附加数据,可空，最大127位:附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	String out_trade_no = null;//商户订单号，不能为空，必填项，最大32位：商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
	String fee_type = null;//货币类型,可空，最大16位:CNY	符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	Long total_fee = Long.parseLong("0");//总金额,不能为空，必填项:Int	888	订单总金额，单位为分，详见支付金额
	String spbill_create_ip = null;//终端IP,不能为空，必填项,最大16位：APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	String time_start = null;//交易起始时间，可为空，String(14)：	订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	String time_expire = null;//交易结束时间，可为空，String(14)：订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则，注意：最短失效时间间隔必须大于5分钟
	String goods_tag = null;//商品标记,可为空,String(32):WXG	商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
	String notify_url = null;//通知地址，不能为空，必填项，String(256)：http://www.weixin.qq.com/wxpay/pay.php	接收微信支付异步通知回调地址
	String trade_type = null;//交易类型,不能为空，必填项，String(16):取值如下：JSAPI，NATIVE，APP，详细说明见参数规定
	String product_id = null;//商品ID,可为空,String(32):trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	String limit_pay = null;//指定支付方式,可为空,String(32):no_credit--指定不能使用信用卡支付
	String openid = null;//用户标识,可为空,String(128):trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
	
	/**
	 * 
	 */
	public UnifiedOrder(){
		super();
	}
	/**
	 * 构造函数，传入值为必填项
	 * @param appid
	 * @param mch_id
	 * @param nonce_str
	 * @param sign
	 * @param body
	 * @param out_trade_no
	 * @param total_fee
	 * @param spbill_create_ip
	 * @param notify_url
	 * @param trade_type
	 * @param openid
	 */
	public UnifiedOrder(String appid,String mch_id,String nonce_str,String sign,String body,String out_trade_no,Long total_fee,String spbill_create_ip,String notify_url,String trade_type,String openid){
		this.appid = appid;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.body = body;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
		this.openid = openid;
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public Long getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Long total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getLimit_pay() {
		return limit_pay;
	}
	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
