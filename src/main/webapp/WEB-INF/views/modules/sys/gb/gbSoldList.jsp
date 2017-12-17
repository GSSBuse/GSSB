<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卖标信息管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/gb/gbSold/">卖标信息管理列表</a></li>
		<shiro:hasPermission name="sys:gb:gbSold:edit"><li><a href="${ctx}/sys/gb/gbSold/form">卖标信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbSold" action="${ctx}/sys/gb/gbSold/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>title：</label>
				<form:input path="title" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>title</th>
				<shiro:hasPermission name="sys:gb:gbSold:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbSold">
			<tr>
				<td><a href="${ctx}/sys/gb/gbSold/form?id=${gbSold.id}">
					${gbSold.title}
				</a></td>
				<shiro:hasPermission name="sys:gb:gbSold:edit"><td>
    				<a href="${ctx}/sys/gb/gbSold/form?id=${gbSold.id}">修改</a>
					<a href="${ctx}/sys/gb/gbSold/delete?id=${gbSold.id}" onclick="return confirmx('确认要删除该卖标信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>