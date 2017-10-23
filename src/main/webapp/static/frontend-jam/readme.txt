1 如果是IE10以下。
	则需要修改require.config.js。
	把avalon.modern.shim.min.js 修改为 avalon.shim.min.js。
	
2 示例文件说明：
	example.html  requirejs + avalon集合。演示了这两个框架之间的组合，并演示了avalon的动态绑定机制，及使用 widget，增加了验证插件。
										验证插件完整演示地址：http://ued.qunar.com/oniui/index.html#!/widgets?widgetId=validation
										
	jingle-example.html requirejs + jingle集合。演示了这两个框架之间的组合，并展示了 Jingle的组件。
	
	jqmobile-example.html  requirejs + jquery-mobile + avalon集合。演示了三个框架的组合，并且展示页面跳转，和avalon的事件绑定。
	button-example.html    requirejs + jquery-mobile + avalon集合。演示了三个框架的组合，并且 表单控件 及 avalon双向绑定机制。