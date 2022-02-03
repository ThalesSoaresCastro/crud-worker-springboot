package com.thales.workerapp.service;

import java.util.List;

import com.thales.workerapp.Repository.EventRepository;
import com.thales.workerapp.model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event save(String typeEvent, String clientId) {
        return eventRepository.save(new Event(typeEvent, clientId));
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findOrderByDate() {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "eventRegister") );
    }

    @Override
    public List<Event> findOrderByDateAndClientId(String clientId) {
        // TODO Auto-generated method stub
        return eventRepository.findByClientId(clientId, Sort.by(Sort.Direction.DESC, "eventRegister"));
    }
    
    
}
