
(function() {
var factory = function(){
	//标准处理日期，生成Date
	parseDateStr = function (date){
		var dateRE = /^(\d{4})(?:\-|\/)(\d{1,2})(?:\-|\/)(\d{1,2})(?:\s)(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
		return dateRE.test(date) ? new Date(parseInt(RegExp.$1, 10), parseInt(RegExp.$2, 10) - 1, parseInt(RegExp.$3, 10),
				parseInt(RegExp.$4, 10), parseInt(RegExp.$5, 10), parseInt(RegExp.$6, 10)) : null;
	} 
	
	//优化价格显示
	priceDisplay = function(amount){
		if(amount<10000){
			return amount;
		}
		if(amount>=10000){
			return amount/10000+"万";
		}
	}
	//生成分享地址 herf为window.location.herf的值，shareFrom为分享动作来源入ibuy.html
	var shareHref = function(href,shareFrom){
		var href = href.substring(0,href.indexOf('domainname/')+11);
		var url = href+shareFrom;
		return url;
	}

	// 毫秒数转换时分秒(提供时分秒返回值，默认时分秒的显示方式)
	var millisecondToDate = function(msd) {
		if(msd >= 1000){
			var time = parseFloat(msd) / 1000;
			time = parseInt(time);
			if (null != time && "" != time) {
				var hours = mins = secs = 0;
				if (time > 60 && time < 60 * 60) {
					mins = parseInt(time / 60);
					secs = time % 60;//parseInt((parseFloat(time / 60.0) - parseInt(time / 60.0)) * 60);
					time = {
							day : 0,
							hours : 0,
							mins : mins,
							secs : secs
					}
				} else if (time >= 60 * 60) { // time >= 60 * 60 && time < 60 * 60 * 24
					hours = parseInt(time / 3600);
					time = time % 3600;//扣除小时数
					if(time != 0){
						mins = parseInt(time / 60);//parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60);
						time = time % 60;//扣除分钟数
						secs = time;//parseInt((parseFloat((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60) - parseInt((parseFloat(time / 3600.0) - parseInt(time / 3600.0)) * 60)) * 60);
					}else{
						mins = 0;
						secs = 0;
					}
					day = parseInt(hours/24);
					hours = hours%24;
					time = {
							day : day,
							hours : hours,
							mins : mins,
							secs : secs
					}
//				if (mins == 0) {
//					time = {
//							hours : hours - 1,
//							mins : 60,
//							secs : secs
//					}
//				} else {
//					time = {
//							hours : hours,
//							mins : mins,
//							secs : secs
//					}
//				}
				} else {
					secs = parseInt(time);
					time = {
							day : 0,
							hours : 0,
							mins : 0,
							secs : secs
					}
				}
			}
			
		}else{
			time = {
					day : 0,
					hours : 0,
					mins : 0,
					secs : 0
			}
		}
		
		var displayTime = (time.day? time.day+"天":"") + (time.hours < 10 ? ("0" + time.hours) : time.hours) + "时"
				+ (time.mins < 10 ? ("0" + time.mins) : time.mins) + "分"
				+ (time.secs < 10 ? ("0" + time.secs) : time.secs) + "秒";
		return {
			time : time,
			displayTime : displayTime
		}
	}

	var loadmore = function(element, callback) {
		var self = $("#" + element);
		self.removeClass("nomore");
		self.removeClass("hide-loadmore");
		if (self.data("loadmore-init") == "true") {
			return;
		}
		self.data("loadmore-init", "true");
		self.data("loadmore-status", "ready");
		try {
			var parentPage = $(self).closest(".page");
			
			var checkload = function(e){
				if (self.hasClass("nomore")) {
					return true;
				}
				$(self).show();
				if (self.data("loadmore-status") != "ready") {
					return true;
				}
				self.data("loadmore-status", "checking");
				
				
				var item = self[0],
					eleTop = item.offsetTop,
					eleHeight = item.offsetHeight,
					winTop = parentPage.scrollTop(),//document.compatMode === "BackCompat" ? document.body.scrollTop : document.documentElement.scrollTop,
					winHeight = document.compatMode === "BackCompat" ? document.body.clientHeight : document.documentElement.clientHeight;
	
				if(winTop === 0){ //修正chrome下取不到的問題
					winTop = document.body.scrollTop;
				}
				
				if (parentPage.data("scroll")) {
					var scl = $.m.Scroll(parentPage);
					winTop = -scl.scroller.y;
					winHeight = scl.scroller.wrapperH;
				}
//					console.log("eleTop:" + eleTop + ",eleHeight:" + eleHeight + ",winTop:" + winTop + ",winHeight:" + winHeight);
//					if (eleTop < winTop + winHeight && eleTop + eleHeight > winTop) {
				var merge = 10;
				merge = !$(self).hasClass("has-footer") ? merge : merge + $("footer").height();
				var delta = (eleTop) - (winTop + winHeight-merge);
				parentPage.find("#debug").html(
					"eleTop:" + eleTop + "<br>"+
					"merge:" + merge + "<br>"+
					"winTop:" + winTop + "<br>"+
					"winHeight:" + winHeight + "<br>"+
					"delta:" + delta + "<br>"+
					""
				);
				if (delta < 300) {
					if (!$(self).hasClass("nomore") && self.data("loadmore-status") == "checking") {
						self.data("loadmore-status", "loading");
						setTimeout(function(){
								callback(function(type){
									if(type === "warn") {
										$(self).addClass("nomore");
									} else {
										//$(self).hide();
									}
									//$(self).hide();
									self.data("loadmore-status", "ready");
								});
						}, 1);
					} else if (self.data("loadmore-status") == "checking"){
						self.data("loadmore-status", "ready");
					}
				} else {
					self.data("loadmore-status", "ready");
				}
				
				return true;
			}
			
			var eventName = "touchmove";
			if (parentPage.data("scroll")) {
				eventName = "scrollEnd";
				parentPage.on(eventName, checkload);
			} else {
				setInterval(checkload, 1000);
			}
		} catch (e) {
			alert(e);
		}
	}

	var getUrlParam = function(name) {
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		var value = "";
		if(r != null) {
			value = unescape(r[2]);
		}
		return value;
	}
	
	var jscache = {
		cache : {},
		getV : function(key) {
			return jscache.cache[key];
		},
		setV : function (key, value) {
			jscache.cache[key] = value;
		},
		clear : function() {
			jscache.cache = {};
		}
	}

	return {
		millisecondToDate : millisecondToDate,
		loadmore : loadmore,
		getUrlParam : getUrlParam,
		shareHref : shareHref,
		priceDisplay:priceDisplay,
		parseDateStr : parseDateStr,
		jscache : jscache
	}
};

	if (typeof window.define == "function") {
		if (define.amd || define.cmd) {
			define("utils", factory);
		}
	} else {
		window.utils = factory();
	}
})();