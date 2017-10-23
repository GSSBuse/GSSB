package com.thinkgem.jeesite.modules.wx.web.domainname;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.bean.WxRedpack;
import com.thinkgem.jeesite.common.wx.bean.WxReturnWxRedpackResult;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAccessRecord;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyAttention;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBidhistory;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyBonus;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyDomainname;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyNews;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyShareRecord;
import com.thinkgem.jeesite.modules.sys.service.dy.DyAccessRecordService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyAttentionService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBidhistoryService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBonusService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyFinanceService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyNewsService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyShareRecordService;
import com.thinkgem.jeesite.modules.sys.utils.CacheUpdateFlagUtil;
import com.thinkgem.jeesite.modules.sys.utils.NewsUpdateFlagUtil;
import com.thinkgem.jeesite.modules.sys.utils.ShowBidListCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.ShowDomainCacheUtil;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidCashInfo;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BidClient;
import com.thinkgem.jeesite.modules.wx.entity.domainname.BonusClient;
import com.thinkgem.jeesite.modules.wx.entity.domainname.PageDomainEntity;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 我要买 页面控制器
 * @author shenzb.fnst
 * @since 2015/10/12
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/domainname")
public class IbuyController implements Constant {

	/** 关注文本 */
	private static final String ATTENTION_NORMAL = "关注";
	private static final String ATTENTION_DEL    = "取消";

	/** 排序规则 */
//	private final String sort_DyDomainname         = "endTime asc";                      // 结拍时间升序
	private final String sort_DyBidhistory_OrderBy = "bidAmount desc, proxyAmount desc"; // 竞拍出价金额降序，代理竞价金额降序
//	private final String sort_DyAttention          = "createDate desc";                  // 关注时间降序

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DyDomainnameService dyDomainnameService;     // 域名信息管理Service

	@Autowired
	private DyBidhistoryService dyBidhistoryService;     // 会员出价信息Service

	@Autowired
	private DyAttentionService dyAttentionService;       // 域名关注Service

	@Autowired
	private DyClientService dyClientService;             // 会员信息管理Service

	@Autowired
	private DyFinanceService dyFinanceService;           // 会员财务信息管理Service

	@Autowired
	private DyNewsService dyNewsService;                 // 最新消息管理Service

	@Autowired
	private DyShareRecordService dyShareRecordService;   // 分享记录管理Service

	@Autowired
	private DyAccessRecordService dyAccessRecordService; // 访问记录管理Service

	@Autowired
	private DyBonusService dyBonusService;               // 红包佣金信息Service

	/**
	 * 我要买 页面请求
	 * @param model 我要买页面模型
	 * @param singleDomainId 单域名页面域名ID
	 * @param shareClientId 分享者ID
	 * @return 我要买 页面视图
	 */
	@RequestMapping(value = {"ibuy"})
	public String ibuy(
			HttpServletRequest req,
			Model model,
			@RequestParam(value = "singleDomainId", required = false)String singleDomainId,
			@RequestParam(value = "shareClientId", required = false)String shareClientId,
			@RequestParam(value = "autoFollow", required = false)String autoFollow) {
		
		// 获取初始化数据
		// 第一次打开时，只显示两条
		AjaxResult ar = this.domainList(model, String.valueOf(1), singleDomainId, shareClientId, null);
		ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时
		model.addAttribute("initData", JsonMapper.toJsonString(ar.getData()));
		
		return "modules/wx/domainname/ibuy";
	}

	@RequestMapping(value = {"singleDomainname"})
	public String singleDomainname(
			Model model,
			@RequestParam(value = "singleDomainId", required = false)String singleDomainId,
			@RequestParam(value = "shareClientId", required = false)String shareClientId,
			@RequestParam(value = "autoFollow", required = false)String autoFollow){
		//当用户进入某个域名页面时，自动添加对该域名的关注 
				if ( StringUtils.isNotBlank(singleDomainId) && StringUtils.isNotBlank(autoFollow) ) {
					try {
						DyClient currClient = DySysUtils.getCurrentDyClient();
						
						DyAttention entity = new DyAttention();
						entity.setClientId(currClient.getId());
						entity.setDomainnameId(singleDomainId);
						entity.setDelFlag(DyAttention.DEL_FLAG_NORMAL);
						
						if(!dyAttentionService.isAttented(entity)){
							entity.preInsert(UserUtils.get(currClient.getBrokerId()));
							entity.setIsNewRecord(true);
							dyAttentionService.save(entity);
						}
					} catch (Exception e) {
						return "modules/wx/domainname/singleDomainname";
					}
				}
				/**author wufulin end**/
				return "modules/wx/domainname/singleDomainname";
		
	}
	/**
	 * 拍卖列表 页面请求
	 * @param model 拍卖列表页面模型
	 * @return 拍卖列表 页面视图
	 */
	@RequestMapping(value = {"auction-list"})
	public String auctionList(Model model) {
		return "modules/wx/domainname/auctionList";
	}

	/**
	 * 出价记录 页面请求
	 * @param model 出价记录页面模型
	 * @return 出价记录 页面视图
	 */
	@RequestMapping(value = {"bidding-list"})
	public String biddingList(Model model) {
		return "modules/wx/domainname/biddingList";
	}
	
	/**
	 * 单一域名红包佣金记录 页面请求
	 * @param model 页面模型
	 * @return 页面视图
	 */
	@RequestMapping(value = {"bonusRecordSingle"})
	public String bonusRecordSingle(Model model, String domainId) {
		return "modules/wx/domainname/bonusRecordSingle";
	}
	
