
define("isell", function (){
	var vm = avalon.define({
		$id : "isell",
		datas : {
			rechargeAmount:0,
			sellerDeposit : 0,//当前平台卖家提交域名时应交的保证金
			domainList : [],
			userinfo : {},
			//财务信息
			financeinfo:{
				accountBalance:0,
				freezeBalance:0,
			}
		},
		setup : function (id,index) {
			if(vm.datas.domainList[index].status != "00"){
				//00状态需要充值，不跳转
				$.m.changePage("#isell-setting?domainnameId="+id);
			}else{
				if(vm.datas.userinfo.avoidDeposit == '1'){
					if((vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance) >= (vm.datas.domainList[index].bonusShareTotal)){
						$.m.changePage("#isell-setting?domainnameId="+id+"&from=changeDomainname");
					}
				}else{
					if((vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance) >= (vm.datas.sellerDeposit + vm.datas.domainList[index].bonusShareTotal)){
						$.m.changePage("#isell-setting?domainnameId="+id+"&from=changeDomainname");
					}
				}
			}
		},
		linkToIsellSettingPage:function(){
			$.m.changePage("#isell-setting");
		},
		linkToAddRedPack:function(domainnameId){
			$.m.changePage("#addRedPack?domainnameId="+domainnameId);
		},
		openConfirmDeleteAllDialog:function(){
			var size = 0;
			$.each(vm.datas.domainList,function(index,val){
				if(val.status == "02"){
					size = size + 1;
				}
			});
			if(size > 0){
				$("#deleteAllDialog").show();
			}else{
				$.tips({
					content : "没有审核失败的域名",
					stayTime: 2000,
					type : "warn"
				});
			}
		},
		cancelDeleteAll:function(){
			$("#deleteAllDialog").hide();
		},
		comfirmDeleteAll:function(){
			$.post("isell/deleteDomain", {}, function(res){
				$("#deleteAllDialog").hide();
				$.tips({
					content : res.msg,
					stayTime: 2000,
					type : res.type
				});
				if(res.type == "success"){
					//删除成功则清除审核失败的信息
					$.each(vm.datas.domainList,function(index,val){
						if(val.status == "02"){
							vm.datas.domainList.splice(index,1);
						}
					});
				}
			});
		},
		//提交域名
		isellsubmitDomain:function(id,index){
			committing.show();
			$.post("isell/isellSubmitDomain", {domainnameId:id},function(res) {
				if(res.type == "success"){
					// 支付成功后的回调函数
					$.get("refreshFinanceinfo.json", {}, function(res) {
						//财务信息
						avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
					});
					vm.datas.domainList[index].status = "01";
					committing.hide();
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					});
				}
				if(res.type == "warn"){
					committing.hide();
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					}).on("tips:hide",function(){
						vm.recharge(index);
					});
				}
			});
		},
		recharge:function(index){
			if(vm.datas.userinfo.avoidDeposit == '1'){
				vm.datas.rechargeAmount = (vm.datas.domainList[index].bonusShareTotal) - (vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance);
			}else{
				vm.datas.rechargeAmount = (vm.datas.sellerDeposit + vm.datas.domainList[index].bonusShareTotal) - (vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance);
			}
			$("#rechargeForIsell").dialog("show");
		},
		//提交充值
		confirmRecharge:function(){
			//微信支付
			$.ajax({
				type : 'POST',
				url : 'wxPay',
				data : "operate=充值" + "&operateAmount=" + vm.datas.rechargeAmount+"&payFrom=isell",
				dataType : "json",
				success :function(res, textStatus){
					if(res.type == "error"){
						$.tips({
							content : "充值失败",
							stayTime:2000,
							type : "success"
						});
						return false;
					}
					$("#rechargeForIsell").dialog("hide");
					vm.datas.rechargeAmount = 0;
					//alert("res: " + JSON.stringify(res));
					wx.chooseWXPay({
						timestamp: res.data.timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
						nonceStr: res.data.nonceStr, // 支付签名随机串，不长于 32 位
						'package': res.data['package'], // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
						signType: res.data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
						paySign: res.data.paySign, // 支付签名
						success: function (res) {
							// 支付成功后的回调函数
							$.get("refreshFinanceinfo.json", {}, function(res) {
								//财务信息
								avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
							});
							$.tips({
								content : "充值成功,重新点击提交",
								stayTime:2000,
								type : "success"
							}).on("tips:hide",function(){
								$.m.changePage("#isell-setting?domainnameId="+id+"&from=changeDomainname");
							});
						},
						fail:function(res){
							$.tips({
								content : "微信充值接口调用失败",
								stayTime:2000,
								type : "success"
							}).on("tips:hide",function(){
								$.m.changePage("#isell");
							});
						}
					 });
				 },
				 error : function(){
					vm.datas.rechargeAmount = 0;
					 $.tips({
						 content :"微信充值失败，请重新操作或联系经纪人",
						 stayTime:2000,
						 type : "error"
					}).on("tips:hide",function(){
						$.m.changePage("#isell");
					});
					$("#rechargeForSubDomain").dialog("hide");
				 }
			});
		}
	});
	
	var firstTime = true;
	$("#isell").on("pageshow", function(){
		$.get("refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
		});
		$.get("userInfo.json", {}, function(res) {
			//用户信息
			avalon.mix(vm.datas.userinfo, res.data.userinfo);
			if(!vm.datas.userinfo.mobile){
				$.tips({
					content : "请先设置手机号",
					stayTime: 2000,
					type : "success"
				}).on("tips:hide",function(){
					window.location.href = ctx + "/domainname/changePhone.html?fromIsell=true";
				});
			}else{
				$.get("isell/domainList.json", {}, function(res){
					if (res.type == "success") {
						if (firstTime && res.data.domainList.length == 0) {
							firstTime = false;
							$.m.changePage("#isell-setting");
						} else {
							vm.datas.domainList.clear();
							vm.datas.domainList.pushArray(res.data.domainList);
						}
					} else {
						$.tips({
			                content : res.msg,
			                stayTime: 2000,
			                type : res.type
			            });
					}
				});
			}
		});
	});
	
	 $("#isell").on("pageloaded", function() {	
		vm.datas.sellerDeposit = sellerDeposit;//卖家保证金
    	// 获取jsticket
		$.get("jsapiTicket.json", {href:window.location.href},function(res) {
			wx.config({
				debug : false,
				appId : res.data.appId,
				timestamp : res.data.timestamp,
				nonceStr : res.data.nonceStr,
				signature : res.data.signature, // 签名
				jsApiList : ['chooseImage', 'uploadImage','chooseWXPay']
			});
			
			wx.ready(function(){
				
			});
			
			wx.error(function(res){
				alert("页面错误，请退出重试");
			});
		});
	});
	 committing = $.loading({content:'提交中...'}).hide();
	return vm;
})