<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>我正出售的域名</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/frontend-jam/static/js/isell.js"></script>
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<div data-title="我正出售的域名" class="page active ms-controller" ms-controller="isell" id="isell" >
			<script type="text/javascript">
				var sellerDeposit = ${sellerDeposit};
			</script>
			<header class="ui-header ui-header-stable"style= "height:50px;">
				<a style= "top: 40%;class="ui-on-left" ms-click ="linkToIsellSettingPage()"><i class="iconfont m-l-3" ms-class="icon-plus" style="font-size:40px;"></i></a>
				<div style= "margin-left: 80%;top: 40%;"class="ui-on-left" ms-click ="openConfirmDeleteAllDialog"><i class="ui-icon-delete m-l-3" style="font-size:48px;padding-left:10px;color: #04BE02;font-weight:bold;"></i></div>
			</header>
			<section class="ui-container has-footer"><!-- class="ui-icon-add ui-name-icon" -->
				<!--  
				<a class="ui-on-left" ms-click ="linkToIsellSettingPage()"><i class="iconfont m-l-3" ms-class="icon-plus" style="font-size:50px;padding-left:10px;"></i></a>
				-->
				<ul class="ui-list  ui-list-text ui-border-tb">
						<li class=" ui-isell-li ui-border-t " ms-repeat="datas.domainList">
							
							<div class="ui-list-info" ms-click="setup(el.id,$index)">
								<h4 class="ui-nowrap ui-nowrap-new" style="font-size:20px;">{{el.name}}</h4>
								<div class="ui-txt-info ui-time">结拍时间：{{el.endTime | date("yyyy-MM-dd")}}</div>
							</div>
							<div class="ui-list-action" ms-if-loop="datas.userinfo.avoidDeposit == '0'">
								<div  class="ui-isell-check" ms-if-loop="el.status=='01'">审核中</div>
								<div  class="ui-isell-check" ms-if-loop="el.status=='02'">审核失败</div>
								<div  class="ui-isell-check" ms-if-loop="el.status=='00' && (datas.financeinfo.accountBalance - datas.financeinfo.freezeBalance) >= (datas.sellerDeposit + el.bonusShareTotal)">等待提交</div>
								<div  class="ui-isell-check" ms-if-loop="el.status=='00' && (datas.financeinfo.accountBalance - datas.financeinfo.freezeBalance) &lt; (datas.sellerDeposit + el.bonusShareTotal)" ms-click="recharge($index)">等待充值</div>
								<%-- <button class="ui-btn ui-isell-redbag" ms-if-loop="el.status=='03'" ms-click="linkToAddRedPack(el.id)">追加红包</button>--%>
							</div>
							<div class="ui-list-action" ms-if-loop="datas.userinfo.avoidDeposit == '1'">
								<div  class="ui-isell-check" ms-if-loop="el.status=='01'">审核中</div>
								<div  class="ui-isell-check" ms-if-loop="el.status=='02'">审核失败</div>
								<div  class="ui-isell-check" ms-if-loop="el.status=='00' && (datas.financeinfo.accountBalance - datas.financeinfo.freezeBalance) >= el.bonusShareTotal">等待提交</div>
								<div  class="ui-isell-check" ms-if-loop="el.status=='00' && (datas.financeinfo.accountBalance - datas.financeinfo.freezeBalance) &lt; el.bonusShareTotal" ms-click="recharge($index)">等待充值</div>
								<%-- <button class="ui-btn ui-isell-redbag" ms-if-loop="el.status=='03'" ms-click="linkToAddRedPack(el.id)">追加红包</button>--%>
							</div>
							
						</li>
				</ul>
			</section>
			<!-- 充值弹窗 -->
			<div class="ui-dialog dialog-font" id="rechargeForIsell" >
				<div class="ui-dialog-cnt" style="width: 90%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>充值</h3>
					</header>
					<div class="ui-dialog-bd">
						<div class="ui-form ui-border-t">
							<div class="ui-form-item ui-border-b">
								<label>充值金额：</label> <input type="tel" name="rechargeAmount"
									ms-duplex="datas.rechargeAmount"
									id="rechargeAmount" value="" style="padding-left: 80px; width: 96%;" readonly>
							</div>
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;" data-scroll='true'>
							<div>
							<button class="ui-btn-lg ui-btn-primary"
								ms-click="confirmRecharge">微信支付</button>
							<br>
							<button type="button" data-role="button"
								class="select ui-btn-lg ui-btn-primary" id="dialogButton">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 确认删除所有审核未通过的域名弹窗 -->
			<div class="ui-dialog dialog-font" id="deleteAllDialog" data-scroll='true'>
				<div class="ui-dialog-cnt" style="width: 90%;top: 20%; left: 5%;">
					<header class="ui-dialog-hd ui-border-b">
						<h3>删除所有审核失败的域名</h3>
					</header>
					<div class="ui-dialog-bd">
						<div>
							确定要删除所有审核未通过的域名？删除后不可恢复！
						</div>
						<div class="ui-btn-wrap" style="padding: 15px 0px;">
							<button class="ui-btn-lg ui-btn-primary" ms-click="comfirmDeleteAll">确定</button>
							<br>
							<button data-role="button" class="ui-btn-lg ui-btn-primary" ms-click="cancelDeleteAll">取消</button>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript" data-page="isell"></script>
		</div><!-- page -->
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
						<a href="${pageContext.request.contextPath}/domainname/isell.html" rel="external" class="iconfont icon-domain" style="display: block; width: 100%; height: auto;">
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
</body>
</html>