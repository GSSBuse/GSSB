/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjRewardContents;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjRewardContentsDao;

/**
 * 悬赏信息评论Service
 * @author snnu
 * @version 2017-11-20
 */
@Service
@Transactional(readOnly = true)
public class GbjRewardContentsService extends CrudService<GbjRewardContentsDao, GbjRewardContents> {

	public GbjRewardContents get(String id) {
		return super.get(id);
	}
	
	public List<GbjRewardContents> findList(GbjRewardContents gbjRewardContents) {
		return super.findList(gbjRewardContents);
	}
	
	public Page<GbjRewardContents> findPage(Page<GbjRewardContents> page, GbjRewardContents gbjRewardContents) {
		return super.findPage(page, gbjRewardContents);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjRewardContents gbjRewardContents) {
		super.save(gbjRewardContents);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjRewardContents gbjRewardContents) {
		super.delete(gbjRewardContents);
	}
	
}