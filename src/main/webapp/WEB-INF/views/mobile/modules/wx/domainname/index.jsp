<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <!-- <title>jqmobile 样例</title> -->
 <title>米乐拍卖</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <script type="text/javascript">
        var ctx = "${pageContext.request.contextPath}";
    </script>
    <link href="${pageContext.request.contextPath}/static/frontend-jam/static/css/common.css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/frontend-jam/static/vendors/require/require.min.js" data-main="${pageContext.request.contextPath}/static/frontend-jam/static/js/require.config"></script>

</head>
<body class="ms-loader ms-loading">

<div data-role="page" id="demo-intro" ms-controller="demo-intro" data-title="Inbox" class="ms-controller">

    <div data-role="header" data-position="fixed" data-theme="b">
        <h1>样例</h1>
        <!--<a href="#demo-intro" data-rel="back" data-icon="carat-l" data-iconpos="notext">Back</a>
        <a href="#" onclick="window.location.reload()" data-icon="back" data-iconpos="notext">Refresh</a>-->
    </div><!-- /header -->

    <div role="main" class="ui-content">

        <ul id="list" data-role="listview" data-split-icon="delete">
            <li>
                <a href="button-example.html">输入及表单</a>
            </li>
            <li>
                <a href="button-example.html" ms-on-swipeleft="swipe" data-transition="slide">向左滑动</a>
            </li>
            <li>
                <a href="http://demos.jquerymobile.com/1.4.5/" rel="external">官方示例</a>
            </li>
            <li>
                <a href="http://cubiq.org/dropbox/iscroll4/examples/pull-to-refresh/" rel="external">下拉刷新示例</a>
            </li>
        </ul>

    </div><!-- /content -->
	<script type="text/javascript" data-page="index"></script>
</div><!-- /page -->



</body>
</html>