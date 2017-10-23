<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<!-- <title>拍卖列表</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<!--
<body class="ms-loader ms-loading">
-->
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<div data-title="拍卖列表" class="page active ms-controller" ms-controller="auction-list" id="auction-list">
			<header class="ui-header ui-header-stable back" onclick="history.back()">
				<i class="ui-icon-return"></i>
			</header>
			<section class="ui-container p-r-5 p-l-5">
				<!-- <div ms-widget="overscroll"></div> -->
				<ul class="ui-list ui-list-text ui-list-cover">
					<li class="ui-border-b li-p" ms-repeat="datas.domainList" ms-click="changePage(el.id)">
						<!-- <div class="iconfont" ms-class="icon-new-right-top:el.hasNews"></div> -->
						<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
							<img width="46px" height="46px" ms-attr-src="el.client.photo+'96'">
						</div>
						<div class="ui-list-info">
							<ul class="ui-row">
								<li class="ui-col width-50">
									<h4 class="ui-nowrap domain-name">{{el.name}}</h4>
									<div class="ui-nowrap-flex auction-list-current-price">
										<span ms-if="el.currAmount==0">该域名无出价</span>
										<span ms-if="el.currAmount>0 && !el.endFlag">当前价 {{el.currAmount | transferCurrentAmount}}</span>
										<span ms-if="el.currAmount>0 && el.endFlag">成交价 {{el.currAmount | transferCurrentAmount}}</span>
									</div>
								</li>
								<li class="ui-col width-50">
									<div class="ui-flex ui-flex-pack-end wx-description ">
										<span ms-html="getCountDown(el.endTime).displayTime"></span><!-- {{getCountDown(el.endTime).displayTime}} -->
									</div>
									<div class="ui-flex ui-flex-pack-end ui-txt-warning">
										<!-- 
											<span ms-if="!el.hasNews"><i class="iconfont icon-yuandian"></i>{{el.newBidCount}}</span>
										 -->
										<div class="news-style" ms-if="el.hasNews">
											<span  ms-if="el.newBidCount &gt; '99'">99</span>
											<span  ms-if="el.newBidCount &lt; '100'">{{el.newBidCount}}</span>
										</div>
									</div>
									<!--  
									<div class="ui-flex ui-flex-pack-end" style="height: 40px;">
										<div class="iconfont icon-auction-amount" style="width: 100.641px;  position: relative;top:-22px;">
											<span style="font-size: 12px; color: white; position: relative; left: 20px; top: 16px;" ms-if="el.currAmount==0">该域名无出价</span>
											<span style="font-size: 12px; color: white; position: relative; left: 20px; top: 16px;" ms-if="el.currAmount>0 && !el.endFlag">当前价 {{el.currAmount}}</span>
											<span style="font-size: 12px; color: white; position: relative; left: 20px; top: 16px;" ms-if="el.currAmount>0 && el.endFlag">成交价 {{el.currAmount}}</span>
										</div>
										<div class="iconfont h-100 p-l-5"
												ms-class-1="icon-love:(el.attentioned)"
												ms-class-2="icon-loved:!(el.attentioned)"
												ms-click="switchFollowStatus($index)" style="position: relative; top: 3.5px;">
										</div>
									</div>
									-->
								</li>
							</ul>
						</div>
					</li>
				</ul>
			</section>
			<div id="auctionmore" class="dropload-down has-footer">
				<div class="dropload-load">
					<div class="dropload-showmore"><span class="loading"></span>加载更多...</div>
					<div class="dropload-nomore">没有更多数据了</div>
				</div>
			</div>
			<script type="text/javascript" data-page="auction-list"></script>
		</div><!-- /page -->
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>