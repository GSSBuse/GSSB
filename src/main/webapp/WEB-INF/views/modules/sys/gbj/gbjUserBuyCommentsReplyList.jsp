<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>买标信息评论回复管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/gbj/gbjUserBuyCommentsReply/">买标信息评论回复管理列表</a></li>
		<shiro:hasPermission name="sys:gbj:gbjUserBuyCommentsReply:edit"><li><a href="${ctx}/sys/gbj/gbjUserBuyCommentsReply/form">买标信息评论回复管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjUserBuyCommentsReply" action="${ctx}/sys/gbj/gbjUserBuyCommentsReply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>买标信息ID</th>
				<th>对应评论信息ID</th>
				<th>评论用户id</th>
				<th>回复信息</th>
				<th>创建时间</th>
				<th>删除标记</th>
				<shiro:hasPermission name="sys:gbj:gbjUserBuyCommentsReply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjUserBuyCommentsReply">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjUserBuyCommentsReply/form?id=${gbjUserBuyCommentsReply.id}">
					${gbjUserBuyCommentsReply.buyId}
				</a></td>
				<td>
					${gbjUserBuyCommentsReply.toId}
				</td>
				<td>
					${gbjUserBuyCommentsReply.userId}
				</td>
				<td>
					${gbjUserBuyCommentsReply.replyComments}
				</td>
				<td>
					<fmt:formatDate value="${gbjUserBuyCommentsReply.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(gbjUserBuyCommentsReply.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="sys:gbj:gbjUserBuyCommentsReply:edit"><td>
    				<a href="${ctx}/sys/gbj/gbjUserBuyCommentsReply/form?id=${gbjUserBuyCommentsReply.id}">修改</a>
					<a href="${ctx}/sys/gbj/gbjUserBuyCommentsReply/delete?id=${gbjUserBuyCommentsReply.id}" onclick="return confirmx('确认要删除该买标信息评论回复管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>