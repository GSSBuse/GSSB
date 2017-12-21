/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserRewardComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldComments;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserRewardCommentsDao;

/**
 * 悬赏信息评论Service
 * @author snnu
 * @version 2017-12-21
 */
@Service
@Transactional(readOnly = true)
public class GbjUserRewardCommentsService extends CrudService<GbjUserRewardCommentsDao, GbjUserRewardComments> {

	@Autowired
	GbjUserRewardCommentsDao gbjUserRewardCommentsDao;
	
	public GbjUserRewardComments get(String id) {
		return super.get(id);
	}
	
	public List<GbjUserRewardComments> findList(GbjUserRewardComments gbjUserRewardComments) {
		return super.findList(gbjUserRewardComments);
	}
	
	//根据reward_id 查询评论
			public Page<GbjUserRewardComments>  findPages(Page<GbjUserRewardComments> page,String reward_Id){
				
				/*return gbjUserRewardCommentsDao.getCommentsList(reward_Id);*/
				page.setList(gbjUserRewardCommentsDao.getCommentsList(reward_Id));
				
				return page;
			}
	
	
	public Page<GbjUserRewardComments> findPage(Page<GbjUserRewardComments> page, GbjUserRewardComments gbjUserRewardComments) {
		return super.findPage(page, gbjUserRewardComments);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjUserRewardComments gbjUserRewardComments) {
		super.save(gbjUserRewardComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjUserRewardComments gbjUserRewardComments) {
		super.delete(gbjUserRewardComments);
	}
	
}