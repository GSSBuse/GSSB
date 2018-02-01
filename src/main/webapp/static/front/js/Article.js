
/**
 * 首页
 */
 
pageData = window.pageData?window.pageData:[];

var vm11 = avalon.define({
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
		     domainBuyReplyCommentsArticleList:[],
		     domainSoldReplyCommentsArticleList:[],
		     domainRewardReplyCommentsArticleList:[],
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
					vm11.datas.domainUserBuyArticleList.clear();
					vm11.datas.domainUserBuyArticleList.pushArray(res.data.UserBuyArticleData);
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
					vm11.datas.domainUserSoldArticleList.clear();
					vm11.datas.domainUserSoldArticleList.pushArray(res.data.UserSoldArticleData);
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
					vm11.datas.domainUserRewardArticleList.clear();
					vm11.datas.domainUserRewardArticleList.pushArray(res.data.UserRewardArticleData);
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
					vm11.datas.domainBuyCommentsArticleList.clear();
					vm11.datas.domainBuyCommentsArticleList.pushArray(res.data.ArticleBuyCommentsData);
					timeout_ArticleBuyComments = setTimeout(interval_ArticleBuyComments_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_ArticleBuyComments_status_check();
	
	
	//回复评论内容展示  18.1.12
var interval_ArticleBuyReplyComments_status_check = function() {
	var x = window.location.href.split("?")[1].substring(3,35);
		//alert(x);
		var count = 11;//首页最多显示11条
		var id= x;
		//var toId='4827051ac1044cdaa36e247c2abd494f';//暂时屏蔽掉 了，因为会出现错误。
		//var id = null;
		//var id="${gbjBuyDetail.id}";
		//alert("${gbjBuyDetail.id}");
		$.post(
			"polling/ArticleBuyReplyCommentsData.json",
			{
				count : count ,   //参数1，检索的limit条数
				id    :   id     //参数:检索买标信息id
				//toId  :  toId     //参数:检索评论信息id
			},
			function(res) {
				if (res.type == "success") {					
					vm11.datas.domainBuyReplyCommentsArticleList.clear();
					vm11.datas.domainBuyReplyCommentsArticleList.pushArray(res.data.ArticleBuyReplyCommentsData);
					timeout_ArticleBuyReplyComments = setTimeout(interval_ArticleBuyReplyComments_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_ArticleBuyReplyComments_status_check();
	
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
						vm11.datas.domainSoldCommentsArticleList.clear();
						vm11.datas.domainSoldCommentsArticleList.pushArray(res.data.ArticleSoldCommentsData);
						timeout_ArticleSoldComments = setTimeout(interval_ArticleSoldComments_status_check, 30000); //30秒自动刷新一次
					}
				}
			);
		}
		interval_ArticleSoldComments_status_check();
		
		//回复评论内容展示  18.1.12
		var interval_ArticleSoldReplyComments_status_check = function() {
			var x = window.location.href.split("?")[1].substring(3,35);
				//alert(x);
				var count = 11;//首页最多显示11条
				var id= x;
				//var id = null;
				//var id="${gbjBuyDetail.id}";
				//alert("${gbjBuyDetail.id}");
				$.post(
					"polling/ArticleSoldReplyCommentsData.json",
					{
						count : count ,   //参数1，检索的limit条数
						id    :   id
					},
					function(res) {
						if (res.type == "success") {					
							vm11.datas.domainSoldReplyCommentsArticleList.clear();
							vm11.datas.domainSoldReplyCommentsArticleList.pushArray(res.data.ArticleSoldReplyCommentsData);
							timeout_ArticleSoldReplyComments = setTimeout(interval_ArticleSoldReplyComments_status_check, 30000); //30秒自动刷新一次
						}
					}
				);
			}
			interval_ArticleSoldReplyComments_status_check();
			
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
							vm11.datas.domainRewardCommentsArticleList.clear();
							vm11.datas.domainRewardCommentsArticleList.pushArray(res.data.ArticleRewardCommentsData);
							timeout_ArticleRewardComments = setTimeout(interval_ArticleRewardComments_status_check, 30000); //30秒自动刷新一次
						}
					}
				);
			}
			interval_ArticleRewardComments_status_check();
		
			//回复评论内容展示  18.1.12
			var interval_ArticleRewardReplyComments_status_check = function() {
				var x = window.location.href.split("?")[1].substring(3,35);
					//alert(x);
					var count = 11;//首页最多显示11条
					var id= x;
					//var id = null;
					//var id="${gbjBuyDetail.id}";
					//alert("${gbjBuyDetail.id}");
					$.post(
						"polling/ArticleRewardReplyCommentsData.json",
						{
							count : count ,   //参数1，检索的limit条数
							id    :   id
						},
						function(res) {
							if (res.type == "success") {					
								vm11.datas.domainRewardReplyCommentsArticleList.clear();
								vm11.datas.domainRewardReplyCommentsArticleList.pushArray(res.data.ArticleRewardReplyCommentsData);
								timeout_ArticleRewardReplyComments = setTimeout(interval_ArticleRewardReplyComments_status_check, 30000); //30秒自动刷新一次
							}
						}
					);
				}
				interval_ArticleRewardReplyComments_status_check();
				
			
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
								vm11.datas.domainCommentsArticleList.clear();
								vm11.datas.domainCommentsArticleList.pushArray(res.data.ArticleCommentsData);
								timeout_ArticleComments = setTimeout(interval_ArticleComments_status_check, 30000); //30秒自动刷新一次
							}
						}
					);
				}
				interval_ArticleComments_status_check();
			
			
			
