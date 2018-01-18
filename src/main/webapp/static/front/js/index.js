
/**
 * 首页
 */
 
pageData = window.pageData?window.pageData:[];

var vm = avalon.define({
		$id : "index1",
		test: "tst",
		domainBuyList1 : [{title:"test"},{title:"test2"}],
		//domainSoldList1 : [{title:"test"},{title:"test2"}],//买标信息一览（最新11条，首页只表示最新的）
		datas : {
			domainBuyList : [],//买标信息一览（最新11条，首页只表示最新的）
			domainSoldList : [],//卖标信息一览（最新11条，首页只表示最新的）
			domainRewardList : [],//卖标信息一览（最新11条，首页只表示最新的）
			timeStamp: 0,
			tmp : {
				currentClientId : null,
				newDate : new Date()
			},
			userinfo : {
				id : ""
			}
		},
		//跳转至单个买标信息详细页面
		goToSingleDomainBuy : function(index){
			window.location.href = ctx + "/gbbuysingle.html?id=" + vm.datas.domainBuyList[index].id;
		},
		//跳转至单个卖标信息详细页面
		goToSingleDomainSold : function(index){
			window.location.href = ctx + "/gbsoldsingle.html?id=" + vm.datas.domainSoldList[index].id;
		},
		//跳转至单个悬赏起名详细页面
		goToSingleDomainReward : function(index){
			window.location.href = ctx + "/gbrewardsingle.html?id=" + vm.datas.domainRewardList[index].id;
		},

		//跳转至查看买标信息一览页面
		goToSingleDomainname : function(index){
			window.location.href = ctx + "/gbbuylists.html";
		},
		//跳转至单个卖标信息一览页面
		goToSingleDomainname : function(index){
			window.location.href = ctx + "/gbsoldlists.html";
		},
		//跳转至单个悬赏起名一览页面
		goToSingleDomainname : function(index){
			window.location.href = ctx + "/gbrewardlists.html";
		},
		
//		showLogin : function (e) {
//			$("#js_uiLoginBox").show();
//			e.stopPropagation();
//			e.preventDefault();
//			return false;
//		}
	});

	// 初始化动作
	$(function(){      
		avalon.scan();
		//TODO
	});
	
	// 轮询买标信息一览数据
	var interval_gbbuy_status_check = function() {
		
		var count = 7;//首页最多显示11条
		
		$.post(
			"polling/gbbuyData.json",
			{
				count : count         //参数1，检索的limit条数
			},
			function(res) {
				if (res.type == "success") {					
					vm.datas.domainBuyList.clear();
					vm.datas.domainBuyList.pushArray(res.data.gbbuyData);
					timeout_gbbuy = setTimeout(interval_gbbuy_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	 ();
	
	// 轮询卖标信息一览数据
	var interval_gbsold_status_check = function() {
		//TODO
          var count = 7;//首页最多显示11条
		$.post(
			"polling/gbsoldData.json",
			{
				count : count         //参数1，检索的limit条数
			},
			function(res) {
				if (res.type == "success") {					
					vm.datas.domainSoldList.clear();
					vm.datas.domainSoldList.pushArray(res.data.gbsoldData);
					timeout_gbsold = setTimeout(interval_gbsold_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_gbsold_status_check();
	
	// 轮询悬赏信息一览数据
	var interval_gbreward_status_check = function() {
		//TODO
		 var count = 7;//首页最多显示11条
			$.post(
				"polling/gbrewardData.json",
				{
					count : count         //参数1，检索的limit条数
				},
				function(res) {
					if (res.type == "success") {					
						vm.datas.domainRewardList.clear();
						vm.datas.domainRewardList.pushArray(res.data.gbrewardData);
						timeout_gbreward = setTimeout(interval_gbreward_status_check, 30000); //30秒自动刷新一次
					}
				}
			);
	}
	interval_gbreward_status_check();
