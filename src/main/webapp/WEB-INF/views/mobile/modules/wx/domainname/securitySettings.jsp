<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>安全设置</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 安全设置导航begin -->
		<div data-title="安全设置" class="page active ms-controller" ms-controller="securitySettings" id="securitySettings">
			<section class="ui-container">
				<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover">
					<li class="ui-border-t" ms-if="!datas.userinfo.payPassword" ms-click="addPayPassWord">
						<div class="ui-list-info">
							<h4 class="ui-nowrap title-key">添加支付密码</h4>
						</div>
						<div>
							<h4 class="ui-nowrap title-value" >
								<i class="ui-icon-warn-lg" style="display:inline-block;font-size: 20px;line-height: 30px;color:red;"></i>
							</h4>
						</div>
					</li>
					<li class="ui-border-t" ms-if="datas.userinfo.payPassword" ms-click="changePayPassWord">
						<div class="ui-list-info">
							<h4 class="ui-nowrap title-key">重置支付密码</h4>
						</div>
						<div>
							<h4 class="ui-nowrap title-value" >
								<i class="ui-icon-arrow" style="display:inline-block;font-size: 20px;line-height: 30px;"></i>
							</h4>
						</div>
					</li>
					<li class="ui-border-t" ms-if="datas.userinfo.payPassword" ms-click="getContactWithBroker">
						<div class="ui-list-info">
							<h4 class="ui-nowrap title-key">忘记原密码？</h4>
						</div>
						<div>
							<h4 class="ui-nowrap title-value" >
								<i class="ui-icon-arrow" style="display:inline-block;font-size: 20px;line-height: 30px;"></i>
							</h4>
						</div>
					</li>
				</ul>
			</section>
			<!-- 忘记原密码提示联系经纪人弹窗 -->
			<div class="ui-dialog dialog-font" id="contactWithBrokerDialog">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>忘记原密码</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							如果您忘记了原密码，请联系经纪人重置密码！
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button type="button" data-role="button" class="select ui-btn-lg ui-btn-primary" id="dialogButton">确认</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript" data-page="securitySettings"></script>
		</div>
		<!-- 安全设置导航end -->
		
		<!-- 添加密码页面begin -->
		<!--
		<div class="page ms-controller" ms-controller="securitySettings" id="addPayPassword">
			<section class="ui-container">
				<div class="ui-form ui-border-t" id="password">
					<form method="post">
						<div class="ui-form-item ui-border-b">
							<label>新密码： </label> <input type="password" name="payPassword" id="payPassword" ms-duplex="temp.payPassword"
								placeholder="请输入6位数字新密码" style="padding-left: 70px; width: 96%;">
							<i class="ui-icon-close" ms-class-1="hidden:temp.payPassword.length==0" ms-click="clearPayPasswordInput"> </i>
						</div>
					</form>
					<div class="ui-btn-wrap" style="padding: 10px 0px;">
						<button class="ui-btn-lg ui-btn-primary" ms-click="goToconfirmPayPassword">确认</button>
						<br>
						<button type="button" data-role="button" onclick="history.back()"
							class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
					</div>
				</div>
				<div class="ui-form ui-border-t" id="confirmPassword" style="display:none;">
					<form method="post">
						<div class="ui-form-item ui-border-b">
							<label>确认密码： </label> <input type="password" name="comfirmPayPassword" id="comfirmPayPassword" ms-duplex="temp.comfirmPayPassword"
								placeholder="再次输入6位数字新密码" style="padding-left: 70px; width: 96%;">
							<i class="ui-icon-close" ms-class-1="hidden:temp.comfirmPayPassword.length==0" ms-click="clearComfirmPayPasswordInput"> </i>
						</div>
					</form>
					<div class="ui-btn-wrap" style="padding: 10px 0px;">
						<button class="ui-btn-lg ui-btn-primary" ms-click="confirmAddPayPassword">确认</button>
						<br>
						<button type="button" data-role="button" ms-click="openPassword"class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
					</div>
				</div>
			</section>
			<script type="text/javascript" data-page="securitySettings"></script>
		</div>
		-->
		<!-- 添加密码页面end -->
		
		<!-- 重置密码页面begin -->
		<!--
		<div class="page ms-controller" ms-controller="securitySettings" id="setPayPassword">
			<section class="ui-container">
				<div class="ui-form ui-border-t" id="verificationOldPassword">
					<form method="post">
						<div class="ui-form-item ui-border-b">
							<label>原密码： </label> <input type="password" name="oldPassword" id="oldPassword" ms-duplex="temp.oldPassword"
								placeholder="请输入6位原密码" style="padding-left: 70px; width: 96%;">
							<i class="ui-icon-close" ms-class-1="hidden:temp.oldPassword.length==0" ms-click="clearOldPasswordInput"> </i>
						</div>
					</form>
					<div class="ui-btn-wrap" style="padding: 10px 0px;">
						<button class="ui-btn-lg ui-btn-primary" ms-click="confirmOldPassword">确认</button>
						<br>
						<button type="button" data-role="button" onclick="history.back()"class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
					</div>
				</div>
			</section>
			<script type="text/javascript" data-page="securitySettings"></script>
		</div>
		-->
		<!-- 重置密码页面end -->
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>