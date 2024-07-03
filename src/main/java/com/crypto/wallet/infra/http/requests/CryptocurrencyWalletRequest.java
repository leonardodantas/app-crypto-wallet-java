package com.crypto.wallet.infra.http.requests;

public record CryptocurrencyWalletRequest(
        String name,
        double quantity
) {

}
