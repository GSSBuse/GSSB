/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserComments;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidClient;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserBuyCommentsDao;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserCommentsDao;

/**
 * 买标信息评论Service
 * @author snnu
 * @version 2017-12-18
 */
@Service
@Transactional(readOnly = true)
public class GbjUserCommentsService extends CrudService<GbjUserCommentsDao, GbjUserComments> {
 
	@Autowired
	GbjUserCommentsDao gbjUserCommentsDao;
	
	public GbjUserComments get(String id) {
		return super.get(id);
	}
	
	/*public  List<GbjUserComments> getFrontCommentsList(String id) {
		return gbjUserCommentsDao.getFrontCommentsList(id);
	}*/
	
	
	
	public List<GbjUserComments> findList(GbjUserComments gbjUserComments) {
		return super.findList(gbjUserComments);
	}
	
	
	
	//根据buy_id 查询评论
	/*public Page<GbjUserComments>  findPages(Page<GbjUserComments> page,String buy_Id){
		
		return gbjUserBuyCommentsDao.getCommentsList(buy_Id);
		page.setList(gbjUserCommentsDao.getCommentsList(buy_Id));
		
		return page;
	}*/
	
	
	public Page<GbjUserComments> findPage(Page<GbjUserComments> page, GbjUserComments gbjUserComments) {
		return super.findPage(page, gbjUserComments);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjUserComments gbjUserComments) {
		super.save(gbjUserComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjUserComments gbjUserComments) {
		super.delete(gbjUserComments);
	}
	public List<GbjUserComments> findDomainGbjUserCommentsList(@RequestParam("id") String  id) {
	return gbjUserCommentsDao.findDomainGbjUserCommentsList(id);
}
	
}