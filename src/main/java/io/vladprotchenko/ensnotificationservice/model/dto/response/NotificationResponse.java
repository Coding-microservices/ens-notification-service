package io.vladprotchenko.ensnotificationservice.model.dto.response;

import io.vladprotchenko.ensnotificationservice.model.entity.enums.NotificationStatus;
import io.vladprotchenko.ensnotificationservice.model.entity.enums.NotificationType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NotificationResponse(
        Long id,
        NotificationType type,
        String credential,
        String content,
        NotificationStatus status,
        Integer retryAttempts,
        LocalDateTime createdAt,
        Long userId
) {
}
