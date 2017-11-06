<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>买标信息管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/gb/gbBuy/">买标信息管理列表</a></li>
		<shiro:hasPermission name="sys:gb:gbBuy:edit"><li><a href="${ctx}/sys/gb/gbBuy/form">买标信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbBuy" action="${ctx}/sys/gb/gbBuy/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>国标标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>国标序号</th>
				<th>国标类型</th>
				<th>国标标题</th>
				<th>国标描述</th>
				<th>联系人</th>
				<th>手机号</th>
				<th>预算出价</th>
				<th>实际出价</th>
				<th>国标状态</th>
				<th>创建时间</th>
				<th>成交时间</th>
				<shiro:hasPermission name="sys:gb:gbBuy:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:set var="index" value="0" /> 
		<c:forEach items="${page.list}" var="gbBuy">
		<c:set var="index" value="${index+1}" /> 
			<tr>
				<td>
					${index}
				</td>
				<td>
					<c:if test="${gbBuy.typeId eq '1'}">
						商标
					</c:if>
					<c:if test="${gbBuy.typeId eq '2'}">
						版权
					</c:if>
					<c:if test="${gbBuy.typeId eq '3'}">
						专利
					</c:if>
				</td>
				<td><a href="${ctx}/sys/gb/gbBuy/form?id=${gbBuy.id}">
					${gbBuy.title}
				</a></td>
				<td>
					${gbBuy.description}
				</td>
				<td>
					${gbBuy.connacts}
				</td>
				<td>
					${gbBuy.mobile}
				</td>
				<td>
					${gbBuy.price}
				</td>
				<td>
					${gbBuy.realprice}
				</td>
				<td>
					<c:if test="${gbBuy.status eq '1'}">
						正在交易
					</c:if>
					<c:if test="${gbBuy.status eq '0'}">
						交易结束
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${gbBuy.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>
					<fmt:formatDate value="${gbBuy.dealTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<shiro:hasPermission name="sys:gb:gbBuy:edit"><td>
    				<a href="${ctx}/sys/gb/gbBuy/form?id=${gbBuy.id}">修改</a>
					<a href="${ctx}/sys/gb/gbBuy/delete?id=${gbBuy.id}" onclick="return confirmx('确认要删除该买标信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>