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

							<h2>专利权评价报告</h2>
							<p>专利权评价报告是国家知识产权局根据专利权人或者利害关系人的请求，在实用新型或者外观设计被授予专利权后对相关实用新型或外观设计专利进行检索，并就该专利是否符合专利法及其实施细则规定的授权条件进行分析和评价，作出专利权评价报告，是一种官方出具的较权威专利质量评价。</p>
							<p>专利侵权纠纷涉及实用新型专利或者外观设计专利的，人民法院或者管理专利工作的部门可以要求专利权人或者利害关系人出具由国务院专利行政部门对相关实用新型或者外观设计进行检索、分析和评价后作出的专利权评价报告，作为审理、处理专利侵权纠纷的证据。</p>

							<h2>必要性:</h2>
							<p class="qo">无论是对于应对专利侵权纠纷，还是支撑专利推广，专利权评价报告都具有非常重要的作用，具体如下：</p>
							<p class="qo">（一）专利权评价报告是人民法院或者管理专利工作的部门判断是否中止审理、处理程序依据。</p>
							<p class="qo">（二）专利权评价报告是人民法院或者管理专利工作的部门审理、处理专利侵权纠纷中判断是否侵权依据。</p>
							<p class="qo">根据《专利法》第六十一条第二款规定，专利侵权纠纷涉及实用新型专利 或者外观设计专利的，人民法院或者管理专利工作的部门可以要求专利权人或者利害关系人出具由国务院专利行政部门对相关实用新型或者外观设计进行检索、分析和评价后作出的专利权评价报告，作为审理、处理专利侵权纠纷的证据。</p>
							<p class="qo">（三）专利权评价报告是专利权人专利推广中证明专利“含金量”的依据。</p>
							<p class="qo">由于未经实质审查即可授权，实用新型和外观专利多被认为稳定性不强，“含金量”不高。而如果获得了专利局下发的正面的专利权评价报告，则相当于该专利获得了国家专利局的再一次权威认可，专利的稳定性和含金量将会得到进一步认可。</p>
							<p class="qo">（四）专利权评价报告是权利人应对专利侵权的必要准备。</p>

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
