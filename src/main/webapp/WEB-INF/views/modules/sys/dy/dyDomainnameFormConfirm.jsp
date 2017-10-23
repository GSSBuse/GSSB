<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>域名信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			/*设置隐藏选项*/
			if("${isShareBonus}" == 0){
				$("#bonusShareTotalDiv").hide();
				$("#bonusShareNumberDiv").hide();
			}
		});
		 function conComfirm(r){
			 if(r == "0"){
				 $("#statusId").val("02");
			 }else if(r == "1"){
				 $("#statusId").val("03");
			 }
		
			if($("#bonusShareTotalId").val() == ""){
				$("#bonusShareTotalId").val("0");
			}
			if($("#bonusShareNumberId").val() == ""){
				$("#bonusShareNumberId").val("0");
			}
			if($("#bonusSecond").val() == ""){
				$("#bonusSecond").val("0");
			}
			if($("#reservePrice").val() == ""){
				$("#reservePrice").val("0");
			}
		
		 }
		function preView(inputId){
			var path = $("#"+inputId).val();
			if(path == ""){
				return false;
			}
			var html = "<div><image width='800px' src='"+path+".org'></div>";
			$.jBox(html,{title : "预览"});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dy/dyDomainname/">域名信息列表</a></li>
		<li class="active"><a href="${ctx}/sys/dy/dyDomainname/form?id=${dyDomainname.id}">域名信息审核</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dyDomainname" action="${ctx}/sys/dy/dyDomainname/savePass" method="post" class="form-horizontal">
		<form:hidden path="id" id="id"/>
		<form:hidden path="clientId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">域名名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="63" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">卖家米友号：</label>
			<div class="controls">
				<form:input path="dyClient.dyid" readonly="true"  htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">拍卖结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${dyDomainname.endTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">域名描述：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保留价：</label>
			<div class="controls">
				<form:input path="reservePrice" id="reservePrice" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<c:if test="${not empty dyDomainname.id}">
			<div class="control-group">
				<label class="control-label">状态：</label>
				<div class="controls">	
						<form:hidden id="statusId"  path="status" htmlEscape="false" class="input-xlarge  digits"/>
						<c:if test="${dyDomainname.status eq '01'}">
						       审核中
						 </c:if>
						<c:if test="${dyDomainname.status eq '02'}">
						      审核失败
						 </c:if>
						<c:if test="${dyDomainname.status >= '03'}">
						       审核通过
						 </c:if>
				</div>
			</div>
		</c:if>
		<div class="control-group" id="bonusShareTotalDiv">
			<label class="control-label">转发送红包总额：</label>
			<div class="controls">
				<form:input path="bonusShareTotal" id="bonusShareTotalId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group" id="bonusShareNumberDiv">
			<label class="control-label">转发送红包份数：</label>
			<div class="controls">
				<form:input path="bonusShareNumber" id="bonusShareNumberId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">次高价红包：</label>
			<div class="controls">
				<form:input path="bonusSecond" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片1：</label>
			<div class="controls">
				<form:hidden id="nameImage1" path="image1" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage1" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="150"/>
				<input class="btn" type="button" value="预览" onclick="preView('nameImage1')">
			</div>
			
			
		</div>
		<div class="control-group">
			<label class="control-label">图片2：</label>
			<div class="controls">
				<form:hidden id="nameImage2" path="image2" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage2" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="150"/>
				<input class="btn" type="button" value="预览" onclick="preView('nameImage2')">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片3：</label>
			<div class="controls">
				<form:hidden id="nameImage3" path="image3" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage3" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="150"/>
				<input class="btn" type="button" value="预览" onclick="preView('nameImage3')">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="confirmPass" class="btn btn-primary" type="submit" value="审核通过" onclick="conComfirm('1')"/>
			<input id="confirmFail" class="btn btn-primary" type="submit" value="审核失败" onclick="conComfirm('0')"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>