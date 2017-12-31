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
<script type="text/javascript"
	src="${ctxStatic }/front/js/Articlebuy.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/gbBuy.js"></script>
<script type="text/javascript"
	src="${ctxStatic }/front/js/buyupcounts.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		//var x = "xxxxxxxxxxxxx";
		
		//$('user_id').val("zzzzzzzzzzz");
		//alert($('user_id').val());
		
		/* if ($('#gbjType').text() == '0') {
			$('#gbjType').text('商标');
		}
		if ($('#gbjType').text() == '1') {
			$('#gbjType').text('专利');
		}
		if ($('#gbjType').text() == '2') {
			$('#gbjType').text('版权');
		} */
	});
</script>

<body ms-controller="buyarticles">
	<%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
	<div class="about">
		<div class="container">
			<h1>
				买标信息<span class="m_1"><br>最新发布的买标信息，如有兴趣请联系123456789</span>
			</h1>
		</div>
	</div>
	<!-- Start of Page Container -->
	<div class="page-container">
		<div class="container">
			<div class="row">
				<!-- start of page content -->
				<div class="span8 main-listing">
					<div class="span12 page-content"></div>
					<article class=" page type-page hentry clearfix">
						<h1 class="post-title">
							<a href="#">买标全部交易信息</a>
						</h1>
						<hr>
					</article>
					<article class="format-standard type-post hentry clearfix"
						ms-repeat-el="datas.domainBuyArticleList">
						<header class="clearfix">
							<h3 class="post-title">
								<a ms-attr-href="${ctx }/single.html?id={{el.id}}&type=buy">{{el.title}}</a>
							</h3>
							<div class="post-meta clearfix ">
								<span class="date">{{el.createDate}}</span> <span
									class="category"><a href="#" title="查询该标签所有内容"
									id="gbjType">{{el.typeId}}</a> &amp;&amp;&amp;<a href="#"
									title="查询该标签所有内容"> 餐饮</a></span> <span class="comments"><a
									href="#">3个回复</a></span> <a
									ms-attr-href="${ctx}/single.html?id={{el.id}}&type=buy"><span
									class="like-count">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{el.upCounts}}
								</span></a>
							</div>
							<!-- end of post meta -->
						</header>
						<p>
							{{el.description}}<a class="readmore-link" href="#">查看全部</a>
						</p>
					</article>
					<div id="pagination">
						<a href="#" class="btn active">1</a> <a href="#" class="btn">2</a>
						<a href="#" class="btn">3</a> <a href="#" class="btn">下一页 ></a> <a
							href="#" class="btn">最后一页 >></a>
					</div>
				</div>
				<!-- end of page content -->
				<!-- start of sidebar -->
				<aside class="span4 page-sidebar">
					<div class="row-fluid top-cats" style="text-align: right;">
						<a href="#buy-dialog" onclick="show1()">
							<section class="span6">
								<img src="${ctxStatic }/images/btn1.png" style="margin-left: 30px; width: 220px;"/>
								
							</section>
						</a>
					</div>
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
	<div id="buy-dialog-bg"
		style="width: 100%; height: 100%; position: fixed; top: 0; left: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1; display: none;"></div>
	<div id="buy-dialog" ms-controller="buy-dialog"
		style="position: fixed; background: rgb(249, 249, 249); top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 10; display: none;">
		<div id="close-dialog1"
			style="position: absolute; right: -10px; top: -14px; width: 24px; height: 24px; text-align: center; font-size: 25px; border: 2px solid #d2d1d1; border-radius: 50%; background-color: #fff; color: #e71a1a; cursor: pointer;">×</div>
		<form id="domainform" action="${ctx }/buyarticles.html" method="post"
			ms-widget="validation" class="form-horizontal"
			style="padding: 20px 30px; margin: 0;">
			<h1 class="post-title">
				<a href="#">我要买标</a>
			</h1>
			<p class="comment-notes">请输入您需要发布的信息。专业顾问人工查询，结果分析更准确！</p>

			<div>
				<label for="title">商标名称 *</label> <input class="span4" type="text"
					name="title" id="title"
					ms-duplex-required="datas.domainInfo1.title">
					
			</div>
			 <div>
				<label for="mobile">用户id</label>
				 <input class="span4" name="user_id" type="text"  id="user_id" 
				 value="${login_user.id}"
				 ms-duplex="datas.domainInfo1.user_id" >
			</div> 
			<div>
				<label for="mobile">联系电话 *</label> <input class="span4" type="text"
					name="mobile" id="mobile"
					ms-duplex-required="datas.domainInfo1.mobile">
			</div>
			<div>
				<label for="mobile">联系人*</label> <input class="span4" type="text"
					name="realname" id="realname"
					ms-duplex-required="datas.domainInfo1.realname">
			</div>


			<div class="payment-sendbtns">
				<input class="btn" name="submit" type="submit" id="submit01"
					value="提交查询">
			</div>
		</form>
	</div>
	
	<script type="text/javascript"> <!--我要买标弹出框js -->
function show1(){
	
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
    document.getElementById("buy-dialog").style.display="block";
    document.getElementById("buy-dialog-bg").style.display = 'block';
	}
}
         
function hide(){
    document.getElementById("buy-dialog").style.display="none";
    document.getElementById("buy-dialog-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
$('#buy-dialog-bg').click(function(){
	$('#buy-dialog,#buy-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialog1').click(function(){
    $('#buy-dialog,#buy-dialog-bg').hide();
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
     }) 
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
	
	
</body>
</html>
