<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<!-- <title>我要买</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>
	<script type="text/javascript">
	//<!--
	var pageData = ${initData};
	// -->
		//var pageData = {};
		<%--pageData.currClient = JSON.parse('${currClient}');
		//pageData.domainList = JSON.parse('${domainList}');
		--%>
	</script>
</head>
<!--
<body class="ms-loader ms-loading">
-->
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<div data-title="我要买" class="page active ms-controller" ms-controller="ibuy" id="ibuy" <c:if test="${isApple }">data-scroll="true"</c:if> >
			<div>
			<section id="dmList" class="ui-container" ms-class="p-t-70">
				<div>
				<div class="ui-row p-r-5 p-l-5">
					<!-- 域名详情循环显示开始 -->
					<div ms-repeat-del="datas.domainList" class="ui-border-b" style="overflow: hidden;">
						<!-- 头像显示 -->
						<div class="ui-col ui-col-20" ms-class="p-t-15:($index > 0)" style="width:15%;max-width: 100px;  min-width: 46px;">
							<div class="ui-justify center p-r-5">
								<img width="90%" height="auto" ms-attr-src="del.client.photo+'96'" alt="name" title="name">
							</div>
							<!-- 分享位置已经调整,暂时保留此代码
							<div class="ui-whitespace center p-t-5">
									<div ms-if="del.bonusShareTotal && !del.endFlag" class="iconfont icon-share-red-bag" ms-click="share($index)"></div>
									<div ms-if="!del.bonusShareTotal || (del.bonusShareTotal && del.endFlag)" class="iconfont icon-share ui-txt-info" ms-click="share($index)"></div>
							</div>
							-->
						</div>
						<!-- 域名详细信息显示 -->
						<div class="ui-col ui-col-80 p-b-10 p-r-10" ms-class="p-t-15:($index > 0)" style="width:85%;">
							<!-- 域名名字，卖家昵称，当前价图标及价格显示 -->
							<ul class="ui-row">
								<li class="ui-col ui-col-50" ms-click="linkToSingleDomainname(del.id)">
									<!--<div class="client-name ui-nowrap">{{del.client.nickname}}</div>-->
									<div class="domain-name ui-nowrap">{{del.name}}</div>
								</li>
								<li class="ui-col ui-col-50">
									<div class="ui-flex ui-flex-pack-end current-money">
										<div class="text">
											<span ms-if="del.currAmount==0">该域名无出价</span>
											<span ms-if="del.currAmount>0 && !getCountDown(del.endTime, $index).disabled">当前 {{del.currAmount | transferCurrentAmount}}</span>
											<span ms-if="del.currAmount>0 && getCountDown(del.endTime, $index).disabled">成交价 {{del.currAmount | transferCurrentAmount}}</span>
										</div>
									</div>
								</li>
							</ul>
							<!-- 域名描述-->
							<div ms-if="del.description" class="clear ibuy-domain-description">{{del.description}}</div>
							<!-- 域名三个图片显示 -->
							<ul class="ui-list ui-list-text m-t-5 ibuy-image-ul">
								<li class="ibuy-image-li">
									<!-- 如果只有一张图片显示 -->
									<div class="ui-list-info p-r-0 only-one-image" ms-attr-id="'only-one-image' + del.id " ms-if="del.image1 && !del.image2">
										<img class="image1" ms-attr-src="{{del.image1}}" ms-attr-alt="{{del.name}}" ms-attr-title="{{del.name}}" ms-click="previewImage($index, 0)">
									</div>
									<!-- 如果有两张或三张图片显示-->
									<img ms-if="del.image1 && del.image2" ms-attr-id="'image1'+ del.id" ms-attr-height="datas.imageHeight" ms-attr-width="datas.imageWidth" ms-attr-src="{{del.image1}}" ms-attr-alt="{{del.name}}" ms-attr-title="{{del.name}}" ms-click="previewImage($index, 0)">
									<img ms-if="del.image2" ms-attr-id="'image2'+ del.id" ms-attr-height="datas.imageHeight" ms-attr-width="datas.imageWidth" ms-attr-src="{{del.image2}}" ms-attr-alt="{{del.name}}" ms-attr-title="{{del.name}}" ms-click="previewImage($index, 1)">
									<img ms-if="del.image3" ms-attr-id="'image3'+ del.id" ms-attr-height="datas.imageHeight" ms-attr-width="datas.imageWidth" ms-attr-src="{{del.image3}}" ms-attr-alt="{{del.name}}" ms-attr-title="{{del.name}}" ms-click="previewImage($index, 2)">
									<%-- 
									<div class="ui-list-info ibuy-image1-div" ms-if="del.image1 && del.image2">
										<img ms-attr-id="'image1'+ del.id" ms-attr-height="datas.imageHeight" ms-attr-width="datas.imageWidth" ms-attr-src="{{del.image1}}" ms-attr-alt="{{del.name}}" ms-attr-title="{{del.name}}" ms-click="previewImage($index, 0)">
									</div>
									<div class="ui-list-info ibuy-image2-div" ms-if="del.image2">
										<img ms-attr-id="'image2'+ del.id" ms-attr-height="datas.imageHeight" ms-attr-width="datas.imageWidth" ms-attr-src="{{del.image2}}" ms-attr-alt="{{del.name}}" ms-attr-title="{{del.name}}" ms-click="previewImage($index, 1)">
									</div>
									<div class="ui-list-info p-r-0 ibuy-image3-div" ms-if="del.image3">
										<img ms-attr-id="'image3'+ del.id" ms-attr-height="datas.imageHeight" ms-attr-width="datas.imageWidth" ms-attr-src="{{del.image3}}" ms-attr-alt="{{del.name}}" ms-attr-title="{{del.name}}" ms-click="previewImage($index, 2)">
									</div>
									<div class="ui-list-info p-r-0 ibuy-image3-div" ms-if="!del.image3">
										<span ms-attr-height="datas.imageHeight" ms-attr-width="datas.imageWidth">&nbsp;</span>
									</div>
									 --%>
								</li>
							</ul>
							<!-- 保证金，结拍时间倒计时 -->
							<ul class="ui-list ui-list-text m-t-_2" style="background-color: #f8f8f8;">
								<li class="ibuy-deposit-li">
									<div class="ui-list-info width-26">
										<span class="iconfont icon-deposit"> </span><span class="ibuy-domain-deposit">{{del.deposit}}元</span>
									</div>
									<div class="ui-list-info p-r-0 width-74">
										<span class="ui-flex ui-flex-pack-end ui-txt-info count-down">距离结束:
											
											<span ms-html="getCountDown(del.endTime, $index).displayTime"></span>	
											 <%--
											 <span class="time"></span>--%>
										</span>
									</div>
								</li>
							</ul>
							<!-- 出价按钮及分享图标 -->
							<div class="p-l-0 p-r-0 m-t-5 share-father-div">
								<!-- 没有结拍 的按钮显示-->
								<div ms-if="del.bidCount != -1 && del.clientId != datas.tmp.currentClientId && !del.endFlag" ms-attr-id="del.id" class="ui-btn-lg ui-btn-primary ibuy-bid-button" ms-click="bidForm( $index )">
									<sapn ms-if="del.bidCount==0 || del.topBidClientId != datas.tmp.currentClientId">出价（{{del.currAmount + del.increment | transferCurrentAmount}}）</sapn>
									<sapn ms-if="del.bidCount>0 && del.topBidClientId == datas.tmp.currentClientId && del.proxyAmount">出价（{{del.proxyAmount + del.proxyIncrement | transferCurrentAmount}}）</sapn>
									<sapn ms-if="del.bidCount>0 && del.topBidClientId == datas.tmp.currentClientId && !del.proxyAmount">出价（{{del.currAmount + del.increment | transferCurrentAmount}}）</sapn>
								</div>
								<!-- 结拍 的按钮显示-->
								<div ms-if="del.bidCount != -1 && del.clientId != datas.tmp.currentClientId && del.endFlag" ms-attr-id="del.id" class="ui-btn-lg ui-btn-primary ibuy-bid-button bid-button-disabled">
									<sapn ms-if="del.bidCount==0 || del.topBidClientId != datas.tmp.currentClientId">出价（{{del.currAmount + del.increment | transferCurrentAmount}}）</sapn>
									<sapn ms-if="del.bidCount>0 && del.topBidClientId == datas.tmp.currentClientId && del.proxyAmount">出价（{{del.proxyAmount + del.proxyIncrement | transferCurrentAmount}}）</sapn>
									<sapn ms-if="del.bidCount>0 && del.topBidClientId == datas.tmp.currentClientId && !del.proxyAmount">出价（{{del.currAmount + del.increment | transferCurrentAmount}}）</sapn>
								</div>
								<div ms-if="del.bidCount == -1" ms-attr-id="del.id" class="ui-btn-lg ui-btn-primary ibuy-bid-button bid-button-disabled">
									<sapn>正在载入</sapn>
								</div>
								<div ms-attr-id="del.id"  ms-if="del.clientId == datas.tmp.currentClientId" class="ui-btn-lg ui-btn-danger active ibuy-bid-button">该域名属于自己</div>
								<!-- 分享 的按钮显示-->
								<div ms-if="del.bonusShareTotal && !del.endFlag && datas.shareBonusEnable == 1" class="iconfont icon-share-red-bag share-red-bag-position" ms-click="share($index)"></div>
								<div ms-if="(!del.bonusShareTotal || (del.bonusShareTotal && del.endFlag)) && datas.shareBonusEnable == 1" class="iconfont icon-fenxiang1 ui-txt-info share-img-position" ms-click="share($index)"></div>
								<div ms-if="datas.shareBonusEnable == 0" class="iconfont icon-fenxiang1 ui-txt-info share-img-position" ms-click="share($index)"></div>
							</div>
							<!-- 关注列表显示 -->
							<%--
							<ul class="ui-row">
								<li class="ui-col ui-col-50"><span class="small-font ui-txt-info">参与及关注人数 {{del.attentionCount}}</span></li>
								<li class="ui-col ui-col-50" style="text-align: right;"><span class="small-font ui-txt-info">查看红包佣金记录</span></li>
							</ul>
							 --%>
							<div class="ui-row" ms-if="datas.shareBonusEnable == 1">
								<div class="all-arrow">
									<span class="m-l-0 ibuy-attention">参与及关注人数 {{del.attentionCount}}</span>
									<a href="#" ms-click="goTobonusRecordSingle(del.id)"><span class="bonus-commission">红包佣金</span></a>
								</div>
							</div>
							<div class="ui-row" ms-if="datas.shareBonusEnable == 0">
								<div class="all-arrow">
									<span class="m-l-0 ibuy-attention">参与及关注人数 {{del.attentionCount}}</span>
									<span class="bonus-commission" ms-click="share($index)">分享</span>
								</div>
							</div>
							<div>
								<ul ms-click="expand" class="ui-justify ui-whitespace list-backgroud-color p-r-0 p-l-0 att-list expand-attendtion" style="text-align: left;text-align: -webkit-left;">
									<li class="iconfont p-l-5 p-r-5" style=" height: 32px; width: 27px;"
											ms-class-1="icon-loved:(del.attentioned)"
											ms-class-2="icon-love:!(del.attentioned)"
											ms-click="switchFollowStatus($index)" >
									</li>
									<!-- TODO 要修正 -->
									<li ms-if="del.attentionCount != -1" class="m-t-3 m-b-3 p-l-5 p-r-5" ms-repeat-ael="del.attentionList">
										<img width="27px" height="27px" ms-attr-src="ael.photo+'46'" ms-data-clientid="ael.id">
									</li>
									<li ms-if="del.attentionCount == -1" class="m-t-3 m-b-3 p-l-5 p-r-5" style="font-size: 11px;">
										正在加载中
									</li>
								</ul>
							</div>

							<!-- 分割线 -->
							<ul class="ui-list-text border-list m-t-_2"><li class="ui-border-b p-t-0"></li></ul>
							
							<!-- 出价记录显示 -->
							<div class="ui-row">
								<div class="all-arrow">
									<span class="ui-panel-subtitle m-l-0 bid-count">出价记录（{{del.bidCount}}）</span>
									<a href="#" ms-click="goBiddingList(del.id)"><span class="bid-count-all">全部</span></a>
								</div>
							</div>
							<!-- 出价记录列表显示 -->
							<div>
								<ul ms-if="del.bidList.size()>0" class="ui-list ui-list-cover list-backgroud-color">
									<li class="p-l-0 p-b-0" ms-class="ui-border-b:(del.bidList.size()-1) != $index" ms-repeat-bel="del.bidList">
										<div class="ui-list-thumb bid-record">
											<img class="bid-record-img" ms-attr-src="bel.photo+'46'">
										</div>
										<div class="p-l-5 p-t-5 p-b-5 ui-list-info">
											<ul class="ui-row">
												<li class="ui-col width-68">
													<!--  <h4 class="ui-nowrap" style="color: #576b95;font-size: 12px;">{{bel.nickname}}</h4>-->
													<div class="ui-txt-warning bid-record-money">￥{{bel.bidAmount | transferCurrentAmount}}元</div>
												</li>
												<li class="ui-col width-32">
													<div class="ui-flex ui-flex-pack-center">
														<div ms-if="$index==0">
															<div class="iconfont icon-top2 bid-record-first-img"></div>
															<div class=" bid-record-first-text">最高得主</div>
														</div>
														<div ms-if="$index>0">
															<div class="iconfont icon-gift-money-active bid-record-second-img" ms-if="$index == 1 && del.bonusSecond"></div>
															<p><span class="bid-record-second-text" ms-if="$index == 1 && del.bonusSecond">次高奖红包</span></p>
															<div class="iconfont icon-out ui-txt-info bid-record-out" ms-if="!del.bonusSecond || $index != 1"></div>
														</div>
													</div>
												</li>
											</ul>
										</div>
									</li>
								</ul>
	
								<div class="list-backgroud-color" ms-if="del.bidList.size()==0">
									<span ms-if="del.bidCount != -1" class="small-font">该域名无人出价</span>
									<span ms-if="del.bidCount == -1" class="small-font">正在加载中</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
			</section>
				<div id="loadmore" class="dropload-down has-footer">
					<div class="dropload-load">
						<div class="dropload-showmore"><span class="loading"></span>正在加载...</div>
						<div class="dropload-nomore">没有更多域名了</div>
					</div>
				</div>
				
			</div>

			<!-- 出价表单 -->
			<div id="bidForm" class="ui-dialog has-footer">
				<div class="ui-dialog-cnt has-footer" style="border-radius: 0px; width: 100%; position: fixed; bottom: 0;">
					<div class="ui-row-flex">
						<div class="ui-col ui-col ui-flex ui-flex-align-center"><div class="p-l-10">当前价&nbsp;&nbsp;<span id="bidCurrent" style="color: #576b95"></span>元</div></div>
						<div class="ui-col ui-col ui-flex ui-flex-pack-end"><div class="iconfont icon-close center p-r-10"></div></div>
					</div>
					<section>
