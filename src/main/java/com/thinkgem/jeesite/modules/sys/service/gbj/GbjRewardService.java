/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjReward;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjRewardDao;

/**
 * 悬赏信息管理Service
 * @author snnu
 * @version 2017-11-20
 */
@Service
@Transactional(readOnly = true)
public class GbjRewardService extends CrudService<GbjRewardDao, GbjReward> {

	public GbjReward get(String id) {
		return super.get(id);
	}
	
	public List<GbjReward> findList(GbjReward gbjReward) {
		return super.findList(gbjReward);
	}
	
	public Page<GbjReward> findPage(Page<GbjReward> page, GbjReward gbjReward) {
		return super.findPage(page, gbjReward);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjReward gbjReward) {
		super.save(gbjReward);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjReward gbjReward) {
		super.delete(gbjReward);
	}
	
}