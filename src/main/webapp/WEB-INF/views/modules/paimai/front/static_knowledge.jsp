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
						<h2 style="text-align: center; margin-top: 10px;">国商所知识产权托管</h2>
						<p>国商知识产权托管是针对中外企业知识产权事务的首家具有中国特色的整体管理业务，包括制定知识产权管理制度、战略规划、执行方案、搜集情报、知识产权评估及质押融资使用、知识产权变现、知识产权侵权保护和维权等职责，是一种与企业互动的行为方式。

							通过“知识产权托管”服务，企业可将知识产权工作全部交由“国商知识产权托管项目组”完成，在节约人力、物力成本的前提下，能使自身的知识产权业务更加专业化。我们会根据客户在知识产权管理方面存在的问题，从企业实际管理的角度出发，为企业量身打造适合企业发展的知识产权整体性的战略规划，提出相应的知识产权发展目标、重点和任务，并给出相应的发展措施，为企业保护自身权益、提高市场占有率、增强市场竞争力、加强自主创新能力提供专业支持。</p>
						<img src="http://www.shangbiaozongju.com/images/360.jpg"
							style="width: 680px;">
						<h2>知识产权托管服务包括：</h2>
						<ul style="font-size: 13px;">
							<li>协助贵司制定企业知识产权管理制度；</li>
							<li>制定企业知识产权战略；</li>
							<li>企业创新技术管理；</li>
							<li>品牌战略管理；</li>
							<li>国外新技术检索与应用；</li>
							<li>国内竞争对手技术分析；</li>
							<li>与企业的相互培训；</li>
							<li>贵司现有知识产权的监控、归纳、整理、授权、许可等；</li>
							<li>知识产权情报收集与分析；</li>
							<li>商标、专利、著作权、域名的发掘和申请；</li>
							<li>商业秘密、技术秘密的管理。</li>
						</ul>
						<img src="http://www.shangbiaozongju.com/images/zscq5.jpg"
							style="width: 680px;">
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
