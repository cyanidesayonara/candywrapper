package com.candywrapper.controller;

import com.candywrapper.model.User;
import com.candywrapper.service.UserService;
import com.candywrapper.service.SecurityService;
import com.candywrapper.validator.LoginValidator;
import com.candywrapper.validator.RegisterValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RestController
@RequestMapping("api/accounts/")
public class AccountAPIController {

    public static final Logger logger = LoggerFactory.getLogger(UserAPIController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private RegisterValidator signupValidator;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid User user, BindingResult bindingResult) {
        logger.info("Logging in User : {}", user);
        
        loginValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        UserDetails userDetails = securityService.autologin(user.getUsername(), user.getPassword());
        return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid User user, BindingResult bindingResult) {
        logger.info("Signing up User : {}", user);
        
        signupValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User createdUser = userService.save(user);
        UserDetails userDetails = securityService.autologin(createdUser.getUsername(), createdUser.getPasswordConfirm());
        return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
    }
}