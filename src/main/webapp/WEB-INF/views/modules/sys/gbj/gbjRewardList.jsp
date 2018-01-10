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
				<th>标题</th>
				<th>起名需求</th>
				<th>打赏金额</th>
				<th>手机号码</th>
				<th>支付状态</th>
				<th>状态</th>
				<th>支付流水号</th>
				<th>中标者</th>
				
				<th>备注</th>
				
				<th>发布撤回</th>
				<!-- <th>删除标记</th> -->
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
					${fns:getDictLabel(gbjReward.payStatus, 'gbj_pay_status', '')}
				</td>
				<td>
					${fns:getDictLabel(gbjReward.status, 'gbj_reward_status', '')}
				</td>
				<td>
					${gbjReward.payFlowNumber}
				</td>
				<td>
					${gbjReward.successfulBidder}
				</td>
				
				<td>
					${gbjReward.remarks}
				</td>
				<td>
					${fns:getDictLabel(gbjReward.frontDelFlag, 'gbj_front_del_flag', '')}
				</td>
				
				<%-- <td>
					${fns:getDictLabel(gbjReward.delFlag, 'del_flag', '')}
				</td> --%>
					
					<td><shiro:hasPermission name="sys:gbj:gbjReward:edit">
						<a
							href="${ctx}/sys/gbj/gbjReward/form?id=${gbjReward.id}">修改</a> <a
							href="${ctx}/sys/gbj/gbjReward/delete?id=${gbjReward.id}"
							onclick="return confirmx('确认要删除该悬赏信息管理吗？', this.href)">删除</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:gbj:gbjReward:edit">
					<a class="fabu" href="${ctx}/sys/gbj/gbjReward/release?id=${gbjReward.id}" onclick="return confirmx('确认要发布该悬赏信息吗？', this.href)">${gbjReward.frontDelFlag}</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="sys:gbj:gbjReward:edit">
					<a class="chehui" href="${ctx}/sys/gbj/gbjReward/withdraw?id=${gbjReward.id}" onclick="return confirmx('确认要撤回该悬赏信息吗？', this.href)">${gbjReward.frontDelFlag}</a>
				</shiro:hasPermission>
					
					
					<shiro:hasPermission name="sys:gbj:gbjUserRewardComments:view">
					<a href="${ctx}/sys/gbj/gbjUserRewardComments/getRewardCommentsListbyid?reward_Id=${gbjReward.id} ">查看评论</a>
				</shiro:hasPermission>	
				
				<shiro:hasPermission name="sys:gbj:gbjUserRewardComments:view">
					<a href="${ctx}/sys/gbj/gbjUserRewardComments/form?reward_Id=${gbjReward.id} ">评论追加</a>
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