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
<script type="text/javascript" src="${ctxStatic }/front/js/rewardcomments.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/rewardreplycomments.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/rewardupcounts.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/rewardcommentsupcounts.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/xuanshang.js"></script>



<body ms-controller="articles">
	<%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
	<div class="about">
		<div class="container">
			<h1>
				悬赏起名<span class="m_1"><br>最新发布的悬赏起名信息，如有兴趣请联系123456789</span>
			</h1>
		</div>
	</div>

	<!-- Start of Page Container -->
	<div class="page-container">
		<div class="container">
			<div class="row">

				<!-- start of page content -->
				<div class="span8 page-content">

					<article class=" type-post format-standard hentry clearfix">

						<h1 class="post-title">
							<a href="#">${gbjRewardDetail.title}  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 悬赏金额:${gbjRewardDetail.totalFee} 元</a>
							&nbsp;&nbsp;&nbsp;&nbsp;中标状态:<span id="zbstatus">${gbjRewardDetail.status}</span>
						</h1>
						<div class="jiathis_style_32x32" >
							<a class="jiathis_button_tsina" id="zjc"></a>
							<a class="jiathis_button_tqq" id="zjc"></a>
							<a class="jiathis_button_weixin" ></a>
							<a class="jiathis_button_cqq" ></a>
							<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
							<a class="jiathis_counter_style"></a>
						</div>
						<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
						<br><br><br>
						<div class="post-meta clearfix like-btn" ms-controller="upcounts">
							<span id="time" class="date">${gbjRewardDetail.createDate}</span> <span class="category"><a
								href="#" class="gbjType">${gbjRewardDetail.typeId}</a></span> 
								<span class="comments"><a href="#">${gbjRewardDetail.lookCounts}</a></span>
							
							<form id="like-it-form" action="#" method="post" style="display: inline; float: right;">
								<span class="like-it" >${gbjRewardDetail.upCounts}</span> <input
									type="hidden" name="id" id="rewardid" />

							</form>
						</div>

						<!--   <p>买标买标悬赏等的详细信息。进入这个页面是需要2个参数，id和type。type绝对去从那个标里面去检索。也可以3个表做个视图（看看有没有共通的字段了要）。然后根据id取得对应的详细信息，包括回复和点赞等信息。分享等我后面再加。
                                                                                                                                                                                这里暂时不支持富文本的样式显示，只支持一般文本就好了。
                                                        </p>-->
						<p><h5>${gbjRewardDetail.description}</h5></p>

					</article>
					<section id="comments">

						<h3 id="comments-title">起名评论信息</h3>
						<p id="no-comments">暂无起名评论</p>
						<ol class="commentlist"
							ms-repeat-ell="datas.domainRewardCommentsArticleList">

							<li class="comment even thread-even depth-1" id="li-comment-2">
								<article id="comment-2">

									<a href="#"> <img alt=""
										src="http://1.gravatar.com/avatar/50a7625001317a58444a20ece817aeca?s=60&amp;d=http%3A%2F%2F1.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D60&amp;r=G"
										class="avatar avatar-60 photo" height="60" width="60">
									</a>

									<div class="comment-meta">

										<h5 class="author">
											<cite class="fn"> <a href="#" rel="external nofollow"
												class="url">{{ell.user.username}}</a>
											</cite>-<a href="#reply-dialog" onclick="show1(this)">回复</a><p id="zan" class="ellb" style="display: none">{{ell.id}}</p>
										</h5>

										<p class="date">
											<time datetime="2013-02-26T13:18:47+00:00">{{ell.createDate}}</time>
										</p>

									</div>
									<!-- end .comment-meta -->
									

									<div class="comment-body like-btn">
										<p><h5>{{ell.comment}}</h5>
											<form id="like-comment-form" action="#" method="post" style="display: inline; float: right;">
									         	<span class="like-it">{{ell.childId}}</span>
									       		<span type="" style="display:none" name="id" class="rewardcommentsid" >{{ell.id}}</span> 
								          	</form>
								 <a class="tome"  href="#xuanshang" onclick="xuanshang(this)">悬赏给他</a><p  style="display: none">{{ell.user.username}}</p>
										</p>
									</div>
									<!-- end of comment-body -->
								</article>
								<!-- end of comment -->
                                 <ul  class="children" ms-repeat-data="datas.domainRewardReplyCommentsArticleList" >
								      
									<li ms-if="ell.id==data.toId"
										class="comment byuser comment-author-saqib-sarwar bypostauthor odd alt depth-2"
										id="li-comment-3">
										<article id="comment-3">
											<a> <img alt=""
												src="http://www.mf08s.com/y/q/UploadFiles_q/20121005/2012100507413841.jpg"
												class="avatar avatar-60 photo" height="60" width="60">
											</a>

											<div class="comment-meta">

												<h5 class="author">
													<cite class="fn">{{data.user.username}}</cite>
												</h5>

												<p class="date">
													<time datetime="2013-02-26T13:20:14+00:00">{{data.createDate}}</time>
												</p>

											</div>
											<div class="comment-body ">
												<p>
												<h6>{{data.replyComments}}</h6>
												</p>
											</div>
										</article>
									</li>
								</ul>
							</li>


						</ol>

						<%-- <div id="respond">

							<h3>评论回复</h3>

							<div class="cancel-comment-reply">
								<a rel="nofollow" id="cancel-comment-reply-link" href="#"
									style="display: none;">Click here to cancel reply.</a>
							</div>
                                <div class="form-error" style="color:#ff0000">
					              <i></i><label class="text"></label>
				                </div>

							<form method="post" id="commentform">
								<p class="comment-notes"></p>
								<div>
									<label for="comment"></label> 
									<input name="parentId"
										type="hidden" id="parentId" value="${login_user.id}">
									<input name="id" type="hidden" id="id"
										value="${gbjRewardDetail.id}">
									<textarea class="span8" name="comment" id="comment" cols="58"
										rows="10" style="border: 1px solid #cacaca; border-radius: 5px; margin-top: -38px;"></textarea>
								</div>
								<div>
									<input class="btn" name="submit" type="button" id="submit" onclick="commentSubmit();"
										value="提交评论" style="margin-top:10px;">
								</div>

							</form>

						</div> --%>
					</section>
					<!-- end of comments -->

				</div>
				<!-- end of page content -->


				<!-- start of sidebar -->
				<aside class="span4 page-sidebar">
					<%@ include file="/WEB-INF/views/include/frontSidebar.jsp"%>
				</aside>
				<!-- end of sidebar -->
			</div>
		</div>
	</div>
	<!-- End of Page Container -->

	<!-- Start of Footer -->
	<%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
	<!-- End of Footer -->

	<a href="#top" id="scroll-top"></a>
	<div id="reply-dialog-bg"
		style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="reply-dialog"  style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-dialog1" style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		                    <form method="post" id="replyform" >
								<p class="comment-notes"></p>
								<div>
									<label for="comment"></label> 
									<input name="rewardId" type="hidden" id="rewardId"
										value="${gbjRewardDetail.id}">
										<input name="userId" type="hidden" id="userId"
										value="${login_user.id}">
										<input name="toId" type="hidden" id="toId"
										value=''>
									<textarea class="span8" name="replyComments" id="replyComments" cols="58"
										rows="10" style="border: 1px solid #cacaca; border-radius: 5px; margin-top: 16px; margin-left: 20px; margin-right: 20px;"></textarea>
								</div>
								<div>
									<input class="btn" onclick="rewardreplySubmit();" name="submit" type="button" id="submit" value="提交评论" style="float: right; margin-right: 20px; margin-bottom: 12px; margin-top:12px;">
								</div>
							</form>
	</div>
	
	<div id="xuanshang-bg" style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="xuanshang"  style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-xuanshang" style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		  <form method="post" id="xuanshang-form"  style="padding: 20px 30px; margin: 0;">
				<div class="form-error" style="color:#FF0000">
						<i></i><label class="text"></label>
				</div>
				<div class="form-success" style="color:#FF0000; display: none;">
					<i></i><label class="text"></label>
				</div>
				<div class="form-group" style="display: none;">
					<input name="successfulBidder" type="hidden" id="successfulBidder" value="">
					<input name="reward-id" type="hidden" id="reward-id" value="${gbjRewardDetail.id}">
					<input name="totalFee" type="hidden" id="totalFee" value="${gbjRewardDetail.totalFee}">
			    </div>
				<div class="payment-sendbtns" style="margin-top: 0">
					<input  class="btn" name="submit" type="button" id="submit" onclick="xuanshangSubmit();" value="确定悬赏？" style="height: 50px;">
				</div>
			</form> 
	</div>
	<script type="text/javascript">
