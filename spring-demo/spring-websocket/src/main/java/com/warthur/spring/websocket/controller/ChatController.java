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

@RestController
@Log4j
public class ChatController {

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	/**
	 *
	 * @param chatMessage
	 * @return
	 */
	@MessageMapping("/chat")
	@SendToUser("/chat")
	public ChatMessage sendToUser(@Payload ChatMessage chatMessage) {

		messagingTemplate.convertAndSendToUser(chatMessage.getReceiver(), "/chat/sendmsg", chatMessage);
		return chatMessage;
	}

	/**
	 * 发消息给指定用户
	 * @param chatMessage
	 * @return
	 */
	@MessageMapping("/chat/sendmsg")
	@SendToUser("/channel/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

		return chatMessage;
	}

	/**
	 * 上线广播通知所有client
	 * @param chatMessage
	 * @param headerAccessor
	 * @return
	 */
	@MessageMapping("/chat.addUser")
	@SendTo("/channel/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage,
	                           SimpMessageHeaderAccessor headerAccessor) {

		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
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
