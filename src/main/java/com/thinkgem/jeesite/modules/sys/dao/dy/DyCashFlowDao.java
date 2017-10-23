/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyCashFlow;
import com.thinkgem.jeesite.modules.sys.entity.dy.CashFlowInfo.CashFlowInfo;

/**
 * 资金流信息DAO接口
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@MyBatisDao
public interface DyCashFlowDao extends CrudDao<DyCashFlow> {

	/**
	 * 查询分页数据
	 * @param brokerId 经纪人ID
	 * @param dyCashFlow 资金流实体
	 * @return 
	 */
	public List<DyCashFlow> findListByBrokerId(CashFlowInfo cashFlowInfo);
	
	
	/**
	 * 获取用户的资金流信息：不需要冻结和解冻的信息
	 * @param dyCashFlow
	 * @return
	 */
	public List<DyCashFlow> findCashFlowList(DyCashFlow dyCashFlow);
	
	/**
	 * 获取用户的资金流信息：提现 （等待 处理中）
	 * @param dyCashFlow
	 * @return
	 */
	public List<DyCashFlow> findCashFlowWithdrawals(DyCashFlow dyCashFlow);
	
	/**
	 * 获取用户的冻结记录信息：冻结和提现
	 * @param dyCashFlow
	 * @return
	 */
	public List<DyCashFlow> findFreezeInfoList(DyCashFlow dyCashFlow);
	
	/**
	 * 根据查询条件获取资金流分页数据
	 * @param brokerId 经纪人ID
	 * @param dyCashFlow 资金流实体
	 * @param nickname 会员昵称
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	public List<DyCashFlow> findPageforQuery(CashFlowInfo cashFlowInfo);
	/**
	 * 获取某个会员的资金流水
	 */
	public List<DyCashFlow> findCashListByClientId(DyCashFlow dyCashFlow);
	/**
	 *获取某个会员的冻结解冻记录
	 */
	public List<DyCashFlow> findFreePageByClientId(DyCashFlow dyCashFlow);
	/**
	 * 查询充值提现待确认的消息的数量
	 * @return
	 */
	public int cUnconfirmCount();
	/**
	 * 返回资金流的所有详细信息
	 * @param cashFlowInfo	资金流信息实体
	 * @return
	 */
	public DyCashFlow getCashFlowInfo(String id);
	/**
	 * 查询某个经纪人，最近的5条充值记录
	 * @param brokerId 经纪人id
	 * @return
	 */
	public List<CashFlowInfo> findLastCashFlow(String brokerId);
	/**
	 * 保存资金流信息，此方法可防止冲突提交
	 * @param dyCashFlow
	 * @return
	 */
	public int updateCashFlow(DyCashFlow dyCashFlow);
	/**
	 * 根据用户id获取该用户处于等待和处理中状态的提现记录信息
	 * @param clientId 用户id
	 * @param CASHFLOW_OPERATE_WITHDRAW 操作类型：提现
	 * @param CASHFLOW_CONFIRM_WAIT 处理状态：等待
	 * @param CASHFLOW_COMFIRM_DOING 处理状态：处理中
	 * @return
	 */
	public List<DyCashFlow> findWithdrawalsList(@Param("clientId")String clientId,@Param("CASHFLOW_OPERATE_WITHDRAW")String CASHFLOW_OPERATE_WITHDRAW,@Param("CASHFLOW_CONFIRM_WAIT")String CASHFLOW_CONFIRM_WAIT,@Param("CASHFLOW_COMFIRM_DOING") String CASHFLOW_COMFIRM_DOING);
	/**
	 * 查询冻结/解冻记录，需要（域名Id、会员id、操作、操作金额）
	 */
	public int updateFreeCashFlow(DyCashFlow dyCashFlow);
	/**
	 * 修改冻结资金流的冻结金额（域名Id、会员id、操作金额、）
	 */
	public int updateFreeCashFlowAmount(DyCashFlow dyCashFlow , long newProxyAmount);
}