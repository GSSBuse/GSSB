<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金操作申请</title>
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
		function checkValue(r){
			/*检查金额是否大于0*/
			if(Number($('#operateAmountIdd').val()) == 0){
				top.$.jBox.error("申请金额必须大于0");
				return false;
			}
			/*如果是提现，检查余额*/
			var operate = $('input:radio:checked').val();
			if(operate == "提现"){
				var oparateAmount = parseInt($("#operateAmountIdd").val());
				var dLast = parseInt($("#dLastId").val());
				if(oparateAmount > dLast){
					top.$.jBox.error("余额不足");
					return false;
				}
			}
			$("#inputForm").submit();
			return true;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dy/dyCashFlow/dyCashFlowApply?clientId=${dyClient.id}">资金操作申请</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dyCashFlow" action="${ctx}/sys/dy/dyCashFlow/applySave" method="post" class="form-horizontal">		
		<div hidden="true"><input name="clientId" value="${dyClient.id}" /></div>
		<div class="control-group">
			<label class="control-label">米友号：</label>
			<div class="controls">
				<input name="dyid" value="${dyClient.dyid}" readonly="readonly" maxlength="64" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<input name="dName" value="${dyClient.name}" readonly="readonly"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信昵称：</label>
			<div class="controls">
				<input name="dNickname" value="${dyClient.nickname}" readonly="readonly"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机：</label>
			<div class="controls">
				<input name="dMobile" value="${dyClient.mobile}" readonly="readonly" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当前账户余额：</label>
			<div class="controls">
				<input name="dAccountBalance" value="${dyClient.dyFinance.accountBalance}" readonly="readonly"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">冻结金额：</label>
			<div class="controls">
				<input name="dFreezeBalance" value="${dyClient.dyFinance.freezeBalance}" readonly="readonly"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可用余额：</label>
			<div class="controls">
				<input id="dLastId" name="dLast" value="${dyClient.dyFinance.accountBalance - dyClient.dyFinance.freezeBalance}" readonly="readonly"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">你将给用户申请资金操作：</label>
			<div class="controls">
				<span>
					<input id="operate1" name="operate" type="radio" value="线下充值" class="input-xlarge required" />
					<label for="operate1">充值</label>
				</span>
				<span>
					<input id="operate2" name="operate" type="radio" value="提现" class="input-xlarge required" />
					<label for="operate2">提现</label>
				</span>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作金额：</label>
			<div class="controls">
				<form:input id="operateAmountIdd" path="operateAmount"  htmlEscape="false" maxlength="20" class="input-xlarge required digits" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div></div>
		<div >
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<caption style="text-align: left"> 你最近申请的资金操作如下： </caption>
				<thead>
					<tr>
						<td>时间</td>
						<td>会员米友号</td>
						<td>会员姓名</td>
						<td>会员所属经纪人</td>
						<td>操作</td>
						<td>确认结果</td>
						<td>金额</td>
					</tr>
				</thead>
				<c:forEach items="${list}" var="d">
					<tr>
						<td><fmt:formatDate value="${d.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${d.dyid}</td>
						<td>${d.name}</td>
						<td>${d.brokerName}</td>
						<td>${d.operate}</td>
						<td>${d.confirmResult}</td>
						<td>${d.operateAmount}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button"  onclick="checkValue(this)" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>