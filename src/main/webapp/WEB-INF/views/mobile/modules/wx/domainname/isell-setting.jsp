<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- <title>出售域名</title> -->
 <title>米乐拍卖</title>
<%@include file="/WEB-INF/views/include/common.jsp"%>
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<div data-title="出售域名" id="isell-setting" ms-controller="isell-setting" class="page ms-controller" id="isell-setting">
			<script type="text/javascript">
				var sellerDeposit = ${sellerDeposit};
				var Lately7DayDomianNumList = ${Lately7DayDomianNumList};
			</script>
			<input id="from" value="${from}" type="hidden">
			<header class="ui-header ui-header-stable">
				<i class="ui-icon-return" onclick="history.back()"></i>
				<button class="ui-btn ui-btn-primary sub" id="submitDomain" ms-click="submitDomain()" ms-if="datas.from=='setDomainname' || datas.from == 'changeDomainname'">提交</button>
				<button class="ui-btn ui-btn-primary" id="submitDomain" disabled="disabled" ms-if="datas.from=='seeDomainname'">提交</button>
				<div class="service-protocol" style="margin-top: 25px;">
					<span class="p-l-10" ms-click="LinkToServiceProtocol">提交即表示同意<span style="color:#586c96;">《米乐拍卖竞拍服务协议》</span></span>
				</div>
			</header>
			<section class="ui-container has-footer" style="border-top-width:44px;">
				<!--  
				<ul class="ui-list ui-list-text ui-border-tb">
					<li class="ui-border-t">
						<div class="ui-list-info">
							<h4 class="ui-btn" onclick="history.back()">返回</h4>
						</div>
						<div class="ui-btn ui-btn-primary" id="submitDomain" ms-click="submitDomain()" ms-if="datas.from=='setDomainname'">提交</div>
						<button class="ui-btn ui-btn-primary" id="submitDomain" disabled="disabled" ms-if="datas.from=='seeDomainname'">提交</button>
					</li>
				</ul>
				-->
				<form id="commitform" action="uploadImage"	enctype="multipart/form-data" method="post" style="margin-top: 0px;">
					<%-- <div class="ui-form-item  ui-form-background">
						<label id="name-label">域名</label> <input type="text" name="name" id="name" maxlength="63"
							ms-duplex-required="datas.domainInfo.name" placeholder=""/>
					</div>--%>
					<div class="ui-form-item ui-form-item-textarea ui-form-background ui-form-common"
						style="height: 65px; ">
						<label id="description-label">域名</label>
						<textarea name="name" id="name" placeholder="仅接受com/cn/com.cn短域名和双拼，及其他顶级域名，如88.net" maxlength="63"
							ms-duplex-required="datas.domainInfo.name" style="height: 65px;margin-top:6px;"
							required></textarea>
					</div>
					<div class="ui-form-item ui-form-item-textarea ui-form-background ui-form-common"
						style="height: 100px; ">
						<label id="description-label">描述</label>
						<textarea name="description" id="description" placeholder="特点价值等…" maxlength="100"
							ms-duplex="datas.domainInfo.description" style="height: 100px;margin-top:6px;"
							required></textarea>
					</div>
					<div class="ui-form-item ui-form-common" style="padding-top: 10px;height:70px;">
						<label id="image-label">图片</label> 
						<img id="image1" name="image1" ms-attr-src="{{datas.domainInfo.image1}}" ms-click="chooseImages('image1')"
							alt="上传照片" title=""
							style="width: 58px; height: 58px; margin-left: 95px"> 
						<img id="image2" name="image2"
							ms-attr-src="{{datas.domainInfo.image2}}" ms-click="chooseImages('image2')"
							alt="上传照片" title=""
							style="width: 58px; height: 58px; margin-left: 5px"> 
						<img id="image3" name="image3"
							ms-attr-src="{{datas.domainInfo.image3}}" ms-click="chooseImages('image3')"
							alt="上传照片" title=""
							style="width: 58px; height: 58px; margin-left: 5px">
					</div>
					<div class="ui-form-item ui-form-common">
						<label>保留价</label> <input type="number" name="reservePrice"
							id="reservePrice" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="无保留价更容易拍出高价"
							ms-duplex-number="datas.domainInfo.reservePrice">
					</div>
					<div class="ui-form-item  ui-form-common">
						<label for="endtime">截止日期</label>
						<select name="endTime" id="endTime" ms-duplex="datas.domainInfo.endTime" style="width: 150px; margin-left: 95px; height:28px; line-height:28px;"></select>
					</div>
					<c:if test="${shareBonusEnable eq '1'}">
					<ul class="ui-list m-t-10">
						<li style=" padding-bottom: 0px;" ms-click="switchifTransmitWelStatus()">
							<div class="ui-list-thumb" style="height: 40px;margin: 0px 0px 0px 0;">
								<i id="ifTransmitWel" class="iconfont m-l-3"
								ms-class-1="icon-gift-money-active:(datas.ifTransmitWel)" 
								ms-class-2="icon-gift-money:!(datas.ifTransmitWel)"></i>
							</div>
							<div class="ui-list-info" style="padding:0px;">
								转发送红包
							</div>
						</li>
					</ul>
					<ul class="ui-list ui-list-text" id = "shareRedPack" style="display:none;margin-top:10px;">
						<li style=" padding-bottom: 0px;padding-top: 0px; height: 40px;">
							<div class="ui-list-info">
								<h4 class="ui-nowrap" style="padding-left: 47px;">红包个数</h4>
							</div>
							<div class="ui-form-item">
								<input type="number" style="width: 60px;padding-left: 5px;" onkeyup="value=value.replace(/[^\d]/g,'')"	name="bonusShareNumber" id="bonusShareNumber" ms-duplex-number="datas.domainInfo.bonusShareNumber">个
							</div>
						</li>
						<li style=" padding-bottom: 0px;padding-top: 0px; height: 40px;">
							<div class="ui-list-info">
								<h4 class="ui-nowrap" style="padding-left: 47px;">总金额</h4>
							</div>
							<div class="ui-form-item">
								<input 	type="number" style="width: 60px;padding-left: 5px;" onkeyup="value=value.replace(/[^\d]/g,'')" name="bonusShareTotal" id="bonusShareTotal" ms-duplex-number="datas.domainInfo.bonusShareTotal">元
							</div>
						</li>
					</ul>
					</c:if>
					<ul class="ui-list m-t-10">
						<li style=" padding-bottom: 0px;" ms-click="switchifRewardSecondStatus()">
							<div class="ui-list-thumb" style="height: 40px;margin: 0px 0px 0px 0;">
								<i id="ifRewardSecond" class="iconfont m-l-3" 
								ms-class-1="icon-gift-money-active:(datas.ifRewardSecond)"
								ms-class-2="icon-gift-money:!(datas.ifRewardSecond)"></i>
							</div>
							<div class="ui-list-info" style="padding:0px;">
								次高价红包
							</div>
						</li>
					</ul>
					<ul class="ui-list ui-list-text" id = "secondRedPack" style="display:none;margin-top:10px;">
						<li style=" padding-bottom: 0px;padding-top: 0px; height: 40px;">
							<div class="ui-list-info">
								<h4 class="ui-nowrap" style="padding-left: 47px;">总金额</h4>
							</div>
							<div class="ui-form-item">
								<input type="number" style="width: 60px;padding-left: 5px;"  style="width: 42px; padding-left: 0px; text-align: right; " name="bonusSecond" id="bonusSecond" onkeyup="value=value.replace(/[^\d]/g,'')" ms-duplex-number="datas.domainInfo.bonusSecond">元
							</div>
						</li>
					</ul>
				</form>
				<!--  <div id="scroll-up" style="height:200px;width:99%;display:none;"></div>-->
			</section>
			<!-- 充值弹窗 -->
			<div class="ui-dialog dialog-font" id="rechargeForSubDomain" >
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
								class="select ui-btn-lg ui-btn-primary" id="dialogButton" ms-click="cancleRecharge()">取消</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript" data-page="isell-setting"></script>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>