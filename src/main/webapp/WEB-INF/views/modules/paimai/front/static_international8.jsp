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
					<h2 style="text-align: center; margin-top: 10px;">国际商标注册的注意事项</h2>
					<div
						style="border-bottom: 1px solid #eee; text-align: center; padding: 5px 0px;">发布时间:2014-06-24
						23:59:17</div>
					<div>
						<p></p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">1
							、申请人必须具有一定的主体资格。申请人应在我国有真实有效的工商营业所；如果没有，应在我国境内有住所；如果没有住所，申请人应有我国国籍。非
							“ 马德里联盟 ”
							成员国的国民，若在我国有其合资或独资企业，可以通过商标局提出国际注册申请。另外，台湾省的法人或自然人均可通过商标局提出国际注册申请。
							而香港和澳门特别行政区的法人或自然人目前还不能通过商标局提出国际注册申请。</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							2 、申请国际注册的商标必须已经在我国启动一定的商标注册申请程序。自 2008 年 9 月 1 日 起 ，申请人指定保护的国家是纯
							“ 马德里协定 ” 成员国的，申请国际注册的商标必须是在我国已经获得注册；申请人指定保护的国家是纯 “ 马德里议定书 ”
							成员国的，或是同属“马德里协定”和“马德里议定书”的成员国，申请国际注册的商标可以是已在我国提出注册申请的商标，也可以是已经注册的商标。</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							3
							、国际注册申请应与国家基础注册或基础申请内容一致。国际注册申请人的名义应与国内申请人或注册人的名义完全一致；申请人地址应与国内申请人或注册人的地址完全一致；商标应与国内注册的商标完全相同，包括颜色完全一致；所报的商品和服务应与国内注册的商品和服务相同或者不超过国内申请或注册的商品和服务范围。如果国内申请或注册的是在不同商品或服务类别的同一商标，在申请国际注册时，可提交一份国际注册申请，将国内所报不同类别的商品或服务按类别顺序填写在该国际注册申请书上。</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							4
							、符合一定条件的可声明要求优先权。申请人在申请国际注册时，如果与国内提出的商标注册申请相隔时间未超过六个月，那么申请人在提出国际注册申请时，可要求优先权，但应提供国内《受理通知书》复印件。</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							5 、不予受理的国际注册申请 凡不符合《商标国际注册马德里协定及其议定书共同实施细则》第六条之 1 、 2 、 3
							或其中一条的，商标局不予受理该商标国际注册申请。</p>
						<p
							style="margin: 0px; padding: 0px; font-family: 微软雅黑, 宋体; font-size: 14px; line-height: 25px; color: rgb(51, 51, 51);">
							6 、国际注册的有效期满后，如想继续使用的，应当续展注册。</p>
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
