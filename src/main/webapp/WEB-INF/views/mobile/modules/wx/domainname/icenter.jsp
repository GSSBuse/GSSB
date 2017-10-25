<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>个人中心</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
	
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/frontend-jam/static/js/icenter.js"></script>
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 个人中心导航页面begin -->
		<div data-title="个人中心" class="page active ms-controller" ms-controller="icenter" id="icenter">
			
			<section class="ui-container has-footer" id="navigation">
				<div class="ui-body-d ui-content" style="margin-top: 10px;">
					<ul class="ui-list ui-list-text ui-list-cover">
						<li
							style="padding-left: 5px; padding-right: 15px; padding-top: 5px; padding-bottom: 5px;">
							<div class="ui-list-thumb" style="width: 70px; height: 70px;">
								<img ms-attr-src="datas.userinfo.photo+'96'" width="100%"
									height="100%">
							</div>
							<div class="ui-list-info">
								<ul class="ui-row">
									<li class="ui-col ui-col-50"
										style="width: 100%; padding-left: 10px;">
										<h3 class="ui-nowrap nickname-h3">{{datas.userinfo.nickname}}</h3>
										<h3 class="dyid-h3">米友号：{{datas.userinfo.dyid}}</h3>
									</li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
				<div class="ui-body-d ui-content" style="margin-top: 15px;">
					<ul class="ui-list ui-list-one ui-list-link ui-list-active ui-list-cover">
						<li class="ui-border-t" ms-click="linkToUserinfo" style="padding-bottom:0px;">
							<div class="ui-list-thumb">
								<!-- <img ms-attr-src="{{datas.userinfo.photo}}" width="100%" height="100%"> -->
								<i class="iconfont icon-my-info" style="font-size: 25px;color:#4cd127;"></i>
							</div>
							<div class="ui-list-info">
								<h4 class="ui-nowrap">个人信息</h4>
								<div class="ui-txt-info"></div>
							</div>
						</li>
						<li class="ui-border-t" ms-click="linkToFinanceinfo" style="padding-bottom:0px;">
							<div class="ui-list-thumb">
								<!-- <img ms-attr-src="{{datas.userinfo.photo}}" width="100%" height="100%">-->
								<i class="iconfont icon-finance" style="font-size: 30px;color:#4cd127;"></i>
							</div>
							<div class="ui-list-info">
								<h4 class="ui-nowrap">财务管理</h4>
								<div class="ui-txt-info">{{datas.financeinfo.accountBalance}}元</div>
							</div>
						</li>
						<li class="ui-border-t" ms-click="linkToMyTransactionsinfo" style="padding-bottom:0px;">
							<div class="ui-list-thumb">
								<!-- <img ms-attr-src="{{datas.userinfo.photo}}" width="100%" height="100%">-->
								<i class="iconfont icon-cart" style="font-size: 25px;color:#4cd127;"></i>
							</div>
							<div class="ui-list-info">
								<h4 class="ui-nowrap">我的交易</h4>
								<div class="ui-txt-info"><span class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;"ms-if="datas.myTransactionsWaitToDealSize != 0"></span></div>
							</div>
						</li>
						<li class="ui-border-t" ms-click="linkToSecuritySettings" style="padding-bottom:0px;">
							<div class="ui-list-thumb">
								<!-- <img ms-attr-src="{{datas.userinfo.photo}}" width="100%" height="100%">-->
								<i class="iconfont icon-anquanshezhi" style="font-size: 25px;color:#4cd127;"></i>
							</div>
							<div class="ui-list-info">
								<h4 class="ui-nowrap">安全设置</h4>
								<div class="ui-txt-info">
									<span class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;" ms-if="datas.userinfo.id && !datas.userinfo.payPassword"></span>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</section>
			<script type="text/javascript" data-page="icenter"></script>
		</div>
		<!-- 个人中心导航页面end -->
		<footer class="ui-footer ui-footer-stable">
			<div class="ui-row-flex ui-border-t h-100">
				<div class="ui-col ui-col">
					<div class="center">
						<a href="${pageContext.request.contextPath}/domainname/ibuy.html" rel="external" class="iconfont icon-auction ui-txt-info" style="display: block; width: 100%; height: auto;">
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
						<a href="${pageContext.request.contextPath}/domainname/icenter.html" rel="external" class="iconfont icon-personal " style="display: block; width: 100%; height: auto;">
							<span style="font-size: small; display: block; padding-top: 5px;">我的</span>
						</a>
					</div>
				</div>
			</div>
		</footer>
		
	</div>
</body>
</html>