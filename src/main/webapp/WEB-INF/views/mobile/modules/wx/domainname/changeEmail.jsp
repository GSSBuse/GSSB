<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>修改email</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 输入验证码页面 begin-->
		<div data-title="修改email" class="page ms-controller" ms-controller="changeEmail" id="changeEmail">
			<header class="ui-header ui-header-positive ui-border-b">
				<h1>验证码输入</h1>
			</header>
			<section class="ui-container" style="padding-top: 5px;">
				<h3 class="ui-title" style="text-align: center;" id ="tip-text">我们将发送验证码到你的手机</h3>
				<h3 class="ui-title" style="text-align: center;">{{datas.userinfo.mobile}}</h3>
				<div class="ui-form ui-border-t">
					<div class="ui-form-item ui-form-item-r ui-border-b">
						<input type="tel" placeholder="请输入验证码" name="verificationCode" id="verificationCode" ms-duplex="datas.temp.verificationCode">
						<!-- 若按钮不可点击则添加 disabled 类 -->
						<button type="button" class="ui-border-l"ms-click="warnInfo" ms-if="datas.n != 0">{{getCountDown()}}秒后重新发送</button>
						<button type="button" class="ui-border-l" ms-click="reSendVerificationCode" ms-if="datas.n == 0">重新发送</button>
						<i class="ui-icon-close" ms-class-1="hidden:datas.temp.verificationCode.length==0" ms-click="clearVerificationInput"></i>
					</div>
					<div class="ui-form-item ui-border-b">
						<label>邮箱： </label> <input type="text" name="email" id="email" ms-duplex="datas.temp.email"
							placeholder="我们将保护您的隐私安全" style="padding-left: 60px; width: 96%;">
						<i class="ui-icon-close" ms-class-1="hidden:datas.temp.email.length==0" ms-click="clearEmailInput"> </i>
					</div>
				</div>
				<div class="ui-btn-wrap" style="padding: 10px 0px;" data-scroll='true'>
					<button class="ui-btn-lg ui-btn-primary" ms-click="confirmEmailInput">确认</button>
					<br>
					<button type="button" data-role="button"
						class="select ui-btn-lg ui-btn-primary" onclick="history.back()">取消</button>
				</div>
			</section>
			<div class="ui-dialog dialog-font" id="changeE_mail">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<div class="ui-dialog-bd">
						<h4 class="ui-title">修改邮箱须先验证手机</h4>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<a ms-click="comfirmGetCode" class="ui-btn-lg ui-btn-primary">确定</a> <br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" ms-click="returnBack">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
			<!-- 修改email成功弹窗 -->
			<div class="ui-dialog dialog-font" id="informationDialog">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>修改Email成功</h3>
					</header>
					<div class="ui-dialog-bd">
							<div class="ui-form ui-border-t" style="text-align:center;">
								<span>修改Email成功!</span>
								<br>
								<span>我们将发送激活链接到邮箱{{datas.temp.email}}</span>
								<br>
								<span>请及时激活!</span>
							</div>
							<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
								<div>
								<button class="ui-btn-lg ui-btn-primary" ms-click="confirmInfomation">确定</button><br>
								</div>
							</div>
					</div>
				</div>
			</div>
			
			<script type="text/javascript" data-page="changeEmail"></script>
		</div>
		<!-- 输入验证码页面 end-->
		<!-- 输入邮箱页面 begin-->
		<div class="page active ms-controller" ms-controller="changeEmail" id="EmailInput">
			<header class="ui-header ui-header-positive ui-border-b">
				<h1>修改邮箱</h1>
			</header>
			<section class="ui-container" style="padding-top: 5px;">
				<div class="guide">（请输入真实的Email）</div>
				<div class="ui-form ui-border-t">
					<div class="ui-form-item ui-border-b">
						<label>邮箱： </label> <input type="text" name="email" id="email" ms-duplex="datas.temp.email"
							placeholder="我们将保护您的隐私安全" style="padding-left: 60px; width: 96%;">
						<i class="ui-icon-close" ms-class-1="hidden:datas.temp.email.length==0" ms-click="clearEmailInput"> </i>
					</div>
				</div>
				<div class="ui-btn-wrap" style="padding: 10px 0px;" data-scroll='true'>
					<button class="ui-btn-lg ui-btn-primary" ms-click="confirmEmailInput">确认</button>
					<br>
					<button type="button" data-role="button"
						class="select ui-btn-lg ui-btn-primary" onclick="history.back()">取消</button>
				</div>
			</section>
			<script type="text/javascript" data-page="changeEmail"></script>
		</div>
		<!-- 输入邮箱页面 end-->
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>