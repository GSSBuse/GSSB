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
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyComments;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidClient;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjUserBuyCommentsDao;

/**
 * 买标信息评论Service
 * @author snnu
 * @version 2017-12-18
 */
@Service
@Transactional(readOnly = true)
public class GbjUserBuyCommentsService extends CrudService<GbjUserBuyCommentsDao, GbjUserBuyComments> {
 
	@Autowired
	GbjUserBuyCommentsDao gbjUserBuyCommentsDao;
	
	public GbjUserBuyComments get(String id) {
		return super.get(id);
	}
	
	public List<GbjUserBuyComments> findList(GbjUserBuyComments gbjUserBuyComments) {
		return super.findList(gbjUserBuyComments);
	}
	
	
	
	//根据buy_id 查询评论
	public Page<GbjUserBuyComments>  findPages(Page<GbjUserBuyComments> page,String buy_Id){
		
		/*return gbjUserBuyCommentsDao.getCommentsList(buy_Id);*/
		page.setList(gbjUserBuyCommentsDao.getCommentsList(buy_Id));
		
		return page;
	}
	
	public Page<GbjUserBuyComments> findPage(Page<GbjUserBuyComments> page, GbjUserBuyComments gbjUserBuyComments) {
		return super.findPage(page, gbjUserBuyComments);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjUserBuyComments gbjUserBuyComments) {
		super.save(gbjUserBuyComments);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjUserBuyComments gbjUserBuyComments) {
		super.delete(gbjUserBuyComments);
	}
	
}