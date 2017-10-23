/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformAccount;

/**
 * 平台总账户管理DAO接口
 * @author quanyf.fnst
 * @version 2015-12-15
 */
@MyBatisDao
public interface DyPlatformAccountDao extends CrudDao<DyPlatformAccount> {
	/**
	 * 获取平台当前账户总额
	 */
	public long getFinanceAccount();
	/**
	 * 获取平台当前账户总额
	 */
	public long getIncomeAccount();
	/**
	 * 更新平台账户
	 */
	public int updateAccount(DyPlatformAccount dyPlatformAccount);
}