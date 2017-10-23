/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyTemplateMessage;

/**
 * 通知消息设置DAO接口
 * @author songshuqing
 * @version 2015-11-15
 */
@MyBatisDao
public interface DyTemplateMessageDao extends CrudDao<DyTemplateMessage> {
	
}