package com.warthur.spring.websocket.config;

import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
@Log4j
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	// 注入adviser service 建立聊天关系

	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
		// 客户端与服务器端建立连接的点
		stompEndpointRegistry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {

		// 配置客户端发送信息的路径的前缀
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/channel", "/user");
		registry.setUserDestinationPrefix("/user");
	}
}
