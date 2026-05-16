package com.sam.taskmanagement.websocket;

import com.sam.taskmanagement.interceptor.CustomHandshakeHandler;
import com.sam.taskmanagement.interceptor.UserHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
		// The endpoint for client connect to server websocket
		registry
		    .addEndpoint("/ws")
		    .setAllowedOriginPatterns("*")
		    .addInterceptors(new UserHandshakeInterceptor())
		    .setHandshakeHandler(new CustomHandshakeHandler())
		    .withSockJS();
	}
	
	@Override
	public void configureMessageBroker(@NonNull MessageBrokerRegistry registry) {
		// Enable a simple broker in memory to transfer data between server and client
		registry.enableSimpleBroker("/topic", "/queue");
		
		// Define prefix for requests from client to server
		registry.setApplicationDestinationPrefixes("/app");
		
		// Define prefix for user
		registry.setUserDestinationPrefix("/user");
	}
}
