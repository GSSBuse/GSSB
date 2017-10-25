/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyFinanceDao;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 会员财务信息管理Service
 * @author shenzb.fnst
 * @version 2015-10-12
 */

@Service
@Transactional(readOnly = true)
public class DyFinanceService extends CrudService<DyFinanceDao, DyFinance> implements Constant{
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DyCashFlowService cashFlowService;

	@Autowired
	private DyDomainnameService domainnameService;
	
	@Autowired
	private DyClientService clientService;
	
	@Autowired
	private DyBidhistoryService bidhistoryService;
	
	public DyFinance get(String id) {
		return super.get(id);
	}
	
	/**
	 * 个人中心：获取财务管理信息
	 *@return AjaxResult中data中存储数据
	 */
	public AjaxResult Financeinfo() {
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		String clientId = u.getId();

		// 获取登录用户的财务信息
		DyFinance financeinfo = null;// 记录财务信息
		Long available_balance = Long.parseLong("0");// 记录可用余额
		financeinfo = this.getFinanceInfo(clientId);
		if (financeinfo != null) {
			available_balance = financeinfo.getAccountBalance()
					- financeinfo.getFreezeBalance();
		} else {
			financeinfo = new DyFinance();
			financeinfo.setAccountBalance(Long.parseLong("0"));
			financeinfo.setClientId(u.getId());
			financeinfo.setFreezeBalance(Long.parseLong("0"));
		}
		AjaxResult ar = AjaxResult.makeSuccess("");
		// 财务信息
		ar.getData().put("financeinfo", financeinfo);
		ar.getData().put("available_balance", available_balance);
		return ar;
	}
	/**
	 * 根据会员id获取财务记录
	 */
	public DyFinance getByClientId(String clientId){
		return dao.getByClientId(clientId);
	}
	
	public List<DyFinance> findList(DyFinance dyFinance) {
		return super.findList(dyFinance);
	}
	
	public Page<DyFinance> findPage(Page<DyFinance> page, DyFinance dyFinance) {
		return super.findPage(page, dyFinance);
	}
	
	@Transactional(readOnly = false)
	public void save(DyFinance dyFinance) {
		super.save(dyFinance);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyFinance dyFinance) {
		super.delete(dyFinance);
	}
	/**
	 * 保存账户信息,如果保存失败则回滚
	 * @param clientId
	 * @param operateAmount
	 */
	@Transactional(readOnly = false)
	public boolean updateFinanceRooback(DyFinance dyFinance) throws ServiceException{
		if(dao.updateFinance(dyFinance) <= 0){
			throw new ServiceException("保存个人账户失败");
		}
		return true;
	}
	/**
	 * 保存账户信息
	 * @param clientId
	 * @param operateAmount
	 */
	@Transactional(readOnly = false)
	public boolean updateFinance(DyFinance dyFinance){
		if(dao.updateFinance(dyFinance) <= 0){
			return false;
		}
		return true;
	}
	/**
	 * 冻结金额：保证金、付款、提现都需先冻结所需金额
	 * @param id 会员财务记录ID
	 * @param freezeBalance 冻结的金额数
	 * @param updateDate用于检测同步问题
	 */
	@Transactional(readOnly = false)
	public int updateFreezeBalance(String id, Long freezeBalance, Date updateDate) {
		return dao.updateFreezeBalance(id, freezeBalance,updateDate);
	}
	
	/**
	 * @author wufl.fnst
	 *@param clientId会员ID
	 */
	@Transactional(readOnly = true)
	public DyFinance getFinanceInfo(String clientId){
		DyFinance finance= new DyFinance();
		finance.setClientId(clientId);
		List<DyFinance> fList = dao.findList(finance);
		return fList.isEmpty() ? null : fList.get(0);
	}
	
	/**
	 * 冻结金额：提现
	 * @param currentClient 会员实体
	 * @param freezeBalance 冻结的金额数
	 * @param updateDate用于检测同步问题
	 * @param remarks 备注微信提现还是先下提现
	 */
	@Transactional(readOnly = false)
	public void updateFreezeBalanceForRecharge(DyClient currentClient, Long freezeBalance,String operate,String remarks) throws Exception{
		//更新财务表
		DyFinance finance = new DyFinance();
		finance.setClientId(currentClient.getId());
		finance = dao.findList(finance).get(0);
		if((finance.getAccountBalance() - finance.getFreezeBalance()) < freezeBalance){
			throw new Exception("可用余额不足");
		}
		int n = updateFreezeBalance(finance.getId(), freezeBalance, finance.getUpdateDate());
		if(n == 0){
			throw new ServiceException();
		}
		//插入资金流表
		//构建资金流表记录，插入到表中
		DyCashFlow cashFlow = new DyCashFlow();
		cashFlow.setClientId(currentClient.getId());
		cashFlow.setOperate(operate);
		cashFlow.setOperateAmount(freezeBalance);
		cashFlow.setOperateTime(new Date());
		cashFlow.setConfirmResult(CASHFLOW_CONFIRM_WAIT);
		cashFlow.setRemarks(remarks);		
		//设置id,create_by,create_date,update_by,update_date,del_flag,remarks
		cashFlow.preInsert(UserUtils.get(currentClient.getBrokerId()));
		//设置该实体为新记录
		cashFlow.setIsNewRecord(true);
		cashFlowService.save(cashFlow);
		logger.debug("[cashLog]会员id:"+currentClient.getId()+"会员资金流id:"+cashFlow.getId()+":提现冻结："+freezeBalance+"冻结总额："+finance.getFreezeBalance());
	}
	
