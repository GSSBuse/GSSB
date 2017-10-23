<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>提现进度</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 提现进度页面 -->
		<div data-title="提现进度" class="page active ms-controller" ms-controller="withdrawalsProgress" id="withdrawalsProgress">
			<header class="ui-header ui-header-stable back" onclick="history.back()">
				<i class="ui-icon-return"></i>
			</header>
			<section class="ui-container has-footer">
				<ul class="ui-list ui-list-text ui-border-tb ui-list-active" ms-each-del="datas.withdrawalsInfo">
					<li class="ui-border-t" ms-click="cancelWithdrawals($index)" style=" line-height: 20px;">
						<div class="ui-list-info">
							<h4 class="ui-nowrap">{{del.operate}}</h4>
							<h4 class="ui-nowrap">{{ del.operateTime | date("yyyy-MM-dd HH:mm:ss")}}</h4>
						</div>
						<div>
							<h4 class="ui-nowrap">{{del.operateAmount}}元</h4>
							<h4 class="ui-nowrap">{{del.confirmResult}}</h4>
						</div>
					</li>
				</ul>
			</section>
			<div class="ui-dialog" id="cancelWithdrawalDialog">
				<div class="ui-dialog-cnt">
					<div class="ui-dialog-bd">
						<div>您确定要取消此次提现？</div>
					</div>
					<div class="ui-btn-wrap" style="padding: 15px 0px;">
							<button class="ui-btn-lg ui-btn-primary" ms-click="confirmWithdrawal">确定</button>
							<br>
							<button type="button" data-role="button" class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
					</div>
				</div>
			</div>
			<script type="text/javascript" data-page="withdrawalsProgress"></script>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>