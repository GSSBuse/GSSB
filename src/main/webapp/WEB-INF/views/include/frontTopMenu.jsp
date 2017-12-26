<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101442633" charset="utf-8"></script>
<div class="top">
    <div class="mainWidth top-notic">
        <div class="top-left f-l">
            <span class="f-l">站内公告：</span>
            <span class="f-l pic"><a href="#">国标商标最新商标发布，年终打5折！</a></span>
        </div>
        <div class="top-right f-r">
        	<span>咨询电话：029-12345678/87654321</span>
        	<c:choose>
        		<c:when test="${!empty login_user.username}">
	        		<span>${login_user.username}，您好</span>
					| <span><a class="icenter" href="#">个人中心</a></span>
					| <span><a class="logout" href="#">退出</a></span>
        		</c:when>
        		<c:otherwise>
        			<span><a class="tc" href="#">登录</a>
        			| <a class="td" href="#">注册</a></span>
        		</c:otherwise>
        	</c:choose>
            <a href="#">加入收藏</a>
        </div>
    </div>
    <div style="clear:both;"></div>
    
</div>
<div class="header-wrapper" >
        <header>
                <div class="container">
                        <div class="logo-container">
                                <!-- Website Logo -->
                                <img style="width:30%;" src="${ctxStatic }/images/logo.png"  align="left">
                        </div>                
                
                        <!-- Start of Main Navigation -->
                        <nav class="main-nav">
                                <div class="menu-top-menu-container">
                                        <ul id="menu-top-menu" class="clearfix">
                                            <li class="current-menu-item"><a href="${ctx }/index1.html">首页</a></li>
                                            
                                            <li><a href="#">动态信息</a>                                            
                                                   <ul class="sub-menu" style="z-index:123">
                                                           <li><a href="${ctx }/buyarticles.html">买标</a></li>
                                                           <li><a href="${ctx }/soldarticles.html">卖标</a></li>
                                                           <li><a href="${ctx }/rewardarticles.html">悬赏起名</a></li>
                                                   </ul>
                                            </li>
                                            
                                            <li><a href="${ctx }/articles.html">商标查询</a></li>
                                            
                                            <li><a href="${ctx }/registers.html">商标注册</a>
                                            		<ul class="sub-menu" style="z-index:123;">
                                            				<li><a href="trademark.jsp">商标许可</a></li>
                                            				<li><a>商标变更</a></li>
                                            				<li><a>商标续展</a></li>
                                            				<li><a>商标补证</a></li>
                                            				<li><a>为什么要及时注册商标</a></li>
                                            				<li><a>商标注册的好处</a></li>
                                            				<li><a>商标注册存在的客观风险</a></li>
                                            				<li><a>商标注册的时间、程序、费用</a></li>
                                            		</ul>
                                            </li>
                                            
                                            <li><a href="${ctx }/todo.html">专利服务</a>
	                                            <ul class="sub-menu" style="z-index:123;">

	                                            		<span style="color:gray">国内专利</span>
	                                            		<li><a href="trademark.jsp">发明专利申请</a></li>
	                                            		<li><a>实用新型专利申请</a></li>
	                                            		<li><a>外观设计专利申请</a></li>
													
														<span style="color:gray">国际专利</span>
	                                            		<li><a>PCT途径</a></li>
	                                            		<li><a>巴黎公约途径</a></li>
	                                            		<li><a>香港专利申请</a></li>
	                                            		<li><a>美国专利申请</a></li>
	                                            	
														<span style="color:gray">专利管理交易</span>
	                                            		<li><a>专利实施许可合同备案</a></li>
	                                            		<li><a>专利著录项目变更</a></li>
	                                            		<li><a>专利权利恢复</a></li>
	                                            		<li><a>专利年费代缴</a></li>
	                                            		<li><a>专利权评价报告</a></li>
	                                            	
	                                            </ul>
                                            </li>
                                            <li><a href="${ctx }/faqs.html">版权登记</a></li>
                                            <li><a href="${ctx }/testimonials.html">国际商标</a></li>
                                            <li><a href="${ctx }/contact.html">法律服务</a></li>
                                            <li><a href="${ctx }/todo.html">知识产权托管</a></li>
                                        </ul>
                                </div>
                        </nav>
                        <!-- End of Main Navigation -->
                
                </div>
        </header>
</div>
<!-- End of Header -->

