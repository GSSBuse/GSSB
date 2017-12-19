<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>米乐拍卖-我的交易</title>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/front/css/content_common.css">
<script type="text/javascript" src="${ctxStatic }/front/js/icenter.js"></script>
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
			<div class="contentbase ms-controller" ms-controller="icenter">
				<div id="col3">
					<div class="precontent">
						<h1>我的交易</h1>
						<p>买卖域名的交易记录</p>
					</div>
					<div class="clear"></div>

					<div class="thirdnavi">
						<ul>
							<li class="selected"><a href="">拍卖中</a></li>
							<li><a href="">待处理</a></li>
							<li><a href="">已完成</a></li>
						</ul>
					</div>
					
					<div class="content" id="historyDoingTab" ms-controller="historyTable">
						<div class="contentbox">
							<table class="contenttable" style="margin-top:0px;">
								<caption>拍卖中</caption>
								<thead>
									<tr>
										<th scope="col">域名</th>
										<th scope="col" class="alignr">距离结束</th>
										<th scope="col" class="alignr">当前价</th>
										<th scope="col" class="alignr">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 拍卖中：买 -->
									<tr class="tr-odd" ms-repeat-idata="datas.myTransactionsBuyingList">
										<th scope="row">{{idata.name}}</th>
										<td class="alignr">{{getCountDown(idata.endTime).displayTime}}</td>
										<td class="alignr">￥{{idata.bidAmount | transferCurrentAmount}}</td>
										<td class="alignr"><strong class="error">正在买</strong></td>
									</tr>
									<!-- 拍卖中：卖 -->
									<tr class="tr-even" ms-repeat-idata="datas.myTransactionsSellingList">
										<th scope="row">{{idata.name}}</th>
										<td class="alignr">{{getCountDown(idata.endTime).displayTime}}</td>
										<td class="alignr">
											<span ms-if="!idata.bidAmount">当前无人出价</span>
											<span ms-if="idata.bidAmount">￥{{idata.bidAmount | transferCurrentAmount}}</span>
										</td>
										<td class="alignr"><strong class="error">正在卖</strong></td>
									</tr>
								</tbody>
								<tfoot>
									<tr class="tr-foot tr-even">
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
					
					<div class="content" style="" id="historyTodoTab" ms-controller="historyTable">
						<div class="contentbox">
							<table class="contenttable" style="margin-top:0px;">
								<caption>待处理</caption>
								<thead>
									<tr>
										<th scope="col">域名</th>
										<th scope="col" class="alignr">距离结束</th>
										<th scope="col" class="alignr">当前价</th>
										<th scope="col" class="alignr">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 待处理：买 -->
									<tr class="tr-odd" ms-repeat-idata="datas.myTransactionsBoughtList">
										<th scope="row">{{idata.name}}</th>
										<td class="alignr">
											<span>{{getCountDown(idata.endTime).displayTime}}</span>
										</td>
										<td class="alignr">￥{{idata.bidAmount | transferCurrentAmount}}</td>
										<td class="alignr">
											<strong class="error" ms-if="idata.status=='11'">等待付款</strong>
											<strong class="error" ms-if="idata.status=='12'">等待对方操作</strong>
											<strong class="error" ms-if="idata.status=='13'">确认收到</strong>
											<strong class="error" ms-if="idata.status=='14'">等待系统确认</strong>
											<strong class="error" ms-if="idata.status=='15'">已成交</strong>
										</td>
									</tr>
									<!-- 待处理：卖 -->
									<tr class="tr-even" ms-repeat-idata="datas.myTransactionsSoldList">
										<th scope="row">{{idata.name}}</th>
										<td class="alignr">
											<span>{{getCountDown(idata.endTime).displayTime}}</span>
										</td>
										<td class="alignr">￥{{idata.bidAmount | transferCurrentAmount}}</td>
										<td class="alignr">
											<strong class="error" ms-if="idata.status=='11'">等待对方操作</strong>
											<strong class="error" ms-if="idata.status=='12'">确认转移</strong>
											<strong class="error" ms-if="idata.status=='13'">等待对方操作</strong>
											<strong class="error" ms-if="idata.status=='14'">等待系统确认</strong>
											<strong class="error" ms-if="idata.status=='15'">已成交</strong>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr class="tr-foot tr-even">
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
					
					<div class="content" style="" id="historyDoneTab" ms-controller="historyTable">
						<div class="contentbox">
							<table class="contenttable" style="margin-top:0px;">
								<caption>已完成</caption>
								<thead>
									<tr>
										<th scope="col">域名</th>
										<th scope="col" class="alignr">距离结束</th>
										<th scope="col" class="alignr">成交价</th>
										<th scope="col" class="alignr">操作</th>
									</tr>
								</thead>
								<tbody>
									<!-- 已完成：买 -->
									<tr class="tr-odd" ms-repeat-idata="datas.myTransactionsDoneBoughtList">
										<th scope="row">{{idata.name}}</th>
										<td class="alignr">{{getCountDown(idata.endTime).displayTime}}</td>
										<td class="alignr">￥{{idata.bidAmount | transferCurrentAmount}}</td>
										<td class="alignr">
											<strong class="error" ms-if="idata.status=='15'">已成交</strong>
										</td>
									</tr>
									<!-- 已完成：卖 -->
									<tr class="tr-even" ms-repeat-idata="datas.myTransactionsDoneSoldList">
										<th scope="row">{{idata.name}}</th>
										<td class="alignr">{{getCountDown(idata.endTime).displayTime}}</td>
										<td class="alignr">￥{{idata.bidAmount | transferCurrentAmount}}</td>
										<td class="alignr">
											<strong class="error" ms-if="idata.status=='15'">已成交</strong>
										</td>
									</tr>
									<!-- 已完成：买~异常状态 -->
									<tr class="tr-odd" ms-repeat-idata="datas.myTransactionsBoughtExceptionList">
										<th scope="row">{{idata.name}}</th>
										<td class="alignr">{{getCountDown(idata.endTime).displayTime}}</td>
										<td class="alignr">
											<span ms-if="!idata.bidAmount">当前无人出价</span>
											<span ms-if="idata.bidAmount">￥{{idata.bidAmount | transferCurrentAmount}}</span>
										</td>
										<td class="alignr">
											<strong class="error" ms-if="idata.status=='21'">您已违约</strong>
											<strong class="error" ms-if="idata.status=='22'">卖家违约</strong>
											<strong class="error" ms-if="idata.status=='23'">流拍</strong>
										</td>
									</tr>
									<!-- 已完成：卖~异常状态 -->
									<tr class="tr-even" ms-repeat-idata="datas.myTransactionsSoldExceptionList">
										<th scope="row">{{idata.name}}</th>
										<td class="alignr">{{getCountDown(idata.endTime).displayTime}}</td>
										<td class="alignr">
											<span ms-if="!idata.bidAmount">当前无人出价</span>
											<span ms-if="idata.bidAmount">￥{{idata.bidAmount | transferCurrentAmount}}</span>
										</td>
										<td class="alignr">
											<strong class="error" ms-if="idata.status=='21'">买家违约</strong>
											<strong class="error" ms-if="idata.status=='22'">您已违约</strong>
											<strong class="error" ms-if="idata.status=='23'">流拍</strong>
										</td>
									</tr>
								</tbody>
								<tfoot>
									<tr class="tr-foot tr-even">
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
					
				</div>
				<%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
			</div>

			<!-- content结束 -->

		</div>
	</div>
	<!-- 容器结束 -->
	<script type="text/javascript">
	require(["utils"], function(utils) {
		var vm = avalon.define({
			$id : "historyTable",
			datas : {
				myTransactionsBuyingList          : [],
				myTransactionsSellingList         : [],
				myTransactionsDoingSize           : 0,
				myTransactionsBoughtList          : [],
				myTransactionsSoldList            : [],
				myTransactionsDoneBoughtList      : [],
				myTransactionsDoneSoldList        : [],
				myTransactionsBoughtExceptionList : [],
				myTransactionsSoldExceptionList   : [],
				//倒计时：当前时间
				newDate:new Date().getTime()
			},
			// 请求数据
			refreshData: function () {
				$.get("${ctx}/domainname/refreshMyTransactions.json", 
					{},
					function(res){
						vm.datas.myTransactionsBuyingList  = res.data.myTransactionsBuyingList;
						vm.datas.myTransactionsSellingList = res.data.myTransactionsSellingList;
						vm.datas.myTransactionsDoingSize   = res.data.myTransactionsDoingSize;
						vm.datas.myTransactionsBoughtList  = res.data.myTransactionsBoughtList;
						vm.datas.myTransactionsSoldList  = res.data.myTransactionsSoldList;
						vm.datas.myTransactionsDoneBoughtList  = res.data.myTransactionsDoneBoughtList;
						vm.datas.myTransactionsDoneSoldList  = res.data.myTransactionsDoneSoldList;
						vm.datas.myTransactionsBoughtExceptionList  = res.data.myTransactionsBoughtExceptionList;
						vm.datas.myTransactionsSoldExceptionList  = res.data.myTransactionsSoldExceptionList;
					}
				);
			},
			// 计算倒计时
			getCountDown : function(endTime) {
				var restTime = new Date(endTime).getTime() - vm.datas.newDate;
				if (restTime <= 0) {
					time = {
							hours : '00',
							mins : '00',
							secs : '00'
					}
					return {
						time : time,
						displayTime:"00时00秒00分"
					}
				}
				return utils.millisecondToDate(restTime);
			}
		});
		vm.refreshData();
	});
	</script>
</body>
</html>