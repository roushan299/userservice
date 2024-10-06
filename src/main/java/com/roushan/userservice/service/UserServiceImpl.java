package com.roushan.userservice.service;

import com.roushan.userservice.communicator.RatingServiceCommunicator;
import com.roushan.userservice.entities.Rating;
import com.roushan.userservice.entities.User;
import com.roushan.userservice.exceptions.ResourceNotFoundExceptions;
import com.roushan.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

   @Autowired
   private RatingServiceCommunicator ratingServiceCommunicator;


    @Override
    public User saveUser(User user) {
        UUID uuid = UUID.randomUUID();
        String userId = uuid.toString();
        user.setUserId(userId);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = this.userRepository.findAll();
        for(User user : userList){
            List<Rating> ratings = this.ratingServiceCommunicator.getRatingsByUserId(user.getUserId());
            user.setRatings(ratings);
        }
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user =  this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundExceptions("user id not found in the server: "+userId));
        List<Rating> usersRating = this.ratingServiceCommunicator.getRatingsByUserId(userId);
        user.setRatings(usersRating);
        return user;
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
