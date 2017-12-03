package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.paimai.bean.GbjBuyEntity;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTouristRequire;
import com.thinkgem.jeesite.modules.sys.front.service.gbj.FrontGbjBuyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjBuyService;
import com.thinkgem.jeesite.modules.wx.entity.domainname.PageDomainEntity;

@Controller
//@RequestMapping(value = "${frontPath}")
public class FrontGbjBuyController extends BaseController{

	@Autowired
	private GbjBuyService gbjBuyService;
	
	//@RequestMapping(value= {"", "index"})
	public String index(Model model) {
		return "modules/paimai/front/index";
	}	
	
	//@RequestMapping(value= {"buy"})
	@ResponseBody
	public AjaxResult buy(Model model, GbjBuy gbjBuy, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		model.addAttribute("domainInfoJson", JsonMapper.toJsonString(gbjBuy));
		try{
			
			gbjBuyService.save(gbjBuy);
						
			
			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
			
			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【"+e.getMessage()+"】");
			return AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】");
		}
		
	}
	
	
	
}
