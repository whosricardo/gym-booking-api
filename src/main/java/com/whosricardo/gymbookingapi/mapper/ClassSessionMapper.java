package com.whosricardo.gymbookingapi.mapper;

import com.whosricardo.gymbookingapi.dto.ClassSessionResponse;
import com.whosricardo.gymbookingapi.entity.ClassSession;

public class ClassSessionMapper {
    public static ClassSessionResponse toResponse(ClassSession classSession) {
        if (classSession == null) return null;

        return new ClassSessionResponse(
                classSession.getId(),
                classSession.getTrainer().getId(),
                classSession.getClassType().getId(),
                classSession.getStartTime(),
                classSession.getCapacity()
        );
    }
}