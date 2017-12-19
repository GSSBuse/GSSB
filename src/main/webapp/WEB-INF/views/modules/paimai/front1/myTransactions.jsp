<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<script type="text/javascript" src="${ctxStatic }/front/js/myTransactions.js"></script>
<title>米乐拍卖-米乐拍卖</title>
</head>
<body class="v4 layout01">
	 <div id="uiWrapper">
      	<div id="uiContainer">
			<div id="uiHeader">
          		<%@ include file="/WEB-INF/views/include/frontTopMenu.jsp"%>
          	<!-- 蓝条结束 -->
         	</div>
        </div>
         <!-- 头部结束 -->
        <div class="clear"></div>
         
         <!-- content开始 -->
  		<div class="contentbase ms-controller" ms-controller="myTransactions" id="myTransactions">
			<div id="col3">
				<div class="precontent">
					<h1>我的交易</h1>
					<p>参与的拍卖中、待处理和已完成的域名</p>
				</div>
				<div class="clear"></div>
			
				<div class="thirdnavi">
					<ul>
						<li id = "myTransactionsDoingLi" class="selected" ms-click="goTomyTransactionsDoing('buy')"><a href="" onclick="return false;">拍卖中</a></li>
						<li id = "myTransactionsWaitDealLi" ms-click="goTomyTransactionsWaitDeal('buy')"><a href="" onclick="return false;">待处理</a></li>
						<li id = "myTransactionsDoneLi" ms-click="goTomyTransactionsDone('buy')"><a href="" onclick="return false;">已完成</a></li>
					</ul>
				</div>
				<div class="content">
					<div class="contentbox">
						<!-- 拍卖中交易begin -->
						<div class="m09 whitebox ps-a" id = "myTransactionsDoing">
							<h1>参与正在拍卖的交易信息</h1>
							<select id="isBuyFlagForDoing" class="select-position"> 
								<option value="buy" selected = "selected">我出价的域名</option> 
								<option value="sell" >我出售的域名</option>
							</select>
							<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)">
							<table class="table blue" id="" style="width: 100%;">
										<caption align="top"></caption>
										<thead class="thead">
											<tr class="tr">
												<td class="th bl aLeft width-240"><h3>域名名称</h3></td>
												<td class="th bl aLeft width-120"><h3>当前价（元）</h3></td>
												<td class="th bl aLeft width-140"><h3>剩余时间</h3></td>
												<td class="th bl aLeft width-100"><h3>新消息</h3></td>
											</tr>
										</thead>
										<tbody class="tbody">
											<tr ms-repeat-del="datas.myTransactionsDoingInfo" class="tr shuffleRow even" ms-class-odd="$index%2 == 0">
												<td class="td aLeft width-240">
													<span ms-click="goToSingleDomainname($index,'myTransactionsDoing')"><a href="" onclick="return false;">{{del.domainname}}</a></span>
												</td>
												
												<td class="td aLeft width-120">
													<span>{{del.currentPrice}}</span>
												</td>
												<td class="td aLeft width-140">
													<span>{{getCountDown(del.endTime).displayTime}}</span>
												</td>
												<td class="td aLeft width-100">
													<span></span>
												</td>
											</tr>
										</tbody>
										<tfoot class="">
											<tr class="odd">
												<td colspan="2" style="text-align : left;">
													<span class="">&nbsp;共&nbsp;<span>{{datas.myTransactionsDoingInfo.size()}}</span>&nbsp;个</span>
												</td>
												<td colspan="2">
													<span class="right" style="margin-right: 10px;"><span class="nextpage" ms-click="goPrev('myTransactionsDoing')" ms-class-disabled="datas.page.myTransactionsDoingPage.pageNo <=1">&lt;上一页</span>&nbsp;&nbsp;
													<span class="nextpage" ms-click="goNext('myTransactionsDoing')"  ms-class-disabled="datas.page.myTransactionsDoingPage.pageNo*datas.page.myTransactionsDoingPage.pageSize +1 > datas.page.myTransactionsDoingPage.count ">下一页&gt;</span></span>
												</td>
											</tr>
										</tfoot>
								</table>
						</div>
						<!-- 拍卖中交易end -->
						<!--  待处理交易begin -->
						<div class="m09 whitebox hidden ps-a" id = "myTransactionsWaitDeal">
							<h1>需要进行操作的交易信息</h1>
							<select id="isBuyFlagForWaitDeal" class="select-position"> 
								<option value="buy" selected = "selected">我买的域名</option> 
								<option value="sell" >我卖的域名</option>
							</select>
							<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)">
								<table class="table blue" id="" style="width: 100%;">
										<caption align="top"></caption>
										<thead class="thead">
											<tr class="tr">
												<td class="th bl aLeft width-240"><h3>域名名称</h3></td>
												<td class="th bl aLeft width-120"><h3>成交价（元）</h3></td>
												<td class="th bl aLeft width-100"><h3>操作</h3></td>
												<td class="th bl aLeft width-140"><h3>操作限时</h3></td>
											</tr>
										</thead>
										<tbody class="tbody">
											<tr ms-repeat-del="datas.myTransactionsWaitDealInfo" class="tr shuffleRow even" ms-class-odd="$index%2 == 0">
												<td class="td aLeft width-240">
													<span ms-click="goToSingleDomainname($index,'myTransactionsWaitDeal')"><a href="" onclick="return false;">{{del.domainname}}</a></span>
												</td>
												
												<td class="td aLeft width-100">
													<span>{{del.dealPrice}}</span>
												</td>
												<td class="td aLeft width-100">
													<span class="span-c" ms-if="del.domainnameStatus == '11' && datas.isBuyFlag.isBuyFlagForWaitDeal == 'buy'" ms-click="openPaymentDialog($index)"><a href="" onclick="return false;">付款</a></span>
													<span ms-if="del.domainnameStatus == '12' && datas.isBuyFlag.isBuyFlagForWaitDeal == 'buy'">等待对方转移</span>
													<span class="span-c" ms-if="del.domainnameStatus == '13' && datas.isBuyFlag.isBuyFlagForWaitDeal == 'buy'" ms-click="receiveDomamainname($index)"><a href="" onclick="return false;">确认收到</a></span>
													<span ms-if="del.domainnameStatus == '14' && datas.isBuyFlag.isBuyFlagForWaitDeal == 'buy'">等待系统确认</span>
													<span ms-if="del.domainnameStatus == '11' && datas.isBuyFlag.isBuyFlagForWaitDeal == 'sell'">等待对方付款</span>
													<span class="span-c" ms-if="del.domainnameStatus == '12' && datas.isBuyFlag.isBuyFlagForWaitDeal == 'sell'" ms-click="confirmTransferDomainname($index)"><a href="" onclick="return false;">转移域名</a></span>
													<span ms-if="del.domainnameStatus == '13' && datas.isBuyFlag.isBuyFlagForWaitDeal == 'sell'">等待对方确认</span>
													<span ms-if="del.domainnameStatus == '14' && datas.isBuyFlag.isBuyFlagForWaitDeal == 'sell'">等待系统确认</span>
												</td>
												<td class="td aLeft width-160">
													<span>{{getCountDown(del.waitTime).displayTime}}</span>
												</td>
											</tr>
										</tbody>
										<tfoot class="">
											<tr class="odd">
												<td colspan="2" style="text-align:left;">
													<span class="">&nbsp;共&nbsp;<span>{{datas.myTransactionsWaitDealInfo.size()}}</span>&nbsp;个</span>
												</td>
												<td colspan="2">
													<span class="right" style="margin-right: 10px;"><span class="nextpage" ms-click="goPrev('myTransactionsWaitDeal')" ms-class-disabled="datas.page.myTransactionsWaitDealPage.pageNo <=1">&lt;上一页</span>&nbsp;&nbsp;
													<span class="nextpage" ms-click="goNext('myTransactionsWaitDeal')"  ms-class-disabled="datas.page.myTransactionsWaitDealPage.pageNo*datas.page.myTransactionsWaitDealPage.pageSize +1 > datas.page.myTransactionsWaitDealPage.count ">下一页&gt;</span></span>
												</td>
											</tr>
										</tfoot>
								</table>
						</div>
						<!-- 待处理交易end -->
						<!-- 已完成交易begin -->
						<div class="m09 whitebox hidden ps-a" id = "myTransactionsDone">
							<h1>拍卖已经结束的交易信息</h1>
							<select id="isBuyFlagForDone" class="select-position"> 
								<option value="buy" selected = "selected">我买的域名</option> 
								<option value="sell" >我卖的域名</option>
							</select>
							<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)">
							<table class="table blue" id="" style="width: 100%;">
										<caption align="top"></caption>
										<thead class="thead">
											<tr class="tr">
												<td class="th bl aLeft width-240"><h3>域名名称</h3></td>
												<td class="th bl aLeft width-200""><h3>成交价（元）</h3></td>
												<td class="th bl aLeft width-100"><h3>交易状态</h3></td>
											</tr>
										</thead>
										<tbody class="tbody">
											<tr ms-repeat-del="datas.myTransactionsDoneInfo" class="tr shuffleRow even" ms-class-odd="$index%2 == 0">
												<td class="td aLeft">
													<span ms-click="goToSingleDomainname($index,'myTransactionsDone')"><a href="" onclick="return false;">{{ del.domainname}}</a></span>
												</td>
												<td class="td aLeft ">
													<span>{{del.dealPrice}}</span>
												</td>
												<td class="td aLeft ">
													<span ms-if="del.domainnameStatus == '21' && datas.isBuyFlag.isBuyFlagForDone == 'buy'">您已违约</span>
													<span ms-if="del.domainnameStatus == '22' && datas.isBuyFlag.isBuyFlagForDone == 'buy'">卖家违约</span>
													<span ms-if="del.domainnameStatus == '23' && datas.isBuyFlag.isBuyFlagForDone == 'buy'">流拍</span>
													<span ms-if="del.domainnameStatus == '21' && datas.isBuyFlag.isBuyFlagForDone == 'sell'">买家违约</span>
													<span ms-if="del.domainnameStatus == '22' && datas.isBuyFlag.isBuyFlagForDone == 'sell'">您已违约</span>
													<span ms-if="del.domainnameStatus == '23' && datas.isBuyFlag.isBuyFlagForDone == 'sell'">流拍</span>
												</td>
											</tr>
										</tbody>
										<tfoot class="">
											<tr class="odd">
												<td colspan="1" style="text-align : left;">
													<span class="">&nbsp;共&nbsp;<span>{{datas.myTransactionsDoneInfo.size()}}</span>&nbsp;个</span>
												</td>
												<td colspan="2">
													<span class="right" style="margin-right: 10px;"><span class="nextpage" ms-click="goPrev('myTransactionsDone')" ms-class-disabled="datas.page.myTransactionsDonePage.pageNo <=1">&lt;上一页</span>&nbsp;&nbsp;
													<span class="nextpage" ms-click="goNext('myTransactionsDone')"  ms-class-disabled="datas.page.myTransactionsDonePage.pageNo*datas.page.myTransactionsDonePage.pageSize +1 > datas.page.myTransactionsDonePage.count ">下一页&gt;</span></span>
												</td>
											</tr>
										</tfoot>
								</table>
						</div>
						<!-- 已完成交易end -->
					</div>
				</div>
			</div>
      <div class="clear"></div>
     <%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
    </div>
    <!-- content结束 -->
   </div>
   <!-- 容器结束 -->
</body>
</html>