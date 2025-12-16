package com.whosricardo.gymbookingapi.dto;

import java.time.LocalDateTime;

public record ClassSessionUpdateRequest (LocalDateTime startTime, Integer capacity) {
}
