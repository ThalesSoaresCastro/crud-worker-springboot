package com.thales.serverapi.service;

import java.util.List;
import java.util.Optional;

import com.thales.serverapi.model.Client;

public interface ClientService {
    Client save(Client client);
    List<Client> findAll();
    Optional<Client> findById(Long id);
    Client update(Client client);
    void deleteById(Long id);
}
