package io.vladprotchenko.notificationapi.model.kafka;

import io.vladprotchenko.notificationapi.model.enums.NotificationStatus;
import io.vladprotchenko.notificationapi.model.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationKafka {

    private Long id;

    private UUID senderId;
    private Long recipientId;

    private NotificationType type;
    private String title;
    private String content;

    @Builder.Default
    private Integer retryAttempts = 0;

    @Builder.Default
    private NotificationStatus status = NotificationStatus.NEW;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();


}
