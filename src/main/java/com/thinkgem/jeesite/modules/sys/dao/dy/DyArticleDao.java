/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao.dy;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyArticle;

/**
 * 资讯文章管理DAO接口
 * @author quanyf.fnst
 * @version 2015-10-19
 */
@MyBatisDao
public interface DyArticleDao extends CrudDao<DyArticle> {
	
}