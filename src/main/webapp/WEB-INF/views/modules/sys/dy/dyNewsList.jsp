<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>最新消息管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyNews/">最新消息列表</a></li>
		<shiro:hasPermission name="sys:dy:dyNews:edit"><li><a href="${ctx}/sys/dy/dyNews/form">最新消息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyNews" action="${ctx}/sys/dy/dyNews/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>被关注域名id：</label>
				<form:input path="domainnameId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>关注者id：</label>
				<form:input path="clientId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>被关注域名id</th>
				<th>关注者id</th>
				<th>最新出价计数</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyNews:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyNews">
			<tr>
				<td><a href="${ctx}/sys/dy/dyNews/form?id=${dyNews.id}">
					${dyNews.domainnameId}
				</a></td>
				<td>
					${dyNews.clientId}
				</td>
				<td>
					${dyNews.newBidCount}
				</td>
				<td>
					<fmt:formatDate value="${dyNews.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyNews.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyNews:edit"><td>
    				<a href="${ctx}/sys/dy/dyNews/form?id=${dyNews.id}">修改</a>
					<a href="${ctx}/sys/dy/dyNews/delete?id=${dyNews.id}" onclick="return confirmx('确认要删除该最新消息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>