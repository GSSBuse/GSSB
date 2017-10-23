
define(["utils"], function(utils) {
	
	var vm = avalon.define({
		$id : "securitySettings",
		datas : {
			userinfo:{}
		},
		temp:{
			payPassword:"",
			comfirmPayPassword:"",
			oldPassword:""
		},
		//添加密码链接跳转
		addPayPassWord:function(){
			$.m.changePage("#addPayPassword?type=set");
		},
		//清除首次输入密码
		clearPayPasswordInput:function(){
			vm.temp.payPassword = "";
			$("#payPassword").focus();
		},
		//跳转至二次确认密码输入
		goToconfirmPayPassword:function(){
			if(vm.temp.payPassword == ""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			
			var reg = /^[\d]{6}$/;//只能输入6位数字
			if(!reg.test(vm.temp.payPassword)){
				$.tips({
					content : "只能输入6位数字",
					stayTime:2000,
					type : "warn"
				});
			}else{
				$("#password").css("display","none");
				$("#confirmPassword").css("display","block");
			}
		},
		//清除二次确认密码输入
		clearComfirmPayPasswordInput:function(){
			vm.temp.comfirmPayPassword = "";
			$("#comfirmPayPassword").focus();
		},
		//返回首次输入密码界面
		openPassword:function(){
			$("#password").css("display","block");
			$("#confirmPassword").css("display","none");
		},
		//确认提交密码
		confirmAddPayPassword:function(){
			if(vm.temp.comfirmPayPassword == ""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			
			var reg = /^[\d]{6}$/;//只能输入6位数字
			if(!reg.test(vm.temp.comfirmPayPassword)){
				$.tips({
					content : "只能输入6位数字",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			if(vm.temp.comfirmPayPassword != vm.temp.payPassword){
				$.tips({
					content : "两次输入不一致",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			
			$.post("addPayPassword", {payPassword:vm.temp.payPassword}, function(res) {
				if(res.type == "success"){
					$.m.changePage("icenter.html");
				}else{
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					})
				}
			});
			
		},
		//重置密码链接跳转
		changePayPassWord:function(){
			$.m.changePage("#addPayPassword?type=change");
		},
		//忘记原密码联系经济人提示弹窗
		getContactWithBroker:function(){
			$("#contactWithBrokerDialog").dialog("show");
		},
		//清除原密码输入
		clearOldPasswordInput:function(){
			vm.temp.oldPassword = "";
			$("#oldPassword").focus();
		},
		//确认提交旧密码，进行验证
		confirmOldPassword:function(){
			if(vm.temp.oldPassword == ""){
				$.tips({
					content : "不能为空",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			
			var reg = /^[\d]{6}$/;//只能输入6位数字
			if(!reg.test(vm.temp.oldPassword)){
				$.tips({
					content : "只能输入6位数字",
					stayTime:2000,
					type : "warn"
				});
				return false;
			}
			
			$.post("verificationOldPassword", {oldPassword:vm.temp.oldPassword}, function(res) {
				if(res.type == "success"){
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					});
					$.m.changePage("#addPayPassword");
				}else if(res.type == "warn"){
					$.tips({
						content : res.msg,
						stayTime:2000,
						type : res.type
					}).on("tips:hide",function(){
						vm.temp.oldPassword = "";
						$("#oldPassword").focus();
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
	});



	// 下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#securitySettings").on("pageloaded", function(e, d){
		

	});
	
	// 从其他页面跳转回来时，如果想做恢复动作可以监听这个事件
	$("#securitySettings").on("pageshow", function(e, d){
		//请求个人信息数据
		$.get("userInfo.json", {}, function(res) {
			//用户信息
			vm.datas.userinfo = res.data.userinfo;
		});
	});
	
	$("#addPayPassword").on("pageshow", function(e, d){
		$("#password").css("display","block");
		$("#confirmPassword").css("display","none");
	});
	
	$("#addPayPassword").on("pagehide", function(e, d){
		vm.temp.payPassword = "";
		vm.temp.comfirmPayPassword = "";
	});
	$("#setPayPassword").on("pagehide", function(e, d){
		vm.temp.oldPassword = "";
	});
	$("#securitySettings").on("pagehide", function(e, d){
		vm.temp.payPassword = "";
		vm.temp.comfirmPayPassword = "";
		vm.temp.oldPassword = "";
	});
	
	return vm;
});
