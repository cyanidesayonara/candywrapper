package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.Role;

public interface RoleService {
    List<Role> findAll();
    Role findById(String id);
    Role findOneByName(String name);
    boolean existsByName(String name);
    Role save(Role role);
    Role update(Role role);
    void deleteById(String id);
    void deleteAll();
}