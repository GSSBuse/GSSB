<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业微信标签管理</title>
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
		<li class="active"><a href="${ctx}/wx/wxTag/">标签列表</a></li>
		<shiro:hasPermission name="wx:wxTag:edit"><li><a href="${ctx}/wx/wxTag/form">标签添加</a></li></shiro:hasPermission>
	</ul>
<%-- 	<form:form id="searchForm" modelAttribute="wxTag" action="${ctx}/wx/wxTag/" method="post" class="breadcrumb form-search">
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
				<th>标签名称</th>
				<th>英文名称</th>
				<th>所属企业</th>
				<th>数据范围</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="wx:wxTag:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxTag">
			<tr>
				<td><a href="${ctx}/wx/wxTag/form?id=${wxTag.id}">
					${wxTag.name}
				</a></td>
				<td>
					${wxTag.enname}
				</td>
				<td>
					${wxTag.office.name}
				</td>
				<td>
					${fns:getDictLabel(wxTag.dataScope, 'sys_data_scope', '无')}
				</td>
				<td>
					<fmt:formatDate value="${wxTag.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${wxTag.remarks}
				</td>
				<shiro:hasPermission name="wx:wxTag:edit"><td>
					<a href="${ctx}/wx/wxTag/assign?id=${wxTag.id}">添加成员</a>
					<a href="${ctx}/wx/wxTag/form?id=${wxTag.id}">修改</a>
					<a href="${ctx}/wx/wxTag/delete?id=${wxTag.id}" onclick="return confirmx('确认要删除该企业微信标签吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>