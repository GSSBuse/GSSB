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

							<h2>国内专利维权 </h2>
							<p>专利权人一旦发现自己拥有专利权的发明创造被他人非法使用构成侵权时，可以 依照《专利法》的规定，采取以下方式和措施，对遭到侵犯的专利权实行救济：</p>
							<p><span class="t1">1</span>双方协商解决</p>
							<p>专利权是民事权利的一种，当事人依法可以处分自己的民事权利。因此， 因专利侵权引起的纠纷，法律允许当事人协商解决。当然，当事人协商解决专 利纠纷必须依法进行，不得损害国家的、集体的或者第三人的合法权益，不得 违反法律的强制性规定和社会公共利益。</p>
							
							<p><span class="t1">2</span>请求行政处理</p>
							<p>当事人不愿协商或者协商不成的，专利权人或者利害关系人可以请求管理 专利工作的部门处理。管理专利工作的部门依请求处理专利纠纷时，认定侵权 行为成立的，可以责令侵权人立即停止侵权行为，当事人不服的，可以自收到 处理通知之日起十五日内依照《行政诉讼法》向人民法院起诉；</p>
							<p>侵权人期满不起诉又不停止侵权行为的，管理专利工作的部门可以申请人 民法院强制执行。进行处理的管理专利工作的部门应当事人的请求，可以就侵 犯专利权的赔偿数额进行调解；</p>
							<p>调解不成的，当事人可以依照《民事诉讼法》的规定，向人民法院起诉。</p>
							
							<p><span class="t1">3</span>提起民事诉讼</p>
							<p>对于专利侵权纠纷，当事人不愿协商或者协商不成的，同时也不愿意请求 管理专利工作的部门处理的，专利权人或者利害关系人还可以直接向人民法院 提起民事诉讼；</p>
							<p>或者当事人就侵犯专利权的赔偿数额请求管理专利工作的部门进行调解但 调解不成的，当事人也可以向人民法院提起民事诉讼。当然，当事人向人民法 院提起民事诉讼的，必须符合《民事诉讼法》、《专利法》及相关法律法规、 司法解释规定的起诉条件，并且必须向有管辖权的人民法院提出，否则，人民 法院将不予受理。</p>
							
							<br>
							<p>除了上述救济措施之外，根据我国《专利法》的规定，除依法承担民事责任外，由 管理专利工作的部门责令改正并予公告，没收违法所得，可以并处违法所得四倍以下的 罚款；没有违法所得的，可以处二十万元以下的罚款；构成犯罪的，依法追究刑事责任。</p>
							
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
