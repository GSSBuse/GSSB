<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信支付结果管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/dy/dyWxpayResult/">微信支付结果管理列表</a></li>
		<shiro:hasPermission name="sys:dy:dyWxpayResult:edit"><li><a href="${ctx}/sys/dy/dyWxpayResult/form">微信支付结果管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyWxpayResult" action="${ctx}/sys/dy/dyWxpayResult/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户订单号：</label>
				<form:input path="outTradeNo" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>微信支付订单号：</label>
				<form:input path="transactionId" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>支付结果：</label>
				<form:input path="resultCode" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>处理状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>会员openid</th>
				<th>签名</th>
				<th>商户订单号</th>
				<th>交易总额</th>
				<th>微信支付订单号</th>
				<th>支付完成时间</th>
				<th>支付结果</th>
				<th>处理状态</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:dy:dyWxpayResult:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyWxpayResult">
			<tr>
				<td><a href="${ctx}/sys/dy/dyWxpayResult/form?id=${dyWxpayResult.id}">
					${dyWxpayResult.openid}
				</a></td>
				<td>
					${dyWxpayResult.sign}
				</td>
				<td>
					${dyWxpayResult.outTradeNo}
				</td>
				<td>
					${dyWxpayResult.totalFee}
				</td>
				<td>
					${dyWxpayResult.transactionId}
				</td>
				<td>
					<fmt:formatDate value="${dyWxpayResult.timeEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyWxpayResult.resultCode}
				</td>
				<td>
					${dyWxpayResult.status}
				</td>
				<td>
					${dyWxpayResult.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyWxpayResult:edit"><td>
    				<a href="${ctx}/sys/dy/dyWxpayResult/form?id=${dyWxpayResult.id}">修改</a>
					<a href="${ctx}/sys/dy/dyWxpayResult/delete?id=${dyWxpayResult.id}" onclick="return confirmx('确认要删除该微信支付结果管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>