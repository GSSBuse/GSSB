/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.Threads;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.bean.Attachment;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.modules.paimai.bean.TransactionsEntity;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyAccessRecordDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyBidhistoryDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyBonusDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyCashFlowDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyClientDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyDomainnameDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyFinanceDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyLevelSettingDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyNewsDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyPlatformIncomeDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DomainEndNumEntity;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAttention;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBonus;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyNews;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformIncome;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyShareRecord;
import com.thinkgem.jeesite.modules.sys.entity.dy.FollowInfoToMsg;
import com.thinkgem.jeesite.modules.sys.entity.dy.TransactionInformation;
import com.thinkgem.jeesite.modules.sys.utils.NewsUpdateFlagUtil;
import com.thinkgem.jeesite.modules.sys.utils.ShowDomainCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidCashInfo;
import com.thinkgem.jeesite.modules.wx.entity.domainname.PageDomainEntity;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 域名信息管理Service
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Service
@Transactional(readOnly = true)
public class DyDomainnameService extends CrudService<DyDomainnameDao, DyDomainname> implements Constant {
	@Autowired
	private DyFinanceDao dyFinanceDao;
	@Autowired
	private DyCashFlowDao dyCashFlowDao;
	@Autowired
	private DyBidhistoryDao dyBidhistoryDao;
	@Autowired
	private DyNewsDao dyNewsDao;
	@Autowired
	private DyLevelSettingDao dyLevelSettingDao;
	@Autowired
	private DyAccessRecordDao dyAccessRecordDao;
	@Autowired
	private DyClientDao dyClientDao;
	@Autowired
	private DyBonusDao dyBonusDao;
	@Autowired
	private DyPlatformIncomeDao dyPlatformIncomeDao;
	
	@Autowired
	private DyPlatformAccountService dyPlatformAccountService;
	
	@Autowired
	private DyBidhistoryService bidhistoryService;
	
	@Autowired
	private DyNewsService newsService;
	
	@Autowired
	private DyShareRecordService dyShareRecordService;
	
	@Autowired
	private DyCashFlowService cashFlowService;
	
	public Page<DyDomainname> findPageByUserId(Page<DyDomainname> page, DyDomainname dyDomainname, String userId) throws Exception{
		PageDomainEntity pageDomainEntity = new PageDomainEntity();
		BeanUtils.copyProperties(pageDomainEntity, dyDomainname);
		pageDomainEntity.setPage(page);
		pageDomainEntity.setClient(dyDomainname.getDyClient() == null ? new DyClient() : dyDomainname.getDyClient());
		pageDomainEntity.setCurrentSysUserId(userId);
//		dyDomainname.setPage(page);
		page.setList(dao.findPageByUserId(pageDomainEntity));
		return page;
	}
	public DyDomainname get(String id) {
		return super.get(id);
	}
	
	public List<DyDomainname> findList(DyDomainname dyDomainname) {
		return super.findList(dyDomainname);
	}
	
	public Page<DyDomainname> findPage(Page<DyDomainname> page, DyDomainname dyDomainname) {
		return super.findPage(page, dyDomainname);
	}
	
	@Transactional(readOnly = false)
	public void domainSave(DyDomainname dyDomainname,DyClient client) {
		/*压缩图片*/
		this.computerImpressPicture(dyDomainname);
		super.save(dyDomainname);
	}
	
	@Transactional(readOnly = false)
	public void save(DyDomainname dyDomainname) {
		super.save(dyDomainname);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyDomainname dyDomainname) {
		super.delete(dyDomainname);
	}
	
	@Transactional(readOnly = false)
	public void insert(DyDomainname dyDomainname) {
		dao.insert(dyDomainname);
	}
	
	@Transactional(readOnly = false)
	public void update(DyDomainname dyDomainname) {
		dao.update(dyDomainname);
	}
	
	public List<DyDomainname> findListByClientId(String clientId, String status) {
		return dao.findListByClientId(clientId, status);
	}

