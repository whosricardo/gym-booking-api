package com.whosricardo.gymbookingapi.controller;

import com.whosricardo.gymbookingapi.entity.ClassType;
import com.whosricardo.gymbookingapi.exception.ClassTypeNotFoundException;
import com.whosricardo.gymbookingapi.service.ClassTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class-types")
public class ClassTypeController {
    private final ClassTypeService classTypeService;

    public ClassTypeController(ClassTypeService classTypeService) {
        this.classTypeService = classTypeService;
    }

    @GetMapping("/{id}")
    public ClassType getClassType(@PathVariable Long id) {
        return this.classTypeService
                .fetchClassTypeById(id)
                .orElseThrow(() -> new ClassTypeNotFoundException("ClassType Not Found"));
    }
}
