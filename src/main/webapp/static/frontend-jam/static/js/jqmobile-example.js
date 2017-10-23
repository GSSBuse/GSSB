
define(["jq-common", "avalon", "jqmobile"], function($, avalon, m) {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "demo-intro",
		swipe : function(e){
			console.log(e.type);
			m.navigate("button-example.html", { "transition" : "slide"});
		}
	});

	/*
	 * 如果无法立即返回vm对象。可以使用 $.Defered 对象，延迟返回。
	 * 
	 * 在前端，如果项目中，自带有 jquery 类库的话。

// $.Deferred

//sound.js
define(["SoundManager", "jQuery"],function(sm2, $){
        var deferred = $.Deferred();
        sm2.soundManager.setup({
            url:'js/sm2/swf/',
            onready: function() {
                // 返回 sound
                try {
                    var sound = sm2.soundManager.createSound({
                        url: 'sound/sound1.mp3',
                        autoLoad: true
                    });
                    // success return
                    deferred.resolve(sound);
                } catch (ex) {
                    // error return
                    deferred.reject(new Error(ex))
                }
            }
        }); 
    });
    // 返回 promise 对象
    return deferred;
});
	 */
	
	return vm;
});
