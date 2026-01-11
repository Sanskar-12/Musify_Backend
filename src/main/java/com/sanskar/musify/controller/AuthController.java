package com.sanskar.musify.controller;

import com.sanskar.musify.io.RegisterRequest;
import com.sanskar.musify.io.UserResponse;
import com.sanskar.musify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/api/auth/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        try {
            UserResponse response = userService.registerUser(request);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
