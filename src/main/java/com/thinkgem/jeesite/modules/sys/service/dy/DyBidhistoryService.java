/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Objects;
import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyAttentionDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyBidhistoryDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyCashFlowDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyClientDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyDomainnameDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyFinanceDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyNewsDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyPlatformIncomeDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAttention;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformIncome;
import com.thinkgem.jeesite.modules.sys.entity.dy.TransactionInformation;
import com.thinkgem.jeesite.modules.sys.utils.ShowBidListCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.ShowDomainCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidCashInfo;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidClient;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 会员出价信息Service
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Service
@Transactional(readOnly = true)
public class DyBidhistoryService extends CrudService<DyBidhistoryDao, DyBidhistory> implements Constant{
	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DyDomainnameDao dyDomainnameDao; // 域名信息管理DAO接口

	@Autowired
	private DyAttentionDao dyAttentionDao;   // 会员关注管理DAO接口

	@Autowired
	private DyFinanceDao dyFinanceDao;       // 会员财务信息管理DAO接口

	@Autowired
	private DyBidhistoryDao dyBidhistoryDao; // 会员出价信息DAO接口

	@Autowired
	private DyNewsDao dyNewsDao;             // 最新消息管理DAO接口

	@Autowired
	private DyClientDao dyClientDao;         // 会员信息管理DAO接口
	


	@Autowired
	private DyPlatformIncomeDao dyPlatformIncomeDao;
	
	@Autowired
	private DyPlatformAccountService dyPlatformAccountService;
	
	@Autowired
	private DyCashFlowService cashFlowService;
	
	@Autowired
	private DyAttentionService attentionService;
	
	@Autowired
	private DyCashFlowDao dyCashFlowDao;
	

	/** 出价类型 */
	private static final String BID_TYPE_NORMAL = "普通出价"; // 普通出价
	private static final String BID_TYPE_PROXY  = "代理竞价"; // 代理竞价

	public DyBidhistory get(String id) {
		return super.get(id);
	}
	
	public List<DyBidhistory> findList(DyBidhistory dyBidhistory) {
		return super.findList(dyBidhistory);
	}
	
	public Page<BidClient> findPage(Page<BidClient> page, BidClient bidClient) {
		bidClient.setPage(page);
		page.setList(dao.bidhistoryList(bidClient));
		return page;
	}
	
	/**
	 * 根据域名Id查询出价表
	 * @param domainnameId 域名Id
	 * @return
	 */
	public List<BidClient> bidhistoryList(BidClient bidClient){
		return dao.bidhistoryList(bidClient);
	}
	
	@Transactional(readOnly = false)
	public void save(DyBidhistory dyBidhistory) {
		super.save(dyBidhistory);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyBidhistory dyBidhistory) {
		super.delete(dyBidhistory);
	}

	/**
	 * 自定义页面查询
	 * @param bidClientPage 出价记录页
	 * @param bidClient 出价-会员信息
	 * @return 出价记录页
	 */
	public Page<BidClient> customizedFindPage(Page<BidClient> bidClientPage,
			BidClient bidClient) {
		bidClient.setPage(bidClientPage);
		bidClientPage.setList(dao.customizedFindList(bidClient));
		return bidClientPage;
	}

	/**
	 * 根据域名ID获取域名最新价
	 * @param domainId 域名ID
	 * @return 域名最新价
	 */
	public DyBidhistory getMaxBidAmount(String domainId) {
		return ShowBidListCacheUtil.getMaxBidAmount(domainId);
	}
	/**
	 * 判断出价金额是否合法
	 * @param dyBidhistory 出价记录实体
	 * @return
	 */
	public boolean isLegealAmount(DyBidhistory dyBidhistory){
		boolean anser = false;
		long bidAmount = dyBidhistory.getBidAmount(); //出价金额
		long increment = dyDomainnameDao.get(dyBidhistory.getDomainnameId()).getIncrement();  //加价幅度
		long maxBidAmount = ShowBidListCacheUtil.getMaxAmountById(dyBidhistory.getDomainnameId());  //最大出价记录
		if(bidAmount >= maxBidAmount && (bidAmount-maxBidAmount)%increment == 0){
			anser = true;
		}
		return anser;
	}
	
	/**
	 * 根据域名ID，出价记录ID查询出价表获取大于出价金额的出价记录
	 * @param domainId 域名ID
	 * @param bidhistoryId 出价记录ID
	 * @return
	 */
	public List<BidClient> biddingService(String domainId , String bidhistoryId){
		return dao.findListForAmounter(domainId , bidhistoryId);
	}

	/**
	 * 根据域名ID列表查询出价表，获取每个域名的最新出价
	 * @param domainIdList 域名ID列表
	 * @return
	 */
	public List<Long> auctionService(String[] domainIdList){
		List<Long> bidAmountList= new ArrayList<Long>();
		for(String domainId : domainIdList){
			Long bidAmount = ShowBidListCacheUtil.getMaxAmountById(domainId);
			bidAmountList.add(null == bidAmount ? 0L : bidAmount);
		}
		return bidAmountList;
	}

	/**
	 * 根据用户ID和域名状态标记查询出价表、域名表获取用户参与交易的域名信息:买域名
	 * @param clientId 用户id
	 * @param status 域名状态标记
	 * @return Map:BidAmount domainnameId name endTime status photo waitTime(截止时间)
	 */
	public List<Map<String, Object>> myTransactionsBuyInfo(String clientId,String status){
		return dao.myTransactionsBuyInfo(clientId,status);
		
	}

	/**
	 * 根据用户ID和域名状态标记查询出价表、域名表获取用户参与交易的域名信息:卖域名
	 * @param clientId 用户id
	 * @param status 域名状态标记
	 * @return Map:BidAmount domainnameId name endTime status photo updateDate
	 */
	public List<Map<String, Object>> myTransactionsSellInfo(String clientId,String status){
		return dao.myTransactionsSellInfo(clientId,status);
		
	}
	
	private static Map<String, Object> bidLocks = new HashMap<String, Object>();
	private static Object getLock(String domainId) {
		if (bidLocks.containsKey(domainId)) {
			return bidLocks.get(domainId);
		} else {
			Object o = new Object();
			bidLocks.put(domainId, o);
			return o;
		}
	}
	
	/**
	 * 修改最高价的代理竞价（原来的代理竞价一定比新修改的代理竞价大）
	 * @param dyBidhistory
	 */
	@Transactional(readOnly = false)
	public void updateProxy(DyBidhistory dyBidhistory){
		// 同时出价锁
		synchronized (getLock(dyBidhistory.getDomainnameId())) {
			try{
				/**检查是否有新的出价*/
				DyBidhistory oldDyBidhistory = ShowBidListCacheUtil.getMaxBidAmount(dyBidhistory.getDomainnameId());
				if(oldDyBidhistory == null || !StringUtils.equals(dyBidhistory.getId() , oldDyBidhistory.getId())){
					throw new ServiceException("当前出价已过时，请刷新页面");
				}
				/**检查新的代理竞价是否合法*/
				if(dyBidhistory.getProxyAmount() >= oldDyBidhistory.getProxyAmount() || dyBidhistory.getProxyAmount() <= dyBidhistory.getBidAmount()){
					throw new ServiceException("代理竞价必须介于【"+dyBidhistory.getBidAmount()+"，"+oldDyBidhistory.getProxyAmount()+"】之间");
				}
				/**修改最高价的代理竞价*/
				int updCnt = ShowBidListCacheUtil.updateProxyAmountById(dyBidhistory.getDomainnameId(), dyBidhistory.getId(), dyBidhistory.getProxyAmount());
				if (0 == updCnt) {
					throw new ServiceException("更新代理竞价失败");
				}
				/**获取改代理竞价的冻结资金*/
				Long newDeposit = DySysUtils.calculateDepositAndIncrement(dyBidhistory.getProxyAmount(), BID_RULE_TYPE_DEPOSIT);//计算当前保证金
				Long oldDeposit = DySysUtils.calculateDepositAndIncrement(oldDyBidhistory.getProxyAmount(), BID_RULE_TYPE_DEPOSIT);//计算原来的保证金
				long gap = 0;
				if(newDeposit.longValue() == oldDeposit.longValue()){
					return ;
				}else{
					//原来的代理竞价一定比新修改的代理竞价大(此值为负数)
					gap = newDeposit - oldDeposit;
				}
				/**修改最高出价会员的财务表的冻结资金*/
				DyFinance dyFinance = dyFinanceDao.getByClientId(dyBidhistory.getClientId());
				if(dyFinanceDao.updateFreezeBalance(dyFinance.getId(), gap, dyFinance.getUpdateDate()) == 0){
					throw new ServiceException("更新会员账户冻结资金失败");
				}
				/**修改资金流的冻结金额*/
				DyCashFlow dyCashFlow = new DyCashFlow();
				dyCashFlow.setDomainnameId(dyBidhistory.getDomainnameId());
				dyCashFlow.setClientId(dyBidhistory.getClientId());
				dyCashFlow.setOperateAmount(oldDeposit);
				if(dyCashFlowDao.updateFreeCashFlowAmount(dyCashFlow, newDeposit) == 0){
					throw new ServiceException("更新会员资金流的冻结金额失败");
				}
			} catch (ServiceException se) {
				// Service层公用的Exception抛出时，触发回滚
				ShowBidListCacheUtil.clearCache(dyBidhistory.getDomainnameId());
				throw se;			
			}
		}
	}

