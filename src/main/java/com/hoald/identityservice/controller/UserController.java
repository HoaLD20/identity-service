package com.hoald.identityservice.controller;


import com.hoald.identityservice.dto.request.UserCreationRequest;
import com.hoald.identityservice.dto.request.UserUpdateRequest;
import com.hoald.identityservice.entity.User;
import com.hoald.identityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreationRequest request){
        return userService.createUser(request);
    }

    @GetMapping
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    User updateUserById(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId){
        return userService.updateUserById(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId){

        if (Objects.nonNull(getUserById(userId))){
            userService.deleteUser(userId);
            return "User deleted";
        }
        return "User not found";
    }

}
