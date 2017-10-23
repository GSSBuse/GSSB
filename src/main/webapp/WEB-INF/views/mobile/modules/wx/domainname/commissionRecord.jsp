<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>领佣金记录</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 领佣金页面 -->
		<div data-title="领佣金记录" class="page active ms-controller" ms-controller="commissionRecord" id="commissionRecord">
		<!-- clientId 、current_dy_client_id存储后台model传过来的值 -->
		<input id="clientId" value="${clientId}" type="hidden">
		<input id="current_dy_client_id" value="${current_dy_client_id}" type="hidden">
		<div class="redbag-head" style="background-image:url(${pageContext.request.contextPath}/static/images/redbag-back.jpg);  background-position: 0% 90%;">
			<header class="heade-style">
				<a class="head-back"  onclick="history.back()">返回</a>
				<h1 class="head-title" >领佣金记录</h1>
				<a class="head-share" ms-if="clientId == 'clientId_null'"><span class="head-span" ms-click="share">分享</span></a>
			</header>
			<img ms-attr-src="{{datas.photo}}"width="60px" height="60px">
			<div style="font-size:20px;">{{datas.currentClientName}}</div>
			<div class="head-money">共{{datas.totalMoney}}元</div>
		</div>
			<section class="ui-container">
				<ul class="ui-list ui-list-text ui-border-tb">
					<li class="ui-border-t">
						<div class="ui-list-info">
							<div class="ui-nowrap redbag-list-first"><b>通过以下域名领取了佣金：</b></div>
						</div>
						<div class="ui-list-info" class="" style="font-size:15px;padding-right:0px;text-align:right;"><b>共{{datas.size}}个</b></div>
						<div>
						</div>
					</li>
					</ul>
				<ul class="ui-list ui-list-text ui-border-tb"  ms-repeat-del="datas.commissionRecordOrBonusRecordInfo">
					<li class="ui-border-t">
						<div class="ui-list-info" style="text-align:left;width:33%;">
							<ul class="ui-row">
								<li class="ui-col">
									<div class="ui-nowrap" style="font-size:15px;"><b>{{del.domainname}}</b></div>
									<div class="ui-nowrap" style="font-size:10px;">{{del.createDate}}</div>
									<!--  <div class="ui-txt-warning">平台所发</div>-->
								</li>
							</ul>
						</div>
						<div style="text-align:right;width:33%;">
							<ul class="ui-row">
								<li class="ui-col">
									<h4 class="ui-nowrap" style="font-size:12px;"><b>{{del.type}}</b></h4>
									<div class="ui-txt-warning" style="font-size:12px;">{{del.money}}元</div>
								</li>
							</ul>
						</div>
					</li>
				</ul>
			</section>
			<div id="directiveDivCommission" class="ui-dialog share-arrow">
		  		<img class="share-arrow-image" src="${pageContext.request.contextPath}/static/images/share-arrow.png"">
		  	</div>
			<script type="text/javascript" data-page="commissionRecord"></script>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>