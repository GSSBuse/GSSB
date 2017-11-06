<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卖标信息管理管理</title>
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
		<li><a href="${ctx}/sys/gb/gbSold/">卖标信息管理列表</a></li>
		<li class="active"><a href="${ctx}/sys/gb/gbSold/form?id=${gbSold.id}">卖标信息管理<shiro:hasPermission name="sys:gb:gbSold:edit">${not empty gbSold.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:gb:gbSold:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gbSold" action="${ctx}/sys/gb/gbSold/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户ID：</label>
			<div class="controls">
				<form:input path="gbclientId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">国标类型：</label>&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="typeId">
				 <option value="1" <c:if test="${'1' eq gbBuy.typeId}">selected="selected"</c:if>>商标</option>
				 <option value="2" <c:if test="${'2' eq gbBuy.typeId}">selected="selected"</c:if>>版权</option>
				 <option value="2" <c:if test="${'3' eq gbBuy.typeId}">selected="selected"</c:if>>专利</option>
			</select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label class="control-label">标题:</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">国标描述：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="200" class="input-xlarge "/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="connacts" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预算价格：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" maxlength="20" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">国标实际成交价格：</label>
			<div class="controls">
				<form:input path="realprice" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">国标状态：</label>&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="status">
				 	<option value="0" <c:if test="${'0' eq gbSold.status}">selected="selected"</c:if>>交易结束</option>
				 	<option value="1" <c:if test="${'1' eq gbSold.status}">selected="selected"</c:if>>正在交易</option>
			</select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${gbSold.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成交时间：</label>
			<div class="controls">
				<input name="dealTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${gbSold.dealTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:gb:gbSold:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>