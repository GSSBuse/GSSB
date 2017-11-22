
/**
 * 首页
 */
 
pageData = window.pageData?window.pageData:[];

require(["utils"], function(utils) {
	var vm = avalon.define({
		$id : "ibuy",
		datas : {
			//记录轮询的时间戳，与后台相等时不更新操作
			timeStamp : "0",
			domainList : pageData.domainList,
			count : pageData.count,
			pageIndex : 1,
			newsCnt : 0,
			shareClientId: "",
			tmp : {
				currentClientId : null,
				newDate : new Date()
			},
			userinfo : {
				id : ""
			}
		},
		//跳转至单域名页面
		goToSingleDomainname : function(index){
			window.location.href = ctx + "/ibuysingle.html?domainId=" + vm.datas.domainList[index].id;
		},
		// 计算倒计时
		getCountDown : function(endTime, index) {
			try {
				var restTime = utils.parseDateStr(endTime).getTime() - vm.datas.tmp.newDate;
				if (utils.jscache.getV(restTime)) {
					return utils.jscache.getV(restTime);
				}
				//var restTime = new Date(endTime.replace(/-/g, "/")) - vm.datas.tmp.newDate;
				if (restTime <= 0) {
					return {
						disabled : true,
						displayTime : '00时00分00秒'
					}
				}
				var data = {}, h = m = s = "";
				data = utils.millisecondToDate(restTime);
				var displayTime = data.displayTime;
				
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
		showLogin : function (e) {
			$("#js_uiLoginBox").show();
			e.stopPropagation();
			e.preventDefault();
			return false;
		},
		goPrev: function () {
			if ($(this).is(".disabled")) {
				return;
			}
			vm.datas.pageIndex = vm.datas.pageIndex - 1;
			vm.refresh();
		},
		goNext: function () {
			if ($(this).is(".disabled")) {
				return;
			}
			vm.datas.pageIndex = vm.datas.pageIndex + 1;
			vm.refresh();
		},
		refresh: function () {
			// 请求数据
			$.get("domainList.json", 
				{
					pageIndex : vm.datas.pageIndex,
					singleDomainId : "",
					shareOpenid : ""
				},
				function(res){
					vm.datas.domainList.clear();
					vm.datas.domainList.pushArray(res.data.domainList);
				}
			);
		}
	});

	// 初始化动作
	$(function(){
		avalon.scan();
		$.get(ctx+"/financialManagement/userInfo.json", {}, function(res) {
			//用户信息
			avalon.mix(vm.datas.userinfo, res.data.userinfo);
		});
	});
	// 倒计时
	var countDonwInterv = setInterval(function(){
		utils.jscache.clear();
		vm.datas.tmp.newDate = new Date();
	}, 1000);
	
	
	// 轮询ibuy数据
	var interval_ibuy_status_check = function() {
		if (vm.datas.domainList.size() == 0) {
			return;
		}
		var domainIdList = [];
		for (var i = 0; i < vm.datas.domainList.size(); i++) {
			domainIdList.push(vm.datas.domainList[i].id);
		}
		$.post(
			ctx + "/polling/ibuyData.json",
			{
				domainIdList : domainIdList,
				timeStamp : vm.datas.timeStamp
			},
			function(res) {
				if (res.type == "success") {
					if(vm.datas.timeStamp != res.data.timeStamp){//有新数据
						vm.datas.timeStamp = res.data.timeStamp;
						for (var i = 0; i < res.data.ibuyData.length; i++) {
							// 增加出价记录
							vm.datas.domainList[i].endTime = res.data.ibuyData[i].endTime;
							vm.datas.domainList[i].currAmount = res.data.ibuyData[i].currAmount;
							vm.datas.domainList[i].deposit = res.data.ibuyData[i].deposit;
							vm.datas.domainList[i].increment = res.data.ibuyData[i].increment;
							//vm.datas.domainList[i].bidList = res.data.ibuyData[i].bidList;
							//vm.datas.domainList[i].proxyAmount = res.data.ibuyData[i].proxyAmount;
							//vm.datas.domainList[i].proxyIncrement = res.data.ibuyData[i].proxyIncrement;
							//vm.datas.domainList[i].attentionCount = res.data.ibuyData[i].attentionCount;
							//vm.datas.domainList[i].attentionList = res.data.ibuyData[i].attentionList;
							vm.datas.domainList[i].bidCount = res.data.ibuyData[i].bidCount;
							vm.datas.domainList[i].topBidClientId = res.data.ibuyData[i].topBidClientId;
						}
					}
				}
				timeout_ibuy = setTimeout(interval_ibuy_status_check, 3000);
			}
		);
	}
	interval_ibuy_status_check();
});