//删除买标信息js  001
function xuanshang(obj){
   //alert(obj.tagName);
	//alert($(obj).prev().tagName);
	var pp = obj.nextSibling;
	var cnt = pp.innerText;
	//alert(cnt);
	document.getElementById("successfulBidder").setAttribute("value", cnt);
	document.getElementById("xuanshang").style.display="block";
	document.getElementById("xuanshang-bg").style.display = 'block';
}
function hide(){
    document.getElementById("xuanshang").style.display="none";
    document.getElementById("xuanshang-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
 $('#xuanshang-bg').click(function(){
	$('#xuanshang,#xuanshang-bg').hide();
	
}); 
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-xuanshang').click(function(){
    $('#xuanshang,#xuanshang-bg').hide();
  // location.reload();
});
</script>
	
	<script type="text/javascript"> <!--我要买标弹出框js -->

	$('#comment').focus(function(){
		var x = $('#loginspan').text();
		
		if(x == "登录"){
		
	         $("#popup").show();//查找ID为popup的DIV show()显示#gray
	         tc_center();
	         $('.loginV2').show();
	         $('.registerBox').hide();
	         $('.form-error').hide();
	    
	     $(".td").click(function(){
	         $("#popup").show();//查找ID为popup的DIV show()显示#gray
	         tc_center();
	         $('.loginV2').hide();
	         $('.registerBox').show();
	         $('.form-error').hide();
	     });
	     //点击关闭按钮
	     $("a.guanbi").click(function(){
	         $("#popup").hide();//查找ID为popup的DIV hide()隐藏
	     })
	     //点击退出按钮
	     $(".logout").click(function(){
	    	 $.ajax({
	 			url : ctx + "/logout.json",
	 			type : "POST",
	         	dataType : 'json',
	 			success : function(data) {
	 				window.location.reload();
	 				return true;
	 			},
	 			error : function(data) {
	 				alert(data.responseText);
	 				return false;
	 			}
	 		});
	     });

	     document.getElementById("denglu-dialog-bg").style.display = 'block';
			
			// 点击弹窗背景关闭当前弹窗
				$('#denglu-dialog-bg').click(function() {
					$('#popup').hide();
					$('#denglu-dialog-bg').hide();
				});
				// 点击弹窗的关闭按钮关闭当前弹窗
				
				$("a.guanbi").click(function() {
					$("#popup").hide();//查找ID为popup的DIV hide()隐藏
					$('#denglu-dialog-bg').hide();
				})
	     
	     //窗口水平居中
	     $(window).resize(function(){
	         tc_center();
	     });

	     function tc_center(){
	         var _top=($(window).height()-$(".popup").height())/2;
	         var _left=($(window).width()-$(".popup").width())/2;
	         
	         $(".popup").css({top:_top,left:_left});
	     }   
	     }
		else{
	    return true;
		}
	
	})
	
	function show1(obj){
	   //alert(obj.tagName);
		//alert($(obj).prev().tagName);
		var pp = obj.nextSibling;
		var cnt = pp.innerText;
		//alert(cnt);
		document.getElementById("toId").setAttribute("value", cnt);
		var x = $('#loginspan').text();
		if(x == "登录"){
	         $("#popup").show();//查找ID为popup的DIV show()显示#gray
	         tc_center();
	         $('.loginV2').show();
	         $('.registerBox').hide();
	         $('.form-error').hide();
	     $(".td").click(function(){
	         $("#popup").show();//查找ID为popup的DIV show()显示#gray
	         tc_center();
	         $('.loginV2').hide();
	         $('.registerBox').show();
	         $('.form-error').hide();
	     });
	     //点击关闭按钮
	     $("a.guanbi").click(function(){
	         $("#popup").hide();//查找ID为popup的DIV hide()隐藏
	     })
	     //点击退出按钮
	     $(".logout").click(function(){
	    	 $.ajax({
	 			url : ctx + "/logout.json",
	 			type : "POST",
	         	dataType : 'json',
	 			success : function(data) {
	 				window.location.reload();
	 				return true;
	 			},
	 			error : function(data) {
	 				alert(data.responseText);
	 				return false;
	 			}
	 		});
	     });

	     document.getElementById("denglu-dialog-bg").style.display = 'block';
			
			// 点击弹窗背景关闭当前弹窗
				$('#denglu-dialog-bg').click(function() {
					$('#popup').hide();
					$('#denglu-dialog-bg').hide();
				});
				// 点击弹窗的关闭按钮关闭当前弹窗
				
				$("a.guanbi").click(function() {
					$("#popup").hide();//查找ID为popup的DIV hide()隐藏
					$('#denglu-dialog-bg').hide();
				})
	     
	     //窗口水平居中
	     $(window).resize(function(){
	         tc_center();
	     });

	     function tc_center(){
	         var _top=($(window).height()-$(".popup").height())/2;
	         var _left=($(window).width()-$(".popup").width())/2;
	         
	         $(".popup").css({top:_top,left:_left});
	     }   
	     }
		else{
	    document.getElementById("reply-dialog").style.display="block";
	    document.getElementById("reply-dialog-bg").style.display = 'block';
		}
	}
	         
	function hide(){
	    document.getElementById("reply-dialog").style.display="none";
	    document.getElementById("reply-dialog-bg").style.display = 'none';
	}
	// 点击弹窗背景关闭当前弹窗
	$('#reply-dialog-bg').click(function(){
		$('#reply-dialog,#reply-dialog-bg').hide();
	});
	// 点击弹窗的关闭按钮关闭当前弹窗
	$('#close-dialog1').click(function(){
	    $('#reply-dialog,#reply-dialog-bg').hide();
	});
	


