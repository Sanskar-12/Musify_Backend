package com.sanskar.musify.service.impl;

import com.sanskar.musify.document.User;
import com.sanskar.musify.io.RegisterRequest;
import com.sanskar.musify.io.UserResponse;
import com.sanskar.musify.repository.UserRepository;
import com.sanskar.musify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        Boolean existingUser = userRepository.existsByEmail(request.getEmail());

        if (existingUser) {
            throw new RuntimeException("Email already exists");
        }

        User newUser = convertToEntity(request);

        userRepository.save(newUser);

        return UserResponse.builder()
                .email(newUser.getEmail())
                .role(UserResponse.Role.USER)
                .build();
    }

    public User convertToEntity(RegisterRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(User.Role.USER)
                .build();
    }
}
