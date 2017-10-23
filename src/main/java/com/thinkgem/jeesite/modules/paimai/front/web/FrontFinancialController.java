package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyDomainnameDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.service.dy.AuctionScheduleService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyAttentionService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBidhistoryService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBonusService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyCashFlowService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyNewsService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyWxpayResultService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;


@Controller
@RequestMapping(value= {"financialManagement"})
public class FrontFinancialController implements Constant{
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private DyClientService clientService;
	
	@Autowired
	private DyFinanceService financeService;
	
	@Autowired
	private DyCashFlowService cashFlowService;
	
	@Autowired
	private DyDomainnameService domainnameService;

	@RequestMapping(value= {"financeInfo"})
	public String financeInfo(Model model) {
		DyClient currentDyClient = (DyClient)UserUtils.getSession().getAttribute("current_dy_client");
		//财务表的的clientId确定是对应会员表的id?
		String id = currentDyClient.getId();
		DyFinance dyFinance = financeService.getByClientId(id);
		model.addAttribute("dyFinance",dyFinance);
		return "modules/paimai/front/financialManagement";
	}
	
	@RequestMapping(value = {"refreshFinanceinfo"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult refreshFinanceinfo(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if(u !=null){
			//财务信息
			AjaxResult ar = financeService.Financeinfo();
			//红包开启标志
			ar.getData().put("shareBonusEnable", DySysUtils.SHARE_BONUS_ENABLE);
			return ar;
		}else{
			//ajax返回的数据
			AjaxResult ar = AjaxResult.makeError("非法访问！");
			return ar;
		}
		
	}
	
	@RequestMapping(value = {"cashFlowInfo"},method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult myCashFlow(Model model,String pageIndex) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			// 获取登录用户的资金流信息：冻结、解冻的记录不需要
			DyCashFlow dyCashFlow = new DyCashFlow();
			dyCashFlow.setClientId(u.getId());
			dyCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
			try {
				
				Page<DyCashFlow> page = new Page<DyCashFlow>();
				page.setPageSize(PAGE_SIZE_12);
				page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
				page.setOrderBy("a.operate_time desc");
				page = cashFlowService.findCashFlowPage(page, dyCashFlow);
				if (page.getList().isEmpty()) {
					AjaxResult ar = AjaxResult.makeWarn("当前没有资金流信息");
					ar.getData().put("cashflowinfo", page.getList());
					ar.getData().put("cashFlowPage", page);
					return ar;
				} else {
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("cashflowinfo", page.getList());
					ar.getData().put("cashFlowPage", page);
					return ar;
				}
			} catch (Exception e) {
				logger.error("获取资金流出错", e);
				return AjaxResult.makeError("获取资金流出错:" + e.getMessage());
			}
		} else {
			// ajax返回的数据
			AjaxResult ar = AjaxResult.makeError("非法访问！");
			return ar;
		}
	}
	
	@RequestMapping(value = {"withdrawalsInfo"},method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult withdrawalsInfo(Model model,String pageIndex){
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			//获取用户处于等待和处理中的提现信息
			DyCashFlow dyCashFlow = new DyCashFlow();
			dyCashFlow.setClientId(u.getId());
			dyCashFlow.setOperate(CASHFLOW_OPERATE_WITHDRAW);
			
			Page<DyCashFlow> page = new Page<DyCashFlow>();
			page.setPageSize(PAGE_SIZE_12);
			page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
			page.setOrderBy("a.operate_time desc");
			page = cashFlowService.findWithdrawalsPage(page,dyCashFlow);
			if (page.getList().isEmpty()) {
				AjaxResult ar = AjaxResult.makeWarn("当前没有资金流信息");
				ar.getData().put("cashflowinfo", page.getList());
				ar.getData().put("cashFlowPage", page);
				return ar;
			} else {
				AjaxResult ar = AjaxResult.makeSuccess("");
				ar.getData().put("withdrawalsinfo", page.getList());
				ar.getData().put("withdrawalsPage", page);
				return ar;
			}
		}else{
			return AjaxResult.makeError("非法访问！");
			
		}
	}
	
	@RequestMapping(value = {"freezeinfo"},method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult freezeInfo(Model model,String pageIndex) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			// 获取登录用户的冻结资金流信息：冻结和提现记录
			DyCashFlow dyCashFlow = new DyCashFlow();
			dyCashFlow.setClientId(u.getId());
			try {
				Page<DyCashFlow> page = new Page<DyCashFlow>();
				page.setPageSize(PAGE_SIZE_12);
				page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
				page.setOrderBy("a.create_date desc");
				page = cashFlowService.findFreezeInfoPage(page, dyCashFlow);
				
				if (page.getList().isEmpty()) {
					AjaxResult ar = AjaxResult.makeWarn("当前没有冻结记录信息");
					ar.getData().put("freezeinfo", page.getList());
					ar.getData().put("freezePage", page);
					return ar;
				} else {
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("freezeinfo", page.getList());
					ar.getData().put("freezePage", page);
					return ar;
				}
			} catch (Exception e) {
				logger.error("获取冻结记录出错", e);
				return AjaxResult.makeError("获取冻结记录出错:" + e.getMessage());
			}
		} else {
			// ajax返回的数据
			AjaxResult ar = AjaxResult.makeError("非法访问！");
			return ar;
		}
	}
	
