<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var type = "${allList}";
			var checkBox = $("#allListCheck");
			if(type == "0"){
				checkBox.attr('checked' , true);
			}else{
				checkBox.attr('checked' , false);
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function allListType(obj){
			var checkBox = $("#allListCheck");
			var allListInput = $("#allListInput");
			if(checkBox.attr("checked")){
				allListInput.val("0");
			}else{
				allListInput.val("1");
			}
			$("#searchForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dy/dyClient/">会员信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="dyClient" action="${ctx}/sys/dy/dyClient?search=search" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort name="orderBy" value="${page.orderBy}" callback="page();" id="orderBy"></sys:tableSort>
		<ul class="ul-form">
			<li><label>经纪人：</label>
				<form:input path="broker.name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>微信昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>手机号：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>米友号：</label>
				<form:input path="dyid" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>身份认证：</label>
				<select name="authenticationMark">
				 	<option value="" <c:if test="${empty dyClient.authenticationMark}">selected="selected"</c:if>>查看全部</option>
				 	<option value="0" <c:if test="${'0' eq dyClient.authenticationMark}">selected="selected"</c:if>>未认证</option>
				 	<option value="2" <c:if test="${'2' eq dyClient.authenticationMark}">selected="selected"</c:if>>认证中</option>
				 	<option value="1" <c:if test="${'1' eq dyClient.authenticationMark}">selected="selected"</c:if>>已认证</option>
				</select>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</li>
			<li><input type="hidden" id="allListInput" name="allList" value="${allList}"></li>
			<li style="margin-left: 50px"><input id="allListCheck" type="checkbox"  onclick="allListType(this)" />只看自己</li>	
			<li class="clearfix"></li>
		</ul>
		<ul class="ul-form">
			<shiro:hasPermission name="sys:dy:dyClient:edit">
				<li><label>批量操作：</label>
					<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
					<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
					<input id="btnUpload" class="btn btn-primary" type="button" value="同步"/>
				</li>
			</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column dyid">米友号</th>
				<th class="sort-column name">姓名</th>
				<th class="sort-column nickname">微信昵称</th>
				<th>邮箱</th>
				<th>手机</th>
				<th>QQ号</th>
				<th>微信号</th>
				<th>身份认证</th>
				<th>所属经纪人</th>
				<th>账户总额</th>
				<th>账户冻结金额</th>
				<shiro:hasPermission name="sys:dy:dyClient:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyClient">
			<tr>
				<td>
					<a href="${ctx}/sys/dy/dyClient/form?id=${dyClient.id}">
						${dyClient.dyid}
					</a>
				</td>
				<td>
					${dyClient.name}
				</td>
				<td>
					${dyClient.nickname}
				</td>
				<td>
					${dyClient.email}
				</td>
				<td>
					${dyClient.mobile}
				</td>
				<td>
					${dyClient.qq}
				</td>
				<td>
					${dyClient.wx}
				</td>
				<td>
					<c:if test="${dyClient.authenticationMark eq '0'}">
						未认证
					</c:if>
					<c:if test="${dyClient.authenticationMark eq '1'}">
						已认证
					</c:if>
					<c:if test="${dyClient.authenticationMark eq '2'}">
						认证中
					</c:if>
				</td>
				<td>
					${dyClient.broker.name}
				</td>
				<td>
					${dyClient.dyFinance.accountBalance}
				</td>
				<td>
					${dyClient.dyFinance.freezeBalance}
				</td>
				<shiro:hasPermission name="sys:dy:dyClient:edit"><td>
					<a href="${ctx}/sys/dy/dyCashFlow/dyCashFlowApply?clientId=${dyClient.id}">充值提现</a>
    				<a href="${ctx}/sys/dy/dyClient/form?id=${dyClient.id}">修改</a>
    				<a href="${ctx}/sys/dy/dyCashFlow/dyCashFlowClient?clientId=${dyClient.id}">资金流</a>
    				<a href="${ctx}/sys/dy/dyCashFlow/freeCashFlowClient?clientId=${dyClient.id}">冻结解冻</a>
					<%-- <a href="${ctx}/sys/dy/dyClient/delete?id=${dyClient.id}" onclick="return confirmx('确认要删除该会员信息吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>