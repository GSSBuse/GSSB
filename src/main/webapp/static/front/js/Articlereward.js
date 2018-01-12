
/**
 * 首页
 */
 
pageData = window.pageData?window.pageData:[];

var vmreward = avalon.define({
		$id : "rewardarticles",
		test: "tst",
		datas : {
			domainRewardArticleList : [],//买标信息一览（最新11条，首页只表示最新的）
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
var interval_ArticleReward_status_check = function() {
		
		var count = 11;//首页最多显示11条
		var page =  GetQueryString("page");
		$.post(
			"polling/ArticleRewardData.json",
			{
				count : count    ,     //参数1，检索的limit条数
				page:page
			},
			function(res) {
				if (res.type == "success") {					
					vmreward.datas.domainRewardArticleList.clear();
					vmreward.datas.domainRewardArticleList.pushArray(res.data.ArticleRewardData);
					timeout_ArticleReward = setTimeout(interval_ArticleReward_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_ArticleReward_status_check();
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	    
	}