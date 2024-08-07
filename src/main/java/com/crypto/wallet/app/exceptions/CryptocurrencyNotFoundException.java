package com.crypto.wallet.app.exceptions;

public class CryptocurrencyNotFoundException extends RuntimeException {

    public CryptocurrencyNotFoundException(final String cryptocurrency) {
        super(String.format("Cryptocurrency %s not found", cryptocurrency));
    }
}
