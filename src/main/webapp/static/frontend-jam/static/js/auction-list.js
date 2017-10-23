
define(function() {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "auction-list",
		datas : {
			newDate:new Date().getTime(),
			domainList : [],
			pageIndex : 1,
			tmp : {
				domainId : "",
				increment : 0,
				currAmount : 0,
				index : 0
			}
		},
		switchFollowStatus : function(index){
			$.post("follow",
					{
						"domainId" : vm.datas.domainList[index].id
					},
					function (res) {
						if (res.type == "success") {
							vm.datas.domainList[index].attentioned = !vm.datas.domainList[index].attentioned;
//							vm.datas.domainList[index].attentionText = vm.datas.domainList[index].attentioned ? "取消":"关注";
							// vm.datas.domainList[index].attentionList = [];
							vm.datas.domainList[index].attentionList = res.data.attentionCList;
							vm.datas.domainList[index].attentionCount = res.data.attentionCount;
						} else {
							$.tips({
								content : res.msg,
								stayTime : 2000,
								type : res.type
							});
						}
					}
				)

			return false;
		},
		changePage : function(id){
//			location.href = ctx + "/domainname/singleDomainname?singleDomainId=" + id;
//			return true;
			$.m.changePage("#singleDomainname?singleDomainId="+id);
		},
		// 计算倒计时
		getCountDown : function(endTime) {
			try {
				var restTime = utils.parseDateStr(endTime).getTime() - vm.datas.newDate;
				//var restTime = new Date(endTime.replace(/-/g, "/")).getTime() - vm.datas.newDate.getTime();
				if (restTime <= 0) {
					return {
						disabled : true,
						displayTime : '<i style="font-size: 16px">00</i>时<i style="font-size: 16px">00</i>分<i style="font-size: 16px">00</i>秒'
					}
				}
				var date = {}, h = m = s = "";
				date = utils.millisecondToDate(restTime);
				h = (date.time.hours < 10 ? ("0" + date.time.hours) : date.time.hours);
				m = (date.time.mins < 10 ? ("0" + date.time.mins) : date.time.mins);
				s = (date.time.secs < 10 ? ("0" + date.time.secs) : date.time.secs);
				var displayTime = "";
				if (date.time.day != 0) {
					displayTime += '<i style="color: red; font-size: 16px">' + date.time.day + '</i>天';
				}
				
				displayTime += '<i style="color: red; font-size: 16px">' + h + '</i>时<i style="color: red; font-size: 16px">'
					+ m + '</i>分<i style="color: red; font-size: 16px">' + s + '</i>秒';
				
				
			} catch (e) {
				alert(e);
			}
			return {
				disabled : false,
				displayTime : displayTime
			};
		}
	});

	//每一秒钟给newDate赋值一次，显示倒计时
	setInterval(function() {
		vm.datas.newDate = vm.datas.newDate + 1000;
	}, 1000);

	// 页面显示之后，通过参数获取数据
	var intervalId;
	$("#auction-list").on("pageshow", function() {
		vm.datas.pageIndex = 1;

		// 请求数据
		$.get("auctionData.json", 
				{pageIndex : vm.datas.pageIndex},
				function(res){
					vm.datas.domainList.clear();
					vm.datas.domainList.pushArray(res.data.domainList);
					vm.datas.pageIndex = vm.datas.pageIndex + 1;
					vm.datas.newDate = res.data.sysServerTime;
					// 上滑加载更多
					utils.loadmore("auctionmore", function(callback){
						$.get("auctionData.json", 
							{pageIndex : vm.datas.pageIndex},
							function(res){
								if (res.type == "success") {
									vm.datas.domainList.pushArray(res.data.domainList);
									vm.datas.pageIndex = vm.datas.pageIndex + 1;
								} else {
//									$.tips({
//										content : res.msg,
//										stayTime : 4000,
//										type : res.type
//									});
									setTimeout(function(){
										$("#auctionmore").addClass("hide-loadmore");
									}, 2000);
								}
								callback(res.type);
						});
					});
					
					$("#auction-list").trigger("touchmove");
				});

		// 拍卖列表轮询
		intervalId = setInterval(function(){
			if (vm.datas.domainList.size() == 0) {
				return;
			}
			var domainList = [];
			for (var i = 0; i < vm.datas.domainList.size(); i++) {
				domainList.push(vm.datas.domainList[i].id);
			}
			$.post("polling/auction.json", 
					{
						domainList : domainList
					},
					function(res){
						vm.datas.newDate = res.data.sysServerTime;
						if (res.type == "success" && res.data.newAmount.length > 0) {
							for (var i = 0; i < res.data.newAmount.length; i++) {
								// 增加出价记录
								vm.datas.domainList[i].currAmount = res.data.newAmount[i];
							}
						}
					});
		}, 60000);
	});

	// 页面改变之后，清除轮询操作
	$("#bidding-list").on("pageprehide", function() {
		clearInterval(intervalId);
	});
	

	// 当前价显示优化
	avalon.filters.transferCurrentAmount = function(amount) {
		return utils.priceDisplay(amount);
	};

	return vm;
});
