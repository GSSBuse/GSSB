
/**
 * 买标
 */
 
require(["utils"], function(utils) {
	var now = new Date();
	var buyInfo = {
		user: "",
		typeid: "",
		price: "",
		realprice:"",
		linkman:"",
		mobile:"",
		description:"",
		title:""
	}
	
	var validationVM = null;
	var vm = avalon.define({
		$id : "buy-dialog",
		datas : {
			domainInfo : buyInfo
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
                	$("#___").attr("disabled", true);
                	$.jBox.tip("提交处理中",'loading',{opacity:0});
                    $.ajax({
						url: ctx + "/buy.json",
						data: vm.datas.domainInfo,
						type: "POST",
						success: function (resp) {
							if (resp.type == "success") {
								$.jBox.tip("提交成功",'success',{opacity:0});
								setTimeout(function(){
									window.location.href = ctx + "/___";
								}, 1000);
							} else {
								$.jBox.error(resp.msg, '错误', {border:5}); 
								$("#___").attr("disabled", false);
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
	
	
	
	// 轮询buy数据
	var buy_check = function() {
		
		var domainInfo = [];
		
		$.post(
			ctx + "/buy.json",
			{
				domainInfo : buyInfo
				
			},
			function(res) {
				if (res.type == "success") {
					
						for (var i = 0; i < res.data.ibuyData.length; i++) {
							vm.datas.domainInfo[i].user = res.data.buy[i].user;
							vm.datas.domainInfo[i].price = res.data.buy[i].price;
							vm.datas.domainInfo[i].realprice = res.data.buy[i].realprice;
							vm.datas.domainInfo[i].linkman = res.data.buy[i].linkman;
							vm.datas.domainInfo[i].mobile = res.data.buy[i].mobile;
							vm.datas.domainInfo[i].description = res.data.buy[i].description;
							vm.datas.domainInfo[i].title = res.data.buy[i].title;
						
						}
					
				}
				
			}
		);
	}
	
});