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
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/frontend-jam/static/vendors/avalon/avalon.js"></script>
<script type="text/javascript" src="${ctxStatic}/frontend-jam/static/js/utils.js"></script>

<script type="text/javascript" src="${ctxStatic }/front/js/login.js"></script>

<script type="text/javascript">
	var ctx = '${ctx}';
</script>
<c:set var="currentClient" value="${fnc:getCurrentDyClient()}"/>

<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Blue/jbox.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.js" type="text/javascript"></script>
