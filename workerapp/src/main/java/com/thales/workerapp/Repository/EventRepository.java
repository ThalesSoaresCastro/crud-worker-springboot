package com.thales.workerapp.Repository;

import java.util.List;

import com.thales.workerapp.model.Event;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EventRepository extends MongoRepository<Event, String>{
    
    @Query("db.event.find({'clientId': clientId}).sort({'eventRegister':-1})")
    List<Event> findByDateEventRegister(Integer clientId);
}
