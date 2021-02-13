package com.aarshinkov.api.hotelly.controllers;

import com.aarshinkov.api.hotelly.entities.UserEntity;
import com.aarshinkov.api.hotelly.repositories.UsersRepository;
import com.aarshinkov.api.hotelly.requests.users.UserCreateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@RestController
@Api(value = "Users", tags = "Users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @ApiOperation(value = "Get users")
    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserEntity> getUsers() {

        return usersRepository.findAll();
    }

    @ApiOperation(value = "Get particular user")
    @GetMapping(value = "/api/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity getUser(@PathVariable("userId") String userId) {

        return usersRepository.findByUserId(userId);
    }

    @ApiOperation(value = "Create user")
    @PostMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity createUser(@RequestBody UserCreateRequest ucr) {

        UserEntity user = new UserEntity();
        user.setEmail(ucr.getEmail());
        user.setPassword(ucr.getPassword());
        user.setFirstName(ucr.getFirstName());
        user.setLastName(ucr.getLastName());

        UserEntity createdUser = usersRepository.save(user);

        return createdUser;
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping(value = "/api/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteUser(@PathVariable("userId") String userId) {
        UserEntity user = usersRepository.findByUserId(userId);

        if (user == null) {
            return false;
        }

        try {
            usersRepository.delete(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
