package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.gbj.ArticleListDao;
import com.thinkgem.jeesite.modules.sys.dao.gbj.BuyArticleListDao;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjBuyDao;
import com.thinkgem.jeesite.modules.sys.dao.gbj.SoldArticleListDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.BuyArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.SoldArticleList;
@Service
@Transactional(readOnly = true)
public class SoldArticleListService extends CrudService<SoldArticleListDao, SoldArticleList> {
	@Autowired
	SoldArticleListDao soldarticleListdao;
	public List<SoldArticleList> findDomainSoldArticleList(@Param(value="counts") String count) {
		return soldarticleListdao.findDomainSoldArticleList(Integer.parseInt(count));
	}
	
}

