/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyNews;

/**
 * 最新消息管理DAO接口
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@MyBatisDao
public interface DyNewsDao extends CrudDao<DyNews> {

	/**
	 * 将相关域名记录中除登录会员以外的记录的计数字段加一
	 * @param domainId 域名ID
	 * @param clientId 登录会员ID
	 */
	void updateNewBidCount(String domainnameId, String clientId);

	/**
	 * 根据会员ID查询最新消息表，获取该会员相关域名的新消息计数不为0的记录的数量
	 * @param clientId 会员ID
	 * @return 
	 */
	public int getMessageNum(String clientId);

	/**
	 * 根据ID删除记录
	 * @param id 最新消息ID
	 */
	void deleteById(String id);

	/**
	 * 清除新出价标记
	 * @param news 最新消息管理Entity
	 */
	void clearBidCount(DyNews news);

	
	/**
	 * 清除结拍域名的相关新消息记录
	 * @return 操作记录数
	 */
	int deleteOverDomainNews();
}