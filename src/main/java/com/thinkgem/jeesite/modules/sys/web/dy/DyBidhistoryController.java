/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.dy;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBidhistoryService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.sys.utils.NewsUpdateFlagUtil;
import com.thinkgem.jeesite.modules.sys.utils.ShowDomainCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidClient;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 会员出价信息Controller
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyBidhistory")
public class DyBidhistoryController extends BaseController {

	@Autowired
	private DyBidhistoryService dyBidhistoryService;
	@Autowired
	private DyClientService dyClientService;
	@Autowired
	private DyDomainnameService dyDomainnameService;
	
	@ModelAttribute
	public DyBidhistory get(@RequestParam(required=false) String id) {
		DyBidhistory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyBidhistoryService.get(id);
		}
		if (entity == null){
			entity = new DyBidhistory();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(BidClient bidClient, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BidClient> page = dyBidhistoryService.findPage(new Page<BidClient>(request, response), bidClient); 
		DyDomainname dyDomainname = dyDomainnameService.get(bidClient.getDomainId());
		/**判断是否可以修改最高价的代理竞价*/
		String canAlter = "";
		if(page != null && page.getList().size() > 0){
			BidClient temp = page.getList().get(0);
			/*获取当前价的加价幅度*/
			Long gap = DySysUtils.calculateDepositAndIncrement(temp.getBidAmount(), Constant.BID_RULE_TYPE_INCREMENT);//计算当前保证金
			if(StringUtils.equals(dyDomainname.getStatus(), Constant.DOMAIN_STATUS_03) &&
					temp.getProxyAmount() != null &&
					temp.getProxyAmount() != 0 && 
					(temp.getProxyAmount() - temp.getBidAmount() > gap.longValue())){
				canAlter = "yes";
			}
			
		}
		model.addAttribute("page", page);
		/*判断是否可以添加出价*/
		model.addAttribute("domainStatus", dyDomainname.getStatus());
		model.addAttribute("canAlter", canAlter);
		return "modules/sys/dy/dyBidhistoryList";
	}

	@RequestMapping(value = "form")
	public String form(DyBidhistory dyBidhistory, HttpServletRequest request, Model model) {
		model.addAttribute("dyBidhistory", dyBidhistory);
		return "modules/sys/dy/dyBidhistoryForm";
	}

	@RequestMapping(value = "save")
	public String save(DyBidhistory dyBidhistory, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws ServiceException, Exception {
		DyClient dyClient = dyClientService.getByDyid(request.getParameter("dyid"));
		if(dyClient == null ){
			addMessage(model,"保存失败，找不到会员，请确认会员米友号是否有误");
			return "modules/sys/dy/dyBidhistoryForm";
		}
		if (!DySysUtils.conformToBidRule(dyBidhistory.getBidAmount())) {
			String[] rule = DySysUtils.getBidLevel(dyBidhistory.getBidAmount());
			addMessage(model,"保存失败，不符合出价规则（" + rule[0] + "加价幅度为" + rule[1] + "）");
			return "modules/sys/dy/dyBidhistoryForm";
		}
		try{
			//设置用户信息,传递参数
			dyBidhistory.setClientId(dyClient.getId());
			dyClient.setBrokerId(UserUtils.getUser().getId());
			//出价
			AjaxResult ajx = dyBidhistoryService.bids(dyBidhistory.getDomainnameId(), dyBidhistory.getBidAmount(), dyClient);
			addMessage(redirectAttributes, ajx.getMsg());
			if(!ajx.getMsg().contains("失败")){
				String paramsStr = "";
				paramsStr += "会员提交的出价信息如下：";
				paramsStr += " 	出价金额：" + request.getParameter("bidAmount");
				paramsStr += "  出价时间：" + request.getParameter("bidTime");
				paramsStr += "  经系统验证后，新的的出价信息如下：  ";
				BidClient bidClient = new BidClient();
				bidClient.setDomainId(dyBidhistory.getDomainnameId());
				bidClient.setClientId(dyClient.getId());
				/*保存日志*/
				LogUtils.saveSpecialLog(request, paramsStr + dyBidhistoryService.bidhistoryList(bidClient).get(0).toString());
			}
			/*清除域名缓存*/
			ShowDomainCacheUtil.clearCache();

			NewsUpdateFlagUtil.setUpdateTimestamp();
		}catch(Exception e){
			addMessage(redirectAttributes, "出价失败【"+e.getMessage()+"】");
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyBidhistory?domainId="+ dyBidhistory.getDomainnameId() +"&repage";
	}
	/**
	 * 修改代理竞价
	 */
	@RequestMapping(value = "updateProxy")
	@ResponseBody
	public String updateProxy(DyBidhistory dyBidhistory, HttpServletRequest request){
		/*判断加价是否符合规则*/
		if (!DySysUtils.conformToBidRule(dyBidhistory.getProxyAmount())) {
			String[] rule = DySysUtils.getBidLevel(dyBidhistory.getProxyAmount());
			return ("保存失败，不符合出价规则（" + rule[0] + "加价幅度为" + rule[1] + "）");
		}
		try{
			/*修改代理竞价*/
			dyBidhistory.setBidTime(new Date());
			dyBidhistoryService.updateProxy(dyBidhistory);
			ShowDomainCacheUtil.clearCache();
			/*保存日志*/
			String paramsStr = "";
			paramsStr += "修改最高价会员的代理竞价信息如下：";
			paramsStr += " 	新代理竞价金额：" + dyBidhistory.getProxyAmount();
			paramsStr += "  出价时间：" + dyBidhistory.getBidTime();
			paramsStr += "  经系统验证后，实际的出价信息如下：  ";
			BidClient bidClient = new BidClient();
			bidClient.setDomainId(dyBidhistory.getDomainnameId());
			bidClient.setClientId(dyBidhistory.getClientId());
			/*清除域名缓存*/
			ShowDomainCacheUtil.clearCache();
			/*保存日志*/
			LogUtils.saveSpecialLog(request, paramsStr + dyBidhistoryService.bidhistoryList(bidClient).get(0).toString());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return (" 修改代理竞价失败【"+e.getMessage()+"】");
		}
		return "success";
	}
	
	@RequestMapping(value = "delete")
	public String delete(BidClient bidClient, RedirectAttributes redirectAttributes) {
		DyBidhistory dyBidhistory= new DyBidhistory(bidClient.getBidhistoryId());
		dyBidhistoryService.delete(dyBidhistory);
		addMessage(redirectAttributes, "删除出价成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyBidhistory/?domainId=" + bidClient.getDomainId() + "&repage";
	}

}