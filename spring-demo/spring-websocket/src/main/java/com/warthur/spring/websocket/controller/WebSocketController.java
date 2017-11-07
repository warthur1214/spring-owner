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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Log4j
@MessageMapping("channel")
public class WebSocketController {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	/**
	 * 点对点消息
	 * @param chatMessage
	 * @return
	 */
	@MessageMapping("/chat")
	@SendToUser(value = "/channel/chat", broadcast = false)
	public ChatMessage sendToUser(@Payload ChatMessage chatMessage, Principal principal) {
		log.error(principal.getName());
		return chatMessage;
	}

	/**
	 * 广播通知所有client
	 * @param chatMessage
	 * @param headerAccessor
	 * @return
	 */
	@MessageMapping("/publish")
	@SendTo("/channel/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage,
	                           SimpMessageHeaderAccessor headerAccessor) {

		if (chatMessage.getType() == ChatMessage.MessageType.JOIN) {
			headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		} else if (chatMessage.getType() == ChatMessage.MessageType.JOIN) {
			headerAccessor.getSessionAttributes().remove("username");
		}
		return chatMessage;
	}

	/**
	 * web api测试指定用户发送消息
	 * @param user
	 * @return
	 */
	@GetMapping("/send/{user}")
	public ChatMessage send(@PathVariable String user) {
		ChatMessage message = ChatMessage.builder()
				.sender("server")
				.type(ChatMessage.MessageType.CHAT)
				.content("服务端发给你的")
				.receiver(user)
				.build();
		messagingTemplate.convertAndSendToUser(message.getReceiver(), "/chat", message);

		return message;
	}
}
