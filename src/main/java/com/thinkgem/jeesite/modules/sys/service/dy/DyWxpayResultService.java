/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.text.SimpleDateFormat;
import java.util.List;

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
import com.thinkgem.jeesite.modules.sys.dao.dy.DyWxpayResultDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformFinance;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyWxpayResult;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 微信支付结果管理Service
 * @author quanyf.fnst
 * @version 2015-11-05
 */
@Service
@Transactional(readOnly = true)
public class DyWxpayResultService extends CrudService<DyWxpayResultDao, DyWxpayResult> implements Constant{

	@Autowired
	private DyFinanceDao dyFinanceDao;
	
	@Autowired
	private DyClientDao dyClientDao;
	
	@Autowired
	private DyCashFlowDao dyCashFlowDao;
	
	@Autowired
	private DyPlatformAccountService dyPlatformAccountService;
	@Autowired
	private DyPlatformFinanceDao dyPlatformFinanceDao;
	
	public DyWxpayResult get(String id) {
		return super.get(id);
	}
	
	public List<DyWxpayResult> findList(DyWxpayResult dyWxpayResult) {
		return super.findList(dyWxpayResult);
	}
	
	public Page<DyWxpayResult> findPage(Page<DyWxpayResult> page, DyWxpayResult dyWxpayResult) {
		return super.findPage(page, dyWxpayResult);
	}
	
	@Transactional(readOnly = false)
	public void save(DyWxpayResult dyWxpayResult) {
		super.save(dyWxpayResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyWxpayResult dyWxpayResult) {
		super.delete(dyWxpayResult);
	}
	
	/**
	 * @param wxpayResult 支付结果实体
	 * @return flag为1处理成功，为0处理失败
	 * @throws Exception
	 */
	@Transactional(readOnly = false)
	public void dealWithPayResult(DyWxpayResult wxpayResult)throws Exception,ServiceException{
		//更新微信支付结果表
		int n1 = dao.update(wxpayResult);
		if(n1 == 0){
			throw new ServiceException("更新微信支付结果表异常");
		}
		
		if (wxpayResult.getResultCode().equals("SUCCESS")) {
			//获取该订单所属会员信息
			DyClient client = dyClientDao.getByOpenid(wxpayResult.getOpenid());
			
			//支付成功则更新财务表
			DyFinance finance = new DyFinance();
			finance.setClientId(client.getId());
			finance = dyFinanceDao.findList(finance).get(0);
			int n2 = dyFinanceDao.updateAccountBalance(wxpayResult.getTotalFee(),finance.getUpdateDate(),finance.getId());
			if(n2 == 0){
				throw new ServiceException("更新新财务表异常");
			}
			
			//插入资金流表一条记录			
			DyCashFlow cashFlow = new DyCashFlow();
			cashFlow.setClientId(client.getId());
			cashFlow.setOperate(CASHFLOW_OPERATE_RECHARGE_ONLINE);
			cashFlow.setOperateAmount(wxpayResult.getTotalFee());
			cashFlow.setAmountBalance(finance.getAccountBalance()+wxpayResult.getTotalFee());//充值后的余额
			cashFlow.setOperateTime(wxpayResult.getTimeEnd());
			cashFlow.setConfirmResult(CASHFLOW_COMFIRM_DONE);
			cashFlow.setTransactionId(wxpayResult.getTransactionId());
			// 设置id,create_by,create_date,update_by,update_date,del_flag,remarks
			cashFlow.preInsert(UserUtils.get(client.getBrokerId()));
			// 设置该实体为新记录
			cashFlow.setIsNewRecord(true);
			int n3 = dyCashFlowDao.insert(cashFlow);
			if(n3 == 0){
				throw new ServiceException("更新资金流表异常");
			}
			
			/*更新平台总账户*/
			Long totalFinanceAmount = dyPlatformAccountService.updateFinanceAccount(wxpayResult.getTotalFee(), Constant.CASHFLOW_OPERATE_RECHARGE_ONLINE);
			if(totalFinanceAmount == null){
				throw new ServiceException("更新平台总账户失败");
			}
			/*插入平台账户记录*/
			DyPlatformFinance dyPlatformFinance = new DyPlatformFinance();
			dyPlatformFinance.setClientId(client.getId());
			dyPlatformFinance.setOperate(Constant.CASHFLOW_OPERATE_RECHARGE_ONLINE);
			dyPlatformFinance.setOperateAmount(wxpayResult.getTotalFee());
			dyPlatformFinance.setTotalAmount(totalFinanceAmount);
			dyPlatformFinance.preInsert(UserUtils.get(client.getBrokerId()));
			if(dyPlatformFinanceDao.insert(dyPlatformFinance) == 0){
				throw new ServiceException("更新平台账户失败");
			}
			
			//发送微信支付成功通知
			Message message = new Message();
			String openId = wxpayResult.getOpenid();
			String title = DySysUtils.TEMPLATE_TITLE_0027;
			String content = DySysUtils.TEMPLATE_MESSAGE_0027;
			content = content.replace("{{totalFee.DATA}}", wxpayResult.getTotalFee().toString());
			content = content.replace("{{outTradeNo.DATA}}", wxpayResult.getOutTradeNo());
			content = content.replace("{{transactionId.DATA}}", wxpayResult.getTransactionId());
			content = content.replace("{{resultCode.DATA}}", "支付成功");
			content = content.replace("{timeEnd.DATA}}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wxpayResult.getTimeEnd()));
			message.SendNews(WeChat.getAccessToken(), openId, title, content, null);
		}else{
			//发送微信失败成功通知
			Message message = new Message();
			String openId = wxpayResult.getOpenid();
			String title = DySysUtils.TEMPLATE_TITLE_0027;
			String content = DySysUtils.TEMPLATE_MESSAGE_0027;
			content = content.replace("{{totalFee.DATA}}", wxpayResult.getTotalFee().toString());
			content = content.replace("{{outTradeNo.DATA}}", wxpayResult.getOutTradeNo());
			content = content.replace("{{transactionId.DATA}}", wxpayResult.getTransactionId());
			content = content.replace("{{resultCode.DATA}}", "支付失败");
			content = content.replace("{timeEnd.DATA}}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(wxpayResult.getTimeEnd()));
			message.SendNews(WeChat.getAccessToken(), openId, title, content, null);
		}
	}
}