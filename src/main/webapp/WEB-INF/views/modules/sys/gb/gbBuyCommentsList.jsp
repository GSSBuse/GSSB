<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>管理买标评论管理</title>
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
		<li class="active"><a href="${ctx}/sys/gb/gbBuyComments/">管理买标评论列表</a></li>
		<shiro:hasPermission name="sys:gb:gbBuyComments:edit"><li><a href="${ctx}/sys/gb/gbBuyComments/form">管理买标评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbBuyComments" action="${ctx}/sys/gb/gbBuyComments/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>买标ID：</label>
				<form:input path="buyId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			
			<tr>
				<th>评论序号</th>
				<th>国标名称</th>
				<th>国标评论</th>
				<shiro:hasPermission name="sys:gb:gbBuyComments:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:set var="index" value="0" /> 
		<c:forEach items="${page.list}" var="gbBuyComments">
		<c:set var="index" value="${index+1}" /> 
			<tr>
				<td>
					${index}
				</td>
				<td>
					${gbBuyComments.buyId}
				</td>
				<td>
					${gbBuyComments.comments}
				</td>
				<shiro:hasPermission name="sys:gb:gbBuyComments:edit"><td>
    				<a href="${ctx}/sys/gb/gbBuyComments/form?id=${gbBuyComments.id}">修改</a>
					<a href="${ctx}/sys/gb/gbBuyComments/delete?id=${gbBuyComments.id}" onclick="return confirmx('确认要删除该管理买标评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>