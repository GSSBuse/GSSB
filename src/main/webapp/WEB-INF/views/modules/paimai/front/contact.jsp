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

                <%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
                <div class="about">
                     <div class="container">
                        <h1>联系我们<span class="m_1"><br>您有什么问题，我都可以帮您解答</span></h1>
                    </div>
                </div>

                <!-- Start of Page Container -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">

                                        <!-- start of page content -->
                                        <div class="span8 page-content">

                                                <article class="type-page hentry clearfix">
                                                        <h1 class="post-title">
                                                                <a href="#">联系我们</a>
                                                        </h1>
                                                        <hr>
                                                        <p>您有任何建议、咨询、问题都可以在线留言，我们将在第一时间给您答复。</p>
                                                </article>


                                                <form id="contact-form" class="row" action="http://inspirythemes.com/templates/knowledgebase-html/contact_form_handler.php" method="post">

                                                        <div class="span2">
                                                                <label for="name">您的姓名 <span>*</span> </label>
                                                        </div>
                                                        <div class="span6">
                                                                <input type="text" name="name" id="name" class="required input-xlarge" value="" title="* Please provide your name">
                                                        </div>

                                                        <div class="span2">
                                                                <label for="email">您的邮箱 <span>*</span></label>
                                                        </div>
                                                        <div class="span6">
                                                                <input type="text" name="email" id="email" class="email required input-xlarge" value="" title="* Please provide a valid email address">
                                                        </div>

                                                        <div class="span2">
                                                                <label for="phone">您的电话  <span>*</span></label>
                                                        </div>
                                                        <div class="span6">
                                                                <input type="text" name="phone" id="phone" class="input-xlarge" value="">
                                                        </div>

                                                        <div class="span2">
                                                                <label for="message">您要咨询的内容 <span>*</span> </label>
                                                        </div>
                                                        <div class="span6">
                                                                <textarea name="message" id="message" class="required span6" rows="6" title="* Please enter your message"></textarea>
                                                        </div>

                                                        <div class="span6 offset2 bm30">
                                                                <input type="submit" name="submit" value="提交" class="btn btn-inverse">
                                                                <img src="images/loading.gif" id="contact-loader" alt="Loading...">
                                                        </div>

                                                        <div class="span6 offset2 error-container"></div>
                                                        <div class="span8 offset2" id="message-sent"></div>

                                                </form>
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

