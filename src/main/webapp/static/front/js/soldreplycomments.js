    function soldreplySubmit(){
    	var soldId = $("#replyform").find("#soldId").eq(0).val();
        var replyComments = $("#replyform").find("#replyComments").eq(0).val();
        var toId = $("#replyform").find("#toId").eq(0).val();
        var userId = $("#replyform").find("#userId").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        if(replyComments == null  || replyComments == ""){
            showError("请输入评论");
            return false;
        }
        var ajaxResult;
        $.ajax({
			url : ctx + "/soldreplycomments.json",
			type : "POST",
			data : {
				soldId : soldId,
				replyComments : replyComments,
				toId:toId,
				userId:userId
			},
        	dataType : 'json',
        	async : false,
			success : function(data) {
				if (data.type == 'success') {
					location.reload();
				} else {
					showError(data.msg);
					ajaxResult = false;
					return false;
				}
			},
			error : function(data) {
				showError(data.responseText);
				ajaxResult = false;
				return false;
			}
		});
        
        if (ajaxResult == false) {
        	return false;
        }
        return true;
    }
    