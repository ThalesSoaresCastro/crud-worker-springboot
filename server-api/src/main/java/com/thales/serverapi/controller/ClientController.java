package com.thales.serverapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.thales.serverapi.model.Client;
import com.thales.serverapi.service.ClientService;

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
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<List<Client>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Optional<Client>> findById(@PathVariable Long id){
        if(id <= 0){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params id not exists");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST); 
        }
        
        Optional<Client> clt = clientService.findById(id);

        if(clt.isPresent() == false){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","client not exists in database");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clt);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Client client){
        if(client.getName() == null || client.getDocument() == null){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        if(client.getName().isBlank() || client.getDocument().isBlank()){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        if(clientService.findClient(client)){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","client exists in database");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        try {
            Client aux = clientService.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(aux);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/alterClient")
    public ResponseEntity<Object> update(@RequestBody Client client){
        
        if(client.getName() == null || client.getDocument() == null || client.getId() <= 0){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        if(client.getName().isBlank() || client.getDocument().isBlank()){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params not found");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        try {
            Client aux = clientService.update(client);
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        if(id <= 0){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","params id not exists");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST); 
        }
        
        Optional<Client> clt = clientService.findById(id);

        if(clt.isPresent() == false){
            HashMap<String, String> map = new HashMap<>();
            map.put("message","client not exists in database");
            return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        try {
            clientService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
