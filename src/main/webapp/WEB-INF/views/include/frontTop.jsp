<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

				<ul id="uiMainNav">
          			<li><a href="${ctx }/index.html">首页</a></li>
          			<li><a href="${ctx }/ibuy.html" class="">我要买</a></li>
          			<c:if test="${ currentClient != null}" >
	          			<li><a href="${ctx }/isell.html">我要卖</a></li>
	          			<li><a href="${ctx }/icenter.html">个人中心</a>
	          				<div class="sub">
	          					<a href="${ctx }/icenter.html">个人信息</a>
	          					<a href="${ctx }/financialManagement/financeInfo.html">财务管理</a>
	          					<a href="${ctx }/myTransactions.html">我的交易</a>
	          					<%-- <a href="${ctx }/securitysetting.html">安全设置</a> --%>
	          				</div>
	          			</li>
          			</c:if>
          		</ul>
          		
          		<div id="uiBlueBar">
          			<a id="uiLogo" href="${ctx }/index.html" title="首页">拍域名</a>
          			<div id="uiSearch">
          				<form method="get" action="${ctx }/ibuy.html">
          					<span class="table-cell search-input">
          						<input type="hidden" name="keyword">
          						<input type="hidden" name="search_trigger" value="off">
          						
          						<span class="input-wrapper">
          							<span class="search-input">
          								<input class="mainSearch" type="text" value="${searchDomain}" name="domain" id="">
          							</span>
	          						<!-- <span id="">
		          						<div class="tld-dialog-selector">
		          							<span class="tld-dialog-selector-text">后缀</span>
		          							<span class="tld-dialog-selector-toggle"></span>
		          						</div>
	          						</span> -->
          						</span>
          					</span>
          					<span class="table-cell search-button" id="submitSearch"><a href="" class="button green L left"><span>搜索</span></a></span>
          				</form>
          			</div>
          			<div id="uiLoginSignup">
          				<div id="uiLoginBox">
          					<c:if test="${ currentClient != null}" >
          					<div class="loggedin" id="js_showUserNotifications" 
          							<c:if test="${ currentClient == null}">style="display: none;"</c:if>
          							<c:if test="${ currentClient != null}">style="display: block;"</c:if>>
          						<div id="" class=" formular"  >
		          						<div class="loggedin" id="" style="">
		          							<span>
		          								<strong class="name">您好 ${currentClient.nickname }!</strong> 
		          								<a href="" class="logout" title="注销" id="logout">注销</a>
		          							</span>
		          						</div>
		          					</div>
          					</div>
          					</c:if>
          					<c:if test="${ currentClient == null}" >
	          				<div class="loggedout" >
		          				<div class="uiLoginBoxButton"><a href="#" class="uiLoginBoxButtonInner" id="js_login_box_button">&nbsp;登录&nbsp;</a>
		          					<div id="js_uiLoginBox" class="uiLoginBox formular" style="display: none;">
		          						<img alt="微信登录二维码" src="${ctx }/common/loginQrImage"><br>
		          						请使用微信扫一扫授权登录。
		          					</div>
		          					
		          					<div id="js_uiLoginBox2" class="uiLoginBox formular" style="display: none;">
		          						<span class="line" id="js_input_empty_hint" style="display: none; color:red;">用户名或密码不能为空！</span>
		          						<span class="line" id="js_error_hint" style="display: none; color:red;">登陆信息错误！</span>
		          						<form  method="post" name="loginform" id="js_bluebar_login_form">
		          							<input type="hidden" name="linkurl" value="">
		          							<input type="hidden" name="partnerid" value="">
		          							<input type="hidden" name="language" value="cn">
		          							<input type="hidden" name="session" value="">
		          							<input type="hidden" name="tracked" value="">
		          							<input type="hidden" name="task" value="login">
		          							<span class="line">
		          								<input type="text" class="input text js_default_val" tabindex="100" name="login_name" placeholder="米友号"></span>
		          							<span class="line">
		          								<input type="password" class="input text js_default_val" tabindex="101" name="password" placeholder="密码"></span>
		          							<span class="line aCenter" id="js_login_span">
		          								<button type="button" class="button green M" id="js_login_button" value="">
		          								<span>登录</span></button>
		          							</span>
		          						    <span class="line aCenter"><a href="" class="forgotdetails">若忘记密码请联系经纪人</a></span>
		          							<!--<span class="line fRight"><a href="" class="create_new_account register_link">创建新账户！</a></span> -->
		          						
		          						</form>
		          					</div>
		          				</div>
	          				</div>
	          				</c:if>
          				</div>
          		</div>
          	</div>