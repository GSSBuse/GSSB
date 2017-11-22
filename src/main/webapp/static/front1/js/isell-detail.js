
/**
 * 首页
 */
 
domainInfo = window.domainInfo?window.domainInfo:{};

require(["plugin/datepicker/avalon.datepicker", "plugin/validation/avalon.validation", "domReady!"], function() {
	var now = new Date();
	var defaultInfo = {
		name: "",
		description: "",
		reservePrice: 0,
		// 第二天
		endTime: avalon.ui.datepicker.defaults.formatDate(new Date(now.getTime() + 1000*3600*24*1)),
		bonusSecond: 0
	}
	
	defaultInfo = avalon.mix(defaultInfo, domainInfo);
	
	var checkList = [];
	if (defaultInfo.bonusSecond>0) {
		checkList.push("on");
	}
	defaultInfo.endTime = defaultInfo.endTime.substring(0, 10);
	
	var validationVM = null;
	var vm = avalon.define({
		$id : "isell-detail",
		$skipArray: ["datepicker", "validation"],
		datas : {
			domainInfo : defaultInfo,
			tmp : {
				check : checkList
			}
		},
		datepicker : {
			width: 120,
			minDate: now,
			maxDate: new Date(now.getTime() + 1000 * 3600 * 24 * 7)
		},
		validation: {
			validationHooks:{
	            domainname:{
	                message: "必须填入一个正确的域名",
	                get: function(value, data, next){
	                    next(/^[^\/+=()$#@!{};:'"\\|,.<>?*&\^$~`]+\.[^\/+=()$#@!{};:'"\\|,.<>?*&\^$~`]+$/.test(value))
	                    return value
	                }
	            }
	        },
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
						url: ctx + "/isell/save.json",
						data: vm.datas.domainInfo,
						type: "POST",
						success: function (resp) {
							if (resp.type == "success") {
								$.jBox.tip("提交成功",'success',{opacity:0});
								setTimeout(function(){
									window.location.href = ctx + "/isell.html";
								}, 1000);
							} else {
								$.jBox.error(resp.msg, '错误', {border:5}); 
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