<div class="main-im">
    <div id="open_im" class="open-im">&nbsp;</div>  
    <div class="im_main" id="im_main">
        <div id="close_im" class="close-im"><a href="javascript:void(0);" title="点击关闭">&nbsp;</a></div>
        <a href="http://wpa.qq.com/msgrd?v=3&uin=4005958&site=qq&menu=yes" target="_blank" class="im-qq qq-a" title="在线QQ客服">
            <div class="qq-container"></div>
            <div class="qq-hover-c"><img class="img-qq" src="${ctxStatic }/images/qq.png"></div>
            <span> QQ在线咨询</span>
        </a>
        <div class="im-tel">
            <div>售前咨询热线</div>
            <div class="tel-num">400-123-45678</div>
            <div>售后咨询热线</div>
            <div class="tel-num">010-12345678</div>
        </div>
        <div class="im-footer" style="position:relative">
            <div class="weixing-container">
                <div class="weixing-show">
                    <div class="weixing-txt">微信扫一扫<br>打开国标商标</div>
                    <img class="weixing-ma" src="${ctxStatic }/images/weixing-ma.jpg">
                    <div class="weixing-sanjiao"></div>
                    <div class="weixing-sanjiao-big"></div>
                </div>
            </div>
            <div class="go-top"><a href="javascript:;" title="返回顶部"></a> </div>
            <div style="clear:both"></div>
        </div>
    </div>
</div>
<script>
$(function(){
    $('#close_im').bind('click',function(){
        $('#main-im').css("height","0");
        $('#im_main').hide();
        $('#open_im').show();
    });
    $('#open_im').bind('click',function(e){
        $('#main-im').css("height","272");
        $('#im_main').show();
        $(this).hide();
    });
    $('.go-top').bind('click',function(){
        $(window).scrollTop(0);
    });
    $(".weixing-container").bind('mouseenter',function(){
        $('.weixing-show').show();
    })
    $(".weixing-container").bind('mouseleave',function(){        
        $('.weixing-show').hide();
    });
});
</script>


