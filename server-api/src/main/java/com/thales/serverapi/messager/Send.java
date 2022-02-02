package com.thales.serverapi.messager;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Send {

    @Value("${queue.send.message}")
    private String queueSend;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void messageSend(String msg){
        rabbitTemplate.convertAndSend(queueSend, msg);
    }
}
