package io.vladprotchenko.ensnotificationservice.exception;

import jakarta.persistence.EntityNotFoundException;

public class TemplateRecipientsNotFoundException extends EntityNotFoundException {

    public TemplateRecipientsNotFoundException(String message) {
        super(message);
    }
}
