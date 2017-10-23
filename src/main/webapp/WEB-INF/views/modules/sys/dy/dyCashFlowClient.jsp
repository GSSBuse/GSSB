<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金流管理</title>
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
		function ahrefClick(r){
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#" onclick="ahrefClick(this)">资金流列表</a></li>
		<li class=""><a href="${ctx}/sys/dy/dyClient/">会员信息列表</a></li>
		<!-- <li class=""><a href="#" onclick="history.go(-2)">返回</a></li> -->
	</ul>
	<form:form id="searchForm" modelAttribute="cashFlowInfo" action="${ctx}/sys/dy/dyCashFlow/dyCashFlowClient" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="clientId" name="clientId" value="${dyClient.id}" type="hidden"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<caption>${dyClient.nickname} 资金流列表</caption>
		<thead>
			<tr>
				<th>操作</th>
				<th>操作金额</th>
				<th>操作后余额</th>
				<th>域名</th>
				<th>操作时间</th>
				<th>财务确认结果</th>
				<th width="30%">备注</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyCashFlow">
			<tr>
				<td>
					${dyCashFlow.operate}
				</td>
				<td>
					${dyCashFlow.operateAmount}
				</td>
				<td>
					${dyCashFlow.amountBalance}
				</td>
				<td>
					<c:if test="${not empty dyCashFlow.domainnameId}">
						${dyCashFlow.dyDomainname.name}
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${dyCashFlow.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyCashFlow.confirmResult}
				</td>
				<td>
					${dyCashFlow.remarks}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>