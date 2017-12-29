/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjSold;

/**
 * 卖标信息管理DAO接口
 * @author snnu
 * @version 2017-12-16
 */
@MyBatisDao
public interface GbjSoldDao extends CrudDao<GbjSold> {
	public List<GbjSold> findDomainSoldList(int count);
	
	
	//卖标点赞
	public int updateCount(GbjSold gbjSold);
	//发布卖标信息
	public int release(GbjSold gbjSold);
			
	//撤回卖标信息
	public int withdraw(GbjSold gbjSold);
	
}