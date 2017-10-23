/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAccessRecord;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;

/**
 * 访问记录管理DAO接口
 * @author quanyf.fnst
 * @version 2015-11-04
 */
@MyBatisDao
public interface DyAccessRecordDao extends CrudDao<DyAccessRecord> {
	/**
	 * 交易结束时，获得分享佣金的会员Id
	 * @param domainId 域名Id
	 * @param clintId 买家Id
	 * @return
	 */
	public String getShareClientId(String domainId , String clintId);
	/**
	 * 交易结束时，获得分享佣金的会员Id
	 * @param domainId 域名Id
	 * @param clintId 买家Id
	 * @return
	 */
	public DyClient getShareClient(String domainId , String clintId);
}