// 框架初始化
//define([], function(){
//console.log("jq-common")
try {
		
	var config = {
		pageTransition : "slide",  // fade | slide
		pageLocation : ".",
		historyLength : 0,
		isBack : false
	}
	
	$.ajaxSettings.error = function(XMLHttpRequest, textStatus, errorThrown){
		if (textStatus != "abort") {
			$.tips({
				content : "出错:" + textStatus + ":" + errorThrown,
				stayTime:2000,
				type:"error"
			});
		}
	}
	
	$.m = {};
	
	$.m.stack = [];

	$( document ).on( "click", "a", function (e) {
		if (!$(this).attr("href")){
			return;
		}
		if ($(this).attr("rel") != "external") {
			if ($(this).attr("href") == "#") {
				e.preventDefault();
			}
			if ($(this).attr("href").charAt(0) != "#") {
				changePage($(this).attr("href"), {}, $(this).data("transition"), false);
				e.preventDefault();
			}
		}
	});
	
	function loadPage(href, pdata, transition) {
		$(document).trigger("pagepreload", {href:href});
		$.ajax({
			url : href,
			dataType : "html",
			success : function (data) {
				var toPage = $(data).find(".page");
				toPage.attr("data-url", href);
//				toPage.css({opacity:0, zIndex:0});
				
				$(".page-wrapper").append(toPage);
				
				toPage.removeClass("active");
				$(toPage).eq(0).data("trans", transition);
				$(document).trigger("pageload", {
					fromPage : $.m.nowPage,
					toPage : $(toPage).eq(0),
					transition :transition,
					data : pdata
				});
			},
			error : function (xhr, textStatus, errorThrown) {
			    // 通常 textStatus 和 errorThrown 之中
				var text = textStatus + ":" + errorThrown;
				if (xhr.status == 404) {
					text = "找不到文件：" + href;
				}
			    el=$.tips({
	                content: text,
	                stayTime:2000,
	                type:"warn"
	            })
			    this; // 调用本次AJAX请求时传递的options参数
			}
		})
	}
	
	/**
	 * 换页
	 * @param href 页面地址或锚点
	 * @param transition 页面变换方法
	 * @param 
	 */
	function changePage(href, data, transition, external, hashEvent) {
		if (external) {
			window.location.href = href;
		} else {
			var transition = transition ? transition : config.pageTransition;
			
			var toPage = false;
			var nowPage = $.m.nowPage;
			var back = false;
			
			
			//var href = $(this).attr("href");
			if (href.charAt(0) == "#") {
				
				var pageId = $.m.getHashPage(href);
				var pageParam = $.m.getHashParam(href);
				
				var pdata = $.extend({}, pageParam, data);
				
				if ($(pageId).size() != 0) {
					
					toPage = $(pageId);
					if (toPage.data("trans") != null) {
						transition = toPage.data("trans");
						toPage.removeData("trans");
					}
					if (hashEvent) {
						$(document).trigger("pagechange", {fromPage:nowPage, toPage:toPage, transition:transition, data:pdata});
					} else {
						var params = "";
						if (pdata) {
							params = "?"+$.param(pdata);
						}
						window.location.hash = "#" + toPage.attr("id")+params;
					}
				} else {
					// 没有复合条件的 元素。尝试加载pageLocation文件夹中的文件
					
					var pdata = $.extend({}, pageParam, data);
					
					var url = config.pageLocation + "/" + pageId.substring(1) + ".html" + "?"+$.param(pdata);
					toPage = $(".page[data-url='"+url+"']");
					
					if (!toPage || toPage.size() == 0) {
						// 第一次加载
						loadPage(url, pdata, transition);
					} else {
						toPage.data("trans", transition);
						var params = "";
						if (pdata) {
							params = "?"+$.param(pdata);
						}
						window.location.hash = "#" + toPage.attr("id")+params;
					}
				}
			} else {
				// URL时
				toPage = $(".page[data-url='"+href+"']");
				
				if (!toPage || toPage.size() == 0) {
					// 第一次加载
					loadPage(href, data, transition);
				} else {
					toPage.data("trans", transition);
					var params = "";
					if (data) {
						params = "?"+$.param(data);
					}
					window.location.hash = "#" + toPage.attr("id")+params;
				}
			}
		}
	}
	
	function changePageAnime(fromPage, toPage, trans, callback, back) {
		$.m.Transition.run(fromPage, toPage, "", callback, back);
	}
	
	$.m.Transition = (function($){
	    var isBack,$current,$target,transitionName,
	        animationClass = {
	        //[[currentOut,targetIn],[currentOut,targetIn]]
	        slide : [['slideLeftOut','slideLeftIn'],['slideRightOut','slideRightIn']],
	        cover : [['','slideLeftIn'],['slideRightOut','']],
	        slideUp : [['','slideUpIn'],['slideDownOut','']],
	        slideDown : [['','slideDownIn'],['slideUpOut','']],
	        popup : [['','scaleIn'],['scaleOut','']]
	        };
	
	    var _doTransition = function(callback){
	        //触发 beforepagehide 事件
	//        $current.trigger('beforepagehide',[isBack]);
	        //触发 beforepageshow 事件
	//        $target.trigger('beforepageshow',[isBack]);
	        var c_class = transitionName[0]||'empty' ,t_class = transitionName[1]||'empty';
	        
	        $current.addClass('animated '+ c_class);
	        $target.bind('webkitAnimationEnd.frozen', function(){_finishTransition(callback);}).addClass('animated animating '+ t_class);
	    }
	    var _finishTransition = function(callback) {
	        $current.off('webkitAnimationEnd.frozen');
	        $target.off('webkitAnimationEnd.frozen');
	        //reset class
	        $current.attr('class','page');
	        $target.attr('class','page active');
	        
	        //add custom events
	        !$target.data('init') && $target.trigger('pageinit').data('init',true);
	        !$current.data('init') && $current.trigger('pageinit').data('init',true);
	        //触发pagehide事件
	//        $current.trigger('pagehide',[isBack]);
	        //触发pageshow事件
	//        $target.trigger('pageshow',[isBack]);
			callback();
			
	//        $current.find('article.active').trigger('articlehide');
	//        $target.find('article.active').trigger('articleshow');
	        $current = $target = null;//释放
	    }
	
	    /**
	     * 执行转场动画，动画类型取决于目标page上动画配置(返回时取决于当前page)
	     * @param current 当前page
	     * @param target  目标page
	     * @param back  是否为后退
	     */
	    var run = function(current,target,type,callback, back){
	        //关闭键盘
	        $(':focus').trigger('blur');
	        isBack = back;
	        $current = $(current);
	        $target = $(target);
	        
	        if ($current.size() == 0) {
	        	$target.addClass("active");
	        	callback();
	        	return
	        }
	        var type = isBack?$current.attr('data-transition'):$target.attr('data-transition');
	        type = type|| config.pageTransition;
	        //后退时取相反的动画效果组
	        transitionName  = isBack ? animationClass[type][1] : animationClass[type][0];
	        _doTransition(callback);
	    }
	
	    /**
	     * 添加自定义转场动画效果
	     * @param name  动画名称
	     * @param currentOut 正常情况下当前页面退去的动画class
	     * @param targetIn   正常情况下目标页面进入的动画class
	     * @param backCurrentOut 后退情况下当前页面退去的动画class
	     * @param backCurrentIn 后退情况下目标页面进入的动画class
	     */
	    var addAnimation = function(name,currentOut,targetIn,backCurrentOut,backCurrentIn){
	        if(animationClass[name]){
	            console.error('该转场动画已经存在，请检查你自定义的动画名称(名称不能重复)');
	            return;
	        }
	        animationClass[name] = [[currentOut,targetIn],[backCurrentOut,backCurrentIn]];
	    }
	    return {
	        run : run,
	        add : addAnimation
	    }
	
	})($);
	
	$(document).on("pagechange", function (e, d) {
		var back = false;
		if ($.m.stack) {
			// 当前页
			var p1 = $.m.stack.pop();
			// 历史前页
			var p2 = $.m.stack.pop();
			back = p2 == $(d.toPage).attr("id");
			
			$.m.stack.push(p2);
			if (!back) {
				$.m.stack.push(p1);
			}
		}
		
		if (!back) {
			$.m.stack.push($(d.toPage).attr("id"));
		}
		
		$(d.toPage).trigger("pagepreshow", d);
		$(d.fromePage).trigger("pageprehide", d);
//		var l = window.history.length;
//		config.isBack = l <= config.historyLength;
		
		changePageAnime(d.fromPage, d.toPage, d.transition, function(){
			$.m.loading.hide();
			$(d.toPage).trigger("pageshow", d);
			$(d.fromPage).trigger("pagehide", d);
		}, back);
		
//		config.historyLength = l;
	});
	
	$(document).on("pagepreload", function (e, d) {
		$.m.loading.show();
	});
	
	(function($){
	    var scrollCache = {},index = 1;
	    $.m.Scroll = function(selector,opts){
	        var scroll,scrollId,$el = $(selector),
	            options = {
	               hScroll : false,
	               bounce : false,
	               lockDirection : true,
	               useTransform: true,
	               useTransition: false,
	               checkDOMChanges: false,
	               onBeforeScrollStart: function (e) {
	               		$el.trigger("beforeScrollStart",e);
	                    e.preventDefault();
	                }
	                ,onScrollStart: function (e) {
	                    $el.trigger("scrollStart",e);
	                }
//	                ,onScrollMove: function(e) {
//	                	$el.trigger("scrollMove",e);
//	                }
	                ,onBeforeScrollEnd : function (e) {
	                	$el.trigger("beforeScrollEnd",e);
	                }
	                ,onScrollEnd: function (e) {
	                	$el.trigger("scrollEnd",e);
	                }
	                ,onTouchEnd: function (e) {
	                	$el.trigger("scrollTouchEnd",e);
	                }
	            };
	        scrollId = $el.data('_jscroll_');
	        //滚动组件使用频繁，缓存起来节省开销
	        if(scrollId && scrollCache[scrollId]){
	            scroll = scrollCache[scrollId];
	            $.extend(scroll.scroller.options,opts)
	            scroll.scroller.refresh();
	            return scroll;
	        }else{
	            scrollId = '_jscroll_'+index++;
	            $el.data('_jscroll_',scrollId);
	            $.extend(options,opts);
	            scroller = new iScroll($el[0],options);
	            return scrollCache[scrollId] = {
	                scroller : scroller,
	                destroy : function(){
	                    scroller.destroy();
	                    delete scrollCache[scrollId];
	                }
	            };
	        };
	    }
	})($);
	
	$(document).on("pageloaded", function (e, d) {
		var to = $(e.target);
		var p = to.find("*[data-scroll='true']");
		
		if (to.is("*[data-scroll='true']")) {
			$.m.Scroll(to);
		}
		
		if (p.size() > 0) {
			$.each(p, function(index, item) {
				$.m.Scroll(item);
			})
		}
        
	});
	
	$(document).on("pagepreshow", function (e, d) {
		avalon.scan($(d.toPage)[0]);
	});
	
	$(document).on("pageshow", function (e, d) {
		$.m.nowPage = $(d.toPage);
		
		// 如果设定的title的话 -不要
//		if ($.m.nowPage.data("title")) {
//			document.title = $.m.nowPage.data("title")
//		}
		
		console.log("page show :" + $(d.toPage).attr("id"));
	});
	
	$(document).on("pageload", function (e, d) {
		// 加载 data-page脚本
		var scripts = [];
		$(d.toPage).find("script[data-page]").each(function(){
			scripts.push($(this).data("page"));
		});
		
		require(scripts, function(){
			$(d.toPage).trigger("pageloaded", {});
			if ($.m.getHashPage() == "#"+$(d.toPage).attr("id")) {
				$(document).trigger("pagechange", d);
			} else {
				var params = "";
				if (d.data) {
					params = "?"+$.param(d.data);
				}
				window.location.hash = $(d.toPage).attr("id") + params;
			}
		});
	});
	
	$(window).hashchange(function(e, d) {
		if (!$.m.nowPage) {
			// 初始化时隐藏所有page
			//$(".page").hide();
//				$(".page").css({opacity:0, zIndex:0});
		}
			
		var page = $.m.getHashPage();
		if (page != "" && page != "#") {
//					if (d && d.init) {
//						$(page).trigger("pageloaded", {});
//					}
			changePage(page, {}, null, null, true);
		} else {
			var toPage = $(".page").eq(0);
			
			var pageId = "#" + toPage.attr("id");
			
//					if (d && d.init) {
//						$(pageId).trigger("pageloaded", {});
//					}
			
			changePage(pageId, {}, null, null, true);
		}
		
	});
	
	$.m.frozeninit = false;
	$.m.jamready = false;
	$(document).on("frozeninit", function(e){
		
		// 两个加载全部初始化完毕，才进行下一步
			config.historyLength = window.history.length;
			
			$(window).trigger("hashchange", {init:true});
	})
	
	// 换页方法
	$.m.changePage = changePage;
	
	$.m.loading = $.loading({content:'加载中...'});
	
	$.m.getHashPage = function (str) {
		if (str) {
			hash = str;
		} else {
			hash = window.location.hash;
		}
		if (!hash) {
			return "";
		}
		var paramIndex = hash.indexOf("?");
		if (paramIndex != -1) {
			return hash.substring(0, paramIndex);
		} else {
			return hash;
		}
	}
	
	$.m.parseParam = function(paramStr) {
		var data = {};
		if(!paramStr) {
			return data;
		}
		var paramPairs = paramStr.split("&");
		$.each(paramPairs, function(p){
			var kv = this.split("=");
			var k = decodeURIComponent((kv[0]));
			var v = decodeURIComponent(kv[1]);
			if (k.substring(k.length-3) == "[]") {
				var kn = k.substring(0, k.length-3);
				if (data[kn] != undefined){
					data[kn] = [];
				}
				data[kn].push(v);
			} else {
				eval("data." + k + " = '"+v+"'");
			}
		});
		
		return data;
	}
	
	$.m.getHashParam = function (str) {
		if (str) {
			hash = str;
		} else {
			hash = window.location.hash;
		}
		if (!hash) {
			return {};
		}
		var paramIndex = hash.indexOf("?");
		if (paramIndex != -1) {
			var paramStr = hash.substring(paramIndex+1);
			
			return $.m.parseParam(paramStr);
		}
		return {};
	}
	
	$.m.getUrlParam = function(str) {
		if (str) {
			query = str;
		} else {
			query = window.location.search;
		}
		if (!query) {
			return {};
		}
		
		var paramIndex = query.indexOf("?");
		if (paramIndex != -1) {
			var paramStr = query.substring(paramIndex+1);
			
			return $.m.parseParam(paramStr);
		}
		
		return {};
	}
	
	// 增加$.m.getParam() 可以将#号前后的参数合并取得。方便分享的时候使用。
	$.m.getParam = function () {
		return $.extend({}, $.m.getUrlParam(), $.m.getHashParam());
	}
	
	$.m.noop = function() {};
	
	$(function(){
		$(document).trigger("frozeinit");
		$(".ms-loader").removeClass("ms-loading");
		$(document).trigger("pageload", {toPage:$(".page.active")})	
		//$(".page").trigger("pageloaded");
	})
	
} catch(e) {
	alert(e);
}
