/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyPlatformFinance;

/**
 * 平台总账户管理DAO接口
 * @author quanyf.fnst
 * @version 2015-12-15
 */
@MyBatisDao
public interface DyPlatformFinanceDao extends CrudDao<DyPlatformFinance> {
	
}