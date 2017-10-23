/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013  , MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.thinkgem.jeesite.common.wx.inf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.bean.Articles;
import com.thinkgem.jeesite.common.wx.bean.DuoKfOutMessage;
import com.thinkgem.jeesite.common.wx.bean.DuoKfOutMessage.TransInfo;
import com.thinkgem.jeesite.common.wx.bean.InMessage;
import com.thinkgem.jeesite.common.wx.bean.NewsOutMessage;
import com.thinkgem.jeesite.common.wx.bean.OutMessage;
import com.thinkgem.jeesite.common.wx.bean.UserInfo;
import com.thinkgem.jeesite.common.wx.oauth.DuoKefu;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.common.wx.oauth.User;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyFinance;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessage;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessagegroup;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyMessageService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyMessagegroupService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

public class DefaultMessageProcessingHandlerImpl implements MessageProcessingHandler, Constant{
	
	private static final Logger LOGGER = Logger.getLogger(DefaultMessageProcessingHandlerImpl.class);

	private OutMessage outMessage;
	
	private DyClientService dyClientService = null;
	
	private DyFinanceService dyFinanceService = null;
	
	private DyMessagegroupService dyMessagegroupService = null;
	
	private DyMessageService dyMessageService = null;
	
	public DyClientService getDyClientService() {
		if (dyClientService == null) {
			dyClientService = SpringContextHolder.getBean(DyClientService.class);
		} 
		
		return dyClientService;
	}
	
	public DyFinanceService getDyFinanceService() {
		if (dyFinanceService == null) {
			dyFinanceService = SpringContextHolder.getBean(DyFinanceService.class);
		} 
		
		return dyFinanceService;
	}
	
	public DyMessagegroupService getDyMessagegroupService() {
		if (dyMessagegroupService == null) {
			dyMessagegroupService = SpringContextHolder.getBean(DyMessagegroupService.class);
		} 
		
		return dyMessagegroupService;
	}
	
	public DyMessageService getDyMessageService() {
		if (dyMessageService == null) {
			dyMessageService = SpringContextHolder.getBean(DyMessageService.class);
		} 
		
		return dyMessageService;
	}
	
	@Override
	public void allType(InMessage msg){
//		TextOutMessage out = new TextOutMessage();
//		out.setContent("您的消息已经收到！");
//		setOutMessage(out);
		LOGGER.debug("消息已经收到");
	}
	
	@Override
	public void textTypeMsg(InMessage msg) {
		// 所有消息都转发给多客服
		contactDuokf(msg);
	}

	@Override
	public void locationTypeMsg(InMessage msg) {
	}

	@Override
	public void imageTypeMsg(InMessage msg) {
	}
	
	@Override
	public void videoTypeMsg(InMessage msg) {
	}
	
	@Override
	public void voiceTypeMsg(InMessage msg) {
	}

	@Override
	public void linkTypeMsg(InMessage msg) {
	}
	
	@Override
	public void verifyTypeMsg(InMessage msg) {}

