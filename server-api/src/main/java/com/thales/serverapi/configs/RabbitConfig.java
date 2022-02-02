package com.thales.serverapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RabbitConfig {
 
    @Value("${queue.send.message}")
    private String sendQ;

	@Value("${queue.receive.message}")
	private String receiveQ;

    @Bean
	public Queue queueS(){
		return new Queue(sendQ,true);
	}

    @Bean
	public Queue queueR(){
		return new Queue(receiveQ,true);
	}

}
