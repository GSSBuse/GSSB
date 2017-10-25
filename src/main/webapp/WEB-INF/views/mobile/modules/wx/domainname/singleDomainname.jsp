<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>
	<script type="text/javascript">
		var singlePage = true;
	</script>
</head>
<!--
<body class="ms-loader ms-loading">
-->
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<div class="page active ms-controller" ms-controller="singleDomainname" id="singleDomainname">
			<header class="ui-header ui-header-stable">
				<i ms-if="!datas.singlePage" class="ui-icon-return" onclick="history.back()"></i>
				<h1></h1><a ms-click="linkToAllDomain" class="ui-btn">查看所有域名</a>
			</header>
			<section class="ui-container p-t-0_2" ms-class="has-footer:!(datas.singlePage)">
				<div class="ui-row p-r-5 p-l-5">
					<!-- 域名详情循环显示开始 -->
					<div ms-repeat-del="datas.domainList" class="ui-border-b" style="overflow: hidden;">
						<!-- 头像显示 -->
						<div class="ui-col ui-col-20" ms-class="p-t-15:($index > 0)" style="width:15%;max-width: 100px;  min-width: 46px;">
							<div class="ui-justify center p-r-5">
								<img width="90%" height="auto" ms-attr-src="del.client.photo+'96'" alt="name" title="name">
							</div>
							<%--  挪位置了
							<div class="ui-whitespace center p-t-5">
									<div ms-if="del.bonusShareTotal && !del.endFlag" class="iconfont icon-share-red-bag" ms-click="share($index)"></div>
									<div ms-if="!del.bonusShareTotal || (del.bonusShareTotal && del.endFlag)" class="iconfont icon-share ui-txt-info" ms-click="share($index)"></div>
							</div>
							--%>
						</div>
						<!-- 域名详细信息显示 -->
						<div class="ui-col ui-col-80 p-b-10 p-r-10" ms-class="p-t-15:($index > 0)" style="width:85%;">
							<!-- 域名名字，卖家昵称，当前价图标及价格显示 -->
							<ul class="ui-row">
								<li class="ui-col ui-col-50" ms-click="linkToSingleDomainname(del.id)">
									<!--<div class="client-name ui-nowrap">{{del.client.nickname}}</div>-->
									<div class="domain-name ui-nowrap" style="word-wrap: break-word;white-space: normal;">{{del.name}}</div>
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
									<div style="padding-right: 2px;" class="ui-list-info p-r-0" ms-if="del.image1 && !del.image2">
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
										<span ms-if="del.status == '03'" class="ui-flex ui-flex-pack-end ui-txt-info count-down">距离结束:
											<span ms-html="getCountDown(del.endTime, $index).displayTime"></span>	
										</span>
										<span ms-if="del.status != '03'" class="ui-flex ui-flex-pack-end ui-txt-info count-down">结拍时间:
											<span>{{del.endTime | date("yyyy-MM-dd")}}</span>	
										</span>
									</div>
								</li>
							</ul>
							<!-- 出价按钮 -->
							<div class="p-l-0 p-r-0 m-t-5 share-father-div">
								<!-- 没有结拍 的按钮显示-->
								<div ms-if="del.clientId != datas.tmp.currentClientId && !del.endFlag" ms-attr-id="del.id" class="ui-btn-lg ui-btn-primary ibuy-bid-button" ms-click="bidFormSingle( $index )">
									<sapn ms-if="del.bidCount==0 || del.topBidClientId != datas.tmp.currentClientId">出价（{{del.currAmount + del.increment | transferCurrentAmount}}）</sapn>
									<sapn ms-if="del.bidCount>0 && del.topBidClientId == datas.tmp.currentClientId && del.proxyAmount">出价（{{del.proxyAmount + del.proxyIncrement | transferCurrentAmount}}）</sapn>
									<sapn ms-if="del.bidCount>0 && del.topBidClientId == datas.tmp.currentClientId && !del.proxyAmount">出价（{{del.currAmount + del.increment | transferCurrentAmount}}）</sapn>
								</div>
								<!-- 结拍 的按钮显示-->
								<div ms-if="del.clientId != datas.tmp.currentClientId && del.endFlag" ms-attr-id="del.id" class="ui-btn-lg ibuy-bid-button bid-button-disabled">
									<sapn ms-if="del.bidCount==0 || del.topBidClientId != datas.tmp.currentClientId">出价（{{del.currAmount + del.increment | transferCurrentAmount}}）</sapn>
									<sapn ms-if="del.bidCount>0 && del.topBidClientId == datas.tmp.currentClientId && del.proxyAmount">出价（{{del.proxyAmount + del.proxyIncrement | transferCurrentAmount}}）</sapn>
									<sapn ms-if="del.bidCount>0 && del.topBidClientId == datas.tmp.currentClientId && !del.proxyAmount">出价（{{del.currAmount + del.increment | transferCurrentAmount}}）</sapn>
								</div>
								<div ms-attr-id="del.id"  ms-if="del.clientId == datas.tmp.currentClientId" class="ui-btn-lg ui-btn-danger active ibuy-bid-button">该域名属于自己</div>
								<!-- 分享 的按钮显示-->
								<div ms-if="del.bonusShareTotal && !del.endFlag && datas.shareBonusEnable == 1" class="iconfont icon-share-red-bag share-img-position" ms-click="share($index)"></div>
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
								<ul class="ui-justify ui-whitespace list-backgroud-color p-r-0 p-l-0" style="text-align: left; text-align: -webkit-left;">
									<li class="iconfont p-l-5 p-r-5" style=" height: 32px; width: 27px;"
											ms-class-1="icon-loved:(del.attentioned)"
											ms-class-2="icon-love:!(del.attentioned)"
											ms-click="switchFollowStatus($index)" >
									</li>
									<!-- TODO 要修正 -->
									<li class="m-t-3 m-b-3 p-l-5 p-r-5" ms-repeat-ael="del.attentionList">
										<img width="27px" height="27px" ms-attr-src="ael.photo+'46'" ms-data-clientid="ael.id">
									</li>
								</ul>
							</div>

							<!-- 分割线 -->
							<ul class="ui-list-text border-list m-t-_2"><li class="ui-border-b p-t-0"></li></ul>
							
							<!-- 出价记录显示 -->
							<div class="ui-row">
								<div class="all-arrow">
									<span class="ui-panel-subtitle m-l-0 bid-count">出价记录（{{del.bidCount}}）</span>
									<span class="small-font red" ms-if="del.reservePrice && !del.proxyAmount && del.currAmount<del.reservePrice">未过保留价</span>
									<span class="small-font green" ms-if="del.reservePrice && !del.proxyAmount && del.currAmount>=del.reservePrice">出价已达保留价</span>
									<span class="small-font red" ms-if="del.reservePrice && del.proxyAmount && del.proxyAmount<del.reservePrice">代理价未过保留价</span>
									<span class="small-font green" ms-if="del.reservePrice && del.proxyAmount && del.proxyAmount>=del.reservePrice">代理价已达保留价</span>
									
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
									<span class="small-font">该域名无人出价</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- 出价表单 -->
			<div id="bidFormSingle" class="ui-dialog">
				<div class="ui-dialog-cnt" style="border-radius: 0px; width: 100%; position: fixed; bottom: 0;">
					<div class="ui-row-flex">
						<div class="ui-col ui-col ui-flex ui-flex-align-center"><div class="p-l-10">当前价&nbsp;&nbsp;<span id="bidCurrentSingle" style="color: #576b95"></span>元</div></div>
						<div class="ui-col ui-col ui-flex ui-flex-pack-end"><div class="iconfont icon-close center p-r-10"></div></div>
					</div>
					<section>
