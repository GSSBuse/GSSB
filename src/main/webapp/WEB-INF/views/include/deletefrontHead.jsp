
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="国商局,商标,版权,专利,商标查询,版权登记,专利注册" />
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

<link rel="stylesheet" type="text/css" href="${ctxStatic}/front/css/base.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/front/css/buttons.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/front/css/table.css">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/front/css/center.css" >
<link rel="stylesheet" type="text/css" href="${ctxStatic}/front/css/override.css" >
<link rel="stylesheet" type="text/css" href="${ctxStatic}/front/css/navigation.css" >
<!-- Custom Theme files -->
<link href="${ctxStatic }/front/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="${ctxStatic }/front/css/style.css" rel='stylesheet' type='text/css' />
<!-- webfonts -->
<!-- <link href='http://fonts.googleapis.com/css?family=Open+Sans:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'> -->
<!-- Add fancyBox main JS and CSS files -->
<link href="${ctxStatic }/front/css/popup.css" rel="stylesheet" type="text/css">
<link href="${ctxStatic }/front/css/flexslider.css" rel='stylesheet' type='text/css' />

<script type="text/javascript" src="${ctxStatic }/front/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/jquery.magnific-popup.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/nav.js"></script>
<script defer src="${ctxStatic }/front/js/jquery.flexslider.js"></script>

<%-- <script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script> --%>
<script type="text/javascript" src="${ctxStatic}/frontend-jam/static/vendors/avalon/avalon.js"></script>
<script type="text/javascript" src="${ctxStatic}/frontend-jam/static/js/utils.js"></script>

<script type="text/javascript">
	var ctx = '${ctx}';
</script>
<c:set var="currentClient" value="${fnc:getCurrentDyClient()}"/>
<script>
    $(document).ready(function() {
        $('.popup-with-zoom-anim').magnificPopup({
            type: 'inline',
            fixedContentPos: false,
            fixedBgPos: true,
            overflowY: 'auto',
            closeBtnInside: true,
            preloader: false,
            midClick: true,
            removalDelay: 300,
            mainClass: 'my-mfp-zoom-in'
    });
});
</script>

<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Blue/jbox.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.js" type="text/javascript"></script>        
