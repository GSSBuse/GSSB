<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>米乐拍卖-提交身份认证</title>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<script type="text/javascript">
//<!--
var pageData = ${initData};
// -->
</script>
<script type="text/javascript" src="${ctxStatic }/front/js/idcardAuth.js"></script>
</head>

<body class="v4 layout01">
	
	 <div id="uiWrapper">
      	<div id="uiContainer">
        
          	<div id="uiHeader">
          		<%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
          	<!-- 蓝条结束 -->
         	</div>
         <!-- 头部结束 -->
         <div class="clear"></div>
         
         <!-- content开始 -->
         	
  		<div class="contentbase ms-controller" ms-controller="idcardAuth">
      			 <!-- typo3PageId: 66 -->
			<div id="col3">
				<div class="precontent">
					<h1>我的帐号</h1>
				</div>
				<div class="clear"></div>
			
				<div class="thirdnavi">
					 <ul>
						  <li class="selected"><a href="">提交身份证认证</a></li>
					 </ul>
				</div>
				<div class="content">
					<div class="contentbox">
						<form name="accountform" method="post" id="accountform"  ms-widget="validation" action="" enctype="multipart/form-data" target="uploadFrame">
								<div class="m09 whitebox upload">
									<div class="sedoform">
										<div class="blockcolsingle1 fLeft">
											<div class="row">
												<label class="col1">
													<span>*身份证号:</span>
												</label>
												<span class="col2">
													<input type="text" name="idcardNumber" ms-duplex-id="datas.clientInfo.idcardNumber" class="input" style="width: 200px">
												</span>
											</div>
											<div class="row uploadingimg">
												<label class="col1">
													<span>*身份证照片:</span>
												</label>
												<label class="col2" >
													<img id="authenticationPositiveImage" src="${ctxStatic }/images/upimage.jpg" width="100" title="正面" alt="正面">
													<input ms-change="choosePositive()" type="file" name="authenticationPositiveImageFile"accept="image/png,image/gif,image/jpeg">
												</label>
												<label class="col2" >
													<img id="authenticationNegativeImage" src="${ctxStatic }/images/upimage.jpg" width="100" title="背面" alt="背面">
													<input ms-change="chooseNegative()" type="file" name="authenticationNegativeImageFile" accept="image/png,image/gif,image/jpeg">
												</label>
											</div>
											<div class="clear"></div>
										</div>
						
									</div>
										<div class="clear"></div>
										<div id="tax_data" class="sedoform"></div>
								</div>
								<div class="m09 ">*标有星号的空格必须填入所需信息。</div>
								<div class="m09">
									<div class="fCenter">
										<input id="submitChange" class="submitbutton sb160" type="submit" name="submitbtn" value="提交认证信息">
									</div>
								</div>
							</form>
						<br>
						<iframe name="uploadFrame" style="width: 0px;height: 0px;visibility: hidden;"></iframe>
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