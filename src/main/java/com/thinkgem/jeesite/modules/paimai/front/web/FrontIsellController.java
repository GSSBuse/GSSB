package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBidhistoryService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.sys.utils.ShowDomainCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;
import com.thinkgem.jeesite.modules.wx.web.domainname.IsellController;

@Controller
@RequestMapping(value= {"isell"})
public class FrontIsellController extends BaseController {
	
	@Autowired
	IsellController isellController;
	
	@Autowired
	DyBidhistoryService bidhistoryService;
	
	@Autowired
	DyDomainnameService domainnameService;
	
	@RequestMapping(value= {""})
	public String index(Model model, String domain) {
		AjaxResult ar = this.domainList(model, "1", 12);
		model.addAttribute("initData", JsonMapper.toJsonString(ar.getData()));
		model.addAttribute("sellerDeposit", DySysUtils.SELL_DEPOSIT);
		return "modules/paimai/front/isell";
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
	public AjaxResult domainList(Model model, String pageIndex, Integer pageSizeP) {
		AjaxResult ar = isellController.domainList(model);
		
		List<DyDomainname> list = (List<DyDomainname>)ar.getData().get("domainList");
		if (list != null) {
			for (DyDomainname dm : list) {
				DyBidhistory bh = bidhistoryService.getMaxBidAmount(dm.getId());
				if (bh != null) {
					dm.setAppraisePrice(bh.getBidAmount());
				} else {
					dm.setAppraisePrice(0L);
				}
	        }
		}
		return ar;
	}
	
	@ModelAttribute
	public DyDomainname get(@RequestParam(required=false) String id) {
		DyDomainname entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = domainnameService.get(id);
		}
		if (entity == null){
			entity = new DyDomainname();
		}
		return entity;
	}
	
	@RequestMapping(value= {"add"})
	public String add(Model model, DyDomainname dyDomainname) {
		//model.addAttribute("domainInfo", dyDomainname);
		model.addAttribute("domainInfoJson", JsonMapper.toJsonString(dyDomainname));
		return "modules/paimai/front/isellDetail";
	}
	
	@RequestMapping(value= {"edit"})
	public String edit(Model model, DyDomainname dyDomainname) {
		//model.addAttribute("domainInfo", dyDomainname);
		model.addAttribute("domainInfoJson", JsonMapper.toJsonString(dyDomainname));
		return "modules/paimai/front/isellDetail";
	}
	
	@RequestMapping(value= {"delete"}, method = {RequestMethod.POST})
	@ResponseBody
	public AjaxResult delete(Model model, String id) {
		try{
			DyClient client = DySysUtils.getCurrentDyClient();
			domainnameService.deleteDomainByClientId(client.getId(), id);
			
			return AjaxResult.makeSuccess("");
		} catch (Exception e) {
			return AjaxResult.makeError("删除域名失败【" + e.getMessage() + "】");
		}
	}
	
	@RequestMapping(value= {"save"})
	@ResponseBody
	public AjaxResult save(Model model, DyDomainname dyDomainname, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try{
			if(DySysUtils.SHARE_BONUS_ENABLE.equals("0")){
				//红包功能关闭
				dyDomainname.setBonusShareTotal(0L);
			}
			// 取得当前会员ID
			DyClient client = DySysUtils.getCurrentDyClient();
			String clientId = client.getId();
			try {
				if(dyDomainname.getId() != null){
					//充值后的，二次提交
					DyDomainname domainname = domainnameService.get(dyDomainname.getId());
					if( domainname != null && domainname.getStatus().equals(Constant.DOMAIN_STATUS_00)){
						boolean flag = domainnameService.submitDomain(dyDomainname,client);
						if(flag){
							addMessage(redirectAttributes, "提交成功");
							return AjaxResult.makeSuccess("提交成功");
						}else{
							addMessage(redirectAttributes, "提交失败，资金不足");
							return AjaxResult.makeWarn("提交失败，资金不足");
						}
					}else{
						addMessage(redirectAttributes, "提交异常，域名不存在");
						return AjaxResult.makeError("提交异常，域名不存在");
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				addMessage(redirectAttributes, "二次提交失败【"+e.getMessage()+"】");
				return AjaxResult.makeError("二次提交失败【"+e.getMessage()+"】");
			}
			
			//首次提交或者审核失败后再次编辑提交
			dyDomainname.setClientId(clientId);
			dyDomainname.setType(DySysUtils.getDomainnameType(dyDomainname.getName()));
			//dyDomainname.setStatus(Constant.DOMAIN_STATUS_01); //状态设置为审核中
			dyDomainname.setIncrement(DySysUtils.calculateDepositAndIncrement(0L, Constant.BID_RULE_TYPE_INCREMENT));
			dyDomainname.setDeposit(DySysUtils.SELL_DEPOSIT);
			
			if (dyDomainname.getBonusSecond() == null) {
				dyDomainname.setBonusSecond(0L);
			}
			
			if (dyDomainname.getIsNewRecord()) {
				dyDomainname.setIsNewRecord(false);
				dyDomainname.preInsert(UserUtils.get(client.getBrokerId()));
				dyDomainname.setIsNewRecord(true);
			} else {
				dyDomainname.preUpdate(UserUtils.get(client.getBrokerId()));
			}
			//提交域名，或者02状态时的修改也看做是一次提交动作
			domainnameService.submitDomain(dyDomainname,client);
			/*清除域名缓存*/
			ShowDomainCacheUtil.clearCache();
			/*保存日志*/
			LogUtils.saveSpecialLog(request, null);
			addMessage(redirectAttributes, "保存域名信息成功");
			
			return AjaxResult.makeSuccess("保存域名成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "编辑域名信息失败【"+e.getMessage()+"】");
			return AjaxResult.makeError("编辑域名信息失败【"+e.getMessage()+"】");
		}
	}
	
	@RequestMapping(value = {"addRedPackInfo"}, method = RequestMethod.POST)
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult addRedPackInfo(Model model,String domainId,String bonusShareTotal, String bonusShareNumber, String bonusSecond,String payPassword)throws Exception{
		return isellController.addRedPackInfo(model, domainId, bonusShareTotal, bonusShareNumber, bonusSecond,payPassword);
	}
}
