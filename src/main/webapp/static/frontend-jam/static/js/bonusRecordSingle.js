/**
 * 
 */

define( function() {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "bonusRecordSingle",
		datas:{
			currentClientId:"",
			nickname:"",
			domainnameId : "",
			domainname : "",
			description:"",
			domainPhoto : "",
			sharenumber : 0,
			sharetotal : 0,
			shareList:[],
			sharedSize:0,
			secondBonusData:"",
			shareCommissionData:"",
		},
		// 分享
		share : function(){
			if (vm.datas.currentClientId == "" || vm.datas.currentClientId == null) {
				$.tips({
					content : "请先关注公共号再进行域名分享",
					stayTime : 2000,
					type : "error"
				});
				return false;
			}
			
			var url = utils.shareHref(window.location.href,"singleDomainname.html")+"?singleDomainId=" + vm.datas.domainnameId + "&shareClientId=" + vm.datas.currentClientId;
			var self = $(this);
			//分享按钮操作
			$("#directiveDivBonusOrCommission").addClass("show").click(function(){
				$(this).removeClass("show");
			});
			wx.onMenuShareAppMessage({
				title: '【' + vm.datas.nickname + '】分享的域名' + vm.datas.domainname, // 分享标题
				desc: vm.datas.description, // 分享描述
				link: url, // 分享链接
				imgUrl: "http://" + window.location.host + vm.datas.domainPhoto, // 分享图标
				type: '', // 分享类型,music、video或link，不填默认为link
				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				success: function () { 
					// 用户确认分享后执行的回调函数
					shareSuccessCallback();
				},
				cancel: function () {
					// 用户取消分享后执行的回调函数
					$("#directiveDivBonusOrCommission").removeClass("show");
				}
			});

			//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
			wx.onMenuShareTimeline({
				title : '【' + vm.datas.nickname + '】分享的域名' + vm.datas.domainname, // 分享标题
				link : url, // 分享链接
				imgUrl : "http://" + window.location.host + vm.datas.domainPhoto, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					shareSuccessCallback();
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
					$("#directiveDivBonusOrCommission").removeClass("show");
				}
			});

			//获取“分享到QQ”按钮点击状态及自定义分享内容接口
			wx.onMenuShareQQ({
				title: '【' + vm.datas.nickname + '】分享的域名' + vm.datas.domainname, // 分享标题
				desc: vm.datas.description, // 分享描述
				link : url, // 分享链接
				imgUrl : "http://" + window.location.host + vm.datas.domainPhoto, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					shareSuccessCallback();
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
					$("#directiveDivBonusOrCommission").removeClass("show");
				}
			});

			//获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
			wx.onMenuShareWeibo({
				title: '【' + vm.datas.nickname + '】分享的域名' + vm.datas.domainname, // 分享标题
				desc: vm.datas.description, // 分享描述
				link : url, // 分享链接
				imgUrl : "http://" + window.location.host + vm.datas.domainPhoto, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					shareSuccessCallback();
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
					$("#directiveDivBonusOrCommission").removeClass("show");
				}
			});

			//获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
			wx.onMenuShareQZone({
				title: '【' + vm.datas.nickname + '】分享的域名' + vm.datas.domainname, // 分享标题
				desc: vm.datas.description, // 分享描述
				link : url, // 分享链接
				imgUrl : "http://" + window.location.host + vm.datas.domainPhoto, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					shareSuccessCallback();
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
					$("#directiveDivBonusOrCommission").removeClass("show");
				}
			});
			return false;
		}
	});
	
	$("#bonusRecordSingle").on("pageloaded", function() {
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
							'chooseWXPay'
				]
			});
		});
	});
	
	// 下面是获取个人领红包记录,页面和js加载的时候就要初始化，所以不需要监听任何事件
	$("#bonusRecordSingle").on("pageshow", function(e, d){
		vm.datas.domainnameId = $.m.getParam().domainnameId;
		$.get("bonusRecordSingleInfo.json", {domainnameId:vm.datas.domainnameId}, function(res) {
			vm.datas.domainname = res.data.domainname;
			vm.datas.domainPhoto = res.data.domainPhoto;
			vm.datas.sharenumber = res.data.sharenumber;
			vm.datas.sharetotal = res.data.sharetotal;
			vm.datas.currentClientId = res.data.currentClientId;
			vm.datas.nickname = res.data.nickname;
			vm.datas.description = res.data.description;
			if(res.type=="warn"){
				$.tips({
	                content : res.msg,
	                stayTime:2000,
	                type : res.type
	            })
			}else{
				vm.datas.sharedSize = res.data.sharedSize;
				if(vm.datas.sharedSize>0){
					vm.datas.shareList.pushArray(res.data.shareList);
				}else{
					vm.datas.shareList = "";
				}
				vm.datas.secondBonusData = res.data.secondBonusData;
				vm.datas.shareCommissionData = res.data.shareCommissionData;
			}
		});
	});
	$("#bonusRecordSingle").on("pagehide", function(e, d){
		vm.datas.currentClientId="";
		vm.datas.nickname="";
		vm.datas.domainnameId = "";
		vm.datas.domainname = "";
		vm.datas.description = "";
		vm.datas.domainPhoto = "";
		vm.datas.sharenumber = 0;
		vm.datas.sharetotal =0,
		vm.datas.shareList.clear();
		vm.datas.sharedSize = 0;
		vm.datas.secondBonusData = "";
		vm.datas.shareCommissionData = "";
	});
	
	function shareSuccessCallback() {
		$("#directiveDivBonusOrCommission").removeClass("show");
		var hasmore = vm.datas.sharenumber > vm.datas.sharedSize ? true : false;
		$.post('share', {domainId : vm.datas.domainnameId, hasRedBag : hasmore},
			function (res) {
				if (res.type === 'success') {
					$("#directiveDivBonusOrCommission").removeClass("show");
				} else {
					$("#directiveDivBonusOrCommission").removeClass("show");
					$.tips({
						content : res.msg,
						stayTime : 2000,
						type : res.type
					});
				}
			});
	}
	
	return vm;
});	