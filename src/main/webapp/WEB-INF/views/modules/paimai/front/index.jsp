<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" import="com.thinkgem.jeesite.common.utils.*" %>
<!DOCTYPE html>
<html>
<head>
<title>国标商标-专业知识产权平台</title>
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<script type="application/x-javascript"> 
    addEventListener("load", 
		function() { 
           setTimeout(hideURLbar, 0); }, 
        false); 

function hideURLbar(){ 
	window.scrollTo(0,1); 
	} 
	

});
</script>
</head>
<body>
<div class="header">
    <div class="container">
        <div class="header_top">
            <div class="logo">
                <a href="index.html"><img src="${ctxStatic }/images/logo.png" alt="国标商标"/></a>
            </div> 
            <div class="cssmenu">
                    <ul>
                        <li class="active"><a href="login.jsp">登录</a></li> 
                        <li><a href="register.jsp">注册</a></li>
                    </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
<!--        <div class="h_menu4">start h_menu4 -->
<!--            <a class="toggleMenu" href="#">Menu</a> -->
<!--                <ul class="nav"> -->
<!--                    <li class="active"><a href="index.html">首页</a></li> -->
<!--                    <li><a href="about.html">交易信息</a></li> -->
<!--                    <li><a href="service.html">服务内容</a></li> -->
<!--                    <li><a href="faq.html">常见问题</a></li> -->
<!--                    <li><a href="testimonials.html">用户评价</a></li> -->
<!--                     <li><a href="about.html">关于我们</a></li> -->
<!--                    <li><a href="contact.html">联系我们</a></li> -->
<!--                 </ul> -->
<!--          </div>end h_menu4 -->
     </div>
</div>

<div class="banner">
     <div class="container">
        <!-- FlexSlider -->
              <section class="slider">
                  <div class="flexslider">
                    
                    <div class="domain search-wrap">
                        <div class="container center">
                            <div class="box1 text-center">
                                 <form class="search-form domain-search" >
                                    <div class="one-fifth column first" style="margin-left: 14%;">
                                       <span class="selection-box">
                                          <select class="domains valid" name="domains">
                                             <option>商标</option>
                                             <option>版权</option>
                                             <option>专利</option>
                                          </select>
                                       </span>
                                    </div>
                                    <div class="three-fifth column first">
                                        <input id="fuck" type="text" class="text" value="请输入商标名称，如：国标商标" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入商标名称，如：国标商标';}">               
                                     </div>
                                     <div class="one-fifth column">
                                        <a id="fuck1" class="popup-with-zoom-anim search-btn" href="#search-dialog">免费查询</a>
                                     </div>
                                 </form>
                           </div>
                        </div>
                    </div>
                    
                    <ul class="slides">
                        <li><img src="${ctxStatic }/images/servers.png" class="img-responsive" alt=""/>
                            <div class="banner_desc">
                                <h1>&nbsp;</h1>
                            </div>
                        </li>
                        <li><img src="${ctxStatic }/images/servers1.png" class="img-responsive" alt=""/>
                            <div class="banner_desc">
                                <h1>&nbsp;</h1>
                            </div>
                        </li>
                        <li><img src="${ctxStatic }/images/servers2.png" class="img-responsive" alt=""/>
                            <div class="banner_desc">
                                <h1>&nbsp;</h1>
                            </div>
                        </li>
                        
                    </ul>
                  </div>
             </section>
           <!-- FlexSlider -->
    </div>
</div>

<div class="domain">
    <div class="container">
        <div class="box1 text-center">
            <div class="col-md-3" >
                <img src="${ctxStatic }/images/service1.png"/>
                <a class="popup-with-zoom-anim " href="#dialog1"><h4><p class=" m_1">我要买标</p></h4></a>
            </div>
            <div class="col-md-3">
                <img src="${ctxStatic }/images/service2.png"/>
                <a class="popup-with-zoom-anim " href="#dialog2"><h4><p class=" m_1">我要卖标</p></h4></a>
            </div>
            <div class="col-md-3">
                <img src="${ctxStatic }/images/service3.png"/>
                <a class="popup-with-zoom-anim " href="#dialog3"><h4><p class=" m_1">悬赏起名</p></h4></a>
            </div>
            <div class="col-md-3">
                <img src="${ctxStatic }/images/service4.png"/>
                <a class="popup-with-zoom-anim " href="#dialog4"><h4><p class=" m_1">淘金客</p></h4></a>
            </div>
        </div>
    </div>
