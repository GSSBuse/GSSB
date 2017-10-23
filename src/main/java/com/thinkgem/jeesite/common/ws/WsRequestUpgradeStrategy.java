package com.thinkgem.jeesite.common.ws;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.jetty.JettyRequestUpgradeStrategy;

@Component
public class WsRequestUpgradeStrategy extends JettyRequestUpgradeStrategy{

	@Override
    public String[] getSupportedVersions() {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public List<WebSocketExtension> getSupportedExtensions(ServerHttpRequest request) {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public void upgrade(ServerHttpRequest request, ServerHttpResponse response, String selectedProtocol,
            List<WebSocketExtension> selectedExtensions, Principal user, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws HandshakeFailureException {
	    
    }

}
