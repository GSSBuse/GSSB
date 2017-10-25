/**
 * 
 */

define([], function() {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "history",
		datas:{
			historyinfo:[],
			pageIndex : 1
		},
		//跳转至当个域名页面
		linkToSingleDomainname:function(id){
			var href = "singleDomainname.html?singleDomainId="+id;
			location.href = href;
		},
	});
	
	// 下面是获取个人资金流,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#getHistory").on("pageloaded", function(e, d){
		vm.datas.historyinfo.clear();
		vm.datas.pageIndex = 1;
		utils.loadmore("loadmore_historyInfo", function(callback){
			$.get("getHistoryInfoPage.json", {pageIndex : vm.datas.pageIndex},	function(res){
				if (res.type == "success") {
					vm.datas.historyinfo.pushArray(res.data.historyinfo);
					vm.datas.pageIndex = vm.datas.pageIndex + 1;
					setTimeout(function(){
						$("#getHistory").trigger("touchmove");
					}, 100);
				} else {
					setTimeout(function(){
						$("#loadmore_historyInfo").addClass("hide-loadmore");
					}, 2000);
				}
				callback(res.type);
			});
		});
		$("#getHistory").trigger("touchmove");
	});
	return vm;
});	