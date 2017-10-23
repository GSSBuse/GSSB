package com.thinkgem.jeesite.common.wx.oauth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.json.Json;
import org.nutz.mapl.Mapl;

import com.thinkgem.jeesite.common.wx.oauth.Oauth;
import com.thinkgem.jeesite.common.wx.util.ConfKit;


public class OAuthServlet extends HttpServlet {
	private static final long serialVersionUID = -1847238807216447030L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();// 返回与当前request相关联的session，如果没有则在服务器端创建一个;

		String openid = (String) session.getAttribute("weixin_openid");

		String jump = request.getParameter("jump");

		if (openid == null || "".equals(openid)) {

			Oauth oauth = new Oauth(ConfKit.get("AppId"), ConfKit.get("AppSecret"));

			// 用户同意授权后，能获取到code
			String code = request.getParameter("code");

			if (code == null) {
				response.sendRedirect(oauth.getCode(ConfKit.get("oauth_uri") + jump));
				return;
			} else {
				// 用户同意授权
				if (!"authdeny".equals(code)) {
					// 获取网页授权access_token
					try {
						String responseDate = oauth.getToken(code);

						Object jsonObject = Json.fromJson(responseDate);
						openid = (String)Mapl.cell(jsonObject, "openid");
					} catch (Exception e) {
						e.printStackTrace();
					}

					session.setAttribute("weixin_openid", openid);

					// 跳转到weixiuapp页面
					response.sendRedirect(ConfKit.get("redirect_uri_"+jump));
					return;
				}
			}
		} else {
			// 跳转到app页面
			response.sendRedirect(ConfKit.get("redirect_uri_" + jump));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}