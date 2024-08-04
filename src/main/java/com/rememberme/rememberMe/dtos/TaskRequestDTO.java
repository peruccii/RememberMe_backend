package com.rememberme.rememberMe.dtos;

import java.time.LocalDateTime;

public record TaskRequestDTO(
        String name,
        String description,
        Float coast,
        String alert_at
) {
}
