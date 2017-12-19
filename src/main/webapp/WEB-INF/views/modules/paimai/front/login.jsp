<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
        <!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
        <!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
        <!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
        <!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
        <head>
                
			<%@ include file="/WEB-INF/views/include/frontHead1.jsp"%>
			
			<style type="text/css">
			    .btn_tab_login{float: right; margin-top: 48px;}
			    .btn_tab_login li{display: inline-block; margin-left:30px; font-size: 14px;}
			    .btn_tab_login li.cur a{color:#d00;}
			</style>
			<style type="text/css">
				#weixin_login_container iframe{
				    width:158px;
				    height:158px;
				}
			</style>
			
        </head>

        <body>

                <%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
                <div class="login-wrap">
				    <div class="wrap clearfix">
				        <div class="form-box fr loginV2"  style="display:block;">
				            <ul class="form-tab clearfix">
				                <li class="tab-li"><a href="javascript:;" ></i>微信登录</a></li>
				                <li class="tab-li cur"><a href="javascript:;" >账号登录</a></li>
				            </ul>
				            <div class="form-con">
				                <div class="weixin-login" style="display:none;">
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
				                </div>
				                <div class="login-normal" style="display:block;">
				                    <form id="nameLoginForm" method="post" autocomplete="off" onsubmit="return nameLoginCheck();">
				                        <div class="form-error" style=""><i></i><label class="text"></label></div>
				                        <dl class="clearfix">
				                            <dt>账户名：</dt>
				                            <dd><input type="text" name="loginName" id="normalUser" class="input-text" autocomplete="off" /><span class="placeholder">用户名/邮箱/手机号</span></dd>
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
				                                <a href="javascript:;" >手机快捷登录</a>
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
				            
				    </div>
				</div>
                <!-- End of Page Container -->
                
                 <!-- Start of Footer -->
                <%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>  
                <!-- End of Footer -->

                <a href="#top" id="scroll-top"></a>
                
        </body>
</html>

<script type="text/javascript">
	var _wx_server_qr_code_count = 0;
	var _wx_server_qr_code_loaded = false;
	var _qr_code_limited = '';
	var _qr_code_wait_time = 20;
	var flashQrCodeWaitingTimer = null;
	var getQrCodeStatusTimer = null;
	var getQrCodeTimer = null;
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
	
	function mobileCheck(obj){
	    if(!(_mobile_reg).test($("#partnerPhone").val())){
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
	            //www.sucaihuo.com 素材火
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
	$('.phoneLogin').click(function(){
	    $('.loginV2').hide();
	    $('.shortLogin').show();
	    $('.form-error').hide();
	});
	$('.backLogin').click(function(){
	     $('.login-normal').show();
	    $('.loginV2').show();
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
// 		                                 $("#openId").val(result.openId);
// 		                                 console.info(result);
// 		                                 $("#loginName_qw").val(result.loginName);   
// 		                                  var fm=document.getElementById("qqcheckForm");  
// 		                                 // fm.action="";
// 		                                  fm.submit(); 
		                                }
// 		                            	这里跳转到一个补充手机号注册流程的页面。 openid也保存到数据库中   
		                            }
		                        }
		                    });
// 		                    qq授权后台处理思路：通过点击qq登录，登录成功后回调，在回调中通过 QC.api("get_user_info")获取登录后的信息，
// 		                                                            在后台通过qq的openid来查询数据库，若是库中有值，则直接进入登录成功流程，若是没有值则跳转到手机号注册流程。
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