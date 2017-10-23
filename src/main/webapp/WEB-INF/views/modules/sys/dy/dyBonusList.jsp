<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>红包佣金管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyBonus/">红包佣金列表</a></li>
		<shiro:hasPermission name="sys:dy:dyBonus:edit"><li><a href="${ctx}/sys/dy/dyBonus/form">红包佣金添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyBonus" action="${ctx}/sys/dy/dyBonus/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>域名id：</label>
				<form:input path="domainnameId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>会员id：</label>
				<form:input path="clientId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>金额：</label>
				<form:input path="money" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>收支类型：</label>
				<form:input path="type" htmlEscape="false" maxlength="2" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>域名id</th>
				<th>会员id</th>
				<th>金额</th>
				<th>收支类型</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyBonus:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyBonus">
			<tr>
				<td><a href="${ctx}/sys/dy/dyBonus/form?id=${dyBonus.id}">
					${dyBonus.domainnameId}
				</a></td>
				<td>
					${dyBonus.clientId}
				</td>
				<td>
					${dyBonus.money}
				</td>
				<td>
					${dyBonus.type}
				</td>
				<td>
					<fmt:formatDate value="${dyBonus.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyBonus.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyBonus:edit"><td>
    				<a href="${ctx}/sys/dy/dyBonus/form?id=${dyBonus.id}">修改</a>
					<a href="${ctx}/sys/dy/dyBonus/delete?id=${dyBonus.id}" onclick="return confirmx('确认要删除该红包佣金吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>