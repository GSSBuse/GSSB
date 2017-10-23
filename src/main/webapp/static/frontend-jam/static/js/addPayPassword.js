
define(["utils"], function(utils) {
	
	var vm = avalon.define({
		$id : "addPayPassword",
		datas : {
			userinfo:{}
		},
		temp:{
			type:""
		},
		//设置提示语
		setTip : function () {
			var e = $("#tip");
			if (vm.temp.type === "change"){
				e.text("请输入旧的支付密码，以验证身份");
			}else if (vm.temp.type === "set"){
				e.text("请设置支付密码，建议勿与银行卡取卡取款密码相同");
			}else if(vm.temp.type === "confirm"){
				e.text("请再次填写以确认");
			}
		},
		//控制密码框显示
		syncPassLen:function (length) {
			$("#pass-box b").removeClass("active");//清除所有的显示
			$("#pass-box b.pass-"+length).addClass("active");//显示对应的密码框
		},
		//修改密码
		changePayPassword:function(e){
			var val = $("#oldpaypass").val();//存储密码
			var oldLength = $("#oldpaypass").val().length;//当前输入密码的位数
			var selfVal = $(e).attr("data");//按键值
			if(selfVal==="del") {
				if(oldLength != 0){
					val = val.substring(0, val.length - 1);
				}
			} else {
				if(oldLength >= 6){
					return false;
				}
				val = val + "" + selfVal;
			}
			$("#oldpaypass").val(val);
			var newLength = $("#oldpaypass").val().length;
			vm.syncPassLen(newLength);
			//如果已输入六位，发送请求验证旧密码是否正确
			if(newLength == 6){
				$.post("verificationOldPassword", {oldPassword:$("#oldpaypass").val()}, function(res) {
					if(res.type == "success"){
						$.tips({
							content : res.msg,
							stayTime:2000,
							type : res.type
						});
						vm.temp.type = 'set';
						vm.setTip();
						$("#pass-box b").removeClass("active");//清除所有的显示
						$("#paypass").val("");
						$("#confirmpaypass").val("");
					}else if(res.type == "warn"){
						$.tips({
							content : res.msg,
							stayTime:2000,
							type : res.type
						}).on("tips:hide",function(){
							vm.temp.type = 'change';
							vm.setTip();
							$("#pass-box b").removeClass("active");//清除所有的显示
							$("#oldpaypass").val("");
						});
					}else{
						$.tips({
							content : res.msg,
							stayTime:2000,
							type : res.type
						});
						$("#pass-box b").removeClass("active");//清除所有的显示
						$("#oldpaypass").val("");
						$("#paypass").val("");
						$("#confirmpaypass").val("");
					}
				});
			}
		},
		//设置密码
		setPayPassword:function(e){
			var val = $("#paypass").val();//存储密码
			var oldLength = $("#paypass").val().length;//当前输入密码的位数
			var selfVal = $(e).attr("data");//按键值
			if(selfVal==="del") {
				if(oldLength != 0){
					val = val.substring(0, val.length - 1);
				}
			} else {
				if(oldLength >= 6){
					return false;
				}
				val = val + "" + selfVal;
			}
			$("#paypass").val(val);
			var newLength = $("#paypass").val().length;
			vm.syncPassLen(newLength);
			//如果已输入六位，进入确认密码输入
			if(newLength == 6){
				setTimeout(function(){
					vm.temp.type = 'confirm';
					vm.setTip();
					$("#pass-box b").removeClass("active");//清除所有的显示
					$("#confirmpaypass").val("");
				}, 500);
			}
		},
		//确认密码
		confirmPayPassword:function(e){
			var val = $("#confirmpaypass").val();//存储密码
			var oldLength = $("#confirmpaypass").val().length;//当前输入密码的位数
			var selfVal = $(e).attr("data");//按键值
			if(selfVal==="del") {
				if(oldLength != 0){
					val = val.substring(0, val.length - 1);
				}
			} else {
				if(oldLength >= 6){
					return false;
				}
				val = val + "" + selfVal;
			}
			$("#confirmpaypass").val(val);
			var newLength = $("#confirmpaypass").val().length;
			vm.syncPassLen(newLength);
			if(newLength == 6){
				if($("#confirmpaypass").val() == $("#paypass").val()){
					$.post("addPayPassword", {payPassword:$("#confirmpaypass").val()}, function(res) {
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
				}else{
					$.tips({
						content : "两次输入不一致",
						stayTime:2000,
						type : "warn"
					});
					$("#confirmpaypass").val("");
					$("#pass-box b").removeClass("active");//清除所有的显示
				}
			}
		},
		//隐藏键盘
		showKeyboard:function(){
			$("#keyboard").show();
		},
		hideKeyboard:function(){
			$("#keyboard").hide();
		}
	});
	


	$(".numKeyboard").find("li.active-numKey").bind("touchstart", function(){
		//修改密码
		if(vm.temp.type == "change"){
			vm.changePayPassword(this);//this为当前动作键盘按钮
		}
		//设置密码
		if(vm.temp.type == "set"){
			vm.setPayPassword(this);//this为当前动作键盘按钮
		}
		//二次确认密码
		if(vm.temp.type == "confirm"){
			vm.confirmPayPassword(this);//this为当前动作键盘按钮
		}
		
	});
	
	$("#addPayPassword").on("pageshow", function(e, d){
		vm.temp.type = $.m.getHashParam().type;
		$("#paypass").val("");
		$("#confirmpaypass").val("");
		$("#oldpaypass").val("");
		$("#pass-box b").removeClass("active");//清除所有的显示
		$("#keyboard").hide();
		vm.setTip();
	});
	$("#addPayPassword").on("pagehide", function(e, d){
		vm.temp.type = "";
		$("#paypass").val("");
		$("#confirmpaypass").val("");
		$("#oldpaypass").val("");
		$("#pass-box b").removeClass("active");//清除所有的显示
		$("#keyboard").hide();
	});
	
	
	return vm;
});
