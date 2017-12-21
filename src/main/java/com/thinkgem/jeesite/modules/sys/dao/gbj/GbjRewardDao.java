/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjReward;

/**
 * 悬赏信息管理DAO接口
 * @author snnu
 * @version 2017-12-18
 */
@MyBatisDao
public interface GbjRewardDao extends CrudDao<GbjReward> {
	public List<GbjReward> findDomainRewardList(int count);
	
	//发布悬赏信息
	public int release(GbjReward gbjReward);
	
	//撤回悬赏信息
	public int withdraw(GbjReward gbjReward);
	
	
}