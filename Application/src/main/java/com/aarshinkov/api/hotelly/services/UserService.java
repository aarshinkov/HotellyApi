package com.aarshinkov.api.hotelly.services;

import com.aarshinkov.api.hotelly.entities.UserEntity;

public interface UserService {

    UserEntity getUserByEmail(String email);
}
