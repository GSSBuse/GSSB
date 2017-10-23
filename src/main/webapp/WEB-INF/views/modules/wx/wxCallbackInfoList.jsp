<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>回调验证管理</title>
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
		<li class="active"><a href="${ctx}/wx/wxCallbackInfo/">回调验证信息列表</a></li>
		<shiro:hasPermission name="wx:wxCallbackInfo:edit"><li><a href="${ctx}/wx/wxCallbackInfo/form">回调验证信息添加</a></li></shiro:hasPermission>
	</ul><%-- 
	<form:form id="searchForm" modelAttribute="wxCallbackInfo" action="${ctx}/wx/wxCallbackInfo/" method="post" class="breadcrumb form-search">
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
				<th>编号</th>
				<th>所属公司</th>
				<th>微信企业ID</th>
				<th>应用ID</th>
				<th>应用名称</th>
				<th>Token</th>
				<th>EncodingAESKey</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="wx:wxCallbackInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wxCallbackInfo">
			<tr>
				<td><a href="${ctx}/wx/wxCallbackInfo/form?id=${wxCallbackInfo.id}">
					${wxCallbackInfo.id}
				</a></td>
				<td>
					${wxCallbackInfo.office.name}
				</td>
				<td>
					${wxCallbackInfo.corpid}
				</td>
				<td>
					${wxCallbackInfo.agentId}
				</td>
				<td>
					${wxCallbackInfo.agentName}
				</td>
				<td>
					${wxCallbackInfo.token}
				</td>
				<td>
					${wxCallbackInfo.encodingAeskey}
				</td>
				<td>
					<fmt:formatDate value="${wxCallbackInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${wxCallbackInfo.remarks}
				</td>
				<shiro:hasPermission name="wx:wxCallbackInfo:edit"><td>
    				<a href="${ctx}/wx/wxCallbackInfo/form?id=${wxCallbackInfo.id}">修改</a>
					<a href="${ctx}/wx/wxCallbackInfo/delete?id=${wxCallbackInfo.id}" onclick="return confirmx('确认要删除该回调验证吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>