	/**
	 * 根据域名ID，会员ID获取拍卖列表数据
	 * @param domainId 域名ID
	 * @param clientId 会员ID
	 * @return 拍卖列表数据
	 */
	public PageDomainEntity getAuctionData(String domainId, String clientId) {
		return dao.getAuctionData(domainId, clientId);
	}

	
	/**
	 * 自定义获取域名页
	 * @param page 分页对象
	 * @param dyDomainname 域名实体
	 * @return 域名页
	 */
	public Page<DyDomainname> customizedFindPage(Page<DyDomainname> page, DyDomainname dyDomainname) {
		dyDomainname.setPage(page);
		page.setList(dao.customizedFindList(dyDomainname));
		return page;
	}
	/**
	 * 根据经纪人ID，获取该经纪人下所有待确认的已付款的域名列表
	 * @param brokerId
	 * @return
	 */
	public Page<BidCashInfo> findConfirmList(Page<BidCashInfo> page, BidCashInfo bidCashInfo){
		bidCashInfo.setPage(page);
		page.setList(dao.findConfirmList(bidCashInfo));
		for(BidCashInfo bid : page.getList()){
			bid.setRebate((long)(DySysUtils.BONUS_PERCENT_PLATFORM * bid.getBidAmount()/100));
		}
		return page;
	}
	/**
	 * 获取未审核的域名数量
	 * @return
	 */
	public int unconfirmCount(){
		return dao.unconfirmCount();
	}
	/**
	 * 根据经纪人ID，获取未审核的域名数量
	 * @return
	 */
	public int unconfirmCountBroker(String brokerID){
		return dao.unconfirmCountBroker(brokerID);
	}
	/**
	 * 获取等待经济人确认交易结束的域名数量
	 * @return
	 */
	public int unfinishCount(){
		return dao.unfinishCount();
	}
	/**
	 * 获取该会员处于（待买家付款，待买家确认，待卖家转移域名）的域名数量
	 * @param clientId 会员id
	 * @return
	 */
	public int clientToDo(String clientId){
		return (dao.clientToSellDo(clientId) + dao.clientToBuyDo(clientId));
	}
	/**
	 * 根据经纪人ID，获取等待经济人确认交易结束的域名数量
	 * @param brokerID
	 * @return
	 */
	public int unfinishCountBroker(String brokerID){
		return dao.unfinishCountBroker(brokerID);
	}
	/**
	 * 获取域名的详细交易信息
	 * @param bidCashInfo
	 * @return
	 */
    public BidCashInfo finddealByDomainId(String domainnaneId){
    	BidCashInfo newBidCashInfo = dao.finddealByDomainId(domainnaneId);
    	if(newBidCashInfo == null){
    		return null;
    	}
    	//平台佣金
    	if(newBidCashInfo.getBidAmount() > 0){
    		newBidCashInfo.setRebate((long)(DySysUtils.BONUS_PERCENT_PLATFORM * newBidCashInfo.getBidAmount()/100));
    	}

		/*设置次高价信息*/
			if(newBidCashInfo.getBonusSecond() > 0 && newBidCashInfo.getBidAmount() > 0){
				/*设置次高价红包*/
			   	 long redBagLimit = (long)(DySysUtils.BONUS_SECOND_LIMIT * newBidCashInfo.getBidAmount()/100); //次高价红包上限
			   	 if(newBidCashInfo.getBonusSecond() > redBagLimit){
			   		newBidCashInfo.setBonusSecond(redBagLimit);
			   	 }
			   	 /*设置次高价获得者信息*/
			   	 List<BidCashInfo> bidhistoryList = dyBidhistoryDao.findSecondHighList(newBidCashInfo.getDomainId());  //出价历史列表
			    if(bidhistoryList.size() == 2){
			    	newBidCashInfo.setSecondClientId(bidhistoryList.get(1).getSecondClientId());
			    	newBidCashInfo.setSecondClientName(bidhistoryList.get(1).getSecondClientName());
			    	newBidCashInfo.setSecondClientNickname(bidhistoryList.get(1).getSecondClientNickname());
			    }
			}
		/*设置分享佣金获得者信息*/
			long shareAmount = 0;
			if(DySysUtils.SHARE_BONUS_ENABLE.equals(SHARE_BONUS_SWITCH_ON)){
				//值为1，红包、分享佣金功能开启了
				shareAmount = (long)(newBidCashInfo.getRebate() * DySysUtils.BONUS_PERCENT_SHARE/100); //分享佣金
				if(shareAmount != 0){
					DyClient shareClient = dyAccessRecordDao.getShareClient(newBidCashInfo.getDomainId(), newBidCashInfo.getBuyClientId());
					if(shareClient != null){
						newBidCashInfo.setShareClientId(shareClient.getId());
						newBidCashInfo.setShareClientName(shareClient.getName());
						newBidCashInfo.setShareClientNickname(shareClient.getNickname());
					}
				}
			}
			newBidCashInfo.setShareRebate(shareAmount);
    	return newBidCashInfo;
    }
	/**
	 * 根据域名ID更新域名状态
	 * @param domainId 域名ID
	 * @return 操作结果
	 */
	@Transactional(readOnly = false)
	public AjaxResult updateDomainStatusById(String domainId) {
		try {
			DyDomainname domain = dao.get(domainId);
			DyBidhistory bidHist = dyBidhistoryDao.getMaxBidAmount(domainId);
			int updCnt = 0;
			if (domain.getReservePrice().longValue() > bidHist.getBidAmount().longValue()) {
				updCnt = dao.updateDomainStatusById(domainId, Constant.DOMAIN_STATUS_23, String.valueOf(0));
			} else {
				updCnt = dao.updateDomainStatusById(domainId, Constant.DOMAIN_STATUS_11, String.valueOf(2));
			}

			if (updCnt == 0) {
				return AjaxResult.makeError("域名状态更新失败");
			}
			return AjaxResult.makeSuccess("");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("域名状态更新失败");
		}
	}
	/**
	 * @return 最近七天交易记录，记录以Map记录：domainnameId（域名id），status（域名状态），name（域名名称），sellClientId（卖家id），sellNickName（卖家昵称），sellDate（交易日期），bidAmount（成交日期），buyClientId（买家id），buyNickName(买家昵称)
	 */
	public List<Map<String , Object>> getHistoryInfo(){
		return dao.getHistoryInfo();
	}
	/**
	 * 交易结束，更新买家账户、买家账户，插入买家和买家的资金流信息
	 * @param bidCashInfo
	 */
	@Transactional(readOnly = false)
	public boolean updateFinance(BidCashInfo bidCashInfo, DyDomainname dyDomainname)throws ServiceException,Exception{
		try{
			//买家实际的冻结保证金：成交款
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
			/**买家账户扣除成交价，解冻冻结金额*/
			//更新买家账户
			DyFinance financeTemp = new DyFinance();
			financeTemp.setClientId(bidCashInfo.getBuyClientId());
			DyFinance buyFinance = dyFinanceDao.get(financeTemp);
			long acountBalance = buyFinance.getAccountBalance() - bidCashInfo.getBidAmount();	//账户总额
			long freezeBalance = buyFinance.getFreezeBalance() - realDeposit;	//冻结金额
			buyFinance.setAccountBalance(acountBalance);
			buyFinance.setFreezeBalance(freezeBalance);
			if(dyFinanceDao.updateFinance(buyFinance)<=0){
				throw new ServiceException("更新买家账户失败");
			}
			
			/*解冻买家资金流信息*/
			freeDepositDyCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
			cashFlowService.save(freeDepositDyCashFlow);
			/**
			DyCashFlow freeDepositDyCashFlow = new DyCashFlow();
			freeDepositDyCashFlow.setClientId(bidCashInfo.getBuyClientId());
			freeDepositDyCashFlow.setDomainnameId(bidCashInfo.getDomainId());
			freeDepositDyCashFlow.setOperateAmount(realDeposit);
			if(dyCashFlowDao.updateFreeCashFlow(freeDepositDyCashFlow) == 0){
				throw new ServiceException("解冻买家资金流失败");
			}
			 **/
			
			/*插入买家资金流*/
			DyCashFlow buyDyCashFlow = new DyCashFlow();
			buyDyCashFlow.setClientId(bidCashInfo.getBuyClientId());
			buyDyCashFlow.setDomainnameId(dyDomainname.getId());
			buyDyCashFlow.setOperate(Constant.CASHFLOW_OPERATE_PAY);
			buyDyCashFlow.setOperateAmount(bidCashInfo.getBidAmount());
			buyDyCashFlow.setOperateTime(new Date());
			buyDyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
			buyDyCashFlow.setAmountBalance(buyFinance.getAccountBalance());
			buyDyCashFlow.preInsert();
			if(dyCashFlowDao.insert(buyDyCashFlow) == 0){
				throw new ServiceException("更新账户失败");
			}
				
			/**发送次高价红包，此费用从成交价里面扣（由卖家承担）*/
			long getBalance = 0;	//卖家收款金额
			if(bidCashInfo.getBonusSecond() <= 0 || StringUtils.isBlank(bidCashInfo.getSecondClientId())){
				getBalance = bidCashInfo.getBidAmount() - bidCashInfo.getRebate();
			}else{
				getBalance = bidCashInfo.getBidAmount() - bidCashInfo.getRebate() - bidCashInfo.getBonusSecond(); //收款金额
				 /*更新次高价会员账户信息*/
		    	 DyFinance financeTemp2 = new DyFinance();
		    	 financeTemp2.setClientId(bidCashInfo.getSecondClientId());
		    	 DyFinance redBagFinance = dyFinanceDao.get(financeTemp2);	//次高价账户
		    	 redBagFinance.setAccountBalance(redBagFinance.getAccountBalance() + bidCashInfo.getBonusSecond());
		    	 if(dyFinanceDao.updateFinance(redBagFinance)<=0){
						throw new ServiceException("发送次高价红包失败！");
					}
		    	 /*插入次高价红包资金流*/
					DyCashFlow secondDyCashFlow = new DyCashFlow();
					secondDyCashFlow.setClientId(redBagFinance.getClientId());
					secondDyCashFlow.setDomainnameId(dyDomainname.getId());
					secondDyCashFlow.setOperate(Constant.CASHFLOW_OPERATE_REBBAG_IN);
					secondDyCashFlow.setOperateAmount(bidCashInfo.getBonusSecond());
					secondDyCashFlow.setOperateTime(new Date());
					secondDyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
					secondDyCashFlow.setAmountBalance(redBagFinance.getAccountBalance());
					secondDyCashFlow.preInsert();
					if(dyCashFlowDao.insert(secondDyCashFlow) == 0){
						throw new ServiceException("插入次高价红包资金流失败");
					}
				 /*插入红包表*/
					DyBonus secondDyBonus = new DyBonus();
					secondDyBonus.setDomainnameId(bidCashInfo.getDomainId());
					secondDyBonus.setClientId(secondDyCashFlow.getClientId());
					secondDyBonus.setMoney(secondDyCashFlow.getOperateAmount());
					secondDyBonus.setType(Constant.BONUS_TYPE_02);
					secondDyBonus.preInsert();
					if(dyBonusDao.insert(secondDyBonus) == 0){
						throw new ServiceException("插入红包表失败");
					}
			}
				
			/**从成交价里面扣除平台佣金、次高价红包，剩下的归卖家*/
			//卖家的冻结保证金
			Long sellDeposit = null;
			DyCashFlow sellerDyCashFlow = new DyCashFlow();
			sellerDyCashFlow.setClientId(bidCashInfo.getSellClientId());
			sellerDyCashFlow.setDomainnameId(bidCashInfo.getDomainId());
			sellerDyCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
			List<DyCashFlow> sellerDyCashFlowList = dyCashFlowDao.findList(sellerDyCashFlow);
			if(sellerDyCashFlowList.isEmpty()){
				throw new ServiceException("无卖家冻结记录");
			}else{
				sellerDyCashFlow = sellerDyCashFlowList.get(0);
				sellDeposit = sellerDyCashFlow.getOperateAmount();
			}
			
			//更新卖家账户
			DyFinance financeTemp1 = new DyFinance();
			financeTemp1.setClientId(bidCashInfo.getSellClientId());
			DyFinance sellFinance = dyFinanceDao.get(financeTemp1);
			long sellAcountBalance = sellFinance.getAccountBalance() + getBalance;	//账户金额
			long sellFreezeBalance = sellFinance.getFreezeBalance() - sellDeposit;	//保留金
			sellFinance.setAccountBalance(sellAcountBalance);
			sellFinance.setFreezeBalance(sellFreezeBalance);
			if(dyFinanceDao.updateFinance(sellFinance)<=0){
				throw new ServiceException("更新卖家账户失败");
			}
			
			/*解冻卖家资金流信息*/
			/**
			if(DySysUtils.SELL_DEPOSIT > 0){
				DyCashFlow freeSellDyCashFlow = new DyCashFlow();
				freeSellDyCashFlow.setClientId(bidCashInfo.getSellClientId());
				freeSellDyCashFlow.setDomainnameId(bidCashInfo.getDomainId());
				freeSellDyCashFlow.setOperateAmount(DySysUtils.SELL_DEPOSIT);
				if(dyCashFlowDao.updateFreeCashFlow(freeSellDyCashFlow) == 0){
					throw new ServiceException("解冻卖家资金流失败");
				}
			}
			 **/
			sellerDyCashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
			cashFlowService.save(sellerDyCashFlow);
			
			/*插入卖家资金流*/
			DyCashFlow sellDyCashFlow = new DyCashFlow();
			sellDyCashFlow.setClientId(bidCashInfo.getSellClientId());
			sellDyCashFlow.setDomainnameId(dyDomainname.getId());
			sellDyCashFlow.setOperate(Constant.CASHFLOW_OPERATE_GET);
			sellDyCashFlow.setOperateAmount(getBalance);
			sellDyCashFlow.setOperateTime(new Date());
			sellDyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
			sellDyCashFlow.setAmountBalance(sellFinance.getAccountBalance());
			sellDyCashFlow.preInsert();
			if(dyCashFlowDao.insert(sellDyCashFlow) == 0){
				throw new ServiceException("插入卖家资金流失败");
			}
		
			if(DySysUtils.SHARE_BONUS_ENABLE.equals(SHARE_BONUS_SWITCH_ON)){
				//值为1，红包功能开启了
				/**发送分享者佣金(从平台佣金里面扣除)*/
				if(StringUtils.isNotBlank(bidCashInfo.getShareClientId()) && bidCashInfo.getShareRebate() > 0){
					DyFinance financeTemp3 = new DyFinance();
					financeTemp3.setClientId(bidCashInfo.getShareClientId());
					DyFinance shareFinance = dyFinanceDao.get(financeTemp3);
					shareFinance.setAccountBalance(shareFinance.getAccountBalance() + bidCashInfo.getShareRebate());
					if(dyFinanceDao.updateFinance(shareFinance)<=0){
						throw new ServiceException("发送分享者佣金失败");
					}
					/*插入分享者资金流*/
					DyCashFlow shareDyCashFlow = new DyCashFlow();
					shareDyCashFlow.setClientId(bidCashInfo.getShareClientId());
					shareDyCashFlow.setDomainnameId(dyDomainname.getId());
					shareDyCashFlow.setOperate(Constant.CASHFLOW_OPERATE_SHARE_IN);
					shareDyCashFlow.setOperateAmount(bidCashInfo.getShareRebate());
					shareDyCashFlow.setOperateTime(new Date());
					shareDyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
					shareDyCashFlow.setAmountBalance(shareFinance.getAccountBalance());
					shareDyCashFlow.preInsert();
					if(dyCashFlowDao.insert(shareDyCashFlow) == 0){
						throw new ServiceException("更新分享者账户失败");
					}
					/*插入红包表*/
					DyBonus shareDyBonus = new DyBonus();
					shareDyBonus.setDomainnameId(bidCashInfo.getDomainId());
					shareDyBonus.setClientId(shareDyCashFlow.getClientId());
					shareDyBonus.setMoney(shareDyCashFlow.getOperateAmount());
					shareDyBonus.setType(Constant.BONUS_TYPE_03);
					shareDyBonus.preInsert();
					if(dyBonusDao.insert(shareDyBonus) == 0){
						throw new ServiceException("插入红包表失败");
					}
				}
				/**更新平台账户（平台佣金 - 分享佣金）*/
				if(bidCashInfo.getRebate() - bidCashInfo.getShareRebate() > 0){
					Long totalIncome = dyPlatformAccountService.updateIncomeAccount(bidCashInfo.getRebate() - bidCashInfo.getShareRebate());	
					if(totalIncome == null){
						throw new ServiceException("更新平台总账户失败");
					}
					/*插入平台收支记录*/
					DyPlatformIncome dyPlatformIncome = new DyPlatformIncome();
					dyPlatformIncome.setDomainnameId(bidCashInfo.getDomainId());
					dyPlatformIncome.setBuyClientId(bidCashInfo.getBuyClientId());
					dyPlatformIncome.setOperate(Constant.PLATFORM_INCOME_OPERATE_IN);
					dyPlatformIncome.setOperateAmount(bidCashInfo.getRebate() - bidCashInfo.getShareRebate());
					dyPlatformIncome.setTotalAmount(totalIncome);
					dyPlatformIncome.preInsert();
					if(dyPlatformIncomeDao.insert(dyPlatformIncome) == 0){
						throw new ServiceException("插入平台收支信息失败");
					}
				}
			}else{
				//红包功能关闭
				/**更新平台账户（平台佣金 - 分享佣金）*/
				if(bidCashInfo.getRebate() > 0){
					Long totalIncome = dyPlatformAccountService.updateIncomeAccount(bidCashInfo.getRebate() - bidCashInfo.getShareRebate());	
					if(totalIncome == null){
						throw new ServiceException("更新平台总账户失败");
					}
					/*插入平台收支记录*/
					DyPlatformIncome dyPlatformIncome = new DyPlatformIncome();
					dyPlatformIncome.setDomainnameId(bidCashInfo.getDomainId());
					dyPlatformIncome.setBuyClientId(bidCashInfo.getBuyClientId());
					dyPlatformIncome.setOperate(Constant.PLATFORM_INCOME_OPERATE_IN);
					dyPlatformIncome.setOperateAmount(bidCashInfo.getRebate() - bidCashInfo.getShareRebate());
					dyPlatformIncome.setTotalAmount(totalIncome);
					dyPlatformIncome.preInsert();
					if(dyPlatformIncomeDao.insert(dyPlatformIncome) == 0){
						throw new ServiceException("插入平台收支信息失败");
					}
				}
			}
			
				
			/**更新域名信息*/
			if(dao.update(dyDomainname) == 0){
				throw new ServiceException("更新账户失败");
			}

			//给买家发送微信消息
			Message message = new Message();
			String title = DySysUtils.TEMPLATE_TITLE_0017;
			String content = DySysUtils.TEMPLATE_MESSAGE_0017;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			content = content.replace("{{seller.DATA}}", bidCashInfo.getSellNickname());
			content = content.replace("{{price.DATA}}", Long.toString(bidCashInfo.getBidAmount()));
			message.SendNews(WeChat.getAccessToken(), bidCashInfo.getBuyOpenId(), title, content, dyDomainname.getId());
			//给次高价红包获得者发送微信消息
			String redBagGetterNickName = "无";
			if (bidCashInfo.getBonusSecond() > 0 && StringUtils.isNotBlank(bidCashInfo.getSecondClientId())) {
				// 次高价红包获得者
				DyClient redBagGetter = dyClientDao.get(bidCashInfo.getSecondClientId());
				redBagGetterNickName = redBagGetter.getNickname();
				title = DySysUtils.TEMPLATE_TITLE_0019;
				content = DySysUtils.TEMPLATE_MESSAGE_0019;
				content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
				content = content.replace("{{price.DATA}}", Long.toString(bidCashInfo.getBidAmount()));
				content = content.replace("{{bonus.DATA}}", Long.toString(bidCashInfo.getBonusSecond()));
				message.SendNews(WeChat.getAccessToken(), redBagGetter.getOpenid(), title, content, dyDomainname.getId());
			}
			if(DySysUtils.SHARE_BONUS_ENABLE.equals(SHARE_BONUS_SWITCH_ON)){
				//值为1，红包功能开启了
				//给分享佣金获得者发送微信消息
				if(StringUtils.isNotBlank(bidCashInfo.getShareClientId()) && bidCashInfo.getShareRebate() > 0){
					DyClient shareClient = dyClientDao.get(bidCashInfo.getShareClientId());
					title = DySysUtils.TEMPLATE_TITLE_0020;
					content = DySysUtils.TEMPLATE_MESSAGE_0020;
					content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
					content = content.replace("{{price.DATA}}", Long.toString(bidCashInfo.getBidAmount()));
					content = content.replace("{{bonus.DATA}}", Long.toString(bidCashInfo.getShareRebate()));
					message.SendNews(WeChat.getAccessToken(), shareClient.getOpenid(), title, content, dyDomainname.getId());
				}
			}
			//给卖家发送微信消息
			title = DySysUtils.TEMPLATE_TITLE_0018;
			content = DySysUtils.TEMPLATE_MESSAGE_0018;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			content = content.replace("{{buyer.DATA}}", bidCashInfo.getBuyNickname());
			content = content.replace("{{price.DATA}}", Long.toString(bidCashInfo.getBidAmount()));
			content = content.replace("{{bonus.DATA}}", Long.toString(bidCashInfo.getBonusSecond()));
			content = content.replace("{{bonusgetter.DATA}}", redBagGetterNickName);
			content = content.replace("{{platformbonus.DATA}}", Long.toString(bidCashInfo.getRebate()));
			content = content.replace("{{amount.DATA}}", Long.toString(getBalance));
			message.SendNews(WeChat.getAccessToken(), bidCashInfo.getSellOpenId(), title, content, dyDomainname.getId());
		}catch(ServiceException se){
			// Service层公用的Exception抛出时，触发回滚
			throw se;
		}catch(Exception e){
			throw e;
		}	
		return true;
	}

