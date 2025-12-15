package com.whosricardo.gymbookingapi.service;

import com.whosricardo.gymbookingapi.entity.Trainer;

import java.util.List;

public interface TrainerService {
    Trainer saveTrainer(Trainer trainer);
    List<Trainer> fetchTrainerList();
    Trainer fetchTrainerById(Long id);
    Trainer updateTrainer(Trainer trainer, Long id);
    void deleteTrainerById(Long id);
}
