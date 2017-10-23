/**
 * 
 */

define([], function() {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "addRedPack",
		datas:{
			//域名信息
			domainInfo:{
			},
			//临时数据，用于提交
			temp:{
				bonusShareNumber:0,
				bonusShareTotal:0,
				bonusSecond:0,
				promptStr:""//提示信息，用于提示输入金额的范围
			},
			//财务信息:可用余额
			available_balance:""
		},
		//设置按钮是否有效
		buttonIsEffective : function(){
			vm.datas.temp.bonusSecond = $("#bonusSecond").val();
			
			if(vm.datas.temp.bonusSecond != 0 && vm.datas.temp.bonusSecond != ""){
				if(vm.datas.temp.bonusShareTotal != 0 && vm.datas.temp.bonusShareTotal != "" && vm.datas.temp.bonusShareNumber != 0 && vm.datas.temp.bonusShareNumber != ""){
					if((vm.datas.temp.bonusShareTotal / vm.datas.temp.bonusShareNumber >= 1) && (vm.datas.temp.bonusShareTotal / vm.datas.temp.bonusShareNumber <= 200)){
						$("#comfirmButton").removeAttr("disabled");
					}else{
						$("#comfirmButton").attr("disabled","disabled");
					}
				}else{
					if(vm.datas.temp.bonusShareTotal > 0 || vm.datas.temp.bonusShareNumber >0){
						$("#comfirmButton").attr("disabled","disabled");
					}else{
						$("#comfirmButton").removeAttr("disabled");
						vm.datas.temp.bonusShareTotal = 0;
						vm.datas.temp.bonusShareNumber = 0;
					}
				}
			}else{
				if(vm.datas.temp.bonusShareTotal != 0 && vm.datas.temp.bonusShareTotal != "" && vm.datas.temp.bonusShareNumber != 0 && vm.datas.temp.bonusShareNumber != ""){
					if((vm.datas.temp.bonusShareTotal / vm.datas.temp.bonusShareNumber >= 1) && (vm.datas.temp.bonusShareTotal / vm.datas.temp.bonusShareNumber <= 200)){
						$("#comfirmButton").removeAttr("disabled");
					}else{
						$("#comfirmButton").attr("disabled","disabled");
					}
				}else{
					$("#comfirmButton").attr("disabled","disabled");
				}
			}
		},
		//提交追加红包申请
		comfirmAddRedPack:function(){
			if((vm.datas.temp.bonusShareTotal > '0' && vm.datas.temp.bonusShareNumber > '0')
					|| (vm.datas.temp.bonusShareTotal == '0' && vm.datas.temp.bonusShareNumber == '0'&& vm.datas.temp.bonusSecond > '0')) {
			} else{
				$.tips({
					content : "输入不能为空",
					stayTime : 2000,
					type : "warn"
				});
				return false;
			}
			if(vm.datas.available_balance < vm.datas.temp.bonusShareTotal){
				$.tips({
					content : "余额不足请先充值",
					stayTime : 2000,
					type : "warn"
				});
			}else{
				if ((vm.datas.temp.bonusShareTotal / vm.datas.temp.bonusShareNumber < 1) || (vm.datas.temp.bonusShareTotal / vm.datas.temp.bonusShareNumber > 200)) {
					$.tips({
						content : "转发红包每份最少1元 最多200元",
						stayTime : 2000,
						type : "warn"
					});
					$("#comfirmButton").attr("disabled","disabled")
				}else{
					committing.show();
					var submitData = $.param({domainId:vm.datas.domainInfo.id, bonusShareTotal:vm.datas.temp.bonusShareTotal, bonusShareNumber:vm.datas.temp.bonusShareNumber, bonusSecond:vm.datas.temp.bonusSecond});
					$.m.changePage("#inputPayPassword",{from : "addRedPack",url : "isell/addRedPackInfo",data : submitData,changePage : "isell",errorMsg : "追加红包失败"});
					committing.hide();
//					这个请求转移到输入支付密码页面做，在此页面传递参数过去
//					$.ajax({
//						type : 'POST',
//						url : 'isell/addRedPackInfo',
//						data : {domainId:vm.datas.domainInfo.id, bonusShareTotal:vm.datas.temp.bonusShareTotal, bonusShareNumber:vm.datas.temp.bonusShareNumber, bonusSecond:vm.datas.temp.bonusSecond},
//						dataType : "json",
//						success :function(res, textStatus){
//							$.m.changePage("#isell");
//							committing.hide();
//						 },
//						 error : function(){
//							 committing.hide();
//							 $.tips({
//								 content :"追加红包失败",
//								 stayTime:2000,
//								 type : "error"
//							});
//						 }
//					});
				}
			}
		}
		
	});
	
	// 下面是获取个人领红包记录,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#addRedPack").on("pageloaded", function(e, d){
		vm.datas.ifTransmitRedPack = false;
		vm.datas.ifSecondRedPack = false;
	});
	
	$("#addRedPack").on("pagepreshow", function(e, d) {
		var domainnameId = $.m.getHashParam().domainnameId;
		
		$.get("refreshFinanceinfo.json", {}, function(res){
			vm.datas.available_balance = res.data.available_balance;
		});
		
		$.get("isell/domainInfo.json", {domainId:domainnameId}, function(res){
			vm.datas.domainInfo = res.data.domainInfo;
		});
	});
	
	$("#addRedPack").on("pagehide", function() {
		vm.datas.domainInfo = {};
		vm.datas.temp.bonusShareNumber=0;
		vm.datas.temp.bonusShareTotal=0;
		vm.datas.temp.bonusSecond=0;
		vm.datas.temp.promptStr="";
		$("#bonusSecond").val(0);
		$("#comfirmButton").attr("disabled","disabled");
	});
	//touchend
	$("#bonusShareNumber").bind("keyup", function(){
		//设置提示语
		if(vm.datas.temp.bonusShareNumber!=0 && vm.datas.temp.bonusShareNumber!=""){
			if(vm.datas.temp.bonusShareTotal==0||vm.datas.temp.bonusShareTotal==""){
				vm.datas.temp.promptStr = vm.datas.temp.bonusShareNumber.toString()+"元~"+(vm.datas.temp.bonusShareNumber*200).toString()+"元之间"
			}else{
				if(vm.datas.temp.bonusShareTotal < vm.datas.temp.bonusShareNumber || vm.datas.temp.bonusShareTotal > vm.datas.temp.bonusShareNumber*200){
					vm.datas.temp.promptStr = vm.datas.temp.bonusShareNumber.toString()+"元~"+(vm.datas.temp.bonusShareNumber*200).toString()+"元之间"
				}else{
					vm.datas.temp.promptStr = "";
				}
			}
		}else{
			vm.datas.temp.promptStr = "";
			if(vm.datas.temp.bonusShareTotal != 0 && vm.datas.temp.bonusShareTotal != ""){
				$.tips({
					content : "红包个数不能为空或0",
					stayTime: 2000,
					type : "warn"
				});
			}
		}
		
		//检查按钮是否需要有效
		vm.buttonIsEffective();
	});
	
	$("#bonusShareTotal").bind("keyup", function(){
		//设置提示语
		if(vm.datas.temp.bonusShareTotal > 0){
			if(vm.datas.temp.bonusShareNumber != 0 && vm.datas.temp.bonusShareNumber != ""){
				if(vm.datas.temp.bonusShareTotal < vm.datas.temp.bonusShareNumber || vm.datas.temp.bonusShareTotal > vm.datas.temp.bonusShareNumber*200){
					vm.datas.temp.promptStr = vm.datas.temp.bonusShareNumber.toString()+"元~"+(vm.datas.temp.bonusShareNumber*200).toString()+"元之间"
				}else{
					vm.datas.temp.promptStr = "";
				}
			}else{
				$.tips({
					content : "红包个数不能为空或0",
					stayTime: 2000,
					type : "warn"
					});
				vm.datas.temp.promptStr = "";
			}
		}else{
			if(vm.datas.temp.bonusShareNumber != 0 && vm.datas.temp.bonusShareNumber != ""){
				vm.datas.temp.promptStr = vm.datas.temp.bonusShareNumber.toString()+"元~"+(vm.datas.temp.bonusShareNumber*200).toString()+"元之间"
			}else{
				vm.datas.temp.promptStr = "";
			}
		}
		//检查按钮是否需要有效
		vm.buttonIsEffective();
	});
	
	$("#bonusSecond").bind("keyup", function(){
		//检查按钮是否需要有效
		vm.buttonIsEffective();
	});
	
	committing = $.loading({content:'提交中...'}).hide();
	return vm;
});	