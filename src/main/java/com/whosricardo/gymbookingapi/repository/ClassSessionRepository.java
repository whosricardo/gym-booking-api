package com.whosricardo.gymbookingapi.repository;

import com.whosricardo.gymbookingapi.entity.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Long> {
    @Query("select cs from ClassSession cs join fetch cs.trainer" +
            " join fetch cs.classType where cs.id = :id and cs.active = true")
    Optional<ClassSession> findActiveByIdWithTrainerAndClassType(@Param("id") Long id);

    @Query("select cs from ClassSession cs join fetch cs.trainer " +
            "join fetch cs.classType where cs.active = true order by cs.startTime asc")
    List<ClassSession> findAllActiveWithTrainerAndClassType();
}
