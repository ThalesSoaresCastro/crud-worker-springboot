package com.thales.serverapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.thales.serverapi.model.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{
    List<Event> findByClientId(Long clientId);

    @Transactional
    void deleteByClientId(long clientId);
}
