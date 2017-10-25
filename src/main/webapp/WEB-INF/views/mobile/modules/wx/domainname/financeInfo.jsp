<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>财务管理</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<!-- 个人中心:我的财务页面begin -->
		<div data-title="财务管理" class="page ms-controller" ms-controller="financeInfo"
			id="financeInfo">
			<section class="ui-container has-footer" id="financial-management">
				<div class="ui-body-d ui-content" style="background: #686F78;">
					<ul class="ui-list ui-list-text ui-border-tb" style="background:  #686F78;">
						<li class="ui-border-t" style="height: 100px;">
							<div class="ui-list-info"
								style="width: 33%; text-align: center; color: white;">
								<h4 class="ui-nowrap">
									<i class="iconfont icon-account-balance" style="color: white;font-size:23px;"></i><span
										style="display: block;">账户余额</span><span
										style="display: block;">{{datas.financeinfo.accountBalance}}元</span>
								</h4>
							</div>
							<div class="ui-list-info"
								style="width: 33%; text-align: center; color: white;">
								<h4 class="ui-nowrap">
									<i class="iconfont icon-freeze-balance" style="color: white;font-size:23px;"></i><span
										style="display: block;">冻结金额</span><span
										style="display: block;">{{datas.financeinfo.freezeBalance}}元</span>
								</h4>
							</div>
							<div style="width: 33%; text-align: center; color: white;">
								<h4 class="ui-nowrap">
									<i class="iconfont icon-available-balance" style="color: white;font-size:30px;"></i><span
										style="display: block;">可用余额</span><span
										style="display: block;">{{datas.available_balance}}元</span>
								</h4>
							</div>
						</li>
					</ul>
				</div>
				<!--  暂时关闭此部分功能begin-->
				<div class="ui-body-d ui-content" style="margin-top: 10px;" ms-if="datas.shareBonusEnable == 1">
					<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover">
						<li class="ui-border-t record" ms-click="linkToBonusRecord">
							<div class="ui-list-info">
								<h4 class="ui-nowrap">领红包记录</h4>
							</div>
							<div>
								<i	class="iconfont icon-check iconfont-finance"></i>
							</div>
						</li>
					</ul>
				</div>

				<div class="ui-body-d ui-content" style="margin-top: 10px;" ms-if="datas.shareBonusEnable == 1">
					<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover">
						<li class="ui-border-t record" ms-click="linkToCommissionRecord">
							<div class="ui-list-info">
								<h4 class="ui-nowrap">领佣金记录</h4>
							</div>
							<div>
								<i	class="iconfont icon-check iconfont-finance"></i>
							</div>
						</li>
					</ul>
				</div>
				<!--  暂时关闭此部分功能end-->

				<div class="ui-body-d ui-content" style="margin-top: 10px;">
					<ul class="ui-list ui-list-text ui-border-tb">
						<li class="ui-border-t record">
							<div class="ui-list-info" style="width:50%;" ms-click="recharge">
								<h4 class="ui-nowrap">
									<i class="iconfont icon-acount-in iconfont-finance"></i>
									<span >充值</span>
								</h4>
							</div>
							<div style="width:50%;text-align:right;" ms-click="withdrawals">
								<span class="ti-xian">提现</span>
								<i class="iconfont icon-acount-out iconfont-finance"></i>
							</div>
						</li>
					</ul>
				</div>

				<div class="ui-body-d ui-content" style="margin-top: 10px;">
					<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover">
						<li class="ui-border-t record" ms-click="goToFreezeRecord">
							<div class="ui-list-info">
								<h4 class="ui-nowrap">冻结记录</h4>
							</div>
							<div>
								<i	class="iconfont icon-check iconfont-finance"></i>
							</div>
						</li>
					</ul>
				</div>
				<div class="ui-body-d ui-content" style="margin-top: 10px;">
					<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover">
						<li class="ui-border-t record" ms-click="linkToCashFlow">
							<div class="ui-list-info">
								<h4 class="ui-nowrap">资金流水</h4>
							</div>
							<div>
								<i	class="iconfont icon-cashflow iconfont-finance"></i>
							</div>
						</li>
					</ul>
				</div>
				<div class="ui-body-d ui-content" style="margin-top: 10px;">
					<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover">
						<li class="ui-border-t record" ms-click="linkToWithdrawalsProgress">
							<div class="ui-list-info">
								<h4 class="ui-nowrap">提现进度</h4>
							</div>
							<div>
								<i	class="iconfont icon-check iconfont-finance"></i>
							</div>
						</li>
					</ul>
				</div>
				<%--
				<div class="ui-body-d ui-content" style="margin-top: 10px;">
					<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover">
						<li class="ui-border-t record" ms-click="open_income_expense_dialog">
							<div class="ui-list-info">
								<h4 class="ui-nowrap">银行信息</h4>
							</div>
							<div>
								<span ms-if="!datas.userinfo.defaultIncomeExpense" class="iconfont icon-yuandian ui-txt-warning" style="font-size: 10px;" ></span>
								<i class="iconfont icon-check iconfont-finance"></i>
							</div>
						</li>
					</ul>
				</div>
				 --%>
				<div class="ui-body-d ui-content" style="margin-top: 10px;">
					<ul class="ui-list ui-list-text ui-border-tb ui-list-active ui-list-cover">
						<li class="ui-border-t record" ms-click="seeplatformBankInfo">
							<div class="ui-list-info">
								<h4 class="ui-nowrap">平台账户</h4>
							</div>
							<div>
								<i class="iconfont icon-check iconfont-finance"></i>
							</div>
						</li>
					</ul>
				</div>
			</section>
			<!--财务管理弹窗begin-->
			<!--提现弹窗 -->
			<div class="ui-dialog dialog-font" id="withdrawals">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>提现</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label><b>您的提现：</b></label> <input type="text" value=""
									readonly style="padding-left: 80px; width: 96%;">
							</div>
							<div class="ui-form-item ui-border-b">
								<label>提现账号：</label> <input type="text"
									ms-duplex="datas.userinfo.defaultIncomeExpense" readonly
									style="padding-left: 80px; width: 96%;">
							</div>
							<div class="ui-form-item ui-border-b">
								<label>提现金额：</label> <input type="tel" name="operate_amount"
									ms-duplex="datas.cashflowinfo.operateAmount"
									id="operate_amount" placeholder="请输入提现金额"
									style="padding-left: 80px; width: 96%;"><i
									class="ui-icon-close"
									ms-class-1="hidden:datas.cashflowinfo.operateAmount.length==0"
									ms-click="clearOperateAmountInput"> </i>
							</div>
							<input type="hidden" id="operate" name="operate"
								ms-duplex="datas.cashflowinfo.operate">
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary"
								ms-click="openVerificationPayPasswordForWithdrawals">确定</button>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 提现支付密码弹窗 -->
			<div class="ui-dialog dialog-font" id="verificationPayPasswordForWithdrawals">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>安全密码</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label>您提现：</label> <input type="text"
									ms-duplex="datas.cashflowinfo.operateAmount" style="padding-left: 80px; width: 96%;" readonly>
							</div>
							<div class="ui-form-item ui-border-b">
								<label>安全密码：</label> <input type="password"
									ms-duplex="datas.cashflowinfo.payPassword" name="payPassword"id="payPassword" style="padding-left: 80px; width: 96%;">
									<i class="ui-icon-close" ms-class-1="hidden:datas.cashflowinfo.payPassword.length==0" ms-click="clearPayPasswordInput"> </i>
							</div>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary" ms-click="confirmWithdrawals">确定</button>
							<br>
							<button class="select ui-btn-lg ui-btn-primary" id="dialogButton" ms-click="clearForWithdrawals">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 充值弹窗 -->
			<div class="ui-dialog dialog-font" id="recharge" >
				<div class="ui-dialog-cnt" style="width: 99%;">
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
							<div id = "platformBankInfo" class="ui-dialog-bd ui-border-b hidden">
								<div>米乐拍卖银行信息</div>
								<div> <img src="${pageContext.request.contextPath}/static/images/bank.PNG"></div>
								<div>开户行：招商银行汉中门支行</div>
								<div>开户名：南京登羽信息科技有限公司</div>
								<div>银行账号：125905117710811</div>
								<div> 注：请在打款备注里留下您的米友号。</div>
							</div>
							<div class="ui-form-item ui-form-item-radio ui-border-b"  ms-click = "selectOffLine">
								<label class="ui-radio" for="radio"> <input id="offline"
									type="radio" name="radio">
								</label>
								<p>线下支付</p>
							</div>
							<div class="ui-form-item ui-form-item-radio ui-border-b"  ms-click = "selectOnLine">
								<label class="ui-radio" for="radio"> <input id="online"
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
			<!-- 提示信息 -->
			<div class="ui-dialog dialog-font" id="prompt-msg">
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>线下充值提醒</h3>
					</header>
					<div class="ui-dialog-bd">
						<div> 您已操作{{datas.cashflowinfo.amount}}元充值，请尽快完成线下充值。</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
								<button type="button" data-role="button" class="select ui-btn-lg ui-btn-primary" id="dialogButton">确认</button>
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
						<div> <img src="${pageContext.request.contextPath}/static/images/bank.PNG"></div>
						<div>开户行：招商银行汉中门支行</div>
						<div>开户名：南京登羽信息科技有限公司</div>
						<div>银行账号：125905117710811</div>
						<div> 注：请在打款备注里留下您的米友号。</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
								<button type="button" data-role="button" class="select ui-btn-lg ui-btn-primary" id="dialogButton">确认</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--财务管理弹窗end-->
			<script type="text/javascript" data-page="financeInfo"></script>
		</div>
		<!-- 个人中心：我的财务页面end -->
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>