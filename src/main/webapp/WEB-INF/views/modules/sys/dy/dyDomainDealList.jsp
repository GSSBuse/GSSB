<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>域名管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var type="${allList}";
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dy/dyDomainname/deal">域名交易列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="bidCashInfo" action="${ctx}/sys/dy/dyDomainname/deal" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort name="orderBy" value="${page.orderBy}" callback="page();" id="orderBy"></sys:tableSort>
		<ul class="ul-form">
			<li><label>卖家昵称：</label>
				<form:input path="sellNickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>卖家米友号：</label>
				<form:input path="sellDyId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>买家昵称：</label>
				<form:input path="buyNickname" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>买家米友号：</label>
				<form:input path="buyDyId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>域名名称：</label>
				<form:input path="domainName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>截拍日期：</label>
				<input name="searchStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${bidCashInfo.searchStartTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				&nbsp;&nbsp;&nbsp;&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="searchEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${bidCashInfo.searchEndTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="clearfix"></li>
		</ul>
		<ul class="ul-form">
			<li><label>交易状态：</label>
				<form:select path="status" cssStyle="width: 230px;">
					<option value="" >*.全部</option>
					<option value="11" >1.待买家付款</option>
					<option value="12" >2.买家已付款，待卖家转移域名</option>
					<option value="13" >3.卖家已转移域名，待买家确认</option>
					<option value="14" >4.买家已确认，等待交易完成</option>
					<option value="21" >5.买家违约</option>
					<option value="22" >6.卖家违约</option>
					<option value="15" >f.交易结束</option>
					<option value="23" >e.流拍或终止</option>
				</form:select>
			</li>
			<li><input type="hidden" name="allList" value="${allList}"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li style="margin-left: 50px"><input id="allListCheck" type="checkbox" onclick="allListType(this)" />只看自己</li>	
		</ul>	
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column domainName">域名</th>
				<th class="sort-column sellName">卖家姓名</th>
				<th>卖家昵称</th>
				<th  class="sort-column sellBrokerName">卖家经纪人</th>
				<th class="sort-column buyName">买家姓名</th>
				<th>买家昵称</th>
				<th class="sort-column buyBrokerName">买家经纪人</th>
				<th>保留价</th>
				<th>成交金额</th>
				<th>佣金</th>
				<th>次高价红包</th>
				<th>拍卖结束时间</th>
				<th>交易结束时间</th>
				<th class="sort-column status">交易状态</th>
				<shiro:hasPermission name="sys:dy:dyCashFlow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bidCashInfo">
			<tr>
				<td>
				<a href="${ctx}/sys/dy/dyDomainname/bidCashInfoForm?domainId=${bidCashInfo.domainId}">
					${bidCashInfo.domainName}
				</a>
				</td>
				<td>
					${bidCashInfo.sellName}
				</td>
				<td>
					${bidCashInfo.sellNickname}
				</td>
				<td>
					${bidCashInfo.sellBrokerName}
				</td>
				<td>
					${bidCashInfo.buyName}
				</td>
				<td>
					<a href="${ctx}/sys/dy/dyClient/form?id=${bidCashInfo.buyClientId}">
						${bidCashInfo.buyNickname}
					</a>
				</td>
				<td>
					${bidCashInfo.buyBrokerName}
				</td>
				<td>
					${bidCashInfo.reservePrice}
				</td>
				<td>
					${bidCashInfo.bidAmount}
				</td>
				<td>
					${bidCashInfo.rebate}
				</td>
				<td>
					${bidCashInfo.bonusSecond}
				</td>
				<td>
					${bidCashInfo.endTime}
				</td>
				<td>
					${bidCashInfo.waitTime}
				</td>
				<td>
					<c:if test="${bidCashInfo.status eq '11'}">
						1.待买家付款
					</c:if>
					<c:if test="${bidCashInfo.status eq '12'}">
						2.买家已付款，待卖家转移域名
					</c:if>
					<c:if test="${bidCashInfo.status eq '13'}">
						3.卖家已转移域名，待买家确认
					</c:if>
					<c:if test="${bidCashInfo.status eq '14'}">
						4.买家已确认，等待交易完成
					</c:if>
					<c:if test="${bidCashInfo.status eq '15'}">
						f.交易完成
					</c:if>
					<c:if test="${bidCashInfo.status eq '21'}">
						5.买家违约
					</c:if>
					<c:if test="${bidCashInfo.status eq '22'}">
						6.卖家违约
					</c:if>
					<c:if test="${bidCashInfo.status eq '23'}">
						e.流拍或终止
					</c:if>
				</td>
				<td>
					<shiro:hasPermission name="sys:dy:dyDomainname:edit">
						<a href="${ctx}/sys/dy/dyDomainname/bidCashInfoForm?domainId=${bidCashInfo.domainId}">修改</a>	
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>