package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.modules.wx.entity.domainname.PageDomainEntity;
import com.thinkgem.jeesite.modules.wx.web.domainname.IbuyController;

@Controller
@RequestMapping(value = { "ibuysingle" })
public class FrontIbuyDetailController {

	@Autowired
	private IbuyController iBuyController;

	@RequestMapping(value = { "" })
	public ModelAndView ibuyDetail(@RequestParam("domainId") String domainId, Model model) {
		ModelAndView mav = new ModelAndView("modules/paimai/front/ibuysingle");

		AjaxResult result = this.iBuyController.domainList(model, "0", domainId, null, null);
		List<PageDomainEntity> pDomainList = (List<PageDomainEntity>) result.getData().get("domainList");
		if (pDomainList != null && !pDomainList.isEmpty()) {
			PageDomainEntity pDomain = pDomainList.get(0);
			mav.addObject("domainnameDetail", pDomain);
		}

		return mav;
	}
}