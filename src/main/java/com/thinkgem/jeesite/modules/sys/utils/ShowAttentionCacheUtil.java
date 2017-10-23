package com.thinkgem.jeesite.modules.sys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.base.Objects;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAttention;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;

public abstract class ShowAttentionCacheUtil implements Constant {
	
	private static Map<String, ReadWriteLock> attentionsLocks = new HashMap<String, ReadWriteLock>();
	private static ReadWriteLock getLock(String domainId) {
		if (attentionsLocks.containsKey(domainId)) {
			return attentionsLocks.get(domainId);
		} else {
			ReadWriteLock o = new ReentrantReadWriteLock();
			attentionsLocks.put(domainId, o);
			return o;
		}
	}
	
	public static void deleteAttention(DyAttention dyAttention) {
		try {
			getLock(dyAttention.getDomainnameId()).writeLock().lock();
			
			List<DyClient> attentionList =  (List<DyClient>)CacheUtils.get(PAGE_DATA_CACHE,SHOW_DOMAIN_ATTENTIONS_KEY_PREFIX + dyAttention.getDomainnameId());
			if (attentionList != null) {
				for (Iterator<DyClient> it = attentionList.iterator(); it.hasNext();) {
					DyClient atten =  it.next();
		            if (Objects.equal(dyAttention.getClientId(), atten.getId())) {
		            	it.remove();
		            	break;
		            }
		        }
				CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_ATTENTIONS_KEY_PREFIX + dyAttention.getDomainnameId(), attentionList);
			}
			
		} finally {
			CacheUpdateFlagUtil.setUpdateTimestamp();
			getLock(dyAttention.getDomainnameId()).writeLock().unlock();
		}
	}
	
	public static List<DyClient> getAttetionList(String domainId) {
		try {
			getLock(domainId).readLock().lock();
			
			List<DyClient> attentionList = (List<DyClient>)CacheUtils.get(PAGE_DATA_CACHE, SHOW_DOMAIN_ATTENTIONS_KEY_PREFIX + domainId);
			return attentionList;
		} finally {
			getLock(domainId).readLock().unlock();
		}
	}
	
	public static void setAttetionList(String domainId, List<DyClient> list) {
		try {
			getLock(domainId).writeLock().lock();
			CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_ATTENTIONS_KEY_PREFIX + domainId, list);
		} finally {
			CacheUpdateFlagUtil.setUpdateTimestamp();
			getLock(domainId).writeLock().unlock();
		}
	}
	
	public static void addAttetion2List(String domainId, DyClient client) {
		try {
			getLock(domainId).writeLock().lock();
			List<DyClient> attentionList = getAttetionList(domainId);
			if (attentionList == null) {
				attentionList = new ArrayList<DyClient>();
			}
			attentionList.add(client);
			CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_ATTENTIONS_KEY_PREFIX + domainId, attentionList);
		} finally {
			CacheUpdateFlagUtil.setUpdateTimestamp();
			getLock(domainId).writeLock().unlock();
		}
	}
}