<%-- 						<div class="ui-input ui-border-radius">
							<input type="text" name="bidAmount" value="" placeholder="出价" required="required">
						</div> --%>
						<div class="ui-flex ui-flex-align-center white">
							<div class="ui-flex ui-flex-align-center">
								<span class="p-l-10" style="display: -webkit-box;">出价&nbsp;&nbsp;</span>
								<span id="icon-cursor" class="iconfont icon-cursor"><i id="bidAmount" style="display: -webkit-inline-box; position: relative; right: -8px; bottom: 2px; color: #576b95; font-size: 18px; font-family: Helvetica Neue,Helvetica,STHeiTi,sans-serif;"></i></span>
							</div>
							<i ms-click="clearAmount"class="ui-icon-close" style="position: absolute; right: 7px;"></i>
						</div>
						<div class="service-protocol">
							<span class="p-l-10" ms-click="LinkToServiceProtocol">出价即表示同意《拍域名竞拍服务协议》</span>
						</div>
						<ul class="ui-row ui-whitespace p-t-10">
							<li><button class="ui-btn-lg ui-btn-primary m-b-10" ms-click="bidding">出价</button></li>
						</ul>
					</section>
					<div class="numKeyboard">
						<ul class="ui-row-flex ui-list-text border-list ui-list-active ui-list-cover">
							<li class="ui-col ui-col ui-border-r center white numKey-style" data="1">1</li>
							<li class="ui-col ui-col ui-border-r center white numKey-style" data="2">2</li>
							<li class="ui-col ui-col center white numKey-style" data="3">3</li>
						</ul>
						<ul class="ui-list-text border-list"><li class="ui-border-b p-t-0 p-b-1"></li></ul>
						<ul class="ui-row-flex ui-list-text border-list ui-list-active ui-list-cover">
							<li class="ui-col ui-col ui-border-r center white numKey-style" data="4">4</li>
							<li class="ui-col ui-col ui-border-r center white numKey-style" data="5">5</li>
							<li class="ui-col ui-col center white numKey-style" data="6">6</li>
						</ul>
						<ul class="ui-list-text border-list"><li class="ui-border-b p-t-0 p-b-1"></li></ul>
						<ul class="ui-row-flex ui-list-text border-list ui-list-active ui-list-cover">
							<li class="ui-col ui-col ui-border-r center white numKey-style" data="7">7</li>
							<li class="ui-col ui-col ui-border-r center white numKey-style" data="8">8</li>
							<li class="ui-col ui-col center white numKey-style" data="9">9</li>
						</ul>
						<ul class="ui-list-text border-list"><li class="ui-border-b p-t-0 p-b-1"></li></ul>
						<ul class="ui-row-flex ui-list-text border-list ui-list-active ui-list-cover">
							<li class="ui-col ui-col ui-border-r center num-gray numKey-style" data="00">00</li>
							<li class="ui-col ui-col ui-border-r center white numKey-style" data="0">0</li>
							<li class="ui-col ui-col center num-gray" data="del"><i class="iconfont icon-ret" style="font-size: 25px"></i></li>
						</ul>
					</div>
				</div>
			</div>

			<!-- 保证金充值表单 -->
			<div id="bondForm" class="ui-dialog">
				<div class="ui-dialog-cnt has-footer" style="border-radius: 0px; width: 100%; position: fixed; bottom: 0;" data-scroll="true">
					<header class="ui-dialog-hd ui-border-b">
						<h3>买家保证金</h3>
						<i class="ui-dialog-close" data-role="button"></i>
					</header>
					<section class="ui-border-t">
						<div id = "platformBankInfo" class="ui-dialog-bd ui-border-b hidden">
							<div>拍域名银行信息</div>
							<div> <img src="${pageContext.request.contextPath}/static/images/bank.PNG"></div>
							<div>开户行：招商银行汉中门支行</div>
							<div>开户名：南京登羽信息科技有限公司</div>
							<div>银行账号：125905117710811</div>
							<div> 注：请在打款备注里留下您的米友号。</div>
						</div>
						<div class="ui-whitespace">
							<h1>保证金：<span class="ui-txt-warning">￥ {{datas.tmp.deposit}}元</span></h1>
							<h3>继续参加本次拍卖需缴纳保证金{{datas.tmp.deposit}}元</h3>
							<h3>帐户余额不足，请充值</h3>
						</div>
						<div class="ui-form ui-border-t">
							<form action="#">
								<div class="ui-form-item ui-form-item-radio ui-border-b" ms-click="selectOffLine">
									<label class="ui-radio" for="chargeType">
										<input id="offline" type="radio" name="chargeType">
									</label>
									<p>线下支付</p>
								</div>
								<div class="ui-form-item ui-form-item-radio ui-border-b" ms-click="selectOnLine">
									<label class="ui-radio" for="chargeType">
										<input id="online" type="radio" name="chargeType" checked>
									</label>
									<p>微信支付</p>
								</div>
							</form>
						</div>
						<ul class="ui-row ui-whitespace p-t-10">
							<li><button class="ui-btn-lg ui-btn-primary m-b-10" ms-click="charge(datas.tmp.deposit)">安全支付</button></li>
						</ul>
					</section>
				</div>
			</div>
			<!-- 提示信息 -->
			<div class="ui-dialog dialog-font" id="prompt-msg">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>线下充值提醒</h3>
					</header>
					<div class="ui-dialog-bd">
						<div> 您已操作{{datas.tmp.deposit}}元充值，请尽快完成线下充值。</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
								<button type="button" data-role="button" class="select ui-btn-lg ui-btn-primary" id="dialogButton">确认</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="ui-dialog share-arrow" id="share-float"><img class="share-arrow-image" src="${pageContext.request.contextPath}/static/images/share-arrow.png"></div>
			<script type="text/javascript" data-page="ibuy"></script>
			<%--<div id="debug">
			</div> --%>
			<!-- 非单个域名页面时，顶部消息 -->
			<%--
			<div class="fixed" style="height: 40px;z-index: 21;top: 0px;">
				<div class="ui-newstips-wrap m-t-5">
					<a ms-click="goToAuctionList" class="ui-newstips">
						<span class="p-r-10" ms-if="datas.newsCnt==0">拍卖列表</span>
						<span ms-if="datas.newsCnt>0">新消息</span>
						<span class="ui-badge-num" ms-if="datas.newsCnt>0">{{datas.newsCnt}}</span>
					</a>
				</div>
			</div> --%>
			
			<div class="fixed" style="height: 32px;width: 100%;z-index: 20;top: 0px;background-color: #383939;text-align: center;">
				<a ms-click="goToAuctionList" class="" style="color: white;font-size: 15px; line-height: 28px;">
					<span class="p-r-10 all-arrow" ms-if="datas.newsCnt==0">拍卖列表 </span>
					<span ms-if="datas.newsCnt>0">新消息</span>
					<span class="ui-badge-num" ms-if="datas.newsCnt>0">{{datas.newsCnt}}</span>
				</a>
			</div>
			<div id="openBigPhoto" class="fixed" style="height: 53px;width: 53px;z-index: 23;top: 5px;right: 24px;">
				<img src="${pageContext.request.contextPath}/static/images/brand_logo.jpg" style="height: 100%;"/>
			</div>
			
		</div><!-- /page -->
			<footer class="ui-footer ui-footer-stable">
				<div class="ui-row-flex ui-border-t h-100">
					<div class="ui-col ui-col">
						<div class="center">
							<a class="iconfont icon-auction" style="display: block; width: 100%; height: auto;">
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
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/frontend-jam/static/js/ibuy.js"></script>
</body>
</html>