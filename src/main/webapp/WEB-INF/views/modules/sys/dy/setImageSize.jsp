<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片压缩</title>
	<meta name="decorator" content="default"/>
</head>
<body >
	<form:form id="inputForm" action="${ctx}/sys/dy/dyDomainname/updateImageSize" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		<div hidden="true" style="margin-left:auto;margin-right:auto"> 
			<input  name="imagePath" value="${image}"/>
		</div>	
		<div class="">
			<img src="${image}" <c:if test="${imageWitdh*1.0 > 200}">style="height: 200px"</c:if>>
			<p>原图片宽度：${imageWitdh} px &nbsp;&nbsp;&nbsp;&nbsp;高度：${imageHeight} px</p>
		</div>
		<div>
			压缩后宽度：<input name="width" value="" maxlength="20" size="3" class=" digits required"/>&nbsp;px
		</div>
		<div>
			压缩后高度：<input name="height" value="" maxlength="20" class=" digits required"/>&nbsp;px
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="submit" value="保 存"/>&nbsp; 
		</div>
	</form:form>
</body>
</html>