package com.thinkgem.jeesite.modules.paimai.front.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjBuyService;

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
