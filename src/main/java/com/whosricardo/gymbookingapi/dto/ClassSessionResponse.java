package com.whosricardo.gymbookingapi.dto;

import java.time.LocalDateTime;

public record ClassSessionResponse (Long id,
                                    Long trainerId,
                                    Long classTypeId,
                                    LocalDateTime startTime,
                                    Integer capacity){
}
