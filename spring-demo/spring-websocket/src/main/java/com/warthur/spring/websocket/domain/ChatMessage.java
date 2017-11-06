package com.warthur.spring.websocket.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Silence on 2017/4/25.
 */
@Data
@Builder
public class ChatMessage implements Serializable {

	private MessageType type;
	private String content;
	private String sender;

	public enum MessageType {
		CHAT, JOIN, LEAVE;
	}
}
