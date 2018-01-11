package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.gbj.SoldArticleListDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.SoldArticleList;

@Service
@Transactional(readOnly = true)
public class SoldArticleListService extends CrudService<SoldArticleListDao, SoldArticleList> {
	@Autowired
	SoldArticleListDao soldarticleListdao;

	public List<SoldArticleList> findDomainSoldArticleList(@Param(value = "start") int start,
			@Param(value = "end") int end) {
		return soldarticleListdao.findDomainSoldArticleList(start, end);
	}

	public List<SoldArticleList> findDomainUserSoldArticleList(@RequestParam("id") String id) {
		// TODO Auto-generated method stub
		return soldarticleListdao.findDomainUserSoldArticleList(id);
	}

	public int findDomainSoldArticlePageCount() {
		return soldarticleListdao.findDomainSoldArticlePageCount();
	}
}
