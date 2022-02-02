package com.thales.workerapp.messager;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    
    @RabbitListener(queues = "${queue.receive.message}")
    public void receiveMessage(@Payload String fileString){
        System.out.println("MESSAGE: "+fileString);
    }

}
