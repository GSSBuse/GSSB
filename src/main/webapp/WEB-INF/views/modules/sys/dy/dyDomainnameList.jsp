<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>域名信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var type = "${allList}";
		 	var checkBox = document.getElementById("allListCheck");
			if(type == "1"){
				checkBox.checked = false;
			}else{
				checkBox.checked = true;
			}  
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		//是否查看全部
		function allListType(obj){
		 	var checkBox = document.getElementById("allListCheck");
		 	var allistInput = document.getElementsByName("allList")[0];
			if(!checkBox.checked){		//查看全部
				allistInput.value = "1";
			}else{	//查看自己
				allistInput.value = "0";
			}
			$("#searchForm").submit();
		}

		function jjj(obj, domainId) {
			top.$.jBox.warning("审核通过请点击 '是' ,  审核不通过请点击 '否'。", "审核", function (v, h, f) {
			    if (v == 'yes') {
			    	window.location.href="${ctx}/sys/dy/dyDomainname/savePass?id=" + domainId + "&pass=1";
			    }
			    if (v == 'no') {
			    	window.location.href="${ctx}/sys/dy/dyDomainname/savePass?id=" + domainId + "&pass=0";
			    }
			    if (v == 'cancel') {
			    	//window.location.href="${ctx}/sys/dy/dyDomainname?allList=0";
			    }
			});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
		function stopSell(domainnameId){
			top.$.jBox.confirm("确定要停止拍卖吗？","确认",function(v,h,f){
				if(v == "ok"){
					$.get("${ctx}/sys/dy/dyDomainname/stopSell?id="+domainnameId,function(success){
							if(success != "success"){
								top.$.jBox.tip("停止拍卖失败【"+success+"】");
							}else{
								top.$.jBox.tip("停止拍卖成功");
							}
							$("#searchForm").submit();
					});
				}
			});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
		function confirmPayDeposit(domainnameId){
			top.$.jBox.confirm("确定帮其支付保证金？","确认",function(v,h,f){
				if(v == "ok"){
					$.get("${ctx}/sys/dy/dyDomainname/confirmPayDeposit?id="+domainnameId,function(res){
						top.$.jBox.tip(res,'info',{timeout:2000});
						setTimeout(function(){
							$("#searchForm").submit();
						},3000);
					});
				}
			});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dy/dyDomainname">域名信息列表</a></li>
		<shiro:hasPermission name="sys:dy:dyDomainname:edit"><li><a href="${ctx}/sys/dy/dyDomainname/form">域名信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dyDomainname" action="${ctx}/sys/dy/dyDomainname/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort name="orderBy" value="${page.orderBy}" callback="page();" id="orderBy"></sys:tableSort>
		<ul class="ul-form">
			<li><label>经纪人姓名：</label>
				<form:input path="dyClient.broker.name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>域名名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<!-- 2015-10-22 yuyang start 查询条件修改 -->
			<li><label>卖家昵称：</label>
				<form:input path="dyClient.nickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>卖家手机：</label>
				<form:input path="dyClient.mobile" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status">
					<form:options items="${fns:getDictList('dy_domainname_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li hidden="true"><label></label><input name="allList" value="${allList}"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>	
			<li style="margin-left: 50px"><input id="allListCheck" type="checkbox" onclick="allListType(this)" />只看自己</li>	
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column name">域名名称</th>
				<th class="sort-column dyClient.name">卖家姓名</th>
				<th class="sort-column dyClient.nickname">卖家昵称</th>
				<th>卖家手机</th>
				<th class="sort-column endTime">截拍时间</th>
				<th>域名描述</th>
				<th>保留价</th>
				<th>状态</th>
				<th class="sort-column brokerName">经纪人</th>
				<th>更新时间</th>
				<shiro:hasPermission name="sys:dy:dyDomainname:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyDomainname">
			<tr>
				<td><a href="${ctx}/sys/dy/dyDomainname/form?id=${dyDomainname.id}">${dyDomainname.name}</a></td>
				<td>${dyDomainname.dyClient.name}</td>
				<td>${dyDomainname.dyClient.nickname}</td>
				<td>${dyDomainname.dyClient.mobile}</td>
				<td>
					<fmt:formatDate value="${dyDomainname.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td style="width: 25%">
					${dyDomainname.description}
				</td>
				<td>
					${dyDomainname.reservePrice}
				</td>
				<td>
					<c:if test="${dyDomainname.status eq '01'}">
					       审核中
					 </c:if>
					<c:if test="${dyDomainname.status eq '02'}">
					      审核失败
					 </c:if>
					<c:if test="${dyDomainname.status >= '03'}">
					       审核通过
					 </c:if>
					 <c:if test="${dyDomainname.status eq '00'}">
					       待付保证金
					 </c:if>
				</td>
				<td>${dyDomainname.dyClient.broker.name}</td>
				<td>
					<fmt:formatDate value="${dyDomainname.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="sys:dy:dyDomainname:edit"><td>
					<c:if test="${dyDomainname.status eq '01'}">
						<a href="${ctx}/sys/dy/dyDomainname/form?id=${dyDomainname.id}">审核</a>
					</c:if>
					<c:if test="${dyDomainname.status ne '01' and dyDomainname.status ne '00'}">
						<a href="${ctx}/sys/dy/dyDomainname/form?id=${dyDomainname.id}">修改</a>
					</c:if>
					<%-- <a href="${ctx}/sys/dy/dyDomainname/delete?id=${dyDomainname.id}" onclick="return confirmx('确认要删除该域名信息吗？', this.href)">删除</a> --%>
    				<c:if test="${dyDomainname.status ne '01' and dyDomainname.status ne '02' and dyDomainname.status ne '00'}">
					     <a href="${ctx}/sys/dy/dyBidhistory?domainId=${dyDomainname.id}">出价管理</a>
					 </c:if>
					 <c:if test="${dyDomainname.status eq '03'}">
					 	<a href="#" onclick="stopSell('${dyDomainname.id}')">停止拍卖</a>
					 </c:if>
					  <c:if test="${dyDomainname.status eq '00'}">
					 	<a href="#" onclick="confirmPayDeposit('${dyDomainname.id}')">确认支付保证金</a>
					 </c:if>
					<%-- <c:if test="${dyDomainname.status eq '01'}">
					     <a href="#"onclick="jjj(this, '${dyDomainname.id}')">审核</a>
					 </c:if> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>