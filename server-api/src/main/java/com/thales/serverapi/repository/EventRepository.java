package com.thales.serverapi.repository;

import com.thales.serverapi.model.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{
    
}
