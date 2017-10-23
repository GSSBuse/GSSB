

define(["frozen-common", "avalon"], function ($, avalon){
	var tab = new fz.Scroll('.ui-tab', {
        role: 'tab',
        autoplay: true,
        interval: 3000
    });
    
    var vm = avalon.define({
    	$id : "frozen-example"
    });
    
    return vm;
})