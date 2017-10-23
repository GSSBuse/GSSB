<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>应用管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
	</script>
	<style type="text/css">
		.img-circle{-webkit-border-radius:500px;-moz-border-radius:500px;border-radius:500px}
		.app-circle{
			width: 80px;height: 80px;
			-webkit-border-radius:500px;-moz-border-radius:500px;border-radius:500px;
			line-height:20px;border:1px solid #ddd;-webkit-transition:all .2s ease-in-out;-moz-transition:all .2s ease-in-out;-o-transition:all .2s ease-in-out;transition:all .2s ease-in-out;
		}
		.app-border{
			-webkit-border-radius:500px;-moz-border-radius:500px;border-radius:500px;
			display:block;/*padding:4px*/;line-height:20px;border:1px solid #ddd;-webkit-transition:all .2s ease-in-out;-moz-transition:all .2s ease-in-out;-o-transition:all .2s ease-in-out;transition:all .2s ease-in-out;
		}
		a.app-border:hover,a.app-border:focus{border-color:#08c;}
		.plus{
			background: url(https://res.wx.qq.com/mmocbiz/zh_CN/tmt/pc/dist/img/dev/icon_appstore_4f97141e.png) 0 0 no-repeat;
			display: inline-block;
			width: 100px;height: 100px
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/app/appManagementList">应用管理</a></li>
	</ul><br/>
	<sys:message content="${message}"/>
	<div class="container-fluid">
		<ul class="thumbnails">
			<c:forEach items="${agentList}" var="agent">
				<li class="span1.5">
					<div class="thumbnail text-center" style="border: none">
						<a href="${ctx}/app/appManagement/${agent.agentid}" class="app-border">
							<img data-src="holder.js/140x140" src="${agent.round_logo_url}" alt="${agent.name}" title="${agent.name}" class="app-circle">
						</a>
						<h5>${agent.name}</h5>
					</div>
				</li>
			</c:forEach>
			<li class="span1.5">
				<div class="thumbnail text-center" style="border: none">
					<div style="padding: 2px;">
					<a href="${ctx}/app/appManagement/create" class="plus" title="新增"></a>
					</div>
				</div>
			</li>
		</ul>
<%-- 		<div class="row">
			<c:forEach items="${agentList}" var="agent">
				<div class="span2" >
					<a href="${ctx}/app/appManagement/${agent.agentid}" class="thumbnail">
						<img data-src="holder.js/140x140" src="${agent.round_logo_url}" alt="${agent.name}" title="${agent.name}">
					</a>
				</div>
			</c:forEach>
		</div> --%>
	</div>
</body>
</html>