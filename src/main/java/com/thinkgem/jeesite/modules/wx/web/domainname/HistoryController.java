package com.thinkgem.jeesite.modules.wx.web.domainname;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;


/**
 * @author wufl.fnst
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/domainname")
public class HistoryController {
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private DyClientService clientService;
	
	@Autowired
	private DyDomainnameService domainnameService;
	
	@RequestMapping(value = {"error"})
	public String error(Model model) {
		return "modules/wx/domainname/error";
	}
	/**
	 * 查看平台最近七天交易记录
	 */
	@RequestMapping(value = {"getHistory"}, method={RequestMethod.GET})
	public String  history(Model model) {
		if (DySysUtils.getCurrentDyClient() == null) {
			return "modules/wx/domainname/error";
		}else{
			DyClient user = clientService.get(DySysUtils.getCurrentDyClient().getId());
			UserUtils.getSession().setAttribute("current_dy_client", user);
		}
		
		return "modules/wx/domainname/history";
	}
	

	/**
	 * @return 最近七天交易记录，记录以Map记录：domainnameId（域名id），status（域名状态），name（域名名称），sellClientId（卖家id），sellNickName（卖家昵称），sellDate（交易日期），bidAmount（成交日期），buyClientId（买家id），buyNickName(买家昵称)
	 */
	@RequestMapping(value = {"getHistoryInfo"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult getCompletedTransactionsInfo(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if(u!=null){
			List<Map<String , Object>> historyinfo = domainnameService.getHistoryInfo();
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("historyinfo", historyinfo);
			return ar;
		}else{
			AjaxResult ar = AjaxResult.makeError("没有用户登录！");
			return ar;
		}
		
	}

}
