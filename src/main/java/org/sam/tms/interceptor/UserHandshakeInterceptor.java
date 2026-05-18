package org.sam.tms.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.security.Principal;
import java.util.Map;

public class UserHandshakeInterceptor implements HandshakeInterceptor {
	@Override
	public boolean beforeHandshake(
		@NonNull ServerHttpRequest request,
		@NonNull ServerHttpResponse response,
		@NonNull WebSocketHandler wsHandler,
		@NonNull Map<String, Object> attributes
	) {
		if (request instanceof ServletServerHttpRequest) {
			String userId = ((ServletServerHttpRequest) request).getServletRequest().getParameter("userId");
			if (userId != null) attributes.put("user", (Principal) () -> userId);
		}
		return true;
	}

	@Override
	public void afterHandshake(
		@NonNull ServerHttpRequest request,
		@NonNull ServerHttpResponse response,
		@NonNull WebSocketHandler wsHandler,
		@Nullable Exception exception
	) {}
}
