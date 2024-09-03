package com.crypto.wallet.infra.controllers.jsons.responses;

import org.springframework.validation.FieldError;

import java.util.UUID;

public record ErrorsResponse(
        String id,
        String field,
        String message
) {

    public static ErrorsResponse of(final FieldError field, final String message) {
        return new ErrorsResponse(UUID.randomUUID().toString(), field.getField(), message);
    }
}
