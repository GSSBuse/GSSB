/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.dy;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.entity.dy.CashFlowInfo.CashFlowInfo;
import com.thinkgem.jeesite.modules.sys.service.dy.DyCashFlowService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 资金流信息Controller
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyCashFlow")
public class DyCashFlowController extends BaseController {

	@Autowired
	private DyCashFlowService dyCashFlowService;
	@Autowired
	private DyClientService dyClientService;
	@Autowired
	private DyFinanceService dyFinanceService;
	
	@ModelAttribute
	public DyCashFlow get(@RequestParam(required=false) String id) {
		DyCashFlow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyCashFlowService.get(id);
		}
		if (entity == null){
			entity = new DyCashFlow();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyCashFlow:view")
	@RequestMapping(value = {"list", ""})
	public String list( CashFlowInfo cashFlowInfo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {	
		String type = request.getParameter("type");
		if(StringUtils.equals("2", type)){
			cashFlowInfo.setConfirmResult(Constant.CASHFLOW_CONFIRM_WAIT);
		}
		try{
			Page<DyCashFlow> page = dyCashFlowService.findPageforQuery(new Page<DyCashFlow>(request, response),cashFlowInfo);		
			model.addAttribute("page", page);
		}catch(Exception e){
			addMessage(model, "查询资金流失败，系统出错，请联系系统管理员");
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "modules/sys/dy/dyCashFlowList";
	}
	@RequiresPermissions("sys:dy:dyCashFlow:view")
	@RequestMapping(value = "form")
	public String form(DyCashFlow dyCashFlow, HttpServletRequest request,Model model) {
		dyCashFlow.setDyClient(dyClientService.get(dyCashFlow.getClientId()));
		model.addAttribute("dyCashFlow", dyCashFlow);
		return "modules/sys/dy/dyCashFlowForm";
	}
	/**
	 * 返回资金流的所有详细信息(会员信息，经纪人充值记录)
	 */
	@RequestMapping(value = "dyCashFlowApply")
	public String dyCashFlowApply(DyCashFlow dyCashFlow,HttpServletRequest request, Model model){
		DyClient dyClient = dyClientService.getAllInfo(request.getParameter("clientId"));
		model.addAttribute("dyClient",dyClient);
		List<CashFlowInfo> list = dyCashFlowService.findLastCashFlow(UserUtils.getUser().getId());
		model.addAttribute("list", list);
		return "modules/sys/dy/dyCashFlowApply";
	}
	/**
	 *获取某个会员的资金流水
	 */
	@RequestMapping(value = "dyCashFlowClient")
	public String dyCashFlowClient(DyCashFlow dyCashFlow,HttpServletRequest request, HttpServletResponse response, Model model){
		DyClient dyClient = dyClientService.get(dyCashFlow.getClientId());
		model.addAttribute("dyClient",dyClient);
		Page<DyCashFlow> page = dyCashFlowService.findPageByClientId(new Page<DyCashFlow>(request,response), dyCashFlow);
		model.addAttribute("page", page);
		return "modules/sys/dy/dyCashFlowClient";
	}
	/**
	 *获取某个会员的冻结解冻记录
	 */
	@RequestMapping(value = "freeCashFlowClient")
	public String freeCashFlowClient(DyCashFlow dyCashFlow,HttpServletRequest request, HttpServletResponse response, Model model){
		/*获取会员信息*/
		DyClient dyClient = dyClientService.get(dyCashFlow.getClientId());
		/*获取账户信息*/
		DyFinance tempFinance = new DyFinance();
		tempFinance.setClientId(dyClient.getId());
		DyFinance dyFinance =  dyFinanceService.get(tempFinance);
		model.addAttribute("dyClient",dyClient);
		model.addAttribute("dyFinance",dyFinance);
		Page<DyCashFlow> page = dyCashFlowService.findFreePageByClientId(new Page<DyCashFlow>(request,response), dyCashFlow);
		model.addAttribute("page", page);
		return "modules/sys/dy/freeCashFlowClient";
	}
	/**
	 * 申请资金流
	 * @throws Exception 
	 */
	@RequestMapping(value = "applySave")
	public String applySave(DyCashFlow dyCashFlow, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
		dyCashFlow.setConfirmResult(Constant.CASHFLOW_CONFIRM_WAIT);
		dyCashFlow.setOperateTime(new Date());
		if (!beanValidator(model, dyCashFlow)){
			return form(dyCashFlow, request ,model);
		}
		try{
			dyCashFlowService.applySave(dyCashFlow);
			/*保存日志*/
			LogUtils.saveSpecialLog(request,null);
			addMessage(redirectAttributes, "资金申请成功");
		}catch(Exception e){
			e.printStackTrace();
			addMessage(redirectAttributes, "资金流申请失败【"+e.getMessage()+"】");
			logger.error(e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyClient?repage";
	}
	/**
	 * 提现处理中
	 * @throws Exception 
	 */
	@ResponseBody
	@RequiresPermissions("sys:dy:dyCashFlow:edit")
	@RequestMapping(value = "drawProcessing")
	public String drawProcessing(DyCashFlow dyCashFlow, HttpServletRequest request){
		String pass = request.getParameter("pass");
		/*判断财务信息是否已经过时*/
		DyCashFlow newCashFlow = dyCashFlowService.get(dyCashFlow.getId());
		if(!StringUtils.equals(newCashFlow.getConfirmResult(),Constant.CASHFLOW_CONFIRM_WAIT)){
			return "财务信息过时，请刷新页面";
		}
		try {
			dyCashFlowService.drawProcessing(dyCashFlow,pass);
			/*保存日志*/
			LogUtils.saveSpecialLog(request,dyCashFlowService.getCashFlowInfo(dyCashFlow.getId()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
		return "success";
	}
	
	/**
	 * 资金流确认
	 * @throws Exception 
	 */
	@RequiresPermissions("sys:dy:dyCashFlow:edit")
	@ResponseBody
	@RequestMapping(value = "confirmDo")
	public String confirmDo(DyCashFlow dyCashFlow, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws Exception{
		String pass = request.getParameter("pass");
		/*判断财务信息是否已经过时*/
		DyCashFlow newCashFlow = dyCashFlowService.get(dyCashFlow.getId());
		if(!StringUtils.equals(newCashFlow.getConfirmResult(),Constant.CASHFLOW_CONFIRM_WAIT) &&
		   !StringUtils.equals(newCashFlow.getConfirmResult(),Constant.CASHFLOW_COMFIRM_DOING)){
			return "财务信息过时，请刷新页面";
		}
		try{
			dyCashFlowService.confirmDo(dyCashFlow, pass);
			/*保存日志*/
			LogUtils.saveSpecialLog(request,dyCashFlowService.getCashFlowInfo(dyCashFlow.getId()).toString());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
		return "success";
	}
	
	@RequiresPermissions("sys:dy:dyCashFlow:edit")
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, DyCashFlow dyCashFlow, RedirectAttributes redirectAttributes) {
		String parmasStr = dyCashFlowService.getCashFlowInfo(dyCashFlow.getId()).toString();
		try{
			dyCashFlowService.delete(dyCashFlow);
			addMessage(redirectAttributes, "删除资金流信息成功");
			/*保存日志*/
			LogUtils.saveSpecialLog(request,parmasStr);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "删除失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyCashFlow/?repage";
	}
	/**
	 * 查询充值提现待确认的消息的数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "cUnconfirmCount")
	public String cUnconfirmCount(){
		return String.valueOf(dyCashFlowService.cUnconfirmCount());
	}
}