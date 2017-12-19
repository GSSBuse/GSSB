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
<script type="text/javascript" src="${ctxStatic }/front/js/Article.js"></script>
        <body ms-controller="articles"> 
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
                                                        <span><h1 class="post-title"><a href="#">交易信息</a></h1></span>
                                                        <span>
                                                        <input type="button" onClick="javascript:alert('把首页的show1()移过来');"  value="我要发布买标信息">
                                                                             
                                                </article>
                                                <article class="format-standard type-post hentry clearfix"  ms-repeat-el="datas.domainArticleList">

                                                        <header class="clearfix">

                                                                <h3 class="post-title">
                                                                        <a href="${ctx }/single.html">{{el.title}}</a>
                                                                </h3>

                                                                <div class="post-meta clearfix">
                                                                        <span class="date">{{el.createDate}}</span>
                                                                        <span class="category"><a href="#" title="查询该标签所有内容">{{el.typeId}} &amp;&amp;&amp; 餐饮</a></span>
                                                                        <span class="comments"><a href="#">3个回复</a></span>
                                                                        <span class="like-count">66</span>
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

        </body>
</html>
