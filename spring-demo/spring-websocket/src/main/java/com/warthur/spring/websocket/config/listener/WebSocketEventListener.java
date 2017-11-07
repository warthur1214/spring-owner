package com.warthur.spring.websocket.config.listener;

import com.warthur.spring.websocket.domain.ChatMessage;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Log4j
public class WebSocketEventListener {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		log.info("Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String username = (String) headerAccessor.getSessionAttributes().get("username");
		if(username != null) {
			log.info("User Disconnected : " + username);

			ChatMessage chatMessage = ChatMessage.builder()
					.type(ChatMessage.MessageType.LEAVE)
					.sender(username)
					.receiver("all")
					.build();

			headerAccessor.getSessionAttributes().remove("username");

			// 广播用户离线消息
			messagingTemplate.convertAndSend("/channel/public", chatMessage);
		}
	}
}
