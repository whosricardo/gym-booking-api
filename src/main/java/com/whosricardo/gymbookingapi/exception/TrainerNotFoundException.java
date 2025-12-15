package com.whosricardo.gymbookingapi.exception;

public class TrainerNotFoundException extends RuntimeException{
    public TrainerNotFoundException(String message) {
        super(message);
    }
}
