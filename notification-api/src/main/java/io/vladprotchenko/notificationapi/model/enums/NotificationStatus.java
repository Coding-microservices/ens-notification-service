package io.vladprotchenko.notificationapi.model.enums;

import lombok.Getter;

@Getter
public enum NotificationStatus implements EnumEntity {
    NEW("New"),
    PENDING("Pending"),
    SENT("Sent"),
    RESENDING("Resending"),
    ERROR("Error"),
    CORRUPT("Corrupt"),
    IN_PROCESS("In_process");

    private final String code;

    NotificationStatus(String code) {
        this.code = code;
    }

}
