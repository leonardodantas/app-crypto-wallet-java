package com.crypto.wallet.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static Crypto from(final String id, final String type) {
        return new Crypto(id, TypeOperation.valueOf(type));
    }
}
