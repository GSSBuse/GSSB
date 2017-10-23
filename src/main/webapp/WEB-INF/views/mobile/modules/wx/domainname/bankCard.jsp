<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- <title>个人中心</title> -->
 <title>米乐拍卖</title>
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
<link
	href="${pageContext.request.contextPath}/static/frontend-jam/static/css/common.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/static/frontend-jam/static/css/custom.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/frontend-jam/static/vendors/require/require.min.js"
	data-main="${pageContext.request.contextPath}/static/frontend-jam/static/js/require.config"></script>
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 银行卡认证页面 -->
		<div data-title="设置银行卡" class="page active ms-controller" ms-controller="bankCard" id="bankCard">
			<header class="ui-header ui-header-positive ui-border-b">
				<h1>银行卡认证</h1>
			</header>
			<section class="ui-container" style="padding-top: 5px;">
				<ul class="ui-list ui-list-text ui-border-tb">
					<li class="ui-border-t">
						<div class="ui-list-info">
							<h4 class="ui-nowrap">银行卡</h4>
						</div>
						<div>
							<a href="#add-bank-card"><i class="ui-icon-add"></i></a>
						</div>
					</li>
					<li class="ui-border-t">
						<div class="ui-list-info">
							<h4 class="ui-nowrap">01234567899632</h4>
						</div>
						<div>
							<i class="ui-icon-delete"></i>
						</div>
					</li>
					<li class="ui-border-t">
						<div class="ui-list-info">
							<h4 class="ui-nowrap">01234567899632</h4>
						</div>
						<div>
							<i class="ui-icon-delete"></i>
						</div>
					</li>
					<li class="ui-border-t">
						<div class="ui-list-info">
							<h4 class="ui-nowrap">01234567899632</h4>
						</div>
						<div>
							<i class="ui-icon-delete"></i>
						</div>
					</li>
				</ul>
			</section>
		</div>

		<!-- 添加银行卡页面 -->
		<div class="page" id="add-bank-card">
			<header class="ui-header ui-header-positive ui-border-b">
				<h1>添加银行卡</h1>
			</header>
			<section class="ui-container" style="padding-top: 5px;">
				<div class="ui-form ui-border-t">
							<form action="#">
								<div class="ui-form-item ui-border-b">
								<label>持卡人：</label> <input type="text" name="cardholder"
									id="cardholder" value="" placeholder="输入持卡人姓名"
									style="padding-left: 80px; width: 96%;"><i
									class="ui-icon-close" id="clearCardholderInput"> </i>
							</div>
							<div class="ui-form-item ui-border-b">
								<label>卡号：</label> <input type="text" name="bank-card-number" id="bank-card-number"
									value="" placeholder="输入银行账号"
									style="padding-left: 80px; width: 96%;"><i
									class="ui-icon-close" id="clearBankCardNumberInput"> </i>
							</div>
							</form>
						</div>
					<div class="ui-btn-wrap" style="padding: 15px 0px;">
						<button class="ui-btn-lg ui-btn-primary">确定</button>
						<br>
						<button type="button" data-role="button"
							class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
					</div>
			</section>
		</div>
		<span
			style="background-image: url(http://placeholder.qiniudn.com/60x60)"></span>
		<script type="text/javascript" data-page="bankCard"></script>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>