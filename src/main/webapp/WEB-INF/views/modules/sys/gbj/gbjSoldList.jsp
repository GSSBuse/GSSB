<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>卖标信息管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/gbj/gbjSold/">卖标信息管理列表</a></li>
		<shiro:hasPermission name="sys:gbj:gbjSold:edit"><li><a href="${ctx}/sys/gbj/gbjSold/form">卖标信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjSold" action="${ctx}/sys/gbj/gbjSold/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户ID：</label>
				<form:input path="user.id" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>真实姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>国标类型：</label>
				<form:select path="typeId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('gbjBuy_type_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>国标标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
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
				<th>真实姓名</th>
				<th>国标类型</th>
				<th>国标标题</th>
				<th>国标描述</th>
				<th>预算价格</th>
				<th>联系人手机号</th>
				<th>标签</th>
				<th>点赞数</th>
				<th>查看数</th>
				<th>评论数</th>
				<th>创建时间</th>
				<th>备注信息</th>
				<th>删除标记</th>
				<shiro:hasPermission name="sys:gbj:gbjSold:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjSold">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjSold/form?id=${gbjSold.id}">
					${gbjSold.user.username}
				</a></td>
				<td>
					${gbjSold.realname}
				</td>
				<td>
					${fns:getDictLabel(gbjSold.typeId, 'gbjBuy_type_id', '')}
				</td>
				<td>
					${gbjSold.title}
				</td>
				<td>
					${gbjSold.description}
				</td>
				<td>
					${gbjSold.price}
				</td>
				<td>
					${gbjSold.mobile}
				</td>
				<td>
					${gbjSold.tag}
				</td>
				<td>
					${gbjSold.upCounts}
				</td>
				<td>
					${gbjSold.lookCounts}
				</td>
				<td>
					${gbjSold.commentsCounts}
				</td>
				<td>
					<fmt:formatDate value="${gbjSold.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gbjSold.remarks}
				</td>
				<td>
					${fns:getDictLabel(gbjSold.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="sys:gbj:gbjSold:edit"><td>
    				<a href="${ctx}/sys/gbj/gbjSold/form?id=${gbjSold.id}">修改</a>
					<a href="${ctx}/sys/gbj/gbjSold/delete?id=${gbjSold.id}" onclick="return confirmx('确认要删除该卖标信息管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>