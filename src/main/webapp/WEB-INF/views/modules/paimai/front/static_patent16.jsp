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
				<div class="span8 page-content">

					<div style="width: 700px; margin: 0 auto;">
						<div class="divtit">帮助中心</div>
						<h2 style="text-align: center; margin-top: 10px;">专利申请的风险</h2>
						<div
							style="border-bottom: 1px solid #eee; text-align: center; padding: 5px 0px;">发布时间:2014-06-23
							10:49:28</div>
						<div>
							<p></p>
							<p
								style="margin: 20px 0px; padding: 0px; color: rgb(51, 51, 51); font-family: 'Microsoft Yahei', 微软雅黑, arial, 宋体, sans-serif; font-size: 16px; line-height: 28px; text-align: justify;">
								<span style="font-size: 15px;">专利申请是有风险的，并不是在交钱后就一定能够授权。</span>
							</p>
							<p
								style="margin: 20px 0px; padding: 0px; color: rgb(51, 51, 51); font-family: 'Microsoft Yahei', 微软雅黑, arial, 宋体, sans-serif; font-size: 16px; line-height: 28px; text-align: justify;">
								<span style="font-size: 15px;">
									一般而言，外观设计和实用新型专利申请由于只需通过初步审查即可获得授权，因而授权失败的风险较小，目前我公司代理的外观设计和实用新型专利申请全部授权，授权率达到百分之百；而发明专利申请需要通过初步审查和实质审查才能获得授权，因而授权失败的风险较大，发明专利申请的授权风险主要来自于以下三个方面：</span>
							</p>
							<p
								style="margin: 20px 0px; padding: 0px; color: rgb(51, 51, 51); font-family: 'Microsoft Yahei', 微软雅黑, arial, 宋体, sans-serif; font-size: 16px; line-height: 28px; text-align: justify;">
								<span style="font-size: 15px;">
									1、专利申请存在最长18个月的检索盲期。根据《专利法》第三十四条的规定，正常情况下，发明专利申请自申请之日起最晚18个月才会自动公开，即使是应申请人要求提前公开，也需要约6个月才会公开，而专利申请在公开之前的保密期内任何人都无法通过公开途径检索到其信息。</span>
							</p>
							<p
								style="margin: 20px 0px; padding: 0px; color: rgb(51, 51, 51); font-family: 'Microsoft Yahei', 微软雅黑, arial, 宋体, sans-serif; font-size: 16px; line-height: 28px; text-align: justify;">
								<span style="font-size: 15px;">
									2、专利审查具有一定的主观性，专利能否授权取决于审查员的具体判断。一项专利是否符合授权要求，特别是是否具备创造性，在多数情况下最终取决于审查员的主观判断。</span>
							</p>
							<p
								style="margin: 20px 0px; padding: 0px; color: rgb(51, 51, 51); font-family: 'Microsoft Yahei', 微软雅黑, arial, 宋体, sans-serif; font-size: 16px; line-height: 28px; text-align: justify;">
								<span style="font-size: 15px;">
									3、专利代理机构的失误（未进行检索），不负责任（漏报、错报）或者无职业道德（不告知明显风险或者违约透露专利技术内容）。</span>
							</p>
							<p></p>
						</div>
					</div>
				</div>

				<!-- end of page content -->
				<aside class="span4 page-sidebar">
					<%@ include file="/WEB-INF/views/include/frontSidebar.jsp"%>
				</aside>


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
