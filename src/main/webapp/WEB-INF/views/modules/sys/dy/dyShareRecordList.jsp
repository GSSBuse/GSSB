<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分享记录管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyShareRecord/">分享记录管理列表</a></li>
		<shiro:hasPermission name="sys:dy:dyShareRecord:edit"><li><a href="${ctx}/sys/dy/dyShareRecord/form">分享记录管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyShareRecord" action="${ctx}/sys/dy/dyShareRecord/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>域名id：</label>
				<form:input path="domainnameId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>会员id：</label>
				<form:input path="clientId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>分享时间：</label>
				<input name="shareTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dyShareRecord.shareTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>分享时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyShareRecord:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyShareRecord">
			<tr>
				<td><a href="${ctx}/sys/dy/dyShareRecord/form?id=${dyShareRecord.id}">
					${dyShareRecord.domainnameId}
				</a></td>
				<td>
					${dyShareRecord.clientId}
				</td>
				<td>
					<fmt:formatDate value="${dyShareRecord.shareTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyShareRecord.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyShareRecord:edit"><td>
    				<a href="${ctx}/sys/dy/dyShareRecord/form?id=${dyShareRecord.id}">修改</a>
					<a href="${ctx}/sys/dy/dyShareRecord/delete?id=${dyShareRecord.id}" onclick="return confirmx('确认要删除该分享记录管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>