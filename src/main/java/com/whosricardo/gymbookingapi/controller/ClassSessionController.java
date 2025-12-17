package com.whosricardo.gymbookingapi.controller;

import com.whosricardo.gymbookingapi.dto.ClassSessionCreateRequest;
import com.whosricardo.gymbookingapi.entity.ClassSession;
import com.whosricardo.gymbookingapi.exception.BadRequestException;
import com.whosricardo.gymbookingapi.service.ClassSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class-sessions")
public class ClassSessionController {
    private final ClassSessionService classSessionService;

    public ClassSessionController(ClassSessionService classSessionService) {
        this.classSessionService = classSessionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassSession saveClassSession(@RequestBody ClassSessionCreateRequest classSessionCreateRequest) {
        if (classSessionCreateRequest.trainerId() == null) {
            throw new BadRequestException("class session need's a trainer id");
        }

        if (classSessionCreateRequest.classTypeId() == null) {
            throw new BadRequestException("class session need's a class type id");
        }

        if (classSessionCreateRequest.startTime() == null) {
            throw new BadRequestException("class session must have an start time");
        }

        return this.classSessionService.createClassSession(
                classSessionCreateRequest.trainerId(),
                classSessionCreateRequest.classTypeId(),
                classSessionCreateRequest.startTime(),
                classSessionCreateRequest.capacity()
        );
    }
}
