<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>历史交易量管理</title>
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
		<li class="active"><a href="${ctx}/sys/gbj/gbjBusinessNumber/">历史交易量列表</a></li>
		<shiro:hasPermission name="sys:gbj:gbjBusinessNumber:edit"><li><a href="${ctx}/sys/gbj/gbjBusinessNumber/form">历史交易量添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjBusinessNumber" action="${ctx}/sys/gbj/gbjBusinessNumber/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商标交易量</th>
				
				<th>版权交易量</th>
				<th>专利交易量</th>
				<th>总交易数量</th>
				<shiro:hasPermission name="sys:gbj:gbjBusinessNumber:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjBusinessNumber">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjBusinessNumber/form?id=${gbjBusinessNumber.id}">
					${gbjBusinessNumber.shangbiaoNumber}
				</a></td>
				
				<td>
					${gbjBusinessNumber.banquanNumber}
				</td>
				<td>
					${gbjBusinessNumber.zhuanliNumber}
				</td>
				<td>
					${gbjBusinessNumber.businessNumber}
				</td>
				<shiro:hasPermission name="sys:gbj:gbjBusinessNumber:edit"><td>
    				<a href="${ctx}/sys/gbj/gbjBusinessNumber/form?id=${gbjBusinessNumber.id}">修改</a>
					<a href="${ctx}/sys/gbj/gbjBusinessNumber/delete?id=${gbjBusinessNumber.id}" onclick="return confirmx('确认要删除该历史交易量吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>