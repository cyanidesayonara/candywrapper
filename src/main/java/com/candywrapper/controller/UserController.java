package com.candywrapper.controller;

import com.candywrapper.model.User;
import com.candywrapper.service.UserService;
import com.candywrapper.service.SecurityService;
import com.candywrapper.validator.UserValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/users/")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        logger.info("Fetching user with id {}" , id);
        User user = userService.findById(id);
        if (user == null) {
            logger.error("User with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/users/register")
    public ResponseEntity<?> register(@RequestBody User user, BindingResult bindingResult) {
        logger.info("Registering User : {}", user);
        
        userValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.save(user);

        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        User createdUser = userService.save(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody User user) {
        logger.info("Updating User with id {}", id);

        User currentUser = userService.findById(id);
        if (currentUser == null) {
            logger.error("User with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());

        User updatedUser = userService.update(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        logger.info("Fetching and deleting User with id {}", id);

        if (userService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/")
    public ResponseEntity<?> deleteAll() {
        logger.info("Deleting all users");

        userService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}