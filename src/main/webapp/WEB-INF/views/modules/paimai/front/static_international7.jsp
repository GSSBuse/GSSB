<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en-US">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/views/include/frontHead1.jsp"%>
<link rel="stylesheet" href="${ctxStatic}/front/css1/staticPage.css"
	type="text/css" />
</head>
<script type="text/javascript"
	src="${ctxStatic }/front/js/Articlesold.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/gbsold.js"></script>
<body ms-controller="soldarticles">
	<%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
	<div class="about">
		<div class="container">
			<h1>商标注册</h1>
		</div>
	</div>
	<!-- Start of Page Container -->
	<div class="page-container">
		<div class="container">
			<div class="row">

				<!-- start of page content -->

				<div style="width: 800px; margin: 0 auto;">

					<div class="divtit">帮助中心</div>
					<h2 style="text-align: center; margin-top: 10px;">国际商标注册的方式</h2>
					<div
						style="border-bottom: 1px solid #eee; text-align: center; padding: 5px 0px;">发布时间:2014-06-24
						23:58:05</div>
					<div>
						<p></p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">根据《马德里协定》的要求，商标国际注册应通过原属国的注册当局向国际局提出，商标国际注册申请必须采用共同细则所规定的格式提出，根据共同细则第2条的规定，与国际局之间的通信应按照WIPO总干事根据共同细则第41条的规定所发布的“行政指令”（admininistrative
							instruction）的要求进行。2005年1月1日生效的行政指令对于提出申请的方式作出了明确的规定。据行政指令第6条规则的规定，有两种可以接受的方式。</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							<strong style="margin: 0px; padding: 0px;"> 1.书面方式</strong>
						</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							行政指令第6条a款规定，除行政指令第11条a款另有规定者外，申请应以打字机或其他机器打印的书面提交防卫有效。</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							共同细则规定允许用电话传真和电报或用户电报来传递除了国际申请之外的其他文件或通知。</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							<strong style="margin: 0px; padding: 0px;">2.电子形式</strong>
						</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							根据行政指令第11条a款的规定，在国际局和有关原属国的注册当局之间的通信，包括国际申请的提交，可通过国际局与有关注册当局同一的方式使用电子手段。</p>
						<p></p>
					</div>
				</div>


				<!-- end of page content -->



			</div>
		</div>
	</div>
	<!-- End of Page Container -->

	<!-- Start of Footer -->

	<%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
	<!-- End of Footer -->

	<a href="#top" id="scroll-top"></a>
	<div id="sold-dialog-bg"
		style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="sold-dialog" ms-controller="sold-dialog"
		style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-dialog2"
			style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		<form id="domainform" action="${ctx }/index1.html" method="post"
			ms-widget="validation" style="padding: 20px 30px; margin: 0;">
			<h1 class="post-title">
				<a href="#">我要卖标</a>
			</h1>
			<p class="comment-notes">请输入您需要发布的信息。专业顾问人工查询，结果分析更准确！</p>

			<div>
				<label for="author">商标名称 *</label> <input class="span4" type="text"
					name="title" id="title"
					ms-duplex-required="datas.domainInfo2.title"
					onFocus="this.value = '';" value="" size="22">
			</div>

			<div>
				<label for="email">联系电话 *</label> <input class="span4" type="text"
					name="mobile" id="mobile"
					ms-duplex-required="datas.domainInfo2.mobile"
					onFocus="this.value = '';" value="" size="22">
			</div>

			<div>
				<label for="url">联系人 *</label> <input class="span4" type="text"
					name="linkman" id="linkman"
					ms-duplex-required="datas.domainInfo2.linkman"
					onFocus="this.value = '';" value="" size="22">
			</div>
			<div class="payment-sendbtns">
				<input class="btn" name="submit" type="submit" id="submit02"
					value="提交查询">
			</div>
		</form>
	</div>
	<script type="text/javascript">
	<!--我要卖标弹出框js -->
		function show2() {
			document.getElementById("sold-dialog").style.display = "block";
			document.getElementById("sold-dialog-bg").style.display = 'block';
		}
		function hide() {
			document.getElementById("sold-dialog").style.display = "none";
			document.getElementById("sold-dialog-bg").style.display = 'none';
		}
		// 点击弹窗背景关闭当前弹窗
		$('#sold-dialog-bg').click(function() {
			$('#sold-dialog,#sold-dialog-bg').hide();
		});
		// 点击弹窗的关闭按钮关闭当前弹窗
		$('#close-dialog2').click(function() {
			$('#sold-dialog,#sold-dialog-bg').hide();
		});
	</script>



</body>
</html>
