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

								<h2>实用新型专利</h2>
								<p>实用新型专利是专利法保护的专利的三种类型之一，实用新型是指对产品的形状、构造或者其结合所提出的适于实用的新的技术方案，又称小发明或小专利。</p>
								<h2>必要性:</h2>
								<p class="qo">国家之所以保护实用新型，目的在于鼓励低成本、研制周期短的小发明的创造，更快的适应经济发展的需要。它的创造性和技术水平较发明专利低，但实用价值大，在专利权审批上采取简化审批程序、缩短保护期限、降低收费标准办法加以保护。</p>

							</article>
						</div>
						<div class="one">
							<img
								src="http://www.shangbiaozongju.com/userfiles/20140625070532198.png">
							<img
								src="http://www.shangbiaozongju.com/userfiles/20140625070510157.png">
							<img
								src="http://www.shangbiaozongju.com/userfiles/20140625070423156.png">

							<table width="100%" cellspacing="1" cellpadding="0" border="0"
								style="text-align: center">
								<tbody>
									<tr id="th"
										style="color: rgb(255, 255, 255); background: rgb(238, 238, 238);">
										<th colspan="3"><span style="color: #000;">实用新型、外观设计专利年费</span></th>
									</tr>
									<tr id="th"
										style="color: rgb(255, 255, 255); background: rgb(16, 158, 226);">
										<td width="50%">对应年度</td>
										<td width="50%">年费标准</td>
									</tr>
									<tr style="background: rgb(238, 238, 238);">
										<td width="50%">第1-3年</td>
										<td width="50%">￥1,900.00</td>
									</tr>
									<tr style="background: rgb(238, 238, 238);">
										<td>第4-5年</td>
										<td>￥2,200.00</td>
									</tr>
									<tr style="background: rgb(238, 238, 238);">
										<td>第6-8年</td>
										<td>￥2,500.00</td>
									</tr>
									<tr style="background: rgb(238, 238, 238);">
										<td>第9-10年</td>
										<td>￥3,300.00</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="one">
							<img src="http://www.shangbiaozongju.com/images/zlys1.jpg">
							<img src="http://www.shangbiaozongju.com/images/zlys2.jpg">
							<img src="http://www.shangbiaozongju.com/images/zlys3.jpg">
						</div>
						<div class="one"></div>
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
