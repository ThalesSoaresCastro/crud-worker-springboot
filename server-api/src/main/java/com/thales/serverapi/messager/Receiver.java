package com.thales.serverapi.messager;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues={"${queue.receive.message}"})
    public void receive(@Payload String fileBody){
        System.out.println("Message Receiver: "+fileBody);
    }
}
