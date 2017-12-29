/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserBuyComments;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjUserSoldComments;

/**
 * 卖标信息评论DAO接口
 * @author 管理员
 * @version 2017-12-21
 */
@MyBatisDao
public interface GbjUserSoldCommentsDao extends CrudDao<GbjUserSoldComments> {
	
	//根据sold_id查询卖标评论信息
	public List<GbjUserSoldComments>  getCommentsList(String sold_Id);
	public List<GbjUserSoldComments> findDomainArticleSoldCommentsList(String id);
}