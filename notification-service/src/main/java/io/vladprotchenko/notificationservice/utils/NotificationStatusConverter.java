package io.vladprotchenko.notificationservice.utils;

import io.vladprotchenko.notificationapi.model.enums.NotificationStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NotificationStatusConverter extends BaseEnumConverter<NotificationStatus> {

    public NotificationStatusConverter() {
        super(NotificationStatus.class);
    }
}