	/**
	 * 根据域名获取所有红包佣金信息
	 * @param model 页面模型
	 * @param singleDomainId 单域名页面域名ID
	 * @param shareClientId 分享者ID
	 * @return 我要买 域名列表数据
	 */
	@ResponseBody
	@RequestMapping(value = {"bonusRecordSingleInfo"})
	public AjaxResult bonusRecordSingleInfo(Model model, String domainnameId) {
		DyDomainname dyDomainname = dyDomainnameService.get(domainnameId);
		if (dyDomainname == null) {
			return AjaxResult.makeError("域名不存在");
		}
		
		DyBonus dyBonus = new DyBonus();
		dyBonus.setDomainnameId(domainnameId);
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//处理日期格式
		List <Map<String, Object>> shareList = new ArrayList <Map<String, Object>>(); //记录分享红包信息
		Map<String, Object> secondBonusData = null;//记录次高价红包信息
		Map<String, Object> shareCommissionData = null;//记录分享佣金信息
		/*获取该域名的所有红包列表*/
		List<BonusClient> shareBonusList = dyBonusService.getBonusListByDomainId(domainnameId);
		if(shareBonusList.size() > 0){
			for(BonusClient bonusClient : shareBonusList){
				Map<String, Object> data = new HashMap<String, Object>();//记录每条记录所需信息
				data.put("domainnameId", domainnameId);
				data.put("clientId", bonusClient.getClientId());
				data.put("money", bonusClient.getMoney()/100.00);
				data.put("type", bonusClient.getType());
				data.put("clientPhoto", bonusClient.getClientPhoto());
				data.put("clientNickname", bonusClient.getClientNickname());
				data.put("createDate", format.format(bonusClient.getCreateDate()));
				if(StringUtils.equals(bonusClient.getType(), Constant.BONUS_TYPE_01)){
					/*获取分享红包列表*/
					shareList.add(data);
				}else if(StringUtils.equals(bonusClient.getType(), Constant.BONUS_TYPE_02)){
					/*如果域名已经交易结束（状态为15），则可以获取到次高价红包信息*/
					secondBonusData = data;
				}else if(StringUtils.equals(bonusClient.getType(), Constant.BONUS_TYPE_03)){
					/*如果域名已经交易结束（状态为15），则可以获取到分享佣金信息*/
					shareCommissionData = data;
				}
			}
		}
		/*获取域名次高价红包和分享佣金信息(域名状态为03、11、12、13、14)*/
		/*(域名状态为21、22、23，则没有次高价红包和分享佣金信息)*/
		if(!StringUtils.equals(dyDomainname.getStatus(), Constant.DOMAIN_STATUS_15) && !dyDomainname.getStatus().startsWith("2")){
			//获取域名当前的详细交易信息
			BidCashInfo bidCashInfo = dyDomainnameService.finddealByDomainId(domainnameId);
			if(bidCashInfo != null){
				/*获取次高价红包信息*/
				if(StringUtils.isNotBlank(bidCashInfo.getSecondClientId())){
					secondBonusData = new HashMap<String, Object>();
					secondBonusData.put("domainnameId", domainnameId);
					secondBonusData.put("clientId", bidCashInfo.getSecondClientId());
					secondBonusData.put("money", bidCashInfo.getBonusSecond());
					secondBonusData.put("type", Constant.BONUS_TYPE_02);
					secondBonusData.put("clientPhoto", dyClientService.get(bidCashInfo.getSecondClientId()).getPhoto());
					secondBonusData.put("clientNickname", bidCashInfo.getSecondClientNickname());
					secondBonusData.put("createDate", "");
				}
				/*获取分享佣金信息*/
				if(StringUtils.isNotBlank(bidCashInfo.getShareClientId())){
					shareCommissionData = new HashMap<String, Object>();
					shareCommissionData.put("domainnameId", domainnameId);
					shareCommissionData.put("clientId", bidCashInfo.getShareClientId());
					shareCommissionData.put("money", bidCashInfo.getShareRebate());
					shareCommissionData.put("type", Constant.BONUS_TYPE_03);
					shareCommissionData.put("clientPhoto", dyClientService.get(bidCashInfo.getShareClientId()).getPhoto());
					shareCommissionData.put("clientNickname", bidCashInfo.getShareClientNickname());
					shareCommissionData.put("createDate", "");
				}
			}
		}
		
		AjaxResult ar = null;
		if(shareList.size() == 0 && secondBonusData == null && shareCommissionData == null){
			ar = AjaxResult.makeWarn("没有记录");
			ar.getData().put("domainname", dyDomainname.getName());
			ar.getData().put("description", dyDomainname.getDescription());
			ar.getData().put("domainPhoto", dyDomainname.getImage1());
			ar.getData().put("sharenumber", dyDomainname.getBonusShareNumber());
			ar.getData().put("sharetotal", dyDomainname.getBonusShareTotal());
		}else{
			ar = AjaxResult.makeSuccess("");
			ar.getData().put("domainname", dyDomainname.getName());
			ar.getData().put("description", dyDomainname.getDescription());
			ar.getData().put("domainPhoto", dyDomainname.getImage1());
			ar.getData().put("sharenumber", dyDomainname.getBonusShareNumber());
			ar.getData().put("sharetotal", dyDomainname.getBonusShareTotal());
			if(shareList.size() > 0){
				ar.getData().put("shareList", shareList);
				ar.getData().put("sharedSize", shareList.size());
			}else{
				ar.getData().put("shareList", "");
				ar.getData().put("sharedSize", 0);
			}
			if(secondBonusData != null){
				ar.getData().put("secondBonusData", secondBonusData);
			}else{
				ar.getData().put("secondBonusData", "");
			}
			if(shareCommissionData != null){
				ar.getData().put("shareCommissionData", shareCommissionData);
			}else{
				ar.getData().put("shareCommissionData", "");
			}
		}
		DyClient currClient = DySysUtils.getCurrentDyClient();
		if(currClient != null){
			ar.getData().put("currentClientId", currClient.getId());
			ar.getData().put("nickname", currClient.getNickname());
		}else{
			ar.getData().put("currentClientId", "");
			ar.getData().put("nickname", "");
		}
		return ar;
	}

