<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员关注管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyAttention/">会员关注列表</a></li>
		<shiro:hasPermission name="sys:dy:dyAttention:edit"><li><a href="${ctx}/sys/dy/dyAttention/form">会员关注添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyAttention" action="${ctx}/sys/dy/dyAttention/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>会员id：</label>
				<form:input path="clientId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>被关注域名id：</label>
				<form:input path="domainnameId" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>被关注域名id</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyAttention:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyAttention">
			<tr>
				<td><a href="${ctx}/sys/dy/dyAttention/form?id=${dyAttention.id}">
					${dyAttention.clientId}
				</a></td>
				<td>
					${dyAttention.domainnameId}
				</td>
				<td>
					${dyAttention.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${dyAttention.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${dyAttention.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyAttention.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyAttention:edit"><td>
    				<a href="${ctx}/sys/dy/dyAttention/form?id=${dyAttention.id}">修改</a>
					<a href="${ctx}/sys/dy/dyAttention/delete?id=${dyAttention.id}" onclick="return confirmx('确认要删除该会员关注吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>