<!-- 						<div class="ui-input ui-border-radius">
							<input type="text" name="bidAmount" value="" placeholder="出价" required="required">
						</div> -->
						<div class="ui-flex ui-flex-align-center white">
							<div class="ui-flex ui-flex-align-center">
								<span class="p-l-10" style="display: -webkit-box;">出价&nbsp;&nbsp;</span>
								<span id="icon-cursor" class="iconfont icon-cursor"><i id="bidAmountSingle" style="display: -webkit-inline-box; position: relative; right: -8px; bottom: 2px; color: #576b95; font-size: 18px; font-family: Helvetica Neue,Helvetica,STHeiTi,sans-serif;"></i></span>
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
			<div id="bondFormSingle" class="ui-dialog">
				<div class="ui-dialog-cnt" ms-class-1="has-footer:(!datas.singlePage)" style="border-radius: 0px; width: 100%; position: fixed; bottom: 0;">
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
							<h1>需充值：<span class="ui-txt-warning">￥ {{datas.tmp.charge}}元</span></h1>
							<h3>继续参加本次拍卖需缴纳保证金{{datas.tmp.deposit}}元</h3>
							<h3>帐户可用余额不足，请充值</h3>
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
							<li><button class="ui-btn-lg ui-btn-primary m-b-10" ms-click="charge(datas.tmp.charge)">安全支付</button></li>
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
			<div class="ui-dialog share-arrow" id="share-float-single"><img class="share-arrow-image" src="${pageContext.request.contextPath}/static/images/share-arrow.png"></div>
			<script type="text/javascript" data-page="singleDomainname"></script>

		</div><!-- /page -->
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/frontend-jam/static/js/singleDomainname.js"></script>
</body>
</html>