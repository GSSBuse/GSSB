
define(["utils"], function(utils) {
	
	var vm = avalon.define({
		$id : "inputPayPassword",
		datas : {
			userinfo:{}
		},
		temp:{
			//标记需要输入支付密码的来源
			from:"",
			url:"",
			data:"",
			changePage:"",
			errorMsg:""
		},
		//控制密码框显示
		syncPassLen:function (length) {
			$("#pass-box b").removeClass("active");//清除所有的显示
			$("#pass-box b.pass-"+length).addClass("active");//显示对应的密码框
		},
		//确认支付密码
		confirmPayPassword:function(e){
			var val = $("#payPassword").val();//存储密码
			var oldLength = $("#payPassword").val().length;//当前输入密码的位数
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
			$("#payPassword").val(val);
			var newLength = $("#payPassword").val().length;
			vm.syncPassLen(newLength);
			if(newLength == 6){
				var datas = vm.temp.data + "&payPassword="+ $("#payPassword").val()
				$.ajax({
					type : 'POST',
					url :  vm.temp.url,
					data : datas,
					dataType : "json",
					success :function(res, textStatus){
						if(res.type == "success"){
							//操作成功
							$("#payPassword").val("");
							$("#pass-box b").removeClass("active");//清除所有的显示
							$.m.changePage("#"+ vm.temp.changePage);
						}else if(res.type == "warn"){
							//密码错误
							$("#payPassword").val("");
							$("#pass-box b").removeClass("active");//清除所有的显示
							$.tips({
								 content :res.msg,
								 stayTime:2000,
								 type : "warn"
							});
						}else{
							$.tips({
								 content :res.msg,
								 stayTime:2000,
								 type : "warn"
							});
							$.m.changePage("#"+ vm.temp.changePage);
						}
					 },
					 error : function(){
						$("#payPassword").val("");
						$("#pass-box b").removeClass("active");//清除所有的显示
						$.tips({
							 content :vm.temp.errorMsg,
							 stayTime:2000,
							 type : "error"
						});
						$.m.changePage("#"+ vm.temp.changePage);
					 }
				});
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
		vm.confirmPayPassword(this);//this为当前动作键盘按钮
	});
	
	$("#inputPayPassword").on("pageshow", function(e, d){
		vm.temp.from = $.m.getParam().from;
		vm.temp.url = $.m.getParam().url;
		vm.temp.data = $.m.getParam().data;
		vm.temp.changePage = $.m.getParam().changePage;
		vm.temp.errorMsg = $.m.getParam().errorMsg;
			
		$("#payPassword").val("");
		$("#pass-box b").removeClass("active");//清除所有的显示
		$("#keyboard").hide();
	});
	$("#inputPayPassword").on("pagehide", function(e, d){
		vm.temp.from = "";
		vm.temp.url = "";
		vm.temp.data = "";
		vm.temp.changePage = "";
		vm.temp.errorMsg = "";
		
		$("#payPassword").val("");
		$("#pass-box b").removeClass("active");//清除所有的显示
		$("#keyboard").hide();
	});
	
	
	return vm;
});
