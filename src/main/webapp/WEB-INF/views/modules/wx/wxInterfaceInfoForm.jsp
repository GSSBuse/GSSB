<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>接口验证信息管理</title>
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
		<li><a href="${ctx}/wx/wxInterfaceInfo/">接口验证信息列表</a></li>
		<li class="active"><a href="${ctx}/wx/wxInterfaceInfo/form?id=${wxInterfaceInfo.id}">接口验证信息<shiro:hasPermission name="wx:wxInterfaceInfo:edit">${not empty wxInterfaceInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="wx:wxInterfaceInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wxInterfaceInfo" action="${ctx}/wx/wxInterfaceInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%-- 	
		<div class="control-group">
			<label class="control-label">企业编号：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${wxInterfaceInfo.office.id}" labelName="office.name" labelValue="${wxInterfaceInfo.office.name}"
					title="部门" url="/sys/office/treeData?type=1" cssClass="required" allowClear="true" notAllowSelectParent="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		 --%>
		<div class="control-group">
			<label class="control-label">所属公司：</label>
			<div class="controls">
				<label class="lbl">${wxInterfaceInfo.office.name}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Corporation Id：</label>
			<div class="controls">
				<form:input path="corpid" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Provider Secret：</label>
			<div class="controls">
				<form:input path="providerSecret" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="wx:wxInterfaceInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>