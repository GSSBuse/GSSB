//个人中心修改信息    
function changeSubmit(){
        var id = $("#change").find("#id").eq(0).val();
        //var username = $("#change").find("#username").eq(0).val();
        var name = $("#change").find("#name").eq(0).val();
        var photo = $("#change").find("#photo").eq(0).val();
        var mobile = $("#change").find("#mobile").eq(0).val();
        var email = $("#change").find("#email").eq(0).val();
        var qq = $("#change").find("#qq").eq(0).val();
        var wechat = $("#change").find("#wechat").eq(0).val();
        var payway = $("#change").find("#payway").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        var ajaxResult;
        $.ajax({
			url : ctx + "/change.json",
			type : "POST",
			data : {
				id : id,
				name:name,
				photo:photo,
				//username:username,
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
    
//个人中心提现申请 001
function putwalletSubmit(){
    /*var userId = $("#putwallet").find("##user_id").eq(0).val();
    var username = $("#putwallet").find("#username").eq(0).val();
    var money = $("#putwallet").find("#money").eq(0).val();
    var payway = $("#putwallet").find("#payway").eq(0).val();
    var mobile = $("#putwallet").find("#mobile").eq(0).val();
    */
	var zhanghujine = $("#zhanghujine").val();
	
	var userId = $("#userId").val();
    var username = $("#user_name").val();
    var money = $("#money").val();
    var payway = $("#payways").val();
    var mobile = $("#mobiles").val();
	
   
    /*alert(zhanghujine);
    alert(userId);
    alert(username);
    alert(money);
    alert(payway);
    alert(mobile);*/
    
    if($(".tips ").is(":visible")){
        return false;
    }
    if(money == null  || money == "" ){
        showError("请输入您要提现的金额");
        return false;
    }
    if(payway == null  || payway == ""){
        showError("请输入您的支付宝账号");
        return false;
    }
    if(mobile == null  || mobile == ""){
        showError("请输入提现成功后的通知电话");
        return false;
    }
    
    if(zhanghujine < money){
    	showError("您的账户余额不足，请重新输入提现金额");
        return false;
    }
    
    var ajaxResult;
    $.ajax({
		url : ctx + "/putwallet.json",
		type : "POST",
		data : {
			userId : userId,
			username : username,
			money : money,
			payway : payway,
			mobile : mobile
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



   //个人中心修改买标信息 001
    function changebuySubmit(){
        var id = $("#changebuy").find("#idbuy").eq(0).val();
        var title = $("#changebuy").find("#title").eq(0).val();
        var mobile = $("#changebuy").find("#mobile").eq(0).val();
        var realname = $("#changebuy").find("#realname").eq(0).val();
        var user_id = $("#changebuy").find("#user_id").eq(0).val();
        
        if($(".tips ").is(":visible")){
            return false;
        }
        if(title == null  || title == ""){
            showError("请输入您要修改的标题");
            return false;
        }
        if(mobile == null  || mobile == ""){
            showError("请输入您要修改的联系电话");
            return false;
        }
        if(realname == null  || realname == ""){
            showError("请输入您要修改的联系人");
            return false;
        }
        var ajaxResult;
        $.ajax({
			url : ctx + "/changebuy.json",
			type : "POST",
			data : {
				id : id,
				title:title,
				realname:realname,
				mobile:mobile,
				user_id:user_id
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
    
    //个人中心修改卖标信息 002
    function changesoldSubmit(){
        var id = $("#changesold").find("#idsold").eq(0).val();
        var title = $("#changesold").find("#title").eq(0).val();
        var mobile = $("#changesold").find("#mobile").eq(0).val();
        var realname = $("#changesold").find("#realname").eq(0).val();
        var user_id = $("#changesold").find("#user_id").eq(0).val();
        
        if($(".tips ").is(":visible")){
            return false;
        }
        if(title == null  || title == ""){
            showError("请输入您要修改的标题");
            return false;
        }
        if(mobile == null  || mobile == ""){
            showError("请输入您要修改的联系电话");
            return false;
        }
        if(realname == null  || realname == ""){
            showError("请输入您要修改的联系人");
            return false;
        }
        var ajaxResult;
        $.ajax({
			url : ctx + "/changesold.json",
			type : "POST",
			data : {
				id : id,
				title:title,
				realname:realname,
				mobile:mobile,
				user_id:user_id
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
    //个人中心删除买标信息 0001
    function deletebuydialogSubmit(){
        var id = $("#deletebuydialog").find("#idbuydelete").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        var ajaxResult;
        $.ajax({
			url : ctx + "/deletebuydialog.json",
			type : "POST",
			data : {
				id : id
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
    
    //个人中心删除卖标信息 0002
    function deletesolddialogSubmit(){
        var id = $("#deletesolddialog").find("#idsolddelete").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        var ajaxResult;
        $.ajax({
			url : ctx + "/deletesolddialog.json",
			type : "POST",
			data : {
				id : id
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
    
    //个人中心删除悬赏信息 0003
    function deleterewarddialogSubmit(){
        var id = $("#deleterewarddialog").find("#idrewarddelete").eq(0).val();
        if($(".tips ").is(":visible")){
            return false;
        }
        var ajaxResult;
        $.ajax({
			url : ctx + "/deleterewarddialog.json",
			type : "POST",
			data : {
				id : id
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
       
    
    
    
    
