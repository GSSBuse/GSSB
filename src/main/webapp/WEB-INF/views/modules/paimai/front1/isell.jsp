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

	var sellerDeposit = ${sellerDeposit};
	// -->
</script>
<script type="text/javascript" src="${ctxStatic }/front/js/isell.js"></script>
</head>
<body class="v4 layout01 ">
	 <div id="uiWrapper">
      	<div id="uiContainer">
        
          	<div id="uiHeader">
          		<%@ include file="/WEB-INF/views/include/frontTop.jsp"%>
	          	<!-- 蓝条结束 -->
          	</div>
          	
         <!-- 头部结束 -->
         
		<div id="uiContent" class="ms-controller" ms-controller="isell">
         	<div id="uiContentRSS" class="uiContent">
         		<div class="buy_table-wrapper">
	                <div class="table-container">
		           	 	<table class="table blue" id="">
		           	 		<caption align="top"></caption>   	
	                        <thead class="thead">
	                            <tr class="tr">
	                                <td class="th bl aLeft"><a href=""><h3>序号</h3></a></td>
	                                <td class="th bl aLeft"><a href=""><h3>域名</h3></a></td>
	                                <!-- <td class="th bl aLeft"><a href=""><h3>域名描述</h3></a> -->
	                                <td class="th bl aLeft"><a href=""><h3>状态</h3></a></td>
	                                <td class="th bl aLeft"><a href=""><h3>保留价</h3></a></td>
	                                <td class="th bl aLeft"><a href=""><h3>当前价格</h3></a></td>
	                                <td class="th bl aLeft"><a href=""><h3>操作</h3></a></td>
	                            </tr>
	                        </thead>
	                        <tbody class="tbody">
		                        <tr ms-repeat-el="datas.domainList" class="tr shuffleRow even" ms-class-odd="$index%2 == 0">
		                       	    <td class="td aCenter"><a class="url" title="">{{$index+1}}</a></td>
			                        <td class="td even"><a class="url" title="trade.credit">{{el.name}}</a></td>
			                        <!-- <td class="td even"><a class="url" href="" title="">域名描述域名描述域名描述域名描述</a></td> -->
			                      	<td class="td aLeft ">
			                      		<div  class="ui-isell-check" ms-if-loop="el.status=='01'">审核中</div>
										<div  class="ui-isell-check" ms-if-loop="el.status=='02'">审核失败</div>
										<div  class="ui-isell-check" ms-if-loop="el.status=='03'">审核通过</div>
										<div  class="ui-isell-check" ms-if-loop="el.status=='00' && (datas.financeinfo.accountBalance - datas.financeinfo.freezeBalance) >= (datas.sellerDeposit + el.bonusShareTotal)">等待提交</div>
										<div  class="ui-isell-check" ms-if-loop="el.status=='00' && (datas.financeinfo.accountBalance - datas.financeinfo.freezeBalance) &lt; (datas.sellerDeposit + el.bonusShareTotal)">等待充值</div>
									</td>
			                      	<td class="td aLeft ">
			                      		<a class="price icon" href="" title="" onclick="return false">{{el.reservePrice || 0}}</a>
			                      	</td>
			                      	<td class="td aLeft ">
			                      		<a class="price icon" href="" title="" onclick="return false">{{el.appraisePrice || 0}} </a>
			                      	</td>
			                      	<td class="td aLeft ">
			                      		<span>
										<a ms-attr-href="'${ctx }/isell/edit.html?id='+el.id" class="url price " ms-if-loop="el.status=='02'">编辑</a>
			                      		<a ms-click="deleteDomain($event, el.id, el.name)" href="" class="url price " ms-if-loop="el.status=='02'">删除</a>
			                      		<a ms-click="submitDomain($index)" href="" onclick="return false;"class="url price" ms-if-loop="el.status=='00' && (datas.financeinfo.accountBalance - datas.financeinfo.freezeBalance) >= (datas.sellerDeposit + el.bonusShareTotal)">提交</a>
										<a ms-click="recharge($index)" href="" onclick="return false;" class="url price" ms-if-loop="el.status=='00' && (datas.financeinfo.accountBalance - datas.financeinfo.freezeBalance) &lt; (datas.sellerDeposit + el.bonusShareTotal)">充值</a>
								<%--<a ms-click="addRedPack($event, el.id, el.name, el.bonusSecond)" href="" class="url price " ms-if-loop="el.status=='03'">追加红包</a>--%>
										</span>
			                      	</td>
		                        </tr>
		                        
		                    </tbody>
		                    
		                    <tfoot class="">
	                			<tr class="odd">
				                    <!-- <td class="first"><input class="checkall" type="checkbox"></td> -->
				                    <td colspan="6">
				                    	<span class="">&nbsp;共&nbsp;<span>{{datas.domainList.size()}}</span>&nbsp;个</span>
				                    </td>
	                			</tr>
	            			 </tfoot>
		               	</table>
		               	
		               	
		           	 	<span class="fRight"><a href="${ctx }/isell/add.html" class="button green L"><span>添加</span></a></span>
		           	 	
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