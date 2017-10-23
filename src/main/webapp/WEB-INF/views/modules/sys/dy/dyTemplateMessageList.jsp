<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知消息设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dy/dyTemplateMessage/">通知消息设置列表</a></li>
		<shiro:hasPermission name="sys:dy:dyTemplateMessage:edit"><li><a href="${ctx}/sys/dy/dyTemplateMessage/form">通知消息设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyTemplateMessage" action="${ctx}/sys/dy/dyTemplateMessage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>消息ID：</label>
				<form:input path="templateId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>消息标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>消息内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="1024" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>消息ID</th>
				<th>消息标题</th>
				<th>消息内容</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyTemplateMessage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyTemplateMessage">
			<tr>
				<td><a href="${ctx}/sys/dy/dyTemplateMessage/form?id=${dyTemplateMessage.id}">
					${dyTemplateMessage.templateId}
				</a></td>
				<td>
					${dyTemplateMessage.title}
				</td>
				<td>
					${dyTemplateMessage.content}
				</td>
				<td>
					<fmt:formatDate value="${dyTemplateMessage.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyTemplateMessage.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyTemplateMessage:edit"><td>
    				<a href="${ctx}/sys/dy/dyTemplateMessage/form?id=${dyTemplateMessage.id}">修改</a>
					<a href="${ctx}/sys/dy/dyTemplateMessage/delete?id=${dyTemplateMessage.id}" onclick="return confirmx('确认要删除该通知消息设置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>