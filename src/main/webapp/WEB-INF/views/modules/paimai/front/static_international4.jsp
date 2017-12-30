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

							<p>我们不仅能够提供专业、快捷的国际商标查询、注册等基础服务，还能够提供专业、快捷的国际商标确权、维权等后续服务，如：答复驳回、答复异议、申请异议、驳回复审、异议复审、申请撤销侵权商标、撰写发送律师警告函、海关备案维权、打击仿冒侵权、打击抢注侵权、提起诉讼或参与商标谈判等。</p>

						</article>
					</div>
					<div class="one">


					</div>
					<div class="one">
						<div>
							<span class="ll1">1</span>所有商标注册全部当天网报
						</div>

						<div>
							<span class="ll1">2</span>专业的商标近似检索系统和优秀的商标近似检索团队
						</div>
						<div>
							<span class="ll1">1</span>所有商标注册全部当天网报
						</div>
						<br>
						<div>
							<span class="ll1">2</span>专业的商标近似检索系统和优秀的商标近似检索团队
						</div>
						<br>
						<div>
							<span class="ll1">3</span>全国重多大型知名的企业的选择
						</div>
						<br>
						<div>
							<span class="ll1">4</span>专业的客服人员会在定期会在你的商标进度随时与你回访和对我们的商标受理人员的满意度调查，让我们的服务达到客服百分百的满意！
						</div>
						<br>
						<div>
							<span class="ll1">5</span>国商所实行多部门协调服务，多人员针对客户一家企业进行服务！
						</div>
						<br>
						<div>
							<span class="ll1">6</span>证书下达后使用的过程会持续跟踪服务，保证在证书下达后能够正确使用和即时维护客户自身的权利。7X24小时在线400客服随时接听和解决客服知识产权需求。
						</div>
						<br>
						<div>
							<span class="ll1">7</span>所有客户知识产权项目都会纳入360度服务计划
						</div>
						<br>


						<p style="text-align: center;">
							<img src="http://www.shangbiaozongju.com/images/gjsbys.jpg"
								style="width: 680px;">
						</p>


						<p style="text-align: center;">
							<img src="http://www.shangbiaozongju.com/images/gjsbys1.jpg"
								style="width: 680px;">
						</p>
						<p style="text-align: center;">
							<img src="http://www.shangbiaozongju.com/images/gjsbys2.jpg"
								style="width: 680px;">
						</p>

						<p style="text-align: center;">
							<img src="http://www.shangbiaozongju.com/images/gjsbys3.png"
								style="width: 680px;">
						</p>

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
