
/**
 * 首页
 */
 
pageData = window.pageData?window.pageData:{domainList:[]};
pageData.domainList = pageData.domainList?pageData.domainList:[];

require(["utils"], function(utils) {
	var vm = avalon.define({
		$id : "isell",
		datas : {
			sellerDeposit : 0,//当前平台卖家提交域名时应交的保证金
			//财务信息
			financeinfo:{
				accountBalance:0,
				freezeBalance:0,
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
			//记录轮询的时间戳，与后台相等时不更新操作
			timeStamp : "0",
			domainList : pageData.domainList,
			count : pageData.count,
			pageIndex : 1,
			tmp : {
				currentClientId : null,
				newDate : new Date()
			}
		},
		//提交域名
		submitDomain: function(index){
			$.post(ctx+"/isell/save", {id:vm.datas.domainList[index].id}, function(res){
				$.jBox.tip(res.msg);
				if(res.type == "success"){
					vm.datas.domainList[index].status = "01";
				}
			});
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
		//待充值域名，充值弹窗
		recharge:function(index){
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
			var money = vm.datas.domainList[index].bonusShareTotal + vm.datas.sellerDeposit - (vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance);
			var imageSrc = ctx+"/static/images/bank.PNG"
			var bankInfo = "<div style='margin-top:15px;'><div>拍域名银行信息</div><div> <img src='"+imageSrc+"'></div><div>开户行：招商银行汉中门支行</div><div>开户名：南京登羽信息科技有限公司</div>	<div>银行账号：125905117710811</div><div> 注：请在打款备注里留下您的米友号。</div></div>"
			$.jBox(
					"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>充值金额：<input class='line-input' type='text' id='operateAmount' name='operateAmount' value='"+money+"'readonly/>"+bankInfo+"</div>" ,
					{
						title: "线下充值", 
						buttons : {"确定":"yes","取消": "no"},
						submit: function (v, h, f){
							if(v == "yes"){
								var operateAmount = $("#operateAmount").val();
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
		},
		deleteDomain: function (e, id, name) {
			e.preventDefault();
			$.jBox.confirm("是否确认要删除该域名("+name+")？",'系统提示',function(v,h,f){
				if(v=='ok'){
					$.ajax({
						url : ctx + "/isell/delete.json",
						type: "POST",
						data: {
							id : id
						},
						success: function (resp) {
							if (resp.type == "success") {
								$.jBox.tip("删除成功",'success',{opacity:0});
							} else {
								$.jBox.error(resp.msg, '错误', {border:5});
							}
							
							refresh()
						}
					})
				}
			});
		},
		addRedPack: function (e, id, name, second) {
			e.preventDefault();
			$.jBox("<div class='form-search' style='padding:20px 20px 0px;text-align:center;'>" +
					"追加金额：<input class='line-input' type='text' id='txt' name='txt'/>元" +
					"</div>" +
					"<div class='form-search' style='padding:20px;text-align:center;'>" +
					"安全密码：<input class='line-input' type='password' id='password' name='password'/>　" +
					"</div>", {
				title: "追加次高价红包(已有"+second+"元)", submit: function (v, h, f){
			    if (f.txt == '') {
			        top.$.jBox.tip("请输入金额。", 'error');
			        return false;
			    }
			    if (!/^\d+$/.test(f.txt)) {
			        top.$.jBox.tip("请输入整数。", 'error');
			        return false;
			    }
				
			    $.ajax({
			    	url: ctx + "/isell/addRedPackInfo.json",
			    	type: "POST",
			    	data: {
			    		domainId: id,
			    		bonusSecond: f.txt,
			    		bonusShareTotal: 0,
			    		bonusShareNumber: 0,
			    		payPassword: f.password
			    	},
			    	success: function (resp) {
			    		if (resp.type == "success") {
							$.jBox.tip(resp.msg,'success',{opacity:0});
						} else {
							$.jBox.error(resp.msg, '错误', {border:5});
						}
						
						refresh()
			    	}
			    });
			    
			}});
		}
	});

	function refresh() {
		$.ajax({
			url : ctx + "/isell/domainList.json",
			success: function (resp) {
				if (resp.type == "success") {
					vm.datas.domainList.clear();
					vm.datas.domainList.pushArray(resp.data.domainList);
				} else {
					
				}
			}
		});
		$.get(ctx+"/financialManagement/refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
		});
	}
	//refresh();
	
	// 初始化动作
	$(function(){
		avalon.scan();
		vm.datas.sellerDeposit = sellerDeposit;//卖家保证金
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