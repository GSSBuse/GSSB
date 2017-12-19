<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>米乐拍卖-我要买</title>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<script type="text/javascript">
	//<!--
	var pageData = ${initData};
	// -->
</script>
<script type="text/javascript" src="${ctxStatic }/front/js/ibuy.js"></script>
</head>
<body class="v4 layout01 ">
	 <div id="uiWrapper">
      	<div id="uiContainer">
        
          	<div id="uiHeader">
          		<%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
	          	<!-- 蓝条结束 -->
          	</div>
          	
         <!-- 头部结束 -->
         
         <div id="uiContent" class="ms-controller" ms-controller="ibuy">
         	<div id="uiContentRSS" class="uiContent">
         		<!-- <div id="img-container" class="fLeft">
         			 <img id="moblieImg" src="../allfiles/mobile.png"></img>
         			<span>扫我关注本网站官方微信，更方便哦~</span> 
         		</div> -->
  		
         		<div class="buy_table-wrapper">
	                <div class="table-container">
	                    <table class="table blue" id="">
	                        <thead class="thead">
	                            <tr class="tr">
	                                <td class="th bl even"><a href=""><h3>域名</h3></a></td>
	                                <td class="th bl aLeft"><a href=""><h3>当前价格</h3></a></td>
	                                <td class="th bl aLeft"><a href=""><h3>剩余时间</h3></a></td>
	                                <td class="th bl aLeft"><a href=""><h3>出价</h3></a></td>
	                               <!--  <td class="th aLeft odd"><a class="icon rss" id="js_rss_fd_link" href="" target="_blank"></a></td> -->
	                            </tr>
	                        </thead>
	                        <tbody class="tbody">
		                        <tr ms-repeat-del="datas.domainList" class="tr shuffleRow even" ms-class-odd="$index%2 == 0">
			                        <td class="td even"><a class="url" href="" ms-attr-title="del.name" onclick="return false;" ms-click="goToSingleDomainname($index)">{{del.name}}</a></td>
			                        <td class="td aLeft odd price">
		                        		<span ms-if="del.currAmount==0">该域名无出价</span>
										<span ms-if="del.currAmount>0 && !getCountDown(del.endTime, $index).disabled">{{del.currAmount | transferCurrentAmount}}</span>
										<span ms-if="del.currAmount>0 && getCountDown(del.endTime, $index).disabled">成交:{{del.currAmount | transferCurrentAmount}}</span>
			                        </td>
			                         <td class="td aLeft"><span ms-html="getCountDown(del.endTime, $index).displayTime"></span></td>
			                        <td class="td aLeft">
			                        	<c:choose>
			                        		<c:when test="${not empty currentClient &&  not empty currentClient.id}">
			                        			<span ms-if="!getCountDown(del.endTime, $index).disabled">
			                        				<a class="url price icon" ms-attr-href="'${ctx}/ibuysingle.html?domainId=' + del.id" ms-attr-title="del.name" ms-if="del.clientId != datas.userinfo.id"><strong>出价</strong></a>
			                        				<a class="url price icon" ms-attr-href="'${ctx}/ibuysingle.html?domainId=' + del.id" ms-attr-title="del.name" ms-if="del.clientId == datas.userinfo.id"><strong class="color-myDomainname">自己出售的域名</strong></a>
			                        			</span>
			                        		</c:when>
			                        		<c:otherwise>
			                        			<a class="url price icon" href="#" ms-attr-title="del.name" ms-click="showLogin()"><strong>我要出价</strong></a>
			                        		</c:otherwise>
			                        	</c:choose>
			                        </td>
		                        </tr>
			                    </tbody>
			                    
		                        <tfoot class="">
		                			<tr class="odd">
					                    <!-- <td class="first"><input class="checkall" type="checkbox"></td> -->
					                    <td colspan="1">
					                    	<span class="">
					                    		<span class=""></span>&nbsp;共&nbsp;</span>{{datas.count}}&nbsp;个
					                    </td>
					                  
					                    <td colspan="3">
		                        			<span class="right"><span class="nextpage" ms-click="goPrev()" ms-class-disabled="datas.pageIndex <= 1">&lt;上一页</span>&nbsp;&nbsp;
		                        			<span class="nextpage" ms-click="goNext()"  ms-class-disabled="datas.pageIndex * 12 +1 > datas.count ">下一页&gt;</span></span>
		                    			</td>
		                			</tr>
		            			 </tfoot>
			                </table>
		              	</div>
	              	</div>
	              <!-- 表格结束 -->
	         	</div>
	         </div>
         
	         <%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
         
     	</div>
     </div>
</body>
</html>