<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>授权登录</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<div data-title="授权登录成功" class="page active "	id="adminSucess">
			<section class="ui-container font-styles">
				<div class="mainContent" style="height:400px;">
					<div style="background-color: #A3BA51;width:100%;height:70%;">
						<img src="${pageContext.request.contextPath}/static/images/pymLogo.jpg" style="width: 40%;margin-top: 20%;margin-left: 30%;"></img>
					</div>
					<div style="text-align:center;line-height:120px;">
						您已授权该应用登录成功
					</div>
				</div>
			</section>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>