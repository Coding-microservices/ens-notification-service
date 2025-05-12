package io.vladprotchenko.ensnotificationservice.service;

import io.vladprotchenko.ensnotificationservice.client.TemplateClient;
import io.vladprotchenko.ensnotificationservice.exception.NotificationNotFoundException;
import io.vladprotchenko.ensnotificationservice.exception.TemplateRecipientsNotFoundException;
import io.vladprotchenko.ensnotificationservice.model.dto.kafka.NotificationKafka;
import io.vladprotchenko.ensnotificationservice.model.dto.kafka.RecipientListKafka;
import io.vladprotchenko.ensnotificationservice.model.dto.request.NotificationRequest;
import io.vladprotchenko.ensnotificationservice.model.dto.response.NotificationResponse;
import io.vladprotchenko.ensnotificationservice.model.dto.response.RecipientResponse;
import io.vladprotchenko.ensnotificationservice.model.dto.response.TemplateResponse;
import io.vladprotchenko.ensnotificationservice.model.entity.Notification;
import io.vladprotchenko.ensnotificationservice.model.entity.enums.NotificationStatus;
import io.vladprotchenko.ensnotificationservice.model.mapper.NotificationMapper;
import io.vladprotchenko.ensnotificationservice.repository.NotificationRepository;
import io.vladprotchenko.ensnotificationservice.utils.CollectionUtils;
import io.vladprotchenko.ensnotificationservice.utils.NodeChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.SECONDS;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final KafkaTemplate<String, RecipientListKafka> kafkaTemplate;
    private final TemplateClient templateClient;
    private final NodeChecker nodeChecker;
    private final NotificationRepository notificationRepository;
    private final NotificationMapper mapper;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.kafka.topics.splitter}")
    private String recipientListDistributionTopic;

    public String distributeNotifications(Long userId, Long templateId) {
        TemplateResponse templateResponse = templateClient.getTemplateByUserIdAndTemplateId(userId, templateId)
                .getBody();

        if (templateResponse == null) {
            return "TODO";
        }

        List<Long> recipientIds = templateResponse.recipientIds()
                .stream()
                .map(RecipientResponse::id)
                .toList();
        if (recipientIds.isEmpty()) {
            throw new TemplateRecipientsNotFoundException(
                    String.format("Template %s does not contain any recipients for userId %s", templateId, userId)
            );
        }

        System.out.println(nodeChecker.getAmountOfRunningNodes(applicationName));
        for (List<Long> recipients : CollectionUtils.splitList(recipientIds, nodeChecker.getAmountOfRunningNodes(applicationName))) {
            RecipientListKafka listKafka = new RecipientListKafka(recipients, templateResponse, userId);
            kafkaTemplate.send(recipientListDistributionTopic, listKafka);
        }

        return "Notification's been successfully sent!";
    }

    public NotificationResponse createNotification(NotificationRequest request) {
        return Optional.of(request)
                .map(mapper::toEntity)
                .map(notificationRepository::saveAndFlush)
                .map(mapper::toDto)
                .orElseThrow();
    }

    public List<NotificationKafka> getNotificationsForRebalancing(Long pendingSec, Long newSec, Integer size) {
        LocalDateTime now = LocalDateTime.now();
        return notificationRepository.findNotificationsByStatusAndCreatedAt(
                        now.minus(pendingSec, SECONDS), now.minus(newSec, SECONDS), Pageable.ofSize(size)
                ).stream()
                .map(notification -> notification.setNotificationStatus(NotificationStatus.PENDING))
                .map(Notification::updateCreatedAt)
                .map(notificationRepository::saveAndFlush)
                .map(mapper::mapToKafka)
                .toList();
    }

    public NotificationResponse setNotificationAsSent(Long userId, Long notificationId) {
        return setNotificationAsExecutedWithGivenStatus(userId, notificationId, NotificationStatus.SENT);
    }

    public NotificationResponse setNotificationAsProcessing(Long userId, Long notificationId) {
        return setNotificationAsExecutedWithGivenStatus(userId, notificationId, NotificationStatus.IN_PROCESS);
    }

    public NotificationResponse setNotificationAsError(Long userId, Long notificationId) {
        return setNotificationAsExecutedWithGivenStatus(userId, notificationId, NotificationStatus.ERROR);
    }

    public NotificationResponse setNotificationAsCorrupt(Long userId, Long notificationId) {
        return setNotificationAsExecutedWithGivenStatus(userId, notificationId, NotificationStatus.CORRUPT);
    }

    public NotificationResponse setNotificationAsPending(Long userId, Long notificationId) {
        return notificationRepository.findByIdAndUserId(notificationId, userId)
                .map(notification -> notification.setNotificationStatus(NotificationStatus.PENDING))
                .map(notificationRepository::saveAndFlush)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotificationNotFoundException(
                        String.format("Notification with id %s not found", notificationId)
                ));
    }

    public NotificationResponse setNotificationAsResending(Long userId, Long notificationId) {
        return notificationRepository.findByIdAndUserId(notificationId, userId)
                .map(Notification::incrementRetryAttempts)
                .map(notification -> notification.setNotificationStatus(NotificationStatus.RESENDING))
                .map(notificationRepository::saveAndFlush)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotificationNotFoundException(
                        String.format("Notification with id %s not found", notificationId)
                ));
    }

    private NotificationResponse setNotificationAsExecutedWithGivenStatus(
            Long userId, Long notificationId,
            NotificationStatus status
    ) {
        return notificationRepository.findByIdAndUserId(notificationId, userId)
//                .map(notification -> {
//                    notificationRepository.delete(notification);
//                    return notification;
//                })
                .map(notification -> notification.setNotificationStatus(status))
                .map(notificationRepository::saveAndFlush)
                .map(mapper::toDto)
                .orElseThrow(() -> new NotificationNotFoundException(
                        String.format("Notification with id %s not found", notificationId)
                ));
    }

    private List<List<Long>> splitRecipientIds(List<Long> list) {
        return CollectionUtils.splitList(list, nodeChecker.getAmountOfRunningNodes(applicationName));
    }
}
