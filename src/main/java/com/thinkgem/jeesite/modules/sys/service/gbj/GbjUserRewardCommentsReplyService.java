/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserRewardCommentsReplyDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserRewardCommentsReply;

/**
 * 悬赏信息评论回复表Service
 * 
 * @author snnu
 * @version 2018-01-18
 */
@Service
@Transactional(readOnly = true)
public class GbjUserRewardCommentsReplyService
		extends CrudService<GbjUserRewardCommentsReplyDao, GbjUserRewardCommentsReply> {

	@Autowired
	GbjUserRewardCommentsReplyDao gbjUserRewardCommentsReplyDao;

	public GbjUserRewardCommentsReply get(String id) {
		return super.get(id);
	}

	public List<GbjUserRewardCommentsReply> findList(GbjUserRewardCommentsReply gbjUserRewardCommentsReply) {
		return super.findList(gbjUserRewardCommentsReply);
	}

	public Page<GbjUserRewardCommentsReply> findPage(Page<GbjUserRewardCommentsReply> page,
			GbjUserRewardCommentsReply gbjUserRewardCommentsReply) {
		return super.findPage(page, gbjUserRewardCommentsReply);
	}

	@Transactional(readOnly = false)
	public void save(GbjUserRewardCommentsReply gbjUserRewardCommentsReply) {
		super.save(gbjUserRewardCommentsReply);
	}

	@Transactional(readOnly = false)
	public void delete(GbjUserRewardCommentsReply gbjUserRewardCommentsReply) {
		super.delete(gbjUserRewardCommentsReply);
	}

	public List<GbjUserRewardCommentsReply> findDomainArticleRewardReplyCommentsList(@RequestParam("id") String id) {
		return gbjUserRewardCommentsReplyDao.findDomainArticleRewardReplyCommentsList(id);
	}

}