<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员信息管理</title>
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
		});
		function preView(inputId){
			var path = $("#"+inputId).val();
			var html = "<div><image width='800px' src='"+path+"'></div>";
			$.jBox(html,{title : "预览"});
		}
		function checkValue(){
			var newByid = $("#dyidId").val();
			if(newByid != $("#oldDyidID").val()){	//如果修改了米友号
				var dyidNum = parseInt(newByid);
				if(dyidNum >= 10000){
					top.$.jBox.error("米友靓号必须小于【10000】");
					return false;
				}
			}
			if($("#IDcardNumberId").val() != ""){
				var reg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;//只能输入身份证格式
				if(!reg.test($("#IDcardNumberId").val())){
					top.$.jBox.error("身份证号码格式不对");
					return false;
				}
			}
			var authenticationMark = $("#authenticationMark").val();
			var name = $("#name").val();
			if(authenticationMark == "1" && name == ""){
				top.$.jBox.error("身份通过认证后，必须输入会员姓名");
				return false;
			}
			$("#inputForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dy/dyClient/">会员信息列表</a></li>
		<li class="active"><a href="${ctx}/sys/dy/dyClient/form?id=${dyClient.id}">会员信息<shiro:hasPermission name="sys:dy:dyClient:edit">${not empty dyClient.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:dy:dyClient:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dyClient" action="${ctx}/sys/dy/dyClient/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<c:if test="${not empty dyClient.id}">
			<div class="control-group">
			<label class="control-label">微信头像：</label>
			<div class="controls">
				<form:hidden id="nameImage" path="photo"  htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<img alt="" src="${dyClient.photo}" width="80">
			</div>
			</div>
			<div class="control-group">
				<label class="control-label">所属经纪人:</label>
				<div class="controls">
	                <sys:treeselect id="broker" name="brokerId" value="${dyClient.broker.id}" labelName="broker.name" labelValue="${dyClient.broker.name}"
						title="经纪人" url="/sys/user/brokerTreeDate" cssClass="required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">米友号：</label>
				<div class="controls">
					<input id="oldDyidID" name="oldDyid" value="${dyClient.dyid}" type="hidden"/>
					<form:input id="dyidId" path="dyid" htmlEscape="false" maxlength="200" class="input-xlarge required digits"/>
					<span class="help-inline">如果想修改米友靓号，则必须小于【10000】</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">微信标识：</label>
				<div class="controls">
					<form:input path="openid" readonly="true" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信昵称：</label>
			<div class="controls">
				<form:input path="nickname" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="200" class="input-xlarge " autocomplete="off"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱认证：</label>
			<div class="controls">
				<form:select path="emailFlag">
					<form:options items="${fns:getDictList('dyClient_email_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<input id="newPayPassword" name="newPayPassword" type="password" value="" maxlength="50" minlength="3" class="${empty dyClient.id?'required':''}" autocomplete="off"/>
				<c:if test="${empty dyClient.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
				<c:if test="${not empty dyClient.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPayPassword" autocomplete="off"/>
				<c:if test="${empty dyClient.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机：</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">QQ号：</label>
			<div class="controls">
				<form:input path="qq" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信号：</label>
			<div class="controls">
				<form:input path="wx" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<c:if test="${ not empty dyClient.id}">
			<div class="control-group">
				<label class="control-label">账户总额：</label>
				<div class="controls">
					<form:input path="" name="accountBalance" value="${dyClient.dyFinance.accountBalance}" readonly="true" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">冻结金额：</label>
				<div class="controls">
					<form:input path="" name="freezeBalance" value="${dyClient.dyFinance.freezeBalance}" readonly="true" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">账户余额：</label>
				<div class="controls">
					<form:input path="" name="laveBalance" value="${dyClient.dyFinance.accountBalance - dyClient.dyFinance.freezeBalance}" readonly="true" class="input-xlarge "/>
				</div>
			</div>
		</c:if>
		<div class="control-group" hidden="true">
			<label class="control-label">会员等级：</label>
			<div class="controls">
				<form:input path="vip" htmlEscape="false" maxlength="11" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">免除保证金：</label>
			<div class="controls">
				<form:select path="avoidDeposit">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">“是”代表出价、提交域名时不需要付保证金，“否”则表示需要付保证金</span>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">封号标记：</label>
			<div class="controls">
				<form:select path="sealFlag">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">“是”代表弃用此号，“否”则表示此号可以使用</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份认证：</label>
			<div class="controls">
				<form:select path="authenticationMark">
					<form:options items="${fns:getDictList('dyClient_authentication_mark')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证号码：</label>
			<div class="controls">
				<form:input id="IDcardNumberId" path="iDcardNumber" htmlEscape="false" maxlength="18" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行卡号：</label>
			<div class="controls">
				<form:input path="defaultIncomeExpense" htmlEscape="false" maxlength="22" minlength="15" class="input-xlarge digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证正面照片：</label>
			<div class="controls">
				<form:hidden id="nameImage1" path="authenticationPositiveImageUrl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage1" type="images" uploadPath="/authenticationPositiveImageUrl" selectMultiple="false" maxWidth="100" maxHeight="100"/>
				<input class="btn" type="button" value="预览" onclick="preView('nameImage1')">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">身份证反面照片：</label>
			<div class="controls">
				<form:hidden id="nameImage2" path="authenticationNegativeImageUrl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="nameImage2" type="images" uploadPath="/authenticationNegativeImageUrl" selectMultiple="false" maxWidth="100" maxHeight="100"/>
				<input class="btn" type="button" value="预览" onclick="preView('nameImage2')">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:dy:dyClient:edit">
				<input id="btnSubmit" class="btn btn-primary" type="button" onclick="checkValue()" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>