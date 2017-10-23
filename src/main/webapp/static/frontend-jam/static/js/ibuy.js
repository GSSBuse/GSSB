

define("ibuy", function (){

	var interval_cursor;
	var vm = avalon.define({
		$id : "ibuy",
		//服务器当前时间戳：用于更新倒计时，在本地做递加，定期同步服务器
		$sysServerTime : new Date().getTime(),
		datas : {
			
			//记录轮询的时间戳，与后台相等时不更新操作
			timeStamp : "0",
			//红包开关
			shareBonusEnable : 0,
			//图片显示
			screenWidth : 0,
			imageHeight : 78,
			imageWidth	: 78,
			domainList : [],
			pageIndex : 1,
			newsCnt : 0,
			shareClientId: "",
			tmp : {
				domainId : "",
				index : 0,
				newDate : new Date().getTime(),
				singleDomainId : $.m.getParam().singleDomainId ? $.m.getParam().singleDomainId : '',
				shareClientId : $.m.getParam().shareClientId ? $.m.getParam().shareClientId : '',
				nickname : "",
				currentClientId : null,
				bidBtn : null,
				deposit : 0
			}
		},
		//跳转至服务协议页面
		LinkToServiceProtocol : function(){
			window.location.href = ctx+"/domainname/viewArticle?articleId=1";
		},
		//跳转至当个域名页面
		linkToSingleDomainname:function(id){
//			var href = "singleDomainname.html?singleDomainId="+id;
//			location.href = href;
			$.m.changePage("#singleDomainname?singleDomainId="+id);
		},
		//跳转至拍卖列表
		goToAuctionList:function(){
			$.m.changePage("#auction-list");
		},
		//跳转到单个域名的领红包佣金记录页面
		goTobonusRecordSingle:function(id){
			$.m.changePage("#bonusRecordSingle?domainnameId="+id);
		},
		// 关注/取消关注
		switchFollowStatus : function(index){
			if (vm.datas.tmp.currentClientId == "" || vm.datas.tmp.currentClientId == null) {
				$.tips({
					content : "请先关注公共号再进行域名关注",
					stayTime : 2000,
					type : "error"
				}).on("tips:hide",function(){
					$.m.changePage("#error");
				});
				return false;
			}
			$.post("follow",
					{
						"domainId" : vm.datas.domainList[index].id
					},
					function (res) {
						if (res.type == "success") {
//							vm.datas.domainList[index].attentioned    = !vm.datas.domainList[index].attentioned;
							if (res.data.attended !== undefined) {
								vm.datas.domainList[index].attentioned = res.data.attended;
							}
							vm.datas.domainList[index].attentionList  = res.data.attentionCList;
							vm.datas.domainList[index].attentionCount = res.data.attentionCount;
						} else {
							$.tips({
								content : res.msg,
								stayTime : 2000,
								type : res.type
							});
						}
					}
				)

			return false;
		},
		// 分享
		share : function(index){
			if (vm.datas.tmp.currentClientId == "" || vm.datas.tmp.currentClientId == null) {
				$.tips({
					content : "请先关注公共号再进行域名分享",
					stayTime : 2000,
					type : "error"
				}).on("tips:hide",function(){
					$.m.changePage("#error");
				});
				return false;
			}
			
//			var endIndex = window.location.href.indexOf("?");
//			if (endIndex == -1) {
//				var url = window.location.href.slice(0) + "?singleDomainId=" + vm.datas.domainList[index].id + "&shareClientId=" + vm.datas.shareClientId;
//			} else {
//				var url = window.location.href.slice(0, endIndex) + "?singleDomainId=" + vm.datas.domainList[index].id + "&shareClientId=" + vm.datas.shareClientId;
//			}
			var url = utils.shareHref(window.location.href,"singleDomainname.html")+"?singleDomainId=" + vm.datas.domainList[index].id + "&shareClientId=" + vm.datas.shareClientId;
			
			var self = $(this);
			//分享按钮操作
			$("#share-float").addClass("show").click(function(){
				$(this).removeClass("show");
			});
			wx.onMenuShareAppMessage({
				title: vm.datas.domainList[index].name + '正在【拍域名】拍卖' , // 分享标题
				desc: vm.datas.domainList[index].description, // 分享描述
				link: url, // 分享链接
				imgUrl: "http://" + window.location.host + vm.datas.domainList[index].image1, // 分享图标
				type: '', // 分享类型,music、video或link，不填默认为link
				dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
				success: function () { 
					// 用户确认分享后执行的回调函数
					shareSuccessCallback(index, self);
				},
				cancel: function () {
					// 用户取消分享后执行的回调函数
					$("#share-float").removeClass("show");
				}
			});

			//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
			wx.onMenuShareTimeline({
				title : vm.datas.domainList[index].name + '正在【拍域名】拍卖' , // 分享标题
				link : url, // 分享链接
				imgUrl : "http://" + window.location.host + vm.datas.domainList[index].image1, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					shareSuccessCallback(index, self);
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
					$("#share-float").removeClass("show");
				}
			});

			//获取“分享到QQ”按钮点击状态及自定义分享内容接口
			wx.onMenuShareQQ({
				title: vm.datas.domainList[index].name + '正在【拍域名】拍卖' , // 分享标题
				desc: vm.datas.domainList[index].description, // 分享描述
				link : url, // 分享链接
				imgUrl : "http://" + window.location.host + vm.datas.domainList[index].image1, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					shareSuccessCallback(index, self);
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
					$("#share-float").removeClass("show");
				}
			});

			//获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
			wx.onMenuShareWeibo({
				title: vm.datas.domainList[index].name + '正在【拍域名】拍卖' , // 分享标题
				desc: vm.datas.domainList[index].description, // 分享描述
				link : url, // 分享链接
				imgUrl : "http://" + window.location.host + vm.datas.domainList[index].image1, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					shareSuccessCallback(index, self);
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
					$("#share-float").removeClass("show");
				}
			});

			//获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
			wx.onMenuShareQZone({
				title: vm.datas.domainList[index].name + '正在【拍域名】拍卖' , // 分享标题
				desc: vm.datas.domainList[index].description, // 分享描述
				link : url, // 分享链接
				imgUrl : "http://" + window.location.host + vm.datas.domainList[index].image1, // 分享图标
				success : function() {
					// 用户确认分享后执行的回调函数
					shareSuccessCallback(index, self);
				},
				cancel : function() {
					// 用户取消分享后执行的回调函数
					$("#share-float").removeClass("show");
				}
			});
			return false;
		},
		// 手动设置按钮显示,参数domain为vm.datas.domainList元素
		buttonText:function(domain){
			if (domain.clientId != vm.datas.tmp.currentClientId && (domain.bidCount==0 || domain.topBidClientId != vm.datas.tmp.currentClientId)) {
				var str = avalon.filters.transferCurrentAmount(domain.currAmount + domain.increment);
				$("#"+domain.id).text("出价（" + str + "）");
			}
			if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
				var str = avalon.filters.transferCurrentAmount(domain.proxyAmount + domain.proxyIncrement);
				$("#"+domain.id).text("出价（" + str + "）");
			}
			if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
				var str = avalon.filters.transferCurrentAmount(domain.currAmount + domain.increment);
				$("#"+domain.id).text("出价（" + str + "）");
			}
		},
		// 出价表单
		bidForm : function(index){
			if (vm.datas.domainList[index].endFlag) {
				return false;
			}
			
			if (vm.datas.tmp.currentClientId == "" || vm.datas.tmp.currentClientId == null) {
				$.tips({
					content : "请先关注公共号再进行域名出价",
					stayTime : 2000,
					type : "error"
				}).on("tips:hide",function(){
					$.m.changePage("#error");
				});
				return false;
			}
			
			vm.datas.tmp.domainId = vm.datas.domainList[index].id;
			vm.datas.tmp.index = index;
//			$.get("getBidListByDomainId", {domainId : vm.datas.domainList[index].id},
//				function(res) {
//				if(res.data.isNoBid=="false"){//历史有人出价
//					vm.datas.domainList[index].endTime = res.data.pDomain.endTime;
//					vm.datas.domainList[index].currAmount = res.data.pDomain.currAmount;
//					vm.datas.domainList[index].deposit = res.data.pDomain.deposit;
//					vm.datas.domainList[index].increment = res.data.pDomain.increment;
//					vm.datas.domainList[index].bidList = res.data.pDomain.bidList;
//					vm.datas.domainList[index].proxyAmount = res.data.pDomain.proxyAmount;
//					vm.datas.domainList[index].proxyIncrement = res.data.pDomain.proxyIncrement;
//					vm.datas.domainList[index].bidCount = res.data.pDomain.bidCount;
//					vm.datas.domainList[index].topBidClientId = res.data.pDomain.topBidClientId;
//
					var domain = vm.datas.domainList[index];
//					//延时1毫秒设置按钮的值
//					setTimeout(function(){
//						vm.buttonText(domain);
//					}, 1);
//					//vm.datas.tmp.bidBtn = $("#"+vm.datas.domainList[index].id);
//					//更新按钮的值
////					if (domain.clientId != vm.datas.tmp.currentClientId && (domain.bidCount==0 || domain.topBidClientId != vm.datas.tmp.currentClientId)) {
////						$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.currAmount + domain.increment) + "）");
////					}
////					if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
////						$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.proxyAmount + domain.proxyIncrement) + "）");
////					}
////					if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
////						$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.currAmount + domain.increment) + "）");
////					}
					//更新出价弹窗的值
					try {
						if (domain.clientId != vm.datas.tmp.currentClientId && (domain.bidCount==0 || domain.topBidClientId != vm.datas.tmp.currentClientId)) {
							$("#bidAmount").text(domain.currAmount + domain.increment);
							$("#bidCurrent").text(vm.datas.domainList[index].currAmount);
						}
						if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
							$("#bidAmount").text(domain.proxyAmount + domain.proxyIncrement);
							$("#bidCurrent").text(vm.datas.domainList[index].currAmount);
						}
						if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
							$("#bidAmount").text( domain.currAmount + domain.increment);
							$("#bidCurrent").text(vm.datas.domainList[index].currAmount);
						}
						$("#bidForm").dialog("show");
					} catch (e) {
						alert(e);
					}