</script>
	
	
	
	//下面全是和登录相关的js
<script type="text/javascript">
     $(document).ready(function(){ 

         $(".top_nav").mousedown(function(e){ 
             $(this).css("cursor","move");//改变鼠标指针的形状 
             var offset = $(this).offset();//DIV在页面的位置 
             var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离 
             var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离 
             $(document).bind("mousemove",function(ev){ //绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件 
             
                 $(".popup").stop();//加上这个之后 
                 
                 var _x = ev.pageX - x;//获得X轴方向移动的值 
                 var _y = ev.pageY - y;//获得Y轴方向移动的值 
             
                 $(".popup").animate({left:_x+"px",top:_y+"px"},10); 
             }); 

         }); 

         $(document).mouseup(function() { 
             $(".popup").css("cursor","default"); 
             $(this).unbind("mousemove"); 
         });
         
         var zbstatus = $("#zbstatus").text();
        
         if(zbstatus == "已中标"){
        	 $(".tome").hide();
         }
         
     }); 
</script>



<script type="text/javascript">
    var _wx_server_qr_code_count = 0;
    var _wx_server_qr_code_loaded = false;
    var _qr_code_limited = '';
    var _qr_code_wait_time = 20;
    var flashQrCodeWaitingTimer = null;
    var getQrCodeStatusTimer = null;
    var getQrCodeTimer = null;
    var _mobile_regu =/^1[3|4|5|8][0-9]\d{4,8}$/; 
    var _mobile_reg = new RegExp(_mobile_regu);
    
    //帐号密码登录
    function nameLoginCheck(){
        var loginName = $("#nameLoginForm").find("#normalUser").eq(0).val();
        var password = $("#nameLoginForm").find("#normalPassword").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        if(loginName == null  || loginName == ""){
            showError("请输入用户名");
            return false;
        }
        if(password == null  || password == ""){
            showError("请输入密码");
            return false;
        }
        if($("#normalYzm")  && $("#nameLoginForm").find("#normalYzm").length > 0 ){
            if($("#normalYzm").val() == "" || $("#normalYzm").val() == null){
                showError("请输入验证码");
                return false;
            }
        }
        
        var ajaxResult;
        $.ajax({
			url : ctx + "/nameLogin.json",
			type : "POST",
			data : {
				username : loginName,
				passwd : password
			},
        	dataType : 'json',
        	async : false,
			success : function(data) {
				if (data.type == 'success') {
					return true;
				} else {
					showError(data.msg);
					ajaxResult = false;
					return false;
				}
			},
			error : function(data) {
				showError(data.responseText);
				ajaxResult = false;
				return false;
			}
		});
        
        if (ajaxResult == false) {
        	return false;
        }
        return true;
    }
    
    //手机登陆验证
    function mobileLoginCheck(){
        var mobile = $("#mobileLoginForm").find("#partnerPhone").eq(0).val();
        var captch = $("#mobileLoginForm").find("#partnerYzm").eq(0).val();
        var code = $("#mobileLoginForm").find("#partnerJym").eq(0).val();
        if(mobile == null || mobile == '' || !(_mobile_reg).test(mobile)){
            showError("请填写正确的手机号");
            return false;
        }
        if(captch == null || captch == "" || captch == undefined){
            showError("请填写验证码");
            return false;
        }
        if(code == null || code == ""){
            showError("请填写校验码");
            return false;
        }
        return true;
    }

    //注册
    function registerCheck(){
    	var mobile = $("#registerForm").find("#nomalMobile").eq(0).val();
        var password = $("#registerForm").find("#normalPassword").eq(0).val();
        var rePassword = $("#registerForm").find("#reNormalPassword").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        if(mobile == null  || mobile == ""){
            showError("请输入手机号");
            return false;
        }
        if(password == null  || password == ""){
            showError("请输入密码");
            return false;
        }
        if(rePassword == null  || rePassword == ""){
            showError("请再次输入密码");
            return false;
        }
        if(password != rePassword){
        	showError("两次输入密码不一致");
            return false;
        }
        //if($("#normalYzm")  && $("#registerBox").find("#normalYzm").length > 0 ){
        //    if($("#normalYzm").val() == "" || $("#normalYzm").val() == null){
        //        showError("请输入验证码");
        //        return false;
        //    }
        //}
        
        var ajaxResult;
        $.ajax({
			url : ctx + "/register.json",
			type : "POST",
			data : {
				mobile : mobile,
				passwd : password
			},
        	dataType : 'json',
        	async : false,
			success : function(data) {
				if (data.type == 'success') {
					return true;
				} else {
					showError(data.msg);
					ajaxResult = false;
					return false;
				}
			},
			error : function(data) {
				showError(data.responseText);
				ajaxResult = false;
				return false;
			}
		});
        
        if (ajaxResult == false) {
        	return false;
        }
        return true;
    }
    
    //注册
    function iagreeChange(){
        if(!$('#iagree').is(':checked')) {
            showError("请选择同意协议");
            return;
        }else{
            closeError();
        }
    }
  
    function mobileCheck(obj){
        if(!(_mobile_reg).test($("#nomalMobile").val())){
            showError("请填写正确的手机号");
            return;
        }else{
            closeError();
        }
    }
    
    //发送短信
    function sendSms(obj){
        alert("信息已发送");
    }
    
    function captchCheck(obj){
        if(!(_mobile_reg).test($("#partnerPhone").val())){
            showError("请填写正确的手机号");
            return;
        }
        var captch = $(obj).val();
        if(captch == '' || captch == null){
            showError("请填写验证码");
        }else{
            checkCaptch(captch,
                        function(){
                            if(!$("#smsSendButton").hasClass("sending")){
                                $("#smsSendButton").removeClass("disabled");
                            }
                             closeError();
                        },function(){
                            showError("验证码错误");
                             $("#smsSendButton").addClass("disabled");
                        }
            );
        }
    }
    
    $(function(){
        $(".form-tab li").on("click",function(){
            var index = $(this).index();
            $(this).addClass("cur").siblings().removeClass("cur");
            $(".form-con>div").hide().eq(index).show();
            if(index == 0){
                $(".form-foot").hide();           
            }else{
                $(".form-foot").show();
            }
            $(".form-error").hide();
        });
        $(".weixin-login .help-a").hover(
            function(){
                $(".wx-img-box,.wx-image").stop();
                $(this).parents(".weixin-login").find(".wx-img-box").animate({"marginLeft":"15px"},300,function(){
                    $(this).parents(".weixin-login").find(".wx-image").animate({"opacity":1},300);
                });
            },
            function(){
                $(".wx-img-box,.wx-image").stop();
                $(this).parents(".weixin-login").find(".wx-image").stop().animate({"opacity":0},300,function(){
                    $(this).parents(".weixin-login").find(".wx-img-box").animate({"marginLeft":"110px"},300);
                });
            }
        );
    
        
    });
    
    $('.jia_foot_open').click(function(){
        $('.footnone').slideToggle();
        $(this).find('i').toggleClass('footnow');
    });
    $('.register').click(function(){
        $('.loginV2').hide();
        $('.registerBox').show();
        $('.form-error').hide();
    });

    $('.phoneLogin').click(function(){
        $('.loginV2').hide();
        $('.shortLogin').show();
        $('.form-error').hide();
    });
    $('.backLogin').click(function(){
         $('.login-normal').show();
        $('.loginV2').show();
        $('.registerBox').hide();
        $('.shortLogin').hide();
        $('.form-error').hide();
    });
    //开启错误提示
    function showError(error){
        $(".form-error").find("label").html(error);
        $(".form-error").show();
    }
