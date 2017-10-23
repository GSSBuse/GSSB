<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE >
<html>
<head>
	<meta charset="utf-8">
	<title>Test APP</title>
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<%@include file="/WEB-INF/views/include/jingle.jsp"%>
	
	 <%-- 
	<link href="${ctxStatic}/jingle/css/app.css" type="text/css" rel="stylesheet" />
	<link href="${ctxStatic}/jingle/css/Jingle.css" type="text/css" rel="stylesheet" />
	 --%>
	<script type="text/javascript">
		var slider = new J.Slider("#sliderId");
		Jingle.launch();
	</script>
</head>
<body>
	<div id="aside_container">
</div>

<div id="section_container">
    <section id="testApp_section" class="active">
        <header>
            <!--<nav class="left">
                <a id="btn_scan_barcode" data-icon="qrcode" href=""></a>
            </nav>-->
            <h1 class="title">TestApp</h1>
            <nav class="right">
                <a data-target="section" data-icon="info" href="#about_section"></a>
            </nav>
        </header>
        <article class="active" data-scroll="true">
            <div class="grid vertical" style="height: 100%;width:100%;">
            	<div id="sliderId" class="slider">
    				<div style="text-align: center"><img src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png"></div>
    				<div style="text-align: center"><img src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png"></div>
    				<div style="text-align: center"><img src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png"></div>
            	</div>
            	<div class="one-part grid vertical" style="background-color:#eee;color: #666;padding: 0 0 5px 5px;">
                        <div class="grid one-part">
			                <div class="col-1">col-1</div>
			                <div class="col-1">col-2</div>
                        </div>
                        <div class="grid one-part">
			                <div class="col-1">col-3</div>
			                <div class="col-1">col-4</div>
                        </div>
                    </div>
            </div>
        </article>
    </section>
</div>
</body>
</html>