//				}else{//历史无人出价
//					$("#bidAmount").text( vm.datas.domainList[index].increment);
//					$("#bidCurrent").text(vm.datas.domainList[index].currAmount);
//					$("#bidForm").dialog("show");
//				}
//			});

//			interval_cursor = setInterval(function() {
//				$("#icon-cursor").toggleClass("icon-cursor");
//			}, 500);
		},
		// 出价
		bidding : function(){
			$("#bidForm").dialog("hide");
//			clearInterval(interval_cursor);
			if (vm.datas.tmp.currentClientId == "" || vm.datas.tmp.currentClientId == null) {
				$.tips({
					content : "请先关注公共号再进行域名出价",
					stayTime : 2000,
					type : "error"
				}).on("tips:hide",function(){
					$.m.changePage("#error");
					});
				return false;
			}
			try {
//				var bidAmount = $("#bidForm").find("input").val();
				var bidAmount = $("#bidAmount").text();
				if (!bidAmount || isNaN(bidAmount)) {
					$.tips({
						content : "请输入正确的出价金额",
						stayTime : 2000,
						type : "error"
					});
					return false;
				}

				$.ajax({
						type: "POST",
						url : "bid",
						data : {
							"domainId" : vm.datas.tmp.domainId,
							"bidAmount" : bidAmount
						},
						dataType : "json",
						success : function(res) {
							var index = vm.datas.tmp.index;
							if (res.type == "success") {
								if(res.msg == "出价被超出"){
									$.tips({
										content : res.msg,
										stayTime : 2000,
										type : "warn"
									});
								}else{
									$.tips({
										content : res.msg,
										stayTime : 2000,
										type : res.type
									});
								}

								$.get("getBidListByDomainId", {domainId : vm.datas.domainList[index].id},
										function(res) {
										if(res.data.isNoBid=="false"){//历史有人出价
											vm.datas.domainList[vm.datas.tmp.index].endTime = res.data.pDomain.endTime;
											vm.datas.domainList[vm.datas.tmp.index].currAmount = res.data.pDomain.currAmount;
											vm.datas.domainList[vm.datas.tmp.index].deposit = res.data.pDomain.deposit;
											vm.datas.domainList[vm.datas.tmp.index].increment = res.data.pDomain.increment;
											vm.datas.domainList[vm.datas.tmp.index].bidList = res.data.pDomain.bidList;
											vm.datas.domainList[vm.datas.tmp.index].proxyAmount = res.data.pDomain.proxyAmount;
											vm.datas.domainList[vm.datas.tmp.index].proxyIncrement = res.data.pDomain.proxyIncrement;
											vm.datas.domainList[vm.datas.tmp.index].bidCount = res.data.pDomain.bidCount;
											vm.datas.domainList[vm.datas.tmp.index].topBidClientId = res.data.pDomain.topBidClientId;

											//vm.datas.tmp.bidBtn = $("#"+vm.datas.domainList[vm.datas.tmp.index].id);
											var domain = vm.datas.domainList[vm.datas.tmp.index];
											//延时1毫秒设置按钮的值
											setTimeout(function(){
												vm.buttonText(domain);
											}, 1);
											//更新按钮的值
//											if (domain.clientId != vm.datas.tmp.currentClientId && (domain.bidCount==0 || domain.topBidClientId != vm.datas.tmp.currentClientId)) {
//												$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.currAmount + domain.increment) + "）");
//											}
//											if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
//												$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.proxyAmount + domain.proxyIncrement) + "）");
//											}
//											if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
//												$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.currAmount + domain.increment) + "）");
//											}
											//更新出价弹窗的值
											try {
												if (domain.clientId != vm.datas.tmp.currentClientId && (domain.bidCount==0 || domain.topBidClientId != vm.datas.tmp.currentClientId)) {
													$("#bidAmount").text(domain.currAmount + domain.increment);
													$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												}
												if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
													$("#bidAmount").text(domain.proxyAmount + domain.proxyIncrement);
													$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												}
												if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
													$("#bidAmount").text( domain.currAmount + domain.increment);
													$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												}
												vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
											} catch (e) {
												alert(e);
											}
										}else{//历史无人出价
											$("#bidAmount").text( vm.datas.domainList[vm.datas.tmp.index].increment);
											$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
											vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
										}
									});
							} else if (res.type == "warn") {
								if (res.data.type) {
									$.tips({
										content : res.msg,
										stayTime : 3000,
										type : res.type
									});
									$("#bidForm").dialog("show");
									$.get("getBidListByDomainId", {domainId : vm.datas.domainList[index].id},
											function(res) {
											if(res.data.isNoBid=="false"){//历史有人出价
												vm.datas.domainList[vm.datas.tmp.index].endTime = res.data.pDomain.endTime;
												vm.datas.domainList[vm.datas.tmp.index].currAmount = res.data.pDomain.currAmount;
												vm.datas.domainList[vm.datas.tmp.index].deposit = res.data.pDomain.deposit;
												vm.datas.domainList[vm.datas.tmp.index].increment = res.data.pDomain.increment;
												vm.datas.domainList[vm.datas.tmp.index].bidList = res.data.pDomain.bidList;
												vm.datas.domainList[vm.datas.tmp.index].proxyAmount = res.data.pDomain.proxyAmount;
												vm.datas.domainList[vm.datas.tmp.index].proxyIncrement = res.data.pDomain.proxyIncrement;
												vm.datas.domainList[vm.datas.tmp.index].bidCount = res.data.pDomain.bidCount;
												vm.datas.domainList[vm.datas.tmp.index].topBidClientId = res.data.pDomain.topBidClientId;

												var domain = vm.datas.domainList[vm.datas.tmp.index];
												//延时1毫秒设置按钮的值
												setTimeout(function(){
													vm.buttonText(domain);
												}, 1);
												//更新按钮的值
//												if (domain.clientId != vm.datas.tmp.currentClientId && (domain.bidCount==0 || domain.topBidClientId != vm.datas.tmp.currentClientId)) {
//													$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.currAmount + domain.increment) + "）");
//												}
//												if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
//													$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.proxyAmount + domain.proxyIncrement) + "）");
//												}
//												if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
//													$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.currAmount + domain.increment) + "）");
//												}
												//更新出价弹窗的值
												try {
													//更新出价弹窗的值
													if (domain.clientId != vm.datas.tmp.currentClientId && (domain.bidCount==0 || domain.topBidClientId != vm.datas.tmp.currentClientId)) {
														$("#bidAmount").text(domain.currAmount + domain.increment);
														$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
														$("#bidAmount").text(domain.proxyAmount + domain.proxyIncrement);
														$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
														$("#bidAmount").text( domain.currAmount + domain.increment);
														$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
													$("#bidForm").dialog("show");
												} catch (e) {
													alert(e);
												}
											}else{//历史无人出价
												$("#bidAmount").text( vm.datas.domainList[vm.datas.tmp.index].increment);
												$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
												$("#bidForm").dialog("show");
											}
										});
								} else {
									$.get("getBidListByDomainId", {domainId : vm.datas.domainList[index].id},
											function(res) {
											if(res.data.isNoBid=="false"){//历史有人出价
												vm.datas.domainList[vm.datas.tmp.index].endTime = res.data.pDomain.endTime;
												vm.datas.domainList[vm.datas.tmp.index].currAmount = res.data.pDomain.currAmount;
												vm.datas.domainList[vm.datas.tmp.index].deposit = res.data.pDomain.deposit;
												vm.datas.domainList[vm.datas.tmp.index].increment = res.data.pDomain.increment;
												vm.datas.domainList[vm.datas.tmp.index].bidList = res.data.pDomain.bidList;
												vm.datas.domainList[vm.datas.tmp.index].proxyAmount = res.data.pDomain.proxyAmount;
												vm.datas.domainList[vm.datas.tmp.index].proxyIncrement = res.data.pDomain.proxyIncrement;
												vm.datas.domainList[vm.datas.tmp.index].bidCount = res.data.pDomain.bidCount;
												vm.datas.domainList[vm.datas.tmp.index].topBidClientId = res.data.pDomain.topBidClientId;

												var domain = vm.datas.domainList[vm.datas.tmp.index];
												//延时1毫秒设置按钮的值
												setTimeout(function(){
													vm.buttonText(domain);
												}, 1);
//												//更新按钮的值
//												if (domain.clientId != vm.datas.tmp.currentClientId && (domain.bidCount==0 || domain.topBidClientId != vm.datas.tmp.currentClientId)) {
//													$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.currAmount + domain.increment) + "）");
//												}
//												if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
//													$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.proxyAmount + domain.proxyIncrement) + "）");
//												}
//												if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
//													$("#"+vm.datas.domainList[index].id).text("出价（" + (domain.currAmount + domain.increment) + "）");
//												}
												//更新出价弹窗的值
												try {
													if (domain.clientId != vm.datas.tmp.currentClientId && (domain.bidCount==0 || domain.topBidClientId != vm.datas.tmp.currentClientId)) {
														$("#bidAmount").text(domain.currAmount + domain.increment);
														$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
														$("#bidAmount").text(domain.proxyAmount + domain.proxyIncrement);
														$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
														$("#bidAmount").text( domain.currAmount + domain.increment);
														$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													
													vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
//													$("#bidForm").dialog("show");
												} catch (e) {
													alert(e);
												}
											}else{//历史无人出价
												$("#bidAmount").text( vm.datas.domainList[vm.datas.tmp.index].increment);
												$("#bidCurrent").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
//												$("#bidForm").dialog("show");
											}
										});
									vm.datas.tmp.deposit = res.data.deposit;
									
									$("#bondForm #platformBankInfo").addClass("hidden");
									$("#bondForm #online").trigger('click');
									$("#bondForm").dialog("show");
								}
							} else {
								$.tips({
									content : res.msg,
									stayTime : 3000,
									type : res.type
								});
							}
						},
						error : function(res) {
							$.tips({
								content : "出价失败，请重试",
								stayTime : 3000,
								type : "error"
							});
//							alert("服务器出错，请联系管理员或重试");
						}
				});
			} catch (e) {
				// TODO: handle exception
				alert(e);
			}
			return false;
		},
		//设置选择线下充值
		selectOffLine : function(){
			$("#bondForm #offline").trigger('click');
		},
		//设置选择微信充值
		selectOnLine : function(){
			$("#bondForm #online").trigger('click');
		},
		// 充值请求
		charge : function(chargeAmount){
			$("#bondForm #platformBankInfo").addClass("hidden");
			$("#bondForm").dialog("hide");
			var chargeType = $("#bondForm").find("input[name='chargeType']:checked").attr("id");
			if (chargeType === "offline") {
				$.post("rechargeOrWithdrawals", {operate : "充值", operateAmount : chargeAmount,from:"rechargeForBid"}, function(res){
					if(res.type == "success"){
						$("#bondForm #online").trigger('click');
						$.tips({
							content : res.msg,
							stayTime:2000,
							type : res.type
						}).on("tips:hide",function(){
							$("#ibuy #prompt-msg").dialog("show");
						});
					}else{
						$.tips({
							content : res.msg,
							stayTime:2000,
							type : res.type
						});
					}
				});
			} else if (chargeType === "online") {
				$.ajax({
					type : 'POST',
					url : 'wxPay',
					data : {operate : "充值", operateAmount:chargeAmount, payFrom: "ibuy"},
					dataType : "json",
					success : function(res, textStatus){
						 $("#recharge").dialog("hide");
						 wx.chooseWXPay({
							timestamp: res.data.timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
							nonceStr: res.data.nonceStr, // 支付签名随机串，不长于 32 位
							'package': res.data['package'], // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
							signType: res.data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
							paySign: res.data.paySign, // 支付签名
							success: function (res) {
								// 支付成功后的回调函数
								$.tips({
										content : "充值成功",
										stayTime:2000,
										type : "success"
										});
								}
							});
					},
					error : function(){
						$.tips({
							content :"微信充值失败，请重新操作或联系经纪人",
							stayTime : 2000,
							type : "error"
						});
						$("#bondForm #platformBankInfo").addClass("hidden");
						$("#bondForm").dialog("hide");
					}
				});
			}
		},
		// 跳转到出价列表
		goBiddingList : function(domainId) {
			//location.href = ctx + "/domainname/bidding-list.html?domainId=" + domainId;
			$.m.changePage("#bidding-list?domainId=" + domainId);
//			$.m.changePage("#bidding-list", {domainId:domainId});
		},
		// 预览图片
		previewImage : function(index, count) {
			var imgList = new Array();
			if (vm.datas.domainList[index].image1) {
				imgList.push("http://" + window.location.host + vm.datas.domainList[index].image1 + ".org");
			}
			if (vm.datas.domainList[index].image2) {
				imgList.push("http://" + window.location.host + vm.datas.domainList[index].image2 + ".org");
			}
			if (vm.datas.domainList[index].image3) {
				imgList.push("http://" + window.location.host + vm.datas.domainList[index].image3 + ".org");
			}
			wx.previewImage({
				current: imgList[count],
				urls: imgList
			});
		},
		// 计算倒计时
		getCountDown : function(endTime, index) {
			try {
				var restTime = utils.parseDateStr(endTime).getTime() - vm.datas.tmp.newDate;
				//var restTime = new Date(endTime.replace(/-/g, "/")) - vm.datas.tmp.newDate;
				if(restTime < 1000){
					vm.datas.domainList[index].endFlag = true;
					$("#"+vm.datas.domainList[index].id).removeClass("ui-btn-primary");
				}
				if (utils.jscache.getV(restTime)) {
					return utils.jscache.getV(restTime);
				}
				
				if (restTime <= 0) {
					return {
						disabled : true,
						displayTime : '<i style="font-size: 16px">00</i>时<i style="font-size: 16px">00</i>分<i style="font-size: 16px">00</i>秒'
					}
				}
				var date = {}, h = m = s = "";
				date = utils.millisecondToDate(restTime);
				h = (date.time.hours < 10 ? ("0" + date.time.hours) : date.time.hours);
				m = (date.time.mins < 10 ? ("0" + date.time.mins) : date.time.mins);
				s = (date.time.secs < 10 ? ("0" + date.time.secs) : date.time.secs);
				var displayTime = "";
				if (date.time.day != 0) {
					displayTime += '<i style="color: red; font-size: 16px">' + date.time.day + '</i>天';
				}
				
				displayTime += '<i style="color: red; font-size: 16px">' + h + '</i>时<i style="color: red; font-size: 16px">'
					+ m + '</i>分<i style="color: red; font-size: 16px">' + s + '</i>秒';
				
				utils.jscache.setV(restTime, {
					disabled : false,
					displayTime : displayTime
				});
			} catch (e) {
				alert(e);
			}
			return {
				disabled : false,
				displayTime : displayTime
			};
		},
		clearAmount : function(){
			$("#bidAmount").text("");
		},
		expand : function () {
			$(this).toggleClass("expand-attendtion");
		}
	});

	// 页面显示之后，通过参数获取数据
	var timeout_news, interval_date, timeout_ibuy;
	$("#ibuy").on("pageloaded", function() {
		//计算屏幕宽度用于计算图片显示的高度
		//vm.datas.screenWidth = $(document.body).width();
		vm.datas.pageIndex = 1;
		// 请求数据
//		$.get("domainList.json", 
//				{
//					pageIndex : vm.datas.pageIndex,
//					singleDomainId : vm.datas.tmp.singleDomainId,
//					shareClientId : vm.datas.tmp.shareClientId
//				},
//				function(res){
//					if (res.type === 'success') {
						vm.datas.domainList.clear();
//						vm.datas.domainList.pushArray(res.data.domainList);
						vm.datas.domainList.pushArray(pageData.domainList);
//						vm.datas.tmp.nickname = res.data.currClient.nickname;
//						vm.datas.tmp.currentClientId = res.data.currClient.id;
						//红包开关
						vm.datas.shareBonusEnable = pageData.shareBonusEnable;
						//服务器当前时间
						vm.$sysServerTime = pageData.sysServerTime;
//						var loadIndex = 0;
//						var loadLength = pageData.domainList.length;
//						if (loadIndex < loadLength) {
//							vm.datas.domainList.push(pageData.domainList[loadIndex++]);
//						}
//						var pushData = function() { 
//                            if (loadIndex < loadLength) { 
//                                vm.datas.domainList.push(pageData.domainList[loadIndex++]); 
//                                setTimeout(pushData, 100); 
//                            } else {
//                            	
//                            	setTimeout(function(){
//                            		$("#ibuy").trigger("touchmove");
//                            		$("#ibuy").trigger("scrollEnd");
//                            		
//                            		if ($("#ibuy").data("scroll")) {
//	                            		var dml = $.m.Scroll("#ibuy");
//	                            		dml.scroller.refresh();
//                            		}
//                            	}, 100);
//                            }
//                        }
//                        pushData();
						//设置图片显示
//						for(var i = 0;i < vm.datas.domainList.length; i++){
//							$("#image1"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//							$("#image2"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//							$("#image3"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//						}
						
						vm.datas.tmp.nickname = pageData.currClient.nickname;
						if (pageData.currClient) {
							vm.datas.tmp.currentClientId = pageData.currClient.id;
						}
						vm.datas.pageIndex = vm.datas.pageIndex + 1;
						if (vm.datas.tmp.singleDomainId != undefined && vm.datas.tmp.singleDomainId != null && vm.datas.tmp.singleDomainId != "") {
						} else {
							utils.loadmore("loadmore", function(callback){
								$.get("domainList.json", 
										{pageIndex : vm.datas.pageIndex},
										function(res){
											if (res.type == "success") {
												vm.datas.domainList.pushArray(res.data.domainList);
												vm.datas.pageIndex = vm.datas.pageIndex + 1;
												//设置图片显示
//												for(var i = 0;i < vm.datas.domainList.length; i++){
//													$("#image1"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//													$("#image2"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//													$("#image3"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//												}
											} else {
//												$.tips({
//													content : res.msg,
//													stayTime : 4000,
//													type : res.type
//												});
												setTimeout(function(){
													$("#loadmore").addClass("hide-loadmore");
												}, 2000);
											}
											if ($("#ibuy").data("scroll")) {
			                            		var dml = $.m.Scroll("#ibuy");
			                            		dml.scroller.refresh();
		                            		}
											callback(res.type);
										});
							});
						}
//					} else {
//						$.tips({
//							content : res.msg,
//							stayTime : 4000,
//							type : res.type
//						});
//					}
//					$.m.loading.hide();
//				});
						

						
						pollingMessage();
						interval_ibuy_status_check();
						
		$("#bondForm #offline").click(function(e){
			$("#bondForm #platformBankInfo").removeClass("hidden");
			e.stopPropagation();
		});
		$("#bondForm #online").click(function(e){
			$("#bondForm #platformBankInfo").addClass("hidden");
			e.stopPropagation();
		});
	});

	$("#ibuy").on("pageshow", function() {
		//设置图片显示
//		for(var i = 0;i < vm.datas.domainList.length; i++){
//			$("#image1"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//			$("#image2"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//			$("#image3"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//		}
		if (pageData.currClient) {
			if (vm.datas.tmp.singleDomainId != undefined && vm.datas.tmp.singleDomainId != null && vm.datas.tmp.singleDomainId != "") {
			} else {
				// 获取新消息 新消息轮询
				
			}
		}
		// 定时设置当前时间
		interval_date = setInterval(function() {
			if (redrawData) {
				utils.jscache.clear();
				vm.datas.tmp.newDate = vm.$sysServerTime;//页面滑动时，倒计时停止，要同步真正的服务器时间vm.$sysServerTime
			}
		}, 1000);
		// 轮询页面最新状态
		//interval_ibuy_status_check();
	});
	
	// 设置服务器时间每秒加1秒
	setInterval(function() {
		vm.$sysServerTime = vm.$sysServerTime + 1000;//服务器时间加1秒
	}, 1000);
	
	var msgTimestamp = 0;
	// 获取新消息 新消息轮询
	var pollingMessage = function(){ 
		if (!redrawData) {
			return;
		}
		
		if (vm.datas.tmp.currentClientId == "" || vm.datas.tmp.currentClientId == null) {
			return;
		}
		
		$.get("polling/message.json", {timeStamp: msgTimestamp, _ : Math.random()}, function(res) {
			msgTimestamp = res.data.timeStamp;
			if (res.data.messageNum) {
				vm.datas.newsCnt = res.data.messageNum;
			}
			timeout_news = setTimeout(pollingMessage, 500);
		});
    }
    
    var invalid_date_func = function() {
		if (redrawData) {
			utils.jscache.clear();
			vm.datas.tmp.newDate = vm.$sysServerTime;
		}
	}
                
                
	var getPos = function(e) {
		var ev = e;
		if (e._args) {
			ev = e._args;
		}
		if (!ev.touches || ev.touches.length == 0) {
			return nowPos;
		}
		return {
			x : ev.touches[0].pageX,
			y : ev.touches[0].pageY
		}
	}
	var posYDelta = function(p1, p2) {
		var d = p1.y - p2.y;
		d = d<0?-d:d;
		return d;
	}
	var redrawData = true;
	var startPos = {x:0,y:0};
	var nowPos = {x:0,y:0};
	$("#ibuy").on("scrollStart", function(e) {
		//console.log("scrollStart")
		nowPos = startPos = getPos(e);
		//clearTimeout(timeout_news);
		clearInterval(interval_date);
		//clearTimeout(timeout_ibuy);
		redrawData = false;
	});
	//$("#ibuy").on("touchmove", function(e) {
		//console.log("touchmove")
		//nowPos = getPos(e);
	//});
	$("#ibuy").on("touchend", function(e) {
		console.log("touchend")
		//nowPos = getPos(e);
		if (!redrawData){
			interval_date = setInterval(invalid_date_func, 1000);
			redrawData = true;
		}
	});
	$("#ibuy").on("scrollEnd", function(e) {
		//console.log("scrollEnd")
		redrawData = true;
		// 定时设置当前时间
		if (!redrawData){
			interval_date = setInterval(invalid_date_func, 1000);
		}
		//interval_ibuy_status_check();
		//pollingMessage();
	});
	
	$("#ibuy").on("scrollTouchEnd", function(e) {
		//console.log("scrollTouchEnd")
		//console.log(getPos(e))
		//if (!redrawData){
		//	redrawData = posYDelta(nowPos, startPos) <3;
			// 定时设置当前时间
		//	interval_date = setInterval(invalid_date_func, 1000);
			//interval_ibuy_status_check();
			//pollingMessage();
		//}
		
	});
	
	// 轮询ibuy数据
	var interval_ibuy_status_check = function() {
		//if (!redrawData) {
		//	return;
		//}
		if (vm.datas.domainList.size() == 0) {
			return;
		}
		var domainIdList = [];
		for (var i = 0; i < vm.datas.domainList.size(); i++) {
			domainIdList.push(vm.datas.domainList[i].id);
		}
		$.post(
			"polling/ibuyData.json",
			{
				domainIdList : domainIdList,
				timeStamp : vm.datas.timeStamp,
				_ : Math.random()
			},
			function(res) {
				if (res.type == "success") {
					if(vm.datas.timeStamp != res.data.timeStamp){//有新数据
						vm.$sysServerTime = res.data.sysServerTime;//同步服务器当前时间
						vm.datas.timeStamp = res.data.timeStamp;
						for (var i = 0; i < res.data.ibuyData.length; i++) {
							// 增加出价记录
							vm.datas.domainList[i].attentioned = res.data.ibuyData[i].attentioned;
							vm.datas.domainList[i].endTime = res.data.ibuyData[i].endTime;
							vm.datas.domainList[i].currAmount = res.data.ibuyData[i].currAmount;
							vm.datas.domainList[i].deposit = res.data.ibuyData[i].deposit;
							vm.datas.domainList[i].increment = res.data.ibuyData[i].increment;
							vm.datas.domainList[i].bidList = res.data.ibuyData[i].bidList;
							vm.datas.domainList[i].proxyAmount = res.data.ibuyData[i].proxyAmount;
							vm.datas.domainList[i].proxyIncrement = res.data.ibuyData[i].proxyIncrement;
							vm.datas.domainList[i].attentionCount = res.data.ibuyData[i].attentionCount;
							vm.datas.domainList[i].attentionList = res.data.ibuyData[i].attentionList;
							vm.datas.domainList[i].bidCount = res.data.ibuyData[i].bidCount;
							vm.datas.domainList[i].topBidClientId = res.data.ibuyData[i].topBidClientId;
							
							//更新按钮的值
							if (vm.datas.domainList[i].clientId != vm.datas.tmp.currentClientId && (vm.datas.domainList[i].bidCount==0 || vm.datas.domainList[i].topBidClientId != vm.datas.tmp.currentClientId)) {
								$("#"+vm.datas.domainList[i].id).text("出价（" + (utils.priceDisplay(vm.datas.domainList[i].currAmount + vm.datas.domainList[i].increment)) + "）");
							}
							if (vm.datas.domainList[i].clientId != vm.datas.tmp.currentClientId && vm.datas.domainList[i].bidCount>0 && vm.datas.domainList[i].topBidClientId == vm.datas.tmp.currentClientId && vm.datas.domainList[i].proxyAmount) {
								$("#"+vm.datas.domainList[i].id).text("出价（" + (utils.priceDisplay(vm.datas.domainList[i].proxyAmount + vm.datas.domainList[i].proxyIncrement)) + "）");
							}
							if (vm.datas.domainList[i].clientId != vm.datas.tmp.currentClientId && vm.datas.domainList[i].bidCount>0 && vm.datas.domainList[i].topBidClientId == vm.datas.tmp.currentClientId && !vm.datas.domainList[i].proxyAmount) {
								$("#"+vm.datas.domainList[i].id).text("出价（" + (utils.priceDisplay(vm.datas.domainList[i].currAmount + vm.datas.domainList[i].increment)) + "）");
							}
						}
					}
				}
				timeout_ibuy = setTimeout(interval_ibuy_status_check, 500);
			}
		);
	}

	// 用户昵称显示优化
	avalon.filters.hideMiddle = function(nickname) {
		if (nickname.length < 3) {
			return nickname;
		} else {
			return nickname.charAt(0) + "**" + nickname.charAt(nickname.length - 1);
		}
	};

	// 当前价显示优化
	avalon.filters.transferCurrentAmount = function(amount) {
		return utils.priceDisplay(amount);
	};

	// 页面改变之后，清除轮询操作
	$("#ibuy").on("pagehide", function() {
//		clearTimeout(timeout_news);
		clearInterval(interval_date);
//		clearTimeout(timeout_ibuy); 
		
		//离开页面时，清除所有数据
		//vm.datas.domainList.clear();
		//vm.datas.pageIndex = 1;
		vm.datas.newsCnt = 0;
		vm.datas.shareClientId = "";
		vm.datas.tmp.domainId = "";
		vm.datas.tmp.index = 0;
		vm.datas.tmp.newDate = new Date().getTime();
		vm.datas.tmp.singleDomainId = $.m.getParam().singleDomainId ? $.m.getParam().singleDomainId : '';
		vm.datas.tmp.shareClientId = $.m.getParam().shareClientId ? $.m.getParam().shareClientId : '';
		//vm.datas.tmp.nickname = "";
		//vm.datas.tmp.currentClientId = null;
		vm.datas.tmp.bidBtn = null;
		vm.datas.tmp.deposit = 0;
	});
	
	$("#ibuy").on("pageloaded", function() {
		if (pageData.currClient) {
		// 获取jsticket
			$.get("jsapiTicket", {
				href : window.location.href
			}, function(res) {
				if (res.type === "success") {
					vm.datas.shareClientId = res.data.shareClientId;
					var config = res.data;
					// 注册微信JS
					wx.config({
						debug : false,
						appId : config.appId,
						timestamp : config.timestamp,
						nonceStr : config.nonceStr,
						signature : config.signature, // 签名
						jsApiList : [ 'hideMenuItems', 'onMenuShareTimeline',
								'onMenuShareAppMessage', 'previewImage' ,'chooseWXPay']
					});
					
					var mainTitle = '碉堡了，刷刷“朋友圈”就能拍卖域名';
					var mainDesc = "你没见过这么简单的域名拍卖，点进来试试。每天推出各种品类的精品域名。";
					var mainUrl = window.location.href;
					var mainImg = "http://" + window.location.host + ctx + "/static/images/brand_logo.jpg";

					wx.ready(function() {
						// 隐藏菜单
						console.log("config执行成功");
						wx.hideMenuItems({
							menuList : [ "menuItem:share:qq" ]
						});
						
						
						wx.onMenuShareAppMessage({
							title: mainTitle, // 分享标题
							desc: mainDesc, // 分享描述
							link: mainUrl, // 分享链接
							imgUrl: mainImg, // 分享图标
							type: '', // 分享类型,music、video或link，不填默认为link
							dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
							success: function () { 
								// 用户确认分享后执行的回调函数
								//shareSuccessCallback(index, self);
								$("#share-float").removeClass("show");
							},
							cancel: function () {
								// 用户取消分享后执行的回调函数
								$("#share-float").removeClass("show");
							}
						});
			
						//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
						wx.onMenuShareTimeline({
							title: mainTitle, // 分享标题
							desc: mainDesc, // 分享描述
							link: mainUrl, // 分享链接
							imgUrl: mainImg, // 分享图标
							success : function() {
								// 用户确认分享后执行的回调函数
								//shareSuccessCallback(index, self);
								$("#share-float").removeClass("show");
							},
							cancel : function() {
								// 用户取消分享后执行的回调函数
								$("#share-float").removeClass("show");
							}
						});
			
						//获取“分享到QQ”按钮点击状态及自定义分享内容接口
						wx.onMenuShareQQ({
							title: mainTitle, // 分享标题
							desc: mainDesc, // 分享描述
							link: mainUrl, // 分享链接
							imgUrl: mainImg, // 分享图标
							success : function() {
								// 用户确认分享后执行的回调函数
								//shareSuccessCallback(index, self);
								$("#share-float").removeClass("show");
							},
							cancel : function() {
								// 用户取消分享后执行的回调函数
								$("#share-float").removeClass("show");
							}
						});
			
						//获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
						wx.onMenuShareWeibo({
							title: mainTitle, // 分享标题
							desc: mainDesc, // 分享描述
							link: mainUrl, // 分享链接
							imgUrl: mainImg, // 分享图标
							success : function() {
								// 用户确认分享后执行的回调函数
								//shareSuccessCallback(index, self);
								$("#share-float").removeClass("show");
							},
							cancel : function() {
								// 用户取消分享后执行的回调函数
								$("#share-float").removeClass("show");
							}
						});
					});

					wx.error(function() {
						console.log("config执行失败");
					});
				} else {
					$.tips({
						content : res.msg,
						stayTime : 2000,
						type : res.type
					});
				}
			});
		}
	})

	$(".numKeyboard").find("li.ui-col").bind("touchstart", function(){
//		var val = $("#bidForm").find("input").val();
		var val = $("#bidAmount").text();
		var selfVal = $(this).attr("data");
		if(selfVal==="del") {
			val = val.substring(0, val.length - 1);
		} else {
			val = val + "" + selfVal;
		}
//		$("#bidForm").find("input").val(val);
		$("#bidAmount").text(val);
//		clearInterval(interval_cursor);
//		$("#icon-cursor").addClass("icon-cursor");
//		setTimeout(function(){
//			interval_cursor = setInterval(function() {
//				$("#icon-cursor").toggleClass("icon-cursor");
//			}, 500);
//		},1000);
	});

	$(".icon-close").bind("click", function(){
		$("#bidForm").dialog("hide");
//		clearInterval(interval_cursor);
	});
	
	$("#openBigPhoto").click(function () {
		//alert(2)
		//var src = $(this).find("img").attr("src");
		var d = $("<div class='bigphoto'>");
		
		var d2 = $("<div class='back-white'>");
		var i = $("<img>");
		i.attr("src",ctx + "/static/images/qrcode.jpg");
		d2.append(i);
		
		d2.append("<br>");
		
		var s = $("<span>");
		s.text("长按二维码关注拍域名，立即参与竞拍。");
		d2.append(s);
		
		d.append(d2);
		
		d.click(function(){
			d.remove();
		})
		$("#personalInfo").on("pagehide",function() {
			d.remove();
		});
	
		$("body").append(d);
	});

	function shareSuccessCallback(index, self) {
		$("#share-float").removeClass("show");
		$.post('share', {domainId : vm.datas.domainList[index].id, hasRedBag : self.hasClass("icon-share-red-bag")},
			function (res) {
				if (res.type === 'success') {
					$("#share-float").removeClass("show");
				} else {
					$("#share-float").removeClass("show");
					$.tips({
						content : res.msg,
						stayTime : 2000,
						type : res.type
					});
				}
			});
	}

	return vm;
})