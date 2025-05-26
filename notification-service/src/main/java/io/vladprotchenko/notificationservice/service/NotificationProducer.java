package io.vladprotchenko.notificationservice.service;

import io.vladprotchenko.notificationapi.model.kafka.NotificationKafka;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {

    KafkaTemplate<String, NotificationKafka> kafkaTemplate;

    public void produce(NotificationKafka notification) {
        String topic = notification.getType().toString().toLowerCase() + "-notification";
        log.info("Sending notification to topic {}", topic);
        kafkaTemplate.send(topic, notification);
    }
}
