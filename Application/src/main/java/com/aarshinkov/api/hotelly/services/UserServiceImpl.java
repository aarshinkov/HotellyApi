package com.aarshinkov.api.hotelly.services;

import com.aarshinkov.api.hotelly.entities.UserEntity;
import com.aarshinkov.api.hotelly.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserEntity getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
