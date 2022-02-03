package com.thales.workerapp.Repository;

import java.util.List;

import com.thales.workerapp.model.Event;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface EventRepository extends MongoRepository<Event, String>{

    @Query("{ 'clientId' : ?0 }")
    List<Event> findByClientId(String clientId, Sort sort);
}
