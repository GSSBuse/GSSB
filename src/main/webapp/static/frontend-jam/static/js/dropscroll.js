define(["iscroll"],function(){

	var $win = $(window);
	var $doc = $(document);
	var noop = function(){};
	
	var defaults = {
		gap : 10,
		height : 50,
		pullUpAction : noop,
		pullDownAction : noop
	}
	
	$.fn.DropScroll = function(options) {
		var drop = new DropScroll(this, options)
		this.data("_dropscroll", drop);
		return drop;
	}

	var DropScroll = function(el, options) {
		var self = this;
		this.$el = $(el);
		
		var ops = $.extend({}, defaults, options);
		this.init(ops);
		
		this.onUpActionEnd =  function () {
			if (self.$domUp) {
				self.$domUp.remove();
				self.$domUp = null;
				self.refresh();
			}
		}
		
		this.onDownActionEnd =  function () {
			if (self.$domDown) {
				self.$domDown.remove();
				self.$domDown = null;
				self.refresh();
			}
		}
		
		this.refresh =  function () {
			self.scroll.refresh();
		}

		return self;
	}

	// 初始化
	DropScroll.prototype.init = function(options) {
		var self = this;
		var gap = options.gap;
		
		this.scroll = new iScroll(self.$el[0], {
			hScroll : false,
//            bounce : false,
            lockDirection : true,
            useTransform: true,
            useTransition: false,
            checkDOMChanges: false,
            snap: true,
			onRefresh: function () {
				
			},
			onScrollMove: function () {
				if (this.y > gap && options.pullDownAction) {
//					this.minScrollY = 0;
					self.direction = "down";
					if (self.$domDown) {
						self.$domDown.html("").append('<div class="dropload-update">↑释放更新</div>');
					}
				} else if (this.y > 0 && this.y < gap && options.pullDownAction) {
					if (!self.$domDown) {
						self.$domDown = $('<div class="dropload-up"></div>');
						self.$el.children().first().prepend(self.$domDown);
						self.$domDown.css("height", options.height);
						//this.refresh();
					}
					self.$domDown.html("").append('<div class="dropload-refresh">↓下拉刷新</div>');
				} else if (this.y < (this.maxScrollY - gap) && options.pullUpAction) {
//					this.maxScrollY = this.maxScrollY;
					self.direction = "up";
					if (self.$domUp) {
						self.$domUp.html("").append('<div class="dropload-update">↓释放加载</div>');
					}
				} else if (this.y > (this.maxScrollY - gap) && this.y < this.maxScrollY && options.pullUpAction) {
					if (!self.$domUp) {
						self.$domUp = $('<div class="dropload-down"></div>');
						self.$el.children().first().append(self.$domUp);
						self.$domUp.css("height", options.height);
						this.refresh();
					}
					self.$domUp.html("").append('<div class="dropload-refresh">↑上拉加载更多</div>');
				}

			},
			onScrollEnd : function(){
				if (self.direction === "down") {
					if (self.$domDown) {
						self.$domDown.html("").append('<div class="dropload-load"><span class="loading"></span>加载中...</div>');
					}
					options.pullDownAction(self.onDownActionEnd);
				} else if (self.direction === "up") {
//					this.y = this.maxScrollY - gap;
					if (self.$domUp) {
						self.$domUp.html("").append('<div class="dropload-load"><span class="loading"></span>加载中...</div>');
					}
					options.pullUpAction(self.onUpActionEnd);
				}
				self.direction = "";
			}
			
		});
		return this;
	}

	return DropScroll;
});