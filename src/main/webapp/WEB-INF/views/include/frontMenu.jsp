<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101442633" charset="utf-8"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/searchBrand.js"></script> 
<div class="header-wrapper" >
        <header>
                <div class="container">
                        <div class="logo-container">
                                <!-- Website Logo -->
                                <a href="#"  title="国标商标">
                                        <img style="width:20%;" src="${ctxStatic }/images/logo.png" alt="国标商标">
                                </a>
                        </div>                
                
                        <!-- Start of Main Navigation -->
                        <nav class="main-nav">
                                <div class="menu-top-menu-container">
                                        <ul id="menu-top-menu" class="clearfix">
											<li class="current-menu-item"><a href="${ctx }/index1.html">首页</a></li>
											<li><a href="#">主营业务</a>
											       <ul class="sub-menu">
                                                           <li><a href="${ctx }/articles.html">买标</a></li>
                                                           <li><a href="${ctx }/articles.html">卖标</a></li>
                                                           <li><a href="${ctx }/articles.html">悬赏起名</a></li>
                                                   </ul>
                                            </li>
                                            <li><a href="${ctx }/todo.html">动态信息</a></li>
                                            <li><a href="${ctx }/todo.html">企业要闻</a></li>
                                            <li><a href="${ctx }/todo.html">合作案例</a></li>
                                            <li><a href="${ctx }/testimonials.html">客户之声</a></li>
											<li><a href="${ctx }/faqs.html">帮助中心</a></li>
											<li><a href="${ctx }/contact.html">联系我们</a></li>
											<li><span id="qqLoginBtn"></span></li>
											<li><span id="username"></span></li>
											<li><img id="userimgId" src=""/></li>
                                        </ul>
                                </div>
                        </nav>
                        <!-- End of Main Navigation -->
                
                </div>
        </header>
</div>
<!-- End of Header -->

</script>
    <!--qq登录用 -->
  <script>
        QC.Login({
            //btnId：插入按钮的节点id，必选
            btnId:"qqLoginBtn",
            //用户需要确认的scope授权项，可选，默认all
            scope:"all",
            //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
            size: "B_M"
        }, function(reqData, opts){//登录成功
            //根据返回数据，更换按钮显示状态方法
            console.log(reqData);//查看返回数据
            QC.Login.getMe(function(openId, accessToken){//获取用户的openId
                console.log('QQOPENID:'+openId);
                //thirdparty(null,null,reqData.figureurl_qq_1,reqData.nickname,1,openId);
                $("#userimgId").attr('src',reqData.figureurl_qq_1); 
                $('#username').html(reqData.nickname);
                //QC.Login.signOut();//退出QQ登录调用事件
            });
        }
    );
  </script>






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










<!-- 下面的待删除 -->
<!-- 				<ul id="uiMainNav"> -->
<%--           			<li><a href="${ctx }/index.html">首页</a></li> --%>
<%--           			<li><a href="${ctx }/ibuy.html" class="">我要买</a></li> --%>
<%--           			<c:if test="${ currentClient != null}" > --%>
<%-- 	          			<li><a href="${ctx }/isell.html">我要卖</a></li> --%>
<%-- 	          			<li><a href="${ctx }/icenter.html">个人中心</a> --%>
<!-- 	          				<div class="sub"> -->
<%-- 	          					<a href="${ctx }/icenter.html">个人信息</a> --%>
<%-- 	          					<a href="${ctx }/financialManagement/financeInfo.html">财务管理</a> --%>
<%-- 	          					<a href="${ctx }/myTransactions.html">我的交易</a> --%>
<%-- 	          					<a href="${ctx }/securitysetting.html">安全设置</a> --%>
<!-- 	          				</div> -->
<!-- 	          			</li> -->
<%--           			</c:if> --%>
<!--           		</ul> -->
          		
<!--           		<div id="uiBlueBar"> -->
<%--           			<a id="uiLogo" href="${ctx }/index.html" title="首页">拍域名</a> --%>
<!--           			<div id="uiSearch"> -->
<%--           				<form method="get" action="${ctx }/ibuy.html"> --%>
<!--           					<span class="table-cell search-input"> -->
<!--           						<input type="hidden" name="keyword"> -->
<!--           						<input type="hidden" name="search_trigger" value="off"> -->
          						
