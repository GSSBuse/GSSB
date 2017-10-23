
define(["jq-common", "avalon", "widget-lib/overscroll/overscroll", "avalon-plugin/lazyload/avalon.lazyload"], function($, avalon) {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "ibuy",
		data : {
			shareOrNot : false,
			shareText : "关注"
		},
		switchShareStatus : function(){
			vm.data.shareOrNot = !vm.data.shareOrNot;
			vm.data.shareText = !vm.data.shareOrNot ? "关注" : "取消";
		},
		addWxPopupClass : function(){
			var tmp = $(this).attr("href");
			popupId = tmp.replace("#", "") + "-popup";
//			$("#" + popupId).addClass("wx-popup");
//			$(tmp).on("popupafteropen", function(event,ui){
//				$("#" + popupId).css("max-width", "none");
//				$("#" + popupId).css("top", "auto");
//				$("#" + popupId).css("left", "0");
//			});
		}
	});

	return vm;
});
