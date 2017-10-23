/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web.dy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.mapl.Mapl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.wx.WeChat;
import com.thinkgem.jeesite.common.wx.bean.Article;
import com.thinkgem.jeesite.common.wx.bean.ArticlesNews;
import com.thinkgem.jeesite.common.wx.oauth.Message;
import com.thinkgem.jeesite.common.wx.util.ConfKit;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.Threads;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyClient;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessage;
import com.thinkgem.jeesite.modules.sys.entity.dy.DyMessagegroup;
import com.thinkgem.jeesite.modules.sys.service.dy.DyClientService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyMessageService;
import com.thinkgem.jeesite.modules.sys.service.dy.DyMessagegroupService;

/**
 * 群发消息列表管理Controller
 * @author quanyf.fnst
 * @version 2015-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dy/dyMessagegroup")
public class DyMessagegroupController extends BaseController {
	@Autowired
	private DyMessagegroupService dyMessagegroupService;
	
	@Autowired
	private DyClientService dyClientService;
	
	@Autowired
	private DyMessageService dyMessageService;   //推送消息管理Service
	
	@ModelAttribute
	public DyMessagegroup get(@RequestParam(required=false) String id) {
		DyMessagegroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dyMessagegroupService.get(id);
		}
		if (entity == null){
			entity = new DyMessagegroup();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:dy:dyMessagegroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(DyMessagegroup dyMessagegroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DyMessagegroup> page = dyMessagegroupService.findPage(new Page<DyMessagegroup>(request, response), dyMessagegroup); 
		model.addAttribute("page", page);
		return "modules/sys/dy/dyMessagegroupList";
	}

	@RequiresPermissions("sys:dy:dyMessagegroup:view")
	@RequestMapping(value = "form")
	public String form(DyMessagegroup dyMessagegroup, Model model) {
		//获取该图文消息下得所有消息
		List<DyMessage> dyMessageList;
		if(StringUtils.isNotBlank(dyMessagegroup.getMessageList())){
			dyMessageList = dyMessageService.getListByIds(dyMessagegroup.getMessageList());
		} else {
			dyMessageList = Lists.newArrayList();
		}
		
		int size = dyMessageList.size();
		if (size < 10) {
			for (int i=0; i<(10-size); i++) {
				dyMessageList.add(new DyMessage());
			}
		}
		dyMessagegroup.setDyMessageList(dyMessageList);
		model.addAttribute("dyMessagegroup", dyMessagegroup);
		return "modules/sys/dy/dyMessagegroupForm";
	}

	@RequiresPermissions("sys:dy:dyMessagegroup:edit")
	@RequestMapping(value = "save")
	public String save(DyMessagegroup dyMessagegroup, HttpServletRequest request,Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dyMessagegroup)){
			return form(dyMessagegroup, model);
		}
		boolean reSend = true;		//判断是否要重新发送消息
		try{
			if(StringUtils.isNotBlank(dyMessagegroup.getId())){			//如果没有更新发送时间，则不需要重新发送
				DyMessagegroup dyMessagegroupOld = dyMessagegroupService.get(dyMessagegroup.getId());
				if(StringUtils.equals("1", dyMessagegroup.getType()) && StringUtils.equals("1", dyMessagegroupOld.getType()) &&
						(dyMessagegroupOld.getSendTime().getTime() - dyMessagegroup.getSendTime().getTime() == 0)){
					reSend = false;
				}
			}
			dyMessagegroupService.saveMessageGroup(dyMessagegroup); //保存消息内容
			if(reSend){
				dyMessagegroup.setStatus(Constant.MESSAGEGROUP_STATUS_NOTSEND);
				sendByTiming(dyMessagegroup);	//定时发送
			}
			addMessage(redirectAttributes, "保存群发消息列表成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			addMessage(redirectAttributes, "保存群发消息列表失败【"+e.getMessage()+"】");
		}
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyMessagegroup/?repage";
	}
	/**
	 * 定时发送
	 * @param dyMessagegroup	//消息群组Id
	 */
	public void sendByTiming(DyMessagegroup dyMessagegroup){
		final String dyMessagegroupId = dyMessagegroup.getId();
		long timeSpace = 0;		//发送消息时间间隔
		if(StringUtils.equals("1", dyMessagegroup.getType())){
			long nowTime = System.currentTimeMillis();
			long sendTime = dyMessagegroup.getSendTime().getTime();
			if(sendTime>nowTime){
				timeSpace = (dyMessagegroup.getSendTime().getTime() - System.currentTimeMillis())/1000;
			}
		}
		/*定时发送队列*/
		final Map<String , ScheduledFuture<?>> scheduledMap = Threads.getScheduledMap();
		if(scheduledMap.containsKey(dyMessagegroupId)){
			scheduledMap.get(dyMessagegroupId).cancel(false);
			System.out.println(dyMessagegroupId+"被取消");
			scheduledMap.remove(dyMessagegroupId);
		}
		/*定时发送*/
		ScheduledExecutorService executorScheduledService = Threads.getExecutorScheduledService();
		ScheduledFuture<?> scheduledFuture = executorScheduledService.schedule(new Runnable() {
            public void run() {
            	DyMessagegroup dyMessagegroupSQL = dyMessagegroupService.get(dyMessagegroupId);//消息群组实体
        		List<DyMessage> newMessageListSQL = dyMessageService.getListByIds(dyMessagegroupSQL.getMessageList());	
                sendMessageGroup(newMessageListSQL,dyMessagegroupSQL.getSendToAll());
                /*将发送状态修改为已发送*/
        		dyMessagegroupService.updateTypeSend(dyMessagegroupId);
        		scheduledMap.remove(dyMessagegroupId);
            }
        },timeSpace,TimeUnit.SECONDS);
		scheduledMap.put(dyMessagegroupId, scheduledFuture);
	}
	/**
	 * 群发消息
	 * @param dyMessageList 群发列表
	 * @param isSendToAll 是否群发
	 */
	public void sendMessageGroup(List<DyMessage> dyMessageList,String isSendToAll){
		String accessToken;
		try {
			accessToken = WeChat.getAccessToken();
	 		System.out.println("accessToken:" + accessToken);
			
	 		List<ArticlesNews> artList = new ArrayList<ArticlesNews>();
	 		for (DyMessage dyMessage : dyMessageList){
				ArticlesNews articles = new ArticlesNews();
				articles.setTitle(dyMessage.getTitle());
				articles.setDescription(dyMessage.getDescription());
				articles.setPicurl(ConfKit.get("host_address") + dyMessage.getPicture());
				
				if("0".equals(dyMessage.getUrlType())){
					articles.setUrl(ConfKit.get("root_uri") + "/domainname/viewArticle?articleId=" + dyMessage.getUrlId());
				} else {
					articles.setUrl(ConfKit.get("root_uri") + "/domainname/singleDomainname?singleDomainId=" + dyMessage.getUrlId() + "&autoFollow=1");
				}
				artList.add(articles);
			}
			Message message = new Message();
			List<DyClient> clientList = new ArrayList<DyClient>(); //接受者群组
			if(StringUtils.equals(Constant.MESSAGEGROUP_SEND_ALL, isSendToAll)){
				 clientList = dyClientService.findList(new DyClient());
			}else{
				clientList = dyClientService.findLinkClientList(dyMessageList);
			}
			for (DyClient client : clientList) {
				if (client.getOpenid().length() == 28) {
					message.SendNews(accessToken, client.getOpenid(), artList);
				}
			}
//			message.SendNews(accessToken, "oQlZNwd0KUYetW3z0EvWHVb0VK6s", artList);
//			message.SendNews(accessToken, "oQlZNwVYC8vr8PuGuI56J4JWpE1I", artList);
//			message.SendNews(accessToken, "oQlZNwUyC1y0aC_yOW4vpTPagHLA", artList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequiresPermissions("sys:dy:dyMessagegroup:edit")
	@RequestMapping(value = "delete")
	public String delete(DyMessagegroup dyMessagegroup, RedirectAttributes redirectAttributes) {
		dyMessagegroupService.delete(dyMessagegroup);
		final String dyMessagegroupId = dyMessagegroup.getId();
		/*如果消息还没有发送，则从消息队列里面删除*/
		if(StringUtils.equals(Constant.MESSAGEGROUP_STATUS_NOTSEND, dyMessagegroup.getStatus())){
			/*定时发送队列*/
			final Map<String , ScheduledFuture<?>> scheduledMap = Threads.getScheduledMap();
			if(scheduledMap.containsKey(dyMessagegroupId)){
				scheduledMap.get(dyMessagegroupId).cancel(false);
				System.out.println(dyMessagegroupId+"被取消");
				scheduledMap.remove(dyMessagegroupId);
			}
		}
		addMessage(redirectAttributes, "删除群发消息列表成功");
		return "redirect:"+Global.getAdminPath()+"/sys/dy/dyMessagegroup/?repage";
	}

	@RequestMapping(value = "treeSelect")
	public String treeSelect(HttpServletRequest request,Model model) {
//		<sys:treeselect id="urlNameId${status.index}" name="dyMessageList[${status.index}].urlId" value="${dyMessage.urlId}" labelName="urlName${status.index}" labelValue="${dyMessage.urlName}"
//				title="文章列表" url="/sys/dy/dyArticle/selectList"/>
		String type = request.getParameter("type");
		String index = request.getParameter("index");
		String urlIdTemp = request.getParameter("urlIdTemp");
		String urlNameTemp = request.getParameter("urlNameTemp");
		model.addAttribute("id", "urlNameId"+index);
		model.addAttribute("name", "dyMessageList["+index+"].urlId");
		model.addAttribute("value", urlIdTemp);
		model.addAttribute("labelName", "urlName"+index);
		model.addAttribute("labelValue", urlNameTemp);
		if(StringUtils.equals(Constant.MESSAGE_URL_ARTILE, type)){
			model.addAttribute("title", "文章列表");
			model.addAttribute("url", "/sys/dy/dyArticle/selectList");
		}else{
			model.addAttribute("title", "域名列表");
			model.addAttribute("url", "/sys/dy/dyDomainname/selectList");
		}
		
		return "modules/sys/dy/treeSelect";
	}
	
	public static void main(String[] args) {
		DyMessagegroupController con = new DyMessagegroupController();
		con.sendMessageGroup1();
	}
	
	public void sendMessageGroup1(){
 		String accessToken;
		try {
			accessToken = WeChat.getAccessToken();
//			accessToken = "FpVNA1hKE3WMxqjlVHr9a2Im1HrxOMzc--2K-vx7-3R5d9whTaHeEyJ3LBVrI7VaSTdTnZ1noKr5qo1opHInNG-PVCdoZG7bgY5tvejowUsYBMeAIADGR";
	 		System.out.println("accessToken:" + accessToken);
			
			Article article = new Article();
			File picture = new File("C:\\Chrysanthemum.jpg");
			Object json = WeChat.uploadMedia(accessToken, "image", picture);
			String thumbMediaId = Mapl.cell(json, "media_id").toString();
			System.out.println(thumbMediaId);
			
			article.setTitle("群发消息测试！！！");
			article.setContent("<p>\n <img alt='' src='/dengyu/userfiles/1/images/photo/2015/10/Hydrangeas.jpg' style='width: 94px; height: 59px;' /></p>\n <p> 你猜是显示图片还是标签？</p>");
			article.setContent_source_url("http://www.pymchina.com/dengyu/domainname/singleDomainname?singleDomainId=34fcea6a37a14e969587ee7d2e12361a");
			article.setAuthor("Song Shuqing");
			article.setDigest("这是一个群发消息测试内容。");
			article.setThumb_media_id(thumbMediaId);
			
			List<Article> artList = new ArrayList<Article>();
			artList.add(article);
				
			Message message = new Message();
			json = message.uploadnews(accessToken, artList);
			String mediaId = Mapl.cell(json, "media_id").toString();
			System.out.println(mediaId);
//			String mediaId = "2aiwoYtcN6eplB6hJXRzPn92nfhBIz7CRyukPftY0rkifHnz7yDpQlva3n3hylVq";
			String[] ids = new String[]{"oQlZNwd0KUYetW3z0EvWHVb0VK6s","oQlZNwVYC8vr8PuGuI56J4JWpE1I","oQlZNwUyC1y0aC_yOW4vpTPagHLA"};
		//	String[] ids = new String[]{"oQlZNwUpRc_v8VmoYTP--H63ftRc", "oQlZNwUyC1y0aC_yOW4vpTPagHLA", "oQlZNwe_YJ2kOmO_rEcROxxHgDQ8", "oQlZNwSOutGWZxCM76hO-G-tHMfQ", "oQlZNwVYC8vr8PuGuI56J4JWpE1I", "oQlZNwd0KUYetW3z0EvWHVb0VK6s"};
			if (!StringUtils.isBlank(mediaId)) {
				json = message.massSend(accessToken, ids, mediaId);
				System.out.println(json);
			}
			
//			WxTemplate template = new WxTemplate();
//			template.setTouser("oQlZNwVYC8vr8PuGuI56J4JWpE1I");
//			template.setTemplate_id("oE2NmoeJs7OUXjRZwNEn2xUwHMttY5oxK7zGjkbjc2Y");
//			template.setUrl("http://www.pymchina.com/dengyutest/domainname/icenter");
//			Map<String, TemplateData> data = new HashMap<String, TemplateData>();
//			template.setData(data);
//			Object json = message.sendTemplate(accessToken, template);
//			System.out.println(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}