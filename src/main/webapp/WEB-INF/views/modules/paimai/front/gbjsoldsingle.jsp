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
                                        <div class="span8 page-content" >

                                                <ul class="breadcrumb">
                                                        <li><a href="#">国标商标</a><span class="divider">/</span></li>
                                                        <li><a href="#">商标 &amp; 餐饮</a> <span class="divider">/</span></li>
                                                        <li class="active">我想买个小吃店的店名商标</li>
                                                </ul>

                                                <article class=" type-post format-standard hentry clearfix"  ms-repeat-el="datas.domainBuyArticleList">

                                                        <h1 class="post-title"><a href="#">{{el.title}}</a></h1>

                                                        <div class="post-meta clearfix">
                                                                <span class="date">2017/11/28</span>
                                                                <span class="category"><a href="#">商标 &amp; 餐饮</a></span>
                                                                <span class="comments"><a href="#">3个回复</a></span>
                                                                <span class="like-count">66</span>
                                                        </div><!-- end of post meta -->

                                                       <!--   <p>买标买标悬赏等的详细信息。进入这个页面是需要2个参数，id和type。type绝对去从那个标里面去检索。也可以3个表做个视图（看看有没有共通的字段了要）。然后根据id取得对应的详细信息，包括回复和点赞等信息。分享等我后面再加。
                                                                                                                                                                                这里暂时不支持富文本的样式显示，只支持一般文本就好了。
                                                        </p>-->
                                                        <p>{{el.description}}</p>

                                                </article>

                                                <div class="like-btn">

                                                        <form id="like-it-form" action="#" method="post">
                                                                <span class="like-it ">66</span>
                                                                <input type="hidden" name="post_id" value="这里是该条信息的id，提交这个表单">
                                                                <input type="hidden" name="action" value="like_it">
                                                        </form>

                                                        <span class="tags">
                                                                <strong>标签:&nbsp;&nbsp;</strong><a href="#" rel="tag">餐饮</a>, <a href="#" rel="tag">商标</a>, <a href="#" rel="tag">小吃</a>
                                                        </span>

                                                </div>

                                                <section id="comments" ms-repeat-ell="datas.domainBuyCommentsArticleList">

                                                        <h3 id="comments-title">评论内容</h3>

                                                        <ol class="commentlist" >

                                                                <li class="comment even thread-even depth-1" id="li-comment-2">
                                                                        <article id="comment-2">

                                                                                <a href="#">
                                                                                        <img alt="" src="http://1.gravatar.com/avatar/50a7625001317a58444a20ece817aeca?s=60&amp;d=http%3A%2F%2F1.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D60&amp;r=G" class="avatar avatar-60 photo" height="60" width="60">
                                                                                </a>

                                                                                <div class="comment-meta">

                                                                                        <h5 class="author">
                                                                                                <cite class="fn">
                                                                                                        <a href="#" rel="external nofollow" class="url">这里头像回头用用户表里面存储的qq或weixin头像和名字</a>
                                                                                                </cite>
                                                                                                - <a class="comment-reply-link" href="#">回复</a>
                                                                                        </h5>

                                                                                        <p class="date">
                                                                                                 <time datetime="2013-02-26T13:18:47+00:00">{{ell.commentTime}}</time>
                                                                                        </p>

                                                                                </div><!-- end .comment-meta -->

                                                                                <div class="comment-body" >
                                                                                        <p>{{ell.comment}}</p>
                                                                                </div><!-- end of comment-body -->

                                                                        </article><!-- end of comment -->

                                                                        <ul class="children">

                                                                                <li class="comment byuser comment-author-saqib-sarwar bypostauthor odd alt depth-2" id="li-comment-3">
                                                                                        <article id="comment-3">

                                                                                                <a href="#">
                                                                                                        <img alt="" src="http://0.gravatar.com/avatar/2df5eab0988aa5ff219476b1d27df755?s=60&amp;d=http%3A%2F%2F0.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D60&amp;r=G" class="avatar avatar-60 photo" height="60" width="60">
                                                                                                </a>

                                                                                                <div class="comment-meta">

                                                                                                        <h5 class="author">
                                                                                                                <cite class="fn">头像和名字一样数据库取得</cite>
                                                                                                                - <a class="comment-reply-link" href="#">回复</a>
                                                                                                        </h5>

                                                                                                        <p class="date">
                                                                                                                <time datetime="2013-02-26T13:20:14+00:00">February 26, 2013 at 1:20 pm</time>
                                                                                                        </p>

                                                                                                </div><!-- end .comment-meta -->

                                                                                                <div class="comment-body">
                                                                                                        <p>{{ell.comment}}</p>
                                                                                                </div><!-- end of comment-body -->

                                                                                        </article><!-- end of comment -->

                                                                                </li>
                                                                        </ul>
                                                                </li>

                                                                <li class="comment even thread-odd thread-alt depth-1" id="li-comment-4">
                                                                        <article id="comment-4">

                                                                                <a href="#">
                                                                                        <img alt="" src="http://1.gravatar.com/avatar/50a7625001317a58444a20ece817aeca?s=60&amp;d=http%3A%2F%2F1.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D60&amp;r=G" class="avatar avatar-60 photo" height="60" width="60">
                                                                                </a>

                                                                                <div class="comment-meta">

                                                                                        <h5 class="author">
                                                                                                <cite class="fn">
                                                                                                        <a href="#" rel="external nofollow" class="url">这里头像回头用用户表里面存储的qq或weixin头像和名字</a>
                                                                                                </cite>
                                                                                                - <a class="comment-reply-link" href="#">回复</a>
                                                                                        </h5>

                                                                                        <p class="date">
                                                                                                 <time datetime="2013-02-26T13:18:47+00:00">2017/11/28 13:18:56</time>
                                                                                        </p>

                                                                                </div><!-- end .comment-meta -->

                                                                                <div class="comment-body">
                                                                                        <p>这里是回复内容。下面的回复一样，ajax取得数据后重复显示出来表示即可.注意元素的id</p>
                                                                                </div><!-- end of comment-body -->

                                                                        </article><!-- end of comment -->
                                                                </li>
                                                        </ol>

                                                        <div id="respond">

                                                                <h3>评论回复</h3>

                                                                <div class="cancel-comment-reply">
                                                                        <a rel="nofollow" id="cancel-comment-reply-link" href="#" style="display:none;">Click here to cancel reply.</a>
                                                                </div>


                                                                <form action="#" method="post" id="commentform">


                                                                        <p class="comment-notes">登录后可进行评论回复。这里回头我加上qq登录。</p>


                                                                        <div>
                                                                                <label for="comment">Comment</label>
                                                                                <textarea class="span8" name="comment" id="comment" cols="58" rows="10"></textarea>
                                                                        </div>

                                                                        <div>
                                                                                <input class="btn" name="submit" type="submit" id="submit"  value="提交评论">
                                                                        </div>

                                                                </form>

                                                        </div>

                                                </section><!-- end of comments -->

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
</html>
