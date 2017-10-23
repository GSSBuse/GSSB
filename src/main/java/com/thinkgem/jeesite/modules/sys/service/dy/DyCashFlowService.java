/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyCashFlowDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyClientDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyFinanceDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyPlatformFinanceDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformFinance;
import com.thinkgem.jeesite.modules.sys.entity.dy.CashFlowInfo.CashFlowInfo;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 资金流信息Service
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Service
@Transactional(readOnly = true)
public class DyCashFlowService extends CrudService<DyCashFlowDao, DyCashFlow> {
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	private DyClientDao dyClientDao;
	@Autowired
	private DyFinanceDao dyFinanceDao;
	@Autowired
	private DyPlatformAccountService dyPlatformAccountService;
	@Autowired
	private DyPlatformFinanceDao dyPlatformFinanceDao;
	
	public DyCashFlow get(String id) {
		return super.get(id);
	}
	
	public List<DyCashFlow> findList(DyCashFlow dyCashFlow) {
		return super.findList(dyCashFlow);
	}
	/**
	 * 获取该经纪人下的待确认记录
	 */
	public Page<DyCashFlow> findPageByBrokerId(Page<DyCashFlow> page, DyCashFlow dyCashFlow, String brokerId) throws Exception{
		CashFlowInfo cashFlowInfo = new CashFlowInfo();
		BeanUtils.copyProperties(cashFlowInfo, dyCashFlow);
		cashFlowInfo.equals(brokerId);
		cashFlowInfo.setPage(page);
		page.setList(dao.findListByBrokerId(cashFlowInfo));
		return page;
	}
	/**
	 * 查询某个经纪人，最近的5条充值记录
	 * @param brokerId 经纪人id
	 * @return
	 */
	public List<CashFlowInfo> findLastCashFlow(String brokerId){
		return dao.findLastCashFlow(brokerId);
	}
	/**
	 * 解冻资金流，需要（域名Id、会员id、操作金额）
	 */
	@Transactional(readOnly = false)
	public int updateFreeCashFlow(DyCashFlow dyCashFlow){
		return dao.updateFreeCashFlow(dyCashFlow);
	}
	/**
	 * 修改冻结资金流的冻结金额（域名Id、会员id、操作金额、）
	 */
	@Transactional(readOnly = false)
	public int updateFreeCashFlowAmount(DyCashFlow dyCashFlow , long newProxyAmount){
		return dao.updateFreeCashFlowAmount(dyCashFlow , newProxyAmount);
	}
	/**
	 * 根据查询条件获取资金流分页数据
	 * @param page 分页数据
	 * @param brokerId 经纪人ID
	 * @param dyCashFlow 资金流实体
	 * @param nickname 会员昵称
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public Page<DyCashFlow> findPageforQuery(Page<DyCashFlow> page, CashFlowInfo cashFlowInfo){
		cashFlowInfo.setPage(page);
		page.setList(dao.findPageforQuery(cashFlowInfo));
		return page;
	}
	/**
	 * 返回资金流的所有详细信息
	 * @param cashFlowInfo	资金流信息实体
	 * @return
	 */
	public DyCashFlow getCashFlowInfo(String id){
		return dao.getCashFlowInfo(id);
	}
	/**
	 * 查询充值提现待确认的消息的数量
	 * @return
	 */
	public int cUnconfirmCount(){
		return dao.cUnconfirmCount();
	}
	
	public Page<DyCashFlow> findPage(Page<DyCashFlow> page, DyCashFlow dyCashFlow) {
		return super.findPage(page, dyCashFlow);
	}
	
	/**
	 * 获取用户的资金流信息：不需要冻结和解冻
	 * @param page
	 * @param dyCashFlow
	 * @return
	 */
	public Page<DyCashFlow> findCashFlowPage(Page<DyCashFlow> page, DyCashFlow dyCashFlow) {
		dyCashFlow.setPage(page);
		page.setList(dao.findCashFlowList(dyCashFlow));
		return page;
	}
	
