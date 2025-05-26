package io.vladprotchenko.notificationservice.model.entity;

import io.vladprotchenko.notificationapi.model.enums.NotificationStatus;
import io.vladprotchenko.notificationapi.model.enums.NotificationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Notification setNotificationStatus(NotificationStatus status) {
        this.setStatus(status);
        return this;
    }

    public Notification incrementRetryAttempts() {
        this.retryAttempts++;
        return this;
    }

}
