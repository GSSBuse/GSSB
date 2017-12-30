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

				<div
					style="width: 800px; margin: 0 auto; border: 1px solid #ddd; background: url('http://www.shangbiaozongju.com/images/111.jpg') no-repeat 0 0; overflow: hidden;">
					<div style="text-align: center; padding-top: 20px;">
						<h4 style="font-size: 20px;">商标查询是提高商标注册成功率的有效途径（*为必填项）</h4>
						<br> <br>
						<div>
							<label><span class="labeltitle">商标名称：</span><input
								class="input" name="name" maxlength="100" value=""
								placeholder="请输入查询名称"></label>*
						</div>
						<div>
							<label><span class="labeltitle">商品/服务：</span><input
								class="input" name="sname" maxlength="100" value=""
								placeholder="请输入查询名称"></label>*
						</div>
						<div>
							<label><span class="labeltitle">联 系 人 ：</span><input
								class="input" maxlength="10" name="uname" placeholder="联系人姓名"></label>*
						</div>
						<div>
							<label><span class="labeltitle">联系电话：</span><input
								class="input" name="tel" placeholder="联系电话"></label>*
						</div>
						<div>
							<label><span class="labeltitle">QQ/邮箱：</span><input
								class="input" name="qq" placeholder="qq/email"></label>*
						</div>
						<div style="margin: 10px 0px;">
							<input type="button" id="subbtn"
								style="text-align: center; height: 47px; border: none; width: 200px; background: #0D53C0; font-size: 20px; color: #fff; cursor: pointer;"
								value="查询">
						</div>

					</div>
					<input type="hidden" name="sst" value="4">
					<div id="mov" style="margin-top: 80px;">
						<div style="width: 400px; float: left;">
							<marquee id="a" onmouseover="a.stop()" style="color: #000;"
								onmouseout="a.start()" scrollamount="2" direction="up">
								<ul
									style="width: 100%; text-align: center; font-size: 16px; line-height: 30px; font-family: 微软雅黑;">
									<li>王**,2014-05-18 22:33:06进行了在线查询</li>
									<li>赵**,2014-05-18 22:33:06进行了在线查询</li>
									<li>王**,2014-05-18 22:33:07进行了在线查询</li>
									<li>赵**,2014-05-18 22:33:07进行了在线查询</li>
								</ul>
							</marquee>
						</div>
						<img src="http://www.shangbiaozongju.com/static/imgs/lc.png"
							style="width: 375px; float: left;">
					</div>


					<div class="help" style="margin-left:20px;">
						<h1>友情提示</h1>
						<ul>
							<li><span class="ll1">1</span>国商知识产权网上提供的商标查询服务是免费的，
								您无需为该服务支付任何费用。网站上所填写的资料只用于方便为您服务，不用于其它用途。点击“我接受”即表示您同意接受服务条款以及隐私政策内容；点击查看&gt;隐私协议</li>
							<br><li><span class="ll1">2</span>国商知识产权提供的商标查询服务的范围仅限于国家商标局已公开的商标数据，不含从查询之日起前八个月内申请的在先权利信息；不含处于评审状态的在先权利信息；不含《中华人民共和国商标法》第10、11条所规定的内容。</li>
							<br><li><span class="ll1">3</span>本公司提供查询结果及建议仅供参考，不能作为商标注册的法律依据。</li>
							<br><li><span class="ll1">4</span>我们将对您的查询内容进行保密，严格保守您的商业秘密。</li>
						</ul>
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
