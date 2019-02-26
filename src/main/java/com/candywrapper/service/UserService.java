package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.User;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    List<User> findByUsername(String username);
    User findOneByUsername(String username);
    boolean existsByUsername(String username);
    User save(User user);
    User update(User user);
    void deleteById(String id);
    void deleteAll();
}