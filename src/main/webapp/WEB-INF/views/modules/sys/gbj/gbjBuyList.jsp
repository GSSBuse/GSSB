<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>买标信息列表管理</title>
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
		<li class="active"><a href="${ctx}/sys/gbj/gbjBuy/">买标信息管理列表</a></li>
		<shiro:hasPermission name="sys:gbj:gbjBuy:edit"><li><a href="${ctx}/sys/gbj/gbjBuy/form">买标信息列表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gbjBuy" action="${ctx}/sys/gbj/gbjBuy/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<%-- <li><label>用户名：</label>
				<form:input path="user.id" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li> --%>
			<li><label>真实姓名：</label>
				<form:input path="realname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			
			<%-- <li><label>发布状态：</label>
				<form:input path="frontDelFlag" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li> --%>
			
			
			<li><label>国标类型：</label>
				<form:select path="typeId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('gbjBuy_type_id')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>国标标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>

			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" />
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
				</li>

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
				<!-- <th>标签</th> -->
				<th>点赞数</th>
				<th>查看数</th>
				<th>评论数</th>
				<th>创建时间</th>
				<th>备注</th>
				
				<th>发布撤回</th>
				<shiro:hasPermission name="sys:gbj:gbjBuy:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gbjBuy">
			<tr>
				<td><a href="${ctx}/sys/gbj/gbjBuy/form?id=${gbjBuy.id}">
					${gbjBuy.user.username}
				</a></td>
				<td>
					${gbjBuy.realname}
				</td>
				<td>
					${fns:getDictLabel(gbjBuy.typeId, 'gbjBuy_type_id', '')}
				</td>
				<td>
					${gbjBuy.title}
				</td>
				<td>
					${gbjBuy.description}
				</td>
				<td>
					${gbjBuy.price}
				</td>
				<td>
					${gbjBuy.mobile}
				</td>
				<%-- <td>
					${gbjBuy.tag}
				</td> --%>
				<td>
					${gbjBuy.upCounts}
				</td>
				<td>
					${gbjBuy.lookCounts}
				</td>
				<td>
					${gbjBuy.commentsCounts}
				</td>
				<td>
					<fmt:formatDate value="${gbjBuy.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${gbjBuy.remarks}
				</td>
				
				<td>
					${fns:getDictLabel(gbjBuy.frontDelFlag, 'gbj_front_del_flag', '')}
				</td>
				
				<td>
				<shiro:hasPermission name="sys:gbj:gbjBuy:edit">
    				<a href="${ctx}/sys/gbj/gbjBuy/form?id=${gbjBuy.id}">修改</a>
    			</shiro:hasPermission>
    			<shiro:hasPermission name="sys:gbj:gbjBuy:edit">
					<a href="${ctx}/sys/gbj/gbjBuy/delete?id=${gbjBuy.id}" onclick="return confirmx('确认要删除该买标信息列表吗？', this.href)">删除</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="sys:gbj:gbjBuy:edit">
					<a class="fabu" href="${ctx}/sys/gbj/gbjBuy/release?id=${gbjBuy.id}" onclick="return confirmx('确认要发布该买标信息吗？', this.href)">${gbjBuy.frontDelFlag}</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="sys:gbj:gbjBuy:edit">
					<a class="chehui" href="${ctx}/sys/gbj/gbjBuy/withdraw?id=${gbjBuy.id}" onclick="return confirmx('确认要撤回该买标信息吗？', this.href)">${gbjBuy.frontDelFlag}</a>
				</shiro:hasPermission>
			
			
				<shiro:hasPermission name="sys:gbj:gbjUserBuyComments:view">
					<a href="${ctx}/sys/gbj/gbjUserBuyComments/getBuyCommentsListbyid?buy_Id=${gbjBuy.id} ">查看评论</a>
				</shiro:hasPermission>
				 
				 
				<shiro:hasPermission name="sys:gbj:gbjUserBuyComments:view">
					<a href="${ctx}/sys/gbj/gbjUserBuyComments/form?buy_Id=${gbjBuy.id} ">评论追加</a>
				</shiro:hasPermission>
				
				<script type="text/javascript">
				$(document).ready(function() {
					
					
					$('.fabu').each(function(){
						if($(this).text() == '1'){
							$(this).attr("style","display:none;");
							
						}
						if($(this).text() == '0'){
							$(this).attr("style","display:;");
							$(this).text('发布');
						}
						
					}); 
					$('.chehui').each(function(){
						if($(this).text() == '0'){
							$(this).attr("style","display:none;");
							
						}
						if($(this).text() == '1'){
							$(this).attr("style","display:;");
							$(this).text('撤回');
						}
						
					});
					
				});
				</script> 
					
					
					
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>