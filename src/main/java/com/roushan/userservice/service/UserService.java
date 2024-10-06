package com.roushan.userservice.service;


import org.springframework.stereotype.Service;
import com.roushan.userservice.entities.User;

import java.util.List;

@Service
public interface UserService {


    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);

    void deleteUserById(String userId);

    void updateUser(User user);
}
