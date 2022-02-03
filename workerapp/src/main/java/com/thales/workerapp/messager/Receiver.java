package com.thales.workerapp.messager;

import java.util.concurrent.TimeUnit;

import com.thales.workerapp.service.EventService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private Sender s;

    //------------------------------------------------------------------
    @Autowired
    private EventService eventService;

    private String typeEventRandom(int position){
        String[] tEvents = {
            "user consent for our newsletter","click in buy product","user click in sign out", 
            "click in ask a demo product","click on help", "user added product to cart"
        };
        return tEvents[position];
    }
    
    //Random event created
    public void SaveEvent(String idClient){
        //set random position for type event
        int maxV=5;
        int minV=0;
        int randomPosition= (int)Math.floor(Math.random()*(maxV-minV+1)+minV);
        String typeEvt = typeEventRandom(randomPosition);
        var actEvt = eventService.save(typeEvt, idClient);

        s.sendMessage("ID "+actEvt.getId()+" / "+
                    "CLIENT ID "+actEvt.getClientId()+" / "+
                    "EVENT TYPE "+actEvt.getTypeEvent()+" / "+
                    "EVENT DATETIME "+actEvt.getEventRegister());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

     }
    
    
    //--------------------------------------------------------------------------

    //Random qtd of events
    private int QtdOfEvents(int maxV, int minV){
        return (int)Math.floor(Math.random()*(maxV-minV+1)+minV);
    }

    // Extract ID user from message
    private String ExtractID(String msg){
        String [] tokens = msg.split("/");
        for(String t : tokens){
            String[] aux = t.split(" ");
            if( aux[0].contains("ID") ){
                return aux[1];
            }
        }
        return "";
    }

    @RabbitListener(queues = "${queue.receive.message}")
    public void receiveMessage(@Payload String fileString){
        
        System.out.println("MESSAGE RECEIVED: "+fileString);

        //create random events
        String idClt = ExtractID(fileString);
        int qtdEvt = QtdOfEvents(2, 4);
        
        if(!idClt.isBlank()){
            for(int pos= 0; pos <= qtdEvt; pos++){
                try{
                    SaveEvent(idClt);
                }catch(Exception e){
                    System.out.println("Exception: "+e);
                }
            }        
        }
    }

}
