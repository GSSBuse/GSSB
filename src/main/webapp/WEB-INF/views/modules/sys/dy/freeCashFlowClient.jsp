<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金流管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			/*设置表格每一行的背景颜色*/
			for(var i=1 ; i < $("#contentTable tr").length ; i++){
				if(i == 1){
					$("#contentTable tr").eq(i).css("background-color", "#f3f3f3");
				}else{
					if($("#contentTable tr").eq(i-1).text().indexOf("解冻") > 0){
						$("#contentTable tr").eq(i).css("background-color", $("#contentTable tr").eq(i-1).css("background-color"));
					}else if($("#contentTable tr").eq(i-1).css("background-color") == "rgb(243, 243, 243)"){
						$("#contentTable tr").eq(i).css("background-color", "");
					}else{
						$("#contentTable tr").eq(i).css("background-color", "#f3f3f3");
					}
				}
					
			}
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
		<li class="active"><a href="#" onclick="ahrefClick(this)">冻结解冻记录列表</a></li>
		<li class=""><a href="${ctx}/sys/dy/dyClient/">会员信息列表</a></li>
		<!-- <li class=""><a href="#" onclick="history.go(-2)">返回</a></li> -->
	</ul>
	<form:form id="searchForm" modelAttribute="cashFlowInfo" action="${ctx}/sys/dy/dyCashFlow/freeCashFlowClient" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="clientId" name="clientId" value="${dyClient.id}" type="hidden"/>
	</form:form>
	<sys:message content="${message}"/>
	<div align="right">
		<p>当前账户总额：${dyFinance.accountBalance}</p>
		<p>当前冻结总额：${dyFinance.freezeBalance} </p>
	</div>
	<table id="contentTable" class="table  table-bordered table-condensed">
		<caption>${dyClient.nickname} 冻结解冻记录列表</caption>
		<thead>
			<tr>
				<th>操作</th>
				<th>操作金额</th>
				<th>域名</th>
				<th>操作时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyCashFlow">
			<c:if test="${(dyCashFlow.operate eq '提现' and dyCashFlow.confirmResult eq '完成') 
							or (dyCashFlow.operate eq '提现' and dyCashFlow.confirmResult eq '打回')
							or (dyCashFlow.operate eq '提现' and dyCashFlow.confirmResult eq '已取消')
							or dyCashFlow.operate eq '解冻'}">
				<tr>
					<td>
						<c:if test="${dyCashFlow.operate eq '提现'}">
							提现解冻
						</c:if>
						<c:if test="${dyCashFlow.operate eq '解冻'}">
							解冻
						</c:if>
					</td>
					<td>
						${dyCashFlow.operateAmount}
					</td>
					<td>
						<c:if test="${not empty dyCashFlow.domainnameId}">
							${dyCashFlow.dyDomainname.name}
						</c:if>
					</td>
					<td>
						<fmt:formatDate value="${dyCashFlow.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
			</c:if>
			<tr>
				<td>
					<c:if test="${dyCashFlow.operate eq '提现'}">
						提现冻结
					</c:if>
					<c:if test="${dyCashFlow.operate ne '提现'}">
						冻结
					</c:if>
				</td>
				<td>
					${dyCashFlow.operateAmount}
				</td>
				<td>
					<c:if test="${not empty dyCashFlow.domainnameId}">
						${dyCashFlow.dyDomainname.name}
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${dyCashFlow.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>