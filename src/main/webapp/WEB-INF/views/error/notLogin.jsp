<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<script type="text/javascript">
	var pageData = ${initData};
</script>
<script type="text/javascript" src="${ctxStatic }/front/js/index.js"></script>
</head>
<body class="v4 layout01 ">
	 <div id="uiWrapper">
      	<div id="uiContainer">
        
          	<div id="uiHeader">
          		<%@ include file="/WEB-INF/views/include/frontTop.jsp"%>
	          	<!-- 蓝条结束 -->
          	</div>
          	
         <!-- 头部结束 -->
         
         <div id="uiContent" class="ms-controller" ms-controller="index">
         	<div id="uiContentRSS" class="uiContent">
         		<!-- <div id="img-container" class="fLeft">
         			 <img id="moblieImg" src="../allfiles/mobile.png"></img>
         			<span>扫我关注本网站官方微信，更方便哦~</span> 
         		</div> -->
  		
         		<div class="buy_table-wrapper">
	                您尚未登录
	              <!-- 表格结束 -->
	         	</div>
	         </div>
         
	         <%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
         
     	</div>
     </div>
</body>
</html>