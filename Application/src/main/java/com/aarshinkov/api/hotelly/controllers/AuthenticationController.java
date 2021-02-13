package com.aarshinkov.api.hotelly.controllers;

import com.aarshinkov.api.hotelly.entities.UserEntity;
import com.aarshinkov.api.hotelly.requests.LoginRequest;
import com.aarshinkov.api.hotelly.responses.LoginResponse;
import com.aarshinkov.api.hotelly.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        UserEntity user = userService.getUserByEmail(loginRequest.getEmail());

        LoginResponse response = new LoginResponse();
        
        if (user == null) {
            return response;
        }

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            return response;
        }

        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());

        return response;
    }
}
