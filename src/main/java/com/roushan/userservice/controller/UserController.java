package com.roushan.userservice.controller;

import com.roushan.userservice.entities.User;
import com.roushan.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private int retryCount = 0;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saveduser = this.userService.saveUser(user);
        return new ResponseEntity<>(saveduser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
    @Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        retryCount++;
        log.info("Retry count: "+retryCount);
        User user = this.userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> ratingHotelFallBack(@PathVariable("userId") String userId, Exception exception) {
       log.info("Fallback is executed because service is down: "+exception.getMessage());
       User user = User.builder()
               .name("dummy")
               .email("dummy@gmail.com")
               .about("This user is created dummy because some service is down")
               .build();
       return new ResponseEntity<>(user, HttpStatus.FAILED_DEPENDENCY);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
