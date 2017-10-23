<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>修改手机</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 输入手机号码页面 begin-->
		<div data-title="修改手机" class="page active ms-controller" ms-controller="changePhone" id="changePhone">
			<header class="ui-header ui-header-positive ui-border-b">
				<h1>添加/修改手机</h1>
			</header>
			<section class="ui-container" style="padding-top: 5px;">
				<div class="guide">（请输入真实的手机号）</div>
				<div class="ui-form ui-border-t">
					<form action="${pageContext.request.contextPath}/domainname/changeMobile.html" method="post">
						<div class="ui-form-item ui-border-b">
							<label>手机号： </label> <input type="tel" name="mobile" id="mobile" ms-duplex="datas.temp.mobile"
								placeholder="我们将保护您的隐私安全" style="padding-left: 60px; width: 96%;">
							<i class="ui-icon-close" ms-class-1="hidden:datas.temp.mobile.length==0" ms-click="clearPhoneInput"> </i>
						</div>
					</form>
				</div>
				<div class="ui-btn-wrap" style="padding: 10px 0px;">
					<button class="ui-btn-lg ui-btn-primary" ms-click="openConfirmChangePhonDialog">下一步</button>
					<br>
					<button type="button" data-role="button" onclick="history.back()"
						class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
				</div>
			</section>
			<!-- 弹窗 -->
			<div class="ui-dialog dialog-font" id="confirmChangePhone">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<div class="ui-dialog-bd">
						<h4 class="ui-title">我们将发送验证码到：{{datas.temp.mobile}}</h4>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<a href="#pageVerificationInput" class="ui-btn-lg ui-btn-primary" ms-click="changeMobileConfirm">确定</a>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 输入验证码页面 end-->
			<script type="text/javascript" data-page="changePhone"></script>
		</div>
		<!-- 输入手机号码页面 end-->
		<!-- 输入验证码页面 begin-->
		<div class="page ms-controller" id="pageVerificationInput" ms-controller="changePhone">
			<header class="ui-header ui-header-positive ui-border-b">
				<h1>验证码输入</h1>
			</header>
			<section class="ui-container" style="padding-top: 5px;">
				<h3 class="ui-title" style="text-align: center;">我们已发送验证码到你的手机</h3>
				<h3 class="ui-title" style="text-align: center;">{{datas.temp.mobile}}</h3>
				<div class="ui-form ui-border-t">
					<form action="${pageContext.request.contextPath}/domainname/verificationPhone.html" method="POST">
						<div class="ui-form-item ui-form-item-r ui-border-b">
							<input type="tel" placeholder="请输入验证码" name="verificationCode" id="verificationCode" ms-duplex="datas.temp.verificationCode">
							<!-- 若按钮不可点击则添加 disabled 类 -->
							<button type="button" class="ui-border-l"ms-click="warnInfo" ms-if="datas.n != 0">{{getCountDown()}}秒后重新发送</button>
							<button type="button" class="ui-border-l" ms-click="reSendVerificationCode" ms-if="datas.n == 0">重新发送</button>
							<i class="ui-icon-close" ms-class-1="hidden:datas.temp.verificationCode.length==0" ms-click="clearVerificationInput"></i>
						</div>
					</form>
				</div>
				<div class="ui-btn-wrap" style="padding: 10px 0px;" data-scroll='true'>
					<div>
					<button class="ui-btn-lg ui-btn-primary" ms-click="confirmVerificationInput">确认</button>
					</div>			
				</div>
			</section>			
			<script type="text/javascript" data-page="changePhone"></script>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>