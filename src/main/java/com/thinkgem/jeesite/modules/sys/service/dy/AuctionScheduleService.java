package com.thinkgem.jeesite.modules.sys.service.dy;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyClientDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyDomainnameDao;
import com.thinkgem.jeesite.modules.sys.dao.dy.DyFinanceDao;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.FollowInfoToMsg;
import com.thinkgem.jeesite.modules.sys.entity.dy.TransactionInformation;
import com.thinkgem.jeesite.modules.sys.utils.ShowDomainCacheUtil;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

@Service
@Lazy(false)
public class AuctionScheduleService implements Constant {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DyDomainnameDao dyDomainnameDao;
	
	@Autowired
	private DyBidhistoryService dyBidhistoryService;
	
	@Autowired
	private DyDomainnameService dyDomainnameService;
	
	@Autowired
	private DyClientDao dyClientDao;
	
	@Autowired
	private DyFinanceDao dyFinanceDao;

	@Autowired
	private DyNewsService dyNewsService;                 // 最新消息管理Service

	/**
	 * 每隔1分钟触发一次
	 */
	@SuppressWarnings("unused")
	//@Scheduled(cron = "0 0/1 * * * ?")
	public void auctionResultsSchedule() throws ServiceException{
		//域名状态检测begin
		// TODO 流拍状态设置，以下三步待检
		//买家违约
		List<TransactionInformation> list1 = dyBidhistoryService.domainnameBuyerAndSeller(DOMAIN_STATUS_11,null);//List :所有买家违约记录信息
		if (!list1.isEmpty()){
			for(int i=0;i<list1.size();i++){
				try {
					dyBidhistoryService.buyerBreachHandle(list1.get(i));
				} catch (Exception e) {
					logger.debug(e.toString());
				}
			}
		}
		
		//卖家违约
		List<TransactionInformation> list2 = dyBidhistoryService.domainnameBuyerAndSeller(DOMAIN_STATUS_12,null);//List :所有卖家违约记录信息
		if (!list2.isEmpty()){
			for(int i=0;i<list2.size();i++){
				try {
					dyBidhistoryService.sellerBreachHandle(list2.get(i));
				} catch (Exception e) {
					logger.debug(e.toString());
				}
			}
		}
		
		//默认买家收到域名，提示买家/卖家联系经纪人
		List<TransactionInformation> list3 = dyBidhistoryService.domainnameBuyerAndSeller(DOMAIN_STATUS_13,null);//List :所有13状态，已过确认操作时限的记录信息
		if (!list3.isEmpty()){
			for(int i=0;i<list3.size();i++){
				try {
					dyBidhistoryService.notConfirmReceiveDomainname(list3.get(i));
				} catch (Exception e) {
					logger.debug(e.toString());
				}
			}
		}

		/*域名状态有03变成11需要设置一个操作时限	,且要考虑以下几种情况：
			*无人出价--->状态改为23流拍、
			*有人出价且无保留价--->11成交、
			*有人出价且最高价低于保留价--->23流拍、
			*有人出价且最高价高于保留价--->11成交	
		 * */
		//无人出价--->状态改为23流拍、且向卖家发送提示消息
		List<DyDomainname> listNobid = dyDomainnameDao.getDomainnameIfDomainEndAuctionAndNoBid(DOMAIN_STATUS_03,null);
		if(!listNobid.isEmpty()){
			DyDomainname dyDomainname = null;
			DyClient dyClient = null;
			String[] domainIds = new String[listNobid.size()];
			for(int i=0;i<listNobid.size();i++){
				try {
					dyDomainnameService.auctionFailHandle(listNobid.get(i),true);
					domainIds[i] = listNobid.get(i).getId();
				} catch (Exception e) {
					logger.debug(e.toString());
				}
			}
			
			ShowDomainCacheUtil.removeFromShowDomainList(domainIds);
		}
			
		//有人出价,无保留价，向买家卖家发送消息 ，域名状态改为11
		List<TransactionInformation> listNoReservePrice = dyDomainnameDao.domainnameBuyerAndSellerIfDomainEndAuction(DOMAIN_STATUS_03,null);//List :有人出价,无保留价，已到节拍时间的所有记录
		if(!listNoReservePrice.isEmpty()){
			DyDomainname dyDomainname = null;
			DyClient buyer = null;
			DyClient seller = null;
			String[] domainIds = new String[listNoReservePrice.size()];
			for (int i = 0; i < listNoReservePrice.size(); i++) {
				try {
					TransactionInformation transactionInformation = listNoReservePrice.get(i);//买卖信息
					dyDomainnameService.auctionSuccessAndNoReservePriceHandle(transactionInformation);
					domainIds[i] = transactionInformation.getDomainnameId();
				} catch (Exception e) {
					logger.debug(e.toString());
				}
			}
			ShowDomainCacheUtil.removeFromShowDomainList(domainIds);
		}
		
		//有人出价，有保留价，出价高于保留价，状态改为11且向买家、卖家发送消息
		List<TransactionInformation> listHigherReservePrice = dyDomainnameDao.getDomainnameIfDomainEndAuctionAndHigh(DOMAIN_STATUS_03,null);//有人出价，有保留价，出价高于保留价，已到节拍时间的所有记录
		if(!listHigherReservePrice.isEmpty()){
			DyDomainname dyDomainname = null;
			DyClient buyer = null;
			DyClient seller = null;
			String[] domainIds = new String[listHigherReservePrice.size()];
			for (int i = 0; i < listHigherReservePrice.size(); i++) {
				try {
					TransactionInformation transactionInformation = listHigherReservePrice.get(i);//买卖信息
					dyDomainnameService.auctionSuccessAndHigherReservePriceHandle(transactionInformation);
					domainIds[i] = transactionInformation.getDomainnameId();
				} catch (Exception e) {
					logger.debug(e.toString());
				}
			}
			ShowDomainCacheUtil.removeFromShowDomainList(domainIds);
		}
		//有人出价，有保留价，出价低于保留价，状态改为23且向卖家发送信息
		List<TransactionInformation> listLowerReservePrice = dyDomainnameDao.getDomainnameIfDomainEndAuctionAndLow(DOMAIN_STATUS_03,null);//有人出价，有保留价，出价低于保留价,已到节拍时间的所有记录
		if(!listLowerReservePrice.isEmpty()){
			DyDomainname dyDomainname = null;
			DyClient buyer = null;
			DyClient seller = null;
			String[] domainIds = new String[listLowerReservePrice.size()];
			for (int i = 0; i < listLowerReservePrice.size(); i++) {
				try {
					TransactionInformation transactionInformation = listLowerReservePrice.get(i);//买卖信息
					/*
					 * 增加功能：  当代理竞价高于保留价时，使用保留价进行实际出价。再回到正确处理环节
					 */
					if(transactionInformation.getProxyAmount() != null && transactionInformation.getProxyAmount() >= transactionInformation.getReservePrice()){
						// 代理竞价高于保留价时，自动出价到保留价
						dyDomainnameService.auctionSuccessAndProxyHigherReservePriceHandle(transactionInformation);
					} else {
						dyDomainnameService.auctionSuccessAndLowerReservePriceHandle(transactionInformation,true);
						domainIds[i] = transactionInformation.getDomainnameId();
					}
				} catch (Exception e) {
					logger.debug(e.toString());
				}
			}
			ShowDomainCacheUtil.removeFromShowDomainList(domainIds);
		}
		//域名状态检测end

		// 清除结拍域名的相关新消息记录
		int m5 = dyNewsService.deleteOverDomainNews();
		
		//域名结拍只剩1小时，给关注人发送消息提示begin
		FollowInfoToMsg followInfoToMsg = new FollowInfoToMsg();
		// 设置时间
		Date date = new Date();
		// 2016/3/8 时间改为15分钟前
//		followInfoToMsg.setMin59(new Date(date.getTime() + 59*60*1000));
//		followInfoToMsg.setMin60(new Date(date.getTime() + 60*60*1000));
		followInfoToMsg.setMin59(new Date(date.getTime() + 14*60*1000));
		followInfoToMsg.setMin60(new Date(date.getTime() + 15*60*1000));
		List<FollowInfoToMsg> list = dyDomainnameService.lastOneHourDomainInfo(followInfoToMsg);
		for(int i = 0;i<list.size();i++){
			//发送消息
			try {
				//向关注者发送消息通知
				Message message = new Message();
				String title = DySysUtils.TEMPLATE_TITLE_0028;
				String content = DySysUtils.TEMPLATE_MESSAGE_0028;
				content = content.replace("{{domainname.DATA}}", list.get(i).getDomainname());
				message.SendNews(WeChat.getAccessToken(), list.get(i).getOpenId(), title, content, list.get(i).getDomainnameId());
			} catch (Exception e) {
				
			}
		}
		//域名结拍只剩1小时，给关注人发送消息提示end
	}

}
