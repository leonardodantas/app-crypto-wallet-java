package com.crypto.wallet.domain;

import lombok.Getter;

@Getter
public class Crypto {

    private String id;
    private TypeOperation typeOperation;

    private Crypto(final TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public static Crypto from(final TypeOperation typeOperation) {
        return new Crypto(typeOperation);
    }
}
