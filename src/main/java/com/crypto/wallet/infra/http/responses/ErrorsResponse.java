package com.crypto.wallet.infra.http.responses;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.UUID;

@Getter
public class ErrorsResponse {

    private final String id;
    private final String field;
    private final String message;

    private ErrorsResponse(FieldError field, String message) {
        this.id = UUID.randomUUID().toString();
        this.field = field.getField();
        this.message = message;
    }

    public static ErrorsResponse of(FieldError field, String message) {
        return new ErrorsResponse(field, message);
    }
}
