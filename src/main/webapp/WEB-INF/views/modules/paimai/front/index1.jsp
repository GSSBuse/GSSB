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

        <body>
                <%@ include file="/WEB-INF/views/include/frontTop.jsp"%>

                <!-- Start of Page Container -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">
                                        <div class="span12 page-content">
                                                <div class="row-fluid top-cats" style="text-align: center;">

                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service1.png"/>
                                                                <h4><a href="#">我要买标</a></h4>
                                                                <div class="category-description">
                                                                        <p>发布您需要咨询或有购买意向的商标专利信息，第一时间获取交易信息</p>
                                                                </div>
                                                        </section>

                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service2.png"/>
                                                                <h4><a href="#">我要卖标</a></h4>
                                                                <div class="category-description">
                                                                        <p>发布您需要咨询或有购买意向的商标专利信息，第一时间获取交易信息</p>
                                                                </div>
                                                        </section>

                                                        <section class="span3">
                                                                <img src="${ctxStatic }/images/service3.png"/>
                                                                <h4><a href="#">悬赏起名</a></h4>
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
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">求一个餐馆的店名</a></h4>
                                                                                <span class="article-meta">2017/11/12 <a href="#" title="查看该分类">商标   &amp; 餐饮</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里用ajax取得后台数据库的信息来显示</a></h4>
                                                                                <span class="article-meta">2017/11/12 <a href="#" title="查看该分类">商标   &amp; 餐饮</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是买标信息基本描述信息，如果字长的话截取表示</a></h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">循环展示，最多6条就够了</a></h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">按照发布时期排序吧</a></h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是基本描述信息，如果字长的话截取表示</a></h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">
                                                                <h3 class="category">卖标信息</h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">“weixin.com”域名脱手</a></h4>
                                                                                <span class="article-meta">2017/11/12 <a href="#" title="查看该分类">商标   &amp; 餐饮</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是卖标信息基本描述信息，如果字长的话截取表示</a></h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是卖标信息基本描述信息，如果字长的话截取表示</a></h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">循环展示，最多6条就够了</a></h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">按照发布时期排序吧</a></h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是卖标信息基本描述信息，如果字长的话截取表示</a></h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                </ul>
                                                        </section>
                                                        
                                                        <section class="span4 articles-list">
                                                                <h3 class="category">悬赏起名</h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">国标商标官网logo悬赏起名</a> 1000元</h4>
                                                                                <span class="article-meta">2017/11/12 <a href="#" title="查看该分类">商标   &amp; 餐饮</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是悬赏信息，如果字长的话截取表示</a> 悬赏金额</h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是悬赏信息，如果字长的话截取表示</a> 悬赏金额</h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是悬赏信息，如果字长的话截取表示</a> 悬赏金额</h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是悬赏信息，如果字长的话截取表示</a> 悬赏金额</h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="${ctx }/single.html">这里是悬赏信息，如果字长的话截取表示</a> 悬赏金额</h4>
                                                                                <span class="article-meta">YYYY/MM/DD <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                </ul>
                                                        </section>
                                                </div>
                                        </div>
                                        <div class="row home-category-list-area">
                                                <div class="span12" style="text-align: center;">
                                                        <input type="submit" name="submit" value="查看更多" class="btn btn-inverse">
                                                </div>
                                        </div>
                                        <!-- end of page content -->

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