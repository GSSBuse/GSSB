

    function xuanshangSubmit(){
        var id = $("#xuanshang-form").find("#reward-id").eq(0).val();
        var successfulBidder = $("#xuanshang-form").find("#successfulBidder").eq(0).val();
        var totalFee = $("#xuanshang-form").find("#totalFee").eq(0).val();
        var status = "已中标";
        if($(".tips ").is(":visible")){
            return false;
        }
        /*if(comment == null  || comment == ""){
            showError("请输入评论");
            return false;
        }*/
        
       
        
        var ajaxResult;
        $.ajax({
			url : ctx + "/xuanshangSubmit.json",
			type : "POST",
			data : {
				id : id,
				totalFee : totalFee,
				successfulBidder:successfulBidder,
				status : status
			},
        	dataType : 'json',
        	async : false,
			success : function(data) {
				if (data.type == 'success') {
					ajaxResult = true;
					//alert("111");
		        	$(".tome").remove();
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
        
    }
    
   
    