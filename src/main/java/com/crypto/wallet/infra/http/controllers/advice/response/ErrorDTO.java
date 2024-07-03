package com.crypto.wallet.infra.http.controllers.advice.response;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ErrorDTO {

    private final String uuid;
    private final String message;

    private ErrorDTO(String message) {
        this.uuid = UUID.randomUUID().toString();
        this.message = message;
    }

    public static ErrorDTO from(String message) {
        return new ErrorDTO(message);
    }
}
