<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员财务信息管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyFinance/">会员财务信息列表</a></li>
		<shiro:hasPermission name="sys:dy:dyFinance:edit"><li><a href="${ctx}/sys/dy/dyFinance/form">会员财务信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyFinance" action="${ctx}/sys/dy/dyFinance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员id：</label>
				<form:input path="clientId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>账户余额：</label>
				<form:input path="accountBalance" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>冻结金额：</label>
				<form:input path="freezeBalance" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员id</th>
				<th>账户余额</th>
				<th>冻结金额</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyFinance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyFinance">
			<tr>
				<td><a href="${ctx}/sys/dy/dyFinance/form?id=${dyFinance.id}">
					${dyFinance.clientId}
				</a></td>
				<td>
					${dyFinance.accountBalance}
				</td>
				<td>
					${dyFinance.freezeBalance}
				</td>
				<td>
					<fmt:formatDate value="${dyFinance.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyFinance.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyFinance:edit"><td>
    				<a href="${ctx}/sys/dy/dyFinance/form?id=${dyFinance.id}">修改</a>
					<a href="${ctx}/sys/dy/dyFinance/delete?id=${dyFinance.id}" onclick="return confirmx('确认要删除该会员财务信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>