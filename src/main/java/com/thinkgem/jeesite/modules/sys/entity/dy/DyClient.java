/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity.dy;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 会员信息管理Entity
 * @author shenzb.fnst
 * @version 2015-10-12
 */
public class DyClient extends DataEntity<DyClient> {
	
	private static final long serialVersionUID = 1L;
	private String dyid;		// 米友号
	private String openid;		// 微信标识
	private String name;		// 姓名
	private String nickname;		// 微信昵称
	private String email;		// 邮箱
	private String payPassword;	//支付密码
	private String mobile;		// 手机
	private String qq;		// QQ号
	private String wx;		// 微信号
	private String photo;		// 微信头像
	private String vip;		// 会员等级
	private String avoidDeposit = "0";		// 是否免除保证金（1:是,0:否）
	private String idcardNumber;	//身份证号码
	private String sealFlag;		// 封号标记
	private String emailFlag;		//邮箱人认证
	private String brokerId;		// 所属经纪人id
	private String authenticationMark;		// 身份认证
	private String defaultIncomeExpense;		// 默认收支方式
	private String authenticationPositiveImageUrl;		// 身份证正面照片
	private String authenticationNegativeImageUrl;		// 身份证反面照片
	
	private String newPayPassword;  // 新密码
	
	private User broker;
	private DyFinance dyFinance;
	public DyClient() {
		super();
	}

	public DyClient(String id){
		super(id);
	}

	/**
	 * dyid的取得
	 * @return dyid
	 */
	@Length(min=1, max=200, message="米友号长度必须介于 1 和 200 之间")
	public String getDyid() {
		return dyid;
	}

	/**
	 * dyid的设定
	 * @param dyid dyid
	 */
	public void setDyid(String dyid) {
		this.dyid = dyid;
	}

	/**
	 * openid的取得
	 * @return openid
	 */
	@Length(min=1, max=200, message="微信标识长度必须介于 1 和 200 之间")
	public String getOpenid() {
		return openid;
	}

