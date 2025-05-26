package io.vladprotchenko.notificationservice.service;

import io.vladprotchenko.ensstartercore.security.service.AuthenticationFacade;
import io.vladprotchenko.notificationservice.model.dto.request.CreateNotificationRequest;
import io.vladprotchenko.notificationservice.model.dto.response.NotificationResponseDto;
import io.vladprotchenko.notificationservice.model.dto.response.SimpleResponseDto;
import io.vladprotchenko.notificationservice.model.entity.Notification;
import io.vladprotchenko.notificationservice.repository.NotificationRepository;
import io.vladprotchenko.notificationservice.service.mapper.NotificationMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    NotificationRepository notificationRepository;
    NotificationProducer notificationProducer;
    AuthenticationFacade authenticationFacade;
    NotificationMapper notificationMapper;

    @Transactional
    public SimpleResponseDto sendNotification(CreateNotificationRequest request) {
        log.info("Sending notification to kafka");
        Notification notification = notificationMapper.toEntity(request);
        UUID userId = authenticationFacade.getAccountIdFromAuthentication();
        notification.setSenderId(userId);

        // TODO: save to db
        notification = notificationRepository.save(notification);
        notificationProducer.produce(notificationMapper.toKafkaDto(notification));
        return SimpleResponseDto.builder().message("Notification passed to kafka").build();
    }

    public List<NotificationResponseDto> getAll() {
        List<Notification> notifications = notificationRepository.findAll();
        return notificationMapper.toDtoList(notifications);
    }

    public NotificationResponseDto getById(Long id) {
        Notification notification = notificationRepository.findById(id).orElse(null);
        return notificationMapper.toDto(notification);
    }
}
