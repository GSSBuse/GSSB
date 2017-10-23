<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>追加红包</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<div data-title="追加红包" class="page active ms-controller" ms-controller="addRedPack"	id="addRedPack">
			<section class="ui-container font-styles" id="financial-management">
				<ul class="ui-list ui-list-text" style="color:white;background-image:url(${pageContext.request.contextPath}/static/images/redbag-back.jpg);background-position: 0% 90%;">
					<li>
						<div class="ui-list-info" style="width:30%;"onclick="history.back()">
							<span>返回</span>
						</div>
						<div style="width:70%;">
							{{datas.domainInfo.name}}
						</div>
					</li>
				</ul>
				<c:if test="${shareBonusEnable eq '1'}">
				<ul class="ui-list m-t-10">
					<li style=" padding-bottom: 0px;">
						<div class="ui-list-thumb" style="height: 40px;margin: 0px 0px 0px 0;">
							<i id="ifTransmitWel" class="iconfont m-l-3 icon-gift-money-active"></i>
						</div>
						<div class="ui-list-info" style="padding:0px;">
							<span>追加转发送红包</span><span class="font-prompt">(已有{{datas.domainInfo.bonusShareNumber}}个,共{{datas.domainInfo.bonusShareTotal}}元)</span>
						</div>
					</li>
				</ul>
				<ul class="ui-list ui-list-text addRedPackUl">
					<li style=" padding-bottom: 0px;padding-top: 0px; height: 40px;">
						<div class="ui-list-info">
							<span>追加个数</span>
						</div>
						<div class="ui-form-item font-styles">
							<input type="number" style="width: 60px;padding-left: 5px;" onkeyup="value=value.replace(/[^\d]/g,'')"	name="bonusShareNumber" id="bonusShareNumber" ms-duplex-number="datas.temp.bonusShareNumber"><span>个</span>
						</div>
					</li>
				</ul>
				<ul class="ui-list ui-list-text addRedPackUl">
					<li style=" padding-bottom: 0px;padding-top: 0px; height: 40px;">
						<div class="ui-list-info">
							<span>追加金额</span><span style="font-size:10px;font-family:微软雅黑;color:red;">{{datas.temp.promptStr}}</span>
						</div>
						<div class="ui-form-item font-styles">
							<input 	type="number" style="width: 60px;padding-left: 5px;" onkeyup="value=value.replace(/[^\d]/g,'')" name="bonusShareTotal" id="bonusShareTotal" ms-duplex-number="datas.temp.bonusShareTotal"><span>元</span>
						</div>
					</li>
				</ul>
				</c:if>
				<ul class="ui-list m-t-10">
						<li style=" padding-bottom: 0px;">
							<div class="ui-list-thumb" style="height: 40px;margin: 0px 0px 0px 0;">
								<i id="ifRewardSecond" class="iconfont m-l-3 icon-gift-money-active"></i>
							</div>
							<div class="ui-list-info" style="padding:0px;">
								<span>追加次高价红包</span><span class="font-prompt">(已有{{datas.domainInfo.bonusSecond}}元)</span>
							</div>
						</li>
					</ul>
					<ul class="ui-list ui-list-text addRedPackUl">
						<li style=" padding-bottom: 0px;padding-top: 0px; height: 40px;">
							<div class="ui-list-info">
								<span>追加金额</span>
							</div>
							<div class="ui-form-item font-styles">
								<input type="number" style="width: 60px;padding-left: 5px;" onkeyup="value=value.replace(/[^\d]/g,'')"  name="bonusSecond" id="bonusSecond" value="0"><span>元</span>
							</div>
						</li>
					</ul>
					<div class="ui-btn-wrap">
						<button id="comfirmButton" class="ui-btn-lg ui-btn-danger" disabled="disabled" ms-click="comfirmAddRedPack"><span class="font-styles">塞钱进红包</span> </button>
					</div>
			</section>
			<script type="text/javascript" data-page="addRedPack"></script>
		</div>
		
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>