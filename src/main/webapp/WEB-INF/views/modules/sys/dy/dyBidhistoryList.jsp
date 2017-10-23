<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出价管理</title>
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
		function updateProxy(){
			var bidAmount = parseInt("${page.list[0].bidAmount}");
			var proxy = parseInt("${page.list[0].proxyAmount}");
			var html = "<div style='padding:10px;'>输入新的代理竞价：<input type='text' id='newProxy' name='newProxy'/>"+
			"代理竞价必须介于【"+bidAmount+"，"+proxy+"】之间</div>";
			var submit = function (v, h, f) {
				var temp = f.newProxy;
			    if (temp == "" || isNaN(temp) || parseInt(temp) <= bidAmount || parseInt(temp) >= proxy ){
			        $.jBox.tip("代理竞价不合法。", 'error', { focusId: "newProxy" }); // 关闭设置 yourname 为焦点
			        return false;
			    }
				if(v == "ok"){
					var id = "${page.list[0].bidhistoryId}";
					$.post("${ctx}/sys/dy/dyBidhistory/updateProxy",{id : id, proxyAmount : temp},function(data){
						if(data == "success"){
							top.$.jBox.success("修改代理竞价成功");
						}else{
							top.$.jBox.error("修改代理竞价失败【"+data+"】");
						}
						$("#searchForm").submit();
					});
				}
			    return true;
			};

			$.jBox(html, { title: "输入新的代理竞价", submit: submit });
			
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dy/dyBidhistory?domainId=${bidClient.domainId}">出价列表</a></li>
		<c:if test="${domainStatus eq '03'}">
			<li><a href="${ctx}/sys/dy/dyBidhistory/form?domainnameId=${bidClient.domainId}">出价添加</a></li>
		</c:if>
		<li><a href="${ctx}/sys/dy/dyDomainname/">域名信息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bidClient" action="${ctx}/sys/dy/dyBidhistory?domainId=${bidClient.domainId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>米友号：</label>
				<form:input path="dyid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>出价类型：</label>
				<select name="type">
						<option value="" <c:if test="${empty bidClient.type}">selected="selected"</c:if>>查看全部</option>
						<option value="普通出价" <c:if test="${'普通出价' eq bidClient.type}">selected="selected"</c:if>>普通出价</option>
						<option value="代理竞价" <c:if test="${'代理竞价' eq bidClient.type}">selected="selected"</c:if>>代理竞价</option>
				</select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>域名</th>
				<th>会员昵称</th>
				<th>会员米友号</th>
				<th>出价金额</th>
				<th>代理竞价金额</th>
				<th>出价类型</th>
				<th>出价时间</th>
				<th>截拍时间</th>
				<shiro:hasPermission name="sys:dy:dyBidhistory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bidClient" varStatus="status">
			<tr>
				<td>
					${bidClient.domainName}
				</td>
				<td>
					${bidClient.nickname}
				</td>
				<td>
					${bidClient.dyid}
				</td>
				<td>
					${bidClient.bidAmount}
				</td>
				<td>
					${bidClient.proxyAmount}
				</td>
				<td>
					${bidClient.type}
				</td>
				<td>
					${bidClient.bidTime}
				</td>
				<td>
					${bidClient.endTime}
				</td>
				<shiro:hasPermission name="sys:dy:dyBidhistory:edit"><td>
				<c:set var="nowDate" value="<%=String.valueOf(System.currentTimeMillis())%>"></c:set>
				<c:if test="${status.index == 0 and not empty canAlter}">
					<a href="#" onclick="updateProxy()">修改代理竞价</a>
				</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>