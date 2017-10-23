<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>天天域名-个人中心</title>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<script type="text/javascript">
//<!--
var pageData = ${initData};
// -->
</script>
<script type="text/javascript" src="${ctxStatic }/front/js/icenter.js"></script>
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
         	
  		<div class="contentbase ms-controller" ms-controller="icenter">
      			 <!-- typo3PageId: 66 -->
			<div id="col3">
				<div class="precontent">
					<%-- <div class="keyvisual">
						<img src="${ctxStatic }/front/img/WorldMap.jpg"/>
					</div> --%>
					<h1>我的账户</h1>
					<p>管理个人信息、首选项和账户状态</p>
				</div>
				<div class="clear"></div>
			
				<div class="thirdnavi">
					 <ul>
						  <li class="selected"><a href="">个人信息</a></li>
					 </ul>
				</div>
				<div class="content">
					<div class="contentbox">
						<form name="accountform" method="post" id="accountform"  ms-widget="validation">
								<div class="m09 whitebox">
									<div class="sedoform">
										<div class="blockcolsingle1 fLeft">
											<div class="row">
												<label class="col1">
													<span>米友号:</span>
												</label>
												<span class="col2">
													${ currentClient.dyid}
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>昵称:</span>
												</label>
												<span class="col2">
													${ currentClient.nickname}
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>*姓名:</span>
												</label>
												<span class="col2">
													<input type="text" size="15" maxlength="30" name="name" 
														ms-duplex-required="datas.clientInfo.name"
														ms-attr-readonly="datas.clientInfo.authenticationMark!=0" 
														ms-class-input="datas.clientInfo.authenticationMark==0">
												</span>
												<span ms-if="datas.clientInfo.authenticationMark==0">
													（未认证, <a href="${ctx}/icenter/idcardAuth.html">点此立即认证</a>）
												</span>
												<span ms-if="datas.clientInfo.authenticationMark==1">
													（已认证）
												</span>
												<span ms-if="datas.clientInfo.authenticationMark==2">
													（认证中）
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>*银行信息:</span>
												</label>
												<span class="col2">
													<input type="text" size="11" maxlength="11" name="defaultIncomeExpense" 
														ms-duplex="datas.clientInfo.defaultIncomeExpense"
														readonly="readonly"  style="width: 140px;">
													<button type="button" ms-click="addDefaultIncomeExpense">设置银行信息</button>
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>手机:</span>
												</label>
												<span class="col2">
													<input type="text" size="11" maxlength="11" name="mobile" 
														ms-duplex="datas.clientInfo.mobile"
														readonly="readonly"  style="width: 140px;">
													<button type="button" ms-click="modifyMobile()">设置手机号码</button>
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>Email:</span>
												</label>
												<span class="col2">
													<input type="text" readonly="readonly" name="email" ms-duplex="datas.clientInfo.email" style="width: 140px;">
													<button type="button" ms-click="modifyEmail()">设置邮箱</button>
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>微信:</span>
												</label>
												<span class="col2">
													<input type="text" size="15" maxlength="30" name="wx" ms-duplex="datas.clientInfo.wx" class="input">
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>QQ:</span>
												</label>
												<span class="col2">
													<input type="text" size="11" maxlength="12" name="qq" ms-duplex-qq="datas.clientInfo.qq" class="input" tabindex="2">
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>*经纪人:</span>
												</label>
												<span class="col2">
													${ currentClient.broker.name}
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>身份证号:</span>
												</label>
												<span class="col2">
													<input type="text" name="idcardNumber" ms-duplex="datas.clientInfo.idcardNumber" readonly="readonly">
												</span>
											</div>
											<div class="row uploadingimg">
												<label class="col1">
													<span>身份证照片:</span>
												</label>
												<span class="col1" ms-if="datas.clientInfo.authenticationPositiveImageUrl!=''">
													<img ms-attr-src="datas.clientInfo.authenticationPositiveImageUrl">
												</span>
												<span class="col1" ms-if="datas.clientInfo.authenticationNegativeImageUrl!=''">	
													<img ms-attr-src="datas.clientInfo.authenticationNegativeImageUrl">
												</span>
												<span class="col1" ms-if="datas.clientInfo.authenticationNegativeImageUrl=='' && datas.clientInfo.authenticationPositiveImageUrl==''">	
													未上传
												</span>
											</div>
											
										</div>
										<div class="blockcolsingle2 fRight" style="width: 100px;height: 100px;">
											<img src="${ currentClient.photo}" />
										</div>
						
									</div>
										<div class="clear"></div>
										<div id="tax_data" class="sedoform"></div>
								</div>
								<div class="m09 ">*标有星号的空格必须填入所需信息。</div>
								<div class="m09">
									<div class="fCenter">
										<input id="submitChange" class="submitbutton sb160" type="submit" name="submit" value="保存更改" tabindex="12">
									</div>
								</div>
							</form>
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