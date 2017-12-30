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
</head>
<body>

	<%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
	<div class="about">
		<div class="container">
			<h1>
				个人中心<span class="m_1"><br>赵三的个人中心</span>
			</h1>
		</div>
	</div>

	<!-- Start of Page Container -->
	<div class="page-container">
		<div class="container">
			<div class="row">
				<!-- start of page content -->
				<div class="span12 page-content">

					<article class="type-page hentry clearfix">

						<h1 class="post-title">
							<a href="#">个人中心</a>
						</h1>

						<hr>

						<ul class="tabs-nav">
							<li class="active" style=""><a href="#">我的信息</a></li>
							<li><a href="#">我发布过的信息</a></li>
							<li><a href="#">我点赞的信息</a></li>
						</ul>

						<div class="tabs-container">
							<div class="tab-content">
								<ul>
									<li><h3 class="category">用 户 名:
											${gbjUserDetail.username}</h3></li>
									<li><h3 class="category">真实姓名: ${gbjUserDetail.name}</h3></li>
									<li><h3 class="category">手机号码:
											${gbjUserDetail.mobile}</h3></li>
									<li><h3 class="category">邮 箱: ${gbjUserDetail.email}</h3></li>
									<li><h3 class="category">Q Q: ${gbjUserDetail.qq}</h3></li>
									<li><h3 class="category">微 信 号:
											${gbjUserDetail.wechat}</h3></li>
									<li><h3 class="category">支 付 宝:
											${gbjUserDetail.payway}</h3></li>
								</ul>
							</div>
							<div class="tab-content">

								这里就是list来表示，用卖标一览表页面那种查询list然后分页表示就好。（查询条件是查自己的）
								这里可以把所有信息都汇总表示。之前做的那个“3表合一”视图可能有用。</div>
							<div class="tab-content">
								同上，查询出来按一览详细页面的样式表示。那边的代码copy过来后，对应的变量名改下。</div>
						</div>

					</article>
				</div>

			</div>
		</div>
	</div>
	<!-- End of Page Container -->

	<!-- Start of Footer -->
	<%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
	<!-- End of Footer -->

	<a href="#top" id="scroll-top"></a>

</body>
</html>

