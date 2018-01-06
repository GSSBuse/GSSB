
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
			 //domainArticleList : [],//sidebar信息一览（最新5条）12.31
			 domainUserBuyArticleList : [],//个人中心买标信息一览（最新11条，首页只表示最新的）12.31
			 domainUserSoldArticleList : [],//个人中心卖标信息一览（最新11条，首页只表示最新的 12.31
			 domainUserRewardArticleList : [],//个人中心悬赏信息一览（最新11条，首页只表示最新的 12.31
		     domainBuyCommentsArticleList:[],//买标评论内容获取
		     domainSoldCommentsArticleList:[],//卖标评论内容获取
		     domainRewardCommentsArticleList:[],//悬赏评论内容获取
		    domainCommentsArticleList:[],
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
	});
	// 初始化动作
	$(function(){
		avalon.scan();
		//TODO
	});
	
	//sidebar信息一览（最新5条）12.31
	/*var interval_GbjArticle_status_check = function() {
		//var v = window.location.href.split("?")[1].substring(3,35);
		//alert(u);
		var count = 5;//首页最多显示11条
		//var id= v;
		
		$.post(
			"polling/GbjArticle.json",
			{
				count : count        //参数1，检索的limit条数
			//	id:id
			},
			function(res) {
				if (res.type == "success") {					
					vm.datas.domainArticleList.clear();
					vm.datas.domainArticleList.pushArray(res.data.GbjArticle);
					timeout_GbjArticle = setTimeout(interval_GbjArticle_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_GbjArticle_status_check();*/
	
	// 轮询个人中心买标信息一览数据
	var interval_UserBuyArticle_status_check = function() {
		var v = window.location.href.split("?")[1].substring(3,35);
		//alert(u);
		var count = 20;//首页最多显示11条
		var id= v;
		
		$.post(
			"polling/UserBuyArticleData.json",
			{
				count : count ,        //参数1，检索的limit条数
				id:id
			},
			function(res) {
				if (res.type == "success") {					
					vm.datas.domainUserBuyArticleList.clear();
					vm.datas.domainUserBuyArticleList.pushArray(res.data.UserBuyArticleData);
					timeout_UserBuyArticle = setTimeout(interval_UserBuyArticle_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_UserBuyArticle_status_check();
	
	// 轮询个人中心卖标信息一览数据
	var interval_UserSoldArticle_status_check = function() {
		var v = window.location.href.split("?")[1].substring(3,35);
		//alert(u);
		var count = 20;//首页最多显示11条
		var id= v;
		
		$.post(
			"polling/UserSoldArticleData.json",
			{
				count : count ,        //参数1，检索的limit条数
				id:id
			},
			function(res) {
				if (res.type == "success") {					
					vm.datas.domainUserSoldArticleList.clear();
					vm.datas.domainUserSoldArticleList.pushArray(res.data.UserSoldArticleData);
					timeout_UserSoldArticle = setTimeout(interval_UserSoldArticle_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_UserSoldArticle_status_check();
	
	// 轮询个人中心悬赏信息一览数据
	var interval_UserRewardArticle_status_check = function() {
		var v = window.location.href.split("?")[1].substring(3,35);
		//alert(u);
		var count = 20;//首页最多显示11条
		var id= v;
		
		$.post(
			"polling/UserRewardArticleData.json",
			{
				count : count ,        //参数1，检索的limit条数
				id:id
			},
			function(res) {
				if (res.type == "success") {					
					vm.datas.domainUserRewardArticleList.clear();
					vm.datas.domainUserRewardArticleList.pushArray(res.data.UserRewardArticleData);
					timeout_UserRewardArticle = setTimeout(interval_UserRewardArticle_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_UserRewardArticle_status_check();
	
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
			
			//用户评论内容展示
			var interval_ArticleComments_status_check = function() {
				var u = window.location.href.split("?")[1].substring(3,35);
					//alert(u);
					var count = 20;//首页最多显示11条
					var id= u;
					//var id = null;
					//var id="${gbjBuyDetail.id}";
					//alert("${gbjBuyDetail.id}");
					$.post(
						"polling/CommentsData.json",
						{
							count : count ,   //参数1，检索的limit条数
							id    :   id
						},
						function(res) {
							if (res.type == "success") {					
								vm.datas.domainCommentsArticleList.clear();
								vm.datas.domainCommentsArticleList.pushArray(res.data.ArticleCommentsData);
								timeout_ArticleComments = setTimeout(interval_ArticleComments_status_check, 30000); //30秒自动刷新一次
							}
						}
					);
				}
				interval_ArticleComments_status_check();
			
			
			
