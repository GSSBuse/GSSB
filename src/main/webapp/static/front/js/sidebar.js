
/**
 * 首页
 */
 
pageData = window.pageData?window.pageData:[];

var vm = avalon.define({
		$id : "sidebar",
		test: "tst",
		//domainBuyList1 : [{title:"test"},{title:"test2"}],
		//domainSoldList1 : [{title:"test"},{title:"test2"}],//买标信息一览（最新11条，首页只表示最新的）
		datas : {
			domainArticleList : [],//sidebar信息一览（最新5条）12.31
			
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
	var interval_GbjArticle_status_check = function() {
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
					//vm.datas.domainArticleList.clear();
					vm.datas.domainArticleList.pushArray(res.data.GbjArticle);
					timeout_GbjArticle = setTimeout(interval_GbjArticle_status_check, 30000); //30秒自动刷新一次
				}
			}
		);
	}
	interval_GbjArticle_status_check();
