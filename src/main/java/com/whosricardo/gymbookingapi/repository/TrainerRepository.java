package com.whosricardo.gymbookingapi.repository;

import com.whosricardo.gymbookingapi.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
