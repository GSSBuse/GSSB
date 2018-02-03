/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjWithdraw;

/**
 * 提现记录表DAO接口
 * @author snnu
 * @version 2018-02-03
 */
@MyBatisDao
public interface GbjWithdrawDao extends CrudDao<GbjWithdraw> {
	
}