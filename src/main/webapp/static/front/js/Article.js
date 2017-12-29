
/**
 * 首页
 */
 
pageData = window.pageData?window.pageData:[];

var vm = avalon.define({
		$id : "articles",
		test: "tst",
		//domainBuyList1 : [{title:"test"},{title:"test2"}],
		//domainSoldList1 : [{title:"test"},{title:"test2"}],//买标信息一览（最新11条，首页只表示最新的）
		datas : {
			 domainArticleList : [],//买标信息一览（最新11条，首页只表示最新的）
		     domainBuyArticleList : [],//卖标信息一览（最新11条，首页只表示最新的）
		    domainBuyCommentsArticleList:[],//评论内容获取
		    domainSoldCommentsArticleList:[],//评论内容获取
		    domainRewardCommentsArticleList:[],
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
		/*//跳转至单个买标信息详细页面
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
		},*/
		
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
	var interval_Article_status_check = function() {
		
		var count = 6;//首页最多显示11条
		
		$.post(
			"polling/ArticleData.json",
			{
				count : count         //参数1，检索的limit条数
			},
			function(res) {
				if (res.type == "success") {					
					vm.datas.domainArticleList.clear();
					vm.datas.domainArticleList.pushArray(res.data.ArticleData);
					timeout_Article = setTimeout(interval_Article_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_Article_status_check();
	
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
	
	//评论内容展示
var interval_ArticleBuyComments_status_check = function() {
	
	var x = window.location.href.split("?")[1].substring(3,35);
	
		//alert(x);
		var count = 11;//首页最多显示11条
		var id= x;
		//var id = null;
		//var id="${gbjBuyDetail.id}";
		//alert("${gbjBuyDetail.id}");
		$.post(
			"polling/ArticleBuyCommentsData.json",
			{
				count : count ,   //参数1，检索的limit条数
				id    :   id
			},
			function(res) {
				if (res.type == "success") {					
					vm.datas.domainBuyCommentsArticleList.clear();
					vm.datas.domainBuyCommentsArticleList.pushArray(res.data.ArticleBuyCommentsData);
					timeout_ArticleBuyComments = setTimeout(interval_ArticleBuyComments_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_ArticleBuyComments_status_check();
	
	//買標评论内容展示
	var interval_ArticleSoldComments_status_check = function() {
		
		var y = window.location.href.split("?")[1].substring(3,35);
		
			//alert(x);
			var count = 11;//首页最多显示11条
			var id= y;
			//var id = null;
			//var id="${gbjBuyDetail.id}";
			//alert("${gbjBuyDetail.id}");
			$.post(
				"polling/ArticleSoldCommentsData.json",
				{
					count : count ,   //参数1，检索的limit条数
					id    :   id
				},
				function(res) {
					if (res.type == "success") {					
						vm.datas.domainSoldCommentsArticleList.clear();
						vm.datas.domainSoldCommentsArticleList.pushArray(res.data.ArticleSoldCommentsData);
						timeout_ArticleSoldComments = setTimeout(interval_ArticleSoldComments_status_check, 30000); //30秒自动刷新一次
					}
				}
			);
		}
		interval_ArticleSoldComments_status_check();
		
		//悬赏评论内容展示
		var interval_ArticleRewardComments_status_check = function() {
			
			var z = window.location.href.split("?")[1].substring(3,35);
			
				//alert(x);
				var count = 11;//首页最多显示11条
				var id= z;
				//var id = null;
				//var id="${gbjBuyDetail.id}";
				//alert("${gbjBuyDetail.id}");
				$.post(
					"polling/ArticleRewardCommentsData.json",
					{
						count : count ,   //参数1，检索的limit条数
						id    :   id
					},
					function(res) {
						if (res.type == "success") {					
							vm.datas.domainRewardCommentsArticleList.clear();
							vm.datas.domainRewardCommentsArticleList.pushArray(res.data.ArticleRewardCommentsData);
							timeout_ArticleRewardComments = setTimeout(interval_ArticleRewardComments_status_check, 30000); //30秒自动刷新一次
						}
					}
				);
			}
			interval_ArticleRewardComments_status_check();
			
		
			
