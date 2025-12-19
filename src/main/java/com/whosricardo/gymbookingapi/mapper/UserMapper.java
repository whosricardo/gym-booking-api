package com.whosricardo.gymbookingapi.mapper;

import com.whosricardo.gymbookingapi.dto.UserResponse;
import com.whosricardo.gymbookingapi.entity.User;

public class UserMapper {
    public static UserResponse toResponse (User user) {
        if (user == null) return null;

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt()
        );
    }
}
