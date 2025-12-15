package com.whosricardo.gymbookingapi.service;

import com.whosricardo.gymbookingapi.entity.Trainer;
import com.whosricardo.gymbookingapi.exception.TrainerNotFoundException;
import com.whosricardo.gymbookingapi.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Trainer saveTrainer(Trainer trainer) {
        return this.trainerRepository.save(trainer);
    }

    @Override
    public List<Trainer> fetchTrainerList() {
        return this.trainerRepository.findAll();
    }

    @Override
    public Trainer fetchTrainerById(Long id) {
        return this.trainerRepository.findById(id)
                .orElseThrow(() -> new TrainerNotFoundException("Trainer not found"));
    }

    @Override
    public Trainer updateTrainer(Trainer trainer, Long id) {
        Trainer updatedTrainer = fetchTrainerById(id);
        updatedTrainer.setEmail(trainer.getEmail());
        updatedTrainer.setBio(trainer.getBio());
        updatedTrainer.setPhone(trainer.getPhone());
        updatedTrainer.setSpecialty(trainer.getSpecialty());
        updatedTrainer.setActive(trainer.isActive());
        return this.trainerRepository.save(updatedTrainer);
    }

    @Override
    public void deleteTrainerById(Long id) {
        Trainer targetDeleteTrainer = fetchTrainerById(id);
        this.trainerRepository.delete(targetDeleteTrainer);
    }
}
