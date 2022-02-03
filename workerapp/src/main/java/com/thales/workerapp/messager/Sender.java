package com.thales.workerapp.messager;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
@Component
public class Sender {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //@Autowired
    //private Queue sendQ;

    @Value("${queue.send.message}")
    private String sendQ;


    public void sendMessage(String msg){
        //rabbitTemplate.convertAndSend(this.sendQ.getName(), msg);
        rabbitTemplate.convertAndSend(sendQ, msg);
    }

}