<!--           						<span class="input-wrapper"> -->
<!--           							<span class="search-input"> -->
<%--           								<input class="mainSearch" type="text" value="${searchDomain}" name="domain" id=""> --%>
<!--           							</span> -->
<!-- 	          						<span id="">
<!-- 		          						<div class="tld-dialog-selector"> -->
<!-- 		          							<span class="tld-dialog-selector-text">后缀</span> -->
<!-- 		          							<span class="tld-dialog-selector-toggle"></span> -->
<!-- 		          						</div> -->
<!-- 	          						</span> -->
<!--           						</span> -->
<!--           					</span> -->
<!--           					<span class="table-cell search-button" id="submitSearch"><a href="" class="button green L left"><span>搜索</span></a></span> -->
<!--           				</form> -->
<!--           			</div> -->
<!--           			<div id="uiLoginSignup"> -->
<!--           				<div id="uiLoginBox"> -->
<%--           					<c:if test="${ currentClient != null}" > --%>
<!--           					<div class="loggedin" id="js_showUserNotifications"  -->
<%--           							<c:if test="${ currentClient == null}">style="display: none;"</c:if> --%>
<%--           							<c:if test="${ currentClient != null}">style="display: block;"</c:if>> --%>
<!--           						<div id="" class=" formular"  > -->
<!-- 		          						<div class="loggedin" id="" style=""> -->
<!-- 		          							<span> -->
<%-- 		          								<strong class="name">您好 ${currentClient.nickname }!</strong>  --%>
<!-- 		          								<a href="" class="logout" title="注销" id="logout">注销</a> -->
<!-- 		          							</span> -->
<!-- 		          						</div> -->
<!-- 		          					</div> -->
<!--           					</div> -->
<%--           					</c:if> --%>
<%--           					<c:if test="${ currentClient == null}" > --%>
<!-- 	          				<div class="loggedout" > -->
<!-- 		          				<div class="uiLoginBoxButton"><a href="#" class="uiLoginBoxButtonInner" id="js_login_box_button">&nbsp;登录&nbsp;</a> -->
<!-- 		          					<div id="js_uiLoginBox" class="uiLoginBox formular" style="display: none;"> -->
<%-- 		          						<img alt="微信登录二维码" src="${ctx }/common/loginQrImage"><br> --%>
<!-- 		          						请使用微信扫一扫授权登录。 -->
<!-- 		          					</div> -->
		          					
<!-- 		          					<div id="js_uiLoginBox2" class="uiLoginBox formular" style="display: none;"> -->
<!-- 		          						<span class="line" id="js_input_empty_hint" style="display: none; color:red;">用户名或密码不能为空！</span> -->
<!-- 		          						<span class="line" id="js_error_hint" style="display: none; color:red;">登陆信息错误！</span> -->
<!-- 		          						<form  method="post" name="loginform" id="js_bluebar_login_form"> -->
<!-- 		          							<input type="hidden" name="linkurl" value=""> -->
<!-- 		          							<input type="hidden" name="partnerid" value=""> -->
<!-- 		          							<input type="hidden" name="language" value="cn"> -->
<!-- 		          							<input type="hidden" name="session" value=""> -->
<!-- 		          							<input type="hidden" name="tracked" value=""> -->
<!-- 		          							<input type="hidden" name="task" value="login"> -->
<!-- 		          							<span class="line"> -->
<!-- 		          								<input type="text" class="input text js_default_val" tabindex="100" name="login_name" placeholder="米友号"></span> -->
<!-- 		          							<span class="line"> -->
<!-- 		          								<input type="password" class="input text js_default_val" tabindex="101" name="password" placeholder="密码"></span> -->
<!-- 		          							<span class="line aCenter" id="js_login_span"> -->
<!-- 		          								<button type="button" class="button green M" id="js_login_button" value=""> -->
<!-- 		          								<span>登录</span></button> -->
<!-- 		          							</span> -->
<!-- 		          						    <span class="line aCenter"><a href="" class="forgotdetails">若忘记密码请联系经纪人</a></span> -->
<!-- 		          							<span class="line fRight"><a href="" class="create_new_account register_link">创建新账户！</a></span> -->
		          						
<!-- 		          						</form> -->
<!-- 		          					</div> -->
<!-- 		          				</div> -->
<!-- 	          				</div> -->
<%-- 	          				</c:if> --%>
<!--           				</div> -->
<!--           		</div> -->
<!--           	</div> -->