<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台总账户管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyPlatformAccount/">平台总账户管理列表</a></li>
		<shiro:hasPermission name="sys:dy:dyPlatformAccount:edit"><li><a href="${ctx}/sys/dy/dyPlatformAccount/form">平台总账户管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyPlatformAccount" action="${ctx}/sys/dy/dyPlatformAccount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>平台（账户/收支）总额</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyPlatformAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyPlatformAccount">
			<tr>
				<td><a href="${ctx}/sys/dy/dyPlatformAccount/form?id=${dyPlatformAccount.id}">
					${dyPlatformAccount.totalFinance}
				</a></td>
				<td>
					<fmt:formatDate value="${dyPlatformAccount.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyPlatformAccount.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyPlatformAccount:edit"><td>
    				<a href="${ctx}/sys/dy/dyPlatformAccount/form?id=${dyPlatformAccount.id}">修改</a>
					<a href="${ctx}/sys/dy/dyPlatformAccount/delete?id=${dyPlatformAccount.id}" onclick="return confirmx('确认要删除该平台总账户管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>