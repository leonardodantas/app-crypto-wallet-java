package com.crypto.wallet.domain;

import lombok.Getter;

@Getter
public enum TypeOperation {

    SELL("SELL"), BUY("BUY");

    private final String type;

    TypeOperation(final String type) {
        this.type = type;
    }

}
