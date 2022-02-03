package com.thales.workerapp.controller;

import java.util.List;

import com.thales.workerapp.model.Event;
import com.thales.workerapp.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findAll());
    }

    @GetMapping("/allorderbydate")
    public ResponseEntity<List<Event>> getAllOrderByDate(){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findOrderByDate());
    }

    @GetMapping("/allorderbydate/{clientId}")
    public ResponseEntity<List<Event>> getAllOrderByDateByClientId(@PathVariable String clientId){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findOrderByDateAndClientId(clientId));
    }

}
