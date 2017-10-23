/**
 *个人中心 
 */

pageData = window.pageData?window.pageData:{};

require(["domReady!", "plugin/validation/avalon.validation"], function(ready){
	
	var defaultInfo = {
		idcardNumber: "",
		authenticationMark: "",
		authenticationPositiveImageUrl: "",
		authenticationNegativeImageUrl: ""
	}
	
	defaultInfo = avalon.mix(defaultInfo, pageData);
	
	var vm = avalon.define({
		$id: "idcardAuth",
		$skipArray: ["validation"],
		datas: {
			clientInfo : defaultInfo,
			tmp: {
				
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
                
                var p1 = $("#authenticationPositiveImage");
                if (p1.attr("src").substring((ctx.length)) == "/static/images/upimage.jpg") {
                	avalon(p1[0]).removeClass("success").addClass("error")
                	var err = {message:"请选择", img:true}
                	reasons.push(err)
                    showError(p1[0], err)
                } else {
                	avalon(p1[0]).removeClass("error").addClass("success")
                	removeError(p1[0])
                }
                
                var p2 = $("#authenticationNegativeImage");
                if (p2.attr("src").substring((ctx.length)) == "/static/images/upimage.jpg") {
                	avalon(p2[0]).removeClass("success").addClass("error")
                	var err = {message:"请选择", img:true}
                	reasons.push(err)
                    showError(p2[0], err)
                } else {
                	avalon(p2[0]).removeClass("error").addClass("success")
                	removeError(p2[0])
                }
                
                if (reasons.length === 0) {
                	$("#submitChange").attr("disabled", true);
                	$.jBox.tip("提交处理中",'loading',{opacity:0});
                	
                	$("#accountform").submit();
                }
            }
        },
        choosePositive: function (e) {
        	if (e.target.files) {
        		var f = e.target.files[0];
        		var url = URL.createObjectURL(f);
	        	$("#authenticationPositiveImage").attr("src", url);
        	} else {
        		$("#authenticationPositiveImage").attr("src", ctx+"/static/images/userinfo.jpg");
        	}
        },
        chooseNegative: function (e) {
        	if (e.target.files) {
        		var f = e.target.files[0];
        		var url = URL.createObjectURL(f);
	        	$("#authenticationNegativeImage").attr("src", url);
        	} else {
        		$("#authenticationNegativeImage").attr("src", ctx+"/static/images/userinfo.jpg");
        	}
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
			
			if (reason.img) {
				r.css("top", 100);
				r.css("position", "absolute")
			}
		}
		r.text(reason.message);
		$(el).after(r);
	}
	
	if (!window.URL || !window.URL.createObjectURL) {
		$("#authenticationPositiveImage").click(function() {
			$(this).nextAll("input").click();
		});
		
		$("#authenticationNegativeImage").click(function() {
			$(this).nextAll("input").click();
		});
	}
	
	avalon.scan();
	
})