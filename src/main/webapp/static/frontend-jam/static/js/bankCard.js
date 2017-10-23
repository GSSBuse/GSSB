
define([], function() {
	/**
	 * 在逻辑代码中，一定要先绑定事件，然后再加载jqm
	 */
	var vm = avalon.define({
		$id : "bankCard"
	});
	
	$("#clearCardholderInput").tap(function() {
		document.getElementById("cardholder").value = "";
		document.getElementById("cardholder").focus();
	});
	
	$("#clearBankCardNumberInput").tap(function() {
		document.getElementById("bank-card-number").value = "";
		document.getElementById("bank-card-number").focus();
	});
	
	return vm;
});	