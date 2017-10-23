/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;


import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;

/**
 * 会员财务信息管理DAO接口
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@MyBatisDao
public interface DyFinanceDao extends CrudDao<DyFinance> {
	/**
	 * 根据会员id获取财务记录
	 */
	public DyFinance getByClientId(String clientId);
	/**
	 * 保存账户信息，此方法可防止冲突提交
	 * @param dyFinance
	 * @return
	 */
	public int updateFinance(DyFinance dyFinance);
	/**
	 * 冻结保证金
	 * @param id 会员财务记录ID
	 * @param deposit 保证金
	 */
	public int updateFreezeBalance(@Param("id")String id, @Param("freezeBalance")Long freezeBalance,@Param("updateDate")Date updateDate);
	
	/**
	 * @param accountBalance 充值金额
	 * @param updateDate 上次更新日期，防止冲突提交
	 * @param id 会员财务记录ID
	 * @return
	 */
	public int updateAccountBalance(@Param("accountBalance")Long accountBalance,@Param("updateDate")Date updateDate,@Param("id")String id);
}