package com.whosricardo.gymbookingapi.controller;

import com.whosricardo.gymbookingapi.dto.ClassSessionCreateRequest;
import com.whosricardo.gymbookingapi.dto.ClassSessionResponse;
import com.whosricardo.gymbookingapi.dto.ClassSessionUpdateRequest;
import com.whosricardo.gymbookingapi.exception.BadRequestException;
import com.whosricardo.gymbookingapi.mapper.ClassSessionMapper;
import com.whosricardo.gymbookingapi.service.ClassSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class-sessions")
public class ClassSessionController {
    private final ClassSessionService classSessionService;

    public ClassSessionController(ClassSessionService classSessionService) {
        this.classSessionService = classSessionService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassSessionResponse getClassSession(@PathVariable Long id) {
        return ClassSessionMapper.toResponse(
                this.classSessionService.fetchClassSessionById(id)
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClassSessionResponse> getClassSessions() {
        return this.classSessionService.fetchClassSessionList()
                .stream()
                .map(ClassSessionMapper::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassSessionResponse saveClassSession(@RequestBody ClassSessionCreateRequest classSessionCreateRequest) {
        if (classSessionCreateRequest.trainerId() == null) {
            throw new BadRequestException("class session need's a trainer id");
        }

        if (classSessionCreateRequest.classTypeId() == null) {
            throw new BadRequestException("class session need's a class type id");
        }

        if (classSessionCreateRequest.startTime() == null) {
            throw new BadRequestException("class session must have an start time");
        }

        return ClassSessionMapper.toResponse(
                this.classSessionService.createClassSession(
                        classSessionCreateRequest.trainerId(),
                        classSessionCreateRequest.classTypeId(),
                        classSessionCreateRequest.startTime(),
                        classSessionCreateRequest.capacity()
                )
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassSessionResponse updateClassSession(@RequestBody ClassSessionUpdateRequest classSessionUpdateRequest,
                                                   @PathVariable Long id) {
        if (classSessionUpdateRequest.startTime() == null) {
            throw new BadRequestException("start time can't be null");
        }
        return ClassSessionMapper.toResponse(
                this.classSessionService.updateClassSession(
                        classSessionUpdateRequest.startTime(),
                        classSessionUpdateRequest.capacity(),
                        id
                )
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassSessionById(@PathVariable Long id) {
        this.classSessionService.deleteClassSessionById(id);
    }
}