</div>
<div class="features">
    <div class="container">
        <div class="col-md-4">
           <h4 class="tz-title-4 tzcolor-blue">
             <p class="tzweight_Bold m_2"><span class="m_1">最热<br></span>买标信息</p>
          </h4>
          <ul class="offer">
             <li><p class="m_3"><span class="m_4">商标<br></span>猪八戒</p></li> 
             <li><p class="m_5">50元</p></li>    
          </ul>
        </div>
        <div class="col-md-8 row_1">
            <h4 class="tz-title-4 tzcolor-blue">
             <p class="tzweight_Bold m_2"><span class="m_1">最新<br></span>买标信息</p>
           </h4>
           <div class="section_1">
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                      <li><a href="">【商标】练练车</a></li>
                      <li><a href="">【商标】练练车阿斯</a></li>
                      <li><a href="">【商标】练练车傻傻的</a></li>
                      <li><a href="">【商标】练练车啊等等</a></li>     
                    </ul>
                </div>
            </div>
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                      <li><a href="">【商标】练练车</a></li>
                      <li><a href="">【商标】练练车sda</a></li>
                      <li><a href="">【商标】练练车as</a></li>
                      <li><a href="">【商标】练练车是</a></li>     
                    </ul>
                </div>
            </div>
            <div class="col_1_of_3 span_1_of_3">
                <a class="but1" href="#">查看全部</a>
            </div>
            <div class="clearfix"> </div>
           </div>
        </div>
    </div>
</div>

<div class="domain">
    <div class="container">
        <div class="col-md-4">
           <h4 class="tz-title-4 tzcolor-blue">
             <p class="tzweight_Bold m_2"><span class="m_1">最热<br></span>买标信息</p>
          </h4>
          <ul class="offer">
             <li><p class="m_3"><span class="m_4">商标<br></span>猪八戒</p></li> 
             <li><p class="m_5">50元</p></li>    
          </ul>
        </div>
        <div class="col-md-8 row_1">
            <h4 class="tz-title-4 tzcolor-blue">
             <p class="tzweight_Bold m_2"><span class="m_1">最新<br></span>买标信息</p>
           </h4>
           <div class="section_1">
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                      <li><a href="">【商标】练练车</a></li>
                      <li><a href="">【商标】练练车阿斯</a></li>
                      <li><a href="">【商标】练练车傻傻的</a></li>
                      <li><a href="">【商标】练练车啊等等</a></li>     
                    </ul>
                </div>
            </div>
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                      <li><a href="">【商标】练练车</a></li>
                      <li><a href="">【商标】练练车sda</a></li>
                      <li><a href="">【商标】练练车as</a></li>
                      <li><a href="">【商标】练练车是</a></li>     
                    </ul>
                </div>
            </div>
            <div class="col_1_of_3 span_1_of_3">
                <a class="but1" href="#">查看全部</a>
            </div>
            <div class="clearfix"> </div>
           </div>
        </div>
        
    </div>
</div>

<div class="features">
    <div class="container">
        <div class="col-md-4">
           <h4 class="tz-title-4 tzcolor-blue">
             <p class="tzweight_Bold m_2"><span class="m_1">最热<br></span>买标信息</p>
          </h4>
          <ul class="offer">
             <li><p class="m_3"><span class="m_4">商标<br></span>猪八戒</p></li> 
             <li><p class="m_5">50元</p></li>    
          </ul>
        </div>
        <div class="col-md-8 row_1">
            <h4 class="tz-title-4 tzcolor-blue">
             <p class="tzweight_Bold m_2"><span class="m_1">最新<br></span>买标信息</p>
           </h4>
           <div class="section_1">
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                      <li><a href="">【商标】练练车</a></li>
                      <li><a href="">【商标】练练车阿斯</a></li>
                      <li><a href="">【商标】练练车傻傻的</a></li>
                      <li><a href="">【商标】练练车啊等等</a></li>     
                    </ul>
                </div>
            </div>
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                      <li><a href="">【商标】练练车</a></li>
                      <li><a href="">【商标】练练车sda</a></li>
                      <li><a href="">【商标】练练车as</a></li>
                      <li><a href="">【商标】练练车是</a></li>     
                    </ul>
                </div>
            </div>
            <div class="col_1_of_3 span_1_of_3">
                <a class="but1" href="#">查看全部</a>
            </div>
            <div class="clearfix"> </div>
           </div>
        </div>
    </div>
