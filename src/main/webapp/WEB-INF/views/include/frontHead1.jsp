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
<link rel="stylesheet" href="${ctxStatic}/front/css1/loginstyle.css"/>
<link rel='stylesheet' id='bootstrap-css-css'  href='${ctxStatic}/front/css1/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='responsive-css-css'  href='${ctxStatic}/front/css1/responsive5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='pretty-photo-css-css'  href='${ctxStatic}/front/js1/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
<link rel='stylesheet' id='main-css-css'  href='${ctxStatic}/front/css1/main5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='custom-css-css'  href='${ctxStatic}/front/css1/custom5152.css?ver=1.0' type='text/css' media='all' />
<link rel='stylesheet' id='im-css-css'  href='${ctxStatic}/front/css1/im.css?ver=1.0' type='text/css' media='all' />

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
<script type="text/javascript" src="${ctxStatic }/front/js/jquery-1.11.1.min.js"></script>
<script defer src="${ctxStatic }/front/js/jquery.flexslider.js"></script>
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

<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101442633"  charset="utf-8"></script>

 
<style type="text/css">
*{margin: 0px;padding:0;list-style:none;}
body{font-size:12px;}
.main{width:800px;position:fixed;top:280px;
    z-index: 10011;}
*html .main{position:absolute;top:expression(eval(document.documentElement.scrollTop));margin-top:320px;}
.main2{
    background-color: #525252;
    background: rgba(82,82,82,0.67);
    width: 780px;
    height: 150px;
    /* margin: 0 auto; */
    position: relative;
    padding: 10px;
    text-align: center;
    }
.main2 ul li {
    float: left;
    margin-left: 50px;
    margin-right: 50px;
    text-align: center;    display: list-item;
}
.main2 ul li a {
    color: #fff;
    font-family: "微软雅黑", Arial;
    transition: .3s;
}
.main2 ul li a img {
    margin-top: 15px;
    height: 40px;
    margin-bottom: 10px;
}
.main2 h1 {
    font-size: 26px;
    color: #c31b1b;
    font-family: "微软雅黑", Arial;
}
.bar{width:25px;height:105px;position:absolute;right:-25px;background:url("${ctxStatic }/images/mini_bg.png") no-repeat;display:block;}
</style> 
<%-- <div class="main">
    <div class="main2"><a href="javascript:" class="bar"></a>
            <h1 align="left">您可能想知道</h1>
            <ul>
                <li><a href="${ctx }/static_trademark.html"><img src="${ctxStatic }/images/footer-1.png">
                    <p>如何收费</p>
                    </a></li>
                <li><a href="${ctx }/static_trademark1.html"><img src="${ctxStatic }/images/footer-2.png">
                    <p>注册流程</p>
                    </a></li>
                <li><a href="${ctx }/static_trademark2.html"><img src="${ctxStatic }/images/footer-3.png">
                    <p>注册周期</p>
                    </a></li>
                <li><a href="${ctx }/static_trademark3.html"><img src="${ctxStatic }/images/footer-4.png">
                    <p>申请条件</p>
                    </a></li>
                <li><a href="${ctx }/static_trademark4.html"><img src="${ctxStatic }/images/footer-5.png">
                    <p>行业分类</p>
                    </a></li>
            </ul>
    </div>
</div>
<script type="text/javascript">
$(function(){
    
    $('.main').css('left','-802px');
    
    var expanded = true;
    
    $('.bar').click(function(){
        if(expanded){
            $('.main').animate({left:'0'},500);
            $('.bar').css('background-position','-25px 0px');
        }else{
            $('.main').animate({left:'-802px'},500);
            $('.bar').css('background-position','-0px 0px');
        }
        expanded = !expanded;
    });

});
</script> --%>
