<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>天天域名-我的交易</title>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>

<script type="text/javascript" src="${ctxStatic }/front/js/securitySetting.js"></script>
</head>

<body class="v4 layout01">
	
	 <div id="uiWrapper">
      	<div id="uiContainer">
        
          	<div id="uiHeader">
          		<%@ include file="/WEB-INF/views/include/frontTop.jsp"%>
          	<!-- 蓝条结束 -->
         	</div>
         <!-- 头部结束 -->
         <div class="clear"></div>
         
         <!-- content开始 -->
         	
  		<div class="contentbase ms-controller" ms-controller="securitySetting" id="securitySetting">
      			 <!-- typo3PageId: 66 -->
			<div id="col3">
				<div class="precontent">
					<h1>我的账户</h1>
					<p>管理个人信息、首选项和账户状态</p>
				</div>
				<div class="clear"></div>
			
				<div class="thirdnavi">
					 <ul>
						  <li class="selected"><a href="">密码设置</a></li>
						  <!-- <li class=""><a href="">修改密码</a></li> -->
					 </ul>
				</div>
				<div class="content">
					<div class="contentbox">
							<div class="sedoform">
							
								<!-- 重置密码 -->
								<!-- <div class="m09 whitebox ">
									<h1>忘记密码？</h1>
									<h1>发送信息到关联的用户认证联系方式（号码？还是？）已重置密码</h1>
									<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)">
									<div class="row clear">
												<label class="col1">
													<span>*联系方式:</span>
												</label>
												<span class="col2">
													<input type="text" size="11" maxlength="12" name="" value="88888888" class="input" tabindex="2">
													<span id="iok_userName" class="tips i-ok marginright260" ></span>
												</span>
									</div>
									<div class="row" style="margin:20px 100px;">
										<button>发送</button>
									</div>
								</div>
								 -->
								<!-- 修改密码 -->
								<div class="m09 whitebox ">
									<div class="row" style="margin:20px;">
												<label class="col1">
													<span>*原密码:</span>
												</label>
												<span class="col2">
													<input id="oldPasswordId" type="text" size="11" maxlength="12" name="oldPassword" value=""class="input" tabindex="2">
													<span id="iok_userName" class="tips i-ok marginright260" ></span>
												</span>
									</div>
									<div class="row" style="margin:20px;">
												<label class="col1">
													<span>*新密码:</span>
												</label>
												<span class="col2">
													<input id="newPasswordId" type="text" size="11" maxlength="12" name="newPassword" value="" class="input" tabindex="2">
													<span id="iok_userName" class="tips i-ok marginright260" ></span>
												</span>
									</div>
									<div class="row" style="margin:20px;">
												<label class="col1">
													<span>*确认密码:</span>
												</label>
												<span class="col2">
													<input id="confirmPasswordId" type="text" size="11" maxlength="12" name="confirmPassword" value="" class="input" tabindex="2">
													<span id="iok_userName" class="tips i-ok marginright260" ></span>
												</span>
									</div>
									<div class="row " style="margin:20px 100px;">
										<button ms-click="securityCheck()">确认修改</button>
									</div>
								</div>
							</div>
						<br>
						<br>
					</div>
				</div>
			</div>
      <div class="clear"></div>
      <%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
    </div>
         	
         <!-- content结束 -->
         
       </div>
     </div>
     <!-- 容器结束 -->
</body>
</html>