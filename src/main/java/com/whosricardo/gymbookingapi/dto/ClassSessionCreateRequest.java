package com.whosricardo.gymbookingapi.dto;

import java.time.LocalDateTime;

public record ClassSessionCreateRequest(Long trainerId,
                                        Long classTypeId,
                                        LocalDateTime startTime,
                                        Integer capacity) {
}
