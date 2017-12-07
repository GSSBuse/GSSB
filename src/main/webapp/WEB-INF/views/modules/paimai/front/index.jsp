<%@ page contentType="text/html;charset=UTF-8" %>
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
	
</script>
<script type="text/javascript" src="${ctxStatic}/front/js/index.js"></script>

</head>
<body ms-controller="index">
<div class="header" >
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
                      <li ms-repeat-el="datas.domainBuyList" ><a href="" ms-click="goToSingleDomainBuy">{{el.title}}</a></li>
                    </ul>
                </div>
            </div>
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
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
             <p class="tzweight_Bold m_2"><span class="m_1">最热<br></span>卖标信息</p>
          </h4>
          <ul class="offer">
             <li><p class="m_3"><span class="m_4">商标<br></span>猪八戒</p></li> 
             <li><p class="m_5">50元</p></li>    
          </ul>
        </div>
        <div class="col-md-8 row_1">
            <h4 class="tz-title-4 tzcolor-blue">
             <p class="tzweight_Bold m_2"><span class="m_1">最新<br></span>卖标信息</p>
           </h4>
           <div class="section_1">
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                     <li ms-repeat-el="datas.domainSoldList"><a href="#">{{el.title}}</a></li>    
                    </ul>
                </div>
            </div>
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                           
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
             <p class="tzweight_Bold m_2"><span class="m_1">最热<br></span>悬赏信息</p>
          </h4>
          <ul class="offer">
             <li><p class="m_3"><span class="m_4">商标<br></span>猪八戒</p></li> 
             <li><p class="m_5">50元</p></li>    
          </ul>
        </div>
        <div class="col-md-8 row_1">
            <h4 class="tz-title-4 tzcolor-blue">
             <p class="tzweight_Bold m_2"><span class="m_1">最新<br></span>悬赏信息</p>
           </h4>
           <div class="section_1">
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                     <li ms-repeat-el="datas.domainRewardList"><a href="#">{{el.rewardMoney}}</a></li>    
                    </ul>
                </div>
            </div>
            <div class="col_1_of_3 span_1_of_3">
                <div class="list_1">
                    <ul>
                           
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
<!-- 免费查询form表单 -->                             
<div id="search-dialog" class="mfp-hide"  ms-controller="search-dialog">
     <div class="pop_up">
         <div class="payment-online-form-left" >
            <form id="domainform" method="post" action="${ctx }/index.html" ms-widget="validation"  class="form-horizontal">
                 <h4><span class="shipping"> </span>免费查询</h4>
                 <h6>专业顾问人工查询，结果分析更准确</H6>
                 <ul>
                     <li>
                        <div>商标名称</div>
                        <input  id="name" name="name" class="text-box-dark" type="text" value="请填写要查询的商标名称" ms-duplex-required="datas.domainInfo.name" maxlength="20" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请填写要查询的商标名称';}">
                    </li>
                     <li>
                        <div>联系电话</div>
                        <input id="mobile"  name="mobile" class="text-box-dark" type="text" value="请输入您的联系电话" ms-duplex-required-phone="datas.domainInfo.mobile" maxlength="11" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入您的联系电话';}">
                     </li>
                     <li>
                        <div>联系人</div>
                        <input id="connacts"   name="searchContents" class="text-box-dark" type="text" value="如：王先生/王女士" ms-duplex-required="datas.domainInfo.searchContents" maxlength="20" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '如：王先生/王女士';}">
                     </li>
                     
                 </ul>
                  <ul class="payment-sendbtns">
                     <li> <input  id="submitChange"  type="submit" value="获取查询结果" ></li>
                 </ul>
            </form>
         </div>
    </div>
</div>
 
<!--我要买标 对话框      2017/12/4    by snnu--> 
<div id="dialog1" class="mfp-hide"  ms-controller="buy-dialog">
<div class="pop_up">
         <div class="payment-online-form-left" >
            <form id="domainform" method="post" action="${ctx}/index.html" ms-widget="validation"  class="form-horizontal">
                 <h4><span class="shipping"> </span>免费查询</h4>
                 <h6>专业顾问人工查询，结果分析更准确</H6>
                 <ul>
                     <li>
                        <div>商标名称</div>
                        <input  id="title" name="title" class="text-box-dark" type="text" value="请填写要查询的商标名称" ms-duplex-required="datas.domainInfo1.title" maxlength="20" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请填写要查询的商标名称';}">
                    </li>
                     <li>
                        <div>联系电话</div>
                        <input id="mobile"  name="mobile" class="text-box-dark" type="text" value="请输入您的联系电话" ms-duplex-required-phone="datas.domainInfo1.mobile" maxlength="11" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入您的联系电话';}">
                     </li>
                     <li>
                        <div>联系人</div>
                        <input id="linkman"   name="linkman" class="text-box-dark" type="text" value="如：王先生/王女士" ms-duplex-required="datas.domainInfo1.linkman" maxlength="20" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '如：王先生/王女士';}">
                     </li>
                   </ul>
                  <ul class="payment-sendbtns">
                     <li> <input  id="submit01"  type="submit" value="获取查询结果" ></li>
                 </ul>
            </form>
         </div>
    </div>
