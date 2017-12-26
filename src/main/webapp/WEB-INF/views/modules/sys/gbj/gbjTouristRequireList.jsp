<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>游客查询管理管理</title>
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
		<li class="active"><a href="${ctx}/sys/gbj/gbjTouristRequire/">游客查询管理列表</a></li>
		<shiro:hasPermission name="sys:gbj:gbjTouristRequire:edit"><li><a href="${ctx}/sys/gbj/gbjTouristRequire/form">游客查询管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjTouristRequire" action="${ctx}/sys/gbj/gbjTouristRequire/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>查询内容：</label>
				<form:input path="searchContents" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>商标类型：</label>
				<form:select path="typeId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('gbjBuy_type_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>电话：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>查询内容</th>
				<th>商标类型</th>
				<th>姓名</th>
				<th>电话</th>
				<th>邮箱</th>
				<th>QQ</th>
				<th>是否回访</th>
				<th>更新时间</th>
				<th>回访方式</th>
				<th>回访备注</th>
				<th>创建时间</th>
				<th>删除标记</th>
				<shiro:hasPermission name="sys:gbj:gbjTouristRequire:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjTouristRequire">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjTouristRequire/form?id=${gbjTouristRequire.id}">
					${gbjTouristRequire.searchContents}
				</a></td>
				<td>
					${fns:getDictLabel(gbjTouristRequire.typeId, 'gbjBuy_type_id', '')}
				</td>
				<td>
					${gbjTouristRequire.name}
				</td>
				<td>
					${gbjTouristRequire.mobile}
				</td>
				<td>
					${gbjTouristRequire.email}
				</td>
				<td>
					${gbjTouristRequire.qq}
				</td>
				<td>
					${fns:getDictLabel(gbjTouristRequire.isVisit, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${gbjTouristRequire.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(gbjTouristRequire.visitWay, 'gbj_visit_way', '')}
				</td>
				<td>
					${gbjTouristRequire.visitRemarks}
				</td>
				<td>
					<fmt:formatDate value="${gbjTouristRequire.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(gbjTouristRequire.delFlag, 'del_flag', '')}
				</td>
				<shiro:hasPermission name="sys:gbj:gbjTouristRequire:edit"><td>
    				<a href="${ctx}/sys/gbj/gbjTouristRequire/form?id=${gbjTouristRequire.id}">修改</a>
					<a href="${ctx}/sys/gbj/gbjTouristRequire/delete?id=${gbjTouristRequire.id}" onclick="return confirmx('确认要删除该游客查询管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>