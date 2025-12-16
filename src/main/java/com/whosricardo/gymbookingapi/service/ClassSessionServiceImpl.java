package com.whosricardo.gymbookingapi.service;

import com.whosricardo.gymbookingapi.entity.ClassSession;
import com.whosricardo.gymbookingapi.entity.ClassType;
import com.whosricardo.gymbookingapi.entity.Trainer;
import com.whosricardo.gymbookingapi.exception.BadRequestException;
import com.whosricardo.gymbookingapi.exception.NotFoundException;
import com.whosricardo.gymbookingapi.repository.ClassSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassSessionServiceImpl implements ClassSessionService {
    private final ClassSessionRepository classSessionRepository;
    private final ClassTypeService classTypeService;
    private final TrainerService trainerService;

    public ClassSessionServiceImpl(ClassSessionRepository classSessionRepository,
                                   ClassTypeService classTypeService,
                                   TrainerService trainerService) {
        this.classSessionRepository = classSessionRepository;
        this.classTypeService = classTypeService;
        this.trainerService = trainerService;
    }

    @Override
    public ClassSession createClassSession(Long trainerId,
                                           Long classTypeId,
                                           LocalDateTime startTime,
                                           Integer capacity) {
        Trainer trainer = this.trainerService.fetchTrainerById(trainerId);

        ClassType classType = this.classTypeService.fetchClassTypeById(classTypeId);

        if (startTime.isBefore(LocalDateTime.now())) throw new BadRequestException("startTime needs to be today or forward");

        if (capacity == null && classType.getMaxCapacity() == null) throw new BadRequestException("capacity required" +
                " because class type has no maxCapacity");

        // if capacity is null, use the maxCapacity from class type
        if (capacity == null) capacity = classType.getMaxCapacity();

        if (classType.getMaxCapacity() != null
                && capacity > classType.getMaxCapacity()) throw new BadRequestException("capacity can't be bigger than" +
                " maxCapacity");

        if (capacity <= 0) throw new BadRequestException("capacity must be greater than 0");

        // creating class session
        ClassSession classSession = new ClassSession();

        classSession.setTrainer(trainer);
        classSession.setClassType(classType);
        classSession.setCapacity(capacity);
        classSession.setStartTime(startTime);
        classSession.setActive(true);

        return this.classSessionRepository.save(classSession);
    }

    @Override
    public ClassSession fetchClassSessionById(Long id) {
        return this.classSessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("class session not found with this id provided"));
    }

    @Override
    public List<ClassSession> fetchClassSessionList() {
        return this.classSessionRepository.findByActive(true);
    }

    @Override
    public ClassSession updateClassSession(LocalDateTime startTime, Integer capacity, Long id) {
        ClassSession classSession = fetchClassSessionById(id);

        if (startTime.isBefore(LocalDateTime.now())) throw new BadRequestException("start time needs to be today or forward");

        if (capacity == null) capacity = classSession.getCapacity();
        // edge case (capacity that's stored it's null)
        if (capacity == null) throw new BadRequestException("capacity is not defined for this session");
        if (capacity <= 0) throw new BadRequestException("capacity needs to be greater than 0");
        if (classSession.getClassType().getMaxCapacity() != null && capacity > classSession.getClassType().getMaxCapacity()) {
            throw new BadRequestException("capacity can't be " + " greater than maxCapacity");
        }

        classSession.setCapacity(capacity);
        classSession.setStartTime(startTime);

        return this.classSessionRepository.save(classSession);
    }

    @Override
    public void deleteClassSessionById(Long id) {
        ClassSession classSession = fetchClassSessionById(id);
        if (!classSession.isActive()) throw new BadRequestException("class session already inactive");

        // soft delete
        classSession.setActive(false);
        this.classSessionRepository.save(classSession);
    }
}
