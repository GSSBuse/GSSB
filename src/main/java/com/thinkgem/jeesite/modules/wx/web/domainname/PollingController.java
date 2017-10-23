package com.thinkgem.jeesite.modules.wx.web.domainname;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.bean.AjaxResult;
import com.thinkgem.jeesite.modules.sys.service.dy.DyBidhistoryService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyDomainnameService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyNewsService;
import com.thinkgem.jeesite.modules.sys.utils.NewsUpdateFlagUtil;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 轮询
 * @author shenzb.fnst
 * @since 2015/10/12
 *
 */
@Controller
@RequestMapping(value = "${frontPath}/domainname/polling")
public class PollingController {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DyNewsService dyNewsService;             // 最新消息管理Service

	@Autowired
	private DyBidhistoryService dyBidhistoryService; // 会员出价信息Service

	@Autowired
	private DyDomainnameService dyDomainnameService; // 域名信息管理Service

	@Autowired
	private DyClientService dyClientService;         // 会员信息管理Service
		
	/**
	 * 根据会员ID查询最新消息表，获取该会员相关域名的新消息计数不为0的记录的数量
	 * @param clientId 会员ID
	 * @return
	 */
	@RequestMapping(value = {"message"})
	@ResponseBody
	public AjaxResult message(String clientId,@RequestParam(required=false) String timeStamp) {
		//判断时间戳，相等无需操作
		if(!NewsUpdateFlagUtil.isNeedUpdate(timeStamp)) {
			
			// 等待更新
			try {
				synchronized (NewsUpdateFlagUtil.updateLock) {
					NewsUpdateFlagUtil.updateLock.wait(20 * 1000);
	            }
				
				// 时间到了还没有更新，则直接返回时间戳
				if(!NewsUpdateFlagUtil.isNeedUpdate(timeStamp)) {
					AjaxResult ar = AjaxResult.makeSuccess("");
					ar.getData().put("timeStamp", timeStamp);
					return ar;
				}
				
			} catch (InterruptedException e) {
				// 有更新, 程序继续
				logger.debug("新闻消息数据有更新，进行读取数据操作");
			}
		}
		
		AjaxResult ar = AjaxResult.makeSuccess("");
		if (DySysUtils.getCurrentDyClient() == null) {
			return AjaxResult.makeError("用户未登录");
		}
		ar.getData().put("messageNum", dyNewsService.messageService(DySysUtils.getCurrentDyClient().getId()));
		ar.getData().put("timeStamp", NewsUpdateFlagUtil.getUpdateTimestamp());
		return ar;
	}
	
	/**
	 * 根据域名ID列表查询出价表，获取每个域名的最新出价
	 * @param domainIdList 域名ID列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"auction"})
	public AjaxResult auction(@RequestParam("domainList[]")String[] domainList){
		AjaxResult ar = AjaxResult.makeSuccess("");
		ar.getData().put("newAmount", dyBidhistoryService.auctionService(domainList));
		ar.getData().put("sysServerTime", new Date());//传输一个系统时间，用于计算倒计时
		return ar;
	}
	
	/**
	 * 根据域名ID，页面的出价ID查询出价表获取大于页面出价的出价记录
	 * @param domainId 域名ID
	 * @param bidhistoryId 页面的出价ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"bidding"})
	public AjaxResult bidding(String domainId , String bidhistoryId){
		AjaxResult ar = AjaxResult.makeSuccess("");
		ar.getData().put("bidClientList", dyBidhistoryService.biddingService(domainId , bidhistoryId));
		return ar;
	}
}