	@Override
	public void eventTypeMsg(final InMessage msg) {
		
		if ("CLICK".equalsIgnoreCase(msg.getEvent())) {
			String eventKey = msg.getEventKey();
			if (eventKey.equalsIgnoreCase("contact")) {
				// 首先发一条信息及时反馈
				try {
					Message message = new Message();
					String accessToken = WeChat.getAccessToken();
					message.sendText(accessToken, msg.getFromUserName(), "正在为您转接经纪人...");
				} catch (Exception e) {
					// Do nothing
					LOGGER.debug(e.getMessage());
				}
				// 响应“联系经纪人”CLICK事件
				contactDuokf(msg);
			}
//			NewsOutMessage outMessage2 = new NewsOutMessage();
//			List<WeixinClickArticleModel>  models = WeixinClickArticleModel.dao.getArticlesByEventKey(eventKey);
//			if (models == null) {
//				return;
//			}
//			List<Articles> artList = Articles.from(models);
//			for (Articles a : artList) {
//				a.setUrl(a.getUrl() + msg.getFromUserName());
//			}
//			outMessage2.setArticles(artList);
//			
//			setOutMessage(outMessage2);
		} else if ("subscribe".equalsIgnoreCase(msg.getEvent())) {
			// 关注事件
			// 发送欢迎消息
			NewsOutMessage omsg = new NewsOutMessage();
			List<Articles> artList = new ArrayList<Articles>();
			
			// 直接推送最近发布过的图文消息，没有图文消息则默认发欢迎内容
			List<DyMessagegroup> dyMessagegroupSQL = getDyMessagegroupService().findList(new DyMessagegroup());
			if (dyMessagegroupSQL.size() == 0) {
				Articles a = new Articles();
				a.setTitle("感谢您关注拍域名");
				a.setDescription("尊敬的客户，感谢您关注拍域名。\n" + "这里是域名拍卖平台。\n");
				a.setUrl(ConfKit.get("root_uri") + "/servlet/OAuthServlet?jump=1");
				artList.add(a);
			} else {
				List<DyMessage> newMessageListSQL = getDyMessageService().getListByIds(dyMessagegroupSQL.get(0).getMessageList());

		 		for (DyMessage dyMessage : newMessageListSQL){
					Articles articles = new Articles();
					articles.setTitle(dyMessage.getTitle());
					articles.setDescription(dyMessage.getDescription());
					articles.setPicUrl(ConfKit.get("host_address") + dyMessage.getPicture());
					
					if("0".equals(dyMessage.getUrlType())){
						articles.setUrl(ConfKit.get("root_uri") + "/domainname/viewArticle?articleId=" + dyMessage.getUrlId());
					} else {
						articles.setUrl(ConfKit.get("root_uri") + "/domainname/singleDomainname?singleDomainId=" + dyMessage.getUrlId() + "&autoFollow=1");
					}
					artList.add(articles);
				}
			}
    		
			omsg.setArticles(artList);
			setOutMessage(omsg);
			
			// 插入会员表
			String openid = msg.getFromUserName();
			if (openid != null) {
				DyClient dc = getDyClientService().getByOpenid(openid);
				boolean isNew = dc == null;
				com.thinkgem.jeesite.modules.sys.entity.User broker = null;
				if (isNew) {
					dc = new DyClient();
					dc.setEmailFlag(Constant.EMAIL_FLAG_2);
					// 自动分配经纪人
					broker = getDyClientService().getRandomBroker();
					dc.setBrokerId(broker.getId());
					dc.setVip("0");
					dc.setSealFlag("0");
					dc.preInsert(broker);
					dc.setIsNewRecord(true);
				}
				try {
					UserInfo userInfo = User.getUserInfo(WeChat.getAccessToken(), openid);
					dc.setNickname(userInfo.getNickname());
					dc.setOpenid(openid);
					String headImg = userInfo.getHeadimgurl();
					if (headImg != null && headImg.lastIndexOf('/') < headImg.length()-1) {
						headImg = headImg.substring(0, headImg.lastIndexOf('/')+1);
					}
					dc.setPhoto(headImg);
					dc.setAvoidDeposit(SWITCH_OFF);
					
					getDyClientService().save(dc);
					if (isNew) {
						DyFinance finance = new DyFinance();
						finance.setClientId(dc.getId());
						finance.setAccountBalance(0L);
						finance.setFreezeBalance(0L);
						finance.preInsert(broker);
						finance.setIsNewRecord(true);
						
						getDyFinanceService().save(finance);
					}
					UserUtils.getSession().setAttribute("current_dy_client", dc);
				} catch (Exception e) {
					LOGGER.error("=======获取用户信息失败===============", e);
					System.out.println("=======获取用户信息失败==============="+e.getMessage());
				}
			}
			
		} else if ("scan".equalsIgnoreCase(msg.getEvent())) {
			// 已经关注用户扫描，触发扫码事件
			NewsOutMessage omsg = new NewsOutMessage();
			List<Articles> artList = new ArrayList<Articles>();
//			bindUserToOrder(msg, artList);
			omsg.setArticles(artList);
			setOutMessage(omsg);
		}
	}

	private void contactDuokf(InMessage msg) {
		String openid = msg.getFromUserName();
		DuoKfOutMessage outmessage = new DuoKfOutMessage(msg);
		// 如果经纪人有客服账号且在线时，指定客服为经纪人
		if (openid != null) {
			try {
				String accessToken = WeChat.getAccessToken();
				
				// 转发多客服
				DyClient dc = getDyClientService().getByOpenid(openid);
				com.thinkgem.jeesite.modules.sys.entity.User broker = getDyClientService().viewBroker(dc.getBrokerId());
				String kfAccount = broker.getKfAccount();
				if (StringUtils.isNotBlank(kfAccount)) {
					//判断当前经纪人的客服账号是否在线
					DuoKefu duokefu = new DuoKefu();
					if (duokefu.isKfAccountOnline(accessToken, kfAccount)) {
						TransInfo transInfo = outmessage.new TransInfo();
						transInfo.setKfAccount(kfAccount);
						outmessage.setTransInfo(transInfo);
					}
				}
			} catch (Exception e) {
				// Do nothing
				LOGGER.debug(e.getMessage());
			}
		}
		
		setOutMessage(outmessage);
	}
	
	@Override
	public void setOutMessage(OutMessage outMessage) {
		this.outMessage = outMessage;
	}
	
	@Override
	public void afterProcess(InMessage inMessage,OutMessage outMessage) {
	}
	
	@Override
	public OutMessage getOutMessage() {
		return outMessage;
	}
	
	public static void main(String[] args) {
		
		DefaultMessageProcessingHandlerImpl df = new DefaultMessageProcessingHandlerImpl();
		InMessage msg = new InMessage();
		msg.setFromUserName("user_open_id");
		msg.setToUserName("my_open_id");
		msg.setEvent("CLICK");
		msg.setEventKey("xx");
		
		df.textTypeMsg(msg);
		System.out.println(df.getOutMessage());
		System.out.println(System.currentTimeMillis());
	}
}
