
require(["utils"], function(utils) {
	
	var vm = avalon.define({
		$id : "securitySetting",
		datas : {
			userinfo:{}
		},
		temp:{
			payPassword:"",
			comfirmPayPassword:"",
			oldPassword:""
		},
		securityCheck : function(){
			var reg = /^[\d]{6}$/;//只能输入6位数字
			var oldPassword = $("#oldPasswordId").val();
			var newPassword = $("#newPasswordId").val();
			var confirmPasswordId = $("#confirmPasswordId").val();
			if(!reg.test(oldPassword)){
				$.jBox.error('原密码只能输入6位数字');
				return false;
			}
			if(!reg.test(newPassword)){
				$.jBox.error("新密码只能输入6位数字");
				return false;
			}
			if(!reg.test(confirmPassword)){
				$.jBox.error("确认密码只能输入6位数字");
				return false;
			}
			if(newPassword != confirmPasswordId){
				$.jBox.error("确认密码错误");
				return false;
			}
			$.post("securitysetting/checkOldPassword",{payPassword : oldPassword},function(data){
				if(data == "error"){
					$.jBox.error("原密码错误");
				}else{
					$.post("securitysetting/updatePayPassword",{payPassword : newPassword},function(data){
						if(data == "success"){
							$.jBox.error("修改密码成功");
						}else{
							$.jBox.error("修改密码失败");
						}
					}
				}
			});
		},
		b :function(){
			vm.a()
		}
		,
	});



	// 下面是获取个人资料,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#securitySettings").on("pageloaded", function(e, d){
		

	});
	
	avalon.scan();
	
});
