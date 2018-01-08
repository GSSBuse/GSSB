/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjTagDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjTag;

/**
 * 标签类型Service
 * 
 * @author snnu
 * @version 2018-01-08
 */
@Service
@Transactional(readOnly = true)
public class GbjTagService extends CrudService<GbjTagDao, GbjTag> {

	@Autowired
	GbjTagDao gbjTagDao;

	public GbjTag get(String id) {
		return super.get(id);
	}

	public List<GbjTag> findList(GbjTag gbjTag) {
		return super.findList(gbjTag);
	}

	public Page<GbjTag> findPage(Page<GbjTag> page, GbjTag gbjTag) {
		return super.findPage(page, gbjTag);
	}

	@Transactional(readOnly = false)
	public void save(GbjTag gbjTag) {
		super.save(gbjTag);
	}

	@Transactional(readOnly = false)
	public void delete(GbjTag gbjTag) {
		super.delete(gbjTag);
	}

	public List<GbjTag> findDomainTagList(@Param(value = "counts") String count) {
		return gbjTagDao.findDomainTagList(Integer.parseInt(count));
	}

}