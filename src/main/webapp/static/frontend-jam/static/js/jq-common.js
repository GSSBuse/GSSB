// 框架初始化
define(["jquery", "css!vendors-lib/jquery-mobile/jquery.mobile.external-png-1.4.5.min.css"], function($){
	//console.log("jq-common")
	$( document ).on( "mobileinit", function () {
		//console.log("mobileinit")
		$(document).on("pageload", "body", function(e,d){
			d.toPage.find("script").each(function(){
				var pagejs = $(this).data("page");
				require([pagejs]);
			})
			
		})
		$(document).on("pagebeforeload", "body", function(e,d){
			//$(".ms-loader").addClass("ms-loading");
		});
		$(document).on("pageshow", "body", function(e,d){
			require(["avalon"], function(avalon){
				avalon.scan(d.toPage[0]);
			})
			$(".ms-loader").removeClass("ms-loading");
			//console.log("pageshow")
		})
	});
	
	require(["jqmobile"]);
	
	return $;
})