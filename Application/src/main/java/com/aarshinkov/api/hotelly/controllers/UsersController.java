package com.aarshinkov.api.hotelly.controllers;

import com.aarshinkov.api.hotelly.entities.UserEntity;
import com.aarshinkov.api.hotelly.repositories.UsersRepository;
import com.aarshinkov.api.hotelly.requests.users.UserCreateRequest;
import com.aarshinkov.api.hotelly.responses.users.UserCreatedResponse;
import com.aarshinkov.api.hotelly.responses.users.UserGetResponse;
import com.aarshinkov.api.hotelly.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@RestController
@Api(value = "Users", tags = "Users")
public class UsersController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get users")
    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserGetResponse>> getUsers() {

        List<UserEntity> users = userService.getUsers();

        List<UserGetResponse> response = new ArrayList<>();

        for (UserEntity user : users) {
            response.add(getUserResponseFromEntity(user));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Get particular user")
    @GetMapping(value = "/api/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserGetResponse> getUser(@PathVariable("userId") String userId) {

        UserEntity user = userService.getUserByUserId(userId);

        return new ResponseEntity<>(getUserResponseFromEntity(user), HttpStatus.OK);
    }

    @ApiOperation(value = "Create user")
    @PostMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreatedResponse> createUser(@RequestBody UserCreateRequest ucr) {

        UserEntity createdUser = userService.createUser(ucr);

        UserCreatedResponse response = new UserCreatedResponse();
        response.setUserId(createdUser.getUserId());
        response.setEmail(createdUser.getEmail());
        response.setFirstName(createdUser.getFirstName());
        response.setLastName(createdUser.getLastName());
        response.setCreatedOn(createdUser.getCreatedOn());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping(value = "/api/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable("userId") String userId) {

        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    private UserGetResponse getUserResponseFromEntity(UserEntity user) {

        UserGetResponse ugr = new UserGetResponse();
        ugr.setUserId(user.getUserId());
        ugr.setEmail(user.getEmail());
        ugr.setFirstName(user.getFirstName());
        ugr.setLastName(user.getLastName());
        ugr.setCreatedOn(user.getCreatedOn());
        ugr.setEditedOn(user.getEditedOn());
        return ugr;
    }
}
