
define( function() {

	var vm = avalon.define({
		$id : "personalInfo",
		datas : {
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
				idcardNumber:""
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
				IDcardNumber:""
			},
			//经济人信息
			brokerinfo:{
				name:"",
				email:"",
				mobile:"",
				wechat:"",
				qq:"",
				photo:"",
				nickName:""
			},
			//身份认证时，暂存信息
			authentication:{
				image1_localIds:"",
				image2_localIds:"",
				image1_serverId:"",
				image2_serverId:"",
				image_source:"",//原始图：提示上传图片的图
				image_source1:"",//原始图：提示上传图片的图
				image_source2:""//原始图：提示上传图片的图
			},
			cashflowinfo:{
				payPassword:""//存储修改支付方式时的支付密码
			}
		},
		//跳转至修改手机页面
		linkToChangePhone:function(){
			$.m.changePage("changePhone.html");
		},
		// 财务管理：认证默认收支方式
		open_income_expense_dialog:function(e){
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
			$("#change-default-income-expense").dialog("show");
			$("#default_income_expense").focus();
		},
		//取消修改默认收支方式
		cancelChangeDefaultIncomeExpense:function(){
			$("#change-default-income-expense").dialog("hide");
			vm.datas.temp.defaultIncomeExpense = vm.datas.userinfo.defaultIncomeExpense;
		},
		
		clearBankCardNumberInput:function(e){
			vm.datas.temp.defaultIncomeExpense = "";
			document.getElementById("default_income_expense").focus();
		},
		//打开修改银行卡号的支付密码弹窗
		openVerificationPayPasswordForDIE:function(){
			if($("#default_income_expense").val()==""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#default_income_expense").val() ==vm.datas.userinfo.defaultIncomeExpense){
				$.tips({
					content : "不能与原来的相同",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#default_income_expense").val() != vm.datas.userinfo.defaultIncomeExpense && $("#default_income_expense").val() != ""){
				var reg = /^[\d]{15,22}$/;//只能输入银行卡号格式
				if(!reg.test($("#default_income_expense").val())){
					$.tips({
						content : "只能输入银行卡号格式",
						stayTime:2000,
						type : "warn"
					});
					return false;
				}
			}
			$("#change-default-income-expense").dialog("hide");
			$("#verificationPayPasswordForDIE").dialog("show");
			$("#payPasswords").focus();
		},
		clearPayPasswordForDIEInput:function(){
			vm.datas.cashflowinfo.payPassword = "";
			$("#payPasswords").focus();
		},
		//提交修改支付方式
		confirmChangeDefaultIncomeExpense:function(e){
			
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
					$.post("changeDefaultIncomeExpense", {defaultIncomeExpense:vm.datas.temp.defaultIncomeExpense}, function(res){
						if (res.type=="success") {
							vm.datas.userinfo.defaultIncomeExpense = res.data.newDefaultIncomeExpense;
							vm.datas.temp.defaultIncomeExpense = vm.datas.userinfo.defaultIncomeExpense;
							}
						vm.datas.cashflowinfo.payPassword = "";
						$("#change-default-income-expense").dialog("hide");
						$("#verificationPayPasswordForDIE").dialog("hide");
						committing.hide();
						$.tips({
							content : res.msg,
							stayTime:2000,
							type : res.type
						});
					});
				}else if(res.type == "warn"){
					$("#change-default-income-expense").dialog("hide");
					$("#verificationPayPasswordForDIE").dialog("show");
					vm.datas.cashflowinfo.payPassword = "";
					committing.hide();
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					});
				}else{
					vm.datas.cashflowinfo.payPassword = "";
					$("#change-default-income-expense").dialog("hide");
					$("#verificationPayPasswordForDIE").dialog("hide");
					committing.hide();
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					});
				}
			});	
		},
		//关闭支付密码弹窗时清除数据
		clearForDIE:function(){
			$("#verificationPayPasswordForDIE").dialog("hide");
			$("#change-default-income-expense").dialog("hide");
			vm.datas.cashflowinfo.payPassword = "";
			vm.datas.temp.defaultIncomeExpense = vm.datas.userinfo.defaultIncomeExpense;
		},
		// 个人信息：修改姓名弹窗
		changeName : function(e) {
			$("#changeName").dialog("show");
			$("#name").focus();
		},
		changeNameComfirm : function (e) {
			if($("#name").val()==""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#name").val() ==vm.datas.userinfo.name){
				$.tips({
					content : "不能与原来的相同",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#name").val() != vm.datas.userinfo.name && $("#name").val() != ""){
				var reg = /^[\u4E00-\u9FA5]{2,10}$/;//只能输入汉字
				if(!reg.test($("#name").val())){
					$.tips({
						content : "只能输入2至10位汉字",
						stayTime:2000,
						type : "warn"
					});
					return false;
				}else{
					$.post("changeName", {name:vm.datas.temp.name}, function(res) {
						$.tips({
							content : res.msg,
							stayTime:2000,
							type : res.type
						});
						if (res.type=="success") {
							vm.datas.userinfo.name = res.data.newName;
							vm.datas.temp.name = vm.datas.userinfo.name;
							$("#changeName").dialog("hide");
						}
					});
				}
			}
		},
		changeNameCancel : function (e) {
			$("#changeName").dialog("hide");
		},
		clearNameInput : function(e) {
			vm.datas.temp.name = "";
			document.getElementById("name").focus();
		},
		 //个人信息：修改微信弹窗
		changeWx:function(e){
			$("#changeWx").dialog("show");
			$("#wx").focus();
		},
		changeWxComfirm:function(e){
			if($("#wx").val()==""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#wx").val() ==vm.datas.userinfo.wx){
				$.tips({
					content : "不能与原来的相同",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#wx").val() != vm.datas.userinfo.wx && $("#wx").val() != ""){
				var reg = /^[\w]{1,20}$/;//只能输入微信号格式
				if(!reg.test($("#wx").val())){
					$.tips({
						content : "请输入正确的微信号",
						stayTime:2000,
						type : "warn"
					});
					return false;
				}else{
					$.post("changeWx", {wx:vm.datas.temp.wx}, function(res){
						$.tips({
							content : res.msg,
							stayTime:2000,
							type : res.type
						});
						if (res.type=="success") {
							vm.datas.userinfo.wx = res.data.newWx;
							vm.datas.temp.wx = vm.datas.userinfo.wx;
							$("#changeWx").dialog("hide");
						}
					})
				}
			}
		},
		clearWxInput:function(e){
			vm.datas.temp.wx = "";
			document.getElementById("wx").focus();
		},
		// 个人信息：修改QQ弹窗
		changeQQ:function(e){
			$("#changeQQ").dialog("show");
			$("#qq").focus();
		},
		changeQQComfirm:function(e){
			if($("#qq").val()==""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#qq").val() == vm.datas.userinfo.qq){
				$.tips({
					content : "不能与原来的相同",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#qq").val() != vm.datas.userinfo.qq && $("#qq").val() != ""){
				var reg = /^[\d]{4,20}$/;//只能输入QQ号格式
				if(!reg.test($("#qq").val())){
					$.tips({
						content : "只能输入QQ号格式！",
						stayTime:2000,
						type : "warn"
					});
					return false;
				}else{
						$.post("changeQQ", {qq:vm.datas.temp.qq}, function(res){
							$.tips({
								content : res.msg,
								stayTime:2000,
								type : res.type
							});
							if (res.type=="success") {
								vm.datas.userinfo.qq = res.data.newQQ;
								vm.datas.temp.qq = vm.datas.userinfo.qq;
								$("#changeQQ").dialog("hide");
							}
						});
					}
				}
		},
		clearQQInput:function(e){
			vm.datas.temp.qq = "";
			document.getElementById("qq").focus();
		},
		//当email不为空且未激活时，可以选择修改或激活
		changeOrActiveEmail:function(){
			$("#changeOrActiveEmail").dialog("show");
		},
		//选择修改邮箱
		chooseChangeEmail:function(){
			$("#changeOrActiveEmail").dialog("hide");
			if(vm.datas.userinfo.mobile){
				vm.goToChangeEmail();
			}else{
				vm.addEmail();
			}
		},
//		//修改email弹窗
//		changeEmail:function(e){
//			setTimeout(function(){
//				$("#changeE_mail").dialog("show");
//			}, 100);
//		},
		goToChangeEmail:function(){
			$.m.changePage("changeEmail.html");
		},
		//激活email
		chooseActivateEmail:function(){
			$("#changeOrActiveEmail").dialog("hide");
			committing.show();
			$.ajax({
				 type : 'GET',
				 url : 'activateEmail',
				 data : "email=" + vm.datas.userinfo.email,
				 dataType : "json",
				 success :function(res, textStatus){
					committing.hide();
					$.tips({
		                content : "请登陆邮箱点击激活链接，完成激活",
		                stayTime:2000,
		                type : "success"
		            });
				 },
				 error : function(res, textStatus){
					 committing.hide();
					 $.tips({
		                content : "获取激活链接失败，请重新操作",
		                stayTime:2000,
		                type : "warn"
			         });
				 }
			});
		},
		//添加email
		addEmail:function(){
			$("#addEmail").dialog("show");
			$("#email").focus();
		},
		clearEmailInput:function(e){
			vm.datas.temp.email="";
			document.getElementById("email").focus();
		},
		
		confirmEmailInput:function(e){
			if(vm.datas.temp.email==""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if(vm.datas.temp.email ==vm.datas.userinfo.email){
				$.tips({
					content : "不能与原来的相同",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if(vm.datas.temp.email != vm.datas.userinfo.email && vm.datas.temp.email != ""){
				var reg =  /^\w+[\w\-\._]*@[\w-_\.]+\.[a-zA-Z]{2,16}/;//只能输入email格式
				var email = vm.datas.temp.email;
				if(!reg.test(vm.datas.temp.email)){
					$.tips({
						content : "Email格式不对",
						stayTime:2000,
						type : "warn"
					});
					return false;
				}else{
					committing.show();
					$.post("emailInput", {email:vm.datas.temp.email}, function(res){
						 if (res.type=="success"){
							 $("#addEmail").dialog("hide");
							 committing.hide();
							 vm.datas.userinfo.emailFlag = '0';
							 vm.datas.userinfo.email = email;
							 $("#informationDialogForAddEmail").dialog("show");
						 }else{
							 committing.hide();
							 $.tips({
								 content : res.msg,
								 stayTime:2000,
								 type : res.type
							 });
						 }
						
					})
				}
			}
		},
		//查看经纪人弹窗
		viewBroker:function(e){
			$("#viewBroker").dialog("show");
		},
//		// 财务管理：认证默认收支方式
//		open_income_expense_dialog:function(e){
//			$("#change-default-income-expense").dialog("show");
//		},
//		clearBankCardNumberInput:function(e){
//			vm.datas.temp.defaultIncomeExpense = "";
//			document.getElementById("default_income_expense").focus();
//		},
//		confirmChangeDefaultIncomeExpense:function(e){
//			$.post("changeDefaultIncomeExpense", {defaultIncomeExpense:vm.datas.temp.defaultIncomeExpense}, function(res){
//				$.tips({
//	                content : res.msg,
//	                stayTime:2000,
//	                type : res.type
//	            });
//	            if (res.type=="success") {
//	            	vm.datas.userinfo.defaultIncomeExpense = res.data.newDefaultIncomeExpense;
//	            	vm.datas.temp.defaultIncomeExpense = vm.datas.userinfo.defaultIncomeExpense;
//	            	$("#change-default-income-expense").dialog("hide");
//	            }
//			})
//		},
		//身份已认证查看
		viewIDcard:function(){
			$("#viewIDcard").dialog("show");
		},
		//身份认证、修改
		//身份认证:修改弹窗
		changeIDcard:function(){
			$("#changeIDcard").dialog("show");
			$("#IDcardNumber").focus();
		},
		//清除身份证号的输入
		clearIDcardNumberInput : function(){
			vm.datas.temp.IDcardNumber = "";
			document.getElementById("IDcardNumber").focus();
		},
		//选择正面证件图片
		authentication_image1:function(){
			wx.chooseImage({
			    count: 1, // 默认9
			    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
			    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
			    success: function (res) {
			    vm.datas.authentication.image1_localIds = res.localIds.toString(); // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			    //$("#authentication_image1").attr("src",vm.datas.authentication.image1_localIds);
			    vm.datas.authentication.image_source1 = vm.datas.authentication.image1_localIds;
			    },
			    fail: function (res) {
			    	vm.datas.authentication.image1_localIds = "fail";
			    	$.tips({
						content : "选择失败，请重新选择照片",
						stayTime:2000,
						type : "warn"
					});
			    }
			});
		},
		//选择反面证件图片
		authentication_image2:function(){
			wx.chooseImage({
			    count: 1, // 默认9
			    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
			    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
			    success: function (res) {
			    vm.datas.authentication.image2_localIds = res.localIds.toString(); // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			    //$("#authentication_image2").attr("src",vm.datas.authentication.image2_localIds);
			    vm.datas.authentication.image_source2 = vm.datas.authentication.image2_localIds;
			    },
			    fail: function (res) {
			    	vm.datas.authentication.image2_localIds = "fail";
			    	$.tips({
						content : "选择失败，请重新选择照片",
						stayTime:2000,
						type : "warn"
					});
			    }
			});
		},
		//确认提交身份认证
		confirmAuthentication:function(){
			if($("#IDcardNumber").val()==""){
				$.tips({
					content : "身份证号不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if($("#IDcardNumber").val() == vm.datas.userinfo.idcardNumber){
				$.tips({
					content : "身份证号不能与原来的相同",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			
			var reg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;//只能输入身份证格式
			if(!reg.test(vm.datas.temp.IDcardNumber)){
				$.tips({
					content : "身份证号格式不对",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
		//正反面都选择了相片，则上传（选择微信接口上传，图片暂存在了微信服务器，需在后台根据serverId调用微信接口下载到自己的服务器上）
		if(vm.datas.authentication.image1_localIds != "" && vm.datas.authentication.image2_localIds !=""){
			if(vm.datas.authentication.image2_localIds != "fail" && vm.datas.authentication.image2_localIds !="fail"){
				wx.uploadImage({
				    localId: vm.datas.authentication.image1_localIds, // 需要上传的图片的本地ID，由chooseImage接口获得
				    isShowProgressTips: 1, // 默认为1，显示进度提示
				    success: function (res) {
				    	vm.datas.authentication.image1_serverId = res.serverId; // 返回图片的服务器端ID
				    	wx.uploadImage({
						    localId: vm.datas.authentication.image2_localIds, // 需要上传的图片的本地ID，由chooseImage接口获得
						    isShowProgressTips: 1, // 默认为1，显示进度提示
						    success: function (res) {
						    	vm.datas.authentication.image2_serverId = res.serverId; // 返回图片的服务器端ID
						    	//微信接口上传成功，则提交到后台处理（从微信服务上下载到自己的服务器上）
								if(vm.datas.authentication.image1_serverId !="fail" && vm.datas.authentication.image2_serverId !="fail" && vm.datas.authentication.image1_serverId !="" && vm.datas.authentication.image2_serverId !=""){
									$.post("authenticationIDcard", {image1_serverId:vm.datas.authentication.image1_serverId,image2_serverId:vm.datas.authentication.image2_serverId,IDcardNumber : vm.datas.temp.IDcardNumber}, function(res){
										$.tips({
											content : res.msg,
											stayTime:2000,
											type : res.type
										});
							            if (res.type=="success") {
							            	vm.datas.userinfo.authenticationPositiveImageUrl = res.data.authenticationPositiveImageUrl;
							            	vm.datas.userinfo.authenticationNegativeImageUrl = res.data.authenticationNegativeImageUrl;
							            	vm.datas.userinfo.authenticationMark = "2";
							            	vm.datas.userinfo.idcardNumber = vm.datas.temp.IDcardNumber;
							            	//$("#authentication_image1").attr("src",vm.datas.authentication.image_source);
							    			//$("#authentication_image2").attr("src",vm.datas.authentication.image_source);
							            	$("#changeIDcard").dialog("hide");
							            }else{
							            	vm.datas.authentication.image_source1 = vm.datas.authentication.image_source;
							            	vm.datas.authentication.image_source2 = vm.datas.authentication.image_source;
							            	$("#changeIDcard").dialog("hide");
							            }
									});
								}else{
									$.tips({
										content : "身份证上传失败，请重新操作",
										stayTime:2000,
										type : "warn"
									});
								}
						    },
						    fail: function (res) {
						    	alert("res.serverId失败");
						    	vm.datas.authentication.image2_serverId = "fail";
						    	$.tips({
									content : "上传失败，重新提交！",
									stayTime:2000,
									type : "warn"
								});
						    }
						});
				    },
				    fail: function (res) {
				    	alert("res.serverId失败");
				    	vm.datas.authentication.image1_serverId = "fail";
				    	$.tips({
							content : "上传失败，重新提交！",
							stayTime:2000,
							type : "warn"
						});
				    }
				});
			}else{
				$.tips({
					content : "选择失败，请重新选择照片",
					stayTime:2000,
					type : "warn"
				});
			}
			//vm.datas.temp.IDcardNumber = "";
		}else{
				$.tips({
					content : "请继续选择照片，不能为空",
					stayTime:2000,
					type : "warn"
				});
			}
		},
		//取消提交身份认证
		cancelAuthentication:function(){
			$("#authentication_image1").attr("src",vm.datas.authentication.image_source);
			$("#authentication_image2").attr("src",vm.datas.authentication.image_source);
			$("#changeIDcard").dialog("hide");
		}
	});
	
	// 下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#personalInfo").on("pageloaded", function(e, d){		
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
		
		//alert($("#openBigPhoto").size())
		$("#openBigPhoto").click(function () {
			//alert(2)
			var src = $(this).find("img").attr("src");
			var d = $("<div class='bigphoto'>");
			var i = $("<img>");
			i.attr("src",src);
			
			d.append(i);
			
			d.click(function(){
				d.remove();
			})
			$("#personalInfo").on("pagehide",function() {
				d.remove();
			});
		
			$("body").append(d);
		});
	});
	
	// 从其他页面跳转回来时，如果想做恢复动作可以监听这个事件
	$("#personalInfo").on("pagepreshow", function(e, d){
		
		vm.datas.authentication.image_source = ctx +"/static/images/upimage.jpg";
		vm.datas.authentication.image_source1 = ctx +"/static/images/upimage.jpg";
		vm.datas.authentication.image_source2 = ctx +"/static/images/upimage.jpg";
		
		//用户信息
		$.get("userInfo.json", {}, function(res) {
			avalon.mix(vm.datas.userinfo, res.data.userinfo);
			avalon.mix(vm.datas.temp, res.data.userinfo);
		});
		
		//经纪人信息
		$.get("brokerInfo.json", {}, function(res) {
		if(res.type=="success"){
			vm.datas.brokerinfo = res.data.brokerinfo;//					
		}else{
			$.tips({
                content : res.msg,
               stayTime:2000,
                type : res.type
           });
		}
		});
		// 关闭所有打开的对话框
		$(".ui-dialog").dialog("hide");
		// 从changePhone过来的页面
		if ($(d.fromPage).attr("id") == "pageVerificationInput") {
			// 从changeEmail页面返回：下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
			$.get("userInfo.json", {}, function(res) {
				vm.datas.userinfo.mobile = res.data.userinfo.mobile;
			});
		}
		if ($(d.fromPage).attr("id") == "EmailInput") {
			// 从changeEmail页面返回：下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
			$.get("userInfo.json", {}, function(res) {
				vm.datas.userinfo.email = res.data.userinfo.email;
				vm.datas.userinfo.emailFlag = res.data.userinfo.emailFlag;
			});
		}

	});
	committing = $.loading({content:'提交中...'}).hide();
	return vm;
});
