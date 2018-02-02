
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
        <!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
        <!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
        <!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
        <!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
        <head>
                <%@ include file="/WEB-INF/views/include/frontHead1.jsp"%> 
              
        </head>
        
<link rel="stylesheet" type="text/css" href="//as.zbjimg.com/??/static/as-common/global/global_d80dcf1.css"/>
<link rel="stylesheet" type="text/css" href="//as.zbjimg.com/??/static/as-ipr-www/widget/2017index/mod_banner_e95bfaf.css,"/>
        <body ms-controller="index1" style="overflow-x: hidden;"> 
                <%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
                
                 <%@ include file="/WEB-INF/views/include/frontIndexBanner.jsp"%>
                
                <!-- Start of 主营业务模块 -->
                <div class="index-page-container">
                       <div id="main-content">
		<div id="li-menu">
			<ul>
				<li class="li-menu-content"><div class="test div-check"><h1>买</h1></div></li>
				<li class="li-menu-content"><div class="test"><h1>卖</h1></div></li>
				<li class="li-menu-content"><div class="test"><h1>悬</h1></div></li>
				<li class="li-menu-content"><div class="test"><h1>淘</h1></div></li>
			</ul>
		</div>
		
		<div class="dy-content check">
		
		<a href="${ctx }/buyarticles.html">
			<div class="dy-each-first dy-buy" id="dy-buy-first">
				<h2 style="color:white;">我要买标</h2>
				<img alt="" src="${ctxStatic }/images/bag.png"/>
			</div>
		</a>
		
		<div class="dy-each"  ms-repeat-el="datas.domainBuyList">
			<a ms-attr-href="${ctx }/single.html?id={{el.id}}&type=buy">
				<div >			
					<h4>
						 <span>{{el.title}}</span>
					</h4>
					<span class="article-meta">		
						<span  title="查询该标签所有内容" class="Type">{{el.typeId=='0'?'商标':el.typeId=='1'?'专利':'版权'}}</span><br>
						{{el.createDate}}
						<%-- &amp; <a ms-attr-href="${ctx }/single.html?id={{el.id}}&type=buy" >{{el.tag}}</a> --%>
					</span> <br>
					
					<span class="like-count">
	                	<img src="${ctxStatic }/images/like.png"/>
	                	<span ms-attr-href="${ctx }/single.html?id={{el.id}}&type=buy">{{el.upCounts}}</span>
	                </span>
				</div>
			</a>
		</div>	
			
			<a href="${ctx }/buyarticles.html">
			<div class="dy-each dy-last">
				<h3>>>查看更多</h3>
				
			</div>
			</a>
			
			

		</div>
		
		<div class="dy-content">
		
		<a href="${ctx }/soldarticles.html">
			<div class="dy-each-first dy-sold" id="dy-sold-first">
				<h2 style="color:white;">我要卖标</h2>
				<img alt="" src="${ctxStatic }/images/bag1.png"/>
			</div>
		</a>
		
		<div class="dy-each"  ms-repeat-el="datas.domainBuyList">
			<a ms-attr-href="${ctx }/single.html?id={{el.id}}&type=buy">			
			<div>
				<h4> 
					<span>{{el.title}}</span>
				</h4>		                                                                
                <span class="article-meta"> 
                	<span class="gbjType">{{el.typeId=='0'?'商标':el.typeId=='1'?'专利':'版权'}}</span><br>  
                	{{el.createDate}} 
                	<%-- &amp; <a ms-attr-href="${ctx }/single.html?id={{el.id}}type=sold">{{el.tag}}</a> --%>
                </span>
                <span class="like-count">
                	<img src="${ctxStatic }/images/like.png"/>
                	<span>{{el.upCounts}}</span>
                </span>
            </div>
            </a>
		</div>

			<a href="${ctx }/soldarticles.html">
			<div class="dy-each dy-last">
				<h3>>>查看更多</h3>
				
			</div>
			</a>
		</div>

		
		<div class="dy-content">
		
		<a href="${ctx }/rewardarticles.html">
			<div class="dy-each-first dy-reword" id="dy-reward-first">
				<h2 style="color:white;">悬赏起名</h2>
				<img alt="" src="${ctxStatic }/images/bag2.png"/>
			</div>
		</a>
		
		<div class="dy-each" ms-repeat-el="datas.domainRewardList">
			<a  ms-attr-href="${ctx }/single.html?id={{el.id}}&type=reward">
			<div>
			  <h4>
			  	<span>悬赏金额: {{el.totalFee}}元</span>
			  	<span class="article-meta">>>点击进入查看详细悬赏</span>
			  	
			  </h4>		                                                                
              <span class="article-meta">{{el.createDate}} &nbsp;&nbsp;&nbsp;&nbsp;
              	<span class="gbjType">悬赏起名</span> 
              	<%-- &amp; <a ms-attr-href="${ctx }/single.html?id={{el.id}}type=reward" >{{el.tag}}</a> --%></span>
              
              <span class="like-count">
                	<img src="${ctxStatic }/images/like.png"/>
                	<span>{{el.upCounts}}</span>
                </span>  
			</div>
				</a>
		</div>
		

			
			<a href="${ctx }/rewardarticles.html">
			<div class="dy-each dy-last">
				<h3>>>查看更多</h3>
				
			</div>
			</a>
			
		</div>


		<div class="dy-content ">
			<a  onclick="show1()">
				<div class="dy-each-first" style="width:75%; height:100%"><img src="${ctxStatic }/images/goldtao.jpg" style="width:100%; height:100%"></div>
			</a>
			<div class="dy-each gold-find" style="width:23%; height:100%">
				<ul class="list-group">
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li><br>
					<li>王先生刚才淘到了200元！</li>
				</ul>
			</div>
		</div>
	</div>
                </div>                
                <!-- End of 主营业务模块 -->
                
                <!-- Start of 动态信息 -->  
                <!-- End of Page 动态信息 -->
                
                <!-- Start of 广告banner-->
                <div style="padding-bottom:10px; margin-top: 544px;">
                    <img src="${ctxStatic }/images/split1.png" style="width:100%"/>
                </div>
                <!-- End of Page 广告banner-->
                
                <!-- Start of 客户之声-->
				<div class="about_grid1">
				<img src="${ctxStatic }/images/split2.png" style="width:100%;margin-bottom:10px;">
				    <div class="container">
				        
				        <div class="wmuSlider example1">
				            <div class="wmuSliderWrapper">
				                <article style="position: absolute; width: 100%; opacity: 0;"> 
				                       <div class="banner-wrap">
				                           <ul class="grid-1">
				                                <li class="grid-1_left">
				                                    <img src="${ctxStatic }/images/s3.jpg" class="img-responsive" alt=""/>
				                                </li>
				                                <li class="grid-1_right">
				                                	<h4>--<br><span class="m_10">胡老板</span></h4>
				                                    <p style="font-style: normal; color: #0000007a;font-size: 15px;">非常好，真的十分靠谱，解决了我的燃眉之急。服务十分专业，进度跟的很紧，我觉得很不错！</p>
				                                    
				                                </li>
				                                <div class="clearfix"> </div>
				                            </ul>
				                       </div>
				                   </article>
				                   <article style="position: absolute; width: 100%; opacity: 0;"> 
				                       <div class="banner-wrap">
				                           <ul class="grid-1">
				                                <li class="grid-1_left">
				                                    <img src="${ctxStatic }/images/s4.jpg" class="img-responsive" alt=""/>
				                                </li>
				                                <li class="grid-1_right">
				                                	<h4>--<br><span class="m_10">蔡老板</span></h4>
				                                    <p style="font-style: normal; color: #0000007a;font-size: 15px;">我在这里买了一个商标，服务进度跟进很及时，效率很高，而且做得很棒，体验很不错。我觉得可以推荐给朋友们！！</p>
				                                    
				                                </li>
				                                <div class="clearfix"> </div>
				                            </ul>
				                       </div>
				                   </article>
				                    <article style="position: absolute; width: 100%; opacity: 0;"> 
				                       <div class="banner-wrap">
				                           <ul class="grid-1">
				                                <li class="grid-1_left">
				                                    <img src="${ctxStatic }/images/s2.jpg" class="img-responsive" alt=""/>
				                                </li>
				                                <li class="grid-1_right">
				                                	<h4>--<br><span class="m_10">王先生</span></h4>
				                                    <p style="font-style: normal; color: #0000007a;font-size: 15px;">买了一个大商标，我觉得很不错，这里的服务很周到，也很耐心，自己基本没怎么操心。我给打五分！</p>
				                                    
				                                </li>
				                                <div class="clearfix"> </div>
				                            </ul>
				                       </div>
				                   </article>
				                </div>
				                <ul class="wmuSliderPagination">
				                    <li><a href="#" class="">0</a></li>
				                    <li><a href="#" class="">1</a></li>
				                    <li><a href="#" class="wmuActive">2</a></li>
				                </ul>
				            </div>
				            <script src="${ctxStatic}/front/js/jquery.wmuSlider.js"></script> 
				              <script>
				                $('.example1').wmuSlider();         
				             </script>                    
				    </div>
				</div>
                <!-- End of Page 客户之声-->
                
                <!-- Start of 合作案例-->
                <div class="about_grid">
                <img src="${ctxStatic }/images/split3.png" style="width:100%;margin-bottom:10px;">
				    <div class="container">
				        <div class="box5">
				          <div class="team_box1">
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic4.jpg" class="img-responsive" alt="" style="margin: 0 auto;"/>
				                <div class="team_desc">
				                    <h3>西安音乐学院</h3>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic5.jpg" class="img-responsive" alt="" style="margin: 0 auto;"/>
				                <div class="team_desc">
                                    <h3>陕西唐都水泥制品有限责任公司</h3>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic6.jpg" class="img-responsive" alt="" style="margin: 0 auto;"/>
				                <div class="team_desc">
                                    <h3>西安工业大学</h3>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic7.jpg" class="img-responsive" alt="" style="margin: 0 auto;"/>
				                <div class="team_desc">
                                    <h3>西安明珠食品有限公司</h3>
				                </div>
				            </div>
				            <div class="clearfix"> </div>
				           </div>
				           <div class="team_box2">
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic8.jpg" class="img-responsive" alt="" style="margin: 0 auto;"/>
				                <div class="team_desc">
                                    <h3>陕西万象灵动有限公司</h3>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic9.jpg" class="img-responsive" alt="" style="margin: 0 auto;"/>
				                <div class="team_desc">
                                    <h3>陕西振丰科工贸有限公司</h3>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic10.jpg" class="img-responsive" alt="" style="margin: 0 auto;"/>
				                <div class="team_desc">
                                    <h3>西安华讯微电子有限公司</h3>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic11.jpg" class="img-responsive" alt="" style="margin: 0 auto;"/>
				                <div class="team_desc">
                                    <h3>西部电影频道</h3>
				                </div>
				            </div>
				            <div class="clearfix"> </div>
				           </div>
				        </div>
				    </div>
				</div>
                <!-- End of Page 客户之声-->
   <!-- 我要买标弹框事件 -->
                <!-- Start of Footer -->
                <%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>   
                <!-- End of Footer -->
                <a href="#top" id="scroll-top"></a>
 <!--    <script type="text/javascript">
		function myclick() {

			var x = $('#loginspan').text();

			if (x == "登录") {
				alert("请登录!登录后即可分享赚取赏金哦!");
				return false;

				$("#popup").show();//查找ID为popup的DIV show()显示#gray
				tc_center();
				$('.loginV2').show();
				$('.registerBox').hide();
				$('.form-error').hide();

				$(".td").click(function() {
					$("#popup").show();//查找ID为popup的DIV show()显示#gray
					tc_center();
					$('.loginV2').hide();
					$('.registerBox').show();
					$('.form-error').hide();
				});
				//点击关闭按钮
				$("a.guanbi").click(function() {
					$("#popup").hide();//查找ID为popup的DIV hide()隐藏
				})
				//点击退出按钮
				$(".logout").click(function() {
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
				$(window).resize(function() {
					tc_center();
				});

				function tc_center() {
					var _top = ($(window).height() - $(".popup").height()) / 2;
					var _left = ($(window).width() - $(".popup").width()) / 2;

					$(".popup").css({
						top : _top,
						left : _left
					});
				}
			} else {
				 return true;
			}
		}
	</script> -->
      	<script type="text/javascript"> <!--评论控制js -->
	function show1(){
	   //alert(obj.tagName);
		//alert($(obj).prev().tagName);
		var x = $('#loginspan').text();
		if(x == "登录"){
	         $("#popup").show();//查找ID为popup的DIV show()显示#gray
	         tc_center();
	         $('.loginV2').show();
	         $('.registerBox').hide();
	         $('.form-error').hide();
	     $(".td").click(function(){
	    	 $("#shortLogin").hide();
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
			window.location.href="${ctx }/goldMiner.html?UserId=${login_user.id}";
		}
	}
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
    	$("#shortLogin").hide();
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
				 	window.location.href="${ctx }/goldMiner.html?UserId=${login_user.id}";
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
        window.location.href="${ctx }/goldMiner.html?UserId=${login_user.id}";
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
    	$("#shortLogin").hide();
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
        $("#registerBox").hide();
        $("#shortLogin").show();
        
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
		var tabList = document.getElementsByClassName("li-menu-content");
		var tabCon = document.getElementsByClassName("dy-content");

		var tabDivCur = new Array();
		tabDivCur[0] = tabList[0].firstChild;
		tabDivCur[1] = tabList[1].firstChild;
		tabDivCur[2] = tabList[2].firstChild;
		tabDivCur[3] = tabList[3].firstChild;
		
		for(i=0;i<tabList.length;i++){
			(function(){
				var t = i;
				
				tabList[t].onmouseover = function(){

					for(o=0;o<tabCon.length;o++){
						tabCon[o].className = "dy-content";
						tabDivCur[o].className = "test";
						
						if(t==o){
							tabCon[o].className += " check";
							tabDivCur[t].className += " div-check";
						}
					}
					
				}
				
			})()
		}
	</script>
	

	
	
<script type="text/javascript" src="${ctxStatic }/front/js/searchBrand.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/gbBuy.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/gbsold.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/gbreward.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/index.js"></script>
        </body>
</html>