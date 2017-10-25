<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>历史交易</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 历史交易记录 -->
		<div data-title="历史交易" class="page active ms-controller" ms-controller="history" id="getHistory">
			<section class="ui-container">
				<ul class="ui-list ui-list-text ui-border-tb ui-list-active" ms-each-del="datas.historyinfo">
					<li class="ui-border-t" ms-click="linkToSingleDomainname(del.domainnameId)" style=" line-height: 20px;">
						<div class="ui-list-info">
							<h4 class="ui-nowrap">{{del.name}}</h4>
							<h4 class="ui-nowrap history-size m-t-5">成交日期：{{ del.sellDate | date("yyyy-MM-dd HH:mm:ss")}}</h4>
						</div>
						<div>
							<h4 class="ui-nowrap history-size">{{del.bidAmount}}元</h4>
						</div>
					</li>
				</ul>
			</section>
			<div id="loadmore_historyInfo" class="dropload-down has-footer">
				<div class="dropload-load">
					<div class="dropload-showmore"><span class="loading"></span>加载更多...</div>
					<div class="dropload-nomore">没有更多历史记录数据了</div>
				</div>
			</div>
			<script type="text/javascript" data-page="history"></script>
			
			<footer class="ui-footer ui-footer-stable">
				<div class="ui-row-flex ui-border-t h-100">
					<div class="ui-col ui-col">
						<div class="center">
							<a href="${pageContext.request.contextPath}/domainname/ibuy.html" rel="external" class="iconfont icon-auction" style="display: block; width: 100%; height: auto;">
								<span style="font-size: small; display: block; padding-top: 5px;">我要买</span>
							</a>
						</div>
					</div>
					<div class="ui-col ui-col">
						<div class="center">
							<a href="${pageContext.request.contextPath}/domainname/isell.html" rel="external" class="iconfont icon-domain ui-txt-info" style="display: block; width: 100%; height: auto;">
								<span style="font-size: small; display: block; padding-top: 5px;">我要卖</span>
							</a>
						</div>
					</div>
					<div class="ui-col ui-col">
						<div class="center">
							<a href="${pageContext.request.contextPath}/domainname/icenter.html" rel="external" class="iconfont icon-personal ui-txt-info" style="display: block; width: 100%; height: auto;">
								<span style="font-size: small; display: block; padding-top: 5px;">我的</span>
							</a>
						</div>
					</div>
				</div>
			</footer>
			
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>