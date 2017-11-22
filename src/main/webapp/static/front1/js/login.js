/**
 * 登陸按鈕操作
 */


$(document).ready(function(){
	
	// 定时检查是否已经登录
	var checking = false;
	var checkLogin = function(){
		if ($("#js_login_box_button").size() == 0) {
			// 已经登录，不需要检查
			return;
		}
		
		if (checking) {
			// 已经开启了检查，不要重复做
			return;
		}
		
		checking = true;
		
		$.ajax({
	        type: "POST",
	        url: ctx + "/common/qrloginCheck",
	        data: {},
	        dataType: "json",
	        success: function(resp) {
	        	if(resp.type == "success"){
	        		window.location.reload()
	        	}
	        	else{
	        		checking = false;
	        		setTimeout(checkLogin, 5000);
	        	}
	        }
    	});
	}
	
	$("#js_login_box_button").on({
		click: function(e){
			e.preventDefault();
			e.stopPropagation();
			$("#js_uiLoginBox").show();
			
			setTimeout(checkLogin, 5000);
		}
	});
	
	
	//请求登陆验证
	$('#js_login_button').on({
		click: function(e){
			var login_name = $("input[name='login_name']").val(),
				password = $("input[name='password']").val(); 
				
			if(login_name == "" || password == ""){
				$("#js_error_hint").hide();
				$("#js_input_empty_hint").show();
				return false;
			}
			
			$.ajax({
		        type: "POST",
		        url: ctx + "/common/loginCheck",
		        data: {
		        	"name":login_name,
		        	"password":password
		        	},
		        dataType: "text",
		        success: function(data) {
		        	if(data == "ok"){
		        		window.location.reload();
//				        		$(".loggedout").hide();
//								$(".loggedin").show();
						

		        	}
		        	else{
		        		$("#js_input_empty_hint").hide();
		        		$("#js_error_hint").show();
		        	}
		        }
	    });
			
		}
	});
	//请求登陆验证结束
	
	
	
	$("#js_uiLoginBox").click( function ( e ) {
		e.stopPropagation();
	});
	
	$('body').on({
		click: function() {
			$("#js_uiLoginBox").hide();
			$('form').find("#js_error_hint").hide();
//			$('form').find("#js_error_hint").css('display','none');
//			$("#js_uiLoginBox").css('display','none');
		}
	});
	
	//注销
	$('#logout').on({
		click: function(e){
			
			$.ajax({
		        type: "POST",
		        url: ctx + "/common/logout",
		        data: {},
		        dataType: "text",
		        success: function(data) {
		        	if(data){
		        		window.location.href = ctx + "/index.html"
		        	}
		        	else{
		        	}
		        }
	    	});
			
		}
	});
	
	$("#submitSearch").on("click", function (e) {
		$("#uiSearch form").submit();
		return false;
	});
});
