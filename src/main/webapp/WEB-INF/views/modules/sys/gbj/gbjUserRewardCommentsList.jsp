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
		<li class="active"><a href="${ctx}/sys/gbj/gbjUserRewardComments/">悬赏信息评论列表</a></li>
		<shiro:hasPermission name="sys:gbj:gbjUserRewardComments:edit"><li><a href="${ctx}/sys/gbj/gbjUserRewardComments/form">悬赏信息评论添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjUserRewardComments" action="${ctx}/sys/gbj/gbjUserRewardComments/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评论内容：</label>
				<form:input path="comment" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns">
			<shiro:hasPermission name="sys:gbj:gbjReward:view">
    				<a class="btn btn-primary" href="${ctx}/sys/gbj/gbjReward">返回</a>
    			</shiro:hasPermission>
    		</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>悬赏信息</th>
				<th>评论内容</th>
				<th>评论时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>删除标记</th>
				<shiro:hasPermission name="sys:gbj:gbjUserRewardComments:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjUserRewardComments">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjUserRewardComments/form?id=${gbjUserRewardComments.id}">
					${gbjUserRewardComments.reward.title}
				</a></td>
				<td>
					${gbjUserRewardComments.comment}
				</td>
				<td>
					<fmt:formatDate value="${gbjUserRewardComments.commentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${gbjUserRewardComments.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gbjUserRewardComments.remarks}
				</td>
				<td>
					${fns:getDictLabel(gbjUserRewardComments.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="sys:gbj:gbjUserRewardComments:edit"><td>
    				<a href="${ctx}/sys/gbj/gbjUserRewardComments/form?id=${gbjUserRewardComments.id}">修改</a>
					<a href="${ctx}/sys/gbj/gbjUserRewardComments/delete?id=${gbjUserRewardComments.id}" onclick="return confirmx('确认要删除该悬赏信息评论吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>