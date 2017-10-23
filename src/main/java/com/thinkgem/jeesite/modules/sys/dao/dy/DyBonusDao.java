/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBonus;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BonusClient;
import com.thinkgem.jeesite.modules.wx.entity.domainname.ShareOrSecondBonus;

/**
 * 红包佣金信息DAO接口
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@MyBatisDao
public interface DyBonusDao extends CrudDao<DyBonus> {
	/**
	 * 根据域名Id，获取该域名的所有红包记录
	 * @param domainnameId 域名Id
	 * @return
	 */
	public List<BonusClient> getBonusListByDomainId(String domainnameId);
	
	/**
	 *  获取用户的分享红包和次高价红包记录，并按时间排序
	 * @param dyBonus
	 * @return
	 */
	public List<ShareOrSecondBonus> findShareOrsecondBonusList(DyBonus dyBonus);
}