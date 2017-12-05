/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gb;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbBuy;

/**
 * 买标信息管理DAO接口
 * @author 管理员
 * @version 2017-11-02
 */
@MyBatisDao
public interface GbBuyDao extends CrudDao<GbBuy> {
	
	/**
	 * 查询首页我要买标数据列表
	 * @param entity
	 * @return
	 */
	public List<GbBuy> findDomainBuyList(int count);
}