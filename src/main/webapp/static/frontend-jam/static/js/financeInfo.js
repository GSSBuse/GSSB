
define(function() {
	
	var vm = avalon.define({
		$id : "financeInfo",
		datas : {
			//红包功能开关标记
			shareBonusEnable : 0,
			//用户信息
			userinfo : {
				dyid : "",
				name : "",
				email: "",
				mobile:"",
				wx:"",
				qq:"",
				defaultIncomeExpense:"",
				nickname:"",
				authenticationMark:"",
				emailFlag:"",
				authenticationPositiveImageUrl:"",
				authenticationNegativeImageUrl:"",
				photo:"",
				dyid:"",
				payPassword:""
			},
			// 需要更新的数据，可以暂存在这里
			temp : {
				name : "",
				email: "",
				mobile:"",
				qq:"",
				wx:"",
				defaultIncomeExpense:"",
				nickname:"",
				authenticationMark:"",
				emailFlag:"",
				authenticationPositiveImageUrl:"",
				authenticationNegativeImageUrl:"",
				photo:"",
				dyid:"",
				payPassword:""
			},
			//财务信息
			financeinfo:{
				accountBalance:"",
				freezeBalance:"",
			},
			//可用余额
			available_balance:"",
			//资金流信息
			cashflowinfo:{
				operateAmount:"",
				rechargeAmount:"",
				operate:"",
				payPassword:"",
				amount : ""
			}
		},
		//跳转至冻结资金记录页面
		goToFreezeRecord:function(){
			$.m.changePage("freezeRecord.html");
		},
//		//跳转至领红包记录页面
//		linkToBonusRecord:function(){
//			$.m.changePage("bonusRecord.html");
//			//location.href = 'bonusRecord.html';
//		},
//		//跳转至领佣金记录页面
//		linkToCommissionRecord:function(){
//			$.m.changePage("commissionRecord.html");
//			//location.href = 'commissionRecord.html';
//		},
		//跳转至资金流页面
		linkToCashFlow:function(){
			$.m.changePage("cashFlow.html");
			//location.href = 'cashFlow.html';
		},
		//跳转至提现进度页面
		linkToWithdrawalsProgress:function(){
			$.m.changePage("withdrawalsProgress.html");
			//location.href = 'withdrawalsProgress.html';
		},
		//检查用户是否完善了个人信息
		checkPersonalInfo : function(){
			try {
				if(!vm.datas.userinfo.name){
					return false;
				}
				if(vm.datas.userinfo.emailFlag != "1" || !vm.datas.userinfo.email ){
					return false;
				}
				if(!vm.datas.userinfo.mobile){
					return false;
				}
				if(!vm.datas.userinfo.qq && !vm.datas.userinfo.wx){
					return false;
				}
				if(vm.datas.userinfo.authenticationMark != "1" || !vm.datas.userinfo.authenticationPositiveImageUrl || !vm.datas.userinfo.authenticationNegativeImageUrl){
					return false;
				}
				if(!vm.datas.userinfo.defaultIncomeExpense){
					return false;
				}
				return true;
			} catch(e) {
				alert(e);
				return false;
			}
		},
		//财务管理：提现弹窗
		withdrawals:function(e){
			if(!vm.datas.userinfo.payPassword){
				$.tips({
					content : "请先设置安全密码",
					stayTime:1000,
					type : "warn"
				}).on("tips:hide",function(){
					$.m.changePage("#addPayPassword?type=set");
				});
				return false
			}
			var verificationPersonalInfo = vm.checkPersonalInfo();
			if(!verificationPersonalInfo){
				if (vm.datas.userinfo.authenticationMark != "2") {
					$.tips({
						content : "请先完善个人信息",
						stayTime:1000,
						type : "warn"
					}).on("tips:hide",function(){
						$.m.changePage("#personalInfo");
					});
				}
				if(vm.datas.userinfo.authenticationMark == "2"){
					$.tips({
						content : "请等待经纪人审核个人信息",
						stayTime: 1000,
						type : "warn"
					});
				}
				return false;
			}
			$("#withdrawals").dialog("show");
			$("#operate_amount").focus();
		},
		clearOperateAmountInput:function(e){
			vm.datas.cashflowinfo.operateAmount="";
			document.getElementById("operate_amount").focus();
		},
		//打开提现验证支付密码对话框
		openVerificationPayPasswordForWithdrawals:function(){
			if($("#operate_amount").val()=="" || $("#operate_amount").val()=="0"){
				$.tips({
					content : "不能为空或为0",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			var reg = /^[\d]{1,9}$/;//只能输入数字
			if(!reg.test($("#operate_amount").val())){
				$.tips({
					content : "只能输入小于9位数字",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#operate_amount").val()!="" && reg.test($("#operate_amount").val())){
				if(vm.datas.available_balance<vm.datas.cashflowinfo.operateAmount){
					$.tips({
						content : "可用余额不足",
						stayTime:2000,
						type : "warn"
					});
					return false;
				}
			}
			$("#verificationPayPasswordForWithdrawals").dialog("show");
			$("#withdrawals").dialog("hide");
			$("#payPassword").focus();
		},
		//清除支付密码输入
		clearPayPasswordInput:function(){
			vm.datas.cashflowinfo.payPassword = "";
			$("#payPassword").focus();
		},
		clearForWithdrawals:function(){
			vm.datas.cashflowinfo.payPassword = "";
			$("#verificationPayPasswordForWithdrawals").dialog("hide");
			$("#withdrawals").dialog("hide");
		},
		//财务管理：提交提现
		confirmWithdrawals:function(e){	
			console.log(e);
			if(vm.datas.cashflowinfo.payPassword == ""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			
			var reg = /^[\d]{6}$/;//只能输入6位数字
			if(!reg.test(vm.datas.cashflowinfo.payPassword)){
				$.tips({
					content : "只能输入6位数字",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			committing.show();
			$.post("verificationOldPassword", {oldPassword:vm.datas.cashflowinfo.payPassword}, function(res){
				if(res.type == "success"){
					vm.datas.cashflowinfo.operate="提现";
					$.ajax({
							 type : 'POST',
							 url : 'rechargeOrWithdrawals',
							 data : "operate=" + vm.datas.cashflowinfo.operate + "&operateAmount=" + vm.datas.cashflowinfo.operateAmount,
							 dataType : "json",
							 success :function(res, textStatus){
								if(res.type=="success"){
									$("#verificationPayPasswordForWithdrawals").dialog("hide");
									vm.datas.cashflowinfo.payPassword = "";
									vm.datas.cashflowinfo.operateAmount = "";
								}
								committing.hide();
								$.tips({
						                content : res.msg,
						                stayTime:2000,
						                type : res.type
						        });
							 },
							 error : function(){
								 $("#verificationPayPasswordForWithdrawals").dialog("hide");
								 vm.datas.cashflowinfo.payPassword = "";
								 committing.hide();
								 $.tips({
									 content :"提现失败，请重新操作或联系经纪人",
									 stayTime:2000,
									 type : "error"
								 });
							 }
					});
				}else if(res.type == "warn"){
					$("#verificationPayPasswordForWithdrawals").dialog("show");
					vm.datas.cashflowinfo.payPassword = "";
					committing.hide();
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					});
				}else{
					vm.datas.cashflowinfo.payPassword = "";
					$("#verificationPayPasswordForWithdrawals").dialog("hide");
					$("#withdrawals").dialog("hide");
					committing.hide();
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					});
				}
			});		
		},
		//查看平台银行信息
		seeplatformBankInfo : function(){
			$("#financeInfo #platform-bank-info").dialog("show");
		},
		//设置选择线下充值
		selectOffLine : function(e){
//			$("#recharge #online").removeAttr("checked")
//			$("#recharge #offline").attr("checked",true);
			//$("#recharge #offline").trigger('click');
			$("#recharge #offline").click();
		},
		//设置选择微信充值
		selectOnLine : function(){
//			$("#recharge #offline").removeAttr("checked")
//			$("#recharge #online").attr("checked",true);
			//$("#recharge #online").trigger('click');
			$("#recharge #online").click();
		},
		//财务管理：充值弹窗
		recharge:function(){
			if(!vm.datas.userinfo.payPassword){
				$.tips({
					content : "请先设置安全密码",
					stayTime:1000,
					type : "warn"
				}).on("tips:hide",function(){
					$.m.changePage("#addPayPassword?type=set");
				});
				return false
			}
			
			var verificationPersonalInfo = vm.checkPersonalInfo();
			if(!verificationPersonalInfo){
				if (vm.datas.userinfo.authenticationMark != "2") {
					$.tips({
						content : "请先完善个人信息",
						stayTime:1000,
						type : "warn"
					}).on("tips:hide",function(){
						$.m.changePage("#personalInfo");
					});
				}
				if(vm.datas.userinfo.authenticationMark == "2"){
					$.tips({
						content : "请等待经纪人审核个人信息",
						stayTime: 1000,
						type : "warn"
					});
				}
				return false;
			}
			$("#recharge #platformBankInfo").addClass("hidden");
			$("#recharge").dialog("show");
			$("#recharge #online").click();
			$("#rechargeAmount").focus();
		},
		clearRechargeAmountInput:function(){
			vm.datas.cashflowinfo.rechargeAmount="";
			document.getElementById("rechargeAmount").focus();
		},
		//财务管理：提交充值
		confirmRecharge:function(){
			vm.datas.cashflowinfo.operate="充值";
			if($("#rechargeAmount").val()=="" || $("#rechargeAmount").val() == "0"){
				$.tips({
					content : "不能为空或为0",
					stayTime:2000,
					type : "warn"
				});
			}else{
				var reg = /^[\d]{1,9}$/;//只能输入数字
				if(!reg.test($("#rechargeAmount").val())){
					$.tips({
						content : "只能输入数字且最多9位",
						stayTime:2000,
						type : "warn"
					});
				}else{
					//线下支付
					if(document.getElementById("offline").checked){
						committing.show();
						$.post("rechargeOrWithdrawals", {operate:vm.datas.cashflowinfo.operate,operateAmount:vm.datas.cashflowinfo.rechargeAmount}, function(res){
							if (res.type=="success") {								
								$("#recharge #online").click();
								$("#recharge #platformBankInfo").addClass("hidden");
								vm.datas.cashflowinfo.amount = vm.datas.cashflowinfo.rechargeAmount
								vm.datas.cashflowinfo.rechargeAmount = "";
								vm.datas.cashflowinfo.operate = "";
								$("#recharge").dialog("hide");
								committing.hide();
								$.tips({
									content : res.msg,
									stayTime:2000,
									type : res.type
								}).on("tips:hide",function(){
									$("#financeInfo #prompt-msg").dialog("show");
								});
							}else{
								$.tips({
									content : res.msg,
									stayTime:2000,
									type : res.type
								});
							}
						});
					}
					
					//微信支付，待检
					if(document.getElementById("online").checked){
						$.ajax({
							type : 'POST',
							url : 'wxPay',
							data : "operate=" + vm.datas.cashflowinfo.operate + "&operateAmount=" + vm.datas.cashflowinfo.rechargeAmount,
							dataType : "json",
							success :function(res, textStatus){
								$("#recharge #platformBankInfo").addClass("hidden");
								$("#recharge").dialog("hide");
								vm.datas.cashflowinfo.rechargeAmount = "";
								vm.datas.cashflowinfo.operate = "";
								wx.chooseWXPay({
									timestamp: res.data.timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
									nonceStr: res.data.nonceStr, // 支付签名随机串，不长于 32 位
									'package': res.data['package'], // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
									signType: res.data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
									paySign: res.data.paySign, // 支付签名
									success: function (res) {
										// 支付成功后的回调函数
										$.tips({
											content : "充值成功",
											stayTime:2000,
											type : "success"
										});
									},
									fail:function(){
										$.tips({
											content : "微信充值接口调用失败",
											stayTime:2000,
											type : "success"
										});
									}
								 });
							 },
							 error : function(){
								vm.datas.cashflowinfo.rechargeAmount = "";
								vm.datas.cashflowinfo.operate = "";
								 $.tips({
									 content :"微信充值失败，请重新操作或联系经纪人",
									 stayTime:2000,
									 type : "error"
								});
								 $("#recharge #platformBankInfo").addClass("hidden");
								$("#recharge").dialog("hide");
							 }
						});
					}
				}
			}
		}
	});
	
	// 下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#financeInfo").on("pageloaded", function(e, d){
		
		$.get("refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
			vm.datas.available_balance = res.data.available_balance;
			vm.datas.shareBonusEnable = res.data.shareBonusEnable;
		});

		$.get("userInfo.json", {}, function(res) {
			//用户信息
			avalon.mix(vm.datas.userinfo, res.data.userinfo);
			avalon.mix(vm.datas.temp, res.data.userinfo);
		});
		
		// 获取jsticket
		$.get("jsapiTicket.json", {href:window.location.href},function(res) {
			wx.config({
				debug : false,
				appId : res.data.appId,
				timestamp : res.data.timestamp,
				nonceStr : res.data.nonceStr,
				signature : res.data.signature, // 签名
				jsApiList :[
					'chooseImage',
					'uploadImage',
					'hideOptionMenu',
					'showOptionMenu',
					'hideMenuItems',
					'showMenuItems',
					'chooseWXPay'
				]
			});
			
			wx.ready(function(){
				// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
//				wx.hideMenuItems({
//					menuList: ["menuItem:share:qq"]
//				});
			});
			
			wx.error(function(res){
				// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			});
		});
		
		$("#recharge #offline").click(function(e){
			$("#recharge #platformBankInfo").removeClass("hidden");
			$("#rechargeAmount").focus();
			e.stopPropagation();
		});
		$("#recharge #online").click(function(e){
			$("#recharge #platformBankInfo").addClass("hidden");
			$("#rechargeAmount").focus();
			e.stopPropagation();
		});
	});
	
	// 从其他页面跳转回来时，如果想做恢复动作可以监听这个事件
	$("#financeInfo").on("pagepreshow", function(e, d){
		// 关闭所有打开的对话框
		$(".ui-dialog").dialog("hide");
		
		$.get("userInfo.json", {}, function(res) {
			//用户信息
			avalon.mix(vm.datas.userinfo, res.data.userinfo);
			avalon.mix(vm.datas.temp, res.data.userinfo);
		});
		
		$.get("refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
			vm.datas.available_balance = res.data.available_balance;
			vm.datas.shareBonusEnable = res.data.shareBonusEnable;
		});
	});

	$("#financeInfo").on("pagehide", function(e, d){
		vm.datas.cashflowinfo.payPassword = "";
	});
	
	committing = $.loading({content:'提交中...'}).hide();
	return vm;
});
