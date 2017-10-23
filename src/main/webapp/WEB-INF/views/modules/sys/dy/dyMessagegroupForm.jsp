<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>群发消息列表管理</title>
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
		function setTreeSelect(r,index){
			var type = r.value;
			var id = "urlNameId"+index+"Id";
			var urlIdTemp = document.getElementById("urlNameId"+index+"Id").value;
			var urlNameTemp = document.getElementById("urlNameId"+index+"Name").value;
			$.get("${ctx}/sys/dy/dyMessagegroup/treeSelect", {index : index , type : type ,urlIdTemp : urlIdTemp ,urlNameTemp : urlNameTemp},
					function(res) {
						$(r).parent("td").next("td").empty().append($(res)[1]).append($(res)[3]);
					}
				);
		}
		function clearInput(r){
			$("#tr"+r+" input:lt(8)").val("");
			$("#tr"+r+" a:eq(2)").click();
			$("#tr"+r).css("display","none");
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dy/dyMessagegroup/">群发消息列表</a></li>
		<li class="active"><a href="${ctx}/sys/dy/dyMessagegroup/form?id=${dyMessagegroup.id}">群发消息列表<shiro:hasPermission name="sys:dy:dyMessagegroup:edit">${not empty dyMessagegroup.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:dy:dyMessagegroup:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dyMessagegroup" action="${ctx}/sys/dy/dyMessagegroup/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">图文消息标题：</label>
			<div class="controls">
				<form:input path="title"  htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">定时发送：</label>
			<div class="controls">
				<form:select path="type">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> “是”代表定时发送，“否”则表示立即发送</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发送时间：</label>
			<div class="controls">
				<input name="sendTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dyMessagegroup.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline">若定时发送为true，则设定发送时间</span>
			</div>
		</div>
		<c:if test="${not empty dyMessagegroup.id}">
			<div class="control-group">
				<label class="control-label">发送状态：</label>
				<div class="controls">
					<form:hidden path="status"  htmlEscape="false"  class="input-xlarge "/>
					<c:if test="${dyMessagegroup.status eq '1'}">已发送</c:if>
					<c:if test="${dyMessagegroup.status eq '0'}">未发送</c:if>
				</div>
			</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">是否群发：</label>
			<div class="controls">
				<form:select path="sendToAll">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> “是”代表群发，“否”则表示关联发送</span>
			</div>
		</div>
		<div class="control-group" >
			<label class="control-label">消息列表：</label>
			<div class="controls">
				<!--<form:input path="messageList" htmlEscape="false" maxlength="700" class="input-xlarge "/>-->
				<table id="contentTable" class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>标题</th>
							<th>描述</th>
							<th>封面</th>
							<th>链接类型</th>
							<th>域名/文章</th>
							<th>操作</th>
						</tr>
					</thead>
					
		
					<tbody>
					<c:forEach items="${dyMessagegroup.dyMessageList}" var="dyMessage" varStatus="status" >
						<tr id="tr${status.index}">
							<td>
									<form:hidden path="dyMessageList[${status.index}].id"/>
									<form:input path="dyMessageList[${status.index}].title" value="${dyMessage.title}" onchange="" maxlength="255"/>
							</td>
							<td>
									<form:input path="dyMessageList[${status.index}].description" value="${dyMessage.description}" maxlength="255"/>
							</td>
							<td>
									<form:hidden id="nameImage${status.index}" path="dyMessageList[${status.index}].picture" value="${dyMessage.picture}" htmlEscape="false"/>
									<sys:ckfinder input="nameImage${status.index}" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
							</td>
							<td>
									<form:select id="urlType${status.index}" path="dyMessageList[${status.index}].urlType" value="${dyMessage.urlType}" onchange="setTreeSelect(this,${status.index})">
										<form:options items="${fns:getDictList('dy_url_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
									</form:select>
							</td>
							<td>	
									<c:if test="${'0' eq dyMessage.urlType or empty dyMessage.urlType}">
										<sys:treeselect id="urlNameId${status.index}" name="dyMessageList[${status.index}].urlId" value="${dyMessage.urlId}" labelName="urlName${status.index}" labelValue="${dyMessage.urlName}"
												title="文章列表" url="/sys/dy/dyArticle/selectList"/>
									</c:if>
									<c:if test="${'1' eq dyMessage.urlType}">
										<sys:treeselect id="urlNameId${status.index}" name="dyMessageList[${status.index}].urlId" value="${dyMessage.urlId}" labelName="urlName${status.index}" labelValue="${dyMessage.urlName}"
											title="域名列表" url="/sys/dy/dyDomainname/selectList"/>
									</c:if>
							</td>
							<td>
								<input type="button" value="清空" onclick="clearInput(${status.index})"/>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:dy:dyMessagegroup:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>