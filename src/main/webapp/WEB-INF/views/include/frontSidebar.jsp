<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <script type="text/javascript" src="${ctxStatic }/front/js/sidebar.js"></script> --%>

<script type="text/javascript" src="${ctxStatic }/front/js/tag.js"></script>
<br/>
<br/><br/>
<section class="widget">
        <div class="support-widget">
                <h3 class="title">在线支持</h3>
                <p class="intro">是否需要帮助? 如果您需要帮助请联系我们！400-590-580
                <img  style="CURSOR: pointer" onclick="javascript:window.open('http://b.qq.com/webc.htm?new=0&sid=4005958&o=国商商标&q=7', '_blank', 'height=502, width=644,toolbar=no,scrollbars=no,menubar=no,status=no');"  border="0" SRC=http://wpa.qq.com/pa?p=1:4005958:10 alt="点击这里给我发消息">
                </p>
        </div>
</section>

<section class="widget">
        <h3 class="title">分类</h3>
        
        <div align="center">
                                           <a href="${ctx }/buyarticles.html" >
                                               
                                                       <img style="width:30%;" src="${ctxStatic }/images/service1.png"/>
                                               
                                            </a>
                                            <a href="${ctx }/soldarticles.html" img style="width:30%;">
                                               
                                                       <img style="width:30%;" src="${ctxStatic }/images/service2.png"/>
                                               
                                            </a>
                                            <a href="${ctx }/rewardarticles.html" img style="width:30%;">
                                               
                                                       <img style="width:30%;" src="${ctxStatic }/images/service3.png"/>
                                               
                                            </a>
                                            
                                       </div>
        
        
       <%--  <div align="center">
		        <a href="#"><img style="width:30%;" src="${ctxStatic }/images/service1.png"/></a>
                <a href="#"><img style="width:30%;" src="${ctxStatic }/images/service2.png"/></a>
                <a href="#"><img style="width:30%;" src="${ctxStatic }/images/service3.png"/></a>
        </div> --%>
</section>

<section class="widget" ms-controller="tag">
        <h3 class="title">标签</h3>
        <div class="tagcloud">
        
        <ul class="btn btn-mini" ms-repeat-el="datas.domainTagList">
                <li class="recentcomments"><a href="#" rel="external nofollow" class="url">{{el.tagName}}</a>
        </ul>
        
               
        </div>
</section>
<%-- <section class="widget" ms-controller="sidebar">
        <h3 class="title">最新评论</h3>
        <ul id="recentcomments" ms-repeat-el="datas.domainArticleList">
                <li class="recentcomments"><a href="#" rel="external nofollow" class="url">张三</a> on <a href="#">{{el.title}}</a></li>
        </ul>
</section>

<section class="widget"  ms-controller="sidebar" >
        <h3 class="title">最新交易信息</h3>
        <ul class="articles" ms-repeat-el="datas.domainArticleList">
                <li class="article-entry standard">
                        <h4><a href="${ctx }/single.html">{{el.title}}</a></h4>
                        <span class="article-meta">{{el.createDate}} <a href="#" title="查看该分类">类别   &amp; 行业分类</a></span>
                        <span class="like-count">{{el.upCounts}}</span>
                </li>
        </ul>
</section> --%>
