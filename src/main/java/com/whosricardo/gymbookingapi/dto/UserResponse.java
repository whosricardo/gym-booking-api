package com.whosricardo.gymbookingapi.dto;

import com.whosricardo.gymbookingapi.domain.enums.Role;

import java.time.LocalDateTime;

public record UserResponse(Long id,
                           String name,
                           String email,
                           Role role,
                           boolean active,
                           LocalDateTime createdAt) {
}
