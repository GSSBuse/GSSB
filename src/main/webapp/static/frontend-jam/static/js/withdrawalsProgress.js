
define(["utils"], function(utils) {
	
	var vm = avalon.define({
		$id : "withdrawalsProgress",
		datas : {
			withdrawalsInfo:[],
			size:0,
			index:0
		},
		//打开某天记录的取消提现的对话框
		cancelWithdrawals:function(index){
			if(vm.datas.withdrawalsInfo[index].confirmResult == "处理中"){
				$.tips({
					content : "处理中的提现不能取消",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if(vm.datas.withdrawalsInfo[index].confirmResult == "已取消"){
				$.tips({
					content : "已取消",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			vm.datas.index = index;
			$("#cancelWithdrawalDialog").dialog("show")
		},
		//确定取消某个提现
		confirmWithdrawal:function(){
			$.ajax({
				 type : 'POST',
				 url : 'cancelWithdrawal',
				 data : {id:vm.datas.withdrawalsInfo[vm.datas.index].id,money:vm.datas.withdrawalsInfo[vm.datas.index].operateAmount},
				 dataType : "json",
				 success :function(res, textStatus){
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					});
					if(res.type=="success"){
						vm.datas.withdrawalsInfo[vm.datas.index].confirmResult = "已取消";
						$("#cancelWithdrawalDialog").dialog("hide");
					}
				 },
				 error : function(){
					 $.tips({
						 content :"取消失败，请重新操作",
						 stayTime:2000,
						 type : "error"
						 });
					 $("#cancelWithdrawalDialog").dialog("hide");
				 }
				});
		}
	});



	// 下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#withdrawalsProgress").on("pageloaded", function(e, d){
		

	});
	
	// 从其他页面跳转回来时，如果想做恢复动作可以监听这个事件
	$("#withdrawalsProgress").on("pageshow", function(e, d){
		$.get("getWithdrawalsInfo.json", {}, function(res) {
			if(res.type=="success"){
				vm.datas.withdrawalsInfo.pushArray(res.data.withdrawalsInfo);
				vm.datas.size = res.data.size;
			}else if(res.type=="warn") {
				vm.datas.size = res.data.size;
				$.tips({
					content : res.msg,
					stayTime:2000,
					type : res.type
				});
			}else{
				$.tips({
					content : res.msg,
					stayTime:2000,
					type : res.type
				});
			}
			
		});
		
	});
	
	
	$("#withdrawalsProgress").on("pagehide", function(e, d){
		vm.datas.withdrawalsInfo.clear();
		vm.datas.size = 0;
		vm.datas.index = 0;
	});
	
	
	return vm;
});
