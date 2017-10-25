<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>域名交易信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			

			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dy/dyDomainname/deal">域名交易信息列表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bidCashInfo" action="${ctx}/sys/dy/dyDomainname/dealSave?oldStatus=${bidCashInfo.status}" method="post" class="form-horizontal">
		<form:hidden path="domainId" id="id"/>
		<form:hidden path="sellClientId" />
		<form:hidden path="buyClientId" />
		<form:hidden path="sellOpenId" />
		<form:hidden path="buyOpenId" />
		<form:hidden path="sellBrokerId" />
		<form:hidden path="buyBrokerId" />
		<form:hidden path="secondClientId" />
		<form:hidden path="shareClientId" />
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">域名名称：</label>
			<div class="controls">
				<form:input path="domainName" readonly="true" htmlEscape="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">卖家姓名：</label>
			<div class="controls">
				<form:input path="sellName" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">卖家昵称：</label>
			<div class="controls">
				<form:input path="sellNickname" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">卖家经纪人：</label>
			<div class="controls">
				<form:input path="sellBrokerName" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">买家姓名：</label>
			<div class="controls">
				<form:input path="buyName" readonly="true" htmlEscape="false" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">买家昵称：</label>
			<div class="controls">
				<form:input path="buyNickname" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">买家经纪人：</label>
			<div class="controls">
				<form:input path="buyBrokerName" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保留价：</label>
			<div class="controls">
				<form:input path="reservePrice" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成交金额：</label>
			<div class="controls">
				<form:input path="bidAmount" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">平台佣金：</label>
			<div class="controls">
				<form:input path="rebate" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">次高价红包：</label>
			<div class="controls">
				<form:input path="bonusSecond" readonly="true" htmlEscape="false"/>
				<span class="help-inline">取【用户设定的次高价、系统设定的次高价上限】最小值</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">次高价会员姓名：</label>
			<div class="controls">
				<form:input path="secondClientName" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">次高价会员昵称：</label>
			<div class="controls">
				<form:input path="secondClientNickname" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分享佣金：</label>
			<div class="controls">
				<form:input path="shareRebate" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分享者会员姓名：</label>
			<div class="controls">
				<form:input path="shareClientName" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分享者会员昵称：</label>
			<div class="controls">
				<form:input path="shareClientNickname" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group" id="beginTime">
			<label class="control-label">拍卖结束时间：</label>
			<div class="controls">
				<form:input path="endTime" readonly="true" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">交易有效时间：</label>
			<div class="controls">
				<input name="waitTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${bidCashInfo.waitTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<c:if test="${bidCashInfo.status eq '11'}">
					<select name="status" style="width: 210px;">
						<option value="11" selected="selected">1.待买家付款</option>
						<option value="12" >2.买家已付款，待卖家转移域名</option>
						<option value="21" >5.买家违约</option>
						<option value="22" >6.卖家违约</option>
					</select>
				</c:if>
				<c:if test="${bidCashInfo.status eq '12'}">
					<select name="status" style="width: 210px;">
						<option value="12" selected="selected">2.买家已付款，待卖家转移域名</option>
						<option value="13" >3.卖家已转移域名，待买家确认</option>
						<option value="21" >5.买家违约</option>
						<option value="22" >6.卖家违约</option>
					</select>
				</c:if>
				<c:if test="${bidCashInfo.status eq '13'}">
					<select name="status" style="width: 210px;">
						<option value="13" selected="selected">3.卖家已转移域名，待买家确认</option>
						<option value="14" >4.买家已确认，等待交易完成</option>
						<option value="21" >5.买家违约</option>
						<option value="22" >6.卖家违约</option>
					</select>
				</c:if>
				<c:if test="${bidCashInfo.status eq '14'}">
					<select name="status" style="width: 210px;">
						<option value="14" selected="selected">4.买家已确认，等待交易完成</option>
						<option value="15" >f.交易结束</option>
					</select>
				</c:if>
				<c:if test="${bidCashInfo.status eq '15'}">
					f.交易完成
				</c:if>
				<c:if test="${bidCashInfo.status eq '21'}">
					5.买家违约
				</c:if>
				<c:if test="${bidCashInfo.status eq '22'}">
					6.卖家违约
				</c:if>
				<c:if test="${bidCashInfo.status eq '23'}">
					e.流拍或终止
				</c:if>
			</div>
		</div>
		<div class="form-actions">
			<c:if test="${bidCashInfo.status ne '15' and bidCashInfo.status ne '21' and bidCashInfo.status ne '22' and bidCashInfo.status ne '23' and showSave eq '1'}">
				<shiro:hasPermission name="sys:dy:dyDomainname:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>