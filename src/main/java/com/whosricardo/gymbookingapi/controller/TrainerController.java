package com.whosricardo.gymbookingapi.controller;

import com.whosricardo.gymbookingapi.entity.Trainer;
import com.whosricardo.gymbookingapi.exception.TrainerDifferentException;
import com.whosricardo.gymbookingapi.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Trainer getTrainer(@PathVariable Long id) {
        return this.trainerService.fetchTrainerById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Trainer> getTrainers() {
        return this.trainerService.fetchTrainerList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainer saveTrainer(@RequestBody Trainer trainer) {
        return this.trainerService.saveTrainer(trainer);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Trainer updateTrainer(@RequestBody Trainer trainer, @PathVariable Long id) {
        if (trainer.getId() != null && !trainer.getId().equals(id)) {
            throw new TrainerDifferentException("Trainer Id and Id from path with diff id");
        }
        return this.trainerService.updateTrainer(trainer, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainerById(@PathVariable Long id) {
        this.trainerService.deleteTrainerById(id);
    }
}