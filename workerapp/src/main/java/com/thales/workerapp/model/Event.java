package com.thales.workerapp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document(collection="event")
@Getter
public class Event {
    @Id
    private String id;
    private String typeEvent;
    private Date eventRegister;

    private String clientId;

    public Event(String typeEvent, String clientId){
        this.typeEvent = typeEvent;
        this.clientId = clientId;
        
        this.eventRegister = new Date();
    }

}