	/**
	 * 根据域名ID，会员出价金额进行出价操作
	 * @param domainId 域名ID
	 * @param newBidAmount 会员出价金额
	 * @param dyClient 会员实体（经纪人代替出价是设定）
	 * @return 出价结果
	 * @throws ServiceException
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public AjaxResult bid(String domainId, Long newBidAmount, DyClient dyClient) throws ServiceException, Exception {
		DyClient currClient = DySysUtils.getCurrentDyClient();         // 当前登录会员
		if (null != dyClient) {
			//经纪人代替出价是设定
			currClient = dyClient;
		}
		
		String clientId           = currClient.getId();
		DyDomainname dyDomainname = ShowDomainCacheUtil.getDomainFromCache(domainId); // 域名实体 

		if(dyDomainname == null){
			return AjaxResult.makeError("出价失败，无此域名");
		}
		
		// 是否结拍检查
		if (new Date().after(dyDomainname.getEndTime())) {
			return AjaxResult.makeError("出价失败，该域名已结拍");
		}

		// 如果该域名属于登录会员，不允许出价
		if (clientId.equals(dyDomainname.getClientId())) {
			AjaxResult ar = AjaxResult.makeWarn("出价失败，不能对自己的域名出价");
			ar.getData().put("type", "owner");
			return ar;
		}
		
		// 先判断出价是否高于当前价
		DyBidhistory currentBidhistory = ShowBidListCacheUtil.getMaxBidAmount(domainId);
		// 判断出价是否过期
		if (null != currentBidhistory && newBidAmount.longValue() <= currentBidhistory.getBidAmount().longValue()) {
			AjaxResult ar = AjaxResult.makeWarn("出价失败，出价应高于当前价");
			ar.getData().put("type", "overdue");
			return ar;
		}
		
		// 同时出价锁
		synchronized (getLock(domainId)) {
			User broker = UserUtils.get(currClient.getBrokerId()); // 会员经纪人
	
			try {
				DyFinance dyFinance = new DyFinance();//当前出价者，即当前登录用户的财务实体
				dyFinance.setClientId(clientId);
				dyFinance.setDelFlag(DyFinance.DEL_FLAG_NORMAL);
				dyFinance = dyFinanceDao.findList(dyFinance).get(0); // 会员财务实体
	
				Long availableBalance = dyFinance.getAccountBalance() - dyFinance.getFreezeBalance(); // 会员可用余额 = 会员帐户余额 - 会员帐户冻结金额
				Long deposit          = 0L; // 保证金
				Long increment        = 0L; // 加价幅度
	
				// 获取当前最高出价记录
				DyBidhistory dyBidhistory = ShowBidListCacheUtil.getMaxBidAmount(domainId);
	
				//自己已经是最高出价者
				if (null != dyBidhistory && dyBidhistory.getClientId().equals(clientId)) {
					if (dyBidhistory.getProxyAmount() != null && dyBidhistory.getProxyAmount().longValue() >= newBidAmount.longValue()) {
						return AjaxResult.makeError("出价失败，请出更高的代理竞价");
					}
					
					//检查可用余额
					Long newDeposit = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_DEPOSIT);//计算当前保证金
					Long oldDeposit = 0L;
					if (dyBidhistory.getProxyAmount() == null || dyBidhistory.getProxyAmount().longValue() == 0) {
						oldDeposit = DySysUtils.calculateDepositAndIncrement(dyBidhistory.getBidAmount(), BID_RULE_TYPE_DEPOSIT);//计算上次出价保证金
					} else {
						oldDeposit = DySysUtils.calculateDepositAndIncrement(dyBidhistory.getProxyAmount(), BID_RULE_TYPE_DEPOSIT);//计算上次出价保证金
					}
					if (availableBalance < newDeposit - oldDeposit) {
						// 可用余额无法支付保证金时，提醒会员充值
						AjaxResult ar = AjaxResult.makeWarn("出价失败，帐户可用余额不足，请充值");
						ar.getData().put("type", "");
						ar.getData().put("deposit", newDeposit - oldDeposit);
						return ar;
					}
					// 最高价出价者和本次出价者为同一人时，只需将最高价出价记录的代理竞价更新为本次出价
					int updCnt = ShowBidListCacheUtil.updateProxyAmountById(domainId, dyBidhistory.getId(), newBidAmount);
					if (0 == updCnt) {
						throw new ServiceException("更新代理竞价出错");
					} else {
						//代理竞价成功，重新冻结保证金
						if(oldDeposit.longValue() != 0){
							if(oldDeposit.longValue() != newDeposit.longValue()){
								// 保证金是按区间取的，不同的代理竞价保证金有可能相等，相等时无序操作
								// 冻结当前保证金，解冻上次出价保证金
								if (!this.depositControl(
										dyFinance.getId(),
										dyFinance.getUpdateDate(),
										newDeposit,
										dyFinance.getId(),
										dyFinance.getUpdateDate(),
										oldDeposit)) {
									throw new ServiceException("冻结保证金操作失败");
								}
								
								//将新的保证金更新到上次保证金记录
								DyCashFlow oldDepositCashFlow = new DyCashFlow();
								oldDepositCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
								oldDepositCashFlow.setClientId(clientId);
								oldDepositCashFlow.setOperateAmount(oldDeposit);
								oldDepositCashFlow.setDomainnameId(domainId);
								oldDepositCashFlow.setDelFlag(DEL_FLAG_0);
								List<DyCashFlow> list = cashFlowService.findList(oldDepositCashFlow);
								if(list.isEmpty()){
									throw new ServiceException("上次出价保证金，无");
								}else {
									oldDepositCashFlow = list.get(0);
									oldDepositCashFlow.setOperateAmount(newDeposit);
									oldDepositCashFlow.setIsNewRecord(false);
									cashFlowService.save(oldDepositCashFlow);
								}
							}
						}else{
							if(newDeposit.longValue() != 0){
								//检查可用余额
								if (availableBalance < newDeposit) {
									// 可用余额无法支付保证金时，提醒会员充值
									AjaxResult ar = AjaxResult.makeWarn("出价失败，帐户可用余额不足，请充值");
									ar.getData().put("type", "");
									ar.getData().put("deposit", newDeposit);
									return ar;
								}
								// 冻结当前保证金
								if (!this.depositControl(
										dyFinance.getId(),
										dyFinance.getUpdateDate(),
										newDeposit,
										null,
										null,
										null)) {
									throw new ServiceException("冻结保证金操作失败");
								}
								//冻结当前保证金记录插入资金流
								DyCashFlow newDepositCashFlow = new DyCashFlow();
								newDepositCashFlow.setClientId(clientId);
								newDepositCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
								newDepositCashFlow.setOperateAmount(newDeposit);
								newDepositCashFlow.setOperateTime(new Date());
								newDepositCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
								newDepositCashFlow.setDomainnameId(domainId);
								newDepositCashFlow.preInsert(UserUtils.get(currClient.getBrokerId()));
								//设置该实体为新记录
								newDepositCashFlow.setIsNewRecord(true);
								cashFlowService.save(newDepositCashFlow);
							}
						}
						
						return AjaxResult.makeSuccess("您当前出价为" + dyBidhistory.getBidAmount().toString() + "，代理出价为" + newBidAmount.toString());
					}
				}
	
				// 根据本次出价计算保证金
				deposit = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_DEPOSIT);
				if (availableBalance < deposit) {
					// 可用余额无法支付保证金时，提醒会员充值
					AjaxResult ar = AjaxResult.makeWarn("出价失败，帐户余额不足，请充值");
					ar.getData().put("type", "");
					ar.getData().put("deposit", deposit);
					return ar;
				}
	
				DyFinance unFreezeFinance = new DyFinance(); // 释放保证金财务实体
				Long dbBidAmount     = 0L; // 出价金额
				Long proxyMaxAmount  = 0L; // 代理竞价上限金额
				String proxyClientId = ""; // 代理竞价人
	
				if (null != dyBidhistory) {
					dbBidAmount    = dyBidhistory.getBidAmount();
					proxyMaxAmount = dyBidhistory.getProxyAmount();
					if (null == proxyMaxAmount) {
						proxyMaxAmount = 0L;
					}
					proxyClientId  = dyBidhistory.getClientId();
	
					unFreezeFinance.setClientId(proxyClientId);
					unFreezeFinance.setDelFlag(DyFinance.DEL_FLAG_NORMAL);
					unFreezeFinance = dyFinanceDao.findList(unFreezeFinance).get(0); // 会员财务实体
				}
	
				// 判断出价是否过期
				if (newBidAmount.longValue() <= dbBidAmount.longValue()) {
					AjaxResult ar = AjaxResult.makeWarn("出价失败，出价应高于当前价");
					ar.getData().put("type", "overdue");
					return ar;
				}
	
				// 增加关注记录
				DyAttention dyAttention = new DyAttention();
				dyAttention.setClientId(clientId);
				dyAttention.setDomainnameId(domainId);
				dyAttention.setDelFlag(DyAttention.DEL_FLAG_NORMAL);
				if (!attentionService.isAttented(dyAttention)) {
//					dyAttention.preInsert(broker);
//					dyAttention.setIsNewRecord(true);
					attentionService.follow(domainId);
				}
	
				AjaxResult ar = AjaxResult.makeSuccess("出价成功");
	
				
				Long unfreezeDeposit = 0L;// 释放保证金金额:出价将被超者的保证金
				if (dbBidAmount.longValue() < proxyMaxAmount.longValue()) {// 该域名有代理竞价的场合
					User proxyBroker = UserUtils.get(dyClientDao.get(proxyClientId).getBrokerId()); // 代理竞价会员经纪人
					DyFinance proxyFinance = new DyFinance(); // 代理竞价会员财务信息
					proxyFinance.setClientId(proxyClientId);
					proxyFinance.setDelFlag(DyFinance.DEL_FLAG_NORMAL);
					proxyFinance = dyFinanceDao.findList(proxyFinance).get(0);
	
					if (newBidAmount.longValue() > proxyMaxAmount.longValue()) {// 如果当前出价金额大于代理竞价上限金额
						// 将当前代理竞价上限金额进行出价
						this.doBid(domainId, proxyClientId, proxyMaxAmount, BID_TYPE_PROXY, proxyBroker, proxyMaxAmount);
						unfreezeDeposit = DySysUtils.calculateDepositAndIncrement(proxyMaxAmount, BID_RULE_TYPE_DEPOSIT);
						// 更新最新消息计数
						dyNewsDao.updateNewBidCount(domainId, proxyClientId);
	
						// 根据当前代理竞价上限金额计算本次出价加价幅度
						increment = DySysUtils.calculateDepositAndIncrement(proxyMaxAmount, BID_RULE_TYPE_INCREMENT);
	
						// 冻结保证金金额
						Long freezeDeposit = 0L;
						if ((newBidAmount.longValue() - proxyMaxAmount.longValue()) <= increment.longValue()) {// 当前出价金额 - 代理竞价上限金额 == 加价幅度的场合  ：普通出价   小于的情况不应该出现
							freezeDeposit = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_DEPOSIT);
							this.doBid(domainId, clientId, newBidAmount, BID_TYPE_NORMAL, broker, null);
						} else {
							// 当前出价金额 - 代理竞价上限金额 > 加价幅度的场合 ：代理竞价
							Long bidAmount = proxyMaxAmount.longValue() + increment.longValue();
							freezeDeposit = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_DEPOSIT);
							this.doBid(domainId, clientId, bidAmount, BID_TYPE_PROXY, broker, newBidAmount);
							ar = AjaxResult.makeSuccess("您当前出价为" + bidAmount.toString() + "，代理出价为" + newBidAmount.toString());
						}
	
						// 更新最新消息计数
						dyNewsDao.updateNewBidCount(domainId, clientId);
	
						if(unfreezeDeposit.longValue() == 0){
							if(freezeDeposit.longValue() != 0){
								// 冻结当前出价者的保证金，解冻被超者的保证金
								if (!this.depositControl(
										dyFinance.getId(),
										dyFinance.getUpdateDate(),
										freezeDeposit,
										null,
										null,
										null)) {
									throw new ServiceException("冻结保证金操作失败");
								}
								//冻结当前出价者的保证金记录插入资金流
								DyCashFlow firstCashFlow = new DyCashFlow();
								firstCashFlow.setClientId(clientId);
								firstCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
								firstCashFlow.setOperateAmount(freezeDeposit);
								firstCashFlow.setOperateTime(new Date());
								firstCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
								firstCashFlow.setDomainnameId(domainId);
								firstCashFlow.preInsert(UserUtils.get(currClient.getBrokerId()));
								//设置该实体为新记录
								firstCashFlow.setIsNewRecord(true);
								cashFlowService.save(firstCashFlow);
							}
						}else{
							// 冻结当前出价者的保证金，解冻被超者的保证金
							if (!this.depositControl(
									dyFinance.getId(),
									dyFinance.getUpdateDate(),
									freezeDeposit,
									unFreezeFinance.getId(),
									unFreezeFinance.getUpdateDate(),
									unfreezeDeposit)) {
								throw new ServiceException("冻结保证金操作失败");
							}
							
							//解冻被超者的保证金记录插入资金流
							DyCashFlow secondCashFlow = new DyCashFlow();
							secondCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
							secondCashFlow.setClientId(proxyClientId);
							secondCashFlow.setOperateAmount(unfreezeDeposit);
							secondCashFlow.setDomainnameId(domainId);
							secondCashFlow.setDelFlag(DEL_FLAG_0);
							List<DyCashFlow> secondList = cashFlowService.findList(secondCashFlow);
							if(secondList.isEmpty()){
								throw new ServiceException("被超者出价保证金，无");
							}else {
								secondCashFlow = secondList.get(0);
								secondCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
								secondCashFlow.setIsNewRecord(false);
								cashFlowService.save(secondCashFlow);
							}
							
							//冻结当前出价者的保证金记录插入资金流
							DyCashFlow firstCashFlow = new DyCashFlow();
							firstCashFlow.setClientId(clientId);
							firstCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
							firstCashFlow.setOperateAmount(freezeDeposit);
							firstCashFlow.setOperateTime(new Date());
							firstCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
							firstCashFlow.setDomainnameId(domainId);
							firstCashFlow.preInsert(UserUtils.get(currClient.getBrokerId()));
							//设置该实体为新记录
							firstCashFlow.setIsNewRecord(true);
							cashFlowService.save(firstCashFlow);
						}
					} else {// 如果当前出价金额小于或者等于代理竞价上限金额
						//当前用户出价
						this.doBid(domainId, clientId, newBidAmount, BID_TYPE_NORMAL, broker, null);
						// 更新最新消息计数
						dyNewsDao.updateNewBidCount(domainId, clientId);
	
						// 代理竞价者进行代理竞价
						// 根据本次出价计算加价幅度
						increment = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_INCREMENT);
						// 冻结保证金金额
						if (increment.longValue() >= proxyMaxAmount.longValue() - newBidAmount.longValue()) {// 如果当前出价金额等于代理竞价上限金额，或者与代理竞价只差一个加价幅度
							this.doBid(domainId, proxyClientId, proxyMaxAmount, BID_TYPE_PROXY, proxyBroker, proxyMaxAmount);
						} else {// 如果当前出价金额小于代理竞价上限金额且相差至少两个加价幅度
							this.doBid(domainId, proxyClientId, newBidAmount.longValue() + increment.longValue(), BID_TYPE_PROXY, proxyBroker, proxyMaxAmount);
						}
	
						ar = AjaxResult.makeSuccess("出价被超出");
						
						// 更新最新消息计数
						dyNewsDao.updateNewBidCount(domainId, proxyClientId);
					}
				} else {// 该域名无代理竞价的场合
					// 根据当前最高出价计算加价幅度
					increment = DySysUtils.calculateDepositAndIncrement(dbBidAmount, BID_RULE_TYPE_INCREMENT);
	
					// 冻结保证金金额
					Long freezeDeposit = 0L;
					if (newBidAmount.longValue() - dbBidAmount.longValue() <= increment.longValue()) {//出价金额刚好一个加价幅度,小于的情况不应该出现
						// 当前出价金额 - 出价金额 <= 加价幅度的场合  普通出价
						freezeDeposit = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_DEPOSIT);
						this.doBid(domainId, clientId, newBidAmount, BID_TYPE_NORMAL, broker, null);
					} else {
						// 当前出价金额 - 出价金额 > 加价幅度的场合  代理竞价
						freezeDeposit = DySysUtils.calculateDepositAndIncrement(newBidAmount.longValue(), BID_RULE_TYPE_DEPOSIT);
						this.doBid(domainId, clientId, dbBidAmount.longValue() + increment.longValue(), BID_TYPE_PROXY, broker, newBidAmount);
						ar = AjaxResult.makeSuccess("您当前出价为" + (dbBidAmount.longValue() + increment.longValue()) + "，代理出价为" + newBidAmount.toString());
					}
					if(null != dyBidhistory){
						unfreezeDeposit = DySysUtils.calculateDepositAndIncrement(dbBidAmount, BID_RULE_TYPE_DEPOSIT);
					}else{
						unfreezeDeposit = 0L;
					}
					
	
					if(unfreezeDeposit.longValue() == 0){
						if(freezeDeposit.longValue() != 0){
							// 冻结当前出价者的保证金，解冻被超者的保证金
							if (!this.depositControl(
									dyFinance.getId(),
									dyFinance.getUpdateDate(),
									freezeDeposit,
									null,
									null,
									null)) {
								throw new ServiceException("冻结保证金操作失败");
							}
							//冻结当前出价者的保证金记录插入资金流
							DyCashFlow firstCashFlow = new DyCashFlow();
							firstCashFlow.setClientId(clientId);
							firstCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
							firstCashFlow.setOperateAmount(freezeDeposit);
							firstCashFlow.setOperateTime(new Date());
							firstCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
							firstCashFlow.setDomainnameId(domainId);
							firstCashFlow.preInsert(UserUtils.get(currClient.getBrokerId()));
							//设置该实体为新记录
							firstCashFlow.setIsNewRecord(true);
							cashFlowService.save(firstCashFlow);
						}
					}else{
						// 冻结当前出价者的保证金，解冻被超者的保证金
						if (!this.depositControl(
								dyFinance.getId(),
								dyFinance.getUpdateDate(),
								freezeDeposit,
								unFreezeFinance.getId(),
								unFreezeFinance.getUpdateDate(),
								unfreezeDeposit)) {
							throw new ServiceException("冻结保证金操作失败");
						}
						//解冻被超者的保证金记录插入资金流
						DyCashFlow secondCashFlow = new DyCashFlow();
						secondCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
						secondCashFlow.setClientId(proxyClientId);
						secondCashFlow.setOperateAmount(unfreezeDeposit);
						secondCashFlow.setDomainnameId(domainId);
						secondCashFlow.setDelFlag(DEL_FLAG_0);
						List<DyCashFlow> secondList = cashFlowService.findList(secondCashFlow);
						if(secondList.isEmpty()){
							throw new ServiceException("被超者出价保证金，无");
						}else {
							secondCashFlow = secondList.get(0);
							secondCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
							secondCashFlow.setIsNewRecord(false);
							cashFlowService.save(secondCashFlow);
						}
						
						//冻结当前出价者的保证金记录插入资金流
						DyCashFlow firstCashFlow = new DyCashFlow();
						firstCashFlow.setClientId(clientId);
						firstCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
						firstCashFlow.setOperateAmount(freezeDeposit);
						firstCashFlow.setOperateTime(new Date());
						firstCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
						firstCashFlow.setDomainnameId(domainId);
						firstCashFlow.preInsert(UserUtils.get(currClient.getBrokerId()));
						//设置该实体为新记录
						firstCashFlow.setIsNewRecord(true);
						cashFlowService.save(firstCashFlow);
					}
	
					// 更新最新消息计数
					dyNewsDao.updateNewBidCount(domainId, clientId);
				}
	
				// 当前日期
				Calendar currentCal = Calendar.getInstance();
				currentCal.setTime(new Date());
				currentCal.add(Calendar.MINUTE, DySysUtils.END_TIME_DELAY);
				// 结拍日期
				Calendar endCal = Calendar.getInstance();
				endCal.setTime(dyDomainname.getEndTime());
				if (currentCal.compareTo(endCal) >= 0) {
					// 如果在结拍前N分钟出价，则结拍时间自动增加N分钟
					endCal.add(Calendar.MINUTE, DySysUtils.END_TIME_DELAY);
					dyDomainnameDao.updateEndTimeById(domainId, currentCal.getTime());
					
					dyDomainname.setEndTime(currentCal.getTime());
					ShowDomainCacheUtil.syncDomainInfoToCache(dyDomainname);
				}
				
				return ar;
			} catch (ServiceException se) {
				// Service层公用的Exception抛出时，触发回滚
				ShowBidListCacheUtil.clearCache(domainId);
				throw se;
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.makeError("出价失败");
			}
		}
	}

	/**
	 * 会员出价后，保证金控制
	 * @param freezeId 冻结保证金财务ID
	 * @param freezeUpdateDate 冻结时间
	 * @param deposit 冻结金额
	 * @param unFreezeId 释放保证金财务ID
	 * @param unFreezeUpdateDate 释放时间
	 * @param unFreezeDeposit 释放金额
	 * @return 操作结果
	 */
	private boolean depositControl(
			String freezeId,
			Date freezeUpdateDate,
			Long deposit,
			String unFreezeId,
			Date unFreezeUpdateDate,
			Long unFreezeDeposit) {
		// 冻结保证金
		int freezeCnt = dyFinanceDao.updateFreezeBalance(freezeId, deposit, freezeUpdateDate);
		// 释放保证金
		int unFreezeCnt = 1;
		if (StringUtils.isNotEmpty(unFreezeId)) {
			if (freezeId.equals(unFreezeId)) {
				unFreezeUpdateDate = dyFinanceDao.get(new DyFinance(unFreezeId)).getUpdateDate();
			}
			unFreezeCnt = dyFinanceDao.updateFreezeBalance(unFreezeId, 0L - unFreezeDeposit, unFreezeUpdateDate);
		}
		return freezeCnt > 0 && unFreezeCnt > 0;
	}


