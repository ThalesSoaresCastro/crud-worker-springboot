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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/events")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(eventService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/allorderbydate")
    public ResponseEntity<List<Event>> getAllOrderByDate(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(eventService.findOrderByDate());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }

    @GetMapping("/allorderbydate/{clientId}")
    public ResponseEntity<List<Event>> getAllOrderByDateByClientId(@PathVariable String clientId){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findOrderByDateAndClientId(clientId));
    }

    @GetMapping("/allbydateinterval")
    public ResponseEntity<List<Event>> getAllOrderByDateInterval(@RequestParam("initial") String dateI, 
                                                                    @RequestParam("final") String dateF){
        String dateIni = dateI+"T00:00:00.000Z";
        String dateFin = dateF+"T23:59:59.999Z";
        try {
            return ResponseEntity.status(HttpStatus.OK).body(eventService.findForDateInterval(dateIni,dateFin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/allbydate/{date}")
    public ResponseEntity<List<Event>> getAllOrderByDateUni(@PathVariable String date){
        //return ResponseEntity.status(HttpStatus.OK).body(eventService.findOrderByDateAndClientId(clientId));
        
        try {
            return ResponseEntity.status(HttpStatus.OK).body(eventService.findForDate(date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
