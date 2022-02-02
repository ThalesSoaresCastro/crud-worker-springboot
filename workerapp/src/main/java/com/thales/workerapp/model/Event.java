package com.thales.workerapp.model;

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

    private Long clientId;

}
