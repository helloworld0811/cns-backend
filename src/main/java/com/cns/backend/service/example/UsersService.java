package com.cns.backend.service.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cns.backend.model.example.User;
import com.cns.backend.repository.example.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repo;

    public List<User> getAllUsers() {
        return (List<User>) repo.findAll();
    }

    public User findUserById(long userId) {
        return repo.findOne(userId);
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public User updateUser(long userId, User updatedUser) {
        if(repo.exists(userId)) {
            updatedUser.setUserId(userId);
            return repo.save(updatedUser);
        }
        else {
            return null;
        }
    }

    public void deleteUser(long userId) {
        repo.delete(userId);
    }

}
