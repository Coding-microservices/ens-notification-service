package io.vladprotchenko.notificationservice.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateNotificationDto {

    @Schema(description = "Заголовок уведомления", example = "Обновление пароля")
    private String title;

    @Schema(description = "Текст сообщения", example = "Ваш новый код: 789012")
    private String message;

    @Schema(description = "Тип уведомления", example = "PUSH")
    private String type;

    @Schema(description = "Время запланированной отправки", example = "2025-05-20T12:00:00")
    private LocalDateTime scheduledAt;

    @Schema(description = "Список ID получателей", example = "[1, 2, 3]")
    private List<Long> recipientIds;
}
