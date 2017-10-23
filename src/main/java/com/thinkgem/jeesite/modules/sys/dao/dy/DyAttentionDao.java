/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAttention;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;

/**
 * 会员关注管理DAO接口
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@MyBatisDao
public interface DyAttentionDao extends CrudDao<DyAttention> {

	/**
	 * 根据域名ID获取关注会员列表
	 * @param dyBidhistory 关注实体 
	 * @return 关注会员列表
	 */
	List<DyClient> findAttentionList(DyBidhistory dyBidhistory);

}