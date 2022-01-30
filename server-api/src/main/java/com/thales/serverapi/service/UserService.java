package com.thales.serverapi.service;

import java.util.List;
import java.util.Optional;

import com.thales.serverapi.model.User;

public interface UserService {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    User update(User user);
    void deleteById(Long id);
}
