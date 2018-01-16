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
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserBuyCommentsReplyDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyCommentsReply;

/**
 * 买标信息评论回复管理Service
 * 
 * @author snnu
 * @version 2018-01-15
 */
@Service
@Transactional(readOnly = true)
public class GbjUserBuyCommentsReplyService extends CrudService<GbjUserBuyCommentsReplyDao, GbjUserBuyCommentsReply> {

	@Autowired
	GbjUserBuyCommentsReplyDao gbjUserBuyCommentsReplyDao;

	public GbjUserBuyCommentsReply get(String id) {
		return super.get(id);
	}

	public List<GbjUserBuyCommentsReply> findList(GbjUserBuyCommentsReply gbjUserBuyCommentsReply) {
		return super.findList(gbjUserBuyCommentsReply);
	}

	public Page<GbjUserBuyCommentsReply> findPage(Page<GbjUserBuyCommentsReply> page,
			GbjUserBuyCommentsReply gbjUserBuyCommentsReply) {
		return super.findPage(page, gbjUserBuyCommentsReply);
	}

	@Transactional(readOnly = false)
	public void save(GbjUserBuyCommentsReply gbjUserBuyCommentsReply) {
		super.save(gbjUserBuyCommentsReply);
	}

	@Transactional(readOnly = false)
	public void delete(GbjUserBuyCommentsReply gbjUserBuyCommentsReply) {
		super.delete(gbjUserBuyCommentsReply);
	}

	public List<GbjUserBuyCommentsReply> findDomainArticleBuyReplyCommentsList(@RequestParam("id") String id) {
		return gbjUserBuyCommentsReplyDao.findDomainArticleBuyReplyCommentsList(id);
	}

}