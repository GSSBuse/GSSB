/**
 *个人中心 
 */

pageData = window.pageData?window.pageData:{};

require(["domReady!", "plugin/validation/avalon.validation"], function(ready){
	
	var defaultInfo = {
		name: "",
		mobile: "",
		email: "",
		wx: "",
		qq: "",
		idcardNumber: "",
		authenticationMark: "",
		authenticationPositiveImageUrl: "",
		authenticationNegativeImageUrl: "",
		defaultIncomeExpense : "",
		payPassword : ""
	}
	
	defaultInfo = avalon.mix(defaultInfo, pageData);
	
	var vm = avalon.define({
		$id: "icenter",
		$skipArray: ["validation"],
		datas: {
			clientInfo : defaultInfo,
			tmp: {
				sys_verificationCode: ""
			}
		},
		validation: {
			onInit: function(v) {
                validationVM = v
            },
            onReset: function(e, data) {
                data.valueResetor && data.valueResetor()
                avalon(this).removeClass("error success")
                removeError(this)
            },
            resetInFocus: false,
            onError: function(reasons) {
                reasons.forEach(function(reason) {
                    avalon(this).removeClass("success").addClass("error")
                    showError(this, reason)
                }, this)
            },
            onSuccess: function() {
                avalon(this).removeClass("error").addClass("success")
                removeError(this)
            },
            onValidateAll: function(reasons) {
                reasons.forEach(function(reason) {
                    avalon(reason.element).removeClass("success").addClass("error")
                    showError(reason.element, reason)
                })
                if (reasons.length === 0) {
                	$("#submitChange").attr("disabled", true);
                	$.jBox.tip("提交处理中",'loading',{opacity:0});
                    
                	$.ajax({
				        type: 'POST',
				        url: ctx + '/icenter/dyInfoSave',
				        data: vm.datas.clientInfo,
				        success: function(resp) {
				        	if(resp.type == 'success'){
				        		$.jBox.tip("保存成功",'success');
				        		setTimeout(function(){
					        		window.location.reload();
				        		},1200);
				        	}
				        	else{
				        		$("#submitChange").attr("disabled", false);
				        		$.jBox.tip(resp.msg,'error');
				        	}
				        }
					});
                }
            }
        },
		//认证默认收支方式
		addDefaultIncomeExpense:function(e){
			if(!vm.datas.clientInfo.payPassword){
				$.jBox.tip("请先设置安全密码", 'error');
				return false
			}
			$.jBox(
					"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>银行卡号：<input class='line-input' type='text' id='defaultIncomeExpense' name='defaultIncomeExpense' value='"+vm.datas.clientInfo.defaultIncomeExpense+"'/></div><div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>安全密码：<input class='line-input' type='password' id='payPassword' name='payPassword' value=''/></div>" ,
					{
						title: "添加/修改银行账号", 
						buttons : {"确定":"yes","取消": "no"},
						submit: function (v, h, f){
							if(v == "yes"){
								var defaultIncomeExpense = $("#defaultIncomeExpense").val();
								var payPassword = $("#payPassword").val();
								if(defaultIncomeExpense ==""){
									$.jBox.tip("卡号不能为空", 'error');
									return false;
								}
								if(defaultIncomeExpense == vm.datas.clientInfo.defaultIncomeExpense){
									$.jBox.tip("不能与原来相同", 'error');
									return false;
								}
								var reg = /^[\d]{15,22}$/;//只能输入银行卡号格式
								if(!reg.test(defaultIncomeExpense)){
									$.jBox.tip("只能是银行卡号格式", 'error');
									return false;
								}
								if(payPassword == ""){
									$.jBox.tip("安全密码不能为空", 'error');
									return false;
								}
								var reg = /^[\d]{6}$/;//只能输入6位数字
								if(!reg.test(payPassword)){
									$.jBox.tip("安全密码只能6位数字", 'error');
									return false;
								}
								$.post(ctx+"/financialManagement/changeDefaultIncomeExpense", {defaultIncomeExpense:defaultIncomeExpense,payPassword : payPassword}, function(res){
									$.jBox.tip(res.msg);
									if(res.type == "success"){
										vm.datas.clientInfo.defaultIncomeExpense = defaultIncomeExpense;
									}
								});
							}else if(v == "no"){
								return true;
							}
						}
					}
				);
			
		},
        modifyMobile: function () {
        	var mobile = vm.datas.clientInfo.mobile;
        	$.jBox("<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>手机号码：<input class='line-input' type='text' id='txt' name='txt' value='"+mobile+"'/><button class='sendcaptcha' type='button'>发送验证码</button></div>" +
        			"<div class='form-search' style='padding:10px 5px 10px 30px;text-align:left;'>　验证码：<input class='line-input' type='text' id='chapt' name='chapt' value=''/></div>", 
        		{
					title: "设置手机号码", 
					loaded: function (v) {
						var cpbutton = $(v).find(".sendcaptcha");
						cpbutton.on("click", function(){
							cpbutton.text("验证码正在发送");
							$.ajax({
						    	url: ctx + "/icenter/sendCaptcha.json",
						    	type: "POST",
						    	data: {
						    		mobile: $(v).find("#txt").val()
						    	},
						    	success: function (resp) {
						    		if (resp.type == "success") {
						    			vm.datas.tmp.captcha = resp.data.sys_verificationCode;
						    			vm.datas.tmp.mobile = $(v).find("#txt").val();
						    			$.jBox.tip("验证码已经发送",'success',{opacity:0});
						    			var leftTime = 30;
						    			function countDown() {
						    				leftTime-=1;
						    				cpbutton.text("再次发送(" + leftTime+")");
						    				if (leftTime > 0) {
						    					setTimeout(countDown, 1000);
						    				} else {
						    					cpbutton.text("再次发送验证码");
						    				}
						    			}
						    			setTimeout(countDown, 1000);
						    		} else {
						    			$.jBox.error(resp.msg, '错误');
						    		}
						    	}
							});
						});
					},
					submit: function (v, h, f){
					    if (!/^\d{11}$/.test(f.txt)) {
					        top.$.jBox.tip("请输入11位数的手机号码", 'error');
					        return false;
					    }
					    
					    if (!/^\d{6}$/.test(f.chapt)) {
					        top.$.jBox.tip("请输入6位数字的验证码", 'error');
					        return false;
					    }
					    
					    if (vm.datas.tmp.mobile != f.txt) {
					    	top.$.jBox.tip("手机号码修改后，请重新发送验证码", 'error');
					        return false;
					    }
					    
					    //if (vm.datas.tmp.captcha == f.chapt) {
						    $.ajax({
						    	url: ctx + "/icenter/verificationPhone.json",
						    	type: "POST",
						    	data: {
						    		mobile: vm.datas.tmp.mobile,
						    		verificationCode: f.chapt
						    	},
						    	success: function (resp) {
						    		if (resp.type == "success") {
										$.jBox.tip(resp.msg,'success',{opacity:0});
										$.jBox.close($(h).closest(".jbox-body").attr("id"));
										vm.datas.clientInfo.mobile = vm.datas.tmp.mobile;
									} else {
										$.jBox.error(resp.msg, '错误');
									}
						    	}
						    });
					    //} else {
					    //	$.jBox.error("验证码错误", '错误');
					    //}
					    
					    return false;
					}
				}
			);
        },
        modifyEmail: function () {
        	var mobile = vm.datas.clientInfo.mobile;
        	var email = vm.datas.clientInfo.email;
        	var temp = "";
        	if (mobile) {
        		temp += "<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>手机号码："+mobile+" <button class='sendcaptcha' type='button'>发送验证码</button></div>" +
        			"<div class='form-search' style='padding:10px 5px 0px 30px;text-align:left;'>　验证码：<input class='line-input' type='text' style='width:160px;' id='chapt' name='chapt' value=''/></div>";
        	}
        	
        	temp += "<div class='form-search' style='padding:10px 5px 10px 30px;text-align:left;'>邮箱地址：<input class='line-input' type='text' style='width:160px;' id='email' name='email' value='"+email+"'/></div>";
        	
        	$.jBox(
        			temp, 
        		{
        			buttons : {
						'发送激活邮件到邮箱' : 'ok'
					},
					title: "设置邮箱", 
					loaded: function (v) {
						var cpbutton = $(v).find(".sendcaptcha");
						cpbutton.on("click", function(){
							cpbutton.text("验证码正在发送");
							$.ajax({
						    	url: ctx + "/icenter/sendCaptcha.json",
						    	type: "POST",
						    	data: {
						    		mobile: $(v).find("#txt").val()
						    	},
						    	success: function (resp) {
						    		if (resp.type == "success") {
						    			vm.datas.tmp.captcha = resp.data.sys_verificationCode;
						    			vm.datas.tmp.mobile = mobile;
						    			$.jBox.tip("验证码已经发送",'success',{opacity:0});
						    			var leftTime = 30;
						    			function countDown() {
						    				leftTime-=1;
						    				cpbutton.text("再次发送(" + leftTime+")");
						    				if (leftTime > 0) {
						    					setTimeout(countDown, 1000);
						    				} else {
						    					cpbutton.text("再次发送验证码");
						    				}
						    			}
						    			setTimeout(countDown, 1000);
						    		} else {
						    			$.jBox.error(resp.msg, '错误');
						    		}
						    	}
							});
						});
					},
					submit: function (v, h, f){
//					    if (!/^\d{11}$/.test(f.txt)) {
//					        $.jBox.tip("请输入11位数的手机号码", 'error');
//					        return false;
//					    }
						if (!/^([A-Z0-9]+[_|\_|\.]?)*[A-Z0-9]+@([A-Z0-9]+[_|\_|\.]?)*[A-Z0-9]+\.[A-Z]{2,3}$/i.test(f.email)) {
							$.jBox.tip("请输入正确的邮箱格式", 'error');
							return false;
						}
						
					    if (mobile) {
						    if (!/^\d{6}$/.test(f.chapt)) {
						        $.jBox.tip("请输入6位数字的验证码", 'error');
						        return false;
						    }
//					    
						    //if (vm.datas.tmp.captcha != f.chapt) {
						    //	$.jBox.tip("验证码错误", 'error');
						    //	return false;
						    //}
					    }
					    $(h).find("button").attr("disabled", true);
					    $.jBox.tip("邮件正在发送",'loading');
					    $.ajax({
					    	url: ctx + "/icenter/emailInput.json",
					    	type: "POST",
					    	data: {
					    		email: f.email,
					    		verificationCode: f.chapt
					    	},
					    	success: function (resp) {
					    		if (resp.type == "success") {
									$.jBox.tip(resp.msg,'success',{opacity:0});
									$.jBox.close($(h).closest(".jbox-body").attr("id"));
								} else {
									$.jBox.error(resp.msg, '错误');
								}
								$.jBox.closeTip();
					    	}
					    });
					    
					    return false;
					}
				}
			);
        }
	});
	
	function removeError(el) {
		$(el).next(".reason").remove();
	}
	
	function showError(el, reason) {
		var r = $(el).next(".reason");
		if (r.size() == 0) {
			r = $("<span>");
			r.addClass("reason");
		}
		r.text(reason.message);
		$(el).after(r);
	}
	
	avalon.scan();
	
})