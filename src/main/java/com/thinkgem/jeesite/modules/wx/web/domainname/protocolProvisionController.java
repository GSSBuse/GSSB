package com.thinkgem.jeesite.modules.wx.web.domainname;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * @author wufl.fnst
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/domainname")
public class protocolProvisionController {

	Logger logger = Logger.getLogger(getClass());
	/**
	 * 查看协议、条款
	 */
	@RequestMapping(value = {"protocolProvision"}, method={RequestMethod.GET})
	public String  history(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/protocolProvision";
		}else{
			
		}
		
		return "modules/wx/domainname/history";
	}
	
	/**
	 * @return 获取协议、条款信息
	 */
	@RequestMapping(value = {"getProtocolProvisionInfo"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult getCompletedTransactionsInfo(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if(u!=null){
			
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("historyinfo", "");
			return ar;
		}else{
			AjaxResult ar = AjaxResult.makeError("没有用户登录！");
			return ar;
		}
		
	}
}
