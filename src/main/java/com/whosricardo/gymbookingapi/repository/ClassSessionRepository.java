package com.whosricardo.gymbookingapi.repository;

import com.whosricardo.gymbookingapi.entity.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {
    List<ClassSession> findByActive(boolean active);
}
