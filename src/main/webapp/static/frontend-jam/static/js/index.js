
define(["jq-common", "avalon"], function($, avalon) {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "index",
		swipe : function(e){
			console.log(e.type);
			var m = require("jqmobile");
			m.navigate("button-example.html", { "transition" : "slide"});
		}
	});

	return vm;
});
