<%@ page contentType="text/html;charset=UTF-8" %>

<!-- META TAGS -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="国标商标" />

<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="fnc" uri="/WEB-INF/tlds/fnc.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="act" tagdir="/WEB-INF/tags/act" %>
<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/cms" %>

<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getFrontPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<!-- Style Sheet-->
<link rel="stylesheet" href="${ctxStatic}/front/css1/style.css"/>
<link rel='stylesheet' id='bootstrap-css-css'  href='${ctxStatic}/front/css1/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='responsive-css-css'  href='${ctxStatic}/front/css1/responsive5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='pretty-photo-css-css'  href='${ctxStatic}/front/js1/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
<link rel='stylesheet' id='main-css-css'  href='${ctxStatic}/front/css1/main5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='custom-css-css'  href='${ctxStatic}/front/css1/custom5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='custom-css-css'  href='${ctxStatic}/front/css1/im.css?ver=1.0' type='text/css' media='all' />

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="${ctxStatic}/front/js1/html5.js"></script>
<![endif]-->

<!-- Custom Theme files -->
<!-- webfonts -->
<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'> -->
<!-- Add fancyBox main JS and CSS files -->
<link href="${ctxStatic }/front/css/popup.css" rel="stylesheet" type="text/css">
<link href="${ctxStatic }/front/css/flexslider.css" rel='stylesheet' type='text/css' />

<!-- script -->
<script type='text/javascript' src='${ctxStatic}/front/js1/jquery-1.8.3.min.js'></script>
<script type='text/javascript' src='${ctxStatic}/front/js1/jquery.easing.1.3.js'></script>
<%-- <script type='text/javascript' src='${ctxStatic}/front/js1/prettyphoto/jquery.prettyPhoto.js'></script> --%>
<%-- <script type='text/javascript' src='${ctxStatic}/front/js1/jflickrfeed.js'></script> --%>
<script type='text/javascript' src='${ctxStatic}/front/js1/jquery.liveSearch.js'></script>
<script type='text/javascript' src='${ctxStatic}/front/js1/jquery.form.js'></script>
<script type='text/javascript' src='${ctxStatic}/front/js1/jquery.validate.min.js'></script>
<script type='text/javascript' src='${ctxStatic}/front/js1/custom.js'></script>

<script type="text/javascript" src="${ctxStatic}/frontend-jam/static/vendors/avalon/avalon.js"></script>
<script type="text/javascript" src="${ctxStatic}/frontend-jam/static/js/utils.js"></script>

<script type="text/javascript">
	var ctx = '${ctx}';
</script>       

<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Blue/jbox.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.js" type="text/javascript"></script>
    