/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.wx;

import java.io.StringReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.wx.aes.WXBizMsgCrypt;

/**
 * 微信服务器推送suite_ticket拦截器
 * @author shenzb.fnst
 * @version 2015-08-03
 */
public class WxSuiteTicketInterceptor extends BaseService implements HandlerInterceptor {

	private static final String SUITE_TICKET = "suite_ticket";       // 微信后台推送的ticket
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String sToken = "QDG6eK";
		String sSuiteID = "wx5823bf96d3bd56c7";
		String sEncodingAESKey = "jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C";

		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sSuiteID);

		// String sReqMsgSig = request.getParameter("msg_signature");
		String sReqMsgSig = "477715d11cdb4164915debcba66cb864d751f3e6";
		// String sReqTimeStamp = request.getParameter("timestamp");
		String sReqTimeStamp = "1409659813";
		// String sReqNonce = request.getParameter("nonce");
		String sReqNonce = "1372623149";
		// post请求的密文数据
		String sReqData = "<xml><ToUserName><![CDATA[wx5823bf96d3bd56c7]]></ToUserName><Encrypt><![CDATA[RypEvHKD8QQKFhvQ6QleEB4J58tiPdvo+rtK1I9qca6aM/wvqnLSV5zEPeusUiX5L5X/0lWfrf0QADHHhGd3QczcdCUpj911L3vg3W/sYYvuJTs3TUUkSUXxaccAS0qhxchrRYt66wiSpGLYL42aM6A8dTT+6k4aSknmPj48kzJs8qLjvd4Xgpue06DOdnLxAUHzM6+kDZ+HMZfJYuR+LtwGc2hgf5gsijff0ekUNXZiqATP7PF5mZxZ3Izoun1s4zG4LUMnvw2r+KqCKIw+3IQH03v+BCA9nMELNqbSf6tiWSrXJB3LAVGUcallcrw8V2t9EL4EhzJWrQUax5wLVMNS0+rUPA3k22Ncx4XXZS9o0MBH27Bo6BpNelZpS+/uh9KsNlY6bHCmJU9p8g7m3fVKn28H3KDYA5Pl/T8Z1ptDAVe0lXdQ2YoyyH2uyPIGHBZZIs2pDBS8R07+qN+E7Q==]]></Encrypt><AgentID><![CDATA[218]]></AgentID></xml>";

		try {
			ServletInputStream sis = request.getInputStream();
			// 取HTTP请求流长度  
			int size = request.getContentLength();
			// 用于缓存每次读取的数据
			byte[] buffer = new byte[size];
			// 用于存放结果的数组
			byte[] xmldataByte = new byte[size];
			int count = 0;
			int rbyte = 0;
			// 循环读取
			while (count < size) {
				// 每次实际读取长度存于rbyte中
				rbyte = sis.read(buffer);
				for (int i = 0; i < rbyte; i++) {
					xmldataByte[count + i] = buffer[i];
				}
				count += rbyte;
			}
			sReqData = new String(xmldataByte, "UTF-8");

			String sMsg = wxcpt.DecryptMsg(sReqMsgSig, sReqTimeStamp, sReqNonce, sReqData);
			logger.info("after decrypt msg: " + sMsg);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(sMsg);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("SuiteTicket");
			String suiteTicket = nodelist1.item(0).getTextContent();
			logger.info("suiteTicket：" + suiteTicket);
			// 写入缓存
			CacheUtils.put(SUITE_TICKET, suiteTicket);
			// 获取SuiteTicket成功，返回"success"
			response.getWriter().write("success");
		} catch (Exception e) {
			// 获取SuiteTicket失败，错误原因请查看异常
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}