<!-- Start of 登录框 -->
<div class="popup" id="popup">
    <div class="top_nav" id='top_nav'>
                <div align="center">
                    <a class="guanbi"></a>
                </div>
            </div>
    <div class="form-box fr loginV2"  style="display:block;">
         <ul class="form-tab clearfix">
			<!-- <li class="tab-li"><a href="javascript:;" ></i>微信登录</a></li> -->
             <li class="tab-li cur"><a href="javascript:;" >账号登录</a></li>
         </ul>
         <div class="form-con">
             <!-- <div class="weixin-login" style="display:none;">
                 <div class="wx-box clearfix">
                     <a href="javascript:;" class="wx-img-box">
                         <img class="wx-qrCode" src="${ctxStatic }/images/ewm.jpg" id="qrCodeImg">
                         <img class="wx-qrCode-logo" src="${ctxStatic }/images/ewm.jpg" id="qrCodeLogo">
                         <img class="statusImg" src="${ctxStatic }/images/wx-confirm.png" id="statusImg">
                         <div id="weixin_login_container" style="display:none;"></div>
                     </a>
                     <img src="${ctxStatic }/images/wx-image.png" class="wx-image">
                 </div>
                 <p class="wx-text">微信扫一扫  快速登录</p>
                 <p class="wx-help"><a href="javascript:;" class="help-a">如何使用？</a></p>
             </div> -->
             <div class="login-normal" style="display:block;">
                 <form id="nameLoginForm" method="post" autocomplete="off" onsubmit="return nameLoginCheck();">
                     <div class="form-error" style=""><i></i><label class="text"></label></div>
                     <dl class="clearfix">
                         <dt>手机号：</dt>
                         <dd><input type="text" name="loginName" id="normalUser" class="input-text" autocomplete="off" /><span class="placeholder">请输入注册手机号</span></dd>
                     </dl>
                     <dl class="top1 clearfix">
                         <dt>密<em></em>码：</dt>
                         <dd><input type="password" name="password" id="normalPassword" class="input-text"><span class="placeholder">请输入密码</span></dd>
                     </dl>
                     
                     <div class="form-remember">
                         
                         <input name="rememberName" type="checkbox" id="remUser" class="rem-check" style="display:none;" checked="checked">
                         <span class="rem-box rem-box-r memCheck"><input name="rememberMe" type="checkbox" id="remLogin" class="rem-check">三个月之内免登录</span> 
                     </div>
                     <div class="btn-box clearfix">
                         <input id="normalSubmit" class="btn-settlement" type=submit value="登    录"  >
                     
                     </div>
                     <div class="link-box clearfix">
                         <a href="javascript:;" class="register" >新用户注册</a>
                         <a href="javascript:;" class="forget-pass" >忘记密码？</a>
                     </div>
                 </form>
                 <div class="login-short clearfix">
                    <div class="short-left">
                         <h3>使用合作账号登录：</h3>
                         <ul class="clearfix">
                             <li class="qq"><a onclick="qqlogin()" > </a></li>
                             <li class="sina"><a href="#" onclick="javascript:alert('TODO')"></a></li>                                               
                             <li class="weixin"><a href="#" onclick="javascript:alert('TODO')"></a></li>
                         </ul>
                     </div>
                    <div class="short-right">
                         <h3>您还可以选择：</h3>
                         <p class="phone-short clearfix">
                             <i class="phone"></i>
                             <a href="javascript:;" class="txt phoneLogin">手机快捷登录</a>
                         </p>
                    </div>
                 </div>
             </div>
         </div>
     </div>
     <!-- -快捷登录 -->  
     <div class="form-box fr shortLogin" style="display:none;">
         <h5 class="title">快捷登录</h5>
          <div class="form-con">
             <form id="mobileLoginForm" method="post" onsubmit="return mobileLoginCheck();">
                 <div class="form-error" style=""><i></i><label class="text"></label></div>
                 <dl class="clearfix">
                     <dt>手机号：</dt>
                     <dd><input name="mobile" type="text" id="partnerPhone" autocomplete="off" class="input-text mobile" maxlength="11" onblur="mobileCheck(this);"><span class="placeholder">请输入手机号</span></dd>
                 </dl>
                 <dl class="top1 clearfix">
                     <dt>验证码：</dt>
                     <dd>
                         <input name="smsCaptcha" type="text" id="partnerYzm" class="input-yzm" onblur="captchCheck(this);" maxlength="4" autocomplete="off"/>
                         <span class="span-yzm">
                             <img id="smsCaptchaImage" src="${ctxStatic }/images/code.jpg" title="点击图片刷新校验码" alt="点击图片刷新校验码" onclick="changeCode('smsCaptchaImage','partnerYzm');"/>
                             <a href="javascript:changeCode('smsCaptchaImage','partnerYzm');" class="forget-pass">换一张</a>
                         </span>
                     </dd>
                 </dl>
                 <dl class="top2 clearfix">
                     <dt>校验码：</dt>
                     <dd>
                         <input name="code" type="text" id="partnerJym" class="input-jym" maxlength="6" autocomplete="off"/>
                         <a id="smsSendButton" href="javascript:sendSms(this);" class="span-jym disabled" >发送短信校验码</a>
                     </dd>
                 </dl>
                 <div class="form-remember">
                     
                     <input name="rememberName" type="checkbox" id="remUser" class="rem-check" style="display:none;" checked="checked">
                     <span class="rem-box rem-box-r"><input name="rememberMe" type="checkbox" id="remLogin" class="rem-check">三个月之内免登录</span> 
                 </div>
                 <div class="btn-box clearfix">
                     <input id="partnerSubmit" class="btn-settlement" type="submit" value="登    录" >
                     
                 </div>
                 <div class="link-box clearfix">
                      <a href="javascript:;" class="backLogin">返回账号登录>></a>
                 </div>
             </form>
             </div>
     </div>
     <!-- -注册 -->  
     <div class="form-box fr registerBox" style="display:none;">
         <h5 class="title">填写注册信息</h5>
          <div class="form-con">
             <form id="registerForm" method="post" onsubmit="return registerCheck();">
                 <div class="form-error" style=""><i></i><label class="text"></label></div>
                 <dl class="clearfix">
                     <dt>手机号：</dt>
                     <dd><input type="text" name="mobile" id="nomalMobile" autocomplete="off" class="input-text mobile" maxlength="11" onblur="mobileCheck(this);"><span class="placeholder">请输入手机号</span></dd>
                 </dl>
                 
                 <dl class="top1 clearfix">
                     <dt>密<em></em>码：</dt>
                     <dd><input type="password" name="password" id="normalPassword" class="input-text"><span class="placeholder">密码由6-16位字符组成</span></dd>
                 </dl>
                 <dl class="top1 clearfix">
                     <dt>密<em></em>码：</dt>
                     <dd><input type="password" name="rePassword" id="reNormalPassword" class="input-text"><span class="placeholder">请再次输入密码</span></dd>
                 </dl>
                 <dl class="top1 clearfix">
                     <dt>验证码：</dt>
                     <dd>
                         <input name="smsCaptcha" type="text" id="partnerYzm" class="input-yzm" onblur="captchCheck(this);" maxlength="4" autocomplete="off"/>
                         <span class="span-yzm">
                             <img id="smsCaptchaImage" src="${ctxStatic }/images/code.jpg" title="点击图片刷新校验码" alt="点击图片刷新校验码" onclick="changeCode('smsCaptchaImage','partnerYzm');"/>
                             <a href="javascript:changeCode('smsCaptchaImage','partnerYzm');" class="forget-pass">换一张</a>
                         </span>
                     </dd>
                 </dl>
                 <div class="form-remember">
                     <span class="rem-box" onclick="iagreeChange(this);"><input type="checkbox" name="iagree" id="iagree" class="rem-check" checked="checked" />我已阅读并且同意国商局的<a href="#" class="forget-pass" target="_blank">用户协议</a></span>
                 </div>
                 <div class="btn-box">                       
                     <input id="partnerSubmit" class="btn-settlement" type="submit" value="注     册" >
                 </div>
                 <div class="link-box clearfix">
                      <a href="javascript:;" class="backLogin">我已有国商局账号，返回登录>></a>
                 </div>
             </form>
         </div>
     </div>
</div>

    
 <script type="text/javascript">
     //窗口效果
     //点击登录class为tc 显示
     $(".tc").click(function(){
         $("#popup").show();//查找ID为popup的DIV show()显示#gray
         tc_center();
         $('.loginV2').show();
         $('.registerBox').hide();
         $('.form-error').hide();
     });
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
     </script>

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

