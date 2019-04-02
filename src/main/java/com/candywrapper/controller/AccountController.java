package com.candywrapper.controller;

import javax.validation.Valid;

import com.candywrapper.model.User;
import com.candywrapper.service.SecurityService;
import com.candywrapper.service.UserService;
import com.candywrapper.validator.LoginValidator;
import com.candywrapper.validator.RegisterValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/accounts")
public class AccountController {

    public static final Logger logger = LoggerFactory.getLogger(UserAPIController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private RegisterValidator registerValidator;

    
    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("view", "login");
        return "base";
    }
    
    @GetMapping("/signup")
    public String getSignup(Model model) {
        model.addAttribute("view", "signup");
        return "base";
    }
    
    @PostMapping("/login")
    public String postLogin(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        logger.info("Logging in User : {}", user);
        
        loginValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            // error message
            model.addAttribute("view", "login");
            return "base";
        }
        securityService.autologin(user.getUsername(), user.getPassword());
        return "redirect:/products/";
    }
    
    @PostMapping("/signup")
    public String postSignup(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        logger.info("Registering User : {}", user);
        
        registerValidator.validate(user, bindingResult);
        
        if (bindingResult.hasErrors()) {
            // error message
            model.addAttribute("view", "signup");
            return "base";
        }
        
        User createdUser = userService.save(user);
        securityService.autologin(createdUser.getUsername(), createdUser.getPasswordConfirm());
        return "redirect:/products/";
    }
}