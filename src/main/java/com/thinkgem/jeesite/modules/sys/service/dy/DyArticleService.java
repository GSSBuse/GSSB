/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyArticle;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyArticleDao;

/**
 * 资讯文章管理Service
 * @author quanyf.fnst
 * @version 2015-10-19
 */
@Service
@Transactional(readOnly = true)
public class DyArticleService extends CrudService<DyArticleDao, DyArticle> {

	public DyArticle get(String id) {
		return super.get(id);
	}
	
	public List<DyArticle> findList(DyArticle dyArticle) {
		return super.findList(dyArticle);
	}
	
	public Page<DyArticle> findPage(Page<DyArticle> page, DyArticle dyArticle) {
		return super.findPage(page, dyArticle);
	}
	
	@Transactional(readOnly = false)
	public void save(DyArticle dyArticle) {
		super.save(dyArticle);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyArticle dyArticle) {
		super.delete(dyArticle);
	}
	
}