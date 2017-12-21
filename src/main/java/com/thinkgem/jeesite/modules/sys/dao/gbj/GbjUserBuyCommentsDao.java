/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyComments;

/**
 * 买标信息评论DAO接口
 * @author snnu
 * @version 2017-12-18
 */
@MyBatisDao
public interface GbjUserBuyCommentsDao extends CrudDao<GbjUserBuyComments> {
	
	//根据buy_id 买标评论信息
	 public List<GbjUserBuyComments>  getCommentsList(String buy_Id);
	 
	 //
	 public List<GbjUserBuyComments> findDomainArticleBuyCommentsList(int count);
}