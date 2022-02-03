package com.thales.workerapp.service;

import java.time.Instant;
import java.util.Date;
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
        return eventRepository.findByClientId(clientId, Sort.by(Sort.Direction.DESC, "eventRegister"));
    }

    @Override
    public List<Event> findForDateInterval(String dateI, String dateF) {
        return eventRepository.findEventForDateInterval(
                                    Date.from(Instant.parse(dateI)), 
                                    Date.from(Instant.parse(dateF)));
    }

    @Override
    public List<Event> findForDate(String date) {

        String dateIni = date+"T00:00:00.000Z";
        String dateFin = date+"T23:59:59.999Z";

        return eventRepository.findEventForDateInterval(Date.from(Instant.parse(dateIni)), 
                                                        Date.from(Instant.parse(dateFin)) );
    }

    
    
    
}