	/**
	 * 获取用户的资金流信息：提现 （等待 处理中）
	 * @param page
	 * @param dyCashFlow
	 * @return
	 */
	public Page<DyCashFlow> findWithdrawalsPage(Page<DyCashFlow> page, DyCashFlow dyCashFlow) {
		dyCashFlow.setPage(page);
		page.setList(dao.findCashFlowWithdrawals(dyCashFlow));
		return page;
	}
	
	/**
	 * 获取用户的冻结记录信息：冻结和提现
	 * @param page
	 * @param dyCashFlow
	 * @return
	 */
	public Page<DyCashFlow> findFreezeInfoPage(Page<DyCashFlow> page, DyCashFlow dyCashFlow) {
		dyCashFlow.setPage(page);
		page.setList(dao.findFreezeInfoList(dyCashFlow));
		return page;
	}
	
	
	/**
	 * 获取某个会员的资金流水
	 */
	public Page<DyCashFlow> findPageByClientId(Page<DyCashFlow> page, DyCashFlow dyCashFlow) {
		dyCashFlow.setPage(page);
		page.setList(dao.findCashListByClientId(dyCashFlow));
		return page;
	}
	
	/**
	 *获取某个会员的冻结解冻记录
	 */
	public Page<DyCashFlow> findFreePageByClientId(Page<DyCashFlow> page, DyCashFlow dyCashFlow) {
		dyCashFlow.setPage(page);
		page.setList(dao.findFreePageByClientId(dyCashFlow));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(DyCashFlow dyCashFlow) {
		if (dyCashFlow.getIsNewRecord()){
			dyCashFlow.preInsert();
			dao.insert(dyCashFlow);
		}else{
			dyCashFlow.preUpdate();
			int rt = dao.update(dyCashFlow);
			if (rt == 0) {
				throw new ServiceException("无法更新 - 不存在该资金记录:" + dyCashFlow.toString());
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DyCashFlow dyCashFlow) {
		super.delete(dyCashFlow);
	}
	/**
	 * 充值提现申请
	 * @param dyCashFlow
	 */
	@Transactional(readOnly = false)
	public void applySave(DyCashFlow dyCashFlow){
		//如果是提现的话，冻结体现金额
		if(StringUtils.equals(Constant.CASHFLOW_OPERATE_WITHDRAW, dyCashFlow.getOperate())){
			//查询条件
			DyFinance dyFinance = new DyFinance();
			dyFinance.setClientId(dyCashFlow.getClientId());
			//个人账户
			DyFinance dyFinanceTemp = dyFinanceDao.get(dyFinance);
			dyFinanceTemp.setFreezeBalance(dyFinanceTemp.getFreezeBalance() + dyCashFlow.getOperateAmount());
			if(dyFinanceDao.updateFinance(dyFinanceTemp) == 0){
				throw new ServiceException("更新个人账户信息失败");
			}
		}
		dyCashFlow.preInsert();
		if(dao.insert(dyCashFlow) == 0){
			throw new ServiceException("充值提现申请失败");
		}
	}
	/**
	 * 提现处理中
	 */
	@Transactional(readOnly = false)
	public void drawProcessing(DyCashFlow dyCashFlow,String pass) throws Exception{
		Long money =null;
		if(StringUtils.isNotBlank(pass)){	//处理中
			dyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DOING);
		}else{			//打回
			//查询条件
			DyFinance dyFinance = new DyFinance();
			dyFinance.setClientId(dyCashFlow.getClientId());
			//个人账户
			DyFinance dyFinanceTemp = dyFinanceDao.get(dyFinance);
			dyFinanceTemp.setFreezeBalance(dyFinanceTemp.getFreezeBalance() - dyCashFlow.getOperateAmount());
			money = dyFinanceTemp.getFreezeBalance();
			if(dyFinanceDao.updateFinance(dyFinanceTemp) == 0){
				throw new ServiceException("更新个人账户信息失败");
			}
			dyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_NOT);
			dyCashFlow.setAmountBalance(dyFinanceTemp.getAccountBalance());
			String title = DySysUtils.TEMPLATE_TITLE_0025;
			String content = DySysUtils.TEMPLATE_MESSAGE_0025;
			Message message = new Message();
			content = content.replace("{{amount.DATA}}", Long.toString(dyCashFlow.getOperateAmount()));
			content = content.replace("{{total.DATA}}", Long.toString(dyCashFlow.getAmountBalance()));
			message.SendNews(WeChat.getAccessToken(), dyClientDao.get(dyCashFlow.getClientId()).getOpenid(), title, content, null);
		}
		if(dao.updateCashFlow(dyCashFlow) == 0){
			throw new ServiceException("更新个人账户信息失败");
		}
		logger.debug("[cashLog]会员id:"+dyCashFlow.getClientId()+"会员资金流id:"+dyCashFlow.getId()+dyCashFlow.getOperate()+"解冻："+dyCashFlow.getOperateAmount()+"冻结总额："+money);
}
	
	/**
	 * 资金流确认
	 * @param dyCashFlow
	 * @param pass	//是否通过
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public void confirmDo(DyCashFlow dyCashFlow,String pass) throws Exception{
		//查询条件
		DyFinance dyFinance = new DyFinance();
		dyFinance.setClientId(dyCashFlow.getClientId());
		//个人账户
		DyFinance dyFinanceTemp = dyFinanceDao.get(dyFinance);
		String title = "";
		String content = "";
		if(StringUtils.equals("0", pass)){
			/*审核不通过*/
			dyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_NOT);
			if(StringUtils.equals(Constant.CASHFLOW_OPERATE_RECHARGE_ONLINE, dyCashFlow.getOperate()) ||
			   StringUtils.equals(Constant.CASHFLOW_OPERATE_RECHARGE_OUTLINE, dyCashFlow.getOperate()) ){
				//充值
				 title = DySysUtils.TEMPLATE_TITLE_0024;
				 content = DySysUtils.TEMPLATE_MESSAGE_0024;
			}else if(StringUtils.equals(Constant.CASHFLOW_OPERATE_WITHDRAW, dyCashFlow.getOperate())){
				//提现
				dyFinanceTemp.setFreezeBalance(dyFinanceTemp.getFreezeBalance() - dyCashFlow.getOperateAmount());
				title = DySysUtils.TEMPLATE_TITLE_0025;
				content = DySysUtils.TEMPLATE_MESSAGE_0025;				
			}
		}else if(StringUtils.equals("1", pass)){
			/*审核通过*/
			dyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_DONE);
			long accountBalance = 0; //个人账户总资产
			if(StringUtils.equals(Constant.CASHFLOW_OPERATE_RECHARGE_ONLINE, dyCashFlow.getOperate()) ||
			   StringUtils.equals(Constant.CASHFLOW_OPERATE_RECHARGE_OUTLINE, dyCashFlow.getOperate()) ){
				//充值
				accountBalance = dyFinanceTemp.getAccountBalance() + dyCashFlow.getOperateAmount();
				title = DySysUtils.TEMPLATE_TITLE_0021;
				content = DySysUtils.TEMPLATE_MESSAGE_0021;
			}else if(StringUtils.equals(Constant.CASHFLOW_OPERATE_WITHDRAW, dyCashFlow.getOperate())){
				//提现
				accountBalance = dyFinanceTemp.getAccountBalance() - dyCashFlow.getOperateAmount();
				dyFinanceTemp.setFreezeBalance(dyFinanceTemp.getFreezeBalance() - dyCashFlow.getOperateAmount());
				title = DySysUtils.TEMPLATE_TITLE_0022;
				content = DySysUtils.TEMPLATE_MESSAGE_0022;
			}
			dyFinanceTemp.setAccountBalance(accountBalance);
			/*更新平台总账户*/
			Long totalFinanceAmount = dyPlatformAccountService.updateFinanceAccount(dyCashFlow.getOperateAmount(), dyCashFlow.getOperate());
			if(totalFinanceAmount == null){
				throw new ServiceException("更新平台总账户失败");
			}
			/*插入平台账户记录*/
			DyPlatformFinance dyPlatformFinance = new DyPlatformFinance();
			dyPlatformFinance.setClientId(dyCashFlow.getClientId());
			dyPlatformFinance.setOperate(dyCashFlow.getOperate());
			dyPlatformFinance.setOperateAmount(dyCashFlow.getOperateAmount());
			dyPlatformFinance.setTotalAmount(totalFinanceAmount);
			dyPlatformFinance.preInsert();
			if(dyPlatformFinanceDao.insert(dyPlatformFinance) == 0){
				throw new ServiceException("更新平台账户失败");
			}
			/**/
		}
		if(dyFinanceDao.updateFinance(dyFinanceTemp) == 0){
			throw new ServiceException("更新个人账户信息失败");
		}
		dyCashFlow.setAmountBalance(dyFinanceTemp.getAccountBalance());
		if(dao.updateCashFlow(dyCashFlow) == 0){
			throw new ServiceException("更新个人账户信息失败");
		}
		logger.debug("[cashLog]会员id:"+dyCashFlow.getClientId()+"会员资金流id："+dyCashFlow.getId()+dyCashFlow.getOperate()+"解冻："+dyCashFlow.getOperateAmount()+"冻结总额："+dyFinanceTemp.getFreezeBalance());
		// 发送通知消息
		Message message = new Message();
		content = content.replace("{{amount.DATA}}", Long.toString(dyCashFlow.getOperateAmount()));
		content = content.replace("{{total.DATA}}", Long.toString(dyCashFlow.getAmountBalance()));
		message.SendNews(WeChat.getAccessToken(), dyClientDao.get(dyCashFlow.getClientId()).getOpenid(), title, content, null);
	}
	/**
	 * 根据用户id获取该用户处于等待和处理中状态的提现记录信息
	 * @param clientId 用户id
	 * @param CASHFLOW_OPERATE_WITHDRAW 操作类型：提现
	 * @param CASHFLOW_CONFIRM_WAIT 处理状态：等待
	 * @param CASHFLOW_COMFIRM_DOING 处理状态：处理中
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<DyCashFlow> findWithdrawalsList(String clientId,String CASHFLOW_OPERATE_WITHDRAW,String CASHFLOW_CONFIRM_WAIT,String CASHFLOW_COMFIRM_DOING){
		List<DyCashFlow> list =  dao.findWithdrawalsList(clientId,CASHFLOW_OPERATE_WITHDRAW,CASHFLOW_CONFIRM_WAIT,CASHFLOW_COMFIRM_DOING);
		 return list;
	}
	
	@Transactional(readOnly = false)
	public int updateCashFlow(DyCashFlow dyCashFlow) {
		return dao.updateCashFlow(dyCashFlow);
	}
	/**
	 * 根据用户id和提现的资金流id，取消用户的提现申请
	 * @param clientId 用户id
	 * @param id 提现的资金流id
	 * @param money 提现的金额
	 */
	@Transactional(readOnly = false)
	public void cancel(String clientId, String id,String money) throws Exception,ServiceException{
		//更新财务表,释放冻结的提现金额
		DyFinance dyFinance = new DyFinance();
		dyFinance.setClientId(clientId);
		dyFinance = dyFinanceDao.findList(dyFinance).get(0);
		dyFinance.setFreezeBalance(dyFinance.getFreezeBalance()-Long.parseLong(money));
		int n = dyFinanceDao.updateFinance(dyFinance);
		if(n == 0){
			throw new ServiceException();
		}
		
		//更新资金流表,将记录的确认状态改为已取消
		DyCashFlow dyCashFlow = dao.get(id);
		dyCashFlow.setConfirmResult(Constant.CASHFLOW_COMFIRM_CANCEL);
		dyCashFlow.setRemarks("取消提现");
		int n1 = dao.updateCashFlow(dyCashFlow);
		if(n1 == 0){
			throw new ServiceException();
		}
		logger.debug("[cashLog]会员id:"+clientId+"会员资金流id:"+dyCashFlow.getId()+"取消提现,解冻："+money+"冻结总额："+dyFinance.getFreezeBalance());
	}
}