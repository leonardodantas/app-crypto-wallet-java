package com.crypto.wallet.infra.http.responses;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ErrorResponse {

    private final String uuid;
    private final String message;

    private ErrorResponse(String message) {
        this.uuid = UUID.randomUUID().toString();
        this.message = message;
    }

    public static ErrorResponse from(String message) {
        return new ErrorResponse(message);
    }
}
