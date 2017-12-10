<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
        <!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
        <!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
        <!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
        <!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->
        <head>
                <%@ include file="/WEB-INF/views/include/frontHead1.jsp"%>
                <script type="text/javascript" src="${ctxStatic }/front/js/searchBrand.js"></script> 
                <script type="application/x-javascript"> 
                   addEventListener("load", 
		              function() { 
                     setTimeout(hideURLbar, 0); }, 
                           false); 

                   function hideURLbar(){ 
	              window.scrollTo(0,1); 
	                  } 
	
</script>
        </head>

        <body ms-controller="index1">
                <%@ include file="/WEB-INF/views/include/frontTop.jsp"%>

                <!-- Start of Page Container -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">
                                        <div class="span12 page-content">
                                                <div class="row-fluid top-cats" style="text-align: center;">

                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service1.png"/>
                                                                <h4><a href="#buy-dialog" onclick="show1()">我要买标</a></h4>
                                                                <div class="category-description">
                                                                        <p>发布您需要咨询或有购买意向的商标专利信息，第一时间获取交易信息</p>
                                                                </div>
                                                        </section>

                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service2.png"/>
                                                                <h4><a href="#sold-dialog" onclick="show2()">我要卖标</a></h4>
                                                                <div class="category-description">
                                                                        <p>发布您需要咨询或有购买意向的商标专利信息，第一时间获取交易信息</p>
                                                                </div>
                                                        </section>

                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service3.png"/>
                                                                <h4><a href="#reward-dialog"  onclick="show3()">悬赏起名</a></h4>
                                                                <div class="category-description">
                                                                        <p>发布您需要咨询或有购买意向的商标专利信息，第一时间获取交易信息</p>
                                                                </div>
                                                        </section>
                                                       
                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service4.png"/>
                                                                <h4><a href="#">淘金客</a></h4>
                                                                <div class="category-description">
                                                                        <p>发布您需要咨询或有购买意向的商标专利信息，第一时间获取交易信息</p>
                                                                </div>
                                                        </section>
                                                </div>
                                        </div>
                                        
                                        <div class="row home-category-list-area">                     
                                                <div class="span12" style="text-align: center;">
                                                        <p align="center">
                                                            <img style="height:150%;" src="${ctxStatic }/images/split.jpg" alt=""/>
                                                        </p>
                                                        <h2>最新动态信息</h2>
                                                </div>
                                        </div>
                                        
                                        <!-- start of page content -->
                                        <div class="span12 page-content">   
                                                <!-- Basic Home Page Template -->
                                                <div class="row separator">
                                                        <section class="span4 articles-list">
                                                                <h3 class="category">买标信息</h3>
                                                                <ul class="articles">
                                                                       <li class="article-entry standard" ms-repeat-el="datas.domainBuyList" >
		                                                                        <h4><a href="${ctx }/single.html" >{{el.title}}</a></h4>
		                                                                        <span class="article-meta">{{el.createDate}} &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" title="查看该分类">商标   &amp; 餐饮</a></span>
		                                                                        <span class="like-count">66</span>
		                                                               </li>
                                                                       
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">
                                                                <h3 class="category">卖标信息</h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry standard" ms-repeat-el="datas.domainSoldList" >
		                                                                
		                                                                        <h4><a href="${ctx }/single.html" >{{el.title}}</a></h4>
		                                                                
		                                                                        <span class="article-meta">{{el.createDate}} &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" title="查看该分类">商标   &amp; 餐饮</a></span>
		                                                                        <span class="like-count">66</span>
		                                                                </li>
                                                                </ul>
                                                        </section>
                                                        
                                                        <section class="span4 articles-list">
                                                                <h3 class="category">悬赏起名</h3>
                                                                <ul class="articles">
                                                                       <li class="article-entry standard" ms-repeat-el="datas.domainRewardList" >
		                                                                
		                                                                        <h4><a href="${ctx }/single.html" >{{el.title}}</a> {{el.rewardMoney}}元</h4>
		                                                                
		                                                                        <span class="article-meta">{{el.createDate}} &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" title="查看该分类">商标   &amp; 餐饮</a></span>
		                                                                        <span class="like-count">66</span>
		                                                                </li>
                                                                </ul>
                                                        </section>
                                                </div>
                                        </div>
                                        <div class="row home-category-list-area">
                                                <div class="span12" style="text-align: center;">
                                                        <input class="search-btn" type="button" onclick="window.location.href('${ctx }/articles.html')" value="查看更多" />
                                                </div>
                                        </div>
                                        <!-- end of page content -->

                                </div>
                        </div>
                </div>
                <!-- End of Page Container -->
                
   <!-- 我要买标弹框事件 -->
