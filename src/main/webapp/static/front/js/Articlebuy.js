
/**
 * 首页
 */
 
pageData = window.pageData?window.pageData:[];

var vm = avalon.define({
		$id : "buyarticles",
		test: "tst",
		
		//domainBuyList1 : [{title:"test"},{title:"test2"}],
		//domainSoldList1 : [{title:"test"},{title:"test2"}],//买标信息一览（最新11条，首页只表示最新的）
		datas : {
			domainBuyArticleList : [],//买标信息一览（最新11条，首页只表示最新的）
			//domainSoldList : [],//卖标信息一览（最新11条，首页只表示最新的）
			//domainRewardList : [],//卖标信息一览（最新11条，首页只表示最新的）
			timeStamp: 0,
			tmp : {
				currentClientId : null,
				newDate : new Date()
			},
			userinfo : {
				id : ""
			}
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
var interval_ArticleBuy_status_check = function() {
		
		var count = 11;//首页最多显示11条
		
		$.post(
			"polling/ArticleBuyData.json",
			{
				count : count         //参数1，检索的limit条数
			},
			function(res) {
				if (res.type == "success") {					
					vm.datas.domainBuyArticleList.clear();
					vm.datas.domainBuyArticleList.pushArray(res.data.ArticleBuyData);
					timeout_ArticleBuy = setTimeout(interval_ArticleBuy_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_ArticleBuy_status_check();
	
	
