package io.vladprotchenko.ensnotificationservice.model.mapper;

import io.vladprotchenko.ensnotificationservice.model.dto.kafka.NotificationKafka;
import io.vladprotchenko.ensnotificationservice.model.dto.request.NotificationRequest;
import io.vladprotchenko.ensnotificationservice.model.dto.response.NotificationResponse;
import io.vladprotchenko.ensnotificationservice.model.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends EntityMapper<Notification, NotificationRequest, NotificationResponse> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "retryAttempts", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Notification toEntity(NotificationRequest request);

//    @Mapping(target = "template", expression = "java(templateClient.getTemplateByUserIdAndTemplateId(notification.getUserId(), notification.getTemplateId()).getBody())")
    NotificationResponse toDto(Notification notification);

    NotificationKafka mapToKafka(Notification notification);

    NotificationKafka mapToKafka(NotificationResponse notificationResponse);

}