<div id="buy-dialog-bg" style="width: 100%;height: 100%;position: fixed;top: 0;left: 0;background-color: rgba(0, 0, 0, 0.5);z-index: 1;display: none;"></div>               
<div id="buy-dialog"  ms-controller="buy-dialog"  style="position: fixed;background: rgb(249, 249, 249);top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: 10;display: none;">
    <div id="close-dialog1"  style="position: absolute;right: -10px;top: -14px;width: 24px;height: 24px;text-align: center;font-size: 25px;border: 2px solid #d2d1d1;border-radius: 50%;background-color: #fff; color: #e71a1a;cursor: pointer;">×</div>
    <form id="domainform"  action="${ctx }/index1.html" method="post" ms-widget="validation"  class="form-horizontal" style="padding: 20px 30px;margin: 0;">
           <h1 class="post-title"><a href="#">我要买标</a></h3>
           <p class="comment-notes">请输入您需要发布的信息。专业顾问人工查询，结果分析更准确！</p>

           <div>
                   <label for="title">商标名称 *</label>
                   <input class="span4" type="text" name="title"  id="title"  ms-duplex-required="datas.domainInfo1.title" onFocus="this.value = '';" value="" size="22">
           </div>

           <div>
                   <label for="mobile">联系电话 *</label>
                   <input class="span4" type="text" name="mobile" id="mobile" ms-duplex-required="datas.domainInfo1.mobile" onFocus="this.value = '';" value=""  size="22" >
           </div>

           <div>
                   <label for="linkman">联系人 *</label>
                   <input class="span4" type="text" name="linkman" id="linkman"  ms-duplex-required="datas.domainInfo1.linkman" onFocus="this.value = '';" value="" size="22" >
           </div>
           <div class="payment-sendbtns">
                   <input class="btn" name="submit" type="submit" id="submit01"  value="提交查询">
           </div>
   </form>
</div>
<!-- 我要卖标弹出框事件 -->
<div id="sold-dialog-bg" style="width: 100%;height: 100%;position: fixed;top: 0;left: 0;background-color: rgba(0, 0, 0, 0.5);z-index: 1;display: none;"></div>               
<div id="sold-dialog"  ms-controller="sold-dialog"  style="position: fixed;background: rgb(249, 249, 249);top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: 10;display: none;">
    <div id="close-dialog2"  style="position: absolute;right: -10px;top: -14px;width: 24px;height: 24px;text-align: center;font-size: 25px;border: 2px solid #d2d1d1;border-radius: 50%;background-color: #fff; color: #e71a1a;cursor: pointer;">×</div>
    <form id="domainform"  action="${ctx }/index1.html" method="post" ms-widget="validation"  style="padding: 20px 30px;margin: 0;">
           <h1 class="post-title"><a href="#">我要卖标</a></h3>
           <p class="comment-notes">请输入您需要发布的信息。专业顾问人工查询，结果分析更准确！</p>

           <div>
                   <label for="author">商标名称 *</label>
                   <input class="span4" type="text" name="title"  id="title"  ms-duplex-required="datas.domainInfo2.title" onFocus="this.value = '';" value="" size="22">
           </div>

           <div>
                   <label for="email">联系电话 *</label>
                   <input class="span4" type="text" name="mobile" id="mobile" ms-duplex-required="datas.domainInfo2.mobile" onFocus="this.value = '';" value=""  size="22" >
           </div>

           <div>
                   <label for="url">联系人 *</label>
                   <input class="span4" type="text" name="linkman" id="linkman"  ms-duplex-required="datas.domainInfo2.linkman" onFocus="this.value = '';" value="" size="22" >
           </div>
           <div class="payment-sendbtns">
                   <input class="btn" name="submit" type="submit" id="submit02"  value="提交查询">
           </div>
   </form>
