package com.warthur.spring.websocket.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Silence on 2017/4/25.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Serializable {

	private MessageType type;
	private String content;
	private String sender;
	private String receiver;

	public enum MessageType {
		CHAT, JOIN, LEAVE;
	}
}
