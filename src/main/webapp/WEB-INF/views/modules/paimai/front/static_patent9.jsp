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
				<div id="list-menu" class="menuA">
					<ul style="width: 400px;">
						<li class="check"><a>业务详情</a></li>
						<li><a>流程费用</a></li>
						<li><a>国商优势</a></li>
						<li><a>成功案例</a></li>
					</ul>
				</div>
				<br>
				<div class="menuB">
					<div class="one" style="display: block;">
						<article>

							<h2>专利权利恢复</h2>
							<p>当事人因不可抗拒的事由而延误专利法或者专利法实施细则规定的期限或者国务院专利行政部门指定的期限，导致其权利丧失的，自障碍消除之日起2个月内，最迟自期限届满之日起2年内，可 以向国务院专利行政部门请求恢复权利。</p>
							<p>除前款规定的情形外，当事人因其他正当理由延误专利法或者专利法实施细则规定的期限或者国务院专利行政部门指定的期限，导致其权利丧失的，可以自收到国务院专利行政部门的<strong>通知之日起2个月内</strong>向国务院专利行政部门请求恢复权利。</p>
							<p>授予专利权的外观设计与现有设计或者现有设计特征的组合相比，应当具有明显区别。</p>
							<p>授予专利权的外观设计不得与他人在申请日以前已经取得的合法权利相冲突。</p>
							<h2>必要性:</h2>
							<p class="qo">由于某种原因而丧失了已取得的权利或引起某种权利的终止，导致专利申请被视为撤回，都是申请人或专利权人所不愿见到的。为了对申请人、专利权人或者其它利害关系人因不可抗拒的事由或因正当理由而造成的权利丧失提供补救的机会，特规定了请求恢复权利的程序。</p>

						</article>
					</div>
					<div class="one">
						
					</div>
					<div class="one">
						<img src="http://www.shangbiaozongju.com/images/zlys1.jpg">
						<img src="http://www.shangbiaozongju.com/images/zlys2.jpg">
						<img src="http://www.shangbiaozongju.com/images/zlys3.jpg">
					</div>
					<div class="one"></div>
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

	<script>
		function $_class(name) {
			var elements = document.getElementsByTagName("*");
			for (s = 0; s < elements.length; s++) {
				if (elements[s].className == name) {
					return elements[s];
				}
			}
		}
		var tabList = $_class("menuA").getElementsByTagName("li")
		tabCon = $_class("menuB").getElementsByTagName("div");
		for (i = 0; i < tabList.length; i++) {
			(function() {
				var t = i;
				tabList[t].onmouseover = function() {
					for (o = 0; o < tabCon.length; o++) {
						tabCon[o].style.display = "none";
						tabList[o].className = "";
						if (t == o) {
							this.className = "check";
							tabCon[o].style.display = "block";
						}
					}
				}
			})()
		}
	</script>

</body>
</html>
