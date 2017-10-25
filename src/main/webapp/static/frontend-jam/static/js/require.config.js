require.config({
    "baseUrl": (window.ctx?ctx+"/":"")+ "static/frontend-jam/static/js",
    "waitSeconds" : 0,
    "urlArgs": "ver=v1.0.40",
    "paths": {
      "vendors-lib" : "../vendors",
      "jq-plugin" : "../vendors/jquery/plugins",
      "jquery": "../vendors/jquery/jquery",
      "bootstrap": "../vendors/bootstrap/dist/js/bootstrap.min",
      "growl": "../vendors/bootstrap-growl/bootstrap-growl.min",
      
      "domReady": "../vendors/require/plugin/domReady",
      "text": "../vendors/require/plugin/text",
      "css": "../vendors/require/plugin/css",
      "i18n": "../vendors/require/plugin/i18n",
      "widget-lib": "w/../../widget",
      
      // avalon
      //"avalon" : "//cdn.bootcss.com/avalon.js/1.4.6.2/avalon.mobile.shim",
      "avalon-plugin" : "../vendors/avalon/plugin",
      
      // for mobile
      "zepto": "../vendors/jingle/js/lib/zepto.min",
      "iscroll" : "../vendors/jingle/js/lib/iscroll",
      "artTemplate" : "../vendors/jingle/js/lib/template.min",
      "zepto.touch2mouse" : "../vendors/jingle/js/lib/zepto.touch2mouse",
      "jchart" : "../vendors/jingle/js/lib/JChart.debug",
      "cordova" : "../vendors/jingle/js/lib/cordova",
      "jingle" : "../vendors/jingle/js/lib/Jingle.debug",
      
      // for jq-mobile
      "jqmobile" : "../vendors/jquery-mobile/jquery.mobile-1.4.5",
      
      // weixin js
      "jweixin" : "../vendors/jweixin/jweixin-1.0.0.js",
      
      // frozen-ui
      "frozen" : "//cdn.bootcss.com/FrozenUI/1.3.0/js/frozen",
      
      // dropload
      "dropload" : "../vendors/dropload/dropload"
    },
    "shim": {
        "jqmobile": {
        	deps : [
        		"jquery"
        		//"css!vendors-lib/jquery-mobile/jquery.mobile.structure-1.4.5.min.css"
        		//"css!vendors-lib/jquery-mobile/jquery.mobile.theme-1.4.5.min.css"
        	]
        },
        "jingle": {
        	deps : [
        		"zepto",
        		"iscroll",
        		"artTemplate",
        		"zepto.touch2mouse"
        	]
        },
        "zepto.touch2mouse" : [
        	"zepto"
        ],
        "bootstrap" : {
        	deps : [
        	    "jquery",
        	    "css!vendors-lib/bootstrap/dist/css/bootstrap.min.css",
        	    "css!vendors-lib/bootstrap/dist/css/bootstrap-theme.min.css"
        	]
        },
        "growl" : [
            "bootstrap"
        ]
    }
});

//define(["domReady"], function(dready){
//	dready(function(){
//		
//		var scripts = document.getElementsByTagName("script");
//		var scriptNames = [];
//		for (var i = 0; i < scripts.length; i++) {
//			var pageScriptName = scripts[i].getAttribute("data-page");
//			if (pageScriptName) {
//				scriptNames.push(pageScriptName);
//			}
//		}
//		
//		require(scriptNames, function() {
//			$(document).trigger("jamready");
//		});
//	});
//	
//	return {
//		ready : function (cb) {
//			dready(cb);
//		}
//	}
//});