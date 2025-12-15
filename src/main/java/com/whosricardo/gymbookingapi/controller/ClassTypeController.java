package com.whosricardo.gymbookingapi.controller;

import com.whosricardo.gymbookingapi.entity.ClassType;
import com.whosricardo.gymbookingapi.exception.BadRequestException;
import com.whosricardo.gymbookingapi.exception.ClassTypeDifferentException;
import com.whosricardo.gymbookingapi.service.ClassTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class-types")
public class ClassTypeController {
    private final ClassTypeService classTypeService;

    public ClassTypeController(ClassTypeService classTypeService) {
        this.classTypeService = classTypeService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassType getClassType(@PathVariable Long id) {
        return this.classTypeService.fetchClassTypeById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClassType> getClassTypes() {
        return this.classTypeService.fetchClassTypeList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassType saveClassType(@RequestBody ClassType classType) {
        if (classType.getId() != null) {
            throw new BadRequestException("ClassType can't have an ID assigned");
        }
        return this.classTypeService.saveClassType(classType);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassType updateClassType(@RequestBody ClassType classType,
                                     @PathVariable Long id) {
        if (classType.getId() != null && !classType.getId().equals(id)) {
            throw new ClassTypeDifferentException("ClassType ID and ID from path with diff id");
        }
        return this.classTypeService.updateClassType(classType, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassTypeById(@PathVariable Long id) {
        this.classTypeService.deleteClassTypeById(id);
    }
}
