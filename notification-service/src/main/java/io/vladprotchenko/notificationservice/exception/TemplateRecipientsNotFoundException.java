package io.vladprotchenko.notificationservice.exception;

import jakarta.persistence.EntityNotFoundException;

public class TemplateRecipientsNotFoundException extends EntityNotFoundException {

    public TemplateRecipientsNotFoundException(String message) {
        super(message);
    }
}
