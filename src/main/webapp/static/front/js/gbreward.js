function rewardSubmit(){
	 var id = $("#rewardform").find("#id").eq(0).val();
     var title = $("#rewardform").find("#title").eq(0).val();
     var mobile = $("#rewardform").find("#mobile").eq(0).val();
     var realname = $("#rewardform").find("#realname").eq(0).val();
     var description = $("#rewardform").find("#description").eq(0).val();
     var price = $("#rewardform").find("#price").eq(0).val();
     if($(".tips ").is(":visible")){
         return false;
     }
     if(description == null  || description == ""){
         showError("请输入起名需求");
         return false;
     }
     if(price == null  || price == ""){
         showError("请输入悬赏金额");
         return false;
     }
     if(realname == null  || realname == ""){
         showError("请输入联系人");
         return false;
     }
     if(mobile == null  || mobile == ""){
         showError("请输入联系电话");
         return false;
     }
	
	
	
		$("#qrcode").attr("style", "display:block;");
		$("#price").attr("readonly","readonly");
		var wxurl = "";
		var bodys = "悬赏金额支付";
		var totalFees = $("#price").val();
		$.ajax({
 			url : ctx + "/polling/wechatPay.json",
 			type : "POST",
 			data : {
 				bodys : bodys,
				totalFees : totalFees
			
			},
        	dataType : 'json',
        	async : false,
			success : function(res) {
				if (res.type == 'success') {
					wxurl = res.data.wechatPay;
				} else {					
					// 错误消息处理
					return false;
				}
			},
			error : function(res) {				
				// 错误消息处理
				return false;
			}
		});

		if(wxurl == ""){
			//没有正确生成url，就返回，页面上可以提示错误消息
			alert("请您刷新页面重新输入!");
			return false;
		}
		else{
			
			$("#submit").unbind("onclick");
			
			//参数1表示图像大小，取值范围1-10；参数2表示质量，取值范围'L','M','Q','H'
			var qr = qrcode(10, 'M');
			qr.addData(wxurl);
			qr.make();
			var dom=document.createElement('DIV');
			dom.innerHTML = qr.createImgTag();
			var element=document.getElementById("qrcode");
			element.appendChild(dom);
			
		}
		
		return false;
		//$("#submit").removeAttr("onclick");
	
		var x = 1;
		var y = 2;
		if(x == y){
			
		        var ajaxResult;
		        $.ajax({
					url : ctx + "/gbReward.json",
					type : "POST",
					data : {
						id : id,
						mobile : mobile,
						realname : realname,
						price:price,
						description:description
					},
		        	dataType : 'json',
		        	async : false,
					success : function(data) {
						if (data.type == 'success') {
							showSuccess("提交成功！请等待审核！");
						} else {
							showError(data.msg);
							ajaxResult = false;
							return false;
						}
					},
					error : function(data) {
						showError(data.responseText);
						ajaxResult = false;
						return false;
					}
				});
		        
		        if (ajaxResult == false) {
		        	return false;
		        }
		        return true;
		    }
		}
	
		

	
       

/**
 * 悬赏信息
 */
 
/*require(["plugin/validation/avalon.validation", "domReady!"], function() {
	var now = new Date();
	var defaultInfo = {
		description: "",
		title:"",
		mobile:"",
		realname:"",
		price: ""
	
	}
	
	var validationVM = null;
	var vm = avalon.define({
		$id : "reward-dialog",
		datas : {
			domainInfo3 : defaultInfo
		},
		validation: {
			validationHooks:{},
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
                	$("#submit03").attr("disabled", true);
                	//$.jBox.tip("提交处理中",'loading',{opacity:0});
                    $.ajax({
						url: ctx + "/gbreward.json",
						data: vm.datas.domainInfo3,
						type: "POST",
						success: function (resp) {
							if (resp.type == "success") {
								alert("提交成功！");
							//	$.jBox.tip("提交成功",'success',{opacity:0});
								setTimeout(function(){
									window.location.href = ctx + "/rewardarticles.html";
								}, 1000);
							} else {
								//$.jBox.error(resp.msg, '错误', {border:5}); 
								$("#submit03").attr("disabled", false);
							}
						}
					});
                }
            }
        }
	});
	avalon.scan();
	
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
});*/