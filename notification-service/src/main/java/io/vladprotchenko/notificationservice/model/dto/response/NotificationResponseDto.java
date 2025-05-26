package io.vladprotchenko.notificationservice.model.dto.response;

import io.vladprotchenko.notificationapi.model.enums.NotificationStatus;
import io.vladprotchenko.notificationapi.model.enums.NotificationType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class NotificationResponseDto {

    private Long id;

    private UUID senderId;
    private Long recipientId;

    private NotificationType type;
    private String title;
    private String content;

    private Integer retryAttempts;

    private NotificationStatus status;

    private LocalDateTime createdAt;
}
