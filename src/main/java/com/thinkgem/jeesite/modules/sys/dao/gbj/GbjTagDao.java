/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTag;

/**
 * 标签类型DAO接口
 * 
 * @author snnu
 * @version 2018-01-08
 */
@MyBatisDao
public interface GbjTagDao extends CrudDao<GbjTag> {
	public List<GbjTag> findDomainTagList(int count);
}