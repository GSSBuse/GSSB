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
							<a href="#">个人中心</a>
							<a href="${ctx }/personaluserbuy.html?id=${login_user.id}">我发布过的买标信息</a>
						</h1>

						<hr>

						<ul class="tabs-nav">
							<li class="active"><h4 style="height: 50px;">
									<a href="#">我的信息</a>
								</h4></li>
								<li><h4>
									<a href="#">修改个人信息</a>
								</h4></li>
							<li><h4>
									<a href="${ctx }/personaluserbuy.html?id=${login_user.id}">我发布过的买标信息</a>
								</h4></li>
							<li><h4>
									<a href="${ctx }/personalusersold.html?id=${login_user.id}">我发布过的卖标信息</a>
								</h4></li>
							<li><h4>
									<a href="${ctx }/personaluserreward.html?id=${login_user.id}">我发布过的悬赏信息</a>
								</h4></li>
							<li><h4>
									<a href="#">我评论过的信息</a>
								</h4></li>

						</ul>

						<div class="tabs-container">
							<div class="tab-content">
								<table class="setting-profile-table"
									style="width: 400px; margin-left: 100px;">
									<tbody>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">用户名：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">${gbjUserDetail.username}</label>
										</tr>

										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">真实姓名：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">${gbjUserDetail.name}</label>
										</tr>

										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">手机号码：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">${gbjUserDetail.mobile}</label>
										</tr>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">邮 箱：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">${gbjUserDetail.email}</label>
										</tr>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">Q Q：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">${gbjUserDetail.qq}</label>
										</tr>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">微 信 号：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">${gbjUserDetail.wechat}</label>
										</tr>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">支 付 宝：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">${gbjUserDetail.payway}</label>
										</tr>



									</tbody>
								</table>
							</div>
							<script type="text/javascript">
							 
							  function showError(error){
								   $('.form-error').hide();
							        $(".form-error").find("label").html(error);
							        $(".form-error").show();
							    }
							</script>
							<div class="tab-content">
							<form method="post" id="change">
								<table class="setting-profile-table"
									style="width: 400px; margin-left: 100px;">
									<tbody>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">用户名：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">
												<input  type="text" name="username" id="username"  value="${gbjUserDetail.username}"
												  onfocus="if (value =='${gbjUserDetail.username}'){value =''}"
					                             onblur="if (value ==''){value='${gbjUserDetail.username}'}"
												 >
												<input name="id" type="hidden" id="id" value="${login_user.id}"></label>
										</tr>

										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">真实姓名：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">
												<input  type="text" name="name" id="name" value="${gbjUserDetail.name}"
												 onfocus="if (value =='${gbjUserDetail.name}'){value =''}"
					                             onblur="if (value ==''){value='${gbjUserDetail.name}'}"
												 ></label>
												
										</tr>

										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">手机号码：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">
												<input  type="text" name="mobile" id="mobile" value="${gbjUserDetail.mobile}"
												 onfocus="if (value =='${gbjUserDetail.mobile}'){value =''}"
					                             onblur="if (value ==''){value='${gbjUserDetail.mobile}'}"
												 ></label>
												
										</tr>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">邮 箱：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">
												<input  type="text" name="email" id="email" value="${gbjUserDetail.email}"
												 onfocus="if (value =='${gbjUserDetail.email}'){value =''}"
					                             onblur="if (value ==''){value='${gbjUserDetail.email}'}"
												 ></label>
											
										</tr>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">Q Q：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">
												<input  type="text" name="qq" id="qq" value="${gbjUserDetail.qq}"
												 onfocus="if (value =='${gbjUserDetail.qq}'){value =''}"
					                             onblur="if (value ==''){value='${gbjUserDetail.qq}'}"
												 ></label>
												
										</tr>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">微 信 号：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">
												<input  type="text" name="wechat" id="wechat" value="${gbjUserDetail.wechat}"
												 onfocus="if (value =='${gbjUserDetail.wechat}'){value =''}"
					                             onblur="if (value ==''){value='${gbjUserDetail.wechat}'}"
												 ></label>
												
										</tr>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">支 付 宝：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">
												<input  type="text" name="payway" id="payway"  value="${gbjUserDetail.payway}"
												 onfocus="if (value =='${gbjUserDetail.payway}'){value =''}"
					                             onblur="if (value ==''){value='${gbjUserDetail.payway}'}"
												></label>
											
										</tr>
										<tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;"></th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">
												<input class="btn" onclick="changeSubmit();" name="change" type="button" id="change" value="修改"></label>
										</tr>
									</tbody>
								</table>
								</form>
							</div>
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
							<div class="tab-content">
								<article class="format-standard type-post hentry clearfix"
									ms-repeat-el="datas.domainUserSoldArticleList">
									<header class="clearfix">
										<h3 class="post-title">
											<a ms-attr-href="${ctx }/single.html?id={{el.id}}&type=sold">{{el.title}}</a>
										</h3>
										<div class="post-meta clearfix ">
											<span class="date">{{el.createDate}}</span>
										</div>
										<!-- end of post meta -->
									</header>
								</article>
							</div>
							<!--            我发布的悬赏信息。        by snnu   2017.12.31           -->
							<div class="tab-content">
								<article class="format-standard type-post hentry clearfix"
									ms-repeat-el="datas.domainUserRewardArticleList">
									<header class="clearfix">
										<h3 class="post-title">
											<a
												ms-attr-href="${ctx }/single.html?id={{el.id}}&type=reward">{{el.title}}</a>
										</h3>
										<div class="post-meta clearfix ">
											<span class="date">{{el.createDate}}</span>
										</div>
										<!-- end of post meta -->
									</header>
								</article>
							</div>
							<!--            我评论的信息。        by snnu   2017.12.31           -->
							<div class="tab-content">
								<article class="format-standard type-post hentry clearfix"
									ms-repeat-el="datas.domainCommentsArticleList">
									<header class="clearfix">
										<h3 class="post-title">
											<a>{{el.comment}}</a>
										</h3>
										<div class="post-meta clearfix ">
											<span class="date">{{el.createDate}}</span>
										</div>
										<!-- end of post meta -->
									</header>
								</article>
							</div> 

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

