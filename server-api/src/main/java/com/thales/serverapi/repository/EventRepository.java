package com.thales.serverapi.repository;

import java.util.List;

import com.thales.serverapi.model.Client;
import com.thales.serverapi.model.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{
    List<Event> findByClientId(Client client);
}
