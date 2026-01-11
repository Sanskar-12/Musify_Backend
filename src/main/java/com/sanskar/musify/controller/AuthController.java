package com.sanskar.musify.controller;

import com.sanskar.musify.document.User;
import com.sanskar.musify.io.AuthRequest;
import com.sanskar.musify.io.AuthResponse;
import com.sanskar.musify.io.RegisterRequest;
import com.sanskar.musify.io.UserResponse;
import com.sanskar.musify.service.UserService;
import com.sanskar.musify.service.impl.AppUserDetailsServiceImpl;
import com.sanskar.musify.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthController {

    @Autowired
    private AppUserDetailsServiceImpl appUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

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

    @PostMapping("/api/auth/login")
    public ResponseEntity login(@RequestBody AuthRequest request) throws Exception {
        try {
            // authenticate the user credentials
            authenticate(request.getEmail(), request.getPassword());

            // Getting the user details with the help of AppUserDetailsService
            final UserDetails userDetails = appUserDetailsService.loadUserByUsername(request.getEmail());
            User user = userService.findByEmail(request.getEmail());

            String token = jwtUtil.generateToken(userDetails, user.getRole().name());

            return ResponseEntity.ok(new AuthResponse(token, request.getEmail(), user.getRole().name()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled");
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email or password is incorrect");
        }
    }
}