	/**
	 * 将审核通过的域名并且结拍时间不大于当前时间的域名状态更新为待买家付款
	 * @param domainStatus03 审核通过
	 * @param domainStatus11 待买家付款
	 */
	@Transactional(readOnly = false)
	public void updateStatusIfDomainEndAuction() {
		dao.updateStatusIfDomainEndAuction(Constant.DOMAIN_STATUS_03, Constant.DOMAIN_STATUS_11, DySysUtils.OPERATE_LIMIT_TIME_PAY);
	}
	/**
	 * 审核确认操作(检查交易时间和账户余额)
	 * @param setDate  交易的日期（精确到天）
	 * @return
	 * @throws ParseException
	 */
	@Transactional(readOnly = false)
	public int checkDealNums(DyDomainname dyDomainname) throws ParseException{
		/*计算交易结束的开始时间*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String oldDateString = sdf.format(dyDomainname.getEndTime());
		Date dayStart = sdf.parse(oldDateString.split(" ")[0] + " " + DySysUtils.DEAL_START_TIME);	//当天的交易开始时间
		Date dayEnd = sdf.parse(oldDateString.split(" ")[0] + " " + DySysUtils.DEAL_END_TIME);	//当天的交易结束时间
		Date dayEndTime = sdf.parse(oldDateString.split(" ")[0] + " " + Constant.DAY_END_TIME);
		long interval = (dayEnd.getTime() - dayStart.getTime())/DySysUtils.DEAL_NUM;	//时间间隔
		int nums = dao.checkDealNums(dayStart,dayEnd);//当天交易的域名数量
//		long maxNums = (dayEndTime.getTime() - dayStart.getTime())/interval;	//当天交易的域名数量上限
		if(nums >= DySysUtils.DEAL_NUM){			//当天交易数量已满
			return 0;
		}
		Date newDate =new Date(dayStart.getTime() + nums*interval);		//交易结束时间
		if(newDate.getTime() < (new Date().getTime() + Constant.DOMAINNAE_ENDDATE_LAST)){	//判断交易日期是否已过时
			return 2;
		}
//		Date newDate; //交易结束的开始时间
//		if(nums == 0){
//			newDate = dayStart;
//		}else{
//			 Date maxDelDate = dao.getMaxDealDate(dayStart, dayEnd);
//			 newDate =new Date(maxDelDate.getTime() + interval);		//设置交易结束的开始时间
//		}
		dyDomainname.setEndTime(newDate);
//		/*冻结保证金和红包总额*/ 已经转移到提交域名时做了
//		long freeze = DySysUtils.SELL_DEPOSIT + dyDomainname.getBonusShareTotal();	//保证金和红包总额
//		DyFinance dyFinanceTemp = new DyFinance();
//		dyFinanceTemp.setClientId(dyDomainname.getClientId());
//		DyFinance dyFinance = dyFinanceDao.get(dyFinanceTemp);
//		if(dyFinance.getAccountBalance() < (freeze + dyFinance.getFreezeBalance())){	//余额不足
//			return 1;
//		}
		//注释原因：审核时域名一定已经扣过保证金相关操作了
//		//检查资金是否充足，欠账情况下不能审核通过
//		DyFinance dyFinanceTemp = new DyFinance();
//		dyFinanceTemp.setClientId(dyDomainname.getClientId());
//		DyFinance dyFinance = dyFinanceDao.get(dyFinanceTemp);
//		if(dyFinance.getAccountBalance() < 0 || (dyFinance.getAccountBalance() - dyFinance.getFreezeBalance()) < 0){	//欠账、可用余额不足
//			return 1;
//		}
		//检查资金冻结扣除情况
		DyClient client = dyClientDao.get(dyDomainname.getClientId());
		Long freeze = null;
		if(Constant.SWITCH_ON.equals(client.getAvoidDeposit())){
			freeze = 0L;//保证金
		}else {
			freeze = DySysUtils.SELL_DEPOSIT;//保证金
		}
		Long bonusShareTotal = dyDomainname.getBonusShareTotal();//红包总额
		
		DyCashFlow cashFlow = new DyCashFlow();
		cashFlow.setClientId(dyDomainname.getClientId());
		cashFlow.setDomainnameId(dyDomainname.getId());
		cashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		cashFlow.setOperateAmount(freeze);
		cashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
		cashFlow.setIsNewRecord(false);
		//检查保证金是否已冻结
		if(cashFlowService.findList(cashFlow).isEmpty()){
			return 1;
		}
		if(bonusShareTotal > 0){
			cashFlow.setOperate(CASHFLOW_OPERATE_REBBAG_OUT);
			cashFlow.setOperateAmount(bonusShareTotal);
			//检查红包金额是否扣除
			if(cashFlowService.findList(cashFlow).isEmpty()){
				return 4;
			}
		}
		return 3;
	}
	/**
	 * 审核确认操作
	 * @param dyDomainname
	 */
	@Transactional(readOnly = false)
	public void updateDomainStatus(DyDomainname dyDomainname){
		//以下注释掉的代码已经转移到提交域名时处理
		/*如果保证金或红包不为0，则更新财务*/
//		if(DySysUtils.SELL_DEPOSIT > 0 || dyDomainname.getBonusShareTotal() > 0){
//			/*更新会员财务表*/
//			DyFinance dyFinanceTemp = new DyFinance();
//			dyFinanceTemp.setClientId(dyDomainname.getClientId());
//			DyFinance dyFinance = dyFinanceDao.get(dyFinanceTemp);
//			dyFinance.setFreezeBalance(dyFinance.getFreezeBalance() + DySysUtils.SELL_DEPOSIT);
//			dyFinance.setAccountBalance(dyFinance.getAccountBalance() - dyDomainname.getBonusShareTotal());
//			if(dyFinanceDao.updateFinance(dyFinance) == 0){			//更新冲突
//				throw new ServiceException("更新会员财务异常");
//			}
//			
//			if(DySysUtils.SELL_DEPOSIT > 0){
//				/*插入资金流冻结保证金信息*/
//				DyCashFlow freeCashFlow = new DyCashFlow();
//				freeCashFlow.setClientId(dyDomainname.getClientId());
//				freeCashFlow.setDomainnameId(dyDomainname.getId());
//				freeCashFlow.setOperate(Constant.CASHFLOW_OPERATE_FREEZE);
//				freeCashFlow.setOperateAmount(DySysUtils.SELL_DEPOSIT);
//				freeCashFlow.setOperateTime(new Date());
//				freeCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
//				freeCashFlow.preInsert();
//				if(dyCashFlowDao.insert(freeCashFlow) == 0){
//					throw new ServiceException("插入资金流异常");
//				}
//			}
//			
//			if(dyDomainname.getBonusShareTotal() > 0){
//				/*插入资金流信息*/
//				DyCashFlow dyCashFlow = new DyCashFlow();
//				dyCashFlow.setClientId(dyDomainname.getClientId());
//				dyCashFlow.setDomainnameId(dyDomainname.getId());
//				dyCashFlow.setOperate(Constant.CASHFLOW_OPERATE_REBBAG_OUT);
//				dyCashFlow.setOperateAmount(dyDomainname.getBonusShareTotal());
//				dyCashFlow.setOperateTime(new Date());
//				dyCashFlow.setAmountBalance(dyFinance.getAccountBalance());
//				dyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
//				dyCashFlow.preInsert();
//				if(dyCashFlowDao.insert(dyCashFlow) == 0){
//					throw new ServiceException("插入资金流异常");
//				}
//			}
//		}

		/*更新域名信息*/
		if(dao.update(dyDomainname) == 0){
			throw new ServiceException("更新域名信息异常");
		}
		/*插入缓存*/
		ShowDomainCacheUtil.syncDomainInfoToCache(dyDomainname);
		
		/*插入最新消息表*/
		DyNews news = new DyNews();
		news.setClientId(dyDomainname.getClientId());
		news.setDomainnameId(dyDomainname.getId());
		news.setNewBidCount("0");
		news.preInsert();
		if(dyNewsDao.insert(news) == 0){
			throw new ServiceException("审核通过时插入最新消息异常");
		}
	}
	
