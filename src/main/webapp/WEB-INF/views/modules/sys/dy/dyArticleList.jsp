<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资讯文章管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyArticle/">资讯文章列表</a></li>
		<shiro:hasPermission name="sys:dy:dyArticle:edit"><li><a href="${ctx}/sys/dy/dyArticle/form">资讯文章添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyArticle" action="${ctx}/sys/dy/dyArticle/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文章标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>关联域名：</label>
				<form:input path="domainnameName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="30%">文章标题</th>
				<th>关联域名</th>
				<th>发送时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyArticle:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyArticle">
			<tr>
				<td><a href="${ctx}/sys/dy/dyArticle/form?id=${dyArticle.id}">
					${dyArticle.title}
				</a></td>
				<td>
					${dyArticle.domainnameName}
				</td>
				<td>
					<fmt:formatDate value="${dyArticle.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${dyArticle.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyArticle.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyArticle:edit"><td>
    				<a href="${ctx}/sys/dy/dyArticle/form?id=${dyArticle.id}">修改</a>
					<a href="${ctx}/sys/dy/dyArticle/delete?id=${dyArticle.id}" onclick="return confirmx('确认要删除该资讯文章吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>