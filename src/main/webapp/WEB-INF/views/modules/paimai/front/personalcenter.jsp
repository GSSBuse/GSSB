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
							<%-- <a href="${ctx }/personaluserbuy.html?id=${login_user.id}">我发布过的买标信息</a> --%>
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
									<a href="#">我发布过的买标信息</a>
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
										<%-- <tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">头像：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;"><img src="${ctx }${gbjUserDetail.photo}"/></label>
										</tr> --%>
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
										<%-- <tr style="height: 35px; font-size: 16px;">
											<th style="text-align: right; width: 100px;">头像：</th>
											<td class="line30"><label class="profile-gender"
												for="passport-sex-1" style="padding-left: 20px;">
												<input  type="file" name="photo" id="photo" value=""
												 onfocus="if (value =='${gbjUserDetail.photo}'){value =''}"
					                             onblur="if (value ==''){value='${gbjUserDetail.photo}'}"
												 ></label>
												
										</tr> --%>


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
											
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#changebuy-dialog" onclick="showbuy(this)">修改</a><p class="ellb" style="display: none">{{el.id}}</p>
											<%-- <a href="javascript:void(0)" ms-click="$remove">删除</a>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                         <a ms-attr-href="${ctx}/personalcenter.html?id={{el.id}}" >删除</a> --%>
					                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                         <a href="#deletebuy-dialog" onclick="showbuydelete(this)">删除</a><p class="ellb" style="display: none">{{el.id}}</p>
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
							<script type="text/javascript">
							  function showError(error){
								   $('.form-error').hide();
							        $(".form-error").find("label").html(error);
							        $(".form-error").show();
							    }
							</script>
	<div id="changebuy-dialog-bg" style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="changebuy-dialog"  style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-dialogbuy" style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		  <form method="post" id="changebuy" 
		        	style="padding: 20px 30px; margin: 0;">
		        <h1 class="post-title">
					<a href="#">修改买标信息</a>
				</h1>
				<p class="comment-notes">请输入您需要修改的信息。</p>
				<div class="form-error" style="color:#FF0000">
						<i></i><label class="text"></label>
				</div>
				<div class="form-success" style="color:#FF0000">
					<i></i><label class="text"></label>
				</div>
				<div class="form-group" >
					<label for="title">商标名称 *</label> 
					<input class="spn4 form-control" type="text" name="title" id="title"   style="width: 320px;">
					<input name="user_id" type="hidden" id="user_id" value="${login_user.id}">
					<input name="idbuy" type="hidden" id="idbuy" value="">
			    </div>
			    <div>
					<label for="title">联系电话 *</label> 
					<input class="spn4 form-control" type="text" name="mobile" id="mobile" style="width: 320px;">
			    </div><br>
			    <div>
					<label for="title">联系人 *</label> 
					<input class="spn4 form-control" type="text" name="realname" id="realname" style="width: 320px;">
			    </div>
			                       
				<div class="payment-sendbtns">
					<input  class="btn" name="changebuy" type="button" id="changebuy" onclick="changebuySubmit();" value="提交修改" style="height: 50px;">
				</div>
			</form> 
	</div>
	
	
	<div id="deletebuy-dialog-bg" style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="deletebuy-dialog"  style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-dialogbuydelete" style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		  <form method="post" id="deletebuydialog"  style="padding: 20px 30px; margin: 0;">
				<div class="form-error" style="color:#FF0000">
						<i></i><label class="text"></label>
				</div>
				<div class="form-success" style="color:#FF0000">
					<i></i><label class="text"></label>
				</div>
				<div class="form-group" >
					<input name="idbuydelete" type="hidden" id="idbuydelete" value="">
			    </div>
				<div class="payment-sendbtns">
					<input  class="btn" name="deletebuydialog" type="button" id="deletebuydialog" onclick="deletebuydialogSubmit();" value="确定删除？" style="height: 50px;">
				</div>
			</form> 
	</div>
							
							<!--            我发布的卖标信息。        by snnu   2017.12.31           -->
							<div class="tab-content">
								<article class="format-standard type-post hentry clearfix"
									ms-repeat-el="datas.domainUserSoldArticleList">
									<header class="clearfix">
										<h3 class="post-title">
											<a ms-attr-href="${ctx }/single.html?id={{el.id}}&type=sold">{{el.title}}</a>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#changesold-dialog" onclick="showsold(this)">修改</a><p class="ellb" style="display: none">{{el.id}}</p>
											<%-- <a href="javascript:void(0)" ms-click="$remove">删除</a>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                         <a ms-attr-href="${ctx}/personalcenter.html?id={{el.id}}" >删除</a> --%>
					                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                         <a href="#deletesold-dialog" onclick="showsolddelete(this)">删除</a><p class="ellb" style="display: none">{{el.id}}</p>
										</h3>
										<div class="post-meta clearfix ">
											<span class="date">{{el.createDate}}</span>
										</div>
										<!-- end of post meta -->
									</header>
								</article>
							</div>
							
