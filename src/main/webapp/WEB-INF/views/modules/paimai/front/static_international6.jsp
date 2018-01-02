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
			<h1>涉外商标注册的必要性和好处</h1>
		</div>
	</div>
	<!-- Start of Page Container -->
	<div class="page-container">
		<div class="container">
			<div class="row">

				<!-- start of page content -->

				<div style="width: 800px; margin: 0 auto;">
					<div class="divtit">帮助中心</div>
					<h2 style="text-align: center; margin-top: 10px;">涉外商标注册的必要性和好处</h2>
					<div
						style="border-bottom: 1px solid #eee; text-align: center; padding: 5px 0px;">发布时间:2014-06-23
						10:55:26</div>
					<div>
						<p></p>
						<div class="exp-content-block"
							style="color: rgb(51, 51, 51); font-size: 16px; line-height: 28px; margin-top: 40px; font-family: 'Microsoft Yahei', 微软雅黑, arial, 宋体, sans-serif;">
							<div class="exp-content-body exp-content-blog-body"
								style="text-align: justify; margin-top: 40px;">
								<div class="exp-content-listblock" style="position: relative;">
									<div class="content-listblock-text">
										<p style="margin: 20px 0px; padding: 0px;">1、商标权利是有地域性限制的，即：在一个国家/地区获准的商标权，仅在该国/该地区有效。如：“五粮液”商标在国内注册后，仍需到韩国、智利等销售国分别去注册保护，才能在这些国家均获得法律保护。</p>
										<p style="margin: 20px 0px; padding: 0px;">2、避免长期使用的商标被他人在境外抢先注册（包括竞争对手、海外经销商或其他利害关系人所抢注），不得不以高昂代价与之合作或被迫重新打造新品牌。
											如：“今麦郎”商标2005年12月在国内申请后未及时到德国注册，被一德国公司于2006年2月在德国抢注；若“今麦郎”产品在德国销售则意味着商标侵权，其产品要进入德国市场，就不能使用“今麦郎”商标。</p>
										<p style="margin: 20px 0px; padding: 0px;">
											3、避免在境外无意或被动侵犯他人商标权利，导致支付巨额赔偿费。
											如：中国电子信息产业集团2009年在国内第9类注册了“长城GRAET&nbsp;<br>
											WALL”商标，香港一公司在1990年已于德国在第9类上注册了“长城GRAET&nbsp;<br>
											WALL”商标；若中国电子信息产业集团商标在国内注册后便直接出口产品到德国，无意中便在德国构成了商标使用侵权，等待它的将是侵权诉讼和侵权赔偿。
										</p>
										<p style="margin: 20px 0px; padding: 0px;">4、大部分国家/地区商标注册的周期较长，提前进行涉外商标注册有利于出口保护和市场战略的实施。以避免产生产品急需出口，但却无国际注册商标可用的尴尬局面。5、树立品牌形象，提升企业品牌价值，塑造国际品牌。如：“海尔Haier”商标，在进行国际注册后就可以加以宣传，赢得消费者更多信赖。</p>
										<p style="margin: 20px 0px; padding: 0px;">6、国家对出口企业在境外注册商标有专项资金补贴和其他一些优惠政策。如：国家财政部、商务部联合提供的中小企业国际市场开拓资金。</p>
										<div>&nbsp;</div>
									</div>
								</div>
							</div>
						</div>
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
