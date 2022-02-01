package com.thales.serverapi.service;

import java.util.List;
import java.util.Optional;

import com.thales.serverapi.model.Event;

public interface EventService {
    Event save(Event event);
    List<Event> findAll();
    Optional<Event> findById(Long id);
    Event update(Event event);
    void deleteById(Long id);
    List<Event> findEventsByClient(Long clientId);
}
