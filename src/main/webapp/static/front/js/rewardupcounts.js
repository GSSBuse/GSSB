jQuery(document).ready(function(e) {

	$ = jQuery;


$('#like-it-form .like-it').click(function(){
	        var likeButton = $(this);
	        var likeHtml = likeButton.html();
	        var likeNum = parseInt(likeHtml, 10);
	        likeNum += 1;
	        
	        likeButton.html(likeNum);

	        
	        var c = window.location.href.split("?")[1].substring(3,35);
		       
	        $("#rewardid").val(c);
	        
	        $.ajax({
				url : ctx + "/rewardupcounts.json",
				type : "POST",
				data : {
					id : $("#rewardid").val(),
					upCount : likeNum
				},
	        	dataType : 'json',
	        	async : false,
				success : function(data) {
					if (data.type == 'success') {
						return true;
					} else {
						return false;
					}
				},
				error : function(data) {
					return false;
				}
			});
	    });
});