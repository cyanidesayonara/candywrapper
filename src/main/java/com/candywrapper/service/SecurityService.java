package com.candywrapper.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityService {
    String findLoggedInUsername();

    UserDetails autologin(String username, String password);
}