<div id="changesold-dialog-bg" style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="changesold-dialog"  style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-dialogsold" style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		  <form method="post" id="changesold" 
		        	style="padding: 20px 30px; margin: 0;">
		        <h1 class="post-title">
					<a href="#">修改卖标信息</a>
				</h1>
				<p class="comment-notes">请输入您需要修改的信息。</p>
				<div class="form-error" style="color:#FF0000">
						<i></i><label class="text"></label>
				</div>
				<div class="form-success" style="color:#FF0000">
					<i></i><label class="text"></label>
				</div>
				<div class="form-group" >
					<label for="title">商标名称 *</label> 
					<input class="spn4 form-control" type="text" name="title" id="title"   style="width: 320px;">
					<input name="user_id" type="hidden" id="user_id" value="${login_user.id}">
					<input name="idsold" type="hidden" id="idsold" value="">
			    </div>
			    <div>
					<label for="title">联系电话 *</label> 
					<input class="spn4 form-control" type="text" name="mobile" id="mobile" style="width: 320px;">
			    </div><br>
			    <div>
					<label for="title">联系人 *</label> 
					<input class="spn4 form-control" type="text" name="realname" id="realname" style="width: 320px;">
			    </div>
			                       
				<div class="payment-sendbtns">
					<input  class="btn" name="changesold" type="button" id="changesold" onclick="changesoldSubmit();" value="提交修改" style="height: 50px;">
				</div>
			</form> 
	</div>
	
	
	<div id="deletesold-dialog-bg" style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="deletesold-dialog"  style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-dialogsolddelete" style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		  <form method="post" id="deletesolddialog"  style="padding: 20px 30px; margin: 0;">
				<div class="form-error" style="color:#FF0000">
						<i></i><label class="text"></label>
				</div>
				<div class="form-success" style="color:#FF0000">
					<i></i><label class="text"></label>
				</div>
				<div class="form-group" >
					<input name="idsolddelete" type="hidden" id="idsolddelete" value="">
			    </div>
				<div class="payment-sendbtns">
					<input  class="btn" name="deletesolddialog" type="button" id="deletesolddialog" onclick="deletesolddialogSubmit();" value="确定删除？" style="height: 50px;">
				</div>
			</form> 
	</div>
							
							<!--            我发布的悬赏信息。        by snnu   2017.12.31           -->
							<div class="tab-content">
								<article class="format-standard type-post hentry clearfix"
									ms-repeat-el="datas.domainUserRewardArticleList">
									<header class="clearfix">
										<h3 class="post-title">
											<a
												ms-attr-href="${ctx }/single.html?id={{el.id}}&type=rewards">{{el.title}}</a>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="#changereward-dialog" onclick="showreward(this)">修改</a><p class="ellb" style="display: none">{{el.id}}</p>
											<%-- <a href="javascript:void(0)" ms-click="$remove">删除</a>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                         <a ms-attr-href="${ctx}/personalcenter.html?id={{el.id}}" >删除</a> --%>
					                         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					                         <a href="#deletereward-dialog" onclick="showrewarddelete(this)">删除</a><p class="ellb" style="display: none">{{el.id}}</p>
										</h3>
										<div class="post-meta clearfix ">
											<span class="date">{{el.createDate}}</span>
										</div>
										<!-- end of post meta -->
									</header>
								</article>
							</div>
							
							
							
							<div id="changereward-dialog-bg" style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="changereward-dialog"  style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-dialogreward" style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		  <form method="post" id="changereward" 
		        	style="padding: 20px 30px; margin: 0;">
		        <h1 class="post-title">
					<a href="#">修改悬赏信息</a>
				</h1>
				<p class="comment-notes">请输入您需要修改的信息。</p>
				<div class="form-error" style="color:#FF0000">
						<i></i><label class="text"></label>
				</div>
				<div class="form-success" style="color:#FF0000">
					<i></i><label class="text"></label>
				</div>
				<div class="form-group" >
					<label for="title">商标名称 *</label> 
					<input class="spn4 form-control" type="text" name="title" id="title"   style="width: 320px;">
					<input name="user_id" type="hidden" id="user_id" value="${login_user.id}">
					<input name="idreward" type="hidden" id="idreward" value="">
			    </div>
			    <div>
					<label for="title">联系电话 *</label> 
					<input class="spn4 form-control" type="text" name="mobile" id="mobile" style="width: 320px;">
			    </div><br>
			    <div>
					<label for="title">联系人 *</label> 
					<input class="spn4 form-control" type="text" name="realname" id="realname" style="width: 320px;">
			    </div>
			                       
				<div class="payment-sendbtns">
					<input  class="btn" name="changereward" type="button" id="changereward" onclick="changerewardSubmit();" value="提交修改" style="height: 50px;">
				</div>
			</form> 
	</div>
	
	
	<div id="deletereward-dialog-bg" style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="deletereward-dialog"  style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-dialogrewarddelete" style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		  <form method="post" id="deleterewarddialog"  style="padding: 20px 30px; margin: 0;">
				<div class="form-error" style="color:#FF0000">
						<i></i><label class="text"></label>
				</div>
				<div class="form-success" style="color:#FF0000">
					<i></i><label class="text"></label>
				</div>
				<div class="form-group" >
					<input name="idrewarddelete" type="hidden" id="idrewarddelete" value="">
			    </div>
				<div class="payment-sendbtns">
					<input  class="btn" name="deleterewarddialog" type="button" id="deleterewarddialog" onclick="deleterewarddialogSubmit();" value="确定删除？" style="height: 50px;">
				</div>
			</form> 
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
<script type="text/javascript">
//修改买标信息js 01
function showbuy(obj){
   //alert(obj.tagName);
	//alert($(obj).prev().tagName);
	var pp = obj.nextSibling;
	var cnt = pp.innerText;
	//alert(cnt);
	document.getElementById("idbuy").setAttribute("value", cnt);
	document.getElementById("changebuy-dialog").style.display="block";
	document.getElementById("changebuy-dialog-bg").style.display = 'block';
}
function hide(){
    document.getElementById("changebuy-dialog").style.display="none";
    document.getElementById("changebuy-dialog-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
$('#changebuy-dialog-bg').click(function(){
	$('#changebuy-dialog,#changebuy-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialogbuy').click(function(){
    $('#changebuy-dialog,#changebuy-dialog-bg').hide();
});
</script>
<script type="text/javascript">
//修改卖标信息js 02
function showsold(obj){
   //alert(obj.tagName);
	//alert($(obj).prev().tagName);
	var pp = obj.nextSibling;
	var cnt = pp.innerText;
	//alert(cnt);
	document.getElementById("idsold").setAttribute("value", cnt);
	document.getElementById("changesold-dialog").style.display="block";
	document.getElementById("changesold-dialog-bg").style.display = 'block';
}
function hide(){
    document.getElementById("changesold-dialog").style.display="none";
    document.getElementById("changesold-dialog-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
$('#changesold-dialog-bg').click(function(){
	$('#changesold-dialog,#changesold-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialogsold').click(function(){
    $('#changesold-dialog,#changesold-dialog-bg').hide();
});
</script>
<script type="text/javascript">
//修改悬赏信息js 03
function showreward(obj){
   //alert(obj.tagName);
	//alert($(obj).prev().tagName);
	var pp = obj.nextSibling;
	var cnt = pp.innerText;
	//alert(cnt);
	document.getElementById("idreward").setAttribute("value", cnt);
	document.getElementById("changereward-dialog").style.display="block";
	document.getElementById("changereward-dialog-bg").style.display = 'block';
}
function hide(){
    document.getElementById("changereward-dialog").style.display="none";
    document.getElementById("changereward-dialog-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
$('#changereward-dialog-bg').click(function(){
	$('#changereward-dialog,#changereward-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialogreward').click(function(){
    $('#changereward-dialog,#changereward-dialog-bg').hide();
});
</script>
<script type="text/javascript">
//删除买标信息js  001
function showbuydelete(obj){
   //alert(obj.tagName);
	//alert($(obj).prev().tagName);
	var pp = obj.nextSibling;
	var cnt = pp.innerText;
	//alert(cnt);
	document.getElementById("idbuydelete").setAttribute("value", cnt);
	document.getElementById("deletebuy-dialog").style.display="block";
	document.getElementById("deletebuy-dialog-bg").style.display = 'block';
}
function hide(){
    document.getElementById("deletebuy-dialog").style.display="none";
    document.getElementById("deletebuy-dialog-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
$('#deletebuy-dialog-bg').click(function(){
	$('#deletebuy-dialog,#deletebuy-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialogbuydelete').click(function(){
    $('#deletebuy-dialog,#deletebuy-dialog-bg').hide();
});
</script>
<script type="text/javascript">
//删除卖标信息js 002
function showsolddelete(obj){
   //alert(obj.tagName);
	//alert($(obj).prev().tagName);
	var pp = obj.nextSibling;
	var cnt = pp.innerText;
	//alert(cnt);
	document.getElementById("idsolddelete").setAttribute("value", cnt);
	document.getElementById("deletesold-dialog").style.display="block";
	document.getElementById("deletesold-dialog-bg").style.display = 'block';
}
function hide(){
    document.getElementById("deletesold-dialog").style.display="none";
    document.getElementById("deletesold-dialog-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
$('#deletesold-dialog-bg').click(function(){
	$('#deletesold-dialog,#deletesold-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialogsolddelete').click(function(){
    $('#deletesold-dialog,#deletesold-dialog-bg').hide();
});
</script>
<script type="text/javascript">
//删除悬赏信息js 003
function showrewarddelete(obj){
   //alert(obj.tagName);
	//alert($(obj).prev().tagName);
	var pp = obj.nextSibling;
	var cnt = pp.innerText;
	//alert(cnt);
	document.getElementById("idrewarddelete").setAttribute("value", cnt);
	document.getElementById("deletereward-dialog").style.display="block";
	document.getElementById("deletereward-dialog-bg").style.display = 'block';
}
function hide(){
    document.getElementById("deletereward-dialog").style.display="none";
    document.getElementById("deletereward-dialog-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
$('#deletereward-dialog-bg').click(function(){
	$('#deletereward-dialog,#deletereward-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialogrewarddelete').click(function(){
    $('#deletereward-dialog,#deletereward-dialog-bg').hide();
});
</script>
	<!-- Start of Footer -->
	<%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
	<!-- End of Footer -->

	<a href="#top" id="scroll-top"></a>

</body>
</html>

