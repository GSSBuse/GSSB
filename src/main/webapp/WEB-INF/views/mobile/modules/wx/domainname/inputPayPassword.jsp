<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>输入安全密码</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 安全设置导航begin -->
		<div data-title="输入安全密码" class="page active ms-controller" ms-controller="inputPayPassword" id="inputPayPassword">
			<section class="ui-container">
				<div class="pay-pass">
					<p class="tip" id="tip">输入支付密码，切勿泄露</p>
					<div class="pass-box" id="pass-box" ms-click="showKeyboard">
						<b class="pass-1 pass-2 pass-3 pass-4 pass-5 pass-6"></b>
						<b class="pass-2 pass-3 pass-4 pass-5 pass-6"></b>
						<b class="pass-3 pass-4 pass-5 pass-6"></b>
						<b class="pass-4 pass-5 pass-6"></b>
						<b class="pass-5 pass-6"></b>
						<b class="pass-6"></b>
					</div>
					<p class="feedback"></p>
					<input type="tel" name="payPassword" id="payPassword" value="">
				</div>
			</section>
			<!-- 支付密码键盘 -->
			<div id="keyboard" style="display:none;">
				<div class="ui-dialog-cnt" style="border-radius: 0px; width: 100%; position: fixed; bottom: 0;">
					<ul class="ui-list-text border-list"><li class="ui-border-b p-t-0 p-b-1"></li></ul>
					<div class="ui-row-flex">
						<i class="ui-icon-unfold" ms-click="hideKeyboard" style="font-size: 40px;margin-left: 45%; line-height: 40px;"></i>
					</div>
					<ul class="ui-list-text border-list"><li class="ui-border-b p-t-0 p-b-1"></li></ul>
					<div class="numKeyboard has-footer">
						<ul class="ui-row-flex ui-list-text border-list ui-list-active ui-list-cover">
							<li class="ui-col ui-col ui-border-r center numKey-style active-numKey" data="1">1</li>
							<li class="ui-col ui-col ui-border-r center numKey-style active-numKey" data="2">2</li>
							<li class="ui-col ui-col center numKey-style active-numKey" data="3">3</li>
						</ul>
						<ul class="ui-list-text border-list"><li class="ui-border-b p-t-0 p-b-1"></li></ul>
						<ul class="ui-row-flex ui-list-text border-list ui-list-active ui-list-cover">
							<li class="ui-col ui-col ui-border-r center numKey-style active-numKey" data="4">4</li>
							<li class="ui-col ui-col ui-border-r center numKey-style active-numKey" data="5">5</li>
							<li class="ui-col ui-col center numKey-style active-numKey" data="6">6</li>
						</ul>
						<ul class="ui-list-text border-list"><li class="ui-border-b p-t-0 p-b-1"></li></ul>
						<ul class="ui-row-flex ui-list-text border-list ui-list-active ui-list-cover">
							<li class="ui-col ui-col ui-border-r center numKey-style active-numKey" data="7">7</li>
							<li class="ui-col ui-col ui-border-r center numKey-style active-numKey" data="8">8</li>
							<li class="ui-col ui-col center numKey-style active-numKey" data="9">9</li>
						</ul>
						<ul class="ui-list-text border-list"><li class="ui-border-b p-t-0 p-b-1"></li></ul>
						<ul class="ui-row-flex ui-list-text border-list ui-list-active ui-list-cover">
							<li class="ui-col ui-col ui-border-r center num-gray numKey-style" data="00"></li>
							<li class="ui-col ui-col ui-border-r center numKey-style active-numKey" data="0">0</li>
							<li class="ui-col ui-col center num-gray active-numKey" data="del"><i class="iconfont icon-ret" style="font-size: 25px"></i></li>
						</ul>
					</div>
				</div>
			</div>
			<script type="text/javascript" data-page="inputPayPassword"></script>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>