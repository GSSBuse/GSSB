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
<script type="text/javascript" src="${ctxStatic }/front/js/Articlereward.js"></script>
<script type="text/javascript" src="${ctxStatic }/front/js/gbreward.js"></script>
        <body ms-controller="rewardarticles"> 
                <%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
                <div class="about">
                     <div class="container">
                        <h1>买标信息<span class="m_1"><br>最新发布的买标信息，如有兴趣请联系123456789</span></h1>
                    </div>
                </div>

                <!-- Start of Page Container -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">

                                        <!-- start of page content -->
                                        <div class="span8 main-listing" >
                                                <article class=" page type-page hentry clearfix">
                                                        <h1 class="post-title"><a href="#">全部悬赏交易信息</a></h1>
                                                        <hr>                                                        
                                                </article>
                                                <article class="format-standard type-post hentry clearfix"  ms-repeat-el="datas.domainRewardArticleList">

                                                        <header class="clearfix">

                                                                <h3 class="post-title">
                                                                        <a ms-attr-href="${ctx }/single.html?id={{el.id}}&type=reward">{{el.title}}</a>
                                                                        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                                                                        <a href="">悬赏金额:</a>
                                                                        <a href="">{{el.price}}元</a>
                                                                </h3>
                                                                

                                                                <div class="post-meta clearfix">
                                                                        <span class="date">{{el.createDate}}</span>
                                                                        <span class="category"><a href="#" title="查询该标签所有内容">{{el.typeId}} &amp;&amp;&amp; 餐饮</a></span>
                                                                        <span class="comments"><a href="#">3个回复</a></span>
                                                                       <a
											ms-attr-href="${ctx}/single.html?id={{el.id}}&type=reward"><span class="like-count" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{el.upCounts}}
										</span></a>
                                                                </div><!-- end of post meta -->


                                                        </header>

                                                        <p>{{el.description}}<a class="readmore-link" href="#">查看全部</a></p>

                                                </article>

                                                
                                                <div id="pagination">
                                                        <a href="#" class="btn active">1</a>
                                                        <a href="#" class="btn">2</a>
                                                        <a href="#" class="btn">3</a>
                                                        <a href="#" class="btn">下一页 ></a>
                                                        <a href="#" class="btn">最后一页 >></a>
                                                </div>

                                        </div>
                                        <!-- end of page content -->


                                        <!-- start of sidebar -->        
                                        <aside class="span4 page-sidebar">
                                         <div class="row-fluid top-cats" style="text-align: right;">
                                                <a href="#reward-dialog" onclick="show3()">
                                                   <section class="span6">
                                                      <img src="${ctxStatic }/images/service3.png"/>
                                                       <h4>我要发布买标信息</h4>
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
                <div id="reward-dialog-bg" style="width: 100%;height: 100%;position: fixed;top: 0;left: 0;background-color: rgba(0, 0, 0, 0.5);z-index: 1;display: none;"></div>               
<div id="reward-dialog"  ms-controller="reward-dialog"  style="position: fixed;background: rgb(249, 249, 249);top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: 10;display: none;">
    <div id="close-dialog3"  style="position: absolute;right: -10px;top: -14px;width: 24px;height: 24px;text-align: center;font-size: 25px;border: 2px solid #d2d1d1;border-radius: 50%;background-color: #fff; color: #e71a1a;cursor: pointer;">×</div>
    <form id="domainform"  action="${ctx }/index1.html" method="post" ms-widget="validation"  style="padding: 20px 30px;margin: 0;">
           <h1 class="post-title"><a href="#">悬赏起名</a></h1>
           <p class="comment-notes">请输入您需要发布的悬赏信息。专业顾问人工查询，结果分析更准确！</p>

           <div>
                   <label for="author">起名标题 *</label>
                   <input class="span4" type="text" name="title"  id="title"  ms-duplex-required="datas.domainInfo3.title" onFocus="this.value = '';" value="" size="22">
           </div>
           <div>
                   <label for="author">起名需求 *</label>
                   <input class="span4" type="text" name="description"  id="description"  ms-duplex-required="datas.domainInfo3.description" onFocus="this.value = '';" value="" size="22">
           </div>
           <div>
                   <label for="email">打赏金额 *</label>
                   <input class="span4" type="text" name="price" id="price" ms-duplex-required="datas.domainInfo3.price" onFocus="this.value = '';" value=""  size="22" >
           </div>
           <div class="payment-sendbtns">
                   <input class="btn" name="submit" type="submit" id="submit03"  value="提交查询">
           </div>
   </form>
</div>
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
        </body>
</html>
