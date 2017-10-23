

define("singleDomainname", function (){

	var interval_cursor;
	var vm = avalon.define({
		$id : "singleDomainname",
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
			},
			singlePage: window.singlePage
		},
		//跳转至ibuy页面
		linkToAllDomain : function(){
			var href = window.location.href;
			if(href.indexOf("ibuy") > 0){
				$.m.changePage("#ibuy");
			}else{
				window.location.href = ctx +"/domainname/ibuy.html"
			}
			
			//href="${pageContext.request.contextPath}/domainname/ibuy" rel="external"
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
							vm.datas.domainList[index].attentioned    = !vm.datas.domainList[index].attentioned;
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
//			if (vm.datas.tmp.currentClientId == "" || vm.datas.tmp.currentClientId == null) {
//				$.tips({
//					content : "请先关注公共号再进行域名分享",
//					stayTime : 2000,
//					type : "error"
//				}).on("tips:hide",function(){
//					$.m.changePage("#error");
//				});
//				return false;
//			}
			
//			var endIndex = window.location.href.indexOf("?");
//			if (endIndex == -1) {
//				var url = window.location.href.slice(0) + "?singleDomainId=" + vm.datas.domainList[index].id + "&shareClientId=" + vm.datas.shareClientId;
//			} else {
//				var url = window.location.href.slice(0, endIndex) + "?singleDomainId=" + vm.datas.domainList[index].id + "&shareClientId=" + vm.datas.shareClientId;
//			}
			
			var self = $(this);
			//分享按钮操作
			$("#share-float-single").addClass("show").click(function(){
				$(this).removeClass("show");
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
		bidFormSingle : function(index){
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
//					vm.datas.domainList[index].topBidClientId = res.data.pDomain.topBidClientId;
//					vm.datas.domainList[index].endTime = res.data.pDomain.endTime;
//					vm.datas.domainList[index].currAmount = res.data.pDomain.currAmount;
//					vm.datas.domainList[index].deposit = res.data.pDomain.deposit;
//					vm.datas.domainList[index].increment = res.data.pDomain.increment;
//					vm.datas.domainList[index].bidList = res.data.pDomain.bidList;
//					vm.datas.domainList[index].bidCount = res.data.pDomain.bidCount;
//					vm.datas.domainList[index].proxyAmount = res.data.pDomain.proxyAmount;
//					vm.datas.domainList[index].proxyIncrement = res.data.pDomain.proxyIncrement;
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
							$("#bidAmountSingle").text(domain.currAmount + domain.increment);
							$("#bidCurrentSingle").text(vm.datas.domainList[index].currAmount);
						}
						if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
							$("#bidAmountSingle").text(domain.proxyAmount + domain.proxyIncrement);
							$("#bidCurrentSingle").text(vm.datas.domainList[index].currAmount);
						}
						if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
							$("#bidAmountSingle").text( domain.currAmount + domain.increment);
							$("#bidCurrentSingle").text(vm.datas.domainList[index].currAmount);
						}
						$("#bidFormSingle").dialog("show");
					} catch (e) {
						alert(e);
					}
//				}else{//历史无人出价
//					$("#bidAmountSingle").text( vm.datas.domainList[index].increment);
//					$("#bidCurrentSingle").text(vm.datas.domainList[index].currAmount);
//					$("#bidFormSingle").dialog("show");
//				}
//			});

//			interval_cursor = setInterval(function() {
//				$("#icon-cursor").toggleClass("icon-cursor");
//			}, 500);
		},
		// 出价
		bidding : function(){
			$("#bidFormSingle").dialog("hide");
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
//				var bidAmount = $("#bidFormSingle").find("input").val();
				var bidAmount = $("#bidAmountSingle").text();
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
											vm.datas.domainList[vm.datas.tmp.index].topBidClientId = res.data.pDomain.topBidClientId;
											vm.datas.domainList[vm.datas.tmp.index].endTime = res.data.pDomain.endTime;
											vm.datas.domainList[vm.datas.tmp.index].currAmount = res.data.pDomain.currAmount;
											vm.datas.domainList[vm.datas.tmp.index].deposit = res.data.pDomain.deposit;
											vm.datas.domainList[vm.datas.tmp.index].increment = res.data.pDomain.increment;
											vm.datas.domainList[vm.datas.tmp.index].bidList = res.data.pDomain.bidList;
											vm.datas.domainList[vm.datas.tmp.index].bidCount = res.data.pDomain.bidCount;
											vm.datas.domainList[vm.datas.tmp.index].proxyAmount = res.data.pDomain.proxyAmount;
											vm.datas.domainList[vm.datas.tmp.index].proxyIncrement = res.data.pDomain.proxyIncrement;

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
													$("#bidAmountSingle").text(domain.currAmount + domain.increment);
													$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												}
												if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
													$("#bidAmountSingle").text(domain.proxyAmount + domain.proxyIncrement);
													$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												}
												if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
													$("#bidAmountSingle").text( domain.currAmount + domain.increment);
													$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												}
												vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
											} catch (e) {
												alert(e);
											}
										}else{//历史无人出价
											$("#bidAmountSingle").text( vm.datas.domainList[vm.datas.tmp.index].increment);
											$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
											vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
										}
									});
							} else if (res.type == "warn") {
								if (res.data.type) {
									$.tips({
										content : res.msg,
										stayTime : 2000,
										type : res.type
									});
									$.get("getBidListByDomainId", {domainId : vm.datas.domainList[index].id},
											function(res) {
											if(res.data.isNoBid=="false"){//历史有人出价
												vm.datas.domainList[vm.datas.tmp.index].topBidClientId = res.data.pDomain.topBidClientId;
												vm.datas.domainList[vm.datas.tmp.index].endTime = res.data.pDomain.endTime;
												vm.datas.domainList[vm.datas.tmp.index].currAmount = res.data.pDomain.currAmount;
												vm.datas.domainList[vm.datas.tmp.index].deposit = res.data.pDomain.deposit;
												vm.datas.domainList[vm.datas.tmp.index].increment = res.data.pDomain.increment;
												vm.datas.domainList[vm.datas.tmp.index].bidList = res.data.pDomain.bidList;
												vm.datas.domainList[vm.datas.tmp.index].bidCount = res.data.pDomain.bidCount;
												vm.datas.domainList[vm.datas.tmp.index].proxyAmount = res.data.pDomain.proxyAmount;
												vm.datas.domainList[vm.datas.tmp.index].proxyIncrement = res.data.pDomain.proxyIncrement;

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
														$("#bidAmountSingle").text(domain.currAmount + domain.increment);
														$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
														$("#bidAmountSingle").text(domain.proxyAmount + domain.proxyIncrement);
														$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
														$("#bidAmountSingle").text( domain.currAmount + domain.increment);
														$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
													$("#bidFormSingle").dialog("show");
												} catch (e) {
													alert(e);
												}
											}else{//历史无人出价
												$("#bidAmountSingle").text( vm.datas.domainList[vm.datas.tmp.index].increment);
												$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
												$("#bidFormSingle").dialog("show");
											}
										});
								} else {
									$.get("getBidListByDomainId", {domainId : vm.datas.domainList[index].id},
											function(res) {
											if(res.data.isNoBid=="false"){//历史有人出价
												vm.datas.domainList[vm.datas.tmp.index].topBidClientId = res.data.pDomain.topBidClientId;
												vm.datas.domainList[vm.datas.tmp.index].endTime = res.data.pDomain.endTime;
												vm.datas.domainList[vm.datas.tmp.index].currAmount = res.data.pDomain.currAmount;
												vm.datas.domainList[vm.datas.tmp.index].deposit = res.data.pDomain.deposit;
												vm.datas.domainList[vm.datas.tmp.index].increment = res.data.pDomain.increment;
												vm.datas.domainList[vm.datas.tmp.index].bidList = res.data.pDomain.bidList;
												vm.datas.domainList[vm.datas.tmp.index].bidCount = res.data.pDomain.bidCount;
												vm.datas.domainList[vm.datas.tmp.index].proxyAmount = res.data.pDomain.proxyAmount;
												vm.datas.domainList[vm.datas.tmp.index].proxyIncrement = res.data.pDomain.proxyIncrement;

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
														$("#bidAmountSingle").text(domain.currAmount + domain.increment);
														$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && domain.proxyAmount) {
														$("#bidAmountSingle").text(domain.proxyAmount + domain.proxyIncrement);
														$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													if (domain.clientId != vm.datas.tmp.currentClientId && domain.bidCount>0 && domain.topBidClientId == vm.datas.tmp.currentClientId && !domain.proxyAmount) {
														$("#bidAmountSingle").text( domain.currAmount + domain.increment);
														$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
													}
													
													vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
//													$("#bidFormSingle").dialog("show");
												} catch (e) {
													alert(e);
												}
											}else{//历史无人出价
												$("#bidAmountSingle").text( vm.datas.domainList[vm.datas.tmp.index].increment);
												$("#bidCurrentSingle").text(vm.datas.domainList[vm.datas.tmp.index].currAmount);
												vm.datas.tmp.domainId = vm.datas.domainList[vm.datas.tmp.index].id;
//												$("#bidFormSingle").dialog("show");
											}
										});
									vm.datas.tmp.deposit = res.data.deposit;
									
									$("#bondFormSingle #platformBankInfo").addClass("hidden");
									$("#bondFormSingle #online").trigger('click');
									$("#bondFormSingle").dialog("show");
								}
							} else {
								$.tips({
									content : res.msg,
									stayTime : 2000,
									type : res.type
								});
							}
						},
						error : function(res) {
							$.tips({
								content : "出价失败，请重试",
								stayTime : 2000,
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
			$("#bondFormSingle #offline").trigger('click');
		},
		//设置选择微信充值
		selectOnLine : function(){
			$("#bondFormSingle #online").trigger('click');
		},
		// 充值请求
		charge : function(chargeAmount){
			$("#bondFormSingle #platformBankInfo").addClass("hidden");
			$("#bondFormSingle").dialog("hide");
			var chargeType = $("#bondFormSingle").find("input[name='chargeType']:checked").attr("id");
			if (chargeType === "offline") {
				$.post("rechargeOrWithdrawals", {operate : "充值", operateAmount : chargeAmount,from : "rechargeForBid"}, function(res){
					if(res.type == "success"){
						$("#bondFormSingle #online").trigger('click');
						$.tips({
							content : res.msg,
							stayTime:2000,
							type : res.type
						}).on("tips:hide",function(){
							$("#singleDomainname #prompt-msg").dialog("show");
						});;
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
						$("#bondFormSingle #platformBankInfo").addClass("hidden");
						$("#bondFormSingle").dialog("hide");
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
				
			} catch (e) {
				alert(e);
			}
			return {
				disabled : false,
				displayTime : displayTime
			};
		},
		clearAmount : function(){
			$("#bidAmountSingle").text("");
		}
	});
	
	var wxready = false;
	function setupShareLink() {
		if (vm.datas.domainList.length <= 0) {
			return;
		}
		if (!wxready) {
			return;
		}
		var index = 0;
		var url = utils.shareHref(window.location.href,"singleDomainname.html")+"?singleDomainId=" + vm.datas.domainList[index].id ;
		
		if (vm.datas.shareClientId) {
			url = url + "&shareClientId=" + vm.datas.shareClientId;
		}
		var mainTitle = vm.datas.domainList[index].name;
		var mainImg = vm.datas.domainList[index].image1 
			? "http://" + window.location.host + vm.datas.domainList[index].image1
			: "http://" + window.location.host + ctx + "/static/images/brand_logo.jpg";
			
		if (vm.datas.domainList[index].status === "03") {
			mainTitle += '正在【拍域名】拍卖';
		}
		
		wx.onMenuShareAppMessage({
			title: mainTitle, // 分享标题
			desc: vm.datas.domainList[index].description, // 分享描述
			link: url, // 分享链接
			imgUrl: mainImg, // 分享图标
			type: '', // 分享类型,music、video或link，不填默认为link
			dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			success: function () { 
				// 用户确认分享后执行的回调函数
				shareSuccessCallback(index, self);
			},
			cancel: function () {
				// 用户取消分享后执行的回调函数
				$("#share-float-single").removeClass("show");
			}
		});

		//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
		wx.onMenuShareTimeline({
			title : mainTitle , // 分享标题
			link : url, // 分享链接
			imgUrl : mainImg, // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
				shareSuccessCallback(index, self);
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
				$("#share-float-single").removeClass("show");
			}
		});

		//获取“分享到QQ”按钮点击状态及自定义分享内容接口
		wx.onMenuShareQQ({
			title: mainTitle, // 分享标题
			desc: vm.datas.domainList[index].description, // 分享描述
			link : url, // 分享链接
			imgUrl : mainImg, // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
				shareSuccessCallback(index, self);
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
				$("#share-float-single").removeClass("show");
			}
		});

		//获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
		wx.onMenuShareWeibo({
			title: mainTitle, // 分享标题
			desc: vm.datas.domainList[index].description, // 分享描述
			link : url, // 分享链接
			imgUrl : mainImg, // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
				shareSuccessCallback(index, self);
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
				$("#share-float-single").removeClass("show");
			}
		});
	}

	// 页面显示之后，通过参数获取数据
	var interval_date, timeout_singleDomainname;
	$("#singleDomainname").on("pageloaded", function() {
		//计算屏幕宽度用于计算图片显示的高度
		//vm.datas.screenWidth = $(document.body).width();
		if (window.location.href.indexOf("ibuy.html") > 0) {
			wxready = true;
		} else {
			wx.ready(function() {
				wxready = true;
				setupShareLink();
			});
			// 初始化单域名页面的wx
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
				}
			});
		}
		
		$("#bondFormSingle #offline").click(function(e){
			$("#bondFormSingle #platformBankInfo").removeClass("hidden");
			e.stopPropagation();
		});
		$("#bondFormSingle #online").click(function(e){
			$("#bondFormSingle #platformBankInfo").addClass("hidden");
			e.stopPropagation();
		});
	});

	$("#singleDomainname").on("pageshow", function() {
		vm.datas.pageIndex = 1;
		vm.datas.tmp.singleDomainId = $.m.getParam().singleDomainId ? $.m.getParam().singleDomainId : '';
		vm.datas.tmp.shareClientId = $.m.getParam().shareClientId ? $.m.getParam().shareClientId : '';
		// 请求数据
		$.get("domainList.json", 
				{
					pageIndex : vm.datas.pageIndex,
					singleDomainId : vm.datas.tmp.singleDomainId,
					shareClientId : vm.datas.tmp.shareClientId
				},
				function(res){
					if (res.type === 'success') {
						vm.datas.domainList.clear();
						vm.datas.domainList.pushArray(res.data.domainList);
						vm.datas.tmp.nickname = res.data.currClient.nickname;
						vm.datas.tmp.currentClientId = res.data.currClient.id;
						vm.datas.shareBonusEnable = res.data.shareBonusEnable;
						//服务器当前时间
						vm.datas.tmp.newDate = res.data.sysServerTime;
						//设置页面的标题为域名名称
						//document.title = vm.datas.domainList[0].name;
						//设置图片显示
//						for(var i = 0;i < vm.datas.domainList.length; i++){
//							$("#image1"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//							$("#image2"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//							$("#image3"+vm.datas.domainList[i].id).css("height",+vm.datas.screenWidth*0.85*0.30+"px")
//						}
						setupShareLink();
					} else {
						$.tips({
							content : res.msg,
							stayTime : 4000,
							type : res.type
						});
					}
					
					interval_singleDomainname_status_check();
//					$.m.loading.hide();
				});
				
		
		// 定时设置当前时间
		interval_date = setInterval(function() {
			vm.datas.tmp.newDate = vm.datas.tmp.newDate + 1000;//页面滑动时，倒计时停止，要同步真正的服务器时间vm.datas.sysServerTime
		}, 1000);
	});
	
	var runningxhr = null;
	var interval_singleDomainname_status_check = function() {
		if (vm.datas.domainList.size() == 0) {
			setTimeout(interval_singleDomainname_status_check, 1000);
			return;
		}
		var domainIdList = [];
		for (var i = 0; i < vm.datas.domainList.size(); i++) {
			domainIdList.push(vm.datas.domainList[i].id);
		}
		$.ajax({
			url: "polling/ibuyData.json",
			data: {
				domainIdList : domainIdList,
				timeStamp : vm.datas.timeStamp
			},
			success: function(res) {
				if (res.type == "success") {
					//服务器当前时间
					if(vm.datas.timeStamp != res.data.timeStamp){//有新数据
						vm.datas.tmp.newDate = res.data.sysServerTime;
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
				runningxhr = null;
				timeout_singleDomainname = setTimeout(interval_singleDomainname_status_check, 1000);
			},
			beforeSend: function (xhr) {
				runningxhr = xhr;
			}
		});
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
	$("#singleDomainname").on("pagehide", function() {
		clearInterval(interval_date);
		clearTimeout(timeout_singleDomainname);
		try {
			if (runningxhr) {
				runningxhr.abort()
			}
		} catch (e) {
		}
		
		//离开页面时，清除所有数据
		//vm.datas.domainList.clear();
		//vm.datas.pageIndex = 1;
		vm.datas.newsCnt = 0;
		vm.datas.shareClientId = "";
		vm.datas.tmp.domainId = "";
		vm.datas.tmp.index = 0;
		vm.datas.tmp.newDate = new Date();
		vm.datas.tmp.singleDomainId = "";
		vm.datas.tmp.shareClientId = "";
		vm.datas.tmp.nickname = "";
		//vm.datas.tmp.currentClientId = null;
		vm.datas.tmp.bidBtn = null;
		vm.datas.tmp.deposit = 0;
		vm.datas.domainList.clear();
	});
	
	$("#singleDomainname").on("pageloaded", function() {
		/*
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

				wx.ready(function() {
					// 隐藏菜单
//					console.log("config执行成功");
//					wx.hideMenuItems({
//						menuList : [ "menuItem:share:qq" ]
//					});
				});

				wx.error(function() {
//					console.log("config执行失败");
				});
			}
		});*/
	})

	$(".numKeyboard").find("li.ui-col").bind("touchstart", function(){
//		var val = $("#bidFormSingle").find("input").val();
		var val = $("#bidAmountSingle").text();
		var selfVal = $(this).attr("data");
		if(selfVal==="del") {
			val = val.substring(0, val.length - 1);
		} else {
			val = val + "" + selfVal;
		}
//		$("#bidFormSingle").find("input").val(val);
		$("#bidAmountSingle").text(val);
//		clearInterval(interval_cursor);
//		$("#icon-cursor").addClass("icon-cursor");
//		setTimeout(function(){
//			interval_cursor = setInterval(function() {
//				$("#icon-cursor").toggleClass("icon-cursor");
//			}, 500);
//		},1000);
	});

	$(".icon-close").bind("click", function(){
		$("#bidFormSingle").dialog("hide");
//		clearInterval(interval_cursor);
	});

	function shareSuccessCallback(index, self) {
		$("#share-float-single").removeClass("show");
		if (vm.datas.currentClientId) {
			$.post('share', {domainId : vm.datas.domainList[index].id, hasRedBag : self.hasClass("icon-share-red-bag")},
				function (res) {
					if (res.type === 'success') {
						$("#share-float-single").removeClass("show");
					} else {
						$("#share-float-single").removeClass("show");
						$.tips({
							content : res.msg,
							stayTime : 2000,
							type : res.type
						});
					}
				}
			);
		}
	}

	return vm;
})