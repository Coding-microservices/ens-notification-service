package io.vladprotchenko.ensnotificationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.vladprotchenko.ensnotificationservice.model.dto.kafka.NotificationKafka;
import io.vladprotchenko.ensnotificationservice.model.dto.response.NotificationResponse;
import io.vladprotchenko.ensnotificationservice.service.NotificationService;
import io.vladprotchenko.ensstartercore.exception.EnsServiceException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications API")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "Create notification", description = "Create notification by template")
    @PostMapping("/{templateId}")
    public ResponseEntity<String> notify(
            @RequestHeader Long userId,
            @PathVariable("templateId") Long templateId
    ) {
        return ResponseEntity.status(OK).body(notificationService.distributeNotifications(userId, templateId));
    }

    @Operation(summary = "Set as sent", description = "Set notification status as sent")
    @PostMapping("/{notificationId}/sent")
    public ResponseEntity<NotificationResponse> setNotificationAsSent(
            @RequestHeader Long userId,
            @PathVariable("notificationId") Long notificationId
    ) {
        return ResponseEntity.status(OK).body(notificationService.setNotificationAsSent(userId, notificationId));
    }

    @Operation(summary = "Set as error", description = "Set notification status as error")
    @PostMapping("/{notificationId}/error")
    public ResponseEntity<NotificationResponse> setNotificationAsError(
            @RequestHeader Long userId,
            @PathVariable("notificationId") Long notificationId
    ) {
        return ResponseEntity.status(OK).body(notificationService.setNotificationAsError(userId, notificationId));
    }

    @Operation(summary = "Set as corrupt", description = "Set notification status as corrupt")
    @PostMapping("/{notificationId}/corrupt")
    public ResponseEntity<NotificationResponse> setNotificationAsCorrupt(
            @RequestHeader Long userId,
            @PathVariable("notificationId") Long notificationId
    ) {
        return ResponseEntity.status(OK).body(notificationService.setNotificationAsCorrupt(userId, notificationId));
    }

    @Operation(summary = "Set as resending", description = "Set notification status as resending")
    @PostMapping("/{notificationId}/resending")
    public ResponseEntity<NotificationResponse> setNotificationAsResending(
            @RequestHeader Long userId,
            @PathVariable("notificationId") Long notificationId
    ) {
        return ResponseEntity.status(OK).body(notificationService.setNotificationAsResending(userId, notificationId));
    }

    @GetMapping("/")
    public ResponseEntity<List<NotificationKafka>> getNotificationsForRebalancing(
            @RequestParam(name = "pending", required = false, defaultValue = "10") Long pendingSec,
            @RequestParam(name = "new", required = false, defaultValue = "10") Long newSec,
            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size
    ) {
        return ResponseEntity.status(OK).body(
                notificationService.getNotificationsForRebalancing(pendingSec, newSec, size)
        );
    }
}
