package com.thales.workerapp.service;

import java.util.List;

import com.thales.workerapp.model.Event;

public interface EventService {
    
    Event save(String typeEvent, String clientId);
    
    List<Event> findAll();
    List<Event> findOrderByDate();
    List<Event> findOrderByDateAndClientId(String clientId);
    List<Event> findForDateInterval(String dateI, String dateF);
    List<Event> findForDate(String date);
}
