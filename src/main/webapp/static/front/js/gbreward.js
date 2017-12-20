
/**
 * 悬赏信息
 */
 
require(["plugin/validation/avalon.validation", "domReady!"], function() {
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
});