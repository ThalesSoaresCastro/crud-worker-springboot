package com.thales.serverapi.service;

import java.util.List;
import java.util.Optional;

import com.thales.serverapi.model.Client;
import com.thales.serverapi.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client update(Client client) {
        return clientRepository.save(client);                            
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);  
    }

    @Override
    public boolean findClient(Client client){
        Client aux = clientRepository.findByNameAndDocument(client.getName(), client.getDocument());

        if(aux == null){
            return false;
        }else{
            return true;
        }

    }

}
