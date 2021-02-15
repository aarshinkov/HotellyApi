package com.aarshinkov.api.hotelly.services;

import com.aarshinkov.api.hotelly.entities.UserEntity;
import com.aarshinkov.api.hotelly.requests.users.UserCreateRequest;

import java.util.List;

public interface UserService {

    UserEntity getUserByUserId(String userId);

    UserEntity getUserByEmail(String email);

    List<UserEntity> getUsers();

    UserEntity createUser(UserCreateRequest ucr);

    Boolean deleteUser(String userId);

    Boolean isUserExistByEmail(String email);
}
