package com.aarshinkov.api.hotelly.controllers;

import com.aarshinkov.api.hotelly.entities.UserEntity;
import com.aarshinkov.api.hotelly.exceptions.HollException;
import com.aarshinkov.api.hotelly.requests.LoginRequest;
import com.aarshinkov.api.hotelly.responses.LoginResponse;
import com.aarshinkov.api.hotelly.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@RestController
@Api(value = "Authentication", tags = "Authentication")
public class AuthenticationController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Login")
    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        UserEntity user = userService.getUserByEmail(loginRequest.getEmail());

        LoginResponse response = new LoginResponse();

        if (user == null) {
            throw new HollException(400, "Bad credentials", "Invalid email or password", HttpStatus.BAD_REQUEST);
        }

        if (!loginRequest.getPassword().equals(user.getPassword())) {
            throw new HollException(400, "Bad credentials", "Invalid email or password", HttpStatus.BAD_REQUEST);
        }

        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());

        return response;
    }
}
