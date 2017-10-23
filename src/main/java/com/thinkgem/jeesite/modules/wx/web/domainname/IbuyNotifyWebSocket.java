package com.thinkgem.jeesite.modules.wx.web.domainname;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class IbuyNotifyWebSocket extends TextWebSocketHandler {
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	    System.out.println("IbuyNotifyWebSocket connect");
	    super.afterConnectionEstablished(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("IbuyNotifyWebSocket close");
	    super.afterConnectionClosed(session, status);
	}
}
