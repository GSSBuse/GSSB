package com.thinkgem.jeesite.modules.paimai.front.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.SendMailUtil;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTouristRequire;
import com.thinkgem.jeesite.modules.sys.service.gb.GbBuyService;
import com.thinkgem.jeesite.modules.sys.service.gbj.GbjTouristRequireService;

@Controller
@RequestMapping(value = "${frontPath}")
public class FrontIndexController extends BaseController{
		
	@Autowired
	private GbjTouristRequireService gbjTouristRequireService;             // 国商商标查询Service
	
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
	
}