</div>
<!-- 悬赏弹框事件 -->
<div id="reward-dialog-bg" style="width: 100%;height: 100%;position: fixed;top: 0;left: 0;background-color: rgba(0, 0, 0, 0.5);z-index: 1;display: none;"></div>               
<div id="reward-dialog"  ms-controller="reward-dialog"  style="position: fixed;background: rgb(249, 249, 249);top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: 10;display: none;">
    <div id="close-dialog3"  style="position: absolute;right: -10px;top: -14px;width: 24px;height: 24px;text-align: center;font-size: 25px;border: 2px solid #d2d1d1;border-radius: 50%;background-color: #fff; color: #e71a1a;cursor: pointer;">×</div>
    <form id="domainform"  action="${ctx }/index1.html" method="post" ms-widget="validation"  style="padding: 20px 30px;margin: 0;">
           <h1 class="post-title"><a href="#">悬赏起名</a></h3>
           <p class="comment-notes">请输入您需要发布的悬赏信息。专业顾问人工查询，结果分析更准确！</p>

           <div>
                   <label for="author">商标名称 *</label>
                   <input class="span4" type="text" name="title"  id="title"  ms-duplex-required="datas.domainInfo3.title" onFocus="this.value = '';" value="" size="22">
           </div>

           <div>
                   <label for="email">联系电话 *</label>
                   <input class="span4" type="text" name="mobile" id="mobile" ms-duplex-required="datas.domainInfo3.mobile" onFocus="this.value = '';" value=""  size="22" >
           </div>

           <div>
                   <label for="url">联系人 *</label>
                   <input class="span4" type="text" name="linkman" id="linkman"  ms-duplex-required="datas.domainInfo3.linkman" onFocus="this.value = '';" value="" size="22" >
           </div>
           <div class="payment-sendbtns">
                   <input class="btn" name="submit" type="submit" id="submit03"  value="提交查询">
           </div>
   </form>
</div>
                <!-- Start of Footer -->
                
                <%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>   
                
                <!-- End of Footer -->

                <a href="#top" id="scroll-top"></a>
<script type="text/javascript"> <!--我要买标弹出框js -->
function show1(){
    document.getElementById("buy-dialog").style.display="block";
    document.getElementById("buy-dialog-bg").style.display = 'block';
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
<script type="text/javascript"> <!--我要卖标弹出框js -->
function show2(){
    document.getElementById("sold-dialog").style.display="block";
    document.getElementById("sold-dialog-bg").style.display = 'block';
}
function hide(){
    document.getElementById("sold-dialog").style.display="none";
    document.getElementById("sold-dialog-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
$('#sold-dialog-bg').click(function(){
	$('#sold-dialog,#sold-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialog2').click(function(){
    $('#sold-dialog,#sold-dialog-bg').hide();
});
</script>
 <script type="text/javascript"> <!--悬赏信息弹出框js -->
function show3(){
    document.getElementById("reward-dialog").style.display="block";
    document.getElementById("reward-dialog-bg").style.display = 'block';
}
function hide(){
    document.getElementById("reward-dialog").style.display="none";
    document.getElementById("reward-dialog-bg").style.display = 'none';
}
// 点击弹窗背景关闭当前弹窗
$('#reward-dialog-bg').click(function(){
	$('#reward-dialog,#reward-dialog-bg').hide();
});
// 点击弹窗的关闭按钮关闭当前弹窗
$('#close-dialog3').click(function(){
    $('#reward-dialog,#reward-dialog-bg').hide();
});
</script>
<script type="text/javascript" src="${ctxStatic }/front/js/searchBrand.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/gbBuy.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/gbsold.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/gbreward.js"></script> 
<script type="text/javascript" src="${ctxStatic }/front/js/index.js"></script>
        </body>
</html>