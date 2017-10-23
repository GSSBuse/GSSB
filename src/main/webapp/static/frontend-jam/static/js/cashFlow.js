/**
 * 
 */

define(["utils"], function(utils) {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "cashFlow",
		datas:{
			cashflowinfo:[],
			pageIndex: 1
		}
	});
	
	// 下面是获取个人资金流,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#cashFlow").on("pageshow", function(e, d){
		vm.datas.cashflowinfo.clear();
		vm.datas.pageIndex = 1;
//		$.get("myCashFlow.json", {pageIndex : vm.datas.pageIndex,}, function(res) {
//			//avalon.mix(vm.datas.cashflowinfo, res.data.cashflowinfo);
//			if(res.type=="warn"){
//				$.tips({
//					content : res.msg,
//					stayTime:2000,
//					type : res.type
//				})
//			}
//			
//			if (res.type === 'success') {
//				vm.datas.cashflowinfo.clear();
//				vm.datas.cashflowinfo.pushArray(res.data.cashflowinfo);
//				vm.datas.pageIndex = vm.datas.pageIndex + 1;
				utils.loadmore("loadmore_cash", function(callback){
					$.get("myCashFlow.json", {pageIndex : vm.datas.pageIndex},	function(res){
						if (res.type == "success") {
							vm.datas.cashflowinfo.pushArray(res.data.cashflowinfo);
							vm.datas.pageIndex = vm.datas.pageIndex + 1;
							setTimeout(function(){
								$("#cashFlow").trigger("touchmove");
							}, 100);
						} else {
//							$.tips({
//								content : res.msg,
//								stayTime : 4000,
//								type : res.type
//							});
							setTimeout(function(){
								$("#loadmore_cash").addClass("hide-loadmore");
							}, 2000);
						}
						callback(res.type);
					});
				});
				$("#cashFlow").trigger("touchmove");
//			}
//		});
	});
	
	$("#cashFlow").on("pagehide", function(e, d){
		vm.datas.cashflowinfo.clear();
		vm.datas.pageIndex = 1;
	});
	return vm;
});	