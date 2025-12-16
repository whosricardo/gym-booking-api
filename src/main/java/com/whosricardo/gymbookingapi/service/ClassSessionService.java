package com.whosricardo.gymbookingapi.service;

import com.whosricardo.gymbookingapi.entity.ClassSession;

import java.time.LocalDateTime;
import java.util.List;

public interface ClassSessionService {
    ClassSession createClassSession(Long trainerId,
                                  Long classTypeId,
                                  LocalDateTime startTime,
                                  Integer capacity);
    ClassSession fetchClassSessionById(Long id);
    List<ClassSession> fetchClassSessionList();
    ClassSession updateClassSession(LocalDateTime startTime,
                                    Integer capacity,
                                    Long id);
    void deleteClassSessionById(Long id);
}