	/**
	 * 审核失败的操作
	 * @param dyDomainname
	 */
	@Transactional(readOnly = false)
	public void verifyToFailure(DyDomainname dyDomainname){
		//保存域名信息
		this.save(dyDomainname);
		
		//解冻保证金
		// 取得会员财务实体
		DyFinance dyFinance = new DyFinance();
		dyFinance.setClientId(dyDomainname.getClientId());
		dyFinance = dyFinanceDao.findList(dyFinance).get(0);
		Long deposit = this.getSellDeposit();//提交域名卖家 保证金
		int n = dyFinanceDao.updateFreezeBalance(dyFinance.getId(), 0L - deposit, dyFinance.getUpdateDate());
		if(n == 0){
			throw new ServiceException();
		}
		
		//将解冻记录更新到资金流
		DyCashFlow cashFlow = new DyCashFlow();
		cashFlow.setClientId(dyDomainname.getClientId());
		cashFlow.setDomainnameId(dyDomainname.getId());
		cashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		cashFlow.setOperateAmount(deposit);
		cashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
		cashFlow.setIsNewRecord(false);
		List<DyCashFlow> list = cashFlowService.findList(cashFlow);
		if(list.isEmpty()){
			throw new ServiceException("无此冻结记录（域名审核时）");
		}else{
			cashFlow = list.get(0);
			cashFlow.setOperate(CASHFLOW_OPERATE_UNFREEZE);
			cashFlowService.save(cashFlow);
		}
		
		//红包扣除退回
		//将红包扣除插入资金流，财务余额扣除
		if(dyDomainname.getBonusShareTotal() > 0 && DySysUtils.SHARE_BONUS_ENABLE.equals(Constant.SHARE_BONUS_SWITCH_ON)){
			//红包归还
			DyFinance dyFinanceTemp = new DyFinance();
			dyFinanceTemp.setClientId(dyDomainname.getClientId());
			dyFinanceTemp = dyFinanceDao.findList(dyFinance).get(0);
			dyFinanceTemp.setAccountBalance(dyFinanceTemp.getAccountBalance() + dyDomainname.getBonusShareTotal());
			if(dyFinanceDao.updateFinance(dyFinanceTemp) == 0){			//更新冲突
				throw new ServiceException("更新会员财务异常");
			}
			
			/*插入资金流信息*/
			DyCashFlow dyCashFlow = new DyCashFlow();
			dyCashFlow.setClientId(dyDomainname.getClientId());
			dyCashFlow.setDomainnameId(dyDomainname.getId());
			dyCashFlow.setOperate(Constant.REDPACK_RETURN);
			dyCashFlow.setOperateAmount(dyDomainname.getBonusShareTotal());
			dyCashFlow.setOperateTime(new Date());
			dyCashFlow.setAmountBalance(dyFinanceTemp.getAccountBalance());
			dyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
			dyCashFlow.preInsert();
			dyCashFlow.setIsNewRecord(true);
			if(dyCashFlowDao.insert(dyCashFlow) == 0){
				throw new ServiceException("插入资金流异常");
			}
		}	
		logger.debug("[cashLog]会员id:"+dyDomainname.getClientId()+"会员资金流id:"+cashFlow.getId()+"审核域名失败解冻："+cashFlow.getOperateAmount()+"冻结总额："+dyFinance.getFreezeBalance());
	}
	/**追加红包操作
	 * @param domainId 对应的域名id
	 * @param total 追加分享红包总额
	 * @param number 追加分享红包的个数
	 * @param second 追加次高价红包的金额
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void updateBonusSetting(String domainId, Long total, Long number, Long second) throws Exception ,ServiceException{
		DyDomainname domain = get(domainId);
		DyClient client = DySysUtils.getCurrentDyClient();
		// 扣除追加红包金额，更新财务表
		DyFinance finance = new DyFinance();
		finance.setClientId(client.getId());
		finance = dyFinanceDao.findList(finance).get(0);		
		if((finance.getAccountBalance() - finance.getFreezeBalance()) > total){//可用余额充足
			finance.setAccountBalance(finance.getAccountBalance() - total);
			int n = dyFinanceDao.updateFinance(finance);
			if(n == 0){
				throw new ServiceException("更新财务异常");
			}
		}else{//可用余额不足
			throw new ServiceException("余额不足");
		}
		
		// 插入资金流表一条记录
		if (total > 0) {
			DyCashFlow cashFlow = new DyCashFlow();
			cashFlow.setClientId(client.getId());
			cashFlow.setDomainnameId(domainId);
			cashFlow.setOperate(Constant.CASHFLOW_OPERATE_REBBAG_OUT);
			cashFlow.setOperateAmount(total);
			cashFlow.setAmountBalance(finance.getAccountBalance());
			cashFlow.setOperateTime(new Date());
			cashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
			cashFlow.preInsert(UserUtils.get(client.getBrokerId()));
			// 设置该实体为新记录
			cashFlow.setIsNewRecord(true);
			if(dyCashFlowDao.insert(cashFlow) == 0){
				throw new ServiceException("更新资金流异常");
			}
		}
		
		// 更新红包设定
		domain.setBonusShareTotal(total+domain.getBonusShareTotal());
		domain.setBonusShareNumber(number+domain.getBonusShareNumber());
		domain.setBonusSecond(second+domain.getBonusSecond());
		if (dao.update(domain) == 0) {
			throw new ServiceException("更新域名异常");
		}
		
		//发通知  用微信客服接口发送消息给卖家提示已经追加红包成功
		Message message = new Message();
		String title = DySysUtils.TEMPLATE_TITLE_0026;
		String content = DySysUtils.TEMPLATE_MESSAGE_0026;
		content = content.replace("{{domainname.DATA}}", domain.getName());
		content = content.replace("{{bonusShareTotal.DATA}}", total.toString());
		content = content.replace("{{bonusShareNumber.DATA}}", number.toString());
		content = content.replace("{{bonusSecond.DATA}}", second.toString());
		message.SendNews(WeChat.getAccessToken(), client.getOpenid(), title, content, domain.getId());
	}
	
	
	/**
	 * @param currClient 当前登录会员
	 * @param domainnameId 域名id
	 * @param bidAmount 成交价
	 * @return Date操作截止时间
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public Date transferDomainname(DyClient currClient,String domainnameId) throws Exception , ServiceException{
		//更改域名
		DyDomainname dyDomainname = dao.get(domainnameId);
		//检查是否需要转移域名，防止恶意攻击
		if(dyDomainname == null || !dyDomainname.getStatus().equals(Constant.DOMAIN_STATUS_12)){
			throw new Exception();
		}
		dyDomainname.setStatus(Constant.DOMAIN_STATUS_13);
		// 设置操作限制时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, DySysUtils.OPERATE_LIMIT_TIME_RECEIVE);// 把日期从当前往后增加.整数往后推,负数往前移动
		dyDomainname.setWaitTime(cal.getTime());
		// 保存
		int n = dao.update(dyDomainname);
		if(n==0){
			throw new ServiceException();
		}
		
		// 获取买家id
		DyBidhistory bidhistory = dyBidhistoryDao.getSuccessfulBidder(domainnameId);//获取得标信息实体
		if(bidhistory == null){
			throw new ServiceException("转移域名时获取得标者出价信息错误");
		}
		String buyClientId = bidhistory.getClientId();
		// 获取买家openId
		String openId = dyClientDao.get(buyClientId).getOpenid();
		// 用微信客服接口发送消息给买家提示卖家已经转移域名
		Message message = new Message();
		String title = DySysUtils.TEMPLATE_TITLE_0012;
		String content = DySysUtils.TEMPLATE_MESSAGE_0012;
		content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
		content = content.replace("{{seller.DATA}}", currClient.getNickname());
		content = content.replace("{{waittime.DATA}}", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
		message.SendNews(WeChat.getAccessToken(), openId, title, content, dyDomainname.getId());
		
		return cal.getTime();
	}
	
	/**
	 * @param domainnameId 域名id
	 * @param bidAmount 成交价
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public Date receiveDomamainname(String domainnameId,String bidAmount) throws Exception,ServiceException{
		//更改域名状态
		DyDomainname dyDomainname = dao.get(domainnameId);
		//检查是否需要确认收到域名，防止恶意攻击
		if(dyDomainname == null || !dyDomainname.getStatus().equals(Constant.DOMAIN_STATUS_13)){
			throw new ServiceException();
		}
		dyDomainname.setStatus(Constant.DOMAIN_STATUS_14);
		// 设置操作限制时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, DySysUtils.OPERATE_LIMIT_TIME_CONFIRM);// 把日期从当前往后增加.整数往后推,负数往前移动
		dyDomainname.setWaitTime(cal.getTime());
		// 保存
		int n = dao.update(dyDomainname);
		if(n==0){
			throw new ServiceException();
		}
		
		// 用微信客服接口发送消息给卖家提示买家已经确认收到域名
		Message message = new Message();
		// 获取卖家openId
		String openId = dyClientDao.get(dyDomainname.getClientId()).getOpenid();
		String title = DySysUtils.TEMPLATE_TITLE_0016;
		String content = DySysUtils.TEMPLATE_MESSAGE_0016;
		content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
		content = content.replace("{{buyer.DATA}}", DySysUtils.getCurrentDyClient().getNickname());
		content = content.replace("{{price.DATA}}", bidAmount);
		message.SendNews(WeChat.getAccessToken(), openId, title, content, dyDomainname.getId());
		
		return cal.getTime();
	}
	
	/**根据域名id统计该域名的红包发放情况，如果剩余则还给卖家，更新财务信息，写进资金流表
	 * @param dyDomainnameId 域名id
	 * @throws Exception
	 */
	public void countRedPack (String dyDomainnameId)throws Exception,ServiceException{
		//根据域名id 获取域名实体
		DyDomainname dyDomainname = dao.get(dyDomainnameId);
		//计算域名所发红包是否剩余，剩余则还给卖家
		Long bonusShareTotal = dyDomainname.getBonusShareTotal();//获取域名红包总额
		if(!StringUtils.isBlank(bonusShareTotal.toString()) && bonusShareTotal!=0){
			//获取已发红包记录
			DyShareRecord dyShareRecord = new DyShareRecord();
			dyShareRecord.setDomainnameId(dyDomainname.getId());
			List<DyShareRecord> list = dyShareRecordService.findList(dyShareRecord);
			//统计发出红包的总额
			Long totalRedBag = Long.parseLong("0");
			for (DyShareRecord entity : list) {
				totalRedBag += entity.getRedbagAmount();
			}
			totalRedBag = totalRedBag/100;
			
			//如果红包所发总金额小于域名红包总额，则把剩余的还给卖家
			if(totalRedBag<bonusShareTotal){
				//获取卖家财务信息，把剩余的红包钱还给卖家
				String sellerId = dyDomainname.getClientId();
				Long returnMoney = bonusShareTotal-totalRedBag;
				DyFinance dyFinance = new DyFinance();
				dyFinance.setClientId(sellerId);
				dyFinance = dyFinanceDao.findList(dyFinance).get(0);
				dyFinance.setAccountBalance(dyFinance.getAccountBalance()+returnMoney);//剩余的红包钱还给卖家
				int n = dyFinanceDao.updateFinance(dyFinance);
				if(n==0){
					throw new ServiceException();
				}
				//更新卖家的资金流信息
				DyCashFlow cashFlow = new DyCashFlow();
				cashFlow.setClientId(sellerId);
				cashFlow.setDomainnameId(dyDomainnameId);
				cashFlow.setOperate(Constant.REDPACK_RETURN);
				cashFlow.setOperateAmount(returnMoney);
				cashFlow.setAmountBalance(dyFinance.getAccountBalance());
				cashFlow.setOperateTime(new Date());
				cashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
				// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
				cashFlow.preInsert(UserUtils.get(dyClientDao.get(sellerId).getBrokerId()));
				// 设置该实体为新记录
				cashFlow.setIsNewRecord(true);
				cashFlowService.save(cashFlow);
			}
		}
	}
	
