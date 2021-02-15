package com.aarshinkov.api.hotelly.services;

import com.aarshinkov.api.hotelly.entities.UserEntity;
import com.aarshinkov.api.hotelly.repositories.UsersRepository;
import com.aarshinkov.api.hotelly.requests.users.UserCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserEntity getUserByUserId(String userId) {
        return usersRepository.findByUserId(userId);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public List<UserEntity> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UserEntity createUser(UserCreateRequest ucr) {

        UserEntity user = new UserEntity();
        user.setUserId(UUID.randomUUID().toString());
        user.setEmail(ucr.getEmail());
        user.setPassword(ucr.getPassword());
        user.setFirstName(ucr.getFirstName());
        user.setLastName(ucr.getLastName());

        return usersRepository.save(user);
    }

    @Override
    public Boolean deleteUser(String userId) {
        UserEntity user = usersRepository.findByUserId(userId);

        if (user == null) {
            return false;
        }

        try {
            usersRepository.delete(user);

            return true;
        } catch (Exception e) {
            log.error("Error deleting user", e);
            return false;
        }
    }

    @Override
    public Boolean isUserExistByEmail(String email) {

        return usersRepository.findByEmail(email) != null;
    }
}
