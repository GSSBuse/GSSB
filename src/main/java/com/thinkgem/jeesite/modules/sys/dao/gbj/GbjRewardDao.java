/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjReward;

/**
 * 悬赏信息管理DAO接口
 * @author snnu
 * @version 2017-11-20
 */
@MyBatisDao
public interface GbjRewardDao extends CrudDao<GbjReward> {
	
}