	/**
	 * 出价操作
	 * @param domainId 域名ID
	 * @param clientId 会员ID
	 * @param bidAmount 出价金额
	 * @param bidType 出价类型
	 * @param broker 会员经纪人
	 * @param longs 代理竞价上限金额
	 */
	private void doBid(String domainId, String clientId, Long bidAmount, String bidType, User broker, Long proxyAmount) {
		DyBidhistory dyBidHist = new DyBidhistory();
		dyBidHist.setClientId(clientId);
		dyBidHist.setDomainnameId(domainId);
		dyBidHist.setBidAmount(bidAmount);
		dyBidHist.setBidTime(new Date());
		dyBidHist.setShareOpenid(DySysUtils.getShareOpenid());
		dyBidHist.setType(bidType);
		if (BID_TYPE_PROXY.equals(bidType)) {
			// 设置代理竞价上限金额
			dyBidHist.setProxyAmount(proxyAmount);
		}
		dyBidHist.setDelFlag(DyBidhistory.DEL_FLAG_NORMAL);
		dyBidHist.preInsert(broker);

		ShowBidListCacheUtil.addBid2ListDao(domainId, dyBidHist);
	}
	
	/**
	 * 根据域名状态查询该状态域名且已经违约的最高出价信息
	 * @param status 域名状态
	 * @return List :Map (keys:domainnameId sellerId buyerId bidAmount )
	 */
	public List<TransactionInformation>domainnameBuyerAndSeller(String status,String domainnameId){
		List<TransactionInformation> list = dao.domainnameBuyerAndSeller(status,domainnameId);
		return list;
	}
	
