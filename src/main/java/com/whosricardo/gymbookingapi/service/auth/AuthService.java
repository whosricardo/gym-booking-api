package com.whosricardo.gymbookingapi.service.auth;

import com.whosricardo.gymbookingapi.dto.UserLoginRequest;
import com.whosricardo.gymbookingapi.dto.UserRegisterRequest;
import com.whosricardo.gymbookingapi.dto.UserResponse;
import com.whosricardo.gymbookingapi.dto.auth.AuthResponse;

public interface AuthService {
    UserResponse register(UserRegisterRequest userRegisterRequest);
    AuthResponse login(UserLoginRequest userLoginRequest);
}
