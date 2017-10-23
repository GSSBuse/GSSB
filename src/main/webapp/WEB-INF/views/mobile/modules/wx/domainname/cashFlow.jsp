<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>资金流水</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 资金流页面 -->
		<div data-title="资金流水" class="page active ms-controller" ms-controller="cashFlow" id="cashFlow">
			<header class="ui-header ui-header-stable back" onclick="history.back()">
				<i class="ui-icon-return"></i>
			</header>
			<section class="ui-container">
				<ul class="ui-list ui-list-text ui-border-tb" ms-each-del="datas.cashflowinfo">
					<li class="ui-border-t">
						<div class="ui-list-info left cash-flow-date">
							<div class="ui-nowrap">{{ del.operateTime | date("MM-dd")}}</div>
							<div class="ui-nowrap">{{ del.operateTime | date("HH:mm")}}</div>
						</div>
						<div class="ui-list-info center">
							<div class="ui-nowrap">
								<span class="cash-flow-num" ms-if="del.operate=='线下充值'|| del.operate=='微信充值' || del.operate=='收款' || del.operate=='佣金收入' || del.operate=='违约收入' || del.operate=='红包收入' || del.operate=='红包退回'">{{del.operateAmount}}元</span>
								<span class="cash-flow-num" ms-if="del.operate=='提现' || del.operate=='付款' || del.operate=='违约扣除' || del.operate=='红包支出'">-{{del.operateAmount}}元</span>
							</div>
							<div class="ui-nowrap cash-flow-operate">
								<span ms-if="del.operate=='收款' || del.operate=='佣金收入' || del.operate=='违约收入' || del.operate=='红包收入' || del.operate=='红包退回' || del.operate=='付款' || del.operate=='违约扣除' || del.operate=='红包支出'">{{del.dyDomainname.name}}&nbsp;</span>
								<span>{{del.operate}}</span>
							</div>
						</div>
						<div class="right cash-flow-operate">
							<div class="ui-nowrap">余<span class="cash-flow-num">{{del.amountBalance}}</span>元</div>
						</div>
					</li>
				</ul>
			</section>
			<div id="loadmore_cash" class="dropload-down has-footer">
				<div class="dropload-load">
					<div class="dropload-showmore"><span class="loading"></span>加载更多...</div>
					<div class="dropload-nomore">没有更多资金流信息了</div>
				</div>
			</div>
			<script type="text/javascript" data-page="cashFlow"></script>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>