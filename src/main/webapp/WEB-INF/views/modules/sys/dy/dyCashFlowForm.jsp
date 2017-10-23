<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金流管理</title>
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
		<li><a href="${ctx}/sys/dy/dyCashFlow?type=0">资金流列表</a></li>
		<li class="active"><a href="${ctx}/sys/dy/dyCashFlow/form?id=${dyCashFlow.id}&type=<%=request.getParameter("type")%>">资金流<shiro:hasPermission name="sys:dy:dyCashFlow:edit">${not empty dyCashFlow.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:dy:dyCashFlow:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dyCashFlow" action="${ctx}/sys/dy/dyCashFlow/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">米友号：</label>
			<div class="controls">
				<form:input path="dyClient.dyid" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${empty dyCashFlow.operate}">
			<div class="control-group">
				<label class="control-label">操作：</label>
				<div class="controls">
					<form:radiobuttons path="operate" items="${fns:getDictList('dy_cash_flow_operate')}" itemLabel="label" itemValue="value" class="required" htmlEscape="false"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<c:if test="${not empty dyCashFlow.operate}">
			<div class="control-group">
				<label class="control-label">操作：</label>
				<div class="controls">
					<form:input path="operate" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">操作金额：</label>
			<div class="controls">
				<form:input path="operateAmount" htmlEscape="false" maxlength="20" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">操作时间：</label>
			<div class="controls">
				<input name="operateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${dyCashFlow.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${not empty dyCashFlow.id}">
			<div class="control-group">
				<label class="control-label">经纪人确认结果：</label>
				<div class="controls">
					<form:radiobuttons path="confirmResult" items="${fns:getDictList('dy_cash_flow_confirm_result')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:dy:dyCashFlow:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>