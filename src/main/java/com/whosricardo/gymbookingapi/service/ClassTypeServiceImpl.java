package com.whosricardo.gymbookingapi.service;

import com.whosricardo.gymbookingapi.entity.ClassType;
import com.whosricardo.gymbookingapi.exception.ClassTypeNotFoundException;
import com.whosricardo.gymbookingapi.repository.ClassTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<ClassType> fetchClassTypeById(Long id) {
        return this.classTypeRepository.findById(id);
    }

    @Override
    public ClassType updateClassType(ClassType classType, Long id) {
        Optional<ClassType> optionalClassType = fetchClassTypeById(id);
        ClassType updatedClassType = optionalClassType
                .orElseThrow(() -> new ClassTypeNotFoundException("ClassType not found"));

        updatedClassType.setMaxCapacity(classType.getMaxCapacity());
        updatedClassType.setDurationInMinutes(classType.getDurationInMinutes());
        return this.classTypeRepository.save(updatedClassType);
    }

    @Override
    public void deleteClassTypeById(Long id) {
        Optional<ClassType> optionalClassType = fetchClassTypeById(id);
        ClassType classType = optionalClassType
                .orElseThrow(() -> new ClassTypeNotFoundException("ClassType not found"));

        this.classTypeRepository.delete(classType);
    }
}