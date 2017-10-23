<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台总账户管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyPlatformFinance/">平台总账户管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="dyPlatformFinance" action="${ctx}/sys/dy/dyPlatformFinance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员昵称：</label>
				<form:input path="dyClient.nickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>操作：</label>
				<select name="operate">
					<option value="" <c:if test="${empty dyPlatformFinance.operate}">selected="selected"</c:if>>查看全部</option>
					<option value="微信充值" <c:if test="${'微信充值' eq dyPlatformFinance.operate}">selected="selected"</c:if>>微信充值</option>
					<option value="线下充值" <c:if test="${'线下充值' eq dyPlatformFinance.operate}">selected="selected"</c:if>>线下充值</option>
					<option value="提现" <c:if test="${'提现' eq dyPlatformFinance.operate}">selected="selected"</c:if>>提现</option>
				</select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员姓名</th>
				<th>会员昵称</th>
				<th>操作</th>
				<th>操作金额</th>
				<th>平台账户总额</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<%-- <shiro:hasPermission name="sys:dy:dyPlatformFinance:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyPlatformFinance">
			<tr>
				<td>
					${dyPlatformFinance.dyClient.name}
				</td>
				<td>
					${dyPlatformFinance.dyClient.nickname}
				</td>
				<td>
					${dyPlatformFinance.operate}
				</td>
				<td>
					${dyPlatformFinance.operateAmount}
				</td>
				<td>
					${dyPlatformFinance.totalAmount}
				</td>
				<td>
					<fmt:formatDate value="${dyPlatformFinance.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyPlatformFinance.remarks}
				</td>
				<%-- <shiro:hasPermission name="sys:dy:dyPlatformFinance:edit"><td>
    				<a href="${ctx}/sys/dy/dyPlatformFinance/form?id=${dyPlatformFinance.id}">修改</a>
					<a href="${ctx}/sys/dy/dyPlatformFinance/delete?id=${dyPlatformFinance.id}" onclick="return confirmx('确认要删除该平台总账户管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>