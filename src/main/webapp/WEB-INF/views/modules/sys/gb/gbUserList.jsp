<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>管理用户信息管理</title>
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
		<li class="active"><a href="${ctx}/sys/gb/gbUser/">管理用户信息列表</a></li>
		<shiro:hasPermission name="sys:gb:gbUser:edit"><li><a href="${ctx}/sys/gb/gbUser/form">管理用户信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbUser" action="${ctx}/sys/gb/gbUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>	
				<th>用户编号</th>
				<th>用户名</th>
				<th>姓名</th>
				<th>手机号</th>
				<th>邮箱</th>
				<th>微信</th>
				<th>QQ</th>
				<th>支付宝</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="sys:gb:gbUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:set var="index" value="0" /> 
		<c:forEach items="${page.list}" var="gbUser">
		<c:set var="index" value="${index+1}" />
			<tr>
				<td>
					${index}
				</td>
				<td>
					${gbUser.username}
				</td>
				<td><a href="${ctx}/sys/gb/gbUser/form?id=${gbUser.id}">
					${gbUser.name}
				</a></td>
				<td>
					${gbUser.mobile}
				</td>
				<td>
					${gbUser.email}
				</td>
				<td>
					${gbUser.wechat}
				</td>
				<td>
					${gbUser.qq}
				</td>
				<td>
					${gbUser.zfb}
				</td>
				<td>
					<fmt:formatDate value="${gbUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>
					<fmt:formatDate value="${gbUser.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<shiro:hasPermission name="sys:gb:gbUser:edit"><td>
    				<a href="${ctx}/sys/gb/gbUser/form?id=${gbUser.id}">修改</a>
					<a href="${ctx}/sys/gb/gbUser/delete?id=${gbUser.id}" onclick="return confirmx('确认要删除该管理用户信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>