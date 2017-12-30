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
				<div>
					<div style="width: 800px; margin: 0 auto;">
						<h2 style="text-align: center; margin-top: 10px;">商标注册的好处</h2>
						<div
							style="border-bottom: 1px solid #eee; text-align: center; padding: 5px 0px;">发布时间:2014-06-23
							09:39:01</div>
						<div>
							<p style="margin: 0px; padding: 0px; color: rgb(69, 69, 69); font-family: tahoma, helvetica, arial; font-size: 14px; line-height: 21px;">
							商标注册是商标使用人取得商标专用权的前提和条件，只有经核准注册的商标，才受法律保护。商标注册原则是确定商标专用权的基本准则，不同的注册原则的选择，是各国立法者在这一个问题中对法律的确定性和法律的公正性二者关系进行权衡的结果。作者认为中国应适用“注册在先为主，兼顾使用”的注册原则。商标注册申请是商标使用的基础，在商标注册申请过程中，灵活地运用商标注册策略，对保护商标及商标权，开拓国内外市场有着非常重要作用。根据中国现状，建议国家工商管理部门采取必要措施促进商标注册，切实保护商标所有人的权益。<br>  商标注册的好处<br>  1、 便于消费者认牌购物。 <br>  2、 商标注册人拥有商标专用权, 受法律保护。<br>  3、 通过商标注册，可以创立品牌，抢先占领市场。<br>  4、 商标是一种无形资产，可对其价值进行评估。 <br>  5、 商标可以通过转让，许可给他人使用，或质押来转换实现其价值。 <br>  6、 商标还是办理质检、卫检、条码等的必备条件。 <br>  7、 地方各级工商局通过对商标的管理来监督商品和服务的质量。
							</p>

						</div>
					</div>
				</div>
				<br>

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
