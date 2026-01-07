package com.whosricardo.gymbookingapi.controller.auth;

import com.whosricardo.gymbookingapi.dto.UserLoginRequest;
import com.whosricardo.gymbookingapi.dto.UserRegisterRequest;
import com.whosricardo.gymbookingapi.dto.UserResponse;
import com.whosricardo.gymbookingapi.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return this.authService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse login(@RequestBody UserLoginRequest userLoginRequest) {
        return this.authService.login(userLoginRequest);
    }
}