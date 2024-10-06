package com.roushan.userservice.service;

import com.roushan.userservice.entities.User;
import com.roushan.userservice.exceptions.ResourceNotFoundExceptions;
import com.roushan.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User saveUser(User user) {
        UUID uuid = UUID.randomUUID();
        String userId = uuid.toString();
        user.setUserId(userId);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExceptions("user id not found in the server: "+userId));
    }

    @Override
    public void deleteUserById(String userId) {
        User user = this.getUserById(userId);
        this.userRepository.delete(user);
    }

    @Override
    public void updateUser(User user) {
        //TODO
    }
}
