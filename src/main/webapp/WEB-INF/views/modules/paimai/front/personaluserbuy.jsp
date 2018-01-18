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
<script type="text/javascript" src="${ctxStatic }/front/js/Article.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/changepersonal.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/jqPaginator.js"></script>
<body ms-controller="articles">
	<%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
	<div class="about bg-three">
		<div class="container">
			<h1>
				个人中心<span class="m_1"><br>${gbjUserDetail.username}的个人中心</span>
			</h1>
		</div>
	</div>

	<!-- Start of Page Container -->
	<div class="page-container" style="min-height: 800px;">
		<div class="container">
			<div class="row">
				<!-- start of page content -->
				<div class="span12 page-content">

					<article class="type-page hentry clearfix">

						<h1 class="post-title">
							<a href="#">个人中心-买标信息一览</a>
						</h1>

						<hr>

						<ul class="tabs-nav">
							<li ><h4 style="height: 50px;">
									<a href="#">我的信息</a>
								</h4></li>
								<li><h4>
									<a href="#">修改个人信息</a>
								</h4></li>
							<li class="active"><h4>
									<a href="${ctx }/personaluserbuy.html?id=${login_user.id}">我发布过的买标信息</a>
								</h4></li>
							<li><h4>
									<a href="#">我发布过的卖标信息</a>
								</h4></li>
							<li><h4>
									<a href="#">我发布过的悬赏信息</a>
								</h4></li>
							<li><h4>
									<a href="#">我评论过的信息</a>
								</h4></li>

						</ul>

						<div class="tabs-container">
							<!--            我发布的买标信息。        by snnu   2017.12.31           -->
							<div class="tab-content">
								<article class="format-standard type-post hentry clearfix"
									ms-repeat-el="datas.domainUserBuyArticleList">
									<header class="clearfix">
										<h3 class="post-title">
											<a ms-attr-href="${ctx }/single.html?id={{el.id}}&type=buy">{{el.title}}</a>
										</h3>
										<div class="post-meta clearfix ">
											<span class="date">{{el.createDate}}</span>
										</div>
										<!-- end of post meta -->
									</header>
								</article>
								<!--  分页代码
								  <div class="demo customBootstrap">
                                    <p id="demo2-text"></p>
                                  <ul id="demo2" class="pagination"></ul>
                                  </div>  -->
							</div>
							<!--            我发布的卖标信息。        by snnu   2017.12.31           -->
							
							<!--            我发布的悬赏信息。        by snnu   2017.12.31           -->
							
							<!--            我评论的信息。        by snnu   2017.12.31           -->
							

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