<!--     TODO  -->
</div> 

<!--我要卖标  对话框 -->
<div id="dialog2" class="mfp-hide" ms-controller="sold-dialog">
<div class="pop_up">
         <div class="payment-online-form-left" >
            <form id="domainform" method="post" action="${ctx}/index.html" ms-widget="validation"  class="form-horizontal">
                 <h4><span class="shipping"> </span>免费查询</h4>
                 <h6>专业顾问人工查询，结果分析更准确</H6>
                 <ul>
                     <li>
                        <div>商标名称</div>
                        <input  id="title" name="title" class="text-box-dark" type="text" value="请填写要查询的商标名称" ms-duplex-required="datas.domainInfo2.title" maxlength="20" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请填写要查询的商标名称';}">
                    </li>
                     <li>
                        <div>联系电话</div>
                        <input id="mobile"  name="mobile" class="text-box-dark" type="text" value="请输入您的联系电话" ms-duplex-required-phone="datas.domainInfo2.mobile" maxlength="11" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入您的联系电话';}">
                     </li>
                     <li>
                        <div>联系人</div>
                        <input id="linkman"   name="linkman" class="text-box-dark" type="text" value="如：王先生/王女士" ms-duplex-required="datas.domainInfo2.linkman" maxlength="20" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '如：王先生/王女士';}">
                     </li>
                                      </ul>
                  <ul class="payment-sendbtns">
                     <li> <input  id="submit02"  type="submit" value="获取查询结果" ></li>
                 </ul>
            </form>
         </div>
    </div>
<!--     TODO  -->
</div> 

<!--悬赏起名 对话框  busnnu-->
<div id="dialog3" class="mfp-hide" ms-controller="reward-dialog">
<div class="pop_up">
         <div class="payment-online-form-left" >
            <form id="domainform" method="post" action="${ctx}/index.html" ms-widget="validation"  class="form-horizontal">
                 <h4><span class="shipping"> </span>悬赏起名</h4>
                 <h6>专业顾问人工查询，结果分析更准确</H6>
                 <ul>
                     <li>
                        <div>悬赏标题</div>
                        <input  id="title" name="title" class="text-box-dark" type="text" value="请填写要查询的商标名称" ms-duplex-required="datas.domainInfo3.title" maxlength="20" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请填写要查询的商标名称';}">
                    </li>
                     <li>
                        <div>悬赏需求</div>
                        <input id="titleNeed"  name="titleNeed" class="text-box-dark" type="text" value="请输入您的联系电话" ms-duplex-required="datas.domainInfo3.titleNeed" maxlength="11" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '请输入您的联系电话';}">
                     </li>
                     <li>
                        <div>悬赏金额</div>
                        <input id="rewardMoney"   name="rewardMoney" class="text-box-dark" type="text" value="如：王先生/王女士" ms-duplex-required="datas.domainInfo3.rewardMoney" maxlength="20" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '如：王先生/王女士';}">
                     </li>
                     <li>
                        <div>联系人</div>
                        <input id="user"   name="user" class="text-box-dark" type="text" value="如：王先生/王女士" ms-duplex-required="datas.domainInfo3.user" maxlength="20" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '如：王先生/王女士';}">
                     </li>
                     
                 </ul>
                  <ul class="payment-sendbtns">
                     <li> <input  id="submit03"  type="submit" value="获取查询结果" ></li>
                 </ul>
            </form>
         </div>
    </div>
<!--     TODO  -->
</div> 

<!--淘金客 对话框 -->
<div id="dialog4" class="mfp-hide">
<!--     TODO  -->
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

<script type="text/javascript" src="${ctxStatic }/front/js/searchBrand.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/gbBuy.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/gbsold.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/gbreward.js"></script> 
</body>
</html>

