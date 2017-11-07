package com.warthur.spring.websocket.controller;

import com.warthur.spring.websocket.domain.ChatMessage;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j
public class ChatController {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@MessageMapping("/chat")
	@SendToUser("/chat")
	public ChatMessage sendToUser(@Payload ChatMessage chatMessage) {

		messagingTemplate.convertAndSendToUser(chatMessage.getReceiver(), "/chat.sendMessage", chatMessage);
		return chatMessage;
	}

	@MessageMapping("/chat.sendMessage")
	@SendToUser("/channel/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/channel/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage,
	                           SimpMessageHeaderAccessor headerAccessor) {

		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

		return chatMessage;
	}

	/**
	 * 测试对指定用户发送消息方法
	 * @return
	 */
	@GetMapping("/send")
	public ChatMessage send() {
		ChatMessage message = ChatMessage.builder()
				.sender("server")
				.type(ChatMessage.MessageType.CHAT)
				.content("服务端发给你的")
				.receiver("all")
				.build();
		messagingTemplate.convertAndSendToUser("yolanda", "/chat", message);

		return message;
	}
}
