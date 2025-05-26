package io.vladprotchenko.notificationservice.model.dto.response;

import lombok.Builder;

@Builder
public record SimpleResponseDto(
        String message
) {
}
