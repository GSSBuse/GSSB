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
                
        </head>

        <body ms-controller="index1">
                <%@ include file="/WEB-INF/views/include/frontTop.jsp"%>

                <!-- Start of 主营业务模块 -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">
                                        <div class="span12 page-content">
                                                <div class="row-fluid top-cats" style="text-align: center;">
                                                    <a href="#buy-dialog" onclick="show1()">
                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service1.png"/>
                                                                <h4>我要买标</h4>
                                                        </section>
                                                     </a>
                                                     <a href="#sold-dialog" onclick="show2()">
                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service2.png"/>
                                                                <h4>我要卖标</h4>
                                                        </section>
                                                     </a>
                                                     <a href="#sold-dialog" onclick="show3()">
                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service3.png"/>
                                                                <h4>悬赏起名</h4>
                                                        </section>
                                                     </a>
                                                     <a href="#">
                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service4.png"/>
                                                                <h4>淘金客</h4>
                                                        </section>
                                                     </a>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>                
                <!-- End of 主营业务模块 -->
                
                <!-- Start of 最新业务信息 -->                        
                <div class="about_grid1">
                        <div class="container" >
                                <div class="row">    
                                    <h4 class="tz-title-4 tzcolor-blue">
                                        <p class="tzweight_Bold"><span class="m_2">业务信息</span></p>
                                    </h4>                                    
                                        <div class="span12 page-content" >   
                                                
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
                                                                <a class="faq_but1 but3" href="${ctx }/articles.html">更多</a>
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
                                                                <a class="faq_but1 but3" href="${ctx }/articles.html">更多</a>
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
                                                                <a class="faq_but1 but3" href="${ctx }/articles.html">更多</a>
                                                        </section>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>
                <!-- End of Page 最新业务信息 -->
                
                <!-- Start of 动态信息 -->    
                <div class="about_grid">
				    <div class="container">
				        <h4 class="tz-title-4 tzcolor-blue">
				            <p class="tzweight_Bold"><span class="m_2">动态信息</span></p>
				        </h4>
				        <div class="col-md-4 thumb_3">
				           <h4 class="tz-title-5 tzcolor-blue">
				              <p class="tzweight_Bold"><span class="m_20">交易信息<br></span>最新5条</p>
				           </h4>
				           <ul class="list_4">
				             <li><a href="#">动次打次动次打次动态信息1.</a></li>
                             <li><a href="#">动次打次动次打次动态信息2.</a></li>
                             <li><a href="#">动次打次动次打次动态信息3.</a></li>
                             <li><a href="#">动次打次动次打次动态信息4.</a></li>
                             <li><a href="#">动次打次动次打次动态信息5.</a></li>
				           </ul>
				           <a class="faq_but1 but3" href="#">更多</a>
				        </div>
				        <div class="col-md-4 thumb_3">
				           <h4 class="tz-title-5 tzcolor-blue">
				              <p class="tzweight_Bold"><span class="m_20">行业信息<br></span>最新5条</p>
				           </h4>
				           <ul class="list_4">
                             <li><a href="#">动次打次动次打次行业信息1.</a></li>
                             <li><a href="#">动次打次动次打次行业信息2.</a></li>
                             <li><a href="#">动次打次动次打次行业信息3.</a></li>
                             <li><a href="#">动次打次动次打次行业信息4.</a></li>
                             <li><a href="#">动次打次动次打次行业信息5.</a></li>
				           </ul>
                           <a class="faq_but1 but3" href="#">更多</a>
				        </div>
				        <div class="col-md-4">
				           <h4 class="tz-title-5 tzcolor-blue">
				              <p class="tzweight_Bold"><span class="m_20">金牌交易人/团队<br></span>10 Topics</p>
				           </h4>
				           <ul class="list_5 list_6">
				             <li class="list_5-left"><img src="${ctxStatic }/images/support.jpg" class="img-responsive" alt=""></li>
				             <li class="list_5-right"><p>金牌交易人，他的事迹，他的绩效，用户评分等金牌交易人，他的事迹，他的绩效，用户评分等金牌交易人，他的事迹，他的绩效，用户评分等</p></li>
				             <div class="clearfix"> </div>           
				           </ul>
				           <ul class="list_5 list_7">
				             <li class="list_5-left"><img src="${ctxStatic }/images/support1.jpg" class="img-responsive" alt=""></li>
				             <li class="list_5-right"><p>金牌交易人，他的事迹，他的绩效，用户评分等金牌交易人，他的事迹，他的绩效，用户评分等金牌交易人，他的事迹，他的绩效，用户评分等</p></li>
				             <div class="clearfix"> </div> 
				           </ul>
                           <a class="faq_but1 but3" href="#">更多</a>
				        </div>
