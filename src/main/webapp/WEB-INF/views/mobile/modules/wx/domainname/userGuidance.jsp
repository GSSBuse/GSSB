<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<!-- <title>${article.title}</title> -->
 <title>米乐拍卖</title>
<script type="text/javascript">
	var ctx = "${pageContext.request.contextPath}";
</script>
<link href="${pageContext.request.contextPath}/static/frontend-jam/static/css/custom.css" rel="stylesheet">
</head>
<body>
	<div id="article">
		<div id="article-title">
			${article.title}
		</div>
		<div id="article-time" class="m-t-5">
			<span class="time"><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/> 米乐拍卖</span>
			<a href="${pageContext.request.contextPath}/domainname/error.html">米乐拍卖</a>
		</div>
		<div id="article-content" class="m-t-5">
			${article.content}
		</div>
	</div>
</body>
</html>