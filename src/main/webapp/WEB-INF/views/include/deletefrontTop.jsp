<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${ctxStatic }/front/js/searchBrand.js"></script> 

<%@ include file="/WEB-INF/views/include/frontMenu.jsp"%>


<!-- Start of Search Wrapper -->
<div class="search-area-wrapper">
        <div class="search-area container">
        
                <ul class="registInfo">
                    <li class="mdl" style="display: none;">
                        <h1>商标注册</h1>
                        <h6>对产品、方法或者其改进所提出的新的技术方案</h6>
                        <p>让世界感受到因您而不同</p>
                       <!-- <a href="javascript:void(0);" onclick="openwin()"><p>了解详细</p></a>-->
                    </li>
                    <li class="partner" style="display: none;">
                        <h1>专利申请</h1>
                        <h6>产品的形状、构造或其结合所提出的适于实用的新的技术方案</h6>
                        <p>通常指有具体形态的新颖性物件</p>
                        <!--<a href="javascript:void(0);" onclick="openwin()"><p>了解详细</p></a>-->
                    </li>
                    <li class="qualifications" style="display: none;">
                        <h1>版权登记</h1>
                        <!--<h6 style="font-family:'STXinwei24b5e7e8749f9';" >您的创新智慧成果，国商助它成财</h6>-->
                        <h6>对产品所作出的富有美感并适于工业应用的新设计</h6>
                        <p>形状、图案或者其结合以及色彩与形状、图案的结合</p>
                        <!--<p style="font-family:'STXinwei24b5e7e8749f9';" >快告诉您要申请的专利种类</p>-->
                        <!--<a href="javascript:void(0);" onclick="openwin()"><p>了解详细</p></a>-->
                    </li>
                </ul>
                <ul class="registbox">
                    <li>
                        <p onClick="openwin()">商标注册</p>
                    </li>

                    <li >
                        <p onClick="openwin()">专利申请</p>
                    </li>

                    <li>
                        <p onClick="openwin()">版权登记</p>
                    </li>
                </ul>
                <script>
	                $(function(){
	                    /* 首页banner自动切换 */
	                    var timer = 10000000;//秒为单位,作为时间间隔
	                    var index = 0;//不做修改
	                    var interval;
	                    $(".registbox>li").mouseover(function(){
	                        index = $(".registbox>li").index(this);
	                        bannerAnmail();
	                        clearInterval(interval);
	                        interval = setInterval(bannerAnmail,timer*1000);
	                    });
	
	                    function bannerAnmail() {
	                        $(".registbox>li").removeClass("registlihover");
	                        $(".registbox>li").eq(index).addClass("registlihover");
	                        $(".registInfo>li").hide();
	                        $(".registInfo>li").eq(index).fadeIn(100);
	                        index +=1;
	                        if(($(".registbox>li").length)==index){index=0;}
	                    }
	                    bannerAnmail();
	                    interval = setInterval(bannerAnmail,timer*1000);
	                    /* 首页banner自动切换 结束 */
	                });

				</script>
                <form id="search-form" class="search-form clearfix" method="get" action="${ctx }/index1.html" autocomplete="off">
                        
<!--                         <select class="select-term"> -->
<!--                             <option value='1' selected>&nbsp;&nbsp;商标</option> -->
<!--                             <option value='2'>&nbsp;&nbsp;专利</option> -->
<!--                             <option value='3'>&nbsp;&nbsp;版权</option> -->
<!--                         </select> -->
                        <input class="search-term required" type="text" id="s" name="s" placeholder="请输入您想要查询的商标" />
                        <input class="search-btn" type="button" onclick="show()" value="免费查询" />
                </form>
                <div class="scroll-box">
				     <ul>
				      <li>2017/12/02 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/03 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/04 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/05 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/06 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/07 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/08 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/09 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/10 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/11 王先生 139582****1002 成功查询商标</li>
                      <li>2017/12/12 王先生 139582****1002 成功查询商标</li>
				     </ul>
				</div>
        </div>
        

</div>

   
<!-- 免费查询form表单 -->
<div id="search-dialog-bg" style="width: 100%;height: 100%;position: fixed;top: 0;left: 0;background-color: rgba(0, 0, 0, 0.5);z-index: 1;display: none;"></div>               
<div id="search-dialog"  ms-controller="search-dialog"  style="position: fixed;background: rgb(249, 249, 249);top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: 10;display: none;">
    <div id="close-dialog"  style="position: absolute;right: -10px;top: -14px;width: 24px;height: 24px;text-align: center;font-size: 25px;border: 2px solid #d2d1d1;border-radius: 50%;background-color: #fff; color: #e71a1a;cursor: pointer;">×</div>
    <form id="domainform"  action="${ctx }/index1.html" method="post" ms-widget="validation"  style="padding: 20px 30px;margin: 0;">
           <h1 class="post-title"><a href="#">免费查询</a></h3>
           <p class="comment-notes">请输入您需要查询的信息。专业顾问人工查询，结果分析更准确！</p>

           <div>
                   <label for="author">商标名称 *</label>
                   <input class="span4" type="text" name="searchContents"  id="connacts"  ms-duplex-required="datas.domainInfo.searchContents" onFocus="this.value = '';" value="" size="22">
           </div>

           <div>
                   <label for="email">联系电话 *</label>
                   <input class="span4" type="text" name="mobile" id="mobile" ms-duplex-required="datas.domainInfo.name" onFocus="this.value = '';" value=""  size="22" >
           </div>

           <div>
                   <label for="url">联系人 *</label>
                   <input class="span4" type="text" name="name" id="name"  ms-duplex-required="datas.domainInfo.mobile" onFocus="this.value = '';" value="" size="22" >
           </div>
           <div class="payment-sendbtns">
                   <input class="btn" name="submit" type="submit" id="submitChange"  value="提交查询">
           </div>
   </form>
</div>

<!-- End of Search Wrapper -->
<script type="text/javascript"> 
function show(){
    document.getElementById("search-dialog").style.display="block";
    document.getElementById("search-dialog-bg").style.display = 'block';
}
         
function hide(){
    document.getElementById("search-dialog").style.display="none";
    document.getElementById("search-dialog-bg").style.display = 'none';
}

// 点击弹窗背景关闭当前弹窗
$('#search-dialog-bg').click(function(){
	$('#search-dialog,#search-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialog').click(function(){
    $('#search-dialog,#search-dialog-bg').hide();
});

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