/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyNewsDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyNews;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 最新消息管理Service
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Service
@Transactional(readOnly = true)
public class DyNewsService extends CrudService<DyNewsDao, DyNews> implements Constant {

	public DyNews get(String id) {
		return super.get(id);
	}
	
	public List<DyNews> findList(DyNews dyNews) {
		return super.findList(dyNews);
	}
	
	public Page<DyNews> findPage(Page<DyNews> page, DyNews dyNews) {
		return super.findPage(page, dyNews);
	}
	
	@Transactional(readOnly = false)
	public void save(DyNews dyNews) {
		super.save(dyNews);
	}
	
	@Transactional(readOnly = false)
	public void delete(DyNews dyNews) {
		super.delete(dyNews);
	}

	/**
	 * 将相关域名记录中除登录会员以外的记录的计数字段加一
	 * @param domainId 域名ID
	 * @param clientId 登录会员ID
	 */
	@Transactional(readOnly = false)
	public void updateNewBidCount(String domainId, String clientId) {
		DyNews news = new DyNews();
		news.setClientId(clientId);
		news.setDomainnameId(domainId);
		news.setDelFlag(DyNews.DEL_FLAG_NORMAL);
		List<DyNews> nList = dao.findList(news);
		if (nList.isEmpty()) {
			// 记录不存在的场合
			news.preInsert(UserUtils.get(DySysUtils.getCurrentDyClient().getBrokerId()));
//			news.setIsNewRecord(true);
			news.setNewBidCount(STRING_0);
			dao.insert(news);
		}
		dao.updateNewBidCount(domainId, clientId);
	}
	
	/**
	 * 根据会员ID查询最新消息表，获取该会员相关域名的新消息计数不为0的记录的数量
	 * @param clientId 会员ID
	 * @return
	 */
	public int messageService(String clientId) {
		return dao.getMessageNum(clientId);
	}

	/**
	 * 根据ID删除记录
	 * @param id 最新消息ID
	 */
	@Transactional(readOnly = false)
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	/**
	 * 清除新出价标记
	 * @param news 最新消息管理Entity
	 */
	@Transactional(readOnly = false)
	public void clearBidCount(DyNews news) {
		dao.clearBidCount(news);
	}

	/**
	 * 清除结拍域名的相关新消息记录
	 * @return 操作记录数
	 */
	@Transactional(readOnly = false)
	public int deleteOverDomainNews() {
		return dao.deleteOverDomainNews();
	}
}