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
			historyinfo:[]
		},
		//跳转至当个域名页面
		linkToSingleDomainname:function(id){
			var href = "singleDomainname.html?singleDomainId="+id;
			location.href = href;
		},
	});
	
	// 下面是获取个人资金流,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#getHistory").on("pageloaded", function(e, d){
		$.get("getHistoryInfo.json", {}, function(res) {
			//最近七天交易记录，记录以Map记录：domainnameId（域名id），status（域名状态），name（域名名称），sellClientId（卖家id），sellNickName（卖家昵称），sellDate（交易日期），bidAmount（成交价），buyClientId（买家id），buyNickName(买家昵称)
			vm.datas.historyinfo.pushArray(res.data.historyinfo);
		});
	});
	return vm;
});	