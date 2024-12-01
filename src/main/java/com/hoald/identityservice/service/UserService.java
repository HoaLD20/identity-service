package com.hoald.identityservice.service;

import com.hoald.identityservice.dto.request.UserCreationRequest;
import com.hoald.identityservice.dto.request.UserUpdateRequest;
import com.hoald.identityservice.entity.User;
import com.hoald.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUserById(String userId, UserUpdateRequest userUpdateRequest) {
        if (Objects.nonNull(getUserById(userId))) {
            User user = getUserById(userId);
            user.setPassword(userUpdateRequest.getPassword());
            user.setFirstName(userUpdateRequest.getFirstName());
            user.setLastName(userUpdateRequest.getLastName());
            user.setDob(userUpdateRequest.getDob());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
