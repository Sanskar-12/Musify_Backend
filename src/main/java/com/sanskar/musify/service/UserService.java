package com.sanskar.musify.service;

import com.sanskar.musify.io.RegisterRequest;
import com.sanskar.musify.io.UserResponse;

public interface UserService {


    public UserResponse registerUser(RegisterRequest request);
}
