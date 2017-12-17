<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息增删改查管理</title>
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
		<li class="active"><a href="${ctx}/sys/gbj/gbjUser/">用户信息增删改查列表</a></li>
		<shiro:hasPermission name="sys:gbj:gbjUser:edit"><li><a href="${ctx}/sys/gbj/gbjUser/form">用户信息增删改查添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjUser" action="${ctx}/sys/gbj/gbjUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="username" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>手机：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>手机</th>
				<th>邮箱</th>
				<th>微信</th>
				<th>QQ</th>
				<th>支付宝</th>
				<th>删除标记</th>
				<shiro:hasPermission name="sys:gbj:gbjUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjUser">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjUser/form?id=${gbjUser.id}">
					${gbjUser.username}
				</a></td>
				<td>
					${gbjUser.name}
				</td>
				<td>
					${gbjUser.mobile}
				</td>
				<td>
					${gbjUser.email}
				</td>
				<td>
					${gbjUser.wechat}
				</td>
				<td>
					${gbjUser.qq}
				</td>
				<td>
					${gbjUser.payway}
				</td>
				<td>
					${fns:getDictLabel(gbjUser.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="sys:gbj:gbjUser:edit"><td>
    				<a href="${ctx}/sys/gbj/gbjUser/form?id=${gbjUser.id}">修改</a>
					<a href="${ctx}/sys/gbj/gbjUser/delete?id=${gbjUser.id}" onclick="return confirmx('确认要删除该用户信息增删改查吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>