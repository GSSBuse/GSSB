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

								<h2>什么是驳回复审？</h2>
								<p>驳回复审是商标申请被驳回的唯一救济途径。</p>
								<p>驳回复审是指国家商标局以申请注册的商标违反商标法的有关规定为由驳回申请
									人的注册申请，申请人不服国家商标局的驳回决定，在法定的期限内向商标评审委员会申请复审
									，在复审中陈述不服国家商标局驳回决定的理由，请求商标评审委员会准予申请商标初审公告的程序。</p>
								<h2>驳回复审必要性:</h2>
								<span class="ll1">01</span>驳回复审是申请商标被驳回的唯一救济途径。<br> <br>
								<span class="ll1">02</span>驳回复审平均成功几率较高，不少知名品牌商标都有过驳回复审的经历。<br>
								<br> <span class="ll1">03</span>重要商标被驳回时，只要有一线希望，大多数当事人都会选择权利用尽进行争取。<br>
								<br> <span class="ll1">04</span>驳回复审可以保留在先申请权，等待扫除注册的障碍以使商标最终取得注册。<br>
								<br> <span class="ll1">05</span>商标局驳回商标具有主观性，当事人可在
								驳回复审中通过理由阐述并提供证据来影 响商评委对商标的再判断。<br>

							</article>
						</div>
						<div class="one">
							<img
								src="http://www.shangbiaozongju.com/userfiles/20140627071157120.png">
						</div>
						<div class="one">

							<span class="ll1">1</span>确定案件详情之后会第一时间组织专家律师团队进行分析探讨，第一时间与客户沟通准备相应的法律团队优势的文件资料。<br>
							<br> <span class="ll1">2</span>、优秀的律师服务团队，专业的客服人员随时与客户保持沟通，强大的后台服务团队让客户的商标在时刻保持权利至上。<br>
							<br> <span class="ll1">3</span>完全托管式服务 <br> <br> <span
								class="ll1">4</span>针对每个案件的不同情况本所会根据每个案件的特别情况制定方案，组织针对特定方案的法律服务团队。<br>
							<br> <span class="ll1">5</span>证书下达后使用的过程会持续跟踪服务，保证在证书下达后能够正确使用和即时维护客户自身的权利。7X24小时在线400客服随时接听和解决客服知识产权需求。<br>
							<br> <span class="ll1">6</span>所有客户知识产权项目都会纳入360度服务计划。<br>
							<br>

							<p style="text-align: center;">
								<img src="http://www.shangbiaozongju.com/images/flys2.jpg"
									style="width: 680px;">
							</p>
							<p style="text-align: center;">
								<img src="http://www.shangbiaozongju.com/images/flys3.jpg"
									style="width: 680px;">
							</p>
							<p style="text-align: center;">
								<img src="http://www.shangbiaozongju.com/images/flys4.jpg"
									style="width: 680px;">
							</p>

						</div>
					</div>
					<div class="one"></div>
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
