
require(["utils"], function(utils) {
	
	var vm = avalon.define({
		$id : "finance",
		datas : {
			page : {
				cashFlowPage : {
				
				},
				withdrawalsPage : {
					
				},
				freezePage :{
					
				}
			},
			//用户信息
			userinfo : {
				id : "",
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
				payPassword: ""
			},
			//财务信息
			financeinfo:{
				id:"",
				accountBalance:"",
				freezeBalance:"",
			},
			//资金流信息
			cashflowinfo:[],
			//提现信息
			withdrawalsinfo : [],
			//冻结信息
			freezeinfo : []
		},
		//跳转至财务信息页面
		goToFinanceInfo : function(){
			//window.location.href = ctx + "/financialManagement/financeInfo.html";
			$.get(ctx+"/financialManagement/refreshFinanceinfo.json", {}, function(res) {
				//财务信息
				avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
			});
			
			$(this).addClass("selected");
			$("#freezeRecordLi").removeClass("selected");
			$("#cashFlowLi").removeClass("selected");
			$("#withdrawalsProgressLi").removeClass("selected");
			$("#bankInfoLi").removeClass("selected");
			
			$("#cashFlowRecord").addClass("hidden");
			$("#bankInfo").addClass("hidden");
			$("#withdrawalsProgress").addClass("hidden");
			$("#freezeRecord").addClass("hidden");
			$("#financeInfo").removeClass("hidden");
		},
		//跳转至冻结资金记录页面
		goToFreezeRecord:function(){
			$.get(ctx+"/financialManagement/freezeinfo.json", {pageIndex : 1},	function(res){
				if(res.type == "success"){
					vm.datas.freezeinfo.clear();
					vm.datas.freezeinfo.pushArray(res.data.freezeinfo);
					vm.datas.page.freezePage = res.data.freezePage;
				}
			});
			
			$(this).addClass("selected");
			$("#financeInfoLi").removeClass("selected");
			$("#cashFlowLi").removeClass("selected");
			$("#withdrawalsProgressLi").removeClass("selected");
			$("#bankInfoLi").removeClass("selected");
			
			$("#cashFlowRecord").addClass("hidden");
			$("#bankInfo").addClass("hidden");
			$("#withdrawalsProgress").addClass("hidden");
			$("#financeInfo").addClass("hidden");
			$("#freezeRecord").removeClass("hidden");
		},
		//跳转至资金流页面
		linkToCashFlow:function(){
			$.get(ctx+"/financialManagement/cashFlowInfo.json", {pageIndex : 1},	function(res){
				if(res.type == "success"){
					vm.datas.cashflowinfo.clear();
					vm.datas.cashflowinfo.pushArray(res.data.cashflowinfo);
					vm.datas.page.cashFlowPage = res.data.cashFlowPage;
				}
			});
			$(this).addClass("selected");
			$("#financeInfoLi").removeClass("selected");
			$("#freezeRecordLi").removeClass("selected");
			$("#withdrawalsProgressLi").removeClass("selected");
			$("#bankInfoLi").removeClass("selected");
			
			$("#bankInfo").addClass("hidden");
			$("#withdrawalsProgress").addClass("hidden");
			$("#financeInfo").addClass("hidden");
			$("#freezeRecord").addClass("hidden");
			$("#cashFlowRecord").removeClass("hidden");
		},
		//跳转至银行信息页面
		linkToBankInfo:function(){
			$(this).addClass("selected");
			$("#financeInfoLi").removeClass("selected");
			$("#freezeRecordLi").removeClass("selected");
			$("#withdrawalsProgressLi").removeClass("selected");
			$("#cashFlowLi").removeClass("selected");
			
			$("#withdrawalsProgress").addClass("hidden");
			$("#financeInfo").addClass("hidden");
			$("#freezeRecord").addClass("hidden");
			$("#cashFlowRecord").addClass("hidden");
			$("#bankInfo").removeClass("hidden");
		},
		//跳转至提现进度页面
		linkToWithdrawalsProgress:function(){
			$.get(ctx+"/financialManagement/withdrawalsInfo.json", {pageIndex : 1}, function(res) {
				if(res.type == "success"){
					vm.datas.withdrawalsinfo.clear();
					vm.datas.withdrawalsinfo.pushArray(res.data.withdrawalsinfo);
					vm.datas.page.withdrawalsPage = res.data.withdrawalsPage;
				}
			});
			
			$(this).addClass("selected");
			$("#financeInfoLi").removeClass("selected");
			$("#freezeRecordLi").removeClass("selected");
			$("#bankInfoLi").removeClass("selected");
			$("#cashFlowLi").removeClass("selected");
			
			$("#financeInfo").addClass("hidden");
			$("#freezeRecord").addClass("hidden");
			$("#cashFlowRecord").addClass("hidden");
			$("#bankInfo").addClass("hidden");
			$("#withdrawalsProgress").removeClass("hidden");
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
			vm.refresh(from,1);
		},
		refresh: function (from,move) {
			if(from == "cashflowinfo"){
				$.get(ctx+"/financialManagement/cashFlowInfo.json", {pageIndex : vm.datas.page.cashFlowPage.pageNo + move},	function(res){
					if(res.type == "success"){
						vm.datas.cashflowinfo.clear();
						vm.datas.cashflowinfo.pushArray(res.data.cashflowinfo);
						vm.datas.page.cashFlowPage = res.data.cashFlowPage;
					}
				});
			}
			if(from == "withdrawalsinfo"){
				$.get(ctx+"/financialManagement/withdrawalsInfo.json", {pageIndex : vm.datas.page.withdrawalsPage.pageNo + move},	function(res){
					if(res.type == "success"){
						vm.datas.withdrawalsinfo.clear();
						vm.datas.withdrawalsinfo.pushArray(res.data.withdrawalsinfo);
						vm.datas.page.withdrawalsPage = res.data.withdrawalsPage;
					}
				});
			}
			
			if(from == "freezeinfo"){
				$.get(ctx+"/financialManagement/freezeinfo.json", {pageIndex : vm.datas.page.freezePage.pageNo + move},	function(res){
					if(res.type == "success"){
						vm.datas.freezeinfo.clear();
						vm.datas.freezeinfo.pushArray(res.data.freezeinfo);
						vm.datas.page.freezePage = res.data.freezePage;
					}
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
		// 财务管理：认证默认收支方式
		addDefaultIncomeExpense:function(e){
			if(!vm.datas.userinfo.payPassword){
				$.jBox.tip("请先到手机端设置安全密码", 'error');
				return false
			}
			$.jBox(
					"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>银行卡号：<input class='line-input' type='text' id='defaultIncomeExpense' name='defaultIncomeExpense' value=''/></div><div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>安全密码：<input class='line-input' type='password' id='payPassword' name='payPassword' value=''/></div>" ,
					{
						title: "添加/修改银行账号", 
						buttons : {"确定":"yes","取消": "no"},
						submit: function (v, h, f){
							if(v == "yes"){
								var defaultIncomeExpense = $("#defaultIncomeExpense").val();
								var payPassword = $("#payPassword").val();
								if(defaultIncomeExpense ==""){
									$.jBox.tip("卡号不能为空", 'error');
									return false;
								}
								if(defaultIncomeExpense == vm.datas.userinfo.defaultIncomeExpense){
									$.jBox.tip("不能与原来相同", 'error');
									return false;
								}
								var reg = /^[\d]{15,22}$/;//只能输入银行卡号格式
								if(!reg.test(defaultIncomeExpense)){
									$.jBox.tip("只能是银行卡号格式", 'error');
									return false;
								}
								if(payPassword == ""){
									$.jBox.tip("安全密码不能为空", 'error');
									return false;
								}
								var reg = /^[\d]{6}$/;//只能输入6位数字
								if(!reg.test(payPassword)){
									$.jBox.tip("安全密码只能6位数字", 'error');
									return false;
								}
								$.post(ctx+"/financialManagement/changeDefaultIncomeExpense", {defaultIncomeExpense:defaultIncomeExpense,payPassword : payPassword}, function(res){
									$.jBox.tip(res.msg);
									if(res.type == "success"){
										vm.datas.userinfo.defaultIncomeExpense = defaultIncomeExpense;
									}
								});
							}else if(v == "no"){
								return true;
							}
						}
					}
				);
			
		},
		//取消提现
		cancelWithdrawals:function(index){
			if(vm.datas.withdrawalsinfo[index].confirmResult == "处理中"){
				$.jBox.tip("处理中的提现不能取消", 'error');
				return false;
			}
			if(vm.datas.withdrawalsinfo[index].confirmResult == "已取消"){
				$.jBox.tip("已取消", 'error');
				return false;
			}
			
			$.jBox(
					"<div class='form-search' style='text-align:center;'>确定取消此提现?</div>",
					{
						title: "取消提现", 
						buttons : {"确定":"yes","取消": "no"},
						submit: function (v, h, f){
							if(v == "yes"){
								$.ajax({
									 type : 'POST',
									 url : ctx+'/financialManagement/cancelWithdrawal',
									 data : {id:vm.datas.withdrawalsinfo[index].id,money:vm.datas.withdrawalsinfo[index].operateAmount},
									 dataType : "json",
									 success :function(res, textStatus){
										$.jBox.tip(res.msg);
										if(res.type=="success"){
											vm.datas.withdrawalsinfo[index].confirmResult = "已取消";
										}
									 },
									 error : function(){
										 $.jBox.tip("取消失败，请重新操作");
									 }
								});
							}else if(v == "no"){
								return true;
							}
						}
					}
			);
		},
		//财务管理：提现弹窗
		withdrawals:function(e){
			if(!vm.datas.userinfo.payPassword){
				$.jBox.tip("请先到手机端设置安全密码", 'error');
				return false
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
					"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>提现金额：<input class='line-input' type='text' id='withdrawalsAmount' name='withdrawalsAmount' value=''/></div><div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>安全密码：<input class='line-input' type='password' id='payPassword' name='payPassword' value=''/></div>" ,
					{
						title: "提现", 
						buttons : {"确定":"yes","取消": "no"},
						submit: function (v, h, f){
							if(v == "yes"){
								var withdrawalsAmount = $("#withdrawalsAmount").val();
								var payPassword = $("#payPassword").val();
								if((vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance) < parseInt(withdrawalsAmount)){
									$.jBox.tip("可用余额不足", 'error');
									return false;
								}
								if(withdrawalsAmount =="" || withdrawalsAmount == "0"){
									$.jBox.tip("提现金额不能为空或0", 'error');
									return false;
								}
								var reg = /^[\d]{1,9}$/;//只能输入数字
								if(!reg.test(withdrawalsAmount)){
									$.jBox.tip("提现金额只能是数字，最多9位", 'error');
									return false;
								}
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
									 url : ctx+'/financialManagement/rechargeOrWithdrawals',
									 data : {operate : "提现",operateAmount : withdrawalsAmount,payPassword : payPassword},
									 dataType : "json",
									 success :function(res, textStatus){
										if(res.type=="success"){
											$.jBox.tip(res.msg);
											withdrawalsFlag = true;
										}else{
											$.jBox.tip(res.msg);
											withdrawalsFlag = false;
										}
									 },
									 error : function(){
										 $.jBox.tip("系统错误，稍后重试");
										 withdrawalsFlag =  true;
									 }
								});
							}else if(v == "no"){
								return true;
							}
						}
					}
				);
		},
		//财务管理：充值弹窗
		recharge:function(){
			if(!vm.datas.userinfo.payPassword){
				$.jBox.tip("请先到手机端设置安全密码", 'error');
				return false
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
			var imageSrc = ctx+"/static/images/bank.PNG"
			var bankInfo = "<div style='margin-top:15px;'><div>拍域名银行信息</div><div> <img src='"+imageSrc+"'></div><div>开户行：招商银行汉中门支行</div><div>开户名：南京登羽信息科技有限公司</div>	<div>银行账号：125905117710811</div><div> 注：请在打款备注里留下您的米友号。</div></div>"
			$.jBox(
					"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>充值金额：<input class='line-input' type='text' id='operateAmount' name='operateAmount' value=''/>"+bankInfo+"</div>" ,
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
									if(res.type == "success"){
										$.jBox.info(res.msg+"，请尽快完成线下充值！");
									}else{
										$.jBox.info(res.msg);
									}
									return true;
								});
								
							}else if(v == "no"){
								return true;
							}
						}
					}
			);
		}
	});
	
	// 初始化动作
	$(function(){
		avalon.scan();
		$.get(ctx+"/financialManagement/userInfo.json", {}, function(res) {
			//用户信息
			avalon.mix(vm.datas.userinfo, res.data.userinfo);
		});
		$.get(ctx+"/financialManagement/refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
		});
	});
});
