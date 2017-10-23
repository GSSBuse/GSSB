/**
 * 
 */

define(["utils"], function(utils) {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "freezeRecord",
		datas:{
			freezeinfo:[],
			pageIndex: 1
		}
	});
	
	// 下面是获取个人资金流,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#freezeRecord").on("pageshow", function(e, d){
		vm.datas.freezeinfo.clear();
		vm.datas.pageIndex = 1;
//		$.get("freezeinfo.json", {pageIndex : vm.datas.pageIndex,}, function(res) {
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
//				vm.datas.freezeinfo.pushArray(res.data.freezeinfo);
//				vm.datas.pageIndex = vm.datas.pageIndex + 1;
				utils.loadmore("loadmore_freeze", function(callback){
					$.get("freezeinfo.json", {pageIndex : vm.datas.pageIndex},	function(res){
						if (res.type == "success") {
							vm.datas.freezeinfo.pushArray(res.data.freezeinfo);
							vm.datas.pageIndex = vm.datas.pageIndex + 1;
							setTimeout(function(){
								$("#freezeRecord").trigger("touchmove");
							}, 100);
						} else {
//							$.tips({
//								content : res.msg,
//								stayTime : 4000,
//								type : res.type
//							});
							setTimeout(function(){
								$("#loadmore_freeze").addClass("hide-loadmore");
							}, 2000);
						}
						callback(res.type);
					});
				});
				$("#freezeRecord").trigger("touchmove");
//			}
//		});
	});
	
	$("#freezeRecord").on("pagehide", function(e, d){
		vm.datas.freezeinfo.clear();
		vm.datas.pageIndex = 1;
	});
	return vm;
});	