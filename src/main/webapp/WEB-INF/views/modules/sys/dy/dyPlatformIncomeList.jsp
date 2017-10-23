<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>平台收入管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyPlatformIncome/">平台收入管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="dyPlatformIncome" action="${ctx}/sys/dy/dyPlatformIncome/" method="post" class="breadcrumb form-search">
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
				<th>域名名称</th>
				<th>域名状态</th>
				<th>买家姓名</th>
				<th>买家昵称</th>
				<th>卖家姓名</th>
				<th>卖家昵称</th>
				<th>操作</th>
				<th>操作金额</th>
				<th>总收入额</th>
				<th>更新时间</th>
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyPlatformIncome">
			<tr>
				<td>
					${dyPlatformIncome.dyDomainname.name}
				</td>
				<td>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '01'}">
					       审核中
					 </c:if>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '02'}">
					      审核失败
					 </c:if>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '03'}">
					       审核通过
					 </c:if>
					 <c:if test="${dyPlatformIncome.dyDomainname.status eq '11'}">
						待买家付款
					</c:if>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '12'}">
						待卖家转移域名
					</c:if>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '13'}">
						待买家确认
					</c:if>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '14'}">
						等待确认
					</c:if>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '15'}">
						交易完成
					</c:if>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '21'}">
						买家违约
					</c:if>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '22'}">
						卖家违约
					</c:if>
					<c:if test="${dyPlatformIncome.dyDomainname.status eq '23'}">
						流拍或终止
					</c:if>
				</td>
				<td>
					${dyPlatformIncome.dyDomainname.dyClient.name}
				</td>
				<td>
					${dyPlatformIncome.dyDomainname.dyClient.nickname}
				</td>
				<td>
					${dyPlatformIncome.buyClient.name}
				</td>
				<td>
					${dyPlatformIncome.buyClient.nickname}
				</td>
				<td>
					${dyPlatformIncome.operate}
				</td>
				<td>
					${dyPlatformIncome.operateAmount}
				</td>
				<td>
					${dyPlatformIncome.totalAmount}
				</td>
				<td>
					<fmt:formatDate value="${dyPlatformIncome.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyPlatformIncome.remarks}
				</td>
				<%-- <shiro:hasPermission name="sys:dy:dyPlatformIncome:edit"><td>
    				<a href="${ctx}/sys/dy/dyPlatformIncome/form?id=${dyPlatformIncome.id}">修改</a>
					<a href="${ctx}/sys/dy/dyPlatformIncome/delete?id=${dyPlatformIncome.id}" onclick="return confirmx('确认要删除该平台收入管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>