<!-- 				        <div class="clearfix"> </div> -->
				    </div>
				</div>
                <!-- End of Page 动态信息-->
                
                <!-- Start of 企业服务-->   			
				<div class="about_grid1">
	                <div class="container">
                            <h4 class="tz-title-4 tzcolor-blue">
                               <p class="tzweight_Bold"><span class="m_2">服务内容</span></p>
                            </h4>
				        <div class="service_box">
				            <div class="col-md-4 service_grid1">
				                <i class="icon1"> </i>
				                <h3>商标注册</h3>
				                <p>商标注册的简介,商标注册的简介,,并且提供国际商标注册，点击跳转到一个商标注册相关的子页面里面去，里面包括了所有商标有关的内容</p>
				            </div>
				            <div class="col-md-4 service_grid1">
				                <i class="icon2"> </i>
				                <h3>专利服务</h3>
				                <p>专利服务的简介,专利服务的简介,点击跳转到一个专利服务册相关的子页面里面去，里面包括了所有专利有关的内容</p>
				            </div>
				            <div class="col-md-4 service_grid1">
				                <i class="icon3"> </i>
				                <h3>版权登记</h3>
                                <p>版权登记的简介,版权登记的简介,点击跳转到一个版权登记相关的子页面里面去，里面包括了所有版权有关的内容</p>
                            </div>
				            <div class="clearfix"> </div>
				        </div>
				        <div class="service_box1">
				            <div class="col-md-4 service_grid1">
				                <i class="icon4"> </i>
				                <h3>国际商标</h3>
                                <p>国际商标的简介,国际商标的简介,点击跳转到一个国际商标相关的子页面里面去，里面包括了所有国际商标有关的内容国际商标国际商标国际商标</p>
                            </div>
				            <div class="col-md-4 service_grid1">
				                <i class="icon5"> </i>
				                <h3>法律服务</h3>
                                <p>法律服务的简介,法律服务的简介,点击跳转到一个法律服务相关的子页面里面去，里面包括了所有法律服务有关的内容</p>
                            </div>
				            <div class="col-md-4 service_grid1">
				                <i class="icon6"> </i>
				                <h3>知识产权托管</h3>
                                <p>知识产权托管的简介,知识产权托管的简介,点击跳转到一个知识产权托管相关的子页面里面去，里面包括了所有知识产权托管有关的内容</p>
                            </div>
				            <div class="clearfix"> </div>
				        </div>
				    </div>
				</div>
                <!-- End of Page 动态信息-->
                
                <!-- Start of 企业要闻-->
				<div class="about_grid">
				    <div class="container">
				        <div class="col-md-7">
				            <h4 class="tz-title-4 tzcolor-blue">
				               <p class="tzweight_Bold"><span class="m_2">企业要闻</span></p>
				            </h4>
				            <div class="service_box2">
				                <div class="lsidebar span_1_of_about">
				                    <img src="${ctxStatic }/images/s1.jpg" class="img-responsive" alt=""/>
				                </div>
				                <div class="cont span_2_of_about">
				                    <h3><a href="#">要闻1</a></h3>
				                    <p>特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻</p>
				                </div>
				                <div class="clearfix"></div>    
				            </div>
				            <div class="service_box2">
				                <div class="lsidebar span_1_of_about">
				                    <img src="${ctxStatic }/images/s1.jpg" class="img-responsive" alt=""/>
				                </div>
				                <div class="cont span_2_of_about">
                                    <h3><a href="#">要闻2</a></h3>
                                    <p>特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻特大新闻</p>
                                </div>
				                <div class="clearfix"></div>    
				            </div>
				        </div>
				        <div class="col-md-5">
				            <h4 class="tz-title-4 tzcolor-blue">
				            <p class="tzweight_Bold"><span class="m_2">最新要闻</span></p>
				        </h4>
				            <ul class="project_box">
				              <li class="mini-post-meta"><time datetime=""><span class="day">1</span><span class="month">2017/12/13</span></time></li>
				              <li class="desc"><h5><a href="#">国家公祭日</a></h5>
				                 <p>勿忘历史，振兴中华。打倒小日本~~~~~~~~~~~~~~~~~~~~~~~~</p>
				              </li> 
				              <div class="clearfix"> </div>
				            </ul>
				            <ul class="project_box">
                              <li class="mini-post-meta"><time datetime=""><span class="day">2</span><span class="month">2017/12/13</span></time></li>
                              <li class="desc"><h5><a href="#">国家公祭日</a></h5>
                                 <p>勿忘历史，振兴中华。打倒小日本~~~~~~~~~~~~~~~~~~~~~~~~</p>
                              </li> 
				              <div class="clearfix"> </div>
				            </ul>
				            <ul class="project_box">
                              <li class="mini-post-meta"><time datetime=""><span class="day">3</span><span class="month">2017/12/13</span></time></li>
                              <li class="desc"><h5><a href="#">国家公祭日</a></h5>
                                 <p>勿忘历史，振兴中华。打倒小日本~~~~~~~~~~~~~~~~~~~~~~~~</p>
                              </li> 
				              <div class="clearfix"> </div>
				            </ul>
                            <ul class="project_box">
                              <li class="mini-post-meta"><time datetime=""><span class="day">4</span><span class="month">2017/12/13</span></time></li>
                              <li class="desc"><h5><a href="#">国家公祭日</a></h5>
                                 <p>勿忘历史，振兴中华。打倒小日本~~~~~~~~~~~~~~~~~~~~~~~~</p>
                              </li> 
                              <div class="clearfix"> </div>
                            </ul>
				        </div>
				        <div class="clearfix"> </div>
				    </div>
				</div>
                <!-- End of Page 企业要闻-->
                
                <!-- Start of 客户之声-->
				<div class="about_grid1">
				    <div class="container">
				        <h4 class="tz-title-4 tzcolor-blue">
				            <p class="tzweight_Bold"><span class="m_2">客户之声</span></p>
				        </h4>
				        <div class="wmuSlider example1">
				            <div class="wmuSliderWrapper">
				                <article style="position: absolute; width: 100%; opacity: 0;"> 
				                       <div class="banner-wrap">
				                           <ul class="grid-1">
				                                <li class="grid-1_left">
				                                    <img src="${ctxStatic }/images/s3.jpg" class="img-responsive" alt=""/>
				                                </li>
				                                <li class="grid-1_right">
				                                    <p>好，很好，非常好。谢谢，感谢，非常感谢！！！！！！！！！！！！！！！！！！！！！！！！！</p>
				                                    <h4>--<br><span class="m_10">客户1</span></h4>
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
				                                    <p>我只有一句话，非常不错，买了个打商标。。。。。。。。。。。。。。。</p>
				                                    <h4>--<br><span class="m_10">客户2</span></h4>
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
				                                    <p>我只有一句话，非常不错，买了个打商标。。。。。。。。。。。。。。。</p>
				                                    <h4>--<br><span class="m_10">客户3</span></h4>
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
				    <div class="container">
				        <h4 class="tz-title-4 tzcolor-blue">
				            <p class="tzweight_Bold"><span class="m_2">合作案例<br></span></p>
				        </h4>
				        <div class="box5">
				          <div class="team_box1">
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic4.jpg" class="img-responsive" alt=""/>
				                <div class="team_desc">
				                    <div class="team_desc-left">
				                        <h3>XXX品牌商标</h3>
				                    </div>
				                    <div class="clearfix"> </div>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic5.jpg" class="img-responsive" alt=""/>
				                <div class="team_desc">
				                    <div class="team_desc-left">
                                        <h3>XXX品牌商标</h3>
                                    </div>
				                    <div class="clearfix"> </div>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic6.jpg" class="img-responsive" alt=""/>
				                <div class="team_desc">
				                    <div class="team_desc-left">
                                        <h3>XXX品牌商标</h3>
                                    </div>
				                    <div class="clearfix"> </div>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic7.jpg" class="img-responsive" alt=""/>
				                <div class="team_desc">
				                    <div class="team_desc-left">
                                        <h3>XXX品牌商标</h3>
                                    </div>
				                    <div class="clearfix"> </div>
				                </div>
				            </div>
				            <div class="clearfix"> </div>
				           </div>
				           <div class="team_box2">
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic8.jpg" class="img-responsive" alt=""/>
				                <div class="team_desc">
				                    <div class="team_desc-left">
                                        <h3>XXX品牌商标</h3>
                                    </div>
				                    <div class="clearfix"> </div>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic9.jpg" class="img-responsive" alt=""/>
				                <div class="team_desc">
				                    <div class="team_desc-left">
                                        <h3>XXX品牌商标</h3>
                                    </div>
				                    <div class="clearfix"> </div>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic10.jpg" class="img-responsive" alt=""/>
				                <div class="team_desc">
				                    <div class="team_desc-left">
                                        <h3>XXX品牌商标</h3>
                                    </div>
				                    <div class="clearfix"> </div>
				                </div>
				            </div>
				            <div class="col-md-3 thumb_1">
				                <img src="${ctxStatic }/images/pic11.jpg" class="img-responsive" alt=""/>
				                <div class="team_desc">
				                    <div class="team_desc-left">
                                        <h3>XXX品牌商标</h3>
                                    </div>
				                    <div class="clearfix"> </div>
				                </div>
				            </div>
				            <div class="clearfix"> </div>
				           </div>
				        </div>
				    </div>
				</div>
                <!-- End of Page 客户之声-->
         
                
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