	/**
	 * openid的设定
	 * @param openid openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * name的取得
	 * @return name
	 */
	@Length(min=0, max=100, message="姓名长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	/**
	 * name的设定
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * nickname的取得
	 * @return nickname
	 */
	@Length(min=1, max=100, message="微信昵称长度必须介于 1 和 100 之间")
	public String getNickname() {
		return nickname;
	}

	/**
	 * nickname的设定
	 * @param nickname nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * email的取得
	 * @return email
	 */
	@Length(min=0, max=200, message="邮箱长度必须介于 0 和 200 之间")
	public String getEmail() {
		return email;
	}

	/**
	 * email的设定
	 * @param email email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * payPassword的取得
	 * @return payPassword
	 */
	@Length(min=0, max=100, message="支付密码长度必须介于 0 和 100 之间")
	public String getPayPassword() {
		return payPassword;
	}

	/**
	 * payPassword的设定
	 * @param payPassword payPassword
	 */
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	/**
	 * mobile的取得
	 * @return mobile
	 */
	@Length(min=0, max=200, message="手机长度必须介于 0 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	/**
	 * mobile的设定
	 * @param mobile mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * qq的取得
	 * @return qq
	 */
	@Length(min=0, max=200, message="QQ号长度必须介于 0 和 200 之间")
	public String getQq() {
		return qq;
	}

	/**
	 * qq的设定
	 * @param qq qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * wx的取得
	 * @return wx
	 */
	@Length(min=0, max=200, message="微信号长度必须介于 0 和 200 之间")
	public String getWx() {
		return wx;
	}

	/**
	 * wx的设定
	 * @param wx wx
	 */
	public void setWx(String wx) {
		this.wx = wx;
	}

	/**
	 * photo的取得
	 * @return photo
	 */
	@Length(min=1, max=1000, message="微信头像长度必须介于 1 和 1000 之间")
	public String getPhoto() {
		return photo;
	}

	/**
	 * photo的设定
	 * @param photo photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * vip的取得
	 * @return vip
	 */
	@Length(min=1, max=11, message="会员等级长度必须介于 1 和 11 之间")
	public String getVip() {
		return vip;
	}

	/**
	 * vip的设定
	 * @param vip vip
	 */
	public void setVip(String vip) {
		this.vip = vip;
	}

	/**
	 * 是否免除保证金（1:是,0:否）
	 * @return avoidDeposit
	 */
	public String getAvoidDeposit() {
	    return avoidDeposit;
	}

	/**
	 * 是否免除保证金（1:是,0:否）
	 * @param avoidDeposit avoidDeposit
	 */
	public void setAvoidDeposit(String avoidDeposit) {
	    this.avoidDeposit = avoidDeposit;
	}

	/**
	 * sealFlag的取得
	 * @return sealFlag
	 */
	@Length(min=1, max=1, message="封号标记长度必须介于 1 和 1 之间")
	public String getSealFlag() {
		return sealFlag;
	}

	/**
	 * sealFlag的设定
	 * @param sealFlag sealFlag
	 */
	public void setSealFlag(String sealFlag) {
		this.sealFlag = sealFlag;
	}

	/**
	 * emailFlag的取得
	 * @return emailFlag
	 */
	@Length(min=1, max=1, message="邮箱认证长度必须介于 1 和 1 之间")
	public String getEmailFlag() {
		return emailFlag;
	}

	/**
	 * emailFlag的设定
	 * @param emailFlag emailFlag
	 */
	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	/**
	 * brokerId的取得
	 * @return brokerId
	 */
	@Length(min=1, max=64, message="所属经纪人id长度必须介于 1 和 64 之间")
	public String getBrokerId() {
		return brokerId;
	}

	/**
	 * brokerId的设定
	 * @param brokerId brokerId
	 */
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	@Length(max=18, message="身份证号码必须为18位")
	public String getIDcardNumber() {
		return idcardNumber;
	}

	public void setIDcardNumber(String iDcardNumber) {
		idcardNumber = iDcardNumber;
	}

	/**
	 * authenticationMark的取得
	 * @return authenticationMark
	 */
	@Length(min=0, max=11, message="身份认证长度必须介于 0 和 11 之间")
	public String getAuthenticationMark() {
		return authenticationMark;
	}

	/**
	 * authenticationMark的设定
	 * @param authenticationMark authenticationMark
	 */
	public void setAuthenticationMark(String authenticationMark) {
		this.authenticationMark = authenticationMark;
	}

	/**
	 * defaultIncomeExpense的取得
	 * @return defaultIncomeExpense
	 */
	@Length(min=0, max=100, message="默认收支方式长度必须介于 0 和 100 之间")
	public String getDefaultIncomeExpense() {
		return defaultIncomeExpense;
	}

	/**
	 * defaultIncomeExpense的设定
	 * @param defaultIncomeExpense defaultIncomeExpense
	 */
	public void setDefaultIncomeExpense(String defaultIncomeExpense) {
		this.defaultIncomeExpense = defaultIncomeExpense;
	}

	/**
	 * authenticationPositiveImageUrl的取得
	 * @return authenticationPositiveImageUrl
	 */
	@Length(min=0, max=100, message="身份证正面照片长度必须介于 0 和 100 之间")
	public String getAuthenticationPositiveImageUrl() {
		return authenticationPositiveImageUrl;
	}

	/**
	 * authenticationPositiveImageUrl的设定
	 * @param authenticationPositiveImageUrl authenticationPositiveImageUrl
	 */
	public void setAuthenticationPositiveImageUrl(String authenticationPositiveImageUrl) {
		this.authenticationPositiveImageUrl = authenticationPositiveImageUrl;
	}

	/**
	 * authenticationNegativeImageUrl的取得
	 * @return authenticationNegativeImageUrl
	 */
	@Length(min=0, max=100, message="身份证反面照片长度必须介于 0 和 100 之间")
	public String getAuthenticationNegativeImageUrl() {
		return authenticationNegativeImageUrl;
	}

	/**
	 * authenticationNegativeImageUrl的设定
	 * @param authenticationNegativeImageUrl authenticationNegativeImageUrl
	 */
	public void setAuthenticationNegativeImageUrl(String authenticationNegativeImageUrl) {
		this.authenticationNegativeImageUrl = authenticationNegativeImageUrl;
	}

	/**
	 * broker的取得
	 * @return broker
	 */
	public User getBroker() {
		return broker;
	}

	/**
	 * broker的设定
	 * @param broker broker
	 */
	public void setBroker(User broker) {
		this.broker = broker;
	}

	/**
	 * dyFinance的取得
	 * @return dyFinance
	 */
	public DyFinance getDyFinance() {
		return dyFinance;
	}

	/**
	 * dyFinance的设定
	 * @param dyFinance dyFinance
	 */
	public void setDyFinance(DyFinance dyFinance) {
		this.dyFinance = dyFinance;
	}

	/**
	 * newPayPassword的取得
	 * @return newPayPassword
	 */
	public String getNewPayPassword() {
		return newPayPassword;
	}

	/**
	 * newPayPassword的设定
	 * @param newPayPassword newPayPassword
	 */
	public void setNewPayPassword(String newPayPassword) {
		this.newPayPassword = newPayPassword;
	}
}