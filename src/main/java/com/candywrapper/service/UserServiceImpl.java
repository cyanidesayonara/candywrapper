package com.candywrapper.service;

import java.util.List;
import java.util.Optional;

import com.candywrapper.model.User;
import com.candywrapper.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean findUserByUsernameIgnoreCase(String username) {
        return !userRepository.findUserByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        }
    }

    @Override
    public void deleteAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            userRepository.delete(user);
        }
    }    
}