</script>





<!--qq登录用 -->
<script>
      function qqlogin(){
            QC.Login({}, function (reqData, opts) {//登录成功
                getInfo();
            }, function (opts) {
                alert('注销成功');
            }
            );
            QC.Login.showPopup({
                   appId:"101442633",
                   redirectURI:"http://localhost:8182/gssb/index.html"
                });
        }
        
      function getInfo() {
            if(QC.Login.check()){
                QC.api("get_user_info")
                    .success(function(s){//成功回调
                        QC.Login.getMe(function(openId, accessToken){
                            
                            var _data={loginName:s.data.nickname,openId:openId,otype:1,token:accessToken};
                            console.log(_data);
                            $.ajax({
                                url:ctx +"/qqlogin.ajax",
                                type:"POST",
                                data:_data,
                                dataType:'json',
                                success:function(result) {
                                    if(result.code==200){              
                                        //登录成功 ,跳转回前一个页面
                                        window.location.href=ctx + '/';
                                    }else{
                                       if(result.code==101){
//                                       $("#openId").val(result.openId);
//                                       console.info(result);
//                                       $("#loginName_qw").val(result.loginName);   
//                                        var fm=document.getElementById("qqcheckForm");  
//                                       // fm.action="";
//                                        fm.submit(); 
                                        }
//                                      这里跳转到一个补充手机号注册流程的页面。 openid也保存到数据库中   
                                    }
                                }
                            });
//                          qq授权后台处理思路：通过点击qq登录，登录成功后回调，在回调中通过 QC.api("get_user_info")获取登录后的信息，
//                                                                  在后台通过qq的openid来查询数据库，若是库中有值，则直接进入登录成功流程，若是没有值则跳转到手机号注册流程。
                        })
                    })
                    .error(function(f){//失败回调
                        alert("获取用户信息失败！登录失败！");
                        location.href = "/";
                    })
                    .complete(function(c){//完成请求回调
                          alert("获取用户信息完成！");
                    });
            }else{
                alert("请先登录qq！");
                location.href = ctx + '/';
            }
        }
  </script>
  
    <script type="text/javascript">
	$(document).ready(function(){
		$(".gbjType").each(function() {
			if($(this).text() == "0"){
				$(this).text("商标") ;
			}
			if($(this).text() == "1"){
				$(this).text("专利");
			}
			if($(this).text() == "2"){
				$(this).text("版权") ;
			}
		});
	});