	/**域名状态03且已过节拍时间，无人出价，处理该流拍域名
	 * @param dyDomainname 流拍的域名实体
	 * @param isSengMsg true 发送信息，false不发
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void auctionFailHandle(DyDomainname dyDomainname,boolean isSengMsg) throws Exception , ServiceException{
		//统计该域名红包发出情况，剩余则返还卖家
		if(DySysUtils.SHARE_BONUS_ENABLE.equals(SHARE_BONUS_SWITCH_ON)){
			//值为1，红包功能开启了
			countRedPack(dyDomainname.getId());
		}
		/**改成通过资金流获得
		Long sellerDeposit = DySysUtils.SELL_DEPOSIT;//卖家保证金，可能为0
		**/
		//获取卖家保证金
		Long sellerDeposit = 0L;
		DyCashFlow sellerCashFlow = new DyCashFlow();
		sellerCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		sellerCashFlow.setClientId(dyDomainname.getClientId());
		sellerCashFlow.setDomainnameId(dyDomainname.getId());
		sellerCashFlow.setDelFlag(DEL_FLAG_0);
		List<DyCashFlow> sellerList = cashFlowService.findList(sellerCashFlow);
		if(sellerList.isEmpty()){
			throw new ServiceException("卖家保证金，无");
		}else {
			sellerCashFlow = sellerList.get(0);
			sellerDeposit = sellerCashFlow.getOperateAmount();
		}
		//状态改为23流拍
		dyDomainname.setStatus(Constant.DOMAIN_STATUS_23);
		int n = dao.update(dyDomainname);
		if(n==0){
			throw new Exception();
		}
		
		//归还卖家保证金
		DyFinance dyFinance = new DyFinance();
		dyFinance.setClientId(dyDomainname.getClientId());
		dyFinance = dyFinanceDao.findList(dyFinance).get(0);
		dyFinance.setFreezeBalance(dyFinance.getFreezeBalance()-sellerDeposit);//解冻卖家保证金
		int n2 = dyFinanceDao.updateFinance(dyFinance);
		if(n2==0){
			throw new ServiceException();
		}
		//将卖家的保证金解冻记录更新到资金流表
		sellerCashFlow.setOperate(Constant.CASHFLOW_OPERATE_UNFREEZE);
		cashFlowService.save(sellerCashFlow);
		
