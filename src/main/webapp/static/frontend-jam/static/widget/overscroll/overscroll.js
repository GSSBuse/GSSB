
define(["avalon", "text!./overscroll.html", "css!./overscroll.css", "domReady!"], function(avalon, template){
	//console.log("overscroll.js")
	var widget = avalon.ui.overscroll = function(element, data, vmodels) {
		var options = data.overscrollOptions; //★★★取得配置项

		var tmp = template.replace("MS_OPTION_DIR", options.direction);
		
		options.template = options.getTemplate(tmp, options);
		
		
        var getYPos = function (e) {
        	var originEvent;
        	if (e.originalEvent) {
        		origin = e.originalEvent;
        	} else {
        		origin = e;
        	}
        	
        	if (origin.touches) {
        		return origin.touches[0].screenY;
        	} else {
        		return origin.screenY;
        	}
//        	return window.scrollY;
        }
        
        function debuglog(str) {
        	if (vmodel.debug) {
        		var debugConsole = document.getElementById("debug");
        		var overscrollDebug = document.getElementById("overscroll-debug");
        		if (!overscrollDebug) {
        			overscrollDebug = document.createElement("div");
        			overscrollDebug.id = "overscroll-debug";
        			debugConsole.appendChild(overscrollDebug);
        		}
        		
        		overscrollDebug.innerHTML = str;
        	}
        }
            
		var vm = {
			$id : data.overscrollId,
			$init : function() {//初始化组件的界面，最好定义此方法，让框架对它进行自动化配置
                
                element.innerHTML = vmodel.template;
                
                avalon.scan(element, [vmodel].concat(vmodels))
                if(typeof vmodel.onInit === "function"){
                   vmodel.onInit.call(element, vmodel, options, vmodels);
                }
                
                vmodel.data.tipText = vmodel.pullText;
                avalon(element).addClass(this.widgetClass);
                //console.log(element.parentNode);
                avalon.bind(element.parentNode, "touchstart", vmodel.start);
                avalon.bind(element.parentNode, "touchend", vmodel.end);
                avalon.bind(element.parentNode, "touchmove", vmodel.move);
			},
			$remove : function() {//清空构成UI的所有节点，最好定义此方法，让框架对它进行自动化销毁
                element.innerHTML =  "";
                avalon.unbind(element.parentNode, "touchstart", vmodel.start);
                avalon.unbind(element.parentNode, "touchend", vmodel.end);
                avalon.unbind(element.parentNode, "touchmove", vmodel.move);
            },
            $data : {
            	startPos : 0,
            	nowPos : 0,
            	drag : false
            },
            data : {
            	tipText : "",
            	paddingTop : 0
            },
            start : function (e) {
            	debuglog("window.scrollY:" + window.scrollY + 
            		"<br>element.parentNode.scrollHeight:" +element.parentNode.scrollHeight + 
            		"<br>window.screen.height:" + window.screen.height
            		);
            	
            	if (vmodel.direction == "down") {
	            	if (window.scrollY <= 5) {
	            		vmodel.$data.drag = true;
	            	}
            	} else {
            		if (window.scrollY == (element.parentNode.offsetTop + element.parentNode.scrollHeight-window.screen.height)) {
	            		vmodel.$data.drag = true;
	            	}
            	}
				vmodel.$data.startPos = getYPos(e);
				vmodel.$data.nowPos = vmodel.$data.startPos;
            },
            end : function (e) {
            	if (!vmodel.$data.drag) {
            		return;
            	}
            	var yPos = vmodel.$data.nowPos;
            	var height =  vmodel.$data.nowPos - vmodel.$data.startPos;
            	if (vmodel.direction == "up") {
            		height = -height;
            	}
            	var data = {
            		height : height,
            		yPos : yPos
            	}
            	var doAction = vmodel.pullEndAnimate(element, data);
            	if (doAction) {
	            	vmodel.pullAction(element, data, function (data) {
	            		vmodel.refreshEndAnimate(element, data);
	            	});
            	} else {
            		vmodel.refreshEndAnimate(element, data);
            	}
            	vmodel.$data.drag = false;
            },
            move : function(e) {
            	if (!vmodel.$data.drag) {
            		return;
            	}
            	var yPos = getYPos(e);
            	vmodel.$data.nowPos = yPos;
            	var height =  yPos - vmodel.$data.startPos;
            	if (vmodel.direction == "up") {
            		height = -height;
            	}
            	avalon(element).css("height", height);
            	
            	vmodel.pullAnimate(element, {
            		height : height,
            		yPos : yPos
            	});
            }
        }
		avalon.mix(vm, options);
        
		var vmodel = avalon.define(vm);
		return vmodel;
	}
		
	widget.defaults = {//默认配置项
		debug: false,
		direction : "down", // up|down 向上还是向下拉
		
		// 下拉提示对象的 class
		widgetClass : "pullElement",
		
        pullText: "松开以进行刷新",
        pullLoadingText: "加载中...",
        pullAction: function (element, data, callback) {
        	setTimeout(function(){
        		callback({});
        	}, 1000)
        },
        
        /**
         * 生成模版方法
         * @param tpl 原有模版
         * @param ops 选项
         */ 
        getTemplate: function (tpl, ops) {
        	return tpl;
        },
        
        /**
         * 下拉时执行的动画
         * @param element 组件dom对象
         * @param data 下拉时的参数 {height, yPos}
         */ 
        pullAnimate: function (element, data) {
        	if (data.height > 40) {
        		avalon(element).addClass("flip")
        		
        		if (this.direction == "down") {
        			this.data.paddingTop = data.height-40;
        		}
        	} else {
        		avalon(element).removeClass("flip")
        	}
        },
        /**
         * 下拉松手后的动作
         * @param element 组件dom对象
         * @param data 下拉时的参数 {height, yPos}
         * 
         * @return 返回true时，说明执行action方法, 否则不执行
         */
        pullEndAnimate: function (element, data) {
        	if (data.height <= 40) {
        		return false;
        	}
        	
        	avalon(element).removeClass("flip");
        	avalon(element).addClass("loading");
        	avalon(element).css("height", 40);
        	this.data.paddingTop = 0;
        	this.data.tipText = this.pullLoadingText;
        	return true;
        },
        /**
         * 动作结束后的动作
         * @param element 组件dom对象
         * @param data 下拉时的参数 {height, yPos}
         */
        refreshEndAnimate: function (element, data) {
        	this.data.tipText = this.pullText;
        	avalon(element).removeClass("loading")
        	avalon(element).css("height", 0);
        }
    }
    
	return avalon;
});