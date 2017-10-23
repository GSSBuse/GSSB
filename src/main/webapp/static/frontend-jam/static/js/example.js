
// 根据页面需要，载入相应的js文件
define([ 
	"jquery",  // 
	"avalon",  // 
	"growl",   // 消息提示组件
	"bootstrap",  // 
	"domReady",   // 页面加载完成的方法
	"avalon-plugin/validation/avalon.validation", // 验证框架 
	"css!vendors-lib/animate/animate.min.css",
	"css!../css/example.css"  // 加载页面css
], function($, avalon, growl, b, ready){
	console.log("do example");
	
	// 定义tabs的动作
	
    var validationVM;
	var vm = avalon.define({
		$id : "tabs",
		userselect : {
			clickText : "click"
		},
		$skipArray : ["userselect", "validation"],
		data : {
			// 所有tab的列表
			tablist : [],
			
			// 当前显示的内容列表
			currentIndex : 0,
			infolist: [],
			a:"`12"
		}, 
		setTabs : function (tablist) {
			vm.data.tablist = tablist;
			if (tablist && tablist.length > 0) {
				vm.data.infolist = tablist[0].infolist;
			}
		},
		changeTab : function (n) {
			vm.data.currentIndex = n;
			vm.data.infolist = vm.data.tablist[n].infolist;
			
		},
		
		// 验证 演示
		a: "xxxx",
        b: "a",
        c: "d",
        d: "",
        e: "",
        f: "",
        g: "",
        reset: function() {
            validationVM && validationVM.resetAll()
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
                    avalon.log("全部验证成功！")
                }
            }
        }
	});
	
	// 自定义如何显示错误
    function showError(el, data) {
        var next = el.nextSibling
        if (!(next && next.className === "error-tip")) {
            next = document.createElement("div")
            next.className = "error-tip"
            el.parentNode.appendChild(next)
        }
        next.innerHTML = data.getMessage()
    }
    // 自定义如何消除错误
    function removeError(el) {
        var next = el.nextSibling
        if (next && next.className === "error-tip") {
            el.parentNode.removeChild(next)
        }
    }
	
	
	// 模拟读取
	setTimeout(function () {
		
		var tablist = [
			{
				name : "世界经济",
				infolist : [
					"经济1",
					"经济2",
					"经济3",
					"经济4",
					"经济5"
				]
			},
			{
				name : "世界文化",
				infolist : [
					"文化1",
					"文化2",
					"文化3",
					"文化4"
				]
			}
		];
		
		vm.setTabs(tablist);
	},1000);
	ready( function () {
		avalon.scan();
	});
	

	return vm;
});