	/**
	 * 买家违约处理(买家已经付款)
	 * @param bidCashInfo
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public void buyerLoser(BidCashInfo bidCashInfo, DyDomainname dyDomainname) throws Exception{
		DyClient buyerClient = dyClientDao.get(bidCashInfo.getBuyClientId());//买家实体
		//买家出价的冻结保证金
		Long buyDeposit = null;
		if(Constant.SWITCH_ON.equals(buyerClient.getAvoidDeposit())){
			buyDeposit = 0L;
		}else{
			buyDeposit = DySysUtils.calculateDepositAndIncrement(bidCashInfo.getBidAmount(), Constant.BID_RULE_TYPE_DEPOSIT);
		}
		//实际的买家冻结金额
		Long realDeposit = null;
		/**改成从资金流获取
		long realDeposit = bidCashInfo.getBidAmount() > buyDeposit ? bidCashInfo.getBidAmount() : buyDeposit;
		**/
		//买家冻结的金额记录
		DyCashFlow freeDepositDyCashFlow = new DyCashFlow();
		freeDepositDyCashFlow.setClientId(bidCashInfo.getBuyClientId());
		freeDepositDyCashFlow.setDomainnameId(bidCashInfo.getDomainId());
		freeDepositDyCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		List<DyCashFlow> freeDepositList = dyCashFlowDao.findList(freeDepositDyCashFlow);
		if(freeDepositList.isEmpty()){
			throw new ServiceException("无买家冻结记录");
		}else{
			freeDepositDyCashFlow = freeDepositList.get(0);
			realDeposit = freeDepositDyCashFlow.getOperateAmount();
		}
		
		//卖家保证金
		Long sellerDeposit = null;
		DyCashFlow sellerDepositCashFlow = new DyCashFlow();
		sellerDepositCashFlow.setClientId(bidCashInfo.getSellClientId());
		sellerDepositCashFlow.setDomainnameId(bidCashInfo.getDomainId());
		sellerDepositCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		List<DyCashFlow> sellerDepositList = dyCashFlowDao.findList(sellerDepositCashFlow);
		if(sellerDepositList.isEmpty()){
			throw new ServiceException("无买家冻结记录");
		}else{
			sellerDepositCashFlow = sellerDepositList.get(0);
			sellerDeposit = sellerDepositCashFlow.getOperateAmount();
		}
		
		/**获取卖家财务信息，并将买家违约的一半保证金给卖家，解冻金额*/
		/*卖家财务信息*/
		DyFinance sellFinance = new DyFinance();
		sellFinance.setClientId(bidCashInfo.getSellClientId());
		sellFinance = dyFinanceDao.get(sellFinance);
		sellFinance.setAccountBalance(sellFinance.getAccountBalance()+buyDeposit/2);//卖家获得买家一半保证金
		sellFinance.setFreezeBalance(sellFinance.getFreezeBalance()-sellerDeposit);//解冻卖家保证金
		if(dyFinanceDao.update(sellFinance)==0){
			throw new ServiceException("更新卖家账户异常");
		}
		
		/*解冻卖家资金流信息*/
		/**
		DyCashFlow freeBuyDyCashFlow = new DyCashFlow();
		freeBuyDyCashFlow.setClientId(bidCashInfo.getBuyClientId());
		freeBuyDyCashFlow.setDomainnameId(bidCashInfo.getDomainId());
		freeBuyDyCashFlow.setOperateAmount(sellerDeposit);
		if(dyCashFlowDao.updateFreeCashFlow(freeBuyDyCashFlow) == 0){
			throw new ServiceException("解冻卖家资金流失败");
		}
		 **/
		sellerDepositCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
		cashFlowService.save(sellerDepositCashFlow);
		
		//更新卖家的资金流信息:违约收入
		DyCashFlow sellCashFlow = new DyCashFlow();
		sellCashFlow.setClientId(bidCashInfo.getSellClientId());
		sellCashFlow.setDomainnameId(dyDomainname.getId());
		sellCashFlow.setOperate(BREACH_IN);
		sellCashFlow.setOperateAmount(buyDeposit/2);
		sellCashFlow.setOperateTime(new Date());
		sellCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
		sellCashFlow.setAmountBalance(sellFinance.getAccountBalance());
		sellCashFlow.preInsert();
		if(dyCashFlowDao.insert(sellCashFlow) == 0){
			throw new ServiceException("更新卖家资金流异常");
		}
		
		/**扣除买家一半保证金，解冻剩余资金*/
		/*更新买家财务信息*/
		DyFinance buyFinance = new DyFinance();
		buyFinance.setClientId(bidCashInfo.getBuyClientId());
		buyFinance = dyFinanceDao.get(buyFinance);
		buyFinance.setAccountBalance(buyFinance.getAccountBalance() - buyDeposit);//扣除保证金
		buyFinance.setFreezeBalance(buyFinance.getFreezeBalance() - realDeposit);//解冻剩余保证金
		if(dyFinanceDao.update(buyFinance)==0){
			throw new ServiceException("更新买家账户异常");
		}
		
		/*解冻买家资金流信息*/
		/**
		DyCashFlow freeDepositDyCashFlow = new DyCashFlow();
		freeDepositDyCashFlow.setClientId(bidCashInfo.getBuyClientId());
		freeDepositDyCashFlow.setDomainnameId(bidCashInfo.getDomainId());
		freeDepositDyCashFlow.setOperateAmount(realDeposit);
		if(dyCashFlowDao.updateFreeCashFlow(freeDepositDyCashFlow) == 0){
			throw new ServiceException("解冻买家资金流失败");
		}
		 **/
		freeDepositDyCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
		cashFlowService.save(freeDepositDyCashFlow);
		
		//更新买家的资金流信息
		DyCashFlow buyCashFlow = new DyCashFlow();
		buyCashFlow.setClientId(bidCashInfo.getBuyClientId());
		buyCashFlow.setDomainnameId(dyDomainname.getId());
		buyCashFlow.setOperate(BREACH_OUT);
		buyCashFlow.setOperateAmount(buyDeposit);
		buyCashFlow.setOperateTime(new Date());
		buyCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
		buyCashFlow.setAmountBalance(buyFinance.getAccountBalance());
		buyCashFlow.preInsert();
		if(dyCashFlowDao.insert(buyCashFlow) == 0){
			throw new ServiceException("更新资金流异常");
		}
	
		/**卖家获得买家保证金的一半，剩下的钱归平台*/
		/*更新平台账户*/
		Long totalIncome = dyPlatformAccountService.updateIncomeAccount(buyDeposit - buyDeposit/2);	
		if(totalIncome == null){
			throw new ServiceException("更新平台总账户失败");
		}
		
		/*插入平台收支记录*/
		DyPlatformIncome dyPlatformIncome = new DyPlatformIncome();
		dyPlatformIncome.setDomainnameId(bidCashInfo.getDomainId());
		dyPlatformIncome.setBuyClientId(bidCashInfo.getBuyClientId());
		dyPlatformIncome.setOperate(Constant.PLATFORM_INCOME_OPERATE_IN);
		dyPlatformIncome.setOperateAmount(buyDeposit - buyDeposit/2);
		dyPlatformIncome.setTotalAmount(totalIncome);
		dyPlatformIncome.preInsert();
		if(dyPlatformIncomeDao.insert(dyPlatformIncome) == 0){
			throw new ServiceException("插入平台收支信息失败");
		}
		
		/**更新域名信息*/
		if(dyDomainnameDao.update(dyDomainname)==0){
			throw new ServiceException("更新域名信息异常");
		}
		
		/*给卖家发送微信消息*/
		Message message = new Message();
		String title = DySysUtils.TEMPLATE_TITLE_0011;
		String content = DySysUtils.TEMPLATE_MESSAGE_0011;
		content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
		content = content.replace("{{buyer.DATA}}", bidCashInfo.getBuyNickname());
		content = content.replace("{{deposit.DATA}}", Long.toString(buyDeposit/2));
		message.SendNews(WeChat.getAccessToken(), bidCashInfo.getSellOpenId(), title, content, dyDomainname.getId());
		
