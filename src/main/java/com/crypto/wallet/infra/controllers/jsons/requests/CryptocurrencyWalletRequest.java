package com.crypto.wallet.infra.controllers.jsons.requests;

public record CryptocurrencyWalletRequest(
        String name,
        double quantity
) {

}
