package com.whosricardo.gymbookingapi.dto;

public record UserRegisterRequest(String name,
                                  String email,
                                  String password) {
}