	/**
	 * 根据分页页码获取所有有效域名信息
	 * @param model 页面模型
	 * @param pageIndex 分页页码
	 * @param singleDomainId 单域名页面域名ID
	 * @param shareClientId 分享者ID
	 * @return 我要买 域名列表数据
	 */
	@ResponseBody
	@RequestMapping(value = {"domainList"})
	public AjaxResult domainList(Model model, String pageIndex, String singleDomainId, String shareClientId, Integer pageSizeSp) {
		// 将分享链接openid保存至用户session中
		UserUtils.getSession().setAttribute(SESSION_KEY_SHARE_OPENID, shareClientId);
		DyClient currClient = DySysUtils.getCurrentDyClient();

		Page<DyDomainname> page = new Page<DyDomainname>();
		page.setPageSize(pageSizeSp == null?PAGE_SIZE_3:pageSizeSp);
		page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));

		DyDomainname searchCond = new DyDomainname(singleDomainId);
		if (null != currClient) {
			page.setOrderBy(DySysUtils.SORT_ORDERBY);
			searchCond.setClientId(currClient.getId());
		}

		if (StringUtils.isBlank(shareClientId)) {
			searchCond.setStatus(DOMAIN_STATUS_03);
		} else {
			searchCond.setStatus(DOMAIN_STATUS_03);
			if (null != currClient) {
				// 通过分享链接进入我要买页面的场合，增加用户点击分享链接记录
				if(DySysUtils.SHARE_BONUS_ENABLE.equals(SHARE_BONUS_SWITCH_ON)){
					//值为1，红包功能开启了
					try {
						// 存在性检查
						DyAccessRecord accessRec = new DyAccessRecord();

						accessRec.setClientId(currClient.getId());
						accessRec.setDomainnameId(singleDomainId);
						accessRec.setShareClientid(shareClientId);
						if (dyAccessRecordService.findList(accessRec).isEmpty()) {
							accessRec.setDelFlag(DyAccessRecord.DEL_FLAG_NORMAL);
							accessRec.setAccessTime(new Date());
							accessRec.preInsert(UserUtils.get(currClient
									.getBrokerId()));
							dyAccessRecordService.save(accessRec);
						}

					} catch (Exception e) {
						logger.error("domainList加载失败：", e);
						return AjaxResult.makeError("页面加载失败：" + e.getMessage());
					}
				}
			}
		}

		//获取页面需显示的域名信息
		List<DyDomainname> domainList = getDomainListInPage(page, searchCond, currClient);
		
		if (page.getPageNo() < Integer.parseInt(pageIndex)) {
			return AjaxResult.makeWarn("没有更多域名了");
		}
		
		//存储返回给页面的数据
		List<PageDomainEntity> pDomainList = new ArrayList<PageDomainEntity>();
		
		for (DyDomainname domain : domainList) {
			PageDomainEntity pDomain = new PageDomainEntity();
			BeanUtils.copyProperties(domain, pDomain);
			List<BidClient> bidList = new ArrayList<BidClient>();     // 域名出价列表
			Long bidCount = 0L;                                       // 域名出价总数
			List<DyClient> attentionList = new ArrayList<DyClient>(); // 域名会员关注列表
			Long attentionCount = 0L;                                 // 域名会员关注总数
			String domainId = domain.getId();

			// 设置域名所有者
			pDomain.setClient(dyClientService.get(domain.getClientId()));

			// 是否结拍
			pDomain.setEndFlag(domain.getEndTime().compareTo(new Date()) <= 0);

			if (page.getPageNo() != 1 || !StringUtils.isEmpty(singleDomainId)) {
				// 查询出价记录列表
				bidCount = this.getBidClientList(domainId, bidList);
				pDomain.setBidCount(bidCount);
				pDomain.setBidList(bidList);
				if (bidList.size() > 3) {
					pDomain.setBidList(bidList.subList(0, 3));
				}
				if (bidList.isEmpty()) {
					pDomain.setCurrAmount(0L);
					pDomain.setTopBidClientId("");
				} else {
					pDomain.setCurrAmount(bidList.get(0).getBidAmount());
					pDomain.setProxyAmount(bidList.get(0).getProxyAmount());
					pDomain.setTopBidClientId(bidList.get(0).getClientId());
					if (null != bidList.get(0).getProxyAmount()) {
						bidList.get(0).setProxyIncrement(DySysUtils.calculateDepositAndIncrement(bidList.get(0).getProxyAmount(), BID_RULE_TYPE_INCREMENT));
						pDomain.setProxyIncrement(bidList.get(0).getProxyIncrement());
					}
				}
				pDomain.setIncrement(DySysUtils.calculateDepositAndIncrement(pDomain.getCurrAmount(), BID_RULE_TYPE_INCREMENT));
				pDomain.setDeposit(DySysUtils.calculateDepositAndIncrement(pDomain.getCurrAmount().longValue() + pDomain.getIncrement().longValue(), BID_RULE_TYPE_DEPOSIT));
				if (null == pDomain.getIncrement()) {
					pDomain.setIncrement(0L);
				}
	
	
				// 查询关注会员列表
				attentionCount = this.getAttentionClientList(pDomain.getId(), attentionList);
				pDomain.setAttentionCount(attentionCount);
				pDomain.setAttentionList(attentionList);
	
				
				if (null != currClient) {
					// 确认是否关注
					DyAttention attention = new DyAttention();
					attention.setClientId(currClient.getId());
					attention.setDomainnameId(pDomain.getId());
					attention.setDelFlag(DyAttention.DEL_FLAG_NORMAL);
					pDomain.setAttentioned(dyAttentionService.isAttented(attention)); // 域名是否被关注
					
					pDomain.setAttentionText(pDomain.isAttentioned() ? ATTENTION_DEL
							: ATTENTION_NORMAL); // 域名是否被关注文本
				}
			} else {
				pDomain.setBidCount(-1L);
				pDomain.setBidList(ListUtils.EMPTY_LIST);
				pDomain.setCurrAmount(-1L);
				pDomain.setTopBidClientId("");
				pDomain.setIncrement(0L);
				pDomain.setDeposit(0L);
				pDomain.setAttentionCount(-1L);
				pDomain.setAttentionList(ListUtils.EMPTY_LIST);
			}
			pDomainList.add(pDomain);
        }

		AjaxResult ar = AjaxResult.makeSuccess("");
		Map<String, String> clientInfo = Maps.newHashMap();
		if (null != currClient) {
			clientInfo.put("id", currClient.getId());
			clientInfo.put("nickname", currClient.getNickname());
			clientInfo.put("photo", currClient.getPhoto());
		} else {
			clientInfo = null;
		}
		ar.getData().put("currClient", clientInfo);
		ar.getData().put("domainList", pDomainList);
		ar.getData().put("count", page.getCount());
		ar.getData().put("shareBonusEnable", DySysUtils.SHARE_BONUS_ENABLE);//添加红包开关
		ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时
		return ar;
	}

	/**
	 * 根据域名ID列表获取我要买页面显示域名的最新数据
	 * @param domainIdList
	 * @return 域名的最新数据
	 */
	@RequestMapping(value = "polling/ibuyData")
	@ResponseBody
	public AjaxResult ibuyData(@RequestParam("timeStamp") String timeStamp, @RequestParam("domainIdList[]") String...domainIdList ) {
		//判断时间戳，相等无需操作
		if(!CacheUpdateFlagUtil.isNeedUpdate(timeStamp)) {
			
			// 等待更新
			try {
				synchronized (CacheUpdateFlagUtil.updateLock) {
					CacheUpdateFlagUtil.updateLock.wait(20 * 1000);
	            }
				
				// 时间到了还没有更新，则直接返回时间戳
				if(!CacheUpdateFlagUtil.isNeedUpdate(timeStamp)) {
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("timeStamp", timeStamp);
					return ar;
				}
				
			} catch (InterruptedException e) {
				// 有更新, 程序继续
				logger.debug("刷新程序感受到数据更新，进行读取数据操作");
			}
		}
		
		Long newTimeStamp = CacheUpdateFlagUtil.getUpdateTimestamp();
		
		DyClient currClient = DySysUtils.getCurrentDyClient();
		List<PageDomainEntity> pageDomainList = new ArrayList<PageDomainEntity>();
		try {
			for (String domainId : domainIdList) {
				PageDomainEntity pageDomain = new PageDomainEntity();
				pageDomain.setId(domainId);
				//获取域名的节拍时间，从缓存取，取不到则直接读数据库
				DyDomainname dyDomainname = null;
				dyDomainname = ShowDomainCacheUtil.getDomainFromCache(domainId);
				if(dyDomainname == null){
					throw new Exception("无此域名");
				}else{
					pageDomain.setEndTime(dyDomainname.getEndTime());
				}
				
				// 根据域名ID获取域名最新价
				DyBidhistory dyBidHist = dyBidhistoryService.getMaxBidAmount(domainId);
				pageDomain.setCurrAmount(null == dyBidHist ? 0L : dyBidHist.getBidAmount());
				pageDomain.setIncrement(DySysUtils.calculateDepositAndIncrement(pageDomain.getCurrAmount(), BID_RULE_TYPE_INCREMENT));
				//设置代理竞价及代理金价加价幅度
				if(dyBidHist != null){
					pageDomain.setTopBidClientId(dyBidHist.getClientId());
					if(dyBidHist.getProxyAmount() != null && dyBidHist.getProxyAmount().longValue() != 0){
						pageDomain.setProxyAmount(dyBidHist.getProxyAmount());
						Long proxyIncrement = DySysUtils.calculateDepositAndIncrement(dyBidHist.getProxyAmount(), BID_RULE_TYPE_INCREMENT);
						pageDomain.setProxyIncrement(proxyIncrement);
					}
				}
				//保证金的计算：有代理竞价则按代理竞价计算，无则按最高价计算
				if(currClient != null && dyBidHist != null && StringUtils.equals(dyBidHist.getClientId(), currClient.getId())){
					//最高价是自己
					if(dyBidHist.getProxyAmount() != null && dyBidHist.getProxyAmount() != 0){
						pageDomain.setDeposit(DySysUtils.calculateDepositAndIncrement(pageDomain.getProxyAmount().longValue() + pageDomain.getProxyIncrement().longValue(), BID_RULE_TYPE_DEPOSIT));
					}else{
						pageDomain.setDeposit(DySysUtils.calculateDepositAndIncrement(pageDomain.getCurrAmount().longValue() + pageDomain.getIncrement().longValue(), BID_RULE_TYPE_DEPOSIT));
					}
				}else{
					pageDomain.setDeposit(DySysUtils.calculateDepositAndIncrement(pageDomain.getCurrAmount().longValue() + pageDomain.getIncrement().longValue(), BID_RULE_TYPE_DEPOSIT));
				}
				
				List<BidClient> bidList = new ArrayList<BidClient>();

				// 根据域名ID获取会员出价列表
				Long bidCount = this.getBidClientList(domainId, bidList);
				pageDomain.setBidCount(bidCount);
				pageDomain.setBidList(bidList);

				// 根据域名ID获取关注会员列表
				List<DyClient> attentionCList = new ArrayList<DyClient>();
				Long attentionCount = this.getAttentionClientList(domainId, attentionCList);
				pageDomain.setAttentionCount(attentionCount);
				pageDomain.setAttentionList(attentionCList);
				
				//获取用户对域名的关注状态
				if(currClient == null){
					//非关注公众号用户
					pageDomain.setAttentioned(false);
				}else{
					pageDomain.setAttentioned(false);
					//登录用户
					DyAttention dyAttention = new DyAttention();
					dyAttention.setClientId(currClient.getId());
					dyAttention.setDomainnameId(domainId);
					dyAttention.setDelFlag(DyAttention.DEL_FLAG_NORMAL);
					for (DyClient dc : attentionCList) {
						if (Objects.equal(dc.getId(), currClient.getId())) {
							pageDomain.setAttentioned(true);
							break;
						}
					}
					// 防止数据库中的数据与上面取到的不一致
//					pageDomain.setAttentioned(dyAttentionService.isAttented(dyAttention));
				}
				
				pageDomainList.add(pageDomain);
			}
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("timeStamp", newTimeStamp);
			ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时
			ar.getData().put("ibuyData", pageDomainList);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.makeError("");
		}
	}
	
	private List<DyDomainname> getDomainListInPage(Page<DyDomainname> page, DyDomainname dyDomainname, DyClient currClient) {

		List<DyDomainname> sublist = new ArrayList<DyDomainname>(0);
		if(dyDomainname !=null && StringUtils.isNotEmpty(dyDomainname.getId()))	{
			//单域名页面不需要缓存
			page = dyDomainnameService.customizedFindPage(page, dyDomainname);
			sublist = page.getList();
		}else{
			//所有数据，处理缓存
			List<DyDomainname> domainList = ShowDomainCacheUtil.getShowDomainList();
			if (domainList != null) {
				int size = domainList.size();
				page.setCount(size);
				page.initialize();
				
				int max = page.getFirstResult()+page.getMaxResults();
				if (max > domainList.size()) {
					max = domainList.size();
				}
				sublist = domainList.subList(page.getFirstResult(), max);
			} else {
				Page<DyDomainname> allDataPage = new Page<DyDomainname>(1, -1);
				allDataPage = dyDomainnameService.customizedFindPage(allDataPage, dyDomainname);
				
				domainList = allDataPage.getList();
				
				ShowDomainCacheUtil.setShowDomainList(domainList);
				Set<String> domaimListSet = new HashSet<String>();
				for (DyDomainname dm : domainList) {
					domaimListSet.add(dm.getId());
	            }
				CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_ID_SET_KEY, domaimListSet);
				
				page.setCount(domainList.size());
				page.initialize();

				int max = page.getFirstResult()+page.getMaxResults();
				if (max > domainList.size()) {
					max = domainList.size();
				}
				sublist = domainList.subList(page.getFirstResult(), max);
			}
		}
		return sublist;
	}

	/**
	 * 当前登录会员根据域名ID对域名进行关注/取消关注
	 * @param domainId 域名ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "follow")
	@ResponseBody
	public AjaxResult follow(String domainId) {
		AjaxResult ar = dyAttentionService.follow(domainId);
		if (!ar.getType().equals(AjaxResult.SUCCESS)) {
			return ar;
		}

		List<DyClient> attentionCList = new ArrayList<DyClient>();;
		Long attentionCount = this.getAttentionClientList(domainId, attentionCList);
		ar.getData().put("attentionCount", attentionCount);
		ar.getData().put("attentionCList", attentionCList);
		NewsUpdateFlagUtil.setUpdateTimestamp();
		return ar;
	}

	/**
	 * 根据域名ID，当前登录会员ID增加分享记录
	 * @param domainId 域名ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "share")
	@ResponseBody
	public AjaxResult share(String domainId, boolean hasRedBag, HttpServletRequest request) {
		DyClient currClient = DySysUtils.getCurrentDyClient();
		User broker = UserUtils.get(currClient.getBrokerId());
		try {
			if(DySysUtils.SHARE_BONUS_ENABLE.equals(Constant.SHARE_BONUS_SWITCH_OFF)){
				//红包功能未开启
				return AjaxResult.makeSuccess("分享成功");
			}
			DyDomainname domain = dyDomainnameService.get(domainId);
			if (!StringUtils.equals(domain.getStatus(), Constant.DOMAIN_STATUS_03)) {
				return AjaxResult.makeSuccess("");
			}
			
			// 存在性检查
			DyShareRecord shareRec = new DyShareRecord();
			shareRec.setClientId(currClient.getId());
			shareRec.setDomainnameId(domainId);

			DyBonus bonus = new DyBonus();
			bonus.setClientId(currClient.getId());
			bonus.setDomainnameId(domainId);

			if (dyShareRecordService.findList(shareRec).isEmpty()) {
				shareRec.setShareTime(new Date());
				if (hasRedBag) {
					// 随机性，分享金额设定
					DyShareRecord listParam = new DyShareRecord();
					listParam.setDomainnameId(domainId);
					List<DyShareRecord> shareList = dyShareRecordService.findList(listParam);
					int totalRedBagCnt = shareList.size();
					if (totalRedBagCnt < domain.getBonusShareNumber()) {
						double totalRedBag = 0;
						for (DyShareRecord entity : shareList) {
							totalRedBag += entity.getRedbagAmount() / 100.0;
						}
						double redbagAmount = 0;
						if ((totalRedBagCnt + 1) == domain.getBonusShareNumber()) {
							NumberFormat formatter = new DecimalFormat("#.##");
							redbagAmount = Double.parseDouble(formatter.format(domain.getBonusShareTotal() - totalRedBag));
						} else {
							redbagAmount = DySysUtils.wxAlgorithm(domain.getBonusShareTotal().doubleValue() - totalRedBag, domain.getBonusShareNumber().intValue() - totalRedBagCnt);
						}
						// 用微信客服接口发送红包消息
						DySysUtils.sendWxMessage(DySysUtils.TEMPLATE_TITLE_0023, DySysUtils.TEMPLATE_MESSAGE_0023, currClient.getOpenid(), domain.getId(), domain.getName(), Double.toString(redbagAmount));
						WxRedpack wxRedpackParameters = new WxRedpack(
								IdGen.uuid().substring(0, 30),
								"",
								WeChat.tradeNumber("redpack"),
								ConfKit.get("mch_id"),
								ConfKit.get("AppId"),
								"登羽科技",
								currClient.getOpenid(),
								(int)redbagAmount * 100,
								1,
								"感谢您分享" + domain.getName(),
								request.getRemoteAddr(), // 本机IP
								"分享域名得红包",
								"首次分享才有哦");
						WxReturnWxRedpackResult redpackResult = WeChat.sendWxRedpack(wxRedpackParameters, ConfKit.get("cert_password"), ConfKit.get("cert_path"), ConfKit.get("partnerKey"));
//						if (redpackResult.getReturn_code() != null
//								&& redpackResult.getReturn_code().equals("SUCCESS")) {
//							if (redpackResult.getResult_code() != null
//									&& redpackResult.getResult_code().equals("SUCCESS")) {
//							} else {
//								logger.debug("微信红包业务结果" + redpackResult.getResult_code()
//										+ ";微信红包错误代码" + redpackResult.getErr_code()
//										+ ";微信红包错误代码描述" + redpackResult.getErr_code_des());
//								return AjaxResult.makeSuccess("");
//							}
//						} else {
//							logger.debug("微信红包返回状态码" + redpackResult.getReturn_code() + ";微信红包返回状态信息" + redpackResult.getReturn_msg());
//							return AjaxResult.makeSuccess("");
//						}
						shareRec.setRedbagAmount((long)(redbagAmount * 100));
						if (dyBonusService.findList(bonus).isEmpty()) {
							bonus.setMoney((long)(redbagAmount * 100));
							bonus.setType(DySysUtils.BONUS_TYPE_01);
							bonus.setDelFlag(DyBonus.DEL_FLAG_NORMAL);
							bonus.preInsert(broker);
							dyBonusService.save(bonus);
						}
					}
				}
				shareRec.setDelFlag(DyShareRecord.DEL_FLAG_NORMAL);
				shareRec.preInsert(broker);
				dyShareRecordService.save(shareRec);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return AjaxResult.makeError("分享记录保存失败");
		}
		return AjaxResult.makeSuccess("");

	}

	/**
	 * 当前登录会员根据域名ID,出价价格对域名进行出价
	 * @param domainId 域名ID
	 * @return 操作结果
	 * @throws ServiceException
	 * @throws Exception 
	 */
	@RequestMapping(value = "bid")
	@ResponseBody
	public AjaxResult bid(String domainId, String bidAmount) throws ServiceException, Exception {
		Long newBidAmount = Long.parseLong(bidAmount); // 当前出价金额

		if (!DySysUtils.conformToBidRule(newBidAmount)) {
			String[] rule = DySysUtils.getBidLevel(newBidAmount);
			AjaxResult ar = AjaxResult.makeWarn("出价失败，不符合出价规则<br>" + rule[0] + "加价幅度为" + rule[1] + "");
			ar.getData().put("type", "rule");
			return ar;
		}

		AjaxResult ar = dyBidhistoryService.bids(domainId, newBidAmount, null);
		if (!ar.getType().equals(AjaxResult.SUCCESS)) {
			return ar;
		}

		List<BidClient> bidList       = new ArrayList<BidClient>(); // 出价列表
		List<DyClient> attentionCList = new ArrayList<DyClient>();  // 关注列表
		// 查询出价记录列表
		Long bidCount = this.getBidClientList(domainId, bidList);
		if (!bidList.isEmpty()) {
			if (null != bidList.get(0).getProxyAmount()) {
				bidList.get(0).setProxyIncrement(DySysUtils.calculateDepositAndIncrement(bidList.get(0).getProxyAmount(), BID_RULE_TYPE_INCREMENT));
			}
		}
		// 查询关注会员列表
		Long attentionCount = this.getAttentionClientList(domainId, attentionCList);

		PageDomainEntity pDomain = new PageDomainEntity();
		pDomain.setEndTime(ShowDomainCacheUtil.getDomainFromCache(domainId).getEndTime());
		pDomain.setBidCount(bidCount);
		pDomain.setBidList(bidList);
		pDomain.setAttentionCount(attentionCount);
		pDomain.setAttentionList(attentionCList);
		pDomain.setCurrAmount(bidList.get(0).getBidAmount());
		pDomain.setProxyAmount(bidList.get(0).getProxyAmount());
		pDomain.setProxyIncrement(bidList.get(0).getProxyIncrement());
		pDomain.setTopBidClientId(bidList.get(0).getClientId());
		pDomain.setAttentioned(true);
		pDomain.setAttentionText(ATTENTION_DEL);
		pDomain.setIncrement(DySysUtils.calculateDepositAndIncrement(pDomain.getCurrAmount(), BID_RULE_TYPE_INCREMENT));
		pDomain.setDeposit(DySysUtils.calculateDepositAndIncrement(pDomain.getCurrAmount().longValue() + pDomain.getIncrement().longValue(), BID_RULE_TYPE_DEPOSIT));
		ar.getData().put("pDomain", pDomain);

		NewsUpdateFlagUtil.setUpdateTimestamp();
		return ar;
	}

	/**
	 * 当前登录会员根据域名ID, 获取域名出价列表前三条
	 * @param domainId 域名ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "getBidListByDomainId")
	@ResponseBody
	public AjaxResult getBidListByDomainId(String domainId) throws ServiceException, Exception {
		DyClient currClient = DySysUtils.getCurrentDyClient();
		List<BidClient> bidList       = new ArrayList<BidClient>(); // 出价列表
		// 查询出价记录列表
		Long bidCount = this.getBidClientList(domainId, bidList);
		if (!bidList.isEmpty()) {
			if (null != bidList.get(0).getProxyAmount()) {
				bidList.get(0).setProxyIncrement(DySysUtils.calculateDepositAndIncrement(bidList.get(0).getProxyAmount(), BID_RULE_TYPE_INCREMENT));
			}
			
			PageDomainEntity pDomain = new PageDomainEntity();
			pDomain.setEndTime(ShowDomainCacheUtil.getDomainFromCache(domainId).getEndTime());
			pDomain.setBidCount(bidCount);
			pDomain.setBidList(bidList);
			pDomain.setCurrAmount(bidList.get(0).getBidAmount());
			pDomain.setProxyAmount(bidList.get(0).getProxyAmount());
			pDomain.setProxyIncrement(bidList.get(0).getProxyIncrement());
			pDomain.setTopBidClientId(bidList.get(0).getClientId());
			pDomain.setIncrement(DySysUtils.calculateDepositAndIncrement(pDomain.getCurrAmount(), BID_RULE_TYPE_INCREMENT));
			//保证金的计算：有代理竞价则按代理竞价计算，无则按最高价计算
			if(bidList.get(0).getClientId().equals(currClient.getId()) && pDomain.getProxyAmount() != null && pDomain.getProxyAmount() != 0){
				pDomain.setDeposit(DySysUtils.calculateDepositAndIncrement(pDomain.getProxyAmount().longValue() + pDomain.getProxyIncrement().longValue(), BID_RULE_TYPE_DEPOSIT));
				
			}else{
				pDomain.setDeposit(DySysUtils.calculateDepositAndIncrement(pDomain.getCurrAmount().longValue() + pDomain.getIncrement().longValue(), BID_RULE_TYPE_DEPOSIT));
			}
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("pDomain", pDomain);
			ar.getData().put("isNoBid", "false");

			return ar;
		}else{
			AjaxResult ar = AjaxResult.makeSuccess("");
			ar.getData().put("isNoBid", "true");

			return ar;
		}

		
	}

	/**
	 * 根据域名ID，分页页码获取该域名的该页出价记录
	 * @param domainId 域名ID
	 * @param pageIndex 分页页码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"bidData"})
	public AjaxResult bidData(String domainId , String pageIndex){
		if (domainId == null) {
			return AjaxResult.makeWarn("没有更多出价了");
		}
		
		Page<DyBidhistory> page = new Page<DyBidhistory>();
		page.setPageSize(PAGE_SIZE_10);
		page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
		page.setOrderBy(sort_DyBidhistory_OrderBy);
//		page.setCount(QUERY_WITHOUT_COUNT);
		DyBidhistory dyBidhistoryTemp = new DyBidhistory();
		dyBidhistoryTemp.setDomainnameId(domainId);
		//获取分页出价信息
		Page<DyBidhistory> pageDomain = dyBidhistoryService.findPage(page, dyBidhistoryTemp);

		if (pageDomain.getPageNo() < Integer.parseInt(pageIndex)) {
			return AjaxResult.makeWarn("没有更多出价了");
		}

		List<DyBidhistory> tmpList = pageDomain.getList();
		//设置返回结果
		List<BidClient> bidClientList = new ArrayList<BidClient>();
		DyDomainname domain = dyDomainnameService.get(domainId);
		for(DyBidhistory dyBidhistory : tmpList){
			BidClient bidClient  = new BidClient();
			bidClient.setId(dyBidhistory.getId());
			bidClient.setDomainId(dyBidhistory.getDomainnameId());
			bidClient.setDomainName(domain!=null?domain.getName():"");
			bidClient.setClientId(dyBidhistory.getClientId());
			bidClient.setBidAmount(dyBidhistory.getBidAmount());
			//获取会员信息
			DyClient dyClient = dyClientService.get(dyBidhistory.getClientId());   
			bidClient.setDyid(dyClient.getDyid());
			bidClient.setName(dyClient.getName());
			bidClient.setNickname(dyClient.getNickname());
			bidClient.setPhoto(dyClient.getPhoto());

			bidClient.setBidTime(DateUtils.formatDate(dyBidhistory.getBidTime(), "MM-dd HH:mm:ss"));
			bidClient.setType(dyBidhistory.getType());

			bidClientList.add(bidClient);
		}
		AjaxResult ar = AjaxResult.makeSuccess("");
		ar.getData().put("domainName", domain.getName());
		ar.getData().put("bidClientList", bidClientList);
		ar.getData().put("bidCount", pageDomain.getCount());
		ar.getData().put("endTime", DateUtils.formatDate(domain.getEndTime(), "MM月dd日 HH:mm"));
		ar.getData().put("isFinished", domain.getEndTime().compareTo(new Date()) <= 0);
		ar.getData().put("hasBonusSecond", null != domain.getBonusSecond() && domain.getBonusSecond().compareTo(0L) > 0);

		return ar;
	}

	/**
	 * 根据分页页码获取所有有效域名拍卖数据
	 * @param model 页面模型
	 * @param pageIndex 分页页码
	 * @return 域名拍卖数据
	 */
	@ResponseBody
	@RequestMapping(value = {"auctionData"})
	public AjaxResult auctionData(Model model, String pageIndex) {
		DyClient client = DySysUtils.getCurrentDyClient();

		Page<DyDomainname> page = new Page<DyDomainname>();
		page.setPageSize(PAGE_SIZE_10);
		page.setPageNo(StringUtils.isBlank(pageIndex) ? DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
		DyDomainname searchCond = new DyDomainname();

		if (null != client) {
			page.setOrderBy(DySysUtils.SORT_ORDERBY);
			searchCond.setClientId(client.getId());
		}
		searchCond.setStatus(DOMAIN_STATUS_03);
		Page<DyDomainname> pageDomain = dyDomainnameService.customizedFindPage(page, searchCond);

		if (pageDomain.getPageNo() < Integer.parseInt(pageIndex)) {
			AjaxResult ar = AjaxResult.makeWarn("没有更多域名了");
			ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时
			return AjaxResult.makeWarn("没有更多域名了");
		}

		List<DyDomainname> domainList = pageDomain.getList();

		List<PageDomainEntity> pDomainList = new ArrayList<PageDomainEntity>(); // 页面域名实体列表
		for (DyDomainname domain : domainList) {
			PageDomainEntity pDomain = new PageDomainEntity(); // 页面域名实体
			pDomain = dyDomainnameService.getAuctionData(domain.getId(), null != client ? client.getId() : null);

			// 清除新出价标记
			if (null != client && pDomain.isHasNews()) {
				DyNews news = new DyNews();
				news.setClientId(client.getId());
				news.setDomainnameId(domain.getId());
				news.setDelFlag(DyNews.DEL_FLAG_NORMAL);
				news.setNewBidCount(STRING_0);
				dyNewsService.clearBidCount(news);
			}
			pDomainList.add(pDomain);
		}

		AjaxResult ar = AjaxResult.makeSuccess("");
		ar.getData().put("domainList", pDomainList);
		ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时

		return ar;
	}

	/**
	 * 根据域名ID更新域名状态
	 * @param domainId 域名ID
	 * @return 操作结果
	 */
	@ResponseBody
	@RequestMapping(value = "updateDomainStatus")
	public AjaxResult updateDomainStatus(String domainId) {
		return dyDomainnameService.updateDomainStatusById(domainId);
	}

	/**
	 * 根据域名ID获取会员出价列表
	 * @param domainId 域名ID
	 * @param bidList 域名-会员出价列表
	 * @return 域名-会员出价总数
	 */
	private Long getBidClientList(String domainId, List<BidClient> bidList) {
		try {
			BidClient bidClient = new BidClient();
			bidClient.setDomainId(domainId);
			Page<BidClient> bidClientPage = new Page<BidClient>();
			bidClientPage.setPageSize(PAGE_SIZE_3);
			bidClientPage.setPageNo(DEFAULT_PAGE_INDEX);
			bidClientPage.setOrderBy(sort_DyBidhistory_OrderBy);
			
	//		bidClientPage = dyBidhistoryService.customizedFindPage(bidClientPage, bidClient);
	//		bidList.addAll(bidClientPage.getList());
			
			//所有数据，处理缓存
			List<BidClient> bList = ShowBidListCacheUtil.getBidList(domainId);
			List<BidClient> sublist = new ArrayList<BidClient>();
			if (bList != null) {
				int size = bList.size();
				bidClientPage.setCount(size);
				bidClientPage.initialize();
				
				int max = bidClientPage.getFirstResult()+bidClientPage.getMaxResults();
				if (max > bList.size()) {
					max = bList.size();
				}
				sublist = bList.subList(bidClientPage.getFirstResult(), max);
			} else {
				Page<BidClient> allDataPage = new Page<BidClient>(1, -1);
				allDataPage.setOrderBy(sort_DyBidhistory_OrderBy);
				
				allDataPage = dyBidhistoryService.customizedFindPage(allDataPage, bidClient);
				
				bList = allDataPage.getList();
				
				ShowBidListCacheUtil.setBidList(domainId, bList);
	//			Set<String> domaimListSet = new HashSet<String>();
	//			for (DyDomainname dm : domainList) {
	//				domaimListSet.add(dm.getId());
	//            }
	//			CacheUtils.put(PAGE_DATA_CACHE, SHOW_DOMAIN_ID_SET_KEY, domaimListSet);
				
				bidClientPage.setCount(bList.size());
				bidClientPage.initialize();
	
				int max = bidClientPage.getFirstResult()+bidClientPage.getMaxResults();
				if (max > bList.size()) {
					max = bList.size();
				}
				sublist = bList.subList(bidClientPage.getFirstResult(), max);
				
			}
			bidList.addAll(sublist);
	
			return bidClientPage.getCount();
		} catch (Exception e) {
			logger.error("获取出价人列表", e);
			return 0L;
		}
	}

	/**
	 * 根据域名ID获取关注会员列表
	 * @param domainId 域名ID
	 * @param attentionCList 域名-关注会员列表
	 * @return 关注会员总数
	 */
	private Long getAttentionClientList(String domainId, List<DyClient> attentionCList) {

//		Page<DyBidhistory> bidhistPage = new Page<DyBidhistory>();
//		bidhistPage.setPageSize(PAGE_SIZE_8);
//		bidhistPage.setPageNo(DEFAULT_PAGE_INDEX);
//		bidhistPage.setOrderBy(sort_DyAttention);
//		Page<DyClient> attentionPage = dyAttentionService.findAttentionPage(bidhistPage, domainId);

//		attentionCList.addAll(attentionPage.getList());

		attentionCList.addAll(dyAttentionService.findAttentionList(domainId));
//		return attentionPage.getCount();
		return (long)attentionCList.size();
	}

	public static void main(String[] args) {
//		System.out.println(wxAlgorithm(100, 10));
		System.out.println((Long.MAX_VALUE));
	}
}