	/**
	 * 个人中心：财务管理充值
	 * @param operate 操作类型
	 * @param operateAmount操作金额
	 * @return
	 */
	@RequestMapping(value = {"rechargeOrWithdrawals"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult rechargeOrWithdrawals(Model model,String operate,String operateAmount , String payPassword)throws Exception {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			//验证用户是否完善了个人信息的填写
			if(!clientService.checkPersonalInfo(u)){
				return AjaxResult.makeError("请先填写完整的个人信息");
			}
			Long money = Long.parseLong(operateAmount);
			// 如果是提现操作，需要将提现金额冻结
			if (operate.equals("提现")) {
				try {
					//MD5加密原始密码
					String payPasswordMD5 = DigestUtils.md5Hex(payPassword.toString());
					if(payPasswordMD5.equals(u.getPayPassword())){
						financeService.updateFreezeBalanceForRecharge(u, money,CASHFLOW_OPERATE_WITHDRAW);
					}else{
						AjaxResult ar = AjaxResult.makeWarn("安全密码错误");
						return ar;
					}
				} catch (Exception e) {
					throw e;
				}
			} else {// 充值（线下操作）
				try {
					// 构建资金流表记录，插入到表中
					DyCashFlow cashFlow = new DyCashFlow();
					cashFlow.setClientId(u.getId());
					cashFlow.setOperate(CASHFLOW_OPERATE_RECHARGE_OUTLINE);
					cashFlow.setOperateAmount(money);
					cashFlow.setOperateTime(new Date());
					cashFlow.setConfirmResult(CASHFLOW_CONFIRM_WAIT);
					// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
					cashFlow.preInsert(UserUtils.get(u.getBrokerId()));
					// 设置该实体为新记录
					cashFlow.setIsNewRecord(true);
					cashFlowService.save(cashFlow);
				} catch (Exception e) {
					logger.error(operate + "出错", e);
					return AjaxResult.makeError(operate + "出错，请联系经纪人");
				}
			}
			AjaxResult ar = AjaxResult.makeSuccess(operate + "申请成功,等待确认");
			return ar;
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：页面跳回icenter,获取我的个人信息
	 */
	@RequestMapping(value = {"userInfo"}, method={RequestMethod.GET})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult userInfo(Model model) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("userinfo", u);
			return ar;
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 取消提现
	 * @param model
	 * @param id 提现的资金流id
	 * @return
	 */
	@RequestMapping(value = {"cancelWithdrawal"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult cancelWithdrawal(Model model,String id,String money)throws Exception{
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			//根据用户id和提现的资金流id，取消用户的提现申请
			cashFlowService.cancel(u.getId(),id,money);
			AjaxResult ar = AjaxResult.makeSuccess("取消提现成功");
			return ar;
		}else{
			return AjaxResult.makeError("非法访问！");
			
		}
	}
	
	/**
	 * 个人中心：根据会员ID更新会员默认支付方式
	 * @param defaultIncomeExpense 会员默认支付方式
	 * @return
	 */
	@RequestMapping(value = {"changeDefaultIncomeExpense"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult changeDefaultIncomeExpense(Model model,String defaultIncomeExpense,String payPassword) {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		if (u != null) {
			try {
				String payPasswordMD5 = DigestUtils.md5Hex(payPassword.toString());
				if(payPasswordMD5.equals(u.getPayPassword())){
					int flag = clientService.changeDefault_income_expense(
							u.getId(), defaultIncomeExpense);
					if (flag == 1) {
						AjaxResult ar = AjaxResult.makeSuccess("银行信息更新成功");
						u.setDefaultIncomeExpense(defaultIncomeExpense);
						ar.getData().put("newDefaultIncomeExpense",
								defaultIncomeExpense);
						return ar;
					} else {
						return AjaxResult.makeWarn("银行信息更新失败");
					}
				}else{
					AjaxResult ar = AjaxResult.makeWarn("安全密码错误");
					return ar;
				}
			} catch (Exception e) {
				logger.error("会员银行信息更新出错", e);
				return AjaxResult.makeError("会员银行信息更新出错:" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
}
