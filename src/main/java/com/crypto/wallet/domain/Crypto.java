package com.crypto.wallet.domain;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class Crypto {

    private String id;
    private TypeOperation typeOperation;

    private Crypto(final TypeOperation operation) {
        this.id = UUID.randomUUID().toString();
        this.typeOperation = operation;
    }

    public static Crypto from(final TypeOperation operation) {
        return new Crypto(operation);
    }
}
