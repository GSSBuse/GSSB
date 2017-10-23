
define(function() {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "bidding-list",
		datas : {
			domainId : "",
			domainName : "",
			domainList : [],
			bidCount : 0,
			endTime : "",
			pageIndex : 1,
			tmp : {
				isFinished : false
			}
		}

	});
	
	// 页面显示之后，通过参数获取数据
	var intervalId;
	$("#bidding-list").on("pageshow", function() {
		vm.datas.pageIndex = 1;
		vm.datas.domainId = $.m.getParam().domainId;
		if (!vm.datas.domainId) {
			return;
		}
		// 请求数据
		$.get("bidData.json", 
				{domainId : vm.datas.domainId, pageIndex : vm.datas.pageIndex},
				function(res){
					if (res.type == "success") {
						vm.datas.domainList.clear();
						vm.datas.domainName = res.data.domainName;
						vm.datas.tmp.isFinished = res.data.isFinished;
						vm.datas.domainList.pushArray(res.data.bidClientList);
						vm.datas.bidCount = res.data.bidCount;
						vm.datas.endTime = res.data.endTime;
						vm.datas.pageIndex = vm.datas.pageIndex + 1;
						
					} else {
						$.tips({
							content : res.msg,
							stayTime : 4000,
							type : res.type
						});
					}
					
					// 上滑加载更多
					utils.loadmore("biddingmore", function(callback){
						$.get("bidData.json", 
								{
									domainId : vm.datas.domainId,
									pageIndex : vm.datas.pageIndex
								},
								function(res){
									if (res.type == "success") {
										vm.datas.domainList.pushArray(res.data.bidClientList);
										vm.datas.pageIndex = vm.datas.pageIndex + 1;
									} else {
//										$.tips({
//											content : res.msg,
//											stayTime : 4000,
//											type : res.type
//										});
										setTimeout(function(){
											$("#biddingmore").addClass("hide-loadmore");
										}, 2000);
									}
									callback(res.type);
						});
					});
					$("#bidding-list").trigger("touchmove");
				});
		
		// 出价列表轮询
		intervalId = setInterval(function(){
			if (vm.datas.domainList.size() == 0) {
				return;
			}
			$.get("polling/bidding.json", 
					{
				domainId : vm.datas.domainId,
				bidhistoryId : vm.datas.domainList[0].id
					},
					function(res){
						if (res.type == "success" && res.data.bidClientList.length > 0) {
							for (var i = res.data.bidClientList.length; i > 0; i--) {
								// 增加出价记录
								vm.datas.domainList.unshift(res.data.bidClientList[i-1]);
							}
							// 增加出价次数
							vm.datas.bidCount = vm.datas.bidCount + res.data.bidClientList.length;
						}
					});
		}, 500000);
	});

	// 页面改变之后，清除轮询操作
	$("#bidding-list").on("pageprehide", function() {
		clearInterval(intervalId);
	});
	
	$("#bidding-list").on("pagehide", function() {
		vm.datas.domainId = "";
		vm.datas.domainName = "";
		vm.datas.domainList.clear();
		vm.datas.bidCount = 0;
		vm.datas.endTime = "";
		vm.datas.pageIndex = 1;
		vm.datas.tmp.isFinished = false;
	});
	
	// 当前价显示优化
	avalon.filters.transferCurrentAmount = function(amount) {
		return utils.priceDisplay(amount);
	};

	return vm;
});
