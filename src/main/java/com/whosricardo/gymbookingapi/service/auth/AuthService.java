package com.whosricardo.gymbookingapi.service.auth;

import com.whosricardo.gymbookingapi.dto.UserRegisterRequest;
import com.whosricardo.gymbookingapi.dto.UserResponse;

public interface AuthService {
    UserResponse register(UserRegisterRequest userRegisterRequest);
}
