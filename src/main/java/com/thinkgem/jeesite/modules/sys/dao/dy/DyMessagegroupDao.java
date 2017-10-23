/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessagegroup;

/**
 * 群发消息列表管理DAO接口
 * @author quanyf.fnst
 * @version 2015-10-19
 */
@MyBatisDao
public interface DyMessagegroupDao extends CrudDao<DyMessagegroup> {
	/**
	 * 将发送状态改为已发送
	 * @param id 消息ＩＤ
	 */
	public void updateTypeSend(@Param("id")String id);
}