		if(isSengMsg){
			//向卖家发送消息通知
			try {
				DyClient dyClient = dyClientDao.get(dyDomainname.getClientId());
				Message message = new Message();
				String title = DySysUtils.TEMPLATE_TITLE_0007;
				String content = DySysUtils.TEMPLATE_MESSAGE_0007;
				content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
				content = content.replace("{{price.DATA}}", "0");
				message.SendNews(WeChat.getAccessToken(), dyClient.getOpenid(), title, content, dyDomainname.getId());
			} catch (Exception e) {
				logger.error("向卖家发送信息失败");
			}
		}
	}
	
	/**域名状态03且已过节拍时间，有人出价,无保留价，对该域名进行处理
	 * @param transactionInformation 某个域名的买卖信息
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void auctionSuccessAndNoReservePriceHandle(TransactionInformation transactionInformation) throws Exception ,ServiceException{
		String domainnameId = transactionInformation.getDomainnameId();
		String burerId = transactionInformation.getBuyerId();
		DyClient buyerClient = dyClientDao.get(burerId);
		String sellerId = transactionInformation.getSellerId();
		
		//统计该域名红包发出情况，剩余则返还卖家
		if(DySysUtils.SHARE_BONUS_ENABLE.equals(SHARE_BONUS_SWITCH_ON)){//红包功能已开启
			countRedPack(domainnameId);
		}
		
		//状态改为11并设定买家的付款时限
		DyDomainname dyDomainname = dao.get(domainnameId);
		dyDomainname.setStatus(Constant.DOMAIN_STATUS_11);
		// 设置操作限制时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, DySysUtils.OPERATE_LIMIT_TIME_PAY);// 把日期从当前往后增加.整数往后推,负数往前移动
		dyDomainname.setWaitTime(cal.getTime());
		int n= dao.update(dyDomainname);
		if(n==0){
			throw new ServiceException();
		}
		
		//更新买家的冻结资金信息：保证金按照成交价计算
		Long buyerDeposit = null;
		Long oldDeposit = null;
		/**改为通过资金流记录获取
		Long buyerDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getBidAmount(), BID_RULE_TYPE_DEPOSIT);//计算买家保证金,有可能是0
		Long oldDeposit = null;//记录买家之前冻结的保证金,有可能没有冻结（当有代理竞价时，冻结了按代理金价计算的保证金，无代理竞价时，buyerDeposit为0时，没有冻结，否则冻结buyerDeposit）
		if(transactionInformation.getProxyAmount() != null && transactionInformation.getProxyAmount().longValue() != 0){
			oldDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getProxyAmount(), BID_RULE_TYPE_DEPOSIT);
		}else{
			oldDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getBidAmount(), BID_RULE_TYPE_DEPOSIT);
		}
		**/
		if(Constant.SWITCH_ON.equals(buyerClient.getAvoidDeposit())){
			//当前用户可以免除保证金
			buyerDeposit = 0L;
		}else{
			buyerDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getBidAmount(), BID_RULE_TYPE_DEPOSIT);//计算买家保证金,有可能是0
		}
		
		DyCashFlow cashFlow = new DyCashFlow();
		cashFlow.setClientId(burerId);
		cashFlow.setDomainnameId(domainnameId);
		cashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		List<DyCashFlow> list = cashFlowService.findList(cashFlow);
		if (list.isEmpty()) {
			throw new ServiceException("无买家保证金记录");
		} else {
			cashFlow = list.get(0);
			oldDeposit = cashFlow.getOperateAmount();
		}
		
		if(buyerDeposit.longValue() != oldDeposit.longValue()){
			//买家新保证金冻结
			cashFlow.setOperateAmount(buyerDeposit);
			cashFlowService.save(cashFlow);
			
			//更新买家的财务信息
			DyFinance buyerFinance = new DyFinance();
			buyerFinance.setClientId(burerId);
			buyerFinance = dyFinanceDao.findList(buyerFinance).get(0);
			int n1 = dyFinanceDao.updateFreezeBalance(buyerFinance.getId(), buyerDeposit - oldDeposit, buyerFinance.getUpdateDate());
			if(n1==0){
				throw new ServiceException();
			}
		}
		
		DyClient seller = dyClientDao.get(sellerId);
		DyClient buyer = dyClientDao.get(burerId);
		
		try {
			//向卖家发送消息通知
			Message message = new Message();
			String content = DySysUtils.TEMPLATE_MESSAGE_0005;
			String title = DySysUtils.TEMPLATE_TITLE_0005;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			content = content.replace("{{buyer.DATA}}", buyer.getNickname());
			content = content.replace("{{price.DATA}}", transactionInformation.getBidAmount().toString());
			message.SendNews(WeChat.getAccessToken(), seller.getOpenid(), title, content, dyDomainname.getId());
			
			//向买家发送消息通知
			String title1 = DySysUtils.TEMPLATE_TITLE_0006;
			String content1 = DySysUtils.TEMPLATE_MESSAGE_0006;
			content1 = content1.replace("{{domainname.DATA}}", dyDomainname.getName());
			content1 = content1.replace("{{seller.DATA}}", seller.getNickname());
			content1 = content1.replace("{{price.DATA}}", transactionInformation.getBidAmount().toString());
			content1 = content1.replace("{{waittime.DATA}}", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
			message.SendNews(WeChat.getAccessToken(), buyer.getOpenid(), title1, content1, dyDomainname.getId());
		} catch (Exception e) {
			logger.error("向卖家/买家发送信息失败");
		}
	}
	
	/**域名状态03且已过节拍时间，有人出价高于保留价，对该域名进行处理
	 * @param transactionInformation 某个域名的买卖信息
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void auctionSuccessAndHigherReservePriceHandle(TransactionInformation transactionInformation) throws Exception ,ServiceException{
		String domainnameId = transactionInformation.getDomainnameId();
		String sellerId = transactionInformation.getSellerId();
		String buyerId = transactionInformation.getBuyerId();
		DyClient buyerClient = dyClientDao.get(buyerId);
		//统计该域名红包发出情况，剩余则返还卖家
		if(DySysUtils.SHARE_BONUS_ENABLE.equals(SHARE_BONUS_SWITCH_ON)){//红包功能已开启
			countRedPack(domainnameId);
		}
		//状态改为11并设定买家的付款时限
		DyDomainname dyDomainname = dao.get(domainnameId);
		dyDomainname.setStatus(Constant.DOMAIN_STATUS_11);
		// 设置操作限制时间
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, DySysUtils.OPERATE_LIMIT_TIME_PAY);// 把日期从当前往后增加.整数往后推,负数往前移动
		dyDomainname.setWaitTime(cal.getTime());
		int n = dao.update(dyDomainname);
		if(n==0){
			throw new ServiceException();
		}
		
		//更新买家的冻结资金信息：冻结保证金变为冻结成交价
		Long buyerDeposit = null;
		Long oldDeposit = null;
		/**改为从资金流获取
		Long buyerDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getBidAmount(), BID_RULE_TYPE_DEPOSIT);//计算买家保证金,有可能是0
		Long oldDeposit = null;//记录买家之前冻结的保证金,有可能没有冻结（当有代理竞价时，冻结了按代理金价计算的保证金，无代理竞价时，buyerDeposit为0时，没有冻结，否则冻结buyerDeposit）
		if(transactionInformation.getProxyAmount() != null && transactionInformation.getProxyAmount().longValue() != 0){
			oldDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getProxyAmount(), BID_RULE_TYPE_DEPOSIT);
		}else{
			oldDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getBidAmount(), BID_RULE_TYPE_DEPOSIT);
		}
		**/
		if(Constant.SWITCH_ON.equals(buyerClient.getAvoidDeposit())){
			//当前用户可以免除保证金
			buyerDeposit = 0L;
		}else{
			buyerDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getBidAmount(), BID_RULE_TYPE_DEPOSIT);//计算买家保证金,有可能是0
		}
		//买家旧保证金记录
		DyCashFlow cashFlow = new DyCashFlow();
		cashFlow.setClientId(buyerId);
		cashFlow.setDomainnameId(domainnameId);
		cashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		List<DyCashFlow> list = cashFlowService.findList(cashFlow);
		if (list.isEmpty()) {
			throw new ServiceException("无买家保证金记录");
		} else {
			cashFlow = list.get(0);
			oldDeposit = cashFlow.getOperateAmount();
		}
		
		if(buyerDeposit.longValue() != oldDeposit.longValue()){
			//买家新保证金冻结记录
			cashFlow.setOperateAmount(buyerDeposit);
			cashFlowService.save(cashFlow);
			
			//更新买家的财务信息
			DyFinance buyerFinance = new DyFinance();
			buyerFinance.setClientId(buyerId);
			buyerFinance = dyFinanceDao.findAllList(buyerFinance).get(0);
			int n1 = dyFinanceDao.updateFreezeBalance(buyerFinance.getId(), buyerDeposit - oldDeposit, buyerFinance.getUpdateDate());
			if(n1==0){
				throw new ServiceException();
			}
		}
		
		
		DyClient seller = dyClientDao.get(transactionInformation.getSellerId());
		DyClient buyer = dyClientDao.get(transactionInformation.getBuyerId());
		 try {
			//向卖家发送消息通知
			Message message = new Message();
			String title = DySysUtils.TEMPLATE_TITLE_0005;
			String content = DySysUtils.TEMPLATE_MESSAGE_0005;
			content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
			content = content.replace("{{buyer.DATA}}", buyer.getNickname());
			content = content.replace("{{price.DATA}}", transactionInformation.getBidAmount().toString());
			message.SendNews(WeChat.getAccessToken(), seller.getOpenid(), title, content, dyDomainname.getId());
			
			//向买家发送消息通知
			String title1 = DySysUtils.TEMPLATE_TITLE_0006;
			String content1 = DySysUtils.TEMPLATE_MESSAGE_0006;
			content1 = content1.replace("{{domainname.DATA}}", dyDomainname.getName());
			content1 = content1.replace("{{seller.DATA}}", seller.getNickname());
			content1 = content1.replace("{{price.DATA}}", transactionInformation.getBidAmount().toString());
			content1 = content1.replace("{{waittime.DATA}}", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
			message.SendNews(WeChat.getAccessToken(), buyer.getOpenid(), title1, content1, dyDomainname.getId());
		} catch (Exception e) {
			logger.error("向买家/卖家发送信息失败");
		}
		
	}
	
	/**域名状态03且已过节拍时间，有人出价低于保留价，对该域名进行处理
	 * @param transactionInformation 某个域名的买卖信息
	 * @param isSengMsg true 发送信息，false不发
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void auctionSuccessAndLowerReservePriceHandle(TransactionInformation transactionInformation,boolean isSengMsg) throws Exception,ServiceException{
		String domainnameId = transactionInformation.getDomainnameId();
		
		//统计该域名红包发出情况，剩余则返还卖家
		if(DySysUtils.SHARE_BONUS_ENABLE.equals(SHARE_BONUS_SWITCH_ON)){//红包功能已开启
			countRedPack(domainnameId);
		}
		
		//状态改为23
		DyDomainname dyDomainname = dao.get(domainnameId);
		dyDomainname.setStatus(Constant.DOMAIN_STATUS_23);
		int n = dao.update(dyDomainname);
		if(n==0){
			throw new ServiceException();
		}
		
		/**改成从资金流获取
		Long sellerDeposit = DySysUtils.SELL_DEPOSIT;//卖家保证金，可能为0
		**/
		Long sellerDeposit = 0L;
		//卖家的保证金记录
		DyCashFlow sellerOldCashFlow = new DyCashFlow();
		sellerOldCashFlow.setClientId(dyDomainname.getClientId());
		sellerOldCashFlow.setDomainnameId(dyDomainname.getId());
		sellerOldCashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		List<DyCashFlow> list = cashFlowService.findList(sellerOldCashFlow);
		if (list.isEmpty()) {
			throw new ServiceException("没有卖家保证金冻结信息");
		} else {
			sellerOldCashFlow = list.get(0);
			sellerDeposit = sellerOldCashFlow.getOperateAmount();
		}
		
		//归还卖家保证金
		DyFinance sellerDyFinance = new DyFinance();
		sellerDyFinance.setClientId(dyDomainname.getClientId());
		sellerDyFinance = dyFinanceDao.findList(sellerDyFinance).get(0);
		sellerDyFinance.setFreezeBalance(sellerDyFinance.getFreezeBalance()-sellerDeposit);//解冻卖家保证金
		int n2 = dyFinanceDao.updateFinance(sellerDyFinance);
		if(n2==0){
			throw new ServiceException();
		}
		
		//将卖家的保证金解冻记录更新到资金流表
		sellerOldCashFlow.setOperate(Constant.CASHFLOW_OPERATE_UNFREEZE);
		cashFlowService.save(sellerOldCashFlow);
	
		//更新买家的冻结资金信息：解冻保证金变
		Long buyerOldDeposit = null;//记录买家之前冻结的保证金,有可能没有冻结（当有代理竞价时，冻结了按代理金价计算的保证金，无代理竞价时，buyerDeposit为0时，没有冻结，否则冻结buyerDeposit）
		/**改成通过资金流获取
		if(transactionInformation.getProxyAmount() != null && transactionInformation.getProxyAmount().longValue() != 0){
			buyerOldDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getProxyAmount(), BID_RULE_TYPE_DEPOSIT);
		}else{
			buyerOldDeposit = DySysUtils.calculateDepositAndIncrement(transactionInformation.getBidAmount(), BID_RULE_TYPE_DEPOSIT);
		}
		**/
		//买家保证金冻结记录
		DyCashFlow cashFlow = new DyCashFlow();
		cashFlow.setClientId(transactionInformation.getBuyerId());
		cashFlow.setDomainnameId(domainnameId);
		cashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
		List<DyCashFlow> buyerList = cashFlowService.findList(cashFlow);
		if (buyerList.isEmpty()) {
			throw new ServiceException("无买家保证金记录");
		} else {
			cashFlow = buyerList.get(0);
			buyerOldDeposit = cashFlow.getOperateAmount();
		}
		
		//买家解冻保证金记录更新
		cashFlow.setOperate(Constant.CASHFLOW_OPERATE_UNFREEZE);
		cashFlowService.save(cashFlow);
			
		//归还买家保证金
		DyFinance buyerDyFinance = new DyFinance();
		buyerDyFinance.setClientId(transactionInformation.getBuyerId());
		buyerDyFinance = dyFinanceDao.findList(buyerDyFinance).get(0);
		buyerDyFinance.setFreezeBalance(buyerDyFinance.getFreezeBalance()-buyerOldDeposit);//解冻买家保证金
		int n3 = dyFinanceDao.updateFinance(buyerDyFinance);
		if(n3==0){
			throw new ServiceException();
		}
		
		DyClient seller = dyClientDao.get(transactionInformation.getSellerId());
		DyClient buyer = dyClientDao.get(transactionInformation.getBuyerId());
		
		if (isSengMsg) {
			try {
				//向卖家发送消息通知
				Message message = new Message();
				String title = DySysUtils.TEMPLATE_TITLE_0007;
				String content = DySysUtils.TEMPLATE_MESSAGE_0007;
				content = content.replace("{{domainname.DATA}}", dyDomainname.getName());
				content = content.replace("{{price.DATA}}", transactionInformation.getBidAmount().toString());
				message.SendNews(WeChat.getAccessToken(), seller.getOpenid(), title, content, dyDomainname.getId());
				
				//向买家发送消息通知
				String title1 = DySysUtils.TEMPLATE_TITLE_0007;
				String content1 = DySysUtils.TEMPLATE_MESSAGE_0007;
				content1 = content1.replace("{{domainname.DATA}}", dyDomainname.getName());
				content1 = content1.replace("{{price.DATA}}", transactionInformation.getBidAmount().toString());
				message.SendNews(WeChat.getAccessToken(), buyer.getOpenid(), title1, content1, dyDomainname.getId());
			} catch (Exception e) {
				logger.error("向买家/卖家发送信息失败");
			}
		}
	}
	/**
	 * 域名停止拍卖
	 */
	@Transactional(readOnly = false)
	public void stopSell(String domainnameId) throws Exception{
		DyDomainname dyDomainname = dao.get(domainnameId);
		dyDomainname.setEndTime(new Date());
		if(dao.update(dyDomainname) == 0){
			throw new ServiceException("更新域名信息异常");
		}
		if(dyBidhistoryDao.getMaxBidAmount(domainnameId) == null){
			this.auctionFailHandle(dyDomainname,false);
		}else{
			TransactionInformation transactionInformation = dao.getTransactionByDomainId(domainnameId);
			auctionSuccessAndLowerReservePriceHandle(transactionInformation,false);
		}
	}
	
	/**
	 * 下载微信服务器上的图片到本地服务器， 并更新域名的Image字段
	 * @param domainInfo
	 */
	@Transactional(readOnly = false)
	public void downloadImages(final DyDomainname domainInfo) {
		try {
			Threads.getExecutorService().execute(new Runnable(){
				@Override
				public void run() {
					downloadImagesSync(domainInfo);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("下载失败了！！！");
		}
	}
	/**
	 * pc端压缩图片
	 */
	@Transactional(readOnly = false)
	public void computerImpressPicture(DyDomainname dyDomainname){
		String hostwar = ConfKit.get("host_war");
		String image1 = dyDomainname.getImage1();
		String image2 = dyDomainname.getImage2();
		String image3 = dyDomainname.getImage3();
		if ((StringUtils.isBlank(image1) || image1.contains(dyDomainname.getId() + "_1.jpg"))
				&& (StringUtils.isBlank(image2) || image2.contains(dyDomainname.getId() + "_2.jpg"))
				&& (StringUtils.isBlank(image3) || image3.contains(dyDomainname.getId() + "_3.jpg"))) {
			return;
		}
		try {
			String baseDir = Global.getUserfilesBaseDir();
			if (StringUtils.isNotBlank(image1) && !image1.contains(dyDomainname.getId() + "_1.jpg")) {
				String filePath = Global.USERFILES_BASE_URL + dyDomainname.getClientId() + "/" + 
									dyDomainname.getId() + "_1.jpg";
				FileUtils.createFile(baseDir + filePath);
				/*原图片路径*/
				String realPath = Global.getUserfilesBaseDir() + image1.substring(image1.indexOf(Global.USERFILES_BASE_URL));
				InputStream in = new BufferedInputStream(new FileInputStream(realPath));
				DySysUtils.formatPicture(in, baseDir + filePath);
				dyDomainname.setImage1(hostwar + filePath);
			}
			if (StringUtils.isNotBlank(image2) && !image2.contains(dyDomainname.getId() + "_2.jpg")) {
				String filePath = Global.USERFILES_BASE_URL + dyDomainname.getClientId() + "/" + 
									dyDomainname.getId() + "_2.jpg";
				FileUtils.createFile(baseDir + filePath);
				/*原图片路径*/
				String realPath = Global.getUserfilesBaseDir() + image2.substring(image2.indexOf(Global.USERFILES_BASE_URL));
				InputStream in = new BufferedInputStream(new FileInputStream(realPath));
				DySysUtils.formatPicture(in, baseDir + filePath);
				dyDomainname.setImage2(hostwar + filePath);
			}
			if (StringUtils.isNotBlank(image3) && !image3.contains(dyDomainname.getId() + "_3.jpg")) {
				String filePath = Global.USERFILES_BASE_URL + dyDomainname.getClientId() + "/" + 
									dyDomainname.getId() + "_3.jpg";
				FileUtils.createFile(baseDir + filePath);
				/*原图片路径*/
				String realPath = Global.getUserfilesBaseDir() + image3.substring(image3.indexOf(Global.USERFILES_BASE_URL));
				InputStream in = new BufferedInputStream(new FileInputStream(realPath));
				DySysUtils.formatPicture(in, baseDir + filePath);
				dyDomainname.setImage3(hostwar + filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("下载失败了！！！");
		}
	}
	
	/**
	 * 下载微信服务器上的图片到本地服务器， 并更新域名的Image字段
	 * @param domainInfo
	 */
	@Transactional(readOnly = false)
	public void downloadImagesSync(DyDomainname domainInfo) {
		Logger logger = LoggerFactory.getLogger(getClass());
		String hostwar = ConfKit.get("host_war");
		String image1 = domainInfo.getImage1();
		String image2 = domainInfo.getImage2();
		String image3 = domainInfo.getImage3();
		logger.debug("image1:" + image1 + " image2" + image2 + " image3" + image3);
		
		if ((StringUtils.isBlank(image1) || image1.startsWith(hostwar))
				&& (StringUtils.isBlank(image2) || image2.startsWith(hostwar))
				&& (StringUtils.isBlank(image3) || image3.startsWith(hostwar))) {
			return;
		}
		
		boolean needUpdate = false;
		try {
			String accessToken = WeChat.getAccessToken();
			String baseDir = Global.getUserfilesBaseDir();
			if (StringUtils.isNotBlank(image1) && !image1.startsWith(hostwar)) {
				Attachment attachment = WeChat.getMedia(accessToken, image1);
				String filePath = Global.USERFILES_BASE_URL + domainInfo.getClientId() + "/" + domainInfo.getId() + "_1." + attachment.getSuffix();
				FileUtils.createFile(baseDir + filePath);
				DySysUtils.formatPicture(attachment.getFileStream(), baseDir + filePath);
				domainInfo.setImage1(hostwar + filePath);
				needUpdate = true;
			}
			
			if (StringUtils.isNotBlank(image2) && !image2.startsWith(hostwar)) {
				Attachment attachment = WeChat.getMedia(accessToken, image2);
				String filePath = Global.USERFILES_BASE_URL + domainInfo.getClientId() + "/" + domainInfo.getId() + "_2." + attachment.getSuffix();
				FileUtils.createFile(baseDir + filePath);
				DySysUtils.formatPicture(attachment.getFileStream(), baseDir + filePath);
				domainInfo.setImage2(hostwar + filePath);
				needUpdate = true;
			}
			
			if (StringUtils.isNotBlank(image3) && !image3.startsWith(hostwar)) {
				Attachment attachment = WeChat.getMedia(accessToken, image3);
				String filePath = Global.USERFILES_BASE_URL + domainInfo.getClientId() + "/" + domainInfo.getId() + "_3." + attachment.getSuffix();
				FileUtils.createFile(baseDir + filePath);
				DySysUtils.formatPicture(attachment.getFileStream(), baseDir + filePath);
				domainInfo.setImage3(hostwar + filePath);
				needUpdate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("下载失败了！！！");
		} finally {
			if (needUpdate) {
				dao.update(domainInfo);
			}
		}
	}
	
	/**
	 * 删除会员所有的审核失败的域名
	 * @param id 会员ID
	 */
	@Transactional(readOnly = false)
	public void deleteDomainByClientId(String id) {
		dao.deleteDomainByClientId(id);
	}
	
	/**
	 * 删除会员所有的审核失败的域名
	 * @param id 会员ID
	 */
	@Transactional(readOnly = false)
	public void deleteDomainByClientId(String clientId, String id) {
		dao.deleteDomainById(clientId, id);
	}
	
	/**
	 * 手机端使用
	 *个人中心： 根据用户ID获取我的交易信息
	 * @return AjaxResult中data中存储数据
	 */
	@Transactional(readOnly = false)
	public AjaxResult myTransactions(){
		// 获取登录用户信息
		DyClient u = DySysUtils.getCurrentDyClient();
		//我的交易：正在参与的
		//myTransactionsDoingSize 记录正在参与的记录数(包括正在卖和正在买的)
		int myTransactionsDoingSize = 0;
		//获取我的交易：参与中的交易域名信息(买)
		List<Map<String, Object>> myTransactionsBuyingList = bidhistoryService.myTransactionsBuyInfo(u.getId(),DOMAIN_STATUS_03);//一个Map存储一条记录	：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址） waitTime(截止时间)
		//myTransactionsBuyingList。sizable（）大于0，则需获取关注信息、新消息状态
		if(myTransactionsBuyingList.size()>0){
			myTransactionsDoingSize = myTransactionsDoingSize + myTransactionsBuyingList.size();
			//获取我的交易：参与中的交易域名信息(买) 关注状态
//			DyAttention dyAttention = new DyAttention();
//			dyAttention.setClientId(u.getId());
//			for(int i=0;i<myTransactionsBuyingList.size();i++){
//				dyAttention.setDomainnameId(myTransactionsBuyingList.get(i).get("domainnameId").toString());
//				List<DyAttention> list = attentionService.findList(dyAttention);//list的size为1说明已关注，否则未关注
//				if(list.size()==1){
//					myTransactionsBuyingList.get(i).put("attentionFlag", "1");//attentionFlag为1表示关注，0表示未关注
//				}else{
//					myTransactionsBuyingList.get(i).put("attentionFlag", "0");
//					}
//				}
			//获取我的交易：参与中的交易域名信息(买) 新消息状态
			DyNews news = new DyNews();
			news.setClientId(u.getId());
			for(int j=0;j<myTransactionsBuyingList.size();j++){
				news.setDomainnameId((String)myTransactionsBuyingList.get(j).get("domainnameId"));
				List<DyNews> list = newsService.findList(news);
				if(list.isEmpty()){
					myTransactionsBuyingList.get(j).put("newsFlag", 0);//newsFlag为0表示该域名没有最新信息,大于0表示有消息
				}else{
					if(list.get(0).getNewBidCount().equals("0")){
						myTransactionsBuyingList.get(j).put("newsFlag", 0);//newsFlag为0表示该域名没有最新信息,大于0表示有消息
					}else{
						myTransactionsBuyingList.get(j).put("newsFlag", Integer.parseInt(list.get(0).getNewBidCount()));//newsFlag为0表示该域名没有最新信息,大于0表示有消息
						//消息标记清0
						list.get(0).setNewBidCount("0");
						newsService.save(list.get(0));
						NewsUpdateFlagUtil.setUpdateTimestamp();
					}
				}
			}
		}
		//获取我的交易：参与中的交易域名信息(卖)
		List<Map<String, Object>> myTransactionsSellingList = bidhistoryService.myTransactionsSellInfo(u.getId(),DOMAIN_STATUS_03);//一个Map存储一条记录：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址）	 waitTime(截止时间)
		//获取我的交易：参与中的交易域名信息(卖) 关注状态
		if (myTransactionsSellingList.size() > 0) {
			myTransactionsDoingSize = myTransactionsDoingSize + myTransactionsSellingList.size();
			DyAttention dyAttention = new DyAttention();
			dyAttention.setClientId(u.getId());
			for (int i = 0; i < myTransactionsSellingList.size(); i++) {
				//无人出价时，bidAmount为空，数据库返回的map没有存储，手动加上				
				if(!myTransactionsSellingList.get(i).containsKey("bidAmount")){
					myTransactionsSellingList.get(i).put("bidAmount", null);
				}
//				//关注状态
//				dyAttention.setDomainnameId(myTransactionsSellingList.get(i).get("domainnameId").toString());
//				List<DyAttention> list = attentionService.findList(dyAttention);// list的size为1说明已关注，否则未关注
//				if (list.size() == 1) {
//					myTransactionsSellingList.get(i).put("attentionFlag", "1");// attentionFlag为1表示关注，0表示未关注
//				} else {
//						myTransactionsSellingList.get(i).put("attentionFlag", "0");
//						}
			}
			//获取我的交易：参与中的交易域名信息(卖) 新消息状态
			DyNews news = new DyNews();
			news.setClientId(u.getId());
			for(int j=0;j<myTransactionsSellingList.size();j++){
				news.setDomainnameId((String)myTransactionsSellingList.get(j).get("domainnameId"));
				List<DyNews> list = newsService.findList(news);
				if(list.isEmpty()){
					myTransactionsSellingList.get(j).put("newsFlag", 0);//newsFlag为0表示该域名没有最新信息,大于0表示有消息
				}else{
					if(list.get(0).getNewBidCount().equals("0")){
						myTransactionsSellingList.get(j).put("newsFlag", 0);//newsFlag为0表示该域名没有最新信息,大于01表示有消息
					}else{
						myTransactionsSellingList.get(j).put("newsFlag", Integer.parseInt(list.get(0).getNewBidCount()));//newsFlag为0表示该域名没有最新信息,大于0表示有消息
						//消息标记清0
						list.get(0).setNewBidCount("0");
						newsService.save(list.get(0));
						}
					}
				}
			}
				
		//我的交易：待处理
		//myTransactionsDoneSize 记录已完成交易的记录数(包括卖和买的)
		int myTransactionsDoneSize = 0;
		//记录需要操作的记录数
		int waitToDealSize = 0;
		//获取我的交易：待处理交易的域名信息(参与买)
		List<Map<String, Object>> myTransactionsBoughtList = bidhistoryService.myTransactionsBuyInfo(u.getId(),DOMAIN_STATUS_1_);// 该结果在数据库语句中剔除了15状态，一个Map存储一条记录	：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址）  waitTime(截止时间)
		if(!myTransactionsBoughtList.isEmpty()){
			myTransactionsDoneSize = myTransactionsDoneSize + myTransactionsBoughtList.size();
			//统计需要付款和确认收到域名的记录数
			for(int i = 0;i < myTransactionsBoughtList.size();i++){
				String status = myTransactionsBoughtList.get(i).get("status").toString();
				if(status.equals(DOMAIN_STATUS_11) || status.equals(DOMAIN_STATUS_13)){
					waitToDealSize = waitToDealSize + 1;
				}
			}
		}
		
		//获取我的交易：待处理成交易的域名信息(卖)
		List<Map<String, Object>> myTransactionsSoldList = bidhistoryService.myTransactionsSellInfo(u.getId(),DOMAIN_STATUS_1_);//该结果在数据库语句中剔除了15状态，一个Map存储一条记录：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址）  waitTime(截止时间)
		if(!myTransactionsSoldList.isEmpty()){
			//无人出价时，bidAmount为空，数据库返回的map没有存储，手动加上
			for(int i = 0;i<myTransactionsSoldList.size();i++){
				if(!myTransactionsSoldList.get(i).containsKey("bidAmount")){
					myTransactionsSoldList.get(i).put("bidAmount", null);
				}
			}
			myTransactionsDoneSize = myTransactionsDoneSize + myTransactionsSoldList.size();
			//统计需要转移域名的记录数
			for(int i = 0;i < myTransactionsSoldList.size();i++){
				String status = myTransactionsSoldList.get(i).get("status").toString();
				if(status.equals(DOMAIN_STATUS_12)){
					waitToDealSize = waitToDealSize + 1;
				}
			}
		}
		
		//已完成的交易
		//获取我的交易：已完成交易的域名信息(参与买)
		int doneSize = 0;
		List<Map<String, Object>> myTransactionsDoneBoughtList = bidhistoryService.myTransactionsBuyInfo(u.getId(),DOMAIN_STATUS_15);//一个Map存储一条记录	：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址）  waitTime(截止时间)
		if(!myTransactionsDoneBoughtList.isEmpty()){
			doneSize = doneSize + myTransactionsDoneBoughtList.size();
		}
		//获取我的交易：：已完成成交易的域名信息(卖)
		List<Map<String, Object>> myTransactionsDoneSoldList = bidhistoryService.myTransactionsSellInfo(u.getId(),DOMAIN_STATUS_15);//一个Map存储一条记录：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址）  waitTime(截止时间)
		if(!myTransactionsDoneSoldList.isEmpty()){
			doneSize = doneSize + myTransactionsDoneSoldList.size();
		}
		//获取我的交易：已完成交易的域名信息(异常状态：终止、流拍、违约):买
		List<Map<String, Object>> myTransactionsBoughtExceptionList = bidhistoryService.myTransactionsBuyInfo(u.getId(),DOMAIN_STATUS_2_);//一个Map存储一条记录	：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址）  waitTime(截止时间)
		if(!myTransactionsBoughtExceptionList.isEmpty()){
			doneSize = doneSize + myTransactionsBoughtExceptionList.size();
		}
		
		//获取我的交易：已完成交易的域名信息(异常状态：终止、流拍、违约):卖
		List<Map<String, Object>> myTransactionsSoldExceptionList = bidhistoryService.myTransactionsSellInfo(u.getId(),DOMAIN_STATUS_2_);//一个Map存储一条记录：bidAmount（当前价） domainnameId（域名id） name(域名名) endTime（拍卖结束时间） status（域名状态） photo（域名所属人头像地址）  waitTime(截止时间)
		if(!myTransactionsSoldExceptionList.isEmpty()){
			//无人出价时，bidAmount为空，数据库返回的map没有存储，手动加上
			for(int i = 0;i<myTransactionsSoldExceptionList.size();i++){
				if(!myTransactionsSoldExceptionList.get(i).containsKey("bidAmount")){
					myTransactionsSoldExceptionList.get(i).put("bidAmount", null);
				}
			}
			doneSize = doneSize + myTransactionsSoldExceptionList.size();
		}
				
		//ajax返回的数据
		AjaxResult ar = AjaxResult.makeSuccess("");
		//交易信息
		//拍卖中
		ar.getData().put("myTransactionsBuyingList", myTransactionsBuyingList);
		ar.getData().put("myTransactionsSellingList", myTransactionsSellingList);
		ar.getData().put("myTransactionsDoingSize", myTransactionsDoingSize);
		//待处理
		ar.getData().put("myTransactionsBoughtList", myTransactionsBoughtList);
		ar.getData().put("myTransactionsSoldList", myTransactionsSoldList);
		ar.getData().put("myTransactionsDoneSize", myTransactionsDoneSize);
		ar.getData().put("waitToDealSize", waitToDealSize);		
		//已完成
		ar.getData().put("myTransactionsDoneBoughtList", myTransactionsDoneBoughtList);
		ar.getData().put("myTransactionsDoneSoldList", myTransactionsDoneSoldList);
		ar.getData().put("myTransactionsBoughtExceptionList", myTransactionsBoughtExceptionList);
		ar.getData().put("myTransactionsSoldExceptionList", myTransactionsSoldExceptionList);
		ar.getData().put("doneSize", doneSize);
		return ar;
	}
	
	
	/**PC端取我正在参与的交易信息
	 * @param page
	 * @param dyDomainname
	 * @param isBuyFlag 为“buy”时表示获取我参与买，为“sell”时表示我卖的
	 * @return
	 */
	public Page<TransactionsEntity> myTransactionsDoingPage(Page<TransactionsEntity> page, TransactionsEntity transactionsEntity,String isBuyFlag) {
		transactionsEntity.setPage(page);
		if(isBuyFlag.equals("buy")){
			page.setList(dao.myTransactionsBuyPage(transactionsEntity));
		}
		if(isBuyFlag.equals("sell")){
			page.setList(dao.myTransactionsSellPage(transactionsEntity));
		}
		return page;
	}
	
	/**PC端取待处理的交易信息
	 * @param page
	 * @param dyDomainname
	 * @param isBuyFlag 为“buy”时表示获取我参与买，为“sell”时表示我卖的
	 * @return
	 */
	public Page<TransactionsEntity> myTransactionsWaitDealPage(Page<TransactionsEntity> page, TransactionsEntity transactionsEntity,String isBuyFlag) {
		transactionsEntity.setPage(page);
		if(isBuyFlag.equals("buy")){
			page.setList(dao.myTransactionsBuyPage(transactionsEntity));
		}
		if(isBuyFlag.equals("sell")){
			page.setList(dao.myTransactionsSellPage(transactionsEntity));
		}
		return page;
	}
	
	/**PC端取已完成的交易信息
	 * @param page
	 * @param dyDomainname
	 * @param isBuyFlag 为“buy”时表示获取我参与买，为“sell”时表示我卖的
	 * @return
	 */
	public Page<TransactionsEntity> myTransactionsDonePage(Page<TransactionsEntity> page, TransactionsEntity transactionsEntity,String isBuyFlag) {
		transactionsEntity.setPage(page);
		if(isBuyFlag.equals("buy")){
			page.setList(dao.myTransactionsBuyPage(transactionsEntity));
		}
		if(isBuyFlag.equals("sell")){
			page.setList(dao.myTransactionsSellPage(transactionsEntity));
		}
		return page;
	}
	
	/**
	 * 提交域名，需同时完成域名信息的插入及保证金的扣除,资金流的插入，状态改为01，但金额不够时状态改为00，以后可以再次提交
	 * @param domainInfo
	 * @param client
	 * @return true 表示资金充足，false表示资金不够，需提示用充值
	 * @throws ServiceException 回滚
	 */
	@Transactional(readOnly = false)
	public boolean submitDomain(DyDomainname domainInfo,DyClient client) throws ServiceException{
		boolean isMoneyEnough = false;
		String clientId = client.getId();
		
		// 取得会员财务实体
		DyFinance dyFinance = new DyFinance();
		dyFinance.setClientId(clientId);
		dyFinance = dyFinanceDao.findList(dyFinance).get(0);
		Long availableBalance = dyFinance.getAccountBalance() - dyFinance.getFreezeBalance(); // 会员可用余额 = 会员帐户余额 - 会员帐户冻结金额
		Long deposit = null;//记录提交域名需扣除的保证金
		if(Constant.SWITCH_ON.equals(client.getAvoidDeposit())){
			//该用户可免除保证金
			deposit = 0L;
		}else{
			//该用户不可免除保证金
			deposit = this.getSellDeposit();//提交域名卖家 保证金
		}
		if (availableBalance >= deposit + domainInfo.getBonusShareTotal()) {
			//资金充足：域名插入且状态01、保证金操作、红包操作
			
			//插入域名信息
			domainInfo.setStatus(Constant.DOMAIN_STATUS_01);
			this.save(domainInfo);
			
			//扣除保证金：不需要交保证金的用户为特殊情况，按照保证金0写入数据库记录起来
			int n = dyFinanceDao.updateFreezeBalance(dyFinance.getId(), deposit, dyFinance.getUpdateDate());
			if(n == 0){
				throw new ServiceException();
			}
			
			//将冻结记录插入资金流
			DyCashFlow cashFlow = new DyCashFlow();
			cashFlow.setClientId(clientId);
			cashFlow.setDomainnameId(domainInfo.getId());
			cashFlow.setOperate(CASHFLOW_OPERATE_FREEZE);
			cashFlow.setOperateAmount(deposit);
			cashFlow.setOperateTime(new Date());
			cashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
			// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
			cashFlow.preInsert(UserUtils.get(client.getBrokerId()));
			// 设置该实体为新记录
			cashFlow.setIsNewRecord(true);
			cashFlowService.save(cashFlow);
			logger.debug("[cashLog]会员id:"+clientId+"会员资金流id:"+cashFlow.getId()+"提交域名冻结："+cashFlow.getOperateAmount()+"冻结总额："+dyFinance.getFreezeBalance());
			
			
			//将红包扣除插入资金流，财务余额扣除
			if(domainInfo.getBonusShareTotal() > 0 && DySysUtils.SHARE_BONUS_ENABLE.equals(Constant.SHARE_BONUS_SWITCH_ON)){
				//财务扣除
				DyFinance dyFinanceTemp = new DyFinance();
				dyFinanceTemp.setClientId(clientId);
				dyFinanceTemp = dyFinanceDao.findList(dyFinance).get(0);
				dyFinanceTemp.setAccountBalance(dyFinanceTemp.getAccountBalance() - domainInfo.getBonusShareTotal());
				if(dyFinanceDao.updateFinance(dyFinanceTemp) == 0){			//更新冲突
					throw new ServiceException("更新会员财务异常");
				}
				
				/*插入资金流信息*/
				DyCashFlow dyCashFlow = new DyCashFlow();
				dyCashFlow.setClientId(clientId);
				dyCashFlow.setDomainnameId(domainInfo.getId());
				dyCashFlow.setOperate(Constant.CASHFLOW_OPERATE_REBBAG_OUT);
				dyCashFlow.setOperateAmount(domainInfo.getBonusShareTotal());
				dyCashFlow.setOperateTime(new Date());
				dyCashFlow.setAmountBalance(dyFinanceTemp.getAccountBalance());
				dyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
				dyCashFlow.preInsert();
				dyCashFlow.setIsNewRecord(true);
				if(dyCashFlowDao.insert(dyCashFlow) == 0){
					throw new ServiceException("插入资金流异常");
				}
			}
		}else{
			//资金不够：插入域名，状态00
			//插入域名信息
			domainInfo.setStatus(Constant.DOMAIN_STATUS_00);
			this.save(domainInfo);
		}
		
		// 发送通知消息
		String content;
		String title;
		if (availableBalance >= deposit + domainInfo.getBonusShareTotal()) {
			// 可用余额足够，通知用户会冻结保证金扣除红包
			title = DySysUtils.TEMPLATE_TITLE_0001;
			content = DySysUtils.TEMPLATE_MESSAGE_0001;
			isMoneyEnough = true;
		} else {
			// 可用余额无法支付保证金和红包时，提醒会员充值
			title = DySysUtils.TEMPLATE_TITLE_0002;
			content = DySysUtils.TEMPLATE_MESSAGE_0002;
		}
		Message message = new Message();
		content = content.replace("{{domainname.DATA}}", domainInfo.getName());
		content = content.replace("{{deposit.DATA}}", Long.toString(deposit));
		content = content.replace("{{bonus.DATA}}", Long.toString(domainInfo.getBonusShareTotal()));
		try {
			message.SendNews(WeChat.getAccessToken(), client.getOpenid(), title, content, null);
		} catch (Exception e) {
			
		}
		return isMoneyEnough;
	}
	//取得卖家保证金
	public Long getSellDeposit() {
		return DySysUtils.SELL_DEPOSIT;
	}
	
	/**
	 * @return 还有一小时结拍的域名名称及关注者openid
	 */
	public List<FollowInfoToMsg> lastOneHourDomainInfo(FollowInfoToMsg followInfoToMsg){
		List<FollowInfoToMsg> list = dao.lastOneHourDomainInfo(followInfoToMsg);
		return list;
	}
	
	/**
	 * 统计最近七天，每一天结拍域名的个数
	 * @return list 有七个数据，每个数据表示当天域名结拍的个数
	 */
	public List<DomainEndNumEntity> countLately7DayDomianNum(){
		List<DomainEndNumEntity> list = null;
		// 设置时间
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);//获取年份
		int month=cal.get(Calendar.MONTH);//获取月份
		int day=cal.get(Calendar.DATE);//获取日
		
		Date beginDate= new Date(year-1900, month,day, 0, 0, 0);//开始时间
		
		Date endDate= new Date(year-1900, month, day, 23, 59, 59);
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_MONTH,6);
		endDate = cal.getTime();//结束时间
		
		list = dao.getDomainNum(DySysUtils.DEAL_NUM,beginDate,endDate);
		 return list;
	}
}