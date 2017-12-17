/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUser;

/**
 * 用户信息表DAO接口
 * @author snnu
 * @version 2017-12-17
 */
@MyBatisDao
public interface GbjUserDao extends CrudDao<GbjUser> {
	
}