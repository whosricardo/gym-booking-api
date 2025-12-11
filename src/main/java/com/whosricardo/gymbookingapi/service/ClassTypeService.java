package com.whosricardo.gymbookingapi.service;

import com.whosricardo.gymbookingapi.entity.ClassType;
import com.whosricardo.gymbookingapi.exception.ClassTypeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ClassTypeService {
    ClassType saveClassType(ClassType classType);
    List<ClassType> fetchClassTypeList();
    Optional<ClassType> fetchClassTypeById(Long id);
    ClassType updateClassType(ClassType classType, Long id);
    void deleteClassTypeById(Long id);
}
