/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;

/**
 * 买标信息DAO接口
 * @author snnu
 * @version 2017-12-17
 */
@MyBatisDao
public interface GbjBuyDao extends CrudDao<GbjBuy> {
	public List<GbjBuy> findDomainBuyList(int count);
	public List<GbjBuy> findDomainArticleBuyList(int count);
}