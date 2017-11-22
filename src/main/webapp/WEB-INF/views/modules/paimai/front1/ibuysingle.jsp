<%@ page contentType="text/html;charset=UTF-8"
	import="com.thinkgem.jeesite.modules.wx.utils.DySysUtils,
	com.thinkgem.jeesite.modules.sys.entity.dy.DyClient,
	com.thinkgem.jeesite.modules.wx.entity.domainname.BidClient,
	com.thinkgem.jeesite.modules.wx.entity.domainname.PageDomainEntity"%>
<!DOCTYPE html>
<html>
<head>
<title>米乐拍卖-首页</title>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/include/frontHead.jsp"%>
<style type="text/css">
body div.m09 h1 {
	font-size: 18px;
}
body .whitebox,
body div.precontent p {
	font-size: 15px;
	font-family: 'Droid Sans', Helvetica, sans-serif !important;
}
div.sedoform .blockcolsingle2 {
   width: 150px;
}
div.sedoform .blockcolsingle1 div.row {
    width: 350px;
}
div.sedoform .blockcolsingle1 .col1 {
    width: 130px;
}
div.sedoform .blockcolsingle1 .col2 {
    width: 200px;
    height: 31px;
}
div.sedoform .bidAmount {
	width:80px;
	font-size:15px;
}
.historyTable {
    background-color: rgb(253, 253, 253);
    color: rgb(85, 85, 85);
    font-size: 12px;
    line-height: 20px;
    width: 90%;
    margin-left: 15px;
    margin-top: 10px;
}
.historyTable th,
.historyTable td {
	border-bottom-color: rgb(221, 221, 221);
    border-bottom-style: solid;
    border-bottom-width: 1px;
    border-left-color: rgba(211,211,211,1);
    border-left-style: solid;
    border-left-width: 1px;
    border-right-color: rgb(221, 221, 221);
    border-right-style: solid;
    border-right-width: 1px;
    border-top-color: rgb(221, 221, 221);
    border-top-style: solid;
    border-top-width: 1px;
}
.historyTable th {
	font-weight: bold;
}
</style>
<script type="text/javascript" src="${ctxStatic }/front/js/ibuysingle.js"></script>

<%
	DyClient currentClient = DySysUtils.getCurrentDyClient();
	
	//是否已登录
	boolean hasLogin = false;
	String currentClientId = null;
	if (currentClient != null && currentClient.getId() != null) {
		hasLogin = true;
		currentClientId = currentClient.getId();
	}
	
	//是否最高出价者
	boolean isTopBidClient = false;
	PageDomainEntity domainnameDetail = (PageDomainEntity) request.getAttribute("domainnameDetail");
	if (domainnameDetail != null && domainnameDetail.getTopBidClientId() != null && domainnameDetail.getProxyAmount() != null && domainnameDetail.getProxyIncrement() != null) {
		if (domainnameDetail.getTopBidClientId().equals(currentClientId)) {
			isTopBidClient = true;
		}
	}
%>
<script type="text/javascript">
	var hasLogin = <%=hasLogin%>;
	var currentClientId = '<%=currentClientId%>';
