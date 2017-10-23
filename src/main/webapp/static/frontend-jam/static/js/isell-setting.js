
	
	
	/*$('#bonusShareNumber').focus(function(){
		var s = $('#isell-setting')[0];
		s.scrollTop = s.scrollHeight;
	})；*/
	
	
	
	
define( function (){
	var vm = avalon.define({
		$id : "isell-setting",
		datas : {
			userinfo : {},
			//财务信息
			financeinfo:{
				accountBalance:0,
				freezeBalance:0,
			},
			sellerDeposit : 0,//当前平台卖家提交域名时应交的保证金
			rechargeAmount : 0,//提交域名时，可用余额不足，充值的差值 sellerDeposit -（accountBalance - freezeBalance）
			from:"",//标记请求来源，setDomainname标识设置域名，seeDomainname标识查看域名
			ifTransmitWel : false,
			ifRewardSecond : false,
			image_source:"",
			domainInfo : {
			},
			//存储图片的serverId
			serverId:{
				serverId1:"",
				serverId2:"",
				serverId3:""
			},
			//临时数据
			temp:{
				id : "",
				name : "",
				description: "",
				image1: "",
				image2: "",
				image3: "",
				endTime: "",
				reservePrice: 0,
				bonusShareTotal: 0,
				bonusShareNumber: 0,
				bonusSecond: 0
			}
		},
		//跳转至服务协议页面
		LinkToServiceProtocol : function(){
			window.location.href = ctx+"/domainname/viewArticle?articleId=1";
		},
		//取消充值
		cancleRecharge:function(){
			$.m.changePage("#isell");
		},
		//提交充值
		confirmRecharge:function(){
			//微信支付
			$.ajax({
				type : 'POST',
				url : 'wxPay',
				data : "operate=充值" + "&operateAmount=" + vm.datas.rechargeAmount + "&payFrom=isell",
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
					$("#rechargeForSubDomain").dialog("hide");
					vm.datas.rechargeAmount = 0;
					wx.chooseWXPay({
						timestamp: res.data.timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
						nonceStr: res.data.nonceStr, // 支付签名随机串，不长于 32 位
						'package': res.data['package'], // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
						signType: res.data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
						paySign: res.data.paySign, // 支付签名
						success: function (res) {
							// 支付成功后的回调函数
							$.tips({
								content : "充值成功,重新点击提交",
								stayTime:2000,
								type : "success"
							}).on("tips:hide",function(){
								$.m.changePage("#isell");
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
						},
						cancel:function(res){
							$.m.changePage("#isell");
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
		},
    	switchifTransmitWelStatus: function(){
    		if (vm.datas.ifTransmitWel){
    			vm.datas.ifTransmitWel = false;
    			$("#shareRedPack").hide();
    			$("#shareRedPack").css("display","none");
    		}else{
    			vm.datas.ifTransmitWel = true;
    			$("#shareRedPack").show();
    			//$("#bonusShareNumber").focus();
    		}
    	},
    	switchifRewardSecondStatus: function(){
    		if (vm.datas.ifRewardSecond){
    			vm.datas.ifRewardSecond = false;
    			$("#secondRedPack").hide();
    			$("#secondRedPack").css("display","none");
    		}else{
    			vm.datas.ifRewardSecond = true;
    			$("#secondRedPack").show();
    			//$("#bonusSecond").focus();
    		}
    	},
    	chooseImages:function(from){
    		if(vm.datas.from == "changeDomainname"){
    			return false;
    		}
			wx.chooseImage({
			    count: 1, // 默认9
			    sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
			    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
			    success: function (res) {
			    	if(from=='image1'){
			    		vm.datas.domainInfo.image1= res.localIds.toString();
			    	}
			    	if(from=='image2'){
			    		vm.datas.domainInfo.image2= res.localIds.toString();
			    	}
			    	if(from=='image3'){
			    		vm.datas.domainInfo.image3= res.localIds.toString();
			    	}
			    }
			});
    		
    	},
        
        //提交域名
        submitDomain : function() {
        	if(vm.datas.from == "seeDomainname"){
        		return false;
        	}
        	committing.show();
        	//防止重复提交
        	var button = $(".sub");
        	if(button.hasClass('disabled')){
        		committing.hide();
        		return false;
        	} else {
        		button.addClass("disabled");
        	}
        	
        	//document.getElementById("submitDomain").disabled=true;
        	if (vm.datas.domainInfo.name == "") {
        		$.tips({
					content : "域名不能为空",
					stayTime : 2000,
					type : "warn"
				});
        		button.removeClass("disabled");
        		committing.hide();
    			return false;
        	}
        	
        	if (vm.datas.domainInfo.name.length > 63) {
        		$.tips({
					content : "域名最长63位",
					stayTime : 2000,
					type : "warn"
				});
        		button.removeClass("disabled");
        		committing.hide();
    			return false;
        	}
        	
        	
        	
        	if (vm.datas.domainInfo.name.indexOf('.') < 0 && vm.datas.domainInfo.name.indexOf('。') < 0) {
        		$.tips({
					content : "域名不能没有点",
					stayTime : 2000,
					type : "warn"
				});
        		button.removeClass("disabled");
        		committing.hide();
    			return false;
        	}
        	var reg = /[|<>]"'&()%@;+/;
        	if (reg.exec(vm.datas.domainInfo.name)) {
        		$.tips({
					content : "域名不能含有特殊字符",
					stayTime : 2000,
					type : "warn"
				});
        		button.removeClass("disabled");
        		committing.hide();
    			return false;
        	}
        	
        	if (vm.datas.domainInfo.description.length > 100) {
        		$.tips({
					content : "域名描述最长100位",
					stayTime : 2000,
					type : "warn"
				});
        		button.removeClass("disabled");
        		committing.hide();
    			return false;
        	}
        	
        	if (vm.datas.ifTransmitWel == true) {
        		if ((vm.datas.domainInfo.bonusShareTotal / vm.datas.domainInfo.bonusShareNumber < 1) 
        				|| (vm.datas.domainInfo.bonusShareTotal / vm.datas.domainInfo.bonusShareNumber > 200)) {
        			$.tips({
						content : "转发红包每份最少1元 最多200元",
						stayTime : 2000,
						type : "warn"
					});
            		button.removeClass("disabled");
            		committing.hide();
        			return false;
        		}
        	}
        	
        	if (vm.datas.ifRewardSecond == true) {
        		if (vm.datas.domainInfo.bonusSecond == 0) {
        			$.tips({
						content : "次高价红包设置错误",
						stayTime : 2000,
						type : "warn"
					});
            		button.removeClass("disabled");
            		committing.hide();
        			return false;
        		}
        	}
        	//冒泡，将已经选择的相片排在前全面：用户可能只选某张相片或者不选，无序的
        	elements = new Array(vm.datas.domainInfo.image1,vm.datas.domainInfo.image2,vm.datas.domainInfo.image3);
        	for(var i=0;i<elements.length-1;i++){
    		 for(var j=0;j<elements.length-i-1;j++){
    			   if(elements[j] == vm.datas.image_source && elements[j+1] != vm.datas.image_source){
    				    var swap=elements[j];
    				    elements[j]=elements[j+1];
    				    elements[j+1]=swap;
    				   }
    			  }
    		 }
//        	vm.datas.domainInfo.image1 = elements[0];
//        	vm.datas.domainInfo.image2 = elements[1];
//        	vm.datas.domainInfo.image3 = elements[2];
        	
        	if(elements[0]!= vm.datas.image_source) {
        		if(vm.datas.from == "changeDomainname"){
        			//二次提交不需要上传图片
        			postCommit(button);
        		}
    			wx.uploadImage({
    				localId: elements[0], // 需要上传的图片的本地ID，由chooseImage接口获得
    				isShowProgressTips: 0, // 默认为1，显示进度提示
    				success: function (res) {
    					vm.datas.serverId.serverId1 = res.serverId; // 返回图片的服务器端ID
    					if(elements[1] !=vm.datas.image_source) {
    		    			wx.uploadImage({
    		    				localId: elements[1], // 需要上传的图片的本地ID，由chooseImage接口获得
    		    				isShowProgressTips: 0, // 默认为1，显示进度提示
    		    				success: function (res) {
    		    					vm.datas.serverId.serverId2 = res.serverId; // 返回图片的服务器端ID
    		    					if(elements[2] != vm.datas.image_source) {
    		    		    			wx.uploadImage({
    		    		    				localId: elements[2], // 需要上传的图片的本地ID，由chooseImage接口获得
    		    		    				isShowProgressTips: 0, // 默认为1，显示进度提示
    		    		    				success: function (res) {
    		    		    					vm.datas.serverId.serverId3 = res.serverId; // 返回图片的服务器端ID
    		    		    					postCommit(button);
    		    		    				}
    		    		    			});
    		    		    		} else {
    		    		    			postCommit(button);
    		    		    		}
    		    				}
    		    			});
    		    		} else {
    		    			postCommit(button);
    		    		}
    				}
    			});
    		} else {
    			postCommit(button);
    		}
        }
	});	
    
	function postCommit(button) {
		//判断是否选了转发红包和次高价红包
		var bonusShareTotal = vm.datas.domainInfo.bonusShareTotal;
		var bonusShareNumber = vm.datas.domainInfo.bonusShareNumber;
		var bonusSecond = vm.datas.domainInfo.bonusSecond;
		if(!vm.datas.ifTransmitWel){
			bonusShareTotal = 0;
			bonusShareNumber = 0;
    	}
		if(!vm.datas.ifRewardSecond){
			bonusSecond = 0;
		}
		var id = ""
		if(vm.datas.from == "changeDomainname"){
			id = $.m.getHashParam().domainnameId;
		}else{
			id = "null";
		}
		//发送请求
		$.post("isell/submitDomain", {
    		"domainId" : vm.datas.domainInfo.id,
    		"name" : vm.datas.domainInfo.name,
    		"description" : vm.datas.domainInfo.description,
			"image1" : vm.datas.serverId.serverId1,
			"image2" : vm.datas.serverId.serverId2,
			"image3" : vm.datas.serverId.serverId3,
			"endTime" : vm.datas.domainInfo.endTime,
			"reservePrice" : vm.datas.domainInfo.reservePrice,
			"bonusShareTotal" : bonusShareTotal,
			"bonusShareNumber" : bonusShareNumber,
			"bonusSecond" : bonusSecond,
			"id" : id
			},  
			function(res) {
				if (res.type == "success") {
					//检查可用余额是否充足，用于支付保证金
					if(!res.data.isMoneyEnough){
						if(vm.datas.userinfo.avoidDeposit == '0' && (vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance) < (vm.datas.sellerDeposit + bonusShareTotal)){
							vm.datas.rechargeAmount = (vm.datas.sellerDeposit + bonusShareTotal)- (vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance);
							$.tips({
								content : "余额不足，无法支付保证金，请充值",
								stayTime : 2000,
								type : res.type
							}).on("tips:hide",function(){
								button.removeClass("disabled");
								committing.hide();
								$("#rechargeForSubDomain").dialog("show");
							});
						}
						
						if(vm.datas.userinfo.avoidDeposit == '1' && (vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance) < bonusShareTotal){
							vm.datas.rechargeAmount = bonusShareTotal - (vm.datas.financeinfo.accountBalance - vm.datas.financeinfo.freezeBalance);
							$.tips({
								content : "余额不足，无法支付保证金，请充值",
								stayTime : 2000,
								type : res.type
							}).on("tips:hide",function(){
								button.removeClass("disabled");
								committing.hide();
								$("#rechargeForSubDomain").dialog("show");
							});
						}
					}else{
						$.tips({
							content : res.msg,
							stayTime : 2000,
							type : res.type
						}).on("tips:hide",function(){
							button.removeClass("disabled");
							committing.hide();
							$.m.changePage("#isell");
						});
					}
				}else {
					$.tips({
						content : res.msg,
						stayTime : 2000,
						type : res.type
					});
					button.removeClass("disabled");
					committing.hide();
				}
				committing.hide();
			}
		);
	}
    
    $("#isell-setting").on("pageloaded", function() {
    	$.get("refreshFinanceinfo.json", {}, function(res) {
			//财务信息
			avalon.mix(vm.datas.financeinfo, res.data.financeinfo);
		});
    	
    	vm.datas.sellerDeposit = sellerDeposit;//卖家保证金
		vm.datas.image_source = ctx +"/static/images/upimage.jpg";
		//输入框获取焦点时，屏幕上移
		$("#bonusSecond").focus(function(){
			setTimeout(function(){
				var s = $('#isell-setting')[0];
				$("#scroll-up").show();
				$("#isell-setting").scrollTop(s.scrollHeight);
			}, 300); 
		});
		$("#bonusSecond").blur(function(){
			$("#scroll-up").hide();
		});
		$("#bonusShareNumber").focus(function(){
			setTimeout(function(){
				var s = $('#isell-setting')[0];
				$("#scroll-up").show();
				$("#isell-setting").scrollTop(s.scrollHeight);
			}, 300); 
		});
		$("#bonusShareNumber").blur(function(){
			$("#scroll-up").hide();
		});
		$("#bonusShareTotal").focus( function(){
			setTimeout(function(){
				var s = $('#isell-setting')[0];
				$("#scroll-up").show();
				$("#isell-setting").scrollTop(s.scrollHeight);
			}, 300); 
		});
		$("#bonusShareTotal").blur(function(){
			$("#scroll-up").hide();
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
	});

    $("#isell-setting").on("pageshow", function() {
		$("#scroll-up").hide();
		$.get("userInfo.json", {}, function(res) {
			//用户信息
			avalon.mix(vm.datas.userinfo, res.data.userinfo);
		});
		var domainnameId = $.m.getHashParam().domainnameId;
		if(domainnameId == null){
			//标记请求来源，setDomainname标识设置域名，seeDomainname标识查看域名
			vm.datas.from = "setDomainname";
			
			//设置进来，临时数据清零
			vm.datas.temp.id = "";
			vm.datas.temp.name = "";
			vm.datas.temp.description = "";
			vm.datas.temp.endTime = "";
			vm.datas.temp.reservePrice = 0;
			vm.datas.temp.bonusShareTotal = 0;
			vm.datas.temp.bonusShareNumber = 0;
			vm.datas.temp.bonusSecond = 0;
			vm.datas.temp.image3 = vm.datas.image_source;
			vm.datas.temp.image2 = vm.datas.image_source;
			vm.datas.temp.image1 = vm.datas.image_source;
			$("#endTime").empty();
			//临时数据赋值给 vm.datas.domainInfo用于显示
			vm.datas.domainInfo = vm.datas.temp;
			
	    	vm.datas.ifTransmitWel = false;
	    	vm.datas.ifRewardSecond = false;
	    	$("#shareRedPack").css("display","none");
	    	$("#secondRedPack").css("display","none");
			//新建域名，将所有的信息设置为可编辑状态
			unlockItems();
	    	appendEndTime(7,Lately7DayDomianNumList);
		}else{
			var from = $.m.getHashParam().from;
			if(from != null){
				vm.datas.from = "changeDomainname";//标记请求来源，setDomainname标识设置域名，seeDomainname标识查看域名 changeDomainname充值后修改提交域名
//				$('input').attr("readonly",true);//将input元素设置为readonly
//				$('textarea').attr("readonly",true);
//				$('select').attr("readonly",true);
				//	$('input').attr("readonly",false)//去除input元素的readonly属性
				lockItems();
			}else{
				vm.datas.from = "seeDomainname";//标记请求来源，setDomainname标识设置域名，seeDomainname标识查看域名
			}
			$.get("isell/domainInfo.json", {domainId:$.m.getHashParam().domainnameId}, function(res){
				if (res.type == "success") {
					//域名已存在
					if (res.data.domainInfo) {
						vm.datas.domainInfo = res.data.domainInfo;
						if ((null == res.data.domainInfo.image1) || ("" == res.data.domainInfo.image1)) {
							vm.datas.domainInfo.image1 = vm.datas.image_source;
						}
						
						if ((null == res.data.domainInfo.image2) || ("" == res.data.domainInfo.image2)) {
							vm.datas.domainInfo.image2 = vm.datas.image_source;
						}
						
						if ((null == res.data.domainInfo.image3) || ("" == res.data.domainInfo.image3)) {
							vm.datas.domainInfo.image3 = vm.datas.image_source;
						}
						//???????????
						if((null != res.data.domainInfo.endTime) && ("" != res.data.domainInfo.endTime)) {
							$("#endTime").empty();
							var endTime = res.data.domainInfo.endTime.split(" ")[0];
							var option = "<option value=" + endTime + " selected=\"selected\">" + endTime + "</option>";
							$("#endTime").append(option);
						}
						
						if(0 != res.data.domainInfo.bonusShareTotal) {
							vm.datas.ifTransmitWel = true;
							$("#shareRedPack").show();
						} else {
							vm.datas.ifTransmitWel = false;
							$("#shareRedPack").css("display","none");
						}
						
						if(0 != res.data.domainInfo.bonusSecond) {
							vm.datas.ifRewardSecond = true;
							$("#secondRedPack").show();
						} else {
							vm.datas.ifRewardSecond = false;
					    	$("#secondRedPack").css("display","none");
						}
						
						if (res.data.domainInfo.status == "03") {
							lockItems();
						}
					} 
				} else {
					$.tips({
		                content : res.msg,
		                stayTime: 2000,
		                type : res.type
		            });
				}
			})
		}
	});
	
	$("#isell-setting").on("pagehide", function() {
		vm.datas.domainInfo = {};
		$("#scroll-up").hide();
	});
	
	
	//结束拍卖日期控件生成
	function appendEndTime(N,Lately7DayDomianNumList) {
		var flag = false;//标记首选哪一天,已选为true
		for (var i = 0; i <N; i++) {
			var addDayFlag = true;//标记这天是否可选  可选为true
			var today = new Date();
			today.setDate(today.getDate() + i);
			var year = today.getFullYear();
			var month = today.getMonth() + 1;
			if(month < 10){
				month = "0" + month;
			}
			var day = today.getDate();
			if(day < 10){
				day = "0" + day;
			}
			var value = year + "-" + month + "-" + day;
			//检测这天是否可选
			for(var j = 0;j < Lately7DayDomianNumList.length;j++){
				if((value == Lately7DayDomianNumList[j].date) && Lately7DayDomianNumList[j].flag){
					addDayFlag = false;
					break;
				}
			}
			if(addDayFlag){
				if (i >= 1 && !flag) {
					flag = true;
					var option = "<option value=" + value + " selected=\"selected\">" +  value + "</option>";
					vm.datas.domainInfo.endTime = value;
				} else {
					var option = "<option value=" + value + ">" + value + "</option>";
				}
				$("#endTime").append(option);
			}
		}
	}
	
	//将域名元素设置为只读状态
	function lockItems() {
		ReadOnly("name");
		ReadOnly("description");
		DisableItem("image1");
		DisableItem("image2");
		DisableItem("image3");
		ReadOnly("reservePrice");
	}
	
	//将所有的信息设置为可编辑状态
	function unlockItems() {
		RemoveReadOnlyAttr("name");
		RemoveReadOnlyAttr("description");
		RemoveDisableAttr("image1");
		RemoveDisableAttr("image2");
		RemoveDisableAttr("image3");
		RemoveReadOnlyAttr("reservePrice");
	}
	
	function RemoveReadOnlyAttr(id) {
		$("#" + id).removeAttr("readonly");
	}
	
	function RemoveDisableAttr(id) {
		$("#" + id).removeAttr("disabled");
	}
	
	function ReadOnly(id) {
		$("#" + id).attr("readonly","readonly");
	}
	
	function DisableItem(id) {
		$("#" + id).attr("disabled","disabled")
	}
	committing = $.loading({content:'提交中...'}).hide();
	
	
	
	return vm;
})