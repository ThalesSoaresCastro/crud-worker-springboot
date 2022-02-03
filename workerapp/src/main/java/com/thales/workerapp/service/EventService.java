package com.thales.workerapp.service;

import java.util.List;

import com.thales.workerapp.model.Event;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;

public interface EventService {
    
    Event save(String typeEvent, String clientId);
    
    List<Event> findAll();
    List<Event> findOrderByDate();
    List<Event> findOrderByDateAndClientId(String clientId);
}
