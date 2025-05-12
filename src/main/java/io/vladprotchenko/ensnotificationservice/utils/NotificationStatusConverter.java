package io.vladprotchenko.ensnotificationservice.utils;

import io.vladprotchenko.ensnotificationservice.model.entity.enums.NotificationStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NotificationStatusConverter extends BaseEnumConverter<NotificationStatus> {

    public NotificationStatusConverter() {
        super(NotificationStatus.class);
    }
}

