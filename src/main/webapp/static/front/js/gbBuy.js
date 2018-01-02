function buySubmit(){
        var id = $("#buyform").find("#id").eq(0).val();
        var title = $("#buyform").find("#title").eq(0).val();
        var mobile = $("#buyform").find("#mobile").eq(0).val();
        var realname = $("#buyform").find("#realname").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        if(title == null  || title == ""){
            showError("请输入商标名称");
            return false;
        }
        if(mobile == null  || mobile == ""){
            showError("请输入联系电话");
            return false;
        }
        if(realname == null  || realname == ""){
            showError("请输入联系人");
            return false;
        }
        var ajaxResult;
        $.ajax({
			url : ctx + "/gbBuy.json",
			type : "POST",
			data : {
				id : id,
				title : title,
				mobile : mobile,
				realname : realname
			},
        	dataType : 'json',
        	async : false,
			success : function(data) {
				if (data.type == 'success') {
					showSuccess("发布成功！请等待审核！");
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
function showSuccess(success) {
	$(".form-success").find("label").html(success);
	$(".form-success").show();
}

/**
 * 买标信息
 */
 
/*require(["plugin/validation/avalon.validation", "domReady!"], function() {
	var now = new Date();
	var defaultInfo = {
		realname: "",
		mobile: "",
		title: "",
		price:"10",
		user_id:'d31a81fa72c745a998401713a1e43f0f'
	}
	
	var validationVM = null;
	var vm = avalon.define({
		$id : "buy-dialog",
		datas : {
			domainInfo1: defaultInfo
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
                	$("#submit01").attr("disabled", true);
                	//$.jBox.tip("提交处理中",'loading',{opacity:0});
                    $.ajax({
						url: ctx + "/gbBuy.json",
						data: vm.datas.domainInfo1,
						type: "POST",
						success: function (resp) {
							if (resp.type == "success") {
								//$.jBox.tip("提交成功",'success',{opacity:0});
								setTimeout(function(){
									window.location.href = ctx + "/buyarticles.html";
								}, 1000);
							} else {
								//$.jBox.error(resp.msg, '错误', {border:5}); 
								$("#submit01").attr("disabled", false);
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