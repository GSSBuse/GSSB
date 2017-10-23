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
</head>
<body>
	<div id="aside_container"></div>
<!-- 
<div id="section_container">
    <section id="index_section" class="active">
        <header>
            <nav class="left">
                <a id="btn_scan_barcode" data-icon="qrcode" href=""></a>
            </nav>
            <h1 class="title">Jingle V0.4</h1>
            <nav class="right">
                <a data-target="section" data-icon="info" href="#about_section"></a>
            </nav>
        </header>
        <article class="active" data-scroll="true">
            <div style="padding: 10px 0 20px;">
            <ul class="list inset demo-list">
                <li data-icon="next" data-selected="selected">
                    <span class="icon newspaper"></span>
                    <a href="#layout_section?a=1&b=2"  data-target="section">
                        <strong>layout</strong>
                        <p>基本布局</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon bars-2"></span>
                    <a href="#button_section"data-target="section">
                        <strong>buttons</strong>
                        <p>按钮</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon list"></span>
                    <a href="#list_section"data-target="section">
                        <strong>lists</strong>
                        <p>基本列表</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon bars"></span>
                    <div class="tag alizarin">new</div>
                    <a href="#chart_section"data-target="section">
                        <strong>chart</strong>
                        <p>Jingle Chart，独立的移动端图表组件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon stack"></span>
                    <a href="#form_section"data-target="section">
                        <strong>form</strong>
                        <p>表单基本组件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon grid"></span>
                    <div class="tag alizarin">new</div>
                    <a href="#grid_section"data-target="section">
                        <strong>grid</strong>
                        <p>栅格组件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon bell"></span>
                    <a href="#toast_section"data-target="section">
                        <strong>toast</strong>
                        <p>通知组件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon rocket"></span>
                    <a href="#popup_section"data-target="section">
                        <strong>popup</strong>
                        <p>弹出框组件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon ellipsis"></span>
                    <a href="#slider_section"data-target="section">
                        <strong>slider</strong>
                        <p>页面轮换组件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon arrow-down-3"></span>
                    <a href="#scroll_section"data-target="section">
                        <strong>scroll</strong>
                        <p>滚动条插件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon arrow-up-2"></span>
                    <a href="#refresh_section"data-target="section">
                        <strong>pull2refresh</strong>
                        <p>上拉/下拉刷新组件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon menu"></span>
                    <a href="#menu_section"data-target="section">
                        <strong>menu</strong>
                        <p>抽屉菜单组件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon calendar"></span>
                    <a href="#calendar_section"data-target="section">
                        <strong>calendar</strong>
                        <p>日历组件</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon bubble"></span>
                    <a href="#icon_section"data-target="section">
                        <strong>icons</strong>
                        <p>用到的字体图标</p>
                    </a>
                </li>
                <li data-icon="next" data-selected="selected">
                    <span class="icon brightness-contrast"></span>
                    <a href="#color_section"data-target="section">
                        <strong>color</strong>
                        <p>常用的一些颜色色值</p>
                    </a>
                </li>
                <li class="divider">其他</li>
                <li data-icon="next" data-selected="selected"><a href="#" data-icon="exit" id="btn_show_welcome"><strong>欢迎页</strong></a></li>
            </ul>
            </div>
        </article>
    </section>
</div> -->
	<div id="section_container">
		<section id="index_section" class="active">
			<header>
				<nav class="left">
					<a id="btn_scan_barcode" data-icon="qrcode" href=""></a>
				</nav>
				<h1 class="title">TestApp</h1>
				<nav class="right">
					<a data-target="section" data-icon="info" href="#about_section"></a>
				</nav>
			</header>
			<article class="active" data-scroll="true">
				<div class="grid vertical" style="height: 100%; width: 100%;">
					<div id="slider_test">
						<div>
							<div style="text-align: center">
								<img
									src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png"
									style="width: 300px; height: 180px">
							</div>
							<div style="text-align: center">
								<img
									src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png"
									style="width: 300px; height: 180px">
							</div>
							<div style="text-align: center">
								<img
									src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png"
									style="width: 300px; height: 180px">
							</div>
						</div>
					</div>
					<div class="one-part grid vertical"
						style="background-color: #eee; color: #666; padding: 0 0 5px 5px;">
						<div class="grid one-part">
							<div class="col-1">
								<img src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png" style="width: 300px; height: 180px">
							</div>
							<div class="col-1">
								<img src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png" style="width: 300px; height: 180px">
							</div>
						</div>
						<div class="grid one-part">
							<div class="col-1"><img src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png" style="width: 300px; height: 180px"></div>
							<div class="col-1"><img src="/jeesite/userfiles/1/images/photo/2015/07/IMG20150615151503.png" style="width: 300px; height: 180px"></div>
						</div>
					</div>
				</div>
			</article>
		</section>
	</div>
</body>
</html>