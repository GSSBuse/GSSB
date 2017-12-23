<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>悬赏信息评论管理</title>
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
			
			var c = window.location.href.split("?")[1].substring(10);

			$("#rewardid").val(c);
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	<shiro:hasPermission name="sys:gbj:gbjUserRewardComments:view">
		<%-- <li><a href="${ctx}/sys/gbj/gbjUserRewardComments/">悬赏信息评论列表</a></li> --%>
		</shiro:hasPermission>
		<li class="active"><a href="${ctx}/sys/gbj/gbjUserRewardComments/form?id=${gbjUserRewardComments.id}">悬赏信息评论<shiro:hasPermission name="sys:gbj:gbjUserRewardComments:edit">${not empty gbjUserRewardComments.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:gbj:gbjUserRewardComments:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gbjUserRewardComments" action="${ctx}/sys/gbj/gbjUserRewardComments/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" >
			<label class="control-label">悬赏信息ID：</label>
			<div class="controls">
				<form:input path="reward.id" id="rewardid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">父ID：</label>
			<div class="controls">
				<form:input path="parentId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">子ID：</label>
			<div class="controls">
				<form:input path="childId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">评论内容：</label>
			<div class="controls">
				
				<form:textarea id="comment" path="comment" htmlEscape="false" rows="4" maxlength="200" class="input-xlarge required"/>
				<sys:ckeditor replace="comment" uploadPath="/sys/gbj/gbjUserRewardComments"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">评论时间：</label>
			<div class="controls">
				<input name="commentTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${gbjUserRewardComments.commentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:gbj:gbjUserRewardComments:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>