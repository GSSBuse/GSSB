require(["plugin/validation/avalon.validation", "domReady!"], function() {
	var now = new Date();
	var defaultInfo = {
		name: "",
		mobile: "",
		qq:"",
		searchContents: ""
	}
	//alert(test);
	var validationVM = null;
	var vm = avalon.define({
		$id : "searchdialog",
		datas : {
			domainInfo : defaultInfo
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
                	$("#submitChange").attr("disabled", true);
                	//$.jBox.tip("提交处理中",'loading',{opacity:0});
                    $.ajax({
						url: ctx + "/searchBrand.json",
						data: vm.datas.domainInfo,
						type: "POST",
						success: function (resp) {
							if (resp.type == "success") {
							//	$.jBox.tip("提交成功,我们会尽快联系您！",'success',{opacity:0});
								alert("提交成功！");
								setTimeout(function(){
									window.location.href = ctx + "/index1.html";
								}, 1000);
							} else {
							//	$.jBox.error(resp.msg, '提交错误，请稍后重试。', {border:5}); 
								$("#submitChange").attr("disabled", false);
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