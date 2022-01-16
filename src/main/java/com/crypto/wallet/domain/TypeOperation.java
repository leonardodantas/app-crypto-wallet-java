package com.crypto.wallet.domain;

public enum TypeOperation {

    SELL("SELL"), BUY("BUY");

    private final String type;

    TypeOperation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
