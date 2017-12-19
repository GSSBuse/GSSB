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
import com.thinkgem.jeesite.modules.sys.dao.gbj.RewardArticleListDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.BuyArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
import com.thinkgem.jeesite.modules.sys.entity.gbj.RewardArticleList;
@Service
@Transactional(readOnly = true)
public class RewardArticleListService extends CrudService<RewardArticleListDao, RewardArticleList> {
	@Autowired
	RewardArticleListDao rewardarticleListdao;
	public List<RewardArticleList> findDomainRewardArticleList(@Param(value="counts") String count) {
		return rewardarticleListdao.findDomainRewardArticleList(Integer.parseInt(count));
	}
	
}

