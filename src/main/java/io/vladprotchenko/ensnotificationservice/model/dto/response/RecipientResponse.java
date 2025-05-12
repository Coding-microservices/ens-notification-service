package io.vladprotchenko.ensnotificationservice.model.dto.response;

import lombok.Builder;

@Builder
public record RecipientResponse(
        Long id,
        String name,
        String email,
        String phoneNumber
) {
}
