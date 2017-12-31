package com.thinkgem.jeesite.modules.sys.service.gbj;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.gbj.ArticleListDao;
import com.thinkgem.jeesite.modules.sys.dao.gbj.BuyArticleListDao;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjBuyDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.ArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.BuyArticleList;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;
@Service
@Transactional(readOnly = true)
public class BuyArticleListService extends CrudService<BuyArticleListDao, BuyArticleList> {
	@Autowired
	BuyArticleListDao buyarticleListdao;
	public List<BuyArticleList> findDomainBuyArticleList(@Param(value="counts") String count) {
		return buyarticleListdao.findDomainBuyArticleList(Integer.parseInt(count));
	}
	
	//个人中心获取个人买标信息
	public List<BuyArticleList> findDomainUserBuyArticleList(@RequestParam("id") String id) {
		return buyarticleListdao.findDomainUserBuyArticleList(id);
	}
	 
}

