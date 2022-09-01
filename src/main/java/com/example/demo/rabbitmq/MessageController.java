package com.example.demo.rabbitmq;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {
	private final SimpMessageSendingOperations messageSendingOperations;

	/*
	 * /pub/hello 메시지 발행
	 * /topic/channelId 구독
	 */

	@MessageMapping("/nurse")
	public void newUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {

		headerAccessor.getSessionAttributes().put("username", message.getSender());
		messageSendingOperations.convertAndSend("/topic/" + message.getChannelId(), message);
	}
}
