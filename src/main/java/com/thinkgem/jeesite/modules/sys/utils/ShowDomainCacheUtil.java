package com.thinkgem.jeesite.modules.sys.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.base.Objects;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyDomainnameDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;

public abstract class ShowDomainCacheUtil implements Constant{

	private static DyDomainnameDao dyDomainnameDao = SpringContextHolder.getBean(DyDomainnameDao.class);
	
	public static void clearCache() {
		CacheUtils.remove(PAGE_DATA_CACHE, SHOW_DOMAIN_LIST_KEY);
		CacheUpdateFlagUtil.setUpdateTimestamp();
	}
	
	public static void syncDomainInfoToCache(DyDomainname dyDomain) {
		List<DyDomainname> domaimList = (List<DyDomainname>)CacheUtils.get(PAGE_DATA_CACHE, SHOW_DOMAIN_LIST_KEY);
		Set<String> domaimListSet = (Set<String>)CacheUtils.get(PAGE_DATA_CACHE, SHOW_DOMAIN_ID_SET_KEY);
		if (domaimList != null && domaimListSet != null) {
			String domainId = dyDomain.getId();
			if (!domaimListSet.contains(dyDomain.getId())) {
				if (Constant.DOMAIN_STATUS_03.equals(dyDomain.getStatus())) {
					// 添加到我要买列表
					domaimList.add(dyDomain);
					CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_LIST_KEY, domaimList);
					
					domaimListSet.add(domainId);
					CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_ID_SET_KEY, domaimListSet);
					CacheUpdateFlagUtil.setUpdateTimestamp();
				}
			} else {
				if (!Constant.DOMAIN_STATUS_03.equals(dyDomain.getStatus())) {
					// 从我要买列表中删除
					removeFromShowDomainList(domainId);
					domaimListSet.remove(domainId);
					CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_ID_SET_KEY, domaimListSet);
				} else {
					// 更新信息
					for (int i=0; i<domaimList.size(); i++) {
						DyDomainname dm =  domaimList.get(i);
			            if (Objects.equal(dyDomain.getId(), dm.getId())) {
			            	domaimList.set(i, dyDomain);
			            }
			        }
					CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_LIST_KEY, domaimList);
				}
				CacheUpdateFlagUtil.setUpdateTimestamp();
			}
		}
	}
	
	public static DyDomainname getDomainFromCache(String domainId) {
		List<DyDomainname> domaimList = (List<DyDomainname>)CacheUtils.get(PAGE_DATA_CACHE, SHOW_DOMAIN_LIST_KEY);
		if (domaimList != null) {
			for (Iterator<DyDomainname> it = domaimList.iterator(); it.hasNext();) {
				DyDomainname dm =  it.next();
	            if (Objects.equal(domainId, dm.getId())) {
	            	return dm;
	            }
	        }
		}
		return dyDomainnameDao.get(domainId);
	}
	
	public static void removeFromShowDomainList(String... domainIds) {
		synchronized (SHOW_DOMAIN_LIST_KEY) {
			List<DyDomainname> domaimList = (List<DyDomainname>)CacheUtils.get(PAGE_DATA_CACHE, SHOW_DOMAIN_LIST_KEY);
			if (domaimList != null) {
				for (Iterator<DyDomainname> it = domaimList.iterator(); it.hasNext();) {
					DyDomainname dm =  it.next();
		            if (ArrayUtils.contains(domainIds, dm.getId())) {
		            	it.remove();
		            }
		        }
				CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_LIST_KEY, domaimList);
				CacheUpdateFlagUtil.setUpdateTimestamp();
			}
        }
	}
	
	public static List<DyDomainname> getShowDomainList() {
		return (List<DyDomainname>)CacheUtils.get(PAGE_DATA_CACHE, SHOW_DOMAIN_LIST_KEY);
	}
	
	public static void setShowDomainList(List<DyDomainname> domaimList) {
		synchronized (SHOW_DOMAIN_LIST_KEY) {
			CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_LIST_KEY, domaimList);
			CacheUpdateFlagUtil.setUpdateTimestamp();
		}
	}
}
