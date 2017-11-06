/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gb;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbSoldComments;

/**
 * 卖标评论管理DAO接口
 * @author 管理员
 * @version 2017-11-05
 */
@MyBatisDao
public interface GbSoldCommentsDao extends CrudDao<GbSoldComments> {
	
}