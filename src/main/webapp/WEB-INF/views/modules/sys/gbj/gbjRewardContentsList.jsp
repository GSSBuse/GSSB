<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>悬赏信息评论管理</title>
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
		<li class="active"><a href="${ctx}/sys/gbj/gbjRewardContents/">悬赏信息评论列表</a></li>
		<shiro:hasPermission name="sys:gbj:gbjRewardContents:edit"><li><a href="${ctx}/sys/gbj/gbjRewardContents/form">悬赏信息评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjRewardContents" action="${ctx}/sys/gbj/gbjRewardContents/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户ID：</label>
				<form:input path="userId" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>悬赏信息ID：</label>
				<form:input path="rewardId" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>点赞个数：</label>
				<form:input path="upCounts" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>评论人id：</label>
				<form:input path="toUserId" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>悬赏信息ID</th>
				<th>评论内容</th>
				<th>点赞个数</th>
				<th>评论人id</th>
				<th>备注信息</th>
				<th>删除标记</th>
				<shiro:hasPermission name="sys:gbj:gbjRewardContents:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjRewardContents">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjRewardContents/form?id=${gbjRewardContents.id}">
					${gbjRewardContents.userId}
				</a></td>
				<td>
					${gbjRewardContents.rewardId}
				</td>
				<td>
					${gbjRewardContents.contents}
				</td>
				<td>
					${gbjRewardContents.upCounts}
				</td>
				<td>
					${gbjRewardContents.toUserId}
				</td>
				<td>
					${gbjRewardContents.remarks}
				</td>
				<td>
					${fns:getDictLabel(gbjRewardContents.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="sys:gbj:gbjRewardContents:edit"><td>
    				<a href="${ctx}/sys/gbj/gbjRewardContents/form?id=${gbjRewardContents.id}">修改</a>
					<a href="${ctx}/sys/gbj/gbjRewardContents/delete?id=${gbjRewardContents.id}" onclick="return confirmx('确认要删除该悬赏信息评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>