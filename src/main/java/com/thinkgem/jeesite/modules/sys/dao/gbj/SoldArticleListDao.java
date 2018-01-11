package com.thinkgem.jeesite.modules.sys.dao.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.SoldArticleList;

@MyBatisDao
public interface SoldArticleListDao extends CrudDao<SoldArticleList> {
	public List<SoldArticleList> findDomainSoldArticleList(@Param(value = "start") int start,
			@Param(value = "end") int end);

	public List<SoldArticleList> findDomainUserSoldArticleList(String id);

	public int findDomainSoldArticlePageCount();
}
