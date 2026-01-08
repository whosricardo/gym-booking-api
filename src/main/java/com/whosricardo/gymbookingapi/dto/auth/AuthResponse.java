package com.whosricardo.gymbookingapi.dto.auth;

import com.whosricardo.gymbookingapi.dto.UserResponse;

public record AuthResponse(String token, UserResponse user) {
}
