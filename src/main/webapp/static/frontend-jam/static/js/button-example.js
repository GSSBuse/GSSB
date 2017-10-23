
//	var s = 1;
define(["jquery", "jq-common", "avalon", "widget-lib/overscroll/overscroll"], function($, mainVm, avalon, a){
	//console.log("button-example:" + Date.now())
	var vm = avalon.define({
		$id : "button-example",
		$osup : {
			direction : "up"
		},
		data : {
			canAnchor : false,
			input : ""
		},
		showAlert : function () {
			alert("show alert");
			vm.data.canAnchor = !vm.data.canAnchor;
		},
		eventtip : function(e) {
			console.log(e.type)
			console.log(e)
		}
	})
	
	return vm;
})
