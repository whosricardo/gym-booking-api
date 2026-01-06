package com.whosricardo.gymbookingapi.service.auth;

import com.whosricardo.gymbookingapi.domain.enums.Role;
import com.whosricardo.gymbookingapi.dto.UserLoginRequest;
import com.whosricardo.gymbookingapi.dto.UserRegisterRequest;
import com.whosricardo.gymbookingapi.dto.UserResponse;
import com.whosricardo.gymbookingapi.entity.User;
import com.whosricardo.gymbookingapi.exception.BadRequestException;
import com.whosricardo.gymbookingapi.exception.NotFoundException;
import com.whosricardo.gymbookingapi.mapper.UserMapper;
import com.whosricardo.gymbookingapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse register(UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest.name() == null) throw new BadRequestException("name can't be null");
        if (userRegisterRequest.email() == null) throw new BadRequestException("email can't be null");
        if (userRegisterRequest.password() == null) throw new BadRequestException("password can't be null");

        String trimmedName = userRegisterRequest.name().trim();

        String trimmedEmail = userRegisterRequest.email().trim().toLowerCase();
        if (userRepository.existsByEmail(trimmedEmail)) throw new BadRequestException("email already exists");

        String encodedPassword = passwordEncoder.encode(userRegisterRequest.password());

        User newUser = new User();
        newUser.setName(trimmedName);
        newUser.setEmail(trimmedEmail);
        newUser.setPasswordHash(encodedPassword);
        newUser.setRole(Role.MEMBER);

        User savedUser = this.userRepository.save(newUser);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse login(UserLoginRequest userLoginRequest) {
        if (userLoginRequest.email() == null) throw new BadRequestException("email can't be null");
        if (userLoginRequest.password() == null) throw new BadRequestException("password can't be null");

        String normalizedEmail = userLoginRequest.email().trim().toLowerCase();

        User user = userRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new BadRequestException("invalid credentials"));

        if (!user.isActive()) throw new BadRequestException("invalid credentials");

        if (!passwordEncoder.matches(userLoginRequest.password(), user.getPasswordHash()))
            throw new BadRequestException("invalid credentials");

        return UserMapper.toResponse(user);
    }
}