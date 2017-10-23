/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.wx.aes.WXBizMsgCrypt;

/**
 * 微信企业号回调URL验证拦截器
 * @author shenzb.fnst
 * @version 2015-08-03
 */
public class WxCallBackUrlInterceptor extends BaseService implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String sToken = "QDG6eK";
		String sCorpID = "wx5823bf96d3bd56c7";
		String sEncodingAESKey = "jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C";

		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);

		String sVerifyMsgSig = request.getParameter("msg_signature");
		//String sVerifyMsgSig = "5c45ff5e21c57e6ad56bac8758b79b1d9ac89fd3";
		String sVerifyTimeStamp = request.getParameter("timestamp");
		//String sVerifyTimeStamp = "1409659589";
		String sVerifyNonce = request.getParameter("nonce");
		//String sVerifyNonce = "263014780";
		String sVerifyEchoStr = request.getParameter("echostr");
		//String sVerifyEchoStr = "P9nAzCzyDtyTWESHep1vC5X9xho/qYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp+4RPcs8TgAE7OaBO+FZXvnaqQ==";
		String sEchoStr = ""; //需要返回的明文
		try {
			if (null != sVerifyEchoStr) {
				sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
						sVerifyNonce, sVerifyEchoStr);
			}
			logger.info("verifyurl echostr: " + sEchoStr);
			response.getWriter().write(sEchoStr);
			// 验证URL成功，将sEchoStr返回
		} catch (Exception e) {
			//验证URL失败，错误原因请查看异常
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