
define("icenter", function() {
	var vm = avalon.define({
		$id : "icenter",
		datas : {
				//用户信息
				userinfo : {
					
				},
				//财务信息
				financeinfo:{
					accountBalance:"",
					freezeBalance:"",
				},
				//可用余额
				available_balance:"",
				myTransactionsWaitToDealSize:0
			},
		//个人中心页面导航跳转
		//跳转至个人信息页面
		linkToUserinfo:function(){
			$.m.changePage("#personalInfo");
		},
		//跳转至财务信息页面
		linkToFinanceinfo:function(){
			$.m.changePage("#financeInfo");
		},
		//跳转至我的交易信息页面
		linkToMyTransactionsinfo:function(){
			location.href = 'myTransactionsInfo.html';
		},
		//
		linkToSecuritySettings:function(){
			$.m.changePage("#securitySettings");
		}
	});



	// 下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#icenter").on("pageloaded", function(e, d){
		//请求个人信息数据
		$.get("userInfo.json", {}, function(res) {
			//用户信息
			//avalon.mix(vm.datas.userinfo, res.data.userinfo);
			vm.datas.userinfo = res.data.userinfo;
		});
		//请求个人财务信息数据
		$.get("refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
			vm.datas.available_balance = res.data.available_balance;
			
		});
	});
	$("#icenter").on("pageshow", function(e, d){
		//是否有需要待处理的交易
		$.get("myTransactionsWaitToDeal.json", {}, function(res) {
			vm.datas.myTransactionsWaitToDealSize = res.data.myTransactionsWaitToDealSize;
		});
	});
	// 从其他页面跳转回来时，如果想做恢复动作可以监听这个事件
	$("#icenter").on("pagepreshow", function(e, d){
		// 关闭所有打开的对话框
		$(".ui-dialog").dialog("hide");
		//请求个人财务信息数据
		$.get("refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
			vm.datas.available_balance = res.data.available_balance;
			
		});
	});
	
	return vm;
});
