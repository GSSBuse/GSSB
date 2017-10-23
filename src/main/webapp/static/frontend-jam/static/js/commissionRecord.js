/**
 * 
 */

define( function() {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "commissionRecord",
		//被分享者访问时，分享地址传的参数
		clientId : "",
		//分享者的id，用于构造分享地址的参数
		current_dy_client_id:"",
		datas:{
			commissionRecordOrBonusRecordInfo:[],
			currentClientName:"",
			photo:"",
			size:"",
			totalMoney:0
		},
		//分享操作
		share : function() {
			//显示提示分享指向
		//	$("#directiveDivCommission").css({"display":"block","opacity":"0.1","width":"100%","height":"100%","margin-top":"-80%"});
			$("#directiveDivCommission").addClass("show").click(function(){
				$(this).removeClass("show");
			});
			//获取当前登录用户的id
			vm.current_dy_client_id = $("#current_dy_client_id").val();
			//拼接分享的网址
			var url = utils.shareHref(window.location.href,"commissionRecord.html")+'?clientId='+vm.current_dy_client_id;
			
			//获取“分享给朋友”按钮点击状态及自定义分享内容接口
			wx.onMenuShareAppMessage({
			    title: '我领的佣金', // 分享标题
			    desc: '通过分享域名拍卖链接，所获得的佣金，惊喜不断哦！！！', // 分享描述
			    link: url, // 分享链接
			    imgUrl: vm.datas.photo, // 分享图标
			    type: '', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			    success: function () { 
			        // 用户确认分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    }
			});
			
			//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
			wx.onMenuShareTimeline({
			    title: '我通过分享域名拍卖链接，所获得的佣金，惊喜不断哦！！！', // 分享标题
			    link: url, // 分享链接
			    imgUrl: vm.datas.photo, // 分享图标
			    success: function () { 
			        // 用户确认分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    }
			});
			
			//获取“分享到QQ”按钮点击状态及自定义分享内容接口
			wx.onMenuShareQQ({
			    title: '我领的佣金', // 分享标题
			    desc: '通过分享域名拍卖链接，所获得的佣金，惊喜不断哦！！！', // 分享描述
			    link: url, // 分享链接
			    imgUrl: vm.datas.photo, // 分享图标
			    success: function () { 
			       // 用户确认分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    },
			    cancel: function () { 
			       // 用户取消分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    }
			});
			
			//获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
			wx.onMenuShareWeibo({
			    title: '我领的佣金', // 分享标题
			    desc: '通过分享域名拍卖链接，所获得的佣金，惊喜不断哦！！！', // 分享描述
			    link: url, // 分享链接
			    imgUrl: vm.datas.photo, // 分享图标
			    success: function () { 
			       // 用户确认分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    }
			});
			
			//获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
			wx.onMenuShareQZone({
			    title: '我领的佣金', // 分享标题
			    desc: '通过分享域名拍卖链接，所获得的佣金，惊喜不断哦！！！', // 分享描述
			    link: url, // 分享链接
			    imgUrl: vm.datas.photo, // 分享图标
			    success: function () { 
			       // 用户确认分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    	$("#directiveDivCommission").removeClass("show");
			    }
			});
		}
	});
	// 下面是获取个人领佣金记录,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#commissionRecord").on("pageloaded", function(e, d){
		vm.clientId = $("#clientId").val();
		$.get("myCommissionRecordOrBonusRecord.json", {type:"03",clientId:vm.clientId}, function(res) {
			//avalon.mix(vm.datas.cashflowinfo, res.data.cashflowinfo);
			if(res.type=="warn"){
				$.tips({
	                content : res.msg,
	                stayTime:2000,
	                type : res.type
	            });
	            vm.datas.currentClientName=res.data.currentClientName;
				vm.datas.photo = res.data.photo;
				vm.datas.size = res.data.size;
			}else{
				vm.datas.commissionRecordOrBonusRecordInfo.pushArray(res.data.commissionRecordOrBonusRecordInfo);
				vm.datas.currentClientName=res.data.currentClientName;
				vm.datas.photo = res.data.photo;
				vm.datas.size = res.data.size;
				
				//累加红包所得金额
				var totalMoney = 0;
				for(var i=0;i<vm.datas.commissionRecordOrBonusRecordInfo.length;i++){
					totalMoney = totalMoney+vm.datas.commissionRecordOrBonusRecordInfo[i].money;
				}
				vm.datas.totalMoney = totalMoney;
				
				if(res.data.size==0){
					$.tips({
		                content : res.msg,
		                stayTime:2000,
		                type : res.type
		            }).on("tips:hide",function(){
		            	$.m.changePage("#icenter");
		            });
				}
			}
		});
		
		// 获取jsticket
		$.get("jsapiTicket.json", {href:window.location.href},function(res) {
			wx.config({
				debug : false,
				appId : res.data.appId,
				timestamp : res.data.timestamp,
				nonceStr : res.data.nonceStr,
				signature : res.data.signature, // 签名
				jsApiList :[
				            'onMenuShareTimeline',
				            'onMenuShareAppMessage',
				            'onMenuShareQQ',
				            'onMenuShareWeibo',
				            'onMenuShareQZone',
				            'hideOptionMenu',
				            'showOptionMenu',
				            'hideMenuItems',
				            'showMenuItems',
							'chooseWXPay'
				]
			});
			
			wx.ready(function(){
				// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
//				wx.hideMenuItems({
//					menuList: ["menuItem:share:qq"]
//				});
			});
			
			wx.error(function(res){
				// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			});
		});
	});
	return vm;
});	