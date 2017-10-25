
define("myTransactionsInfo", function() {
	
	var vm = avalon.define({
		$id : "myTransactionsInfo",
		datas : {
			userinfo:{},
			//财务信息
			financeinfo:{
				accountBalance:"",
				freezeBalance:""
			},
			//可用余额
			available_balance:"",			
			//我的交易：已完成--付款信息
			payment:{
				payMoney:"",
				domainname:"",
				domainnameId:"",
				payPassword:""
			},
			//存储对应下标
			index:"",
			//我的交易信息
			myTransactions:{
				//获取我的交易：参与中的交易域名信息(参与买)
				myTransactionsBuyingList:[],
				//获取我的交易：参与中的交易域名信息(卖)
				myTransactionsSellingList:[],
				//正在参与记录数
				myTransactionsDoingSize:0,
				//获取我的交易：已完成交易的域名信息(参与买)
				myTransactionsBoughtList:[],
				//获取我的交易：已完成交易的域名信息(卖)
				myTransactionsSoldList:[],
				//待处理记录数
				myTransactionsDoneSize:0,
				//待处理：需要操作的记录数
				waitToDealSize:0,
				//已完成买
				myTransactionsDoneBoughtList:[],
				//已完成卖
				myTransactionsDoneSoldList:[],
				//获取我的交易：已完成交易的异常状态域名信息(买)
				myTransactionsBoughtExceptionList:[],
				//获取我的交易：已完成交易的异常状态域名信息(卖)
				myTransactionsSoldExceptionList:[],
				//已完成记录数
				doneSize:0
			},
			//倒计时：当前时间
			newDate:new Date().getTime(),
			//存储对话框跳转单域名页面的域名id
			domainnameId:"",
			//资金信息:用于充值
			cashflowinfo:{
				rechargeAmount:"",
				operate:""
			}
		},
		//检查用户是否完善了个人信息
		checkPersonalInfo : function(){
			try {
				if(!vm.datas.userinfo.name){
					return false;
				}
				// 2016-02-15 个人信息认证不在需要“邮件”和“qq”
				//if(vm.datas.userinfo.emailFlag != "1" || !vm.datas.userinfo.email ){
				//	return false;
				//}
				if(!vm.datas.userinfo.mobile){
					return false;
				}
				if(//!vm.datas.userinfo.qq && 
					!vm.datas.userinfo.wx){
					return false;
				}
				if(vm.datas.userinfo.authenticationMark != "1" || !vm.datas.userinfo.idcardNumber){
					return false;
				}
				if(!vm.datas.userinfo.defaultIncomeExpense || !vm.datas.userinfo.bankName || !vm.datas.userinfo.bankLocation){
					return false;
				}
				return true;
			} catch(e) {
				alert(e);
				return false;
			}
		},
		//我的交易付款余额不足：充值弹窗
		recharge:function(){
			$("#rechargeForPay").dialog("show");
			$("#rechargeAmount").focus();
		},
		clearRechargeAmountInput:function(){
			vm.datas.cashflowinfo.rechargeAmount="";
			document.getElementById("rechargeAmount").focus();
		},
		//提交充值
		confirmRecharge:function(){
			vm.datas.cashflowinfo.operate="充值";
			if($("#rechargeAmount").val()==""){
				$.tips({
					content : "不能为空",
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
					if(document.getElementById("radio1").checked){
						committing.show();
						$.post("rechargeOrWithdrawals", {operate:vm.datas.cashflowinfo.operate,operateAmount:vm.datas.cashflowinfo.rechargeAmount}, function(res){
							if (res.type=="success") {
								$("#rechargeForPay").dialog("hide");
							}
							committing.hide();
							$.tips({
								content : res.msg,
								stayTime:2000,
								type : res.type
							}).on("tips:hide",function(){
								$("#myTransactionsInfo #platform-bank-info").dialog("show");
							});;
						});
					}
					
					//微信支付，待检
					if(document.getElementById("radio2").checked){
						$.ajax({
							type : 'POST',
							url : 'wxPay',
							data : "operate=" + vm.datas.cashflowinfo.operate + "&operateAmount=" + vm.datas.cashflowinfo.rechargeAmount,
							dataType : "json",
							success :function(res, textStatus){
								$("#rechargeForPay").dialog("hide");
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
								 $.tips({
									 content :"微信充值失败，请重新操作或联系经纪人",
									 stayTime:2000,
									 type : "error"
								});
								$("#rechargeForPay").dialog("hide");
							 }
						});
					}
				}
			}
		},
		promptMessage:function(type){
			$.get("refreshMyTransactions.json", {}, function(res) {
				//我的交易
				//我的交易：拍卖中
				vm.datas.myTransactions.myTransactionsBuyingList.clear();
				vm.datas.myTransactions.myTransactionsSellingList.clear();
				vm.datas.myTransactions.myTransactionsBuyingList.pushArray(res.data.myTransactionsBuyingList);
				vm.datas.myTransactions.myTransactionsSellingList.pushArray(res.data.myTransactionsSellingList);
				vm.datas.myTransactions.myTransactionsDoingSize = res.data.myTransactionsDoingSize;
				//我的交易：待处理
				vm.datas.myTransactions.myTransactionsBoughtList.clear();
				vm.datas.myTransactions.myTransactionsSoldList.clear();
				vm.datas.myTransactions.myTransactionsBoughtList.pushArray(res.data.myTransactionsBoughtList);
				vm.datas.myTransactions.myTransactionsSoldList.pushArray(res.data.myTransactionsSoldList);
				vm.datas.myTransactions.myTransactionsDoneSize = res.data.myTransactionsDoneSize;
				vm.datas.myTransactions.waitToDealSize = res.data.waitToDealSize
				//我的交易：已完成
				vm.datas.myTransactions.myTransactionsDoneBoughtList.clear();
				vm.datas.myTransactions.myTransactionsDoneSoldList.clear();
				vm.datas.myTransactions.myTransactionsBoughtExceptionList.clear();
				vm.datas.myTransactions.myTransactionsSoldExceptionList.clear();
				vm.datas.myTransactions.myTransactionsDoneBoughtList.pushArray(res.data.myTransactionsDoneBoughtList);
				vm.datas.myTransactions.myTransactionsDoneSoldList.pushArray(res.data.myTransactionsDoneSoldList);
				vm.datas.myTransactions.myTransactionsBoughtExceptionList.pushArray(res.data.myTransactionsBoughtExceptionList);
				vm.datas.myTransactions.myTransactionsSoldExceptionList.pushArray(res.data.myTransactionsSoldExceptionList);
				vm.datas.myTransactions.doneSize = res.data.doneSize;
			});	
			if(type == 'doing'){
				if(vm.datas.myTransactions.myTransactionsDoingSize == '0'){
					$.tips({
						content : "当前无正在交易的信息",
						stayTime:2000,
						type : "warn"
					});
				}
			}
			if(type == 'waitDeal'){
				if(vm.datas.myTransactions.myTransactionsDoneSize == '0'){
					$.tips({
						content : "当前无待处理交易的信息",
						stayTime:2000,
						type : "warn"
					});
				}
			}
			if(type == 'done'){
				if(vm.datas.myTransactions.doneSize == '0'){
					$.tips({
						content : "当前无已完成交易的信息",
						stayTime:2000,
						type : "warn"
					});
				}
			}
		},
		//跳转至当个域名页面:li点击事件
		linkToSingleDomainname:function(id){
//			var href = "singleDomainname.html?singleDomainId="+id;
//			location.href = href;
			$.m.changePage("#singleDomainname?singleDomainId="+id);
		},
		//跳转至当个域名页面:对话框中的点击链接事件
		linkToSingleDomainnameDetail:function(){
//			var href = "singleDomainname.html?singleDomainId="+vm.datas.domainnameId;
			var id = vm.datas.domainnameId;
			vm.datas.domainnameId = "";
//			location.href = href;
			$.m.changePage("#singleDomainname?singleDomainId="+id);
		},
		//我的交易：关注/取消关注
		addOrCancelAttention:function(type,index,attentionFlag){
			if (type == "myTransactionsSellingList") {
				vm.datas.myTransactions.myTransactionsSellingList[index].attentionFlag = attentionFlag;
				$.post("addOrCancelAttention", {domainnameId:vm.datas.myTransactions.myTransactionsSellingList[index].domainnameId,attentionFlag:vm.datas.myTransactions.myTransactionsSellingList[index].attentionFlag}, function(res){
					if (res.type=="error") {
						if(attentionFlag=="1"){
							vm.datas.myTransactions.myTransactionsSellingList[index].attentionFlag = 0;
							$.tips({
								content : res.msg,
								stayTime:2000,
								type : res.type
							});
						}else{
							vm.datas.myTransactions.myTransactionsSellingList[index].attentionFlag = 1;
							$.tips({
								content : res.msg,
								stayTime:2000,
								type : res.type
							});
						}
					}
				});
			} else {
				vm.datas.myTransactions.myTransactionsBuyingList[index].attentionFlag = attentionFlag;
				$.post("addOrCancelAttention", {domainnameId:vm.datas.myTransactions.myTransactionsBuyingList[index].domainnameId,attentionFlag:vm.datas.myTransactions.myTransactionsBuyingList[index].attentionFlag}, function(res){
					if (res.type=="error") {
						if(attentionFlag=="1"){
							vm.datas.myTransactions.myTransactionsSellingList[index].attentionFlag = 0;
							$.tips({
								content : res.msg,
								stayTime:2000,
								type : res.type
							});
						}else{
							vm.datas.myTransactions.myTransactionsSellingList[index].attentionFlag = 1;
							$.tips({
								content : res.msg,
								stayTime:2000,
								type : res.type
							});
						}
					}
				});
			}
		},
		//我的交易：付款弹窗
		openPaymentDialog:function(index){
			vm.datas.domainnameId = vm.datas.myTransactions.myTransactionsBoughtList[index].domainnameId
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
			vm.datas.index=index;
			if(vm.datas.available_balance<vm.datas.myTransactions.myTransactionsBoughtList[index].bidAmount){
				$.tips({
					content : "可用余额不足，请先充值",
					stayTime:1000,
					type : "warn"
				}).on("tips:hide",function(){
					var flag = vm.checkPersonalInfo();
					if(flag){
						vm.recharge();
					}else{
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
					}
				});
			}else{
				vm.datas.payment.payMoney=vm.datas.myTransactions.myTransactionsBoughtList[index].bidAmount;
				vm.datas.payment.domainname=vm.datas.myTransactions.myTransactionsBoughtList[index].name;
				vm.datas.payment.domainnameId=vm.datas.myTransactions.myTransactionsBoughtList[index].domainnameId;
				$("#paymentDialog").dialog("show");
			}
		},
		//打开输入支付密码对话框
		openVerificationPayPassword:function(){
			vm.datas.domainnameId = "";
			$("#paymentDialog").dialog("hide");
			$("#verificationPayPassword").dialog("show");
			$("#payPassword").focus();
		},
		//清除支付密码输入
		clearPayPasswordInput:function(){
			vm.datas.payment.payPassword = "";
			$("#payPassword").focus();
		},
		//买家确认付款
		confirmPay:function(){
			if(vm.datas.payment.payPassword == ""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			
			var reg = /^[\d]{6}$/;//只能输入6位数字
			if(!reg.test(vm.datas.payment.payPassword)){
				$.tips({
					content : "只能输入6位数字",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if(vm.datas.myTransactions.myTransactionsBoughtList[vm.datas.index].status != "11"){
				return false;
			}
			committing.show();
			$.post("verificationOldPassword", {oldPassword:vm.datas.payment.payPassword}, function(res){
				if(res.type == "success"){
					$.ajax({
						 type : 'POST',
						 url : 'payment',
						 data : "payMoney=" + vm.datas.payment.payMoney + "&domainname=" + vm.datas.payment.domainname+"&domainnameId="+vm.datas.payment.domainnameId,
						 dataType : "json",
						 success :function(res, textStatus){
							vm.datas.payment.payPassword = "";//清空支付密码
							if(res.type=="success"){
								vm.datas.myTransactions.waitToDealSize = vm.datas.myTransactions.waitToDealSize - 1;
								vm.datas.financeinfo.accountBalance=vm.datas.financeinfo.accountBalance-vm.datas.payment.payMoney;
								vm.datas.myTransactions.myTransactionsBoughtList[vm.datas.index].status="12";
								vm.datas.myTransactions.myTransactionsBoughtList[vm.datas.index].waitTime=res.data.waitTime;
							}
							$("#verificationPayPassword").dialog("hide");
							committing.hide();
							$.tips({
								content : res.msg,
								stayTime:2000,
								type : res.type
							});
						 },
						 error : function(){
							 vm.datas.payment.payPassword = "";//清空支付密码
							 $("#paymentDialog").dialog("hide");
							 $("#verificationPayPassword").dialog("hide");
							 committing.hide();
							 $.tips({
								 content :"付款失败，请重新操作",
								 stayTime:2000,
								 type : "error"
							 });
						 }
						});
				}else if(res.type == "warn"){
					committing.hide();
					$("#verificationPayPassword").dialog("show");
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					});
				}else{
					$("#paymentDialog").dialog("hide");
					$("#verificationPayPassword").dialog("hide");
					committing.hide();
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					});
				}
			});
		},
		//打开卖家确认转移域名弹窗
		openConfirmTransferDomainnameDialog:function(index){
			vm.datas.index = index;
			vm.datas.domainnameId = vm.datas.myTransactions.myTransactionsSoldList[index].domainnameId;
			$("#confirmTransferDomainnameDialog").dialog("show");
		},
		//卖家确认转移域名
		confirmTransferDomainname:function(index){
			vm.datas.domainnameId = "";
			if(vm.datas.myTransactions.myTransactionsSoldList[index].status != "12"){
				return false;
			}
			$.post("transferDomainname",{domainnameId:vm.datas.myTransactions.myTransactionsSoldList[index].domainnameId,bidAmount:vm.datas.myTransactions.myTransactionsSoldList[index].bidAmount,domainname:vm.datas.myTransactions.myTransactionsSoldList[index].name},function(res){
				$.tips({
					content : res.msg,
					stayTime:2000,
					type : res.type
				});
				if(res.type=="success"){
					$("#confirmTransferDomainnameDialog").dialog("hide");
					vm.datas.myTransactions.waitToDealSize = vm.datas.myTransactions.waitToDealSize - 1;
					vm.datas.myTransactions.myTransactionsSoldList[index].status="13";
					vm.datas.myTransactions.myTransactionsSoldList[vm.datas.index].waitTime=res.data.waitTime;					
				}
			});
		},
		//买家确认收到域名确认框打开
		openComfirmReceiveDomamainnameDialog:function(index){
			vm.datas.index = index;
			vm.datas.domainnameId = vm.datas.myTransactions.myTransactionsBoughtList[index].domainnameId;
			$("#comfirmReceiveDomamainnameDialog").dialog("show");
		},
		//买家确认收到域名
		receiveDomamainname:function(index){
			vm.datas.domainnameId = "";
			if(vm.datas.myTransactions.myTransactionsBoughtList[index].status != "13"){
				return false;
			}
			$.post("receiveDomamainname",{bidAmount:vm.datas.myTransactions.myTransactionsBoughtList[index].bidAmount,domainnameId:vm.datas.myTransactions.myTransactionsBoughtList[index].domainnameId,domainname:vm.datas.myTransactions.myTransactionsBoughtList[index].name},function(res){
				$.tips({
					content : res.msg,
					stayTime:2000,
					type : res.type
				});
				if(res.type=="success"){
					$("#comfirmReceiveDomamainnameDialog").dialog("hide");
					vm.datas.myTransactions.waitToDealSize = vm.datas.myTransactions.waitToDealSize - 1;
					vm.datas.myTransactions.myTransactionsBoughtList[index].status="14";
					vm.datas.myTransactions.myTransactionsBoughtList[vm.datas.index].waitTime=res.data.waitTime;
				}
			});
		},
		// 计算倒计时
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

//	var tab = new fz.Scroll('#tab1', {
//		role : 'tab',
//		autoplay : false,
//		momentum : false,
//		bounce : false
//		
//	});
//
	var tab1 = new fz.Scroll('#tab2', {
		role : 'tab',
		autoplay : false,
		momentum : false,
		bounce : false
	});	

	// 下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#myTransactionsInfo").on("pageloaded", function(e, d){
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
//		vm.datas.myTransactions.myTransactionsBuyingList.clear();
//		vm.datas.myTransactions.myTransactionsSellingList.clear();
//		vm.datas.myTransactions.myTransactionsBoughtList.clear();
//		vm.datas.myTransactions.myTransactionsSoldList.clear();
//		$.get("refreshMyTransactions.json", {}, function(res) {
//			//我的交易
//			//我的交易：正在参与
//			vm.datas.myTransactions.myTransactionsBuyingList.pushArray(res.data.myTransactionsBuyingList);
//			vm.datas.myTransactions.myTransactionsSellingList.pushArray(res.data.myTransactionsSellingList);
//			vm.datas.myTransactions.myTransactionsDoingSize = res.data.myTransactionsDoingSize;
//			//我的交易：已完成
//			vm.datas.myTransactions.myTransactionsBoughtList.pushArray(res.data.myTransactionsBoughtList);
//			vm.datas.myTransactions.myTransactionsSoldList.pushArray(res.data.myTransactionsSoldList);
//			vm.datas.myTransactions.myTransactionsDoneSize = res.data.myTransactionsDoneSize;
//		});	
//		
//		$.get("refreshFinanceinfo.json", {}, function(res) {
//			//财务信息
//			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
//			vm.datas.available_balance = res.data.available_balance;
//		});	
	});
	// 当前价显示优化
	avalon.filters.transferCurrentAmount = function(amount) {
		return utils.priceDisplay(amount);
	};
	// 从其他页面跳转回来时，如果想做恢复动作可以监听这个事件
	$("#myTransactionsInfo").on("pageshow", function(e, d){
		// 关闭所有打开的对话框
		$(".ui-dialog").dialog("hide");
		$.get("refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
			vm.datas.available_balance = res.data.available_balance;
		});
		
		$.get("userInfo.json", {}, function(res) {
			//用户信息
			//avalon.mix(vm.datas.userinfo, res.data.userinfo);
			vm.datas.userinfo = res.data.userinfo;
		});
		vm.datas.myTransactions.myTransactionsBuyingList.clear();
		vm.datas.myTransactions.myTransactionsSellingList.clear();
		vm.datas.myTransactions.myTransactionsBoughtList.clear();
		vm.datas.myTransactions.myTransactionsSoldList.clear();
		vm.datas.myTransactions.myTransactionsDoneBoughtList.clear();
		vm.datas.myTransactions.myTransactionsDoneSoldList.clear();
		vm.datas.myTransactions.myTransactionsBoughtExceptionList.clear();
		vm.datas.myTransactions.myTransactionsSoldExceptionList.clear();
		$.get("refreshMyTransactions.json", {}, function(res) {
			//服务器时间，用于倒计时
			vm.datas.newDate = res.data.sysServerTime;
			//我的交易
			//我的交易：拍卖中
			vm.datas.myTransactions.myTransactionsBuyingList.clear();
			vm.datas.myTransactions.myTransactionsSellingList.clear();
			vm.datas.myTransactions.myTransactionsBuyingList.pushArray(res.data.myTransactionsBuyingList);
			vm.datas.myTransactions.myTransactionsSellingList.pushArray(res.data.myTransactionsSellingList);
			vm.datas.myTransactions.myTransactionsDoingSize = res.data.myTransactionsDoingSize;
			//我的交易：待处理
			vm.datas.myTransactions.myTransactionsBoughtList.clear();
			vm.datas.myTransactions.myTransactionsSoldList.clear();
			vm.datas.myTransactions.myTransactionsBoughtList.pushArray(res.data.myTransactionsBoughtList);
			vm.datas.myTransactions.myTransactionsSoldList.pushArray(res.data.myTransactionsSoldList);
			vm.datas.myTransactions.myTransactionsDoneSize = res.data.myTransactionsDoneSize;
			vm.datas.myTransactions.waitToDealSize = res.data.waitToDealSize
			//我的交易：已完成
			vm.datas.myTransactions.myTransactionsDoneBoughtList.clear();
			vm.datas.myTransactions.myTransactionsDoneSoldList.clear();
			vm.datas.myTransactions.myTransactionsBoughtExceptionList.clear();
			vm.datas.myTransactions.myTransactionsSoldExceptionList.clear();
			vm.datas.myTransactions.myTransactionsDoneBoughtList.pushArray(res.data.myTransactionsDoneBoughtList);
			vm.datas.myTransactions.myTransactionsDoneSoldList.pushArray(res.data.myTransactionsDoneSoldList);
			vm.datas.myTransactions.myTransactionsBoughtExceptionList.pushArray(res.data.myTransactionsBoughtExceptionList);
			vm.datas.myTransactions.myTransactionsSoldExceptionList.pushArray(res.data.myTransactionsSoldExceptionList);
			vm.datas.myTransactions.doneSize = res.data.doneSize;
			
			if(vm.datas.myTransactions.myTransactionsDoingSize == '0'){
				$.tips({
					content : "当前无正在交易的信息",
					stayTime:2000,
					type : "warn"
				});
			}
		});	
		

	});
	
	committing = $.loading({content:'提交中...'}).hide();
	return vm;
});
