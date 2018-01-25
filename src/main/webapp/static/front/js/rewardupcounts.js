jQuery(document).ready(function(e) {

	$ = jQuery;


$('#like-it-form .like-it').click(function(){
	
	
	var x = $('#loginspan').text();

	if (x == "登录") {

		$("#popup").show();//查找ID为popup的DIV show()显示#gray
		tc_center();
		$('.loginV2').show();
		$('.registerBox').hide();
		$('.form-error').hide();

		$(".td").click(function() {
			$("#popup").show();//查找ID为popup的DIV show()显示#gray
			tc_center();
			$('.loginV2').hide();
			$('.registerBox').show();
			$('.form-error').hide();
		});
		//点击关闭按钮
		$("a.guanbi").click(function() {
			$("#popup").hide();//查找ID为popup的DIV hide()隐藏
		})
		//点击退出按钮
		$(".logout").click(function() {
			$.ajax({
				url : ctx + "/logout.json",
				type : "POST",
				dataType : 'json',
				success : function(data) {
					window.location.reload();
					return true;
				},
				error : function(data) {
					alert(data.responseText);
					return false;
				}
			});
		});

		//窗口水平居中
		$(window).resize(function() {
			tc_center();
		});

		function tc_center() {
			var _top = ($(window).height() - $(".popup").height()) / 2;
			var _left = ($(window).width() - $(".popup").width()) / 2;

			$(".popup").css({
				top : _top,
				left : _left
			});
		}
	} 
	
	else{
		
		var likeButton = $(this);
        var likeHtml = likeButton.html();
        var likeNum = parseInt(likeHtml, 10);
        likeNum += 1;
        $(this).unbind('click');
        
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
	}
	        
	    });
});