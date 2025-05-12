package io.vladprotchenko.ensnotificationservice.utils;

import io.vladprotchenko.ensnotificationservice.model.entity.enums.NotificationType;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NotificationTypeConverter extends BaseEnumConverter<NotificationType> {

    public NotificationTypeConverter() {
        super(NotificationType.class);
    }
}
