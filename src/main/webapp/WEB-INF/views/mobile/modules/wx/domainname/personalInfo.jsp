<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>个人信息</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
			<!-- 个人中心:个人信息页面begin -->
		<div data-title="个人信息" class="page active ms-controller" ms-controller="personalInfo"
			id="personalInfo">
			<section class="ui-container has-footer">
				<div class="ui-body-d ui-content" id="personal-information"
					style="padding: 0;">
					<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover">
						<li class="ui-border-t">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">米友号</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">{{datas.userinfo.dyid}}</h4>
							</div>
						</li>
						<li class="ui-border-t">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">昵称</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">{{datas.userinfo.nickname}}</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-if="datas.userinfo.authenticationMark==1 || datas.userinfo.authenticationMark==2">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">姓名</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">{{datas.userinfo.name}}</h4>
							</div>
						</li>
						<li class="ui-border-t"  ms-if="datas.userinfo.authenticationMark==0" ms-click="changeName">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">姓名</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">
									{{datas.userinfo.name}}
									<span ms-if="!datas.userinfo.name" class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;"></span>
									<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-click="linkToChangePhone">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">手机</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">
									<span ms-if="datas.userinfo.mobile">{{datas.userinfo.mobile}}</span>
									<span ms-if="!datas.userinfo.mobile" class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;" ></span>
									<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-if="!datas.userinfo.email || datas.userinfo.emailFlag==''" ms-click="addEmail">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">Email</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value" >
									<span class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;" ></span>
									<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-if="datas.userinfo.emailFlag=='1' && datas.userinfo.mobile" ms-click="goToChangeEmail">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">Email</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">
									{{datas.userinfo.email}}<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-if="datas.userinfo.emailFlag=='1' && !datas.userinfo.mobile" ms-click="addEmail">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">Email</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">
									{{datas.userinfo.email}}<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-if="datas.userinfo.emailFlag=='0'" ms-click="changeOrActiveEmail">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">Email</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">
									{{datas.userinfo.email}}<i	class="ui-icon-warn-lg personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-click="changeWx">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">微信</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">
									<span ms-if="datas.userinfo.wx || datas.userinfo.qq">{{datas.userinfo.wx}}</span>
									<span ms-if="!(datas.userinfo.wx || datas.userinfo.qq)" class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;" ></span>
									
									<i	class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-click="changeQQ">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">QQ</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value" >
									<span ms-if="datas.userinfo.wx || datas.userinfo.qq">{{datas.userinfo.qq}}</span>
									<span ms-if="!(datas.userinfo.wx || datas.userinfo.qq)" class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;" ></span>
									
									<i	class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-if="datas.userinfo.authenticationMark==2" ms-click="viewIDcard">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">身份证</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value" >
									审核中
									<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-if="datas.userinfo.authenticationMark==0" ms-click="changeIDcard">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">身份证</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value" ms-if="!datas.userinfo.authenticationPositiveImageUrl">
									未认证
									<span class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;" ></span>
									<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
								<h4 class="ui-nowrap title-value" ms-if="datas.userinfo.authenticationPositiveImageUrl">
									审核失败
									<span class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;" ></span>
									<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-if="datas.userinfo.authenticationMark==1" ms-click="viewIDcard">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">身份证</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value" >
									已认证<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-click="open_income_expense_dialog">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">银行信息</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value" >
									<span ms-if="!datas.userinfo.defaultIncomeExpense" class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;" ></span>
									{{datas.userinfo.defaultIncomeExpense}}<i class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
						<li class="ui-border-t" ms-click="viewBroker">
							<div class="ui-list-info">
								<h4 class="ui-nowrap title-key">经纪人</h4>
							</div>
							<div>
								<h4 class="ui-nowrap title-value">
									{{datas.brokerinfo.nickName}}<i	class="ui-icon-arrow personal-information-i"></i>
								</h4>
							</div>
						</li>
					</ul>
				</div>
			</section>
			<!--个人信息弹窗begin-->
			<!-- 修改或者激活email-->
			<!-- 修改E_mail -->
			<div class="ui-dialog dialog-font" id="changeOrActiveEmail">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<div class="ui-dialog-bd">
						<h4 class="ui-title">修改或激活邮箱</h4>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="select ui-btn-lg ui-btn-primary" ms-click="chooseChangeEmail">修改邮箱</button> <br>
							<button class="select ui-btn-lg ui-btn-primary" ms-click="chooseActivateEmail">激活邮箱</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 添加email -->
			<div class="ui-dialog dialog-font" id="addEmail">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>添加邮箱</h3>
					</header>
					<div class="ui-dialog-bd p-t-5">
						<div class="guide">（请输入真实的Email）</div>
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label>邮箱：</label> <input type="text" name="email" id="email"
									ms-duplex="datas.temp.email" placeholder="我们将保护您的隐私安全"
									style="padding-left: 50px; width: 96%;"> <i
									class="ui-icon-close"
									ms-class-1="hidden:datas.temp.email.length==0"
									ms-click="clearEmailInput"> </i>
							</div>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary"
								ms-click="confirmEmailInput">确定</button>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--添加email成功弹窗 -->
			<div class="ui-dialog dialog-font" id="informationDialogForAddEmail">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>添加Email成功</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t" style="text-align: center;">
							<span>添加Email成功!</span> <br> <span>我们将发送激活链接到邮箱{{datas.temp.email}}</span>
							<br> <span>请及时激活!</span>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 身份认证 -->
			<!-- 认证 -->
			<div class="ui-dialog dialog-font" id="changeIDcard">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>身份认证</h3>
					</header>
					<div class="ui-dialog-bd p-t-5">
						<div class="guide">（请输入真实的身份证号）</div>
						<div class="ui-form ui-border-t">
							<ul class="ui-list ui-list-text ui-border-tb cashflow-ul">
								<label>身份证号：</label>
								<div class="ui-form-item ui-border-b p-l-0">
									 <input class="p-l-0"type="text" name="IDcardNumber" id="IDcardNumber"
										ms-duplex="datas.temp.idcardNumber" placeholder="我们将保护您的隐私安全"
										style="width: 100%;"> <i
										class="ui-icon-close"
										ms-class-1="hidden:datas.temp.IDcardNumber.length==0"
										ms-click="clearIDcardNumberInput"> </i>
								</div>
								<label style="text-align: left;">上传身份证的正反面照片</label>
								<li class="ui-border-t">
									<div class="ui-list-info" style="width: 50%; position: relative; height: 90px;">
										<img ms-click="authentication_image1" id="authentication_image1"
											ms-attr-src="{{datas.authentication.image_source1}}"
											alt="上传照片" title="" style="width: 90px; height: 90px;position: absolute;left: 15%;  top: 0px;">
									</div>
									<div style="width: 50%;position: relative; height: 90px;">
										<img ms-click="authentication_image2"
											id="authentication_image2"
											ms-attr-src="{{datas.authentication.image_source2}}"
											alt="上传照片" title="" style="width: 90px; height: 90px;position: absolute;right: 15%;  top: 0px;">
									</div>
								</li>
							</ul>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button type="button" class="ui-btn-lg ui-btn-primary"
								ms-click="confirmAuthentication">确定</button>
							<br>
							<button type="button" class="select ui-btn-lg ui-btn-primary"
								ms-click="cancelAuthentication">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 身份已认证，查看 -->
			<div class="ui-dialog dialog-font" id="viewIDcard">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>认证的身份照片</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							<div ms-if="datas.userinfo.authenticationMark == '2'">身份证号： {{datas.userinfo.idcardNumber}}</div>
							<ul class="ui-list ui-list-text ui-border-tb cashflow-ul">
								<li class="ui-border-t">
									<div class="ui-list-info" style="width: 50%;">
										<img
											ms-attr-src="{{datas.userinfo.authenticationPositiveImageUrl}}"
											alt="" title="" style="width: 90px; height: 90px;padding-left: 10%;">
									</div>
									<div style="width: 50%;">
										<img
											ms-attr-src="{{datas.userinfo.authenticationNegativeImageUrl}}"
											alt="" title="" style="width: 90px; height: 90px;margin-left:10%;">
									</div>
								</li>
							</ul>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 修改姓名 -->
			<div class="ui-dialog dialog-font" id="changeName">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>添加/修改姓名</h3>
					</header>
					<div class="ui-dialog-bd p-t-5">
						<div class="guide">（请输入真实的姓名）</div>
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label>姓名：</label> <input type="text" name="name" id="name"
									ms-duplex="datas.temp.name" placeholder="我们将保护您的隐私安全"
									style="padding-left: 50px; width: 96%;"> <i
									class="ui-icon-close"
									ms-class-1="hidden:datas.temp.name.length==0"
									ms-click="clearNameInput"> </i>
							</div>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary"
								ms-click="changeNameComfirm">确定</button>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 查看经纪人 -->
			<div class="ui-dialog dialog-font" id="viewBroker">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>经纪人信息</h3>
					</header>
					<div class="ui-dialog-bd">
						<ul class="ui-list ui-list-text ui-border-tb">
						<!--
							<li class="ui-border-t">
								<div class="ui-list-info">
									<h4 class="ui-nowrap">姓名</h4>
								</div>
								<div>
									<h4 class="ui-nowrap">{{datas.brokerinfo.name}}</h4>
								</div>
							</li>
							-->
							<li class="ui-border-t">
								<div class="ui-list-info">
									<h4 class="ui-nowrap">昵称</h4>
								</div>
								<div>
									<h4 class="ui-nowrap">{{datas.brokerinfo.nickName}}</h4>
								</div>
							</li>
							<li class="ui-border-t">
								<div class="ui-list-info">
									<h4 class="ui-nowrap">电话</h4>
								</div>
								<div>
									<h4 class="ui-nowrap">{{datas.brokerinfo.mobile}}</h4>
								</div>
							</li>
							<li class="ui-border-t">
								<div class="ui-list-info">
									<h4 class="ui-nowrap">QQ</h4>
								</div>
								<div>
									<h4 class="ui-nowrap">{{datas.brokerinfo.qq}}</h4>
								</div>
							</li>
							<!--
							<li class="ui-border-t">
								<div class="ui-list-info">
									<h4 class="ui-nowrap">email</h4>
								</div>
								<div>
									<h4 class="ui-nowrap">{{datas.brokerinfo.email}}</h4>
								</div>
							</li>
							-->
							<li class="ui-border-t">
								<div class="ui-list-info">
									<h4 class="ui-nowrap">微信</h4>
								</div>
								<div>
									<h4 class="ui-nowrap">{{datas.brokerinfo.wechat}}</h4>
								</div>
							</li>
							<li class="ui-border-t">
								<div class="broker-wx-img-div" id="openBigPhoto">
									<img class="broker-wx-img" ms-attr-src="{{datas.brokerinfo.photo}}">
								</div>
							</li>
						</ul>
						<div class="ui-btn-wrap" style="padding: 10px 0px;" data-scroll='true'>
							<div>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 修改微信 -->
			<div class="ui-dialog dialog-font" id="changeWx">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>添加/修改微信</h3>
					</header>
					<div class="ui-dialog-bd p-t-5">
						<div class="guide">（请输入真实的微信号）</div>
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label>微信号：</label>
								<input type="text" name="wx" id="wx"
									ms-duplex="datas.temp.wx" placeholder="我们将保护您的隐私安全"
									style="padding-left: 60px; width: 96%;">
								<i class="ui-icon-close"
									ms-class-1="hidden:datas.temp.wx.length==0"
									ms-click="clearWxInput"> </i>
							</div>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary"
								ms-click="changeWxComfirm">确定</button>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 修改QQ -->
			<div class="ui-dialog dialog-font" id="changeQQ">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>添加/修改QQ</h3>
					</header>
					<div class="ui-dialog-bd p-t-5">
						<div class="guide">（请输入真实的QQ号）</div>
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label>QQ：</label> <input type="tel" name="qq" id="qq"
									ms-duplex="datas.temp.qq" placeholder="我们将保护您的隐私安全"
									style="padding-left: 50px; width: 96%;"> <i
									class="ui-icon-close"
									ms-class-1="hidden:datas.temp.wx.length==0"
									ms-click="clearQQInput"> </i>
							</div>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary"
								ms-click="changeQQComfirm">确定</button>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 银行信息弹窗：默认收支方式 -->
			<div class="ui-dialog dialog-font" id="change-default-income-expense">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>认证银行信息</h3>
					</header>
					<div class="ui-dialog-bd p-t-5">
						<div class="guide">（请输入真实的银行卡号）</div>
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label>持卡人：</label> 
								<input type="text" ms-attr-value="datas.userinfo.name" ms-if="datas.userinfo.name"
									style="padding-left: 80px; width: 96%;" readonly>
								<input type="text" value="必须为本人" ms-if="!datas.userinfo.name"
									style="padding-left: 80px; width: 96%;" readonly>
							</div>
							<div class="ui-form-item ui-border-b">
								<label>卡号：</label> 
								<input type="tel"
									name="default_income_expense"
									ms-duplex="datas.temp.defaultIncomeExpense"
									id="default_income_expense" placeholder="我们将保护您的隐私安全"
									style="padding-left: 80px; width: 96%;"> <i
									class="ui-icon-close"
									ms-class-1="hidden:datas.temp.defaultIncomeExpense.length==0"
									ms-click="clearBankCardNumberInput"> </i>
							</div>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary"
								ms-click="openVerificationPayPasswordForDIE">确定</button>
							<br>
							<button	class="ui-btn-lg ui-btn-primary" ms-click="cancelChangeDefaultIncomeExpense">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 修改默认收支方式的支付密码弹窗 -->
			<div class="ui-dialog dialog-font" id="verificationPayPasswordForDIE">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>安全密码</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label>安全密码：</label> <input type="password" 
									ms-duplex="datas.cashflowinfo.payPassword" name="payPasswords" id="payPasswords" style="padding-left: 80px; width: 96%;">
									<i class="ui-icon-close" ms-class-1="hidden:datas.cashflowinfo.payPassword.length==0" ms-click="clearPayPasswordForDIEInput"> </i>
							</div>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button id="zhifu" class="ui-btn-lg ui-btn-primary" ms-click="confirmChangeDefaultIncomeExpense">确定</button>
							<br>
							<button	class="select ui-btn-lg ui-btn-primary" id="dialogButton" ms-click="clearForDIE">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript" data-page="personalInfo"></script>
		</div>
		<!-- 个人中心:个人信息页面end -->
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>