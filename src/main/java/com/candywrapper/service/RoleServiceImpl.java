package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.Role;
import com.candywrapper.repository.RoleRepository;
import com.candywrapper.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role findOneByName(String name) {
        return roleRepository.findOneByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public Role save(Role role) {
        role.setUsers(new HashSet<>(userRepository.findAll()));        
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteById(String id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }    
}