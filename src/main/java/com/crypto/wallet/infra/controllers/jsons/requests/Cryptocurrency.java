package com.crypto.wallet.infra.controllers.jsons.requests;

public record Cryptocurrency(
        String name,
        double quantity
) {

    public static Cryptocurrency of(final String name, final double quantity) {
        return new Cryptocurrency(name, quantity);
    }
}