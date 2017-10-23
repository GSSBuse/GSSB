/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.method.HandlerMethod;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.define.Constant;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.Exceptions;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.LogDao;
import com.thinkgem.jeesite.modules.sys.dao.MenuDao;
import com.thinkgem.jeesite.modules.sys.entity.Log;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2014-11-7
 */
public class LogUtils {
	
	public static final String CACHE_MENU_NAME_PATH_MAP = "menuNamePathMap";
	
	private static LogDao logDao = SpringContextHolder.getBean(LogDao.class);
	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);
	
	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, String title){
		saveLog(request, null, null, title);
	}
	/**
	 * 保存用户自己设定的日志
	 */
	public static void saveSpecialLog(HttpServletRequest request,String params){
		User user = UserUtils.getUser();
		try{
			if (user != null && user.getId() != null){
				Log log = new Log();
				log.setType(Log.TYPE_ACCESS);
				log.setRemoteAddr(StringUtils.getRemoteAddr(request));
				log.setUserAgent(request.getHeader(Constant.LOG_USER_AGENT));
				log.setRequestUri(request.getRequestURI());
				log.setMethod(request.getMethod());
				if(log.getRequestUri().contains(Constant.LOG_CASH_FLOW_APPLY_URL)){
					/*会员管理-充值提现申请*/
					logCashFlowApply(log,request);
				}else if(log.getRequestUri().contains(Constant.LOG_CLIENT_FORM_SAVE_URL)){
					/*会员管理-修改*/
					logClientFormSave(log,request);
				}else if(log.getRequestUri().contains(Constant.LOG_DOMAINNAME_SAVEPASS_URL)){
					/*域名拍卖管理-审核*/
					log.setTitle(Constant.LOG_DOMAINNAME_SAVEPASS);
					logDomainnameSavePass(log,request);
				}else if(log.getRequestUri().contains(Constant.LOG_DOMAINNAME_SAVE_URL)){
					/*域名拍卖管理-修改*/
					log.setTitle(Constant.LOG_DOMAINNAME_SAVE);
					logDomainnameSavePass(log,request);
				}else if(log.getRequestUri().contains(Constant.LOG_DOMAINNAME_STOPSELL_URL)){
					/*域名拍卖管理-停止拍卖*/
					log.setTitle(Constant.LOG_DOMAINNAME_STOPSELL);
					log.setParams(params);
				}else if(log.getRequestUri().contains(Constant.LOG_BIDHISTORY_BID_URL)){
					/*域名拍卖管理-出价管理-出价*/
					log.setTitle(Constant.LOG_BIDHISTORY_BID);
					log.setParams(params);
				}else if(log.getRequestUri().contains(Constant.LOG_BIDHISTORY_DELETE_URL)){
					/*域名拍卖管理-出价管理-删除*/
					log.setTitle(Constant.LOG_BIDHISTORY_DELETE);
					log.setParams(params);
				}else if(log.getRequestUri().contains(Constant.LOG_DOMAINNAME_DEALSAVE_URL)){
					/*域名成交管理-修改*/
					logDomainnameDealsave(log,request);
				}else if(log.getRequestUri().contains(Constant.LOG_CASHFLOW_DOING_URL)){
					/*财务管理-提现处理*/
					log.setTitle(Constant.LOG_CASHFLOW_DOING);
					log.setParams(params);
				}else if(log.getRequestUri().contains(Constant.LOG_CASHFLOW_CONFIRM_URL)){
					/*财务管理-确认*/
					log.setTitle(Constant.LOG_CASHFLOW_CONFIRM);
					log.setParams(params);
				}else if(log.getRequestUri().contains(Constant.LOG_CASHFLOW_DELETE_URL)){
					/*财务管理-删除*/
					log.setTitle(Constant.LOG_CASHFLOW_DELETE);
					log.setParams(params);
				}else if(log.getRequestUri().contains(Constant.LOG_BIDHISTORY_UPDATEPROXY_URL)){
					/*域名拍卖管理-出价管理-修改代理竞价*/
					log.setTitle(Constant.LOG_BIDHISTORY_UPDATEPROXY);
					log.setParams(params);
				}
				// 异步保存日志
				new SaveLogThread(log, null, null).start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存日志
	 */
	public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title){
		User user = UserUtils.getUser();
		if (user != null && user.getId() != null){
			Log log = new Log();
			log.setTitle(title);
			log.setParams(request.getParameterMap());
			log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
			log.setRemoteAddr(StringUtils.getRemoteAddr(request));
			log.setUserAgent(request.getHeader(Constant.LOG_USER_AGENT));
			log.setRequestUri(request.getRequestURI());
			log.setMethod(request.getMethod());
			// 异步保存日志
			new SaveLogThread(log, handler, ex).start();
		}
	}
	
	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread{
		
		private Log log;
		private Object handler;
		private Exception ex;
		
		public SaveLogThread(Log log, Object handler, Exception ex){
			super(SaveLogThread.class.getSimpleName());
			this.log = log;
			this.handler = handler;
			this.ex = ex;
		}
		
		@Override
		public void run() {
			// 获取日志标题
			if (StringUtils.isBlank(log.getTitle())){
				String permission = "";
				if (handler instanceof HandlerMethod){
					Method m = ((HandlerMethod)handler).getMethod();
					RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
					permission = (rp != null ? StringUtils.join(rp.value(), ",") : "");
				}
				log.setTitle(getMenuNamePath(log.getRequestUri(), permission));
			}
			// 如果有异常，设置异常信息
			log.setException(Exceptions.getStackTraceAsString(ex));
			// 如果无标题并无异常日志，则不保存信息
			if (StringUtils.isBlank(log.getTitle()) && StringUtils.isBlank(log.getException())){
				return;
			}
			// 保存日志信息
			log.preInsert();
			logDao.insert(log);
		}
	}

	/**
	 * 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
	 */
	public static String getMenuNamePath(String requestUri, String permission){
		String href = StringUtils.substringAfter(requestUri, Global.getAdminPath());
		@SuppressWarnings("unchecked")
		Map<String, String> menuMap = (Map<String, String>)CacheUtils.get(CACHE_MENU_NAME_PATH_MAP);
		if (menuMap == null){
			menuMap = Maps.newHashMap();
			List<Menu> menuList = menuDao.findAllList(new Menu());
			for (Menu menu : menuList){
				// 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
				String namePath = "";
				if (menu.getParentIds() != null){
					List<String> namePathList = Lists.newArrayList();
					for (String id : StringUtils.split(menu.getParentIds(), ",")){
						if (Menu.getRootId().equals(id)){
							continue; // 过滤跟节点
						}
						for (Menu m : menuList){
							if (m.getId().equals(id)){
								namePathList.add(m.getName());
								break;
							}
						}
					}
					namePathList.add(menu.getName());
					namePath = StringUtils.join(namePathList, "-");
				}
				// 设置菜单名称路径
				if (StringUtils.isNotBlank(menu.getHref())){
					menuMap.put(menu.getHref(), namePath);
				}else if (StringUtils.isNotBlank(menu.getPermission())){
					for (String p : StringUtils.split(menu.getPermission())){
						menuMap.put(p, namePath);
					}
				}
				
			}
			CacheUtils.put(CACHE_MENU_NAME_PATH_MAP, menuMap);
		}
		String menuNamePath = menuMap.get(href);
		if (menuNamePath == null){
			for (String p : StringUtils.split(permission)){
				menuNamePath = menuMap.get(p);
				if (StringUtils.isNotBlank(menuNamePath)){
					break;
				}
			}
			if (menuNamePath == null){
				return "";
			}
		}
		return menuNamePath;
	}

	/**
	 * 会员管理-充值提现申请
	 */
	private static void logCashFlowApply(Log log,HttpServletRequest request){
		String paramsStr = "";
		paramsStr += "会员米友号：" + request.getParameter("dyid");
		paramsStr += "  会员昵称：" + request.getParameter("dName");
		paramsStr += "  会员姓名：" + request.getParameter("dNickname");
		paramsStr += "  会员当前账户总额：" + request.getParameter("dAccountBalance");
		paramsStr += "  会员当前冻结金额：" + request.getParameter("dFreezeBalance");
		paramsStr += "  会员当前可用金额：" + request.getParameter("dLast");
		paramsStr += "  操作：" +  request.getParameter("operate");
		paramsStr += "  操作金额：" + request.getParameter("operateAmount");
		log.setTitle(Constant.LOG_CASH_FLOW_APPLY);
		log.setParams(paramsStr);
	}
	
	/**
	 * 会员管理-修改
	 */
	private static void logClientFormSave(Log log,HttpServletRequest request){
		String paramsStr = "";
		paramsStr += "米友号：" + request.getParameter("dyid");
		paramsStr += "  所属经纪人：" + request.getParameter("broker.name");
		paramsStr += "  微信标示：" + request.getParameter("openid");
		paramsStr += "  姓名：" + request.getParameter("name");
		paramsStr += "  微信昵称：" + request.getParameter("nickname");
		paramsStr += "  微信头像：" + request.getParameter("photo");
		paramsStr += "  邮箱：" + request.getParameter("email");
		paramsStr += "  密码：" +  request.getParameter("newPayPassword");
		paramsStr += "  手机：" + request.getParameter("mobile");
		paramsStr += "  QQ号：" + request.getParameter("qq");
		paramsStr += "  微信号：" + request.getParameter("wx");
		if(StringUtils.equals("0", request.getParameter("sealFlag"))){
			paramsStr += "  封号标记：" + "可用";
		}else{
			paramsStr += "  封号标记：" + "不可用";
		}
		if(StringUtils.equals("0", request.getParameter("authenticationMark"))){
			paramsStr += "  身份认证：" + "未认证";
		}else{
			paramsStr += "  身份认证：" + "已认证";
		}
		paramsStr += "  银行卡号：" + request.getParameter("defaultIncomeExpense");
		paramsStr += "  身份证正面照片：" + request.getParameter("authenticationPositiveImageUrl");
		paramsStr += "  身份证反面照片：" + request.getParameter("authenticationNegativeImageUrl");
		paramsStr += "  备注信息：" + request.getParameter("remarks");
		log.setTitle(Constant.LOG_CLIENT_FORM_SAVE);
		log.setParams(paramsStr);
	}
	/**
	 * 域名拍卖管理-审核(修改)
	 */
	public static void logDomainnameSavePass(Log log,HttpServletRequest request){
		String paramsStr = "";
		paramsStr += "域名名称：" + request.getParameter("name");
		paramsStr += "  卖家米友号：" + request.getParameter("dyClient.dyid");
		paramsStr += "  拍卖结束时间：" + request.getParameter("endTime");
		paramsStr += "  域名描述：" + request.getParameter("description");
		paramsStr += "  保留价：" + request.getParameter("reservePrice");
		paramsStr += "  状态：" + DySysUtils.transformDomainStatus(request.getParameter("status"));
		paramsStr += "  转发红包总额：" + request.getParameter("bonusShareTotal");
		paramsStr += "  转发红包份数：" +  request.getParameter("bonusShareNumber");
		paramsStr += "  次高价红包：" + request.getParameter("bonusSecond");
		paramsStr += "  图片1：" + request.getParameter("image1");
		paramsStr += "  图片2：" + request.getParameter("image2");
		paramsStr += "  图片3：" + request.getParameter("image3");
		paramsStr += "  备注：" + request.getParameter("remarks");
		log.setParams(paramsStr);
	}
	
	/**
	 * 域名成交管理-修改
	 */
	public static void logDomainnameDealsave(Log log,HttpServletRequest request){
		String paramsStr = "";
		paramsStr += "域名名称：" + request.getParameter("domainName");
		paramsStr += "  卖家姓名：" + request.getParameter("sellName");
		paramsStr += "  卖家昵称：" + request.getParameter("sellNickname");
		paramsStr += "  买家姓名：" + request.getParameter("buyName");
		paramsStr += "  买家昵称：" + request.getParameter("buyNickname");
		paramsStr += "  成交金额：" + request.getParameter("bidAmount");
		paramsStr += "	域名状态：" + DySysUtils.transformDomainStatus(request.getParameter("status"));
		paramsStr += "  交易有效时间：" + request.getParameter("waitTime");
		log.setTitle(Constant.LOG_DOMAINNAME_DEALSAVE);
		log.setParams(paramsStr);
	}
}
