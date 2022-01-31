package com.thales.serverapi.repository;
import java.util.Optional;

import com.thales.serverapi.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{
   Client findByNameAndDocument(String name, String document);
}
