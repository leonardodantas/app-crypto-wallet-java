package com.crypto.wallet.infra.controllers.jsons.responses;

import java.util.UUID;

public record ErrorResponse(
        String uuid,
        String message
) {

    public static ErrorResponse from(String message) {
        return new ErrorResponse(UUID.randomUUID().toString(), message);
    }
}
