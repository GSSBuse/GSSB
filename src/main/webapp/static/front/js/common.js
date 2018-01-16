/**
 * 取得地址栏？后的参数内容
 * @param name:参数的key
 * @returns {String} 不存在则返回""
 **/
var if_firstime=true;
$(document).ready(function(){
	 var pageSize=11;
	 var y=Math.ceil((totalcounts)/pageSize);//由总条数除以每页数目得到总页数
	 var z=1;
	 if(GetQueryString("page")!=null && !isNaN(GetQueryString("page"))){
		 z=parseInt(GetQueryString("page"));
	 }
	 //alert(y);
	 $("#demo2").jqPaginator({
	        totalPages: y,
	        visiblePages: 10,
	        currentPage: z,
	        onPageChange: function (n) {
	            $("#demo2-text").html("当前第" + n + "页");
	            if(if_firstime){
	            	if_firstime=false;
	            }
	            else if(!if_firstime){
	            	window.location.href="${ctx }/personalcenter.html?id='current_user_id'&page="+n;
	            }
	        }
	    });
	});