	/**
	 * 个人中心：我的交易--付款
	 * @param currClient 当前会员实体
	 * @param payMoney 付款金额
	 * @param domainnameId 被付款域名id
	 */
	//此方法涉及同步问题，待检
	@Transactional(readOnly = false)
	public Date pay(DyClient currClient, String payMoney,String domainnameId) {
		// 获取域名实体，记录相关信息
		DyDomainname domainnameEntity = new DyDomainname();
		domainnameEntity = domainnameService.get(domainnameId);
		//11状态才可以付款
		if(domainnameEntity == null || !domainnameEntity.getStatus().equals(DOMAIN_STATUS_11)){
			throw new ServiceException("");
		}
		domainnameEntity.setStatus(DOMAIN_STATUS_12);
		// 设置操作限制时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, DySysUtils.OPERATE_LIMIT_TIME_TRANSFER);
		domainnameEntity.setWaitTime(cal.getTime());
		// 保存域名更改
		domainnameService.save(domainnameEntity);
		
		DyBidhistory successBidder= bidhistoryService.getSuccessfulBidder(domainnameId);//获取自己的中标信息
		Long deposit = null;//冻结的保证金
		DyCashFlow dyCashFlow = new DyCashFlow();
		dyCashFlow.setClientId(currClient.getId());
		dyCashFlow.setDomainnameId(domainnameId);
		dyCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		List <DyCashFlow> list = cashFlowService.findList(dyCashFlow);
		if(!list.isEmpty()){
			//资金流
			dyCashFlow = list.get(0);
		}else{
			throw new ServiceException("无保证金冻结记录");
		}
		if(!successBidder.getClientId().equals(currClient.getId()) || successBidder.getBidAmount() != Long.parseLong(payMoney)){
			throw new ServiceException("不是得标者/中标价不对");
		}else{
			/**改成从资金流记录获取
			deposit = DySysUtils.calculateDepositAndIncrement(successBidder.getBidAmount(), BID_RULE_TYPE_DEPOSIT);
			**/
			deposit = dyCashFlow.getOperateAmount();
		}
		
		Long freezeBalance = successBidder.getBidAmount().longValue() > deposit.longValue() ? successBidder.getBidAmount() : deposit;//冻结金额，较大者

		// 获取付款用户财务实体
		DyFinance financeEntity = new DyFinance();
		financeEntity.setClientId(currClient.getId());
		financeEntity = dao.findList(financeEntity).get(0);
		if(financeEntity == null || (financeEntity.getAccountBalance()-financeEntity.getFreezeBalance())<(freezeBalance-deposit)){
			throw new ServiceException("可用余额不足/账户不存在");
		}
		
		//更新用户的冻结金额：财务表和资金流表
		//获取出价冻结的保证金记录，并将该记录的冻结金额改为成交价和保证金的较大者
		if(deposit.longValue() != freezeBalance.longValue()){
			//更新资金流
			dyCashFlow.setOperateAmount(freezeBalance);
			dyCashFlow.setIsNewRecord(false);
			cashFlowService.save(dyCashFlow);
			
			//财务更新
			int n = updateFreezeBalance(financeEntity.getId(), freezeBalance-deposit,financeEntity.getUpdateDate());
			if (n == 0) {
				throw new ServiceException("用户id:" + currClient.getId() + ",付款异常");
			}
		}
		
		// 用微信客服接口发送消息给卖家提示买家已经付款
		try{
			Message message = new Message();
			// 获取卖家openId
			String openId = clientService.get(domainnameService.get(domainnameId).getClientId()).getOpenid();
			String title = DySysUtils.TEMPLATE_TITLE_0009;
			String content = DySysUtils.TEMPLATE_MESSAGE_0009;
			content = content.replace("{{domainname.DATA}}", domainnameEntity.getName());
			content = content.replace("{{buyer.DATA}}", currClient.getNickname());
			content = content.replace("{{price.DATA}}", payMoney);
			content = content.replace("{{waittime.DATA}}", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
			message.SendNews(WeChat.getAccessToken(), openId, title, content, domainnameEntity.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return cal.getTime();
	}

}