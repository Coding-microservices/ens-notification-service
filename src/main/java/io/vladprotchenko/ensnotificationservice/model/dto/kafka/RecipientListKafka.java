package io.vladprotchenko.ensnotificationservice.model.dto.kafka;

import io.vladprotchenko.ensnotificationservice.model.dto.response.TemplateResponse;

import java.util.List;

public record RecipientListKafka(
        List<Long> recipientIds,
        TemplateResponse templateResponse,
        Long userId
) {
}
