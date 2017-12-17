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
			<li><label>真实姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>国标类型：</label>
				<form:select path="typeId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('gbjBuy_type_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>打赏金额：</label>
				<form:input path="price" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户名ID</th>
				<th>真实姓名</th>
				<th>国标类型</th>
				<th>标题</th>
				<th>起名需求</th>
				<th>打赏金额</th>
				<th>手机号码</th>
				<th>标签</th>
				<th>点赞数</th>
				<th>查看数</th>
				<th>评论数</th>
				<th>备注信息</th>
				<th>删除标记</th>
				<shiro:hasPermission name="sys:gbj:gbjReward:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjReward">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjReward/form?id=${gbjReward.id}">
					${gbjReward.user.username}
				</a></td>
				<td>
					${gbjReward.realname}
				</td>
				<td>
					${fns:getDictLabel(gbjReward.typeId, 'gbjBuy_type_id', '')}
				</td>
				<td>
					${gbjReward.title}
				</td>
				<td>
					${gbjReward.description}
				</td>
				<td>
					${gbjReward.price}
				</td>
				<td>
					${gbjReward.mobile}
				</td>
				<td>
					${gbjReward.tag}
				</td>
				<td>
					${gbjReward.upCounts}
				</td>
				<td>
					${gbjReward.lookCounts}
				</td>
				<td>
					${gbjReward.commentsCounts}
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