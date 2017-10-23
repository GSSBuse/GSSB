package com.thinkgem.jeesite.modules.paimai.front.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.wx.web.domainname.IbuyController;

@Controller
@RequestMapping(value = "${frontPath}")
public class FrontIndexController implements Constant{
	
	@Autowired
	IbuyController ibuyController;
	
	@Autowired
	private DyClientService dyClientService;             // 会员信息管理Service
	
	/**
	 * 网站首页
	 */
	@RequestMapping(value= {"", "index"})
	public String index(Model model) {
		AjaxResult ar = this.domainList(model, String.valueOf(1), "", "");
		model.addAttribute("initData", JsonMapper.toJsonString(ar.getData()));
		return "modules/paimai/front/index";
	}
	
	/**
	 * 根据分页页码获取所有有效域名信息
	 * @param model 页面模型
	 * @param pageIndex 分页页码
	 * @param singleDomainId 单域名页面域名ID
	 * @param shareClientId 分享者ID
	 * @return 我要买 域名列表数据
	 */
	@ResponseBody
	@RequestMapping(value = {"domainList"})
	public AjaxResult domainList(Model model, String pageIndex, String singleDomainId, String shareClientId) {
		return ibuyController.domainList(model, pageIndex, singleDomainId, shareClientId,12);
	}
	
	@ResponseBody
	@RequestMapping(value = {"polling/ibuyData"})
	public AjaxResult ibuyData(Model model, @RequestParam("timeStamp") String timeStamp, @RequestParam("domainIdList[]") String...domainIdList) {
		return ibuyController.ibuyData(timeStamp, domainIdList);
	}
	
}
