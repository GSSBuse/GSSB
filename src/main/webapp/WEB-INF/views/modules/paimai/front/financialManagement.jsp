<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<script type="text/javascript" src="${ctxStatic }/front/js/finance.js"></script>
<title>财务管理-拍域名</title>
</head>
<body class="v4 layout01">
	 <div id="uiWrapper">
      	<div id="uiContainer">
			<div id="uiHeader">
          		<%@ include file="/WEB-INF/views/include/frontTop.jsp"%>
          	<!-- 蓝条结束 -->
         	</div>
        </div>
         <!-- 头部结束 -->
        <div class="clear"></div>
         
         <!-- content开始 -->
  		<div class="contentbase ms-controller" ms-controller="finance" id="finance">
			<div id="col3">
				<div class="precontent">
					<h1>我的账户</h1>
					<p>管理个人信息、首选项和账户状态</p>
				</div>
				<div class="clear"></div>
			
				<div class="thirdnavi">
					 <ul>
						  <li id = "financeInfoLi" class="selected" ms-click="goToFinanceInfo"><a href="" onclick="return false;">我的资产</a></li>
						  <li id = "freezeRecordLi" ms-click="goToFreezeRecord"><a href="" onclick="return false;">冻结记录</a></li>
						  <li id = "cashFlowLi" ms-click="linkToCashFlow"><a href="" onclick="return false;">资金流水</a></li>
						  <li id = "withdrawalsProgressLi" ms-click="linkToWithdrawalsProgress"><a href="" onclick="return false;">提现进度</a></li>
						  <%-- 
						  <li id = "bankInfoLi" ms-click="linkToBankInfo"><a href="" onclick="return false;">银行信息</a></li>
						  --%>
					 </ul>
				</div>
				<div class="content">
					<div class="contentbox">
								<!-- 我的资产 -->
						<div class="m09 whitebox" id = "financeInfo">
							<div class="sedoform ">
								<h1>我的资产</h1>
								<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)">
										<div class="blockcolsingle1 fLeft">	
											<div class="row">
												<label class="col1">
													<span>账户余额:</span>
												</label>
												<span class="col2 money" name="account">
													{{datas.financeinfo.accountBalance}}
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>冻结金额:</span>
												</label>
												<span class="col2 money" name="freeze">
													{{datas.financeinfo.freezeBalance}}
												</span>
											</div>
											<div class="row">
												<label class="col1">
													<span>可用余额:</span>
												</label>
												<span class="col2 money" name="usable">
													{{datas.financeinfo.accountBalance - datas.financeinfo.freezeBalance}}
												</span>
											</div>
										</div>
										
										<div class="blockcolsingle2 fRight">
											<div class="row">
												<button class="btn-money" ms-click = "recharge">
													充值
												</button>
											</div>									
										</div>
										<div class="blockcolsingle2 fRight">
											<div class="row">
												<button class="btn-money" ms-click = "withdrawals">
													提现
												</button>
											</div>									
										</div>
									</div>
								</div>
						<!-- 冻结记录显示begin -->
						<div class="m09 whitebox hidden" id = "freezeRecord">
							<h1>冻结记录</h1>
							<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)">
							<table class="table blue" id="" style="width: 100%;">
										<caption align="top"></caption>
										<thead class="thead">
											<tr class="tr">
												<td class="th bl aLeft width-60"><h3>时间</h3></td>
												<td class="th bl aLeft width-240"><h3>操作</h3></td>
												<td class="th bl aLeft width-100"><h3>金额（元）</h3></td>
												<td class="th bl aLeft width-200"><h3>备注</h3></td>
											</tr>
										</thead>
										<tbody class="tbody" ms-each-del="datas.freezeinfo">
											<%--冻结记录相应的解冻记录（已解冻的情况） --%>
											<tr class="tr shuffleRow even" ms-class-odd="$index%2 == 0" ms-if="del.operate=='解冻' || (del.operate=='提现' && (del.confirmResult == '完成'||del.confirmResult == '已取消'|| del.confirmResult == '打回'))">
												<td class="td aLeft width-60">
													<span>{{ del.updateDate | date("MM-dd")}}</span>
													<br>
													<span>{{ del.updateDate | date("HH:mm")}}</span>
												</td>
												<td class="td aLeft width-240">
													<span ms-if="del.operate=='解冻'">{{del.dyDomainname.name}}&nbsp;{{del.operate}}</span>
													<span ms-if="del.operate=='提现'">{{del.operate}}解冻（即提现{{del.confirmResult}}）</span>
												</td>
												<td class="td aLeft width-100">
													<span>{{del.operateAmount}}</span>
												</td>
												<td class="td aLeft width-200">
													<span>{{del.remarks}}</span>
												</td>
											</tr>
											<%--冻结的记录--%>
											<tr class="tr shuffleRow even" ms-class-odd="$index%2 == 0">
												<td class="td aLeft width-60">
													<span>{{ del.createDate | date("MM-dd")}}</span>
													<br>
													<span>{{ del.createDate | date("HH:mm")}}</span>
												</td>
												<td class="td aLeft width-240">
													<span ms-if="del.operate=='冻结'">{{del.dyDomainname.name}}&nbsp;{{del.operate}}</span>
													<span ms-if="del.operate=='提现'">{{del.operate}}冻结</span>
													<span ms-if="del.operate=='解冻'">{{del.dyDomainname.name}}&nbsp;冻结</span>
												</td>
												<td class="td aLeft width-100">
													<span>{{del.operateAmount}}</span>
												</td>
												<td class="td aLeft width-200">
													<span>{{del.remarks}}</span>
												</td>
											</tr>
										</tbody>
										<tfoot class="">
											<tr class="odd">
												<td colspan="2" style="text-align:left;">
													<span class="">&nbsp;共&nbsp;<span>{{datas.freezeinfo.size()}}</span>&nbsp;组</span>
												</td>
												<td colspan="2">
				                        			<span class="right" style="margin-right: 10px;"><span class="nextpage" ms-click="goPrev('freezeinfo')" ms-class-disabled="datas.page.freezePage.pageNo <=1">&lt;上一页</span>&nbsp;&nbsp;
				                        			<span class="nextpage" ms-click="goNext('freezeinfo')"  ms-class-disabled="datas.page.freezePage.pageNo*datas.page.freezePage.pageSize +1 > datas.page.freezePage.count ">下一页&gt;</span></span>
				                    			</td>
											</tr>
										</tfoot>
								</table>
						</div>
						<!-- 冻结记录显示end -->
						<!-- 资金流水显示begin -->
						<div class="m09 whitebox hidden" id = "cashFlowRecord">
							<h1>资金流水</h1>
							<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)">
								<table class="table blue" id="" style="width: 100%;">
										<caption align="top"></caption>
										<thead class="thead">
											<tr class="tr">
												<td class="th bl aLeft width-60"><h3>时间</h3></td>
												<td class="th bl aLeft width-100"><h3>操作</h3></td>
												<td class="th bl aLeft width-100"><h3>金额(元)</h3></td>
												<td class="th bl aLeft width-100"><h3>余额(元)</h3></td>
												<td class="th bl aLeft width-240"><h3>域名</h3></td>
											</tr>
										</thead>
										<tbody class="tbody">
											<tr ms-repeat-del="datas.cashflowinfo" class="tr shuffleRow even" ms-class-odd="$index%2 == 0">
												<td class="td aLeft width-60">
													<span>{{ del.operateTime | date("MM-dd")}}</span><br><span>{{ del.operateTime | date("HH:mm")}}</span>
												</td>
												
												<td class="td aLeft width-100">
													<span>{{del.operate}}</span>
												</td>
												<td class="td aLeft width-100">
													<span>{{del.operateAmount}}</span>
												</td>
												<td class="td aLeft width-100">
													<span>{{del.amountBalance}}</span>
												</td>
												<td class="td aLeft width-240">
													<span ms-if="del.dyDomainname">{{del.dyDomainname.name}}</span>
												</td>
											</tr>
										</tbody>
										<tfoot class="">
											<tr class="odd">
												<td colspan="2" style="text-align:left;">
													<span class="">&nbsp;共&nbsp;<span>{{datas.cashflowinfo.size()}}</span>&nbsp;个</span>
												</td>
												<td colspan="3">
				                        			<span class="right" style="margin-right: 10px;"><span class="nextpage" ms-click="goPrev('cashflowinfo')" ms-class-disabled="datas.page.cashFlowPage.pageNo <=1">&lt;上一页</span>&nbsp;&nbsp;
				                        			<span class="nextpage" ms-click="goNext('cashflowinfo')"  ms-class-disabled="datas.page.cashFlowPage.pageNo*datas.page.cashFlowPage.pageSize +1 > datas.page.cashFlowPage.count ">下一页&gt;</span></span>
				                    			</td>
											</tr>
										</tfoot>
								</table>
						</div>
						<!-- 资金流水显示end -->
						<!-- 提现进度显示begin -->
						<div class="m09 whitebox hidden" id = "withdrawalsProgress">
							<h1>提现进度</h1>
							<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)">
							<table class="table blue" id="" style="width: 100%;">
										<caption align="top"></caption>
										<thead class="thead">
											<tr class="tr">
												<td class="th bl aLeft width-60"><h3>时间</h3></td>
												<td class="th bl aLeft width-100"><h3>操作</h3></td>
												<td class="th bl aLeft width-100"><h3>金额（元）</h3></td>
												<td class="th bl aLeft width-100"><h3>状态</h3></td>
												<td class="th bl aLeft width-100"><h3>操作</h3></td>
											</tr>
										</thead>
										<tbody class="tbody">
											<tr ms-repeat-del="datas.withdrawalsinfo" class="tr shuffleRow even" ms-class-odd="$index%2 == 0">
												<td class="td aLeft width-60">
													<span>{{ del.operateTime | date("MM-dd")}}</span>
													<br>
													<span>{{ del.operateTime | date("HH:mm")}}</span>
												</td>
												
												<td class="td aLeft width-100 ">
													<span>{{del.operate}}</span>
												</td>
												<td class="td aLeft width-100">
													<span>{{del.operateAmount}}</span>
												</td>
												<td class="td aLeft width-100">
													<span>{{del.confirmResult}}</span>
												</td>
												<td class="td aLeft width-100">
													<span ms-if="del.confirmResult == '等待'" style="color: #228e05;"><a href="" onclick="return false;" ms-click="cancelWithdrawals($index)">取消</a></span>
												</td>
											</tr>
										</tbody>
										<tfoot class="">
											<tr class="odd">
												<td colspan="2" style="text-align:left;">
													<span class="">&nbsp;共&nbsp;<span>{{datas.withdrawalsinfo.size()}}</span>&nbsp;个</span>
												</td>
												<td colspan="3">
													<span class="right" style="margin-right: 10px;"><span class="nextpage" ms-click="goPrev('withdrawalsinfo')" ms-class-disabled="datas.page.withdrawalsPage.pageNo <=1">&lt;上一页</span>&nbsp;&nbsp;
													<span class="nextpage" ms-click="goNext('withdrawalsinfo')"  ms-class-disabled="datas.page.withdrawalsPage.pageNo*datas.page.withdrawalsPage.pageSize +1 > datas.page.withdrawalsPage.count ">下一页&gt;</span></span>
												</td>
											</tr>
										</tfoot>
								</table>
						</div>
						<!-- 提现进度显示end -->
						<%-- 
						<!-- 银行信息显示begin -->
						<div class="m09 whitebox hidden" id = "bankInfo">
							<h1>银行信息</h1>
							<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity=100,finishopacity=0,style=3)">
							<div class="">	
								<div class="titleDiv">
									<span class="title">持卡人:</span>
									<span style="color: #228e05; text-decoration: none;">
									{{datas.userinfo.name}}
									</span>
								</div>
								<div class="titleDiv">
									<div>
										<span class="title">银行卡号:</span>
										<span ms-if="datas.userinfo.defaultIncomeExpense" style="color: #228e05; text-decoration: none;">{{datas.userinfo.defaultIncomeExpense}}</span>
										<span ms-if="!datas.userinfo.defaultIncomeExpense" style="color: #228e05; text-decoration: none;">无</span>
										<button class="m-l-20" ms-click = "addDefaultIncomeExpense"><span ms-if="!datas.userinfo.defaultIncomeExpense" >添加</span><span ms-if="datas.userinfo.defaultIncomeExpense" >修改</span></button>
									</div>
								</div>
							</div>
						</div>
						<!-- 银行信息end -->
						--%>
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