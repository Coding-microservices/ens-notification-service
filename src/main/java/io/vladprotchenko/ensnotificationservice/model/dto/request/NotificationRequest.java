package io.vladprotchenko.ensnotificationservice.model.dto.request;

import io.vladprotchenko.ensnotificationservice.model.entity.enums.NotificationType;
import lombok.Builder;

@Builder
public record NotificationRequest(
        NotificationType type,
        String credential,
        String title,
        String content,
        Long recipientId,
        Long userId
) {
}
