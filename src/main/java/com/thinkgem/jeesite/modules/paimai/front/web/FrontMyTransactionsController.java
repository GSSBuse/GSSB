package com.thinkgem.jeesite.modules.paimai.front.web;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.paimai.bean.TransactionsEntity;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

@Controller
@RequestMapping(value= {"myTransactions"})
public class FrontMyTransactionsController implements Constant{
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DyDomainnameService domainnameService;
	
	@Autowired
	private DyFinanceService financeService;
	
	@RequestMapping(value= {""})
	public String financeInfo(Model model) {
		DyClient currentDyClient = (DyClient)UserUtils.getSession().getAttribute("current_dy_client");
		
		return "modules/paimai/front/myTransactions";
	}
	
	@RequestMapping(value= {"myTransactionsDoing"},method={RequestMethod.GET})
	@ResponseBody
	public AjaxResult myTransactionsDoing(Model model,String pageIndex,String isBuyFlag) {
		DyClient currentDyClient = DySysUtils.getCurrentDyClient();
		if (currentDyClient != null) {
			TransactionsEntity transactionsEntity = new TransactionsEntity();
			transactionsEntity.setCurrentClientId(currentDyClient.getId());
			transactionsEntity.setSearchStatusOne(DOMAIN_STATUS_03);//只需查询03状态，所以两个检状态都设为03
			transactionsEntity.setSearchStatusTwo(DOMAIN_STATUS_03);
			try {
				
				Page<TransactionsEntity> page = new Page<TransactionsEntity>();
				page.setPageSize(PAGE_SIZE_12);
				page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
				page = domainnameService.myTransactionsDoingPage(page,transactionsEntity,isBuyFlag);
				
				AjaxResult ar = null;
				if (page.getList().isEmpty()) {
					ar = AjaxResult.makeWarn("当前没有交易信息");
				} else {
					ar = AjaxResult.makeSuccess("");
				}
				ar.getData().put("myTransactionsDoingInfo", page.getList());
				ar.getData().put("myTransactionsDoingPage", page);
				ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时
				return ar;
				
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
	
	@RequestMapping(value= {"myTransactionsWaitDeal"},method={RequestMethod.GET})
	@ResponseBody
	public AjaxResult myTransactionsWaitDeal(Model model,String pageIndex,String isBuyFlag) {
		DyClient currentDyClient = DySysUtils.getCurrentDyClient();
		if (currentDyClient != null) {
			TransactionsEntity transactionsEntity = new TransactionsEntity();
			transactionsEntity.setCurrentClientId(currentDyClient.getId());
			transactionsEntity.setSearchStatusOne(DOMAIN_STATUS_1_);//只需查询1_状态，所以两个检状态都设为1_
			transactionsEntity.setSearchStatusTwo(DOMAIN_STATUS_1_);
			try {
				
				Page<TransactionsEntity> page = new Page<TransactionsEntity>();
				page.setPageSize(PAGE_SIZE_12);
				page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
				page = domainnameService.myTransactionsWaitDealPage(page,transactionsEntity,isBuyFlag);
				
				AjaxResult ar = null;
				if (page.getList().isEmpty()) {
					ar = AjaxResult.makeWarn("当前没有交易信息");
				} else {
					ar = AjaxResult.makeSuccess("");
				}
				ar.getData().put("myTransactionsWaitDealInfo", page.getList());
				ar.getData().put("myTransactionsWaitDealPage", page);
				ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时
				return ar;
				
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
	
	@RequestMapping(value= {"myTransactionsDone"},method={RequestMethod.GET})
	@ResponseBody
	public AjaxResult myTransactionsDone(Model model,String pageIndex,String isBuyFlag) {
		DyClient currentDyClient = DySysUtils.getCurrentDyClient();
		if (currentDyClient != null) {
			TransactionsEntity transactionsEntity = new TransactionsEntity();
			transactionsEntity.setCurrentClientId(currentDyClient.getId());
			transactionsEntity.setSearchStatusOne(DOMAIN_STATUS_15);//检索已成交完成的交易
			transactionsEntity.setSearchStatusTwo(DOMAIN_STATUS_2_);//检索异常状态的交易
			try {
				
				Page<TransactionsEntity> page = new Page<TransactionsEntity>();
				page.setPageSize(PAGE_SIZE_12);
				page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
				page = domainnameService.myTransactionsDoingPage(page,transactionsEntity,isBuyFlag);
				
				AjaxResult ar = null;
				if (page.getList().isEmpty()) {
					ar = AjaxResult.makeWarn("当前没有交易信息");
				} else {
					ar = AjaxResult.makeSuccess("");
				}
				ar.getData().put("myTransactionsDoneInfo", page.getList());
				ar.getData().put("myTransactionsDonePage", page);
				ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时
				return ar;
				
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
	
	/**
	 * 个人中心：我的交易-付款
	 * @param payMoney 付款金额
	 * @param domainnameId域名ID
	 * @return
	 */
	@RequestMapping(value = {"payment"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult payment(Model model,String payMoney,String domainnameId,String payPassword) throws Exception{
		// 获取登录用户信息
		DyClient currClient = DySysUtils.getCurrentDyClient();
		if (currClient != null) {
			// 扣款并记录在资金流表中
			try {
				//MD5加密原始密码
				String payPasswordMD5 = DigestUtils.md5Hex(payPassword.toString());
				if(!payPasswordMD5.equals(currClient.getPayPassword())){
					AjaxResult ar = AjaxResult.makeWarn("安全密码错误");
					return ar;
				}else{
					// 买家付款
					Date waitTime = financeService.pay(currClient, payMoney,domainnameId);// 此方法涉及同步问题，待检!!!!!!!!!!!!!
					AjaxResult ar = AjaxResult.makeSuccess("付款成功");
					ar.getData().put("waitTime", waitTime);
					return ar;
				}
			} catch (Exception e) {
				logger.error("个人中心：我的交易-付款出错", e);
				throw e;
				//return AjaxResult.makeError("付款失败，重新操作或联系经纪人" + e.getMessage());
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：我的交易-收到域名
	 * @param domainnameId域名ID
	 * @return
	 */
	@RequestMapping(value = {"receiveDomamainname"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult receiveDomamainname(Model model,String domainnameId,String bidAmount) {
		// 获取登录用户信息
		DyClient currClient = DySysUtils.getCurrentDyClient();
		if (currClient != null) {
			try {
				//设置域名状态，并向卖家发送消息，提示买家已收到域名
				Date waitTime = domainnameService.receiveDomamainname(domainnameId, bidAmount);
				AjaxResult ar = AjaxResult.makeSuccess("买卖成功,等待系统确认");
				ar.getData().put("waitTime", waitTime);
				return ar;
			} catch (Exception e) {
				logger.error("个人中心：我的交易-确认收到域名出错", e);
				return AjaxResult.makeError("确认收到域名失败，请重新操作或联系经纪人");
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	
	/**
	 * 个人中心：我的交易-转移域名
	 * @param domainnameId域名ID
	 * @param bidAmount成交价
	 * @return
	 */
	@RequestMapping(value = {"transferDomainname"},method={RequestMethod.POST})
	@ResponseBody /* 必须设置这个注解，不然无法返回正确数据 */
	public AjaxResult transferDomainname(Model model,String domainnameId,String bidAmount) {
		// 获取登录用户信息
		DyClient currClient = DySysUtils.getCurrentDyClient();
		if (currClient != null) {
			try {
				Date waitTime = domainnameService.transferDomainname(currClient, domainnameId);
				AjaxResult ar = AjaxResult.makeSuccess("转移域名成功");
				ar.getData().put("waitTime", waitTime);
				return ar;
			} catch (Exception e) {
				logger.error("个人中心：我的交易-转移域名出错", e);
				return AjaxResult.makeError("转移域名失败，请重新操作或联系经纪人");
			}
		} else {
			return AjaxResult.makeError("非法访问！");
		}
	}
	

}
