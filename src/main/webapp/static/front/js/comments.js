    function commentSubmit(){
        var id = $("#commentform").find("#id").eq(0).val();
        var comment = $("#commentform").find("#comment").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        if(comment == null  || comment == ""){
            showError("请输入评论");
            return false;
        }
        
        var ajaxResult;
        $.ajax({
			url : ctx + "/comments.json",
			type : "POST",
			data : {
				id : id,
				comment : comment
			},
        	dataType : 'json',
        	async : false,
			success : function(data) {
				if (data.type == 'success') {
					
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