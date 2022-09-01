package com.example.demo.rabbitmq;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
 * Message.builder().type().sender().receiver().data() 사용 가능 (type=null,
 * sender=null, receiver=null, data=null)
 */
public class Message {
	private String sender;
	private String channelId;
	private Object data;
}