</div>
                             
<div id="search-dialog" class="mfp-hide">
     <div class="pop_up">
         <div class="payment-online-form-left" >
         <!--     <form:form id="inputForm" modelAttribute="gbjTouristRequire" action="${ctx}/paimai/front/FrontCommon/save" method="post" class="form-horizontal">-->
               
               <form name="sendEmail" action="">
                 <h4><span class="shipping"> </span>免费查询</h4>
                 <h6>专业顾问人工查询，结果分析更准确</H6>
                 <ul>
                     <li>
                        <div>商标名称</div>
                        <input  id="content" name="searchContents" class="text-box-dark" type="text" value="请填写要查询的商标名称" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请填写要查询的商标名称';}">
                    </li>
                     <li>
                        <div>联系电话</div>
                        <input id="mobile"  name="mobile" class="text-box-dark" type="text" value="请输入您的联系电话" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入您的联系电话';}">
                     </li>
                     <li>
                        <div>联系人</div>
                        <input id="name"   name="name" class="text-box-dark" type="text" value="如：王先生/王女士" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '如：王先生/王女士';}">
                     </li>
                     <div class="clearfix"> </div>
                 </ul>
                 <div class="clear"> <ul class="payment-sendbtns">
                     <li> <input  id="sendemail"  type="submit" value="获取查询结果" ></li>
                   
                 </ul>
               
                 <div class="clearfix"> </div>
            </form:form>
            
            
        <jsp:useBean id="loginBean" class="com.thinkgem.jeesite.common.utils.SendEmailBean" />
        <jsp:setProperty property="content" name="loginBean" param="searchContents" />
        <jsp:setProperty property="name" name="loginBean" param="name" />
        <jsp:setProperty property="mobile" name="loginBean" param="mobile" /> 
        
         
        <jsp:getProperty property="isSend" name="loginBean"   /><br />       
  
             
         </div>
    </div>
</div>
 
//我要买标 对话框
<div id="dialog1" class="mfp-hide">
     <div class="pop_up">
         <div class="payment-online-form-left">
             <form>
                 <h4><span class="shipping"> </span>我要买标</h4>
                 <h6>请输入您要买标的信息</H6>
                 <ul>
                     <li>
                        <div>商标名称</div>
                        <input class="text-box-dark" type="text" value="请填写要查询的商标名称" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请填写要查询的商标名称';}">
                    </li>
                     <li>
                        <div>联系电话</div>
                        <input class="text-box-dark" type="text" value="请输入您的联系电话" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入您的联系电话';}">
                     </li>
                     <li>
                        <div>联系人</div>
                        <input class="text-box-dark" type="text" value="如：王先生/王女士" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '如：王先生/王女士';}">
                     </li>
                     <div class="clearfix"> </div>
                 </ul>
                 <div class="clear"> </div>
                 <ul class="payment-sendbtns">
                     <li><input type="submit" value="获取查询结果"></li>
                 </ul>
                 <div class="clearfix"> </div>
             </form>
         </div>
    </div>
</div> 


 <script type="text/javascript">
    $(function(){
      //SyntaxHighlighter.all();
        $('.popup-with-zoom-anim').magnificPopup({
            type: 'inline',
            fixedContentPos: false,
            fixedBgPos: true,
            overflowY: 'auto',
            closeBtnInside: true,
            preloader: false,
            midClick: true,
            removalDelay: 300,
            mainClass: 'my-mfp-zoom-in search-dialog-wrap'
        });
      
    });
    $(window).load(function(){
      $('.flexslider').flexslider({
        animation: "slide",
        slideshowSpeed: 4000, // 自动播放速度毫秒
        start: function(slider){
          $('body').removeClass('loading');
        }
      });
    });
  </script>
   <script type="text/javascript">
	$(document).ready(function(){
		 $("#sendemail").click(function(){
		 		 		 if($("#content").val()=="请填写要查询的商标名称")  
	        {  
	            alert('商标名称不能为空');  
	            	           return false;  
	        }  
		 		 		if($("#mobile").val()=="请输入您的联系电话")  
		 		        {  
		 		           alert('联系电话无效');  
		 		           return false;  
		 		        }   
		 if($("#name").val()=="如：王先生/王女士")  
	        {  
	            alert('联系人不能为空');  
	            	           return false;  
	        }   
	      	        
	        
	     		  });
	
});
</script>
  
</body>
</html>

