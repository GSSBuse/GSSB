package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTouristRequire;
import com.thinkgem.jeesite.modules.sys.service.gb.GbBuyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjBuyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjTouristRequireService;

/**
 * 官网首页 页面控制器
 * @author wanghs
 * @since 2017/11/15
 *
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class FrontIndexController extends BaseController{
		
	@Autowired
	private GbjTouristRequireService gbjTouristRequireService;             // 国商商标查询Service

	@Autowired
	private GbBuyService gbBuyService; //我要买标service
	
	
	@Autowired
	private GbjBuyService gbjBuyService;                       //我要买标信息发布 2017/12/3  by snnu                                
	
	
	
	/**
	 * 网站首页
	 */
	@RequestMapping(value= {"", "index"})
	public String index(Model model) {
		return "modules/paimai/front/index";
	}	

	/**
	 * 免费查询提交表单
	 */
	@RequestMapping(value= {"searchBrand"})
	@ResponseBody
	public AjaxResult searchBrand(Model model, GbjTouristRequire gbjTouristRequire, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		model.addAttribute("domainInfoJson", JsonMapper.toJsonString(gbjTouristRequire));
		try{
			
			//STEP1  提交查询信息，保存到数据库
			gbjTouristRequireService.save(gbjTouristRequire);
			
			/*保存日志*/  //暂时去掉业务日志，后期打开 by wanghs
			//LogUtils.saveSpecialLog(request, null);
			
			//STEP2 发送邮件  TODO
			// SendMailUtil.sendCommonMail(toMailAddr, subject, message);			
			
			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
			
			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【"+e.getMessage()+"】");
			return AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】");
		}
		
	}
	/**
	 * 我要买标信息提交   2017/12/3  
	 * by snnu
 	 */
	@RequestMapping(value= {"gbBuy"})  
	@ResponseBody
	public AjaxResult gbBuy(Model model, GbjBuy gbjBuy, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		model.addAttribute("domain2InfoJson", JsonMapper.toJsonString(gbjBuy));
		try{
			
			
			//STEP1  提交查询信息，保存到数据库
			gbjBuyService.save(gbjBuy);
		
			addMessage(redirectAttributes, "提交查询成功，我们会及时联系您！");
			
			return AjaxResult.makeSuccess("提交查询成功，我们会及时联系您！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "提交查询成功失败【"+e.getMessage()+"】");
			return AjaxResult.makeError("提交查询成功失败【"+e.getMessage()+"】");
		}
		
	}
	
	/**
	 * 获取页面显示我要买标信息的最新数据
	 * @param domainBuyIdList
	 * @return 我要买标的最新数据
	 */
	@RequestMapping(value = "polling/gbbuyData")
	@ResponseBody
	public AjaxResult gbbuyData( @RequestParam("count") String count) {
		
		//取得最新的我要买标信息
		List<GbBuy> pageDomainBuyList = new ArrayList<GbBuy>();
		try {
			pageDomainBuyList = gbBuyService.findDomainBuyList(count);
		   	 
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("gbbuyData", pageDomainBuyList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}
	
}
