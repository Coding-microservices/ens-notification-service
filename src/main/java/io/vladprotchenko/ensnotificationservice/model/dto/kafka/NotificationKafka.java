package io.vladprotchenko.ensnotificationservice.model.dto.kafka;

import io.vladprotchenko.ensnotificationservice.model.entity.enums.NotificationStatus;
import io.vladprotchenko.ensnotificationservice.model.entity.enums.NotificationType;

public record NotificationKafka(
        Long id,
        NotificationType type,
        String credential,
        String content,
        NotificationStatus status,
        Integer retryAttempts,
        Long userId
) {
}
