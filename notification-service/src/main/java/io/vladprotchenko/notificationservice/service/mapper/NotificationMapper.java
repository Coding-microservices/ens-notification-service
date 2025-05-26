package io.vladprotchenko.notificationservice.service.mapper;

import io.vladprotchenko.notificationapi.model.kafka.NotificationKafka;
import io.vladprotchenko.notificationservice.model.dto.request.CreateNotificationRequest;
import io.vladprotchenko.notificationservice.model.dto.response.NotificationResponseDto;
import io.vladprotchenko.notificationservice.model.entity.Notification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMapper {

    public Notification toEntity(CreateNotificationRequest dto) {
        return Notification.builder()
                .type(dto.getType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public NotificationKafka toKafkaDto(Notification notification) {
        return NotificationKafka.builder()
                .id(notification.getId())
                .type(notification.getType())
                .senderId(notification.getSenderId())
                .recipientId(notification.getRecipientId())
                .title(notification.getTitle())
                .content(notification.getContent())
                .build();
    }

    public NotificationResponseDto toDto(Notification notification) {
        return NotificationResponseDto.builder()
                .id(notification.getId())
                .senderId(notification.getSenderId())
                .recipientId(notification.getRecipientId())
                .type(notification.getType())
                .title(notification.getTitle())
                .content(notification.getContent())
                .retryAttempts(notification.getRetryAttempts())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    public List<NotificationResponseDto> toDtoList(List<Notification> notifications) {
        List<NotificationResponseDto> dtos = new ArrayList<>();
        for (Notification notification : notifications) {
            dtos.add(toDto(notification));
        }
        return dtos;
    }
}
