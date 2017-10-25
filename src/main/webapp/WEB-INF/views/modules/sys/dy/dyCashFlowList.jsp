<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资金流管理</title>
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
		function ahrefClick(r){
			$("#searchForm").submit();
		}
		function confirmDo(r,cashFlowId){
			var html = "<div style='padding:10px;'>"+
						"<p>打回请点击'否'，确认请点击'是'</p>"+
				"备注信息：<textarea cols='45' rows='3' id='remarks' name='remarks' /></div>";
			top.$.jBox.warning(html,"确认",function(v,h,f){
				/*创建form并提交*/ 
				 var temp = f.remarks;
					 if (temp.length > 250) {
					        $.jBox.tip("长度请控制在250个字符以内。", 'error', { focusId: "remarks" }); // 关闭设置 yourname 为焦点
					        return false;
					    }
				 var pass = "";
				 if (v == 'yes') {
					pass = "1";
				    }
			    if (v == 'no') {
			    	pass = "0";
			    }
			    if (v != 'cancel') {
			    	$.post("${ctx}/sys/dy/dyCashFlow/confirmDo",{id : cashFlowId , pass : pass , remarks : temp}, function(data){
			    		if(data != "success"){
							top.$.jBox.error("确认失败【"+data+"】");
						}else{
							top.$.jBox.info("确认成功");
						}
						$("#searchForm").submit();
			    	});
			    }
			});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
		function processing(r,cashFlowId){
			top.$.jBox.warning("不同意提现申请，请点击'否'，同意请点击'是'，并请尽快为会员线下转账。","确认",function(v,h,f){
				var pass = "";
				if(v == 'yes'){
					pass = "pass";
				}
				if(v != 'cancel'){
					top.$.post("${ctx}/sys/dy/dyCashFlow/drawProcessing",{id : cashFlowId , pass : pass},function(success){
						if(success != "success"){
							top.$.jBox.error("提现处理失败【"+success+"】");
						}else{
							top.$.jBox.info("提现处理成功");
						}
						$("#searchForm").submit();
					});
				}
			});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#" onclick="ahrefClick(this)">资金流列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cashFlowInfo" action="${ctx}/sys/dy/dyCashFlow/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort name="orderBy" value="${page.orderBy}" callback="page();" id="orderBy"></sys:tableSort>
		<ul class="ul-form">
			<li><label>经纪人姓名：</label>
				<form:input path="brokerName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>会员姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>米友号：</label>
				<form:input path="dyid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>操作：</label>
				<select name="operate">
					<option value="" <c:if test="${empty cashFlowInfo.operate}">selected="selected"</c:if>>查看全部</option>
					<option value="微信充值" <c:if test="${'微信充值' eq cashFlowInfo.operate}">selected="selected"</c:if>>微信充值</option>
					<option value="线下充值" <c:if test="${'线下充值' eq cashFlowInfo.operate}">selected="selected"</c:if>>线下充值</option>
					<option value="提现" <c:if test="${'提现' eq cashFlowInfo.operate}">selected="selected"</c:if>>提现</option>
				</select>
			</li>
			<li><label>确认结果：</label>
				<select name="confirmResult">
					<option value="" <c:if test="${empty dyCashFlow.confirmResult}">selected="selected"</c:if>>全部</option>
					<option value="等待" <c:if test="${'等待' eq cashFlowInfo.confirmResult}">selected="selected"</c:if>>等待</option>
					<option value="完成" <c:if test="${'完成' eq cashFlowInfo.confirmResult}">selected="selected"</c:if>>完成</option>
					<option value="打回" <c:if test="${'打回' eq cashFlowInfo.confirmResult}">selected="selected"</c:if>>打回</option>
					<option value="处理中" <c:if test="${'处理中' eq cashFlowInfo.confirmResult}">selected="selected"</c:if>>处理</option>
				</select>
			</li>
			<li><label>日期范围：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${cashFlowInfo.startTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				&nbsp;&nbsp;&nbsp;&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${cashFlowInfo.endTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column brokerName">经纪人</th>
				<th class="sort-column clientDyid">米友号</th>
				<th class="sort-column clientName">会员姓名</th>
				<th class="sort-column clientNickname">微信昵称</th>
				<th>手机</th>
				<th>微信订单号</th>
				<th>银行账号</th>
				<th>操作</th>
				<th>操作金额</th>
				<th>操作时间</th>
				<th>财务确认结果</th>
				<th width="10%">备注</th>
				<shiro:hasPermission name="sys:dy:dyCashFlow:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dyCashFlow">
			<tr>
				<td>
					${dyCashFlow.dyClient.broker.name}
				</td>
				<td>
					${dyCashFlow.dyClient.dyid}
				</td>
				<td>
					${dyCashFlow.dyClient.name}
				</td>
				<td>
					${dyCashFlow.dyClient.nickname}
				</td>
				<td>
					${dyCashFlow.dyClient.mobile}
				</td>
				<td>
					${dyCashFlow.transactionId}
				</td>
				<td>
					${dyCashFlow.dyClient.bankName} <br>
					${dyCashFlow.dyClient.bankLocation}<br>
					${dyCashFlow.dyClient.defaultIncomeExpense}
				</td>
				<td>
					${dyCashFlow.operate}
				</td>
				<td>
					${dyCashFlow.operateAmount}
				</td>
				<td>
					<fmt:formatDate value="${dyCashFlow.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dyCashFlow.confirmResult}
				</td>
				<td>
					${dyCashFlow.remarks}
				</td>
				<shiro:hasPermission name="sys:dy:dyCashFlow:edit"><td>
					<c:if test="${dyCashFlow.confirmResult eq '等待' and (dyCashFlow.operate eq '微信充值' or dyCashFlow.operate eq '线下充值')}">
						<a href="#" onclick="confirmDo(this,'${dyCashFlow.id}')">确认</a>
					</c:if>
					<c:if test="${dyCashFlow.confirmResult eq '处理中' and dyCashFlow.operate eq '提现'}">
						<a href="#" onclick="confirmDo(this,'${dyCashFlow.id}')">确认</a>
					</c:if>
					<c:if test="${dyCashFlow.confirmResult eq '等待' and dyCashFlow.operate eq '提现'}">
						<a href="#" onclick="processing(this,'${dyCashFlow.id}')">处理</a>
					</c:if>
					<%-- <c:if test="${dyCashFlow.confirmResult eq '完成' or dyCashFlow.confirmResult eq '打回'}">
						<a href="${ctx}/sys/dy/dyCashFlow/delete?id=${dyCashFlow.id}" onclick="return confirmx('确认要删除该资金流吗？', this.href)">删除</a>
					</c:if> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>