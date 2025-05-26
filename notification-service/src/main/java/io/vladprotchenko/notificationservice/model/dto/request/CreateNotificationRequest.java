package io.vladprotchenko.notificationservice.model.dto.request;

import io.vladprotchenko.notificationapi.model.enums.NotificationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateNotificationRequest {

        NotificationType type;
        String title;
        String content;
        List<Long> recipientIds;
}
