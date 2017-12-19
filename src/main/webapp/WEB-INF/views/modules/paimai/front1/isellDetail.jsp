<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>米乐拍卖-我要买</title>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<script type="text/javascript">
	var domainInfo = ${domainInfoJson};
</script>
<script type="text/javascript" src="${ctxStatic }/front/js/isell-detail.js"></script>

<style type="text/css">
</style>
</head>
<body class="v4 layout01 ">
	 <div id="uiWrapper">
      	<div id="uiContainer">
        
          	<div id="uiHeader">
          		<%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
	          	<!-- 蓝条结束 -->
          	</div>
          	
         <!-- 头部结束 -->
         <div class="clear"></div>
         <!-- content开始 -->
         	
  		<div class="contentbase" ms-controller="isell-detail">
      			 <!-- typo3PageId: 66 -->
			<div id="col3">
				<a class="button-white ab-left" href="${ctx }/isell.html"><i class="oni-icon"></i>返回</a>
				<div class="precontent">
					<h1>添加域名拍卖</h1>
					<p>添加一个新的域名拍卖，您的域名经过审核后将会出现在首页</p>
				</div>
				<div class="clear"></div>
				<div class="content">
					<div class="contentbox">
						<form id="domainform" method="post" action="${ctx }/isell/save.html" ms-widget="validation">
								<div class="m09 whitebox" style="overflow: visible;">
									<div class="sedoform">
										<h1>域名信息</h1>
										<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)"> 
										<div class="blockcolsingle1 fLeft">
											<div class="row">
												<label class="col1">
													<span>*域名:</span>
												</label>
												<span class="col2">
													<input type="text" name="name" size="20" ms-duplex-required-maxlength-domainname="datas.domainInfo.name" maxlength="63" class="input">
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>描述:</span>
												</label>
												<span class="col2" style="height: auto;">
													<textarea name="description" ms-duplex-maxlength="datas.domainInfo.description" maxlength="100" cols="20" rows="4" class="input" style="height: auto;"></textarea>
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>*保留价:</span>
												</label>
												<span class="col2">
													<input name="reservePrice" type="text" size="20" ms-duplex-int="datas.domainInfo.reservePrice" class="input"><span></span>(元)
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>*截止日期:</span>
												</label>
												<span class="col2">
													<input name="endTime" class="input" ms-widget="datepicker" ms-duplex="datas.domainInfo.endTime"/>
												</span>
											</div>
											
											<div class="row">
												<label class="col1">
													<span>
													次高价红包
													<input type="checkbox" ms-duplex="datas.tmp.check" value="on">:</span>
												</label>
												<span class="col2">
													<input name="bonusSecond" ms-attr-disabled="datas.tmp.check.size()==0" type="text" class="input" ms-duplex-int="datas.domainInfo.bonusSecond" >(元)
												</span>
											</div> 
										</div>
									</div>
										<div class="clear"></div>
										<div id="tax_data" class="sedoform"></div>
								</div>
			
					
								<!-- <div class="m09 whitebox">
									<p><input type="Checkbox" name="acceptagb" tabindex="11">
										通过勾选选择框，本人确认已阅读并接受 Sedo 的 <a class="internal_link" href="" target="_blank">条款及细则</a> 和 <a class="internal_link" href="" target="_blank">隐私权政策</a>。
									</p>
								</div> -->
								<div class="m09 ">*标有星号的空格必须填入所需信息。</div>
								<div class="m09">
									<div class="fCenter">
										<input id="submitChange" class="submitbutton sb160" type="submit" value="保存更改">
									</div>
								</div>
							</form>
						<br>
						<br>
					</div>
				</div>
			</div>
			<div class="clear"></div>
         	
         <!-- content结束 -->

	         	</div>
	         </div>
         
	         <%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
         
     	</div>
</body>
</html>