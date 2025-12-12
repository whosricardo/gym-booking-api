package com.whosricardo.gymbookingapi.service;

import com.whosricardo.gymbookingapi.entity.ClassType;
import com.whosricardo.gymbookingapi.exception.ClassTypeNotFoundException;
import com.whosricardo.gymbookingapi.repository.ClassTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassTypeServiceImpl implements ClassTypeService {
    private final ClassTypeRepository classTypeRepository;

    public ClassTypeServiceImpl(ClassTypeRepository classTypeRepository) {
        this.classTypeRepository = classTypeRepository;
    }

    @Override
    public ClassType saveClassType(ClassType classType) {
        return this.classTypeRepository.save(classType);
    }

    @Override
    public List<ClassType> fetchClassTypeList() {
        return this.classTypeRepository.findAll();
    }

    @Override
    public ClassType fetchClassTypeById(Long id) {
        return this.classTypeRepository.findById(id)
                .orElseThrow(() -> new ClassTypeNotFoundException("ClassType not found"));
    }

    @Override
    public ClassType updateClassType(ClassType classType, Long id) {
        ClassType updatedClassType = fetchClassTypeById(id);
        updatedClassType.setMaxCapacity(classType.getMaxCapacity());
        updatedClassType.setDurationInMinutes(classType.getDurationInMinutes());
        return this.classTypeRepository.save(updatedClassType);
    }

    @Override
    public void deleteClassTypeById(Long id) {
        ClassType targetDeleteClassType = fetchClassTypeById(id);
        this.classTypeRepository.delete(targetDeleteClassType);
    }
}