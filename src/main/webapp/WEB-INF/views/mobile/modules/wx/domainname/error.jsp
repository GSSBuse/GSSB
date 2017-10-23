<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<!-- <title>关注拍域名</title> -->
 <title>米乐拍卖</title>
	<%@include file="/WEB-INF/views/include/common.jsp"%>	
</head>
<body ontouchstart="" class="ms-loader ms-loading">
	<div class="page-wrapper">
		<div data-title="关注拍域名" class="page active ms-controller" ms-controller="error"	id="error">
			<section class="ui-container font-styles">
				<div class="mainContent">
					<div class="designerCard">
					<!--
						<div style="margin-top: 7%;width:100%;">
							<img src="${customerInfo.headImg}" style=" width: 100%;  border-radius: 50%; box-shadow: 0 0 0 4px rgb(205, 218, 162);">
						</div>
					-->
						<div class="text-tip-follow">
							长按二维码关注我们<br>即可参加拍卖
						</div>
					</div>
					<div id="myCode" style="text-align: center;">
						<img src="${pageContext.request.contextPath}/static/images/qrcode.jpg" style="width: 80%;margin-top: 6%;"></img>
					</div>
					<div class="text-tip">
						<div>---------温馨提示---------</div>
						<div class="grid middle">
							<div  class="ng-binding" style="width: 80%;text-align: center; margin-left: 8%;">
								如果您已关注，仍然看到此页面，请联系经纪人确认是否被封号
							</div>
						</div>
					</div>
				</div>
			</section>
			<script type="text/javascript" data-page="error"></script>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/commonjs.jsp" %>
</body>
</html>