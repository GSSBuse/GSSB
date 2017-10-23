
define([], function() {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "changeEmail",
		datas : {
			userinfo : {
				mobile:"",
				email : ""
			},
			// 需要更新的数据，可以暂存在这里
			temp : {
				email:"",
				//sys_verificationCode:"",
				verificationCode:""
			},
			//一分钟倒计时：计数
			n:0
		},
		//返回上一页
		returnBack : function(){
			history.back();
		},
		clearVerificationInput:function(e){
			vm.datas.temp.verificationCode="";
			$("#changeEmail #verificationCode").focus();
		},
		//还未到时间点击重新发送
		warnInfo:function(){
			$.tips({
                content : "请在"+vm.datas.n+"秒后点击",
                stayTime:2000,
                type : "warn"
            });
		},
		//重新发送验证码
		reSendVerificationCode:function(){
			$.post("userInfoForEmail", {}, function(res){
				if (res.type=="success"){
					 $.tips({
			                content : "已重新发送",
			                stayTime:2000,
			                type : res.type
			            });
					 //vm.datas.temp.sys_verificationCode = res.data.sys_verificationCode;
					 $("#verificationCode").focus();
					 vm.datas.n = 45;
				 }
				if (res.type=="error"){
					$.tips({
		                content : "验证码发送失败，点击重发",
		                stayTime:2000,
		                type : res.type
		            });
				}
			})
		},
		
		confirmVerificationInput:function(e){
			if(vm.datas.temp.verificationCode==""){
				$.tips({
					content : "验证码不能为空",
					stayTime:2000,
					type : "warn"
				});
			}else{
				var reg = /^\d{6}$/;//只能输入email格式
				if(reg.test(vm.datas.temp.verificationCode.toString())){
//					if(vm.datas.temp.verificationCode==vm.datas.temp.sys_verificationCode){
						$.m.changePage("#EmailInput");
//					}else{
//						 $.tips({
//							 content : "验证码错误",
//							 stayTime:2000,
//							 type : "warn"
//						 });
//					}
				}else{
					$.tips({
						content : "只能输入六位数字验证码",
						stayTime:2000,
						type : "warn"
					});
				}
			}
		},
		
		clearEmailInput:function(e){
			vm.datas.temp.email="";
			document.getElementById("email").focus();
		},
		
		confirmEmailInput:function(e){
			if(vm.datas.temp.verificationCode==""){
				$.tips({
					content : "验证码不能为空",
					stayTime:2000,
					type : "warn"
				});
				return;
			}
			if($("#changeEmail #email").val()==""){
				$.tips({
					content : "邮箱不能为空",
					stayTime:2000,
					type : "warn"
				});
				return;
			}
			if($("#changeEmail #email").val() ==vm.datas.userinfo.email){
				$.tips({
					content : "不能与原来的相同",
					stayTime:2000,
					type : "warn"
				});
				return;
			}
			if($("#changeEmail #email").val() != vm.datas.userinfo.email && $("#changeEmail #email").val() != ""){
				var reg = /^\w+[\w\-\._]*@[\w-_\.]+\.[a-zA-Z]{2,16}/;//只能输入email格式
				if(!reg.test($("#changeEmail #email").val())){
					$.tips({
						content : "Email格式不对",
						stayTime:2000,
						type : "warn"
					});
				}else{
					$.post("emailInput", {email: $("#changeEmail #email").val(), verificationCode: vm.datas.temp.verificationCode}, function(res){
						 if (res.type=="success"){
							$("#informationDialog").dialog("show");
						 }else{
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
		
		confirmInfomation:function(){
			$.m.changePage("#personalInfo");
			$("#informationDialog").dialog("hide");
		},
		comfirmGetCode : function(){
			$("#changeE_mail").dialog("hide");
			vm.datas.n=45;
			$.post("userInfoForEmail.json", {}, function(res) {
				if (res.type=="success"){
					$("#tip-text").text("我们已发送验证码到你的手机");
						$.tips({
							content : "验证码已成功发送",
							stayTime:2000,
							type : res.type
							});	
					vm.datas.userinfo = res.data.userinfo;
					//vm.datas.temp.sys_verificationCode =res.data.sys_verificationCode;
					$("#verificationCode").focus();
				}
				if (res.type=="error"){
					$.tips({
		                content : "验证码发送失败，点击重发",
		                stayTime:2000,
		                type : res.type
		            });
				}
			});
		},
		// 计算倒计时
		getCountDown : function() {
			return vm.datas.n;
		}
	});
	
	//每一秒钟给n自减一次，显示倒计时计数
	setInterval(function() {
		if(vm.datas.n>0){
			vm.datas.n = vm.datas.n - 1;
		}
	}, 1000);
	
	// 初始化changeEmail页面：下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#changeEmail").on("pageloaded", function(e, d){
		//vm.datas.n=45;
		$.get("userInfo.json", {}, function(res) {
			if(res.type=="success"){
				vm.datas.userinfo = res.data.userinfo;
				$("#changeE_mail").dialog("show");
			}
		});
//		$.post("userInfoForEmail.json", {}, function(res) {
//			if (res.type=="success"){
//					$.tips({
//						content : "验证码已成功发送",
//						stayTime:2000,
//						type : res.type
//						});	
//				vm.datas.userinfo = res.data.userinfo;
//				vm.datas.temp.sys_verificationCode =res.data.sys_verificationCode;
//			}
//			if (res.type=="error"){
//				$.tips({
//	                content : "验证码发送失败，点击重发",
//	                stayTime:2000,
//	                type : res.type
//	            });
//			}
//		});
	});
	$("#changeEmail").on("pageshow", function(e, d){
		//$("#verificationCode").focus();
		$("#changeE_mail").dialog("show");
	});
	$("#EmailInput").on("pageshow", function(e, d){
		$("#email").focus();
	});
	
	return vm;
});	