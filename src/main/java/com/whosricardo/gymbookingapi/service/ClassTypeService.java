package com.whosricardo.gymbookingapi.service;

import com.whosricardo.gymbookingapi.entity.ClassType;

import java.util.List;

public interface ClassTypeService {
    ClassType saveClassType(ClassType classType);
    List<ClassType> fetchClassTypeList();
    ClassType fetchClassTypeById(Long id);
    ClassType updateClassType(ClassType classType, Long id);
    void deleteClassTypeById(Long id);
}
