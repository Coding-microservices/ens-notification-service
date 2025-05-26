package io.vladprotchenko.notificationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.vladprotchenko.notificationservice.model.dto.request.CreateNotificationRequest;
import io.vladprotchenko.notificationservice.model.dto.response.NotificationResponseDto;
import io.vladprotchenko.notificationservice.model.dto.response.SimpleResponseDto;
import io.vladprotchenko.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "API for notification sending")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<SimpleResponseDto> sendNotification(@RequestBody CreateNotificationRequest notificationRequest) {
        var response = notificationService.sendNotification(notificationRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(
            summary = "Get all notifications",
            description = "Returns a list of all notifications in the system"
    )
    public ResponseEntity<List<NotificationResponseDto>> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get notification by ID",
            description = "Returns a single notification by its unique identifier"
    )
    public ResponseEntity<NotificationResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getById(id));
    }

//    @PostMapping
//    @Operation(summary = "Create new notification")
//    public ResponseEntity<NotificationResponseDto> create(@RequestBody CreateNotificationDto dto) {
//        return ResponseEntity.ok(notificationService.createNotification(dto));
//        return null;
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Get notification by id")
//    public ResponseEntity<NotificationResponseDto> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(notificationService.getNotificationById(id));
//        return null;
//    }
//
//    @GetMapping
//    @Operation(summary = "Get list of all notifications")
//    public ResponseEntity<List<NotificationResponseDto>> getAll() {
//        return ResponseEntity.ok(notificationService.getAllNotifications());
//        return null;
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Update notification")
//    public ResponseEntity<NotificationResponseDto> update(@PathVariable Long id, @RequestBody UpdateNotificationDto dto) {
//        return ResponseEntity.ok(notificationService.updateNotification(id, dto));
//        return null;
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Delete notification")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        notificationService.deleteNotification(id);
//        return ResponseEntity.noContent().build();
//    }
}

