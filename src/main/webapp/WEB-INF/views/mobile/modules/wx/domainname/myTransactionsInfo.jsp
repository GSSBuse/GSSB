<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>我的交易</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 个人中心:我的交易页面begin -->
		<div data-title="我的交易" class="page active ms-controller" ms-controller="myTransactionsInfo"id="myTransactionsInfo">
			<section class="ui-container has-footer"style="font-family: 微软雅黑; font-size: 15px;">
				<div id="tab2" class="ui-tab">
					<ul class="ui-tab-nav ui-border-b">
						<li class="current" ms-click="promptMessage('doing')">拍卖中</li>
						<li ms-click="promptMessage('waitDeal')">待处理<span class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;"ms-if="datas.myTransactions.waitToDealSize != 0"></span></li>
						<li ms-click="promptMessage('done')">已完成</li>
					</ul>
					<ul class="ui-tab-content" style="width: 200%">
						<li class="current">
							<!-- 我的交易：参与中的交易:拍卖中
							<!--<div ms-if="datas.myTransactions.myTransactionsDoingSize==0">您暂无参与中的交易记录！</div> -->
							<div id="my-transactions-doing" class="ui-body-d ui-content" style="padding: 0px;">
								<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover"	ms-each-del="datas.myTransactions.myTransactionsBuyingList">
									<li class="ui-border-b li-p" ms-click="linkToSingleDomainname(del.domainnameId)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'"width="46px" height="46px">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<li class="ui-col ui-col-50 width-60">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2">
														<span
															class="small-font ui-txt-info">距离结束：{{getCountDown(del.endTime).displayTime}}</span>
													</div>
												</li>
												<li class="ui-col ui-col-50 width-40">
												<!--
													<div class="ui-flex ui-flex-pack-end" ms-if="del.attentionFlag=='0'">
														
														<i class="iconfont icon-love f-s-15" ms-click="addOrCancelAttention('myTransactionsBuyingList',$index,'1')"></i>
														
														<i class="iconfont icon-new-right-top" ms-if="del.newsFlag=='1'"></i>
													</div>
													<div class="ui-flex ui-flex-pack-end" ms-if="del.attentionFlag=='1'">
														<i class="iconfont icon-loved f-s-15"	ms-click="addOrCancelAttention('myTransactionsBuyingList',$index,'0')"></i>
														<i class="iconfont icon-new-right-top" ms-if="del.newsFlag=='1'"></i>
													</div>
												-->
													
													<div class="ui-flex ui-flex-ver ui-flex-align-end ui-flex-pack-end">
														<span class="small-font ui-txt-info">当前价 {{del.bidAmount | transferCurrentAmount}}</span>
													</div>
													<div class="ui-flex ui-flex-pack-end m-t-2">
														<div class="news-style" ms-if="del.newsFlag >'0'">
															<span ms-if="del.newsFlag &gt; '99'">99</span>
															<span ms-if="del.newsFlag &lt; '100'">{{del.newsFlag}}</span>
														</div>
													</div>
												</li>
											</ul>
										</div>
									</li>
								</ul>
								<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover"	ms-each-del="datas.myTransactions.myTransactionsSellingList">
									<li class="ui-border-b li-p" ms-click="linkToSingleDomainname(del.domainnameId)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<li class="ui-col ui-col-50 width-60">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2">
														<span
															class="small-font ui-txt-info">距离结束：{{getCountDown(del.endTime).displayTime}}</span>
													</div>
												</li>
												<li class="ui-col ui-col-50 width-40">
													<!--
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.attentionFlag=='0'">
														<i class="iconfont icon-love f-s-15"	ms-click="addOrCancelAttention('myTransactionsSellingList',$index,'1')"></i>
														<i class="iconfont icon-new-right-top" ms-if="del.newsFlag=='1'"></i>
													</div>
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.attentionFlag=='1'">
														<i class="iconfont icon-loved f-s-15"	ms-click="addOrCancelAttention('myTransactionsSellingList',$index,'0')"></i>
														<i class="iconfont icon-new-right-top" ms-if="del.newsFlag=='1'"></i>
													</div>
													-->
													<div class="ui-flex ui-flex-ver ui-flex-align-end ui-flex-pack-end">
														<span class="small-font ui-txt-info" ms-if="!del.bidAmount">当前无人出价</span> 
														<span class="small-font ui-txt-info" ms-if="del.bidAmount">当前价 {{del.bidAmount | transferCurrentAmount}}</span>
													</div>
													<div class="ui-flex ui-flex-pack-end m-t-2">
														<div class="news-style" ms-if="del.newsFlag >'0'">
															<span ms-if="del.newsFlag &gt; '99'">99</span>
															<span ms-if="del.newsFlag &lt; '100'">{{del.newsFlag}}</span>
														</div>
														<!--
														<div ms-if="del.newsFlag=='1'" class="iconfont icon-yuandian ui-txt-warning" style="font-size: 15px;">
															<span style="  margin-left: -13px;color: white;  font-size: 5px;">新</span>
														</div>
														-->
													</div>
												</li>
											</ul>
										</div>
									</li>
								</ul>
							</div>
						</li>
						<li>
							<!-- 我的交易：待处理 -->
							 <!--<div ms-if="datas.myTransactions.myTransactionsDoneSize==0">您暂无已完成的交易记录！</div> -->
							<div id="my-transactions-waitDeal" class="ui-body-d ui-content"	style="padding: 0px;">
								<!-- 我的交易：待处理 :买 -->
								<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover"
									ms-each-del="datas.myTransactions.myTransactionsBoughtList">
									<li class="ui-border-b li-p" ms-click="linkToSingleDomainname(del.domainnameId)" ms-if="del.status=='12' || del.status=='14' || del.status=='15'">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<li class="ui-col ui-col-50">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2">成交价 {{del.bidAmount | transferCurrentAmount}}</div>
												</li>
												<li class="ui-col ui-col-50">
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.status=='12'">
														<label>等待对方转移</label>
													</div>
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.status=='14'">
														<label>等待经纪人确认</label>
													</div>
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.status=='15'">
														<label>已成交</label>
													</div>
													<div class=" ui-flex ui-flex-pack-end ui-flex-ver ui-flex-align-end m-t-2">
														<span class="small-font ui-txt-info" ms-if="del.status=='12'">{{getCountDown(del.waitTime).displayTime}}</span>
													</div>
												</li>
											</ul>
										</div>
									</li>
									<li class="ui-border-b li-p" ms-if="del.status=='11'" ms-click="openPaymentDialog($index)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
											<!-- ms-click="linkToSingleDomainname(del.domainnameId)" -->
												<li class="ui-col ui-col-50">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2">成交价 {{del.bidAmount | transferCurrentAmount}}</div>
												</li>
												<li class="ui-col ui-col-50">
													<div class="ui-flex ui-flex-pack-end">
														<i class="iconfont icon-wait-pay f-s-15"></i>付款
													</div>
													<div class=" ui-flex ui-flex-pack-end ui-flex-ver ui-flex-align-end m-t-2">
														<span class="small-font ui-txt-info">{{getCountDown(del.waitTime).displayTime}}</span>
													</div>
												</li>
											</ul>
										</div>
									</li>
									<li class="ui-border-b li-p" ms-if="del.status=='13'" ms-click="openComfirmReceiveDomamainnameDialog($index)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<!-- ms-click="linkToSingleDomainname(del.domainnameId)" -->
												<li class="ui-col ui-col-50">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2">成交价 {{del.bidAmount | transferCurrentAmount}}</div>
												</li>
												<li class="ui-col ui-col-50">
													<div class="ui-flex ui-flex-pack-end">
														<i class="iconfont icon-wait-confirm f-s-15"></i>确认收到
													</div>
													<div class=" ui-flex ui-flex-pack-end ui-flex-ver ui-flex-align-end m-t-2">
														<span class="small-font ui-txt-info">{{getCountDown(del.waitTime).displayTime}}</span>
													</div>
												</li>
											</ul>
										</div>
									</li>
								</ul>
								<!-- 我的交易：待处理 :卖 -->
								<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover"	ms-each-del="datas.myTransactions.myTransactionsSoldList">
									<li class="ui-border-b li-p" ms-if="del.status!='12'" ms-click="linkToSingleDomainname(del.domainnameId)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<li class="ui-col ui-col-50">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2">成交价 {{del.bidAmount | transferCurrentAmount}}</div>
												</li>
												<li class="ui-col ui-col-50">
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.status=='11'">
														<label>等待对方付款</label>
													</div>
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.status=='13'">
														<label>等待对方确认</label>
													</div>
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.status=='14'">
														<label>等待经纪人确认</label>
													</div>
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.status=='15'">
														<label>已成交</label>
													</div>
													<div class="ui-flex ui-flex-pack-end ui-flex-ver ui-flex-align-end m-t-2">
														<span class="small-font ui-txt-info" ms-if="del.status=='11' || del.status=='12' || del.status=='13'">{{getCountDown(del.waitTime).displayTime}}</span>
													</div>
												</li>
											</ul>
										</div>
									</li>
									<li class="ui-border-b li-p" ms-if="del.status=='12'" ms-click="openConfirmTransferDomainnameDialog($index)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<!--  ms-click="linkToSingleDomainname(del.domainnameId)" -->
												<li class="ui-col ui-col-50">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2">成交价 {{del.bidAmount | transferCurrentAmount}}</div>
												</li>
												<li class="ui-col ui-col-50">
													<div class="ui-flex ui-flex-pack-end">
														<i class="iconfont icon-wait-transfer f-s-15"></i>确认转移
													</div>
													<div class="ui-flex ui-flex-pack-end ui-flex-ver ui-flex-align-end m-t-2">
														<span class="small-font ui-txt-info">{{getCountDown(del.waitTime).displayTime}}</span>
													</div>
												</li>
											</ul>
										</div>
									</li>
								</ul>
							</div>
						</li>
						<li class="current">
							<!-- 我的交易：已完成的交易
							<!--<div ms-if="datas.myTransactions.myTransactionsDoingSize==0">您暂无参与中的交易记录！</div> -->
							<div id="my-transactions-done" class="ui-body-d ui-content" style="padding: 0px;">
								<!-- 我的交易：已完成 :买 -->
								<ul class="ui-list ui-list-text ui-border-tb  ui-list-active ui-list-cover"
									ms-each-del="datas.myTransactions.myTransactionsDoneBoughtList">
									<li class="ui-border-b li-p" ms-click="linkToSingleDomainname(del.domainnameId)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<li class="ui-col ui-col-50">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2">成交价 {{del.bidAmount | transferCurrentAmount}}</div>
												</li>
												<li class="ui-col ui-col-50">
													<div class="ui-flex ui-flex-pack-end"
														ms-if="del.status=='15'">
														<label>已成交</label>
													</div>
													<div class=" ui-flex ui-flex-pack-end ui-flex-ver ui-flex-align-end m-t-2">
														<span class="small-font ui-txt-info">&nbsp;</span>
													</div>
												</li>
											</ul>
										</div>
									</li>
								</ul>
								<!-- 我的交易：已完成 :卖 -->
								<ul class="ui-list ui-list-text ui-border-tb  ui-list-active ui-list-cover"
									ms-each-del="datas.myTransactions.myTransactionsDoneSoldList">
									<li class="ui-border-b li-p" ms-click="linkToSingleDomainname(del.domainnameId)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<li class="ui-col ui-col-50">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2">成交价 {{del.bidAmount | transferCurrentAmount}}</div>
												</li>
												<li class="ui-col ui-col-50">
													<div class="ui-flex ui-flex-pack-end" ms-if="del.status=='15'">
														<label>已成交</label>
													</div>
													<div class="ui-flex ui-flex-pack-end ui-flex-ver ui-flex-align-end m-t-2">
														<span class="small-font ui-txt-info">&nbsp;</span>
													</div>
												</li>
											</ul>
										</div>
									</li>
								</ul>
								<!-- 我的交易：已完成:异常状态（买） -->
								<ul class="ui-list ui-list-text ui-border-tb  ui-list-active ui-list-cover"
									ms-each-del="datas.myTransactions.myTransactionsBoughtExceptionList">
									<li class="ui-border-b li-p" ms-click="linkToSingleDomainname(del.domainnameId)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<li class="ui-col ui-col-50">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2" ms-if="!del.bidAmount">无人出价</div>
													<div class="ui-txt-warning m-t-2" ms-if="del.bidAmount">成交价 {{del.bidAmount | transferCurrentAmount}}</div>
												</li>
												<li class="ui-col ui-col-50">
													<div class="ui-flex ui-flex-pack-end"ms-if="del.status=='21'">
														<label>您已违约</label>
													</div>
													<div class="ui-flex ui-flex-pack-end"ms-if="del.status=='22'">
														<label>卖家违约</label>
													</div>
													<div class="ui-flex ui-flex-pack-end"ms-if="del.status=='23'">
														<label>流拍</label>
													</div>
													<div class=" ui-flex ui-flex-pack-end ui-flex-ver ui-flex-align-end m-t-2">
														<span class=" small-font ui-txt-info">&nbsp;&nbsp;&nbsp;</span>
													</div>
												</li>
											</ul>
										</div>
									</li>
								</ul>
								<!-- 我的交易：已完成:异常状态（卖） -->
								<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover"
									ms-each-del="datas.myTransactions.myTransactionsSoldExceptionList">
									<li class="ui-border-b li-p" ms-click="linkToSingleDomainname(del.domainnameId)">
										<div class="ui-list-thumb m-r-5 m-t-10 m-b-5">
											<img ms-attr-src="del.photo+'96'" width="46px" height="46px"">
										</div>
										<div class="ui-list-info">
											<ul class="ui-row">
												<li class="ui-col ui-col-50">
													<h4 class="ui-nowrap">{{del.name}}</h4>
													<div class="ui-txt-warning m-t-2" ms-if="!del.bidAmount">无人出价</div>
													<div class="ui-txt-warning m-t-2" ms-if="del.bidAmount">成交价 {{del.bidAmount | transferCurrentAmount}}</div>
												</li>
												<li class="ui-col ui-col-50">
													<div class="ui-flex ui-flex-pack-end"ms-if="del.status=='21'">
														<label>买家违约</label>
													</div>
													<div class="ui-flex ui-flex-pack-end"ms-if="del.status=='22'">
														<label>您已违约</label>
													</div>
													<div class="ui-flex ui-flex-pack-end"ms-if="del.status=='23'">
														<label>流拍</label>
													</div>
													<div class="ui-flex ui-flex-pack-end ui-flex-ver ui-flex-align-end m-t-2">
														<span class=" small-font ui-txt-info">&nbsp;&nbsp;&nbsp;</span>
													</div>
												</li>
											</ul>
										</div>
									</li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
			</section>
			<!-- 我的交易弹窗begin -->
			<!-- 付款弹窗 -->
			<div class="ui-dialog dialog-font" id="paymentDialog">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>付款</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							<form
								action="${pageContext.request.contextPath}/domainname/payment.html"
								method="POST">
								<div class="ui-form-item ui-border-b">
									<label>您需付款：</label> <input type="text"
										ms-duplex="datas.payment.payMoney" name="payMoney"
										id="payMoney" style="padding-left: 80px; width: 96%;" readonly>
								</div>
								<div class="ui-form-item ui-border-b">
									<label>付款域名：</label> <input type="text"
										ms-duplex="datas.payment.domainname" name="domainname"
										id="domainname" style="padding-left: 80px; width: 96%;"
										readonly>
								</div>
								<div class="ui-form-item ui-border-b">
									<a ms-click="linkToSingleDomainnameDetail">点击查看域名详细！</a>
								</div>
								<input type="hidden" ms-duplex="datas.payment.domainnameId"
									name="domainnameId" id="domainnameId" readonly>
							</form>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary" ms-click="openVerificationPayPassword">确定</button>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 支付密码弹窗 -->
			<div class="ui-dialog dialog-font" id="verificationPayPassword">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>安全密码</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							<form
								action="${pageContext.request.contextPath}/domainname/verificationOldPassword.html"
								method="POST">
								<div class="ui-form-item ui-border-b">
									<label>您需付款：</label> <input type="text"
										ms-duplex="datas.payment.payMoney" name="payMoney"
										id="payMoney" style="padding-left: 80px; width: 96%;" readonly>
								</div>
								<div class="ui-form-item ui-border-b">
									<label>安全密码：</label> <input type="password"
										ms-duplex="datas.payment.payPassword" name="payPassword"id="payPassword" style="padding-left: 80px; width: 96%;">
										<i class="ui-icon-close" ms-class-1="hidden:datas.payment.payPassword.length==0" ms-click="clearPayPasswordInput"> </i>
								</div>
							</form>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary" ms-click="confirmPay">确定</button>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 确认收到域名弹窗 -->
			<div class="ui-dialog dialog-font" id="comfirmReceiveDomamainnameDialog">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>确认收到域名</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							确认收到卖家域名了吗？确认收到将不可更改，谨慎操作！<a ms-click="linkToSingleDomainnameDetail">点击查看域名详细！</a>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<button class="ui-btn-lg ui-btn-primary" ms-click="receiveDomamainname(datas.index)">确认收到</button>
							<br>
							<button type="button" data-role="button" class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 确认转移域名弹窗 -->
			<div class="ui-dialog dialog-font" id="confirmTransferDomainnameDialog">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>确认转移域名</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							确认已经转移域名给买家了吗？虚假操作，责任自负，谨慎操作！<a ms-click="linkToSingleDomainnameDetail">点击查看域名详细！</a>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary" ms-click="confirmTransferDomainname(datas.index)">确认转移</button>
							<br>
							<button type="button" data-role="button" class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 充值弹窗 -->
			<div class="ui-dialog dialog-font" id="rechargeForPay" >
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>充值</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label>充值金额：</label> <input type="tel" name="rechargeAmount"
									ms-duplex="datas.cashflowinfo.rechargeAmount"
									id="rechargeAmount" value="" placeholder="请输入充值金额"
									style="padding-left: 80px; width: 96%;"><i
									class="ui-icon-close"
									ms-class-1="hidden:datas.cashflowinfo.rechargeAmount==0"
									ms-click="clearRechargeAmountInput"> </i>
							</div>
							<div class="ui-form-item ui-form-item-radio ui-border-b">
								<label class="ui-radio" for="radio"> <input id="radio1"
									type="radio" name="radio">
								</label>
								<p>线下支付</p>
							</div>
							<div class="ui-form-item ui-form-item-radio ui-border-b">
								<label class="ui-radio" for="radio"> <input id="radio2"
									type="radio" name="radio" checked>
								</label>
								<p>微信支付</p>
							</div>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary"
								ms-click="confirmRecharge">确定</button>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
				<!-- 提示平台银行信息 -->
			<div class="ui-dialog dialog-font" id="platform-bank-info">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>米乐拍卖银行信息</h3>
					</header>
					<div class="ui-dialog-bd">
						<div> 注：请在打款备注里留下您的米友号。</div>
						<div> <img src="${pageContext.request.contextPath}/static/images/bank.PNG"></div>
						<div>开户行：招商银行汉中门支行</div>
						<div>开户名：南京登羽信息科技有限公司</div>
						<div>银行账号：125905117710811</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
								<button type="button" data-role="button" class="select ui-btn-lg ui-btn-primary" id="dialogButton">确认</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 我的交易弹窗end -->
			<script type="text/javascript" data-page="myTransactionsInfo"></script>
		</div>
		<!-- 个人中心：我的交易页面end -->
		
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
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/frontend-jam/static/js/myTransactionsInfo.js"></script>
</body>
</html>