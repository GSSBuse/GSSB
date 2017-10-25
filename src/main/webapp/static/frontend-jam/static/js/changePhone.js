
define([], function() {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载frozen
	 */
	var vm = avalon.define({
		$id : "changePhone",
		datas : {
			userinfo : {
				mobile : ""
			},
			// 需要更新的数据，可以暂存在这里
			temp : {
				mobile:"",
				//sys_verificationCode:"",
				verificationCode:""
			},
			//一分钟倒计时：计数
			n:45
		},
		openConfirmChangePhonDialog:function(e){
			if($("#changePhone #mobile").val()==""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
			}
			if($("#changePhone #mobile").val() == vm.datas.userinfo.mobile){
				$.tips({
					content : "不能与原来的相同",
					stayTime:2000,
					type : "warn"
				});
			}
			if($("#changePhone #mobile").val() != vm.datas.userinfo.mobile && $("#changePhone #mobile").val() != ""){
				var reg = /^(\+\d{2,3}\-)?\d{11}$/;//只能输入移动手机号格式
				if(!reg.test($("#mobile").val())){
					$.tips({
						content : "号码输入有误",
						stayTime:2000,
						type : "warn"
					});
				}else{
					$("#changePhone #confirmChangePhone").dialog("show");
				}
			}
		},
		clearPhoneInput:function(e){
			vm.datas.temp.mobile="";
			$("#changePhone #mobile").focus();
		},
		changeMobileConfirm:function(e){
			committing.show();
			$.post("changeMobile", {mobile:vm.datas.temp.mobile}, function(res){
				if (res.type=="success"){
					 $.tips({
			                content : "验证码已成功发送",
			                stayTime:2000,
			                type : res.type
			            });
					//vm.datas.temp.sys_verificationCode = res.data.sys_verificationCode;
					$("#pageVerificationInput #verificationCode").focus();
					vm.datas.n = 45;
				 }
				if (res.type=="error"){
					$.tips({
		                content : "验证码发送失败，点击重发",
		                stayTime:2000,
		                type : res.type
		            });
				}
			});
			committing.hide();
			$.m.changePage("#pageVerificationInput", $.m.getParam())
		},
		clearVerificationInput: function(e){
			vm.datas.temp.verificationCode="";
			document.getElementById("verificationCode").focus();
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
			$.post("changeMobile", {mobile:vm.datas.temp.mobile}, function(res){
				if (res.type=="success"){
					 $.tips({
			                content : "已重新发送",
			                stayTime:2000,
			                type : res.type
			            });
					//vm.datas.temp.sys_verificationCode = res.data.sys_verificationCode;
					$("#pageVerificationInput #verificationCode").focus();
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
		//确认提交验证码
		confirmVerificationInput:function(e){
			if($("#pageVerificationInput #verificationCode").val()==""){
				$.tips({
					content : "验证码不能为空",
					stayTime:2000,
					type : "warn"
				});
			}else{
				var reg = /^\d{6}$/;//只能输入数字格式
				if(reg.test($("#pageVerificationInput #verificationCode").val())){
					//if(vm.datas.temp.verificationCode==vm.datas.temp.sys_verificationCode){
						committing.show();
						$.post("verificationPhone", {mobile:vm.datas.temp.mobile, verificationCode: $("#pageVerificationInput #verificationCode").val()}, function(res){
							 if (res.type=="success"){
								 $.tips({
						                content : res.msg,
						                stayTime:2000,
						                type : res.type
						            }).on("tips:hide",function(){
						            	// 完成后跳转
						            	var up = $.m.getParam();
						            	if (up.from) {
						            		$.m.changePage("#"+up.from, {newMobile:vm.datas.temp.mobile});
						            	}else if (up.fromIsell) {
						            		window.location.href = ctx + "/domainname/isell.html";
						            	} else {
						            		$.m.changePage("#personalInfo");
						            	}
						            });
							 }else{
								 $.tips({
						                content : res.msg,
						                stayTime:2000,
						                type : res.type
						            });
							 }
						});
						committing.hide();
//					}else{
//						 $.tips({
//			                content : "验证码错误",
//			                stayTime:2000,
//			                type : "warn"
//			            });
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
	
	// 从其他页面跳转回来时，如果想做恢复动作可以监听这个事件
	$("#changePhone").on("pagepreshow", function(e, d){
		// 关闭所有打开的对话框
		$(".ui-dialog").dialog("hide");
		//请求个人信息数据
		$.get("userInfo.json", {}, function(res) {
			//用户信息
			vm.datas.userinfo.mobile=res.data.userinfo.mobile;
		});
	});
	
	$("#changePhone").on("pageshow", function(e, d){
		$("#mobile").focus();
	});
	committing = $.loading({content:'提交中...'}).hide();
	return vm;
});	