package com.crypto.wallet.infra.http.controllers.advice.response;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.UUID;

@Getter
public class ErrorsDTO {

    private final String id;
    private final String field;
    private final String message;

    private ErrorsDTO(FieldError field, String message) {
        this.id = UUID.randomUUID().toString();
        this.field = field.getField();
        this.message = message;
    }

    public static ErrorsDTO of(FieldError field, String message) {
        return new ErrorsDTO(field, message);
    }
}
