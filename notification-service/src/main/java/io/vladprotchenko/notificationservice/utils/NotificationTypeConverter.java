package io.vladprotchenko.notificationservice.utils;

import io.vladprotchenko.notificationapi.model.enums.NotificationType;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NotificationTypeConverter extends BaseEnumConverter<NotificationType> {

    public NotificationTypeConverter() {
        super(NotificationType.class);
    }
}
