package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.User;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    boolean findUserByNameIgnoreCase(User user);
    User save(User user);
    User update(User user);
    void delete(String id);
    void deleteAllUsers();
}