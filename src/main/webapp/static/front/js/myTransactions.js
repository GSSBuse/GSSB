
require(["utils"], function(utils) {
	
	var vm = avalon.define({
		$id : "myTransactions",
		datas : {
			newDate : new Date().getTime(),
			page : {
				myTransactionsDoingPage : {
				
				},
				myTransactionsWaitDealPage : {
					
				},
				myTransactionsDonePage :{
					
				}
			},
			isBuyFlag :{
				isBuyFlagForDoing : "buy",
				isBuyFlagForWaitDeal : "buy",
				isBuyFlagForDone : "buy"
			},
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
				IDcardNumber:"",
				payPassword :""
			},
			//财务信息
			financeinfo:{
				accountBalance : 0,
				freezeBalance : 0,
			},
			//拍卖中信息
			myTransactionsDoingInfo:[],
			//待处理信息
			myTransactionsWaitDealInfo : [],
			//已完成信息
			myTransactionsDoneInfo : []
		},
		//跳转至单域名页面
		goToSingleDomainname : function(index,from){
			var id = null;
			if(from == "myTransactionsDoing"){
				id = vm.datas.myTransactionsDoingInfo[index].domainnameId;
			}
			if(from == "myTransactionsWaitDeal"){
				id = vm.datas.myTransactionsWaitDealInfo[index].domainnameId;
			}
			if(from == "myTransactionsDone"){
				id = vm.datas.myTransactionsDoneInfo[index].domainnameId;
			}
			window.location.href = ctx + "/ibuysingle.html?domainId=" + id;
		},
		//跳转至拍卖中信息页面
		goTomyTransactionsDoing : function(isBuyFlag){
			$.get(ctx+"/myTransactions/myTransactionsDoing.json", {isBuyFlag : isBuyFlag,pageIndex : 1}, function(res) {
				vm.datas.myTransactionsDoingInfo.clear();
				vm.datas.myTransactionsDoingInfo.pushArray(res.data.myTransactionsDoingInfo);
				vm.datas.page.myTransactionsDoingPage = res.data.myTransactionsDoingPage;
				//服务器时间，用于倒计时
				vm.datas.newDate = res.data.sysServerTime;
				vm.datas.isBuyFlag.isBuyFlagForDoing = isBuyFlag;
			});
			
			$(this).addClass("selected");
			$("#myTransactionsWaitDealLi").removeClass("selected");
			$("#myTransactionsDoneLi").removeClass("selected");
			
			$("#myTransactionsWaitDeal").addClass("hidden");
			$("#myTransactionsDone").addClass("hidden");
			$("#myTransactionsDoing").removeClass("hidden");
		},
		//跳转至待处理交易页面
		goTomyTransactionsWaitDeal:function(isBuyFlag){
			$.get(ctx+"/myTransactions/myTransactionsWaitDeal.json", {isBuyFlag : isBuyFlag,pageIndex : 1}, function(res) {
				vm.datas.myTransactionsWaitDealInfo.clear();
				vm.datas.myTransactionsWaitDealInfo.pushArray(res.data.myTransactionsWaitDealInfo);
				vm.datas.page.myTransactionsWaitDealPage = res.data.myTransactionsWaitDealPage;
				//服务器时间，用于倒计时
				vm.datas.newDate = res.data.sysServerTime;
				vm.datas.isBuyFlag.isBuyFlagForWaitDeal = isBuyFlag;
			});
			
			$(this).addClass("selected");
			$("#myTransactionsDoneLi").removeClass("selected");
			$("#myTransactionsDoingLi").removeClass("selected");
			
			$("#myTransactionsDoing").addClass("hidden");
			$("#myTransactionsDone").addClass("hidden");
			$("#myTransactionsWaitDeal").removeClass("hidden");
		},
		//跳转至已完成交易页面
		goTomyTransactionsDone:function(isBuyFlag){
			$.get(ctx+"/myTransactions/myTransactionsDone.json", {isBuyFlag : isBuyFlag,pageIndex : 1}, function(res) {
				vm.datas.myTransactionsDoneInfo.clear();
				vm.datas.myTransactionsDoneInfo.pushArray(res.data.myTransactionsDoneInfo);
				vm.datas.page.myTransactionsDonePage = res.data.myTransactionsDonePage;
				//服务器时间，用于倒计时
				vm.datas.newDate = res.data.sysServerTime;
				vm.datas.isBuyFlag.isBuyFlagForDone = isBuyFlag;
			});
			$(this).addClass("selected");
			$("#myTransactionsDoingLi").removeClass("selected");
			$("#myTransactionsWaitDealLi").removeClass("selected");
			
			$("#myTransactionsDoing").addClass("hidden");
			$("#myTransactionsWaitDeal").addClass("hidden");
			$("#myTransactionsDone").removeClass("hidden");
		},
		goPrev: function (from) {
			if ($(this).is(".disabled")) {
				return;
			}
			vm.refresh(from,-1);
		},
		goNext: function (from) {
			if ($(this).is(".disabled")) {
				return;
			}
			vm.refresh(from,1,isBuyFlag);
		},
		refresh: function (from,move) {
			if(from == "myTransactionsDoing"){
				var isBuyFlag =$("#isBuyFlagForDoing").val();
				$.get(ctx+"/myTransactions/myTransactionsDoing.json", {isBuyFlag : isBuyFlag,pageIndex : vm.datas.page.myTransactionsDoingPage.pageNo + move},	function(res){
					
						vm.datas.myTransactionsDoingInfo.clear();
						vm.datas.myTransactionsDoingInfo.pushArray(res.data.myTransactionsDoingInfo);
						vm.datas.page.myTransactionsDoingPage = res.data.myTransactionsDoingPage;
						//服务器时间，用于倒计时
						vm.datas.newDate = res.data.sysServerTime;
						vm.datas.isBuyFlag.isBuyFlagForDoing = isBuyFlag;
					
				});
			}
			if(from == "myTransactionsWaitDeal"){
				var isBuyFlag =$("#isBuyFlagForWaitDeal").val();
				$.get(ctx+"/myTransactions/myTransactionsWaitDeal.json", {isBuyFlag : isBuyFlag,pageIndex : vm.datas.page.myTransactionsWaitDealPage.pageNo + move},	function(res){
					//if(res.type == "success"){
						vm.datas.myTransactionsWaitDealInfo.clear();
						vm.datas.myTransactionsWaitDealInfo.pushArray(res.data.myTransactionsWaitDealInfo);
						vm.datas.page.myTransactionsWaitDealPage = res.data.myTransactionsWaitDealPage;
						//服务器时间，用于倒计时
						vm.datas.newDate = res.data.sysServerTime;
						vm.datas.isBuyFlag.isBuyFlagForWaitDeal = isBuyFlag;
					//}
				});
			}
			
			if(from == "myTransactionsDone"){
				var isBuyFlag =$("#isBuyFlagForDone").val();
				$.get(ctx+"/myTransactions/myTransactionsDone.json", {isBuyFlag : isBuyFlag,pageIndex : vm.datas.page.myTransactionsDonePage.pageNo + move},	function(res){
					
						vm.datas.myTransactionsDoneInfo.clear();
						vm.datas.myTransactionsDoneInfo.pushArray(res.data.myTransactionsDoneInfo);
						vm.datas.page.myTransactionsDonePage = res.data.myTransactionsDonePage;
						//服务器时间，用于倒计时
						vm.datas.newDate = res.data.sysServerTime;
						vm.datas.isBuyFlag.isBuyFlagForDone = isBuyFlag;
				});
			}
		},
		//检查用户是否完善了个人信息
		checkPersonalInfo : function(){
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
		},
		//充值弹窗
		recharge:function(){
			if(!vm.datas.userinfo.payPassword){
				$.jBox.tip("请先到手机端设置安全密码");
				return false;
			}
			var verificationPersonalInfo = vm.checkPersonalInfo();
			if(!verificationPersonalInfo){
				if (vm.datas.userinfo.authenticationMark != "2") {
					$.jBox.tip("请先完善个人信息", 'error');
				}
				if(vm.datas.userinfo.authenticationMark == "2"){
					$.jBox.tip("请等待经纪人审核个人信息", 'error');
				}
				return false;
			}
			$.jBox(
					"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>充值金额：<input class='line-input' type='text' id='operateAmount' name='operateAmount' value=''/></div>" ,
					{
						title: "线下充值", 
						buttons : {"确定":"yes","取消": "no"},
						submit: function (v, h, f){
							if(v == "yes"){
								var operateAmount = $("#operateAmount").val();
								if(operateAmount =="" || operateAmount == "0"){
									$.jBox.tip("充值金额不能为空或0", 'error');
									return false;
								}
								var reg = /^[\d]{1,9}$/;//只能输入数字
								if(!reg.test(operateAmount)){
									$.jBox.tip("充值金额只能是数字，最多9位", 'error');
									return false;
								}
								
								$.post(ctx+"/financialManagement/rechargeOrWithdrawals", {operate:"充值",operateAmount:operateAmount}, function(res){
									$.jBox.tip(res.msg);
									return true;
								});
								
							}else if(v == "no"){
								return true;
							}
						}
					}
			);
		},
		//我的交易：付款弹窗
		openPaymentDialog:function(index){
			if(!vm.datas.userinfo.payPassword){
				$.jBox.tip("请先到手机端设置安全密码");
				return false;
			}
			var verificationPersonalInfo = vm.checkPersonalInfo();
			if(!verificationPersonalInfo){
				if (vm.datas.userinfo.authenticationMark != "2") {
					$.jBox.tip("请先完善个人信息", 'error');
				}
				if(vm.datas.userinfo.authenticationMark == "2"){
					$.jBox.tip("请等待经纪人审核个人信息", 'error');
				}
				return false;
			}
			if((vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance) < vm.datas.myTransactionsWaitDealInfo[index].dealPrice){
				$.jBox.tip("可用余额不足，请先充值");
				vm.recharge();
				return false;
			}
			if(vm.datas.myTransactionsWaitDealInfo[index].domainnameStatus != "11"){
				return false;
			}
			$.jBox(
					"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>付款金额：<input class='line-input' type='text' id='payMoney' name='payMoney' value='" + vm.datas.myTransactionsWaitDealInfo[index].dealPrice +"元' readonly/></div>" 
					+ "<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>付款域名：<input class='line-input' type='text' id='domainname' name='domainname' value='" + vm.datas.myTransactionsWaitDealInfo[index].domainname + "' readonly/></div>",
					{
						title: "域名付款", 
						buttons : {"确定":"yes","取消": "no"},
						submit: function (v, h, f){
							if(v == "yes"){
								var fatherDialog = $(h).closest(".jbox-body").attr("id");
								$.jBox(
										"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>付款金额：<input class='line-input' type='text' id='payMoney' name='payMoney' value='" + vm.datas.myTransactionsWaitDealInfo[index].dealPrice +"' readonly/></div>"
										+ "<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>安全密码：<input class='line-input' type='password' id='payPassword' name='payPassword' value=''/></div>" ,
										{
											title: "安全密码", 
											buttons : {"确定":"yes","取消": "no"},
											submit: function (v, h, f){
												if(v == "yes"){
													var payPassword = $("#payPassword").val();
													if(payPassword == ""){
														$.jBox.tip("安全密码不能为空", 'error');
														return false;
													}
													var reg = /^[\d]{6}$/;//只能输入6位数字
													if(!reg.test(payPassword)){
														$.jBox.tip("安全密码只能6位数字", 'error');
														return false;
													}
													$.ajax({
														 type : 'POST',
														 url : ctx + '/myTransactions/payment',
														 data : {payMoney : vm.datas.myTransactionsWaitDealInfo[index].dealPrice,domainnameId:vm.datas.myTransactionsWaitDealInfo[index].domainnameId, payPassword : payPassword},
														 dataType : "json",
														 success :function(res, textStatus){
															$.jBox.tip(res.msg);
															if(res.type == "warn"){
																return false;
															}
															if(res.type == "success"){
																vm.datas.myTransactionsWaitDealInfo[index].domainnameStatus = "12";
																vm.datas.myTransactionsWaitDealInfo[index].waitTime = res.data.waitTime;
																$.jBox.close(fatherDialog);
																return true;
															}
															return true;
														 },
														 error : function(){
															 $.jBox.tip("系统错误，稍后再试");
															 $.jBox.close(fatherDialog);
															 return true;
														 }
														});
												}else if(v == "no"){
													return true;
												}
											}
										}
									);
							}else if(v == "no"){
								return true;
							}
						}
					}
			);
		},
		//买家确认收到域名
		receiveDomamainname:function(index){
			if(vm.datas.myTransactionsWaitDealInfo[index].domainnameStatus != "13"){
				return false;
			}
			$.jBox(
					"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>确认收到卖家域名了吗？确认收到将不可更改，谨慎操作！</div>" ,
					{
						title: "确认收到域名", 
						buttons : {"确定":"yes","取消": "no"},
						submit: function (v, h, f){
							if(v == "yes"){
								$.post(ctx + "/myTransactions/receiveDomamainname",{bidAmount:vm.datas.myTransactionsWaitDealInfo[index].dealPrice,domainnameId:vm.datas.myTransactionsWaitDealInfo[index].domainnameId},function(res){
									$.jBox.tip(res.msg);
									if(res.type=="success"){
										vm.datas.myTransactionsWaitDealInfo[index].domainnameStatus = "14";
										vm.datas.myTransactionsWaitDealInfo[index].waitTime=res.data.waitTime;
										return true;
									}
									return false;
								});
							}else if(v == "no"){
								return true;
							}
						}
					}
			);
		},
		//卖家确认转移域名
		confirmTransferDomainname:function(index){
			if(vm.datas.myTransactionsWaitDealInfo[index].domainnameStatus != "12"){
				return false;
			}
			$.jBox(
					"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>确认已经转移域名给买家了吗？虚假操作，责任自负，谨慎操作！</div>" ,
					{
						title: "确认转移域名", 
						buttons : {"确定":"yes","取消": "no"},
						submit: function (v, h, f){
							if(v == "yes"){
								$.post(ctx + "/myTransactions/transferDomainname",{domainnameId:vm.datas.myTransactionsWaitDealInfo[index].domainnameId,bidAmount:vm.datas.myTransactionsWaitDealInfo[index].dealPrice},function(res){
									$.jBox.tip(res.msg);
									if(res.type=="success"){
										vm.datas.myTransactionsWaitDealInfo[index].domainnameStatus = "13";
										vm.datas.myTransactionsWaitDealInfo[index].waitTime=res.data.waitTime;
										return true;				
									}
									return false;
								});
							}else if(v == "no"){
								return true;
							}
						}
					}
			);
		},
		//计算倒计时
		getCountDown : function(endTime) {
			var restTime = new Date(endTime).getTime() - vm.datas.newDate;
			if (restTime <= 0) {
				time = {
						hours : '00',
						mins : '00',
						secs : '00'
				}
				return {
					time : time,
					displayTime:"00时00秒00分"
				}
			}
			return utils.millisecondToDate(restTime);
		}
	});
	//每一秒钟给newDate赋值一次，显示倒计时
	setInterval(function() {
		vm.datas.newDate = vm.datas.newDate + 1000;
	}, 1000);
	
	
	// 初始化动作
	$(function(){
		avalon.scan();
		
		$("#isBuyFlagForDoing").change(function(){
			var isBuyFlag =$("#isBuyFlagForDoing").val();
			$.get(ctx+"/myTransactions/myTransactionsDoing.json", {isBuyFlag : isBuyFlag,pageIndex : 1},	function(res){
				
				vm.datas.myTransactionsDoingInfo.clear();
				vm.datas.myTransactionsDoingInfo.pushArray(res.data.myTransactionsDoingInfo);
				vm.datas.page.myTransactionsDoingPage = res.data.myTransactionsDoingPage;
				//服务器时间，用于倒计时
				vm.datas.newDate = res.data.sysServerTime;
				vm.datas.isBuyFlag.isBuyFlagForDoing = isBuyFlag;
			
			});
		});
		$("#isBuyFlagForWaitDeal").change(function(){
			var isBuyFlag =$("#isBuyFlagForWaitDeal").val();
			$.get(ctx+"/myTransactions/myTransactionsWaitDeal.json", {isBuyFlag : isBuyFlag,pageIndex : 1},	function(res){
				//if(res.type == "success"){
					vm.datas.myTransactionsWaitDealInfo.clear();
					vm.datas.myTransactionsWaitDealInfo.pushArray(res.data.myTransactionsWaitDealInfo);
					vm.datas.page.myTransactionsWaitDealPage = res.data.myTransactionsWaitDealPage;
					//服务器时间，用于倒计时
					vm.datas.newDate = res.data.sysServerTime;
					vm.datas.isBuyFlag.isBuyFlagForWaitDeal = isBuyFlag;
				//}
			});
		});
		$("#isBuyFlagForDone").change(function(){
			var isBuyFlag =$("#isBuyFlagForDone").val();
			$.get(ctx+"/myTransactions/myTransactionsDone.json", {isBuyFlag : isBuyFlag,pageIndex : 1},	function(res){
				
				vm.datas.myTransactionsDoneInfo.clear();
				vm.datas.myTransactionsDoneInfo.pushArray(res.data.myTransactionsDoneInfo);
				vm.datas.page.myTransactionsDonePage = res.data.myTransactionsDonePage;
				//服务器时间，用于倒计时
				vm.datas.newDate = res.data.sysServerTime;
				vm.datas.isBuyFlag.isBuyFlagForDone = isBuyFlag;
			
			});
		});
		
		$.get(ctx+"/myTransactions/myTransactionsDoing.json", {isBuyFlag : "buy",pageIndex :1}, function(res) {
			//拍卖中信息
			vm.datas.myTransactionsDoingInfo.clear();
			vm.datas.myTransactionsDoingInfo.pushArray(res.data.myTransactionsDoingInfo);
			vm.datas.page.myTransactionsDoingPage = res.data.myTransactionsDoingPage;
			//服务器时间，用于倒计时
			vm.datas.newDate = res.data.sysServerTime;
			vm.datas.isBuyFlag.isBuyFlagForDoing = "buy";
		});
		$.get(ctx+"/financialManagement/refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
		});
		$.get(ctx +"/financialManagement/userInfo.json", {}, function(res) {
			//用户信息
			avalon.mix(vm.datas.userinfo, res.data.userinfo);
		});
	});
});
