<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>常量管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
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
		/**检查键值是否合法*/
		function checkValue(){
			var value = $("#valueId").val();
			var type = $("#typeId").val();
			if(type == "dy_constant_deal_end_time" || type == "dy_constant_deal_start_time"){
				/*每日截拍时间段(开始)、每日截拍时间段(结束)*/
				if(!checkDate(value)){
					top.$.jBox.error("时间格式不对");
					return false;
				}else{
					$("#inputForm").submit();
					return true;
				}
			}
			if(isNaN(value)){
				top.$.jBox.error("必须输入数字");
				return false;
			}
			var valueNum = Number(value);
			if(type == "dy_constant_bonus_percent_platform" || type == "dy_constant_bonus_percent_share" || 
				type == "dy_constant_bonus_second_limit"){
				/*平台佣金比率(百分比)、分享佣金比率(百分比)、次高价红包上限(百分比)*/
				if(valueNum <=0 || valueNum >= 100){
					top.$.jBox.error("百分比必须在（0,100）之间");
					return false;
				}
			}else if(type == "dy_constant_operate_limit_time_confirm" || type == "dy_constant_operate_limit_time_receive" ||
					type == "dy_constant_operate_limit_time_transfer" || type == "dy_constant_operate_limit_time_pay"){
				/*经纪人确认完成交易限时(天)、确认收到域名操作限时(天)、转移域名操作限时(天)、付款操作限时(天)*/
				if(!isNum(valueNum) || valueNum <= 0  || valueNum >= 365){
					top.$.jBox.error("操作限时必须在（0,366）之间");
					return false;
				}
			}else if(type == "dy_constant_deal_number" || type == "dy_constant_sell_deposit" ||
					type == "dy_constant_endtime_delay"){
				/*卖家保证金(元)、每日截拍域名总数、截拍时间延时(分钟)*/
				if(!isNum(valueNum) || valueNum <= 0){
					top.$.jBox.error("必须为正整数");
					return false;
				}
			}else if(type == "dy_constant_sort_orderby"){
				/*拍卖列表排序规则*/
				if( valueNum != 1 && valueNum != 2){
						top.$.jBox.error("必须填 1 或者 2");
						return false;
				}
			}else if(type == "dy_constant_sharebonus_enable"){
				/*分享红包功能*/
				if( valueNum != 1 && valueNum != 0){
						top.$.jBox.error("必须填 1 或者 0");
						return false;
				}
			}
			$("#inputForm").submit();
			return true;
		}
		/**检查是不是为正整数*/
		function isNum(num){
			var re = /^(\+|-)?\d+$/;
			if (!re.test(num)){
				return false;
			}else{
				return true;
			}
		}
		/***/
		function checkDate(time){
		    var regTime = /^([0-2][0-9]):([0-5][0-9]):([0-5][0-9])$/;
		    var result = false;
		    if (regTime.test(time)) {
		        if ((parseInt(RegExp.$1) < 24) && (parseInt(RegExp.$2) < 60) && (parseInt(RegExp.$3) < 60)) {
		            result = true;
		        }
		    }
		 return result;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dict/constant">常量列表</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dict" action="${ctx}/sys/dict/constantSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">键值:</label>
			<div class="controls">
				<form:input id="valueId" path="value" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标签:</label>
			<div class="controls">
				<form:input path="label" readonly="true" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:input id="typeId" path="type" readonly="true" htmlEscape="false" maxlength="50" class="required abc"/>
				<span class="help-inline">若非管理员登录，必须以"dy"开头</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述:</label>
			<div class="controls">
				<form:input path="description" readonly="true" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="sort" readonly="true" htmlEscape="false" maxlength="11" class="required digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" readonly="true" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:dict:edit">
				<input id="btnSubmit" class="btn btn-primary" type="button" onclick="checkValue()"  value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>