		//给买家发送微信消息
		title = DySysUtils.TEMPLATE_TITLE_0010;
		content = DySysUtils.TEMPLATE_MESSAGE_0010;
		content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
		content = content.replace("{{seller.DATA}}", bidCashInfo.getSellNickname());
		content = content.replace("{{deposit.DATA}}", Long.toString(buyDeposit));
		message.SendNews(WeChat.getAccessToken(), bidCashInfo.getBuyOpenId(), title, content, dyDomainname.getId());
	}

	
	/**买家违约处理(从“待买家付款”状态，变为“买家违约”，买家还没有付款)
	 * @param map 违约记录 Map (keys:domainnameId sellerId buyerId bidAmount )
	 */
	@Transactional(readOnly = false)
	public void buyerBreachHandle(TransactionInformation transactionInformation) throws ServiceException{
		String domainnameId = transactionInformation.getDomainnameId();
		String sellerId = transactionInformation.getSellerId();
		String buyerId = transactionInformation.getBuyerId();

		/**改为从资金流记录获取
		Long buyerDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getBidAmount(), BID_RULE_TYPE_DEPOSIT);//计算买家保证金,有可能是0
		Long sellerDeposit = DySysUtils.SELL_DEPOSIT;//计算卖家保证金,有可能是0
		**/
		Long buyerDeposit = null;
		Long sellerDeposit = null;
		//卖家保证金冻结记录
		DyCashFlow sellerOldCashFlow = new DyCashFlow();
		sellerOldCashFlow.setClientId(sellerId);
		sellerOldCashFlow.setDomainnameId(domainnameId);
		sellerOldCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		List<DyCashFlow> sellerList = cashFlowService.findList(sellerOldCashFlow);
		if (sellerList.isEmpty()) {
			throw new ServiceException("没有卖家保证金冻结记录");
		} else {
			sellerOldCashFlow = sellerList.get(0);
			sellerDeposit = sellerOldCashFlow.getOperateAmount();
		}
		//买家保证金冻结记录
		DyCashFlow buyerOldCashFlow = new DyCashFlow();
		buyerOldCashFlow.setClientId(buyerId);
		buyerOldCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		buyerOldCashFlow.setDomainnameId(domainnameId);
		List<DyCashFlow> buyerList = cashFlowService.findList(buyerOldCashFlow);
		if(buyerList.isEmpty()){
			throw new ServiceException("没有保证金冻结记录");
		}else {
			buyerOldCashFlow = buyerList.get(0);
			buyerDeposit = buyerOldCashFlow.getOperateAmount();
		}
		
		//卖家id
		DyFinance dyFinance = new DyFinance();//卖家财务
		//获取卖家财务信息，并将买家违约的一半保证金给卖家
		dyFinance.setClientId(sellerId);
		dyFinance = dyFinanceDao.findList(dyFinance).get(0);
		dyFinance.setAccountBalance(dyFinance.getAccountBalance()+buyerDeposit/2);//卖家获得买家一半保证金
		dyFinance.setFreezeBalance(dyFinance.getFreezeBalance()-sellerDeposit);//解冻卖家保证金
		int n = dyFinanceDao.updateFinance(dyFinance);
		if(n==0){
			throw new ServiceException();
		}
		
		//更新卖家的资金流信息
		//将卖家获取的违约收入插入资金流
		DyCashFlow breachInCashFlow = new DyCashFlow();
		breachInCashFlow.setClientId(sellerId);
		breachInCashFlow.setDomainnameId(transactionInformation.getDomainnameId());
		breachInCashFlow.setOperate(BREACH_IN);
		breachInCashFlow.setOperateAmount(buyerDeposit/2);
		breachInCashFlow.setAmountBalance(dyFinance.getAccountBalance());
		breachInCashFlow.setOperateTime(new Date());
		breachInCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
		// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
		breachInCashFlow.preInsert(UserUtils.get(dyClientDao.get(sellerId).getBrokerId()));
		// 设置该实体为新记录
		breachInCashFlow.setIsNewRecord(true);
		cashFlowService.save(breachInCashFlow);
		
		//卖家保证金冻结记录解冻
		sellerOldCashFlow = sellerList.get(0);
		sellerOldCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
		cashFlowService.save(sellerOldCashFlow);
		
		//买家违约金保证金扣除
		DyFinance dyFinance1 = new DyFinance();
		dyFinance1.setClientId(buyerId);
		dyFinance1 = dyFinanceDao.findList(dyFinance1).get(0);
		dyFinance1.setAccountBalance(dyFinance1.getAccountBalance()-buyerDeposit);
		dyFinance1.setFreezeBalance(dyFinance1.getFreezeBalance()-buyerDeposit);
		int n1 = dyFinanceDao.updateFinance(dyFinance1);
		if(n1==0){
			throw new ServiceException();
		}
		
		//更新买家的资金流信息
		//插入违约扣除记录
		DyCashFlow breachOutCashFlow = new DyCashFlow();
		breachOutCashFlow.setClientId(buyerId);
		breachOutCashFlow.setDomainnameId(domainnameId);
		breachOutCashFlow.setOperate(BREACH_OUT);
		breachOutCashFlow.setOperateAmount(buyerDeposit);
		breachOutCashFlow.setAmountBalance(dyFinance1.getAccountBalance());
		breachOutCashFlow.setOperateTime(new Date());
		breachOutCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
		// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
		breachOutCashFlow.preInsert(UserUtils.get(dyClientDao.get(buyerId).getBrokerId()));
		// 设置该实体为新记录
		breachOutCashFlow.setIsNewRecord(true);
		cashFlowService.save(breachOutCashFlow);
		
		//将保证金冻结记录解冻
		buyerOldCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
		cashFlowService.save(buyerOldCashFlow);
	
		/*更新平台账户*/
		Long totalIncome = dyPlatformAccountService.updateIncomeAccount(buyerDeposit - buyerDeposit/2);	
		if(totalIncome == null){
			throw new ServiceException("更新平台总账户失败");
		}
		/*插入平台收支记录*/
		DyPlatformIncome dyPlatformIncome = new DyPlatformIncome();
		dyPlatformIncome.setDomainnameId(transactionInformation.getDomainnameId());
		dyPlatformIncome.setBuyClientId(buyerId);
		dyPlatformIncome.setOperate(Constant.PLATFORM_INCOME_OPERATE_IN);
		dyPlatformIncome.setOperateAmount(buyerDeposit - buyerDeposit/2);
		dyPlatformIncome.setTotalAmount(totalIncome);
		dyPlatformIncome.preInsert(UserUtils.get(dyClientDao.get(buyerId).getBrokerId()));
		if(dyPlatformIncomeDao.insert(dyPlatformIncome) == 0){
			throw new ServiceException("插入平台收支信息失败");
		}
		
		
		//修改域名状态
		DyDomainname dyDomainname = dyDomainnameDao.get(transactionInformation.getDomainnameId());
		dyDomainname.setStatus(DOMAIN_STATUS_21);
		int n2 = dyDomainnameDao.update(dyDomainname);
		if(n2==0){
			throw new ServiceException();
		}
		
		// 获取卖家
		DyClient seller = dyClientDao.get(sellerId);
		// 获取买家
		DyClient buyer = dyClientDao.get(buyerId);
		//给卖家发送微信消息
		try {
			Message message = new Message();
			String title = DySysUtils.TEMPLATE_TITLE_0011;
			String content = DySysUtils.TEMPLATE_MESSAGE_0011;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			content = content.replace("{{buyer.DATA}}", buyer.getNickname());
			content = content.replace("{{deposit.DATA}}", Long.toString(buyerDeposit/2));
			message.SendNews(WeChat.getAccessToken(), seller.getOpenid(), title, content, dyDomainname.getId());
			
			//给买家发送微信消息
			title = DySysUtils.TEMPLATE_TITLE_0010;
			content = DySysUtils.TEMPLATE_MESSAGE_0010;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			content = content.replace("{{seller.DATA}}", seller.getNickname());
			content = content.replace("{{deposit.DATA}}", Long.toString(buyerDeposit));
			message.SendNews(WeChat.getAccessToken(), buyer.getOpenid(), title, content, dyDomainname.getId());
		} catch (Exception e) {
			logger.error("给买家/卖家发送信息失败");
		}
	}
	
	/**卖家违约处理
	 * @param map 违约记录 Map (keys:domainnameId sellerId buyerId bidAmount )
	 */
	@Transactional(readOnly = false)
	public void sellerBreachHandle( TransactionInformation transactionInformation) throws Exception,ServiceException{
		
		String domainnameId = transactionInformation.getDomainnameId();
		String buyerId = transactionInformation.getBuyerId();//买家id
		String sellerId = transactionInformation.getSellerId();//卖家id
		
		/**改成从资金流获取
		Long sellerDeposit = DySysUtils.SELL_DEPOSIT;//计算卖家保证金,可能为0
		Long buyerDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getBidAmount(), BID_RULE_TYPE_DEPOSIT);//计算买家保证金
		Long buyerFreeze = buyerDeposit > transactionInformation.getBidAmount() ? buyerDeposit : transactionInformation.getBidAmount();//买家被冻结的钱
		**/
		Long buyerFreeze = null;
		Long sellerDeposit = null;
		//买家已冻结的金额记录
		DyCashFlow buyerOldCashFlow = new DyCashFlow();
		buyerOldCashFlow.setClientId(buyerId);
		buyerOldCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		buyerOldCashFlow.setDomainnameId(domainnameId);
		List<DyCashFlow> buyerList = cashFlowService.findList(buyerOldCashFlow);
		if (buyerList.isEmpty()) {
			throw new ServiceException("无买家付款冻结记录");
		} else {
			buyerOldCashFlow = buyerList.get(0);
			buyerFreeze = buyerOldCashFlow.getOperateAmount();
		}
		//卖家冻结保证金记录
		DyCashFlow sellerOldCashFlow = new DyCashFlow();
		sellerOldCashFlow.setClientId(sellerId);
		sellerOldCashFlow.setOperate(Constant.CASHFLOW_OPERATE_FREEZE);
		sellerOldCashFlow.setDomainnameId(domainnameId);
		List<DyCashFlow> sellerList = cashFlowService.findList(sellerOldCashFlow);
		if (sellerList.isEmpty()) {
			throw new ServiceException("无卖家付款冻结记录");
		} else {
			sellerOldCashFlow = sellerList.get(0);
			sellerDeposit = sellerOldCashFlow.getOperateAmount();
		}
		
		//获取买家财务信息，并将卖家违约的一半保证金给买家
		DyFinance dyFinance = new DyFinance();
		dyFinance.setClientId(buyerId);
		dyFinance = dyFinanceDao.findList(dyFinance).get(0);
		dyFinance.setAccountBalance(dyFinance.getAccountBalance()+sellerDeposit/2);//，买家获得卖家一半保证金
		dyFinance.setFreezeBalance(dyFinance.getFreezeBalance()-buyerFreeze);//解冻买家冻结金额
		int n = dyFinanceDao.updateFinance(dyFinance);
		if(n==0){
			throw new ServiceException();
		}
		
		//更新买家的资金流信息
		//将买家获取的违约收入插入资金流标表
		DyCashFlow buyerCashFlow = new DyCashFlow();
		buyerCashFlow.setClientId(buyerId);
		buyerCashFlow.setDomainnameId(domainnameId);
		buyerCashFlow.setOperate(BREACH_IN);
		buyerCashFlow.setOperateAmount(sellerDeposit/2);
		buyerCashFlow.setAmountBalance(dyFinance.getAccountBalance());
		buyerCashFlow.setOperateTime(new Date());
		buyerCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
		// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
		buyerCashFlow.preInsert(UserUtils.get(dyClientDao.get(buyerId).getBrokerId()));
		// 设置该实体为新记录
		buyerCashFlow.setIsNewRecord(true);
		cashFlowService.save(buyerCashFlow);
		
		
		//解冻买家已冻结的金额
		buyerOldCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
		cashFlowService.save(buyerOldCashFlow);
		
		//卖家id
		DyFinance sellerDyFinance = new DyFinance();//卖家财务
		//卖家违约金保证金扣除
		sellerDyFinance.setClientId(sellerId);
		sellerDyFinance = dyFinanceDao.findList(sellerDyFinance).get(0);
		sellerDyFinance.setAccountBalance(sellerDyFinance.getAccountBalance()-sellerDeposit);
		sellerDyFinance.setFreezeBalance(sellerDyFinance.getFreezeBalance()-sellerDeposit);
		int n1 = dyFinanceDao.updateFinance(sellerDyFinance);
		if(n1==0){
			throw new ServiceException();
		}
		
		//更新卖家的资金流信息
		//将卖家违约扣除插入资金流
		DyCashFlow sellerCashFlow = new DyCashFlow();
		sellerCashFlow.setClientId(sellerId);
		sellerCashFlow.setDomainnameId(transactionInformation.getDomainnameId());
		sellerCashFlow.setOperate(BREACH_OUT);
		sellerCashFlow.setOperateAmount(sellerDeposit);
		sellerCashFlow.setAmountBalance(sellerDyFinance.getAccountBalance());
		sellerCashFlow.setOperateTime(new Date());
		sellerCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
		// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
		sellerCashFlow.preInsert(UserUtils.get(dyClientDao.get(sellerId).getBrokerId()));
		// 设置该实体为新记录
		sellerCashFlow.setIsNewRecord(true);
		cashFlowService.save(sellerCashFlow);
		
		//将卖家冻结的保证金解冻
		sellerOldCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
		cashFlowService.save(sellerOldCashFlow);
		
		/*更新平台账户*/
		Long totalIncome = dyPlatformAccountService.updateIncomeAccount(sellerDeposit - sellerDeposit/2);	
		if(totalIncome == null){
			throw new ServiceException("更新平台总账户失败");
		}
		/*插入平台收支记录*/
		DyPlatformIncome dyPlatformIncome = new DyPlatformIncome();
		dyPlatformIncome.setDomainnameId(transactionInformation.getDomainnameId());
		dyPlatformIncome.setBuyClientId(buyerId);
		dyPlatformIncome.setOperate(Constant.PLATFORM_INCOME_OPERATE_IN);
		dyPlatformIncome.setOperateAmount(sellerDeposit - sellerDeposit/2);
		dyPlatformIncome.setTotalAmount(totalIncome);
		dyPlatformIncome.preInsert(UserUtils.get(dyClientDao.get(sellerId).getBrokerId()));
		if(dyPlatformIncomeDao.insert(dyPlatformIncome) == 0){
			throw new ServiceException("插入平台收支信息失败");
		}
		
		//修改域名状态
		DyDomainname dyDomainname = dyDomainnameDao.get(transactionInformation.getDomainnameId());
		dyDomainname.setStatus(DOMAIN_STATUS_22);
		int n2 = dyDomainnameDao.update(dyDomainname);
		if(n2==0){
			throw new ServiceException();
		}
		
		// 获取卖家
		DyClient seller = dyClientDao.get(sellerId);
		// 获取买家
		DyClient buyer = dyClientDao.get(buyerId);
		try {
			//给卖家发送微信消息
			Message message = new Message();
			String title = DySysUtils.TEMPLATE_TITLE_0014;
			String content = DySysUtils.TEMPLATE_MESSAGE_0014;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			content = content.replace("{{buyer.DATA}}", buyer.getNickname());
			content = content.replace("{{deposit.DATA}}", Long.toString(sellerDeposit));
			message.SendNews(WeChat.getAccessToken(), seller.getOpenid(), title, content, dyDomainname.getId());
			
			//给买家发送微信消息
			title = DySysUtils.TEMPLATE_TITLE_0013;
			content = DySysUtils.TEMPLATE_MESSAGE_0013;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			content = content.replace("{{seller.DATA}}", seller.getNickname());
			content = content.replace("{{deposit.DATA}}", Long.toString(sellerDeposit/2));
			message.SendNews(WeChat.getAccessToken(), buyer.getOpenid(), title, content, dyDomainname.getId());
		} catch (Exception e) {
			logger.error("给买家/卖家发送信息失败");
		}
		
	}
	/**买家没有确认收到域名
	 * @param map 违约记录 Map (keys:domainnameId sellerId buyerId bidAmount )
	 */
	@Transactional(readOnly = false)
	public void notConfirmReceiveDomainname(TransactionInformation transactionInformation) throws Exception, ServiceException{
		
		//修改域名状态
		DyDomainname dyDomainname = dyDomainnameDao.get(transactionInformation.getDomainnameId());
		dyDomainname.setStatus(DOMAIN_STATUS_14);
		int n2 = dyDomainnameDao.update(dyDomainname);
		if(n2==0){
			throw new ServiceException();
		}
		
		// 获取卖家
		DyClient seller = dyClientDao.get(transactionInformation.getSellerId());
		// 获取买家
		DyClient buyer = dyClientDao.get(transactionInformation.getBuyerId());
		//给卖家发送微信消息
		Message message = new Message();
		String title = DySysUtils.TEMPLATE_TITLE_0016;
		String content = DySysUtils.TEMPLATE_MESSAGE_0016;
		content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
		content = content.replace("{{buyer.DATA}}", buyer.getNickname());
		content = content.replace("{{price.DATA}}", transactionInformation.getBidAmount().toString());
		message.SendNews(WeChat.getAccessToken(), seller.getOpenid(), title, content, dyDomainname.getId());
		
		//给买家发送微信消息
		title = DySysUtils.TEMPLATE_TITLE_0015;
		content = DySysUtils.TEMPLATE_MESSAGE_0015;
		content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
		content = content.replace("{{seller.DATA}}", seller.getNickname());
		content = content.replace("{{price.DATA}}", transactionInformation.getBidAmount().toString());
		message.SendNews(WeChat.getAccessToken(), buyer.getOpenid(), title, content, dyDomainname.getId());
		
	}
	
	/**
	 * @param domainnameId域名id
	 * @return DyBidhistory 中标实体
	 */
	public DyBidhistory getSuccessfulBidder(String domainnameId){
		return dao.getSuccessfulBidder(domainnameId);
	}

	public boolean isBided(DyBidhistory dyBidhistory) {
		List<BidClient> bidList = ShowBidListCacheUtil.getBidList(dyBidhistory.getDomainnameId());
		if (bidList != null) {
			for (BidClient c : bidList) {
	            if (Objects.equal(dyBidhistory.getClientId(), c.getClientId())) {
	            	return true;
	            }
            }
			return false;
		}
		
	    return !dao.findList(dyBidhistory).isEmpty();
    }
	// 出价各个分支封装成方法begin  wufulin
	/**
	 * 根据域名ID，会员出价金额进行出价操作
	 * @param domainId 域名ID
	 * @param newBidAmount 会员出价金额
	 * @param dyClient 会员实体（经纪人代替出价是设定）
	 * @return 出价结果
	 * @throws ServiceException
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public AjaxResult bids(String domainId, Long newBidAmount, DyClient dyClient) throws ServiceException, Exception {
		DyClient currClient = DySysUtils.getCurrentDyClient();         // 当前登录会员
		if (null != dyClient) {
			//经纪人代替出价是设定
			currClient = dyClient;
		}
		
		String clientId           = currClient.getId();
		DyDomainname dyDomainname = ShowDomainCacheUtil.getDomainFromCache(domainId); // 域名实体 

		if(dyDomainname == null){
			return AjaxResult.makeError("出价失败，无此域名");
		}
		
		// 是否结拍检查
		if (new Date().after(dyDomainname.getEndTime())) {
			return AjaxResult.makeError("出价失败，该域名已结拍");
		}

		// 如果该域名属于登录会员，不允许出价
		if (clientId.equals(dyDomainname.getClientId())) {
			AjaxResult ar = AjaxResult.makeWarn("出价失败，不能对自己的域名出价");
			ar.getData().put("type", "owner");
			return ar;
		}
		
		// 先判断出价是否高于当前价
		DyBidhistory currentBidhistory = ShowBidListCacheUtil.getMaxBidAmount(domainId);
		// 判断出价是否过期
		if (null != currentBidhistory && newBidAmount.longValue() <= currentBidhistory.getBidAmount().longValue()) {
			AjaxResult ar = AjaxResult.makeWarn("出价失败，出价应高于当前价");
			ar.getData().put("type", "overdue");
			return ar;
		}
		
		// 同时出价锁
		synchronized (getLock(domainId)) {
			User broker = UserUtils.get(currClient.getBrokerId()); // 会员经纪人
	
			try {
				DyFinance dyFinance = new DyFinance();//当前出价者，即当前登录用户的财务实体
				dyFinance.setClientId(clientId);
				dyFinance.setDelFlag(DyFinance.DEL_FLAG_NORMAL);
				dyFinance = dyFinanceDao.findList(dyFinance).get(0); // 会员财务实体
	
	
				// 获取当前最高出价记录
				DyBidhistory dyBidhistory = ShowBidListCacheUtil.getMaxBidAmount(domainId);
	
				//自己已经是最高出价者,出价处理
				if (null != dyBidhistory && dyBidhistory.getClientId().equals(clientId)) {
					return this.theHighestBid(dyBidhistory,newBidAmount,dyFinance,domainId,currClient);
				}
	
				//自己不是最高出价者
				Long availableBalance = dyFinance.getAccountBalance() - dyFinance.getFreezeBalance(); // 会员可用余额 = 会员帐户余额 - 会员帐户冻结金额
				Long deposit          = 0L; // 保证金
				// 根据本次出价计算保证金
				if(Constant.SWITCH_OFF.equals(currClient.getAvoidDeposit())){
					//当前用户不可以免除保证金
					deposit = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_DEPOSIT);
				}
				
				if (availableBalance < deposit) {
					// 可用余额无法支付保证金时，提醒会员充值
					AjaxResult ar = AjaxResult.makeWarn("出价失败，帐户余额不足，请充值");
					ar.getData().put("type", "");
					ar.getData().put("deposit", deposit);
					return ar;
				}
	
				DyFinance unFreezeFinance = new DyFinance(); // 释放保证金财务实体
				Long dbBidAmount     = 0L; // 当前最高出价金额
				Long proxyMaxAmount  = 0L; // 代理竞价上限金额
				String proxyClientId = ""; // 代理竞价人
	
				if (null != dyBidhistory) {
					dbBidAmount    = dyBidhistory.getBidAmount();
					proxyMaxAmount = dyBidhistory.getProxyAmount();
					if (null == proxyMaxAmount) {
						proxyMaxAmount = 0L;
					}
					proxyClientId  = dyBidhistory.getClientId();
	
					unFreezeFinance.setClientId(proxyClientId);
					unFreezeFinance.setDelFlag(DyFinance.DEL_FLAG_NORMAL);
					unFreezeFinance = dyFinanceDao.findList(unFreezeFinance).get(0); // 会员财务实体
				}
	
				// 判断出价是否过期
				if (newBidAmount.longValue() <= dbBidAmount.longValue()) {
					AjaxResult ar = AjaxResult.makeWarn("出价失败，出价应高于当前价");
					ar.getData().put("type", "overdue");
					return ar;
				}
	
				// 增加关注记录
				DyAttention dyAttention = new DyAttention();
				dyAttention.setClientId(clientId);
				dyAttention.setDomainnameId(domainId);
				dyAttention.setDelFlag(DyAttention.DEL_FLAG_NORMAL);
				if (!attentionService.isAttented(dyAttention)) {
//							dyAttention.preInsert(broker);
//							dyAttention.setIsNewRecord(true);
					attentionService.follow(domainId);
				}
	
				AjaxResult ar = AjaxResult.makeSuccess("出价成功");
				
				if (dbBidAmount.longValue() < proxyMaxAmount.longValue()) {
					//有代理竞价的情况
					ar = this.NotTheHighestBidWithProxy(dyBidhistory, newBidAmount, dyFinance, unFreezeFinance, broker, domainId, currClient);
				} else {
					ar = this.NotTheHighestBidWithoutProxy(dyBidhistory, newBidAmount, dyFinance, unFreezeFinance, broker, domainId, currClient);
				}
	
				// 当前日期
				Calendar currentCal = Calendar.getInstance();
				currentCal.setTime(new Date());
				currentCal.add(Calendar.MINUTE, DySysUtils.END_TIME_DELAY);
				// 结拍日期
				Calendar endCal = Calendar.getInstance();
				endCal.setTime(dyDomainname.getEndTime());
				if (currentCal.compareTo(endCal) >= 0) {
					// 如果在结拍前N分钟出价，则结拍时间自动增加N分钟
					endCal.add(Calendar.MINUTE, DySysUtils.END_TIME_DELAY);
					dyDomainnameDao.updateEndTimeById(domainId, currentCal.getTime());
					
					dyDomainname.setEndTime(currentCal.getTime());
					ShowDomainCacheUtil.syncDomainInfoToCache(dyDomainname);
				}
				
				return ar;
			} catch (ServiceException se) {
				// Service层公用的Exception抛出时，触发回滚
				ShowBidListCacheUtil.clearCache(domainId);
				throw se;
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.makeError("出价失败");
			}
		}
	}
	
	
	/**
	 * 自己已经是最高出价者,出价处理
	 * @param dyBidhistory 最高出价记录
	 * @param newBidAmount 当前新出价价格
	 * @param dyFinance 当前出价者财务实体
	 * @param domainId 被出价域名id
	 * @param currClient 当前出价者实体
	 * @return
	 */
	public AjaxResult theHighestBid(DyBidhistory dyBidhistory,Long newBidAmount,DyFinance dyFinance,String domainId,DyClient currClient){
		Long availableBalance = dyFinance.getAccountBalance() - dyFinance.getFreezeBalance(); // 当前出价者可用余额 	会员可用余额 = 会员帐户余额 - 会员帐户冻结金额
		String clientId           = currClient.getId();//当前出价者id
		
		if (dyBidhistory.getProxyAmount() != null && dyBidhistory.getProxyAmount().longValue() >= newBidAmount.longValue()) {
			//已经是代理竞价，出价需高于当前代理竞价
			return AjaxResult.makeError("出价失败，请出更高的代理竞价");
		}
				
		//检查可用余额
		Long newDeposit = null;//当前出价需冻结的保证金
		Long oldDeposit = null;//上次出价的保证金
		//计算当前保证金和上次保证金
		if(Constant.SWITCH_ON.equals(currClient.getAvoidDeposit())){
			//当前用户可以免除保证金
			newDeposit = 0L;
		}else{
			newDeposit = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_DEPOSIT);//计算当前保证金
		}
		/**旧保证金不在通过计算得出，通过资金流记录得到
		if (dyBidhistory.getProxyAmount() == null || dyBidhistory.getProxyAmount().longValue() == 0) {
			oldDeposit = DySysUtils.calculateDepositAndIncrement(dyBidhistory.getBidAmount(), BID_RULE_TYPE_DEPOSIT);//计算上次出价保证金
		} else {
			oldDeposit = DySysUtils.calculateDepositAndIncrement(dyBidhistory.getProxyAmount(), BID_RULE_TYPE_DEPOSIT);//计算上次出价保证金
		}
		**/
		//获取上次出价保证金
		DyCashFlow oldDepositCashFlow = new DyCashFlow();
		oldDepositCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		oldDepositCashFlow.setClientId(clientId);
		oldDepositCashFlow.setDomainnameId(domainId);
		oldDepositCashFlow.setDelFlag(DEL_FLAG_0);
		List<DyCashFlow> list = cashFlowService.findList(oldDepositCashFlow);
		if(list.isEmpty()){
			throw new ServiceException("上次出价保证金，无");
		}else {
			oldDepositCashFlow = list.get(0);
			oldDeposit = oldDepositCashFlow.getOperateAmount();
		}
		
		if (availableBalance < newDeposit - oldDeposit) {
			// 可用余额无法支付保证金时，提醒会员充值
			AjaxResult ar = AjaxResult.makeWarn("出价失败，帐户可用余额不足，请充值");
			ar.getData().put("type", "");
			ar.getData().put("deposit", newDeposit - oldDeposit);
			return ar;
		}
		
		//已有代理竞价且新出价高于代理竞价、没有代理竞价：这两种情况都进行代理竞价
		// 最高价出价者和本次出价者为同一人时，只需将最高价出价记录的代理竞价更新为本次出价
		int updCnt = ShowBidListCacheUtil.updateProxyAmountById(domainId, dyBidhistory.getId(), newBidAmount);
		if (0 == updCnt) {
			throw new ServiceException("更新代理竞价出错");
		} else {
			//代理竞价成功，重新冻结保证金
			if(oldDeposit.longValue() != newDeposit.longValue()){
				// 保证金是按区间取的，不同的代理竞价保证金有可能相等，相等时无序操作
				// 冻结当前保证金，解冻上次出价保证金
				if (!this.depositControl(
						dyFinance.getId(),
						dyFinance.getUpdateDate(),
						newDeposit,
						dyFinance.getId(),
						dyFinance.getUpdateDate(),
						oldDeposit)) {
					throw new ServiceException("冻结保证金操作失败");
				}
				
				//将新的保证金更新到上次保证金记录
				oldDepositCashFlow.setOperateAmount(newDeposit);
				oldDepositCashFlow.setIsNewRecord(false);
				cashFlowService.save(oldDepositCashFlow);
				
				logger.debug("[cashLog]会员id:"+clientId+"会员资金流id:"+oldDepositCashFlow.getId()+"代理竞价,冻结："+newDeposit+"冻结总额："+(dyFinance.getFreezeBalance()+oldDeposit));
				logger.debug("[cashLog]会员id:"+clientId+"会员资金流id:"+oldDepositCashFlow.getId()+"代理竞价,解冻："+oldDeposit+"冻结总额："+dyFinance.getFreezeBalance());
			}
			return AjaxResult.makeSuccess("您当前出价为" + dyBidhistory.getBidAmount().toString() + "，代理出价为" + newBidAmount.toString());
		}
	}
	
	/**
	 * 自己不是最高出价者,且有代理竞价的场合，出价处理
	 * @param dyBidhistory 最高出价记录
	 * @param newBidAmount 当前新出价价格
	 * @param dyFinance 当前出价者财务实体
	 * @param domainId 被出价域名id
	 * @param currClient 当前出价者实体
	 * @param broker 当前会员的经纪人
	 * @param unFreezeFinance 最高出价者财务实体
	 * @return
	 */
	public AjaxResult NotTheHighestBidWithProxy(DyBidhistory dyBidhistory,Long newBidAmount,DyFinance dyFinance,DyFinance unFreezeFinance,User broker,String domainId,DyClient currClient){
		// 该域名有代理竞价的场合
		String clientId           = currClient.getId();//当前出价者的id
		String proxyClientId  = dyBidhistory.getClientId();// 代理竞价人id
		Long proxyMaxAmount = dyBidhistory.getProxyAmount();//代理竞价上限金额
		Long unfreezeDeposit = 0L;// 释放保证金金额:出价将被超者的保证金
		Long increment        = 0L; // 加价幅度
		
		AjaxResult ar = AjaxResult.makeSuccess("出价成功");
		
		User proxyBroker = UserUtils.get(dyClientDao.get(proxyClientId).getBrokerId()); // 代理竞价会员经纪人
//		DyFinance proxyFinance = new DyFinance(); // 代理竞价会员财务信息
//		proxyFinance.setClientId(proxyClientId);
//		proxyFinance.setDelFlag(DyFinance.DEL_FLAG_NORMAL);
//		proxyFinance = dyFinanceDao.findList(proxyFinance).get(0);

		if (newBidAmount.longValue() > proxyMaxAmount.longValue()) {// 如果当前出价金额大于代理竞价上限金额
			// 将当前代理竞价上限金额进行出价
			this.doBid(domainId, proxyClientId, proxyMaxAmount, BID_TYPE_PROXY, proxyBroker, proxyMaxAmount);
			/**释放保证金不通过计算得到，通过资金流记录获取
			unfreezeDeposit = DySysUtils.calculateDepositAndIncrement(proxyMaxAmount, BID_RULE_TYPE_DEPOSIT);
			**/
			//被超者冻结保证金的资金流记录
			DyCashFlow secondCashFlow = new DyCashFlow();
			secondCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
			secondCashFlow.setClientId(proxyClientId);
			secondCashFlow.setDomainnameId(domainId);
			secondCashFlow.setDelFlag(DEL_FLAG_0);
			List<DyCashFlow> secondList = cashFlowService.findList(secondCashFlow);
			if(secondList.isEmpty()){
				throw new ServiceException("被超者出价保证金，无");
			}else {
				secondCashFlow = secondList.get(0);
				unfreezeDeposit = secondCashFlow.getOperateAmount();
			}
			
			// 更新最新消息计数
			dyNewsDao.updateNewBidCount(domainId, proxyClientId);

			// 根据当前代理竞价上限金额计算本次出价加价幅度
			increment = DySysUtils.calculateDepositAndIncrement(proxyMaxAmount, BID_RULE_TYPE_INCREMENT);

			//计算冻结保证金及出价操作
			Long freezeDeposit = 0L;// 冻结保证金金额
			if(Constant.SWITCH_OFF.equals(currClient.getAvoidDeposit())){
				//当前用户不可以免除保证金
				freezeDeposit = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_DEPOSIT);
			}
			if ((newBidAmount.longValue() - proxyMaxAmount.longValue()) <= increment.longValue()) {// 当前出价金额 - 代理竞价上限金额 == 加价幅度的场合  ：普通出价   小于的情况不应该出现
				this.doBid(domainId, clientId, newBidAmount, BID_TYPE_NORMAL, broker, null);
			} else {
				// 当前出价金额 - 代理竞价上限金额 > 加价幅度的场合 ：代理竞价
				Long bidAmount = proxyMaxAmount.longValue() + increment.longValue();
				this.doBid(domainId, clientId, bidAmount, BID_TYPE_PROXY, broker, newBidAmount);
				ar = AjaxResult.makeSuccess("您当前出价为" + bidAmount.toString() + "，代理出价为" + newBidAmount.toString());
			}

			// 更新最新消息计数
			dyNewsDao.updateNewBidCount(domainId, clientId);

			// 冻结当前出价者的保证金，解冻被超者的保证金
			if (!this.depositControl(
					dyFinance.getId(),
					dyFinance.getUpdateDate(),
					freezeDeposit,
					unFreezeFinance.getId(),
					unFreezeFinance.getUpdateDate(),
					unfreezeDeposit)) {
				throw new ServiceException("冻结保证金操作失败");
			}
			
			//解冻被超者的保证金记录插入资金流
			secondCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
			secondCashFlow.setIsNewRecord(false);
			cashFlowService.save(secondCashFlow);
			
			//冻结当前出价者的保证金记录插入资金流
			DyCashFlow firstCashFlow = new DyCashFlow();
			firstCashFlow.setClientId(clientId);
			firstCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
			firstCashFlow.setOperateAmount(freezeDeposit);
			firstCashFlow.setOperateTime(new Date());
			firstCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
			firstCashFlow.setDomainnameId(domainId);
			firstCashFlow.preInsert(UserUtils.get(currClient.getBrokerId()));
			//设置该实体为新记录
			firstCashFlow.setIsNewRecord(true);
			cashFlowService.save(firstCashFlow);
			
			logger.debug("[cashLog]会员id:"+clientId+"会员资金流id:"+firstCashFlow.getId()+"出价,冻结："+freezeDeposit+"冻结总额："+dyFinance.getFreezeBalance());
			logger.debug("[cashLog]会员id:"+secondCashFlow.getClientId()+"会员资金流id:"+secondCashFlow.getId()+"出价被超,解冻："+unfreezeDeposit+"冻结总额："+(unFreezeFinance.getFreezeBalance()));
		} else {
			// 如果当前出价金额小于或者等于代理竞价上限金额
			//当前用户出价
			this.doBid(domainId, clientId, newBidAmount, BID_TYPE_NORMAL, broker, null);
			// 更新最新消息计数
			dyNewsDao.updateNewBidCount(domainId, clientId);

			// 代理竞价者进行代理竞价
			// 根据本次出价计算加价幅度
			increment = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_INCREMENT);
			
			if (increment.longValue() >= proxyMaxAmount.longValue() - newBidAmount.longValue()) {// 如果当前出价金额等于代理竞价上限金额，或者与代理竞价只差一个加价幅度
				this.doBid(domainId, proxyClientId, proxyMaxAmount, BID_TYPE_PROXY, proxyBroker, proxyMaxAmount);
			} else {// 如果当前出价金额小于代理竞价上限金额且相差至少两个加价幅度
				this.doBid(domainId, proxyClientId, newBidAmount.longValue() + increment.longValue(), BID_TYPE_PROXY, proxyBroker, proxyMaxAmount);
			}

			ar = AjaxResult.makeSuccess("出价被超出");
			
			// 更新最新消息计数
			dyNewsDao.updateNewBidCount(domainId, proxyClientId);
		}
		return ar;
	}
	/**
	 * 自己不是最高出价者,且无代理竞价的场合，出价处理
	 * @param dyBidhistory 最高出价记录
	 * @param newBidAmount 当前新出价价格
	 * @param dyFinance 当前出价者财务实体
	 * @param domainId 被出价域名id
	 * @param currClient 当前出价者实体
	 * @param broker 当前会员的经纪人
	 * @param unFreezeFinance 最高出价者财务实体
	 * @return
	 */
	public AjaxResult NotTheHighestBidWithoutProxy(DyBidhistory dyBidhistory,Long newBidAmount,DyFinance dyFinance,DyFinance unFreezeFinance,User broker,String domainId,DyClient currClient){
		// 该域名无代理竞价的场合
		AjaxResult ar = AjaxResult.makeSuccess("出价成功");
		
		Long increment        = 0L; // 加价幅度
		String clientId           = currClient.getId();//当前出价者的id
		Long dbBidAmount     = 0L; // 当前最高出价金额
		if (null != dyBidhistory) {
			//有人出过价
			dbBidAmount    = dyBidhistory.getBidAmount();
		}
		// 根据当前最高出价计算加价幅度
		increment = DySysUtils.calculateDepositAndIncrement(dbBidAmount, BID_RULE_TYPE_INCREMENT);

		//计算冻结保证金及出价操作
		Long freezeDeposit = 0L;// 冻结保证金金额
		if(Constant.SWITCH_OFF.equals(currClient.getAvoidDeposit())){
			//当前用户不可以免除保证金
			freezeDeposit = DySysUtils.calculateDepositAndIncrement(newBidAmount, BID_RULE_TYPE_DEPOSIT);
		}
		if (newBidAmount.longValue() - dbBidAmount.longValue() <= increment.longValue()) {//出价金额刚好一个加价幅度,小于的情况不应该出现
			// 当前出价金额 - 出价金额 <= 加价幅度的场合  普通出价
			this.doBid(domainId, clientId, newBidAmount, BID_TYPE_NORMAL, broker, null);
		} else {
			// 当前出价金额 - 出价金额 > 加价幅度的场合  代理竞价
			this.doBid(domainId, clientId, dbBidAmount.longValue() + increment.longValue(), BID_TYPE_PROXY, broker, newBidAmount);
			ar = AjaxResult.makeSuccess("您当前出价为" + (dbBidAmount.longValue() + increment.longValue()) + "，代理出价为" + newBidAmount.toString());
		}
		//构造冻结当前出价者的保证金记录
		DyCashFlow firstCashFlow = new DyCashFlow();
		firstCashFlow.setClientId(clientId);
		firstCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		firstCashFlow.setOperateAmount(freezeDeposit);
		firstCashFlow.setOperateTime(new Date());
		firstCashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
		firstCashFlow.setDomainnameId(domainId);
		firstCashFlow.preInsert(UserUtils.get(currClient.getBrokerId()));
		//设置该实体为新记录
		firstCashFlow.setIsNewRecord(true);
		
		Long unfreezeDeposit = 0L;// 释放保证金金额:出价将被超者的保证金
		if(null != dyBidhistory){
			/**释放保证金不在通过计算获得，通过资金流记录获取
			unfreezeDeposit = DySysUtils.calculateDepositAndIncrement(dbBidAmount, BID_RULE_TYPE_DEPOSIT);
			**/
			//被超者的保证金冻结记录
			DyCashFlow secondCashFlow = new DyCashFlow();
			secondCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
			secondCashFlow.setClientId(dyBidhistory.getClientId());
			secondCashFlow.setDomainnameId(domainId);
			secondCashFlow.setDelFlag(DEL_FLAG_0);
			List<DyCashFlow> secondList = cashFlowService.findList(secondCashFlow);
			if(secondList.isEmpty()){
				throw new ServiceException("被超者出价保证金，无");
			}else {
				secondCashFlow = secondList.get(0);
				unfreezeDeposit = secondCashFlow.getOperateAmount();
			}
			
			// 冻结当前出价者的保证金，解冻被超者的保证金
			if (!this.depositControl(
					dyFinance.getId(),
					dyFinance.getUpdateDate(),
					freezeDeposit,
					unFreezeFinance.getId(),
					unFreezeFinance.getUpdateDate(),
					unfreezeDeposit)) {
				throw new ServiceException("冻结保证金操作失败");
			}
			//解冻被超者的保证金记录插入资金流
			secondCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
			secondCashFlow.setIsNewRecord(false);
			cashFlowService.save(secondCashFlow);
			
			//冻结当前出价者的保证金记录插入资金流
			cashFlowService.save(firstCashFlow);
			
			logger.debug("[cashLog]会员id:"+clientId+"会员资金流id:"+firstCashFlow.getId()+"出价,冻结："+freezeDeposit+"冻结总额："+dyFinance.getFreezeBalance());
			logger.debug("[cashLog]会员id:"+secondCashFlow.getClientId()+"会员资金流id:"+secondCashFlow.getId()+"出价被超,解冻："+unfreezeDeposit+"冻结总额："+(unFreezeFinance.getFreezeBalance()));
		}else{
			//历史无人对此域名出价，无序解冻被超者保证金
			// 冻结当前出价者的保证金
			if (!this.depositControl(
					dyFinance.getId(),
					dyFinance.getUpdateDate(),
					freezeDeposit,
					null,
					null,
					null)) {
				throw new ServiceException("冻结保证金操作失败");
			}
			//冻结当前出价者的保证金记录插入资金流
			cashFlowService.save(firstCashFlow);
			logger.debug("[cashLog]会员id:"+clientId+"会员资金流id:"+firstCashFlow.getId()+"出价（自己不是最高价，有代理竞价场合）,冻结："+freezeDeposit+"冻结总额："+dyFinance.getFreezeBalance());
		}

		// 更新最新消息计数
		dyNewsDao.updateNewBidCount(domainId, clientId);
		return ar;
	}
		
	// 出价各个分支封装成方法end  wufulin
}