</script>
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

			<div class="contentbase">
				<div id="col3">
					<div class="precontent" style="height:40px;">
						<div class="keyvisual">
						</div>
						<h1>域名竞价</h1>
					</div>

					<div class="content">
						<div class="contentbox">
							<div class="m09 whitebox">
								<div class="sedoform">
									<h1>${domainnameDetail.name}</h1>
									<hr width=100% size=1 color=#99BBFF style="FILTER: alpha(opacity = 100, finishopacity = 0, style = 3)">
									
									<div class="blockcolsingle1 fLeft" style="width: 100%;">
										<div class="row">
											<label class="col1"><span>域名描述:</span></label>
											<span class="col2" style="height: inherit;white-space: normal; word-wrap: break-word; word-break: break-all;">${domainnameDetail.description}</span>
										</div>
										
										<div class="row">
											<label class="col1"><span>保证金:</span></label>
											<span class="col2">${domainnameDetail.deposit}元</span>
										</div>
										
		                        		<div class="row">
											<label class="col1"><span>截拍时间:</span></label>
											<span class="col2">
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${domainnameDetail.endTime}"  type="both" />
											</span>
										</div>
										<div class="row">
											<label class="col1"><span>距离结束:</span></label>
											<span class="col2" id="restTime"></span>
										</div>
										
										<div class="row">
											<label class="col1"><span>当前最高价:</span></label>
											<span class="col2">
												<span id="currAmount">${domainnameDetail.currAmount}</span>元
											</span>
										</div>
		                        		<div class="row">
											<label class="col1"><span>加价幅度:</span></label>
											<span class="col2">
												<span id="increment">${domainnameDetail.increment}</span>元
											</span>
										</div>
										
										<div class="row">
											<label class="col1"><span>代理竞价金额:</span></label>
											<span class="col2">
												<span id="proxyAmount"><%=isTopBidClient ? domainnameDetail.getProxyAmount() : "0" %></span>元
											</span>
										</div>
										<div class="row">
											<label class="col1"><span>代理竞价加价幅度:</span></label>
											<span class="col2">
												<span id="proxyIncrement"><%=isTopBidClient ? domainnameDetail.getProxyIncrement() : "0" %></span>元
											</span>
										</div>
										 <c:if test="${currentClient.id != domainnameDetail.clientId}">
			                        		<div class="row">
												<label class="col1"><span>现在出价:</span></label>
												<span class="col2">
												<% if (isTopBidClient) {//是最高出价者 %>
													<input type="text" class="input bidAmount" id="bidAmount" value="${domainnameDetail.proxyAmount + domainnameDetail.proxyIncrement}" />元
												<% } else { %>
													<input type="text" class="input bidAmount" id="bidAmount" value="${domainnameDetail.currAmount + domainnameDetail.increment}" />元
												<% } %>
												</span>
												
												<div class="row">
													<div class="fCenter">
														<input class="submitbutton sb160" type="submit" name="submit" onclick="doBidAmount();" value="出价">
													</div>
												</div>
											</div>
										</c:if>
										<c:if test="${currentClient.id == domainnameDetail.clientId}">
											<div class="row">
												<label class="col1"></label>
												<span class="col2">自己出售的域名</span>
											</div>
										</c:if>
									</div>
									
									<div class="blockcolsingle2 fRight">
										<c:if test="${not empty domainnameDetail && not empty domainnameDetail.image1}">
											<img src="${domainnameDetail.image1}" style="margin-bottom:5px;" />
										</c:if>
										<c:if test="${not empty domainnameDetail && not empty domainnameDetail.image2}">
											<img src="${domainnameDetail.image2}" style="margin-bottom:5px;" />
										</c:if>
										<c:if test="${not empty domainnameDetail && not empty domainnameDetail.image3}">
											<img src="${domainnameDetail.image3}" />
										</c:if>
									</div>
								</div>
								<div class="clear"></div>
								<a href="#" onclick="$('#bidHistoryTable').toggle();" style="margin-left:10px;">查看竞价记录</a>
								<div class="sedoform">
									<table id="bidHistoryTable" class="historyTable" ms-controller="bidHistoryTable" style="display:none;">
										<thead>
											<tr>
												<th>会员昵称</th>
												<th>出价金额</th>
												<th>出价时间</th>
											</tr>
										</thead>
										<tbody>
											<tr ms-repeat-idata="datas.bidClientList">
												<td>{{idata.nickname}}</td>
												<td>{{idata.bidAmount}}</td>
												<td>{{idata.bidTime}}</td>
											</tr>
										</tbody>
										<tfoot class="">
				                			<tr class="odd">
							                    <!-- <td class="first"><input class="checkall" type="checkbox"></td> -->
							                    <td colspan="1">
							                    	<span class="">
							                    		<span class=""></span>&nbsp;共&nbsp;</span>{{datas.bidCount}}&nbsp;个
							                    </td>
							                  
							                    <td colspan="3">
				                        			<span class="right"><span class="nextpage" ms-click="goPrev()" ms-class-disabled="datas.pageIndex <= 1">&lt;上一页</span>&nbsp;&nbsp;
				                        			<span class="nextpage" ms-click="goNext()"  ms-class-disabled="datas.pageIndex * 10 +1 > datas.bidCount ">下一页&gt;</span></span>
				                    			</td>
				                			</tr>
				            			 </tfoot>
									</table>
								</div>
							</div>
							
						</div>
					</div>
				</div>

			</div>
		</div>
		<!-- 容器结束 -->
	</div>
	
	<%@ include file="/WEB-INF/views/include/frontFooter.jsp"%>
	
	<script type="text/javascript">
	$(document).ready(function(){
		var endTime = new Date('${domainnameDetail.endTime}');
		window.sysServerTime = (new Date()).getTime();
		
		require(["utils"], function(utils) {
			recomputeRestTimeTask(utils, endTime);
			setInterval(function(){
				window.sysServerTime = window.sysServerTime + 1000;
				utils.jscache.clear();
				recomputeRestTimeTask(utils, endTime);
			}, 1000);
		});
		
		setTimeout(refreshAmountInfoTask, 1000);
	});
	
	/**
	 * 计算并显示距离结束时间定时任务
	 */
	function recomputeRestTimeTask(utils, endTime) {
		var restTime = endTime.getTime() - window.sysServerTime;
		
		if (utils.jscache.getV(restTime)) {
			return utils.jscache.getV(restTime);
		}
		//var restTime = new Date(endTime.replace(/-/g, "/")) - vm.datas.tmp.newDate;
		if (restTime <= 0) {
			return {
				disabled : true,
				displayTime : '00时00分00秒'
			}
		}
		var data = {}, h = m = s = "";
		data = utils.millisecondToDate(restTime);
		var displayTime = data.displayTime;
		
		utils.jscache.setV(restTime, {
			disabled : false,
			displayTime : displayTime
		});
		
		$("#restTime").text(displayTime);
	}
	
	/**
	 * 进行出价
	 */
	function doBidAmount() {
		$.ajax({
			type: "POST",
			url : "${ctx}/domainname/bid",
			data : {
				"domainId" : "${domainnameDetail.id}",
				"bidAmount" : $("#bidAmount").val()
			},
			dataType : "json",
			success : function(res) {
				if (res.type == 'success') {
					$.jBox.info(res.msg);
					if (res.data.pDomain) {
						reshowAmountInfo(res.data.pDomain);
						// 出价成功，刷新竞价记录
						vm.refresh();
					}
				} else if (res.type == 'warn') {
					$.jBox.error(res.msg);
					if (res.data.pDomain) {
						reshowAmountInfo(res.data.pDomain);						
					}
				} else if (res.type == 'error') {
					$.jBox.error(res.msg);
					if (res.data.pDomain) {
						reshowAmountInfo(res.data.pDomain);						
					}
				}
			}
		});
	}
	
	/**
	 * 刷新价格显示内容
	 */
	function reshowAmountInfo(pDomain) {
		$("#currAmount").html(pDomain.currAmount);
		$("#increment").html(pDomain.increment);
		if (hasLogin && currentClientId && currentClientId == pDomain.topBidClientId && pDomain.proxyAmount && pDomain.proxyIncrement) {
			$("#proxyAmount").html(pDomain.proxyAmount);
			$("#proxyIncrement").html(pDomain.proxyIncrement);
			$("#bidAmount").val(new Number(pDomain.proxyAmount) + new Number(pDomain.proxyIncrement));
		} else {
			$("#proxyAmount").html(0);
			$("#proxyIncrement").html(0);
			$("#bidAmount").val(new Number(pDomain.currAmount) + new Number(pDomain.increment));
		}
	}
	
	/**
	 * 刷新价格显示内容定时任务
	 */
	function refreshAmountInfoTask() {
		// 上次数据的更新时间
		if (!window.timeStamp) {
			window.timeStamp = "0";
		}
		
		var domainIdList = [];
		domainIdList.push("${domainnameDetail.id}");
		$.post(
			"${ctx}/domainname/polling/ibuyData.json",
			{
				domainIdList : domainIdList,
				timeStamp : window.timeStamp
			},
			function(res) {
				if (res.type == "success") {
					//服务器当前时间
					if(window.timeStamp != res.data.timeStamp){//有新数据
						if (res.data.sysServerTime) {
							window.sysServerTime = res.data.sysServerTime;
						}
						window.timeStamp = res.data.timeStamp;
						if (res.data.ibuyData && res.data.ibuyData.length > 0) {
							reshowAmountInfo(res.data.ibuyData[0]);
						}
					}
				}
				
				setTimeout(refreshAmountInfoTask, 1000);
			}
		);
	}
	
	var vm = avalon.define({
		$id : "bidHistoryTable",
		datas : {
			bidClientList : [],
			bidCount : 0,
			pageIndex : 1
		},
		goPrev: function () {
			if ($(this).is(".disabled")) {
				return;
			}
			vm.datas.pageIndex = vm.datas.pageIndex - 1;
			vm.refresh();
		},
		goNext: function () {
			if ($(this).is(".disabled")) {
				return;
			}
			vm.datas.pageIndex = vm.datas.pageIndex + 1;
			vm.refresh();
		},
		refresh: function () {
			// 请求数据
			$.get("${ctx}/domainname/bidData.json", 
				{
					domainId : "${domainnameDetail.id}",
					pageIndex : vm.datas.pageIndex
				},
				function(res){
					vm.datas.bidClientList = res.data.bidClientList;
					vm.datas.bidCount = res.data.bidCount;
				}
			);
		},
		refreshTask: function () {
			vm.refresh();
			setTimeout(vm.refreshTask, 30000);
		}
	});
	vm.refreshTask();
	</script>
</body>
</html>