</script>

<script type="text/javascript">
//判断是否有评论
	$(document).ready(function(){
		if($("#li-comment-2").length>0){
			$("#no-comments").css("display","none");
		}else{
			$("#no-comments").css("display","block");
		} 
	});
</script>

 <script type="text/javascript">
//更改日期格式
	$(document).ready(function(){
		var cnt = $('#time').text().toString();
		var len = cnt.length;
		var year;
		var month;
		var date;
		var h_m_s;

		var mon = [['Jan','01'],['Feb','02'],[' Mar','03'],
				   ['Apr','04'],['May','05'],['Jun','06'],
				   ['Jul','07'],['Aug','08'],['Sep','09'],
				   ['Oct','10'],['Nov','11'],['Dec','12']];
		var ans;
		year = cnt.substring(24,28);
		//alert(cnt.substring(4,7));
		month = cnt.substring(4,7)
		for(var i=0; i<12; i++){
			//alert(i);
			if(mon[i][0] == month){
				month = mon[i][1].toString();
				break;
			}
		}
		date = cnt.substring(8,10);
		h_m_s = cnt.substring(11,20);
		ans = year.concat('-',month,'-',date,' ',h_m_s);
		$('#time').html(ans);
	});
</script>
  
</body>
<!--qq登录用 -->
</html>
/html>