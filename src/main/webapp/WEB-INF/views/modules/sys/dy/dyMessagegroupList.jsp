<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>群发消息列表管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyMessagegroup/">群发消息列表列表</a></li>
		<shiro:hasPermission name="sys:dy:dyMessagegroup:edit"><li><a href="${ctx}/sys/dy/dyMessagegroup/form">群发消息列表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyMessagegroup" action="${ctx}/sys/dy/dyMessagegroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>消息标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>定时发送：</label>
				<form:input path="type" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>图文消息标题</th>
				<th>是否定时</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyMessagegroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyMessagegroup">
			<tr>
				<td><a href="${ctx}/sys/dy/dyMessagegroup/form?id=${dyMessagegroup.id}">
					${dyMessagegroup.title}
				</a></td>
				<td>
					<c:if test="${dyMessagegroup.type eq '0'}">
						立即发送
					</c:if>
					<c:if test="${dyMessagegroup.type eq '1'}">
						定时发送
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${dyMessagegroup.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyMessagegroup.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyMessagegroup:edit"><td>
    				<a href="${ctx}/sys/dy/dyMessagegroup/form?id=${dyMessagegroup.id}">修改</a>
					<a href="${ctx}/sys/dy/dyMessagegroup/delete?id=${dyMessagegroup.id}" onclick="return confirmx('确认要删除该群发消息列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>