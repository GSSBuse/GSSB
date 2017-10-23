<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>加价幅度与保证金管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
/* 			$('#aRow').bind("click", function(){
				addRow1(this);
			}); */
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		/**检查最后一行数据是否合法*/
		function checkLastRow(){
			var endLevel = document.getElementsByName("endLevel");
			var startLevel = document.getElementsByName("startLevel");
			var price = document.getElementsByName("price");
			if( isNaN(endLevel[endLevel.length-1].value) || isNaN(price[price.length-1].value) ||
				Number(endLevel[endLevel.length-1].value) <= 0 || Number(price[price.length-1].value) < 0){
				top.$.jBox.error("请输入正整数");
				return false;
			}
			if(endLevel.length == 1){
				if("${type}" == "bid_rule_increment" && Number(price[price.length-1].value) == 0){
					top.$.jBox.error("加价幅度必须大于0");
					return false;
				}else{
					return true;
				}
			}
			if(Number(endLevel[endLevel.length-1].value) <= Number(startLevel[startLevel.length-1].value)){
				top.$.jBox.error("价格区间设置有误");
				return false;
			}
			if("${type}" == "bid_rule_increment"){
				if((Number(endLevel[endLevel.length-1].value) - Number(startLevel[startLevel.length-1].value)) % Number(price[price.length-1].value) != 0){
					top.$.jBox.error("加价区间必须为加价金额的整数倍");
					return false;
				}
			}
			if(endLevel.length >=2 && Number(price[price.length-1].value) <= Number(price[price.length-2].value)){
				top.$.jBox.error("当前金额小于前一区间的金额");
				return false;
			}
			endLevel[endLevel.length-1].readOnly = true;
			price[price.length-1].readOnly = true;
			return true;
		}
		function addRow1(r){
			if(checkLastRow() == false){
				return false;
			}
			//创建新行
			var newTr = document.getElementById("contentTable").insertRow();
			var newTd0 = newTr.insertCell();
			var newTd1 = newTr.insertCell();
			var newTd2 = newTr.insertCell();
			var newTd3 = newTr.insertCell();
			newTd0.innerHTML = '<input  type="text" readonly="readonly" name="startLevel" value=""/>';
			newTd1.innerHTML = '<input  type="text"  name="endLevel" value=""/>';
			newTd2.innerHTML = '<input  type="text"  name="price" value=""/>';
			newTd3.innerHTML = '<shiro:hasPermission name="sys:dy:dyLevelSetting:edit">'+
							   '<input type="button" value="-" onclick="delRow1(this)"/>'+
							   '<input type="button" value="+" onclick="addRow1(this)"/>'+
							   '</shiro:hasPermission>';
		   var endLevel = document.getElementsByName("endLevel");
		   var startLevel = document.getElementsByName("startLevel");
			startLevel[startLevel.length-1].value = endLevel[endLevel.length-2].value;
			r.parentNode.innerHTML  = "";
		}
		function delRow1(r){
			var index = document.getElementById("contentTable").rows.length;
			if(index == 2){
				alert("已是最后一行");
				return;
			}
			document.getElementById("contentTable").deleteRow(index-1);
			document.getElementById("contentTable").rows[index-2].cells[3].innerHTML = 
							   '<shiro:hasPermission name="sys:dy:dyLevelSetting:edit">'+
							   '<input type="button" value="-" onclick="delRow1(this)"/>'+
							   '<input type="button" value="+" onclick="addRow1(this)"/>'+
							   '</shiro:hasPermission>';
			var endLevel = document.getElementsByName("endLevel");
			var price = document.getElementsByName("price");
			endLevel[endLevel.length-1].readOnly = false;
			price[price.length-1].readOnly = false;
		}
		/**提交前检查最后一行*/
		function confirmDo(){
			if(checkLastRow() == false){
				return false;
			}
			$("#subForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dy/dyLevelSetting?type=${type}">
			<c:if test="${type eq 'bid_rule_increment'}">
				加价幅度设置
			</c:if>
			<c:if test="${type eq 'bid_rule_deposit'}">
				保证金设置
			</c:if>
		</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="dyLevelSetting" action="${ctx}/sys/dy/dyLevelSetting/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input name="type" type="hidden" value="${type}"/>
	</form:form>
	<sys:message content="${message}"/>
	<form:form id="subForm" action="${ctx}/sys/dy/dyLevelSetting/subTable" method="post" >
		<div hidden="true"><input type="text" name="type" value="${type}"/></div>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th colspan="2" style="text-align:center;">出价区间</th>
					<c:if test="${type eq 'bid_rule_increment'}">
						<th>加价金额</th>
					</c:if>
					<c:if test="${type eq 'bid_rule_deposit'}">
						<th>保证金</th>
					</c:if>
					<shiro:hasPermission name="sys:dy:dyLevelSetting:edit"><th>操作</th></shiro:hasPermission>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="dyLevelSetting" varStatus="status" >
				<tr name="tr">
					<td>
						<c:if test="${status.index eq 0}">
							<input  type="text" readonly="readonly" name="startLevel" value="0"/>
						</c:if>
						 <c:if test="${status.index != 0}">
							<input  type="text" readonly="readonly" name="startLevel" value="${page.list[status.index-1].level}"/>
						</c:if>
					</td>
					<td>
						<c:if test="${!status.last}">
							<input  type="text" name="endLevel" readonly="readonly"  value="${dyLevelSetting.level}"/> 
						</c:if>
						<c:if test="${status.last}">
							<input  type="text" name="endLevel" value="${dyLevelSetting.level}"/> 
						</c:if>
						
					</td>
					<td>
						<c:if test="${!status.last}">
							<input  type="text" name="price" readonly="readonly"  value="${dyLevelSetting.price}"/>
						</c:if>
						<c:if test="${status.last}">
							<input  type="text" name="price" value="${dyLevelSetting.price}"/>
						</c:if>
					</td>
					<td name="td">
						<shiro:hasPermission name="sys:dy:dyLevelSetting:edit">
							<c:if test="${status.last}">
									<input type="button" value="-" onclick="delRow1(this)"/>
									<input type="button" value="+" onclick="addRow1(this)"/>
							</c:if>
						</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="form-actions" align="center">
			<shiro:hasPermission name="sys:dy:dyLevelSetting:edit">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="confirmDo()"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<div class="pagination">${page}</div>
</body>
</html>