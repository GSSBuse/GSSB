<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>悬赏信息管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/gbj/gbjReward/">悬赏信息管理列表</a></li>
		<shiro:hasPermission name="sys:gbj:gbjReward:edit"><li><a href="${ctx}/sys/gbj/gbjReward/form">悬赏信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjReward" action="${ctx}/sys/gbj/gbjReward/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户ID：</label>
				<form:input path="user.id" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="500" class="input-medium"/>
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
				<th>标题</th>
				<th>起名需求</th>
				<th>打赏金额</th>
				<th>是否支付</th>
				<th>是否完成</th>
				<th>点赞个数</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>删除标记</th>
				<shiro:hasPermission name="sys:gbj:gbjReward:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjReward">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjReward/form?id=${gbjReward.id}">
					${gbjReward.user.id}
				</a></td>
				<td>
					${gbjReward.title}
				</td>
				<td>
					${gbjReward.titleNeed}
				</td>
				<td>
					${gbjReward.rewardMoney}
				</td>
				<td>
					${fns:getDictLabel(gbjReward.isPayReward, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(gbjReward.isFinished, 'yes_no', '')}
				</td>
				<td>
					${gbjReward.upCount}
				</td>
				<td>
					<fmt:formatDate value="${gbjReward.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gbjReward.remarks}
				</td>
				<td>
					${fns:getDictLabel(gbjReward.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="sys:gbj:gbjReward:edit"><td>
    				<a href="${ctx}/sys/gbj/gbjReward/form?id=${gbjReward.id}">修改</a>
					<a href="${ctx}/sys/gbj/gbjReward/delete?id=${gbjReward.id}" onclick="return confirmx('确认要删除该悬赏信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>