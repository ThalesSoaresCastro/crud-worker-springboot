package com.thales.serverapi.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.thales.serverapi.model.Client;
import com.thales.serverapi.model.Event;
import com.thales.serverapi.service.ClientService;
import com.thales.serverapi.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/clients")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/{clientid}/allevents")
    public ResponseEntity<List<Event>> getAllEventsByClientId(@PathVariable Long clientid){
        if(!clientService.findById(clientid).isPresent()){ 
            HashMap<String, String> map = new HashMap<>();
            map.put("message","client not exists");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST); 
        }
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findEventsByClient(clientid));
    }


    @GetMapping("/{clientid}/event/{idevent}")
    public ResponseEntity<Event> getOneEvent(@PathVariable Long clientid, @PathVariable Long idevent){
        Optional<Client> clt = clientService.findById(clientid);

        Optional<Event> auxEvt = eventService.findById(idevent);
        
        //validate client
        if(!clt.isPresent() || !auxEvt.isPresent()){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","client or event not exists");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST); 
        }

        return ResponseEntity.status(HttpStatus.OK).body(auxEvt.get());
    }

    @PostMapping("/{clientid}/eventcreate")
    public ResponseEntity<Event> createEvent(@PathVariable Long clientid,@RequestBody Event event){

        Optional<Client> clt = clientService.findById(clientid);
        
        //validate client
        if(!clt.isPresent()){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","client not exists");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST); 
        }

        if(event.getType() == null){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        if(event.getType().isBlank()){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        try {
            //setando o client
            event.setClient(clt.get());

            //setando a data atual
            Timestamp actualDate = new Timestamp(System.currentTimeMillis());
            event.setRegistration(actualDate);

            Event evt = eventService.save(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(evt);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{clientid}/eventupdate/{idevent}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long clientid, 
                                    @PathVariable Long idevent, @RequestBody Event event){

        Optional<Client> clt = clientService.findById(clientid);

        Optional<Event> auxEvt = eventService.findById(idevent);
        
        //validate client
        if(!clt.isPresent() || !auxEvt.isPresent()){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","client or event not exists");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST); 
        }

        if(event.getType() == null){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        if(event.getType().isBlank()){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        try {
            //setando a data atual
            Timestamp actualDate = new Timestamp(System.currentTimeMillis());
            auxEvt.get().setRegistration(actualDate);

            //set type
            auxEvt.get().setType(event.getType());
            

            Event evt = eventService.save(auxEvt.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(evt);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{clientid}/eventdelete/{idevent}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long clientid, @PathVariable Long idevent){
        Optional<Client> clt = clientService.findById(clientid);

        Optional<Event> auxEvt = eventService.findById(idevent);
        
        //validate client
        if(!clt.isPresent() || !auxEvt.isPresent()){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","client or event not exists");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST); 
        }

        try{
            eventService.deleteById(idevent);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

}
