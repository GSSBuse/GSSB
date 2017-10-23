/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Objects;
import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyAttentionDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyClientDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyNewsDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAttention;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyNews;
import com.thinkgem.jeesite.modules.sys.utils.ShowAttentionCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 会员关注管理Service
 * @author shenzb.fnst
 * @version 2015-10-12
 */
@Service
@Transactional(readOnly = true)
public class DyAttentionService extends CrudService<DyAttentionDao, DyAttention> implements Constant{

	@Autowired
	private DyAttentionDao dyAttentionDao;   // 会员关注管理DAO接口

	@Autowired
	private DyNewsDao dyNewsDao;             // 最新消息管理DAO接口

	@Autowired
	private DyClientDao dyClientDao;         // 会员信息管理DAO接口
	

	public DyAttention get(String id) {
		return super.get(id);
	}
	
	public List<DyAttention> findList(DyAttention dyAttention) {
		return super.findList(dyAttention);
	}
	
	public Page<DyAttention> findPage(Page<DyAttention> page, DyAttention dyAttention) {
		return super.findPage(page, dyAttention);
	}

	/**
	 * 根据域名ID获取关注会员页
	 * @param attentionPage 会员页
	 * @param domainId 域名ID
	 * @return
	 */
//	public Page<DyClient> findAttentionPage(Page<DyBidhistory> dyBidhistoryPage, String domainId) {
//		DyBidhistory dyBidhistory = new DyBidhistory();
//		dyBidhistory.setDomainnameId(domainId);
//		dyBidhistory.setPage(dyBidhistoryPage);
//		Page<DyClient> attentionPage = new Page<DyClient>();
//		attentionPage.setList(dao.findAttentionList(dyBidhistory));
//		attentionPage.setCount(dyBidhistoryPage.getCount());
//		return attentionPage;
//	}

	/**
	 * 根据域名ID获取关注会员列表
	 * @param domainId 域名ID
	 * @return
	 */
	public List<DyClient> findAttentionList(String domainId) {
		List<DyClient> attentionList = ShowAttentionCacheUtil.getAttetionList(domainId);
		
		if (attentionList == null) {
			DyBidhistory dyBidhistory = new DyBidhistory();
			dyBidhistory.setDomainnameId(domainId);
			attentionList = dao.findAttentionList(dyBidhistory);
			ShowAttentionCacheUtil.setAttetionList(domainId, attentionList);
		}
		return attentionList;
	}

	@Transactional(readOnly = false)
	public void save(DyAttention dyAttention) {
		boolean isNew = dyAttention.getIsNewRecord();
		super.save(dyAttention);
		if (isNew) {
			DyClient dyc = dyClientDao.get(dyAttention.getClientId());
			ShowAttentionCacheUtil.addAttetion2List(dyAttention.getDomainnameId(), dyc);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(DyAttention dyAttention) {
		ShowAttentionCacheUtil.deleteAttention(dyAttention);
		super.delete(dyAttention);
	}
	
	public boolean isAttented(DyAttention entity) {
		List<DyClient> attentionList = ShowAttentionCacheUtil.getAttetionList(entity.getDomainnameId());
		
		if (attentionList != null) {
			for (DyClient attention : attentionList) {
	            if (Objects.equal(entity.getClientId(), attention.getId())) {
	            	return true;
	            }
            }
			return false;
		} 
		
		return !dyAttentionDao.findList(entity).isEmpty();
	}

	
	public boolean try2Follow(DyClient currClient, String domainId) {
		
		DyAttention entity = new DyAttention();
		entity.setClientId(currClient.getId());
		entity.setDomainnameId(domainId);
		entity.setDelFlag(DyAttention.DEL_FLAG_NORMAL);
		
		if (!isAttented(entity)) {
			entity.preInsert(UserUtils.get(currClient.getBrokerId()));
			entity.setIsNewRecord(true);
			//JedisUtils.listObjectAdd(Constant.SHOW_DOMAIN_ATTENTIONS_KEY_PREFIX + domainId, entity);
//			dyAttentionDao.insert(entity);
			save(entity);
			return true;
		} else {
			this.delete(entity);
			return false;
		}
	}
	
	private static Map<String, Object> attLocks = new HashMap<String, Object>();
	private static Object getLock(String domainId) {
		if (attLocks.containsKey(domainId)) {
			return attLocks.get(domainId);
		} else {
			Object o = new Object();
			attLocks.put(domainId, o);
			return o;
		}
	}

	/**
	 * 当前登录会员根据域名ID对域名进行关注/取消关注
	 * @param domainId 域名ID
	 * @return 操作结果
	 */
	@Transactional(readOnly = false)
	public AjaxResult follow(String domainId) {
		// 关注同步锁
		synchronized (getLock(domainId)) {
			DyClient currClient = DySysUtils.getCurrentDyClient();
	
			try {
				boolean attended = try2Follow(currClient, domainId);
	
				DyNews news = new DyNews();
				news.setClientId(currClient.getId());
				news.setDomainnameId(domainId);
				news.setDelFlag(DyNews.DEL_FLAG_NORMAL);
				List<DyNews> nList = dyNewsDao.findList(news);
				if (DySysUtils.isParticipated(currClient, new DyDomainname(domainId))) {
					// 如果登录会员参与该域名则增加最新消息记录
					if (nList.isEmpty()) {
						// 最新消息无记录
						news.preInsert(UserUtils.get(DySysUtils.getCurrentDyClient().getBrokerId()));
	//					news.setIsNewRecord(true);
						news.setNewBidCount(STRING_0);
						dyNewsDao.insert(news);
					}
				} else {
					if (!nList.isEmpty()) {
						// 最新消息有记录则删除
						dyNewsDao.deleteById(nList.get(0).getId());
					}
				}
				AjaxResult ar = AjaxResult.makeSuccess("");
				ar.getData().put("attended", attended);
				return ar;
			} catch (Exception e) {
				logger.error("关注或者取消关注失败", e);
				return AjaxResult.makeError("关注或者取消关注失败");
			}

        }
	}

}