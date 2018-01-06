    function changeSubmit(){
        var id = $("#change").find("#id").eq(0).val();
        var username = $("#change").find("#username").eq(0).val();
        var name = $("#change").find("#name").eq(0).val();
        var mobile = $("#change").find("#mobile").eq(0).val();
        var email = $("#change").find("#email").eq(0).val();
        var qq = $("#change").find("#qq").eq(0).val();
        var wechat = $("#change").find("#wechat").eq(0).val();
        var payway = $("#change").find("#payway").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        if(username == null  || username == ""){
            showError("请输入您要修改的用户名");
            return false;
        }
        var ajaxResult;
        $.ajax({
			url : ctx + "/change.json",
			type : "POST",
			data : {
				id : id,
				name:name,
				username:username,
				mobile:mobile,
				email:email,
				qq:qq,
				wechat:wechat,
				payway:payway
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