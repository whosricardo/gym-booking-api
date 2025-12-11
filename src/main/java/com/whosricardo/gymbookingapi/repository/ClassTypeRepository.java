package com.whosricardo.gymbookingapi.repository;

import com.whosricardo.gymbookingapi.entity.ClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassTypeRepository extends JpaRepository<ClassType, Long> {
}
