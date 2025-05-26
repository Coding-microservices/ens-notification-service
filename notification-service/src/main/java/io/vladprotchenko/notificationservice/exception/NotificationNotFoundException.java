package io.vladprotchenko.notificationservice.exception;

import jakarta.persistence.EntityNotFoundException;

public class NotificationNotFoundException extends EntityNotFoundException {

    public NotificationNotFoundException(String message) {
        super(message);
    }
}
