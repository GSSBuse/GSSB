package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.wx.entity.domainname.PageDomainEntity;
import com.thinkgem.jeesite.modules.wx.web.domainname.IbuyController;

@Controller
@RequestMapping(value= {"ibuy"})
public class FrontIbuyController {
	
	@Autowired
	IbuyController ibuyController;
	
	@RequestMapping(value= {""})
	public String index(Model model, String domain) {
		domain = HtmlUtils.htmlEscape(domain);
		AjaxResult ar = this.domainList(model, String.valueOf(1), domain);
		model.addAttribute("initData", JsonMapper.toJsonString(ar.getData()));
		model.addAttribute("searchDomain", domain);
		return "modules/paimai/front/ibuy";
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
	public AjaxResult domainList(Model model, String pageIndex, String domain) {
		Integer pageSize = 12;
		if (StringUtils.isNotEmpty(domain)) {
			pageSize = 9999;
		}
		
		AjaxResult ar = ibuyController.domainList(model, pageIndex, "", "",pageSize);
		if (StringUtils.isNotEmpty(domain)) {
			List<PageDomainEntity> list = (List<PageDomainEntity>)ar.getData().get("domainList");
			
			int pageIndexI = pageIndex != null? Integer.parseInt(pageIndex):1;
			int start = (pageIndexI- 1) * pageSize ;
			int end = pageIndexI * pageSize - 1;
			List<PageDomainEntity> filterList = new ArrayList<PageDomainEntity>();
			if (list != null) {
				for (PageDomainEntity de : list) {
		            if (de.getName().contains(domain)) {
		            	filterList.add(de);
		            }
	            }
			}
			start = start > filterList.size() ? 0:start;
			end = end > filterList.size() ? filterList.size() : end;
			ar.getData().put("domainList", filterList.subList(start, end));
			ar.getData().put("count", filterList.size());
		}
		
		return ar;
	}
}
