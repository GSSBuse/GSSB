<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>接口验证信息管理</title>
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
		<li class="active"><a href="${ctx}/wx/wxInterfaceInfo/">接口验证信息列表</a></li>
		<shiro:hasPermission name="wx:wxInterfaceInfo:edit"><li><a href="${ctx}/wx/wxInterfaceInfo/form">接口验证信息添加</a></li></shiro:hasPermission>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="wxInterfaceInfo" action="${ctx}/wx/wxInterfaceInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属公司</th>
				<th>Corporation Id</th>
				<th>Provider Secret</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="wx:wxInterfaceInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxInterfaceInfo">
			<tr>
				<td><a href="${ctx}/wx/wxInterfaceInfo/form?id=${wxInterfaceInfo.id}">
					${wxInterfaceInfo.office.name}
				</a></td>
				<td>
					${wxInterfaceInfo.corpid}
				</td>
				<td>
					${wxInterfaceInfo.providerSecret}
				</td>
				<td>
					<fmt:formatDate value="${wxInterfaceInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${wxInterfaceInfo.remarks}
				</td>
				<shiro:hasPermission name="wx:wxInterfaceInfo:edit"><td>
    				<a href="${ctx}/wx/wxInterfaceInfo/form?id=${wxInterfaceInfo.id}">修改</a>
					<a href="${ctx}/wx/wxInterfaceInfo/delete?id=${wxInterfaceInfo.id}" onclick="return confirmx('确认要删除该接口验证信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>