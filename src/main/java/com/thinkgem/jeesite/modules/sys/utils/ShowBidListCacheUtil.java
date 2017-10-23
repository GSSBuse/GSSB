package com.thinkgem.jeesite.modules.sys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyBidhistoryDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidCashInfo;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidClient;

public abstract class ShowBidListCacheUtil implements Constant {
	
	private static Logger LOGGER = Logger.getLogger(ShowBidListCacheUtil.class);
	
	private static DyBidhistoryDao dyBidhistoryDao = SpringContextHolder.getBean(DyBidhistoryDao.class);
	
	private static Map<String, Object> bidsLocks = new HashMap<String, Object>();
	private static Object getLock(String domainId) {
		if (bidsLocks.containsKey(domainId)) {
			return bidsLocks.get(domainId);
		} else {
			Object o = new Object();
			bidsLocks.put(domainId, o);
			return o;
		}
	}
	
	public static void clearCache(String domainId) {
		synchronized (getLock(domainId)) {
			CacheUtils.remove(PAGE_DATA_CACHE, SHOW_DOMAIN_BIDS_KEY_PREFIX + domainId);
			CacheUpdateFlagUtil.setUpdateTimestamp();
		}
	}
	
	/**
	 * 根据域名ID获取域名最新价
	 * @param domainId 域名ID
	 * @return 域名最新价
	 */
	public static DyBidhistory getMaxBidAmount(String domainId) {
		List<BidClient> bidList = getBidList(domainId);
		if (bidList != null) {
			if (bidList.size() > 0) {
				BidClient client = bidList.get(0);
				DyBidhistory his = new DyBidhistory();
				try {
					org.springframework.beans.BeanUtils.copyProperties(client, his, "bidTime");
					his.setId(client.getBidhistoryId());
					his.setBidTime(DateUtils.parseDate(client.getBidTime()));
                } catch (Exception e) {
                	LOGGER.error("拷贝缓存出错", e);
                }
				his.setDomainnameId(domainId);
				
				return his;
			} else {
				return null;
			}
		}
		return dyBidhistoryDao.getMaxBidAmount(domainId);
	}
	/**
	 * 获取域名的最高两条出价会员信息记录
	 * @param domainnameId 域名Id
	 * @return
	 */
	public static List<BidCashInfo> findSecondHighList(String domainnameId){
		return dyBidhistoryDao.findSecondHighList(domainnameId);
	}
	/**
	 * 根据域名ID，出价记录ID查询出价表获取大于出价金额的出价记录
	 * @param domainnameId 域名ID
	 * @param bidhistoryId 出价记录ID
	 * @return
	 */
	public static List<BidClient> findListForAmounter(String domainId, String bidhistoryId){
		List<BidClient> bidList = getBidList(domainId);
		if (bidList != null) {
			List<BidClient> newList = Lists.newArrayList();
			for (BidClient c : bidList) {
		        if (Objects.equal(c.getBidhistoryId(), bidhistoryId)) {
		        	break;
		        }
		        newList.add(c);
	        }
			return newList;
		}
		return dyBidhistoryDao.findListForAmounter(domainId, bidhistoryId);
	}
	
	/**
	 * 根据域名ID查询出价表，获取域名的最新出价
	 * @param domainId 域名ID
	 * @return
	 */
	public static Long getMaxAmountById(String domainId){
		List<BidClient> bidList = getBidList(domainId);
		if (bidList != null) {
			if (bidList.size() > 0) {
				return bidList.get(0).getBidAmount();
			} else {
				return null;
			}
		}
		return dyBidhistoryDao.getMaxAmountById(domainId);
	}
	
	public static int updateProxyAmountById(String domainId, String bidHistoryId, Long proxyAmount) {
		List<BidClient> bidList = getBidList(domainId);
		if (bidList != null) {
			for (BidClient c : bidList) {
		        if (Objects.equal(c.getBidhistoryId(), bidHistoryId)) {
		        	c.setProxyAmount(proxyAmount);
		        	CacheUpdateFlagUtil.setUpdateTimestamp();
		        	break;
		        }
	        }
		}
		return dyBidhistoryDao.updateProxyAmountById(bidHistoryId, proxyAmount);
	}
	
//	public static void deleteAttention(DyAttention dyAttention) {
//		synchronized (getLock(dyAttention.getDomainnameId())) {
//			List<DyClient> attentionList =  (List<DyClient>)CacheUtils.get(PAGE_DATA_CACHE,SHOW_DOMAIN_ATTENTIONS_KEY_PREFIX + dyAttention.getDomainnameId());
//			if (attentionList != null) {
//				for (Iterator<DyClient> it = attentionList.iterator(); it.hasNext();) {
//					DyClient atten =  it.next();
//		            if (Objects.equal(dyAttention.getClientId(), atten.getId())) {
//		            	it.remove();
//		            	break;
//		            }
//		        }
//				CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_ATTENTIONS_KEY_PREFIX + dyAttention.getDomainnameId(), attentionList);
//			}
//        }
//	}
	
	public static List<BidClient> getBidList(String domainId) {
			List<BidClient> bidList = (List<BidClient>)CacheUtils.get(PAGE_DATA_CACHE, SHOW_DOMAIN_BIDS_KEY_PREFIX + domainId);
			return bidList;
	}
	
	public static void setBidList(String domainId, List<BidClient> list) {
		CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_BIDS_KEY_PREFIX + domainId, list);
		CacheUpdateFlagUtil.setUpdateTimestamp();
	}
	
	public static void addBid2List(String domainId, BidClient client) {
		synchronized (getLock(domainId)) {
			List<BidClient> bidList = getBidList(domainId);
			if (bidList == null) {
				bidList = new ArrayList<BidClient>();
			}
			bidList.add(0, client);
			setBidList(domainId, bidList);
			
			DyBidhistory dyBidHist = new DyBidhistory();
			
		}
	}
	
	public static void addBid2ListDao(String domainId, DyBidhistory dyBidhistory) {
		//synchronized (getLock(domainId)) {
		dyBidhistoryDao.insert(dyBidhistory);
		BidClient bidClient = new BidClient();
		bidClient.setDomainId(dyBidhistory.getDomainnameId());
		bidClient.setBidhistoryId(dyBidhistory.getId());
		
		List<BidClient> clients = dyBidhistoryDao.bidhistoryList(bidClient);
			
		if (clients.size() > 0) {
			addBid2List(domainId, clients.get(0));
		}
		//}
	}
}
