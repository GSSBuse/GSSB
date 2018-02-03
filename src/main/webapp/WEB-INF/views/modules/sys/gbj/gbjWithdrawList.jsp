<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>提现记录表管理</title>
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
		<li class="active"><a href="${ctx}/sys/gbj/gbjWithdraw/">提现记录表列表</a></li>
		<%-- <shiro:hasPermission name="sys:gbj:gbjWithdraw:edit"><li><a href="${ctx}/sys/gbj/gbjWithdraw/form">提现记录表添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjWithdraw" action="${ctx}/sys/gbj/gbjWithdraw/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名：</label>
				<form:input path="username" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>支付宝账号：</label>
				<form:input path="payway" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>微信：</label>
				<form:input path="wechat" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>提现时间：</label>
				<input name="payTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gbjWithdraw.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>提现是否成功：</label>
				<form:radiobuttons path="isSuccess" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名</th>
				<th>提现金额</th>
				<th>支付宝账号</th>
				<th>微信</th>
				<th>提现时间</th>
				<th>提现通知电话</th>
				<th>提现是否成功</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="sys:gbj:gbjWithdraw:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjWithdraw">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjWithdraw/form?id=${gbjWithdraw.id}">
					${gbjWithdraw.username}
				</a></td>
				<td>
					${gbjWithdraw.money}
				</td>
				<td>
					${gbjWithdraw.payway}
				</td>
				<td>
					${gbjWithdraw.wechat}
				</td>
				<td>
					<fmt:formatDate value="${gbjWithdraw.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gbjWithdraw.mobile}
				</td>
				<td>
					${fns:getDictLabel(gbjWithdraw.isSuccess, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${gbjWithdraw.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gbjWithdraw.remarks}
				</td>
				<shiro:hasPermission name="sys:gbj:gbjWithdraw:edit"><td>
    				<a href="${ctx}/sys/gbj/gbjWithdraw/form?id=${gbjWithdraw.id}">修改</a>
					<a href="${ctx}/sys/gbj/gbjWithdraw/delete?id=${gbjWithdraw.id}" onclick="return confirmx('确认要删除该提现记录表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>