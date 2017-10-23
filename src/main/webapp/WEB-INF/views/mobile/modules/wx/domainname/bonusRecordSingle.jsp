<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- <title>领红包、领佣金</title> -->
 <title>米乐拍卖</title>
<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 领红包佣金记录页面 -->
		<!-- clientId存储后台model传过来的值 -->
		<div data-title="领红包、领佣金" class="page active ms-controller" ms-controller="bonusRecordSingle" id="bonusRecordSingle">
			<div class="redbag-head" style="background-image:url(${pageContext.request.contextPath}/static/images/bonus-back.jpg); background-position: 0% 0%; height: 150px;background-size: 100% 100%;">
				<header class="heade-style" >
				<!--ui-header ui-header-positive ui-border-b -->
					<a class="head-back"  onclick="history.back()">返回</a>
					<h1 class="head-title" style="">域名红包佣金记录</h1>
					<a class="head-share" ><span class="head-span" ms-click="share">我也要</span></a>
				</header>
				<img ms-attr-src="{{datas.domainPhoto}}" width="60px" height="60px" style="border-radius: 50%; margin-top: 11px;">
				<div style="font-size:10px;color:black">{{datas.domainname}}的红包佣金</div>
			</div>
			<section class="ui-container">
				<!-- 转发红包 -->
				<div class="ui-row">
					<div>
						<span class="ui-panel-subtitle small-font">转发红包（{{datas.sharenumber}}份{{datas.sharetotal}}元）</span>
						<span class="ui-panel-title-tips" style="right: 12px; padding-top: 2px">已转发{{datas.sharedSize}}次</span></a>
					</div>
				</div>
				<ul class="ui-list ui-list-text ui-border-tb"	ms-if="datas.sharedSize =='0'">
					<li class="ui-border-b li-p" style="height: 60px;">
						<div class="ui-list-info">
							<span>当前无人分享域名得红包</span>
						</div>
					</li>
				</ul>
				<ul class="ui-list ui-list-text ui-border-tb"	ms-each-del="datas.shareList">
					<li class="ui-border-b li-p">
						<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
							<img ms-attr-src="del.clientPhoto+'46'" width="46px" height="46px" style="border-radius: 50%;">
						</div>
						<div class="ui-list-info">
							<ul class="ui-row">
								<li class="ui-col ui-col-50" style="width: 55%;" ms-click="linkToSingleDomainname(del.domainnameId)">
									<h4 class="ui-nowrap">{{del.clientNickname}}</h4>
									<div class="ui-txt-warning" style="margin-top: 2px;">
										<span
											class="small-font ui-txt-info">{{del.createDate}}</span>
									</div>
								</li>
								<li class="ui-col ui-col-50" style="width: 1%;">
									<div style="width: 100%;">&nbsp</div>
								</li>
								<li class="ui-col ui-col-50" style="width: 44%;">
									<div class="ui-flex ui-flex-pack-end">
										<span class="small-font ui-txt-info">{{del.money}}</span>
									</div>
									<div style="margin-top: 2px;"class="ui-flex ui-flex-ver ui-flex-align-end ui-flex-pack-end">
									</div>
								</li>
							</ul>
						</div>
					</li>
				</ul>
				<!-- 次高价红包 -->
				<div class="ui-row" ms-if="datas.secondBonusData">
					<div>
						<span class="ui-panel-subtitle small-font">次高价红包</span>
						<span class="ui-panel-title-tips" style="right: 12px; padding-top: 2px"></span></a>
					</div>
				</div>
				<ul class="ui-list ui-list-text ui-border-tb" ms-if="datas.secondBonusData">
					<li class="ui-border-b li-p">
						<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
							<img ms-attr-src="datas.secondBonusData.clientPhoto+'46'" width="46px" height="46px" style="border-radius: 50%;">
						</div>
						<div class="ui-list-info">
							<ul class="ui-row">
								<li class="ui-col ui-col-50" style="width: 55%;" ms-click="linkToSingleDomainname(del.domainnameId)">
									<h4 class="ui-nowrap">{{datas.secondBonusData.clientNickname}}</h4>
									<div class="ui-txt-warning" style="margin-top: 2px;">
										<span ms-if="datas.secondBonusData.createDate"
											class="small-font ui-txt-info">{{datas.secondBonusData.createDate}}</span>
										<span ms-if="!datas.secondBonusData.createDate"
											class="small-font ui-txt-info">此时完成交易将获得</span>
									</div>
								</li>
								<li class="ui-col ui-col-50" style="width: 1%;">
									<div style="width: 100%;">&nbsp</div>
								</li>
								<li class="ui-col ui-col-50" style="width: 44%;">
									<div class="ui-flex ui-flex-pack-end">
										<span class="small-font ui-txt-info">{{datas.secondBonusData.money}}元</span>
									</div>
									<div style="margin-top: 2px;"class="ui-flex ui-flex-ver ui-flex-align-end ui-flex-pack-end">
									</div>
								</li>
							</ul>
						</div>
					</li>
				</ul>
				<!-- 佣金 -->
				<div class="ui-row" ms-if="datas.shareCommissionData">
					<div>
						<span class="ui-panel-subtitle small-font">分享佣金</span>
						<span class="ui-panel-title-tips" style="right: 12px; padding-top: 2px"></span></a>
					</div>
				</div>
				<ul class="ui-list ui-list-text ui-border-tb" ms-if="datas.shareCommissionData">
					<li class="ui-border-b li-p">
						<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
							<img ms-attr-src="datas.shareCommissionData.clientPhoto+'46'" width="46px" height="46px" style="border-radius: 50%;">
						</div>
						<div class="ui-list-info">
							<ul class="ui-row">
								<li class="ui-col ui-col-50" style="width: 55%;" ms-click="linkToSingleDomainname(del.domainnameId)">
									<h4 class="ui-nowrap">{{datas.shareCommissionData.clientNickname}}</h4>
									<div class="ui-txt-warning" style="margin-top: 2px;">
										<span ms-if="datas.shareCommissionData.createDate"
											class="small-font ui-txt-info">{{datas.shareCommissionData.createDate}}</span>
										<span ms-if="!datas.shareCommissionData.createDate"
											class="small-font ui-txt-info">此时完成交易将获得</span>
									</div>
								</li>
								<li class="ui-col ui-col-50" style="width: 1%;">
									<div style="width: 100%;">&nbsp</div>
								</li>
								<li class="ui-col ui-col-50" style="width: 44%;">
									<div class="ui-flex ui-flex-pack-end">
										<span class="small-font ui-txt-info">{{datas.shareCommissionData.money}}元</span>
									</div>
									<div style="margin-top: 2px;"class="ui-flex ui-flex-ver ui-flex-align-end ui-flex-pack-end">
									</div>
								</li>
							</ul>
						</div>
					</li>
				</ul>
			</section>
			<div id="directiveDivBonusOrCommission" class="ui-dialog share-arrow">
				<img class="share-arrow-image"src="${pageContext.request.contextPath}/static/images/share-arrow.png">
			</div>
			<script type="text/javascript" data-page="bonusRecordSingle"></script>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>